package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AbnTlAbnormalityDao;
import com.akranta.tpm.dao.sql.AbnTlAbnhistorydtlSql;
import com.akranta.tpm.dao.sql.AbnTlAbnormalitySql;
import com.akranta.tpm.dao.sql.AbnTlDtlSql;
import com.akranta.tpm.dao.sql.AbnormalityReportSqls;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.DailyActivityMstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.WomTlWomstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AbnTlAbnhistorydtl;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AbnTlDtl;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.DocTypeConstants;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public  class AbnTlAbnormalityDaoImpl implements AbnTlAbnormalityDao {


	private DBActionTemplate dbActionTemplate; 
	private AbnTlDtlSql abnTlDtlSql;
	private AbnTlAbnhistorydtlSql abnTlAbnhistorydtlSql;
	private AbnTlAbnormalitySql abnTlAbnormalitySql =  null;
	AbnormalityServiceApi abnServiceApi;
	FunctionCallApi fnCallApi;
	public AbnTlAbnormalityDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		try{
		this.dbActionTemplate = dbActionTemplate;
		abnTlDtlSql= new AbnTlDtlSql();
		abnTlAbnormalitySql = new AbnTlAbnormalitySql();
		abnTlAbnhistorydtlSql = new AbnTlAbnhistorydtlSql();
//		abnServiceApi = new AbnormalityServiceApi();
//		fnCallApi = new FunctionCallApi();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void AbnTlAbnormalityDaoImplJwt(String JwtToken) 
	{
		try{
		abnServiceApi = new AbnormalityServiceApi(JwtToken);
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
	public AbnTlAbnormality createAbnormalitypsi(AbnTlAbnormality abnTlAbnormality,AbnTlAbnormality oldAbnTlAbnormality, AbnormalityBean abn)throws Exception ,BusinessApplicationExceptions { 

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		//AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		 	String elementId = abnTlAbnormality.getAbnmElementid();
		 	String location = null;
		 	String seqIdentfr = AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY;

		 	if( elementId != null && elementId.length() > 10  ){
		 		location = elementId.substring(11, 21); /* location id starts from 11  */
		 		CommonMessage.debugMsg("The Location is"+location);
		 		seqIdentfr += location;
		 	}
		 	CommonMessage.debugMsg("The Abnormality is"+abnTlAbnormality.getSaveArray());
		 	
			abnTlAbnormality.setAbnmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfr,10,"AB","YYMM" ,"Y")); // set the sequnce number
  /*		String abnTagCode=getTagCode(abnTlAbnormality.getAbnmTagclassid());
	 	   if(abnTagCode.equals("RED"))
			{
				if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmRefdocid()))
				{
					//WomTlWomst womTlWomst = new WomTlWomst();
					//updateWorkOrder(abnTlAbnormality,womTlWomst,sqls);	
				}
				else{
					//insertWorkOrder(abnTlAbnormality,sqls);
				}
			}
			*/
			CommonMessage.debugMsg("The After Abnormality is"+abnTlAbnormality.getSaveArray());

			String sql = actionPlanEntry(abnTlAbnormality,"I");

			sqls.add(sql);
			sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table
			
		/*	if(abnTlDtl!=null)
			{
				abnTlDtl.setAbndAbnormalityid(abnTlAbnormality.getAbnmKeyid());
				abnTlDtl.setAbndKeyid(dbActionTemplate.getSequenceNumber(AbnTlDtlSql.TBL_ABN_TL_DTL,11,"ABD","YYMM" ,"Y")); // set the sequnce number 
				sqls.add(AbnTlDtlSql.getInsertSql(abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray())); // 
			}*/
			
			//for team
			//if (abnTlAbnormality.getTeamList().size()>0) 
			
			//CommonFilterSqls.getTeamLinkSqls(abnTlAbnormality.getTeamList(),DocTypeConstants.abnm,abnTlAbnormality.getAbnmKeyid(),sqls,dbActionTemplate);
		
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			//CommonMessage.debugMsg("after dao impl");
			
	
		return abnTlAbnormality;
	}
	
	private String actionPlanEntry(AbnTlAbnormality abnTlAbnormality,String type) throws Exception {

		String sql = "";
		
		GenTlActionplanmst genTlActionplanmst = new GenTlActionplanmst();
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql();
		
		String createdby=abnTlAbnormality.getAbnmCreatedby();
		CommonMessage.debugMsg("The createdby"+createdby);
		actionPlanFillValues(genTlActionplanmst,abnTlAbnormality);
		
		if("I".equals(type))
		{
			String elementId = genTlActionplanmst.getAplmElementid();
		 	String location = null;
		 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);
			genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf,10,"AP","","")); 
			sql = GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(), genTlActionplanmst.getSaveArray());
		}
		else if("U".equals(type))
		{
			genTlActionplanmst.setAplmStatus("C");
			sql = "UPDATE "+GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST+" SET APLM_STATUS = 'C' WHERE APLM_DETAILREFID = '"+abnTlAbnormality.getAbnmKeyid()+"'";
//			sql = GenTlActionplanmstSql.getUpdateSql(genTlActionplanmstSql.getAplmDbFields(), genTlActionplanmst.getSaveArray());
		}
		
		return sql;
	}

	
	
	private GenTlActionplanmst actionPlanFillValues(GenTlActionplanmst genTlActionplanmst,AbnTlAbnormality abnTlAbnormality){
		genTlActionplanmst.setAplmCreatedby(abnTlAbnormality.getAbnmCreatedby());
		genTlActionplanmst.setAplmActive("Y");
		genTlActionplanmst.setAplmCreatedon(abnTlAbnormality.getAbnmCreatedon());
		genTlActionplanmst.setAplmDetailrefid(abnTlAbnormality.getAbnmKeyid());
		genTlActionplanmst.setAplmElementid(abnTlAbnormality.getAbnmElementid());
		genTlActionplanmst.setAplmFlid(abnTlAbnormality.getAbnmFlid());
		genTlActionplanmst.setAplmMaintask("{}");
		genTlActionplanmst.setAplmMasterrefid("{}");
		genTlActionplanmst.setAplmModifiedon(abnTlAbnormality.getAbnmModifiedon());
		genTlActionplanmst.setAplmPillarid("{}");
		genTlActionplanmst.setAplmRefdoctype("ABN");
		genTlActionplanmst.setAplmRemarks(abnTlAbnormality.getAbnmRemarks());
		genTlActionplanmst.setAplmStatus(abnTlAbnormality.getAbnmStatus());
		
		if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate()))
			genTlActionplanmst.setAplmPlandate(abnTlAbnormality.getAbnmDetectiondate());
		
		genTlActionplanmst.setAplmTempfiled2("-");
		genTlActionplanmst.setAplmTempfiled3("-");
		genTlActionplanmst.setAplmTempfiled4("-");
		genTlActionplanmst.setAplmTempfiled5("-");
		
		return genTlActionplanmst;
	}
	
	

	public AbnTlAbnormality update(AbnTlAbnormality abnTlAbnormality,WomTlWomst womTlWomst,AbnTlAbnhistorydtl abnTlAbnhistorydtl)	throws Exception,BusinessApplicationExceptions { 
		
	    CommonMessage.debugMsg("Inside the DaoImpl Update");
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
	//	boolean executeFlag = true;
		//AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql();
		if("C".equals(abnTlAbnormality.getAbnmStatus())){
			CommonMessage.debugMsg("Abn Update Dao Impl : "+abnTlAbnormality.getAbnmWomasterid());
			String actionPlanSql =  abnTlAbnormalitySql.getActionPlanCount(abnTlAbnormality);
			String actionplancnt = dbActionTemplate.getSingleValue(actionPlanSql);
			int cnt = Integer.parseInt(actionplancnt);
			if(cnt > 0)
			{

				throw new BusinessApplicationExceptions("ACTIONPLAN_PENDING");
				
			}
			//else{
				String sql = actionPlanEntry(abnTlAbnormality,"U");
				sqls.add(sql);
			//}
			

		}
			String abnTagCode=getTagCode(abnTlAbnormality.getAbnmTagclassid());
			/*String checkAbn = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.checkAbnQuery());
			if(UIUtils.isValidKeyId(checkAbn));
			{
				if(checkAbn.equals("8"))
				{
					if(abnTagCode.equals("RED"))
					{
						WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
						if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmRefdocid()))
						{
							fillWorkOrder(womTlWomst,abnTlAbnormality);								
							womTlWomst.setWomsKeyid(abnTlAbnormality.getAbnmRefdocid());
							CommonMessage.debugMsg("Activity Id : "+womTlWomst.getWomsActivityid());
							String updateWOSql = SqlUtils.getUpdateSql(TableNames.TBL_WOM_TL_MST,womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray());
							if(UIUtils.isValidKeyId(updateWOSql))
								updateWOSql += " where WOMS_KEYID = ?";
							Object [] updateWO	= {womTlWomst.getWomsKeyid()};
							int [] updateWODatas =  {Types.VARCHAR};
							sqls.add(updateWOSql);
							valueList.add(updateWO);
							dataTypes.add(updateWODatas);
							charList.add("Q");						
							
						}
						else
						{
							executeFlag = false;
							insertWorkOrder(abnTlAbnormality,sqls);							
						}							
						
					}
					if(executeFlag)
					{
						String updateAbnMstSql = SqlUtils.getUpdateSql(AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY,abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray());
						if(UIUtils.isValidKeyId(updateAbnMstSql))
							updateAbnMstSql += "where ABNM_KEYID = ?";
						Object [] updateAbnMst	= {abnTlAbnormality.getAbnmKeyid()};
						int [] updateAbnMstDatas =  {Types.VARCHAR};
						sqls.add(updateAbnMstSql);
						valueList.add(updateAbnMst);
						dataTypes.add(updateAbnMstDatas);
						charList.add("Q");
						
						if(abnTlDtl!=null)
						{
							String updateAbnDtlSql = SqlUtils.getUpdateSql(AbnTlDtlSql.TBL_ABN_TL_DTL,abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray());
							if(UIUtils.isValidKeyId(updateAbnDtlSql))
								updateAbnDtlSql += "where ABND_KEYID = ?";
							Object [] updateAbnDtl	= {abnTlDtl.getAbndKeyid()};
							int [] updateAbnDtlDatas =  {Types.VARCHAR};
							sqls.add(updateAbnDtlSql);
							valueList.add(updateAbnDtl);
							dataTypes.add(updateAbnDtlDatas);
							charList.add("Q");
							sqls.add(AbnTlDtlSql.getUpdateSql(abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray())); // 
						}
						if(abnTlAbnormality.getAbnmStatus().equals("C"))
						{
							String insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEABN";					
							String parameterCode = womTlWomst.getWomsLoss();	
							if(UIUtils.isValidKeyId(parameterCode))
							{
								if(parameterCode.equals("UNPLANNED MAINTENANCE"))
									parameterCode = "UPM";
								else if(parameterCode.equals("JH TAG REMOVAL"))
									parameterCode = "JHTAGREMOVE";
								else if(parameterCode.equals("M AND A"))
									parameterCode = "MANDA";
							}
							String occuredDate = womTlWomst.getWomsOccurreddate();
							String prodStartDate = abnTlAbnormality.getAbnmWoendtime();
							
							if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17)
								occuredDate = occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
							
							prodStartDate = prodStartDate + " 23:59";
							CommonMessage.debugMsg(womTlWomst.getWomsMachineid() + " : "+occuredDate + " : "+prodStartDate+" : "+womTlWomst.getWomsKeyid()+ " : "+parameterCode + " : "+womTlWomst.getWomsCreatedby());
							Object [] insertPCSDatas = {womTlWomst.getWomsMachineid(),occuredDate,prodStartDate,womTlWomst.getWomsKeyid(),parameterCode,womTlWomst.getWomsCreatedby()};
							int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
							sqls.add(insertPCSSql);
							valueList.add(insertPCSDatas);
							dataTypes.add(insertPCSTypes);
							charList.add("P");
						}
					}
					else
					{
						sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray()));
						
						if(abnTlDtl!=null)
						{
							sqls.add(AbnTlDtlSql.getUpdateSql(abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray())); // 
						}
					}
				}
				else
				{*/
					if(abnTagCode.equals("RED"))
					{
						if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmRefdocid()))
						{
							//updateWorkOrder(abnTlAbnormality,womTlWomst,sqls);	
						}
						else{
							//insertWorkOrder(abnTlAbnormality,sqls);
						}
					}
					if(abnTagCode.equals("WHITE"))
					{
//						if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmRefdocid()));
//							sqls.add(AbnTlAbnormalitySql.inactiveWOSql(abnTlAbnormality.getAbnmRefdocid()));
						abnTlAbnormality.setAbnmRefdocid("{}");
					}
						AbnTlAbnormality abnTlAbnormalityHistory =  getAbnData(abnTlAbnormality.getAbnmKeyid());
						boolean isHisInsert = checkAbnFields(abnTlAbnormality,abnTlAbnormalityHistory);
						CommonMessage.debugMsg("isHisInsert......"+isHisInsert);
