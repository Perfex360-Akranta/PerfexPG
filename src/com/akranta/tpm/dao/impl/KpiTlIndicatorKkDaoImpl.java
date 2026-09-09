package com.akranta.tpm.dao.impl;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KpiTlIndicatorKkDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorDeptLinkSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorKkSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.KpiTlActualandIndicatorServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import lotus.domino.corba.UPD_APPEND_ITEM;

/* dao implementation */
public class KpiTlIndicatorKkDaoImpl implements KpiTlIndicatorKkDao {
	
	private KpiTlActualandIndicatorServiceApi kpiTlActualandIndicatorServiceApi;
	FunctionCallApi fnCallApi;
	
	private DBActionTemplate dbActionTemplate; 
	KpiTlIndicatorKkSql newKpiTlIndicatorKkSql;
	KpiTlIndicatorSql newKpiTlIndicatorSql;
	KpiTlIndicatorDeptLinkSql kpiTlIndicatorDeptLinkSql;
	KpiTlIndicatorDeptLink KpiTlIndicatorDeptLink;
	public KpiTlIndicatorKkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		newKpiTlIndicatorKkSql = new KpiTlIndicatorKkSql();
		newKpiTlIndicatorSql = new KpiTlIndicatorSql();
		kpiTlIndicatorDeptLinkSql=new KpiTlIndicatorDeptLinkSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
   public void KpiTlIndicatorKkDaoImplJwt(String jwtToken) {
		
		try{
			kpiTlActualandIndicatorServiceApi = new KpiTlActualandIndicatorServiceApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<KpiTlIndicatorKk> getKpiTlIndicatorKkValues(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception
	{				
		StringBuffer sql= new StringBuffer();
		sql.append(" select ");
		sql.append(" KINK_KEYID,KINK_INDICATORNAME,KINK_INDICATORCODE,KINK_DESCRIPTION,KINK_PARENTID,KINK_LEVELNO,KINK_SORTNO, ");
		sql.append(" KINK_ISCHILD,KINK_INPUTTYPE,KINK_INPUTENTRY,KINK_IDENTIFIER,KINK_MANUALCALCTYPE,KINK_UOMID, ");
		sql.append(" KINK_FREQUENCY,KINK_EXCELNAME,KINK_DEPT_KEYID,KINK_COSTAREA,KINK_TARGETNEED,KINK_PILLARID,childpath ");
		sql.append(" from ");
		sql.append(" KPI_TL_INDICATOR,kpi_vw_keyperInchildpath ");
		sql.append(" where 1=1 ");
		sql.append(" and KINK_KEYID=KINK_PARENTID ");
		sql.append(" and keyid = KINK_KEYID ");
		sql.append(" and KINK_ACTIVE = 'Y' ");
		
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkParentid())){
			sql.append(" AND KINK_PARENTID='" + kpiTlIndicatorKk.getKinkParentid() + "'");
		}
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkPillarid())){
			sql.append(" AND KINK_PILLARID='" + kpiTlIndicatorKk.getKinkPillarid() + "'");
		}
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkKeyid())){
			sql.append(" AND KINK_KEYID='" + kpiTlIndicatorKk.getKinkKeyid() + "'");
		}
		
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkLocation())){
			sql.append(" AND KINK_LOCATION = '"+kpiTlIndicatorKk.getKinkLocation()+"' ");
		}
		
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkTempfield3())){
			sql.append(" AND KINK_TYPE = '"+kpiTlIndicatorKk.getKinkTempfield3()+"' ");
		}
		sql.append(" ORDER BY KINK_KEYID " );	
		CommonMessage.debugMsg(" sql getKpiTlIndicatorKkValues " + sql.toString());
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillIndicatorList(resultList);		
	}

	public List<KpiTlIndicatorKk> getAllkeyInd(KpiTlIndicatorKk kpiTlIndicatorKk) throws Exception
	{		
		StringBuffer sql= new StringBuffer();
		sql.append(" select ");
		sql.append(" KINK_KEYID,KINK_INDICATORNAME,KINK_INDICATORCODE,KINK_DESCRIPTION,KINK_PARENTID,KINK_LEVELNO,KINK_SORTNO, ");
		sql.append(" KINK_ISCHILD,KINK_INPUTTYPE,KINK_INPUTENTRY,KINK_IDENTIFIER,KINK_MANUALCALCTYPE,KINK_UOMID, ");
		sql.append(" KINK_FREQUENCY,KINK_EXCELNAME,KINK_DEPT_KEYID,KINK_COSTAREA,KINK_TARGETNEED,KINK_PILLARID,childpath ");
		sql.append(" from ");
		sql.append(" KPI_TL_INDICATOR,kpi_vw_keyperInchildpath  ");
		sql.append(" where 1=1 ");
		sql.append(" and KINK_KEYID<>KINK_PARENTID ");
		sql.append(" and keyid = KINK_KEYID ");
		sql.append(" and KINK_ACTIVE = 'Y' ");
		
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkParentid())){
			sql.append(" AND KINK_PARENTID='" + kpiTlIndicatorKk.getKinkParentid() + "'");
		}
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkPillarid())){
			sql.append(" AND KINK_PILLARID='" + kpiTlIndicatorKk.getKinkPillarid() + "'");
		}
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkKeyid())){
			sql.append(" AND KINK_KEYID='" + kpiTlIndicatorKk.getKinkKeyid() + "'");
		}
		
		if (CommonFunctions.isValidKeyId(kpiTlIndicatorKk.getKinkLocation())){
			sql.append(" AND KINK_LOCATION = '"+kpiTlIndicatorKk.getKinkLocation()+"' ");
		}
			
		sql.append(" ORDER BY KINK_KEYID " );
		CommonMessage.debugMsg(" sql getAllkeyInd " + sql.toString());
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillIndicatorList(resultList);			
	}
	
	public KpiTlIndicatorKk create(KpiTlIndicatorKk kpiTlIndicatorKk) 	throws ValidationExceptions,BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>(); 
		CommonMessage.debugMsg("create daoimpl");
		try{
			kpiTlIndicatorKk.setKinkKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_KPI_TL_INDICATOR, 10, "KIN", null, null));
			CommonMessage.debugMsg("create daoimpl"+kpiTlIndicatorKk.getKinkKeyid());
			if (!UIUtils.isValidKeyId(kpiTlIndicatorKk.getKinkParentid())){
				kpiTlIndicatorKk.setKinkParentid(kpiTlIndicatorKk.getKinkKeyid());	
			}
			else{			
				sqls.add("update " + TableNames.TBL_KPI_TL_INDICATOR + " set KINK_ISCHILD='N' where KINK_KEYID='" + kpiTlIndicatorKk.getKinkParentid() +  "' " );
				if (kpiTlIndicatorKk.getKinkTargetneed().equals("Y")){
					List<String[]>  searchList = getSearchNode("",kpiTlIndicatorKk.getKinkParentid());
					
					if( searchList.size()>0 ){
						String parentId=searchList.get(0)[0];
						//CommonMessage.debugMsg("parentId:"+parentId);
						parentId=parentId.substring(1, parentId.length());
						//CommonMessage.debugMsg("parentId:"+parentId);
						String[] searchNode = parentId.split("/");
						//CommonMessage.debugMsg("searchNode.length:"+searchNode.length);
						String Sql="";
						for(int i=0;i<searchNode.length;i++){
							String keyid = searchNode[i];
							//CommonMessage.debugMsg("keyid:"+keyid);
							Sql="update " + TableNames.TBL_KPI_TL_INDICATOR + " set KINK_TARGETNEED='Y' where KINK_KEYID='" + keyid +  "'";
							//CommonMessage.debugMsg("update Sql:"+Sql);
							sqls.add(Sql);
						}
					}					
				}								
			}	
			
			sqls.add(newKpiTlIndicatorKkSql.getInsertSql(newKpiTlIndicatorKkSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray()));
			dbActionTemplate.executeStatements(sqls); 			
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("validate e.getMessage():"+e.getMessage());
			
			throw e;// new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw e;//new BusinessApplicationExceptions(e.getMessage());
		}
		return kpiTlIndicatorKk;
	}
	
	public KpiTlIndicatorKk update(KpiTlIndicatorKk kpiTlIndicatorKk)	throws ValidationExceptions,BusinessApplicationExceptions, Exception { 
		
		List<String> sqls = new ArrayList<String>();
		try {
			
			if (kpiTlIndicatorKk.getKinkTargetneed().equals("Y")){
				List<String[]>  searchList = getSearchNode("",kpiTlIndicatorKk.getKinkParentid());
				
				if( searchList.size()>0 ){
					String parentId=searchList.get(0)[0];
					//CommonMessage.debugMsg("parentId:"+parentId);
					parentId=parentId.substring(1, parentId.length());
					//CommonMessage.debugMsg("parentId:"+parentId);
					String[] searchNode = parentId.split("/");
					//CommonMessage.debugMsg("searchNode.length:"+searchNode.length);
					String Sql="";
					for(int i=0;i<searchNode.length;i++){
						String keyid = searchNode[i];
						//CommonMessage.debugMsg("keyid:"+keyid);
						Sql="update " + TableNames.TBL_KPI_TL_INDICATOR + " set KINK_TARGETNEED='Y' where KINK_KEYID='" + keyid +  "'";
						//CommonMessage.debugMsg("update Sql:"+Sql);
						sqls.add(Sql);
					}
				}					
			}		
			
			sqls.add(newKpiTlIndicatorKkSql.getUpdateSql(newKpiTlIndicatorKkSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);			
		} 
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		
		return kpiTlIndicatorKk;
	}
	
	public KpiTlIndicatorKk delete(KpiTlIndicatorKk kpiTlIndicatorKk)
			throws BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>();
		try {			
			sqls.add(newKpiTlIndicatorKkSql.getDeleteSql(newKpiTlIndicatorKkSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			return kpiTlIndicatorKk;
		}
		catch( BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions("FK_KIDL_INDICATORID");
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		
	}
	
	@Override
	public KpiTlIndicator select(KpiTlIndicator kpiTlIndicator) throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		String sql =null;
		sql= newKpiTlIndicatorSql.getSelectSql(newKpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicator.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);		
		Object [] args =  new Object [] {};
		kpiTlIndicator.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+kpiTlIndicator.getKinkKeyid());
		return kpiTlIndicator;
	}
	
	@Override
	public List<KpiTlIndicatorKk> selectList(KpiTlIndicatorKk KpiTlIndicatorKk)throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		String sql =null;
		sql= newKpiTlIndicatorKkSql.getSelectSql(newKpiTlIndicatorKkSql.getKinkDbFields(), KpiTlIndicatorKk.getSaveArray());			
		CommonMessage.debugMsg("DAO SQL : "+sql);	
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillIndicatorList(resultList);		
	}
	
	@Override
	public  List<String[]> getSearchNode(String searchNode,String originalId) throws Exception
	{
		try
		{		
			String sql = null;
			sql=newKpiTlIndicatorKkSql.getSearchNodeSql(searchNode,originalId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}	
	
	public int getkeyIndLevel(KpiTlIndicatorKk kpiTlIndicatorKk)throws Exception
	{		
		String sql = null;
		int menuLevel = 0;	
		sql=newKpiTlIndicatorKkSql.getkeyIndLevelSql(newKpiTlIndicatorKkSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray());
		CommonMessage.debugMsg(" sql " + sql);
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		 for( String [] row : resultList )
	    {			  
			 menuLevel= Integer.parseInt( row[0]);			   		   
	    }
		return menuLevel;
	}
	
	private List<String[]> getTopicParentSql(String keyId)throws Exception
	{		
		String sql = null;
		sql=newKpiTlIndicatorKkSql.getSearchTopicLevelSql(keyId);
		CommonMessage.debugMsg(" sql " + sql);
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		return resultList;		
	}
	
	public int getConfigkeyIndLevel()throws Exception
	{		
		String sql = null;
		int menuLevel = 0;
		sql=newKpiTlIndicatorKkSql.getConfigkeyIndLevel();
		CommonMessage.debugMsg(" sql " + sql);
		
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		 for( String [] row : resultList )
	    {			  
			 menuLevel=Integer.parseInt( row[0]);			   		   
	    }
		return menuLevel;
	}
	
	public String getPillarKeyId(String pillarCode)throws Exception{
		String sql = newKpiTlIndicatorKkSql.getPillarKeyIdSql(pillarCode);				
		CommonMessage.debugMsg(" sql: " + sql);
		
		String pillarKeyId = dbActionTemplate.getSingleValue(sql);	
		CommonMessage.debugMsg(" pillarKeyId: " + pillarKeyId);
		return pillarKeyId;
	}
	
	public String getSortNo(KpiTlIndicatorKk newKpiTlIndicatorKk)throws Exception{
		String sql = newKpiTlIndicatorKkSql.getSortNo(newKpiTlIndicatorKkSql.getKinkDbFields(), newKpiTlIndicatorKk.getSaveArray());				
		CommonMessage.debugMsg(" sql: " + sql);
		
		String sortNo = dbActionTemplate.getSingleValue(sql);	
		CommonMessage.debugMsg(" SortNo: " + sortNo);
		return sortNo;
	}
	
	private List<KpiTlIndicatorKk> fillIndicatorList(List<String []> resultList) throws SQLException
	{
	   List<KpiTlIndicatorKk> menus = new ArrayList<KpiTlIndicatorKk>();
	   for( String [] row : resultList )
	   {		
		   KpiTlIndicatorKk kpiTlIndicatorKk = new KpiTlIndicatorKk();
		   kpiTlIndicatorKk.setKinkKeyid(row[0]);
		   kpiTlIndicatorKk.setKinkIndicatorname(row[1]);	
		   kpiTlIndicatorKk.setKinkIndicatorcode(row[2]);	
		   kpiTlIndicatorKk.setKinkDescription(row[3]);	
		   kpiTlIndicatorKk.setKinkParentid(row[4]);	
		   kpiTlIndicatorKk.setKinkLevelno(row[5]);
		   kpiTlIndicatorKk.setKinkSortno(row[6]);	
		   kpiTlIndicatorKk.setKinkIschild(row[7]);	
		   kpiTlIndicatorKk.setKinkInputtype(row[8]);	
		   kpiTlIndicatorKk.setKinkInputentry(row[9]);		  
		   kpiTlIndicatorKk.setKinkIdentifier(row[10]);
		   kpiTlIndicatorKk.setKinkManualcalctype(row[11]);
		   kpiTlIndicatorKk.setKinkUomid(row[12]);
		   kpiTlIndicatorKk.setKinkFrequency(row[13]);
		   kpiTlIndicatorKk.setKinkExcelname(row[14]);	
		   kpiTlIndicatorKk.setKinkDeptKeyid(row[15]);	
		   kpiTlIndicatorKk.setKinkCostarea(row[16]);
		   kpiTlIndicatorKk.setKinkTargetneed(row[17]);
		   kpiTlIndicatorKk.setKinkPillarid(row[18]);
		   kpiTlIndicatorKk.setKinkTempfield3(row[19]);
		   menus.add(kpiTlIndicatorKk);			   
	   }	    
	   return menus;
	 }

	@Override
	public List<String[]> getKPIProd(GridParams gridParams ,CommonFilter commonFilter) throws Exception
	{
		try
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = "";
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				List<String[]> topFailList=null;
				// sriram 12-nov 2025
				//if (commonFilter.getDrillLevel().equals("LIN"))
				if ("LIN".equals(commonFilter.getDrillLevel())) 
					
				{
					if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
						condParms+="SECTIONID="+commonFilter.getSectionId()+";";
				}//else if (commonFilter.getDrillLevel().equals("FCT"))
				else if ("FCT".equals(commonFilter.getDrillLevel()))
				{
					if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
						condParms+="FACTORYID="+commonFilter.getFactoryId()+";";
				}//else if (commonFilter.getDrillLevel().equals("CEL"))
				else if ("CEL".equals(commonFilter.getDrillLevel()))
				{
					if(UIUtils.isValidKeyId(commonFilter.getCellId()))
						condParms+="CELLID="+commonFilter.getCellId()+";";
				}
				if(UIUtils.isValidKeyId(commonFilter.getFlid()))
					condParms+="flid="+commonFilter.getFlid()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
					condParms+="INDICATORID="+commonFilter.getIndicator()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
					condParms+="PILLARID="+commonFilter.getPillarWise()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getDrillLevel()))
					condParms+="DRILLLEVEL="+commonFilter.getDrillLevel()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getType()))
					condParms+="TYPE="+commonFilter.getType()+";";
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				String sql=KpiTlIndicatorDeptLinkSql.getPillarID(commonFilter.getPillarWise());
				String pillId=dbActionTemplate.getSingleValue(sql);				
				String indicatorSql=KpiTlIndicatorDeptLinkSql.getIndicatorCnt(pillId);
				
				if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
					indicatorSql+="  and KINK_KEYID='"+commonFilter.getIndicator()+"'";
				
				String indicatorCount=dbActionTemplate.getSingleValue(indicatorSql);

				if(commonFilter.getAbnViewType().equals("MAPPEDKPI")){
						CommonMessage.debugMsg("Inside MAPPED KPI LIST");
						//topFailList = dbActionTemplate.processFunctionCalls("KPI_FN_FLIDDEPTLINKMAPKPI", paramValues);
						topFailList = fnCallApi.callFunction("KPI_FN_FLIDDEPTLINKMAPKPI_SB", paramValues,1,true);	
				}
				else if(commonFilter.getActionKeyId().equals("KPIALL")){
					CommonMessage.debugMsg("Inside All KPI List");
					//topFailList = dbActionTemplate.processFunctionCalls("KPI_FN_FLIDINDICATORDEPTLINK", paramValues);
					topFailList = fnCallApi.callFunction("KPI_FN_FLIDINDICATORDEPTLINK_SB", paramValues,1,true);
					
					
				}
			/*	else if(commonFilter.getAbnImp().equals("INACTIVE")){
					CommonMessage.debugMsg("INSIDE INACTIVE");
					CommonMessage.debugMsg("Inside  KPI INACTIVE List");
					topFailList = dbActionTemplate.processFunctionCalls("KPI_PC_KEYPERFORMANCE.KPI_FN_FLIDDEPTLINKINACTIVEKPI", paramValues);
				}*/
				
				long totalCnt1 = Long.parseLong(indicatorCount);
			  	gridParams.setTotalRecordCnt(totalCnt1);
			  	if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return topFailList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
	}
	@SuppressWarnings("static-access")
	@Override
	public KpiTlIndicatorDeptLink createIndicatorDeptLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink,String pillCode,String indicatorId,String deptId,String isIndicatorFactory,String drillLevel) throws Exception 
	{ String exceptionStr="";
		try{
			
			String val=kpiTlIndicatorDeptLink.getKidlCreatedby();
			CommonMessage.debugMsg("INISDE THE DAO IMPL");
			String dateTime1 = CommonFunctions.dateTimeNow();
			CommonMessage.debugMsg("pillCode"+pillCode);
			CommonMessage.debugMsg("drillLevel"+drillLevel);
			CommonMessage.debugMsg("indicatorId"+indicatorId);
			CommonMessage.debugMsg("deptId"+deptId);
			CommonMessage.debugMsg("isIndicatorFactory"+isIndicatorFactory);
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */			
			String sql="";			
			KpiTlIndicatorDeptLinkSql kpiTlIndicatorDeptLinkSql=new KpiTlIndicatorDeptLinkSql();
			sql=KpiTlIndicatorDeptLinkSql.getPillarID(pillCode);
			List <KpiTlIndicatorDeptLink> methodslist = kpiTlIndicatorDeptLink.getmethodPillarFactlink();
			CommonMessage.debugMsg("INSIDE THE METHOD LIST::"+methodslist);
			String pillId=dbActionTemplate.getSingleValue(sql);
			
			/*
			String timeFormat = "dd-MMM-yyyy";
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			Map<Integer,List<Object[]>> saveIndicatorDept=new HashMap<Integer,List<Object[]>>();
			List<Object []> delObj=new ArrayList<Object[]>();
			List<Object []> insObj=new ArrayList<Object[]>();
			List<int[]> dataType=new ArrayList<int[]>();
			String delSql=KpiTlIndicatorDeptLinkSql.getDeleteSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(),pillId,indicatorId,deptId,drillLevel);
			CommonMessage.debugMsg("delSql" + delSql);
			sqls.add(delSql);
			Object [] deleteDeptIndicator={pillId,drillLevel,deptId};
			if(UIUtils.isValidKeyId(indicatorId))
			{
				if(UIUtils.isValidKeyId(deptId))
				{
					Object [] deleteDeptIndicator1={pillId,drillLevel,indicatorId,deptId};
					delObj.add(	deleteDeptIndicator1);
					int [] dataTypeIndicatordel = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					dataType.add(dataTypeIndicatordel);
					saveIndicatorDept.put(0, delObj);
				}
				else
				{
					Object [] deleteDeptIndicator1={pillId,drillLevel,indicatorId};
					delObj.add(	deleteDeptIndicator1);
					int [] dataTypeIndicatordel = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					dataType.add(dataTypeIndicatordel);
					saveIndicatorDept.put(0, delObj);
				}
			}
			else
				{
					CommonMessage.debugMsg("coming dis else");
					CommonMessage.debugMsg("values" + pillId + drillLevel + deptId);
					delObj.add(deleteDeptIndicator);
					int [] dataTypeIndicatordel = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					dataType.add(dataTypeIndicatordel);
					saveIndicatorDept.put(0, delObj);
				}
				
			if(methodslist != null && methodslist.size()>0)
			{    CommonMessage.debugMsg("Inside methodslist");
				sqls.add(KpiTlIndicatorDeptLinkSql.getInsert());
				
				GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource(), KpiTlIndicatorDeptLinkSql.TBL_KPI_TL_INDICATOR_DEPT_LINK, 9, "KID", null,null);
				
				//int i=0;
				for(KpiTlIndicatorDeptLink indicatorFactLink:methodslist)
				{
					String seqNo=sequenceNumber.getSequnceNumber();
					indicatorFactLink.setKidlKeyid(seqNo);
					
					
					indicatorFactLink.setKidlPillarid(pillId);
					
					Object [] insertDeptIndicator	={indicatorFactLink.getKidlKeyid(),indicatorFactLink.getKidlIndicatorid(),deptId,drillLevel,indicatorFactLink.getKidlPillarid()
							,  new java.sql.Date( sdf.parse(indicatorFactLink.getKidlEffectivedate()).getTime()),new java.sql.Date( sdf.parse(indicatorFactLink.getKidlInactivedate()).getTime()),indicatorFactLink.getKidlTempfield1(),indicatorFactLink.getKidlTempfield2()
							,indicatorFactLink.getKidlTempfield3(),indicatorFactLink.getKidlTempfield4(),indicatorFactLink.getKidlTempfield5(),indicatorFactLink.getKidlActive(),indicatorFactLink.getKidlCreatedby()
							, new java.sql.Date( sdf.parse(indicatorFactLink.getKidlCreatedon()).getTime()) , new java.sql.Date( sdf.parse(indicatorFactLink.getKidlModifiedon()).getTime()) };
					
					String inputvalues =  indicatorFactLink.getKidlKeyid() + " , " + indicatorFactLink.getKidlIndicatorid() + " , " + indicatorFactLink.getKidlDeptid() + " , " + indicatorFactLink.getKidlDepttype() + " , " + indicatorFactLink.getKidlPillarid() + " , " ;
					inputvalues = inputvalues +  new java.sql.Date( sdf.parse(indicatorFactLink.getKidlEffectivedate()).getTime()) + " , " + new java.sql.Date( sdf.parse(indicatorFactLink.getKidlInactivedate()).getTime()) + " , " + indicatorFactLink.getKidlTempfield1() + " , " + indicatorFactLink.getKidlTempfield2() + " , ";
					inputvalues = inputvalues + indicatorFactLink.getKidlTempfield3() + " , " + indicatorFactLink.getKidlTempfield4() + " , " + indicatorFactLink.getKidlTempfield5() + " , " + indicatorFactLink.getKidlActive() + " , " + indicatorFactLink.getKidlCreatedby() + " , " ;
					inputvalues = inputvalues +  new java.sql.Date( sdf.parse(indicatorFactLink.getKidlCreatedon()).getTime()) + " , " + new java.sql.Date( sdf.parse(indicatorFactLink.getKidlModifiedon()).getTime()) ; 
					
					CommonMessage.debugMsg("inputvalues" + inputvalues);
					insObj.add(insertDeptIndicator);
					
					
				}
				//sequenceNumber.closeConnection();
				//sequenceNumber =null;
				//int [] dataTypeIndicator = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.DATE,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.CHAR,Types.VARCHAR,Types.DATE,Types.DATE};
				
				//dataType.add(dataTypeIndicator);
				
			}
			CommonMessage.debugMsg("sqls" +  sqls);
			saveIndicatorDept.put(1, insObj);
			dbActionTemplate.executeBatch(sqls, saveIndicatorDept, dataType);
			
			
			*/
              
			
			if(methodslist != null && methodslist.size()>0)
			{       CommonMessage.debugMsg("INSID THE METHOD IF");
				for(KpiTlIndicatorDeptLink indicatorFactLink:methodslist)
				{
					CommonMessage.debugMsg("getKidlIsDeletevalue="+ indicatorFactLink.getIsDelete());
					if (indicatorFactLink.getIsDelete().equals("Y")){
						
						CommonMessage.debugMsg("INSIDE THE IF::");
						//sqls.add(kpiTlIndicatorDeptLinkSql.getDeleteSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(),pillId,indicatorFactLink.getKidlIndicatorid(),indicatorFactLink.getKidlDeptid(),drillLevel));
						try{
							sql=kpiTlIndicatorDeptLinkSql.getDeleteSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(),pillId,indicatorFactLink.getKidlIndicatorid(),indicatorFactLink.getKidlDeptid(),drillLevel);
							dbActionTemplate.executeStatement(sql); 
						}
						catch(BusinessApplicationExceptions e)
						{			
							
							String exceptionSql="SELECT KINK_INDICATORNAME FROM KPI_TL_INDICATOR WHERE KINK_KEYID='"+indicatorFactLink.getKidlIndicatorid()+"'";
							String indicatorName=dbActionTemplate.getSingleValue(exceptionSql);
							exceptionSql="SELECT FUNCTIONALLOC FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+indicatorFactLink.getKidlDeptid()+"'";
							String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
							
							if(UIUtils.isValidKeyId(exceptionStr)){
								exceptionStr+=",";
							}
							exceptionStr+=indicatorName+ " Indicator In " +fnlnName;
							CommonMessage.debugMsg("BusinessApplicationExceptions"+e.getMessage()+exceptionStr);
						}
					}
					else{	
						
						CommonMessage.debugMsg("INSIDE THE ELSE123");
						
						indicatorFactLink.setKidlPillarid(pillId);
						
						
						String dateval="SYSDATE";
						indicatorFactLink.setKidlEffectivedate(dateTime1);
				     indicatorFactLink.setKidlInactivedate(Constants.futureNullDate);
						indicatorFactLink.setKidlModifiedon(dateTime1);
						indicatorFactLink.setKidlCreatedon(dateTime1);
						indicatorFactLink.setKidlDeptid(deptId);
						indicatorFactLink.setKidlDepttype(drillLevel);
						indicatorFactLink.setKidlTempfield1("-");
						indicatorFactLink.setKidlTempfield2("-");
						indicatorFactLink.setKidlTempfield3("-");
						indicatorFactLink.setKidlTempfield4("-");
						indicatorFactLink.setKidlTempfield5("-");
						indicatorFactLink.setKidlActive("Y");
						indicatorFactLink.setKidlCreatedby(val);
						indicatorFactLink.setKidlKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_KPI_TL_INDICATOR_DEPT_LINK, 9, "KID", null, null));
						sqls.add(kpiTlIndicatorDeptLinkSql.getInsertSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(), indicatorFactLink.getSaveArray()));
						CommonMessage.debugMsg("create daoimpl"+indicatorFactLink.getKidlKeyid());						
					}	
					
				}
			}
			
			else{
				CommonMessage.debugMsg("INSIDE THE ELSE");
				KpiTlIndicatorDeptLink indicatorFactLink=new KpiTlIndicatorDeptLink();
				indicatorFactLink.setKidlEffectivedate(dateTime1);
			     indicatorFactLink.setKidlInactivedate(dateTime1);
					indicatorFactLink.setKidlModifiedon(dateTime1);
					indicatorFactLink.setKidlCreatedon(dateTime1);
					indicatorFactLink.setKidlInactivedate(Constants.futureNullDate);
					indicatorFactLink.setKidlTempfield1("-");
					indicatorFactLink.setKidlTempfield2("-");
					indicatorFactLink.setKidlTempfield3("-");
					indicatorFactLink.setKidlTempfield4("-");
					indicatorFactLink.setKidlTempfield5("-");
					indicatorFactLink.setKidlActive("Y");
					//String val=indicatorFactLink.getKidlCreatedby();
					CommonMessage.debugMsg("val"+val);
					indicatorFactLink.setKidlCreatedby(val);
				indicatorFactLink.setKidlPillarid(pillId);
				indicatorFactLink.setKidlDepttype(pillCode);
				indicatorFactLink.setKidlIndicatorid(indicatorId);
				indicatorFactLink.setKidlDeptid(deptId);
				indicatorFactLink.setKidlKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_KPI_TL_INDICATOR_DEPT_LINK, 9, "KID", null, null));
				sqls.add(kpiTlIndicatorDeptLinkSql.getInsertSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(), indicatorFactLink.getSaveArray()));
				
				/* String exceptionSql="SELECT KINK_INDICATORNAME FROM KPI_TL_INDICATOR WHERE KINK_KEYID='"+indicatorFactLink.getKidlIndicatorid()+"'";
				String indicatorName=dbActionTemplate.getSingleValue(exceptionSql);
				exceptionSql="SELECT FUNCTIONALLOC FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+indicatorFactLink.getKidlDeptid()+"'";
				String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
				exceptionStr+=indicatorName+ " Indicator In " +fnlnName;
				*/
				String exceptionSql="SELECT KINK_INDICATORNAME FROM KPI_TL_INDICATOR WHERE KINK_KEYID='"+indicatorFactLink.getKidlIndicatorid()+"'";
				String indicatorName=dbActionTemplate.getSingleValue(exceptionSql);
				exceptionSql="SELECT FNLN_ORIGINALID FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+indicatorFactLink.getKidlDeptid()+"'";
				String fnlnoriginalid=dbActionTemplate.getSingleValue(exceptionSql);
				String str=fnlnoriginalid.substring(0,3);
				
				if(UIUtils.isValidKeyId(fnlnoriginalid) && str.equals("CEL")){
				exceptionSql="SELECT CELL_NAME FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID='"+fnlnoriginalid+"'";
				String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
				exceptionStr+=indicatorName+ " Indicator In " +fnlnName;
				CommonMessage.debugMsg("KPI Exception CEL"+exceptionStr);
				}
				else if(UIUtils.isValidKeyId(fnlnoriginalid) && str.equals("SEC")){
					exceptionSql="SELECT SECT_NAME FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID='"+fnlnoriginalid+"'";
					String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
					exceptionStr+=indicatorName+ " Indicator In " +fnlnName;
					CommonMessage.debugMsg("KPI Exception SEC"+exceptionStr);
				}
				else{
					exceptionSql="SELECT FUNCTIONALLOC FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+indicatorFactLink.getKidlDeptid()+"'";
					String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
					exceptionStr+=indicatorName+ " Indicator In " +fnlnName;
					CommonMessage.debugMsg("KPI Exception SEC"+exceptionStr);
				}
				CommonMessage.debugMsg("KidlKeyid"+indicatorFactLink.getKidlKeyid());
				//CommonFunctionsm..debugMsg("create daoimpl"+indicatorFactLink.getKidlKeyid());		
			}
			dbActionTemplate.executeStatements(sqls); 
			
			
			if(UIUtils.isValidKeyId(exceptionStr)){
				
				throw new BusinessApplicationExceptions(exceptionStr +" Already Referred In Target/Actual Entry"); 
			}
			return kpiTlIndicatorDeptLink;
		}
		
		catch(BusinessApplicationExceptions e)
		{			
			CommonMessage.debugMsg(e.getMessage());
			throw new BusinessApplicationExceptions(exceptionStr + " Already Referred ");
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook KpiExportExcel(net.sf.json.JSONObject tblJSONObj, String format, String compId, String factId,String sectId,String cellId,String drillLevel, String indicatorId, String pillCode, String pillarId,GridParams gridparams)throws Exception
	{
		ResultSet rs = null;
		try
		{
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setComp(compId); 
			commonFilter.setFactoryId(factId);
			commonFilter.setSectionId(sectId);
			commonFilter.setCellId(cellId);
			commonFilter.setDrillLevel(drillLevel);
			commonFilter.setIndicator(indicatorId);
			commonFilter.setPillarWise(pillarId);
			
			
			rs =   getdownTimeReportResultSet( gridparams , commonFilter);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(0,0,254)); //red font
			condFormatTick.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			condFormatTick.setFontHeightPoint((short)14); 
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(3);
			condFormatTick.setToCol(-1);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("1"); //Tick
			condFormatTick.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
			condFormatTick.setIdentfier("1");
			condFormats.add(condFormatTick);
			XLConditionalFormats condFormatBGTick = new XLConditionalFormats();
		 	condFormatBGTick.setFontColor(new RGB(0,254,0)); //red font
		 	condFormatBGTick.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		 	condFormatBGTick.setFontHeightPoint((short)14);
		 	condFormatBGTick.setFontBoldWeight((short)20);
		 	condFormatBGTick.setFromCol(3);
		 	condFormatBGTick.setToCol(-1);
		 	condFormatBGTick.setOperator(ComparisonOperator.EQUAL);
		 	condFormatBGTick.setCondValue("0"); //Tick
		 	condFormatBGTick.setSymbolStr(" ");
		 	condFormatBGTick.setIdentfier("0");
			condFormats.add(condFormatBGTick);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
		}
		finally
		{
			if( rs != null)
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	/*
	 * private ResultSet getdownTimeReportResultSet(String compId, String factId,
	 * String sectId, String cellId,String drillLevel, String indicatorId, String
	 * pillCode) throws Exception { List<String> paramValues =new
	 * ArrayList<String>(); String condParms = "";
	 * if(UIUtils.isValidKeyId(factId)&&!UIUtils.isValidKeyId(sectId)&&!UIUtils.
	 * isValidKeyId(cellId)) {condParms+="FACTORYID="+factId+";";} else
	 * if(UIUtils.isValidKeyId(sectId)&&!UIUtils.isValidKeyId(cellId))
	 * {condParms+="SECTIONID="+sectId+";";} else if(UIUtils.isValidKeyId(cellId))
	 * {condParms+="CELLID="+cellId+";";} if(UIUtils.isValidKeyId(indicatorId))
	 * condParms+="INDICATORID="+indicatorId+";"; if(UIUtils.isValidKeyId(pillCode))
	 * condParms+="PILLARCODE="+pillCode+";";
	 * condParms+="DRILLLEVEL="+drillLevel+";"; paramValues.add(condParms); //return
	 * dbActionTemplate.NewdbFunctionCall2("KPI_FN_INDICATORDEPTLINK", paramValues);
	 * return dbActionTemplate.NewdbFunctionCall2("KPI_FN_FLIDINDICATORDEPTLINK",
	 * paramValues);
	 * //getKPIProd(compId,factId,sectId,cellId,drillLevel,indicatorId,pillCode); }
	 */

	// sriram 20-Nov-2025
	
	private ResultSet getdownTimeReportResultSet(GridParams gridParams ,CommonFilter commonFilter) throws Exception
	{
		try
			{
			ResultSet rs= null;
				List<String> paramValues = new ArrayList<String>();
				String condParms = "";
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				List<String[]> topFailList=null;
				// sriram 12-nov 2025
				//if (commonFilter.getDrillLevel().equals("LIN"))
				if ("LIN".equals(commonFilter.getDrillLevel())) 
					
				{
					if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
						condParms+="SECTIONID="+commonFilter.getSectionId()+";";
				}//else if (commonFilter.getDrillLevel().equals("FCT"))
				else if ("FCT".equals(commonFilter.getDrillLevel()))
				{
					if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
						condParms+="FACTORYID="+commonFilter.getFactoryId()+";";
				}//else if (commonFilter.getDrillLevel().equals("CEL"))
				else if ("CEL".equals(commonFilter.getDrillLevel()))
				{
					if(UIUtils.isValidKeyId(commonFilter.getCellId()))
						condParms+="CELLID="+commonFilter.getCellId()+";";
				}
				if(UIUtils.isValidKeyId(commonFilter.getFlid()))
					condParms+="flid="+commonFilter.getFlid()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
					condParms+="INDICATORID="+commonFilter.getIndicator()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
					condParms+="PILLARID="+commonFilter.getPillarWise()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getDrillLevel()))
					condParms+="DRILLLEVEL="+commonFilter.getDrillLevel()+";";
				
//				if(UIUtils.isValidKeyId(commonFilter.getType()))
//					condParms+="TYPE="+commonFilter.getType()+";";
//				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				String sql=KpiTlIndicatorDeptLinkSql.getPillarID(commonFilter.getPillarWise());
				String pillId=dbActionTemplate.getSingleValue(sql);				
				String indicatorSql=KpiTlIndicatorDeptLinkSql.getIndicatorCnt(pillId);
				
				if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
					indicatorSql+="  and KINK_KEYID='"+commonFilter.getIndicator()+"'";
				
				String indicatorCount=dbActionTemplate.getSingleValue(indicatorSql);

				//if(commonFilter.getAbnViewType().equals("MAPPEDKPI")){
				if ("MAPPEDKPI".equals(commonFilter.getAbnViewType())) {
						CommonMessage.debugMsg("Inside MAPPED KPI LIST");
						rs = dbActionTemplate.NewdbFunctionCall2("KPI_FN_FLIDDEPTLINKMAPKPI", paramValues);	
				}
				//else if(commonFilter.getActionKeyId().equals("KPIALL")){
				else if ("KPIALL".equals(commonFilter.getActionKeyId())) {
					CommonMessage.debugMsg("Inside All KPI List");
					rs = dbActionTemplate.NewdbFunctionCall2("KPI_FN_FLIDINDICATORDEPTLINK", paramValues);
				}
				
				
			/*	else if(commonFilter.getAbnImp().equals("INACTIVE")){
					CommonMessage.debugMsg("INSIDE INACTIVE");
					CommonMessage.debugMsg("Inside  KPI INACTIVE List");
					topFailList = dbActionTemplate.processFunctionCalls("KPI_PC_KEYPERFORMANCE.KPI_FN_FLIDDEPTLINKINACTIVEKPI", paramValues);
				}*/
				
				long totalCnt1 = Long.parseLong(indicatorCount);
			  	gridParams.setTotalRecordCnt(totalCnt1);
			  	if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return  rs;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
	}
	@Override
	public List<String[]> getKpiReport(CommonFilter commonFilter)
			throws Exception {
		StringBuffer sql=new StringBuffer();
		List<String> params =null;
		sql.append("select 'Impact','Improvement','KPI Description','Source of KPI','UoM','Frequency','Reason for Tracking KPI','Type of Input','Datatype',");
		sql.append("'Annual Target','Start date','End Date'");
		sql.append(" from dual union all");
		sql.append(" select 'P','Improvement2','KPI Description1','Voice of Customer','LITRE-LTR','Weekly','Reason for Tracking KPI1','Manual','String,',");
		sql.append("'100000','12-oct-2013','12-oct-2014' from dual union all");
		
		sql.append(" select 'Q','Improvement3','KPI Description1','Self Driven','KILOGRAM-KG','Weekly','Reason for Tracking KPI1','Auto,','Number,',");
		sql.append("'900000','12-oct-2013','12-oct-2014' from dual union all");
		
		sql.append(" select 'C','Improvement4','KPI Description1','Top Driven','LITRE-LTR','Daily','Reason for Tracking KPI1','Data Upload','String,',");
		sql.append("'500000','12-oct-2013','12-oct-2014' from dual union all");
		
		sql.append(" select 'D','Improvement5','KPI Description1','Voice of Customer','METER-MTR','Weekly','Reason for Tracking KPI1','Manual','Number,',");
		sql.append("'600000','12-oct-2013','12-oct-2014' from dual union all");
		
		sql.append(" select 'E','Improvement6','KPI Description1','Top Driven','LITRE-LTR','Daily','Reason for Tracking KPI1','Auto,','String,',");
		sql.append("'200000','12-oct-2013','12-oct-2014' from dual union all");
		
		sql.append(" select 'S','Improvement7','KPI Description1','Self Driven','KILOGRAM-KG','Weekly','Reason for Tracking KPI1','Data Upload','Number,',");
		sql.append("'700000','12-oct-2013','12-oct-2014' from dual union all");
		
		sql.append(" select 'M','Improvement8','KPI Description1','Top Driven','METER-MTR','Daily','Reason for Tracking KPI1','Auto,','Decimal,',");
		sql.append("'600000','12-oct-2013','12-oct-2014' from dual ");
		
		CommonMessage.debugMsg("sql.."+sql);
		List<String[]> datgrid=dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		return datgrid;
	}

	@Override
	public KpiTlIndicatorKk select(KpiTlIndicatorKk kpiTlIndicatorKk)		throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		String sql =null;
		sql= newKpiTlIndicatorSql.getSelectSql(newKpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);		
		Object [] args =  new Object [] {};
		kpiTlIndicatorKk.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+kpiTlIndicatorKk.getKinkKeyid());
		return kpiTlIndicatorKk;
	}
	
	@Override
	public String validateKeyInactiveListLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception{
		
		StringBuilder updateinactive=new StringBuilder();
		updateinactive.append("UPDATE KPI_TL_INDICATOR_DEPT_LINK SET KIDL_ACTIVE='N' ");
		updateinactive.append("WHERE KIDL_INDICATORID='"+kpiTlIndicatorDeptLink.getKidlIndicatorid()+"' ");
		updateinactive.append("AND KIDL_DEPTID='"+kpiTlIndicatorDeptLink.getKidlDeptid()+"' ");
		CommonMessage.debugMsg("The updatedetail::::"+updateinactive);
		String indicatorname=dbActionTemplate.getSingleValue(updateinactive.toString());
		CommonMessage.debugMsg("The indicatorname"+indicatorname);
	    return indicatorname;	
	}
	
	@Override
	public String validatekeyIndLink(
			KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception {
		// TODO Auto-generated method stub
		String isValidate="";

		KpiTlIndicatorDeptLinkSql kpiTlIndicatorDeptLinkSql = new KpiTlIndicatorDeptLinkSql();
		String sql = kpiTlIndicatorDeptLinkSql.getLinkCount(kpiTlIndicatorDeptLink.getSaveArray());				
		CommonMessage.debugMsg(" sql: " + sql);
		String cnt = dbActionTemplate.getSingleValue(sql);
		//CommonMessage.debugMsg(" cnt: " + cnt);
		if(Integer.parseInt(cnt)==0){
			//CommonMessage.debugMsg(" in cnt: " + cnt);
			isValidate="";
		}
		else{
			String exceptionSql="SELECT KINK_INDICATORNAME FROM KPI_TL_INDICATOR WHERE KINK_KEYID='"+kpiTlIndicatorDeptLink.getKidlIndicatorid()+"'";
			String indicatorName=dbActionTemplate.getSingleValue(exceptionSql);
			exceptionSql="SELECT FUNCTIONALLOC FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+kpiTlIndicatorDeptLink.getKidlDeptid()+"'";
			String fnlnName=dbActionTemplate.getSingleValue(exceptionSql);
			isValidate=indicatorName+ " Indicator In " +fnlnName+" Already Referred In Target/Actual Entry";
		}

		return isValidate;		
	}
	
	@Override
	public String validatekeyActiveLink(KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) throws Exception{
		String ActiveIndicator="";
		ActiveIndicator="UPDATE KPI_TL_INDICATOR_DEPT_LINK SET KIDL_ACTIVE='Y' WHERE KIDL_INDICATORID='"+kpiTlIndicatorDeptLink.getKidlIndicatorid()+"' AND KIDL_DEPTID='"+kpiTlIndicatorDeptLink.getKidlDeptid()+"' ";
		CommonMessage.debugMsg("Avtive"+ActiveIndicator);
		dbActionTemplate.getSingleValue(ActiveIndicator);
		return ActiveIndicator;
	}


	@Override
	public KpiTlIndicatorDeptLink deleteDeptLink(
			KpiTlIndicatorDeptLink kpiTlIndicatorDeptLink) {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		KpiTlIndicatorDeptLinkSql kpiTlIndicatorDeptLinkSql = new KpiTlIndicatorDeptLinkSql();
		try{
			
		sqls.add(kpiTlIndicatorDeptLinkSql.getdeleteDeptLinkSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(), kpiTlIndicatorDeptLink.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		}
		
		
		
	catch( Exception e){
		//throw new Exception(e.getMessage());
		CommonMessage.debugMsg("E:::"+e);
	}
	return kpiTlIndicatorDeptLink;
	}

	@Override
	public String deptid(String kpid,String Flid) throws SQLException {
		// TODO Auto-generated method stub
	/*	String  deptid = dbActionTemplate.getSingleValue("KPI_TL_INDICATOR_DEPT_LINK", "KIDL_KEYID", "KIDL_INDICATORID", kpid);
		List<String> sql = new ArrayList<String>();
		//sql="select  KIDL_KEYID FROM KPI_TL_INDICATOR_DEPT_LINK WHERE KIDL_INDICATORID ='kpid' and KIDL_DEPTID='flid'";
		 return deptid;*/
	//dbActionTemplate.executeStatements(sql);
		//return sql;
		
		String sqls = new String();
		KpiTlIndicatorDeptLinkSql kpiTlIndicatorDeptLinkSql = new KpiTlIndicatorDeptLinkSql();
	
			//sqls=kpiTlIndicatorDeptLinkSql.kdlkeyidvalue(kpid, Flid);
		//sqls.add(kpiTlIndicatorDeptLinkSql.getdeptidSql(kpiTlIndicatorDeptLinkSql.getKidlDbFields(), KpiTlIndicatorDeptLink.getSaveArray()));
		 //return dbActionTemplate.getSingleValue(tableName, returnField, checkField, checkValue)getData(sqls);
			String 	Condnsql="KIDL_DEPTID=q'["+Flid+"]'";
			//'" + checkValue +"'
			String kdlkeyid=dbActionTemplate.getSingleValue("kpi_tl_indicator_dept_link", "KIDL_KEYID", "KIDL_INDICATORID", kpid, Condnsql);
	       

		 return kdlkeyid;
		}

	@Override
	public String dapartmentid(String kidlkeyid) throws SQLException {
		// TODO Auto-generated method stub
		String  departid = dbActionTemplate.getSingleValue("KPI_TL_INDICATOR_DEPT_LINK", "KIDL_DEPTID", "KIDL_KEYID", kidlkeyid);
		return departid;
	}
	
	@Override
	public List<String[]> getKPIActiveInactiveProd(GridParams gridParams ,CommonFilter commonFilter) throws Exception
	{
		CommonMessage.debugMsg("KPIActiveInactiveProd");
		try
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = "";
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				
				if (commonFilter.getDrillLevel().equals("LIN"))
				{
					if(UIUtils.isValidKeyId(commonFilter.getSectionId()))
						condParms+="SECTIONID="+commonFilter.getSectionId()+";";
				}else if (commonFilter.getDrillLevel().equals("FCT"))
				{
					if(UIUtils.isValidKeyId(commonFilter.getFactoryId()))
						condParms+="FACTORYID="+commonFilter.getFactoryId()+";";
				}else if (commonFilter.getDrillLevel().equals("CEL"))
				{
					if(UIUtils.isValidKeyId(commonFilter.getCellId()))
						condParms+="CELLID="+commonFilter.getCellId()+";";
				}
				if(UIUtils.isValidKeyId(commonFilter.getFlid()))
					condParms+="flid="+commonFilter.getFlid()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
					condParms+="INDICATORID="+commonFilter.getIndicator()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
					condParms+="PILLARID="+commonFilter.getPillarWise()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getDrillLevel()))
					condParms+="DRILLLEVEL="+commonFilter.getDrillLevel()+";";
				
				if(UIUtils.isValidKeyId(commonFilter.getType()))
					condParms+="TYPE="+commonFilter.getType()+";";
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				String sql=KpiTlIndicatorDeptLinkSql.getPillarID(commonFilter.getPillarWise());
				String pillId=dbActionTemplate.getSingleValue(sql);				
				String indicatorSql=KpiTlIndicatorDeptLinkSql.getIndicatorCnt(pillId);
				
				if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
					indicatorSql+="  and KINK_KEYID='"+commonFilter.getIndicator()+"'";
				
				String indicatorCount=dbActionTemplate.getSingleValue(indicatorSql);
				List<String[]> topFailList=null;
				//topFailList = dbActionTemplate.processFunctionCalls("KPI_PC_KEYPERFORMANCE.KPI_FN_INDICATORDEPTLINK", paramValues);
				
				topFailList = dbActionTemplate.processFunctionCalls("KPI_FN_FLIDINDICATORDEPTLINK", paramValues);
				
				long totalCnt1 = Long.parseLong(indicatorCount);
			  	gridParams.setTotalRecordCnt(totalCnt1);
			  	if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					//CommonMessage.debugMsg("totalCnt....."+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			  	//CommonMessage.debugMsg(" topFailList size " + topFailList.size());
				return topFailList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
	}


	
}


