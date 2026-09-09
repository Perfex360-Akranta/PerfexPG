package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.dao.QRCodeGenerationDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;

public class QRCodeGenerationDaoImpl implements QRCodeGenerationDao {
	private DBActionTemplate dbActionTemplate;
	public QRCodeGenerationDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate=dbActionTemplate;
	}
	@Override
	public List<String[]> getQrMasterGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
	
		/*	try{
			StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT '' ASSELECT,'' QRCODE,MCHM_MACHINENO,MCHM_MACHINENAME, FACT_NAME||'-'||FACT_CODE,SECT_NAME||'-'||SECT_CODE,");
		sql.append(" CELL_NAME||'-'||CELL_CODE,CSTM_CODE,MCHM_KEYID,FACT_KEYID,SECT_KEYID,CELL_KEYID");
		sql.append(" FROM GEN_TL_MACHINEMST,GEN_TL_SECTIONMST,GEN_TL_CELLMST,GEN_TL_FACTORYMST,GEN_TL_COSTCENTREMST");
		sql.append(" WHERE MCHM_CELLID=CELL_KEYID ");
		sql.append(" AND CELL_SECTIONID=SECT_KEYID ");
		sql.append(" AND CELL_FACTORYID=FACT_KEYID ");
		sql.append(" AND MCHM_COSTCENTREID=CSTM_KEYID ");
		sql.append(" AND MCHM_CELLID='"+commonFilter.getCellId()+"'");
		sql.append(" AND MCHM_ACTIVE='Y'");
		
		System.out.println("sql.toString()sql.toString()"+sql.toString() );
		List<String[]> dataList = dbActionTemplate.getDataList(sql.toString());
		
		return dataList;
		}catch( Exception e){
			e.printStackTrace();
		}
		*/
		try{
		List<String > paramValues = new ArrayList<String>();
        String condParms = FilterCondSql.getQrCodeRelated(commonFilter);
        String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
       System.out.println("in side the dao"+commonFilter.getMachineId() +" ---- "+commonFilter.getSectionId()+" --- "+commonFilter.getCellId());
  	   
       paramValues.add(condParms);
  	  
  	     paramValues.add(commonParams);   
        System.out.println("paramvalues" + paramValues +"   condParms "+condParms);  
        List<String[]> toolDetailsList = dbActionTemplate.processFunctionCalls("GEN_FN_QRCODEMAINGRID", paramValues);
        if( commonFilter.getViewClick() == 'Y'){
  	    String totalCnt = paramValues.get(0); 
  	    boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
  	   if(  isInteger ){
  		commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
  	    }
       }

       return toolDetailsList;
      }
      catch (Exception e)
      {
      //  throw new Exception(e.getMessage()); 
  e.printStackTrace();
      }
		return null;
	
} 
}
