package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import sun.swing.UIAction;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KznTlMstDao;
import com.akranta.tpm.dao.sql.BdmTlYycountermeasurelinkSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.dao.sql.GenTlDocupdatesSql;
import com.akranta.tpm.dao.sql.GenTlWorkflowInfoSql;
import com.akranta.tpm.dao.sql.KznTlBestdtlSql;
import com.akranta.tpm.dao.sql.KznTlEvaluationdtlSql;
import com.akranta.tpm.dao.sql.KznTlEvaluationmstSql;
import com.akranta.tpm.dao.sql.KznTlGraphdataSql;
import com.akranta.tpm.dao.sql.KznTlHdmstSql;
import com.akranta.tpm.dao.sql.KznTlLosslinkSql;
import com.akranta.tpm.dao.sql.KznTlMstSql;
import com.akranta.tpm.dao.sql.KznTlPillarlinkSql;
import com.akranta.tpm.dao.sql.QtmTlCustcomplaintdtlSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlGraphdata;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.model.KznTlLosslink;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.KznTlPillarlink;
import com.akranta.tpm.model.QtmTlCustcomplaintdtl;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class KznTlMstDaoImpl implements KznTlMstDao {


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	//private KznTlMstSql kznTlMstSql ;
	//private KznTlPillarlinkSql kznTlPillarlinkSql;
	//private KznTlLosslinkSql kznTlLosslinkSql;
	//private KznTlHdmstSql kznTlHdmstSql ;
	//private KznTlGraphdataSql kznTlGraphdataSql;
	//private BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql;
	//private GenTlDocupdatesSql genTlDocupdatesSql;
	//private QtmTlCustcomplaintdtlSql qtmTlCustcomplaintdtlSql;
	public KznTlMstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		/*kznTlMstSql = new KznTlMstSql(); // contains dbtable,field names, Field types and related sqls  of master table 
		kznTlPillarlinkSql = new KznTlPillarlinkSql();
		kznTlLosslinkSql = new KznTlLosslinkSql();
		kznTlHdmstSql= new KznTlHdmstSql();
		kznTlGraphdataSql = new KznTlGraphdataSql();
		bdmTlYycountermeasurelinkSql = new BdmTlYycountermeasurelinkSql();
		genTlDocupdatesSql = new GenTlDocupdatesSql();
		qtmTlCustcomplaintdtlSql = new QtmTlCustcomplaintdtlSql();*/
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void KznTlMstDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public KznTlMst create(KznTlMst kznTlMst,BdmTlYycountermeasurelink bdmTlYycountermeasurelink,GenTlDocupdates genTlDocupdates,QtmTlCustcomplaintdtl qtmTlCustcomplaintdtl) 	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try{
			
			BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql=new BdmTlYycountermeasurelinkSql();
			KznTlMstSql kznTlMstSql =new KznTlMstSql();
			GenTlDocupdatesSql genTlDocupdatesSql =new GenTlDocupdatesSql();
			KznTlGraphdataSql kznTlGraphdataSql =new KznTlGraphdataSql();
			KznTlGraphdata kznTlGraphdata=new KznTlGraphdata();
			QtmTlCustcomplaintdtlSql qtmTlCustcomplaintdtlSql =new QtmTlCustcomplaintdtlSql();
			KznTlHdmstSql kznTlHdmst=new KznTlHdmstSql();
		    /////add this Code//////
			String elementId = kznTlMst.getKznmElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,KznTlMstSql.TBL_KZN_TL_MST);

		 	/// change ths line///
			kznTlMst.setKznmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,20,"KZ","YY","Y"));
			