//						if(isHisInsert)
							sqls.add("INSERT INTO ABN_TL_ABNHISTORY SELECT * FROM ABN_TL_ABNORMALITY WHERE ABNM_KEYID='"+abnTlAbnormality.getAbnmKeyid()+"'");
						sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray()));
						
						/*if(abnTlDtl!=null)
						{
							sqls.add(AbnTlDtlSql.getUpdateSql(abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray())); // 
						}*/
						CommonMessage.debugMsg("sqls..."+sqls);
				
			
//						AbnTlAbnhistorydtl abnTlAbnhistorydtl = abnTlAbnormality.getAbnTlAbnhistorydtl();
						if(abnTlAbnhistorydtl != null){
							abnTlAbnhistorydtl.setAbnhKeyid(dbActionTemplate.getSequenceNumber(AbnTlAbnhistorydtlSql.TBL_ABN_TL_ABNHISTORYDTL,11,"ABH","YYMM" ,"Y")); // set the sequnce number
							sqls.add(AbnTlAbnhistorydtlSql.getInsertSql(abnTlAbnhistorydtlSql.getAbnhDbFields(), abnTlAbnhistorydtl.getSaveArray())); //
						}
				//}
				
			//}
					
					
					
				
			
			/*if(executeFlag)
			{
				char [] sqlType = new char[charList.size()];
				for(int c=0;c<sqlType.length;c++)
				{
					sqlType[c] = charList.get(c).charAt(0);
					CommonMessage.debugMsg(c +" : "+sqlType[c]);
				}
				dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
			}
			else*/
						//CommonMessage.debugMsg("TEAM:"+abnTlAbnormality.getTeamList().size());
						
					//for team
					//if (abnTlAbnormality.getTeamList() !=null && abnTlAbnormality.getTeamList().size()>0)  
						//CommonFilterSqls.getTeamLinkSqls(abnTlAbnormality.getTeamList(),DocTypeConstants.abnm,abnTlAbnormality.getAbnmKeyid(),sqls,dbActionTemplate);
					
				dbActionTemplate.executeStatements(sqls);
			
		
		
		return abnTlAbnormality;
	}
	
	

	private boolean checkAbnFields(AbnTlAbnormality abnTlAbnormality,AbnTlAbnormality abnTlAbnormalityHistory) 
	{
			
		CommonMessage.debugMsg("flid..."+abnTlAbnormality.getAbnmFlid()+"~~~~~~"+abnTlAbnormalityHistory.getAbnmFlid());
		CommonMessage.debugMsg("date..."+abnTlAbnormality.getAbnmDate()+"~~~~~~"+abnTlAbnormalityHistory.getAbnmDate());
		CommonMessage.debugMsg("tagclas..."+abnTlAbnormality.getAbnmTagclassid()+"~~~~~~"+abnTlAbnormalityHistory.getAbnmTagclassid());
		if(!abnTlAbnormality.getAbnmFlid().equals(abnTlAbnormalityHistory.getAbnmFlid()))
			return true;
		else if(!abnTlAbnormality.getAbnmTagclassid().equals(abnTlAbnormalityHistory.getAbnmTagclassid()))
			return true;
		return false;
	}

	public AbnTlAbnormality delete(AbnTlAbnormality abnTlAbnormality)throws Exception 
	{
		CommonMessage.debugMsg("Inside Dao impl delete");
		List<String> sqls = new ArrayList<String>();
//		AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql();
		CommonMessage.debugMsg(abnTlAbnormality.getAbnmStatus());
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmKeyid()))
		{
			String status = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY, "ABNM_STATUS", "ABNM_KEYID", abnTlAbnormality.getAbnmKeyid());
			CommonMessage.debugMsg(status);
			if(UIUtils.isValidKeyId(status))
			{
				if(status.equals("C"))
					throw new BusinessApplicationExceptions("COMPLETED,");
			}
		}
	
		try {
			String abnTagCode=null;
			if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmTagclassid()))
				abnTagCode = getTagCode(abnTlAbnormality.getAbnmTagclassid());
			if(UIUtils.isValidKeyId(abnTagCode))
			{
				if(abnTagCode.equals("RED"))
				{
//					if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmRefdocid()));
//						sqls.add(AbnTlAbnormalitySql.inactiveWOSql(abnTlAbnormality.getAbnmRefdocid()));
				}
			}
			if(abnTlAbnormality.getAbnTlDtl() != null)
			{
				if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnTlDtl().getAbndKeyid()))
				{
					sqls.add(AbnTlDtlSql.getInactiveSql(abnTlDtlSql.getAbndDbFields(), abnTlAbnormality.getAbnTlDtl().getAbndKeyid()));
				}
			}
			//sqls.add(AbnTlAbnormalitySql.getDeleteSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray()));
			sqls.add(AbnTlAbnormalitySql.inactiveAbnSql(abnTlAbnormality.getAbnmKeyid()));
			//for team
			//if (abnTlAbnormality.getTeamList().size()>0)  
			CommonFilterSqls.getTeamLinkSqls(abnTlAbnormality.getTeamList(),DocTypeConstants.abnm,abnTlAbnormality.getAbnmKeyid(),sqls,dbActionTemplate);
		
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return abnTlAbnormality;
	}

	@Override
	public AbnTlAbnormality select(String AbnmTagno) throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:"+AbnmTagno);
		AbnTlAbnormality abnTlAbnormality = new AbnTlAbnormality();
		
		String sql = AbnTlAbnormalitySql.getabnfrmdatasql();
				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { AbnmTagno };
		abnTlAbnormality.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+abnTlAbnormality.getAbnmKeyid());
		return  abnTlAbnormality;
	}
	
	@Override
	public List<String[]> getAbnUpdatedRow(String keyId) throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("KeyID:"+keyId);
		
		String sql = AbnTlAbnormalitySql.getUpdatedRowAbn(keyId);
				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		List<String[]> updatedRow = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("DAO Query:"+updatedRow.get(0)[2]);
		return  updatedRow;
	}

	@Override
	public List<String[]> getModifyFormDao(CommonFilter commonFilter,String abnStatus,String pillarType)throws Exception {
		try
		{
			//List<String> paramValues = getFilterParamValues(commonFilter);
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			condParms += "EXCEL="+"NOEXCEL"+";";
			if(UIUtils.isValidKeyId(commonFilter.getEmpch()))
				condParms += "EMPLOYEEKEYID="+commonFilter.getEmpch()+";";
			CommonMessage.debugMsg("inside condParms"+condParms);
			condParms+="STATUSNEW="+commonFilter.getAbnCatch()+";";
			if(abnStatus.equals("accecpt"))
			{
				condParms+="ACCECPTENCE=Y;";
			}
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			boolean isInteger = false;
			String fncName="";
			String TAGID="";
			CommonMessage.debugMsg(pillarType + " rtuhngf");
			ComboFilter cmbabnStatus = new ComboFilter();
			CommonMessage.debugMsg("inside getmodifyformdao");
			//condParam +="EMPLOYEEKEYID="+commonFilter.getEmpch()+";";
			//paramValues.add(condParam1);
			CommonMessage.debugMsg(commonFilter.getAbnStatus().getId() + " srtaffhghs");
			if(commonFilter.getAbnStatus() != null){
				if(commonFilter.getAbnStatus().getId()!= null){
					
					CommonMessage.debugMsg(commonFilter.getAbnStatus().getId() + " gsatus");
				}
			}
				
			if(pillarType.equalsIgnoreCase("SHE"))
			{
				//fncName="ABN_PC_ABNORMALITY.HSE_FN_MODIFICATION";
				fncName="JHN_FN_GetSqlForFillSpread_SB";
				//String sql = AbnTlAbnormalitySql.getHseModifyQuery();
				//CommonMessage.debugMsg(sql);
				//return dbActionTemplate.getDataList(sql);				
			}
			else if(pillarType.equalsIgnoreCase("JH"))
			{	fncName="JHN_FN_GetSqlForFillSpread_SB";
			}
			
			if(abnStatus.equals("removal")||abnStatus.equals("modification"))
				{
				
				abnStatus="'P'";
				}
			
			else if(abnStatus.equals("bulkRemoval"))
			{
			abnStatus="'P'";
			
			fncName="ABN_PC_ABNORMALITY.ABN_FN_BULKTAGREMOVAL";
			CommonMessage.debugMsg("abnStatus....!!..."+abnStatus);
			}
			else if(abnStatus.equals("accecpt"))
			{
				abnStatus="'C'";
			}
			else if(abnStatus.equals("view"))
			{
				CommonMessage.debugMsg(commonFilter.getAbnStatus().getId() + " ghghgstatus");
				if(commonFilter.getAbnStatus()!= null){
					if(commonFilter.getAbnStatus().getId()!= null){
						//abnStatus=commonFilter.getStatus();
					}
				}
				fncName="ABN_FN_ABNORMALITYVIEW_SB";
				abnStatus=commonFilter.getStatus();
				//TAGID=commonFilter.getType();
				//CommonMessage.debugMsg(commonFilter.getType() + " tagid");
				
			}
			else
			{
				CommonMessage.debugMsg(abnStatus + "  status");
				{abnStatus="'P','C','W'";}
			}
			
			cmbabnStatus.setId(abnStatus);
			commonFilter.setAbnStatus(cmbabnStatus);
		    //commonFilter.setType(TAGID);
			//CommonMessage.debugMsg("abnStatus....!!..."+abnStatus);
			//CommonMessage.debugMsg("TAGID.!!..."+TAGID);
			//CommonMessage.debugMsg(" commonFilter.setType(TAGID).!!..."+  commonFilter.getType());
			
//			List<String[]> dataList =  dbActionTemplate.processFunctionCalls(fncName, paramValues);
			List<String[]> dataList =  fnCallApi.callFunction(fncName, paramValues,3,false);
			
				
			CommonMessage.debugMsg("Before funct");
			if( commonFilter.getViewClick() == 'Y'){
				CommonMessage.debugMsg("After funct");
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("Total Count:"+totalCnt);
				isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
		
	}

	@Override
	public AbnTlAbnormality abnformfill(String abnId) 
	{
		try
		{
			CommonMessage.debugMsg("Inside the dao impl");
			CommonMessage.debugMsg("ID:"+abnId);
			AbnTlAbnormality abnTlAbnormality = new AbnTlAbnormality();
			
			String sql = AbnTlAbnormalitySql.getabnfrmdatasql();
					
			//CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { abnId };
			abnTlAbnormality.setSaveArray(dbActionTemplate.getDataArr(sql,args));
			//CommonMessage.debugMsg("DAO Query:"+abnTlAbnormality);
			return  abnTlAbnormality;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	public String getShift(String flid,String time)throws BusinessApplicationExceptions,Exception
	{
		try
		{			
			String sql = BdmTlMstSql.getShiftFunction();	
			
			String locnFlidSql = AbnTlAbnormalitySql.getLocnFLID(flid);
			String locnFLid = dbActionTemplate.getSingleValue(locnFlidSql);
			List<String> paramValues =new ArrayList<String>();
			paramValues.add(locnFLid);
			paramValues.add("{}");
			paramValues.add("{}");
			paramValues.add(time);
			
			 List<String[]> shift = dbActionTemplate.processFunctionCalls( sql,paramValues);	
			 if(shift!=null && shift.size()>0)
				 return shift.get(0)[0];
			 else
				  throw new BusinessApplicationExceptions("Shift");
		}
		catch(Exception e)
		{
			throw new BusinessApplicationExceptions(e.getMessage());
			
		}
	}
	
	public String getTagCode(String tagclassId)throws Exception
	{
		try
		{ CommonMessage.debugMsg("The tagclassId:::::"+tagclassId);
			String abnTagCode = dbActionTemplate.getSingleValue("ABN_TL_TAGMST","TAGM_CODE","TAGM_KEYID",tagclassId);
			CommonMessage.debugMsg("The abnTagCode:::::"+abnTagCode);
			return abnTagCode;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception while getting tagCode "+e.getMessage());
		}
		return null;
	}
	
	@Override
	public String getAbnTypeFlag(String abtmKeyid) throws Exception 
	{
		try
		{
			String abnTypeFlag = dbActionTemplate.getSingleValue("ABN_TL_TYPEMST","ABTM_TYPEFLAG","ABTM_KEYID",abtmKeyid);
			return abnTypeFlag;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception while getting typeFlag "+e.getMessage());
		}
		return null;
	}
	
	
	public List<String[]> getAbnormalityDetails(String keyId) throws Exception 
	{
		//String sql = AbnormalityReportSqls.getAbnDetailsSql(keyId);
		//CommonMessage.debugMsg(sql);
		//return dbActionTemplate.getDataList(sql);
		return null;
	}

	@Override
	public List<String> getTgtDateFromConfig() throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sql= new ArrayList<String>();
		String redTag = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST","CNFM_SETTINGVALUE","CNFM_CODE","REDTGT");
		String whiteTag= dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST","CNFM_SETTINGVALUE","CNFM_CODE","WHITETGT");
		sql.add(redTag);
		sql.add(whiteTag);
		
		return sql;
	}
	
	

	@Override
	public Workbook abnExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String reportType) throws Exception {
		// TODO Auto-generated method stub
		  ResultSet rs = null;
		   try
		   {
			   	rs =   getAbnormalityResultSet(commonFilter);
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
				return excelUtils.writeToExcel(rs,reportType,3, 0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs == null ? null : rs.getStatement().getConnection());	   
		   }
	}
	
	
	private List<String> getFilterParamValues(CommonFilter commonFilter)
	{
		List<String> paramValues = new ArrayList<String>();
		CommonMessage.debugMsg("GTE PARAM VAL= "+commonFilter.getFromDate());
		
		String  condParam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		CommonMessage.debugMsg("condParam"+condParam);
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg("condParam"+condParam);
		if("Y".equals(commonFilter.getAcceptenceRequired()))
			condParam += "ACCECPTENCE=Y;";
		String abnType = commonFilter.getDocType();
		if("SHE".equals(abnType))
			condParam += "REFDOCTYPE=SHE;";
		String afeem=commonFilter.getAbnImp();
		if("AFEEM".equals(afeem))
			condParam += "AFEEM=AFEEM;";
		
		condParam +="STATUSNEW="+commonFilter.getAbnCatch()+";";
		condParam +="EXCEL="+commonFilter.getAbnImp()+";";
		
		
		
		
		
		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		CommonMessage.debugMsg("GTE PARAM VAL= "+paramValues.get(0));
		return paramValues;
		
	}
	
	private ResultSet getAbnormalityResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg(" Status :"+commonFilter.getStatus());
		if ( commonFilter.getStatus() != null && commonFilter.getStatus().equals("view")) {
			CommonMessage.debugMsg(commonFilter.getStatus()+"view");
			return  dbActionTemplate.NewdbFunctionCall2("ABN_FN_ABNORMALITYVIEW", paramValues);
		}
		else {
			CommonMessage.debugMsg(commonFilter.getStatus()+"NOTview");
			return  dbActionTemplate.NewdbFunctionCall2("JHN_FN_GetSqlForFillSpread", paramValues);
		}
		
	}
	@Override
	public AbnTlDtl selectHseData(String abnKeyId,String flag) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("ID:"+abnKeyId);
		CommonMessage.debugMsg("FLAG : "+flag);
		AbnTlDtl abnTlDtl = new AbnTlDtl();
		String sql = null;
		String queryFlag = null;
		if(CommonFunctions.isValidKeyId(flag))
		{
			queryFlag = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.getCountHseSelectQueryMST(abnKeyId));
			sql = AbnTlAbnormalitySql.getHseSelectQueryMST();
		}
		else
		{
			queryFlag = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.getCountHseSelectQuery(abnKeyId));
			sql = AbnTlAbnormalitySql.getHseSelectQuery();
		}
				
