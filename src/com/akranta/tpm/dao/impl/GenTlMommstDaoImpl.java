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
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlMommstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.GenTlVisitorsSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class GenTlMommstDaoImpl implements GenTlMommstDao {


	private DBActionTemplate dbActionTemplate; 
	private MomServiceApi momServiceApi;
	FunctionCallApi fnCallApi;

	public GenTlMommstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void GenTlMommstDaoImplJwt(String JwtToken) 
	{
		try{
			momServiceApi = new MomServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public GenTlMommst create(GenTlMommst genTlMommst) throws BusinessApplicationExceptions,Exception 
	{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMommstSql genTlMommstSql = new GenTlMommstSql();// contains dbtable,field names, Field types and related sqls  of master table
		GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
		GenTlMomKpiLinkSql genTlMomKpiLinkSql =new GenTlMomKpiLinkSql();
		//GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql();
		
		String elementId = genTlMommst.getElementid();
	 	String location = null;
	 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlMommstSql.TBL_GEN_TL_MOMMST);

		
		//genTlMommst.setMomsKeyid(dbActionTemplate.getSequenceNumber(GenTlMommstSql.TBL_GEN_TL_MOMMST, 10, "MOM", "YYMM", null));
		genTlMommst.setMomsKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "MOM", "YY", "Y"));  
		genTlMommst.setMomsMeetingno(genTlMommst.getMomsKeyid());

		sqls.add(GenTlMommstSql.getInsertSql(genTlMommstSql.getMomsDbFields(), genTlMommst.getSaveArray())); // add insert sql for master table
		List<GenTlMomdtl> genTlMomdtls= genTlMommst.getMomeetingDetail();
		List<GenTlMomKpiLink> genTlMomKpiLinks=genTlMommst.getMomeetingKPI();
		//if(genTlMommst.getMomeetingDetail()!= null && genTlMommst.getMomeetingDetail().size()>0) // check for detail table data
		//{	
			if( genTlMomdtls != null && genTlMomdtls.size()> 0 )
			{	
				CommonMessage.debugMsg("Inside the genTlMomdtls if");
				for( GenTlMomdtl genTlMomdtl : genTlMomdtls)
				{
					CommonMessage.debugMsg("Inside the genTlMomdtls for");
					//GenTlMomdtl genTlMomdtl = (GenTlMomdtl)genTlMommst.getMomeetingDetail().get(0); // get detail info from list in Mommeeting object
					genTlMomdtl.setMomdKeyid(dbActionTemplate.getSequenceNumber(GenTlMomdtlSql.TBL_GEN_TL_MOMDTL, 10, "MOD", "YYMM","Y"));
					genTlMomdtl.setMomdMomsKeyid(genTlMommst.getMomsKeyid());
					sqls.add(GenTlMomdtlSql.getInsertSql(genTlMomdtlSql.getMomdDbFields(), genTlMomdtl.getSaveArray()));// add insert sql for detail table
            			            
		         //  GenTlMomattendance genTlMomattendance = (GenTlMomattendance)genTlMommst.getMomeetinMomattendances().get(0);
		         //  genTlMomattendance.setMomaKeyid(dbActionTemplate.getSequenceNumber(GenTlMomattendanceSql.TBL_GEN_TL_MOMATTENDANCE,100,"MOMA",  "MMYY", "Y")); // set the sequnce number
		         //  genTlMomattendance.setMomaMomsKeyid(genTlMommst.getMomsKeyid());
				 //  sqls.add(GenTlMomattendanceSql.getInsertSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray())); // add insert sql for master table
		            if( genTlMomKpiLinks !=null && genTlMomKpiLinks.size()>0){
		            for(GenTlMomKpiLink genTlMomKpiLink:genTlMomKpiLinks)
					{
						genTlMomKpiLink.setMokpKeyid(dbActionTemplate.getSequenceNumber(GenTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK,10,"MOK",null,null));
						genTlMomKpiLink.setMokpMomsKeyid(genTlMommst.getMomsKeyid());
						genTlMomKpiLink.setMokpMomdKeyid(genTlMomdtl.getMomdKeyid());
						sqls.add(GenTlMomKpiLinkSql.getInsertSql(genTlMomKpiLinkSql.getMokpDbFields(), genTlMomKpiLink.getSaveArray()));// add insert sql for detail table
					} 
		            }
				}
				
		    }
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return genTlMommst;
	}
	
	public GenTlMommst update(GenTlMommst genTlMommst)	throws Exception { CommonMessage.debugMsg(" Inside Dao Impl :: Mom 6");
		
		List<String> sqls = new ArrayList<String>();
		GenTlMommstSql genTlMommstSql = new GenTlMommstSql();
		GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
		GenTlMomKpiLinkSql genTlMomKpiLinkSql =new GenTlMomKpiLinkSql();
		
		
			
		sqls.add(GenTlMommstSql.getUpdateSql(genTlMommstSql.getMomsDbFields(), genTlMommst.getSaveArray()));
		List<GenTlMomdtl> genTlMomdtls= genTlMommst.getMomeetingDetail();
		List<GenTlMomKpiLink> genTlMomKpiLinks=genTlMommst.getMomeetingKPI();
	//	List<GenTlMomattendance> genTlMomattendances= genTlMommst.getMomeetinMomattendances();
		
		if( genTlMomdtls != null && genTlMomdtls.size()> 0 )
		{	
			for( GenTlMomdtl genTlMomdtl : genTlMomdtls){
				
				if(!UIUtils.isValidKeyId(genTlMomdtl.getMomdKeyid()))
				{
					genTlMomdtl.setMomdKeyid(dbActionTemplate.getSequenceNumber(GenTlMomdtlSql.TBL_GEN_TL_MOMDTL, 10, "MOD", "YYMM","Y"));
					genTlMomdtl.setMomdMomsKeyid(genTlMommst.getMomsKeyid());
					sqls.add(GenTlMomdtlSql.getInsertSql(genTlMomdtlSql.getMomdDbFields(), genTlMomdtl.getSaveArray()));
					if( genTlMomKpiLinks.size()>0 ){//!=null && genTlMomKpiLinks.size()>0  
						for(GenTlMomKpiLink genTlMomKpiLink:genTlMomKpiLinks)
						{

							if(UIUtils.isValidKeyId(genTlMomKpiLink.getMokpKinkKeyid())){
								
								if(!UIUtils.isValidKeyId(genTlMomKpiLink.getMokpMomdKeyid())){
									genTlMomKpiLink.setMokpKeyid(dbActionTemplate.getSequenceNumber(GenTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK,10,"MOK",null,null));
									genTlMomKpiLink.setMokpMomdKeyid(genTlMomdtl.getMomdKeyid());
									sqls.add(GenTlMomKpiLinkSql.getInsertSql(genTlMomKpiLinkSql.getMokpDbFields(), genTlMomKpiLink.getSaveArray()));// add insert sql for detail table
								}
							}
						}
					}
				}
				else
				{
					String sql="";
					String cnt;
					sql = " select Count(*) From gen_tl_mom_kpi_link   where 1=1 AND MOKP_MOMD_KEYID= '"+genTlMomdtl.getMomdKeyid()+"'";
					
					
					genTlMomdtl.setMomdMomsKeyid(genTlMommst.getMomsKeyid());

					sqls.add(GenTlMomdtlSql.getUpdateSql(genTlMomdtlSql.getMomdDbFields(), genTlMomdtl.getSaveArray()));
					cnt=dbActionTemplate.getSingleValue(sql);
					
					if(Integer.parseInt(cnt)>0)
					sqls.add(GenTlMomKpiLinkSql.getDeleteSqlKpi(genTlMomdtl.getMomdKeyid()));
					
					for(GenTlMomKpiLink genTlMomKpiLink:genTlMomKpiLinks)
					{
						if(UIUtils.isValidKeyId(genTlMomKpiLink.getMokpMomdKeyid()) && (genTlMomKpiLink.getMokpMomdKeyid().equals(genTlMomdtl.getMomdKeyid())))
						{
							if(UIUtils.isValidKeyId(genTlMomKpiLink.getMokpKinkKeyid())){
							   genTlMomKpiLink.setMokpKeyid(dbActionTemplate.getSequenceNumber(GenTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK,10,"MOK",null,null));
							   genTlMomKpiLink.setMokpMomdKeyid(genTlMomdtl.getMomdKeyid());
							   
							   sqls.add(GenTlMomKpiLinkSql.getInsertSql(genTlMomKpiLinkSql.getMokpDbFields(), genTlMomKpiLink.getSaveArray()));// add insert sql for detail table
							}
						}
					}
				}	
			
			}
		
		}
	    dbActionTemplate.executeStatements(sqls);
		return genTlMommst;
	}
	
	public GenTlMommst delete(GenTlMommst genTlMommst)throws Exception 
	{
		
		List<String> sqls = new ArrayList<String>();
		GenTlMommstSql genTlMommstSql = new GenTlMommstSql();
		GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
		GenTlMomattendanceSql genTlMomattendanceSql=new GenTlMomattendanceSql();
		GenTlMomKpiLinkSql genTlMomKpiLinkSql=new GenTlMomKpiLinkSql();
		GenTlVisitorsSql newGenTlVisitorsSql=new GenTlVisitorsSql();
		//List<GenTlMomdtl> genTlMomdtls= genTlMommst.getMomeetingDetail();      
       //CommonMessage.debugMsg("dao impl sql "+genTlMomdtls.size());
		CommonMessage.debugMsg("delete the detail table");
		CommonMessage.debugMsg("delete the detail table");
	    //genTlMomdtl.setMomdKeyid(genTlMommst.getMomsKeyid());
		sqls.add("Delete from " +genTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK+" where MOKP_MOMS_KEYID='"+genTlMommst.getMomsKeyid()+"'");
		sqls.add("Delete from " +genTlMomdtlSql.TBL_GEN_TL_MOMDTL +" where  MOMD_MOMS_KEYID ='"+ genTlMommst.getMomsKeyid()+"'");
		sqls.add("Delete from " +newGenTlVisitorsSql.TBL_GEN_TL_VISITORS+" where VISI_MOMS_KEYID ='"+ genTlMommst.getMomsKeyid()+"'");
		sqls.add("Delete from " +genTlMomattendanceSql.TBL_GEN_TL_MOMATTENDANCE+" where MOMA_MOMS_KEYID ='"+ genTlMommst.getMomsKeyid()+"'");

		sqls.add(GenTlMommstSql.getDeleteSql(genTlMommstSql.getMomsDbFields(), genTlMommst.getSaveArray()));
			
		dbActionTemplate.executeStatements(sqls);
	
		return genTlMommst;
	}

	@Override
	public GenTlMommst select(String momKeyid) throws Exception 
	{
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:"+momKeyid);
		GenTlMommst genTlMommst = new GenTlMommst();		
		String sql = GenTlMommstSql.getMomsFrmDataSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { momKeyid };
		genTlMommst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+genTlMommst.getMomsKeyid());
		return  genTlMommst;
	}

	@Override
	public GenTlMommst selectRecall(String shift, String mstDate, String flid, String type)
			throws Exception {
		// TODO Auto-generated method stub
		GenTlMommst genTlMommst = new GenTlMommst();
		String sql = GenTlMommstSql.momRecallGrid();
	    CommonMessage.debugMsg(" mstDate :: In Dao Impl "+mstDate);
		Object [] args =  new Object [] { mstDate };//shift,mstDate,flid,type
		genTlMommst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return  genTlMommst;
	}

	@Override
	public List<String[]> selectRecalling(String shift, String mstDate,
			String flid, String type, String pillarid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> params = new ArrayList<String>();
			CommonMessage.debugMsg(" In Dao Impl "+shift+" shift :: "+mstDate+" flid :: "+flid+" type :: "+type);
			String sql = GenTlMommstSql.mommstrecalngGrid(shift,mstDate,flid,type,pillarid);
			List<String[]> operator = dbActionTemplate.getDataList(sql,params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

public List<String[]> getMomGrid(CommonFilter commonFilter,String KeyId, String momdate, String shift, String pillarid)	throws Exception {
		
		try
		{
			List<String> params = new ArrayList<String>();
			String flid=commonFilter.getFlid();
			String type=commonFilter.getType();
			CommonMessage.debugMsg(" In Dao Impl "+momdate+" shift :: "+shift+" flid :: "+flid);
			String sql = GenTlMommstSql.momGrid(commonFilter,KeyId,flid,momdate,shift,type,pillarid);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
@Override
public List<String[]> getMomeeting(CommonFilter commonFilter) throws Exception {
	
	List<String> paramValues = new ArrayList<String>();		
	String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	//String FLID=commonFilter.getFlid();
	
	CommonMessage.debugMsg(" Inside Dao Impl :: FLID "+commonFilter.getFlid());
	
	CommonMessage.debugMsg("test to............"+commonFilter.getType());
	if(UIUtils.isValidKeyId(commonFilter.getRefdocid())){
		condParms +="MOMREFID="+commonFilter.getRefdocid()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getType())){
		condParms +="MOMREFDATE="+commonFilter.getType()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParms +="FLID="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParms +="MOMTYPE="+commonFilter.getTaskid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getMainGroup())){
		condParms +="MODE="+commonFilter.getMainGroup().toUpperCase()+";";
	}
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_MOMMAINGRID", paramValues);
	List<String[]> dataList = fnCallApi.callFunction("GEN_FN_MOMMAINGRID_SB", paramValues,3,true);
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

@Override
public List<String[]> getMomeetingRvw(CommonFilter commonFilter) throws Exception {
	
	List<String> paramValues = new ArrayList<String>();		
	String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	//String FLID=commonFilter.getFlid();
	
	CommonMessage.debugMsg(" Inside Dao Impl :: FLID "+commonFilter.getFlid());
	
	CommonMessage.debugMsg("test to............"+commonFilter.getType());
	if(UIUtils.isValidKeyId(commonFilter.getRefdocid())){
		condParms +="MOMREFID="+commonFilter.getRefdocid()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getType())){
		condParms +="MOMREFDATE="+commonFilter.getType()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParms +="FLID="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParms +="MOMTYPE="+commonFilter.getTaskid()+";";
	}

	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_MOMREVIEWGRID", paramValues);
	List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_MOMREVIEWGRID_SB", paramValues,3,true);
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
	CommonMessage.debugMsg("cvgggg");
	return dataList; 



}

@Override
public GenTlMomdtl selectdtl(String keyid) throws Exception 
{
	CommonMessage.debugMsg("Inside the dao impl");
	CommonMessage.debugMsg("ID:"+keyid);
	GenTlMomdtl genTlMomdtl = new GenTlMomdtl();
	String sql = GenTlMommstSql.getMomsFrmDataSql1();
	CommonMessage.debugMsg("DAO SQL : "+sql);
	Object [] args =  new Object [] { keyid };
	genTlMomdtl.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	CommonMessage.debugMsg("DAO Query:"+genTlMomdtl.getMomdMomsKeyid());
	return  genTlMomdtl;
}

@Override
public List<String[]> getMomeetingAtt(CommonFilter commonFilter,String KeyId,String location,String flid,String Momdate, String shift, String recall) throws Exception 
{
	
		CommonMessage.debugMsg("The MOMKeyid"+KeyId);
		
		CommonMessage.debugMsg(" Functional Location "+flid);
		if(Momdate == null || Momdate.trim().isEmpty()){
	        throw new Exception("Meeting date (Momdate) is required and cannot be empty");
	    }
	    
		String sql=null;
		
		
		
		
		List<String> paramValues = new ArrayList<String>();
		String empl = commonFilter.getEmployee() != null ? commonFilter.getEmployee().getId():"";
		String dept = commonFilter.getDept() != null ? commonFilter.getDept().getId():"";
		String condParms = "";
		//String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(UIUtils.isValidKeyId(empl)){
			condParms +=";EMPLOYEE="+empl;
		}
		if(UIUtils.isValidKeyId(dept)){
			condParms +=";DEPARTMENT="+dept;
		}
		if(UIUtils.isValidKeyId(flid)){
			condParms +=";FNLN="+flid;
			//condParms +=";FNLN="+flid+";MKEYID="+KeyId;
		}
		if(UIUtils.isValidKeyId(Momdate)){
			condParms +=";MOMDATE="+Momdate;
			//condParms +=";FNLN="+flid+";MKEYID="+KeyId;
		}
		if(UIUtils.isValidKeyId(KeyId)){
			condParms +=";MKEYID="+KeyId;
			 condParms +=";MENUMODE="+commonFilter.getRange();
		}
		
		if(UIUtils.isValidKeyId(location)){
			condParms +=";LOCATION="+location;
		}
		if(UIUtils.isValidKeyId(shift)){
			condParms +=";MOMS_SHIFT="+shift;
		}
		CommonMessage.debugMsg(" Inside Dao Impl :: PD :: "+commonFilter.getActionKeyId());
		
		 if(UIUtils.isValidKeyId(commonFilter.getActionKeyId())){
			 
			 if(commonFilter.getKey().length()==0||commonFilter.getKey()==null)
			 {  
				 String sqll = "select MOMS_PILLARID from GEN_TL_MOMMST where MOMS_KEYID='"+KeyId+"' ";
					String pillarid = dbActionTemplate.getSingleValue(sqll);
					CommonMessage.debugMsg("pillarid in dao"+pillarid);
				 condParms +=";PILLARKEYID="+pillarid; 
			 }
			 else{
			 condParms +=";PILLARKEYID="+commonFilter.getKey();
			 }
			 condParms +=";MEETINGTYPE="+commonFilter.getActionKeyId();
			 condParms +=";FNLN="+flid;
			 if(UIUtils.isValidKeyId(Momdate)){
				    condParms +=";MOMDATE="+Momdate;
				}

			 //condParms +=";LOCATION="+commonFilter.getLocation();
			 condParms +=";CELLID="+commonFilter.getCellId();
			 condParms +=";MKEYID="+KeyId;
			 condParms +=";PILLARGROUP="+commonFilter.getKK();
			 //condParms +=";MENUMODE="+commonFilter.getRange();
			 CommonMessage.debugMsg(" Inside flid "+flid);
			 CommonMessage.debugMsg(" Inside Momdate "+Momdate);
			 //CommonMessage.debugMsg(" Inside LOCATIONID "+commonFilter.getLocation());
			 CommonMessage.debugMsg(" Inside CELLID "+commonFilter.getCellId()+commonFilter.getKK());
			 
		 }
		 CommonMessage.debugMsg(" roleid :: "+commonFilter.getKey()+" PILLAR :: "+commonFilter.getActionKeyId());
		 
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		CommonMessage.debugMsg("ParamValues:"+paramValues);	
		//List<String[]> dataList =   dbActionTemplate.processFunctionCalls("GEN_FN_MOMATTENDENCE", paramValues);
		List<String[]> dataList = fnCallApi.callFunction("GEN_FN_MOMATTENDENCE_SB", paramValues,3,true);
		//List<String[]> dataList = fnCallApi.callFunction("GEN_FN_MOMATTENDENCE_SB", paramValues);
		
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

@Override
public GenTlMomattendance selectatt(String keyid) throws Exception {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("Inside the dao impl");
	CommonMessage.debugMsg("ID:"+keyid);
	GenTlMomattendance genTlMomattendance = new GenTlMomattendance();
	
	String sql = GenTlMommstSql.getMomsFrmDataSql12();
			
	CommonMessage.debugMsg("DAO SQL : "+sql);
	Object [] args =  new Object [] { keyid };
	genTlMomattendance.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	CommonMessage.debugMsg("DAO Query:"+genTlMomattendance.getMomaMomsKeyid());
	return  genTlMomattendance;
}

@Override
public Workbook MomeetingRvwExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
	ResultSet rs = null;
	   try{
		
		rs =   MomeetingreviewResultSet(commonFilter);
		CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
		ExcelUtils excelUtils = new ExcelUtils(colModel);
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		
		XLConditionalFormats condFormat = new XLConditionalFormats();
		condFormat.setFontColor(new RGB(254,0,0)); //red font
		condFormat.setFontName("Wingdings");
		condFormat.setFontHeightPoint((short)104);
		condFormat.setFontBoldWeight((short)20);
		condFormat.setFromCol(103);
		condFormat.setToCol(-10);
		condFormat.setOperator(ComparisonOperator.EQUAL);
		condFormat.setCondValue( (char)252+""); //Tick
		condFormat.setIdentfier("tick");
		condFormats.add(condFormat);
		excelUtils.setCondFormats(condFormats);
		return excelUtils.writeToExcel(rs,rptFormat,2,1,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }  
}

private ResultSet MomeetingreviewResultSet(CommonFilter commonFilter) throws Exception 
{
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParms +="MOMTYPE="+commonFilter.getTaskid()+";";
	}
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMREVIEWGRID", paramValues);

}

/*
 * @Override public Workbook MomeetingExportExcel(CommonFilter
 * commonFilter,JSONObject colModel, String rptFormat) throws Exception {
 * ResultSet rs = null; try{
 * 
 * rs = getMomeetingReportResultSet(commonFilter);
 * CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency()); ExcelUtils
 * excelUtils = new ExcelUtils(colModel); List<XLConditionalFormats> condFormats
 * = new ArrayList<XLConditionalFormats>();
 * 
 * XLConditionalFormats condFormat = new XLConditionalFormats();
 * condFormat.setFontColor(new RGB(254,0,0)); //red font
 * condFormat.setFontName("Wingdings");
 * condFormat.setFontHeightPoint((short)104);
 * condFormat.setFontBoldWeight((short)20); condFormat.setFromCol(103);
 * condFormat.setToCol(-10); condFormat.setOperator(ComparisonOperator.EQUAL);
 * condFormat.setCondValue( (char)252+""); //Tick
 * condFormat.setIdentfier("tick"); condFormats.add(condFormat);
 * excelUtils.setCondFormats(condFormats); return
 * excelUtils.writeToExcel(rs,rptFormat,2,1,0 );//Change for Excel
 * 
 * }finally{ DBActionTemplate.closeConnection(rs, null, null, null,
 * rs.getStatement().getConnection()); } }
 */

@Override
public Workbook MomeetingExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
	ResultSet rs = null;
	   try{
		
		rs =   getMomeetingReportResultSet(commonFilter);
		System.out.println("rs value::::::::"+ rs.getConcurrency());
		ExcelUtils excelUtils = new ExcelUtils(colModel);
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		
		XLConditionalFormats condFormat = new XLConditionalFormats();
		condFormat.setFontColor(new RGB(254,0,0)); //red font
		condFormat.setFontName("Wingdings");
		condFormat.setFontHeightPoint((short)104);
		condFormat.setFontBoldWeight((short)20);
		condFormat.setFromCol(103);
		condFormat.setToCol(-10);
		condFormat.setOperator(ComparisonOperator.EQUAL);
		condFormat.setCondValue( (char)252+""); //Tick
		condFormat.setIdentfier("tick");
		condFormats.add(condFormat);
		excelUtils.setCondFormats(condFormats);
		return excelUtils.writeToExcel(rs,rptFormat,3,1,0 );//Change for Excel 
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }  
}


