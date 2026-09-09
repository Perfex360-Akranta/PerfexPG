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
import com.akranta.tpm.dao.MspTlIndicatorsDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.MspTlHistorySql;
import com.akranta.tpm.dao.sql.MspTlIndicatorsSql;
import com.akranta.tpm.dao.sql.MspTlMstSql;
import com.akranta.tpm.dao.sql.PcsTlProductionplanSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MspTlIndicators;
import com.akranta.tpm.model.MspTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class MspTlIndicatorsDaoImpl implements MspTlIndicatorsDao {


	private DBActionTemplate dbActionTemplate; 

	public MspTlIndicatorsDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public MspTlIndicators create(MspTlIndicators mspTlIndicators,MilestoneBean msBean) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		MspTlMstSql mspTlMstSql = new MspTlMstSql(); 
		MspTlIndicatorsSql mspTlIndicatorsSql = new MspTlIndicatorsSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			mspTlIndicators.setMspiKeyid(dbActionTemplate.getSequenceNumber(MspTlIndicatorsSql.TBL_MSP_TL_INDICATORS, 12, "MSI", "YYMM", "Y")); // set the sequnce number
			if(CommonFunctions.isValidKeyId(mspTlIndicators.getMspiParentid()))
			{
				if(mspTlIndicators.getMspiParentid().trim().equals("MP001"))
				{
					mspTlIndicators.setMspiParentid(mspTlIndicators.getMspiKeyid());
				}
			}
			sqls.add(MspTlIndicatorsSql.getInsertSql(mspTlIndicatorsSql.getMspiDbFields(), mspTlIndicators.getSaveArray())); // add insert sql for master table
			if(UIUtils.isValidKeyId(msBean.getEnableDate()))
			{
				if(UIUtils.isValidKeyId(msBean.getMilestnFromDt()) &&UIUtils.isValidKeyId(msBean.getMilestnToDt()))
				{
					MspTlMst mspTlMst = new MspTlMst();
					fillPlanValues( mspTlMst,mspTlIndicators,msBean);
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
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return mspTlIndicators;
	}
	
	public MspTlIndicators update(MspTlIndicators mspTlIndicators,MilestoneBean msBean)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		MspTlMstSql mspTlMstSql = new MspTlMstSql(); 
		MspTlIndicatorsSql mspTlIndicatorsSql = new MspTlIndicatorsSql();
		try {

			sqls.add(MspTlIndicatorsSql.getUpdateSql(mspTlIndicatorsSql.getMspiDbFields(), mspTlIndicators.getSaveArray()));
			if(UIUtils.isValidKeyId(msBean.getEnableDate()))
			{
				if(UIUtils.isValidKeyId(msBean.getMilestnFromDt()) &&UIUtils.isValidKeyId(msBean.getMilestnToDt()))
				{
					MspTlMst mspTlMst = new MspTlMst();
					fillPlanValues( mspTlMst,mspTlIndicators,msBean);
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
		
		return mspTlIndicators;
	}
	public MspTlIndicators assignParent(MspTlIndicators mspTlIndicators)throws ValidationExceptions,BusinessApplicationExceptions, Exception
	{
		List<String> sqls = new ArrayList<String>();
		MspTlIndicatorsSql mspTlIndicatorsSql = new MspTlIndicatorsSql();
		try {
			
			sqls.add(MspTlIndicatorsSql.getUpdateParentSql(mspTlIndicatorsSql.getMspiDbFields(), mspTlIndicators.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return mspTlIndicators;
	}
	public MspTlIndicators delete(MspTlIndicators mspTlIndicators)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		MspTlIndicatorsSql mspTlIndicatorsSql = new MspTlIndicatorsSql();
		try {
			String sql = MspTlIndicatorsSql.getChildCountSql(mspTlIndicators.getMspiKeyid(),mspTlIndicators.getMspiCellid());
			String count = dbActionTemplate.getSingleValue(sql);
			if(UIUtils.isValidKeyId(count))
			{
				if(Integer.parseInt(count)>0)
				{
					sqls.add(MspTlIndicatorsSql.getDeleteChildSql(mspTlIndicatorsSql.getMspiDbFields(), mspTlIndicators.getSaveArray()));
				}
			}
			sqls.add(MspTlIndicatorsSql.getDeleteSql(mspTlIndicatorsSql.getMspiDbFields(), mspTlIndicators.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return mspTlIndicators;
	}
	public MspTlIndicators select(String nodeId,String cellId) 	throws Exception {
		MspTlIndicators mspTlIndicators = new MspTlIndicators();
		String sql = MspTlIndicatorsSql.getSelectSql(nodeId,cellId);	
		CommonMessage.debugMsg(" nodecell "+mspTlIndicators.getMspiCellid());
		Object [] args =  new Object [] {};
		mspTlIndicators.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		String getParentIndName = dbActionTemplate.getSingleValue(MspTlIndicatorsSql.TBL_MSP_TL_INDICATORS, "MSPI_NAME", "MSPI_KEYID", mspTlIndicators.getMspiParentid());
		if(CommonFunctions.isValidKeyId(getParentIndName))
			mspTlIndicators.setMspiTempfield2(getParentIndName);
		return mspTlIndicators;
	}
	public List<String[]> getActivitiesForSubcategory(MspTlIndicators mspTlIndicators) throws Exception
	{
		 String sql = MspTlIndicatorsSql.getActivitiesForSubcategorySql(mspTlIndicators);
		 CommonMessage.debugMsg(sql);
		 return dbActionTemplate.getDataList(sql);
	}
	public List<String[]> getIndicatorsGrid(CommonFilter commonFilter) throws Exception
	{
		 String sql = MspTlIndicatorsSql.getIndicatorsSql(commonFilter,"Y");
		 CommonMessage.debugMsg(sql);
		 
		 List<String[]> dataList = dbActionTemplate.getDataList(sql);
		
		
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = dbActionTemplate.getSingleValue(MspTlIndicatorsSql.getIndicatorsCountSql());
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		 return dataList;
	}
	public String getTitle(String cellId) throws Exception
	{
		 String sql = MspTlIndicatorsSql.getTitleSql(cellId);
		 CommonMessage.debugMsg(sql);
		 List<String[]> titleList =  dbActionTemplate.getDataList(sql);
		 String title = null;
		 if(titleList != null)
		 {
			 if(titleList.size()>0)
			 {
				 for(int i=0;i<titleList.size();i++)
				 {
					 title = UIUtils.isValidKeyId(titleList.get(i)[0])?titleList.get(i)[0]:title;
					 CommonMessage.debugMsg(i+" : "+title);
				 }
			 }
			 
		 }
		 CommonMessage.debugMsg("TITLE : "+title);
		 return title;
	}
	public List<String[]> getPlanActual(CommonFilter commonFilter,String pillar)throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter)+"PILLARID="+pillar+";";;
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
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
	public List<MspTlIndicators> getMasterPlanActivities(MspTlIndicators mspTlIndicators) throws Exception
	{
		StringBuffer sql= new StringBuffer();
		sql.append(" select ");
		sql.append(" MSPI_KEYID,MSPI_NAME,MSPI_CODE,MSPI_PARENTID,MSPI_FACTORYID,MSPI_SECTIONID,MSPI_CELLID, ");
		sql.append(" MSPI_LEVEL,MSPI_SORTNO,MSPI_REMARKS,MSPI_PILLAR ");		
		sql.append(" from ");
		sql.append(MspTlIndicatorsSql.TBL_MSP_TL_INDICATORS);
		sql.append(" where 1=1 ");
		if (CommonFunctions.isValidKeyId(mspTlIndicators.getMspiKeyid()))
			sql.append(" and MSPI_KEYID<>MSPI_PARENTID ");
			
		
		sql.append(" and MSPI_ACTIVE = 'Y' ");
		CommonMessage.debugMsg(mspTlIndicators.getMspiParentid());

		if (CommonFunctions.isValidKeyId(mspTlIndicators.getMspiParentid())){
			if(mspTlIndicators.getMspiParentid().trim().equals("MP001"))
			{
				sql.append(" AND MSPI_PARENTID=MSPI_KEYID");				
			}
			else
			{
				sql.append(" and MSPI_KEYID<>MSPI_PARENTID ");
				sql.append(" AND MSPI_PARENTID='" + mspTlIndicators.getMspiParentid() + "'");
			}
		}
		if (CommonFunctions.isValidKeyId(mspTlIndicators.getMspiPillar())){
			sql.append(" AND MSPI_PILLAR='" + mspTlIndicators.getMspiPillar() + "'");
		}
	/*	if (CommonFunctions.isValidKeyId(mspTlIndicators.getMspiCellid())){
			sql.append(" AND MSPI_CELLID='" + mspTlIndicators.getMspiCellid() + "'");
		}*/
		
		sql.append(" ORDER BY MSPI_SORTNO " );	
		CommonMessage.debugMsg(" sql " + sql.toString());
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillActivities(resultList);			
	}
	public  List<String[]> getSearchIndicator(String indicatorId,String indName) throws Exception
	{
		try
		{
			/*String indId = dbActionTemplate.getSingleValue(MspTlIndicatorsSql.TBL_MSP_TL_INDICATORS, "MSPI_PARENTID", "MSPI_KEYID", indicatorId);
			if(UIUtils.isValidKeyId(indId))
				indicatorId = indId;*/
			String sql = MspTlIndicatorsSql.getSearchIndicatorSql(indName,indicatorId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
		
	private List<MspTlIndicators> fillActivities(List<String []> resultList) throws SQLException
	{
	
		   List<MspTlIndicators> actList = new ArrayList<MspTlIndicators>();
		   for( String [] row : resultList )
		   {
			   MspTlIndicators activities = new MspTlIndicators();
			   activities.setMspiKeyid(row[0]);
			   activities.setMspiName(row[1]);
			   activities.setMspiParentid(row[3]);
			   activities.setMspiCellid(row[6]);
			   activities.setMspiLevel(row[7]);
			   activities.setMspiSortno(row[8]);
			   activities.setMspiRemarks(row[9]);
			   activities.setMspiPillar(row[10]);
			   actList.add(activities);			   
		   }
  		  return actList;
	 }
	private MspTlMst  fillPlanValues(MspTlMst newMspTlMst,MspTlIndicators mspTlIndicators,MilestoneBean msBean) throws ParseException
	{
		newMspTlMst.setMpmsActive("Y");
		newMspTlMst.setMpmsCreatedon(mspTlIndicators.getMspiCreatedon());
		newMspTlMst.setMpmsModifiedon(mspTlIndicators.getMspiModifiedon());
		newMspTlMst.setMpmsCreatedby(mspTlIndicators.getMspiCreatedby());
		newMspTlMst.setMpmsPlanstartdate(msBean.getMilestnFromDt());
		newMspTlMst.setMpmsPlantilldate(msBean.getMilestnToDt());
		newMspTlMst.setMpmsActualstartdate(Constants.passNullDate);
		newMspTlMst.setMpmsActualtilldate(Constants.futureNullDate);
		newMspTlMst.setMpmsStatus("P");
		int diff = CommonFunctions.getDateDiff(newMspTlMst.getMpmsPlanstartdate()+" 00:00", newMspTlMst.getMpmsPlantilldate()+" 00:00");
		newMspTlMst.setMpmsPlanduration(Integer.toString(diff));
		newMspTlMst.setMpmsActualduration("0");
		if(!UIUtils.isValidKeyId(mspTlIndicators.getMspiKeyid()))
			newMspTlMst.setMpmsIndicatorid("{}");
		else
			newMspTlMst.setMpmsIndicatorid(mspTlIndicators.getMspiKeyid());
		if(!UIUtils.isValidKeyId(mspTlIndicators.getMspiFactoryid()))
			newMspTlMst.setMpmsFactoryid("{}");
		else
			newMspTlMst.setMpmsFactoryid(mspTlIndicators.getMspiFactoryid());
		if(!UIUtils.isValidKeyId(mspTlIndicators.getMspiSectionid()))
			newMspTlMst.setMpmsSectionid("{}");
		else
			newMspTlMst.setMpmsSectionid(mspTlIndicators.getMspiSectionid());
		if(!UIUtils.isValidKeyId(mspTlIndicators.getMspiCellid()))
			newMspTlMst.setMpmsCellid("{}");
		else
			newMspTlMst.setMpmsCellid(mspTlIndicators.getMspiCellid());
		
		newMspTlMst.setMpmsResponsibility(mspTlIndicators.getMspiCreatedby());	
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
	
	public Workbook getAllIndicatorsExcel(JSONObject tblJSONObj,CommonFilter commonFilter,String format) throws Exception {
		 ResultSet rs = null;
		 ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		 rs =   getAllIndicatorsResultSet(commonFilter);
		 return excelUtils.writeToExcel(rs,format, 0,0,0 );
	}

	private ResultSet getAllIndicatorsResultSet(CommonFilter commonFilter) {
		String sql = MspTlIndicatorsSql.getIndicatorsSql(commonFilter,"N");
		CommonMessage.debugMsg("Excel Sql : "+sql);
		try {
			return  dbActionTemplate.getData(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
}

