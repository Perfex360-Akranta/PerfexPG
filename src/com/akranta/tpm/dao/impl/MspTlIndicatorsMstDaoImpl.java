package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MspTlIndicatorsMstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.MspTlIndicatorsDtlSql;
import com.akranta.tpm.dao.sql.MspTlIndicatorsMstSql;
import com.akranta.tpm.dao.sql.MspTlMstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlIndicatorsDtl;
import com.akranta.tpm.model.MspTlIndicatorsMst;
import com.akranta.tpm.model.MspTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class MspTlIndicatorsMstDaoImpl implements MspTlIndicatorsMstDao {    


	private DBActionTemplate dbActionTemplate; 

	public MspTlIndicatorsMstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public MspTlIndicatorsDtl create(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl,MilestoneBean msBean) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		MspTlMstSql mspTlMstSql = new MspTlMstSql(); 
		MspTlIndicatorsMstSql mspTlIndicatorsMstSql = new MspTlIndicatorsMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		MspTlIndicatorsDtlSql mspTlIndicatorsDtlSql = new MspTlIndicatorsDtlSql();
		try{
			if(!UIUtils.isValidKeyId(mspTlIndicatorsMst.getMspiKeyid()))
			{
				mspTlIndicatorsMst.setMspiKeyid(dbActionTemplate.getSequenceNumber(MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
				sqls.add(MspTlIndicatorsMstSql.getInsertSql(mspTlIndicatorsMstSql.getMspiDbFields(), mspTlIndicatorsMst.getSaveArray())); // add insert sql for master table
			}
			else
			{
				sqls.add(MspTlIndicatorsMstSql.getUpdateSql(mspTlIndicatorsMstSql.getMspiDbFields(), mspTlIndicatorsMst.getSaveArray()));
			}
			CommonMessage.debugMsg("pARENT iD " +mspTlIndicatorsDtl.getMsidParentid());
			
			mspTlIndicatorsDtl.setMsidKeyid(dbActionTemplate.getSequenceNumber(MspTlIndicatorsDtlSql.TBL_MSP_TL_INDICATORS_DTL, 12, "MSI", "YYMM", "Y")); // set the sequnce number
			mspTlIndicatorsDtl.setMsidMspiKeyid(mspTlIndicatorsMst.getMspiKeyid());
		
			if(CommonFunctions.isValidKeyId(mspTlIndicatorsDtl.getMsidParentid()))
			{
				if(mspTlIndicatorsDtl.getMsidParentid().trim().equals(MspTlIndicatorsDtlSql.MasterPlanId))
				{
					mspTlIndicatorsDtl.setMsidParentid(mspTlIndicatorsDtl.getMsidKeyid());
				}
			}
			sqls.add(MspTlIndicatorsDtlSql.getInsertSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray())); // add insert sql for master table
			
			CommonMessage.debugMsg("msptlmst");
			if(UIUtils.isValidKeyId(msBean.getEnableDate()))
			{
				CommonMessage.debugMsg("msptlmstmayu");
				if(UIUtils.isValidKeyId(msBean.getMilestnFromDt()) &&UIUtils.isValidKeyId(msBean.getMilestnToDt()))
				{
					CommonMessage.debugMsg("msptlmstmayu123");
					
					MspTlMst mspTlMst = new MspTlMst();
					fillPlanValues( mspTlMst,mspTlIndicatorsMst,mspTlIndicatorsDtl,msBean);
					String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
					if(UIUtils.isValidKeyId(overlap))
					{
						if(Integer.parseInt(overlap)>0)
							throw new BusinessApplicationExceptions("overlap,");
					}
					mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
					CommonMessage.debugMsg("overlap");
					sqls.add(MspTlMstSql.getInsertSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
					CommonMessage.debugMsg("overlapoverlap");
					
				}
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return mspTlIndicatorsDtl;		
	}
	
	public MspTlIndicatorsDtl update(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl,MilestoneBean msBean)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		MspTlMstSql mspTlMstSql = new MspTlMstSql(); 
		MspTlIndicatorsMstSql mspTlIndicatorsMstSql = new MspTlIndicatorsMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		MspTlIndicatorsDtlSql mspTlIndicatorsDtlSql = new MspTlIndicatorsDtlSql();
		try {
			sqls.add(MspTlIndicatorsMstSql.getUpdateSql(mspTlIndicatorsMstSql.getMspiDbFields(), mspTlIndicatorsMst.getSaveArray()));
			mspTlIndicatorsDtl.setMsidMspiKeyid(mspTlIndicatorsMst.getMspiKeyid());
			sqls.add(MspTlIndicatorsDtlSql.getUpdateSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray()));
			if(UIUtils.isValidKeyId(msBean.getEnableDate()))
			{
				if(UIUtils.isValidKeyId(msBean.getMilestnFromDt()) &&UIUtils.isValidKeyId(msBean.getMilestnToDt()))
				{
					MspTlMst mspTlMst = new MspTlMst();
					fillPlanValues( mspTlMst,mspTlIndicatorsMst,mspTlIndicatorsDtl,msBean);
					String overlap = dbActionTemplate.getSingleValue(MspTlMstSql.checkOverlapSql(mspTlMst.getMpmsIndicatorid(), mspTlMst.getMpmsCellid(), mspTlMst.getMpmsPlanstartdate(), mspTlMst.getMpmsPlantilldate()));
					if(UIUtils.isValidKeyId(overlap))
					{
						if(Integer.parseInt(overlap)>0)
							throw new BusinessApplicationExceptions("overlap,");
					}
					mspTlMst.setMpmsKeyid(dbActionTemplate.getSequenceNumber(MspTlMstSql.TBL_MSP_TL_MST, 12, "MSM", "YYMM", "Y")); // set the sequnce number
					sqls.add(MspTlMstSql.getInsertSql(mspTlMstSql.getMpmsDbFields(), mspTlMst.getSaveArray())); // add insert sql for master table
					
				}
			}
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return mspTlIndicatorsDtl;
		
		
	}
	
	public MspTlIndicatorsDtl delete(MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		MspTlIndicatorsDtlSql mspTlIndicatorsDtlSql = new MspTlIndicatorsDtlSql();
		try {
			String sql = MspTlIndicatorsDtlSql.getChildCountSql(mspTlIndicatorsDtl.getMsidKeyid(),mspTlIndicatorsMst.getMspiCellid());
			String count = dbActionTemplate.getSingleValue(sql);
			if(UIUtils.isValidKeyId(count))
			{
				if(Integer.parseInt(count)>0)
				{
					sqls.add(MspTlIndicatorsDtlSql.getDeleteChildSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray(),mspTlIndicatorsMst.getMspiCellid()));
				}
			}
			sqls.add(MspTlIndicatorsDtlSql.getDeleteSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return mspTlIndicatorsDtl;
	}

	@Override
	public MspTlIndicatorsDtl assignParent(MspTlIndicatorsDtl mspTlIndicatorsDtl)
			throws ValidationExceptions, BusinessApplicationExceptions,
			Exception {
		List<String> sqls = new ArrayList<String>();
		MspTlIndicatorsDtlSql mspTlIndicatorsDtlSql = new MspTlIndicatorsDtlSql();
		try {
			
			sqls.add(MspTlIndicatorsDtlSql.getUpdateParentSql(mspTlIndicatorsDtlSql.getMsidDbFields(), mspTlIndicatorsDtl.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return mspTlIndicatorsDtl;
	
	}

	@Override
	public MspTlIndicatorsDtl select(String nodeId, String Flid)
			throws Exception {
		// TODO Auto-generated method stub
		MspTlIndicatorsDtl mspTlIndicatorsDtl = new MspTlIndicatorsDtl();
		MspTlIndicatorsMst mspTlIndicatorsMst = new MspTlIndicatorsMst();
		MilestoneBean msBean = new MilestoneBean();
	
		String sql = MspTlIndicatorsDtlSql.getSelectSql(nodeId,Flid);
		String mstSql = MspTlIndicatorsDtlSql.getSelectMstSql(nodeId,Flid);
		//String mspsql = MspTlMstSql.getSelectPlandate(nodeId,cellId);
        //CommonMessage.debugMsg("mspplandate1  "+mspsql);
		
	   CommonMessage.debugMsg("mspplandate  "+msBean.getMilestnFromDt());
	   CommonMessage.debugMsg("mspplandateto  "+msBean.getMilestnToDt());
		
		Object [] args =  new Object [] {};
		
		mspTlIndicatorsDtl.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		mspTlIndicatorsMst.setSaveArray(dbActionTemplate.getDataArr(mstSql,args));
		//msBean.setSaveArray(dbActionTemplate.getDataArr(mspsql,args));
		
		String getParentIndName = dbActionTemplate.getSingleValue(MspTlIndicatorsDtlSql.TBL_MSP_TL_INDICATORS_DTL, "MSID_NAME", "MSID_KEYID", mspTlIndicatorsDtl.getMsidParentid());
		if(CommonFunctions.isValidKeyId(getParentIndName))
			mspTlIndicatorsDtl.setMsidTempfield2(getParentIndName);

	         mspTlIndicatorsDtl.getMspTlIndicatorsMst().add(mspTlIndicatorsMst);
		CommonMessage.debugMsg("level : "+mspTlIndicatorsDtl.getMsidLevel());

	     mspTlIndicatorsDtl.getMspTlIndicatorsMst().add(mspTlIndicatorsMst);
		 CommonMessage.debugMsg("level : "+mspTlIndicatorsDtl.getMsidLevel());

		 CommonMessage.debugMsg("Parent : "+mspTlIndicatorsDtl.getMsidParentid());
		 CommonMessage.debugMsg("Name : "+mspTlIndicatorsDtl.getMsidName());
		 CommonMessage.debugMsg("Key Id : "+mspTlIndicatorsDtl.getMsidKeyid());
		CommonMessage.debugMsg("Factory : "+mspTlIndicatorsMst.getMspiFactoryid());
		 CommonMessage.debugMsg("Section : "+mspTlIndicatorsMst.getMspiSectionid());
		 CommonMessage.debugMsg("Cell : "+mspTlIndicatorsMst.getMspiCellid());
		 List<MspTlIndicatorsMst> mspMst= mspTlIndicatorsDtl.getMspTlIndicatorsMst();
		 if( mspMst != null && mspMst.size()> 0 )
		 {
	 
			 CommonMessage.debugMsg("Factory : "+mspMst.get(0).getMspiFactoryid());
			 CommonMessage.debugMsg("Section : "+mspMst.get(0).getMspiSectionid());
			 CommonMessage.debugMsg("Cell : "+mspMst.get(0).getMspiCellid());
		
	}
		 return mspTlIndicatorsDtl;
	}

	@Override
	public String getTitle(String cellId,String pillar,String txttitle) throws Exception
	{
		CommonMessage.debugMsg("txttitle");
		 String sql = MspTlIndicatorsMstSql.getTitleSql(cellId,pillar,txttitle);
		 CommonMessage.debugMsg(sql);
		 List<String[]> titleList =  dbActionTemplate.getDataList(sql);
		 String title = null;
		 if(titleList != null)
		 {
			 if(titleList.size()>0)
			 {
				 for(int i=0;i<titleList.size();i++)
				 {
					 String key = UIUtils.isValidKeyId(titleList.get(i)[1])?titleList.get(i)[1]:"";
					 title = UIUtils.isValidKeyId(titleList.get(i)[0])?titleList.get(i)[0]:title;
					 title = key + "::"+title;
					 CommonMessage.debugMsg(i+" : "+title);
				 }
			 }
			 
		 }
		 CommonMessage.debugMsg("TITLE : "+title);
		 return title;
	}

	@Override
	public List<MspTlIndicatorsDtl> getMasterPlanActivities(
			MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select ");
		sql.append(" MSID_KEYID,MSID_NAME,MSID_CODE,MSID_PARENTID,MSPI_FACTORYID,MSPI_SECTIONID,MSPI_CELLID, ");
		sql.append(" MSId_LEVEL,MSID_SORTNO,MSID_REMARKS,MSPi_PILLAR,MSPI_KEYID ");		
		sql.append(" from ");
		sql.append(MspTlIndicatorsDtlSql.TBL_MSP_TL_INDICATORS_DTL+","+MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST);
		sql.append(" where 1=1 and MSPI_KEYID=MSID_MSPI_KEYID(+) ");
		//if (CommonFunctions.isValidKeyId(mspTlIndicatorsMst.getMspiKeyid()))
		 sql.append("  AND MSPI_KEYID ='"+mspTlIndicatorsMst.getMspiKeyid()+"' ");
		
		if (CommonFunctions.isValidKeyId(mspTlIndicatorsDtl.getMsidKeyid()))
			sql.append(" and MSID_KEYID<>MSID_PARENTID ");
			
		
		  sql.append(" and MSID_ACTIVE = 'Y' ");
		//CommonMessage.debugMsg(mspTlIndicatorsDtl.getMsidParentid());

		if (CommonFunctions.isValidKeyId(mspTlIndicatorsDtl.getMsidParentid())){
			if(mspTlIndicatorsDtl.getMsidParentid().trim().equals(MspTlIndicatorsDtlSql.MasterPlanId))
			{
				sql.append(" AND MSID_PARENTID=MSID_KEYID");				
			}
			else
			{
				sql.append(" and MSID_KEYID<>MSID_PARENTID ");
				sql.append(" AND MSID_PARENTID='" + mspTlIndicatorsDtl.getMsidParentid() + "'");
			}
		}
		if (CommonFunctions.isValidKeyId(mspTlIndicatorsMst.getMspiPillar())){
			sql.append(" AND MSPI_PILLAR='" + mspTlIndicatorsMst.getMspiPillar() + "'");
		}
		/*if (CommonFunctions.isValidKeyId(mspTlIndicatorsMst.getMspiCellid())){
			sql.append(" AND MSPI_CELLID='" + mspTlIndicatorsMst.getMspiCellid() + "'");
		}*/
		
		sql.append(" ORDER BY MSID_SORTNO " );
		/*sql.append(" SELECT MSID_KEYID,MSID_NAME,MSID_CODE,MSID_PARENTID,MSPI_FACTORYID,MSPI_SECTIONID,MSPI_CELLID,MSId_LEVEL,MSID_SORTNO,MSID_REMARKS,");
		sql.append(" MSPi_PILLAR,MSPI_KEYID FROM MSP_TL_INDICATORS_DTL,MSP_TL_INDICATORS_MST");
		if(UIUtils.isValidKeyId(mspTlIndicatorsMst.getMspiKeyid()))
		sql.append(" and WHERE 1=1");
		sql.append(" AND MSPI_KEYID ='"+mspTlIndicatorsMst.getMspiKeyid()+"' ");
		sql.append(" AND MSPI_KEYID   =MSID_MSPI_KEYID(+) AND MSID_ACTIVE  = 'Y' AND MSID_PARENTID=MSID_KEYID ORDER BY MSID_SORTNO");*/ 
		
		
		CommonMessage.debugMsg(" sql " + sql.toString());
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		
		return fillActivities(resultList);		
	}

	@Override
	public List<String[]> getIndicatorsGrid(CommonFilter commonFilter,String pillar)
			throws Exception {
		String sql = MspTlIndicatorsMstSql.getIndicatorsSql(commonFilter,pillar,"Y");
		 CommonMessage.debugMsg(sql);
		 
		 List<String[]> dataList = dbActionTemplate.getDataList(sql);
		
		
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = dbActionTemplate.getSingleValue(MspTlIndicatorsDtlSql.getIndicatorsCountNewSql(pillar));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		 return dataList;
	}

	@Override
	public List<String[]> getPlanActual(CommonFilter commonFilter, String pillar,String masterkeyid)
			throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("pillardaoimpl "+pillar);
			
			//String	condParms1 +="masterkeyid="+masterkeyid;
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter)+"PILLARID="+pillar+";MASTERKEYID="+masterkeyid;
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			//paramValues.add(condParms1);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;

			dataList =  dbActionTemplate.processFunctionCalls("MSP_PC_MASTERPLAN.MSP_FN_MASTERPLAN", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
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
	public List<String[]> getActivitiesForSubcategory(
			MspTlIndicatorsDtl mspTlIndicatorsdtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception {
		 CommonMessage.debugMsg("FLid  "+mspTlIndicatorsMst.getMspiFlid());
		 String sql = MspTlIndicatorsDtlSql.getActivitiesForSubcategorySql(mspTlIndicatorsdtl,mspTlIndicatorsMst);
		 CommonMessage.debugMsg("Flid "+mspTlIndicatorsMst.getMspiFlid());
		
		 CommonMessage.debugMsg(sql);
		 return dbActionTemplate.getDataList(sql);
	}

	@Override
	public List<String[]> getSearchIndicator(String indicatorId, String indName)
			throws Exception {
		try
		{
			String sql = MspTlIndicatorsDtlSql.getSearchIndicatorSql(indName,indicatorId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook getAllIndicatorsExcel(JSONObject jsonObject,
			CommonFilter commonFilter, String format,String pillar) throws Exception {
		 ResultSet rs = null;
		 ExcelUtils excelUtils = new ExcelUtils(jsonObject);
		 rs =   getAllIndicatorsResultSet(commonFilter,pillar);
		 return excelUtils.writeToExcel(rs,format, 0,0,0 );
	}

	private ResultSet getAllIndicatorsResultSet(CommonFilter commonFilter,String pillar) {
		String sql = MspTlIndicatorsMstSql.getIndicatorsSql(commonFilter,pillar,"N");
		CommonMessage.debugMsg("Excel Sql : "+sql);
		try {
			return  dbActionTemplate.getData(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	private List<MspTlIndicatorsDtl> fillActivities(List<String []> resultList) throws SQLException
	{
	
		   List<MspTlIndicatorsDtl> actList = new ArrayList<MspTlIndicatorsDtl>();
		   for( String [] row : resultList )
		   {
			   MspTlIndicatorsDtl activities = new MspTlIndicatorsDtl();
			   activities.setMsidKeyid(row[0]);
			   activities.setMsidName(row[1]);
			   activities.setMsidParentid(row[3]);
			   activities.setMsidTempfield(row[6]);
			   activities.setMsidLevel(row[7]);
			   activities.setMsidSortno(row[8]);
			   activities.setMsidRemarks(row[9]);
			   activities.setMsidTempfield2(row[10]);
			   activities.setMsidTempfield3(row[11]);
			   actList.add(activities);			   
		   }
  		  return actList;
	 }
	
	private MspTlMst  fillPlanValues(MspTlMst newMspTlMst,MspTlIndicatorsMst mspTlIndicatorsMst,MspTlIndicatorsDtl mspTlIndicatorsDtl,MilestoneBean msBean) throws ParseException
	{
		newMspTlMst.setMpmsActive("Y");
		newMspTlMst.setMpmsCreatedon(mspTlIndicatorsDtl.getMsidCreatedon());
		newMspTlMst.setMpmsModifiedon(mspTlIndicatorsDtl.getMsidModifiedon());
		newMspTlMst.setMpmsCreatedby(mspTlIndicatorsDtl.getMsidCreatedby());
		newMspTlMst.setMpmsPlanstartdate(msBean.getMilestnFromDt());
		newMspTlMst.setMpmsPlantilldate(msBean.getMilestnToDt());
		newMspTlMst.setMpmsActualstartdate(Constants.passNullDate);
		newMspTlMst.setMpmsActualtilldate(Constants.futureNullDate);
		newMspTlMst.setMpmsStatus("P");
		int diff = CommonFunctions.getDateDiff(newMspTlMst.getMpmsPlanstartdate()+" 00:00", newMspTlMst.getMpmsPlantilldate()+" 00:00");
		newMspTlMst.setMpmsPlanduration(Integer.toString(diff));
		newMspTlMst.setMpmsActualduration("0");
		if(!UIUtils.isValidKeyId(mspTlIndicatorsDtl.getMsidKeyid()))
			newMspTlMst.setMpmsIndicatorid("{}");
		else
			newMspTlMst.setMpmsIndicatorid(mspTlIndicatorsDtl.getMsidKeyid());
		if(!UIUtils.isValidKeyId(mspTlIndicatorsMst.getMspiFactoryid()))
			newMspTlMst.setMpmsFactoryid("{}");
		else
			newMspTlMst.setMpmsFactoryid(mspTlIndicatorsMst.getMspiFactoryid());
		if(!UIUtils.isValidKeyId(mspTlIndicatorsMst.getMspiSectionid()))
			newMspTlMst.setMpmsSectionid("{}");
		else
			newMspTlMst.setMpmsSectionid(mspTlIndicatorsMst.getMspiSectionid());
		if(!UIUtils.isValidKeyId(mspTlIndicatorsMst.getMspiCellid()))
			newMspTlMst.setMpmsCellid("{}");
		else
			newMspTlMst.setMpmsCellid(mspTlIndicatorsMst.getMspiCellid());
		
		newMspTlMst.setMpmsElementid(mspTlIndicatorsMst.getMspiElementid());    /////TTTTTTTT
		newMspTlMst.setMpmsFlid(mspTlIndicatorsMst.getMspiFlid());
		
		newMspTlMst.setMpmsResponsibility(mspTlIndicatorsDtl.getMsidCreatedby());	
		newMspTlMst.setMpmsCompletedby("{}");		
		newMspTlMst.setMpmsPlanstartweek("0");	
		newMspTlMst.setMpmsPlantillweek("0");		
		newMspTlMst.setMpmsActualstartweek("0");		
		newMspTlMst.setMpmsActualtillweek("0");
		newMspTlMst.setMpmsTempfield1("-");
		newMspTlMst.setMpmsTempfield2("-");
		newMspTlMst.setMpmsTempfield3("-");
		newMspTlMst.setMpmsTempfield4("-");
		newMspTlMst.setMpmsTempfield5("-");
		newMspTlMst.setMpmsTempfield6("-");
		newMspTlMst.setMpmsTempfield7("-");
		newMspTlMst.setMpmsTempfield8("-");
		newMspTlMst.setMpmsTempfield9("-");
		newMspTlMst.setMpmsTempfield10("-");
		
		return newMspTlMst;
	}

	@Override
	public MspTlIndicatorsMst create(MspTlIndicatorsMst newMspTlIndicatorsMst,
			MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		 
		MspTlIndicatorsMstSql mspTlIndicatorsMstSql = new MspTlIndicatorsMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try{
			if(!UIUtils.isValidKeyId(newMspTlIndicatorsMst.getMspiKeyid()))
			{
				newMspTlIndicatorsMst.setMspiKeyid(dbActionTemplate.getSequenceNumber(MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST, 12, "MSM", "YYMM", "Y"));  // set the sequnce number 
				sqls.add(MspTlIndicatorsMstSql.getInsertSql(mspTlIndicatorsMstSql.getMspiDbFields(), newMspTlIndicatorsMst.getSaveArray())); // add insert sql for master table
			}
			else
			{
				sqls.add(MspTlIndicatorsMstSql.getUpdateSql(mspTlIndicatorsMstSql.getMspiDbFields(), newMspTlIndicatorsMst.getSaveArray()));
			}
		    		
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newMspTlIndicatorsMst;
	}

	@Override
	public MspTlIndicatorsMst update(MspTlIndicatorsMst newMspTlIndicatorsMst,
			MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		 
		MspTlIndicatorsMstSql mspTlIndicatorsMstSql = new MspTlIndicatorsMstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try{
			
			//	newMspTlIndicatorsMst.setMspiKeyid(dbActionTemplate.getSequenceNumber(MspTlIndicatorsMstSql.TBL_MSP_TL_INDICATORS_MST, 12, "MSM", "YYMM", "Y"));  // set the sequnce number 
				
                sqls.add(MspTlIndicatorsMstSql.getUpdateSql(mspTlIndicatorsMstSql.getMspiDbFields(), newMspTlIndicatorsMst.getSaveArray()));
		
		    		
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newMspTlIndicatorsMst;
	}

	@Override
	public MspTlIndicatorsMst selectMasKeyid(String masterkeyid) throws Exception {
		
         MspTlIndicatorsMst mspTlIndicatorsMst = new MspTlIndicatorsMst();
		String masterSql = MspTlIndicatorsMstSql.getSelectmasterSql();
		CommonMessage.debugMsg(masterkeyid+" sql "+masterSql);
		CommonMessage.debugMsg("masterkeyid : "+masterkeyid);
		Object [] args =  new Object [] {masterkeyid};
		 mspTlIndicatorsMst.setSaveArray(dbActionTemplate.getDataArr(masterSql,args));
		CommonMessage.debugMsg(mspTlIndicatorsMst.getMspiKeyid()+"  masterkeyid : "+mspTlIndicatorsMst.getMspiFlid()+" -- "+mspTlIndicatorsMst.getMspiElementid());
		return mspTlIndicatorsMst;
	}

	@Override
	public List<String[]> getplandate(String nodeId) throws Exception {
		
		StringBuffer sql= new StringBuffer();
		sql.append(" select   To_char(MPMS_PLANSTARTDATE),To_char(MPMS_PLANTILLDATE)  from MSP_TL_MST where   MPMS_INDICATORID = '"+nodeId+"' ");
	     List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
	     CommonMessage.debugMsg("sql.toString()   "+sql.toString());
		CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
		return gridData;
		
		
	}
	}