@Override
public Workbook MomeetingMonthwiseExportExcel(CommonFilter commonFilter,
		JSONObject colmodel, String format) throws Exception {
	// TODO Auto-generated method stub
	   ResultSet rs = null;
	   try{
		
		rs =   getMomeetingReportMonthwiseResultSet(commonFilter);
		CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,format,2,1,0 );//Change for Excel 10 Feb
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}
private ResultSet getMomeetingReportMonthwiseResultSet(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
	if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
		   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
	if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
		   condParms+="PILLARID="+commonFilter.getPillarWise()+";";//Added This Line
	paramValues.add(condParms);
	paramValues.add(commonParams);
	//Main Changes
	return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMATTDNCMONTHWISERPT", paramValues);
}

/*
 * private ResultSet getMomeetingReportResultSet(CommonFilter commonFilter)
 * throws Exception { List<String> paramValues = new ArrayList<String>(); String
 * condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter); String
 * commonParams = FilterCondSql.getGridCommonParams(commonFilter); //
 * "ISTOTALCNT="+commonFilter.getViewClick()
 * +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow()
 * +";";
 * 
 * if(UIUtils.isValidKeyId(commonFilter.getTaskid())){ condParms
 * +="MOMTYPE="+commonFilter.getTaskid()+";"; }
 * 
 * paramValues.add(condParms); paramValues.add(commonParams); return
 * dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMMAINGRID", paramValues);
 * 
 * }
 */

