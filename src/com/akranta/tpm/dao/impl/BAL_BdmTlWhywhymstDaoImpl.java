package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlWhywhymstDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhydtlSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlWhywhymstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlYycountermeasurelinkSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlYydonebymstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlYyeffectivedtlSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlYyeffectivemstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlDocupdatesSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.BAL_ToolChangeTlDetailsSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.BAL_BdmTlYycountermeasurelink;
import com.akranta.tpm.model.BAL_BdmTlYydonebymst;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivemst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.BAL_ToolChangeTlDetails;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BAL_BdmTlWhywhymstDaoImpl implements BAL_BdmTlWhywhymstDao {


	private DBActionTemplate dbActionTemplate; 
	private BAL_BdmTlYyeffectivemstSql bdmTlYyeffectivemstSql;
	private BAL_BdmTlYyeffectivedtlSql bdmTlYyeffectivedtlSql;
	private BAL_BdmTlWhywhymstSql bdmTlWhywhymstSql;
	private BAL_BdmTlWhywhydtlSql bdmTlWhywhydtlSql ;
	private GenTlDocupdatesSql genTlDocupdatesSql;
	private BAL_BdmTlYydonebymstSql bdmTlYydonebymstSql;
	
	//private BdmTlMstSql bdmTlMstSql ;

	public BAL_BdmTlWhywhymstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		bdmTlWhywhymstSql = new BAL_BdmTlWhywhymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		bdmTlWhywhydtlSql = new BAL_BdmTlWhywhydtlSql();
		bdmTlYyeffectivemstSql = new BAL_BdmTlYyeffectivemstSql();
		bdmTlYyeffectivedtlSql = new BAL_BdmTlYyeffectivedtlSql();
		genTlDocupdatesSql = new GenTlDocupdatesSql();
		bdmTlYydonebymstSql = new BAL_BdmTlYydonebymstSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_BdmTlWhywhymst create(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		//BdmTlWhywhymst newBdmTlWhywhymst=new BdmTlWhywhymst();
		//BdmTlWhywhymstSql bdmTlWhywhymstSql=new BdmTlWhywhymstSql();
		
		//try{
			
		/*	for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++)
			{
				
				if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid()))
				{
					sqls.add(BdmTlWhywhymstSql.getUpdateSql(bdmTlWhywhymstSql.getWwmsDbFields(), newBdmTlWhywhymst.getSaveArray()));
				}
				else{
					System.out.println("Inside DAO else....");
					newBdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST)); 
					sqls.add(BdmTlWhywhymstSql.getInsertSql(bdmTlWhywhymstSql.getWwmsDbFields(), newBdmTlWhywhymst.getSaveArray()));
				}
				}*/
		

		String elementId = bdmTlWhywhymst.getElementid();
	 	String location = null;
	 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,BAL_BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST);
	 	
		bdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,12,"YY","YYMM"," "));
			//bdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST,12,"YYM","YYMM"," ")); // set the sequnce number
			sqls.add(BAL_BdmTlWhywhymstSql.getInsertSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray())); // add insert sql for master table
			
			/*BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(i); 
			bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());				
    		bdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL,12,"YYD","YY","Y"));
			sqls.add(BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));*/
			insertDetail(bdmTlWhywhymst,sqls);
			
			//dbActionTemplate.executeStatements(sqls);
			
			String clsfcnId = null;
			if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause()))
			{
				//System.out.println("clsfcnId "+clsfcnId);
				clsfcnId = dbActionTemplate.getSingleValue(BAL_BdmTlWhywhymstSql.getClassification(bdmTlWhywhymst.getWwmsRootcause(),getPillar(bdmTlWhywhymst)));
			}		
			String str = bdmTlWhywhymst.getWwmsRefdocno();
			CommonFunctions.debugMsg("strstrstr"+str);
		    str = str.substring(0, 3);
		    if(str.equals("TCD")){
		    	bdmTlWhywhymst.setWwmsRefdoctype("TCD");
		    }
		    CommonFunctions.debugMsg(str+ "strstrstr"+str);
			if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsRefdoctype()))
			{
				System.out.println("bdmTlWhywhymst.getWwmsRefdoctype() "+bdmTlWhywhymst.getWwmsRefdoctype());
				if(bdmTlWhywhymst.getWwmsRefdoctype().equals("BDM"))
					sqls.add(BAL_BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),clsfcnId,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("UPM"))
					sqls.add(BAL_BdmTlMstSql.updateYYUPMSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),clsfcnId,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("KZN"))
					sqls.add(BAL_BdmTlMstSql.updateYYKaizenSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("CMC"))
					sqls.add(BAL_BdmTlMstSql.updateYYccSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("IMT"))
					sqls.add(BAL_BdmTlMstSql.updateYYIMTSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno()));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("DOC"))
					sqls.add(BAL_BdmTlMstSql.updateYYDockSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("TCD"))
					sqls.add(BAL_ToolChangeTlDetailsSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno()));
				
				//StringUtils.substring("abc", 0, 2)  
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("SFT"))
				{
					String rootcauseDueTo = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_ROOTCAUSEMST, "WRCM_CODE", "WRCM_KEYID", bdmTlWhywhymst.getWwmsRootcauseid());
					sqls.add(BAL_BdmTlMstSql.updateYYSafetySql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst,rootcauseDueTo));
					List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(bdmTlWhywhymst.getWwmsRefdocno()));
					String status = "C";
					if(planStatus.size()>0)
					{
						for(int i=0;i<planStatus.size();i++)
						{
							if(planStatus.get(i)[0].equals("P"))
							{
								status = "P";
								break;
							}
						}
						
					}
					//sqls.add(BdmTlMstSql.updateIncidentSql(bdmTlWhywhymst.getWwmsRefdocno(),status));
					//sqls.add(BdmTlMstSql.updateActionPlanIncidentSql(bdmTlWhywhymst.getWwmsRefdocno(),status));
				}
				
			}
			
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt())) 
			{
				if(bdmTlWhywhymst.getWwmsIsojt().equals("Y"))
				  insertCounterMeasureLink(bdmTlWhywhymst,sqls);
			}
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIssop())) 
			{
				if(bdmTlWhywhymst.getWwmsIssop().equals("Y"))
				  insertCounterMeasureLink(bdmTlWhywhymst,sqls);
			}
			insertDocUpdates(bdmTlWhywhymst,sqls);
			dbActionTemplate.executeStatements(sqls);
			
			//populateSqlsForwhywhyDtl(sqls,bdmTlWhywhymst.getBdmTlWhywhydtl(),bdmTlWhywhymst.getWwmsKeyid());
			
			
			 // execute the block of sqls
			
		//}
		
	
		return bdmTlWhywhymst;
	}
	
	private static String getPillar(BAL_BdmTlWhywhymst bdmTlWhywhymst)
	{
		String pillar = "";
		if(bdmTlWhywhymst.getWwmsIsjh().equals("Y"))
			pillar = "JH";
		else if(bdmTlWhywhymst.getWwmsIspm().equals("Y"))
			pillar = "PM";
		else if(bdmTlWhywhymst.getWwmsIskk().equals("Y"))
			pillar = "CI";
		else if(bdmTlWhywhymst.getWwmsIsopl().equals("Y"))
			pillar = "ET";
		else if(bdmTlWhywhymst.getWwmsIspy().equals("Y"))
			pillar = "PY";		
		else
			pillar = "{}";
		System.out.println("Pillar : "+pillar);
		return pillar;
			
	}
	private  String getClsId(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws Exception
	{
		return BAL_BdmTlWhywhymstSql.getClassification(bdmTlWhywhymst.getWwmsRootcause(),getPillar(bdmTlWhywhymst));
	}
	private List<String> insertDetail(BAL_BdmTlWhywhymst bdmTlWhywhymst,List<String> sqls) throws Exception {   //detail insert
		
		CommonFunctions.debugMsg("Size B: "+bdmTlWhywhymst.getBdmTlWhywhydtl().size());
		CommonFunctions.debugMsg("yyyyyyyyyyyyaaaaaa");
		if(bdmTlWhywhymst.getBdmTlWhywhydtl()!= null && bdmTlWhywhymst.getBdmTlWhywhydtl().size()>0) // check for detail table data
		{
			CommonFunctions.debugMsg("Size : "+bdmTlWhywhymst.getBdmTlWhywhydtl().size());
			CommonFunctions.debugMsg("yyyyyyyyyyyyaaaaaa");
	    	for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++)
			{	
	    	
	    		BAL_BdmTlWhywhydtl bdmTlWhywhydtl = (BAL_BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(i); // get detail info from list in empployee object
	    		bdmTlWhywhydtl.setWwdtSlno(Integer.toString(i+1));
	    		CommonFunctions.debugMsg("Sl No in Dao Impl"+bdmTlWhywhydtl.getWwdtSlno());
	    		bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());				
	    		bdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL,12,"YYD","YY","Y"));
				sqls.add(BAL_BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));// add insert sql for detail table
			}
		}
		//dbActionTemplate.executeStatements(sqls);
		return sqls;
	}
	private List<String> insertDocUpdates(BAL_BdmTlWhywhymst bdmTlWhywhymst,List<String> sqls) throws Exception {
		
		GenTlDocupdates genTlDocupdates = new GenTlDocupdates(); 
		fillDocUpdates(genTlDocupdates,bdmTlWhywhymst);
		genTlDocupdates.setDcupKeyid(dbActionTemplate.getSequenceNumber(GenTlDocupdatesSql.TBL_GEN_TL_DOCUPDATES,12,"DPD","YY","Y")); // set the sequnce number
		if(!genTlDocupdates.getDcupUpdatedoctype().equals("PM"))
			sqls.add(GenTlDocupdatesSql.getInsertSql(genTlDocupdatesSql.getDcupDbFields(), genTlDocupdates.getSaveArray())); // add insert sql for master table
		return sqls;
		
	}
	private List<String> insertCounterMeasureLink(BAL_BdmTlWhywhymst bdmTlWhywhymst,List<String> sqls) throws Exception {
			BAL_BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BAL_BdmTlYycountermeasurelink();
			BAL_BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql = new BAL_BdmTlYycountermeasurelinkSql();
			if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
				bdmTlYycountermeasurelink.setYycmYyid(bdmTlWhywhymst.getWwmsKeyid());			
			
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt())) 
			{
			
				if(bdmTlWhywhymst.getWwmsIsojt().equals("Y"))
				{
					if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsOjtdesc()))
					{
						bdmTlYycountermeasurelink.setYycmRefdoctype("OJT");
						bdmTlYycountermeasurelink.setYycmCountermsrid(bdmTlWhywhymst.getWwmsOjtdesc());
					}
					
				}
			}
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIssop())) 
			{
				
				if(bdmTlWhywhymst.getWwmsIssop().equals("Y"))
				{
					if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsSopdesc()))
					{
						bdmTlYycountermeasurelink.setYycmRefdoctype("SOP");
						bdmTlYycountermeasurelink.setYycmCountermsrid(bdmTlWhywhymst.getWwmsSopdesc());
					}
					
				}
			}
			bdmTlYycountermeasurelink.setYycmWoid("{}");
			bdmTlYycountermeasurelink.setYycmTempfield1("-");
			bdmTlYycountermeasurelink.setYycmTempfield2("-");
			bdmTlYycountermeasurelink.setYycmTempfield3("-");
			bdmTlYycountermeasurelink.setYycmTempfield4("-");
			bdmTlYycountermeasurelink.setYycmTempfield5("-");
			bdmTlYycountermeasurelink.setYycmActive("Y");
			if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedby()))
				bdmTlYycountermeasurelink.setYycmCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
			if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedon()))
				bdmTlYycountermeasurelink.setYycmCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
			if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsModifiedon()))
				bdmTlYycountermeasurelink.setYycmModifieyon(bdmTlWhywhymst.getWwmsModifiedon());
			String getcmLinkId = dbActionTemplate.getSingleValue(BAL_BdmTlYycountermeasurelinkSql.getCMSql(bdmTlYycountermeasurelink.getYycmRefdoctype(),bdmTlWhywhymst.getWwmsKeyid()));
			if(CommonFunctions.isValidKeyId(getcmLinkId))
			{
				bdmTlYycountermeasurelink.setYycmKeyid(getcmLinkId);
				sqls.add(BAL_BdmTlYycountermeasurelinkSql.getUpdateSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
			}
			else
			{
				bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK)); // set the sequnce number
				sqls.add(BAL_BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
		
			}
		
		return sqls;
	}
	private List<String> updateDocUpdates(BAL_BdmTlWhywhymst bdmTlWhywhymst,List<String> sqls) throws Exception {
		new GenTlDocupdates();
		String updateSql = GenTlDocupdatesSql.getDocUpdateSql(bdmTlWhywhymst.getWwmsModifiedon(),bdmTlWhywhymst.getWwmsRefdocno());
		CommonFunctions.debugMsg(updateSql);
		//if(!genTlDocupdates.getDcupUpdatedoctype().equals("PM"))
		sqls.add(updateSql); // add insert sql for master table
		return sqls;
		
	}
	private void fillDocUpdates(GenTlDocupdates genTlDocupdates,BAL_BdmTlWhywhymst bdmTlWhywhymst)
	{
		genTlDocupdates.setDcupCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
		genTlDocupdates.setDcupCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
		genTlDocupdates.setDcupModifiedon(bdmTlWhywhymst.getWwmsModifiedon());
		genTlDocupdates.setDcupFeedbackid("{}");
		genTlDocupdates.setDcupUpdatedoctype(getPillar(bdmTlWhywhymst));
		genTlDocupdates.setDcupDetailid("{}");
		genTlDocupdates.setDcupRefdoctype(bdmTlWhywhymst.getWwmsRefdoctype());
		genTlDocupdates.setDcupRefdocid(bdmTlWhywhymst.getWwmsRefdocno());
	}
	
	public BAL_BdmTlWhywhymst update(BAL_BdmTlWhywhymst bdmTlWhywhymst)	throws BusinessApplicationExceptions,Exception { 
		
		List<String> sqls = new ArrayList<String>();
		//BdmTlWhywhydtl newBdmTlWhywhydtl = new BdmTlWhywhydtl();
		//BdmTlWhywhydtlSql 	bdmTlWhywhydtlSql=new 	BdmTlWhywhydtlSql (); 
		//try {
			/*for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++)
			{
			
			if(UIUtils.isValidKeyId(newBdmTlWhywhydtl.getWwdtKeyid()))
			{
				sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(), newBdmTlWhywhydtl.getSaveArray()));
			}
			else{
				System.out.println("Inside DAO else....");
				newBdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(SopTlVisualchecklistmstSql.TBL_SOP_TL_VISUALCHECKLISTMST)); 
				sqls.add(BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(), newBdmTlWhywhydtl.getSaveArray()));
			}
			}*/
			sqls.add(BAL_BdmTlWhywhymstSql.getUpdateSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray()));
			
		  //  sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(), newBdmTlWhywhydtl.getSaveArray()));
			
			/*for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++)
			{	
	    		BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(i); 
	    		bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());
	    		bdmTlWhywhydtl.setWwdtSlno(Integer.toString(i+1));
	    		//sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));
	    		populateSqlsForwhywhyDtl(sqls,bdmTlWhywhymst.getBdmTlWhywhydtl(),bdmTlWhywhymst.getWwmsKeyid());
			}*/
			
			populateSqlsForwhywhyDtl(sqls,bdmTlWhywhymst.getBdmTlWhywhydtl(),bdmTlWhywhymst.getWwmsKeyid());   //detail insert  update
			
			String clsfcnId="";
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause()))
			{
				clsfcnId = dbActionTemplate.getSingleValue(BAL_BdmTlWhywhymstSql.getClassification(bdmTlWhywhymst.getWwmsRootcause(),getPillar(bdmTlWhywhymst)));
			}
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRefdoctype()))
			{
				if(bdmTlWhywhymst.getWwmsRefdoctype().equals("BDM"))
					sqls.add(BAL_BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),clsfcnId,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("UPM"))
					sqls.add(BAL_BdmTlMstSql.updateYYUPMSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),clsfcnId,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("KZN"))
					sqls.add(BAL_BdmTlMstSql.updateYYKaizenSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("CMC"))
					sqls.add(BAL_BdmTlMstSql.updateYYccSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("IMT"))
					sqls.add(BAL_BdmTlMstSql.updateYYIMTSql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno()));
				else if(bdmTlWhywhymst.getWwmsRefdoctype().equals("SFT"))
				{
					String rootcauseDueTo = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_ROOTCAUSEMST, "WRCM_CODE", "WRCM_KEYID", bdmTlWhywhymst.getWwmsRootcauseid());
					sqls.add(BAL_BdmTlMstSql.updateYYSafetySql(bdmTlWhywhymst.getWwmsKeyid(),bdmTlWhywhymst.getWwmsRefdocno(),bdmTlWhywhymst,rootcauseDueTo));
					List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(bdmTlWhywhymst.getWwmsRefdocno()));
					String status = "C";
					if(planStatus.size()>0)
					{
						for(int i=0;i<planStatus.size();i++)
						{
							CommonFunctions.debugMsg(i+" : "+planStatus.get(i)[0]);
							if(planStatus.get(i)[0].equals("P"))
							{
								CommonFunctions.debugMsg("Inside : "+planStatus.get(i)[0]);
								status = "P";
								break;
							}
						}
						
					}
					//sqls.add(BdmTlMstSql.updateIncidentSql(bdmTlWhywhymst.getWwmsRefdocno(),status));
				}
			}
			
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt())) 
			{
				if(bdmTlWhywhymst.getWwmsIsojt().equals("Y"))
				  insertCounterMeasureLink(bdmTlWhywhymst,sqls);
			}
			if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIssop())) 
			{
				if(bdmTlWhywhymst.getWwmsIssop().equals("Y"))
				  insertCounterMeasureLink(bdmTlWhywhymst,sqls);
			}
			/*if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt()) && bdmTlWhywhymst.getWwmsIsojt().equals("Y"))
			{
				BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
				BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql = new BdmTlYycountermeasurelinkSql();
				if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
					bdmTlYycountermeasurelink.setYycmYyid(bdmTlWhywhymst.getWwmsKeyid());			
				bdmTlYycountermeasurelink.setYycmRefdoctype("OJT");
				if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsOjtdesc()))
					bdmTlYycountermeasurelink.setYycmCountermsrid(bdmTlWhywhymst.getWwmsOjtdesc());
				bdmTlYycountermeasurelink.setYycmWoid("{}");
				bdmTlYycountermeasurelink.setYycmTempfield1("-");
				bdmTlYycountermeasurelink.setYycmTempfield2("-");
				bdmTlYycountermeasurelink.setYycmTempfield3("-");
				bdmTlYycountermeasurelink.setYycmTempfield4("-");
				bdmTlYycountermeasurelink.setYycmTempfield5("-");
				bdmTlYycountermeasurelink.setYycmActive("Y");
				if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedby()))
					bdmTlYycountermeasurelink.setYycmCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
				if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedon()))
					bdmTlYycountermeasurelink.setYycmCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
				if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsModifiedon()))
					bdmTlYycountermeasurelink.setYycmModifieyon(bdmTlWhywhymst.getWwmsModifiedon());
				
				String getcmLinkId = dbActionTemplate.getSingleValue(BdmTlYycountermeasurelinkSql.getCMSql(bdmTlYycountermeasurelink.getYycmRefdoctype(),bdmTlWhywhymst.getWwmsKeyid()));
				if(CommonFunctions.isValidKeyId(getcmLinkId))
				{
					bdmTlYycountermeasurelink.setYycmKeyid(getcmLinkId);
					sqls.add(BdmTlYycountermeasurelinkSql.getUpdateSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
				}
				else
				{
					bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK)); // set the sequnce number
					sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
				}
			}*/
			//sqls.add(BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),clsfcnId,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
			String getDocId = dbActionTemplate.getSingleValue(GenTlDocupdatesSql.TBL_GEN_TL_DOCUPDATES, "DCUP_KEYID", "DCUP_REFDOCID", bdmTlWhywhymst.getWwmsRefdocno());
			if(CommonFunctions.isValidKeyId(getDocId))
				updateDocUpdates(bdmTlWhywhymst,sqls);
			else
				insertDocUpdates(bdmTlWhywhymst,sqls);
			//dbActionTemplate.executeStatements(sqls);
			

		dbActionTemplate.executeStatements(sqls);
		//}

	
		/*catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}*/
		
		return bdmTlWhywhymst;
	}
	public String getDocId(String bdId)throws Exception
	{
		String getDocId = dbActionTemplate.getSingleValue(GenTlDocupdatesSql.TBL_GEN_TL_DOCUPDATES, "DCUP_KEYID", "DCUP_REFDOCID", bdId);
		return getDocId;
	}
	public BAL_BdmTlWhywhymst delete(BAL_BdmTlWhywhymst bdmTlWhywhymst)
			throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>();
		CommonFunctions.debugMsg("count11...");
			String countSql = BAL_BdmTlWhywhymstSql.countermeasureCount(bdmTlWhywhymst);
			CommonFunctions.debugMsg("count..."+countSql);
			String count = dbActionTemplate.getSingleValue(countSql);
			
			int cnt = Integer.parseInt(count);
			CommonFunctions.debugMsg("cnt00"+cnt);

			if(cnt==0){
				sqls.add(BAL_BdmTlWhywhydtlSql.getDeleteAllKZNDtl(bdmTlWhywhydtlSql.getWwdtDbFields(),bdmTlWhywhymst.getWwmsKeyid() ));
				sqls.add(BAL_BdmTlWhywhymstSql.getDeleteSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray()));
				sqls.add(BAL_BdmTlWhywhymstSql.getUpdateKznSql(bdmTlWhywhymst));
				dbActionTemplate.executeStatements(sqls);
				CommonFunctions.debugMsg("cnt no:"+cnt);
			}
			else
				throw new BusinessApplicationExceptions("COUNTERMEASUREEXIST");
			
		
		return bdmTlWhywhymst;
	}
	public BAL_BdmTlWhywhydtl deleteYYDtl(String keyId) throws ValidationExceptions, Exception
	{
		List<String> sqls = new ArrayList<String>();
		String delsql = BAL_BdmTlWhywhydtlSql.delYYDtlSql(keyId);
		try {
				sqls.add(delsql);
				dbActionTemplate.executeStatements(sqls);
		}
		catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return null;
		
	}

	public BAL_BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception {
		
		BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst(); 
		String sql = BAL_BdmTlWhywhymstSql.getSelectSql(wwwsKeyid);
		List<Object> bdmTlWhywhymstList  =(List<Object>  )dbActionTemplate.getDataList(sql , bdmTlWhywhymst);
		return (BAL_BdmTlWhywhymst)bdmTlWhywhymstList.get(0);
	}
	public String checkCounterMsr() throws Exception
	{
		CommonFunctions.debugMsg("YysDaoimp");
		String checkCM = dbActionTemplate.getSingleValue(BAL_BdmTlWhywhymstSql.checkCounterMsrSqlExist());
		if(UIUtils.isValidKeyId(checkCM))
		{
			String checkCMVal = dbActionTemplate.getSingleValue(BAL_BdmTlWhywhymstSql.checkCounterMsrSql());
			if(UIUtils.isValidKeyId(checkCM))
				return checkCMVal;
		}
		return "NO";
			
	}
	public BAL_BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception {
		
		BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst(); 
		String sql = BAL_BdmTlWhywhymstSql.getSelectYYSql(refDocId);
		List<Object> bdmTlWhywhymstList  =(List<Object>  )dbActionTemplate.getDataList(sql , bdmTlWhywhymst);
		return (BAL_BdmTlWhywhymst)bdmTlWhywhymstList.get(0);
	}
	
	public List<Object> getWWDT(String wwwsKeyid) throws Exception {
		
		BAL_BdmTlWhywhydtl bdmTlWhywhydtl = new BAL_BdmTlWhywhydtl(); 
		String sql = BAL_BdmTlWhywhydtlSql.getSelectWWDT(wwwsKeyid);
		List<Object> bdmTlWhywhydtlList  = (List<Object> ) dbActionTemplate.getDataList(sql , bdmTlWhywhydtl);
		return  bdmTlWhywhydtlList;
	}
	public List<String[]> getRootCause(String openMode)throws Exception{
		
		String sql = BAL_BdmTlWhywhymstSql.getRootCauseSql(openMode);
		CommonFunctions.debugMsg(sql);
		return dbActionTemplate.getDataList(sql);
	}
	public List<String[]> getPillar(String yyId,String pillarFlag)throws Exception{
		
		String sql = BAL_BdmTlWhywhymstSql.getPillarSql(yyId,pillarFlag);
		CommonFunctions.debugMsg("YY : "+yyId);
		return dbActionTemplate.getDataList(sql);
		
	}
	public List<String []> getProgramList(String start,String end) throws Exception
	{
		String sql = null;
		sql = BAL_BdmTlWhywhymstSql.getProgramSql(start,end);		
		com.akranta.tpm.utils.CommonFunctions.debugMsg("All Chlid Sql "+sql);		
		return dbActionTemplate.getDataList(sql);
		
	}
	public List<String[]> getSelectedRootCause(String yyNo)throws Exception
	{
		String sql = BAL_BdmTlWhywhymstSql.getSelectedRootCauseSql(yyNo);
		System.out.println("gsrc : "+sql);
		return dbActionTemplate.getDataList(sql);
	}
	
	private void populateSqlsForwhywhyDtl(List<String>  sqls, List <BAL_BdmTlWhywhydtl> bdmTlWhywhydtlList,String masterId ) throws Exception
	{
		
	if( bdmTlWhywhydtlList != null)
		{
		int i=1;
		
			for(BAL_BdmTlWhywhydtl bdmTlWhywhydtl : bdmTlWhywhydtlList )
			{	
				bdmTlWhywhydtl.setWwdtWwmsKeyid(masterId);
				CommonFunctions.debugMsg("detailKeyid  :"+bdmTlWhywhydtl.getWwdtKeyid());
				if(!UIUtils.isValidKeyId(bdmTlWhywhydtl.getWwdtKeyid()))
				{	
					bdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL));
					sqls.add(BAL_BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));
				}
				else{
					i=i;
					
					if(bdmTlWhywhydtl.getWwdtSlno()== null || bdmTlWhywhydtl.getWwdtSlno()==""){
											
							String slNo=Integer.toString(i);	
							System.out.println("update query in  before SET Sl No......"+bdmTlWhywhydtl.getWwdtSlno());
						bdmTlWhywhydtl.setWwdtSlno(slNo);
						System.out.println("update query in SET Sl No......"+bdmTlWhywhydtl.getWwdtSlno());
						
					}
					sqls.add(BAL_BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(), bdmTlWhywhydtl.getSaveArray()));
						System.out.println("update query in wwhymstDaoimpl......"+sqls);
						i++;	
				}		
				
			}
		}
	}
	
	public List<String[]> getWhywhyStd(CommonFilter commonFilter) throws Exception
	{
		try
		{

			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add(commonFilter.getKey()!=null?commonFilter.getKey():"{}");
			
			paramValues.add(commonFilter.getFinalTrade()!= null ? commonFilter.getFinalTrade().getId():"{}");
			
			paramValues.add(commonFilter.getFactory()!= null ? commonFilter.getFactory().getId():"{}");		
			
			paramValues.add(commonFilter.getCell()!= null ? commonFilter.getCell().getId():"{}");
			
			paramValues.add(commonFilter.getCostCenter()!= null ? commonFilter.getCostCenter().getId():"{}");
			
			paramValues.add(commonFilter.getJhStep()!= null ? commonFilter.getJhStep().getId():"{}");
			
			paramValues.add(commonFilter.getMachineRank()!= null ? commonFilter.getMachineRank().getId():"{}");
			
			paramValues.add(commonFilter.getMachine() != null ? commonFilter.getMachine().getId():"{}");
			
			paramValues.add(commonFilter.getCircle()!= null ? commonFilter.getCircle().getId():"{}");
			
			paramValues.add(commonFilter.getAssembly()!= null ? commonFilter.getAssembly().getId():"{}");
			
			paramValues.add(commonFilter.getSection()!= null ? commonFilter.getSection().getId():"{}");	
			
			paramValues.add(commonFilter.getPhenomena()!= null ? commonFilter.getPhenomena().getId():"{}");	
			
			paramValues.add(commonFilter.getFinalCause()!= null ? commonFilter.getFinalCause().getId():"{}");	
			
			paramValues.add(commonFilter.getMaintainChargeId()!= null ? commonFilter.getMaintainChargeId().getId():"{}");
			
			paramValues.add(commonFilter.getEqpGroup()!= null ? commonFilter.getEqpGroup().getId():"{}");
			
			paramValues.add(commonFilter.getFromDate());
			
			paramValues.add(commonFilter.getToDate());
			
		
			return dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORT", paramValues);
			
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}	
	public String checkMstExist(String refDocNo) throws Exception
	{
		String count = dbActionTemplate.getSingleValue(BAL_BdmTlWhywhymstSql.checkMstExistSql(refDocNo));
		return count;
	}
	
	public BAL_BdmTlWhywhymst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst();
		String sql = BAL_BdmTlWhywhymstSql.selectWhyWhy();
		System.out.println(sql);
		Object args [] = new Object [] { keyid };
		bdmTlWhywhymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return bdmTlWhywhymst;
	}

	@Override
	public BAL_BdmTlWhywhymst create1(BAL_BdmTlWhywhymst newBdmTlWhywhymst)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_BdmTlWhywhymst selectmaskeyid(String maskeyid) throws Exception {
		BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst();//model
		String sql = BAL_BdmTlWhywhymstSql.selectmaskeyid();
		System.out.println(maskeyid+" sql "+sql);
		Object args [] = new Object [] {maskeyid};
		bdmTlWhywhymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return   bdmTlWhywhymst;
		
	}

	@Override
	public Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		ResultSet rs = null;
		   try{
		
		rs =   getwhywhyReportResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
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
		return excelUtils.writeToExcel(rs,format, 2,0,0 );
		
	   }
	finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}

	private ResultSet getwhywhyReportResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		if(UIUtils.isValidKeyId(commonFilter.getRefdocid()))
			condParms += "REFDOCID="+commonFilter.getRefdocid()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYMST",paramValues);
	}

