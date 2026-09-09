package com.akranta.tpm.dao.impl;


import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MspTlMstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.MspTlDtlSql;
import com.akranta.tpm.dao.sql.MspTlHistorySql;
import com.akranta.tpm.dao.sql.MspTlMstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlDtl;
import com.akranta.tpm.model.MspTlHistory;
import com.akranta.tpm.model.MspTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class MspTlMstDaoImpl implements MspTlMstDao {


	private DBActionTemplate dbActionTemplate; 
	private String mstPNACOMP = "#f19e3d";
	private String mstPNAPEND = "#00ffda";
	private String mstPNAWIP = "#059780";
	private String mstPNAPLAN = "#fee9bf";

	public MspTlMstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public MspTlMst create(MspTlMst mspTlMst, MilestoneBean msBean) 	throws Exception,BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		List<String> dtlSqls = new ArrayList<String>();
		MspTlMstSql mspTlMstSql = new MspTlMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		MspTlDtlSql mspTlDtlSql = new MspTlDtlSql();
		MspTlHistorySql mspTlHistorySql = new MspTlHistorySql();
        // MspTlDtl mspTlDtl=new MspTlDtl(); 
	
		boolean insertMst = false;
		boolean mstSeqgen = true;		
		String mstId = null;
		if(mspTlMst.getMspTlDtl()!= null && mspTlMst.getMspTlDtl().size()>0) // check for detail table data
		{
			for(int i =0;i<mspTlMst.getMspTlDtl().size();i++)
			{
				MspTlDtl mspTlDtl =mspTlMst.getMspTlDtl().get(i);
				String revisedTgtDate = mspTlDtl.getMspdTempfield5();
				fillMilestoneDtlValues( mspTlDtl,mspTlMst);	
				String insertHistory = mspTlDtl.getMspdActive();
				mspTlDtl.setMspdActive("Y");
				CommonMessage.debugMsg("Responsibilty : "+mspTlDtl.getMspdResponsibility());
				if(CommonFunctions.isValidKeyId(mspTlDtl.getMspdKeyid()))
				{
					if(!CommonFunctions.isValidKeyId(mspTlDtl.getMspdMpmsKeyid()))
					{
						CommonMessage.debugMsg(i + " mstSeqgen: "+mstSeqgen);
						if(mstSeqgen)
						{
							mstSeqgen = false;
							insertMst = true;								
							mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
						}
						 
						mspTlDtl.setMspdMpmsKeyid(mspTlMst.getMpmsKeyid());
					}
					else
					{
						mstSeqgen = false;							
						mstId = mspTlDtl.getMspdMpmsKeyid();
					}
					//String histId = dbActionTemplate.getSingleValue(MspTlHistorySql.TBL_MSP_TL_HISTORY, "MPHI_KEYID", "MPHI_MSPD_KEYID", mspTlDtl.getMspdKeyid());
					/*List<String[]> histIds = dbActionTemplate.getDataList(MspTlHistorySql.selectHistoryIdsSql(mspTlDtl.getMspdKeyid()));
					if(histIds != null)
					{
						if(histIds.size()>0)
						{
							for(int id=0;id<histIds.size();id++)
								dtlSqls.add(MspTlHistorySql.updateStatusSql(mspTlDtl.getMspdStatus(), histIds.get(id)[0],mspTlDtl.getMspdCompletedate(),mspTlDtl.getMspdCompletedby()));
						}
					}*/
					
					if(UIUtils.isValidKeyId(msBean.getFormMode()))
					{		
						if(msBean.getFormMode().indexOf("ACTUAL")>=0)
						{
							if(mspTlDtl.getMspdStatus().equals("P"))
							{
								if(!mspTlMst.getMpmsStatus().equals("W"))
										mspTlMst.setMpmsStatus(mspTlDtl.getMspdStatus());
							}
							else if(mspTlDtl.getMspdStatus().equals("W"))
								mspTlMst.setMpmsStatus(mspTlDtl.getMspdStatus());
						}
					}
					String status = dbActionTemplate.getSingleValue(MspTlDtlSql.TBL_MSP_TL_DTL, "MSPD_STATUS", "MSPD_KEYID", mspTlDtl.getMspdKeyid());
					String responsibility = dbActionTemplate.getSingleValue(MspTlDtlSql.TBL_MSP_TL_DTL, "MSPD_COMPLETEDBY", "MSPD_KEYID", mspTlDtl.getMspdKeyid());
					String assignedto = dbActionTemplate.getSingleValue(MspTlDtlSql.TBL_MSP_TL_DTL, "MSPD_ASSIGNEDTO", "MSPD_KEYID", mspTlDtl.getMspdKeyid());
					CommonMessage.debugMsg("status : "+status + "---------> "+mspTlDtl.getMspdStatus());
					CommonMessage.debugMsg("responsibility : "+responsibility + "---------> "+mspTlDtl.getMspdCompletedby());
					CommonMessage.debugMsg("assignedto : "+assignedto + "---------> "+mspTlDtl.getMspdAssignedto());
					CommonMessage.debugMsg("assignedto CONDN: "+!assignedto.equals(mspTlDtl.getMspdAssignedto()));
					if(UIUtils.isValidKeyId(status))
					{
						if(!status.equals(mspTlDtl.getMspdStatus()) || !responsibility.equals(mspTlDtl.getMspdCompletedby()) ||!assignedto.equals(mspTlDtl.getMspdAssignedto()) )
						{
							CommonMessage.debugMsg(!status.equals(mspTlDtl.getMspdStatus())+" Inside "+!responsibility.equals(mspTlDtl.getMspdCompletedby()));
							insertHistory = "Y";
							revisedTgtDate = mspTlDtl.getMspdTargetdate();
						}
					}
					
					dtlSqls.add(MspTlDtlSql.getUpdateSql(mspTlDtlSql.getMspdDbFields(), mspTlDtl.getSaveArray()));
					//dtlSqls.add(MspTlDtlSql.getUpdateSql1(mspTlDtl.getMspdMpmsKeyid()));
				}
				else
				{
					CommonMessage.debugMsg(i + " else mstSeqgen: "+mstSeqgen);
					if(mstSeqgen)
					{
						String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
						if(UIUtils.isValidKeyId(overlap))
						{
							if(Integer.parseInt(overlap)<=0)
							{
								mstSeqgen = false;
								insertMst = true;							
								mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
							}
							else
							{
								String mstkey = dbActionTemplate.getSingleValue(MspTlMstSql.getMstSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
								if(UIUtils.isValidKeyId(mstkey))
									mstId = mstkey;
							}
						}
						else
						{
							mstSeqgen = false;
							insertMst = true;							
							mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
						}
					}
					if(!CommonFunctions.isValidKeyId(mstId))
						mspTlDtl.setMspdMpmsKeyid(mspTlMst.getMpmsKeyid());
					else
						mspTlDtl.setMspdMpmsKeyid(mstId);
					mspTlDtl.setMspdKeyid(dbActionTemplate.getSequenceNumber(MspTlDtlSql.TBL_MSP_TL_DTL, 12, "MSD", "YYMM", "Y")); // set the sequnce number
					dtlSqls.add(MspTlDtlSql.getInsertSql(mspTlDtlSql.getMspdDbFields(), mspTlDtl.getSaveArray()));
				}
					
				if(CommonFunctions.isValidKeyId(insertHistory))
				{
					if(insertHistory.equals("Y"))
					{
						CommonMessage.debugMsg("insertHistory");
						MspTlHistory mspTlHistory = fillHistoryValues( mspTlDtl,revisedTgtDate);
						mspTlHistory.setMphiKeyid(dbActionTemplate.getSequenceNumber(MspTlHistorySql.TBL_MSP_TL_HISTORY, 12, "MHI", "YYMM", "Y")); // set the sequnce number
						dtlSqls.add(MspTlHistorySql.getInsertSql(mspTlHistorySql.getMphiDbFields(), mspTlHistory.getSaveArray()));
						CommonMessage.debugMsg("update target date "+revisedTgtDate);
						dtlSqls.add("Update "+MspTlDtlSql.TBL_MSP_TL_DTL+" set MSPD_TARGETDATE='"+revisedTgtDate+"' where mspd_keyid='"+mspTlDtl.getMspdKeyid()+"'");
					}
				}
			}
		}
		else
		{
			String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
			if(UIUtils.isValidKeyId(overlap))
			{
				if(Integer.parseInt(overlap)<=0)
				{
					if(UIUtils.isValidKeyId(msBean.getMilestoneFromDupDt()) && UIUtils.isValidKeyId(msBean.getMilestoneToDupDt()))
					{
						String mstkey = dbActionTemplate.getSingleValue(MspTlMstSql.getMstSql(mspTlMst.getMpmsIndicatorid(),mspTlMst.getMpmsCellid(),msBean.getMilestoneFromDupDt(),msBean.getMilestoneToDupDt()));
						if(UIUtils.isValidKeyId(mstkey))
						{
													
							mspTlMst.setMpmsKeyid(mstkey);
						}
					}
					else
					{
						mstSeqgen = false;
						insertMst = true;							
						mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
					}
				}
				else
				{
					String mstkey = dbActionTemplate.getSingleValue(MspTlMstSql.getMstSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
					if(UIUtils.isValidKeyId(mstkey))
						mspTlMst.setMpmsKeyid(mstkey);
				}
			}
		}
			CommonMessage.debugMsg("insertMst : "+insertMst);
			
		if(insertMst)
		{
			String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
			if(UIUtils.isValidKeyId(overlap))
			{
				if(Integer.parseInt(overlap)>0)
					throw new BusinessApplicationExceptions("overlap,");
			}
			sqls.add(MspTlMstSql.getInsertSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
		}
		else
		{
			if(UIUtils.isValidKeyId(msBean.getFormMode()))
			{		
				if(UIUtils.isValidKeyId(mstId))
					mspTlMst.setMpmsKeyid(mstId);
				if(msBean.getFormMode().indexOf("ACTUAL")>=0)
				//	MspTlDtl mspTlDtl=new MspTlDtl(); 
					
					sqls.add(MspTlMstSql.getUpdateMstSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
				   
				else
					//MspTlDtl mspTlDtl=new MspTlDtl(); 
					sqls.add(MspTlMstSql.getUpdatePlanMstSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
				    //sqls.add(MspTlDtlSql.getUpdateSql1(mspTlDtl.getMspdMpmsKeyid()));
				sqls.add(MspTlMstSql.getStatusSql(mspTlMst.getMpmsKeyid()));	
			}
			
		}
		for(int i=0;i<dtlSqls.size();i++)
		{
			sqls.add(dtlSqls.get(i).toString());
		}
		dbActionTemplate.executeStatements(sqls);
		return mspTlMst;
	}
	public MspTlMst createDuplicate(MspTlMst mspTlMst, MilestoneBean msBean) 	throws Exception,BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		List<String> dtlSqls = new ArrayList<String>();
		MspTlMstSql mspTlMstSql = new MspTlMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		MspTlDtlSql mspTlDtlSql = new MspTlDtlSql();
		MspTlHistorySql mspTlHistorySql = new MspTlHistorySql();
	
		boolean insertMst = false;
		boolean mstSeqgen = true;		
		String mstId = null;
		if(mspTlMst.getMspTlDtl()!= null && mspTlMst.getMspTlDtl().size()>0) // check for detail table data
		{
			for(int i =0;i<mspTlMst.getMspTlDtl().size();i++)
			{
				MspTlDtl mspTlDtl =mspTlMst.getMspTlDtl().get(i);
				String revisedTgtDate = mspTlDtl.getMspdTempfield5();
				fillMilestoneDtlValues( mspTlDtl,mspTlMst);	
				String insertHistory = mspTlDtl.getMspdActive();
				mspTlDtl.setMspdActive("Y");
				if(CommonFunctions.isValidKeyId(mspTlDtl.getMspdKeyid()))
				{
					if(!CommonFunctions.isValidKeyId(mspTlDtl.getMspdMpmsKeyid()))
					{
						CommonMessage.debugMsg(i + " mstSeqgen: "+mstSeqgen);
						if(mstSeqgen)
						{
							mstSeqgen = false;
							insertMst = true;								
							mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
						}
						 
						mspTlDtl.setMspdMpmsKeyid(mspTlMst.getMpmsKeyid());
					}
					else
					{
						mstSeqgen = false;							
						mstId = mspTlDtl.getMspdMpmsKeyid();
					}
					//String histId = dbActionTemplate.getSingleValue(MspTlHistorySql.TBL_MSP_TL_HISTORY, "MPHI_KEYID", "MPHI_MSPD_KEYID", mspTlDtl.getMspdKeyid());
					List<String[]> histIds = dbActionTemplate.getDataList(MspTlHistorySql.selectHistoryIdsSql(mspTlDtl.getMspdKeyid()));
					if(histIds != null)
					{
						if(histIds.size()>0)
						{
							for(int id=0;id<histIds.size();id++)
								dtlSqls.add(MspTlHistorySql.updateStatusSql(mspTlDtl.getMspdStatus(), histIds.get(id)[0],mspTlDtl.getMspdCompletedate(),mspTlDtl.getMspdCompletedby()));
						}
					}
					
					if(UIUtils.isValidKeyId(msBean.getFormMode()))
					{		
						if(msBean.getFormMode().indexOf("ACTUAL")>=0)
						{
							if(mspTlDtl.getMspdStatus().equals("P"))
							{
								if(!mspTlMst.getMpmsStatus().equals("W"))
										mspTlMst.setMpmsStatus(mspTlDtl.getMspdStatus());
							}
							else if(mspTlDtl.getMspdStatus().equals("W"))
								mspTlMst.setMpmsStatus(mspTlDtl.getMspdStatus());
						}
					}
					
					
					dtlSqls.add(MspTlDtlSql.getUpdateSql(mspTlDtlSql.getMspdDbFields(), mspTlDtl.getSaveArray()));
				}
				else
				{
					CommonMessage.debugMsg(i + " else mstSeqgen: "+mstSeqgen);
					if(mstSeqgen)
					{
						String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
						if(UIUtils.isValidKeyId(overlap))
						{
							if(Integer.parseInt(overlap)<=0)
							{
								mstSeqgen = false;
								insertMst = true;							
								mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
							}
							else
							{
								String mstkey = dbActionTemplate.getSingleValue(MspTlMstSql.getMstSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
								if(UIUtils.isValidKeyId(mstkey))
									mstId = mstkey;
							}
						}
						else
						{
							mstSeqgen = false;
							insertMst = true;							
							mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
						}
					}
					if(!CommonFunctions.isValidKeyId(mstId))
						mspTlDtl.setMspdMpmsKeyid(mspTlMst.getMpmsKeyid());
					else
						mspTlDtl.setMspdMpmsKeyid(mstId);
					mspTlDtl.setMspdKeyid(dbActionTemplate.getSequenceNumber(MspTlDtlSql.TBL_MSP_TL_DTL, 12, "MSD", "YYMM", "Y")); // set the sequnce number
					dtlSqls.add(MspTlDtlSql.getInsertSql(mspTlDtlSql.getMspdDbFields(), mspTlDtl.getSaveArray()));
				}
					
				if(CommonFunctions.isValidKeyId(insertHistory))
				{
					if(insertHistory.equals("Y"))
					{
						CommonMessage.debugMsg("insertHistory");
						MspTlHistory mspTlHistory = fillHistoryValues( mspTlDtl,revisedTgtDate);
						mspTlHistory.setMphiKeyid(dbActionTemplate.getSequenceNumber(MspTlHistorySql.TBL_MSP_TL_HISTORY, 12, "MHI", "YYMM", "Y")); // set the sequnce number
						dtlSqls.add(MspTlHistorySql.getInsertSql(mspTlHistorySql.getMphiDbFields(), mspTlHistory.getSaveArray()));
						CommonMessage.debugMsg("update target date "+revisedTgtDate);
						dtlSqls.add("Update "+MspTlDtlSql.TBL_MSP_TL_DTL+" set MSPD_TARGETDATE='"+revisedTgtDate+"' where mspd_keyid='"+mspTlDtl.getMspdKeyid()+"'");
					}
				}
			}
		}
		else
		{
			insertMst = true;
			if(!UIUtils.isValidKeyId(mspTlMst.getMpmsKeyid()))
				mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
		}
			CommonMessage.debugMsg("insertMst : "+insertMst);
			
		if(insertMst)
		{
			String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
			if(UIUtils.isValidKeyId(overlap))
			{
				if(Integer.parseInt(overlap)>0)
					throw new BusinessApplicationExceptions("overlap,");
			}
			sqls.add(MspTlMstSql.getInsertSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
		}
		else
		{
			if(UIUtils.isValidKeyId(msBean.getFormMode()))
			{		
				if(msBean.getFormMode().indexOf("ACTUAL")>=0)
				{
					if(UIUtils.isValidKeyId(mstId))
						mspTlMst.setMpmsKeyid(mstId);
					sqls.add(MspTlMstSql.getUpdateMstSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
				}
			}
			
		}
		for(int i=0;i<dtlSqls.size();i++)
		{
			sqls.add(dtlSqls.get(i).toString());
		}
		dbActionTemplate.executeStatements(sqls);
		return mspTlMst;
	}
	
	public MspTlMst update(MspTlMst mspTlMst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		MspTlMstSql mspTlMstSql = new MspTlMstSql();
		try {

			sqls.add(MspTlMstSql.getUpdateSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray()));	
			sqls.add(MspTlMstSql.getStatusSql(mspTlMst.getMpmsKeyid()));	
			dbActionTemplate.executeStatements(sqls);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return mspTlMst;
	}
	
	public MspTlMst delete(MspTlMst mspTlMst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		MspTlMstSql mspTlMstSql = new MspTlMstSql();
		try {
			
			sqls.add(MspTlMstSql.getDeleteSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return mspTlMst;
	}
	public MspTlDtl deleteMilestone(MspTlDtl mspTlDtl)throws Exception {

		List<String> sqls = new ArrayList<String>();
		MspTlDtlSql mspTlDtlSql = new MspTlDtlSql();
		//MspTlMstSql mspTlMstSql = new MspTlMstSql();
		try {
			//boolean delMst = false;
			
			String count = dbActionTemplate.getSingleValue(MspTlHistorySql.getHistoryCountSql(mspTlDtl.getMspdKeyid()));
			CommonMessage.debugMsg("count "+count);
			if(UIUtils.isValidKeyId(count))
			{
				if(Integer.parseInt(count)>=0)
				{
					sqls.add(MspTlHistorySql.delHistorySql(mspTlDtl.getMspdKeyid()));
				}
			}
			/*String mstId = dbActionTemplate.getSingleValue(MspTlDtlSql.TBL_MSP_TL_DTL, "MSPD_MPMS_KEYID", "MSPD_KEYID", mspTlDtl.getMspdKeyid());
			if(UIUtils.isValidKeyId(mstId))
			{
				String count = dbActionTemplate.getSingleValue(MspTlDtlSql.getCountSql(mstId,mspTlDtl.getMspdKeyid()));
				CommonMessage.debugMsg("count "+count);
				if(UIUtils.isValidKeyId(count))
				{
					if(Integer.parseInt(count)<=0)
					{
						delMst = true;
						CommonMessage.debugMsg("delMst "+delMst);
					}
						
				}
			}*/
			sqls.add(MspTlDtlSql.getDeleteSql(mspTlDtlSql.getMspdDbFields(), mspTlDtl.getSaveArray()));
			/*if(delMst)
			{
				MspTlMst mspTlMst = new MspTlMst();
				mspTlMst.setMpmsKeyid(mstId);
				sqls.add(MspTlMstSql.getDeleteSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray()));
			}*/
				
			dbActionTemplate.executeStatements(sqls);			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return mspTlDtl;
	}
	public MspTlMst deleteMilestone(MspTlMst mspTlMst,MilestoneBean msBean)throws Exception
	{
		List<String> sqls = new ArrayList<String>();
		MspTlDtlSql mspTlDtlSql = new MspTlDtlSql();
		MspTlMstSql mspTlMstSql = new MspTlMstSql();
		boolean delMst = false;
		if(mspTlMst.getMspTlDtl()!= null && mspTlMst.getMspTlDtl().size()>0)
		{
			for(int i =0;i<mspTlMst.getMspTlDtl().size();i++)
			{
				delMst = false;
				MspTlDtl mspTlDtl = mspTlMst.getMspTlDtl().get(i);
				String count = dbActionTemplate.getSingleValue(MspTlHistorySql.getHistoryCountSql(mspTlDtl.getMspdKeyid()));
				CommonMessage.debugMsg("History Count "+count);
				if(UIUtils.isValidKeyId(count))
				{
					if(Integer.parseInt(count)>=0)
					{
						sqls.add(MspTlHistorySql.delHistorySql(mspTlDtl.getMspdKeyid()));
					}
				}
				String mstId = dbActionTemplate.getSingleValue(MspTlDtlSql.TBL_MSP_TL_DTL, "MSPD_MPMS_KEYID", "MSPD_KEYID", mspTlDtl.getMspdKeyid());
				if(UIUtils.isValidKeyId(mstId))
				{
					delMst = true;
					mspTlMst.setMpmsKeyid(mstId);
				}
				
				sqls.add(MspTlDtlSql.getDeleteSql(mspTlDtlSql.getMspdDbFields(), mspTlDtl.getSaveArray()));
			}
		}
		else
		{
			String fromDate =  msBean.getMilestoneFromDt();
			if(UIUtils.isValidKeyId(msBean.getFormMode()))
			{
				if(msBean.getFormMode().equalsIgnoreCase("Plan"))
					 fromDate = UIUtils.isValidKeyId(msBean.getMilestoneFromDupDt())?msBean.getMilestoneFromDupDt():fromDate;
			}
			String mstId = dbActionTemplate.getSingleValue(MspTlMstSql.selectMstIDSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(),fromDate, msBean.getFormMode()));
			if(UIUtils.isValidKeyId(mstId))
			{
				delMst = true;
				mspTlMst.setMpmsKeyid(mstId);
			}
			
		}
		CommonMessage.debugMsg("delMst "+delMst);
	//	CommonMessage.debugMsg("mstId "+mstId);
		if(delMst)
		{
			if(UIUtils.isValidKeyId(mspTlMst.getMpmsKeyid()))
				sqls.add(MspTlMstSql.getDeleteSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}
		
		return mspTlMst;
	}
	
	public List<String[]> getAllMilestones(CommonFilter commonFilter,String indicatorId,String cellId,String mode,String fromDate) throws Exception {
		
		try
		{
			if(UIUtils.isValidKeyId(indicatorId) && UIUtils.isValidKeyId(cellId))
			{
				 if( commonFilter.getViewClick() == 'Y'){				 	
						String totalCnt = dbActionTemplate.getSingleValue(MspTlMstSql.getTotalMilestones(indicatorId,cellId,fromDate)); 
						CommonMessage.debugMsg("totalCnt"+totalCnt);
						boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
						if(  isInteger ){
							commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
						}
					 }
				 String sql = MspTlMstSql.selectMilestonesSql(indicatorId,cellId,mode,fromDate);
				CommonMessage.debugMsg(sql);
				if(UIUtils.isValidKeyId(sql))
					return dbActionTemplate.getDataList(sql);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public MspTlDtl select(String keyId) 	throws Exception {
		MspTlDtl mspTlDtl = new MspTlDtl();
		String sql = MspTlDtlSql.selectSql(keyId);			
		Object [] args =  new Object [] {};
		mspTlDtl.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		
		return mspTlDtl;
	}
	public MspTlMst selectMst(String indicatorId,String cellId,String fromDate,String toDate,String flag) 	throws Exception {
		MspTlMst mspTlMst = new MspTlMst();
		String cnt = dbActionTemplate.getSingleValue(MspTlMstSql.selectMstCountSql(indicatorId, cellId, fromDate,toDate,flag));
		CommonMessage.debugMsg("cOUNT : "+cnt);
		if(UIUtils.isValidKeyId(cnt))
		{
			if(Integer.parseInt(cnt)>0)
			{
				String sql = MspTlMstSql.selectMstSql(indicatorId,cellId,fromDate,toDate,flag);
				CommonMessage.debugMsg("sql : "+sql);
				Object [] args =  new Object [] {};
				mspTlMst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
				
				return mspTlMst;
			}
		}
		return null;
	}
	public List<String[]> getAllHistory(String dtlId) throws Exception
	{
		 String sql = MspTlHistorySql.selectHistorySql(dtlId);
		 return dbActionTemplate.getDataList(sql);
	}
	public List<String[]> getColorsFromConfiguration() throws Exception
	{
		 String sql = MspTlMstSql.selectColorsSql();
		 return dbActionTemplate.getDataList(sql);
	}
	private MspTlDtl  fillMilestoneDtlValues(MspTlDtl mspTlDtl,MspTlMst mspTlMst) throws Exception
	{
		if(CommonFunctions.isValidKeyId(mspTlDtl.getMspdKeyid()))
		{
			MspTlDtl mspTlDtls = select(mspTlDtl.getMspdKeyid());
			//CommonMessage.debugMsg(mspTlDtls.getMspdKeyid() + " : "+mspTlDtls.getMspdResponsibility());
			if(!UIUtils.isValidKeyId(mspTlDtls.getMspdCreatedby()))
				mspTlDtl.setMspdCreatedby(mspTlDtls.getMspdCreatedby());
			else
				mspTlDtl.setMspdCreatedby(mspTlMst.getMpmsCreatedby());
			
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdResponsibility()))
				mspTlDtl.setMspdResponsibility(mspTlDtls.getMspdResponsibility());
			else
			{
				if(UIUtils.isValidKeyId(mspTlMst.getMpmsResponsibility()))
					mspTlDtl.setMspdResponsibility(mspTlMst.getMpmsResponsibility());
				else
					mspTlDtl.setMspdResponsibility("{}");
			}
			
			
			if(!UIUtils.isValidKeyId(mspTlDtls.getMspdCreatedon()))
				mspTlDtl.setMspdCreatedon(mspTlDtls.getMspdCreatedon());
			else
				mspTlDtl.setMspdCreatedon(mspTlMst.getMpmsCreatedon());
			
			if(!UIUtils.isValidKeyId(mspTlDtls.getMspdModifiedon()))
				mspTlDtl.setMspdModifiedon(mspTlDtls.getMspdModifiedon());
			else
				mspTlDtl.setMspdModifiedon(mspTlMst.getMpmsModifiedon());
			
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdCompletedate()))
			{
				if(!UIUtils.isValidKeyId(mspTlDtl.getMspdCompletedate()))
					mspTlDtl.setMspdCompletedate(mspTlDtls.getMspdCompletedate());
			}
			else
			{
				if(!UIUtils.isValidKeyId(mspTlDtl.getMspdCompletedate()))
					mspTlDtl.setMspdCompletedate(Constants.futureNullDate);
			}
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdCompletedby()))
			{
				if(!UIUtils.isValidKeyId(mspTlDtl.getMspdCompletedby()))
					mspTlDtl.setMspdCompletedby(mspTlDtls.getMspdCompletedby());
			}
			else
			{
				if(!UIUtils.isValidKeyId(mspTlDtl.getMspdCompletedby()))
					mspTlDtl.setMspdCompletedby("{}");
			}
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdRemarks()))
			{
				if(!UIUtils.isValidKeyId(mspTlDtl.getMspdRemarks()))
					mspTlDtl.setMspdRemarks(mspTlDtls.getMspdRemarks());
			}
			else
			{
				if(!UIUtils.isValidKeyId(mspTlDtl.getMspdRemarks()))
					mspTlDtl.setMspdRemarks("{}");
			}
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdFactoryid()))
				mspTlDtl.setMspdFactoryid(mspTlDtls.getMspdFactoryid());
			else
				mspTlDtl.setMspdFactoryid(mspTlMst.getMpmsFactoryid());
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdSectionid()))
				mspTlDtl.setMspdSectionid(mspTlDtls.getMspdSectionid());
			else
				mspTlDtl.setMspdSectionid(mspTlMst.getMpmsSectionid());
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdCellid()))
				mspTlDtl.setMspdCellid(mspTlDtls.getMspdCellid());
			else
				mspTlDtl.setMspdCellid(mspTlMst.getMpmsCellid());
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdIndicatorid()))
				mspTlDtl.setMspdIndicatorid(mspTlDtls.getMspdIndicatorid());
			else
				mspTlDtl.setMspdIndicatorid(mspTlMst.getMpmsIndicatorid());	
			if(UIUtils.isValidKeyId(mspTlDtls.getMspdMpmsKeyid()))
				mspTlDtl.setMspdMpmsKeyid(mspTlDtls.getMspdMpmsKeyid());
		}
		else
		{
			if(UIUtils.isValidKeyId(mspTlMst.getMpmsResponsibility()))
				mspTlDtl.setMspdResponsibility(mspTlMst.getMpmsResponsibility());
			else
				mspTlDtl.setMspdResponsibility("{}");
			if(!UIUtils.isValidKeyId(mspTlDtl.getMspdCompletedate()))
				mspTlDtl.setMspdCompletedate(Constants.futureNullDate);
			if(!UIUtils.isValidKeyId(mspTlDtl.getMspdCompletedby()))
				mspTlDtl.setMspdCompletedby("{}");
			if(!UIUtils.isValidKeyId(mspTlDtl.getMspdRemarks()))
				mspTlDtl.setMspdRemarks("{}");
				mspTlDtl.setMspdCreatedby(mspTlMst.getMpmsCreatedby());
				mspTlDtl.setMspdCreatedon(mspTlMst.getMpmsCreatedon());
				mspTlDtl.setMspdModifiedon(mspTlMst.getMpmsModifiedon());	
				mspTlDtl.setMspdFactoryid(mspTlMst.getMpmsFactoryid());
				mspTlDtl.setMspdSectionid(mspTlMst.getMpmsSectionid());
				mspTlDtl.setMspdCellid(mspTlMst.getMpmsCellid());	
				mspTlDtl.setMspdIndicatorid(mspTlMst.getMpmsIndicatorid());
		}
		mspTlDtl.setMspdTempfield1("-");
		mspTlDtl.setMspdTempfield2("-");
		mspTlDtl.setMspdTempfield3("-");
		mspTlDtl.setMspdTempfield4("-");
		mspTlDtl.setMspdTempfield5("-");
		mspTlDtl.setMspdTempfield6("-");
		mspTlDtl.setMspdTempfield7("-");
		mspTlDtl.setMspdTempfield8("-");
		//mspTlDtl.setMspdTempfield9("-");
		//mspTlDtl.setMspdTempfield10("-");
		return mspTlDtl;
	}
	private MspTlHistory  fillHistoryValues(MspTlDtl mspTlDtl,String revisedTgtDate) throws Exception
	{
		MspTlHistory mspTlHistory = new MspTlHistory();
		mspTlHistory.setMphiActive("Y");
		mspTlHistory.setMphiCreatedby(mspTlDtl.getMspdCreatedby());
		mspTlHistory.setMphiCreatedon(mspTlDtl.getMspdCreatedon());
		mspTlHistory.setMphiModifiedon(mspTlDtl.getMspdModifiedon());
		mspTlHistory.setMphiTempfield1("-");
		mspTlHistory.setMphiTempfield2("-");
		mspTlHistory.setMphiTempfield3("-");
		mspTlHistory.setMphiTempfield4("-");
		mspTlHistory.setMphiTempfield5("-");
		mspTlHistory.setMphiTempfield6("-");
		mspTlHistory.setMphiTempfield7("-");
		mspTlHistory.setMphiTempfield8("-");
		mspTlHistory.setMphiTempfield9("-");
		//mspTlHistory.setMphiTempfield10("-");
		mspTlHistory.setMphiAssignedto(mspTlDtl.getMspdAssignedto());
		mspTlHistory.setMphiStatus(mspTlDtl.getMspdStatus());
		mspTlHistory.setMphiRemarks(mspTlDtl.getMspdRemarks());
		if(CommonFunctions.isValidKeyId(mspTlDtl.getMspdStatus()))
		{
			CommonMessage.debugMsg("Status In History : "+mspTlDtl.getMspdStatus());
			if(mspTlDtl.getMspdStatus().equals("C"))
				mspTlHistory.setMphiCompletedby(mspTlDtl.getMspdCompletedby());
			else
				mspTlHistory.setMphiCompletedby("{}");
			CommonMessage.debugMsg("Completedby In History : "+mspTlHistory.getMphiCompletedby());
		}
		else
			mspTlHistory.setMphiCompletedby("{}");
		mspTlHistory.setMphiResponsibility(mspTlDtl.getMspdCompletedby());
		mspTlHistory.setMphiMspdKeyid(mspTlDtl.getMspdKeyid());
		mspTlHistory.setMphiCompletedate(mspTlDtl.getMspdCompletedate());
		/*if(UIUtils.isValidKeyId(revisedTgtDate))
			mspTlHistory.setMphiTargetdate(revisedTgtDate);
		else*/
			mspTlHistory.setMphiTargetdate(mspTlDtl.getMspdTargetdate());
		return mspTlHistory;
	}

	@Override
	public Workbook getAllHistoryExcel(String dtlId,JSONObject tblJSONObj,String format) throws Exception {
		 ResultSet rs = null;
		 ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		 rs =   getAllHistoryResultSet(dtlId);
		 return excelUtils.writeToExcel(rs,format, 0,0,0 );
	}

	private ResultSet getAllHistoryResultSet(String dtlId) {
		String sql = MspTlHistorySql.selectHistorySql(dtlId);
		CommonMessage.debugMsg("sql:"+sql);
		try {
			return  dbActionTemplate.getData(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	private ResultSet getPlanActualResultSet(CommonFilter commonFilter,String pillar) throws Exception
	{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter)+"PILLARID="+pillar+";";;
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
		
			return dbActionTemplate.dbFunctionCall("MSP_PC_MASTERPLAN.MSP_FN_MASTERPLAN", paramValues);
	}
	@Override
	public Workbook getPlanActualExcel(String pillar, JSONObject jsonObject,
			CommonFilter commonFilter, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getPlanActualResultSet(commonFilter,pillar);
			ExcelUtils excelUtils = new ExcelUtils(jsonObject);
			List<String[]> colorList = getColorsFromConfiguration();
			if(colorList != null)
			{
				if(colorList.size()>0)
				{
					for(int i=0;i<colorList.size();i++)
		   			{
						if(colorList.get(i)[0].equalsIgnoreCase("MSTPNAPLAN"))
							mstPNAPLAN = colorList.get(i)[1];
						else if(colorList.get(i)[0].equalsIgnoreCase("MSTPNACOMP"))
							mstPNACOMP = colorList.get(i)[1];
						if(colorList.get(i)[0].equalsIgnoreCase("MSTPNAPEND"))
							mstPNAPEND = colorList.get(i)[1];
						if(colorList.get(i)[0].equalsIgnoreCase("MSTPNAWIP"))
							mstPNAWIP = colorList.get(i)[1];
		   			}
				}
			}
			Color planColr = hex2Rgb(mstPNAPLAN);
			Color pendColr = hex2Rgb(mstPNAPEND);
			Color wipColr = hex2Rgb(mstPNAWIP);
			Color compColr = hex2Rgb(mstPNACOMP);
			
			CommonMessage.debugMsg("------------>");
			CommonMessage.debugMsg(planColr);
			CommonMessage.debugMsg(pendColr);
			CommonMessage.debugMsg(wipColr);
			CommonMessage.debugMsg(compColr);
			CommonMessage.debugMsg("------------");
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short)8);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(6);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("0");
			condFormat.setIdentfier("Zero");		
			condFormat.setSymbolStr("");
			condFormats.add(condFormat);
			
			XLConditionalFormats starCondFormat = new XLConditionalFormats();
			starCondFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			starCondFormat.setFontHeightPoint((short)8);
			starCondFormat.setFontBoldWeight((short)20);
			starCondFormat.setFromCol(6);
			starCondFormat.setToCol(-1);
			starCondFormat.setOperator(ComparisonOperator.EQUAL);
			starCondFormat.setCondValue("C*");
			starCondFormat.setIdentfier("CompMilestone");			
			starCondFormat.setBgColor(new RGB(compColr.getRed(), compColr.getGreen(), compColr.getBlue()));//#f19e3d
			starCondFormat.setSymbolStr("*");
			condFormats.add(starCondFormat);
			
			XLConditionalFormats starCondFormat2 = new XLConditionalFormats();
			starCondFormat2.setFontName(XLConditionalFormats.FONT_DEFAULT);
			starCondFormat2.setFontHeightPoint((short)8);
			starCondFormat2.setFontBoldWeight((short)20);
			starCondFormat2.setFromCol(6);
			starCondFormat2.setToCol(-1);
			starCondFormat2.setOperator(ComparisonOperator.EQUAL);
			starCondFormat2.setCondValue("P*");
			starCondFormat2.setIdentfier("PendMilestone");
			starCondFormat2.setBgColor(new RGB(pendColr.getRed(), pendColr.getGreen(), pendColr.getBlue()));//#00ffda
			starCondFormat2.setSymbolStr("*");
			condFormats.add(starCondFormat2);
			
			
			XLConditionalFormats starCondFormat3 = new XLConditionalFormats();
			starCondFormat3.setFontName(XLConditionalFormats.FONT_DEFAULT);
			starCondFormat3.setFontHeightPoint((short)8);
			starCondFormat3.setFontBoldWeight((short)20);
			starCondFormat3.setFromCol(6);
			starCondFormat3.setToCol(-1);
			starCondFormat3.setOperator(ComparisonOperator.EQUAL);
			starCondFormat3.setCondValue("W*");
			starCondFormat3.setIdentfier("WIPMilestone");
			starCondFormat3.setBgColor(new RGB(wipColr.getRed(), wipColr.getGreen(), wipColr.getBlue()));//#059780
			starCondFormat3.setSymbolStr("*");
			condFormats.add(starCondFormat3);
			
			
			
			XLConditionalFormats pendCondFormat = new XLConditionalFormats();
			pendCondFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			pendCondFormat.setFontHeightPoint((short)8);
			pendCondFormat.setFontBoldWeight((short)20);
			pendCondFormat.setFromCol(6);
			pendCondFormat.setToCol(-1);
			pendCondFormat.setOperator(ComparisonOperator.EQUAL);
			pendCondFormat.setCondValue("P");	
			pendCondFormat.setIdentfier("Pending");	
			pendCondFormat.setBgColor(new RGB(pendColr.getRed(), pendColr.getGreen(), pendColr.getBlue()));//#00ffda
			pendCondFormat.setSymbolStr("");
			condFormats.add(pendCondFormat);
			
			XLConditionalFormats compCondFormat = new XLConditionalFormats();
			compCondFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			compCondFormat.setFontHeightPoint((short)8);
			compCondFormat.setFontBoldWeight((short)20);		
			compCondFormat.setFromCol(6);
			compCondFormat.setToCol(-1);
			compCondFormat.setOperator(ComparisonOperator.EQUAL);
			compCondFormat.setCondValue("C");	
			compCondFormat.setIdentfier("Completed");
			compCondFormat.setBgColor(new RGB(compColr.getRed(), compColr.getGreen(), compColr.getBlue()));//#f19e3d
			compCondFormat.setSymbolStr("");
			condFormats.add(compCondFormat);
			
			XLConditionalFormats wipCondFormat = new XLConditionalFormats();
			wipCondFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			wipCondFormat.setFontHeightPoint((short)8);
			wipCondFormat.setFontBoldWeight((short)20);		
			wipCondFormat.setFromCol(6);
			wipCondFormat.setToCol(-1);
			wipCondFormat.setOperator(ComparisonOperator.EQUAL);
			wipCondFormat.setCondValue("W");	
			wipCondFormat.setIdentfier("WorkInProgress");
			wipCondFormat.setBgColor(new RGB(wipColr.getRed(), wipColr.getGreen(), wipColr.getBlue()));//#059780
			wipCondFormat.setSymbolStr("");
			condFormats.add(wipCondFormat);
			
			XLConditionalFormats planCondFormat = new XLConditionalFormats();
			planCondFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			planCondFormat.setFontHeightPoint((short)8);
			planCondFormat.setFontBoldWeight((short)20);
			planCondFormat.setFromCol(6);
			planCondFormat.setToCol(-1);
			planCondFormat.setOperator(ComparisonOperator.EQUAL);
			planCondFormat.setCondValue("X");	
			planCondFormat.setIdentfier("Plan");	
			planCondFormat.setBgColor(new RGB(planColr.getRed(), planColr.getGreen(), planColr.getBlue()));//#fee9bf
			planCondFormat.setSymbolStr("");
			condFormats.add(planCondFormat);
			
			XLConditionalFormats planCondFormat2 = new XLConditionalFormats();
			planCondFormat2.setFontName(XLConditionalFormats.FONT_DEFAULT);
			planCondFormat2.setFontHeightPoint((short)8);
			planCondFormat2.setFontBoldWeight((short)20);			
			planCondFormat2.setFromCol(6);
			planCondFormat2.setToCol(-1);
			planCondFormat2.setOperator(ComparisonOperator.EQUAL);
			planCondFormat2.setCondValue("X*");	
			planCondFormat2.setIdentfier("PlanMilestone");	
			planCondFormat2.setBgColor(new RGB(planColr.getRed(), planColr.getGreen(), planColr.getBlue()));//#fee9bf
			planCondFormat2.setSymbolStr("*");
			condFormats.add(planCondFormat2);
			
			
			
			/*int hex = 0x00ffda;
		    int r = (hex & 0xFF0000) >> 16;
		    int g = (hex & 0xFF00) >> 8;
		    int b = (hex & 0xFF);
			CommonMessage.debugMsg("------------>");
			CommonMessage.debugMsg(r);
			CommonMessage.debugMsg(g);
			CommonMessage.debugMsg(b);
			CommonMessage.debugMsg("------------");*/
		
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		   
		   
	}
	public static Color hex2Rgb(String colorStr) {
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
	            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
	}
	
}

