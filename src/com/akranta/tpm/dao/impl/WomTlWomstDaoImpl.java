package com.akranta.tpm.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.MSROverlapsPCSException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.PcsEntryBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.bean.WOFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.WomTlWomstDao;
import com.akranta.tpm.dao.sql.AbnTlAbnormalitySql;
import com.akranta.tpm.dao.sql.BdmTlDtlSql;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.MldTlMouldunloadmstSql;
//import com.akranta.tpm.dao.sql.MldTlMouldunloadmstSql;
import com.akranta.tpm.dao.sql.PcsTlDtlSql;
import com.akranta.tpm.dao.sql.PcsTlLossreasonlinkSql;
import com.akranta.tpm.dao.sql.PcsTlMstSql;
import com.akranta.tpm.dao.sql.PcsTlWorkorderlinkSql;
import com.akranta.tpm.dao.sql.PlmTlGenmaintenanceSql;
import com.akranta.tpm.dao.sql.PlmTlUnplannedmaintdtlSql;
import com.akranta.tpm.dao.sql.PlmTlUnplannedmaintmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.WomTlWomstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MldTlMouldunloadmst;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.PCSConstants;
import com.akranta.tpm.utils.WOConstants;

/* dao implementation */
public class WomTlWomstDaoImpl implements WomTlWomstDao {


	private DBActionTemplate dbActionTemplate; 
	private BdmTlMstSql bdmTlMstSql = null;
	private BdmTlDtlSql bdmTlDtlSql = null;
	
	  private PlmTlUnplannedmaintmstSql plmTlUnplannedmaintmstSql = null; 
	  private PlmTlUnplannedmaintdtlSql plmTlUnplannedmaintdtlSql = null;
	 

	public WomTlWomstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		bdmTlMstSql = new BdmTlMstSql();
		bdmTlDtlSql = new BdmTlDtlSql();
		
		  plmTlUnplannedmaintmstSql= new PlmTlUnplannedmaintmstSql();
		  plmTlUnplannedmaintdtlSql = new PlmTlUnplannedmaintdtlSql();
		 
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> isMSRExist(WomTlWomst womTlWomst) throws Exception
	{
		String occuredDate = womTlWomst.getWomsOccurreddate();	
		if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17)
			occuredDate = occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
		List<String> paramVals = new ArrayList<String>();
		paramVals.add(womTlWomst.getWomsKeyid());		
		paramVals.add(occuredDate);		
		paramVals.add(womTlWomst.getWomsMachineid());
		List<String[]>  overlapFlag = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISMSROCCURS", paramVals);
		return overlapFlag;
	}
	public WomTlWomst create(WomTlWomst womTlWomst) 	throws Exception,BusinessApplicationExceptions{

		/*String checkWOConfig = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "MSROCCUREDDATE");
		if(UIUtils.isValidKeyId(checkWOConfig))
		{
			int noOfDays = Integer.parseInt(checkWOConfig);
			int selDays = CommonFunctions.getDateDiff(womTlWomst.getWomsOccurreddate(),womTlWomst.getWomsCreatedon());
			if(selDays > noOfDays)
				throw new BusinessApplicationExceptions("MAXBOOKINGDATE,");			
		}*/
			
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		WomTlWomstSql womTlWomstSql = new WomTlWomstSql(); // contains dbtable,field names, Field types and related sqls  of master table
			String startTime = womTlWomst.getWomsOccurreddate().split(" ")[1];
			if(!UIUtils.isValidKeyId(startTime))
				startTime = "00:00";
			
			String sql = BdmTlMstSql.getShiftFunction();
			List<String> paramVal = new ArrayList<String>();			
			paramVal.add(womTlWomst.getWomsFactoryid());
			paramVal.add(womTlWomst.getWomsSectionid());
			paramVal.add(womTlWomst.getWomsCellid());
			paramVal.add(startTime);
			
			
			List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramVal);
			if(fillShift.size()>0)
				womTlWomst.setWomsShiftid(fillShift.get(0)[0]);
			
			List<String> paramValues = new ArrayList<String>();			
			paramValues.add(womTlWomst.getWomsMachineid());
			paramValues.add(womTlWomst.getWomsShiftid());
			paramValues.add(womTlWomst.getWomsShiftdate());
				
			if(UIUtils.isValidKeyId(womTlWomst.getWomsActivitytype()))
			{
				if(womTlWomst.getWomsActivitytype().equals("G") || womTlWomst.getWomsActivitytype().equals("L"))
				{
					womTlWomst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST, 10, "MW", "YYMM", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number 
					sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add insert sql for master table
					/*List<String[]>  overlapFlag = isMSRExist(womTlWomst);
					if(overlapFlag.size()>0)
					{
						throw new BusinessApplicationExceptions("msrOverlap,");
					}*/
					dbActionTemplate.executeStatements(sqls);
				}
				else
				{
					
					List<String[]>  prodAvlTimeFlag = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ISPRODTIMEAVAILABLE", paramValues);
					
					String prodAvlTime = prodAvlTimeFlag.get(0)[0];
					
					if(CommonFunctions.isValidKeyId(prodAvlTime) && prodAvlTime.equals("1"))
					{
						womTlWomst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST, 10, "MW", "YYMM", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number 
						sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add insert sql for master table						
						//if(UIUtils.isValidKeyId(womTlWomst.getWomsActivitytype()) && womTlWomst.getWomsActivitytype().equals("B"))			
						//createBD(bdmTlMst,sqls);	
						/*List<String[]>  overlapFlag = isMSRExist(womTlWomst);
						if(overlapFlag.size()>0)
						{
							throw new BusinessApplicationExceptions("msrOverlap,");
						}*/
						dbActionTemplate.executeStatements(sqls); // execute the block of sqls		
					}
					else
					{
						throw new BusinessApplicationExceptions("prodAvlTime,");
					}
				}
			}
			else
			{
				List<String[]>  prodAvlTimeFlag = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ISPRODTIMEAVAILABLE", paramValues);
				
				String prodAvlTime = prodAvlTimeFlag.get(0)[0];
				
				if(CommonFunctions.isValidKeyId(prodAvlTime) && prodAvlTime.equals("1"))
				{
					womTlWomst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST, 10, "MW", "YYMM", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number 
					sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add insert sql for master table
					/*List<String[]>  overlapFlag = isMSRExist(womTlWomst);
					if(overlapFlag.size()>0)
					{
						throw new BusinessApplicationExceptions("msrOverlap,");
					}*/
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls		
				}
				else
				{
					throw new BusinessApplicationExceptions("prodAvlTime,");
				}
			}
			
		
		
		return womTlWomst;
	}
	public List<String> createBD(BdmTlMst bdmTlMst,List<String> sqls) 	throws Exception {
		bdmTlMst.setBdmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlMstSql.TBL_BDM_TL_MST, 11, "BDM", "MMYY", "Y"));//getSequenceNumber(BdmTlMstSql.TBL_BDM_TL_MST)); 
		sqls.add(BdmTlMstSql.getInsertSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray())); // add insert sql for master table
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			//CommonMessage.debugMsg("If Loop in MST Dao Impl");
			BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
			bdmDetail.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
			bdmDetail.setBdanKeyid(dbActionTemplate.getSequenceNumber(BdmTlDtlSql.TBL_BDM_TL_DTL));
			sqls.add(BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmDetail.getSaveArray()));// add insert sql for detail table
		}		
		
		return sqls;		
	}
	public String bdCreate(BdmTlMst bdmTlMst,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList) 	throws Exception {
		//List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		//bdmTlMst.setBdmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlMstSql.TBL_BDM_TL_MST)); // set the sequnce number
		bdmTlMst.setBdmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlMstSql.TBL_BDM_TL_MST, 11, "BDM", "YYMM", "Y"));
		sqls.add(BdmTlMstSql.getInsertSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray())); // add insert sql for master table
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
		//CommonMessage.debugMsg( "Size : " +bdmTlMst.getBdmDetail().size());
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			//CommonMessage.debugMsg("If Loop in MST Dao Impl");
			BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
			bdmDetail.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
			bdmDetail.setBdanKeyid(dbActionTemplate.getSequenceNumber(BdmTlDtlSql.TBL_BDM_TL_DTL,11,"BDA","YYMM","Y"));
			sqls.add(BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmDetail.getSaveArray()));// add insert sql for detail table
			valueList.add(null);
			dataTypes.add(null);
			typeList.add("Q");
		}
		return bdmTlMst.getBdmsKeyid();
		//dbActionTemplate.executeStatements(sqls);
		//return sqls;		
	}
	public String plmCreate(BdmTlMst bdmTlMst,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList) 	throws Exception {
		//List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();
		
		plmTlUnplannedmaintmst = (PlmTlUnplannedmaintmst) UIUtils.copyObject(bdmTlMst, plmTlUnplannedmaintmst);
		plmTlUnplannedmaintmst.setUpmmKeyid(dbActionTemplate.getSequenceNumber(PlmTlUnplannedmaintmstSql.TBL_PLM_TL_UNPLANNEDMAINTMST,11,"UPM","YYMM","Y"));
		sqls.add(PlmTlUnplannedmaintmstSql.getInsertSql(plmTlUnplannedmaintmstSql.getUpmmDbFields(), plmTlUnplannedmaintmst.getSaveArray())); // add insert sql for master table
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			
			BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
			PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
			plmTlUnplannedmaintdtl = (PlmTlUnplannedmaintdtl)UIUtils.copyObject(bdmDetail, plmTlUnplannedmaintdtl);
			plmTlUnplannedmaintdtl.setUpmdUpmmKeyid(plmTlUnplannedmaintmst.getUpmmKeyid());
			plmTlUnplannedmaintdtl.setUpmdKeyid(dbActionTemplate.getSequenceNumber(PlmTlUnplannedmaintdtlSql.TBL_PLM_TL_UNPLANNEDMAINTDTL,11,"UPD","YYMM","Y"));
			sqls.add(PlmTlUnplannedmaintdtlSql.getInsertSql(plmTlUnplannedmaintdtlSql.getUpmdDbFields(), plmTlUnplannedmaintdtl.getSaveArray()));// add insert sql for detail table
			valueList.add(null);
			dataTypes.add(null);
			typeList.add("Q");
		}
		
		return plmTlUnplannedmaintmst.getUpmmKeyid();
		//dbActionTemplate.executeStatements(sqls);
		//return sqls;		
	}
	
	public void plmUpdate(BdmTlMst bdmTlMst,WomTlWomst womTlWomst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PlmTlUnplannedmaintmstSql plmTlUnplannedmaintmstSql = new PlmTlUnplannedmaintmstSql();
		try {
			CommonMessage.debugMsg("Inside the Unplanned maintenance upDate");
			bdmTlMst.setBdmsReceiveddate(womTlWomst.getWomsAllotteddate());
			PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();			
			plmTlUnplannedmaintmst = (PlmTlUnplannedmaintmst) UIUtils.copyObject(bdmTlMst, plmTlUnplannedmaintmst);
			sqls.add(PlmTlUnplannedmaintmstSql.getUpdateSql(plmTlUnplannedmaintmstSql.getUpmmDbFields(), plmTlUnplannedmaintmst.getSaveArray()));
			
			if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
			{	
				
				BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
				PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
				plmTlUnplannedmaintdtl = (PlmTlUnplannedmaintdtl)UIUtils.copyObject(bdmDetail, plmTlUnplannedmaintdtl);
				sqls.add(PlmTlUnplannedmaintdtlSql.getUpdateSql(plmTlUnplannedmaintdtlSql.getUpmdDbFields(), plmTlUnplannedmaintdtl.getSaveArray()));
			}
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}		
		
	}
	
	public String abnCreate(AbnTlAbnormality abnTlAbnormality,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList) 	throws Exception {

	//	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		//try{
			//CommonMessage.debugMsg("ABN " +abnTlAbnormality.getAbnmTargetremarks() + abnTlAbnormality.getAbnmRemarks());
			String chkTypeExist = dbActionTemplate.getSingleValue(AbnTlAbnormalitySql.chkTypeExistSql());
			CommonMessage.debugMsg("type : "+chkTypeExist);
			if(CommonFunctions.isValidKeyId(chkTypeExist))
			{
				//dbActionTemplate.executeStatement(AbnTlAbnormalitySql.insertABNTypeSql());
				if(chkTypeExist.equals("0"))
				{
				sqls.add(AbnTlAbnormalitySql.insertABNTypeSql());
				valueList.add(null);
				dataTypes.add(null);
				typeList.add("Q");
				}
			}
			String tagClass = dbActionTemplate.getSingleValue("SELECT TAGM_KEYID FROM "+TableNames.TBL_ABN_TL_TAGMST+" WHERE TAGM_NAME='RED'");
			abnTlAbnormality.setAbnmTagclassid(tagClass);
			abnTlAbnormality.setAbnmTypeid(AbnTlAbnormalitySql.ABN_TYPE_VAL);	
			abnTlAbnormality.setAbnmKeyid(dbActionTemplate.getSequenceNumber(AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY)); // set the sequnce number 
			sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table
			valueList.add(null);
			dataTypes.add(null);
			typeList.add("Q");
			
			return abnTlAbnormality.getAbnmKeyid();
			//dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
			
	//	}catch(Exception e)
	//	{
	//		throw new Exception(e.getMessage());
	//	}
		
	}
	public String gmCreate(WomTlWomst womTlWomst,PlmTlGenmaintenance plmTlGenmaintenance,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList) 	throws Exception {

//		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlGenmaintenanceSql plmTlGenmaintenancesql = new PlmTlGenmaintenanceSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		plmTlGenmaintenance.setGmntKeyid(dbActionTemplate.getSequenceNumber(PlmTlGenmaintenanceSql.TBL_PLM_TL_GENMAINTENANCE, 15, "GMN", "MMYY", "Y")); // set the sequnce number
//		CommonMessage.debugMsg("KEY ID  NDNNDNN  "+plmTlGenmaintenance.getGmntKeyid());
		CommonMessage.debugMsg(plmTlGenmaintenance.getGmntBookeddate());
		womTlWomst.setWomsActivityid(plmTlGenmaintenance.getGmntKeyid());
		sqls.add(PlmTlGenmaintenanceSql.getInsertSql(plmTlGenmaintenancesql.getGmntDbFields(), plmTlGenmaintenance.getSaveArray())); // add insert sql for master table
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
		
		return plmTlGenmaintenance.getGmntKeyid();
	//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		
	}
	public String mouldUnloadCreate(WomTlWomst womTlWomst,MldTlMouldunloadmst mldTlMouldunloadmst,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList) 	throws Exception {
		MldTlMouldunloadmstSql mldTlMouldunloadmstSql = new MldTlMouldunloadmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		mldTlMouldunloadmst.setMunlKeyid(dbActionTemplate.getSequenceNumber(MldTlMouldunloadmstSql.TBL_MLD_TL_MOULDUNLOADMST, 10, "MUL", "MMYY", "Y")); // set the sequnce number
		CommonMessage.debugMsg(mldTlMouldunloadmst.getMunlBookeddate());
		womTlWomst.setWomsActivityid(mldTlMouldunloadmst.getMunlKeyid());
		sqls.add(MldTlMouldunloadmstSql.getInsertSql(mldTlMouldunloadmstSql.getMunlDbFields(), mldTlMouldunloadmst.getSaveArray())); // add insert sql for master table
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
		
		return mldTlMouldunloadmst.getMunlKeyid();
	}
	public void abnUpdate(AbnTlAbnormality abnTlAbnormality,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList)	throws Exception { 
		
		AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql();  // contains dbtable,field names, Field types and related sqls  of master table
		sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray()));
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
	}
	public void gmUpdate(PlmTlGenmaintenance plmTlGenmaintenance,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList)	throws Exception { 
		
		//List<String> sqls = new ArrayList<String>();
		PlmTlGenmaintenanceSql plmTlGenmaintenancesql = new PlmTlGenmaintenanceSql(); // contains dbtable,field names, Field types and related sqls  of master table
		//try {
		
		sqls.add(PlmTlGenmaintenanceSql.getUpdateSql(plmTlGenmaintenancesql.getGmntDbFields(), plmTlGenmaintenance.getSaveArray()));
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
			//	dbActionTemplate.executeStatements(sqls);
		/*	} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		 */	
	}
	public void mouldUnloadUpdate(MldTlMouldunloadmst mldTlMouldunloadmst,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes,List<String> typeList)	throws Exception { 
		MldTlMouldunloadmstSql mldTlMouldunloadmstSql = new MldTlMouldunloadmstSql(); 
		sqls.add(MldTlMouldunloadmstSql.getUpdateSql(mldTlMouldunloadmstSql.getMunlDbFields(), mldTlMouldunloadmst.getSaveArray()));
		valueList.add(null);
		dataTypes.add(null);
		typeList.add("Q");
	}