//			kznTlMst.setKznmKeyid(dbActionTemplate.getSequenceNumber(KznTlMstSql.TBL_KZN_TL_MST,20,"KZ","YY","Y")); // set the sequnce number
			CommonMessage.debugMsg("inside daoimpl  query      "+kznTlMst.getKznmKzbnkeyid());
			sqls.add(KznTlMstSql.getInsertSql(kznTlMstSql.getKznmDbFields(), kznTlMst.getSaveArray())); // add insert sql for master table
			setPillarLinkSqls(sqls,kznTlMst);//Pillar Links
			setLossLinkSqls(sqls,kznTlMst);//Loss Link
			CommonMessage.debugMsg(" Inside Fill Values For Graph Data :: 22 "+kznTlMst.getKznmKzbnkeyid());
			fillValues(kznTlMst,kznTlGraphdata);
			sqls.add(KznTlGraphdataSql.getInsertSql(kznTlGraphdataSql.getKzgdDbFields(),kznTlGraphdata.getSaveArray()));
			//setGraphDataSqls(sqls,kznTlMst);//Graph Data
			if(kznTlMst.getKznmKeyid()!=null){
				CommonMessage.debugMsg(" KAIZEN KEYID "+kznTlMst.getKznmKzbnkeyid());
				sqls.add(KznTlMstSql.updateHd(kznTlMst.getKznmKzbnkeyid()));
				CommonMessage.debugMsg(" END OF UPDATE HD TABLE");
			}
			
			CommonMessage.debugMsg("inside daoimpl   "+sqls);
			if(bdmTlYycountermeasurelink!=null)
			{
				CommonMessage.debugMsg("inside daoimpl  1  "+sqls);
				bdmTlYycountermeasurelink .setYycmCountermsrid(kznTlMst.getKznmKeyid());
				bdmTlYycountermeasurelink.setYycmRefdoctype("KZN");
				bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteYYSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),bdmTlYycountermeasurelink.getSaveArray()));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteCounterMeasureLinkSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),kznTlMst.getKznmKeyid(),"KZN"));
				sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
			}
			if(genTlDocupdates != null)
			{
				CommonMessage.debugMsg("inside daoimpl doc   "+sqls);
				genTlDocupdates.setDcupDetailid(kznTlMst.getKznmKeyid());
				sqls.add(GenTlDocupdatesSql.getUpdateDocSql(genTlDocupdatesSql.getDcupDbFields(), genTlDocupdates.getSaveArray()));
			}
			if(qtmTlCustcomplaintdtl!=null)
			{
				CommonMessage.debugMsg("inside daoimpl cust "+sqls);
				qtmTlCustcomplaintdtl.setCucdKaizenno(kznTlMst.getKznmKeyid());
				sqls.add(QtmTlCustcomplaintdtlSql.getUpdateKznNoSql(qtmTlCustcomplaintdtlSql.getCucdDbFields(), qtmTlCustcomplaintdtl.getSaveArray()));
						
			}
				
			CommonMessage.debugMsg("sql dao       "+sqls.toString());
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kznTlMst;
	}

	private void fillValues(KznTlMst kznTlMst, KznTlGraphdata kznTlGraphdata) {
		// TODO Auto-generated method stub
		
	    CommonMessage.debugMsg(" Inside Fill Values For Graph Data :: 11 ");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		CommonMessage.debugMsg(" Inside Fill Values For Graph Data :: 22 "+kznTlMst.getKznmKeyid());
		
		if((kznTlGraphdata.getKzgdKaizenid()==null))
			kznTlGraphdata.setKzgdKaizenid(kznTlMst.getKznmKeyid());
		
		if((kznTlGraphdata.getKzgdAfterdata()==null))
			kznTlGraphdata.setKzgdAfterdata("2");
		
		if((kznTlGraphdata.getKzgdBeforedata()==null))
			kznTlGraphdata.setKzgdBeforedata("0");
		
		if((kznTlGraphdata.getKzgdDatemonthyear()==null))
			kznTlGraphdata.setKzgdDatemonthyear(dateTime);
		
		if((kznTlGraphdata.getKzgdCharttype()==null))
			kznTlGraphdata.setKzgdCharttype("L");
		
		if((kznTlGraphdata.getKzgdCreatedon()==null))
			kznTlGraphdata.setKzgdCreatedon(dateTime);
		
	}

	public KznTlMst update(KznTlMst kznTlMst,BdmTlYycountermeasurelink bdmTlYycountermeasurelink,GenTlDocupdates genTlDocupdates)	throws Exception { 
		KznTlMstSql kznTlMstSql =new KznTlMstSql();
		List<String> sqls = new ArrayList<String>();
		try {
			BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql= new BdmTlYycountermeasurelinkSql();
			GenTlDocupdatesSql genTlDocupdatesSql=new GenTlDocupdatesSql();
			CommonMessage.debugMsg("in update "+kznTlMst.getKznmKeyid());
			KznTlGraphdataSql kznTlGraphdataSql =new KznTlGraphdataSql();
			KznTlGraphdata kznTlGraphdata=new KznTlGraphdata();

			//kznTlMst.setKznmActive("Y");
			
			sqls.add(KznTlMstSql.getUpdateSql(kznTlMstSql.getKznmDbFields(), kznTlMst.getSaveArray()));
			CommonMessage.debugMsg("in update "+kznTlMst.getKznmKeyid());
			setPillarLinkSqls(sqls,kznTlMst);//Pillar Links
			CommonMessage.debugMsg("in update after setPillarLinkSqls ");
			setLossLinkSqls(sqls,kznTlMst);//Loss Link
			CommonMessage.debugMsg("in update after setLossLinkSqls");
			
			CommonMessage.debugMsg(" Inside Fill Values For Graph Data :: 22 "+kznTlMst.getKznmKeyid());

			fillValues(kznTlMst,kznTlGraphdata);
			sqls.add(KznTlGraphdataSql.getInsertSql(kznTlGraphdataSql.getKzgdDbFields(),kznTlGraphdata.getSaveArray()));
			
			
			//setGraphDataSqls(sqls,kznTlMst);//Graph Data
			CommonMessage.debugMsg("in update after graph");
			if(bdmTlYycountermeasurelink!=null)
			{	
				bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
				bdmTlYycountermeasurelink.setYycmRefdoctype("KZN");
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteYYSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),bdmTlYycountermeasurelink.getSaveArray()));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteCounterMeasureLinkSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),kznTlMst.getKznmKeyid(),"KZN"));
				sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
			}
			if(genTlDocupdates != null)
			{
				sqls.add(GenTlDocupdatesSql.getUpdateDocSql(genTlDocupdatesSql.getDcupDbFields(), genTlDocupdates.getSaveArray()));
			}
			
			CommonMessage.debugMsg(" inside main  update  "+sqls.toString());
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kznTlMst;
	}
	
	public KznTlMst delete(KznTlMst kznTlMst)throws Exception 
	{   //
		List<String> sqls = new ArrayList<String>();
		GenTlAllmoduleimgfileSql genTlAllmoduleimgfileSql = new GenTlAllmoduleimgfileSql();
		BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql =new BdmTlYycountermeasurelinkSql();
		KznTlPillarlinkSql kznTlPillarlinkSql=new KznTlPillarlinkSql();
		KznTlLosslinkSql kznTlLosslinkSql=new KznTlLosslinkSql();
		KznTlGraphdataSql kznTlGraphdataSql=new KznTlGraphdataSql();
		KznTlHdmstSql kznTlHdmstSql = new KznTlHdmstSql();
		KznTlMstSql kznTlMstSql =new KznTlMstSql();
		KznTlEvaluationmstSql kznTlEvaluationmstSql=new KznTlEvaluationmstSql();
		KznTlEvaluationdtlSql kznTlEvaluationdtlSql=new KznTlEvaluationdtlSql();
		KznTlBestdtlSql kznTlBestdtlSql= new KznTlBestdtlSql();
		
		try {
			String kznplkaizenid=kznTlMst.getKznmKeyid();
			sqls.add(BdmTlYycountermeasurelinkSql.getDeleteCounterMeasureLinkSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),kznTlMst.getKznmKeyid(),"KZN"));
			sqls.add(KznTlHdmstSql.getDeleteKznSql(kznTlHdmstSql.getKhdmDbFields(), kznplkaizenid));
			sqls.add(KznTlPillarlinkSql.getKznDeleteSql(kznTlPillarlinkSql.getKzplDbFields(), kznplkaizenid));
			sqls.add(KznTlLosslinkSql.getKznDeleteSql(kznTlLosslinkSql.getKzllDbFields(), kznplkaizenid));
			sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql(genTlAllmoduleimgfileSql.getImflDbFields(),kznTlMst.getSaveArray()));
			sqls.add("Delete from " +kznTlEvaluationdtlSql.TBL_KZN_TL_EVALUATIONDTL+" where KEDL_KZNM_KEYID='"+kznplkaizenid+"'");
			sqls.add("Delete from " +kznTlEvaluationmstSql.TBL_KZN_TL_EVALUATIONMST+" where KEVA_KAIZENID='"+kznplkaizenid+"'");
			sqls.add("Delete from " +kznTlBestdtlSql.TBL_KZN_TL_BESTDTL+" where KZBD_KAIZENID='"+kznplkaizenid+"'");
			sqls.add(KznTlGraphdataSql.getDeleteKznSql(kznTlGraphdataSql.getKzgdDbFields(),kznplkaizenid));
			
			//select * from KZN_TL_BESTDTL where KZBD_KAIZENID='KZ1400000081'; 

			//DELETE FROM KZN_TL_EVALUATIONMST WHERE KEVA_KAIZENID='KZN0001029';    KznTlEvaluationmst

			
			
			sqls.add(KznTlMstSql.getDeleteSql(kznTlMstSql.getKznmDbFields(),kznTlMst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kznTlMst;
	}
	public KznTlMst deleteKzn(KznTlMst newKznTlMst) throws Exception{
		  List<String> sql=new ArrayList<String>(); 
	  try
	   {
		  KznTlMstSql kznTlMstSql =new KznTlMstSql();  
		  sql.add(kznTlMstSql.getDeleteSql(kznTlMstSql.getKznmDbFields(),newKznTlMst.getSaveArray()));
	      dbActionTemplate.executeStatements(sql);
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	   return newKznTlMst;
	}
	@Override
	public KznTlMst select(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectSql();
		CommonMessage.debugMsg("kznKeyid in dao impl="+kznKeyid);
		CommonMessage.debugMsg("String sql="+sql);
		KznTlMst kznTlMst = new KznTlMst();  
		Object args [] = new Object [] { kznKeyid };
		kznTlMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return kznTlMst;
	}
	
	public List<String []> getKaizenReport(CommonFilter commonFilter) throws Exception
	{//Checking Now 
		try
		{
			
			CommonMessage.debugMsg("Inside daoimpl");
			
			List<String> paramValues =null;
			
			CommonMessage.debugMsg(" commonFilter.getKAIZEN() :: "+commonFilter.getKAIZEN());
			if(UIUtils.isValidKeyId(commonFilter.getKAIZEN()))
			    paramValues = getFilterEMPKZNParamValues(commonFilter);
			else
				paramValues = getFilterParamValues(commonFilter);
			
			for(String param: paramValues){
				CommonMessage.debugMsg("--" +param) ;
				/*if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
					param +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
				paramValues.add(param);	*/
			}
	
			CommonMessage.debugMsg("Params: " +paramValues) ;
			
			//List<String[]> kaizenReport = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENVIEW_1", paramValues);
			List<String[]> kaizenReport = fnCallApi.callFunction("KZN_FN_KAIZENVIEW_1_SB", paramValues,3,true);
			CommonMessage.debugMsg("Inside daoimpl 5: "+kaizenReport.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}			}
			CommonMessage.debugMsg("Test --->" +kaizenReport.size());
			
			
			return kaizenReport;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String []> getAllKaizenDeleteRpt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues =null;	
			if(UIUtils.isValidKeyId(commonFilter.getKAIZEN()))
			    paramValues = getFilterEMPKZNParamValues(commonFilter);
			else
				paramValues = getFilterParamValues(commonFilter);	
			List<String[]> kaizenReport = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENDELETE", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}			
				}			
			return kaizenReport;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	private List<String> getFilterEMPKZNParamValues(CommonFilter commonFilter) {
		// TODO Auto-generated method stub

		  List<String> paramValues=new ArrayList<String>();

		    CommonMessage.debugMsg(" Employee Wise Kaizen :: "); 
		  
		    CommonMessage.debugMsg(" getMainGroup :: "+commonFilter.getMainGroup()); 
		    String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			String EmpillarKZNUtilize=commonFilter.getUtiliseFuture();
			if(UIUtils.isValidKeyId(EmpillarKZNUtilize)){
		        condParms +="UTILISEFUTURE="+EmpillarKZNUtilize+";";
			}
			if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
			      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
			
			CommonMessage.debugMsg(" Inside daoimpl :: Checking :: "+commonFilter.getMPWorthy());
			//List<String> paramValues = new ArrayList<String>();
			String empKeyId=null;
			CommonMessage.debugMsg(" After :: ");
			//if(commonFilter.getMPWorthy()!="Y")
			     //empKeyId=commonFilter.getEmployee().getId();
			
			
			CommonMessage.debugMsg(" DAO FLID "+commonFilter.getFlid()+" KAIZEN "+commonFilter.getKAIZEN());
			if(UIUtils.isValidKeyId(commonFilter.getKAIZEN()))
			{
				CommonMessage.debugMsg(" From Dao "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
				condParms = condParms + "FLID="+commonFilter.getFlid()+";";
				/*condParms = condParms + "FROMDATE="+commonFilter.getFromDate()+";";
				condParms = condParms + "TODATE="+commonFilter.getToDate()+";";
				condParms = condParms + "FROMMONTH="+commonFilter.getFromMonth()+";";
				condParms = condParms + "TOMONTH="+commonFilter.getToMonth()+";";*/
			
			}
			
			//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			
			condParms = condParms + "EMPLOYEEKEYID=" + empKeyId + ";" ;
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
	}

	@Override
	public KznTlMst deleteKznHd(String khdmkeyIds) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();
		KznTlHdmstSql kznTlHdmstSql=new KznTlHdmstSql();
		try {
			sqls.add(KznTlHdmstSql.getDeleteKznHDSql(kznTlHdmstSql.getKhdmDbFields(), khdmkeyIds));
			dbActionTemplate.executeStatements(sqls);
			//return kznTlHdmst;
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return null;	
	}

	@Override
	public List<GenTlAllmoduleimgfile> saveKznImg(KznTlMst kznTlMst,KaizenFormBean kaizenFormBean)throws Exception 
	{
		// TODO Auto-generated method stub
		try
		{
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = kznTlMst.getAllmoduleimgfile();
			List<String> imflImageType = new ArrayList<String>();
			
			CommonMessage.debugMsg("kznTlMst.getAllmoduleimgfile()"+kznTlMst.getAllmoduleimgfile());
			
		
			if(kaizenFormBean.getAfterImage()!=null && !(kaizenFormBean.getAfterImage().equals("")))
			{	
				if(kaizenFormBean.getAfterImage().equals("after"))
					imflImageType.add("AFT");
			}
			if(kaizenFormBean.getResultImage()!=null && !(kaizenFormBean.getResultImage().equals("")))
			{
				if(kaizenFormBean.getResultImage().equals("result"))
					imflImageType.add("RES");
			}
			if(kaizenFormBean.getPresentImage()!=null && !(kaizenFormBean.getPresentImage().equals("")))
			{
				if(kaizenFormBean.getPresentImage().equals("present"))
					imflImageType.add("PRE");
			}
			if(kaizenFormBean.getPresentImage()!=null && !(kaizenFormBean.getPresentImage().equals("")))
			{
				if(kaizenFormBean.getPresentImage().equals("benefit"))
					imflImageType.add("BEN");
			}
			for(String kznimageType:imflImageType)
			{
				CommonMessage.debugMsg("kznimageType =="+kznimageType);
				if(kznimageType!=null && !kznimageType.trim().equals(""))
				{	
					//sqls.add(GenTlAllmoduleimgfileSql.getDeleteImgSql(kznTlMst.getKznmKeyid(),"KZN",imflImageType));
					sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());	
					Object [] delValue	= { kznTlMst.getKznmKeyid(),"KZN",kznimageType};
					int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					valueList.add(delValue);
					dataTypes.add(delTypes);
				}
			}
			
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
			{
				CommonMessage.debugMsg("FILE NAME:"+genTlAllmoduleimgfile.getImflFilename());
				CommonMessage.debugMsg("BLOB LENGTH:"+genTlAllmoduleimgfile.getImflBloblength());
				CommonMessage.debugMsg("Image Type= "+genTlAllmoduleimgfile.getImflImagetype());
				CommonMessage.debugMsg("Ref Doc Type= "+genTlAllmoduleimgfile.getImflRefdoctype());
				CommonMessage.debugMsg("REF KEYID "+genTlAllmoduleimgfile.getImflRefkeyid());
				
				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
					continue;
				
	            CommonMessage.debugMsg("Before INSERT");
				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
				
				
				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				java.sql.Timestamp  timeStamp = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStampfromPgtimestamp(genTlAllmoduleimgfile.getImflModifiedon()); 
				Object [] insValues = { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype(),
				genTlAllmoduleimgfile.getImflBlobimage(),genTlAllmoduleimgfile.getImflBloblength(),
				genTlAllmoduleimgfile.getImflFilename(),genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
				
				valueList.add(delValue);
				valueList.add(insValues);
		
				dataTypes.add(delTypes);
				dataTypes.add(insDataType);
			
			}
			
			CommonMessage.debugMsg("sqls ="+sqls);
			CommonMessage.debugMsg("valueList ="+valueList);
			CommonMessage.debugMsg("dataTypes ="+dataTypes);
//			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
			dbActionTemplate.saveByteFile(sqls, valueList, dataTypes);
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return null;
	}


	public List<GenTlAllmoduleimgfile> getKznImage(List<GenTlAllmoduleimgfile> kznImgList) throws NoDataFoundException, Exception
	{
		CommonMessage.debugMsg("Inside Dao impl Imgh");
		
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:kznImgList)
		{
			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			String condSql1 = " AND IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND " + condSql ;
			
			String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid(),condSql);
			
			if( fileName != null )
			{
				
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
				
				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
			
				String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
				genTlAllmoduleimgfile.setImflFilename(imgFileName);
				
				CommonMessage.debugMsg(" fileName " + fileNamePath);
				
//				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
				dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
			}
			
		}
		return genTlAllmoduleimgList;
	}
	
	private void setPillarLinkSqls(List<String>sqls, KznTlMst kznTlMst )
	{
		KznTlPillarlinkSql  kznTlPillarlinkSql=new KznTlPillarlinkSql();
		CommonMessage.debugMsg("in pillar   ");
		List <KznTlPillarlink> pillarLinkList = kznTlMst.getPillarLink();
		CommonMessage.debugMsg("in pillar size    "+pillarLinkList.size());
		if(pillarLinkList != null && pillarLinkList.size()>0)
		{
			for(KznTlPillarlink pillarLnk:pillarLinkList)
			{
				CommonMessage.debugMsg(pillarLnk.getDbMode()+ "   in pillar  "+kznTlMst.getKznmKeyid());
				pillarLnk.setKzplKaizenid(kznTlMst.getKznmKeyid());
				if( pillarLnk.getDbMode().equals("INSERT"))
					sqls.add(KznTlPillarlinkSql.getInsertSql(kznTlPillarlinkSql.getKzplDbFields(),pillarLnk.getSaveArray()));
				else if( pillarLnk.getDbMode().equals("UPDATE"))
					sqls.add(KznTlPillarlinkSql.getUpdateSql(kznTlPillarlinkSql.getKzplDbFields(),pillarLnk.getSaveArray()));
				else if( pillarLnk.getDbMode().equals("DELETE"))
					sqls.add(KznTlPillarlinkSql.getDeleteSql(kznTlPillarlinkSql.getKzplDbFields(),pillarLnk.getSaveArray()));
				CommonMessage.debugMsg("Pillar sql   "+sqls.toString());
			}
		}
	}

	@Override
	public List<String[]> getAllPillarLink(String kznId) throws Exception 
	{
		try
		{
			String count = "0";
			if(CommonFunctions.isValidKeyId(kznId))
			{
				count = dbActionTemplate.getSingleValue("select count(*) from KZN_TL_PILLARLINK where kzpl_kaizenid='"+kznId+"'");
				CommonMessage.debugMsg("Count : "+count);
			}
			String sql = KznTlMstSql.getKznPillarLossRecallSql(count);
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(kznId);
			paramValues.add(kznId);
			CommonMessage.debugMsg("paramValues in dao impl for KAIZEN "+paramValues);
			List<String[]> PillarLossList = dbActionTemplate.getDataList(sql,paramValues);
			CommonMessage.debugMsg("PillarLossList "+PillarLossList.size());
			return PillarLossList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	@Override
	public List<String[]> getkznImprvCategory(List<String> pillarId) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> ImprvCategoryList=null;
		try
		{
			String sql = KznTlMstSql.getKznImprvCategorySql();
			CommonMessage.debugMsg("String sql="+sql);
			List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql,params);
			return ImprvCategoryList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getStudent dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getMultiSelectLoss() throws Exception {
		// TODO Auto-generated method stub
		List<String[]> lossList=null;
		try
		{
			String sql = KznTlMstSql.getKznLossDtlSql();
			CommonMessage.debugMsg("String sql="+sql);
			
			lossList = dbActionTemplate.getDataList(sql);
			return lossList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getStudent dao impl"+e.getMessage());
		}
		return null;
	}
	
	private void setLossLinkSqls(List<String>sqls, KznTlMst kznTlMst )
	{
		List <KznTlLosslink> lossLinkList = kznTlMst.getLossLink();
		KznTlLosslinkSql  kznTlLosslinkSql=new KznTlLosslinkSql();
		//CommonMessage.debugMsg("Inside Dao impl lossLinkList="+lossLinkList.size());
		CommonMessage.debugMsg("in loss    ");
		if(lossLinkList != null && lossLinkList.size()>0)
		{
			int count=0;
			//sqls.add(KznTlLosslinkSql.getKznDeleteSql(kznTlLosslinkSql.getKzllDbFields(),kznTlMst.getKznmKeyid()));
			for(KznTlLosslink lossLnk:lossLinkList)
			{
				
				lossLnk.setKzllKaizenid(kznTlMst.getKznmKeyid());
				CommonMessage.debugMsg("Lossflag  "+lossLnk.getSelectLossFlag());
				if( lossLnk.getSelectLossFlag().equals("INSERT"))
					sqls.add(KznTlLosslinkSql.getInsertSql(kznTlLosslinkSql.getKzllDbFields(),lossLnk.getSaveArray()));
				else if( lossLnk.getSelectLossFlag().equals("UPDATE")){
					if(count==0){
						sqls.add(KznTlLosslinkSql.getDeleteSql(kznTlLosslinkSql.getKzllDbFields(),lossLnk.getSaveArray()));
						count++;
					}
					sqls.add(KznTlLosslinkSql.getInsertSql(kznTlLosslinkSql.getKzllDbFields(),lossLnk.getSaveArray()));
				}
				else if( lossLnk.getSelectLossFlag().equals("DELETE"))
					sqls.add(KznTlLosslinkSql.getDeleteSql(kznTlLosslinkSql.getKzllDbFields(),lossLnk.getSaveArray()));
			}
		}
	}

	@Override
	public List<String[]> getkznHDScanTbl(String kaizenId,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> kznHDScanList=null;
		try
		{
			
		/*	if(UIUtils.isValidKeyId(kaizenId)){
				String sql = KznTlMstSql.getKznHDScanDtlsSql(kaizenId);
			 CommonMessage.debugMsg("String sql="+sql);
				kznHDScanList = dbActionTemplate.getDataList(sql);
			}*/
		//String hdKey=KznTlMstSql.getHdKey(kaizenId);
			String hdkey=commonFilter.getMainkeyid();
			CommonMessage.debugMsg("hdkey id ="+hdkey);
			if(UIUtils.isValidKeyId(commonFilter.getMainkeyid())){
				String hdKeyid=commonFilter.getMainkeyid();
				String sql = KznTlMstSql.getKznHDScanStatusSql(hdKeyid);
				 CommonMessage.debugMsg("String sql="+sql);
					kznHDScanList = dbActionTemplate.getDataList(sql);
			}
			else{
				String sql = KznTlMstSql.getKznHDScanDtlsSql(kaizenId);
				 CommonMessage.debugMsg("String kaizen sql="+sql);
					kznHDScanList = dbActionTemplate.getDataList(sql);
		}
			/*CommonMessage.debugMsg("kaizen id"+kaizenId);
			String sql = KznTlMstSql.getKznHDScanDtlsSql(kaizenId);
			CommonMessage.debugMsg("String sql="+sql);
			kznHDScanList = dbActionTemplate.getDataList(sql);*/
			return kznHDScanList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in kznHDScan dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getkznWhyWhyData(String wwmsKeyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = KznTlMstSql.getKznYYDtlsSql(wwmsKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			//dbActionTemplate.executeStatements(sql);
			/*List<String > paramValues = new ArrayList<String>();
			paramValues.add(wwmsKeyid);
			*/
			List<String []> yyDatas = dbActionTemplate.getDataList(sql);
			
			//List<String []> yyDatas = dbActionTemplate.processFunctionCalls( sql,paramValues);	
			//kznHDScanList = dbActionTemplate.getDataList(sql);
			return yyDatas;
			
			
			/*CommonMessage.debugMsg("Inside the dao impl");
			CommonMessage.debugMsg("ID:"+wwmsKeyid);
			KznTlMst kznTlMst=new KznTlMst();
			String sql = KznTlMstSql.getKznYYDtlsSql();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { wwmsKeyid };
			kznTlMst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
			CommonMessage.debugMsg("DAO Query:"+kznTlMst.getKznmKeyid());
			return  kznTlMst;*/
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in kznWhyWhy dao impl"+e.getMessage());
		}
		return null;
	}

	/**---------------------------------- FOR GRAPH DATA --------------------------**/
	@Override
	public List<String[]> getGraphData(String kznKeyid, String fromMonth,String toMonth) throws Exception {
		try
		{
			List<String> paramValues=new ArrayList<String>();
			
			paramValues.add(kznKeyid);
			paramValues.add(fromMonth);
			paramValues.add(toMonth);
			paramValues.add("Y");
			return dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_GETKAIZENRESULT", paramValues);
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Kaizen Graph Data Details dao impl"+e.getMessage());
		}
		return null;
	}
	
	private void setGraphDataSqls(List<String> sqls, KznTlMst kznTlMst) //
	{
		CommonMessage.debugMsg("in graph  ::  "+ kznTlMst.getGraphData().size());
		List <KznTlGraphdata> graphDataList = kznTlMst.getGraphData();
		KznTlGraphdataSql kznTlGraphdataSql =new KznTlGraphdataSql();
		//KznTlGraphdata kznTlGraphdata-new KznTlGraphdata();
		if(graphDataList != null && graphDataList.size()>0)
		{  CommonMessage.debugMsg("in graph  :: 11 ");
		   
			int i=0;
			for(KznTlGraphdata kznTlGraphdata:graphDataList)
			{   CommonMessage.debugMsg("in graph  :: 22 ");
				CommonMessage.debugMsg("in graph    "+kznTlMst.getKznmKeyid());
				kznTlGraphdata.setKzgdKaizenid(kznTlMst.getKznmKeyid());
				if(i==0)
				{
					CommonMessage.debugMsg("in graph    "+i);
					sqls.add(KznTlGraphdataSql.getDeleteSql(kznTlGraphdataSql.getKzgdDbFields(),kznTlGraphdata.getSaveArray()));
					CommonMessage.debugMsg("in graph    "+sqls.toString());
					i++;
				}
				sqls.add(KznTlGraphdataSql.getInsertSql(kznTlGraphdataSql.getKzgdDbFields(),kznTlGraphdata.getSaveArray()));
				CommonMessage.debugMsg("in graph    "+sqls.toString());
			}
		}
		//sqls.add(KznTlGraphdataSql.getInsertSql(kznTlGraphdataSql.getKzgdDbFields(),kznTlGraphdata.getSaveArray()));
	}
	/**-----------------------------------------------------------------------------------------------------------**/

	/**----------------------------FOR KAIZEN COMPLETION ----------------------------------------------------- **/

	@Override
	public List<String[]> getKaizenCompletedDtls(CommonFilter commonFilter)throws Exception
	{
		// TODO Auto-generated method stub
		try	
		{	
List<String> paramValues = new ArrayList<String>();
			
			
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);		
			paramValues.add(commonParams);
			List<String[]> result= dbActionTemplate.processFunctionCalls("KZN_FN_KZNCOMPLETE", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt:"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					CommonMessage.debugMsg("commonFilter.setTotalRecordCnt:"+commonFilter.getTotalRecordCnt());
				}
				
		 }
		
			
			return result;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Kaizen Completed Details dao impl"+e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<String[]> getKznHdCellMch(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = KznTlMstSql.getKznHDCellMchSql(kznKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String []>kznHdCellMchList = dbActionTemplate.getDataList(sql);
			
			return kznHdCellMchList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in kznHdCellMch dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getKaizenHDView(CommonFilter commonFilter) throws Exception 
	{
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues=new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);		
		paramValues.add(commonParams);
		
		List<String[]> result= dbActionTemplate.processFunctionCalls("KZN_FN_KZNHDSUBQUERY", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt:"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					CommonMessage.debugMsg("commonFilter.setTotalRecordCnt:"+commonFilter.getTotalRecordCnt());
				}
				
		 }
			return result;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Kaizen Completed Details dao impl"+e.getMessage());
		}
		return null;
	}

	
	@Override
	public List<String[]> updateKznComplete(String kznmKeyid, String khdmKeyid,String remarks, String completedBy, String completedDate,String modifiedDate)
			throws Exception {
		try
		{
			String sql = KznTlMstSql.updateCompleteDetailsSql(kznmKeyid,khdmKeyid,remarks,completedBy,completedDate,modifiedDate);
			CommonMessage.debugMsg("String sql="+sql);
			List<String []>kznHdCellMchList = dbActionTemplate.getDataList(sql);
			return kznHdCellMchList;
		}
		
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Update Complete "+e.getMessage());
		}
		return null;
		
	}

	@Override
	public KznTlMst updateKznCompletion(KznTlMst kznTlMst) throws Exception {
		// TODO Auto-generated method stub
		KznTlMstSql kznTlMstSql =new KznTlMstSql();
		List<String> sqls = new ArrayList<String>();
		try {
			String modifiedDate=com.akranta.tpm.utils.CommonFunctions.getDate();
			
			sqls.add(KznTlMstSql.getUpdateCompletionSql(kznTlMstSql.getKznmDbFields(), kznTlMst.getSaveArray(),modifiedDate));
			
			//if(isHDPresent==true)
			/*kznTlHdmst.setKhdmCompletedby(kznTlMst.getKznmCompletedid());
			kznTlHdmst.setKhdmCompleteddate(kznTlMst.getKznmCompleteddate());
			kznTlHdmst.setKhdmRemarks(kznTlMst.getKznmRemarks());
			sqls.add(KznTlMstSql.getUpdateHDMSTSql(kznTlHdmstSql.getKhdmDbFields(), kznTlHdmst.getSaveArray(),modifiedDate));
			*/
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kznTlMst;
	}
	
	@Override
	public KznTlHdmst updateKznHDCompletion(KznTlHdmst kznTlHdmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		KznTlHdmstSql kznTlHdmstSql =new KznTlHdmstSql();
		try
		{
			String modifiedDate=com.akranta.tpm.utils.CommonFunctions.getDate();
			CommonMessage.debugMsg(" modifiedDate in dao impl"+modifiedDate);
			sqls.add(KznTlHdmstSql.getUpdateHDMSTSql(kznTlHdmstSql.getKhdmDbFields(), kznTlHdmst.getSaveArray(),modifiedDate));
			
			dbActionTemplate.executeStatements(sqls);	
			return kznTlHdmst;
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return null;
	}
	
	@Override
	public List<String[]> getResultData(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = KznTlMstSql.resultDataSql(kznKeyid);
			//String param = KznTlMstSql.resultDescriptionSql(kznKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String []>kznResultDataList = dbActionTemplate.getDataList(sql);
			return kznResultDataList;
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return null;
		
	}
	/**-----------------------------------------------------------------------------------------------------------**/

	@Override
	public List<String[]> chkForDuplicates(KznTlMst newKznTlMst)throws Exception {
		// TODO Auto-generated method stub
		try{
			if(newKznTlMst!=null)
			{
				String factId=newKznTlMst.getKznmFactoryid();
				String cellId=newKznTlMst.getKznmCellid();
				String mchId=newKznTlMst.getKznmMachineid();
				String theme=newKznTlMst.getKznmTheme();
				String bnchMrk=newKznTlMst.getKznmBenchmark();
				String target=newKznTlMst.getKznmTarget();
				String kznKeyid=newKznTlMst.getKznmKeyid();
			
				String sql = KznTlMstSql.chkForDuplicatesSql(kznKeyid,factId,cellId,mchId,theme.trim(),bnchMrk.trim(),target.trim());
				List<String []>kznChkDuplicatesList = dbActionTemplate.getDataList(sql);
				return kznChkDuplicatesList;
			}
			
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in Check Duplicates"+e.getMessage());
		}
		return null;
		
	}

	@Override
	public List<String[]> fillkznComplete(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = KznTlMstSql.getkznCompleteSql(kznKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String []>fillkznCompleteList = dbActionTemplate.getDataList(sql);
			return fillkznCompleteList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception while filling kzn complete "+e.getMessage());
		}
		return null;
		
	}

	@Override
	public KznTlMst updateDocUpdates(KznTlMst kznTlMst,String docId,String yyId) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		try
		{
			sqls.add(KznTlMstSql.getkznDocUpdatesSql(kznTlMst.getKznmKeyid(),docId));
			
			BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
			bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
			
			sqls.add(KznTlMstSql.insertYYCounterMeasureSql(bdmTlYycountermeasurelink.getYycmKeyid(),yyId,kznTlMst));
	
			
			dbActionTemplate.executeStatements(sqls);	
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception updateDocUpdate Dao impl "+e.getMessage());
		}
		return null;
		
	}

	@Override
	public Workbook kaizenRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType) throws Exception {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		   try
		   {
			   String fname="VIEW";
			   rs=getKaizenResultSet(commonFilter,fname);
			   ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			   return excelUtils.writeToExcel(rs,reportType,2,1,0 );
			
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());  
		   }
	}

	@Override
	public Workbook kaizenCompleteRptExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj,String reportType,boolean kznHd) throws Exception {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		   try
		   {
			   
			   String fnName="COMPLETION";
				if(kznHd==true)
					fnName="HDCOMPLETE";
				else
					fnName="COMPLETION";
				
			   rs=getKaizenResultSet(commonFilter,fnName);
			   ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			   return excelUtils.writeToExcel(rs,reportType,  0,0,0 );
			
		   }
		   catch(Exception e)
		   {
			   CommonMessage.debugMsg("Exception in doa impl"+e.getMessage());
		   }
		   finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		   return null;
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter){
		  List<String> paramValues=new ArrayList<String>();

		    CommonMessage.debugMsg(" getMainGroup :: "+commonFilter.getMainGroup()); 
		    String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			String EmpillarKZNUtilize=commonFilter.getUtiliseFuture();
			if(UIUtils.isValidKeyId(EmpillarKZNUtilize)){
		        condParms +="UTILISEFUTURE="+EmpillarKZNUtilize+";";
			}
			if(UIUtils.isValidKeyId(commonFilter.getJHKaizenCategory().getId()))
			      condParms +=";JHKZNCAT="+commonFilter.getJHKaizenCategory().getId()+";";
			
			CommonMessage.debugMsg(" Inside daoimpl :: Checking :: "+commonFilter.getMPWorthy());
			//List<String> paramValues = new ArrayList<String>();
			String empKeyId=null;
			CommonMessage.debugMsg(" After :: ");
			//if(commonFilter.getMPWorthy()!="Y")
			     empKeyId=commonFilter.getEmployee().getId();
			
			
			CommonMessage.debugMsg(" DAO FLID "+commonFilter.getFlid()+" KAIZEN "+commonFilter.getKAIZEN());
			if(UIUtils.isValidKeyId(commonFilter.getKAIZEN()))
			{
				CommonMessage.debugMsg(" From Dao "+commonFilter.getFromMonth()+" To "+commonFilter.getToMonth());
				condParms = condParms + "FLID="+commonFilter.getFlid()+";";
				/*condParms = condParms + "FROMDATE="+commonFilter.getFromDate()+";";
				condParms = condParms + "TODATE="+commonFilter.getToDate()+";";
				condParms = condParms + "FROMMONTH="+commonFilter.getFromMonth()+";";
				condParms = condParms + "TOMONTH="+commonFilter.getToMonth()+";";*/
			
			}
			
			//String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			
			condParms = condParms + "EMPLOYEEKEYID=" + empKeyId + ";" ;
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;

	}
	
	private ResultSet getKaizenResultSet(CommonFilter commonFilter,String funcIden) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		String sql="";
		if(funcIden.equalsIgnoreCase("COMPLETION"))
			sql = KznTlMstSql.getKaizenCompleteFunction();
		else if(funcIden.equalsIgnoreCase("HDCOMPLETE"))
			sql = KznTlMstSql.getKaizenHDCompleteFunction();
		else if(funcIden.equalsIgnoreCase("VIEW"))
			sql = KznTlMstSql.getKaizenViewFunction();
		
		return dbActionTemplate.NewdbFunctionCall2(sql, paramValues);
		 	
	}

	@Override
	public KznTlMst updateKznComplete(KznTlMst newKznTlMst) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getAllGraphMnths(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = KznTlMstSql.getkznGraphMnthsSql(kznKeyid);
			CommonMessage.debugMsg("String sql="+sql);
			List<String []>fillkzngrphMnthsList = dbActionTemplate.getDataList(sql);
			return fillkzngrphMnthsList;
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception while filling kzn graph months details "+e.getMessage());
		}
		return null;
	}
