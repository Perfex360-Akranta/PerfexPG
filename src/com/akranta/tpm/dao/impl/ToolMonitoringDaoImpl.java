package com.akranta.tpm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.TlmTlAlertBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ToolMonitoringDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.PlmTlGenmaintenanceSql;
//import com.akranta.tpm.dao.sql.SapTlSparesreplacedSql;
import com.akranta.tpm.dao.sql.ToolChangeTlDetailsSql;
//import com.akranta.tpm.dao.sql.ToolMstTlSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.model.TlmTLAlertDetails;
import com.akranta.tpm.model.ToolChangeTlDetails;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.Validations;

public class ToolMonitoringDaoImpl implements ToolMonitoringDao {

	private DBActionTemplate dbActionTemplate; 
	ToolChangeTlDetailsSql toolDtlTlsql;
	Validations validations;
	
	public ToolMonitoringDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		toolDtlTlsql=new ToolChangeTlDetailsSql();
		
	}  

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl)
			throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		ToolChangeTlDetailsSql toolDTlSql =new ToolChangeTlDetailsSql();
		newToolTlDtl.setToolKeyid(dbActionTemplate.getSequenceNumber(toolDTlSql.TBL_TLM_TL_TOOLCHANGE, 12, "TCD", "", "Y") ); // set the sequnce number
		

		sqls.add(ToolChangeTlDetailsSql.getInsertSql(toolDTlSql.getToolDbFields(), newToolTlDtl.getSaveArray())); // add insert sql for master table
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		return newToolTlDtl;
	}

	@Override
	public ToolChangeTlDetails update(ToolChangeTlDetails newToolTlDtl)
			throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		ToolChangeTlDetailsSql toolDtlTlSql =new ToolChangeTlDetailsSql();
		//ToolTlMst toolTlMst =new ToolTlMst();
	
		sqls.add(ToolChangeTlDetailsSql.getUpdateSql(toolDtlTlSql.getToolDbFields(), newToolTlDtl.getSaveArray()));
		CommonFunctions.debugMsg("in update "+newToolTlDtl.getToolKeyid());
		
		
		CommonFunctions.debugMsg(" inside main  update  "+sqls.toString());
		dbActionTemplate.executeStatements(sqls);
		//dbActionTemplate.executeStatements(sqls);
		
		return newToolTlDtl;
	}

	@Override
	public ToolChangeTlDetails delete(ToolChangeTlDetails newToolTlDtl)
			throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		ToolChangeTlDetailsSql toolDtlTlSql =new ToolChangeTlDetailsSql();		//ToolTlMst toolTlMst =new ToolTlMst();	
		sqls.add(ToolChangeTlDetailsSql.getDeleteSql(toolDtlTlSql.getToolDbFields(), newToolTlDtl.getSaveArray()));
		CommonFunctions.debugMsg("in update "+newToolTlDtl.getToolKeyid());			
		CommonFunctions.debugMsg(" inside main  update  "+sqls.toString());
		dbActionTemplate.executeStatements(sqls);
		//dbActionTemplate.executeStatements(sqls);		
		return newToolTlDtl;
	}
	public List<String []> getStdTime(String toolId)throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TLTM_STDCOTIME FROM TLM_TL_TOOLMST WHERE TLTM_KEYID='"+toolId+"'");
		ResultSet rs = null; 
		Connection connection = null;
		
		System.out.println(" sql111 " + sql);
		try{
			List<String[]> stdCotime = dbActionTemplate.getDataList(sql.toString());
			return stdCotime;
		}finally{			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);			
		}
	}
	
	//
	
	public List<String []> getEstSharp(String toolId)throws Exception {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select case " );
		sql.append(" when TLTM_EXTENDEDSHARPENING >0 then TLTM_EXTENDEDSHARPENING ");
		sql.append(" else " );
		sql.append(" TLTM_ESTSAHARPENING" );
		sql.append(" end " );
		sql.append(" FROM TLM_TL_TOOLMST WHERE TLTM_KEYID='"+toolId+"'");
		//sql.append("SELECT TLTM_ESTSAHARPENING FROM TLM_TL_TOOLMST WHERE TLTM_KEYID='"+toolId+"'");
		ResultSet rs = null; 
		Connection connection = null;
		
		System.out.println(" sql111 " + sql);
		try{
			List<String[]> stdCotime = dbActionTemplate.getDataList(sql.toString());
			return stdCotime;
		}
		catch( Exception e){
			e.printStackTrace();
		}finally{			
		}
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
			return null;			
		
	}
	
	public List<String []> getLastChangeDate(String toolId,String cellId, String Machineid)throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MAX(TO_DATE(TLTC_DATECHANGED,'DD-MM-YYYY')) FROM TLM_TL_TOOLCHANGE ");
		sql.append(" WHERE TLTC_MACHINEID= '"+Machineid+"' AND ") ;
        sql.append(" TLTC_CELLID='"+cellId+"' AND " );
        sql.append(" TLTC_TOOLKEYID='"+toolId+"'" );
		ResultSet rs = null; 
		Connection connection = null;
		
		System.out.println(" sql1112 " + sql);
		try{
			List<String[]> stdCotime = dbActionTemplate.getDataList(sql.toString());
			return stdCotime;
		}finally{			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);			
		}
	}
	
	public List<String []> getProdLastChange(String toolId,String cellId, String Machineid)throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select NVL(PRODUCEDQTY+ONEDAY,0) AS PRODUCED FROM (   select TRUNC(SUM(PPRD_PRODUCEDQTY))*TLEL_MACHINESHARE/100 as PRODUCEDQTY,TLPC_PRODUCEDQTY AS ONEDAY from PCS_TL_PRODUCTIONDATA,TLM_TL_TOOLEQUIPMENTLINQ,TLM_TL_PCSDATA ");
		sql.append(" where  TLPC_CELLID=PPRD_CELLID AND PPRD_CELLID='"+cellId+"' and  TLEL_MACHINEID='"+Machineid+"' and   TLEL_TOOL_KEYID='"+toolId+"' and   PPRD_DATE ");
		sql.append(" between (select max(TLTC_DATECHANGED) from TLM_TL_TOOLCHANGE where TLTC_CELLID='"+cellId+"' and   TLTC_MACHINEID= '"+Machineid+"' ");
		sql.append(" and  TLTC_TOOLKEYID='"+toolId+"') and sysdate group by TLPC_PRODUCEDQTY,TLEL_MACHINESHARE ");
		sql.append(" )");
		//sql.append(" SELECT TRUNC(SUM(PPRD_PRODUCEDQTY))*TLEL_MACHINESHARE/100 as producedqty FROM PCS_TL_PRODUCTIONDATA,TLM_TL_TOOLEQUIPMENTLINQ WHERE PPRD_CELLID='"+cellId+"' AND  TLEL_MACHINEID='"+Machineid+"' and   TLEL_TOOL_KEYID='"+toolId+"' and  " );                   
		//sql.append(" PPRD_DATE between (select max(TLTC_DATECHANGED) from TLM_TL_TOOLCHANGE where tltc_CELLID='"+cellId+"' AND   TLTC_MACHINEID= '"+Machineid+"' AND  TLTC_TOOLKEYID='"+toolId+"') AND sysdate GROUP BY TLEL_MACHINESHARE" );

		//sql.append(" SELECT SUM(PPRD_PRODUCEDQTY) FROM PCS_TL_PRODUCTIONDATA,TLM_TL_TOOLCHANGE WHERE PPRD_CELLID='"+cellId+"' AND ");
		//sql.append("  TLTC_MACHINEID= '"+Machineid+"' AND ") ;        
      //  sql.append(" TLTC_TOOLKEYID='"+toolId+"'" );
		ResultSet rs = null; 
		Connection connection = null;
		
		System.out.println(" sql 111YUI " + sql);
		try{
			List<String[]> prdLast = dbActionTemplate.getDataList(sql.toString());
			return prdLast;
		}finally{			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);			
		}
	}
	
	public List<String []> getStdLife(String toolId, String mchId)throws Exception {
			String sql = null;
			List<String[]> stdLife=null;
			
			sql=" SELECT MAX(TLTC_EXTENDEDLIFE) FROM ( " + 
			" select TLTC_EXTENDEDLIFE from TLM_TL_TOOLCHANGE where   TLTC_TOOLKEYID='"+toolId+"' and TLTC_MACHINEID='"+mchId+"' and TLTC_EXTENDEDLIFE>0 AND TLTC_ISLFEXTENDED='Y' " + 
            "  union "+
	        " select (TLEL_MCHTOOLLIFE) from TLM_TL_TOOLEQUIPMENTLINQ where TLEL_TOOL_KEYID='"+toolId+"' and TLEL_MACHINEID='"+mchId+"' )" ;
		/*	"		union " +
            " SELECT TLTM_STDLIFE FROM TLM_TL_TOOLMST WHERE TLTM_KEYID='"+toolId+"') ";

			*/
		
			//sql="select TLTC_EXTENDEDLIFE from TLM_TL_TOOLCHANGE where   TLTC_TOOLKEYID='"+toolId+"' AND TLTC_MACHINEID='"+mchId+"' AND TLTC_EXTENDEDLIFE>0 ";
						
			ResultSet rs = null; 
			Connection connection = null;
			try{
			stdLife = dbActionTemplate.getDataList(sql.toString());
				 if (stdLife.get(0)[0]==null || stdLife.get(0)[0]=="" || stdLife.get(0)[0].isEmpty() || stdLife.get(0)[0]=="0"){

					sql=" SELECT TLTM_STDLIFE FROM TLM_TL_TOOLMST WHERE TLTM_KEYID='"+toolId+"'";
				  stdLife = dbActionTemplate.getDataList(sql.toString());
				}
					System.out.println(" sql111 **** " + sql +"  " +stdLife);

			
							

				return stdLife;
		}finally{			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);			
		}
	}
	public List<String []> getSharpening(String toolId, String mchId)throws Exception {
		String sql = null;
		List<String[]> sharpnng=null;
		
		sql=" select TLTC_TOOLSHARPENINGNO from TLM_TL_TOOLCHANGE where TLTC_MACHINEID='"+mchId+"' and TLTC_TOOLKEYID='"+toolId+"' "+
            " and TLTC_DATECHANGED = (select max(TO_DATE(TLTC_DATECHANGED,'DD-MM-YY'))  from  TLM_TL_TOOLCHANGE where TLTC_MACHINEID='"+mchId+"' and TLTC_TOOLKEYID='"+toolId+"') ";
			
		ResultSet rs = null; 
		Connection connection = null;
		try{
			sharpnng = dbActionTemplate.getDataList(sql.toString());
		
			CommonFunctions.debugMsg(" sharpnng "+sharpnng +"sharpnng "+ sql);

			return sharpnng;
	}finally{			
		DBActionTemplate.closeConnection(rs,null,null,null,connection);			
	}
}

	@Override
	public List<String[]> getAllDetailsList(CommonFilter commonFilter)
			throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
		
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 
			System.out.println("paramvalues" + paramValues);  
			
			List<String[]> toolDetailsList = dbActionTemplate.processFunctionCalls("TLM_PC_TOOLMONITORING.TLM_FN_TOOLCHAGEDETAILS", paramValues);
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
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String[]> getAllSumaryRpt(CommonFilter commonFilter)
	throws Exception {
try
{
	
	List<String > paramValues = new ArrayList<String>();
	 String condParms = FilterCondSql.getToolChangeRelated(commonFilter);
	 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	 
	 paramValues.add(condParms);
	 paramValues.add(commonParams);   
	 
	System.out.println("paramvalues" + paramValues +"   condParms "+condParms);  
	  
	List<String[]> toolDetailsList = dbActionTemplate.processFunctionCalls("TLM_PC_TOOLMONITORING.TLM_FN_TOOLCHAGESUMMARY", paramValues);
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
	throw new Exception(e.getMessage()); 
	//e.printStackTrace();
}
}

    public List<String[]> getSumary(CommonFilter commonFilter,String type) throws Exception {
      try
        {
      List<String > paramValues = new ArrayList<String>();
      String condParms = FilterCondSql.getToolChangeRelated(commonFilter);
      String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
      if(type.equals("extended")){
	  condParms+="LIFEEXTENDED=Y;";
      }
      else if(type.equals("early")){
	  condParms+="LIFEEARLY=Y;";
      }
      else{
	  condParms+="SHEDULED=Y;";
      }
	  paramValues.add(condParms);
      paramValues.add(commonParams);   
      System.out.println("paramvalues" + paramValues +"   condParms "+condParms);  
      List<String[]> toolDetailsList = dbActionTemplate.processFunctionCalls("TLM_PC_TOOLMONITORING.TLM_FN_TOOLSUMMARY", paramValues);
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
      throw new Exception(e.getMessage()); 
//e.printStackTrace();
    }
  }
    
    
    public List<String[]> getSumaryDetails(CommonFilter commonFilter) throws Exception {
        try
          {
        List<String > paramValues = new ArrayList<String>();
        String condParms = FilterCondSql.getToolChangeRelated(commonFilter);
        String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
        
  	    paramValues.add(condParms);
        paramValues.add(commonParams);   
        System.out.println("paramvalues" + paramValues +"   condParms "+condParms);  
        List<String[]> toolDetailsList = dbActionTemplate.processFunctionCalls("TLM_PC_TOOLMONITORING.TLM_FN_TOOLSUMMARYDETAILS", paramValues);
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
        throw new Exception(e.getMessage()); 
  //e.printStackTrace();getSumaryDetails
      }
    }
      
    
    ///
    
    public List<String[]> getSumaryGrid(CommonFilter commonFilter,String type,String alertType) throws Exception {
        try
          {
        List<String > paramValues = new ArrayList<String>();
        String condParms = FilterCondSql.getToolChangeRelated(commonFilter);
        String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
        if(alertType.equals("overDue")){
        	condParms+="OVERDUE=OVERDUE;";
        }
        else if(alertType.equals("dueTools")){
        	condParms+="DUE=DUE;";
        }
        else{
        	condParms+="TOMMOROW=TOMMOROW;";
        }
        
        if(type.equals("extended")){
  	  condParms+="LIFEEXTENDED=Y;";
        }
        else if(type.equals("early")){
  	  condParms+="LIFEEARLY=Y;";
        }
        else{
  	  condParms+="SHEDULED=Y;";
        }
  	  paramValues.add(condParms);
  	  
  	     paramValues.add(commonParams);   
        System.out.println("paramvalues" + paramValues +"   condParms "+condParms);  
        List<String[]> toolDetailsList = dbActionTemplate.processFunctionCalls("TLM_PC_TOOLMONITORING.TLM_FN_TOOLSUMMARYGRID", paramValues);
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
        throw new Exception(e.getMessage()); 
  //e.printStackTrace();
      }
    }

	@Override
	public ToolChangeTlDetails getFillValue(String keyId) throws Exception {
		ToolChangeTlDetails toolTlDtl = new ToolChangeTlDetails();
		String sql = ToolChangeTlDetailsSql.getSelect();
		System.out.println("in dao "+keyId );
		Object args [] = new Object [] { keyId };
		toolTlDtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return toolTlDtl;
	}
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl,String alrtKeyid)
	throws Exception {
		String prefix= alrtKeyid.substring(0, 3);
		String tableName=null;
		CommonFunctions.debugMsg(" test in DAOimpl "+prefix);
		if(prefix.equals("OVD")){
			tableName="TLM_TL_OVERDUETOOLALERT";
		}
 		else if(prefix.equals("DUE")){
			tableName="TLM_TL_DUETOOLALERT";
		}
		else if(prefix.equals("AVD")){
			tableName="TLM_TL_ADVANCEALERT";
		}
		String sql="Delete from "+ tableName +" where TALRT_KEYID = '"+alrtKeyid+"'";
List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
ToolChangeTlDetailsSql toolDTlSql =new ToolChangeTlDetailsSql();
newToolTlDtl.setToolKeyid(dbActionTemplate.getSequenceNumber(toolDTlSql.TBL_TLM_TL_TOOLCHANGE, 12, "TCD", "", "Y") ); // set the sequnce number
//sqls.add("Delete from TLM_TL_");

sqls.add(ToolChangeTlDetailsSql.getInsertSql(toolDTlSql.getToolDbFields(), newToolTlDtl.getSaveArray())); // add insert sql for master table
sqls.add(sql);
dbActionTemplate.executeStatements(sqls); // execute the block of sqls

return newToolTlDtl;
}

	@Override
	public String updateSrNo(List<TlmTLAlertDetails> tlmTLAlertDetailsList)
			throws Exception {
			try{
				CommonFunctions.debugMsg(tlmTLAlertDetailsList.size()+"  KEYID==== in side dao impl  "+tlmTLAlertDetailsList.toString());
				List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			
			for( TlmTLAlertDetails newTlmTLAlertDetails : tlmTLAlertDetailsList)
			{ 
				CommonFunctions.debugMsg("KEYID===="+newTlmTLAlertDetails.getKeyid());
				CommonFunctions.debugMsg("qtyyy===="+newTlmTLAlertDetails.getToolShrpNo());
				CommonFunctions.debugMsg("qtyyy===="+newTlmTLAlertDetails.getToolSrNo());
			//	sapTlSparesreplaced.setSspmKeyId(dbActionTemplate.getSequenceNumber(SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
				StringBuffer sql = new StringBuffer();
				sql.append(" UPDATE TLM_TL_ADVANCEALERT ");
				sql.append(" SET TALRT_TOOLSRNO = " + newTlmTLAlertDetails.getToolSrNo() +" ");
				sql.append(" , TALRT_TOOLSHRPNO = '"+ newTlmTLAlertDetails.getToolShrpNo()+"' ");
				sql.append(" WHERE TALRT_KEYID = '" + newTlmTLAlertDetails.getKeyid() +"' "); 
				System.out.println(sql);
				sqls.add(sql.toString());
			}
			
			dbActionTemplate.executeStatements(sqls);
			return "sucess";
			
			}catch(Exception e){
				e.printStackTrace();
				if(UIUtils.isValidKeyId(e.getMessage())){
					CommonFunctions.debugMsg("MKSAPSPAREINFO  "+e.getMessage());
					throw new Exception(e.getMessage());
				}
			  }
			return "success";
			
		}

	@Override
	public TlmTLAlertDetails updateSrNo(TlmTLAlertDetails newTlmTlAlertdetails) throws Exception {
		// TODO Auto-generated method stub
		try{
		//CommonFunctions.debugMsg(newTlmTlAlertdetails.size()+"  KEYID==== in side dao impl  "+newTlmTlAlertdetails.toString());
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
	
	for( TlmTLAlertDetails newTlmTLAlertDetails : newTlmTlAlertdetails.getToolDetails())
	{ 
		CommonFunctions.debugMsg("KEYID===="+newTlmTLAlertDetails.getKeyid());
		CommonFunctions.debugMsg("SrlnNo===="+newTlmTLAlertDetails.getToolSrNo());
		CommonFunctions.debugMsg("ShrpNo===="+newTlmTLAlertDetails.getToolShrpNo());
	//	sapTlSparesreplaced.setSspmKeyId(dbActionTemplate.getSequenceNumber(SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
		String tableName=null;
		String keyId=newTlmTLAlertDetails.getKeyid();
		 String text=keyId.substring(0, 3);
		 if(text.equals("DUE")){
			 tableName="TLM_TL_DUETOOLALERT";
		 }
		 else if(text.equals("ADV")){
			 tableName="TLM_TL_ADVANCEALERT";
			 
		 }
		 else{
			 tableName= "TLM_TL_OVERDUETOOLALERT";
		 }
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE "+tableName+" ");
		sql.append(" SET TALRT_TOOLSRNO = '"+newTlmTLAlertDetails.getToolSrNo().trim()+"' ");
		sql.append(" , TALRT_TOOLSHRPNO = "+newTlmTLAlertDetails.getToolShrpNo().replaceAll("\\s","")+" ");
		sql.append(" WHERE TALRT_KEYID = '"+newTlmTLAlertDetails.getKeyid().trim()+"' "); 
		System.out.println(sql);
		sqls.add(sql.toString());
	}
	dbActionTemplate.executeStatements(sqls);

	
	return newTlmTlAlertdetails;
	
	}catch(Exception e){
		e.printStackTrace();
		if(UIUtils.isValidKeyId(e.getMessage())){
			CommonFunctions.debugMsg("MKSAPSPAREINFO  "+e.getMessage());
			throw new Exception(e.getMessage());
		}
	  }
	return null;
	
}

	@Override
	public Workbook getAllSumaryRptExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getToolSummaryDataResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font TLM_FN_TOOLGRAPHS
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
			return excelUtils.writeToExcel(rs,format, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getToolSummaryDataResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("TLM_PC_TOOLMONITORING.TLM_FN_TOOLCHAGESUMMARY", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		//String keyId = FilterCondSql.getComboSelectionId(commonFilter.getToolid());

			

		String condParms = FilterCondSql.getToolChangeRelated(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 	
		CommonFunctions.debugMsg(commonParams +" tool keyid keyId  "+ condParms);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
		
}

	