public void bdUpdate(BdmTlMst bdmTlMst,WomTlWomst womTlWomst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		//BdmTlMstSql bdmTlMstSql = new BdmTlMstSql();
		try {
			//CommonMessage.debugMsg("Inside the upDate");
			bdmTlMst.setBdmsReceiveddate(womTlWomst.getWomsAllotteddate());
			//CommonMessage.debugMsg("Inside the Unplanned maintenance upDate : "+bdmTlMst.getBdmsReceiveddate());
			sqls.add(BdmTlMstSql.getUpdateSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
			//List<BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
			if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
			{	
				BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
				//String dtlKey = getBdDtlKey(bdmDetail.getBdanBdms_keyid());
				sqls.add(BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmDetail.getSaveArray()));
			
			/*if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
			{	
				for( BdmTlDtl bdmTlDtl : bdmTlDtls ){
					bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
					String dtlKey = getBdDtlKey(bdmTlDtl.getBdanBdms_keyid());
					if( ! dbActionTemplate.checkDuplicateValue(BdmTlDtlSql.TBL_BDM_TL_DTL,
									"BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid(), "") )
					{	
						sqls.add(BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
					}	
					else{
						String dtlKey = getBdDtlKey(bdmTlDtl.getBdanBdms_keyid());
						if(CommonFunctions.isValidKeyId(dtlKey))
							bdmTlDtl.setBdanKeyid(dtlKey);
						sqls.add(BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
					}	
				}*/
			
			}
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {			
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}		
		
	}
	
	public  String getBdKey(String woKey)
	{
		try {
			return dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_MST, "BDMS_KEYID", "BDMS_WNO", woKey);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}
	public  String  getKeyId(String tableName,String keyIdCol,String woKeyCol,String woKey)
	{
		try {
			return dbActionTemplate.getSingleValue(tableName, keyIdCol, woKeyCol, woKey);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}
	public  String getBdDtlKey(String bdKey)
	{
		try {
			return dbActionTemplate.getSingleValue("BDM_TL_DTL", "BDAN_KEYID", "BDAN_BDMS_KEYID", bdKey);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return null;
	}
	
	public WomTlWomst update(WomTlWomst womTlWomst)	throws Exception,BusinessApplicationExceptions{ 
		
		/*String checkWOConfig = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "MSROCCUREDDATE");
		if(UIUtils.isValidKeyId(checkWOConfig))
		{
			int noOfDays = Integer.parseInt(checkWOConfig);
			int selDays = CommonFunctions.getDateDiff(womTlWomst.getWomsOccurreddate(),womTlWomst.getWomsCreatedon());
			if(selDays > noOfDays)
				throw new BusinessApplicationExceptions("MAXBOOKINGDATE,");			
		}*/
	/*	List<String[]>  overlapFlag = isMSRExist(womTlWomst);
		if(overlapFlag.size()>0)
		{
			throw new BusinessApplicationExceptions("msrOverlap,");
		}*/
		List<String> sqls = new ArrayList<String>();
		WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
		String startTime = womTlWomst.getWomsOccurreddate().split(" ")[1];
		if(!UIUtils.isValidKeyId(startTime))
			startTime = "00:00";
		
		String sql = BdmTlMstSql.getShiftFunction();
		List<String> paramVal = new ArrayList<String>();			
		paramVal.add(womTlWomst.getWomsFlid());
		/*paramVal.add(womTlWomst.getWomsSectionid());
		paramVal.add(womTlWomst.getWomsMachineid());
		paramVal.add(womTlWomst.getWomsCellid());
		paramVal.add(startTime);*/
		paramVal.add("");
		paramVal.add("");
		paramVal.add("");
		
		List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramVal);
		CommonMessage.debugMsg("Shift : "+fillShift.get(0)[0]);
		womTlWomst.setWomsShiftid(fillShift.get(0)[0]);
	
			List<String> paramValues = new ArrayList<String>();			
			paramValues.add(womTlWomst.getWomsMachineid());
			paramValues.add(womTlWomst.getWomsShiftid());
			paramValues.add(womTlWomst.getWomsShiftdate());
			
			if(UIUtils.isValidKeyId(womTlWomst.getWomsActivitytype()))
			{
				if(womTlWomst.getWomsActivitytype().equals("GN") || womTlWomst.getWomsActivitytype().equals("L"))
				{
					sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
					dbActionTemplate.executeStatements(sqls);	
				}
				else
				{
					List<String[]>  prodAvlTimeFlag = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ISPRODTIMEAVAILABLE", paramValues);
					
					String prodAvlTime = prodAvlTimeFlag.get(0)[0];
					
					if(CommonFunctions.isValidKeyId(prodAvlTime) && prodAvlTime.equals("1"))
					{
						sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
						dbActionTemplate.executeStatements(sqls);				
					}
					else
					{
						throw new BusinessApplicationExceptions("prodAvlTime,");
					}
				}
			}
			else
			{
				List<String[]>  prodAvlTimeFlag = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ISPRODTIMEAVAILABLE", paramValues);
				
				String prodAvlTime = prodAvlTimeFlag.get(0)[0];
				
				if(CommonFunctions.isValidKeyId(prodAvlTime) && prodAvlTime.equals("1"))
				{
					sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
					dbActionTemplate.executeStatements(sqls);				
				}
				else
				{
					throw new BusinessApplicationExceptions("prodAvlTime,");
				}
			}
		
		return womTlWomst;
	}
	public WomTlWomst updateApproval(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )  throws Exception
	{
		String approvalUpdateSql = WomTlWomstSql.getUpdateApprovalSql();
		String cancelWorkOrderSql = WomTlWomstSql.getcancelWorkOrderSql();
		//String updateApprovalBDSql = WomTlWomstSql.getUpdateApprovalBDSql();
		//String updateApprovalBDDtlSql = WomTlWomstSql.getUpdateApprovalBDDtlSql();
		
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		if(CommonFunctions.isValidKeyId(newWomTlWomst.getWomsRequestapproved()))
		{
			if(newWomTlWomst.getWomsRequestapproved().equals("A"))
			{
				CommonMessage.debugMsg("Approval Flag : "+newWomTlWomst.getWomsRequestapproved());
				String timeFormat = "dd-MMM-yyyy HH:mm";
				SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
				newWomTlWomst.setWomsStatus("A");
				Object [] updateApproval	= { newWomTlWomst.getWomsRequestapproved(),newWomTlWomst.getWomsRequestapprovedby(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsRequestapproveddate()).getTime()),newWomTlWomst.getWomsRequestapprovremarks(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsKeyid()};
				int [] dataTypes1 =  { Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
				sqls.add(approvalUpdateSql);
				valueList.add(updateApproval);
				dataTypes.add(dataTypes1);
				
			/*	String bdKey = getBdKey(newWomTlWomst.getWomsKeyid());
				String actType = dbActionTemplate.getSingleValue(WomTlWomstSql.TBL_WOM_TL_WOMST, "WOMS_ACTIVITYTYPE", "WOMS_KEYID", newWomTlWomst.getWomsKeyid());
				if(UIUtils.isValidKeyId(actType) && actType.equals("B"))
				{
					Object [] updateApprovalBd	= { newWomTlWomst.getWomsRequestapprovremarks(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsKeyid()};
					int [] dataTypesBd =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				
					sqls.add(updateApprovalBDSql);
					valueList.add(updateApprovalBd);
					dataTypes.add(dataTypesBd);
					
					Object [] updateApprovalBdDtl	= { newWomTlWomst.getWomsRequestapprovedby(),newWomTlWomst.getWomsRequestapproved(),newWomTlWomst.getWomsRequestapprovremarks(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsStatus(),bdKey};
					int [] dataTypesBdDtl =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				
					sqls.add(updateApprovalBDDtlSql);
					valueList.add(updateApprovalBdDtl);
					dataTypes.add(dataTypesBdDtl);
				}*/
				dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
				return newWomTlWomst;
			
			}
			else if(newWomTlWomst.getWomsRequestapproved().equals("C"))
			{
				newWomTlWomst.setWomsRequestapproved("C");
				newWomTlWomst.setWomsActive("N");
				newWomTlWomst.setWomsStatus("D");
				Object [] cancelApproval	= { newWomTlWomst.getWomsRequestapproved(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsActive(),newWomTlWomst.getWomsKeyid()};
				int [] dataTypes1 =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};

				sqls.add(cancelWorkOrderSql);
				valueList.add(cancelApproval);
				dataTypes.add(dataTypes1);
				
				dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
				return newWomTlWomst;
			}
		}
		return newWomTlWomst;
		
		
	}
	public WomTlWomst cancelWorkOrder(WomTlWomst womTlWomst)  throws Exception
	{
		String cancelWorkOrderSql = WomTlWomstSql.getcancelWorkOrderSql();
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		
		Object [] cancelApproval	= { womTlWomst.getWomsRequestapproved(),womTlWomst.getWomsStatus(),womTlWomst.getWomsActive(),womTlWomst.getWomsKeyid()};
		int [] dataTypes1 =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		sqls.add(cancelWorkOrderSql);
		valueList.add(cancelApproval);
		dataTypes.add(dataTypes1);
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		return womTlWomst;
	}
	public WomTlWomst updateCreation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,BdmTlMst bdmTlMst)  throws Exception
	{
		String creationUpdateSql = WomTlWomstSql.getUpdateCreationSql();
		
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		CommonMessage.debugMsg(creationUpdateSql);
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
		
		//CommonMessage.debugMsg(sdf.parse(newWomTlWomst.getWomsProposedstartdate()));
		CommonMessage.debugMsg(sdf.parse(newWomTlWomst.getWomsProposedenddate()));
		Object [] updateCreation	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsAccepteddate()).getTime()), newWomTlWomst.getWomsAcceptedby(),newWomTlWomst.getWomsSafetypermitsrequried(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProposedstartdate()).getTime()),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProposedenddate()).getTime()),newWomTlWomst.getWomsAcceptedremarks(),newWomTlWomst.getWomsAcceptedflag(),newWomTlWomst.getWomsProposedstflag(),newWomTlWomst.getWomsProposedendflag(),newWomTlWomst.getWomsProductionapproval(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsKeyid()};
		int [] dataTypes1 =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		sqls.add(creationUpdateSql);
		valueList.add(updateCreation);
		dataTypes.add(dataTypes1);
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		//if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivitytype()) && oldWomTlWomst.getWomsActivitytype().equals("B"))			
			//bdCreate(bdmTlMst);			
		
		return newWomTlWomst;
	}
	public WomTlWomst updateAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,BdmTlMst bdmTlMst)  throws Exception
	{
		String creationUpdateAcceptanceSql = WomTlWomstSql.getUpdateCreationAcceptanceSql();
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
		
		Object [] updateAcceptance	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsRescheduledate()).getTime()), newWomTlWomst.getWomsRescheduleby(),newWomTlWomst.getWomsRescheduleflag(),newWomTlWomst.getWomsRescheduledstflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsReschedulestartdate()).getTime()),newWomTlWomst.getWomsRescheduledendflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsRescheduleenddate()).getTime()),newWomTlWomst.getWomsRescheduleremarks(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsKeyid()};
		int [] dataTypes1 =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		sqls.add(creationUpdateAcceptanceSql);
		valueList.add(updateAcceptance);
		dataTypes.add(dataTypes1);
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		/*if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivitytype()) && oldWomTlWomst.getWomsActivitytype().equals("B"))
		{
			String bdKey = null;
			if(UIUtils.isValidKeyId(newWomTlWomst.getWomsKeyid()))
			{
			   bdKey = getBdKey(newWomTlWomst.getWomsKeyid());
			   bdmTlMst.setBdmsKeyid(bdKey);
			   bdUpdate(bdmTlMst);
			}
		}*/
			
		
		return newWomTlWomst;
	}
	public WomTlWomst updateAllocation(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,BdmTlMst bdmTlMst,AbnTlAbnormality abnTlAbnormality,PlmTlGenmaintenance plmTlGenmaintenance,MldTlMouldunloadmst mldTlMouldunloadmst )  throws Exception
	{
		
		String parameterCode = null;	
		String occuredDate = oldWomTlWomst.getWomsOccurreddate();
		String prodStartDate = oldWomTlWomst.getWomsProductionstartdate();
		
		if(UIUtils.isValidKeyId(occuredDate))
		{
			if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17)
				occuredDate = occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
		}
		
		if(UIUtils.isValidKeyId(prodStartDate))
		{
			if(prodStartDate.indexOf(" ") > 0 && prodStartDate.length() > 17)
				prodStartDate = prodStartDate.substring(0, prodStartDate.indexOf(" ") + 6);
		}
		
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsRelatedto()))
		{
			if(oldWomTlWomst.getWomsRelatedto().equals("MCH"))
				parameterCode = "MCHFAIL";
			else if(oldWomTlWomst.getWomsRelatedto().equals("MLD"))
				parameterCode = "MLDFAIL";							
		}	
	
			
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
		
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);

		boolean breakdown = false;
		boolean MachineActivity = false;
		boolean unplanned = false;
		boolean abn = false;
		boolean mouldUnloading = false;
		String keyId = null;
		 
		boolean activityFlag=false;
		if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
		{
			if(newWomTlWomst.getWomsFinalactivitytype().equals("BD"))
					breakdown = true;
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("GN"))
				MachineActivity = true;
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("AB"))
				abn = true;
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("S"))
				MachineActivity = true;
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("L"))
				mouldUnloading = true;
		}
		List <String[]> pcsFlagList = dbActionTemplate.getDataList(WomTlWomstSql.checkPCSInsertEnable());
		/*if(pcsFlagList.size()>1)
		{
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[0]) && pcsFlagList.get(1)[0].equals("1"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[0]) && pcsFlagList.get(0)[0].equals("1"))
				{
					if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
					{
						if(newWomTlWomst.getWomsFinalactivitytype().equals("B"))
								breakdown = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[1]) && pcsFlagList.get(1)[1].equals("2"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[1]) && pcsFlagList.get(0)[1].equals("2"))
				{
					if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
					{
						if(newWomTlWomst.getWomsFinalactivitytype().equals("G"))
							MachineActivity = true;
					}
					
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[2]) && pcsFlagList.get(1)[2].equals("4"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[2]) && pcsFlagList.get(0)[2].equals("4"))
				{
					if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
					{
						if(newWomTlWomst.getWomsFinalactivitytype().equals("U"))
							unplanned = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[3]) && pcsFlagList.get(1)[3].equals("8"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[3]) && pcsFlagList.get(0)[3].equals("8"))
				{
					if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
					{
						if(newWomTlWomst.getWomsFinalactivitytype().equals("A"))
							abn = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[4]) && pcsFlagList.get(1)[4].equals("16"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[4]) && pcsFlagList.get(0)[4].equals("16"))
				{
					if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
					{
						if(newWomTlWomst.getWomsFinalactivitytype().equals("S"))
							MachineActivity = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[5]) && pcsFlagList.get(1)[5].equals("32"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[5]) && pcsFlagList.get(0)[5].equals("32"))
				{
					if(FilterCondSql.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
					{
						if(newWomTlWomst.getWomsFinalactivitytype().equals("L"))
							mouldUnloading = true;
					}
				}
			}
				
		}
		String keyId = null;
		boolean insertPCS=false;
		boolean activityFlag=false;
		String insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEMSR";
		if(UIUtils.isValidKeyId(newWomTlWomst.getWomsFinalactivitytype()))
		{
			
			
				CommonMessage.debugMsg("parameterCode : "+parameterCode);
				List<String> paramValues = new ArrayList<String>();			
				paramValues.add(oldWomTlWomst.getWomsMachineid());
				paramValues.add(occuredDate);
				paramValues.add(prodStartDate);
				paramValues.add(parameterCode);
					
				List<String[]>  prodAvlTimeFlag = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ISPRODAVAILFORCOMPLETE", paramValues);
					
				if(prodAvlTimeFlag.size()>0)
				{
					String tableFlag = prodAvlTimeFlag.get(0)[1];  			 Condition To validate 
					String msg = prodAvlTimeFlag.get(0)[0];
					if(CommonFunctions.isValidKeyId(tableFlag))
					{
						if(tableFlag.equals(PCSConstants.PRODUCTION_EXISTS) || tableFlag.equals(PCSConstants.TIME_EXCEEDS))
						{
							throw new MSROverlapsPCSException(msg);
						}
						else if(tableFlag.equals(PCSConstants.NOOVERLAP))
						{
							if(!newWomTlWomst.getWomsFinalactivitytype().equals("L"))
							{
								insertPCS = true;
								if(newWomTlWomst.getWomsFinalactivitytype().equals("A"))
								{
									insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEABN";
									parameterCode = newWomTlWomst.getWomsLoss();	
									if(UIUtils.isValidKeyId(parameterCode))
									{
										if(parameterCode.equals("UNPLANNED MAINTENANCE"))
											parameterCode = "UPM";
										else if(parameterCode.equals("JH TAG REMOVAL"))
											parameterCode = "JHTAGREMOVE";
										else if(parameterCode.equals("M AND A"))
											parameterCode = "MANDA";
									}
								}
							}
							activityFlag = true;
						}
						
					}
					else
					{
						activityFlag = true;
					}
				}
			
		}
		else
		{
			activityFlag = true;
		}*/
		activityFlag = true;
		if(activityFlag)
		{
		
	
			if(newWomTlWomst.getWomsFinalactivitytype().equals("BD"))
			{
				
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid()))
				{
					String AllottedBDSql = WomTlWomstSql.getUpdateAllocationBDSql();		
					Object [] updateAllocationBD	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsAllotteddate()).getTime()),newWomTlWomst.getWomsRelatedto(), oldWomTlWomst.getWomsActivityid()};
					int [] dataTypesBD =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
					sqls.add(AllottedBDSql);
					valueList.add(updateAllocationBD);
					dataTypes.add(dataTypesBD);
					charList.add("Q");	
					keyId = oldWomTlWomst.getWomsActivityid();
				}
				else{
					CommonMessage.debugMsg("bdcreate  .......  ");
					keyId = bdCreate(bdmTlMst,sqls,valueList,dataTypes,charList);
				}
				
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("U"))
			{
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid()))
				{
					String AllottedUMSql = WomTlWomstSql.getUpdateAllocationUMSql();		
					Object [] updateAllocationUM	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsAllotteddate()).getTime()),newWomTlWomst.getWomsRelatedto(),oldWomTlWomst.getWomsActivityid()};
					int [] dataTypesUM =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
					sqls.add(AllottedUMSql);
					valueList.add(updateAllocationUM);
					dataTypes.add(dataTypesUM);
					charList.add("Q");	
					keyId = oldWomTlWomst.getWomsActivityid();
				}
				else
					keyId = plmCreate(bdmTlMst,sqls,valueList,dataTypes,charList);
				
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("GN"))
			{
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid())){
					plmTlGenmaintenance.setGmntKeyid(oldWomTlWomst.getWomsActivityid());
					gmUpdate(plmTlGenmaintenance,sqls,valueList,dataTypes,charList);
					keyId = oldWomTlWomst.getWomsActivityid();
				}	
				else
					keyId = gmCreate(newWomTlWomst,plmTlGenmaintenance,sqls,valueList,dataTypes,charList);
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("S"))
			{
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid())){
					plmTlGenmaintenance.setGmntKeyid(oldWomTlWomst.getWomsActivityid());
					gmUpdate(plmTlGenmaintenance,sqls,valueList,dataTypes,charList);
					keyId = oldWomTlWomst.getWomsActivityid();
				}	
				else
					keyId = gmCreate(newWomTlWomst,plmTlGenmaintenance,sqls,valueList,dataTypes,charList);
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("AB"))
			{
				CommonMessage.debugMsg("IDDDDDDDDDDDDDDDDDDDDDDDDDD : "+oldWomTlWomst.getWomsActivityid());
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid())){
					abnTlAbnormality.setAbnmKeyid(oldWomTlWomst.getWomsActivityid());
					abnUpdate(abnTlAbnormality,sqls,valueList,dataTypes,charList);
					keyId = oldWomTlWomst.getWomsActivityid();
				}	
				else
				{
					keyId = abnCreate(abnTlAbnormality,sqls,valueList,dataTypes,charList);			
				
				}
				if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsProblem()))
					newWomTlWomst.setWomsProblem(oldWomTlWomst.getWomsProblem());
				
			}
			else if(newWomTlWomst.getWomsFinalactivitytype().equals("L"))
			{
				
					//sqls.add("Update "+TableNames.TBL_GEN_TL_MOULDMST+" Set Mldm_Mould_Status='"+WOConstants.mldUnloadedStatus+"' Where Mldm_Mouldid = '"+womTlWomst.getWomsMouldid()+"'");
					if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivityid())){
						mldTlMouldunloadmst.setMunlKeyid(oldWomTlWomst.getWomsActivityid());
						mouldUnloadUpdate(mldTlMouldunloadmst,sqls,valueList,dataTypes,charList);
						keyId = oldWomTlWomst.getWomsActivityid();
					}	
					else
					{
						boolean changeMldStat = false;
						CommonMessage.debugMsg("mOULD sTATUS : "+changeMldStat);
						CommonMessage.debugMsg("getWomsMouldid : "+newWomTlWomst.getWomsMouldid());
						
							if(UIUtils.isValidKeyId(newWomTlWomst.getWomsMouldid()))
							{
								String mldAvl = dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM "+TableNames.TBL_GEN_TL_MOULDMST+" WHERE MLDM_MOULDID='"+newWomTlWomst.getWomsMouldid()+"'");
								CommonMessage.debugMsg("mldAvl : "+mldAvl);
								if(CommonFunctions.isValidKeyId(mldAvl))
								{
									if(Integer.parseInt(mldAvl)>0)
									{
										String mldStatus = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_MOULDMST, "MLDM_MOULD_STATUS", "MLDM_MOULDID", newWomTlWomst.getWomsMouldid());
										CommonMessage.debugMsg("mldStatus "+mldStatus);
										if(CommonFunctions.isValidKeyId(mldStatus))
										{
											if(mldStatus.equals("UNLOADED"))
											{
												throw new BusinessApplicationExceptions("mouldUnloaded,");
											}
											else
												changeMldStat = true;
											
										}
									}
								}
								
							}
							CommonMessage.debugMsg("mOULD FLAG : "+changeMldStat);
						if(changeMldStat)
						{
							keyId = mouldUnloadCreate(newWomTlWomst,mldTlMouldunloadmst,sqls,valueList,dataTypes,charList);
							String mldStatusSql = "Update "+TableNames.TBL_GEN_TL_MOULDMST+" Set Mldm_Mould_Status=? Where Mldm_Mouldid =?";
							Object [] updateMldStatus	= {WOConstants.mldUnloadedStatus,newWomTlWomst.getWomsMouldid()};
							int [] mldStatusType =  {Types.VARCHAR,Types.VARCHAR};
							sqls.add(mldStatusSql);
							valueList.add(updateMldStatus);
							dataTypes.add(mldStatusType);
							charList.add("Q");
						}
					}
			}
			if(UIUtils.isValidKeyId(keyId))
			{
				newWomTlWomst.setWomsActivityid(keyId);
			}
			
				
			String AllottedSql = WomTlWomstSql.getUpdateAllocationSql();
			Object [] updateAllocation	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsAllotteddate()).getTime()), newWomTlWomst.getWomsAllottedto(),newWomTlWomst.getWomsAllottedflag(),newWomTlWomst.getWomsAllottedremarks(),newWomTlWomst.getWomsAllottedsource(),newWomTlWomst.getWomsActivityid(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsFinalstatus(),newWomTlWomst.getWomsFinalactivitytype(),newWomTlWomst.getWomsModifiedby(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsModifiedon()).getTime()),newWomTlWomst.getWomsRelatedto(),newWomTlWomst.getWomsLoss(),newWomTlWomst.getWomsAcceptedflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsAccepteddate()).getTime()),newWomTlWomst.getWomsKeyid()};
			int [] dataTypes1 =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
			
			sqls.add(AllottedSql);
			valueList.add(updateAllocation);
			dataTypes.add(dataTypes1);
			charList.add("Q");
		}
		CommonMessage.debugMsg("insertPCS  -->("+breakdown+ ","+MachineActivity+ ","+unplanned+","+abn+")");
		/*if(insertPCS)
		{
			if(breakdown || abn || unplanned)
			{
				CommonMessage.debugMsg(" oldWomTlWomst.getWomsMachineid(),occuredDate,prodStartDate,oldWomTlWomst.getWomsKeyid(),parameterCode,newWomTlWomst.getWomsCreatedby() " + oldWomTlWomst.getWomsMachineid() + " " + occuredDate + " " + prodStartDate + " " +oldWomTlWomst.getWomsKeyid() + " " + parameterCode + " " + newWomTlWomst.getWomsCreatedby());
				Object [] insertPCSDatas = {oldWomTlWomst.getWomsMachineid(),occuredDate,prodStartDate,oldWomTlWomst.getWomsKeyid(),parameterCode,newWomTlWomst.getWomsCreatedby()};
				int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				sqls.add(insertPCSSql);
				valueList.add(insertPCSDatas);
				dataTypes.add(insertPCSTypes);
				charList.add("P");
			}
		}*/
		
		
		char [] sqlType = new char[charList.size()];
		CommonMessage.debugMsg("lENGTH : "+sqlType.length);
		for(int c=0;c<sqlType.length;c++)
		{
			CommonMessage.debugMsg(c +" : "+charList.get(c));
			sqlType[c] = charList.get(c).charAt(0);
			CommonMessage.debugMsg(c +" : "+sqlType[c]);
		}
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
		
	//	dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		return newWomTlWomst;
	}
	public WomTlWomst updateCompletion(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,BdmTlMst bdmTlMst)  throws Exception
	{
		String completionSql = WomTlWomstSql.getUpdateCompletionSql();
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
		String finalActivity = newWomTlWomst.getWomsFinalactivitytype();
		if(oldWomTlWomst != null)
		{
			if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
				finalActivity = oldWomTlWomst.getWomsFinalactivitytype();
		}
		Object [] updateCompletion	= {finalActivity,newWomTlWomst.getWomsActivityid(),newWomTlWomst.getWomsWorkstartflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsWorkstartdate()).getTime()),newWomTlWomst.getWomsWorkendflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsWorkenddate()).getTime()), newWomTlWomst.getWomsDoneby(),newWomTlWomst.getWomsIntorextequip(),newWomTlWomst.getWomsIntorextequipdesc(),newWomTlWomst.getWomsWoapprovalflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsWoapprovaldate()).getTime()),newWomTlWomst.getWomsWoapprovalby(),newWomTlWomst.getWomsMachinereleaseflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsMachinereleaseddate()).getTime()),newWomTlWomst.getWomsMachinereleaseby(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsKeyid()};
		int [] dataTypes1 =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		sqls.add(completionSql);
		valueList.add(updateCompletion);
		dataTypes.add(dataTypes1);
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		/*if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsActivitytype()) && oldWomTlWomst.getWomsActivitytype().equals("B"))
		{
			String bdKey = null;
			if(UIUtils.isValidKeyId(newWomTlWomst.getWomsKeyid()))
			{
			   bdKey = getBdKey(newWomTlWomst.getWomsKeyid());
			   bdmTlMst.setBdmsKeyid(bdKey);
			   bdUpdate(bdmTlMst);
			}
		}*/
		return newWomTlWomst;
	}
	/* (non-Javadoc)
	 * @see com.akranta.tpm.dao.WomTlWomstDao#updateProdAcceptance(com.akranta.tpm.model.WomTlWomst, com.akranta.tpm.model.WomTlWomst, com.akranta.tpm.bean.WOFormBean)
	 */
	public List<String[]>  checkOverlap(String occuredDate,String prodStartDate,String woId,String actType)  throws Exception
	{
		List<String> paramVal = new ArrayList<String>();
		paramVal.add(woId);
		paramVal.add(occuredDate);
		paramVal.add(prodStartDate);	
		paramVal.add(actType);	
		List<String[]>  overlapFlag = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISMSROVERLAP", paramVal);
		return overlapFlag;
	}
	public WomTlWomst updateProdAcceptance(WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean ,List<String[]> overlapFlag)  throws Exception
	{
		
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
		
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
		
		
		String parameterCode = null;	
		String occuredDate = oldWomTlWomst.getWomsOccurreddate();
		String prodStartDate = newWomTlWomst.getWomsProductionstartdate();
		
	
		if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17)
			occuredDate = occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
		
		if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsRelatedto()))
		{
			if(oldWomTlWomst.getWomsRelatedto().equals("MCH"))
				parameterCode = "MCHFAIL";
			else if(oldWomTlWomst.getWomsRelatedto().equals("MLD"))
				parameterCode = "MLDFAIL";							
		}	
		
		/* Check Overlap */	
		List<String> paramVal = new ArrayList<String>();
		paramVal.add(oldWomTlWomst.getWomsKeyid());
		paramVal.add(occuredDate);
		paramVal.add(prodStartDate);	
		//List<String[]>  overlapFlag = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISMSROVERLAP", paramVal);
		//CommonMessage.debugMsg("overlapFlag : "+overlapFlag.size());
		if(overlapFlag != null && overlapFlag.size()>0)
		{
			int j=0;
			
			String inactSql = WomTlWomstSql.woInactiveSql();
			String inactbdSql = WomTlWomstSql.bdInactiveSql();
			String inactBdDtlSql = WomTlWomstSql.bdDtlInactiveSql();
			String inactupmSql = WomTlWomstSql.upmInactiveSql();
			String inactUPMDtlSql = WomTlWomstSql.upmDtlInactiveSql();
			String inactMASql = WomTlWomstSql.maInactiveSql();
			String inactMUSql = WomTlWomstSql.MUInactiveSql();
			
			String inActParam = "";
			String inActbdParam = "";			
			String inActUpmParam = "";		
			String inActMAParam = "";
			String inActMUParam = "";
			
			List<String> woKeyId = new ArrayList<String>();
			List<String> bdKeyId = new ArrayList<String>();			
			List<String> upmKeyId = new ArrayList<String>();		
			List<String> maKeyId = new ArrayList<String>();
			List<String> muKeyId = new ArrayList<String>();
			
			String keyIds = null;
			
			while(j<overlapFlag.size())
			{
				String keyId = overlapFlag.get(j)[0];
				String actType = overlapFlag.get(j)[1];
				String actid = overlapFlag.get(j)[2];
				CommonMessage.debugMsg("keyId : "+keyId);
				CommonMessage.debugMsg("actType : "+actType);
				CommonMessage.debugMsg("actid : "+actid);
				if(UIUtils.isValidKeyId(keyId))
				{
					inActParam += "?,";					
					woKeyId.add(keyId);
					if(UIUtils.isValidKeyId(keyIds))
						keyIds = keyIds +","+ keyId;
					else
						keyIds = keyId;
				}
				if(UIUtils.isValidKeyId(actType))
				{
					if(actType.equals("BD"))
					{
						if(UIUtils.isValidKeyId(actid))
						{
							inActbdParam += "?,";					
							bdKeyId.add(actid);							
						}
						
					}
					else if(actType.equals("U"))
					{
						if(UIUtils.isValidKeyId(actid))
						{
							inActUpmParam += "?,";					
							upmKeyId.add(actid);
						}
						
					}
					else if(actType.equals("GN"))
					{
						if(UIUtils.isValidKeyId(actid))
						{
							inActMAParam += "?,";					
							maKeyId.add(keyId);
						}
					}
					else if(actType.equals("S"))
					{
						if(UIUtils.isValidKeyId(actid))
						{
							inActMAParam += "?,";					
							maKeyId.add(keyId);
						}
					}
					else if(actType.equals("L"))
					{
						if(UIUtils.isValidKeyId(actid))
						{
							inActMUParam += "?,";					
							muKeyId.add(keyId);
						}
					}
					if(UIUtils.isValidKeyId(actid))
					{
						if(UIUtils.isValidKeyId(keyIds))
							keyIds = keyIds +","+ actid;
						else
							keyIds = actid;
					}


				}
				j++;
			}
			
		//	if(UIUtils.isValidKeyId(keyIds))
			//	throw new MSROverlapsPCSException("Overlap IDs : "+keyIds);
			/*String inactiveSql = WomTlWomstSql.woInactiveSql(); 
			Object [] inactiveKey = {keyId};
			int [] inactiveDatas =  {Types.VARCHAR};
			sqls.add(inactiveSql);
			valueList.add(inactiveKey);
			dataTypes.add(inactiveDatas);


			String inactiveBDSql = WomTlWomstSql.bdInactiveSql();
			Object [] inactiveBDKey = {actid};
			int [] inactiveBDDatas =  {Types.VARCHAR};
			sqls.add(inactiveBDSql);
			valueList.add(inactiveBDKey);
			dataTypes.add(inactiveBDDatas);
			
			String inactiveBDDtlSql = WomTlWomstSql.bdDtlInactiveSql();
			Object [] inactiveBDDtlKey = {actid};
			int [] inactiveBDDtlDatas =  {Types.VARCHAR};
			sqls.add(inactiveBDDtlSql);
			valueList.add(inactiveBDDtlKey);
			dataTypes.add(inactiveBDDtlDatas);

		
			String inactiveUPMSql = WomTlWomstSql.upmInactiveSql();
			Object [] inactiveUPMKey = {actid};
			int [] inactiveUPMDatas =  {Types.VARCHAR};
			sqls.add(inactiveUPMSql);
			valueList.add(inactiveUPMKey);
			dataTypes.add(inactiveUPMDatas);
			
			String inactiveUPMDtlSql = WomTlWomstSql.upmDtlInactiveSql();
			Object [] inactiveUPMDtlKey = {actid};
			int [] inactiveUPMDtlDatas =  {Types.VARCHAR};
			sqls.add(inactiveUPMDtlSql);
			valueList.add(inactiveUPMDtlKey);
			dataTypes.add(inactiveUPMDtlDatas);

		
			String inactiveMASql = WomTlWomstSql.maInactiveSql();
			Object [] inactiveMAKey = {actid};
			int [] inactiveMADatas =  {Types.VARCHAR};
			sqls.add(inactiveMASql);
			valueList.add(inactiveMAKey);
			dataTypes.add(inactiveMADatas);*/
						
			/* Update Active Status in Work Order */
			if(UIUtils.isValidKeyId(inActParam))
			{
				inActParam = inActParam.substring(0, inActParam.length() - 1);
				inactSql += inActParam+")";
				Object [] inactiveKey = new Object[woKeyId.size()];
				int [] inactiveDatas = new int[woKeyId.size()];
				for(int w=0;w<inactiveKey.length;w++)
				{
					inactiveKey[w] = woKeyId.get(w);
					inactiveDatas[w] = Types.VARCHAR;
				}
				sqls.add(inactSql);
				valueList.add(inactiveKey);
				dataTypes.add(inactiveDatas);				
				charList.add("Q");
			}
			
			/* Update Active Status in Breakdown */
			if(UIUtils.isValidKeyId(inActbdParam))
			{
				inActbdParam = inActbdParam.substring(0, inActbdParam.length() - 1);
				inactbdSql += inActbdParam+")";
				inactBdDtlSql += inActbdParam+")";
				Object [] inactiveBDKey = new Object[bdKeyId.size()];
				int [] inactiveBDDatas = new int[bdKeyId.size()];
				for(int w=0;w<inactiveBDKey.length;w++)
				{
					inactiveBDKey[w] = bdKeyId.get(w);
					inactiveBDDatas[w] = Types.VARCHAR;
				}
				sqls.add(inactbdSql);
				valueList.add(inactiveBDKey);
				dataTypes.add(inactiveBDDatas);
				sqls.add(inactBdDtlSql);
				valueList.add(inactiveBDKey);
				dataTypes.add(inactiveBDDatas);
				charList.add("Q");
				charList.add("Q");
			}
			/* Update Active Status in UPM */
			if(UIUtils.isValidKeyId(inActUpmParam))
			{
				inActUpmParam = inActUpmParam.substring(0, inActUpmParam.length() - 1);
				inactupmSql += inActUpmParam+")";
				inactUPMDtlSql += inActUpmParam+")";
				Object [] inactiveUPMKey = new Object[upmKeyId.size()];
				int [] inactiveUPMDatas = new int[upmKeyId.size()];
				for(int w=0;w<inactiveUPMKey.length;w++)
				{
					inactiveUPMKey[w] = upmKeyId.get(w);
					inactiveUPMDatas[w] = Types.VARCHAR;
				}
				sqls.add(inactupmSql);
				valueList.add(inactiveUPMKey);
				dataTypes.add(inactiveUPMDatas);
				sqls.add(inactUPMDtlSql);
				valueList.add(inactiveUPMKey);
				dataTypes.add(inactiveUPMDatas);
				charList.add("Q");
				charList.add("Q");
			}
			
			/* Update Active Status in Machine Activity */
			if(UIUtils.isValidKeyId(inActMAParam))
			{
				inActMAParam = inActMAParam.substring(0, inActMAParam.length() - 1);
				inactMASql += inActMAParam+")";
				Object [] inactiveMAKey = new Object[maKeyId.size()];
				int [] inactiveMADatas = new int[maKeyId.size()];
				for(int w=0;w<inactiveMAKey.length;w++)
				{
					inactiveMAKey[w] = maKeyId.get(w);
					inactiveMADatas[w] = Types.VARCHAR;
				}
				sqls.add(inactMASql);
				valueList.add(inactiveMAKey);
				dataTypes.add(inactiveMADatas);
				charList.add("Q");
			}
			if(UIUtils.isValidKeyId(inActMUParam))
			{
				inActMUParam = inActMUParam.substring(0, inActMUParam.length() - 1);
				inactMUSql += inActMUParam+")";
				Object [] inactiveMUKey = new Object[muKeyId.size()];
				int [] inactiveMUDatas = new int[muKeyId.size()];
				for(int w=0;w<inactiveMUKey.length;w++)
				{
					inactiveMUKey[w] = muKeyId.get(w);
					inactiveMUDatas[w] = Types.VARCHAR;
				}
				sqls.add(inactMUSql);
				valueList.add(inactiveMUKey);
				dataTypes.add(inactiveMUDatas);
				charList.add("Q");
			}
		}
		
		boolean breakdown = false;
		boolean MachineActivity = false;
		boolean unplanned = false;
		boolean abn = false;
		boolean mouldUnloading = false;
		
		List <String[]> pcsFlagList = dbActionTemplate.getDataList(WomTlWomstSql.checkPCSInsertEnable());
		if(pcsFlagList.size()>1)
		{
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[0]) && pcsFlagList.get(1)[0].equals("1"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[0]) && pcsFlagList.get(0)[0].equals("1"))
				{
					if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
					{
						if(oldWomTlWomst.getWomsFinalactivitytype().equals("BD"))
								breakdown = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[1]) && pcsFlagList.get(1)[1].equals("2"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[1]) && pcsFlagList.get(0)[1].equals("2"))
				{
					if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
					{
						if(oldWomTlWomst.getWomsFinalactivitytype().equals("GN"))
							MachineActivity = true;
					}
					
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[2]) && pcsFlagList.get(1)[2].equals("4"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[2]) && pcsFlagList.get(0)[2].equals("4"))
				{
					if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
					{
						if(oldWomTlWomst.getWomsFinalactivitytype().equals("U"))
							unplanned = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[3]) && pcsFlagList.get(1)[3].equals("8"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[3]) && pcsFlagList.get(0)[3].equals("8"))
				{
					if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
					{
						if(oldWomTlWomst.getWomsFinalactivitytype().equals("AB"))
							abn = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[4]) && pcsFlagList.get(1)[4].equals("16"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[4]) && pcsFlagList.get(0)[4].equals("16"))
				{
					if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
					{
						if(oldWomTlWomst.getWomsFinalactivitytype().equals("S"))
							MachineActivity = true;
					}
				}
			}
			if(CommonFunctions.isValidKeyId(pcsFlagList.get(1)[5]) && pcsFlagList.get(1)[5].equals("32"))
			{
				if(CommonFunctions.isValidKeyId(pcsFlagList.get(0)[5]) && pcsFlagList.get(0)[5].equals("32"))
				{
					if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
					{
						if(oldWomTlWomst.getWomsFinalactivitytype().equals("L"))
							mouldUnloading = true;
					}
				}
			}
				
		}
		CommonMessage.debugMsg(" oldWomTlWomst.getWomsFinalactivitytype() " + oldWomTlWomst.getWomsFinalactivitytype());
		if(FilterCondSql.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
		{
			if(oldWomTlWomst.getWomsFinalactivitytype().equals("BD") || oldWomTlWomst.getWomsFinalactivitytype().equals("U")|| oldWomTlWomst.getWomsFinalactivitytype().equals("AB")|| oldWomTlWomst.getWomsFinalactivitytype().equals("S"))
			{
				if(oldWomTlWomst.getWomsFinalactivitytype().equals("AB"))
				{
					parameterCode = oldWomTlWomst.getWomsLoss();	
					if(UIUtils.isValidKeyId(parameterCode))
					{
						if(parameterCode.equals("UNPLANNED MAINTENANCE"))
							parameterCode = "UPM";
						else if(parameterCode.equals("JH TAG REMOVAL"))
							parameterCode = "JHTAGREMOVE";
						else if(parameterCode.equals("M AND A"))
							parameterCode = "MANDA";
					}
				}
				if(oldWomTlWomst.getWomsFinalactivitytype().equals("S"))
				{
					parameterCode = "SETUPANDADJ";
				}
				CommonMessage.debugMsg("parameterCode : "+parameterCode);
				List<String> paramValues = new ArrayList<String>();			
				paramValues.add(oldWomTlWomst.getWomsMachineid());
				paramValues.add(occuredDate);
				paramValues.add(prodStartDate);
				paramValues.add(parameterCode);
				
				List<String[]>  prodAvlTimeFlag = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_ISPRODAVAILFORCOMPLETE", paramValues);
				
				if(prodAvlTimeFlag.size()>0)
				{
					
					String tableFlag = prodAvlTimeFlag.get(0)[1];  			/* Condition To validate */
					String msg = prodAvlTimeFlag.get(0)[0];
					if(tableFlag.equals(PCSConstants.PRODUCTION_EXISTS) || tableFlag.equals(PCSConstants.TIME_EXCEEDS))
					{
						throw new MSROverlapsPCSException(msg);
					}
					else if(tableFlag.equals(PCSConstants.NOOVERLAP))
					{
				
						/*  Update Production Acceptance in Work Order */
						String prodAcceptanceSql = WomTlWomstSql.getUpdateProdAcceptanceSql();
						/*CommonMessage.debugMsg(prodAcceptanceSql + "prodAcceptanceSql");
						CommonMessage.debugMsg(new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProductionstartdate()).getTime()) + "Prod Start date");
						CommonMessage.debugMsg(newWomTlWomst.getWomsProductionby() + "BY");
						CommonMessage.debugMsg(newWomTlWomst.getWomsProductionremarks() + "Remarks");
						CommonMessage.debugMsg(newWomTlWomst.getWomsProductionstartflag() + "Start");
						CommonMessage.debugMsg(newWomTlWomst.getWomsStatus() + "Status");
						CommonMessage.debugMsg(newWomTlWomst.getWomsFinalstatus() + "Final Status");
						CommonMessage.debugMsg(newWomTlWomst.getWomsModifiedby() + "Mod by");
						CommonMessage.debugMsg(new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsModifiedon()).getTime()) + "Mod On");
						CommonMessage.debugMsg(newWomTlWomst.getWomsKeyid() + "Key Id");*/
						Object [] updateProdAcceptance	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProductionstartdate()).getTime()), newWomTlWomst.getWomsProductionby(),newWomTlWomst.getWomsProductionremarks(),newWomTlWomst.getWomsProductionstartflag(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsFinalstatus(),newWomTlWomst.getWomsModifiedby(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsModifiedon()).getTime()),newWomTlWomst.getWomsKeyid()};
						int [] dataTypes1 =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};		 
						sqls.add(prodAcceptanceSql);
						valueList.add(updateProdAcceptance);
						dataTypes.add(dataTypes1);	
						charList.add("Q");		

						/*  Update Production Acceptance in Activity */
						if(UIUtils.isValidKeyId(oldWomTlWomst.getWomsFinalactivitytype()))
						{
							if(oldWomTlWomst.getWomsFinalactivitytype().equals("BD"))
							{
								String downTime = getTimeDifferenceInMins(oldWomTlWomst.getWomsOccurreddate(),newWomTlWomst.getWomsProductionstartdate());
								if(!UIUtils.isValidKeyId(downTime))
									downTime = "0";
								CommonMessage.debugMsg("Down Time : "+downTime);
								String prodAcceptanceBDSql = WomTlWomstSql.getUpdateProdAcceptanceBDSql();
								Object [] updateBreakDown	= {newWomTlWomst.getWomsProductionstartflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProductionstartdate()).getTime()),downTime,newWomTlWomst.getWomsKeyid()};
								int [] bdDatas =  {Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
								sqls.add(prodAcceptanceBDSql);
								valueList.add(updateBreakDown);
								dataTypes.add(bdDatas);
								charList.add("Q");
							}
							else if(oldWomTlWomst.getWomsFinalactivitytype().equals("U"))
							{
								parameterCode = "UPM";
								String downTime = getTimeDifferenceInMins(oldWomTlWomst.getWomsOccurreddate(),newWomTlWomst.getWomsProductionstartdate());
								if(!UIUtils.isValidKeyId(downTime))
									downTime = "0";
								String prodAcceptanceUPMSql = WomTlWomstSql.getUpdateProdAcceptanceUPMSql();
								Object [] updateUnplndMaint	= {newWomTlWomst.getWomsProductionstartflag(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProductionstartdate()).getTime()),downTime,newWomTlWomst.getWomsKeyid()};
								int [] unplndMaintDatas =  {Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
								sqls.add(prodAcceptanceUPMSql);
								valueList.add(updateUnplndMaint);
								dataTypes.add(unplndMaintDatas);
								charList.add("Q");
							}
						 }
						
						if(breakdown || abn || unplanned)
						{
							
							String insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEMSR";
							if(oldWomTlWomst.getWomsFinalactivitytype().equals("AB"))
							{
								parameterCode = oldWomTlWomst.getWomsLoss();	
								if(UIUtils.isValidKeyId(parameterCode))
								{
									if(parameterCode.equals("UNPLANNED MAINTENANCE"))
										parameterCode = "UPM";
									else if(parameterCode.equals("JH TAG REMOVAL"))
										parameterCode = "JHTAGREMOVE";
									else if(parameterCode.equals("M AND A"))
										parameterCode = "MANDA";
								}
								insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEABN";
							}
							
							
							/*CommonMessage.debugMsg(" oldWomTlWomst.getWomsMachineid(),occuredDate,prodStartDate,oldWomTlWomst.getWomsKeyid(),parameterCode,newWomTlWomst.getWomsCreatedby() " + oldWomTlWomst.getWomsMachineid() + " " + occuredDate + " " + prodStartDate + " " +oldWomTlWomst.getWomsKeyid() + " " + parameterCode + " " + newWomTlWomst.getWomsCreatedby());
							Object [] insertPCSDatas = {oldWomTlWomst.getWomsMachineid(),occuredDate,prodStartDate,oldWomTlWomst.getWomsKeyid(),parameterCode,newWomTlWomst.getWomsCreatedby()};
							int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
							sqls.add(insertPCSSql);
							valueList.add(insertPCSDatas);
							dataTypes.add(insertPCSTypes);
							charList.add("P");*/
						}
					}				
					 
				}
			}
			else
			{
				/*  Update Production Acceptance in Work Order */
				String prodAcceptanceSql = WomTlWomstSql.getUpdateProdAcceptanceSql();
				Object [] updateProdAcceptance	= {new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsProductionstartdate()).getTime()), newWomTlWomst.getWomsProductionby(),newWomTlWomst.getWomsProductionremarks(),newWomTlWomst.getWomsProductionstartflag(),newWomTlWomst.getWomsStatus(),newWomTlWomst.getWomsFinalstatus(),newWomTlWomst.getWomsModifiedby(),new java.sql.Timestamp( sdf.parse(newWomTlWomst.getWomsModifiedon()).getTime()),newWomTlWomst.getWomsKeyid()};
				int [] dataTypes1 =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};		 
				sqls.add(prodAcceptanceSql);
				valueList.add(updateProdAcceptance);
				dataTypes.add(dataTypes1);	
				charList.add("Q");		
			}
		}
				
		char [] sqlType = new char[charList.size()];
		for(int c=0;c<sqlType.length;c++)
		{
			sqlType[c] = charList.get(c).charAt(0);
			CommonMessage.debugMsg(c +" : "+sqlType[c]);
		}
		CommonMessage.debugMsg("XX");
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
		
		return newWomTlWomst;
		//	String detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", oldWomTlWomst.getWomsSectionid());
		//detailTableName = detailTableName.replaceAll("-", "#");		
		/*if(prodAvlTimeFlag.size()>0)
		{
			int i =0;
			int timeCount=0;
			int seqNoFlag = 0;
			String dateTime = CommonFunctions.dateTimeNow();		
			Timestamp startTime =null;			
			Timestamp currentTime = new java.sql.Timestamp( sdf.parse(dateTime).getTime());			

			while(i<prodAvlTimeFlag.size())
			{*/
			/*	String tableFlag = prodAvlTimeFlag.get(i)[0];  			 Condition To validate 
				detailTableName = prodAvlTimeFlag.get(i)[1];  		     Detail Table Name 
				String shift = prodAvlTimeFlag.get(i)[2];      			 Shift Id 
				String startDate = prodAvlTimeFlag.get(i)[3];  			 Start Date 
				String prodId= prodAvlTimeFlag.get(i)[4];      			 Production Id 
				String theoreticalCycleTime= prodAvlTimeFlag.get(i)[5];	 Theoretical Cycle time 			
				String cavity= prodAvlTimeFlag.get(i)[6];				 Cavity 
				String lossId = prodAvlTimeFlag.get(i)[7];				 Loss Id 
				String lossCol = prodAvlTimeFlag.get(i)[8];				 Loss Column Name in Detail Table 
				String timeFlag = prodAvlTimeFlag.get(i)[9];*/
				
			/*	timeCount = timeCount+Integer.parseInt(timeFlag);
				if(!tableFlag.equals("-"))
				{
					if(startDate.indexOf(":")<=0)
						startDate = startDate + " 00:00";
						
					if(!prodAvlTimeFlag.get(i)[3].equals("-"))
					 startTime = new java.sql.Timestamp( sdf.parse(startDate.trim()).getTime());
					
					//if(UIUtils.isValidKeyId(parameterCode))
					   //lossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_KEYID", "PLCM_PARAMETERCODE", parameterCode);
	
					PcsTlMst pcsTlMst = new PcsTlMst();
					
					if(tableFlag.equals(PCSConstants.PCSMSTRECNOTEXISTS))
					{
						pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST));
						String insertPcsMstSql = WomTlWomstSql.insertPCSMaster();					
						Object [] insertPcsMst = {pcsTlMst.getPrlmKeyid(),startTime,oldWomTlWomst.getWomsFactoryid(),oldWomTlWomst.getWomsSectionid(),oldWomTlWomst.getWomsCellid(),shift,startTime,newWomTlWomst.getWomsCreatedby(),newWomTlWomst.getWomsCreatedby(),startTime,newWomTlWomst.getWomsCreatedby(),startTime,newWomTlWomst.getWomsCreatedby(),currentTime,currentTime};
						int [] pcsMstDatas =  {Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
						sqls.add(insertPcsMstSql);
						valueList.add(insertPcsMst);
						dataTypes.add(pcsMstDatas);
						//CommonMessage.debugMsg("insertPcsMstSql : "+insertPcsMstSql);
					}
					else if(tableFlag.equals(PCSConstants.DTLTBLNOTEXISTS))
					{
						String masterId = dbActionTemplate.getSingleValue(WomTlWomstSql.getPcsMaster(oldWomTlWomst.getWomsCellid(), shift, prodAvlTimeFlag.get(i)[3]));
						if(UIUtils.isValidKeyId(masterId))
							pcsTlMst.setPrlmKeyid(masterId);
						String createSql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM PCS_TL_DTL ";
						CommonMessage.debugMsg("sqlsqlsql"+createSql);
						dbActionTemplate.executeStatement(createSql);

					}
					else if(tableFlag.equals(PCSConstants.PCSDTLRECNOTEXISTS))
					{
						String masterId = dbActionTemplate.getSingleValue(WomTlWomstSql.getPcsMaster(oldWomTlWomst.getWomsCellid(), shift, prodAvlTimeFlag.get(i)[3]));
						if(UIUtils.isValidKeyId(masterId))
							pcsTlMst.setPrlmKeyid(masterId);
						CommonMessage.debugMsg("masterId : "+masterId);
					}
					else if(tableFlag.equals(PCSConstants.PRODEXIST))
					{
						throw new BusinessApplicationExceptions("prodExist,");
					}
					else if(tableFlag.equals(PCSConstants.TIMEEXCEEDS))
					{
						throw new BusinessApplicationExceptions("timeExceeds,");
					}
				
					PcsTlDtl pcsTlDtl = new PcsTlDtl();	
					String seqNoCount = dbActionTemplate.getSingleValue(WomTlWomstSql.isTableExists(detailTableName));
					
					if(Integer.parseInt(seqNoCount) == 0 && seqNoFlag == 0)
					{
						String insertSeqNogenSql = WomTlWomstSql.insertSeqNoGen();
						Object [] insertSeqNogenVal = {detailTableName,new java.sql.Timestamp( sdf.parse(dateTime).getTime())};
						int [] insertSeqNogenDatas =  {Types.VARCHAR,Types.TIMESTAMP};
						sqls.add(insertSeqNogenSql);// add insert sql for detail table
						valueList.add(insertSeqNogenVal);
						dataTypes.add(insertSeqNogenDatas);
						seqNoFlag =1;
					}
					
					pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(detailTableName,15,"PDE","YY","Y"));
					detailId = pcsTlDtl.getPldetailsid();
					
					
					String insertPcsDetailsSql = WomTlWomstSql.insertPcsDetails(detailTableName,lossCol);
					Object [] insertPcsDetails = {detailId,pcsTlMst.getPrlmKeyid(),oldWomTlWomst.getWomsCellid(),oldWomTlWomst.getWomsMachineid(),prodId,oldWomTlWomst.getWomsKeyid(),theoreticalCycleTime,timeFlag,newWomTlWomst.getWomsCreatedby(),new java.sql.Timestamp( sdf.parse(dateTime).getTime()),new java.sql.Timestamp( sdf.parse(dateTime).getTime())};
					int [] pcsDetailsDatas =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
					sqls.add(insertPcsDetailsSql);
					valueList.add(insertPcsDetails);
					dataTypes.add(pcsDetailsDatas);
				
					
					/*PcsTlWorkorderlink pcsTlWorkorderlink = new PcsTlWorkorderlink();
					pcsTlWorkorderlink.setPtwoKeyid(dbActionTemplate.getSequenceNumber(PcsTlWorkorderlinkSql.TBL_PCS_TL_WORKORDERLINK));
					String insertPcsWoLinkSql = WomTlWomstSql.insertPcsWoLink();
					Object [] insertPcsWO = {pcsTlWorkorderlink.getPtwoKeyid(),pcsTlDtl.getPldetailsid(),pcsTlMst.getPrlmKeyid(),oldWomTlWomst.getWomsKeyid(),theoreticalCycleTime,theoreticalCycleTime,cavity,newWomTlWomst.getWomsCreatedby(),currentTime,currentTime};
					int [] PcsWODatas = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.NUMERIC,Types.NUMERIC,Types.NUMERIC,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
					sqls.add(insertPcsWoLinkSql);
					valueList.add(insertPcsWO);
					dataTypes.add(PcsWODatas);*/
				
				/*	PcsTlLossreasonlink pcsTlLossreasonlink = new PcsTlLossreasonlink();
					pcsTlLossreasonlink.setPlrkKeyid(dbActionTemplate.getSequenceNumber(PcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK,15,"PLRK","YY","Y"));
					String insertPcsLossReasonSql = WomTlWomstSql.insertPcsLossReason();
					Object [] insertPcsLossReason = {pcsTlLossreasonlink.getPlrkKeyid(),lossId,pcsTlDtl.getPldetailsid(),oldWomTlWomst.getWomsKeyid(),startTime,shift,oldWomTlWomst.getWomsFactoryid(),oldWomTlWomst.getWomsSectionid(),oldWomTlWomst.getWomsCellid(),oldWomTlWomst.getWomsMachineid(),newWomTlWomst.getWomsCreatedby(),currentTime,currentTime};
					int [] PcsLossReasonDatas = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
					sqls.add(insertPcsLossReasonSql);
					valueList.add(insertPcsLossReason);
					dataTypes.add(PcsLossReasonDatas);
					//CommonMessage.debugMsg("insertPcsLossReasonSql : "+insertPcsLossReasonSql);
				}
				i++;
			 }
		}*/
		
	
	}
	
	public  void insertPCSMaster(WomTlWomst oldWomTlWomst,WomTlWomst newWomTlWomst,String shift,String prodId,Timestamp startTime,Timestamp currentTime,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes)
	{
		PcsTlMst pcsTlMst = new PcsTlMst();					
		try {
			pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST));
			String insertPcsMstSql = WomTlWomstSql.insertPCSMaster();
			
			Object [] insertPcsMst = {pcsTlMst.getPrlmKeyid(),startTime,oldWomTlWomst.getWomsFactoryid(),oldWomTlWomst.getWomsSectionid(),oldWomTlWomst.getWomsCellid(),shift,startTime,newWomTlWomst.getWomsCreatedby(),newWomTlWomst.getWomsCreatedby(),startTime,newWomTlWomst.getWomsCreatedby(),startTime,newWomTlWomst.getWomsCreatedby(),currentTime,currentTime};
			int [] pcsMstDatas =  {Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
			sqls.add(insertPcsMstSql);
			valueList.add(insertPcsMst);
			dataTypes.add(pcsMstDatas);
			insertPCSDetails(pcsTlMst,oldWomTlWomst,newWomTlWomst,prodId,currentTime,sqls,valueList,dataTypes);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set the sequnce number
		
	}
	public  void  insertPCSDetails(PcsTlMst pcsTlMst,WomTlWomst oldWomTlWomst,WomTlWomst newWomTlWomst,String prodId,Timestamp currentTime,List<String> sqls,List<Object[]> valueList,List<int[]> dataTypes)
	{
	
	}
	public static Object fillDefaultPCSValues(Object beanObject)
	{
		  Class cls  = beanObject.getClass();
		  Method [] beanMethods = cls.getMethods();
		  
		  int i=0;
		  while(i<beanMethods.length)
		  {
			  String getFlag = beanMethods[i].getName().substring(0,3);
			  if(getFlag.equals("set"))
			  {
				  try {
					 
					  	beanMethods[i].invoke( beanObject,"0");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		  }
		
		return beanObject;
		
	}
	/*public List<String> pcsEntry(List<String> sqls ,WomTlWomst newWomTlWomst,WomTlWomst oldWomTlWomst,  WOFormBean woFormBean )
	{
		String pcsMstFlag = WomTlWomstSql.getPCSFlag(newWomTlWomst.getWomsCellid(),newWomTlWomst.getWomsShiftid(),newWomTlWomst.getWomsOccurreddate());
		if(UIUtils.isValidKeyId(pcsMstFlag) )
		{
			if(Integer.parseInt(pcsMstFlag) <= 0)
			{
				PcsTlMst pcsTlMst = new PcsTlMst();
				PcsTlMstSql pcsTlMstSql = new PcsTlMstSql(); 
				fillPCSMstValues(pcsTlMst,oldWomTlWomst,newWomTlWomst.getWomsCreatedby());
				try {
					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST));
					sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray()));
				} catch (Exception e) {
					
				} 	
				
			}
		}
		return sqls;
	}*/
	public WomTlWomst delete(WomTlWomst womTlWomst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
		try {
			 String woStatus = womTlWomst.getWomsStatus();
			 if(CommonFunctions.isValidKeyId(woStatus))
			 {
				 woStatus = getStatus(woStatus);
			 }
			if(woStatus.equals("B"))
				sqls.add(WomTlWomstSql.getDeleteSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
			else
			{
				
				String actType = womTlWomst.getWomsFinalactivitytype();
				String actId = womTlWomst.getWomsActivityid();
			
				String inactiveWOSql = WomTlWomstSql.inactiveWOSql(womTlWomst.getWomsKeyid());
				sqls.add(inactiveWOSql);
				
				if(CommonFunctions.isValidKeyId(actType) && CommonFunctions.isValidKeyId(actId))
				{
					 if(actType.equals("BD"))
					 {
						 sqls.add(BdmTlMstSql.inactiveBDSql(actId));						 
						 sqls.add(BdmTlMstSql.inactiveBDdtlSql(actId));
					 }
					 if(actType.equals("U"))
					 {
						 sqls.add(BdmTlMstSql.inactiveupmSql(actId));						 
						 sqls.add(BdmTlMstSql.inactiveupmdtlSql(actId));
					 }
					 else if(actType.equals("GN"))
					 {
						 sqls.add(PlmTlGenmaintenanceSql.inactiveGenMainSql(actId));
					 }
					 else if(actType.equals("AB"))
					 {
						 sqls.add(AbnTlAbnormalitySql.inactiveAbnSql(actId));
					 }
				 }
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlWomst;
	}
	
	public void updateControlKey(String womsKey,String controlKey) throws Exception{
		String sql="update wom_tl_workorder_mst set WOMS_CONTROLKEY='"+controlKey+"' where woms_keyid='"+womsKey+"'";
		CommonMessage.debugMsg("update control key:"+sql);
		dbActionTemplate.executeStatement(sql);
		
	}
	public List<String[]> getSapEquipmentDetail(String eqpNo) throws Exception  {
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		
		//sql1.append(" SELECT CSTM_KEYID, WKCM_KEYID, MCHM_PLANNERGROUP,  MCHM_PMFUNCTIONALLOCN, MCHM_WBSELEMENT ");			 
		//sql1.append(" SELECT MCHM_COSTCENTREID, MCHM_WORKCENTER, MCHM_PLANNERGROUP,  MCHM_PMFUNCTIONALLOCN, MCHM_WBSELEMENT ");
		//sql1.append(" FROM  GEN_TL_COSTCENTREMST, GEN_TL_WORKCENTREMST, GEN_TL_PLANNER_GROUP,GEN_TL_MACHINEMST ");
		sql1.append("SELECT Costcenter, MCHM_WORKCENTER, MCHM_PLANNERGROUP, MCHM_PMFUNCTIONALLOCN, MCHM_WBSELEMENT, trade  ");
		/*sql1.append("decode(substr(mchm_machineno,0,1),'1','MECHANICAL','2','ELECTRICAL','3','INSTRUMENTATION','4','GENERAL',");
		sql1.append("'5','CIVIL','6','PROCESS','7','SERVICE','8','TECHNICAL','') ");
		*/
		sql1.append(" FROM GEN_TL_COSTCENTREMST, GEN_TL_WORKCENTREMST, GEN_TL_PLANNER_GROUP, GEN_TL_MACHINEMST,Gen_vw_JHCostcenter_Link,gen_vw_mchtradelink ");
		//sql1.append(" WHERE EQPM_NUMBER like '%").append(empNo).append("')");
		sql1.append(" WHERE  ");
		sql1.append("  MCHM_KEYID = '").append(eqpNo).append("' ");
		sql1.append("  AND CSTM_KEYID(+) = Costcenter ");
		sql1.append("  and Costcenter(+) = mchm_costcentreid ");
		sql1.append("  AND CSTM_KEYID(+) = MCHM_COSTCENTREID ");
		sql1.append("  AND WKCM_KEYID(+) = MCHM_WORKCENTER ");
		sql1.append("  AND GPLG_KEYID(+) = MCHM_PLANNERGROUP ");
		sql1.append(" and mchm_prefix=trim(substr(mchm_machineno(+),0,1))");
		
		sql=sql1.toString();
		
		CommonMessage.debugMsg("sql==="+sql.toString());
		List<String[]> eqpDet = dbActionTemplate.getDataList(sql);
		
		if (eqpDet.size() >0 ) { 
			CommonMessage.debugMsg("allowList: " +eqpDet.size());
			CommonMessage.debugMsg("allowList: " +eqpDet.get(0)[0]);
		}
	
		return eqpDet;			
	}
	
	public List<String[]> getWorkOrderList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
					List<String[]> dataList=new ArrayList<String[]>();
					try
						{
							List<String> paramValues = new ArrayList<String>();
							String functName="";
							String condParms = FilterCondSql.getWORelatedCondStr(commonFilter);
							String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
							String type="";
							type=commonFilter.getType();
							if(UIUtils.isValidKeyId(type))
								condParms+="TYPE="+type+";";
							
							paramValues.add(condParms);
							paramValues.add(commonParams);
							CommonMessage.debugMsg("TYPE of MAAINGRID:"+type);
							if(type.equals("NOTI")){
								functName="PLM_PC_PMWORKORDRER.PLM_FN_NOTIFICATIONMAINGRID";
							}
							else{
								functName="PLM_PC_PMWORKORDRER.PLM_FN_WORKORDERMAINGRID";
							}
							dataList= dbActionTemplate.processFunctionCalls(functName, paramValues);
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
	
	public List<String[]> getWorkOrder(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();		
		
		String condParms = FilterCondSql.getWORelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(CommonFunctions.isValidKeyId(commonFilter.getWoapproval()))
			condParms += "WOAPPROVAL="+commonFilter.getWoapproval() +";";
		CommonMessage.debugMsg("commonFilter.getWostatus"+commonFilter.getWoapproval());
		if(!CommonFunctions.isValidKeyId(FilterCondSql.getComboSelectionId((commonFilter.getStatuss()))))
		{
			if(CommonFunctions.isValidKeyId(commonFilter.getWostatus()))
				condParms += "CMBSTATUS="+commonFilter.getWostatus() +";";
		}
		else 
			condParms += "CMBSTATUS="+FilterCondSql.getComboSelectionId(commonFilter.getStatuss()) +";";
		if(CommonFunctions.isValidKeyId(commonFilter.getWoModeForGrid()))
		{
			condParms += "CMBPRODACC="+commonFilter.getWoModeForGrid() +";";
		}
		CommonMessage.debugMsg("test to............");
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_PERMITREQUEST", paramValues);
		CommonMessage.debugMsg("After " +commonFilter.getViewClick());
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	
	public WomTlWomst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		WomTlWomst womTlWomst = new WomTlWomst();
		String sql = WomTlWomstSql.selectWO();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { keyid };
		womTlWomst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return womTlWomst;
	}
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
			List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramValues);
			CommonMessage.debugMsg("Shift : "+fillShift.get(0)[0]);
			return fillShift.get(0)[0];
		}
		catch(Exception e)
		{
		}
		return null;
	}
	public String getYYId(String refDocId)
	{
		try {
			return dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_WHYWHYMST, "WWMS_KEYID", "WWMS_REFDOCNO", refDocId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getRelatedFieldVal(String refDocId,String actType)
	{
		try {
				if(actType.equals("BD"))
					return dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_DTL, "BDAN_FINALACTION", "BDAN_BDMS_KEYID", refDocId);
				else if(actType.equals("AB"))
					return dbActionTemplate.getSingleValue(TableNames.TBL_ABN_TL_ABNORMALITY, "ABNM_COUNTERMEASURE", "ABNM_KEYID", refDocId);
				else
					return dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_GENMAINTENANCE, "GMNT_ACTION", "GMNT_KEYID", refDocId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String checkActivityConfig()
	{
		try {
			String lockActivity = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "MSRLOCKACTIVITY");
			if(UIUtils.isValidKeyId(lockActivity))
			{
				return lockActivity;
			}
			else
				return "N";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private static String getStatus(String status)
	{
		String retVal = null;
		if(status.equals("BOOKED"))
			retVal = "B";
		else if(status.equals("ALLOTTED"))
			retVal = "L";
		else if(status.equals("COMPLETED"))
			retVal = "C";
		else if(status.equals("PRODUCTION APPROVED"))
			retVal = "E";
		
		return retVal;
			
	}
	@Override
	public Workbook workOrderRpt(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getWorkorderRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			//condFormat.setFontColor(new RGB(0,0,254)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short)8);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("HIGH"); //Tick
			condFormat.setIdentfier("D");
			condFormat.setBgColor(new RGB(216, 96, 92));
			//condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_CROSS+"");
			condFormats.add(condFormat);
			
			XLConditionalFormats condFormatMedium = new XLConditionalFormats();
			//condFormat.setFontColor(new RGB(0,0,254)); //red font
			condFormatMedium.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatMedium.setFontHeightPoint((short)8);
			condFormatMedium.setFontBoldWeight((short)20);
			condFormatMedium.setFromCol(0);
			condFormatMedium.setToCol(-1);
			condFormatMedium.setOperator(ComparisonOperator.EQUAL);
			condFormatMedium.setCondValue("MEDIUM"); //Tick
			condFormatMedium.setIdentfier("C");
			condFormatMedium.setBgColor(new RGB(255, 178, 140));
			//condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_CROSS+"");
			condFormats.add(condFormatMedium);
			
			XLConditionalFormats condFormatVeryHigh = new XLConditionalFormats();
			condFormatVeryHigh.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatVeryHigh.setFontHeightPoint((short)8);
			condFormatVeryHigh.setFontBoldWeight((short)20);
			condFormatVeryHigh.setFromCol(0);
			condFormatVeryHigh.setToCol(-1);
			condFormatVeryHigh.setOperator(ComparisonOperator.EQUAL);
			condFormatVeryHigh.setCondValue("VERY HIGH"); //Tick179, 26, 26
			condFormatVeryHigh.setIdentfier("E");
			condFormatVeryHigh.setBgColor(new RGB(179, 26, 26));
			condFormats.add(condFormatVeryHigh);
			
			XLConditionalFormats condFormatBD = new XLConditionalFormats();
			condFormatBD.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatBD.setFontHeightPoint((short)8);
			condFormatBD.setFontBoldWeight((short)20);
			condFormatBD.setFromCol(0);
			condFormatBD.setToCol(7);
			condFormatBD.setOperator(ComparisonOperator.EQUAL);
		
			condFormatBD.setCondValue("Breakdown"); 
			condFormatBD.setIdentfier("BD");
			condFormatBD.setBgColor(new RGB(216, 100, 100));
			condFormats.add(condFormatBD);
			
			XLConditionalFormats condFormatUM = new XLConditionalFormats();
			condFormatUM.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatUM.setFontHeightPoint((short)8);
			condFormatUM.setFontBoldWeight((short)20);
			condFormatUM.setFromCol(0);
			condFormatUM.setToCol(7);
			condFormatUM.setOperator(ComparisonOperator.EQUAL);
			condFormatUM.setCondValue("Unplanned Maintenance"); 
			condFormatUM.setIdentfier("UM");
			condFormatUM.setBgColor(new RGB(255, 254, 175));
			
			
			XLConditionalFormats condFormatMA = new XLConditionalFormats();
			condFormatMA.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatMA.setFontHeightPoint((short)8);
			condFormatMA.setFontBoldWeight((short)20);
			condFormatMA.setFromCol(0);
			condFormatMA.setToCol(-1);
			condFormatMA.setOperator(ComparisonOperator.EQUAL);
			condFormatMA.setCondValue("Machine Activity"); 
			condFormatMA.setIdentfier("MA");
			condFormatMA.setBgColor(new RGB(116, 216, 116));
			condFormats.add(condFormatMA);
			
			XLConditionalFormats condFormatABN = new XLConditionalFormats();
			condFormatABN.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatABN.setFontHeightPoint((short)8);
			condFormatABN.setFontBoldWeight((short)20);
			//condFormatABN.setDbChkColIndx(3);
			condFormatABN.setFromCol(0);
			condFormatABN.setToCol(-1);
			condFormatABN.setOperator(ComparisonOperator.EQUAL);
			condFormatABN.setCondValue("Abnormality"); 
			condFormatABN.setIdentfier("ABN");
			condFormatABN.setBgColor(new RGB(0, 255, 191));
			condFormats.add(condFormatABN);
			
			XLConditionalFormats statusBooked = new XLConditionalFormats();
			statusBooked.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusBooked.setFontHeightPoint((short)8);
			statusBooked.setFontBoldWeight((short)20);
			statusBooked.setDbChkColIndx(4);
			statusBooked.setFromCol(2);
			statusBooked.setToCol(2);
			statusBooked.setOperator(ComparisonOperator.EQUAL);
			statusBooked.setCondValue("B"); 
			statusBooked.setIdentfier("booked");
			statusBooked.setBgColor(new RGB(248, 225, 211));
			condFormats.add(statusBooked);
			
			XLConditionalFormats statusAllotted = new XLConditionalFormats();
			statusAllotted.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusAllotted.setFontHeightPoint((short)8);
			statusAllotted.setFontBoldWeight((short)20);
			statusAllotted.setDbChkColIndx(4);
			statusAllotted.setFromCol(2);
			statusAllotted.setToCol(2);
			statusAllotted.setOperator(ComparisonOperator.EQUAL);
			statusAllotted.setCondValue("L"); 
			statusAllotted.setSymbolStr("");	
			statusAllotted.setIdentfier("allotted");
			statusAllotted.setBgColor(new RGB(103, 176, 237));
			condFormats.add(statusAllotted);
			
			XLConditionalFormats statusCompleted = new XLConditionalFormats();
			statusCompleted.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusCompleted.setFontHeightPoint((short)8);
			statusCompleted.setFontBoldWeight((short)20);
			statusCompleted.setDbChkColIndx(4);
			statusCompleted.setFromCol(2);
			statusCompleted.setToCol(2);
			statusCompleted.setOperator(ComparisonOperator.EQUAL);
			statusCompleted.setCondValue("C"); 
			statusCompleted.setIdentfier("completed");
			statusCompleted.setBgColor(new RGB(85, 216, 129));
			condFormats.add(statusCompleted);
			
			XLConditionalFormats statusProductionApproved = new XLConditionalFormats();
			statusProductionApproved.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusProductionApproved.setFontHeightPoint((short)8);
			statusProductionApproved.setFontBoldWeight((short)20);
			statusProductionApproved.setDbChkColIndx(4);
			statusProductionApproved.setFromCol(2);
			statusProductionApproved.setToCol(2);
			statusProductionApproved.setOperator(ComparisonOperator.EQUAL);
			statusProductionApproved.setCondValue("P"); 
			statusProductionApproved.setIdentfier("prodApproved");
			statusProductionApproved.setBgColor(new RGB(231, 231, 0));
			condFormats.add(statusProductionApproved);
			
			XLConditionalFormats statusProdApproved = new XLConditionalFormats();
			statusProdApproved.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusProdApproved.setFontHeightPoint((short)8);
			statusProdApproved.setFontBoldWeight((short)20);
			statusProdApproved.setDbChkColIndx(4);
			statusProdApproved.setFromCol(2);
			statusProdApproved.setToCol(2);
			statusProdApproved.setOperator(ComparisonOperator.EQUAL);
			statusProdApproved.setCondValue("E"); 
			statusProdApproved.setIdentfier("prodApproved");
			statusProdApproved.setBgColor(new RGB(231, 231, 0));
			condFormats.add(statusProdApproved);
			excelUtils.setCondFormats(condFormats);
			
			
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getWorkorderRptResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PLM_PC_PMWORKORDRER.PLM_FN_WORKORDERMAINGRID", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();		
		
		String condParms = FilterCondSql.getWORelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("commonFilter.getWostatus()"+commonFilter.getWostatus());
		if(CommonFunctions.isValidKeyId(commonFilter.getWoapproval()))
			condParms += "WOAPPROVAL="+commonFilter.getWoapproval() +";";
		if(CommonFunctions.isValidKeyId(commonFilter.getWostatus()))
			condParms += "CMBSTATUS="+commonFilter.getWostatus() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
				
		return paramValues;
	}	
	@Override
	public Workbook workOrderViewRpt(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getWorkorderviewRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getWorkorderviewRptResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValuesforView(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_PERMITREQUEST", paramValues);
	}
	private List<String> getFilterParamValuesforView(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();		
		
		String condParms = FilterCondSql.getWORelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("commonFilter.getWostatus()"+commonFilter.getWostatus());
		paramValues.add(condParms);
		paramValues.add(commonParams);
				
		return paramValues;
	}	
	public void getOEECalculation(String detailTableName, String Pldetailsid) throws Exception
	{
		CommonMessage.debugMsg(detailTableName + " : "+Pldetailsid);
		List<String> paramValues = new ArrayList<String>();		
		paramValues.add(detailTableName);
		paramValues.add(Pldetailsid);
		
		dbActionTemplate.processFunctionCalls("PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL", paramValues);
	}
	public static String getTimeDifferenceInMins(String dateStart,String dateStop)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm",Locale.ENGLISH);
		Date d1 = null;
        Date d2 = null;
        
        try {
			d1 = format.parse(dateStart);
	        d2 = format.parse(dateStop);
	        CommonMessage.debugMsg(d1 +" : "+d2);
	        long diff = d2.getTime() - d1.getTime();
	        long diffMinutes = diff / (60 * 1000); 
	        CommonMessage.debugMsg("diff : "+diff);
	        CommonMessage.debugMsg("diffMinutes : "+diffMinutes);
	        return Long.toString(diffMinutes);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
       
	}


	/*private PcsTlMst fillPCSMstValues(PcsTlMst newPcsTlMst,WomTlWomst womTlWomst,String loginId) {
		
		newPcsTlMst.setPrlmActive("Y");		
		String dateTime = CommonFunctions.dateTimeNow();	
		if(!CommonFunctions.isValidKeyId(newPcsTlMst.getPrlmKeyid()))			
		{	
			newPcsTlMst.setPrlmCreatedon(dateTime);			
		}

		newPcsTlMst.setPrlmDate(womTlWomst.getWomsOccurreddate());	
		newPcsTlMst.setPrlmFactoryid(womTlWomst.getWomsFactoryid());
		newPcsTlMst.setPrlmSectionid(womTlWomst.getWomsSectionid());
		newPcsTlMst.setPrlmCellid(womTlWomst.getWomsCellid());
		newPcsTlMst.setPrlmMachineid(womTlWomst.getWomsMachineid());
		newPcsTlMst.setPrlmShiftid(womTlWomst.getWomsShiftid());
		newPcsTlMst.setPrlmEntrydate(womTlWomst.getWomsOccurreddate());
		newPcsTlMst.setPrlmEntryby(loginId);
		newPcsTlMst.setPrlmUpdatedby(loginId);
		newPcsTlMst.setPrlmUpdateddate(womTlWomst.getWomsOccurreddate());		
		newPcsTlMst.setPrlmApporvedflag("Y");  //y,n,e,c
		newPcsTlMst.setPrlmApprovedby(loginId);
		newPcsTlMst.setPrlmApprovedate(womTlWomst.getWomsOccurreddate());
		newPcsTlMst.setPrlmShiftincharge("{}");
		newPcsTlMst.setPrlmSubgroupid("{}");		
		newPcsTlMst.setPrlmIsnoplan("N");  
		newPcsTlMst.setPrlmLogtype("S");
		newPcsTlMst.setPrlmTimeorqty("T");
		newPcsTlMst.setPrlmTempfield1("{}");
		newPcsTlMst.setPrlmTempfield2("{}");
		newPcsTlMst.setPrlmCreatedby(loginId);		
		newPcsTlMst.setPrlmModifiedon(dateTime);	
		
		return newPcsTlMst;
		
	}*/
	
/*	private PcsTlWorkorderlink workOrderFillValues(PcsTlWorkorderlink newPcsTlWorkorderlink,WomTlWomst womTlWomst,String loginId) 
	{
		String dateTime = CommonFunctions.dateTimeNow();		
		newPcsTlWorkorderlink.setPtwoCreatedon(dateTime);
		newPcsTlWorkorderlink.setPtwoModifiedon(dateTime);
		newPcsTlWorkorderlink.setPtwoActive("Y");
		newPcsTlWorkorderlink.setPtwoPldetailid("{}");
		newPcsTlWorkorderlink.setPtwoMasterid("{}");
		newPcsTlWorkorderlink.setPtwoWno(womTlWomst.getWomsKeyid());
		newPcsTlWorkorderlink.setPtwoTheoriticalcycletime("0");
		newPcsTlWorkorderlink.setPtwoActualcycletime("0");
		newPcsTlWorkorderlink.setPtwoCalendartime("0");
		newPcsTlWorkorderlink.setPtwoNoplaninmins("0");
		newPcsTlWorkorderlink.setPtwoPlannedqty("0");
		newPcsTlWorkorderlink.setPtwoProducedqty("0");
		newPcsTlWorkorderlink.setPtwoRejectionqty("0");
		newPcsTlWorkorderlink.setPtwoNoplaninmins("0");
		newPcsTlWorkorderlink.setPtwoUnaccountedtime("0"); 
		newPcsTlWorkorderlink.setPtwoProductiontime("0");
		newPcsTlWorkorderlink.setPtwoTemp1("{}");			
		newPcsTlWorkorderlink.setPtwoTemp2("{}");
		newPcsTlWorkorderlink.setPtwoTemp3("{}");
		newPcsTlWorkorderlink.setPtwoCreatedby(loginId);
		
		return newPcsTlWorkorderlink;		
	}*/
	
/*	private PcsTlLossreasonlink lossReasonFillValues(PcsTlLossreasonlink newPcsTlLossreasonlink,WomTlWomst womTlWomst,String loginId) 
	{
		String dateTime = CommonFunctions.dateTimeNow();
		newPcsTlLossreasonlink.setPlrkCreatedon(dateTime);
		newPcsTlLossreasonlink.setPlrkModifiedon(dateTime);
		newPcsTlLossreasonlink.setPlrkActive("Y");
		newPcsTlLossreasonlink.setPlrkCreatedby(loginId);
		newPcsTlLossreasonlink.setPlrkLossid("{}");
		newPcsTlLossreasonlink.setPlrkReasonid("{}");
		newPcsTlLossreasonlink.setPlrkCauseid("{}");
		newPcsTlLossreasonlink.setPlrkRootcauseid("{}");
		newPcsTlLossreasonlink.setPlrkPldetailid("{}");
		newPcsTlLossreasonlink.setPlrkWno("{}");
		newPcsTlLossreasonlink.setPlrkMinutes("0");
		newPcsTlLossreasonlink.setPlrkInstance("1");
		newPcsTlLossreasonlink.setPlrkDate(womTlWomst.getWomsOccurreddate());
		newPcsTlLossreasonlink.setPlrkShiftid(womTlWomst.getWomsShiftid());
		newPcsTlLossreasonlink.setPlrkHourno("1");
		newPcsTlLossreasonlink.setPlrkFactoryid(womTlWomst.getWomsFactoryid());
		newPcsTlLossreasonlink.setPlrkSectionid(womTlWomst.getWomsSectionid());
		newPcsTlLossreasonlink.setPlrkCellid(womTlWomst.getWomsCellid());
		newPcsTlLossreasonlink.setPlrkMachineid(womTlWomst.getWomsMachineid());
		newPcsTlLossreasonlink.setPlrkSubgroupid("{}");
		newPcsTlLossreasonlink.setPlrkRemarks("{}");
		newPcsTlLossreasonlink.setPlrkTempfield1("{}");
		newPcsTlLossreasonlink.setPlrkTempfield2("{}");
		newPcsTlLossreasonlink.setPlrkTempfield3("{}");
		
		return newPcsTlLossreasonlink;
	}*/
	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ");
		String selectSql = null;
		String likeSql = null ;
		
		if( comboFilter.getIdField()!= null )
			sql.append(comboFilter.getIdField() + " id ");
		
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
		{
			selectSql = "," + comboFilter.getNameField() + "||'-'||" +comboFilter.getCodeField() ;
			//likeSql = "(" + comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%' or "  
			//		 + comboFilter.getNameField() + " like '%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%'  ) ";
		}
		else if( comboFilter.getNameField() != null )
		{
			selectSql = "," + comboFilter.getNameField();
			//likeSql = comboFilter.getNameField() + " like '%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%'";
		}
		else if( comboFilter.getCodeField() != null )
		{
			selectSql = "," + comboFilter.getCodeField();  
		//	likeSql = comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%'"; 
		}

		
		sql.append( selectSql + " text ");
		sql.append(" from ");
		sql.append(comboFilter.getTableName());
		sql.append(" where 1 = 1  " ) ;// + likeSql);
		
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		
	//	if( comboFilter.getOrderByField() != null)
	//		sql.append(" order by " + comboFilter.getOrderByField() );
	//	else
			sql.append(" order by text ");
		ResultSet rs = null; 
		Connection connection = null;
		
		CommonMessage.debugMsg(" sql111 " + sql);
		try{
			rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				if(!rs.getString("id").equals("0"))
				{
					
					compComb.setId(rs.getString("id"));
					compComb.setText(rs.getString("text"));					
					comboList.add(compComb);
				}
				
			}
			ComboBox compComb = new ComboBox();
			compComb.setId("X");
			compComb.setText(" ");					
			comboList.add(compComb);
			return comboList;
		}finally{
			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}
	public List<String[]> checkPCSInsertEnable() throws Exception
	{
		List <String[]> pcsFlagList = dbActionTemplate.getDataList(WomTlWomstSql.checkEnableActivity());
		return pcsFlagList;
	}
	
	public String checkAssmMand() throws Exception
	{
		String checkAssmMand = dbActionTemplate.getSingleValue(WomTlWomstSql.checkAssmMandSql());
		return checkAssmMand;
	}

	public List<String[]> getOrder() throws Exception  {
		String sql=" select * from (SELECT '100001'as NOTIFICATIONNO , 'BD001' as orderno , 'Breakdown' as ordertype,'07:00:00 AM' as occuredDatetime, ";
		sql+=" 'A' as shift, 'Production\\Moulding\\MHI-B2-1 - Moulding Horizontal Injection - B2-1' as functionalLocation , ";
		sql+=" 'H14 - Injection Moulding Machine (IR539)' as Equipment, 'Belt Change' as problem FROM dual union ";
		sql+=" SELECT '100001'as NOTIFICATIONNO , 'Ex0001' as orderno , 'External Request' as ordertype,'08:00:00 AM' as occuredDatetime, 'B' as shift, ";
		sql+="  'Production\\Moulding\\MHI-B2-3 - Moulding Horizontal Injection - B2-3' as functionalLocation , ";
		sql+=" 'EH-16 - Injection Moulding EH - 16' as Equipment, 'Machine Repair' as problem FROM dual ) d ";
		CommonMessage.debugMsg("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;	
	}
	public List<String[]> getOrderMainGrid() throws Exception  {
		String sql=" select * from (SELECT '100001'as Notifi , 'BD001' as orderno , 'Breakdown' as ordertype,'07:00:00 AM' as occuredDatetime, ";
		sql+=" 'A' as shift, 'Production\\Moulding\\MHI-B2-1 - Moulding Horizontal Injection - B2-1' as functionalLocation , ";
		sql+=" 'H14 - Injection Moulding Machine (IR539)' as Equipment, 'Belt Change' as problem FROM dual union ";
		sql+=" SELECT '100001'as Notifi , 'Ex0001' as orderno , 'External Request' as ordertype,'08:00:00 AM' as occuredDatetime, 'B' as shift, ";
		sql+="  'Production\\Moulding\\MHI-B2-3 - Moulding Horizontal Injection - B2-3' as functionalLocation , ";
		sql+=" 'EH-16 - Injection Moulding EH - 16' as Equipment, 'Machine Repair' as problem FROM dual ) d ";
		CommonMessage.debugMsg("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;	
		
	}
	public List<String[]> getStdWoShMainGrid() throws Exception  {
		String sql=" select * from (SELECT 'Coating'as Process , 'Manual Work' as TypeOfManpower ,  ";
		sql+=" 'Production\\Moulding\\MHI-B2-1 - Moulding Horizontal Injection - B2-1' as FunctionalLocation,  ";
		sql+=" 'Step 1' as MajorSteps, '07:00:00 AM' as DateTime, 'ATPL' as PreparedBy , ";
		sql+=" 'ATPL' as ApprovedBy FROM dual union SELECT 'Trimming'as Process , 'Travel' as TypeOfManpower ,";
		sql+=" 'Production\\Moulding\\MHI-B2-3 - Moulding Horizontal Injection - B2-3' as FunctionalLocation, ";
		sql+=" 'Step 2' as MajorSteps, '08:00:00 AM' as DateTime, 'ATPL' as PreparedBy , 'ATPL' as ApprovedBy FROM dual ) d   ";
		CommonMessage.debugMsg("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;	
		
	}
	public List<String[]> getOrderExernal() throws Exception  {
		String sql=" select * from (SELECT 'RMM/000002'as RequestNo , '08-SEP-2009' as RequestedDate ,  ";
		sql+=" 'MW1152' as ReferenceNo,'Factory A' as Factory, 'CH103 - [Curing/Filling (Processing)]' as section,  ";
		sql+=" 'Forming - [Forming]' as cell , 'COMP001 - [Compressor]' as Equipment,'1469' as NoDaysPending, ";
		sql+=" '09-SEP-2009' as ExpectedDate, '-' as ReturenedDate, ";
		sql+=" 'Repair' as NatureofWork, 'Rerigerant unit to be Serviced' as Description, ";
		sql+=" 'Maintenance Engineer' as sendBy, '-' as sento ,'Pending' as status FROM dual) d  ";
		CommonMessage.debugMsg("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;
		
	}
	
	public List<String[]> getSapqueue(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dataList=new ArrayList<String[]>();
		try
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getWORelatedCondStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				paramValues.add(condParms);
				paramValues.add(commonParams);
				dataList= dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.SAP_FN_QUEUETABLESTATUS", paramValues);
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
	
	public List<String[]> getTaskDetails(CommonFilter commonFilter,	String keyid) throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			StringBuffer sf = new StringBuffer();
			
			//sf.append( " SELECT 'Select','KEYID', 'Task', 'Resource', 'Target', 'Status', 'Ideal Cond. Type', 'Ideal Condition', 'Actual Condition', 'Delete' FROM DUAL" );
			//sf.append( " SELECT  '', '12345', 'Task 1', 'Observation', 'Resource 1', to_char(SYSDATE), 'Pending', '20','R','Action', '' as idealType, '100', 'Ok', ''  as del FROM DUAL " );
			//sf.append( " UNION  SELECT '', '12346', 'Task 2','Observation',  'Resource 2', to_char(SYSDATE), 'Pending', '60','G','Action', '' as idealType,'100', 'Ok', '' FROM DUAL " );
			

			sf.append( "SELECT WTMS_KEYID,WTMS_WOMS_KEYID,WTMS_JOB_TYPE,WTMS_FREQUENCY,WTMS_ASSEMBLYID, ASSM_NAME ,WTMS_ACTIVITYNO,");
			sf.append( " WTMS_TASK, ");
			sf.append( " WTMS_IDEAL_CONDITION_TYPE, DECODE(WTMS_IDEAL_CONDITION_TYPE,'O','OK-NOK','M','MIN-MAX','T','TEXT',WTMS_IDEAL_CONDITION_TYPE), WTMS_IDEAL_CONDITION,");
			sf.append( " WTMS_STATUS, ");
			sf.append( " DECODE(WTMS_STATUS,'P','Pending','C','Completed','I','In-progress',WTMS_STATUS) , ");
			
			sf.append( " WTMS_ACTUAL_CONDITION, WTMS_PLANNED_DURATION,WTMS_ACTUAL_DURATION,");
			sf.append( " WTMS_CBM_READING,WTMS_ZONE_COLOR,WTMS_CBM_ACTION,WTMS_ADJUSTED_READING,TO_CHAR(WTMS_NEXT_DUE_DATE,'DD-MON-YYYY'),");
			sf.append( " WTMS_OBSERVATION,WTMS_RESPONSIBILITY,R.EMPM_NAME,");
			sf.append( " replace(to_char(WTMS_TARGET_DATE,'DD-Mon-YYYY'),'31-Dec-2100',''),  ");
			
			sf.append( " NVL(APLD_ACTIONPLAN,WTMS_ACTION_TAKEN) , DECODE(APLD_STATUS,'P','Pending','W','Work in Progress','C','Completed') , C.EMPM_NAME, ");
			sf.append( " replace(to_char(APLD_COMPLEATEDON,'DD-Mon-YYYY'),'31-Dec-2100','') , '' AS BTNACTION, APLM_KEYID||'-'||APLD_KEYID, "); 
			//sf.append( " WTMS_ACTION_TAKEN,");

			sf.append( " WTMS_REMARKS, " );
			
			sf.append( " WTMS_NOTI_REFNO,WTMS_NOTI_STATUS,'','',WTMS_WOMS_REFNO,WTMS_WOMS_STATUS,WTMS_EXTSERVICE_FLAG,WTMS_EXTREPAIR_FLAG,WTMS_ACTIVITYNO,'' ");
			
			sf.append( " FROM WOMS_TL_TASKMST, GEN_TL_EMPLOYEEMST R, GEN_TL_ASSEMBLYMST , ");
			sf.append( "  GEN_TL_ACTIONPLANMST, GEN_TL_ACTIONPLANDTL, GEN_TL_EMPLOYEEMST C ");
			sf.append( " WHERE WTMS_WOMS_KEYID = '" + keyid + "' ");
			sf.append( " AND R.EMPM_KEYID (+) = WTMS_RESPONSIBILITY AND ASSM_KEYID (+) = WTMS_ASSEMBLYID ");
			
			//sf.append( " AND APLM_KEYID (+) = WTMS_ACTIONPLANID  ");
			sf.append( " AND aplm_masterrefid (+) = WTMS_WOMS_KEYID  AND aplm_detailrefid (+) = WTMS_KEYID "); 
			sf.append( " AND APLD_APLM_KEYID (+) = APLM_KEYID   AND C.EMPM_KEYID (+) = APLD_COMPLETEDBY "); 
			
			CommonMessage.debugMsg("task query:"+sf.toString());
			List<String[]> operator = dbActionTemplate.getDataList(sf.toString(), params);
			
			commonFilter.setTotalRecordCnt(operator.size());
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;		
	}
	

	public List<String[]> getResourceInfo(CommonFilter commonFilter,	String keyid) throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			StringBuffer sf = new StringBuffer();
			
			//sf.append( " SELECT 0 as dataorder, 'KEYID', 'Select', 'ResourceId','ResourceName', 'EstTime', 'EstCost', 'ActTime', 'ActCost', 'Delete' FROM DUAL" );
			sf.append( " SELECT '', '12345',  'EMP12345', 'Resource 1', '10', '1000','10', '1000', '' FROM DUAL " );
			sf.append( " union SELECT '', '12346', 'EMP12345', 'Resource 2', '20', '2000','30', '3000', '' FROM DUAL " );
			sf.append( " union SELECT '', '12346', 'EMP12345', 'Resource 3', '10', '1000','20', '2000', '' FROM DUAL " );
			//sf.append( " order by dataorder ");
			
			List<String[]> operator = dbActionTemplate.getDataList(sf.toString(), params);
			
			commonFilter.setTotalRecordCnt(operator.size());
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;		
	}
	
	
	public List<String[]> getOtherCost(CommonFilter commonFilter,	String keyid, String taskId) throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			StringBuffer sf = new StringBuffer();
			
			/*sf.append( " SELECT   '12345', 'Type ', 'INV1234', sysdate, '84000','Remarks 1' FROM DUAL " );
			sf.append( " UNION SELECT '24568', 'Type', 'INV1234', sysdate, '84000','Remarks 2' FROM DUAL " );*/
			sf.append("SELECT wooc_keyid,wooc_type,b.WOTY_TYPE,wooc_invoiceno,to_char(wooc_invoicedate,'dd-Mon-yyyy'),wooc_invoicevalue,wooc_remarks FROM WOM_TL_WOMSOTHERCOST, WOM_TL_OTHERCOST_TYPEMST b ");
			sf.append("WHERE wooc_womskeyid = '");
			sf.append(keyid).append("'");
			if(UIUtils.isValidKeyId(taskId)){
					sf.append(" AND wooc_taskid = '");
					sf.append(taskId);
					sf.append("' ");
			}
			sf.append(" and B.WOTY_KEYID=wooc_type order by wooc_createdon");
			CommonMessage.debugMsg("other cost Sql=="+sf.toString());
			
			List<String[]> operator = dbActionTemplate.getDataList(sf.toString(), params);
			
			commonFilter.setTotalRecordCnt(operator.size());
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;		
	}
	
	public List<String[]> getSparesDetails(CommonFilter commonFilter,	String keyid) throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			StringBuffer sf = new StringBuffer();
			
			//sf.append( " SELECT 0 as dataorder, 'KEYID', 'Task', 'Resource', 'Target', 'Status', 'Ideal Condition', 'Actual Condition', 'Delete' FROM DUAL" );
			sf.append( " SELECT  '12345', 'Spares 1', '15','10', '5', '' FROM DUAL" );
			sf.append( " UNION SELECT  '12346', 'Spares 2', '6','6', '0','' FROM DUAL" );
			sf.append( " UNION SELECT  '12347', 'Spares 3', '12','10', '2','' FROM DUAL " );
			//sf.append( " order by dataorder ");
			
			List<String[]> operator = dbActionTemplate.getDataList(sf.toString(), params);
			
			commonFilter.setTotalRecordCnt(operator.size());
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;		
	}

	@Override
	public void reSubmitSAP() throws Exception {
		// TODO Auto-generated method stub
		
		dbActionTemplate.processPLSQLProcedures("ins_sapnoti_to_workorder", null, null);
		
	}

	@Override
	public List<String[]> getSapReservationDetail(String keyId)
	throws Exception {
// TODO Auto-generated method stub

			StringBuffer sql1 = new StringBuffer();
			String sql = "";			 
			sql1.append("select wmog_sap_morderno,wmog_reservation_no,wmog_confirmation_no,wmog_pr_no,");
			sql1.append("wmog_po_exists from woms_tl_sap_reservation where wmog_masterid='");	
			sql1.append(keyId+"'");
			sql=sql1.toString();
			
			CommonMessage.debugMsg("reservation sql==="+sql.toString());
			List<String[]> resDet = dbActionTemplate.getDataList(sql);
			
			if (resDet.size() >0 ) { 
				CommonMessage.debugMsg("allowList: " +resDet.size());
				CommonMessage.debugMsg("allowList: " +resDet.get(0)[0]);
			}
			
			return resDet;	
			}

	@Override
	public List<String[]> getSapStatus(String womsId, String type)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		if (type.equals("NOTI")){
		sql1.append("select WOMS_SAPNOTFN_NO, WOMS_SAPNOTFN_STATUS,WOMS_SAPNOTFN_MESSAGE from wom_tl_workorder_mst where woms_keyid='");
		sql1.append(womsId+"'");
		}
		else{
		sql1.append("select WOMS_SAPORDER_NO, WOMS_SAPORDER_STATUS,WOMS_SAPORDER_MESSAGE from wom_tl_workorder_mst where woms_keyid='");
		sql1.append(womsId+"'");
		}
		sql=sql1.toString();
		CommonMessage.debugMsg("SQL for getting sap status:type:"+type+":Sql:"+sql);
		List<String[]> resDet = dbActionTemplate.getDataList(sql);
		
		if (resDet.size() >0 ) { 
			CommonMessage.debugMsg("statusList: " +resDet.size());
			CommonMessage.debugMsg("statusList: " +resDet.get(0)[0]);
		}
		
		return resDet;
	}
	
}

