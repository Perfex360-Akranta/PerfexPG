package com.akranta.tpm.dao.impl;


import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.QtmTlInternalrejectionhourlyDao;
import com.akranta.tpm.dao.sql.QtmTlInternalrejectionhourlySql;
import com.akranta.tpm.dao.sql.QtmTlIntrejectionmstSql;
import com.akranta.tpm.model.QtmTlInternalrejectionhourly;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class QtmTlInternalrejectionhourlyDaoImpl implements QtmTlInternalrejectionhourlyDao {


	private DBActionTemplate dbActionTemplate; 
	private QtmTlIntrejectionmstSql qtmTlIntrejectionmstSql;

	public QtmTlInternalrejectionhourlyDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		qtmTlIntrejectionmstSql= new QtmTlIntrejectionmstSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public QtmTlInternalrejectionhourly create(QtmTlInternalrejectionhourly qtmTlInternalrejectionhourly) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlInternalrejectionhourlySql qtmTlInternalrejectionhourlySql = new QtmTlInternalrejectionhourlySql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{			
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<String> charList  = new ArrayList<String>();
			String detailTableName = null;
			//qtmTlInternalrejectionhourly.setQihbKeyid(dbActionTemplate.getSequenceNumber(QtmTlInternalrejectionhourlySql.TBL_QTM_TL_INTERNALREJECTIONHOURLY)); // set the sequnce number
		
			qtmTlInternalrejectionhourly.setQihbKeyid(dbActionTemplate.getSequenceNumber(QtmTlInternalrejectionhourlySql.TBL_QTM_TL_INTERNALREJECTIONHOURLY, 15, "QHB", "MMYY","Y"));
			sqls.add(QtmTlInternalrejectionhourlySql.getInsertSql(qtmTlInternalrejectionhourlySql.getQihbDbFields(), qtmTlInternalrejectionhourly.getSaveArray())); // add insert sql for master table
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
			
			sqls.add(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY=QIHB_INSPECTEDQTY-QIHB_REJECTEDQTY WHERE QIHB_KEYID = ?");
			Object [] INTERNALREJECTIONHOURLY	= {qtmTlInternalrejectionhourly.getQihbKeyid()};
			int [] dataTypesIRH =  {Types.VARCHAR};
			valueList.add(INTERNALREJECTIONHOURLY);
			dataTypes.add(dataTypesIRH);
			charList.add("Q");
			
			List<String[]> qtyList= dbActionTemplate.getDataList(QtmTlInternalrejectionhourlySql.getQtySql(qtmTlInternalrejectionhourly.getQihbQtmKeyid(),qtmTlInternalrejectionhourly.getQihbKeyid()));
			if(qtyList != null)
			{
				if(qtyList.size()>0)
				{
					int insQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[0])?Integer.parseInt(qtyList.get(0)[0]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty());
					int accQty = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty()) - Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());					
					 accQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[1])?Integer.parseInt(qtyList.get(0)[1]):0) + accQty;
					int qarej = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbMrbqty()) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbQahold());
					qarej = (CommonFunctions.isValidKeyId(qtyList.get(0)[2])?Integer.parseInt(qtyList.get(0)[2]):0) + qarej;
					int rejQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[3])?Integer.parseInt(qtyList.get(0)[3]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());
					String lossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_PARAMETERCODE", "QAREJECTION");
					String sectId = dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_SECTIONID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
					detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
					detailTableName = detailTableName.replaceAll("-", "#");
					String detailId= dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_PLDETAILID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
					sqls.add(QtmTlInternalrejectionhourlySql.updateMstSql(insQty, accQty, qtmTlInternalrejectionhourly.getQihbQtmKeyid()));
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
				
			/*Commented By prasanth. coz  moved to 	PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL
				
			/*		sqls.add(QtmTlInternalrejectionhourlySql.updateDtlSql(detailTableName,insQty, accQty,rejQty,qarej, qtmTlInternalrejectionhourly.getQihbQtmKeyid(),lossId));
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
			*/	
					sqls.add(QtmTlIntrejectionmstSql.PCSUpdateFuncn());
					Object [] insertPCSDatas = {detailTableName,detailId,"N","N"};
					int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					valueList.add(insertPCSDatas);
					dataTypes.add(insertPCSTypes);
					charList.add("P");
				}
			}
			
			char [] sqlType = new char[charList.size()];
			CommonMessage.debugMsg("lENGTH : "+sqlType.length);
			for(int c=0;c<sqlType.length;c++)
			{
				CommonMessage.debugMsg(c +" : "+charList.get(c));
				sqlType[c] = charList.get(c).charAt(0);
				CommonMessage.debugMsg(c +" : "+sqlType[c]);
			}
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);

			/*sqls.add(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY=QIHB_INSPECTEDQTY-QIHB_REJECTEDQTY WHERE QIHB_KEYID = '" + qtmTlInternalrejectionhourly.getQihbKeyid() + "' ");
			
			List<String[]> qtyList= dbActionTemplate.getDataList(QtmTlInternalrejectionhourlySql.getQtySql(qtmTlInternalrejectionhourly.getQihbQtmKeyid(),qtmTlInternalrejectionhourly.getQihbKeyid()));
			if(qtyList != null)
			{
				if(qtyList.size()>0)
				{
					
					
					int insQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[0])?Integer.parseInt(qtyList.get(0)[0]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty());
					int accQty = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty()) - Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());					
					 accQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[1])?Integer.parseInt(qtyList.get(0)[1]):0) + accQty;
					int qarej = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbMrbqty()) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbQahold());
					qarej = (CommonFunctions.isValidKeyId(qtyList.get(0)[2])?Integer.parseInt(qtyList.get(0)[2]):0) + qarej;
					int rejQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[3])?Integer.parseInt(qtyList.get(0)[3]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());
					String lossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_PARAMETERCODE", "QAREJECTION");
					sqls.add(QtmTlInternalrejectionhourlySql.updateMstSql(insQty, accQty, qtmTlInternalrejectionhourly.getQihbQtmKeyid()));
					String sectId = dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_SECTIONID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
					detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
					detailTableName = detailTableName.replaceAll("-", "#");
					sqls.add(QtmTlInternalrejectionhourlySql.updateDtlSql(detailTableName,insQty, accQty,rejQty,qarej, qtmTlInternalrejectionhourly.getQihbQtmKeyid(),lossId));
					List<String[]> pcsList= dbActionTemplate.getDataList(QtmTlInternalrejectionhourlySql.getPcsFieldsSql(qtmTlInternalrejectionhourly.getQihbQtmKeyid()));
					if(pcsList != null)
					{
						if(pcsList.size()>0)
						{
							String entryDate = dbActionTemplate.getSingleValue("SELECT to_char(PRLM_ENTRYDATE,'DD-MON-YYYY') FROM PCS_TL_MST WHERE PRLM_KEYID='"+pcsList.get(0)[0]+"'");
							sqls.add(QtmTlInternalrejectionhourlySql.pcsDaySql(pcsList.get(0)[0], pcsList.get(0)[2], lossId, entryDate));
						}
					}
						
					
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			String detailId= dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_PLDETAILID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
			if(UIUtils.isValidKeyId(detailTableName))
				updatePcs(detailTableName, detailId);*/
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return qtmTlInternalrejectionhourly;
	}
	
	public QtmTlInternalrejectionhourly update(QtmTlInternalrejectionhourly qtmTlInternalrejectionhourly)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		QtmTlInternalrejectionhourlySql qtmTlInternalrejectionhourlySql = new QtmTlInternalrejectionhourlySql();
		try {
			String detailTableName = null;
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<String> charList  = new ArrayList<String>();
			
			sqls.add(QtmTlInternalrejectionhourlySql.getUpdateSql(qtmTlInternalrejectionhourlySql.getQihbDbFields(), qtmTlInternalrejectionhourly.getSaveArray()));
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
			
			sqls.add(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY=QIHB_INSPECTEDQTY-QIHB_REJECTEDQTY WHERE QIHB_KEYID = ?");
			Object [] INTERNALREJECTIONHOURLY	= {qtmTlInternalrejectionhourly.getQihbKeyid()};
			int [] dataTypesIRH =  {Types.VARCHAR};
			valueList.add(INTERNALREJECTIONHOURLY);
			dataTypes.add(dataTypesIRH);
			charList.add("Q");
			
			List<String[]> qtyList= dbActionTemplate.getDataList(QtmTlInternalrejectionhourlySql.getQtySql(qtmTlInternalrejectionhourly.getQihbQtmKeyid(),qtmTlInternalrejectionhourly.getQihbKeyid()));
			if(qtyList != null)
			{
				if(qtyList.size()>0)
				{
					int insQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[0])?Integer.parseInt(qtyList.get(0)[0]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty());
					int accQty = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty()) - Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());					
					 accQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[1])?Integer.parseInt(qtyList.get(0)[1]):0) + accQty;
					int qarej = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbMrbqty()) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbQahold());
					qarej = (CommonFunctions.isValidKeyId(qtyList.get(0)[2])?Integer.parseInt(qtyList.get(0)[2]):0) + qarej;
					int rejQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[3])?Integer.parseInt(qtyList.get(0)[3]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());
					String lossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_PARAMETERCODE", "QAREJECTION");
					String sectId = dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_SECTIONID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
					detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
					detailTableName = detailTableName.replaceAll("-", "#");
					String detailId= dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_PLDETAILID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
					sqls.add(QtmTlInternalrejectionhourlySql.updateMstSql(insQty, accQty, qtmTlInternalrejectionhourly.getQihbQtmKeyid()));
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
					
					
					sqls.add(QtmTlInternalrejectionhourlySql.updateDtlSql(detailTableName,insQty, accQty,rejQty,qarej, qtmTlInternalrejectionhourly.getQihbQtmKeyid(),lossId));
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
					
					sqls.add(QtmTlIntrejectionmstSql.PCSUpdateFuncn());
					Object [] insertPCSDatas = {detailTableName,detailId,"N","N"};
					int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
					valueList.add(insertPCSDatas);
					dataTypes.add(insertPCSTypes);
					charList.add("P");
					CommonMessage.debugMsg("After adding function");
				}
			}
			
			char [] sqlType = new char[charList.size()];
			CommonMessage.debugMsg("lENGTH : "+sqlType.length);
			for(int c=0;c<sqlType.length;c++)
			{
				CommonMessage.debugMsg(c +" : "+charList.get(c));
				sqlType[c] = charList.get(c).charAt(0);
				CommonMessage.debugMsg(c +" : "+sqlType[c]);
			}
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
			
			/*sqls.add(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY=QIHB_INSPECTEDQTY-QIHB_REJECTEDQTY WHERE QIHB_KEYID = '" + qtmTlInternalrejectionhourly.getQihbKeyid() + "' ");
			List<String[]> qtyList= dbActionTemplate.getDataList(QtmTlInternalrejectionhourlySql.getQtySql(qtmTlInternalrejectionhourly.getQihbQtmKeyid(),qtmTlInternalrejectionhourly.getQihbKeyid()));
			if(qtyList != null)
			{
				if(qtyList.size()>0)
				{
					//int insQty = Integer.parseInt(qtyList.get(0)[0]) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty());
					//int accQty = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty()) - Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());
					// accQty = Integer.parseInt(qtyList.get(0)[1]) + accQty;
					int insQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[0])?Integer.parseInt(qtyList.get(0)[0]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty());
					int accQty = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbInspectedqty()) - Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());
						accQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[1])?Integer.parseInt(qtyList.get(0)[1]):0) + accQty;
					int qarej = Integer.parseInt(qtmTlInternalrejectionhourly.getQihbMrbqty()) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbQahold());
						qarej = (CommonFunctions.isValidKeyId(qtyList.get(0)[2])?Integer.parseInt(qtyList.get(0)[2]):0) + qarej;
					int rejQty = (CommonFunctions.isValidKeyId(qtyList.get(0)[3])?Integer.parseInt(qtyList.get(0)[3]):0) + Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty());
					String lossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_PARAMETERCODE", "QAREJECTION");
					sqls.add(QtmTlInternalrejectionhourlySql.updateMstSql(insQty, accQty, qtmTlInternalrejectionhourly.getQihbQtmKeyid()));
					String sectId = dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_SECTIONID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
					detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
					detailTableName = detailTableName.replaceAll("-", "#");
					sqls.add(QtmTlInternalrejectionhourlySql.updateDtlSql(detailTableName,insQty, accQty,rejQty,qarej, qtmTlInternalrejectionhourly.getQihbQtmKeyid(),lossId));
					
					
				}
			}
			dbActionTemplate.executeStatements(sqls);
			String detailId= dbActionTemplate.getSingleValue("QTM_TL_INTREJECTIONMST","QIRM_PLDETAILID","QIRM_KEYID",qtmTlInternalrejectionhourly.getQihbQtmKeyid());
			if(UIUtils.isValidKeyId(detailTableName))
				updatePcs(detailTableName, detailId);*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlInternalrejectionhourly;
	}
	
	public QtmTlInternalrejectionhourly delete(QtmTlInternalrejectionhourly qtmTlInternalrejectionhourly)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		QtmTlInternalrejectionhourlySql qtmTlInternalrejectionhourlySql = new QtmTlInternalrejectionhourlySql();
		try {
			
			sqls.add(QtmTlInternalrejectionhourlySql.getDeleteSql(qtmTlInternalrejectionhourlySql.getQihbDbFields(), qtmTlInternalrejectionhourly.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return qtmTlInternalrejectionhourly;
	}	
	
}