///Added for kaizen Approval added by sriram
	@Override
	public List<String[]> getAllKaizenApproval(CommonFilter commonFilter) throws Exception {
		/*StringBuffer sql= new StringBuffer();

		 sql.append("select 'Improvement Date','Functional Location','Theme','Benchmark','Target' from dual"
			+" union all"
			+" select '13-Nov-2013','ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 DECURLER UNIT1000165','DM Delivery phosphates to be checked daily once','DM Delivery phosphate <0.01ppm','30-Nov-2013' from dual");

			CommonMessage.debugMsg("sql....."+sql);
			List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
			CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
			return gridData;
		
		
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
	
			List<String[]> kaizenReport = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_PC_KAIZEN.KZN_FN_KAIZENVIEW", paramValues);
			return kaizenReport;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}*/
		
		List<String> paramValues = new ArrayList<String>();		
		//String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("test to............");
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg(paramValues +";;; Param values"); 
		//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("KZN_FN_KAIZENVIEW_1", paramValues);
		
		List<String[]> dataList =  fnCallApi.callFunction("KZN_FN_KAIZENVIEW_1_SB", paramValues,3,true);
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
	public List<String[]> getAllPiller() throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append("select 'Select','Pillar Name' from dual");
		sql.append(" union all");
		sql.append(" select '','JH' from dual");
		sql.append(" union all");
		sql.append(" select '','QM' from dual");
	    sql.append(" union all");
	    sql.append(" select '','QTPM' from dual");
	    sql.append(" union all");
	    sql.append(" select '','KK' from dual");
	    sql.append(" union all");
	    sql.append(" select '','DM' from dual");
	    sql.append(" union all");
	   	sql.append(" select '','ET' from dual");
	  	sql.append(" union all");
	    sql.append(" select '','PM' from dual");
	    sql.append(" union all");
	    sql.append(" select '','EHS' from dual");
	   CommonMessage.debugMsg("sql....."+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
		return gridData;

	}

	@Override
	public List<String[]> getAllBenifitReport(String type) throws Exception {
		StringBuffer sql= new StringBuffer();
		CommonMessage.debugMsg("type:::=  "+type);
		 sql.append("select 'BTS No.','Function Location','Kaizen No','Kaizen Idea','Prepared By','Benifit Type' from dual");
		 sql.append(" union all");
		 sql.append(" select 'BTS/000001','ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH - 123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 JOGGER UNIT10001652','KI-10019','Safety at Pallet picker zone','T SRINU-104585','No Savings' from dual");
		
		 if(!type.equals("sbu")){
			 sql.append(" union all");
			 sql.append(" select 'BTS/000002','ITC-BCM2000 /   SBU 2 /   PBU BOARD /   SBU2 /   PBU2 /   FH-123 /   FH 123 - SHEETER 1, /   10001653 /   ','KI-10020','Water Jamming elimination','T.RAMUDU-18306','Safety' from dual");
			 
			 sql.append(" union all");
			 sql.append(" select 'BTS/000003','ITC-BCM2000 / SBU 1SBU 1 / PBU 2PBU 2 / TEST SBU3SBU3 / TEST PBU2PBU3 / UTILITIESUTILITIES / 50 TPH COAL PLANT50 TPH COAL PLANT / COAL CONVEYOR - 4 (SHUTTLE CONV)10004358 ','KI-10021','Reduction of breakage of Hydrometers while handling','G PAPIREDDY','No Savings' from dual");
			 
			 sql.append(" union all");
			 sql.append(" select 'BTS/000004','ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 SLITTER UNIT10001651 ','KI-10022','mending of sharp edges from base plate of tensile strip cutter','M Vijay Bhaskar','Savings upto 5 Lacs' from dual");
			 
			 sql.append(" union all");
			 sql.append(" select 'BTS/000005','ITC-BCM2000 /   SBU 2 /   PBU BOARD /   SBU2 /   PBU2 /   FH-123 /   FH 123 - SHEETER 1, /   10001653 /   ','KI-10023','To eliminate damage of spare IGT ptg rolls','Chandramohan Reddy','No Savings' from dual");
			 
			 sql.append(" union all");
			 sql.append(" select 'BTS/000006','ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 LAYBUOY10001654 /   ','KI-10024','Template handle modification','MUJABAR RAHMAN SK','More than 5 Lacs' from dual");
	
			 sql.append(" union all");
			 sql.append(" select 'BTS/000007','ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 JOGGER UNIT10001652/   ','KI-10025','Elimination of repetitive test for Biomass GCV','APPALA SWAMY D','No Savings' from dual");
			 
			 sql.append(" union all");
			 sql.append(" select 'BTS/000008','ITC-BCM2000 /   SBU 2 /   PBU BOARD /   SBU2 /   PBU2 /   FH-123 /   FH 123 - SHEETER 1, /   10001653 /   ','KI-10026','Reduction of operating cycle distrubence and maintenance','UPENDRA CHARY K','More than 5 Lacs' from dual");
	
			 sql.append(" union all");
			 sql.append(" select 'BTS/000009','2000 /   SBU 2 /   PBU BOARD /   SBU2 /   PBU2 /   FH-123 /   FH 123 - SHEETER 1, /   10001651 /   ','KI-10027','Dryer doctor oscillation','P RAMBABU','Safety' from dual");
	
			 sql.append(" union all");
			 sql.append(" select 'BTS/000010','ITC-BCM2000 /   SBU 2 /   PBU BOARD /   SBU2 /   PBU2 /   FH-123 /   FH 123 - SHEETER 1, /   10001653 /   ','KI-10028','Rope pulley Bracket modification','PRASADA RAO B','Savings upto 5 Lacs' from dual");
	
			 sql.append(" union all");
			 sql.append(" select 'BTS/000011','ITC-BCM2000 / SBU 1SBU 1 / PBU 1PBU 1 / TEST SBU1SBU1 / TEST PBU1PBU1 / PULP MILLPULPMILL / CLO2 PLANTCLO2 PLANT / CASING MARK III SRS 1.5X1.8 P-1K-06B PT1124322619 ','KI-10029','safety in operation of gas mask','BALARAMA MURTY K','No Savings' from dual");
	
			 sql.append(" union all");
			 sql.append(" select 'BTS/000012','ITC-BCM2000 /   SBU 2 /   PBU BOARD /   SBU2 /   PBU2 /   FH-123 /   FH 123 - SHEETER 1, /   10001653 /   ','KI-10030','Calculation time reduction','SRIHARI  M','Savings upto 5 Lacs' from dual");
	
			 sql.append(" union all");
			 sql.append(" select 'BTS/000013','ITC-BCM2000 / SBU 1SBU 1 / PBU 1PBU 1 / TEST SBU1SBU1 / TEST PBU1PBU1 / PULP MILLPULPMILL / CLO2 PLANTCLO2 PLANT / LONG BRG,PN:24,MM:31FL6P22MCS6AO F/PUMP114320182 ','KI-10031','To increase life of the pH electrode','GINJUPALLI RAMU','No Savings' from dual");
	
		 }
		 CommonMessage.debugMsg("sql....."+sql);
			List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
			CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
			return gridData;
	}

	@Override
	public List<String[]> getAllAuthorization(String type) throws Exception {
					StringBuffer sql= new StringBuffer();
					sql.append("select ' ','Name','Approval Status','Remarks' from dual");
				 if("team".equals(type)){
					 sql.append(" union all");
					 sql.append(" select 'Team Leader','T SRINU-104585','Approved','' from dual");
				 }
				 else if("champion".equals(type)){
					 sql.append(" union all");
					 sql.append(" select 'Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Champion','SK SALEEM-1152','Approved','' from dual");
				 }
				 else if("dmt".equals(type)){
					 sql.append(" union all");
					 sql.append(" select 'Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Champion','SK SALEEM-1152','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'DMT Lead','K.NAGARAJU-18313','Approved','' from dual");
				 }else if("finance".equals(type)){
					 sql.append(" union all");
					 sql.append(" select 'Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Champion','SK SALEEM-1152','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'DMT Lead','K.NAGARAJU-18313','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Finance','J.SHIVA KUMAR-2580','Rejected','Remarks 1' from dual");
				 }else if("sbu".equals(type)){
					 sql.append(" union all");
					 sql.append(" select 'Team Leader','T SRINU-104585','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Champion','SK SALEEM-1152','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'DMT Lead','K.NAGARAJU-18313','Approved','' from dual");
					 sql.append(" union all");
					 sql.append(" select 'Finance','J.SHIVA KUMAR-2580','Approved','Remarks 1' from dual");
					 sql.append(" union all");
					 sql.append(" select 'SBU Head','S.S.SATPATHY-97660','Rejected','Remarks 2' from dual");
				 }	else{
				 }

				 CommonMessage.debugMsg("sql....."+sql);
			List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
			return gridData;
	}

	@Override
	public List<String[]> getBTSGridData(CommonFilter commonFilter) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENIDEASHEETBTS", paramValues);
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
	public String selectKznb(String kznbKeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectKzbnSql(kznbKeyid);
		CommonMessage.debugMsg("String sql="+sql);
		String kznKeyid =dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(kznKeyid+"kznKeyid in dao impl="+kznbKeyid);
		return kznKeyid;
	}
	@Override
	public String getElementID(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID= '"+flid+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}

	@Override
	public String getkaizenTheme(String kznbKeyid) throws Exception {
		String sql = KznTlMstSql.selectKznThemeSql(kznbKeyid);
		CommonMessage.debugMsg("String sql theme ="+sql);
		String kznTheme =dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg("kznTheme  "+kznTheme);
		return kznTheme;
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
	
		String sql = KznTlMstSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
		
	}
	
	@Override
	public List<String[]> FillTeamControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectTeamData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	@Override
	public Workbook getEmPillarReportExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getEMPillarResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	@Override
	public Workbook kaizenApprovalExportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub		
		ResultSet rs = null;
		   try{
			
			rs =   getEMPillarResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }

	
	}

	private ResultSet getEMPillarResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		//String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		if(UIUtils.isValidKeyId(commonFilter.getType())){
		    condParms +="EMPPILLAR="+commonFilter.getType()+";";
		}
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENVIEW_1", paramValues);
	}

	
	@Override
	public KznTlMst updateKaizenStatus(String status,String keyid, String nextLevel, String type, String value, String approvallevel,String mpvalue,String verifyamount) throws Exception {
		// TODO Auto-generated method stub
		KznTlMst kznTlMst=new KznTlMst();
	//	String sql=KznTlMstSql.updateKaizenStatus(status,keyid, nextLevel,type,value,approvallevel,mpvalue);
		
		//CommonMessage.debugMsg("Update action plan : "+sql);
		if(verifyamount!=null)
		{
			if(!verifyamount.equals("-"))
			{
			String  sql1= " UPDATE  KZN_TL_MST  SET KZNM_VERIFYAMOUNT ='"+verifyamount+"' WHERE KZNM_KEYID ='"+keyid+"' ";
			dbActionTemplate.executeStatement(sql1);
			}
		}
		
		if(mpvalue!=null)
		{
			if(mpvalue.equals("Y"))
			{
				String  sql1= " UPDATE  KZN_TL_MST  SET KZNM_ISWORTHFORMP ='Y' WHERE KZNM_KEYID ='"+keyid+"' ";
				dbActionTemplate.executeStatement(sql1);
			}
		}
		
		if(value!=null)
		{
			if(value.equals("Y"))
			{
				String  sql1= " UPDATE  KZN_TL_MST  SET KZNM_UTILISEFORFUTURE ='Y' WHERE KZNM_KEYID ='"+keyid+"' ";
				dbActionTemplate.executeStatement(sql1);
			}
		}
		
		
		String sql=null;
		if(UIUtils.isValidKeyId(type))
		     sql= " UPDATE  KZN_TL_MST  SET KZNM_UTILISEFORFUTURE ='"+value+"' WHERE KZNM_KEYID ='"+keyid+"' ";
		//else if(UIUtils.isValidKeyId(approvallevel))
			//sql= " UPDATE  KZN_TL_MST  SET KZNM_ISWORTHFORMP ='"+value+"' WHERE KZNM_KEYID ='"+keyid+"' ";
		else
		{   
			String wrksql=dbActionTemplate.getSingleValue("SELECT count(*) FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='"+keyid+"'");
			String bnfttype=dbActionTemplate.getSingleValue("SELECT KZNM_BENEFITTYPE FROM KZN_TL_MST where KZNM_KEYID='"+keyid+"'");
			CommonMessage.debugMsg("wrksql"+wrksql);
			
			if(!wrksql.equals("0"))
			{	
				CommonMessage.debugMsg("Inside the if i wrkflwosql");
				
				if(wrksql.equals("1"))
				{
					String apprvstatus=dbActionTemplate.getSingleValue("SELECT KZNM_STATUS FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='"+keyid+"' " +
							"AND  WRIN_WRKD_KEYID IN ('WFD0000001','WFD0000004','WFD0000007','WFD0000012')");
					
					if(apprvstatus.equals("A"))
					{	
						 sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='DMT LEADER' WHERE KZNM_KEYID='"+keyid+"' ";
								dbActionTemplate.executeStatement(sql); 
						 
					}
					else{
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='"+nextLevel+"' WHERE KZNM_KEYID='"+keyid+"' ";
							dbActionTemplate.executeStatement(sql);
					}
					
				}
				if(wrksql.equals("2"))
				{
					String apprvstatus=dbActionTemplate.getSingleValue("SELECT KZNM_STATUS FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='"+keyid+"' " +
							"AND  WRIN_WRKD_KEYID IN ('WFD0000002','WFD0000005','WFD0000008','QTM0000113')");
					
					if(apprvstatus.equals("A"))
					{	

						 if(bnfttype.equals("NS"))
						 {
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='C',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='-' WHERE KZNM_KEYID='"+keyid+"' ";
						dbActionTemplate.executeStatement(sql);
						 }
						 else{
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='PBU HEAD' WHERE KZNM_KEYID='"+keyid+"' ";
						dbActionTemplate.executeStatement(sql);
						 }
					}
					else{
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='"+nextLevel+"' WHERE KZNM_KEYID='"+keyid+"' ";
							dbActionTemplate.executeStatement(sql);
					}
					
				}
				
				if(wrksql.equals("3"))
				{
					String apprvstatus=dbActionTemplate.getSingleValue("SELECT KZNM_STATUS FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='"+keyid+"' " +
							"AND  WRIN_WRKD_KEYID IN ('QTM0000114','WFD0000009','WFD0000006')");
					
					if(apprvstatus.equals("A"))
					{	
						 if(bnfttype.equals("S")||bnfttype.equals("LE5"))
						 {
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='C',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='-' WHERE KZNM_KEYID='"+keyid+"' ";
						dbActionTemplate.executeStatement(sql);
						 }
						 else{
							  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='FINANCE' WHERE KZNM_KEYID='"+keyid+"' ";
								dbActionTemplate.executeStatement(sql);
						 }
					}
					else{
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='"+nextLevel+"' WHERE KZNM_KEYID='"+keyid+"' ";
							dbActionTemplate.executeStatement(sql);
					}
					
				}
				
				if(wrksql.equals("4"))
				{
					String apprvstatus=dbActionTemplate.getSingleValue("SELECT KZNM_STATUS FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='"+keyid+"' " +
							"AND  WRIN_WRKD_KEYID IN ('QTM0000112')");
					
					if(apprvstatus.equals("A"))
					{	
						 if(bnfttype.equals("GE5"))
						 {
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='C',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='-' WHERE KZNM_KEYID='"+keyid+"' ";
						dbActionTemplate.executeStatement(sql);
						 }
					}
					else{
						  sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+apprvstatus+"',KZNM_ISWORTHFORMP ='"+mpvalue+"',KZNM_APROV_LEVEL='"+nextLevel+"' WHERE KZNM_KEYID='"+keyid+"' ";
							dbActionTemplate.executeStatement(sql);
					}
					
				}
		   
			}
		}   
		dbActionTemplate.executeStatement(sql);
		return kznTlMst;

	}


	@Override
	public List<String[]> getKaizenEmployeeWiseMonthWise(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			
			String empKeyId=commonFilter.getEmployee().getId();
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			
			condParms = condParms + "EMPLOYEEKEYID=" + empKeyId + ";" ;
			
			CommonMessage.debugMsg("condParms---> " + condParms);

			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_EMPMONTTWISEIDENCOMP",	paramValues);

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public List<String[]> getEmployeeWiseMonthWiseKzn(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
	//		StringBuffer sb=new StringBuffer();
//			String empKeyId=commonFilter.getEmployee().getId();	
		/*	String flid=commonFilter.getFlid();
			CommonMessage.debugMsg("The Flid"+flid);
			if(UIUtils.isValidKeyId(flid)){
				sb.append(" AND flid IN (SELECT flid FROM gen_mv_flidhierarchy WHERE INSTR (parentflids || flid, '"+flid+"') >0 ");
				sb.append(" ORDER BY EMPM_NAME");
			}*/
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		//	condParms = condParms + "EMPLOYEEKEYID=" +  + ";" ;
			condParms+="DETECTEDBY="+commonFilter.getAllotedDtTo();
			CommonMessage.debugMsg("  emptype 2"+commonFilter.getEmpwiseType());
			paramValues.add(condParms +";EMPTYPE="+commonFilter.getEmpwiseType()+";");
		//	CommonMessage.debugMsg("condParms---> " + condParms);
			CommonMessage.debugMsg("The condParms:::"+condParms);
		//	paramValues.add(condParms);
			paramValues.add(commonParams);
		//	paramValues.add(flid);
		//	paramValues.add(sb.toString());
			List<String[]> dataList =null;
			//if(commonFilter.getEmpwiseType().equals("KZN")){                              
		//		dataList=dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_EMPMONTTIDENCOMP",paramValues);	
				dataList=fnCallApi.callFunction("JHN_FN_EMPMONTTIDENCOMP_SB",paramValues,3,false);	
		//	}
			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public Workbook getEmployeeWiseMonthKaizenExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String rptFormat) throws Exception {
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();
		
		String empKeyId=commonFilter.getEmployee().getId();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		
		condParms = condParms + "EMPLOYEEKEYID=" + empKeyId + ";" ;
		
		CommonMessage.debugMsg("condParms---> " + condParms);

		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("JHN_FN_EMPMONTTIDENCOMP_SB", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,3,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 		DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection()); 
		 }
	}

	@Override
	public Workbook getMonthEmployeeWiseKaizenExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String rptFormat) throws Exception {
		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();
		
		String empKeyId=commonFilter.getEmployee().getId();
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		
		condParms = condParms + "EMPLOYEEKEYID=" + empKeyId + ";" ;
		
		CommonMessage.debugMsg("condParms---> " + condParms);

		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("JHN_FN_EMPMONTTWISEIDENCOMP", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}
	
	@Override
	public List<String[]> getKznDateUpdateData(CommonFilter commonFilter)	throws Exception {
	
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			List<String[]> relatedMst = null;
			String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParam);
			paramValues.add(commonParam);
			
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("KZN_DATE_UPDATE_DATA", paramValues);
			
			
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

	@Override
	public void updateKaizenDate(String kaizenId, String kznRespid) throws Exception {
		StringBuffer sql =new StringBuffer(); 
		sql.append("UPDATE KZN_TL_MST SET KZNM_DATE = KZNM_ENDDATE,kznm_preparedid='"+kznRespid+"',kznm_createdby='"+kznRespid+"' WHERE KZNM_KEYID='"+kaizenId+"' ");//.append(kaizenId).append("'");
		CommonMessage.debugMsg("Dao sql:" +sql);
		try{
			dbActionTemplate.executeStatement(sql.toString());
			CommonMessage.debugMsg("The SQL::::"+sql.toString());
		}catch(Exception e){
			CommonMessage.debugMsg("Exception:" + e);
		}
		CommonMessage.debugMsg("UpDate Success" );
		
		
	}
	

	@Override
	public List<String[]> getKaizenEmployeeWiseMonthWiseTotal(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl EmployeeWise Kaizen Total");
			List<String> paramValues = new ArrayList<String>();
			
			String empKeyId=commonFilter.getEmployee().getId();
			CommonMessage.debugMsg("The Employee Keyid"+empKeyId);
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String modiefiedKeyid="";
			//String modiefiedKeyid=empKeyId.replaceAll("\"", "'");
			modiefiedKeyid=empKeyId.replaceAll(",", "','");
			CommonMessage.debugMsg("The modiefiedKeyid::DaoImpl:"+modiefiedKeyid);
			modiefiedKeyid="'"+modiefiedKeyid+"'";
			CommonMessage.debugMsg("The Modified key"+modiefiedKeyid);
			condParms = condParms + "EMPLOYEEKEYID=" + modiefiedKeyid + ";" ;
			CommonMessage.debugMsg("The condParms::::"+condParms);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_EMPMONTTWISEICTOTAL",paramValues);
			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Workbook getKaizenDateUpdateExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getKznDateResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		
	}
	private ResultSet getKznDateResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		//String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("KZN_DATE_UPDATE_DATA", paramValues);
	}

	@Override
	public List<String[]> getKaizenHoriaonDeploy(CommonFilter commonFilter)
			throws Exception {
		try
		{
			
			CommonMessage.debugMsg("Inside daoimpl");
			
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			for(String param: paramValues){
				CommonMessage.debugMsg("--" +param) ;
				
			}
	
			CommonMessage.debugMsg("Params: " +paramValues) ;
			
			List<String[]> kaizenHorizonDeploy = dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENHORIZONDEPLOY", paramValues);
			CommonMessage.debugMsg("Inside daoimpl 5: "+kaizenHorizonDeploy.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}			}
			CommonMessage.debugMsg("Test --->" +kaizenHorizonDeploy.size());
			
			
			return kaizenHorizonDeploy;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public KznTlMst updateKznRejRewStatus(KznTlMst kznTlMst) throws Exception {
		// TODO Auto-generated method stub

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		List<String> sqls1= new ArrayList<String>();
		//GenTlWorkflowInfo workflowinfo= new GenTlWorkflowInfo();
		StringBuffer sqlUpdtStatus = new StringBuffer();
		StringBuffer sqlWorkflowStatus=new StringBuffer();
		sqlUpdtStatus.append(" UPDATE KZN_TL_MST SET KZNM_STATUS = '"+kznTlMst.getKznmStatus()+"' " );
		sqlUpdtStatus.append(" WHERE KZNM_KEYID='"+kznTlMst.getKznmKeyid()+"' ");
        sqls.add(sqlUpdtStatus .toString());
        sqlWorkflowStatus.append(" UPDATE GEN_TL_WORKFLOW_INFO SET WRIN_STATUS ='"+kznTlMst.getKznmStatus()+"' ");
        sqlWorkflowStatus.append(" WHERE WRIN_REF_ID='"+kznTlMst.getKznmKeyid()+"' ");
        sqls1.add(sqlWorkflowStatus.toString()); 
        dbActionTemplate.executeStatements(sqls);
        dbActionTemplate.executeStatements(sqls1);
		return kznTlMst;
	}
	
	@Override
	public Workbook getMonthEmployeeWiseTotalKaizenExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String rptFormat) throws Exception {
		CommonMessage.debugMsg("The Inside the getMonthEmployeeWiseTotalKaizenExcel"); 
		List<String> paramValues = new ArrayList<String>();
		String empKeyId=commonFilter.getEmployee().getId();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String modiefiedKeyid="";
		modiefiedKeyid=empKeyId.replaceAll(",", "','");
		modiefiedKeyid="'"+modiefiedKeyid+"'";
		condParms = condParms + "EMPLOYEEKEYID=" + modiefiedKeyid + ";" ;
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		 
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.NewdbFunctionCall2("JHN_FN_EMPMONTTWISEICTOTAL", paramValues);
		 	ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}

	@Override
	public List<String[]> FillCategoryData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectCategory(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
		
	}
	public List<String[]> getKaizenThemeGridData(CommonFilter commonFilter)	throws Exception {
		
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			//List<String[]> relatedMst = null;
			String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParam);
			paramValues.add(commonParam);
			
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_KAIZEN_THEME_CATEGORY", paramValues);
			
			List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_KAIZEN_THEME_CATEGORY_SB", paramValues,3,true);
			
			
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

	public List<KznTlMst> updateTheme(List<KznTlMst> KaizenThemeList)throws Exception{
		 List<Object[]> valueList = new ArrayList<Object[]>();	 
		 String sql = "UPDATE KZN_TL_MST SET KZNM_THEMECATEGORYID =?, KZNM_RESULTAREA=? WHERE KZNM_KEYID=?";
		 for(KznTlMst KznThemeModel : KaizenThemeList)
	 { 
	  Object[] value={KznThemeModel.getKznmThemecategoryid(),KznThemeModel.getKznmResultarea(),KznThemeModel.getKznmKeyid()}; 
	  valueList.add(value);
	   }
	  int [] dataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
	 
	  CommonMessage.debugMsg("sq;"+ sql);
	  CommonMessage.debugMsg("valueList;"+ valueList);
	  CommonMessage.debugMsg("dataTypes;"+ dataTypes);
	  dbActionTemplate.executeBatch(sql,valueList,dataTypes);
	  return KaizenThemeList;
	}
	@Override
	public Workbook getKaizenThemeUpdateExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getKznThemeResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		
	}
	private ResultSet getKznThemeResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		commonFilter.setFromRow(null);
		commonFilter.setToRow(null);
		//String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_KAIZEN_THEME_CATEGORY", paramValues);

	}

	@Override
	public String getkaizenBenefit(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectKzbnBenefitSql(kznbKeyid);
		CommonMessage.debugMsg("String sql="+sql);
		String kznKeyid =dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(kznKeyid+"kznKeyid in dao impl="+kznbKeyid);
		return kznKeyid;
	}

	@Override
	public String getkaizenPcdqsme(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectKzbnpcdqsmeSql(kznbKeyid);
		CommonMessage.debugMsg("String sql="+sql);
		String kznKeyid =dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(kznKeyid+"kznKeyid in dao impl="+kznbKeyid);
		return kznKeyid;
	}

	@Override
	public String getThemename(String benefit) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlMstSql.selectKzbnThemenameSql(benefit);
		CommonMessage.debugMsg("String sql="+sql);
		String kznKeyid =dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(kznKeyid+"kznKeyid in dao impl="+benefit);
		return kznKeyid;
	}

	@Override
	public String updateCategory(String keyid, String kzbnkeyid,String kznmBenefit) throws Exception {
		// TODO Auto-generated method stub
		String sql=null;
		
		 sql= " UPDATE  KZN_TL_KAIZENBANKMST  SET KZBN_BENEFIT ='"+keyid+"', KZBN_PQCDSME='"+kznmBenefit+"'WHERE KZBN_KEYID ='"+kzbnkeyid+"' ";
		 dbActionTemplate.executeStatement(sql);
		 CommonMessage.debugMsg("sql in dao impl"+sql);
	return sql;
	}
	public KznTlMst updateKaizenUpload(KznTlMst kznTlMst)	throws Exception{ 
		KznTlMstSql kznTlMstSql =new KznTlMstSql();
		List<String> sqls = new ArrayList<String>();
		try {
			sqls.add(KznTlMstSql.getUpdateSql(kznTlMstSql.getKznmDbFields(), kznTlMst.getSaveArray()));
			CommonMessage.debugMsg("in update "+kznTlMst.getKznmKeyid());
			CommonMessage.debugMsg("in update "+kznTlMst.getKznmKeyid());
			CommonMessage.debugMsg("The sqls is::::::::::"+sqls);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kznTlMst;
	}
	public KznTlMst createKaizenUpload(KznTlMst kznTlMst) 	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try{
			
			KznTlMstSql kznTlMstSql =new KznTlMstSql();
			String elementId = kznTlMst.getKznmElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,KznTlMstSql.TBL_KZN_TL_MST);
			kznTlMst.setKznmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,20,"KZ","YY","Y"));
			CommonMessage.debugMsg("inside daoimpl  query      "+kznTlMst.getKznmKzbnkeyid());
			sqls.add(KznTlMstSql.getInsertSql(kznTlMstSql.getKznmDbFields(), kznTlMst.getSaveArray())); // add insert sql for master table	
			CommonMessage.debugMsg("Sql Dao"+sqls.toString());
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kznTlMst;
	}