private ResultSet getMomeetingReportResultSet(CommonFilter commonFilter) throws Exception 
{
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParms +="MOMTYPE="+commonFilter.getTaskid()+";";
	}
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMMAINGRID_SB", paramValues);

}

@Override
public GenTlMommst createatt(GenTlMommst newGenTlMommst) throws Exception {

	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	//GenTlMommstSql genTlMommstSql = new GenTlMommstSql();// contains dbtable,field names, Field types and related sqls  of master table
	//GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
	GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql();
	
		//newGenTlMommst.setMomsKeyid(dbActionTemplate.getSequenceNumber(GenTlMommstSql.TBL_GEN_TL_MOMMST, 100, "MOM", "MMYY", "Y"));  
		//sqls.add(GenTlMommstSql.getInsertSql(genTlMommstSql.getMomsDbFields(), newGenTlMommst.getSaveArray())); // add insert sql for master table
	CommonMessage.debugMsg("Sql Inserted");
	if(newGenTlMommst.getMomeetinMomattendances()!= null && newGenTlMommst.getMomeetinMomattendances().size()>0) // check for detail table data
	{	
			 
        GenTlMomattendance genTlMomattendance = (GenTlMomattendance)newGenTlMommst.getMomeetinMomattendances().get(0);
        genTlMomattendance.setMomaKeyid(dbActionTemplate.getSequenceNumber(GenTlMomattendanceSql.TBL_GEN_TL_MOMATTENDANCE,10,"MOA",  "YYMM", "Y")); // set the sequnce number
        genTlMomattendance.setMomaMomsKeyid(newGenTlMommst.getMomsKeyid());
		sqls.add(GenTlMomattendanceSql.getInsertSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray())); // add insert sql for master table

	}
	
	dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
	
	return newGenTlMommst;
}

@Override
public GenTlMommst updateatt(GenTlMommst newGenTlMommst) throws Exception {
	List<String> sqls = new ArrayList<String>();
	GenTlMommstSql genTlMommstSql = new GenTlMommstSql();
	GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql();	
	sqls.add(GenTlMommstSql.getUpdateSql(genTlMommstSql.getMomsDbFields(), newGenTlMommst.getSaveArray()));
	List<GenTlMomattendance> genTlMomattendances= newGenTlMommst.getMomeetinMomattendances();		
	if( genTlMomattendances != null && genTlMomattendances.size()> 0 )
	{	
		for( GenTlMomattendance genTlMomattendance : genTlMomattendances){
			if(!UIUtils.isValidKeyId(genTlMomattendance.getMomaKeyid()))
			{											
				genTlMomattendance.setMomaKeyid(dbActionTemplate.getSequenceNumber(GenTlMomattendanceSql.TBL_GEN_TL_MOMATTENDANCE,10,"MOA",  "YYMM", null)); // set the sequnce number

				genTlMomattendance.setMomaMomsKeyid(newGenTlMommst.getMomsKeyid());
				sqls.add(GenTlMomattendanceSql.getInsertSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray()));								
			}					
			else{						
				sqls.add(GenTlMomattendanceSql.getUpdateSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray()));
			}	
		}
	}		
	dbActionTemplate.executeStatements(sqls);
		//CommonMessage.debugMsg("Saveed Sucessfully ::: ");
		
	return newGenTlMommst;
}

	@Override
	public void DeleteMomRow(String keyid) throws Exception 
	{
		List<String > sqls = new ArrayList<String>();
		sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
		sqls.add( GenTlMomdtlSql.DeleteMomRow(keyid));
  
		dbActionTemplate.executeStatements(sqls);
	}