private List<String> getFilterParamValues(CommonFilter commonFilter) {
List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
//		if (commonFilter.getAbnIsHSE().equals("Y"))
//			condParms+="REFDOCTYPE=SFT;";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public List<String[]> getAllWhywhy(CommonFilter commonFilter)
			throws Exception {
		  System.out.println("inside get abn");
			
			try
			{
				
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
				if(UIUtils.isValidKeyId(commonFilter.getRefdocid()))
					condParms += "REFDOCID="+commonFilter.getRefdocid()+";";
				paramValues.add(condParms);
				paramValues.add(commonParams);
				List<String[]> dataList =  null;
				dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYMST", paramValues);
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					CommonFunctions.debugMsg("totalCnt....."+totalCnt);
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
	public BAL_BdmTlYyeffectivemst yyEffectivenessCreate(BAL_BdmTlYyeffectivemst bdmTlYyeffectivemst) {

		List<String> sqls = new ArrayList<String>();
		try{
			CommonFunctions.debugMsg("inside insert...");
		//bdmTlYyeffectivemst.setYyefKeyid(dbActionTemplate.getSequenceNumber(BdmTlYyeffectivemstSql.TBL_BDM_TL_YYEFFECTIVEMST,12,"YYE","YYMM"," ")); // set the sequnce number
		CommonFunctions.debugMsg(bdmTlYyeffectivemst.getYyefKeyid());
		CommonFunctions.debugMsg(bdmTlYyeffectivemst.getYyefEffectivedate());
		//sqls.add(BdmTlYyeffectivemstSql.getInsertSql(bdmTlYyeffectivemstSql.getYyefDbFields(), bdmTlYyeffectivemst.getSaveArray())); // add insert sql for master table
		insertEffectMaster(bdmTlYyeffectivemst,sqls);
		//insertEffectDetail(bdmTlYyeffectivemst,sqls);
		CommonFunctions.debugMsg("sqls...."+sqls.toString());
		
		dbActionTemplate.executeStatements(sqls);
		//return bdmTlYyeffectivemst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bdmTlYyeffectivemst;
		
	
	}
	private List<String> insertEffectMaster(BAL_BdmTlYyeffectivemst bdmTlYyeffectivemst,List<String> sqls) throws Exception {
		
		String masterKeyid = null;
		if(bdmTlYyeffectivemst.getBdmTlYyeffectivemst()!= null && bdmTlYyeffectivemst.getBdmTlYyeffectivemst().size()>0) // check for detail table data
		{			
	    	for(int i =0;i<bdmTlYyeffectivemst.getBdmTlYyeffectivemst().size();i++)
			{		    		
	    		BAL_BdmTlYyeffectivemst getBdmTlYyeffectivemst = (BAL_BdmTlYyeffectivemst)bdmTlYyeffectivemst.getBdmTlYyeffectivemst().get(i); // get detail info from list in empployee object
	    		String getsql = " SELECT YYEF_KEYID FROM BDM_TL_YYEFFECTIVEMST WHERE YYEF_WWMS_KEYID = '"+getBdmTlYyeffectivemst.getYyefWwmsKeyid()+"' ";
	    		String keyid = dbActionTemplate.getSingleValue(getsql);
	    		if(UIUtils.isValidKeyId(keyid)){
	    			masterKeyid = keyid;
	    			getBdmTlYyeffectivemst.setYyefKeyid(masterKeyid);
	    			sqls.add(BAL_BdmTlYyeffectivemstSql.getUpdateSql(bdmTlYyeffectivemstSql.getYyefDbFields(), getBdmTlYyeffectivemst.getSaveArray()));// add insert sql for detail table
	    		}
	    		else{
	    		//if(!UIUtils.isValidKeyId(getBdmTlYyeffectivedtl.getYyedKeyid())){
	    			masterKeyid = dbActionTemplate.getSequenceNumber(BAL_BdmTlYyeffectivemstSql.TBL_BDM_TL_YYEFFECTIVEMST,12,"YYE","YYMM"," "); 
	    			getBdmTlYyeffectivemst.setYyefKeyid(masterKeyid); // set the sequnce number
	    		sqls.add(BAL_BdmTlYyeffectivemstSql.getInsertSql(bdmTlYyeffectivemstSql.getYyefDbFields(), getBdmTlYyeffectivemst.getSaveArray()));// add insert sql for detail table
	    		}
	    			
			}
		}
		
		if(bdmTlYyeffectivemst.getBdmTlYyeffectivedtl()!= null && bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size()>0) // check for detail table data
		{
			CommonFunctions.debugMsg("Size : "+bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size());
			CommonFunctions.debugMsg("yyyyyyyyyyyyaaaaaa");
	    	for(int i =0;i<bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size();i++)
			{	
	    		
	    		BAL_BdmTlYyeffectivedtl getBdmTlYyeffectivedtl = (BAL_BdmTlYyeffectivedtl)bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().get(i); // get detail info from list in empployee object
	    		getBdmTlYyeffectivedtl.setYyedYyefKeyid(masterKeyid);
	    		if(!UIUtils.isValidKeyId(getBdmTlYyeffectivedtl.getYyedKeyid())){
	    		getBdmTlYyeffectivedtl.setYyedKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlYyeffectivedtlSql.TBL_BDM_TL_YYEFFECTIVEDTL,12,"YYD","YYMM"," ")); // set the sequnce number
	    		sqls.add(BAL_BdmTlYyeffectivedtlSql.getInsertSql(bdmTlYyeffectivedtlSql.getYyedDbFields(), getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table
	    		}
	    		else
	    			sqls.add(BAL_BdmTlYyeffectivedtlSql.getUpdateSql(bdmTlYyeffectivedtlSql.getYyedDbFields(), getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table
	    			
			}
		}
		
		return sqls;
	}
	private List<String> insertEffectDetail(BAL_BdmTlYyeffectivemst bdmTlYyeffectivemst,List<String> sqls) throws Exception {

		if(bdmTlYyeffectivemst.getBdmTlYyeffectivedtl()!= null && bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size()>0) // check for detail table data
		{
			CommonFunctions.debugMsg("Size : "+bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size());
			CommonFunctions.debugMsg("yyyyyyyyyyyyaaaaaa");
	    	for(int i =0;i<bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size();i++)
			{	
	    		BAL_BdmTlYyeffectivedtl getBdmTlYyeffectivedtl = (BAL_BdmTlYyeffectivedtl)bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().get(i); // get detail info from list in empployee object
	    		CommonFunctions.debugMsg(" Checking :: 1234 "+getBdmTlYyeffectivedtl.getYyedKeyid());
	    		getBdmTlYyeffectivedtl.setYyedYyefKeyid(bdmTlYyeffectivemst.getYyefKeyid());
	    		if(!UIUtils.isValidKeyId(getBdmTlYyeffectivedtl.getYyedKeyid())){
	    		getBdmTlYyeffectivedtl.setYyedKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlYyeffectivedtlSql.TBL_BDM_TL_YYEFFECTIVEDTL,12,"YYD","YYMM"," ")); // set the sequnce number
	    		sqls.add(BAL_BdmTlYyeffectivedtlSql.getInsertSql(bdmTlYyeffectivedtlSql.getYyedDbFields(), getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table
	    		}
	    		else
	    			sqls.add(BAL_BdmTlYyeffectivedtlSql.getUpdateSql(bdmTlYyeffectivedtlSql.getYyedDbFields(), getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table
	    			
			}
		}
		//dbActionTemplate.executeStatements(sqls);
		CommonFunctions.debugMsg("dtl sqls..."+sqls);
		return sqls;
	
	}

	@Override
	public BAL_BdmTlYyeffectivemst yyEffectivenessUpdate(BAL_BdmTlYyeffectivemst bdmTlYyeffectivemst) {

		List<String> sqls = new ArrayList<String>();
		try{
		//bdmTlYyeffectivemst.setYyefKeyid(dbActionTemplate.getSequenceNumber(BdmTlYyeffectivemstSql.TBL_BDM_TL_YYEFFECTIVEMST,12,"YYM","YYMM"," ")); // set the sequnce number
		sqls.add(BAL_BdmTlYyeffectivemstSql.getUpdateSql(bdmTlYyeffectivemstSql.getYyefDbFields(), bdmTlYyeffectivemst.getSaveArray())); // add insert sql for master table
		
		insertEffectDetail(bdmTlYyeffectivemst,sqls);
		
		dbActionTemplate.executeStatements(sqls);
		//return bdmTlYyeffectivemst;
		}
		catch(Exception e)
		{
			
		}
		return bdmTlYyeffectivemst;
		
		
	}
	
	public BAL_BdmTlYydonebymst yyDonebyCreate(BAL_BdmTlYydonebymst bdmTlYydonebymst) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			CommonFunctions.debugMsg("yyyyyyyyyyyyaaaaaa");
			bdmTlYydonebymst.setWwdbKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlYydonebymstSql.TBL_BDM_TL_YYDONEBYMST,12,"YYB","YYMM"," ")); // set the sequnce number
	    	sqls.add(BAL_BdmTlYydonebymstSql.getInsertSql(bdmTlYydonebymstSql.getBdm_DbFields(), bdmTlYydonebymst.getSaveArray()));// add insert sql for detail table
	    			
	    	dbActionTemplate.executeStatements(sqls);
		//dbActionTemplate.executeStatements(sqls);
		CommonFunctions.debugMsg("dtl sqls..."+sqls);
		return bdmTlYydonebymst;
	
	}
	
	public String deleteYYDoneBy(String keyId)throws Exception {
		String  sql = " DELETE FROM BDM_TL_YYDONEBYMST WHERE WWDB_KEYID ='" + keyId + "' ";
		dbActionTemplate.executeStatement(sql);
		return "deleted";
	} 

	public List<String[]> getWhyWhyGenDrillData(CommonFilter commonFilter) throws Exception{
		
		List<String> paramValues = getFilterParamValues(commonFilter);
		List<String[]> dataList =  null;
		
		dataList = dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.WHY_FN_WHYWHYGENDRILLDOWN", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 

			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
		
	}
	
	public Workbook getWhyWhyGenDrillDataExcel(CommonFilter commonFilter,
									JSONObject tblJSONObj, String format) throws Exception{
		ResultSet rs = null;
		try{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
			rs =  dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.WHY_FN_WHYWHYGENDRILLDOWN", paramValues);
			
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 1,0,0 );
		
		   }
		finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	@Override
	public String getYYKeyId(String refDocId) throws Exception {
		// TODO Auto-generated method stub
		String sql=BAL_BdmTlWhywhymstSql.getYYrefKyid(refDocId);
		return dbActionTemplate.getSingleValue(sql);
		
	}
			
}

		