public List<String[]> getKaizenDataGrid(CommonFilter commonFilter)	throws Exception {
		
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			//List<String[]> relatedMst = null;
			String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
			paramValues.add(condParam);
			paramValues.add(commonParam);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_KAIZEN_THEME_CATEGORY", paramValues);
			
			
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
			e.printStackTrace();
			CommonMessage.debugMsg(" excep "  +e.getMessage() );
			throw new Exception(e.getMessage()); 
		}
	}
public List<KznTlMst> updateKaizenData(List<KznTlMst> KaizenDataList)throws Exception{
	 List<Object[]> valueList = new ArrayList<Object[]>();	 
	 //String sql = "UPDATE KZN_TL_MST SET KZNM_THEMECATEGORYID =?, KZNM_RESULTAREA=?,KZNM_BENEFITVALUE= WHERE KZNM_KEYID=?";
	 StringBuilder sql=new StringBuilder("UPDATE KZN_TL_MST SET KZNM_THEMECATEGORYID =?, KZNM_RESULTAREA=?, ");
	 sql.append("KZNM_BENEFITVALUE=?, KZNM_VERIFYAMOUNT=?, KZNM_BENEFITS=?, ");
	 sql.append("KZNM_TEAMMEMBERS=? WHERE KZNM_KEYID=? ");
	 for(KznTlMst KznDataModel : KaizenDataList)
 { 
 Object[] value={KznDataModel.getKznmThemecategoryid(),KznDataModel.getKznmResultarea(),KznDataModel.getKznmBenefitvalue(),
		KznDataModel.getKznmVerifyamount(),KznDataModel.getKznmBenefits(),KznDataModel.getKznmTeammembers(),KznDataModel.getKznmKeyid()}; 
 valueList.add(value);
  }
 int [] dataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};

 CommonMessage.debugMsg("sq;"+ sql);
 CommonMessage.debugMsg("valueList;"+ valueList);
 CommonMessage.debugMsg("dataTypes;"+ dataTypes);
 dbActionTemplate.executeBatch(sql.toString(),valueList,dataTypes);
 return KaizenDataList;
  }