/*
		Object [] args =  new Object [] { abnKeyId };
		try{
		abnTlDtl.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		}catch(Exception e) {}
*/

		if(UIUtils.isValidKeyId(queryFlag))
		{
			if(Integer.parseInt(queryFlag)>0)
			{
				Object [] args =  new Object [] { abnKeyId };
				abnTlDtl.setSaveArray(dbActionTemplate.getDataArr(sql,args));
				CommonMessage.debugMsg("DAO Query:"+abnTlDtl.getAbndKeyid());
			}
		}
		return  abnTlDtl;
	}
	public String checkTag() throws Exception
	{
		String tagValue = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.checkTagSql());
		
		return tagValue;
		
	}
	public String getElementID(String flid) throws Exception {
		String sql = "SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID = '"+flid+"'";
		String elementId = dbActionTemplate.getSingleValue(sql);		
		return elementId;
	}

	public String checkGen() throws Exception
	{
		String tagValue = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.checkAbnQuery());
		
		return tagValue;
		
	}
	public String checkDirectEntry(String woID)throws Exception
	{
		String directEntry = dbActionTemplate.getSingleValue(TableNames.TBL_WOM_TL_MST,"WOMS_DIRECTENTRY","WOMS_KEYID",woID);
		
		return directEntry;
	}
	public String showAbnQuery()throws Exception
	{
		String showAbn = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.showAbnQuery());
		String yyEnable = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "YYFORABN");
		String woEnable = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "WOFORABN");
		if(!UIUtils.isValidKeyId(yyEnable))
			yyEnable = "N";
		if(!UIUtils.isValidKeyId(woEnable))
			woEnable = "N";
		
		if(UIUtils.isValidKeyId(showAbn))
			return showAbn+","+yyEnable+","+woEnable;
		else
			return "N"+","+yyEnable+","+woEnable;
	}
	/** For Work Order Creation **/
	public List<String> insertWorkOrder(AbnTlAbnormality abnTlAbnormality,List<String> sqls) 	throws Exception,BusinessApplicationExceptions {
		WomTlWomst womTlWomst = new WomTlWomst();
		fillWorkOrderInsert(womTlWomst,abnTlAbnormality);	
	/*	String checkWOConfig = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "MSRCOMPLETEDDATE");
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
		WomTlWomstSql womTlWomstSql = new WomTlWomstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
				
			womTlWomst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_BAL_WOM_TL_WOMST, 10, "MW", "YYMM", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
			
			abnTlAbnormality.setAbnmWomasterid(womTlWomst.getWomsKeyid());
			abnTlAbnormality.setAbnmRefdocid(womTlWomst.getWomsKeyid());
			abnTlAbnormality.setAbnmRefdoctype("WOM");
			List<String[]>  overlapFlag = isMSRExist(womTlWomst);
			if(overlapFlag.size()>0)
			{
				throw new BusinessApplicationExceptions("msrOverlap,");
			}
			sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add insert sql for master table
		
		return sqls;
	}
	public List<String>  updateWorkOrder(AbnTlAbnormality abnTlAbnormality,WomTlWomst womTlWomst,List<String> sqls) 	throws Exception,BusinessApplicationExceptions {
		//WomTlWomst womTlWomst = new WomTlWomst();
		CommonMessage.debugMsg("bEFORE fILL : "+womTlWomst.getWomsOccurreddate());
		fillWorkOrder(womTlWomst,abnTlAbnormality);
		CommonMessage.debugMsg("AFTER fILL : "+womTlWomst.getWomsOccurreddate());
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
		
			womTlWomst.setWomsKeyid(abnTlAbnormality.getAbnmRefdocid());
			List<String[]>  overlapFlag = isMSRExist(womTlWomst);
			if(overlapFlag.size()>0)
			{
				throw new BusinessApplicationExceptions("msrOverlap,");
			}
			CommonMessage.debugMsg("Inside Update WO : "+womTlWomst.getWomsKeyid());
			//abnTlAbnormality.setAbnmWomasterid(womTlWomst.getWomsKeyid());			
			sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
		
		return sqls;
	}
	private void fillWorkOrder(WomTlWomst womTlWomst,AbnTlAbnormality abnTlAbnormality) 	throws Exception {
		//womTlWomst.setWomsActive(abnTlAbnormality.getAbnmActive());	
		//womTlWomst.setWomsCreatedon(abnTlAbnormality.getAbnmCreatedon());
		CommonMessage.debugMsg("Inside Fill Work Order" + womTlWomst.getWomsKeyid());
		womTlWomst.setWomsModifiedon(abnTlAbnormality.getAbnmModifiedon());
		
		String reportedDate = Constants.passNullDate;
		CommonMessage.debugMsg("reportedDate " +reportedDate);
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDetectiondate()))
		{		
			reportedDate =abnTlAbnormality.getAbnmDetectiondate();
		}	
		CommonMessage.debugMsg("reportedDate 2 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsLoss()))
		womTlWomst.setWomsLoss("{}");
	CommonMessage.debugMsg("reportedDate 3 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()))
		womTlWomst.setWomsMouldid("{}");
	CommonMessage.debugMsg("reportedDate 4 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsWorkcenterid()))
		womTlWomst.setWomsWorkcenterid("{}");
	CommonMessage.debugMsg("reportedDate 5 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsAlarmno()))
		womTlWomst.setWomsAlarmno("{}");
	CommonMessage.debugMsg("reportedDate 6 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsSubassemblyid()))
		womTlWomst.setWomsSubassemblyid("{}");
	CommonMessage.debugMsg("reportedDate 7 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsFailuretypeid()))
		womTlWomst.setWomsFailuretypeid("{}");
	CommonMessage.debugMsg("reportedDate 8 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsSpareid()))
		womTlWomst.setWomsSpareid("{}");
	CommonMessage.debugMsg("reportedDate 9 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()))
		womTlWomst.setWomsPhenomenaid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsCauseid()))
		womTlWomst.setWomsCauseid("{}");
	CommonMessage.debugMsg("reportedDate 10 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsLocation()))
		womTlWomst.setWomsLocation("{}");
	CommonMessage.debugMsg("reportedDate 11 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()))
		womTlWomst.setWomsBookingremarks("{}");
	CommonMessage.debugMsg("reportedDate 12 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsAcceptedremarks()))
		womTlWomst.setWomsAcceptedremarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRescheduleremarks()))
		womTlWomst.setWomsRescheduleremarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsAllottedremarks()))
		womTlWomst.setWomsAllottedremarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRescheduledremarks()))
		womTlWomst.setWomsRescheduledremarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsProductionremarks()))
		womTlWomst.setWomsProductionremarks("{}");
	CommonMessage.debugMsg("reportedDate 13 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRemarks()))
		womTlWomst.setWomsRemarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRequestapprovremarks()))
		womTlWomst.setWomsRequestapprovremarks("{}");
	CommonMessage.debugMsg("reportedDate 14 " +reportedDate);
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsSafetypermitid()))
		womTlWomst.setWomsSafetypermitid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsStandbyadditionalinfo()))
		womTlWomst.setWomsStandbyadditionalinfo("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsIntorextequipdesc()))
		womTlWomst.setWomsIntorextequipdesc("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsStandbyremarks()))
		womTlWomst.setWomsStandbyremarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRepairremarks()))
		womTlWomst.setWomsRepairremarks("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsSentrepairid()))
		womTlWomst.setWomsSentrepairid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsSentto()))
		womTlWomst.setWomsSentto("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsJobopeningid()))
		womTlWomst.setWomsJobopeningid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsOrderno()))
		womTlWomst.setWomsOrderno("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsAllottedsupplier()))
		womTlWomst.setWomsAllottedsupplier("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsNumofactivities()))
		womTlWomst.setWomsNumofactivities("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsPwdmwono()))
		womTlWomst.setWomsPwdmwono("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRefdoctype()))
		womTlWomst.setWomsRefdoctype("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRefdocid()))
		womTlWomst.setWomsRefdocid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsProcessId()))
		womTlWomst.setWomsProcessId("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRequiredstart()))
		womTlWomst.setWomsRequiredstart("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsRequiredend()))
		womTlWomst.setWomsRequiredend("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsPlannergroup()))
		womTlWomst.setWomsPlannergroup("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsDepartmentid()))
		womTlWomst.setWomsDepartmentid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsTempfield1()))
		womTlWomst.setWomsTempfield1("{}");
	
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsElementid()))
		womTlWomst.setWomsElementid("{}");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsFlid()))
		womTlWomst.setWomsFlid("{}");
	
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsCostcenterid()))
		womTlWomst.setWomsCostcenterid("{}");
	if(womTlWomst.getWomsElementid() == null)
		womTlWomst.setWomsElementid("{}");
	if(womTlWomst.getWomsFlid() == null)
		womTlWomst.setWomsFlid("{}");
		CommonMessage.debugMsg("After rep Date");
		String costCenter = null;
		String factory = null;
	/*	if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmEquipmentid()))
		{
			womTlWomst.setWomsMachineid(abnTlAbnormality.getAbnmEquipmentid());			
			
		}
		else
		{
			womTlWomst.setWomsMachineid("{}");
		}
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCellid()))
		{
			womTlWomst.setWomsCellid(abnTlAbnormality.getAbnmCellid());
			factory = getKeyId(TableNames.TBL_GEN_TL_CELLMST,"CELL_FACTORYID","CELL_KEYID",abnTlAbnormality.getAbnmCellid());
			costCenter = getKeyId(TableNames.TBL_GEN_TL_CELLMST,"CELL_COSTCENTREID","CELL_KEYID",abnTlAbnormality.getAbnmCellid());
		}
		else
			womTlWomst.setWomsCellid("{}");
		
		CommonMessage.debugMsg("Factory : "+factory);
		CommonMessage.debugMsg("costCenter : "+costCenter);
		if(UIUtils.isValidKeyId(factory))
			womTlWomst.setWomsFactoryid(factory);
		else
			womTlWomst.setWomsFactoryid("{}");	
		
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmSectionid()))
			womTlWomst.setWomsSectionid(abnTlAbnormality.getAbnmSectionid());
		else
			womTlWomst.setWomsSectionid("{}");				
		
		womTlWomst.setWomsWorkcenterid("{}");
		
		if(UIUtils.isValidKeyId(reportedDate))	
			womTlWomst.setWomsOccurreddate(reportedDate);
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmShiftid()))	
			womTlWomst.setWomsShiftid(abnTlAbnormality.getAbnmShiftid());	
		else
			womTlWomst.setWomsShiftid("{}");
		
		womTlWomst.setWomsShiftdate(reportedDate);	
		womTlWomst.setWomsPriority("N");
		
		womTlWomst.setWomsReporteddate(reportedDate);*/
		CommonMessage.debugMsg("Before Detected By");
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDetectedby()))
		{
			//womTlWomst.setWomsReportedby(abnTlAbnormality.getAbnmDetectedby());
			womTlWomst.setWomsAcceptedby(abnTlAbnormality.getAbnmDetectedby());
			//womTlWomst.setWomsRescheduleby(abnTlAbnormality.getAbnmDetectedby());
			//womTlWomst.setWomsProductionby(abnTlAbnormality.getAbnmDetectedby());
			womTlWomst.setWomsRequestapprovedby(abnTlAbnormality.getAbnmDetectedby());
		}
		else
		{
			//womTlWomst.setWomsReportedby("{}");	
			womTlWomst.setWomsAcceptedby("{}");	
			
			womTlWomst.setWomsRequestapprovedby("{}");
		}
		womTlWomst.setWomsRescheduleby("{}");
		womTlWomst.setWomsProductionby("{}");
		/*womTlWomst.setWomsProductionstop("X");	
		womTlWomst.setWomsMachinecondition("X");	
		womTlWomst.setWomsActivitytype("A");
		
		womTlWomst.setWomsAlarmno("{}");*/
		
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmAssemblyid()))
			womTlWomst.setWomsAssemblyid(abnTlAbnormality.getAbnmAssemblyid());
		else
			womTlWomst.setWomsAssemblyid("{}");
		
	//	womTlWomst.setWomsSubassemblyid("{}");	
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmBlockdiagramref()))
			womTlWomst.setWomsPartlocation(abnTlAbnormality.getAbnmBlockdiagramref());
		else
			womTlWomst.setWomsPartlocation("{}");
		
		/*womTlWomst.setWomsSpareid("{}");
		womTlWomst.setWomsPhenomenaid("{}");
		womTlWomst.setWomsCauseid("{}");
		womTlWomst.setWomsLocation("{}");*/
			
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDescription()))
			womTlWomst.setWomsProblem(abnTlAbnormality.getAbnmDescription());
		else
			womTlWomst.setWomsProblem("{}");
		
		/*if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmTargetremarks()))
		{
			womTlWomst.setWomsBookingremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsAcceptedremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRescheduleremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsAllottedremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRescheduledremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsProductionremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRemarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRequestapprovremarks(abnTlAbnormality.getAbnmTargetremarks());
		}
		else
		{
			womTlWomst.setWomsBookingremarks("{}");	
			womTlWomst.setWomsAcceptedremarks("{}");
			womTlWomst.setWomsRescheduleremarks("{}");
			womTlWomst.setWomsAllottedremarks("{}");
			womTlWomst.setWomsRescheduledremarks("{}");
			womTlWomst.setWomsProductionremarks("{}");
			womTlWomst.setWomsRemarks("{}");
			womTlWomst.setWomsRequestapprovremarks("{}");
		}*/
		
			/*womTlWomst.setWomsAccepteddate(reportedDate);	
			womTlWomst.setWomsAcceptedflag("Y");	
			
			womTlWomst.setWomsProductionapproval("N");	
			womTlWomst.setWomsSafetypermitsrequried("N");	
			womTlWomst.setWomsSafetypermitid("{}");
		
			womTlWomst.setWomsRescheduleflag("A");	
			womTlWomst.setWomsRescheduledate(reportedDate);	
			
			womTlWomst.setWomsAllottedflag("N");
			womTlWomst.setWomsAllotteddate(reportedDate);
			womTlWomst.setWomsProposedstartdate(reportedDate);
			womTlWomst.setWomsProposedenddate(Constants.futureNullDate  + " "+"00:00");	
		
			CommonMessage.debugMsg("Proposed End Date : "+womTlWomst.getWomsProposedenddate());
			womTlWomst.setWomsProposedstflag("N");
			womTlWomst.setWomsProposedendflag("N");
			
			womTlWomst.setWomsProposeddtacceptflag("X");	
			womTlWomst.setWomsReschedulestartdate(Constants.passNullDate  + " "+"00:00");
			womTlWomst.setWomsRescheduleenddate(Constants.futureNullDate  + " "+"00:00");		
			womTlWomst.setWomsRescheduledstflag("N");
			womTlWomst.setWomsRescheduledendflag("N");	*/	
		
		//	womTlWomst.setWomsProductionstartflag("N");	
			//womTlWomst.setWomsProductionstartdate(reportedDate);
		
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmWostarttime()))
				womTlWomst.setWomsWorkstartdate(abnTlAbnormality.getAbnmWostarttime());
			else
				womTlWomst.setWomsWorkstartdate(reportedDate);
			CommonMessage.debugMsg("womTlWomst "+womTlWomst.getWomsWorkstartdate());
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmFeedbackdate()))
			{
				womTlWomst.setWomsWorkenddate(abnTlAbnormality.getAbnmFeedbackdate());
				womTlWomst.setWomsWoapprovaldate(abnTlAbnormality.getAbnmFeedbackdate());
				womTlWomst.setWomsMachinereleaseddate(abnTlAbnormality.getAbnmFeedbackdate());
				womTlWomst.setWomsWorkendflag("Y");
				womTlWomst.setWomsWoapprovalflag("Y");
				womTlWomst.setWomsMachinereleaseflag("Y");
				
			}
			else
			{
				womTlWomst.setWomsWorkenddate(Constants.futureNullDate);
				womTlWomst.setWomsWoapprovaldate(Constants.passNullDate);
				womTlWomst.setWomsMachinereleaseddate(Constants.passNullDate);
				womTlWomst.setWomsWorkendflag("N");
				womTlWomst.setWomsWoapprovalflag("N");
				womTlWomst.setWomsMachinereleaseflag("N");
			}
		
			womTlWomst.setWomsWorkstartflag("Y");	
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmWoendtime()))
				womTlWomst.setWomsWorkenddate(abnTlAbnormality.getAbnmWoendtime());
			//womTlWomst.setWomsFinalactivitytype("A");
		
			/*if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmKeyid()))
				womTlWomst.setWomsActivityid(abnTlAbnormality.getAbnmKeyid());
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
			womTlWomst.setWomsAllottedsource("X");
			womTlWomst.setWomsAllottedsupplier("{}");
			womTlWomst.setWomsDirectentry("X");*/
			
		/*	if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmRelatedto()))
				womTlWomst.setWomsRelatedto(abnTlAbnormality.getAbnmRelatedto());
			else
				womTlWomst.setWomsRelatedto("X");
			
			womTlWomst.setWomsLoss("{}");
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmMould()))
				womTlWomst.setWomsMouldid(abnTlAbnormality.getAbnmMould());
			else
				womTlWomst.setWomsMouldid("{}");
			
			womTlWomst.setWomsTempfield10("{}");
			womTlWomst.setWomsTempfield9("{}");
			womTlWomst.setWomsTempfield8("{}");
			womTlWomst.setWomsTempfield7("{}");
			womTlWomst.setWomsTempfield6("{}");
			womTlWomst.setWomsTempfield5("{}");
			womTlWomst.setWomsTempfield4("{}");
			womTlWomst.setWomsTempfield3("{}");
			womTlWomst.setWomsTempfield2("{}");
			womTlWomst.setWomsTempfield1("{}");*/
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCreatedby()))
				womTlWomst.setWomsModifiedby(abnTlAbnormality.getAbnmCreatedby());
			else
				womTlWomst.setWomsModifiedby("{}");
			/*if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCreatedby()))
				womTlWomst.setWomsCreatedby(abnTlAbnormality.getAbnmCreatedby());
			else
				womTlWomst.setWomsCreatedby("{}");*/
		
		/*	if(UIUtils.isValidKeyId(costCenter))
				womTlWomst.setWomsCostcenterid(costCenter);
			else
				womTlWomst.setWomsCostcenterid("{}");*/
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsActivityid()))
			{
				if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmKeyid()))
					womTlWomst.setWomsActivityid(abnTlAbnormality.getAbnmKeyid());
				else
					womTlWomst.setWomsActivityid("{}");
			}
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmTradeid()))
				womTlWomst.setWomsTradeid(abnTlAbnormality.getAbnmTradeid());
			else
				womTlWomst.setWomsTradeid("{}");
			
			String woStatus = dbActionTemplate.getSingleValue(WomTlWomstSql.TBL_BAL_WOM_TL_WOMST, "WOMS_STATUS", "WOMS_KEYID", abnTlAbnormality.getAbnmRefdocid());
			CommonMessage.debugMsg("woStatus "+woStatus + " : "+abnTlAbnormality.getAbnmStatus());
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmStatus()))
			{
				if(abnTlAbnormality.getAbnmStatus().equals("C"))
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
							womTlWomst.setWomsStatus(abnTlAbnormality.getAbnmStatus());
							womTlWomst.setWomsFinalstatus(abnTlAbnormality.getAbnmStatus());
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
			//womTlWomst.setWomsFailuretypeid("{}");
			/*if(UIUtils.isValidKeyId( ))
			{
				if(abnTlAbnormality.getAbnmStatus().equals("C"))
				{
					womTlWomst.setWomsStatus(abnTlAbnormality.getAbnmStatus());
					womTlWomst.setWomsFinalstatus("COMPLETED");					
				}
				else
				{
					womTlWomst.setWomsStatus("L");
					womTlWomst.setWomsFinalstatus("ALLOTTED");
				}
			}
			else
			{
				womTlWomst.setWomsStatus("X");
				womTlWomst.setWomsFinalstatus("{}");
			}*/
			CommonMessage.debugMsg("Status ABN : "+womTlWomst.getWomsStatus());
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDetectedby()))	
			{
				//womTlWomst.setWomsAllottedto(abnTlAbnormality.getAbnmDetectedby());
			}
			else
			{
				//womTlWomst.setWomsAllottedto("{}");				
			}
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCompletedby()))	
			{
				womTlWomst.setWomsDoneby(abnTlAbnormality.getAbnmCompletedby());
				womTlWomst.setWomsWoapprovalby(abnTlAbnormality.getAbnmCompletedby());
				womTlWomst.setWomsMachinereleaseby(abnTlAbnormality.getAbnmCompletedby());
			}
			else
			{
				womTlWomst.setWomsDoneby("{}");
				womTlWomst.setWomsWoapprovalby("{}");
				womTlWomst.setWomsMachinereleaseby("{}");
			}
		}	
	
	private void fillWorkOrderInsert(WomTlWomst womTlWomst,AbnTlAbnormality abnTlAbnormality) 	throws Exception {
		womTlWomst.setWomsActive(abnTlAbnormality.getAbnmActive());	
		womTlWomst.setWomsCreatedon(abnTlAbnormality.getAbnmCreatedon());
		womTlWomst.setWomsModifiedon(abnTlAbnormality.getAbnmModifiedon());
		
		String reportedDate = Constants.passNullDate;
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDetectiondate()))
		{		
			reportedDate =abnTlAbnormality.getAbnmDetectiondate();
		}	
		
		String costCenter = null;
		String factory = null;
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmEquipmentid()))
		{
			womTlWomst.setWomsMachineid(abnTlAbnormality.getAbnmEquipmentid());			
			
		}
		else
		{
			womTlWomst.setWomsMachineid("{}");
		}
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCellid()))
		{
			womTlWomst.setWomsCellid(abnTlAbnormality.getAbnmCellid());
			factory = getKeyId(TableNames.TBL_GEN_TL_CELLMST,"CELL_FACTORYID","CELL_KEYID",abnTlAbnormality.getAbnmCellid());
			costCenter = getKeyId(TableNames.TBL_GEN_TL_CELLMST,"CELL_COSTCENTREID","CELL_KEYID",abnTlAbnormality.getAbnmCellid());
		}
		else
			womTlWomst.setWomsCellid("{}");
		
		CommonMessage.debugMsg("Factory : "+factory);
		CommonMessage.debugMsg("costCenter : "+costCenter);
		if(UIUtils.isValidKeyId(factory))
			womTlWomst.setWomsFactoryid(factory);
		else
			womTlWomst.setWomsFactoryid("{}");	
		
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmSectionid()))
			womTlWomst.setWomsSectionid(abnTlAbnormality.getAbnmSectionid());
		else
			womTlWomst.setWomsSectionid("{}");				
		
		womTlWomst.setWomsWorkcenterid("{}");
		
		if(UIUtils.isValidKeyId(reportedDate))	
			womTlWomst.setWomsOccurreddate(reportedDate);
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmShiftid()))	
			womTlWomst.setWomsShiftid(abnTlAbnormality.getAbnmShiftid());	
		else
			womTlWomst.setWomsShiftid("{}");
		
		womTlWomst.setWomsShiftdate(reportedDate);	
		womTlWomst.setWomsPriority("N");
		
		womTlWomst.setWomsReporteddate(reportedDate);
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDetectedby()))
		{
			womTlWomst.setWomsReportedby(abnTlAbnormality.getAbnmDetectedby());
			womTlWomst.setWomsAcceptedby(abnTlAbnormality.getAbnmDetectedby());
			//womTlWomst.setWomsRescheduleby(abnTlAbnormality.getAbnmDetectedby());
			//womTlWomst.setWomsProductionby(abnTlAbnormality.getAbnmDetectedby());
			womTlWomst.setWomsRequestapprovedby(abnTlAbnormality.getAbnmDetectedby());
		}
		else
		{
			womTlWomst.setWomsReportedby("{}");	
			womTlWomst.setWomsAcceptedby("{}");	
			
			womTlWomst.setWomsRequestapprovedby("{}");
		}
		womTlWomst.setWomsRescheduleby("{}");
		womTlWomst.setWomsProductionby("{}");
		womTlWomst.setWomsProductionstop("X");	
		womTlWomst.setWomsMachinecondition("X");	
		womTlWomst.setWomsActivitytype("A");
		
		womTlWomst.setWomsAlarmno("{}");
		
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmAssemblyid()))
			womTlWomst.setWomsAssemblyid(abnTlAbnormality.getAbnmAssemblyid());
		else
			womTlWomst.setWomsAssemblyid("{}");
		
		womTlWomst.setWomsSubassemblyid("{}");	
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmBlockdiagramref()))
			womTlWomst.setWomsPartlocation(abnTlAbnormality.getAbnmBlockdiagramref());
		else
			womTlWomst.setWomsPartlocation("{}");
		
		womTlWomst.setWomsSpareid("{}");
		womTlWomst.setWomsPhenomenaid("{}");
		womTlWomst.setWomsCauseid("{}");
		womTlWomst.setWomsLocation("{}");
			
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDescription()))
			womTlWomst.setWomsProblem(abnTlAbnormality.getAbnmDescription());
		else
			womTlWomst.setWomsProblem("{}");
		
		if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmTargetremarks()))
		{
			womTlWomst.setWomsBookingremarks(abnTlAbnormality.getAbnmTargetremarks());
			/*womTlWomst.setWomsAcceptedremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRescheduleremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsAllottedremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRescheduledremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsProductionremarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRemarks(abnTlAbnormality.getAbnmTargetremarks());
			womTlWomst.setWomsRequestapprovremarks(abnTlAbnormality.getAbnmTargetremarks());*/
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
			
			womTlWomst.setWomsAllottedflag("N");
			womTlWomst.setWomsAllotteddate(reportedDate);
			womTlWomst.setWomsProposedstartdate(reportedDate);
			womTlWomst.setWomsProposedenddate(Constants.futureNullDate  + " "+"00:00");	
		
			CommonMessage.debugMsg("Proposed End Date : "+womTlWomst.getWomsProposedenddate());
			womTlWomst.setWomsProposedstflag("N");
			womTlWomst.setWomsProposedendflag("N");
			
			womTlWomst.setWomsProposeddtacceptflag("X");	
			womTlWomst.setWomsReschedulestartdate(Constants.passNullDate  + " "+"00:00");
			womTlWomst.setWomsRescheduleenddate(Constants.futureNullDate  + " "+"00:00");		
			womTlWomst.setWomsRescheduledstflag("N");
			womTlWomst.setWomsRescheduledendflag("N");		
		
			womTlWomst.setWomsProductionstartflag("N");	
			womTlWomst.setWomsProductionstartdate(reportedDate);
			//womTlWomst.setWomsWorkstartdate(reportedDate);
			
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmWostarttime()))
				womTlWomst.setWomsWorkstartdate(abnTlAbnormality.getAbnmWostarttime());
			else
				womTlWomst.setWomsWorkstartdate(reportedDate);
			
		
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmFeedbackdate()))
			{
				womTlWomst.setWomsWorkenddate(abnTlAbnormality.getAbnmFeedbackdate());
				womTlWomst.setWomsWoapprovaldate(abnTlAbnormality.getAbnmFeedbackdate());
				womTlWomst.setWomsMachinereleaseddate(abnTlAbnormality.getAbnmFeedbackdate());
				womTlWomst.setWomsWorkendflag("Y");
				womTlWomst.setWomsWoapprovalflag("Y");
				womTlWomst.setWomsMachinereleaseflag("Y");
				
			}
			else
			{
				womTlWomst.setWomsWorkenddate(Constants.futureNullDate);
				womTlWomst.setWomsWoapprovaldate(Constants.passNullDate);
				womTlWomst.setWomsMachinereleaseddate(Constants.passNullDate);
				womTlWomst.setWomsWorkendflag("N");
				womTlWomst.setWomsWoapprovalflag("N");
				womTlWomst.setWomsMachinereleaseflag("N");
			}
		
			womTlWomst.setWomsWorkstartflag("Y");					
			womTlWomst.setWomsFinalactivitytype("A");
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmWoendtime()))
				womTlWomst.setWomsWorkenddate(abnTlAbnormality.getAbnmWoendtime());
		
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmKeyid()))
				womTlWomst.setWomsActivityid(abnTlAbnormality.getAbnmKeyid());
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
			womTlWomst.setWomsAllottedsource("X");
			womTlWomst.setWomsAllottedsupplier("{}");
			womTlWomst.setWomsDirectentry("N");
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmRelatedto()))
				womTlWomst.setWomsRelatedto(abnTlAbnormality.getAbnmRelatedto());
			else
				womTlWomst.setWomsRelatedto("X");
			
			womTlWomst.setWomsLoss("{}");
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmMould()))
				womTlWomst.setWomsMouldid(abnTlAbnormality.getAbnmMould());
			else
				womTlWomst.setWomsMouldid("{}");
			
			womTlWomst.setWomsNumofactivities("0");
			womTlWomst.setWomsPwdmwono("{}");
			womTlWomst.setWomsRefdoctype("{}");
			womTlWomst.setWomsRefdocid("{}");
			womTlWomst.setWomsProcessId("{}");
			womTlWomst.setWomsRequiredstart("{}");
			womTlWomst.setWomsRequiredend("{}");
			womTlWomst.setWomsPlannergroup("{}");
			womTlWomst.setWomsDepartmentid("{}");
			womTlWomst.setWomsTempfield1("{}");
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCreatedby()))
				womTlWomst.setWomsModifiedby(abnTlAbnormality.getAbnmCreatedby());
			else
				womTlWomst.setWomsModifiedby("{}");
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCreatedby()))
				womTlWomst.setWomsCreatedby(abnTlAbnormality.getAbnmCreatedby());
			else
				womTlWomst.setWomsCreatedby("{}");
		
			if(UIUtils.isValidKeyId(costCenter))
				womTlWomst.setWomsCostcenterid(costCenter);
			else
				womTlWomst.setWomsCostcenterid("{}");
		
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmTradeid()))
				womTlWomst.setWomsTradeid(abnTlAbnormality.getAbnmTradeid());
			else
				womTlWomst.setWomsTradeid("{}");
			
			womTlWomst.setWomsFailuretypeid("{}");
			
			if(womTlWomst.getWomsElementid() == null)
				womTlWomst.setWomsElementid("{}");
			if(womTlWomst.getWomsFlid() == null)
				womTlWomst.setWomsFlid("{}");
			
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmStatus()))
			{
				if(abnTlAbnormality.getAbnmStatus().equals("C"))
				{
					womTlWomst.setWomsStatus(abnTlAbnormality.getAbnmStatus());
					womTlWomst.setWomsFinalstatus("COMPLETED");					
				}
				else
				{
					womTlWomst.setWomsStatus("L");
					womTlWomst.setWomsFinalstatus("ALLOTTED");
				}
			}
			else
			{
				womTlWomst.setWomsStatus("X");
				womTlWomst.setWomsFinalstatus("{}");
			}
			CommonMessage.debugMsg("Status ABN : "+womTlWomst.getWomsStatus());
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmDetectedby()))	
			{
				womTlWomst.setWomsAllottedto(abnTlAbnormality.getAbnmDetectedby());
			}
			else
			{
				womTlWomst.setWomsAllottedto("{}");				
			}
			if(UIUtils.isValidKeyId(abnTlAbnormality.getAbnmCompletedby()))	
			{
				womTlWomst.setWomsDoneby(abnTlAbnormality.getAbnmCompletedby());
				womTlWomst.setWomsWoapprovalby(abnTlAbnormality.getAbnmCompletedby());
				womTlWomst.setWomsMachinereleaseby(abnTlAbnormality.getAbnmCompletedby());
			}
			else
			{
				womTlWomst.setWomsDoneby("{}");
				womTlWomst.setWomsWoapprovalby("{}");
				womTlWomst.setWomsMachinereleaseby("{}");
			}
		}	

	public  String getKeyId(String tableName,String keyIdCol,String woKeyCol,String woKey)
	{
		try {
			return dbActionTemplate.getSingleValue(tableName, keyIdCol, woKeyCol, woKey);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String[]> getAbnormalityDetails(String keyId,GridParams gridParams) throws Exception {
		
		try
		{
			
			List<String> paramValues = new ArrayList<String>();			
			
			paramValues.add(gridParams.getFromRow());
			paramValues.add(gridParams.getToRow());
			
			List<String[]> relatedMst = null;
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;
			
			String sql = AbnormalityReportSqls.getAbnDetailsSql(keyId,gridParams);			
			String Sqls = "Select * from( "+sql+" ) WHERE slno >= ? and slno <= ? ";
			relatedMst = dbActionTemplate.getDataList(Sqls, paramValues);
			
		
			return relatedMst;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg(" excep "  +e.getMessage() );
			throw new Exception(e.getMessage()); 
		}
		
	}

	
	public String getAbnormalityDetailsCount(String keyId, GridParams gridParams)throws Exception {
		
		try
		{			
			String count = null;
			Boolean [ ] isFunction = new Boolean [ 1 ];
			isFunction [0] = true;			
			String sql = AbnormalityReportSqls.getAbnDetailsSqlCount(keyId,gridParams);
			String sqlCount = " Select count(*) from ("+sql+")";
			count = dbActionTemplate.getSingleValue(sqlCount);
			return count;
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg(" excep "  +e.getMessage() );
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook getExportExcel(JSONObject colModel,String format, GridParams gridParams,String keyId,String count) throws Exception {
		ResultSet rs = null;
		 int colVal = 0;
		   try{
		
			rs =   getAbnDetailsResultSet(gridParams,keyId,count);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			/*List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);*/
			return excelUtils.writeToExcel(rs,format, 0,0,0);
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   } 		
	}

	private ResultSet getAbnDetailsResultSet(GridParams gridParams,String keyId,String count) throws Exception {
		
			try
			{				
				List<String> paramValues = new ArrayList<String>();		
				gridParams.setToRow(count);
				paramValues.add(gridParams.getFromRow());
				paramValues.add(gridParams.getToRow());				
				Boolean [ ] isFunction = new Boolean [ 1 ];
				isFunction [0] = true;
				
				String sql = AbnormalityReportSqls.getAbnDetailsSql(keyId,gridParams);			
				String Sqls = "Select * from( "+sql+" ) WHERE slno >= ? and slno <= ? ";
				return  dbActionTemplate.getData(Sqls, paramValues.toArray());	
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 				
			}
			
		
	}

	
	public List<String[]> getAllAbnormalityDetails(CommonFilter commonFilter,String keyid, String columnId) throws Exception {
		List<String[]> dataList=new ArrayList<String[]>();
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			
			
			String condParams = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			/*CommonMessage.debugMsg("keyid..."+keyid);
			if(keyid.substring(0,3).equals("FCT"))
				condParams += "FACTORYID="+keyid;
			else if(keyid.substring(0,3).equals("CMP"))
				condParams += "COMPANYID="+keyid;
			else if(keyid.substring(0,3).equals("LCN"))
				condParams += "LOCATIONID="+keyid;
			else if(keyid.substring(0,3).equals("LIN"))
				condParams += "SECTIONID="+keyid;
			else if(keyid.substring(0,3).equals("CEL"))
				condParams += "CELLID="+keyid;
			else if(keyid.substring(0,3).equals("MCH"))
				condParams += "MACHINEID="+keyid;
			else if(keyid.substring(0,3).equals("ASM"))
			{	
				condParams += "ASSEMBLYID="+keyid;
				condParams += ";MACHINEID="+commonFilter.getMachineId();
			}*/
			condParams += ";COLUMN="+columnId;
			condParams += ";";
			paramValues.add(condParams);			
			paramValues.add(commonParams);
			
			dataList =  dbActionTemplate.processFunctionCalls("ABN_PC_ABNORMALITY.ABN_FN_VIEWABNORMALITY", paramValues);
			CommonMessage.debugMsg("After " +commonFilter.getViewClick());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return dataList;
	
	
}

	@Override
	public Workbook getHSEAbnExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getHSEAbnResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
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
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}

	private ResultSet getHSEAbnResultSet(CommonFilter commonFilter) {
		
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			return  dbActionTemplate.dbFunctionCall("ABN_PC_ABNORMALITY.HSE_FN_MODIFICATION", paramValues);			
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
		
	}

	@Override
	public List<AbnTlAbnormality> updateBulkTagRemoval(List<AbnTlAbnormality> newAbnTlAbnormality) throws Exception {
		
		 
		List<String> sqls = new ArrayList<String>(); 
		try{
			//if (newAbnTlAbnormality!= null && newAbnTlAbnormality.size() > 0) 
			{
				
				for( AbnTlAbnormality abnTlAbnormality :newAbnTlAbnormality)
				{	
					/*if(!UIUtils.isValidKeyId(Mchrankparameter.getMrkpKeyid()))
					{
						Mchrankparameter.setMrkpKeyid(dbActionTemplate.getSequenceNumber("TBL_GEN_TL_MCHRANKPARAMETER", 12, "MRP", null, null)); // set the sequnce number
						sqls.add(GenTlMchrankparameterSql.getInsertSql(genTlMchrankparameterSql.getMrkpDbFields(), Mchrankparameter.getSaveArray())); // add insert sql for master table}
					}
					else*/
					//{
						sqls.add(AbnTlAbnormalitySql.updateBulkTagRemoval(abnTlAbnormality));
						sqls.add(AbnTlAbnormalitySql.updateWorkOrderMst(abnTlAbnormality));
						
					//}
				}
				CommonMessage.debugMsg("sqls..."+sqls);
				dbActionTemplate.executeStatements(sqls);
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return newAbnTlAbnormality;
		
		
	}

	@Override
	public List<String[]> getRepeatedAbn(CommonFilter commonFilter)throws Exception {
	
		try
		{
			
			List<String> paramValues = new ArrayList<String>();			
			
			List<String[]> relatedMst = null;
			
			String sql = AbnormalityReportSqls.getRepeatedAbn(commonFilter);			
			String Sqls = "Select * from( "+sql+" ) WHERE  slno >= 1 and slno <= 10 order by  dataorder ,slno ";
			//Sqls = "SELECT ABNM_KEYID,AbnormalityDesc FROM ("+Sqls+")";
			CommonMessage.debugMsg("Sqls..."+Sqls);
			relatedMst = dbActionTemplate.getDataList(Sqls);
			
			return relatedMst;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg(" excep "  +e.getMessage() );
			throw new Exception(e.getMessage()); 
		}
	
	}

	@Override
	public List<String[]> getAbnAllocation(CommonFilter commonFilter)	throws Exception {
	
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			List<String[]> relatedMst = null;
//			String sql = AbnormalityReportSqls.getAbnAllocation(commonFilter);			
//			CommonMessage.debugMsg("Sqls..."+sql);
	//		relatedMst = dbActionTemplate.getDataList(sql);
			String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
			if(UIUtils.isValidKeyId(commonFilter.getAllotedDtTo()))
				condParam+="RESPONSIBILITY="+commonFilter.getAllotedDtTo();
			else if(UIUtils.isValidKeyId(commonFilter.getAllotedDtTo()))
				condParam+="DETECTEDBY="+commonFilter.getAllotedFrom();
			paramValues.add(condParam);
			paramValues.add(commonParam);
			
			
//			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ABN_FN_ALLOCATION", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("ABN_FN_ALLOCATION_SB", paramValues,3,false);
			CommonMessage.debugMsg("After " +commonFilter.getViewClick());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
			
		
			//return relatedMst;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg(" excep "  +e.getMessage() );
			throw new Exception(e.getMessage()); 
		}
	
	}


	public AbnTlAbnormality getAbnData(String AbnmTagno) throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:"+AbnmTagno);
		AbnTlAbnormality abnTlAbnormality = new AbnTlAbnormality();
		
		String sql = AbnTlAbnormalitySql.getabnfrmdatasql();
				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { AbnmTagno };
		abnTlAbnormality.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+abnTlAbnormality.getAbnmKeyid());
		return  abnTlAbnormality;
	}

	@Override
	public List<AbnTlAbnormality> updateAbnAllocation(List<AbnTlAbnormality> newAbnTlAbnormality) throws Exception {

		List<String> sqls = new ArrayList<String>(); 
		try{
	 
			{
				
				for( AbnTlAbnormality abnTlAbnormality :newAbnTlAbnormality)
				{	
					sqls.add(AbnTlAbnormalitySql.updateAbnAllocation(abnTlAbnormality));
					/*if(abnTlAbnormality.getAbnTlDtl()!=null)
						sqls.add(AbnTlAbnormalitySql.updateAbnAllocationdtl(abnTlAbnormality));*/
						
				}
				CommonMessage.debugMsg("sqls..."+sqls);
				dbActionTemplate.executeStatements(sqls);
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return newAbnTlAbnormality;
	
	}

	@Override
	
	public Workbook getAbnAllocationExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat) throws Exception {
	
		  ResultSet rs = null;
		   try
		   {
			   	rs =   getAbnormalityAllocationResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
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
				return excelUtils.writeToExcel(rs,rptFormat,2, 0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }
	}
	private ResultSet getAbnormalityAllocationResultSet(CommonFilter commonFilter) throws Exception
	{
		//List<String> paramValues = getFilterParamValues(commonFilter);
		
		List<String> paramValues = new ArrayList<String>();
		List<String[]> relatedMst = null;
//		String sql = AbnormalityReportSqls.getAbnAllocation(commonFilter);			
//		CommonMessage.debugMsg("Sqls..."+sql);
//		relatedMst = dbActionTemplate.getDataList(sql);
		String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
		
		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		return  dbActionTemplate.NewdbFunctionCall2("ABN_FN_ALLOCATION", paramValues);
		
	}

	@Override
	public String getAbnTradeValues(String abnmRespons) throws Exception {
		try
		{			
			String sql = " SELECT DISTINCT  FRP_TRADEID from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM "; 
					sql+=" where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and    EMPM_active = 'Y' AND EMPM_KEYID = '"+abnmRespons+"' ";
			String trade = dbActionTemplate.getSingleValue(sql);
			CommonMessage.debugMsg("trade..."+trade);
			return trade;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception while getting typeFlag "+e.getMessage());
		}
		return null;
	}

	@Override
	public AbnTlAbnormality updateabncomp(AbnTlAbnormality newAbnTlAbnormality)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
		StringBuffer sqlUpdt = new StringBuffer();
		
		    sqlUpdt.append(" update ABN_TL_ABNORMALITY set ABNM_STATUS= '"+newAbnTlAbnormality.getAbnmStatus()+"' " );
		if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmRemarks()))
			sqlUpdt.append(",ABNM_REMARKS= '"+newAbnTlAbnormality.getAbnmRemarks()+"' " );
		if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmCompletedby()))
			sqlUpdt.append(",ABNM_COMPLETEDBY = '"+newAbnTlAbnormality.getAbnmCompletedby()+"' " );
		if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmCountermeasure()))
			sqlUpdt.append(",ABNM_COUNTERMEASURE = '"+newAbnTlAbnormality.getAbnmCountermeasure()+"' " );
		if(UIUtils.isValidKeyId(newAbnTlAbnormality.getAbnmWoendtime()))
			sqlUpdt.append(",ABNM_WOENDTIME = '"+newAbnTlAbnormality.getAbnmWoendtime()+"' " );
		
		sqlUpdt.append(" WHERE ABNM_KEYID = '"+newAbnTlAbnormality.getAbnmKeyid()+"' " );
		
		sqls.add(sqlUpdt.toString());
		String AbnmStatus=newAbnTlAbnormality.getAbnmStatus();
		String AbnmKeyid=newAbnTlAbnormality.getAbnmKeyid();
		String Completedby=newAbnTlAbnormality.getAbnmCompletedby();
		 String CompletedDate=newAbnTlAbnormality.getAbnmWoendtime();
		String PsiKeyid=newAbnTlAbnormality.getAbnmRefdocid();
		CommonMessage.debugMsg("psiref id "+PsiKeyid);
		closePsiAbnormality(AbnmStatus,AbnmKeyid,Completedby,CompletedDate);
		dbActionTemplate.executeStatements(sqls);
	
		return newAbnTlAbnormality;
	}

	private void closePsiAbnormality(String AbnmStatus, String AbnmKeyid, String Completedby, String CompletedDate) throws BusinessApplicationExceptions, Exception {
		StringBuffer sf = new StringBuffer();
		String PsiKeyid=dbActionTemplate.getSingleValue("ABN_TL_ABNORMALITY", "ABNM_REFDOCID", "ABNM_KEYID",AbnmKeyid);
		CommonMessage.debugMsg("PsiKeyid:::"+PsiKeyid);
		//CommonMessage.debugMsg(" delete Dtl.toString()== "+ sf.toString());
		String sql="update SHE_TL_PLANTSAFETYDTL set SPSD_STATUS='"+AbnmStatus+"', SPSD_COMPLETEDBY= '"+Completedby+"',SPSD_COMPLETEDDATE='"+CompletedDate+"' where SPSD_KEYID='"+PsiKeyid+"' ";
		CommonMessage.debugMsg("SQL qc detail:::"+sql);
		dbActionTemplate.executeStatement(sql.toString());
		
	}

	@Override
	public List<String[]> FillabnpopData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = AbnTlAbnormalitySql.selectabnpopData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public String getServiceCount(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String returnval;
		String sql ="select COUNT(*) FROM  GEN_TL_FNLNROLETEAM,GEN_TL_TEAMTRADELINK,gen_mv_flidhierarchY  WHERE frp_frt_keyid=frt_keyid AND flid=frt_fnln_keyid AND FRP_TRADEID='TDE00800007' and instr(parentflidS || '/'||flid,'FNL000124023') > 0 ";
		int servicecnt=Integer.parseInt(dbActionTemplate.getSingleValue(sql.toString()));
		if(servicecnt==1)
		{
			String sqls ="select FRT_EMPM_KEYID FROM  GEN_TL_FNLNROLETEAM,GEN_TL_TEAMTRADELINK,gen_mv_flidhierarchY  WHERE frp_frt_keyid=frt_keyid AND flid=frt_fnln_keyid AND instr(parentflidS || '/'||flid,'FNL000124023') > 0 ";
			returnval=dbActionTemplate.getSingleValue(sqls.toString());
		}
		else{
			returnval="2";
		}
		
		return returnval;
	}
	public String getSubType(String typeId)throws Exception{
		String Sql="";
		String abnmKeyid="";
		AbnTlAbnormalitySql abnTlAbnormalitySql=new AbnTlAbnormalitySql();
        try{
        	Sql=abnTlAbnormalitySql.getSubType(typeId);
        	CommonMessage.debugMsg("Sql"+Sql);
        	abnmKeyid=dbActionTemplate.getSingleValue(Sql);
        }
        catch(Exception e){
        	throw new Exception(e.getMessage());
        }
        return abnmKeyid;
	}
	
	public List<AbnTlAbnormality> Multiplecreate(List<AbnTlAbnormality> list) throws Exception {

		AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table
		try
		{
			
			List<AbnTlAbnormality> AbnList=list;
			String seqIdentfr=""; 
			for(AbnTlAbnormality getElementId :AbnList){
				String elementId = getElementId.getAbnmElementid();
				String location = null;
			 	seqIdentfr = AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY;

			 	if( elementId != null && elementId.length() > 10  ){
			 		location = elementId.substring(11, 21);  
			 		seqIdentfr += location;
			 	}
				
			}
			     List<String> sqls = new ArrayList<String>();
			     List <AbnTlAbnormality> methodslist = list;
			     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),seqIdentfr,10,"AB", "YYMM","Y");
				 for(AbnTlAbnormality abnormalityLink:methodslist)
				 { CommonMessage.debugMsg(" KeyId::::"+abnormalityLink.getAbnmKeyid());
					 if(!UIUtils.isValidKeyId(abnormalityLink.getAbnmKeyid())){
						String seqNo=sequenceNumber.getSequnceNumber();
						CommonMessage.debugMsg("The seqNo::::"+seqNo);
						abnormalityLink.setAbnmKeyid(seqNo);
                        sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnormalityLink.getSaveArray()));
                        
					 }else {
						 
						 CommonMessage.debugMsg("Update KeyId::::"+abnormalityLink.getAbnmKeyid());
	                      sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnormalityLink.getSaveArray()));
					 }
                      //  MultipleactionPlanEntry(list,"I");
                        CommonMessage.debugMsg("The sqls Query:"+sqls);
				 }
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return list;
	}
	
  public void  MultipleactionPlanEntry(List<AbnTlAbnormality> abnTlAbnormality,String type) throws Exception {
		String sql= "";
		GenTlActionplanmst genTlActionplanmst = new GenTlActionplanmst();
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql();
		List<AbnTlAbnormality> AnbList=abnTlAbnormality; 	
		MultipleactionPlanFillValues(genTlActionplanmst,abnTlAbnormality);
		for(AbnTlAbnormality abnormalitydata:AnbList){
		
			if("I".equals(type)){
				CommonMessage.debugMsg("Inside the I");
				String elementId = genTlActionplanmst.getAplmElementid();
			 	String location = null;
			 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);
				genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf,10,"AP","","")); 
				sql = GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(), genTlActionplanmst.getSaveArray());
				CommonMessage.debugMsg("The Sql ActionPlan::::"+sql);
			}
			dbActionTemplate.executeStatement(sql);
		}
	
	}
	
	private GenTlActionplanmst MultipleactionPlanFillValues(GenTlActionplanmst genTlActionplanmst,List<AbnTlAbnormality> abnTlAbnormality){
	     CommonMessage.debugMsg("ActionPlan fill Values");
		 List<AbnTlAbnormality> AnbList=abnTlAbnormality; 	
		for(AbnTlAbnormality abnormalitydata:AnbList){
			genTlActionplanmst.setAplmCreatedby(abnormalitydata.getAbnmCreatedby());
			genTlActionplanmst.setAplmActive("Y");
			genTlActionplanmst.setAplmCreatedon(abnormalitydata.getAbnmCreatedon());
			genTlActionplanmst.setAplmDetailrefid(abnormalitydata.getAbnmKeyid());
			genTlActionplanmst.setAplmElementid(abnormalitydata.getAbnmElementid());
			genTlActionplanmst.setAplmFlid(abnormalitydata.getAbnmFlid());		
			genTlActionplanmst.setAplmMaintask("{}");
			genTlActionplanmst.setAplmMasterrefid("{}");
			genTlActionplanmst.setAplmModifiedon(abnormalitydata.getAbnmModifiedon());
			genTlActionplanmst.setAplmPillarid("{}");
			genTlActionplanmst.setAplmRefdoctype("ABN");
			genTlActionplanmst.setAplmRemarks(abnormalitydata.getAbnmRemarks());
			genTlActionplanmst.setAplmStatus(abnormalitydata.getAbnmStatus());
		
		if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate()))
			genTlActionplanmst.setAplmPlandate(abnormalitydata.getAbnmDetectiondate());
			genTlActionplanmst.setAplmTempfiled2("-");
			genTlActionplanmst.setAplmTempfiled3("-");
			genTlActionplanmst.setAplmTempfiled4("-");
			genTlActionplanmst.setAplmTempfiled5("-");
		}
		return genTlActionplanmst; 
	}
	
	
	/*public AbnTlAbnormality Multiplecreate(AbnTlAbnormality abnTlAbnormality) 	throws Exception ,BusinessApplicationExceptions { 
            CommonMessage.debugMsg("Inside the DaoImpl");
		    List<String> sqls = new ArrayList<String>();  sqls for execution  
		 	String elementId = abnTlAbnormality.getAbnmElementid();
		 	String location = null;
		 	String seqIdentfr = AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY;
		 	if( elementId != null && elementId.length() > 10  ){
		 		location = elementId.substring(11, 21);  location id starts from 11  
		 		seqIdentfr += location;
		 	}
			abnTlAbnormality.setAbnmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfr,10,"AB","YYMM" ,"Y")); // set the sequnce number
			
			String sql = actionPlanEntry(abnTlAbnormality,"I");
			
			sqls.add(sql);
			
			sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table
			insertAnbDetails(abnTlAbnormality,sqls);
			
				if(abnTlDtl!=null)
			{
				abnTlDtl.setAbndAbnormalityid(abnTlAbnormality.getAbnmKeyid());
				abnTlDtl.setAbndKeyid(dbActionTemplate.getSequenceNumber(AbnTlDtlSql.TBL_ABN_TL_DTL,11,"ABD","YYMM" ,"Y")); // set the sequnce number 
				sqls.add(AbnTlDtlSql.getInsertSql(abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray())); // 
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls	
		    return abnTlAbnormality;
	}
	private List<String> insertAnbDetails(AbnTlAbnormality abnTlAbnormality,List<String> sqls) throws Exception {
		AbnTlAbnormalitySql abnTlAbnormalitySql=new AbnTlAbnormalitySql();
        AbnTlDtlSql abnTlDtlSql=new AbnTlDtlSql();
		if(abnTlAbnormality.getabnTldtl()!= null && abnTlAbnormality.getabnTldtl().size()>0)
		{
			for(int i =0;i<abnTlAbnormality.getabnTldtl().size();i++)
			{	
				AbnTlDtl abnTlDtl=(AbnTlDtl)abnTlAbnormality.getabnTldtl().get(i);
					    	   
				if(abnTlDtl.getAbndCreatedon()==null){
					abnTlDtl.setAbndCreatedon(abnTlAbnormality.getAbnmCreatedon());
				}
				if(abnTlDtl.getAbndModifiedon()==null){
					abnTlDtl.setAbndModifiedon(abnTlAbnormality.getAbnmModifiedon());
				}
				if(abnTlDtl.getAbndCreatedby()==null){
					abnTlDtl.setAbndCreatedby(abnTlAbnormality.getAbnmCreatedby());
				}
				abnTlDtl.setAbndAbnormalityid(abnTlAbnormality.getAbnmKeyid());
				if(!CommonFunctions.isValidKeyId(abnTlDtl.getAbndKeyid())){
					abnTlDtl.setAbndKeyid(dbActionTemplate.getSequenceNumber(AbnTlDtlSql.TBL_ABN_TL_DTL,11,"ABD","YYMM","Y"));
				    sqls.add(AbnTlDtlSql.getInsertSql(abnTlDtlSql.getAbndDbFields(),abnTlDtl.getSaveArray()));
				}else{
				    sqls.add(AbnTlDtlSql.getUpdateSql(abnTlDtlSql.getAbndDbFields(),abnTlDtl.getSaveArray()));
				}
			}
		}
		return sqls;
	}
	
	*/
	
	@Override
	public List<String[]> getMultipleAbnDetail(List<String> keyids) throws Exception {
	try
	{
	String sql = AbnTlAbnormalitySql.getMultipleAbnormality(keyids);
	List<String[]> dataList =  null;
	dataList =  dbActionTemplate.getDataList(sql);
	return dataList; 
	}
	catch (Exception e)
	{
	throw new Exception(e.getMessage()); 
	}
	}
	
	
	@Override