@Override
public void DeleteATTVisitorRow(String keyid) throws Exception
{
	// TODO Auto-generated method stub
	String sql = GenTlVisitorsSql.DeleteATTRow(keyid);
	this.dbActionTemplate.executeStatement(sql);
	
}

@Override
public Workbook MomeetingAttExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception
{
	ResultSet rs = null;
	   try{
		
		rs =   getMomeetingAttRptReport(commonFilter);
		CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		
		XLConditionalFormats condFormat = new XLConditionalFormats();
		condFormat.setFontColor(new RGB(254,0,0)); //red font
		condFormat.setFontName("Wingdings");
		condFormat.setFontHeightPoint((short)104);
		condFormat.setFontBoldWeight((short)20);
		condFormat.setFromCol(103);
		condFormat.setToCol(-10);
		condFormat.setOperator(ComparisonOperator.EQUAL);
		condFormat.setCondValue( (char)252+""); //Tick
		condFormat.setIdentfier("tick");
		condFormats.add(condFormat);
		excelUtils.setCondFormats(condFormats);
		return excelUtils.writeToExcel(rs,format,0,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }  
}

@Override
	public Workbook getmomAttReportExcel(CommonFilter commonFilter,
		JSONObject colmodel, String format) throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs = null;
	   try{
		
		rs =   getMomAttReport(commonFilter);
		CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
        List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
        
        XLConditionalFormats condFormat = new XLConditionalFormats();			
		condFormat.setFontColor(new RGB(0,0,0)); //red font
		condFormat.setFontName("Wingdings");
		condFormat.setFontHeightPoint((short)14);
		condFormat.setFontBoldWeight((short)20);
		condFormat.setFromCol(10);
		condFormat.setToCol(-1);
		condFormat.setOperator(ComparisonOperator.EQUAL);
		condFormat.setCondValue("P"); //Tick
		condFormat.setBgColor(new RGB(0,255,0));
		//condFormat.setBgColor(new RGB(255,255,255));
		condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");//+""
		
		condFormats.add(condFormat);
		
		XLConditionalFormats condFormatNtPlnd = new XLConditionalFormats();			
		condFormatNtPlnd.setFontColor(new RGB(0,0,0)); //red font
		condFormatNtPlnd.setFontName(XLConditionalFormats.FONT_DEFAULT);
		condFormatNtPlnd.setFontHeightPoint((short)14);
		condFormatNtPlnd.setFontBoldWeight((short)20);
		condFormatNtPlnd.setFromCol(10);
		condFormatNtPlnd.setToCol(-1);
		condFormatNtPlnd.setOperator(ComparisonOperator.EQUAL);
		condFormatNtPlnd.setCondValue("-"); //Tick
		condFormatNtPlnd.setSymbolStr("N");
		condFormatNtPlnd.setIdentfier("Notplan");
		condFormatNtPlnd.setBgColor(new RGB(135,206,235));
        condFormats.add(condFormatNtPlnd);
        
        XLConditionalFormats condFormatAbsent = new XLConditionalFormats();			
        condFormatAbsent.setFontColor(new RGB(0,0,0)); //red font
        condFormatAbsent.setFontName(XLConditionalFormats.FONT_DEFAULT);
        condFormatAbsent.setFontHeightPoint((short)14);
        condFormatAbsent.setFontBoldWeight((short)20);
        condFormatAbsent.setFromCol(10);
        condFormatAbsent.setToCol(-1);
        condFormatAbsent.setOperator(ComparisonOperator.EQUAL);
        condFormatAbsent.setCondValue("A"); //Tick
        condFormatAbsent.setSymbolStr("A");
        condFormatAbsent.setIdentfier("Absent");
        condFormatAbsent.setBgColor(new RGB(255,0,0));
        condFormats.add(condFormatAbsent);
        
        XLConditionalFormats condFormatLeave = new XLConditionalFormats();			
        condFormatLeave.setFontColor(new RGB(0,0,0)); //red font
        condFormatLeave.setFontName(XLConditionalFormats.FONT_DEFAULT);
        condFormatLeave.setFontHeightPoint((short)14);
        condFormatLeave.setFontBoldWeight((short)20);
        condFormatLeave.setFromCol(10);
        condFormatLeave.setToCol(-1);
        condFormatLeave.setOperator(ComparisonOperator.EQUAL);
        condFormatLeave.setCondValue("L"); //Tick
        condFormatLeave.setSymbolStr("L");
        condFormatLeave.setIdentfier("Leave");
        condFormatLeave.setBgColor(new RGB(255,255,0));
        condFormats.add(condFormatLeave);
        
        XLConditionalFormats condFormatDuty = new XLConditionalFormats();			
        condFormatDuty.setFontColor(new RGB(0,0,0)); //red font
        condFormatDuty.setFontName(XLConditionalFormats.FONT_DEFAULT);
        condFormatDuty.setFontHeightPoint((short)14);
        condFormatDuty.setFontBoldWeight((short)20);
        condFormatDuty.setFromCol(10);
        condFormatDuty.setToCol(-1);
        condFormatDuty.setOperator(ComparisonOperator.EQUAL);
        condFormatDuty.setCondValue("D"); //Tick
        condFormatDuty.setSymbolStr("D");
        condFormatDuty.setIdentfier("OnDuty");
        condFormatDuty.setBgColor(new RGB(255,192,203));
        condFormats.add(condFormatDuty);
		
        
        XLConditionalFormats condFormatWeek = new XLConditionalFormats();			
        condFormatWeek.setFontColor(new RGB(0,0,0)); //red font
        condFormatWeek.setFontName(XLConditionalFormats.FONT_DEFAULT);
        condFormatWeek.setFontHeightPoint((short)14);
        condFormatWeek.setFontBoldWeight((short)20);
        condFormatWeek.setFromCol(10);
        condFormatWeek.setToCol(-1);
        condFormatWeek.setOperator(ComparisonOperator.EQUAL);
        condFormatWeek.setCondValue("W"); //Tick
        condFormatWeek.setSymbolStr("W");
        condFormatWeek.setIdentfier("Weeklyoff");
        condFormatWeek.setBgColor(new RGB(128,128,128));
        condFormats.add(condFormatWeek);
        excelUtils.setCondFormats(condFormats);
        
		return excelUtils.writeToExcel(rs,format,2,1,0 );//Change for Excel 10 Feb
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}
@Override
public Workbook getmomAttReportExcelDHQ(CommonFilter commonFilter,
	JSONObject colmodel, String format) throws Exception {
// TODO Auto-generated method stub
ResultSet rs = null;
   try{
	
	rs =   getMomAttReportDHQ(commonFilter);
	CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
	ExcelUtils excelUtils = new ExcelUtils(colmodel);
    List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
    
    XLConditionalFormats condFormat = new XLConditionalFormats();			
	condFormat.setFontColor(new RGB(0,0,0)); //red font
	condFormat.setFontName("Wingdings");
	condFormat.setFontHeightPoint((short)14);
	condFormat.setFontBoldWeight((short)20);
	condFormat.setFromCol(10);
	condFormat.setToCol(-1);
	condFormat.setOperator(ComparisonOperator.EQUAL);
	condFormat.setCondValue("P"); //Tick
	condFormat.setBgColor(new RGB(0,255,0));
	//condFormat.setBgColor(new RGB(255,255,255));
	condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");//+""
	
	condFormats.add(condFormat);
	
	XLConditionalFormats condFormatNtPlnd = new XLConditionalFormats();			
	condFormatNtPlnd.setFontColor(new RGB(0,0,0)); //red font
	condFormatNtPlnd.setFontName(XLConditionalFormats.FONT_DEFAULT);
	condFormatNtPlnd.setFontHeightPoint((short)14);
	condFormatNtPlnd.setFontBoldWeight((short)20);
	condFormatNtPlnd.setFromCol(10);
	condFormatNtPlnd.setToCol(-1);
	condFormatNtPlnd.setOperator(ComparisonOperator.EQUAL);
	condFormatNtPlnd.setCondValue("-"); //Tick
	condFormatNtPlnd.setSymbolStr("N");
	condFormatNtPlnd.setIdentfier("Notplan");
	condFormatNtPlnd.setBgColor(new RGB(135,206,235));
    condFormats.add(condFormatNtPlnd);
    
    XLConditionalFormats condFormatAbsent = new XLConditionalFormats();			
    condFormatAbsent.setFontColor(new RGB(0,0,0)); //red font
    condFormatAbsent.setFontName(XLConditionalFormats.FONT_DEFAULT);
    condFormatAbsent.setFontHeightPoint((short)14);
    condFormatAbsent.setFontBoldWeight((short)20);
    condFormatAbsent.setFromCol(10);
    condFormatAbsent.setToCol(-1);
    condFormatAbsent.setOperator(ComparisonOperator.EQUAL);
    condFormatAbsent.setCondValue("A"); //Tick
    condFormatAbsent.setSymbolStr("A");
    condFormatAbsent.setIdentfier("Absent");
    condFormatAbsent.setBgColor(new RGB(255,0,0));
    condFormats.add(condFormatAbsent);
    
    XLConditionalFormats condFormatLeave = new XLConditionalFormats();			
    condFormatLeave.setFontColor(new RGB(0,0,0)); //red font
    condFormatLeave.setFontName(XLConditionalFormats.FONT_DEFAULT);
    condFormatLeave.setFontHeightPoint((short)14);
    condFormatLeave.setFontBoldWeight((short)20);
    condFormatLeave.setFromCol(10);
    condFormatLeave.setToCol(-1);
    condFormatLeave.setOperator(ComparisonOperator.EQUAL);
    condFormatLeave.setCondValue("L"); //Tick
    condFormatLeave.setSymbolStr("L");
    condFormatLeave.setIdentfier("Leave");
    condFormatLeave.setBgColor(new RGB(255,255,0));
    condFormats.add(condFormatLeave);
    
    XLConditionalFormats condFormatDuty = new XLConditionalFormats();			
    condFormatDuty.setFontColor(new RGB(0,0,0)); //red font
    condFormatDuty.setFontName(XLConditionalFormats.FONT_DEFAULT);
    condFormatDuty.setFontHeightPoint((short)14);
    condFormatDuty.setFontBoldWeight((short)20);
    condFormatDuty.setFromCol(10);
    condFormatDuty.setToCol(-1);
    condFormatDuty.setOperator(ComparisonOperator.EQUAL);
    condFormatDuty.setCondValue("D"); //Tick
    condFormatDuty.setSymbolStr("D");
    condFormatDuty.setIdentfier("OnDuty");
    condFormatDuty.setBgColor(new RGB(255,192,203));
    condFormats.add(condFormatDuty);
	
    
    XLConditionalFormats condFormatWeek = new XLConditionalFormats();			
    condFormatWeek.setFontColor(new RGB(0,0,0)); //red font
    condFormatWeek.setFontName(XLConditionalFormats.FONT_DEFAULT);
    condFormatWeek.setFontHeightPoint((short)14);
    condFormatWeek.setFontBoldWeight((short)20);
    condFormatWeek.setFromCol(10);
    condFormatWeek.setToCol(-1);
    condFormatWeek.setOperator(ComparisonOperator.EQUAL);
    condFormatWeek.setCondValue("W"); //Tick
    condFormatWeek.setSymbolStr("W");
    condFormatWeek.setIdentfier("Weeklyoff");
    condFormatWeek.setBgColor(new RGB(128,128,128));
    condFormats.add(condFormatWeek);
    excelUtils.setCondFormats(condFormats);
    
	return excelUtils.writeToExcel(rs,format,2,1,0 );//Change for Excel 10Feb - Swetha
	
   }finally{
	   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
   }
}
private ResultSet getMomAttReportDHQ(CommonFilter commonFilter) throws Exception{
	// TODO Auto-generated method stub
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
	String type=commonFilter.getType();
	String PillarId=commonFilter.getMachineId();
	String meetingtype=commonFilter.getAbnViewType();
	
	CommonMessage.debugMsg(" Inside Dao 1 :: "+type+" PillarId :: "+PillarId+" meetingtype :: "+meetingtype);
	
	if(UIUtils.isValidKeyId(type))
		condParms+="TYPE="+type+";";
		   
	if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
		   condParms+="MEETINGTYPE="+commonFilter.getAbnViewType()+";";
	
	if(UIUtils.isValidKeyId(commonFilter.getMachineId()))
		   condParms+="PILLARID="+commonFilter.getMachineId()+";";
	
	CommonMessage.debugMsg(" Inside Dao 1 :: After :: "+type+" PillarId :: After :: "+commonFilter.getMachineId());
	
	CommonMessage.debugMsg(" meetingtype :: After :: "+commonFilter.getAbnViewType());
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMATTENDANCERPTDHQ", paramValues);
	//return null;
}
private ResultSet getMomAttReport(CommonFilter commonFilter) throws Exception{
	// TODO Auto-generated method stub
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
	String type=commonFilter.getType();
	String PillarId=commonFilter.getMachineId();
	String meetingtype=commonFilter.getAbnViewType();
	
	CommonMessage.debugMsg(" Inside Dao 1 :: "+type+" PillarId :: "+PillarId+" meetingtype :: "+meetingtype);
	
	if(UIUtils.isValidKeyId(type))
		condParms+="TYPE="+type+";";
		   
	if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
		   condParms+="MEETINGTYPE="+commonFilter.getAbnViewType()+";";
	
	if(UIUtils.isValidKeyId(commonFilter.getMachineId()))
		   condParms+="PILLARID="+commonFilter.getMachineId()+";";
	
	CommonMessage.debugMsg(" Inside Dao 1 :: After :: "+type+" PillarId :: After :: "+commonFilter.getMachineId());
	
	CommonMessage.debugMsg(" meetingtype :: After :: "+commonFilter.getAbnViewType());
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMATTENDANCERPT", paramValues);
	//return null;
}

private ResultSet getMomeetingAttRptReport(CommonFilter commonFilter) throws Exception 
 {
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	paramValues.add(condParms);
	paramValues.add(commonParams);
	return dbActionTemplate.dbFunctionCall("GEN_FN_MOMATTENDENCE", paramValues);

 }
@Override
public List<String[]> getAttendancemonthwise(CommonFilter commonFilter,
		String flid) throws Exception {
	// TODO Auto-generated method stub
	CommonMessage.debugMsg(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
	List<String> paramValues = new ArrayList<String>();		
	String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	CommonMessage.debugMsg(" Checking 1 ");
	//String pillarId=commonFilter.getPillarWise();
	//if(!UIUtils.isValidKeyId(flid))
		//flid="";
	
	CommonMessage.debugMsg(" Checking for Momtype"+commonFilter.getMaintMode());
	if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
		   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
	
	if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
		condParms+="PILLARID="+commonFilter.getPillarWise()+";";
	
	paramValues.add(condParms);
	paramValues.add(commonParams); 
	
	CommonMessage.debugMsg(" Checking 2 ");
	
	//List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_MOMATTDNCMONTHWISERPT", paramValues);
	
	List<String[]> dataList= fnCallApi.callFunction("GEN_FN_MOMATTDNCMONTHWISERPT_SB", paramValues,3,true);
	
	if( commonFilter.getViewClick() == 'Y'){
		String totalCnt = paramValues.get(0); 
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger ){
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
		
	}
	
	CommonMessage.debugMsg(" Checking 3 ");
	return dataList;

}

@Override
public List<String[]> getAttendance(CommonFilter commonFilter,String flid) throws Exception {
	// TODO Auto-generated method stub
	
	
		CommonMessage.debugMsg(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		String type=commonFilter.getType();
		String meetingtype=commonFilter.getMaintMode();
		
		String pillarId=commonFilter.getPillarWise();
		if(!UIUtils.isValidKeyId(flid))
			flid="";
		
		//if(!UIUtils.isValidKeyId(fromDte))
		//	fromDte="";
		//if(!UIUtils.isValidKeyId(toDte))
		//	toDte="";
		//if(!UIUtils.isValidKeyId(frommonth))
			//frommonth="";
		//if(!UIUtils.isValidKeyId(tomonth))
			//tomonth="";
		
		//FROMMONTH="+frommonth+";TOMONTH="+tomonth+";";
		//condParms+="FLID="+flid+";";
		

		if(UIUtils.isValidKeyId(type))
			condParms+="TYPE="+type+";";
			   
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
			   condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		
		
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		CommonMessage.debugMsg("The Param Values Are:::"+paramValues);
		
		//List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_MOMATTENDANCERPT", paramValues);
		
		List<String[]> dataList = fnCallApi.callFunction("GEN_FN_MOMATTENDANCERPT_SB", paramValues,3,true);
		//List<String[]> dataList = fnCallApi.callFunction("GEN_FN_MOMATTENDENCE_SB", paramValues,3,true);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		return dataList;
	
}
@Override
public List<String[]> getAttendanceDHQ(CommonFilter commonFilter,String flid) throws Exception {
	// TODO Auto-generated method stub
	
	
		CommonMessage.debugMsg(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		String type=commonFilter.getType();
		String meetingtype=commonFilter.getMaintMode();
		
		String pillarId=commonFilter.getPillarWise();
		if(!UIUtils.isValidKeyId(flid))
			flid="";
		
		//if(!UIUtils.isValidKeyId(fromDte))
		//	fromDte="";
		//if(!UIUtils.isValidKeyId(toDte))
		//	toDte="";
		//if(!UIUtils.isValidKeyId(frommonth))
			//frommonth="";
		//if(!UIUtils.isValidKeyId(tomonth))
			//tomonth="";
		
		//FROMMONTH="+frommonth+";TOMONTH="+tomonth+";";
		//condParms+="FLID="+flid+";";
		

		if(UIUtils.isValidKeyId(type))
			condParms+="TYPE="+type+";";
			   
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
			   condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		
		
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		CommonMessage.debugMsg("The Param Values Are:::"+paramValues);
		
		//List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_MOMATTENDANCERPTDHQ", paramValues);
		
		List<String[]> dataList= fnCallApi.callFunction("GEN_FN_MOMATTENDANCERPTDHQ_SB", paramValues,3,true);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		return dataList;
	
}

@Override
public List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) throws Exception {// TODO Auto-generated method stub
		List<String> params = new ArrayList<String>();
		String sql = GenTlMommstSql.momGridVidtor(commonFilter,MasterKeyid,shift,date,flid,type,pillarid,recall);
		List<String[]> operator = dbActionTemplate.getDataList(sql, params);
	    return operator;
		
}

@Override
public GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors) throws Exception {
	// TODO Auto-generated method stub
	
	CommonMessage.debugMsg("Visitor:::Dao impl");
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	GenTlVisitorsSql genTlVisitorsSql = new GenTlVisitorsSql();// contains dbtable,field names, Field types and related sqls  of master table
	GenTlMommst newGenTlMommst=new GenTlMommst();	
	
		CommonMessage.debugMsg("Visitor:::Dao impl 2");	
		CommonMessage.debugMsg("Master Keyid:: "+newGenTlMommst.getMomsKeyid());
		newGenTlVisitors.setVisiKeyid(dbActionTemplate.getSequenceNumber(GenTlVisitorsSql.TBL_GEN_TL_VISITORS,10,"VISI", "MMYY", "Y")); // set the sequnce number 
		sqls.add(GenTlVisitorsSql.getInsertSql(genTlVisitorsSql.getVisiDbFields(), newGenTlVisitors.getSaveArray())); // add insert sql for master table		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
	return newGenTlVisitors;
}

	@Override
	public GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception {
	// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:"+visitorkey);
		GenTlVisitors newGenTlVisitors = new GenTlVisitors();
		String sql = GenTlVisitorsSql.getMomsFrmDataSql1();
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { visitorkey };
		newGenTlVisitors.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newGenTlVisitors.getVisiKeyid());
		return  newGenTlVisitors;
	}
	
	@Override
	public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors) throws Exception 
	{
		List<String> sqls = new ArrayList<String>();
		GenTlVisitorsSql genTlVisitorsSql = new GenTlVisitorsSql();
		sqls.add(GenTlVisitorsSql.getUpdateSql(genTlVisitorsSql.getVisiDbFields(), newGenTlVisitors.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
			
		return newGenTlVisitors;
	}

	@Override
	public GenTlMommst updateApl(String dtlKeyid, String aplKeyid)
			throws Exception {
		GenTlMommst genTlMommst=new GenTlMommst();
		String sql=GenTlMomdtlSql.getUpdateApl(dtlKeyid,aplKeyid);
		
		CommonMessage.debugMsg("Update action plan : "+sql);
		dbActionTemplate.executeStatement(sql);
		return genTlMommst;
	}

	@Override
	public List<String[]> FillMomAttGridData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlMommstSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public List<String[]> getfilldetaildata(String momKeyId, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Inside Dao Impl :: 1 ");
		
		StringBuilder sql = new StringBuilder();
		/*
		 * sql.
		 * append("  SELECT momdetails, kink_indicatorname, apld_actionplan, empm_name,APLD_TARGETDATE,status FROM GEN_TL_TPMPILLARMST,( "
		 * ); sql.
		 * append("  SELECT momd_keyid,momd_discussion_type momtype,momd_pillar mompillar, momd_discussion_details momdetails,kink_keyid,KINK_INDICATORNAME, MOMD_ACTIONPLAN_ID , "
		 * ); sql.
		 * append("  apld_actionplan,empm_name,TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') AS APLD_TARGETDATE ,DECODE(APLD_STATUS,'P','PENDING','C','COMPLETED') as status "
		 * ); sql.
		 * append("  FROM gen_tl_mommst,gen_tl_momdtl,gen_tl_actionplanmst,gen_tl_actionplandtl, gen_tl_employeemst, "
		 * ); sql.
		 * append("  (SELECT LISTAGG(kink_keyid,',' ) WITHIN GROUP (ORDER BY kink_keyid) kink_keyid,LISTAGG(kink_indicatorname,',' ) "
		 * ); sql.
		 * append("  WITHIN GROUP (ORDER BY kink_indicatorname) kink_indicatorname,MOKP_MOMD_KEYID MOKP_MOMD_KEYID "
		 * ); sql.
		 * append("  FROM gen_tl_mom_kpi_link,kpi_tl_indicator WHERE mokp_kink_keyid = kink_keyid(+)GROUP BY MOKP_MOMD_KEYID) "
		 * ); sql.
		 * append("  WHERE moms_keyid = momd_moms_keyid AND moms_keyid = aplm_masterrefid(+) AND momd_keyid = APLM_DETAILREFID(+) "
		 * ); sql.append("  AND momd_keyid = MOKP_MOMD_KEYID(+) AND MOMD_MOMS_KEYID= '"
		 * +momKeyId+"' and apld_aplm_keyid (+)= aplm_keyid and empm_keyid (+)  =apld_responsibility ) "
		 * ); //sql.append(" AND MOMD_MOMS_KEYID= '"+momKeyId+"' )");
		 * sql.append(" WHERE TPMP_KEYID (+)= mompillar ORDER BY momd_keyid  ");
		 */
		//Swetha
		sql.append(" SELECT momdetails, kink_indicatorname, apld_actionplan, empm_name,APLD_TARGETDATE,status FROM GEN_TL_TPMPILLARMST RIGHT OUTER JOIN ( ");
		sql.append(" SELECT momd_keyid,momd_discussion_type momtype,momd_pillar mompillar, momd_discussion_details momdetails,kink_keyid,KINK_INDICATORNAME, MOMD_ACTIONPLAN_ID , ");
		sql.append(" apld_actionplan,empm_name,TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') AS APLD_TARGETDATE ,CASE WHEN APLD_STATUS='P' THEN 'PENDING' WHEN APLD_STATUS='C' THEN 'COMPLETED' END as status ");
		sql.append(" FROM gen_tl_mommst INNER JOIN gen_tl_momdtl ON moms_keyid = momd_moms_keyid LEFT OUTER JOIN gen_tl_actionplanmst ON moms_keyid = aplm_masterrefid LEFT OUTER JOIN gen_tl_actionplandtl ON momd_keyid = APLM_DETAILREFID AND apld_aplm_keyid = aplm_keyid LEFT OUTER JOIN gen_tl_employeemst ON empm_keyid = apld_responsibility LEFT OUTER JOIN ");
		sql.append(" (SELECT STRING_AGG(kink_keyid::TEXT,',' ORDER BY kink_keyid) kink_keyid,STRING_AGG(kink_indicatorname,',' ORDER BY kink_indicatorname) kink_indicatorname,MOKP_MOMD_KEYID MOKP_MOMD_KEYID ");
		sql.append(" FROM gen_tl_mom_kpi_link LEFT OUTER JOIN kpi_tl_indicator ON mokp_kink_keyid = kink_keyid GROUP BY MOKP_MOMD_KEYID) kpi_data ON momd_keyid = MOKP_MOMD_KEYID ");
		sql.append(" WHERE MOMD_MOMS_KEYID= '"+momKeyId+"' ) subquery ON TPMP_KEYID = mompillar ORDER BY momd_keyid ");
		CommonMessage.debugMsg(" Checking details data :: "+sql.toString());
		
		List<String[]>  momdetaildata  = dbActionTemplate.getDataList(sql.toString());
	    return momdetaildata;
	    
	}

	@Override
	public List<String[]> getfillactnplndata(String momKeyId, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Inside Dao Impl :: 2 ");
		
		StringBuilder sql = new StringBuilder();
		/*
		 * sql.
		 * append("  select apld_actionplan,empm_name,APLD_TARGETDATE,decode(APLD_STATUS,'P','PENDING','C','COMPLETED') "
		 * ); sql.
		 * append("  from gen_tl_actionplandtl,gen_tl_employeemst where empm_keyid=apld_responsibility "
		 * ); sql.
		 * append("  and apld_aplm_keyid=(select aplm_keyid from gen_tl_actionplanmst where APLM_MASTERREFID='"
		 * +momKeyId+"') ");
		 */
		//Swetha changes
		sql.append(" SELECT apld_actionplan,empm_name,APLD_TARGETDATE,CASE WHEN APLD_STATUS='P' THEN 'PENDING' WHEN APLD_STATUS='C' THEN 'COMPLETED' END ");
		sql.append(" FROM gen_tl_actionplandtl INNER JOIN gen_tl_employeemst ON empm_keyid=apld_responsibility ");
		sql.append(" WHERE apld_aplm_keyid=(SELECT aplm_keyid FROM gen_tl_actionplanmst WHERE APLM_MASTERREFID='"+momKeyId+"') ");
		CommonMessage.debugMsg(" Checking actnpln data :: "+sql.toString());
		
		//List<String[]>  actnplndata  = dbActionTemplate.getDataList(sql.toString());
	    //return actnplndata;
		return null;
	}

	@Override
	public List<String[]> getfillmstdata(String momKeyId, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("Inside Dao Impl :: 3 ");
		
		StringBuilder sql = new StringBuilder();
		//Swetha CHanges
		sql.append(" SELECT MOMS_MEETINGTITLE,CASE WHEN MOMS_MEETINGTYPE='J' THEN 'JH' WHEN MOMS_MEETINGTYPE='D' THEN 'DMT' WHEN MOMS_MEETINGTYPE='P' THEN 'PILLAR' WHEN MOMS_MEETINGTYPE='FIP' THEN 'FI PROJECT' WHEN MOMS_MEETINGTYPE='DEC' THEN 'DEPT EHS COMMITTEE' WHEN MOMS_MEETINGTYPE='CEC' THEN 'CENTRAL EHS COMMITTEE' WHEN MOMS_MEETINGTYPE='O' THEN 'OTHERS' WHEN MOMS_MEETINGTYPE='PD' THEN 'PRODUCTION MEETING' END,TPMP_CODE, ");
		sql.append(" TO_CHAR(MOMS_DATE,'DD-MON-YYYY') MOMS_DATE,MOMS_AGENDA,MOMS_SAFETYTALK,MOMS_REMARKS,functionalloc,MOMS_MEETINGNO ");
		sql.append(" FROM gen_tl_mommst LEFT OUTER JOIN gen_tl_tpmpillarmst ON MOMS_PILLARID=TPMP_KEYID INNER JOIN GEN_VW_FNLN ON MOMS_FLID=fnln_keyid WHERE MOMS_KEYID='"+momKeyId+"' ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		
		List<String[]>  mstrdata  = dbActionTemplate.getDataList(sql.toString());
	    return mstrdata;
	}

	@Override
	public List<String[]> getfillexternalData(String momKeyId, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT VISI_VISITORNAME,VISI_PURPOSE ");
		sql.append("  FROM GEN_TL_VISITORS ,GEN_TL_MOMMST WHERE  VISI_MOMS_KEYID = MOMS_KEYID ");
		sql.append("  AND VISI_MOMS_KEYID = '"+momKeyId+"' ");
		
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		
		List<String[]>  externalData  = dbActionTemplate.getDataList(sql.toString());
	    return externalData;

	}
	
	@Override
	public List<String[]> getfillattdanceData(String momKeyId, String flid)
			throws Exception {
		// TODO Auto-generated method stub - Swetha changes
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT empm_keyid, Employee, EmployeeCode,STRING_AGG(ROLENAME,',' ORDER BY ROLENAME),attendancename ");
		sql.append(" FROM (SELECT DISTINCT empm_keyid, empm_name Employee,empm_code EmployeeCode, COALESCE(m.role_name,orl.role_name) AS ROLENAME, ");
		sql.append(" CASE WHEN moma_attandance='A' THEN 'ABSENT' WHEN moma_attandance='L' THEN 'LEAVE' WHEN moma_attandance='P' THEN 'PRESENT' WHEN moma_attandance='D' THEN 'ON-DUTY' WHEN moma_attandance='W' THEN 'WEEKLY-OFF' END attendancename ");
		sql.append(" FROM gen_tl_employeemst INNER JOIN gen_tl_momattendance ON moma_employeeid = empm_keyid INNER JOIN GEN_TL_MOMMST ON MOMA_MOMS_KEYID = MOMS_KEYID INNER JOIN gen_mv_flidhierarchy ON MOMS_FLID = FLID ");
		sql.append(" LEFT OUTER JOIN GEN_TL_FNLNROLETEAM ON moma_employeeid = FRT_EMPM_KEYID AND MOMS_FLID = FRT_FNLN_KEYID ");
		sql.append(" LEFT OUTER JOIN ADM_tl_rolemst m ON FRT_ROLE_KEYID = m.ROLE_KEYID ");
		sql.append(" LEFT OUTER JOIN (SELECT DISTINCT FRT_EMPM_KEYID AS OTHER_EMP,FRT_ROLE_KEYID AS OTHER_ROLE FROM GEN_TL_FNLNROLETEAM ");
		sql.append(" WHERE FRT_FNLN_KEYID <> '"+flid+"' ) other_roles ON OTHER_EMP = moma_employeeid ");
		sql.append(" LEFT OUTER JOIN ADM_tl_rolemst orl ON orl.role_keyid = OTHER_ROLE ");
		sql.append(" WHERE MOMS_FLID ='"+flid+"' AND MOMA_MOMS_KEYID ='"+momKeyId+"' ) subquery ");
		sql.append(" GROUP BY empm_keyid, Employee, EmployeeCode,attendancename ");
		sql.append(" ORDER BY Employee ");
		CommonMessage.debugMsg(" Checking mst data :: "+sql.toString());
		
		CommonMessage.debugMsg("sql.toString()"+sql.toString());
		
/*		String countsql = CommonFiltersql.countSql(sql.toString(), commonFilter.getGridFilter());
        CommonMessage.debugMsg("countsql " + countsql);
        String cntStr = dbActionTemplate.getSingleValue(countsql);
        CommonMessage.debugMsg("cntStr " + cntStr);
        int count = Integer.parseInt(cntStr);
        CommonMessage.debugMsg("count " + count);
        commonFilter.setTotalRecordCnt(count);

      GridParams gridParams = new GridParams();
      gridParams.setFromRow(commonFilter.getFromRow());
      gridParams.setToRow(commonFilter.getToRow());
      CommonMessage.debugMsg("From Row  :" +commonFilter.getFromRow());
      CommonMessage.debugMsg("To Row  :" +commonFilter.getToRow());
     
      String oSql =  CommonFiltersql.addPaginationParams(sql.toString(), gridParams);
      CommonMessage.debugMsg("osql " + oSql);
   CommonMessage.debugMsg("sql get selected employee"+sql.toString());
   List<String[]> dataList =  dbActionTemplate.getDataList(oSql.toString());
   CommonMessage.debugMsg("getEmpList :" +dataList.size());
//    CommonMessage.debugMsg("osql  :" +oSql);
  
   if( commonFilter.getViewClick() == 'Y'){
       String totalCnt = cntStr;//paramValues.get(0);
     CommonMessage.debugMsg("totalCnt...."+totalCnt);
       boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
       if(  isInteger ){
           commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
       }
   }
  
   return dataList;
}*/
		List<String[]>  attdanceData  = dbActionTemplate.getDataList(sql.toString());
	    return attdanceData;
	}

	@Override
	public List<String[]> FillRoleDatainGrid(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlMommstSql.selectRoleData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	@Override
	public List<String[]> fillagendadata(String flid, String momdate)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlMommstSql.selectAgendaData(flid,momdate);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId, String flid)throws Exception{
		String sql = GenTlMommstSql.getMomAttendanceEmpMailIdSql();
		//Object [] args = {flid,momKeyId};
		
		Object [] args = {momKeyId};
		CommonMessage.debugMsg("Printing key id "+Arrays.toString(args));
		List<String []> data= dbActionTemplate.getDataList(sql,args);
		return data;
	}

	@Override
	public List<String[]> getMomReleatedFileManager(String momKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		String sql = GenTlMommstSql.selectgetMomReleatedFileManager(momKeyId);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	public List<String[]> getAttendancemonthwiseCount(CommonFilter commonFilter, String flid) throws Exception {
		CommonMessage.debugMsg(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg(" Checking 1 ");
		//String pillarId=commonFilter.getPillarWise();
		//if(!UIUtils.isValidKeyId(flid))
			//flid="";
		
		CommonMessage.debugMsg(" Checking for Momtype"+commonFilter.getMaintMode());
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
			condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		CommonMessage.debugMsg(" Checking 2 ");
		CommonMessage.debugMsg(" chart 2 ");
		List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST1.GEN_FN_MOMATTDNCMONTHWISECNT2", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		CommonMessage.debugMsg(" Checking 3 ");
		return dataList;

	}

	@Override
	public Workbook getMomCountExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getMomAttendanceCountData(CommonFilter commonFilter)
			throws Exception {
		CommonMessage.debugMsg(" Inside DaoImpl :: commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg(" Checking 1 ");
		//String pillarId=commonFilter.getPillarWise();
		//if(!UIUtils.isValidKeyId(flid))
			//flid="";
		
		CommonMessage.debugMsg(" Checking for Momtype"+commonFilter.getMaintMode());
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
			condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		CommonMessage.debugMsg(" Checking 2 ");
		
		List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST1.GEN_FN_MOMATTDNCMONTHWISECNT2", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		CommonMessage.debugMsg(" Checking 3 ");
		return dataList;
	}
	@Override
	public List<String[]> getNewAttendance(CommonFilter commonFilter,String flid) throws Exception {
		// TODO Auto-generated method stub
			List<String> paramValues = new ArrayList<String>();		
			String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			String type=commonFilter.getType();
			String meetingtype=commonFilter.getMaintMode();
			CommonMessage.debugMsg("Dao Meeting Type::"+meetingtype);
			String pillarId=commonFilter.getPillarWise();
			if(!UIUtils.isValidKeyId(flid))
				flid="";

			if(UIUtils.isValidKeyId(type))
				condParms+="TYPE="+type+";";
				   
			//if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
				//  condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
			    if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			    	condParms+="MEETINGTYPE="+meetingtype+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
				   condParms+="PILLARID="+commonFilter.getPillarWise()+";";
			
			CommonMessage.debugMsg("The Conditional Params DaoImpl"+condParms);
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams); 
			CommonMessage.debugMsg("The Param Values Are:::"+paramValues);
			
			List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_NEWMOMATTENDANCERPT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
				
			}
			
			return dataList;
	}
	
	public Workbook getnewmomAttReportExcel(
			JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getNewMomAttReport(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
	        List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
	        
	        XLConditionalFormats condFormat = new XLConditionalFormats();			
			condFormat.setFontColor(new RGB(0,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(10);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("P"); //Tick
			condFormat.setBgColor(new RGB(0,255,0));
			//condFormat.setBgColor(new RGB(255,255,255));
			condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");//+""
			
			condFormats.add(condFormat);
			
			XLConditionalFormats condFormatNtPlnd = new XLConditionalFormats();			
			condFormatNtPlnd.setFontColor(new RGB(0,0,0)); //red font
			condFormatNtPlnd.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatNtPlnd.setFontHeightPoint((short)14);
			condFormatNtPlnd.setFontBoldWeight((short)20);
			condFormatNtPlnd.setFromCol(10);
			condFormatNtPlnd.setToCol(-1);
			condFormatNtPlnd.setOperator(ComparisonOperator.EQUAL);
			condFormatNtPlnd.setCondValue("-"); //Tick
			condFormatNtPlnd.setSymbolStr("N");
			condFormatNtPlnd.setIdentfier("Notplan");
			condFormatNtPlnd.setBgColor(new RGB(135,206,235));
	        condFormats.add(condFormatNtPlnd);
	        
	        XLConditionalFormats condFormatAbsent = new XLConditionalFormats();			
	        condFormatAbsent.setFontColor(new RGB(0,0,0)); //red font
	        condFormatAbsent.setFontName(XLConditionalFormats.FONT_DEFAULT);
	        condFormatAbsent.setFontHeightPoint((short)14);
	        condFormatAbsent.setFontBoldWeight((short)20);
	        condFormatAbsent.setFromCol(10);
	        condFormatAbsent.setToCol(-1);
	        condFormatAbsent.setOperator(ComparisonOperator.EQUAL);
	        condFormatAbsent.setCondValue("A"); //Tick
	        condFormatAbsent.setSymbolStr("A");
	        condFormatAbsent.setIdentfier("Absent");
	        condFormatAbsent.setBgColor(new RGB(255,0,0));
	        condFormats.add(condFormatAbsent);
	        
	        XLConditionalFormats condFormatLeave = new XLConditionalFormats();			
	        condFormatLeave.setFontColor(new RGB(0,0,0)); //red font
	        condFormatLeave.setFontName(XLConditionalFormats.FONT_DEFAULT);
	        condFormatLeave.setFontHeightPoint((short)14);
	        condFormatLeave.setFontBoldWeight((short)20);
	        condFormatLeave.setFromCol(10);
	        condFormatLeave.setToCol(-1);
	        condFormatLeave.setOperator(ComparisonOperator.EQUAL);
	        condFormatLeave.setCondValue("L"); //Tick
	        condFormatLeave.setSymbolStr("L");
	        condFormatLeave.setIdentfier("Leave");
	        condFormatLeave.setBgColor(new RGB(255,255,0));
	        condFormats.add(condFormatLeave);
	        
	        XLConditionalFormats condFormatDuty = new XLConditionalFormats();			
	        condFormatDuty.setFontColor(new RGB(0,0,0)); //red font
	        condFormatDuty.setFontName(XLConditionalFormats.FONT_DEFAULT);
	        condFormatDuty.setFontHeightPoint((short)14);
	        condFormatDuty.setFontBoldWeight((short)20);
	        condFormatDuty.setFromCol(10);
	        condFormatDuty.setToCol(-1);
	        condFormatDuty.setOperator(ComparisonOperator.EQUAL);
	        condFormatDuty.setCondValue("D"); //Tick
	        condFormatDuty.setSymbolStr("D");
	        condFormatDuty.setIdentfier("OnDuty");
	        condFormatDuty.setBgColor(new RGB(255,192,203));
	        condFormats.add(condFormatDuty);
			
	        
	        XLConditionalFormats condFormatWeek = new XLConditionalFormats();			
	        condFormatWeek.setFontColor(new RGB(0,0,0)); //red font
	        condFormatWeek.setFontName(XLConditionalFormats.FONT_DEFAULT);
	        condFormatWeek.setFontHeightPoint((short)14);
	        condFormatWeek.setFontBoldWeight((short)20);
	        condFormatWeek.setFromCol(10);
	        condFormatWeek.setToCol(-1);
	        condFormatWeek.setOperator(ComparisonOperator.EQUAL);
	        condFormatWeek.setCondValue("W"); //Tick
	        condFormatWeek.setSymbolStr("W");
	        condFormatWeek.setIdentfier("Weeklyoff");
	        condFormatWeek.setBgColor(new RGB(128,128,128));
	        condFormats.add(condFormatWeek);
	        excelUtils.setCondFormats(condFormats);
	        
			return excelUtils.writeToExcel(rs,format,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
			
	private ResultSet getNewMomAttReport(CommonFilter commonFilter) throws Exception{
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		String type=commonFilter.getType();
		String PillarId=commonFilter.getMachineId();
		String meetingtype=commonFilter.getAbnViewType();
		
		CommonMessage.debugMsg(" Inside Dao 1 :: "+type+" PillarId :: "+PillarId+" meetingtype :: "+meetingtype);
		
		if(UIUtils.isValidKeyId(type))
			condParms+="TYPE="+type+";";
			   
		if(UIUtils.isValidKeyId(commonFilter.getAbnViewType()))
			   condParms+="MEETINGTYPE="+commonFilter.getAbnViewType()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getMachineId()))
			   condParms+="PILLARID="+commonFilter.getMachineId()+";";
		
		CommonMessage.debugMsg(" Inside Dao 1 :: After :: "+type+" PillarId :: After :: "+commonFilter.getMachineId());
		
		CommonMessage.debugMsg(" meetingtype :: After :: "+commonFilter.getAbnViewType());
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_NEWMOMATTENDANCERPT", paramValues);
		//return null;
	}

	public String updateIsmail(String momid,String val) throws Exception {
		// TODO Auto-generated method stub
		if(val.equals("false"))
		{	
		CommonMessage.debugMsg("inside if");	
		String sql="update GEN_TL_MOMMST set MOMS_ISMAILTRIG='Y' where MOMS_KEYID='"+momid+"'";
		dbActionTemplate.executeStatement(sql.toString());
		}
		else{
			CommonMessage.debugMsg("inside else");	
			String sql="update GEN_TL_MOMMST set MOMS_ISMAILTRIG='N' where MOMS_KEYID='"+momid+"'";
			dbActionTemplate.executeStatement(sql.toString());
		}
		CommonMessage.debugMsg("Success");
		return "success";
	}

	@Override
	public String getmailidTrigger(String mailid) throws Exception {
		// TODO Auto-generated method stub
		 String sql="select MOMS_ISMAILTRIG from GEN_TL_MOMMST WHERE MOMS_KEYID='"+mailid+"'";
		 return dbActionTemplate.getSingleValue(sql.toString());
	}

	@Override
	public String PillarSelectedData(CommonFilter commonfilter)
			throws Exception {
		// TODO Auto-generated method stub
		// String sql="select MOMS_KEYID from GEN_TL_MOMMST WHERE TO_CHAR (MOMS_DATE, 'DD-Mon-YYYY')='"+commonfilter.getActwise()+"' AND MOMS_FLID='"+commonfilter.getFlid()+"' AND MOMS_PILLARID='"+commonfilter.getAbnDetect()+"'";
		 String sql="select MOMS_KEYID from GEN_TL_MOMMST WHERE TO_CHAR (MOMS_DATE, 'DD-Mon-YYYY')='"+commonfilter.getActwise()+"' AND MOMS_FLID='"+commonfilter.getFlid()+"' AND MOMS_PILLARID='"+commonfilter.getAbnDetect()+"' AND MOMS_MEETINGTYPE='"+commonfilter.getType()+"'";
		 
		 CommonMessage.debugMsg("sql"+sql);
		 return dbActionTemplate.getSingleValue(sql.toString());
	}
	@Override
	public List<String[]> getAttendancemonthwiseDHQ(CommonFilter commonFilter, String flid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Inside DaoImpl :: "+flid+" commonFilter ::  "+commonFilter);
		List<String> paramValues = new ArrayList<String>();		
		String condParms=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		CommonMessage.debugMsg(" Checking 1 ");
		//String pillarId=commonFilter.getPillarWise();
		//if(!UIUtils.isValidKeyId(flid))
			//flid="";
		
		CommonMessage.debugMsg(" Checking for Momtype"+commonFilter.getMaintMode());
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		if(UIUtils.isValidKeyId(commonFilter.getPillarWise()))
			condParms+="PILLARID="+commonFilter.getPillarWise()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		CommonMessage.debugMsg(" Checking 2 ");
		//sWETHA MODIFIED - gen_fn_momattdncmonthwisedhq->GEN_FN_MOMATTDNCMONTHWISEDHQIS 
		//List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_MOMATTDNCMONTHWISEDHQIS", paramValues);
		List<String[]> dataList= fnCallApi.callFunction("GEN_FN_MOMATTDNCMONTHWISEDHQIS_SB",paramValues,3,true);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			
		}
		
		CommonMessage.debugMsg(" Checking 3 ");
		return dataList;
	}

	@Override
	public Workbook MomeetingMonthwiseExportExcelDHQ(CommonFilter commonFilter, JSONObject colmodel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		  ResultSet rs = null;
		   try{
			
			rs =   getMomeetingReportMonthwiseResultSetDHQ(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,1,0 );//Change for Excel 10 Feb Swetha
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getMomeetingReportMonthwiseResultSetDHQ(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		if(UIUtils.isValidKeyId(commonFilter.getMaintMode()))
			   condParms+="MEETINGTYPE="+commonFilter.getMaintMode()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//sWETHA MODIFIED - gen_fn_momattdncmonthwisedhq -> GEN_FN_MOMATTDNCMONTHWISEDHQIS
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_MOMATTDNCMONTHWISEDHQIS", paramValues);
	}
	
}