public 	List<String[]> getAllSimplifiedKaizenApproval(CommonFilter commonFilter)throws Exception{
	CommonMessage.debugMsg("getSimplified Approval");
	List<String> paramValues = new ArrayList<String>();		
	String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_SIMKAIZENVIEW", paramValues);
	List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_SIMKAIZENVIEW_SB", paramValues,3,true);
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



public List<GenTlWorkflowInfo> createMultipleApproval(List<GenTlWorkflowInfo> genTlWorkflowInfo) throws Exception{

	GenTlWorkflowInfoSql genTlWorkflowInfoSql = new GenTlWorkflowInfoSql();
	try
	{   
		     CommonMessage.debugMsg("Inside the DaoImpl");
		     List<String> sqls = new ArrayList<String>();
		     String dateTime=CommonFunctions.dateTimeNow();
		     List <GenTlWorkflowInfo> Approvallist = genTlWorkflowInfo;
		     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),GenTlWorkflowInfoSql.TBL_GEN_TL_WORKFLOW_INFO,10,"WF", null,null);			 
			 for(GenTlWorkflowInfo empEmployeeLink:Approvallist)
			 {     
				 if(empEmployeeLink.getWrinKeyid()!=null)
				 {
					
			         sqls.add(GenTlWorkflowInfoSql.getUpdateSql(genTlWorkflowInfoSql.getWrinDbFields(), empEmployeeLink.getSaveArray()));
				 }
				 
				 if(empEmployeeLink.getWrinKeyid()==null)
				 {
					String seqNo=sequenceNumber.getSequnceNumber();
					empEmployeeLink.setWrinKeyid(seqNo);
		            sqls.add(GenTlWorkflowInfoSql.getInsertSql(genTlWorkflowInfoSql.getWrinDbFields(), empEmployeeLink.getSaveArray()));
					
		        	if("E".equals(empEmployeeLink.getWrinStatus())){ // IF Rework Update all bellow level to rework;
						sqls.add(GenTlWorkflowInfoSql.getReworkSatusUpdateSql(empEmployeeLink.getWrinRefId(),empEmployeeLink.getWrinWrmlKeyid(), empEmployeeLink.getWrinRefType()));
						String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + empEmployeeLink.getWrinRefId() + "'";
						sqls.add(uSql);
					}
					/*************** For Inbox Approval Items to Refresh ******************/
					else {
						if( CommonFunctions.isValidKeyId(empEmployeeLink.getNextEmpId())){
							String uSql = " UPDATE ADM_APPROVALS_LIST SET APPROVALEMPID = '"+empEmployeeLink.getNextEmpId() + "' WHERE DOCUMENTNO = '" + empEmployeeLink.getWrinRefId() + "'";
							sqls.add(uSql);
						}
						else
						{
							String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + empEmployeeLink.getWrinRefId() + "'";
							sqls.add(uSql);
						}	
					}
					/*************** For Inbox Approval Items to Refresh ******************/
	
				 }
				
				 //******************************Update Kaizen Status**************************************************//
				 if (UIUtils.isValidKeyId(empEmployeeLink.getWrinRefType()) 
					&& empEmployeeLink.getWrinRefType().length()>2) {
				String refType = empEmployeeLink.getWrinRefType().substring(0,3);
				CommonMessage.debugMsg("refType="+refType);
				if(refType.equals("KZN"))
					updateKaizen(genTlWorkflowInfo,refType );
			} 
				 
			}
			 dbActionTemplate.executeStatements(sqls);
	}
	catch(BusinessApplicationExceptions e)
	{
		CommonMessage.debugMsg("Business Application   :"+e.getMessage());
		throw new BusinessApplicationExceptions(e.getMessage()); 
	}
	return null;
}

