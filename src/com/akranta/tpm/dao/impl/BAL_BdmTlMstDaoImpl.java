package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlMstDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlDtlSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlMultipleRespSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlPhencauseSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlShiftwisesplitSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhydtlSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhymstSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlMchineCalTimeSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlPcsBdSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlMchineCalTimeSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlPcsBdSql;
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
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.model.BAL_BdmTlShiftwisesplit;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.BAL_PcsTlMachineCalTime;
import com.akranta.tpm.model.BAL_PcsTlPcsBd;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlMachineCalTime;
import com.akranta.tpm.model.BAL_PcsTlPcsBd;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.BdmServiceApi;
//import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;



/* dao implementation */
public class BAL_BdmTlMstDaoImpl implements BAL_BdmTlMstDao {


	//private static final Object[][] String = null;
	private DBActionTemplate dbActionTemplate; 
	private BAL_BdmTlMstSql bdmTlMstSql=null ;
	private BAL_BdmTlDtlSql bdmTlDtlSql=null ;
	private BAL_PcsTlPcsBdSql pcsBdSql=null;
	private BAL_BdmTlShiftwisesplitSql bdmTlShiftwisesplitSql =null;
	private BAL_BdmTlMultipleRespSql bdmTlMultipleRespSql = null;
	private WomTlCommunicationlogSql womTlCommunicationlogSql =null;
    private SapExternalServiceMstSql sapExternalServiceMstsql ;
    private SapExternalServiceDtlSql sapExternalServiceDtlsql ;
    private SapExternalRepairSql sapExternalRepairSql;
    private BdmServiceApi bdmServiceApi;
	FunctionCallApi fnCallApi;
	
    private BAL_PcsTlPcsBd pcstlpcsbd=null;
    private BAL_PcsTlMchineCalTimeSql mchCalSql=null;
    private BAL_PcsTlMachineCalTime mchTlCal=null;
	public BAL_BdmTlMstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
	 	
		this.dbActionTemplate = dbActionTemplate;
	 
		bdmTlMstSql = new BAL_BdmTlMstSql();
	 
		bdmTlDtlSql = new BAL_BdmTlDtlSql();
		
		pcstlpcsbd= new BAL_PcsTlPcsBd();
		pcsBdSql = new BAL_PcsTlPcsBdSql();
		
		mchCalSql =new BAL_PcsTlMchineCalTimeSql();
		mchTlCal =new BAL_PcsTlMachineCalTime();
	 
		bdmTlShiftwisesplitSql = new BAL_BdmTlShiftwisesplitSql();
	 
		womTlCommunicationlogSql = new WomTlCommunicationlogSql();
 