public List<String[]> getMultipleAbngridDetail(CommonFilter commonFilter) throws Exception {
try
{
List<String> paramValues = new ArrayList<String>();
String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
paramValues.add(condParms);			
paramValues.add(commonParams);
List<String[]> dataList =  null;
dataList =  dbActionTemplate.processFunctionCalls("ABN_FN_MULTIPLEABNORMALITY", paramValues);
if( commonFilter.getViewClick() == 'Y'){
	String totalCnt = paramValues.get(0); 
	CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
public List<String[]> getIndividualModifyForm(CommonFilter commonFilter,String abnStatus,String pillarType)throws Exception {
	try
	{
		CommonMessage.debugMsg("Inside Dao Impl");
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		condParms += "EXCEL="+"NOEXCEL"+";";
		if(UIUtils.isValidKeyId(commonFilter.getActionKeyId()))
			condParms += "EMPID="+commonFilter.getActionKeyId()+";";
		CommonMessage.debugMsg("inside condParms"+condParms);
		CommonMessage.debugMsg("Logid::::"+commonFilter.getActionKeyId());
		condParms+="STATUSNEW="+commonFilter.getAbnCatch()+";";		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		boolean isInteger = false;
		String fncName="";
		String TAGID="";
		ComboFilter cmbabnStatus = new ComboFilter();
		if(commonFilter.getAbnStatus() != null){
			if(commonFilter.getAbnStatus().getId()!= null){
				
				CommonMessage.debugMsg(commonFilter.getAbnStatus().getId() + " gsatus");
			}
		}
			
		if(pillarType.equalsIgnoreCase("SHE"))
		{
			fncName="ABN_PC_ABNORMALITY.JHN_FN_INDIVIDUALABN";			
		}
		else if(pillarType.equalsIgnoreCase("JH"))
		{	fncName="ABN_PC_ABNORMALITY.JHN_FN_INDIVIDUALABN";
		}
		
		if(abnStatus.equals("removal")||abnStatus.equals("modification"))
			{
			
			abnStatus="'P'";
			}
		else if(abnStatus.equals("view"))
		{
			CommonMessage.debugMsg("Inside the View:::::"+abnStatus);
			CommonMessage.debugMsg(commonFilter.getAbnStatus().getId() + " ghghgstatus");
			if(commonFilter.getAbnStatus()!= null){
				if(commonFilter.getAbnStatus().getId()!= null){
				}
			}
			fncName="ABN_PC_ABNORMALITY.ABN_FN_INDIVIDUALVIEW";
			abnStatus=commonFilter.getStatus();
		}
		else
		{
			CommonMessage.debugMsg(abnStatus + "  status");
			{abnStatus="'P','C','W'";}
		}
		
		cmbabnStatus.setId(abnStatus);
		commonFilter.setAbnStatus(cmbabnStatus);
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls(fncName, paramValues);
		CommonMessage.debugMsg("Before funct");
		if( commonFilter.getViewClick() == 'Y'){
			CommonMessage.debugMsg("After funct");
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("Total Count:"+totalCnt);
			isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw new Exception(e.getMessage()); 
	}
	
}

private List<String> getIndividualFilterParamValues(CommonFilter commonFilter)
{
	List<String> paramValues = new ArrayList<String>();
	String  condParam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
	if(UIUtils.isValidKeyId(commonFilter.getActionKeyId()))
		condParam += "EMPID="+commonFilter.getActionKeyId()+";";
	CommonMessage.debugMsg("Logid::::"+commonFilter.getActionKeyId());
	if("Y".equals(commonFilter.getAcceptenceRequired()))
		condParam += "ACCECPTENCE=Y;";
	String abnType = commonFilter.getDocType();
	if("SHE".equals(abnType))
		condParam += "REFDOCTYPE=SHE;";
	String afeem=commonFilter.getAbnImp();
	if("AFEEM".equals(afeem))
		condParam += "AFEEM=AFEEM;";
	
	condParam +="STATUSNEW="+commonFilter.getAbnCatch()+";";
	condParam +="EXCEL="+commonFilter.getAbnImp()+";";
	paramValues.add(condParam);
	paramValues.add(commonParam);
	return paramValues;
}

@Override
public Workbook IndividualabnExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String reportType) throws Exception {
	// TODO Auto-generated method stub
	  ResultSet rs = null;
	   try
	   {
		   	rs =   getIndividualAbnormalityResultSet(commonFilter);
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
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,reportType,2, 0,0 );
	   }
	   
	   finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
	   }
}
 private ResultSet getIndividualAbnormalityResultSet(CommonFilter commonFilter) throws Exception{
	List<String> paramValues = getIndividualFilterParamValues(commonFilter);
	if(commonFilter.getStatus().equals("view"))
	  return dbActionTemplate.dbFunctionCall("ABN_PC_ABNORMALITY.ABN_FN_INDIVIDUALVIEW", paramValues);
	else
    return  dbActionTemplate.dbFunctionCall("ABN_PC_ABNORMALITY.JHN_FN_INDIVIDUALABN", paramValues);
	
}




@Override
public AbnTlAbnormality create(AbnTlAbnormality abnTlAbnormality) throws Exception, BusinessApplicationExceptions {
	// TODO Auto-generated method stub
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	//AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table
	
	 	String elementId = abnTlAbnormality.getAbnmElementid();
	 	String location = null;
	 	String seqIdentfr = AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY;
	 	CommonMessage.debugMsg("The Location is   "+elementId);

	 	if( elementId != null && elementId.length() > 10  ){
	 		location = elementId.substring(11, 21); /* location id starts from 11  */
	 		seqIdentfr += location;
	 	}
 		CommonMessage.debugMsg("The Location is"+location);
 		CommonMessage.debugMsg("The Abnormality is"+Arrays.toString(abnTlAbnormality.getSaveArray()));

		try {
			abnTlAbnormality.setAbnmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfr,10,"AB","YYMM" ,"Y"));
			CommonMessage.debugMsg(abnTlAbnormality.getAbnmKeyid() +" ABnormality Keyid in DaoImpl....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		CommonMessage.debugMsg("The After Abnormality is"+Arrays.toString(abnTlAbnormality.getSaveArray()));
		sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table

		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		//CommonMessage.debugMsg("after dao impl");
		

	return abnTlAbnormality;

}

@Override
public AbnTlAbnormality createAbnormalitypsiNew(AbnTlAbnormality abnTlAbnormality
		) throws Exception {
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	//AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table
	
	 	String elementId = abnTlAbnormality.getAbnmElementid();
	 	String location = null;
	 	String seqIdentfr = AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY;
	 	CommonMessage.debugMsg("The Location is   "+elementId);

	 	if( elementId != null && elementId.length() > 10  ){
	 		location = elementId.substring(11, 21); /* location id starts from 11  */
	 		seqIdentfr += location;
	 	}
 		CommonMessage.debugMsg("The Location is"+location);

		try {
			abnTlAbnormality.setAbnmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfr,10,"AB","YYMM" ,"Y"));
			CommonMessage.debugMsg(abnTlAbnormality.getAbnmKeyid() +" ABnormality Keyid in DaoImpl....");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set the sequnce number
/*		String abnTagCode=getTagCode(abnTlAbnormality.getAbnmTagclassid());
 	   if(abnTagCode.equals("RED"))
		{
			if(FilterCondSql.isValidKeyId(abnTlAbnormality.getAbnmRefdocid()))
			{
				//WomTlWomst womTlWomst = new WomTlWomst();
				//updateWorkOrder(abnTlAbnormality,womTlWomst,sqls);	
			}
			else{
				//insertWorkOrder(abnTlAbnormality,sqls);
			}
		}
*/
		//String sql = actionPlanEntry(abnTlAbnormality,"I");

		//sqls.add(sql);
		sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table
		
	/*	if(abnTlDtl!=null)
		{
			abnTlDtl.setAbndAbnormalityid(abnTlAbnormality.getAbnmKeyid());
			abnTlDtl.setAbndKeyid(dbActionTemplate.getSequenceNumber(AbnTlDtlSql.TBL_ABN_TL_DTL,11,"ABD","YYMM" ,"Y")); // set the sequnce number 
			sqls.add(AbnTlDtlSql.getInsertSql(abnTlDtlSql.getAbndDbFields(), abnTlDtl.getSaveArray())); // 
		}*/
		
		//for team
		//if (abnTlAbnormality.getTeamList().size()>0) 
		
		//CommonFilterSqls.getTeamLinkSqls(abnTlAbnormality.getTeamList(),DocTypeConstants.abnm,abnTlAbnormality.getAbnmKeyid(),sqls,dbActionTemplate);
	
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		//CommonMessage.debugMsg("after dao impl");
		

	return abnTlAbnormality;
}

@Override
public AbnTlAbnormality updateAbnm(AbnTlAbnormality abnTlAbnormality) throws Exception {

	  
	/*List<String> sqls = new ArrayList<String>();  sqls for execution  
	//AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table
	String seqIdentfr = AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY;
	 
	 	AbnTlAbnormalitySql abnTlAbnormalitySql=new AbnTlAbnormalitySql();
	 
	 	
		sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table
CommonMessage.debugMsg("inside update dao impl");
		return abnTlAbnormality;*/
	List<String> sqls = new ArrayList<String>();
	CommonMessage.debugMsg(abnTlAbnormality.getAbnmKeyid());

	
	sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray()));
	// add insert sql for master table
	CommonMessage.debugMsg(sqls.get(0));
	// add insert sql for master table

CommonMessage.debugMsg("after update ");
	return abnTlAbnormality;

}
}
	