public void updateKaizen(List<GenTlWorkflowInfo> genTlWorkflowInfo,String refType) throws Exception{
	
		CommonMessage.debugMsg("refType===="+refType);
		List <GenTlWorkflowInfo> Updatelist = genTlWorkflowInfo;
		
		for(GenTlWorkflowInfo UpdateKzn: Updatelist){
			
			String wfStatus=UpdateKzn.getWrinStatus();
			CommonMessage.debugMsg("status=="+wfStatus);
	
		String status = "";
	    String nextRoleName="";
	    String lastLevel="Y";
		if(("A".equals(wfStatus) ||"E".equals(wfStatus)||"R".equals(wfStatus)) )
		{
			//&& lastLevel == "Y"){

		     if("A".equals(wfStatus) &&  "Y".equals(lastLevel))
		    	 status="C";
		     else if("A".equals(wfStatus))
		    	 status="A";
		     else if("E".equals(wfStatus)) {
		    	 status="E";
		    	 nextRoleName="REWORK";
		     }else if("R".equals(wfStatus)) {
		    	 status="R";
	     }
		     
	     if(!UIUtils.isValidKeyId(nextRoleName))
	    	 nextRoleName="-";
	     CommonMessage.debugMsg("status=="+status);
	     String keyid=UpdateKzn.getWrinRefId();
	     String sql="";
	     if(refType.equals("KZN") ) 
	    	 sql= " UPDATE KZN_TL_MST SET KZNM_STATUS='"+status+"',KZNM_APROV_LEVEL='"+nextRoleName+"' WHERE KZNM_KEYID='"+keyid+"' ";
		     CommonMessage.debugMsg("Kaizenypdate==="+sql);
	     
	       if(refType.equals("KZN"))
	    	 dbActionTemplate.executeStatement(sql);
	}
		
}
		
}	