		sapExternalServiceMstsql = new SapExternalServiceMstSql(); 
		sapExternalServiceDtlsql = new SapExternalServiceDtlSql();
		sapExternalRepairSql     = new SapExternalRepairSql();
		bdmTlMultipleRespSql = new BAL_BdmTlMultipleRespSql();
 
	}
	//mano
	  public void BAL_BdmTlMstDaoImplJwt(String JwtToken) 
		{
			try{
				bdmServiceApi = new BdmServiceApi(JwtToken);
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
	public List<String[]> isMSRExistPcsbd(WomTlWomst womTlWomst) throws Exception
	{
		BAL_BdmTlMst bdmTlMst = new BAL_BdmTlMst();
		String occuredDate = womTlWomst.getWomsProductionstartdate();	
		if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17)
			
			occuredDate = occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
		List<String> paramVals = new ArrayList<String>();
		paramVals.add(womTlWomst.getWomsKeyid());	
		System.out.println(womTlWomst.getWomsKeyid()+" Wo wo number");
		paramVals.add(occuredDate);		
		paramVals.add(womTlWomst.getWomsMachineid());
		List<String[]>  overlapFlag = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISMSROCCURS", paramVals);
		return overlapFlag;
	}
	public BAL_BdmTlMst create(BAL_BdmTlMst bdmTlMst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			
			BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl)bdmTlMst.getBdmDetail().get(0); 
//			String YYReq = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "YYREQUIRED");
			String YYmand = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "ISYYMANDATRY");
			if(CommonFunctions.isValidKeyId(bdmDetail.getBdanErppoststatus()) && bdmDetail.getBdanErppoststatus().equals("C"))
			{
				if(CommonFunctions.isValidKeyId(YYmand) && YYmand.equals("Y"))
					bdmDetail.setBdanErppoststatus("W");					
			}

		}
		bdmTlMst.setBdmsKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlMstSql.TBL_BAL_BDM_TL_MST, 10, "BDM", "", "Y") ); // set the sequnce number
		if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsWno()))
		{
			System.out.println("work orede no in if");
			WomTlWomst womTlWomst = new WomTlWomst();
			updateWorkOrder(bdmTlMst,womTlWomst,sqls);		
		}
		else
			System.out.println("work orede no in else");
			insertWorkOrder(bdmTlMst,sqls);
		
		sqls.add(BAL_BdmTlMstSql.getInsertSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray())); // add insert sql for master table
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
			bdmDetail.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid()); 
			bdmDetail.setBdanKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL, 10, "BDA", "", "Y"));
			sqls.add(BAL_BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmDetail.getSaveArray()));// add insert sql for detail table
			sqls.add("insert into PCS_TL_MACHINECALTIME(MCTM_FACTORYID,MCTM_SECTIONID,MCTM_CELLID,MCTM_MACHINEID,MCTM_SHIFTID,MCTM_SHIFTDATE,MCTM_CALTIME) values( '"+bdmTlMst.getBdmsFactoryid()+"','"+bdmTlMst.getBdmsSectionid()+"','"+bdmTlMst.getBdmsCellid()+"','"+bdmTlMst.getBdmsMachineid()+"','"+bdmTlMst.getBdmsShiftid()+"',TO_DATE('"+bdmTlMst.getBdmsEntrydate()+"','DD-Mon-YYYY hh24:mi:ss'),"+480+")");
			
		}
		
		insertShiftWise(bdmTlMst,sqls);
		multipleResponsibility(bdmTlMst,sqls);
		checkAndInsertRepeatedBdWhyWhy(bdmTlMst,sqls);
			
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return bdmTlMst;
	}
	private void checkAndInsertRepeatedBdWhyWhy(BAL_BdmTlMst bdmTlMst,List<String> sqls){
		
		CommonFunctions.debugMsg(" --------------------------- " + bdmTlMst.getBdmsRepeatedbdWhWhyNo()  + " bdmTlMst.getBdmDetail().get(0).getBdanWwno() " + bdmTlMst.getBdmDetail().get(0).getBdanWwno());
		if(  com.akranta.tpm.dao.impl.CommonFunctions.isValidKeyId(bdmTlMst.getBdmsRepeatedbdWhWhyNo()) && "Y".equals(bdmTlMst.getBdmsRepeatedbdflag())  
				&& ! bdmTlMst.getBdmsRepeatedbdWhWhyNo().equals( bdmTlMst.getBdmDetail().get(0).getBdanWwno()  ) ){
			try{
				BAL_BdmTlWhywhymst bdmTlWhywhymst = new  BAL_BdmTlWhywhymst();
				String sql = BAL_BdmTlWhywhymstSql.getSelectWhyWhyMstSql();
				String [] args = {bdmTlMst.getBdmsRepeatedbdWhWhyNo() } ;
				
				List<BAL_BdmTlWhywhymst> bdmTlWhywhymstList = (List<BAL_BdmTlWhywhymst>) dbActionTemplate.getDataList(sql, args, bdmTlWhywhymst);
				
				bdmTlWhywhymst = bdmTlWhywhymstList.get(0);
				
				BAL_BdmTlWhywhydtl bdmTlWhywhydtl = new BAL_BdmTlWhywhydtl();
				String dtlSql = BAL_BdmTlWhywhydtlSql.getSelectWhywhyDtls() ;
				
				bdmTlWhywhymst.setBdmTlWhywhydtl((List<BAL_BdmTlWhywhydtl>) dbActionTemplate.getDataList(dtlSql ,args,bdmTlWhywhydtl));
				
				BAL_BdmTlWhywhymstSql bdmTlWhywhymstSql = new BAL_BdmTlWhywhymstSql();
				bdmTlWhywhymst.setWwmsCreatedby(bdmTlMst.getBdmsCreatedby());
				bdmTlWhywhymst.setWwmsCreatedon(bdmTlMst.getBdmsCreatedon());
				bdmTlWhywhymst.setWwmsModifiedon(bdmTlMst.getBdmsModifiedon());
				bdmTlWhywhymst.setWwmsDate(bdmTlMst.getBdmsEntrydate());
				bdmTlWhywhymst.setWwmsProblem(bdmTlMst.getBdmsProblemdescription());
				bdmTlWhywhymst.setWwmsRefdocno(bdmTlMst.getBdmsKeyid());
				bdmTlWhywhymst.setWwmsReportdatetime(bdmTlMst.getBdmsEntrydate());
				
				String elementId = bdmTlMst.getBdmsElementid();

			 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,BAL_BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST);

				bdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,12,"YY","YYMM"," "));
				
				
				sqls.add(BAL_BdmTlWhywhymstSql.getInsertSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray())); // add insert sql for master table

				insertWhyWhyDetailSql( bdmTlWhywhymst, sqls);
				
				sqls.add(BAL_BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),null,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}

	private void insertWhyWhyDetailSql(BAL_BdmTlWhywhymst bdmTlWhywhymst,List<String> sqls) throws Exception {   //detail insert
		
		
		List<BAL_BdmTlWhywhydtl> bdmTlWhywhydtlList = bdmTlWhywhymst.getBdmTlWhywhydtl(); 
		if( bdmTlWhywhydtlList != null && bdmTlWhywhydtlList.size()>0) // check for detail table data
		{
			BAL_BdmTlWhywhydtlSql bdmTlWhywhydtlSql = new BAL_BdmTlWhywhydtlSql();
			GenSequenceNumber genSequenceNumber = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),BAL_BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL,12,"YYD","YY","Y");
			
			
			for(int i =0;i<bdmTlWhywhydtlList.size();i++)
			{	
	    		BAL_BdmTlWhywhydtl bdmTlWhywhydtl = bdmTlWhywhydtlList.get(i); // get detail info from list in empployee object
	    		
	    		bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());
	    		bdmTlWhywhydtl.setWwdtCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
	    		bdmTlWhywhydtl.setWwdtCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
	    		bdmTlWhywhydtl.setWwdtModifiedon(bdmTlWhywhymst.getWwmsModifiedon());
	    		bdmTlWhywhydtl.setWwdtKeyid(genSequenceNumber.getSequnceNumber());
	    		
				sqls.add(BAL_BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));// add insert sql for detail table
			}
			genSequenceNumber.closeConnection();
		}

		
	}

	private List<String> insertShiftWise(BAL_BdmTlMst bdmTlMst,List<String> sqls) throws Exception {
		if(bdmTlMst.getBdmShiftwise()!= null && bdmTlMst.getBdmShiftwise().size()>0) // check for detail table data
		{
			sqls.add(BAL_BdmTlShiftwisesplitSql.getDeleteSql(bdmTlMst.getBdmsKeyid()));
	    	for(int i =0;i<bdmTlMst.getBdmShiftwise().size();i++)
			{	
	    		BAL_BdmTlShiftwisesplit bdmTlShiftwise = (BAL_BdmTlShiftwisesplit)bdmTlMst.getBdmShiftwise().get(i); // get detail info from list in empployee object
				bdmTlShiftwise.setBdssBdno(bdmTlMst.getBdmsKeyid());				
				bdmTlShiftwise.setBdssKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlShiftwisesplitSql.TBL_BDM_TL_SHIFTWISESPLIT));
				sqls.add(BAL_BdmTlShiftwisesplitSql.getInsertSql(bdmTlShiftwisesplitSql.getBdssDbFields(), bdmTlShiftwise.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}
	private List<String> multipleResponsibility(BAL_BdmTlMst bdmTlMst,List<String> sqls) throws Exception {
		if(bdmTlMst.getbdmTlMultipleResp()!= null && bdmTlMst.getbdmTlMultipleResp().size()>0) // check for detail table data
		{
			sqls.add(BAL_BdmTlMultipleRespSql.getDeleteSqlstr(bdmTlMst.getBdmsKeyid()));
	    	for(int i =0;i<bdmTlMst.getbdmTlMultipleResp().size();i++)
			{	
	    		BAL_BdmTlMultipleResp bdmTlMultipleResp = (BAL_BdmTlMultipleResp)bdmTlMst.getbdmTlMultipleResp().get(i); // get detail info from list in empployee object
	    		bdmTlMultipleResp.setBdrsRefid(bdmTlMst.getBdmsKeyid());				
	    		bdmTlMultipleResp.setBdrsKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlMultipleRespSql.TBL_BDM_TL_MULTIPLE_RESP));
				sqls.add(BAL_BdmTlMultipleRespSql.getInsertSql(bdmTlMultipleRespSql.getBdrsDbFields(), bdmTlMultipleResp.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}
	public BAL_BdmTlMst update(BAL_BdmTlMst bdmTlMst,WomTlWomst womTlWomst)	throws Exception ,BusinessApplicationExceptions{  
		
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlMstSql bdmTlMstSql = new BAL_BdmTlMstSql();
		PlmTlUnplannedmaintmstSql plmTlUnplannedmaintmstSql = new PlmTlUnplannedmaintmstSql();
		PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();	
		PlmTlUnplannedmaintdtlSql plmTlUnplannedmaintdtlSql = new PlmTlUnplannedmaintdtlSql();
		PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
		//GenTlEmployeedtlSql genTlEmployeedtlSql = new GenTlEmployeedtlSql();
		
		
			System.out.println("Inside the upDate"+bdmTlMst.getBdmsKeyid().substring(0,1));
			if(bdmTlMst.getBdmsKeyid().substring(0,1).equals("B"))
				sqls.add(BAL_BdmTlMstSql.getUpdateSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
			else
			{
				plmTlUnplannedmaintmst = (PlmTlUnplannedmaintmst) UIUtils.copyObject(bdmTlMst, plmTlUnplannedmaintmst);
				sqls.add(PlmTlUnplannedmaintmstSql.getUpdateSql(plmTlUnplannedmaintmstSql.getUpmmDbFields(), plmTlUnplannedmaintmst.getSaveArray())); // add insert sql for master table
			}
			List<BAL_BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
			String bdanNo = null;
			if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
			{					
				for( BAL_BdmTlDtl bdmTlDtl : bdmTlDtls ){
					//bdmTlDtl.setBdanKeyid(bdmTlMst.getBdmsKeyid());
					if(bdmTlMst.getBdmsKeyid().substring(0,1).equals("B"))
					{
						bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
						
						if( ! dbActionTemplate.checkDuplicateValue(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL,
										"BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid(), "") )
						{	
							sqls.add(BAL_BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}	
						else{
							sqls.add(BAL_BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}						
					}
					else
					{
						plmTlUnplannedmaintdtl = (PlmTlUnplannedmaintdtl) UIUtils.copyObject(bdmTlDtl, plmTlUnplannedmaintdtl);
						CommonFunctions.debugMsg(plmTlUnplannedmaintdtl.getUpmdKeyid());
						plmTlUnplannedmaintdtl.setUpmdUpmmKeyid(plmTlUnplannedmaintmst.getUpmmKeyid());
						CommonFunctions.debugMsg(plmTlUnplannedmaintmst.getUpmmKeyid());
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
				multipleResponsibility(bdmTlMst,sqls);
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsWno())){
					System.out.println("Inside the upDate");
					updateWorkOrder(bdmTlMst,womTlWomst,sqls);			
				}	
				else
					insertWorkOrder(bdmTlMst,sqls);
				
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsFinalphenomena()))
				{
					String parentId = bdmTlMst.getBdmsFactoryid() + "-"+bdmTlMst.getBdmsSectionid();
						   parentId += "-"+bdmTlMst.getBdmsCellid() + "-"+bdmTlMst.getBdmsMachineid();
						   parentId += "-"+bdmTlMst.getBdmsAssemblyid();
					String checkPhnLinkExists = dbActionTemplate.getSingleValue(BAL_BdmTlMstSql.checkPhnLinkExists(parentId,bdmTlMst.getBdmsFinalphenomena()));
					
					String Phenomena = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_PHENOMENAMST, "BPHM_PHENOMENANAME", "BPHM_KEYID", bdmTlMst.getBdmsFinalphenomena());
					CommonFunctions.debugMsg("checkPhnLinkExists : "+checkPhnLinkExists);
					if(checkPhnLinkExists.equals("0"))
					{
						insertPhenCauseLink(parentId,bdmTlMst.getBdmsFinalphenomena(),Phenomena,"PHN",sqls);
						
					}
				}
				System.out.println(" Bdm Tl If dao impl :1");
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsFinalcause()))
				{
					
					String parentId = bdmTlMst.getBdmsFactoryid() + "-"+bdmTlMst.getBdmsSectionid();
						   parentId += "-"+bdmTlMst.getBdmsCellid() + "-"+bdmTlMst.getBdmsMachineid();
						   parentId += "-"+bdmTlMst.getBdmsAssemblyid()+ "-"+bdmTlMst.getBdmsFinalphenomena();
					String checkPhnLinkExists = dbActionTemplate.getSingleValue(BAL_BdmTlMstSql.checkPhnLinkExists(parentId,bdmTlMst.getBdmsFinalcause()));
					String Cause = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_CAUSEMST, "BCSM_NAME", "BCSM_PHENOMENAID", bdmTlMst.getBdmsFinalcause());
					if(checkPhnLinkExists.equals("0"))
					{
						insertPhenCauseLink(parentId,bdmTlMst.getBdmsFinalcause(),Cause,"CAS",sqls);
						
					}
				}
				//System.out.println("SQL"+sqls.get(1));
			}
			checkAndInsertRepeatedBdWhyWhy(bdmTlMst, sqls);
			
			dbActionTemplate.executeStatements(sqls);
		return bdmTlMst;
	}
	
	public BAL_BdmTlMst createPcsBd(BAL_BdmTlMst bdmTlMst) 	throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			
			BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl)bdmTlMst.getBdmDetail().get(0); 
			// String YYReq = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "YYREQUIRED");
			String YYmand = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "ISYYMANDATRY");
			if(CommonFunctions.isValidKeyId(bdmDetail.getBdanErppoststatus()) && bdmDetail.getBdanErppoststatus().equals("C"))
			{
				if(CommonFunctions.isValidKeyId(YYmand) && YYmand.equals("Y"))
					bdmDetail.setBdanErppoststatus("W");					
			}

		}
		bdmTlMst.setBdmsKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlMstSql.TBL_BAL_BDM_TL_MST, 10, "BDM", "", "Y") ); // set the sequnce number
		if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsWno()))
		{
			System.out.println(bdmTlMst.getBdmsWno()+" work orede no in if");
			WomTlWomst womTlWomst = new WomTlWomst();
			updateWorkOrderPcsBd(bdmTlMst,womTlWomst,sqls);		
		}
		else
			System.out.println(bdmTlMst.getBdmsWno()+" work orederrr no in else");
			insertWorkOrder(bdmTlMst,sqls);
		
		sqls.add(BAL_BdmTlMstSql.getInsertSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray())); // add insert sql for master table
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
			bdmDetail.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid()); 
			bdmDetail.setBdanKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL, 10, "BDA", "", "Y"));
			sqls.add(BAL_BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmDetail.getSaveArray()));// add insert sql for detail table
		}
		
		if(bdmTlMst.getPcsbd()!=null && bdmTlMst.getPcsbd().size()>0){
			 CommonFunctions.debugMsg("inside getPcsDb.........");
			BAL_PcsTlPcsBd pcsBd =(BAL_PcsTlPcsBd)bdmTlMst.getPcsbd().get(0);
			String dateTime = CommonFunctions.dateTimeNow();
			pcsBd.setActive("Y");
			pcsBd.setCreatedOn(dateTime);
			pcsBd.setPcsBdmsKeyid(bdmTlMst.getBdmsKeyid());
			pcsBd.setPcsbdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlPcsBdSql.TBL_PCS_TL_PCSBD, 12, "PCS", "", "Y"));
			sqls.add(BAL_PcsTlPcsBdSql.getInsertSql(pcsBdSql.getPcsBdDbFields(), pcsBd.getSaveArray()));
		//	sqls.add(PcsTlMchineCalTimeSql.getInsertSql(mchCalSql.getMchCalDbfields(), mchTlCal.getSaveArray()));
			//  Date dNow = new Date( );
			  SimpleDateFormat ft =  new SimpleDateFormat ("DD-MM-YYYY");
			  String date=bdmTlMst.getBdmsEntrydate();
			  //ft.format(bdmTlMst.getBdmsEntrydate());
			sqls.add("insert into PCS_TL_MACHINECALTIME(MCTM_FACTORYID,MCTM_SECTIONID,MCTM_CELLID,MCTM_MACHINEID,MCTM_SHIFTID,MCTM_SHIFTDATE,MCTM_CALTIME) values( '"+bdmTlMst.getBdmsFactoryid()+"','"+bdmTlMst.getBdmsSectionid()+"','"+bdmTlMst.getBdmsCellid()+"','"+bdmTlMst.getBdmsMachineid()+"','"+bdmTlMst.getBdmsShiftid()+"',TO_DATE('"+bdmTlMst.getBdmsEntrydate()+"','DD-MM-YYYY hh24:mi:ss'),"+480+")");
		}
		
		insertShiftWise(bdmTlMst,sqls);
		multipleResponsibility(bdmTlMst,sqls);
		checkAndInsertRepeatedBdWhyWhy(bdmTlMst,sqls);
			
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return bdmTlMst;
	}
	
	public BAL_BdmTlMst delete(BAL_BdmTlMst bdmTlMst) throws Exception 
	{
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlMstSql bdmTlMstSql = new BAL_BdmTlMstSql();
		try {
			System.out.println("Inside the DaoImpl Delete");
			List<BAL_BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
			String woId = "";
			CommonFunctions.debugMsg("------------------------->"+bdmTlMst.getBdmsWoprodaccepflag());
			CommonFunctions.debugMsg("------------------------->"+bdmTlMst.getBdmsReporteddate());
			
			/*if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoprodaccepflag()))
			{
				if(bdmTlMst.getBdmsWoprodaccepflag().equals("Y"))
				{*/
					woId = dbActionTemplate.getSingleValue(TableNames.TBL_BAL_WOM_TL_WOMST,"WOMS_KEYID", "WOMS_ACTIVITYID", bdmTlMst.getBdmsKeyid());
					if(UIUtils.isValidKeyId(woId))
					{
						CommonFunctions.debugMsg("ID : "+woId);
						sqls.add(BAL_BdmTlMstSql.getUpdateWoSql(bdmTlMst.getBdmsReporteddate(),woId));
					}
				/*}
			}*/
			if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
			{	
				System.out.println("Inside the detail table");
				for( BAL_BdmTlDtl bdmTlDtl : bdmTlDtls ){
					bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
					String bdanId = dbActionTemplate.getSingleValue(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL,"BDAN_KEYID", "BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid());
					bdmTlDtl.setBdanKeyid(bdanId);
					sqls.add(BAL_BdmTlDtlSql.getDeleteSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
				}
				
			}
			sqls.add(BAL_BdmTlMstSql.getDeleteSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
			sqls.add("delete from "+SapExternalServiceMstSql.TBL_SAP_EXTERNAL_SERVICE_MST+" where EXTM_NOTIFICATIONNO = '"+bdmTlMst.getBdmsKeyid()+"'");
			sqls.add("delete from "+BAL_BdmTlMultipleRespSql.TBL_BDM_TL_MULTIPLE_RESP +" where BDRS_REFID ='"+bdmTlMst.getBdmsKeyid()+"'");
			System.out.println("SQL Delete.........."+sqls);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return bdmTlMst; 
		
		
	}	
	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog womTlCommunicationlog) throws Exception
	{
		try{
			CommonFunctions.debugMsg("saveComm DaOIMPL");
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 		
		    womTlCommunicationlog.setWcmlKeyid(dbActionTemplate.getSequenceNumber(WomTlCommunicationlogSql.TBL_WOM_TL_COMMUNICATIONLOG, 11, "CMC", "MMYY", "Y"));
		    		//(WomTlCommunicationlogSql.TBL_WOM_TL_COMMUNICATIONLOG)); // set the sequnce number		
			sqls.add(WomTlCommunicationlogSql.getInsertSql(womTlCommunicationlogSql.getWcmlDbFields(), womTlCommunicationlog.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	 
		}catch(Exception e){
			e.printStackTrace();
		}
		return womTlCommunicationlog;
	}
	

	
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
				//dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_GetActivitiesForBDA", paramValues);	
			dataList =  dbActionTemplate.processFunctionCalls("BDM_FN_GetActivitiesForBDA", paramValues);
			CommonFunctions.debugMsg("After " +dataList.size());
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
			System.out.println("Date : "+commonFilter.getFromDate()+"///////////"+commonFilter.getToDate());
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
		CommonFunctions.debugMsg("Breakdown"+sql);
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		
		return gridData;
   }


@Override
public BAL_BdmTlMst select(String keyid) throws Exception {
	// TODO Auto-generated method stub
	BAL_BdmTlMst bdmTlMst = new BAL_BdmTlMst();
	String sql = BAL_BdmTlMstSql.selectBD();
	System.out.println(sql);
	Object args [] = new Object [] { keyid };
	bdmTlMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	//String sqlExtServ = "Select EXTM_KEYID from SAP_EXTERNAL_SERVICE_MST where EXTM_NOTIFICATIONNO= '"+bdmTlMst.getBdmsWno()+"'";
	//String extKeyid = dbActionTemplate.getSingleValue(sqlExtServ);
	//CommonFunctions.debugMsg(keyid+" KEYIDSSS  "+extKeyid);
	if(UIUtils.isValidKeyId(keyid))
	bdmTlMst.setExternalserviceId(keyid );
	return bdmTlMst;
}

public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception {
	// TODO Auto-generated method stub
	PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();	
	String sql = BAL_BdmTlMstSql.selectUPMSql();
	System.out.println(sql);
	Object args [] = new Object [] { keyid };
	plmTlUnplannedmaintmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return plmTlUnplannedmaintmst;
}

public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception {
	// TODO Auto-generated method stub
	PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
	String sql = BAL_BdmTlMstSql.selectUPMDetailSql();
	System.out.println(sql);
	Object args [] = new Object [] { keyid };
	plmTlUnplannedmaintdtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return plmTlUnplannedmaintdtl;
}


public String getShift(ShiftBean shiftBean)
{
	try
	{			
		String sql = BAL_BdmTlMstSql.getShiftFunction();
		System.out.println(sql);
		List<String > paramValues = new ArrayList<String>();
		paramValues.add(shiftBean.getFactId());
		paramValues.add(shiftBean.getSectId());
		paramValues.add(shiftBean.getCellId());
		paramValues.add(shiftBean.getFromTime());
		System.out.println(shiftBean.getFactId());
		List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramValues);
		//System.out.println("Shift : "+fillShift.get(0)[0]);
		return fillShift.get(0)[0];
	}
	catch(Exception e)
	{
		//CommonFunctions.debugMsg("err : "+e.toString());
	}
	return null;
}
public List<String[]> getDownTime(List<String> paramValues)
{
	try
	{			
		String sql = BAL_BdmTlMstSql.getDownTimeFunction();	
		
		List<String []> downTimeDatas = dbActionTemplate.processFunctionCalls( sql,paramValues);	
		System.out.println("Size : "+downTimeDatas.size());
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
	String sql = BAL_BdmTlMstSql.getCommTextSql(bdId);
	System.out.println(sql);
	List<String[]> commDatas;	
	try {
		commDatas = dbActionTemplate.getDataList(sql);
		System.out.println("Size : "+commDatas.size());				
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
		String sql = BAL_BdmTlMstSql.getYYSql();
		List<String > paramValues = new ArrayList<String>();
		paramValues.add(wwNo);
		System.out.println("Roopa wwNo : "+wwNo);
		List<String []> yyDatas = dbActionTemplate.processFunctionCalls( sql,paramValues);	
		System.out.println("Size : "+yyDatas.size());
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
	String sql = BAL_BdmTlMstSql.getPillarClassfcnSql(pillar);
	System.out.println("....................................");
	System.out.println(sql);
	List<String > paramValues = new ArrayList<String>();
	paramValues.add(pillar);
	
	return dbActionTemplate.getDataList(sql);
}
public List<String[]> getRootCause(String wwNo) throws Exception
{
	String sql = BAL_BdmTlMstSql.getRootCauseSql();
	List<String > paramValues = new ArrayList<String>();
	paramValues.add(wwNo);	
	return dbActionTemplate.getDataList(sql);	
	
}
public String getPhenType(String bookedPhen)
{
	try
	{			
		String phenType = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_PHENOMENAMST, "BPHM_PHENOMENATYPE", "BPHM_KEYID", bookedPhen);
		System.out.println("getPhenType "+phenType);
		return phenType;		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}	
	return bookedPhen;
	
}
public List<String> insertPhenCauseLink(String parentId,String originalId,String dispCode,String type,List<String> sqls) 	throws Exception {
	
	BAL_BdmTlPhncauselink bdmTlPhncauselink = new BAL_BdmTlPhncauselink();
	bdmTlPhncauselink.setBpclOriginalid( originalId);
	bdmTlPhncauselink.setBpclElementid(parentId + "-"+originalId);
	bdmTlPhncauselink.setBpclParentid(parentId);
	bdmTlPhncauselink.setBpclDisplaycode(dispCode);
	bdmTlPhncauselink.setBpclElementtype(type);
	bdmTlPhncauselink.setBpclActive("Y");
	BAL_BdmTlPhencauseSql bdmTlPhenCauseSql = new BAL_BdmTlPhencauseSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
	try{		
		sqls.add(bdmTlPhenCauseSql.getPclInsertSql(bdmTlPhenCauseSql.getBpclDbFields(), bdmTlPhncauselink.getSaveArray())); // add insert sql for master table
	}catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return sqls;
}

public List<String> insertWorkOrder(BAL_BdmTlMst bdmTlMst,List<String> sqls) 	throws Exception {
	WomTlWomst womTlWomst = null;
	//if( bdmTlMst.getWomTlWomst() == null)
		womTlWomst = new WomTlWomst();
	//else
		//womTlWomst = bdmTlMst.getWomTlWomst() ;
	fillWorkOrder(womTlWomst,bdmTlMst);	
	WomTlWomstSql womTlWomstSql = new WomTlWomstSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
		//MANO CHANGE THE TABLE NAME 
		womTlWomst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_BAL_WOM_TL_WOMST, 15, "MW", "YY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
		bdmTlMst.setBdmsWno(womTlWomst.getWomsKeyid());
		sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add insert sql for master table
		bdmTlMst.setWomTlWomst(womTlWomst);
	return sqls;
}
public List<String>  updateWorkOrder(BAL_BdmTlMst bdmTlMst,WomTlWomst womTlWomst,List<String> sqls) 	throws Exception,BusinessApplicationExceptions{ 
	
	
	fillWorkOrder(womTlWomst,bdmTlMst);
	bdmTlMst.setWomTlWomst(womTlWomst);
	CommonFunctions.debugMsg("STATUS ---- > "+bdmTlMst.getBdmsStatus() + " : "+womTlWomst.getWomsStatus());
	List<String[]>  overlapFlag = isMSRExist(womTlWomst);
	if(overlapFlag.size()>0)
	{
		throw new BusinessApplicationExceptions("msrOverlap,");
	}
WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
	
	womTlWomst.setWomsKeyid(bdmTlMst.getBdmsWno());
	//bdmTlMst.setBdmsWno(womTlWomst.getWomsKeyid());
	sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
		
	return sqls;
}
	public List<String>  updateWorkOrderPcsBd(BAL_BdmTlMst bdmTlMst,WomTlWomst womTlWomst,List<String> sqls) 	throws Exception,BusinessApplicationExceptions{ 
		
		fillWorkOrder(womTlWomst,bdmTlMst);
		bdmTlMst.setWomTlWomst(womTlWomst);
		womTlWomst.setWomsKeyid(bdmTlMst.getBdmsWno());
		CommonFunctions.debugMsg("STATUS ---- > "+womTlWomst.getWomsKeyid() + " : "+bdmTlMst.getBdmsWno());//getWomsStatus());
		List<String[]>  overlapFlag = isMSRExistPcsbd(womTlWomst);
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
			CommonFunctions.debugMsg(noOfDays + " : "+selDays);
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
		
		return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}
@Override
public List<String[]> getSapInfoList(CommonFilter commFilter) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("getSapInfoListdaoimp..." );
	StringBuffer sql = new StringBuffer();
	String sql1="";
	String keyid = commFilter.getKey();
	System.out.println("keyid.."+keyid);
	sql.append(" select * from ( " );//'Model','Quantity'
	sql.append(" select 'KeyId','Select','Part No','Spare Name','Spare Loc','Planned Quantity','Actual Quantity','Rate','Avl Stock','hdnCheckSel',0 as dataorder from dual ");
	sql.append(" UNION ALL SELECT SSPM_KEYID,'',TO_CHAR(SSPM_SPARENO),SSPM_SPARENAME,SSPM_STORAGELOCATION,SPRM_MODEL,TO_CHAR(SSPM_QUANTITY),TO_CHAR(SSPM_RATE),SSPM_AVAILABLESTOCK ");
	sql.append(" ,'',1 as dataorder FROM  SAP_TL_SPARESREPLACED,gen_tl_sparesmst ");//gen_tl_sparesmst
    sql.append(" WHERE 1=1  AND TO_CHAR(SSPM_SPARENO) = SPRM_PARTNO (+) ");
    sql.append(" AND SSPM_DOCNUMBER = '"+keyid+"' ");
    sql.append(" ) order by dataorder ");
    
	// TODO Auto-generated method stub
	System.out.println("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());

	//CommonFunctions.debugMsg("GRIDDATA.SIZE"+gridData.size());
	
	return gridData;
}

@Override
public List<String[]> getExtServiceList(String ExtMasterId)
			throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	String sql1="";
	//sql.append("select 'Notification No','WO No','SAP Ref No.','Operation Qty','Price','Material Group','Purch Group','Agreement','Recipient','Requisitioner','Planned Delivery Time','External Sub contract ?','Sort Term','Cost Element','Vendor','Info Record','Unloading Point','Tracking Number','FW Order' from dual");
	sql.append("select EXTD_EXTM_KEYID mstkeyid,EXTD_KEYID detKeyid,EXTD_SERVICE_NO \"Service No\",EXTD_SERVICE_TEXT \"Service\",EXTD_QTY \"Quantity\",EXTD_VALUE \"Value\", EXTD_UOM \"UOM\",EXTD_CURRENCY \"Currency\",EXTD_COST_ELEMENT \"Cost Element\"   from  SAP_EXTERNAL_SERVICE_DTL");
	sql.append(" WHERE EXTD_EXTM_KEYID = '"+ExtMasterId+"'");
	// TODO Auto-generated method stub
	System.out.println("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), null);

	return gridData;
}

@Override
public List<String[]> getExtSubList(java.lang.String wwNo)
		throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	String sql1="";
	//sql.append("select 'Component No.','PART NO','Requirement Qty','UOM','Item Category','Storage Location','Material Rework Indicator' from dual");
	sql.append("select EXTR_EXTM_KEYID as mstKeyid,EXTR_KEYID as repairId , EXTR_COMPONENT_NO as \"Component No.\",EXTR_PARTNO  as  \"PART NO\",");
	sql.append(" EXTR_REQUIREMENT_QTY as \"Requirement Qty\",EXTR_UOM  as \"UOM\",EXTR_ITEM_CATEGORY as \"Item Category\",EXTR_STORAGE_LOCATION as \"Storage Location\",EXTR_MAT_REWORK_INDI as \"Material Rework Indicator\" from SAP_EXTERNAL_REPAIR");			
	// TODO Auto-generated method stub
	System.out.println("sql..." + sql);
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
	System.out.println("sql..." + sql);
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

private void fillWorkOrder(WomTlWomst womTlWomst,BAL_BdmTlMst bdmTlMst) 	throws Exception {
	
	//MANO HAD CHANGE THE TABLE NAME 
	String woStatus = dbActionTemplate.getSingleValue(WomTlWomstSql.TBL_BAL_WOM_TL_WOMST, "WOMS_STATUS", "WOMS_KEYID", bdmTlMst.getBdmsWno());
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
	
		CommonFunctions.debugMsg("Proposed Start Date : "+womTlWomst.getWomsProposedstartdate());
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendtime()))
			womTlWomst.setWomsProposedenddate(bdmTlMst.getBdmsWoendtime());
		else
			womTlWomst.setWomsProposedenddate(Constants.futureNullDate  + " "+"00:00");	
	
		CommonFunctions.debugMsg("Proposed End Date : "+womTlWomst.getWomsProposedenddate());
		
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
		BAL_BdmTlDtl bdmDetail = (BAL_BdmTlDtl)bdmTlMst.getBdmDetail().get(0);
		
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
		CommonFunctions.debugMsg("Status BD "+bdmDetail.getBdanErppoststatus());
		
		
		CommonFunctions.debugMsg("woStatus "+woStatus);

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
		CommonFunctions.debugMsg("Status IN BD: "+womTlWomst.getWomsStatus() + " BD STATUS : "+bdmDetail.getBdanErppoststatus());
		CommonFunctions.debugMsg(womTlWomst.getWomsAllottedto());
		CommonFunctions.debugMsg(womTlWomst.getWomsDoneby());
		CommonFunctions.debugMsg(womTlWomst.getWomsWoapprovalby());
		CommonFunctions.debugMsg(womTlWomst.getWomsMachinereleaseby());
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
		  CommonFunctions.debugMsg("aCTIVE  "+newSapExternalServiceMst.getExtmActive());
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		newSapExternalServiceMst.setExtmKeyid(dbActionTemplate.getSequenceNumber(SapExternalServiceMstSql.TBL_SAP_EXTERNAL_SERVICE_MST,10, "SESM", "MMYY", "Y"));
		CommonFunctions.debugMsg("test  "+newSapExternalServiceMst.getExtmKeyid());
		CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceMst.getExtmCreatedby());
		sqls.add(SapExternalServiceMstSql.getInsertSql(sapExternalServiceMstsql.getExtmDbFields(),newSapExternalServiceMst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonFunctions.debugMsg("MK  "+e.getMessage());
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
	CommonFunctions.debugMsg(extKeyid+" sapExternalServiceMst  ");
	return sapExternalServiceMst;
}

@Override
public SapExternalServiceMst updateExtService(
		SapExternalServiceMst newSapExternalServiceMst) throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonFunctions.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonFunctions.debugMsg("test  "+newSapExternalServiceMst.getExtmKeyid());
		CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceMst.getExtmCreatedby());
		sqls.add(SapExternalServiceMstSql.getUpdateSql(sapExternalServiceMstsql.getExtmDbFields(),newSapExternalServiceMst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonFunctions.debugMsg("update  "+e.getMessage());
	}
	return newSapExternalServiceMst;
}

@Override
public SapExternalServiceDtl createExtServiceDtl(
		SapExternalServiceDtl newSapExternalServiceDtl) throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonFunctions.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		newSapExternalServiceDtl.setExtdKeyid(dbActionTemplate.getSequenceNumber(SapExternalServiceDtlSql.TBL_SAP_EXTERNAL_SERVICE_DTL,10, "SESD", "MMYY", "Y"));
		CommonFunctions.debugMsg("test  "+newSapExternalServiceDtl.getExtdKeyid());
		CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceDtl.getExtdCreatedby());

		sqls.add(SapExternalServiceDtlSql.getInsertSql(sapExternalServiceDtlsql.getExtdDbFields(),newSapExternalServiceDtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonFunctions.debugMsg("insert  "+e.getMessage());
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
		CommonFunctions.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		//newSapExternalServiceDtl.setExtdKeyid(dbActionTemplate.getSequenceNumber(SapExternalServiceDtlSql.TBL_SAP_EXTERNAL_SERVICE_DTL,10, "SESD", "MMYY", "Y"));
		CommonFunctions.debugMsg("test  "+newSapExternalServiceDtl.getExtdKeyid());
		CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceDtl.getExtdCreatedby());

		sqls.add(SapExternalServiceDtlSql.getUpdateSql(sapExternalServiceDtlsql.getExtdDbFields(),newSapExternalServiceDtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonFunctions.debugMsg("update  "+e.getMessage());
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
		CommonFunctions.debugMsg("test  "+newSapExternalRepair.getExtrKeyid());
		CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapExternalRepair.getExtrCreatedby());
		sqls.add(SapExternalRepairSql.getInsertSql(sapExternalRepairSql.getExtrDbFields(),newSapExternalRepair.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonFunctions.debugMsg("MK  "+e.getMessage());
		throw new Exception(e.getMessage());
	}
	return newSapExternalRepair;
}

@Override
public SapExternalRepair updateExtRepair(SapExternalRepair newSapExternalRepair)
		throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonFunctions.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonFunctions.debugMsg("test  "+newSapExternalRepair.getExtrKeyid());
		CommonFunctions.debugMsg("ceatedBYDaoimpl  "+newSapExternalRepair.getExtrCreatedby());
		sqls.add(SapExternalRepairSql.getUpdateSql(sapExternalRepairSql.getExtrDbFields(),newSapExternalRepair.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonFunctions.debugMsg("update  "+e.getMessage());
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
		String sql = "select * from SAP_TL_MAINTENANCEORDER where MOMS_REFDOCID = ?";
		CommonFunctions.debugMsg(sql+" -  "+ woKeyid);
		sapTlMaintenanceOrdermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return sapTlMaintenanceOrdermst;	 
	}catch(Exception e){
		return null;
	}
	
	
}

	public List<String[]> getRepeatedBreakDown(CommonParams commonParams) throws Exception{
		String innerSql = BAL_BdmTlMstSql.getRepeatedBDSql();
		
		String sql = CommonFilterSqls.countSql(innerSql, commonParams.getGridFilters());
		
		String [] arg = {commonParams.getFlid()};
		
		List<String[]> data = dbActionTemplate.getDataList(sql, arg);
		
		int count = 0;
		if (data.size()>0) {
			count = Integer.parseInt(data.get(0)[0]);
		}

		commonParams.setTotalRecordCnt(count);
		if (count > 0) {
			sql = CommonFilterSqls.addPaginationParams(innerSql,commonParams);
			return dbActionTemplate.getDataList(sql, arg);
		}
		throw new NoDataFoundException("No Data Found");
	}

	@Override
	public List<String[]> getBDAnalysisRpt(CommonFilter commonFilter)
			throws Exception {
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
	public Workbook bdRptSummaryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		  ResultSet rs = null;
		   try{
			   List<String> paramValues = getFilterParamValues(commonFilter);
				
				rs= dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_BDANALYSISREPORTSUMMARY", paramValues);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	@Override
	public String getActionPlanDetails(BAL_BdmTlMst newBdmTlMst) throws Exception {
		// TODO Auto-generated method stub
		String cntCond = " AND APLM_MASTERREFID='"+ newBdmTlMst.getBdmsKeyid() + "' ";
		String innerSql = BAL_BdmTlMstSql.getActionplanSql(cntCond);
		
		return dbActionTemplate.getSingleValue(innerSql);
	}

	@Override
	public List<String[]> getMultipleResponsibility(String bdEmrNo,
			CommonParams commonParams) throws Exception{
		// TODO Auto-generated method stub
		String innerSql=BAL_BdmTlMstSql.getMultipleRespSql(bdEmrNo,commonParams);
		String sql = CommonFilterSqls.countSql(innerSql, commonParams.getGridFilters());
		CommonFunctions.debugMsg("SQL 1 "+sql);
		String [] arg = {commonParams.getFlid()};
		
		List<String[]> data = dbActionTemplate.getDataList(sql);
		
		int count = 0;
		if (data.size()>0) {
			count = Integer.parseInt(data.get(0)[0]);
		}

		commonParams.setTotalRecordCnt(count);
		if (count > 0) {
			sql = CommonFilterSqls.addPaginationParams(innerSql,commonParams);
			return dbActionTemplate.getDataList(sql);
		}
		throw new NoDataFoundException("No Data Found");
		//return dbActionTemplate.getDataList(sql);
	}

	@Override
	public List<String[]> getBreakDownList(CommonFilter commonFilter) throws Exception {
		StringBuffer sql = new StringBuffer();
		String sql1="";
		String keyid = commonFilter.getKey();
		System.out.println("keyid.."+keyid);
		sql.append(" select * from ( " );
		sql.append("select 'BDId','Machine','Occured Date','Occured Time','Production Start Date','Production Start Time','Problem','Remark','Shift','ShiftId','MachinId','PRDFLAG','WONO','WTIME','ELEMENT',0 as dataorder from dual ");
		sql.append(" UNION ALL SELECT BDMS_KEYID,E.MACHINE ||' - ' || E.FNLN_DISPLAYCODE as MACHINE,TO_CHAR(BDMS_REPORTEDDATE,'DD-MON-YYYY'),TO_CHAR(BDMS_REPORTEDDATE,'HH24:MI'), TO_CHAR(BDMS_PRODACCEPDATE,'DD-MON-YYYY'),TO_CHAR(BDMS_PRODACCEPDATE,'HH24:MI'),BDMS_PROBLEMdESCRIPTION,BDMS_REMARKS ");
		sql.append(" ,SFTM_CODE,SFTM_KEYID,MCHM_KEYID,BDMS_WOPRODACCEPFLAG,BDMS_WNO,TO_CHAR(BDMS_ACTUALWORKTIME),BDMS_ELEMENTID,1 as dataorder FROM  BDM_TL_MST,GEN_TL_MACHINEMST,GEN_TL_SHIFTMST,gen_mv_flidhierarchy E,PCS_TL_PCSBD ");
		sql.append(" WHERE 1=1   AND BDMS_SHIFTID=SFTM_KEYID AND MCHM_KEYID=BDMS_MACHINEID AND PBD_BDM_KEYID=BDMS_KEYID  AND BDMS_FLID= FLID  AND BDMS_CELLID='"+keyid+"'"); 
		sql.append(" ) order by dataorder ");	
		           
	    
		// TODO Auto-generated method stub
		System.out.println("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());

		//CommonFunctions.debugMsg("GRIDDATA.SIZE"+gridData.size());
		
		return gridData;
	}

	
public BAL_BdmTlMst updatepcsBd(BAL_BdmTlMst bdmTlMst,WomTlWomst womTlWomst)	throws Exception ,BusinessApplicationExceptions{  
		
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlMstSql bdmTlMstSql = new BAL_BdmTlMstSql();
		PlmTlUnplannedmaintmstSql plmTlUnplannedmaintmstSql = new PlmTlUnplannedmaintmstSql();
		PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();	
		PlmTlUnplannedmaintdtlSql plmTlUnplannedmaintdtlSql = new PlmTlUnplannedmaintdtlSql();
		PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
		//GenTlEmployeedtlSql genTlEmployeedtlSql = new GenTlEmployeedtlSql();
		
		
			System.out.println("Inside the upDate"+bdmTlMst.getBdmsKeyid().substring(0,1));
			if(bdmTlMst.getBdmsKeyid().substring(0,1).equals("B"))
				sqls.add(BAL_BdmTlMstSql.getUpdateSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
			else
			{
				plmTlUnplannedmaintmst = (PlmTlUnplannedmaintmst) UIUtils.copyObject(bdmTlMst, plmTlUnplannedmaintmst);
				sqls.add(PlmTlUnplannedmaintmstSql.getUpdateSql(plmTlUnplannedmaintmstSql.getUpmmDbFields(), plmTlUnplannedmaintmst.getSaveArray())); // add insert sql for master table
			}
			List<BAL_BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
			String bdanNo = null;
			if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
			{					
				for( BAL_BdmTlDtl bdmTlDtl : bdmTlDtls ){
					//bdmTlDtl.setBdanKeyid(bdmTlMst.getBdmsKeyid());
					if(bdmTlMst.getBdmsKeyid().substring(0,1).equals("B"))
					{
						bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
						
						if( ! dbActionTemplate.checkDuplicateValue(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL,
										"BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid(), "") )
						{	
							sqls.add(BAL_BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}	
						else{
							sqls.add(BAL_BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}						
					}
					else
					{
						plmTlUnplannedmaintdtl = (PlmTlUnplannedmaintdtl) UIUtils.copyObject(bdmTlDtl, plmTlUnplannedmaintdtl);
						CommonFunctions.debugMsg(plmTlUnplannedmaintdtl.getUpmdKeyid());
						plmTlUnplannedmaintdtl.setUpmdUpmmKeyid(plmTlUnplannedmaintmst.getUpmmKeyid());
						CommonFunctions.debugMsg(plmTlUnplannedmaintmst.getUpmmKeyid());
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
				multipleResponsibility(bdmTlMst,sqls);
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsWno())){
					System.out.println("inside update if condition");
					updateWorkOrderPcsBd(bdmTlMst,womTlWomst,sqls);			
				}	
				else
					insertWorkOrder(bdmTlMst,sqls);
				
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsFinalphenomena()))
				{
					String parentId = bdmTlMst.getBdmsFactoryid() + "-"+bdmTlMst.getBdmsSectionid();
						   parentId += "-"+bdmTlMst.getBdmsCellid() + "-"+bdmTlMst.getBdmsMachineid();
						   parentId += "-"+bdmTlMst.getBdmsAssemblyid();
					String checkPhnLinkExists = dbActionTemplate.getSingleValue(BAL_BdmTlMstSql.checkPhnLinkExists(parentId,bdmTlMst.getBdmsFinalphenomena()));
					
					String Phenomena = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_PHENOMENAMST, "BPHM_PHENOMENANAME", "BPHM_KEYID", bdmTlMst.getBdmsFinalphenomena());
					CommonFunctions.debugMsg("checkPhnLinkExists : "+checkPhnLinkExists);
					if(checkPhnLinkExists.equals("0"))
					{
						insertPhenCauseLink(parentId,bdmTlMst.getBdmsFinalphenomena(),Phenomena,"PHN",sqls);
						
					}
				}	
				System.out.println(" Bdm Tl If dao impl :1");
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsFinalcause()))
				{
					
					String parentId = bdmTlMst.getBdmsFactoryid() + "-"+bdmTlMst.getBdmsSectionid();
						   parentId += "-"+bdmTlMst.getBdmsCellid() + "-"+bdmTlMst.getBdmsMachineid();
						   parentId += "-"+bdmTlMst.getBdmsAssemblyid()+ "-"+bdmTlMst.getBdmsFinalphenomena();
					String checkPhnLinkExists = dbActionTemplate.getSingleValue(BAL_BdmTlMstSql.checkPhnLinkExists(parentId,bdmTlMst.getBdmsFinalcause()));
					String Cause = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_CAUSEMST, "BCSM_NAME", "BCSM_PHENOMENAID", bdmTlMst.getBdmsFinalcause());
					if(checkPhnLinkExists.equals("0"))
					{
						insertPhenCauseLink(parentId,bdmTlMst.getBdmsFinalcause(),Cause,"CAS",sqls);
						
					}
				}
				//System.out.println("SQL"+sqls.get(1));
			}
			checkAndInsertRepeatedBdWhyWhy(bdmTlMst, sqls);
			
			dbActionTemplate.executeStatements(sqls);
			
		
		
		return bdmTlMst;
	}
	



	public BAL_BdmTlMst deletePcsbd(BAL_BdmTlMst bdmTlMst) throws Exception 
		{
			List<String> sqls = new ArrayList<String>();
			BAL_BdmTlMstSql bdmTlMstSql = new BAL_BdmTlMstSql();
			try {
				System.out.println("Inside the DaoImpl Delete");
				List<BAL_BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
				List<BAL_PcsTlPcsBd> pcsbd = bdmTlMst.getPcsbd();
				String woId = "";
				CommonFunctions.debugMsg("------------------------->"+bdmTlMst.getBdmsWoprodaccepflag());
				CommonFunctions.debugMsg("------------------------->"+bdmTlMst.getBdmsReporteddate());
				
				/*if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoprodaccepflag()))
				{
					if(bdmTlMst.getBdmsWoprodaccepflag().equals("Y"))
					{*/
						woId = dbActionTemplate.getSingleValue(TableNames.TBL_WOM_TL_MST,"WOMS_KEYID", "WOMS_ACTIVITYID", bdmTlMst.getBdmsKeyid());
						if(UIUtils.isValidKeyId(woId))
						{
							CommonFunctions.debugMsg("ID : "+woId);
							sqls.add(BAL_BdmTlMstSql.getUpdateWoSql(bdmTlMst.getBdmsReporteddate(),woId));
						}
					/*}
				}*/
				if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
				{	
					System.out.println("Inside the detail table"+pcsbd.size()+"        "+pcsbd);
					for( BAL_BdmTlDtl bdmTlDtl : bdmTlDtls ){
						bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
						String bdanId = dbActionTemplate.getSingleValue(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL,"BDAN_KEYID", "BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid());
						bdmTlDtl.setBdanKeyid(bdanId);
						sqls.add(BAL_BdmTlDtlSql.getDeleteSqlPbd(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}
					
				}
						BAL_PcsTlPcsBd pcsTlbd= new BAL_PcsTlPcsBd();
						pcsTlbd.setPcsBdmsKeyid(bdmTlMst.getBdmsKeyid());
						String pcsbdkeyid = dbActionTemplate.getSingleValue(BAL_PcsTlPcsBdSql.TBL_PCS_TL_PCSBD,"PBD_KEYID", "PBD_BDM_KEYID", bdmTlMst.getBdmsKeyid());
						
						pcsTlbd.setPcsbdKeyid(pcsbdkeyid);
						
						sqls.add(BAL_PcsTlPcsBdSql.getDeleteSql(pcsBdSql.getPcsBdDbFields(), pcsTlbd.getSaveArray()));
						sqls.add(BAL_BdmTlMstSql.getDeleteSqlPcsBd(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
						sqls.add("delete from "+SapExternalServiceMstSql.TBL_SAP_EXTERNAL_SERVICE_MST+" where EXTM_NOTIFICATIONNO = '"+bdmTlMst.getBdmsKeyid()+"'");
						sqls.add("delete from "+BAL_BdmTlMultipleRespSql.TBL_BDM_TL_MULTIPLE_RESP +" where BDRS_REFID ='"+bdmTlMst.getBdmsKeyid()+"'");
						System.out.println("SQL Delete.........."+sqls);
						dbActionTemplate.executeStatements(sqls);
				
			} catch (Exception e) {
				
				throw new Exception(e.getMessage());
			}
			
			return bdmTlMst; 
			
			
		}

	
	
}