@Override
public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid,String empid) throws NoDataFoundException, Exception {
// TODO Auto-generated method stub
StringBuffer sql =new StringBuffer();
sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
if(UIUtils.isValidKeyId(loginflid))
sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
sql.append(" AND FRT_EMPM_KEYID = '"+ empid +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
Object [] args = {} ;
List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
return userDatas;
}

public List<String []> getIndividualKaizenReport(CommonFilter commonFilter) throws Exception
{//Checking Now 
	try
	{
		
		List<String> paramValues = new ArrayList<String>();	
		String condParms =FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	
		/*if(UIUtils.isValidKeyId(commonFilter.getKAIZEN()))
		    paramValues = getFilterEMPKZNParamValues(commonFilter);
		else
			paramValues = getIndividualFilterParamValues(commonFilter);*/
		if(UIUtils.isValidKeyId(commonFilter.getGetKaizenkey()))
			condParms+="EMPID="+commonFilter.getGetKaizenkey()+";";
		if(UIUtils.isValidKeyId(commonFilter.getMainGroup()))
			condParms+="MAINGROUP="+commonFilter.getMainGroup()+";";

		
        CommonMessage.debugMsg("The condParms"+condParms);
        paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> kaizenReport = dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST1.INDIVIDUALKZN_FN_KAIZENVIEW",paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}			}		
		return kaizenReport;
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}
}
@Override
public Workbook kaizenIndividualRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType) throws Exception 
{
	  ResultSet rs = null;
	   try
	   {  
		   rs=getKaizenIndividualResultSet(commonFilter);
		   ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		   return excelUtils.writeToExcel(rs,reportType,  2, 0,0 );
	   }
	   
	   finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());   
	   }
}
private ResultSet getKaizenIndividualResultSet(CommonFilter commonFilter) throws Exception
{
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); 
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 				
	if(UIUtils.isValidKeyId(commonFilter.getGetKaizenkey()))
		condParms+="EMPID="+commonFilter.getGetKaizenkey()+";";
	if(UIUtils.isValidKeyId(commonFilter.getMainGroup()))
		condParms+="MAINGROUP="+commonFilter.getMainGroup()+";";

	paramValues.add(condParms);
	paramValues.add(commonParams);
	return  dbActionTemplate.dbFunctionCall("TEST_PC_TEST1.INDIVIDUALKZN_FN_KAIZENVIEW", paramValues);	 	
}

@Override
public String getFileName(String kznKeyid) throws Exception {
	// TODO Auto-generated method stub

		String sql = "SELECT DMDM_FILENAME FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+kznKeyid+"' AND DMDM_KEYID=(SELECT MAX(DMDM_KEYID) FROM DCM_TL_DOCUMENTMANAGER WHERE DMDM_REFDOCNO='"+kznKeyid+"')"; 
		CommonMessage.debugMsg("String sql="+sql);
		String fileName =dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(kznKeyid+"kznKeyid in dao impl="+fileName);
		return fileName;
	}

	



}