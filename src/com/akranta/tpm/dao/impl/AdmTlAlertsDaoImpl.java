package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdmTlAlertsDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.EntTlAssessmentmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class AdmTlAlertsDaoImpl implements AdmTlAlertsDao {
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;

	public AdmTlAlertsDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	//-- added by vignesh -- //
	public void AdmTlAlertsDaoImplJwt(String JwtToken) {
	    try {
	    //    oplServiceApi = new OplTlMstServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//-- added by vignesh -- //
	@Override
	public List<String[]> getAlertGrid()throws Exception
	{
		//String sql ="SELECT AALM_KEYID, AALM_DESCRIPTION, GETDATACNT(AALM_NAME) AS NCNT FROM ADM_TL_APPALERTMST WHERE AALM_ISTOBEDISPLAYED = 'Y';";
		List<String > paramValues = new ArrayList<String>();
		 paramValues.add("A");
		 paramValues.add("B");
		List<String[]> result=dbActionTemplate.processFunctionCalls("ADM_PC_ADMINISTRATION.ADM_TL_APPALERTS",paramValues);
		
		return result;
	}
	@Override
	public List<String[]> getAlertResGrid(String KeyId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		
		try
		{
		String viewName =dbActionTemplate.getSingleValue("ADM_TL_APPALERTMST","AALM_NAME", "AALM_KEYID", KeyId);
		CommonMessage.debugMsg("viewName:"+viewName);
		String count=dbActionTemplate.getSingleValue(viewName, "count(*)", "1", "1");
		
		long counts=Long.parseLong(count);
		
		
		String sql="select * from "+viewName+" where 1 = 1 "+FilterCondSql.makeGridFilterCond( gridParams.getGridFilters());
		
		//String fromRow = "0";
		/*if(gridParams.getFromRow() == null)
			fromRow = "0";
		else
			fromRow = gridParams.getFromRow();
		*/	
		String sqls = "SELECT  * from ( select ROWNUM as slno, a.* from ( select  * from ( " +sql+" )  where 1 = 1  ) a ) where slno >= ? and slno <= ?";
		//String sqls = "select RowNum as slno ("+sql+") where slno >= "+ gridParams.getFromRow()+"  and slno <= "+ gridParams.getToRow()+"  ORDER BY KEYID DESC ";
		 
		
		gridParams.setTotalRecordCnt(counts);
		List<String> params= new ArrayList<String>();
		params.add(gridParams.getFromRow());
		params.add(gridParams.getToRow());
		//params.add(gridParams.getToRow());
		CommonMessage.debugMsg("params "+params);
		List<String[]> result=dbActionTemplate.getDataListWithColHeader(sqls,params);
		//CommonMessage.debugMsg("LL  "+result.get(0));
		
		return result;
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("EXECEPTION ParmValues:"+e.getMessage());
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public Workbook getAlertsExcel(JSONObject colmodel, String format,GridParams gridParams,String keyId)
			throws Exception {
		CommonMessage.debugMsg("in dao");
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(gridParams,keyId);
			CommonMessage.debugMsg("rptFormat="+format);
			CommonMessage.debugMsg("rs="+rs);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0, 0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	
	}
	private ResultSet getdownTimeReportResultSet(GridParams gridParams,String keyId) throws Exception
	{
		String viewName =dbActionTemplate.getSingleValue("ADM_TL_APPALERTMST","AALM_NAME", "AALM_KEYID", keyId);
		String count=dbActionTemplate.getSingleValue(viewName, "count(*)", "1", "1");
		
		long counts=Long.parseLong(count);
		
		
		String sql="select * from "+viewName+" where 1 = 1 "+FilterCondSql.makeGridFilterCond( gridParams.getGridFilters());
		
		//String fromRow = "0";
		/*5if(gridParams.getFromRow() == null)
			fromRow = "0";
		else
			fromRow = gridParams.getFromRow();
		*/	
		String sqls = "SELECT  * from ( select ROWNUM as slno, a.* from ( select  * from ( " +sql+" )  where 1 = 1  ) a ) where slno >= ? and slno <= ?";
		//String sqls = "select RowNum as slno ("+sql+") where slno >= "+ gridParams.getFromRow()+"  and slno <= "+ gridParams.getToRow()+"  ORDER BY KEYID DESC ";
		 
		
		gridParams.setTotalRecordCnt(counts);
		
		Object [] args = {gridParams.getFromRow(),gridParams.getToRow() };
		//String sqls= "SELECT  * from ( select ROWNUM as slno, a.* from ( select  * from ( " +sql+" )  where 1 = 1  ) a ) where slno >= ? and slno <= ?";
		
		return dbActionTemplate.getData(sqls, args);
		
	}
	//------------------ Reminder fix 04Feb2026 Vignesh ----------------------------------------------------------------//
	@Override
	public List<String[]> getReminderData(String loggeduser, GridParams gridParams, CommonFilter commonFilter) throws Exception {

	    String type = commonFilter.getStatus();   // same as your code
	    CommonMessage.debugMsg("type in dao Impl is " + type);

	    StringBuilder remSql = new StringBuilder();

	    // Base query (same output columns as Oracle method)
	    remSql.append(
	        "SELECT " +
	        "  DOCUMENT AS DOCUMENT, " +
	        "  DOCUMENTNO AS DOCUMENTNO, " +
	        "  ITEM AS ITEM, " +
	        "  to_char(DOCDATE,'DD-Mon-YYYY') AS docdate, " +
	        "  RESPONSIBILITY, " +
	        "  TRDM_NAME AS TRDM_NAME, " +
	        "  ALLPARENTS AS ALLparents, " +
	        "  CREATEDBY AS CREATEDBY " +
	        "FROM GEN_VW_REMAINDERS, GEN_MV_FLIDHIERARCHY " +
	        "WHERE RESPONSIBILITY = '" + loggeduser + "' " +
	        "  AND ABNM_FLID = FLID "
	    );

	    // Oracle: to_char(DOCDATE,'dd-Mon-yyyy') BETWEEN SYSDATE-15 AND SYSDATE
	    // Postgres: DOCDATE BETWEEN (CURRENT_DATE - 15) AND CURRENT_DATE
	    if ("VL".equalsIgnoreCase(type)) {
	        remSql.append(" AND DOCDATE BETWEEN (CURRENT_DATE - 15) AND CURRENT_DATE ");
	    }

	    String remStr = remSql.toString();

	    // keep your existing grid filter logic
	    if (gridParams.getGridFilters() != null) {
	        remStr += FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	    }

	    // Postgres needs alias for subquery in FROM
	    String count = dbActionTemplate.getSingleValue(
	        "SELECT COUNT(*) FROM (" + remStr + ") x"
	    );

	    long counts = Long.parseLong(count);
	    gridParams.setTotalRecordCnt(counts);

	    // Oracle paging (ROWNUM) -> Postgres paging (row_number())
	    // We order by docdate DESC (same intent: latest first)
	    String sqls =
	        "SELECT * FROM ( " +
	        "  SELECT " +
	        "    row_number() OVER ( " +
	        "      ORDER BY to_date(docdate,'DD-Mon-YYYY') DESC " +
	        "    ) AS slno, " +
	        "    a.* " +
	        "  FROM ( " + remStr + " ) a " +
	        ") t " +
	        "WHERE t.slno >= CAST(? AS bigint) " +
	        "  AND t.slno <= CAST(? AS bigint) " +
	        "ORDER BY t.slno";

	    CommonMessage.debugMsg("The Sqls::" + sqls);

	    // IMPORTANT (same as your Approval method):
	    // DBActionTemplate.setString() is hardcoded, so pass args as String
	    Object[] args = {
	        String.valueOf(gridParams.getFromRow()),
	        String.valueOf(gridParams.getToRow())
	    };

	    CommonMessage.debugMsg("The Args " + args[0] + ", " + args[1]);

	    return dbActionTemplate.getDataList(sqls, args);
	}

//	@Override
//	public List<String[]> getReminderData(String loggeduser,GridParams gridParams,CommonFilter commonFilter) throws Exception {
//		String type=commonFilter.getStatus();
//		CommonMessage.debugMsg("type in dao Impl is"+type);
//		StringBuilder remSql=new StringBuilder();
//		if(type.equals("VL")){
//		 remSql = new StringBuilder( "SELECT DOCUMENT AS DOCUMENT,DOCUMENTNO AS DOCUMENTNO,ITEM AS ITEM,to_char(DOCDATE,'dd-Mon-yyyy') AS docdate,RESPONSIBILITY,TRDM_NAME AS TRDM_NAME,ALLparents AS ALLparents,CREATEDBY AS CREATEDBY FROM GEN_VW_REMAINDERS,GEN_MV_FLIDHIERARCHY");
//		 remSql.append(" WHERE RESPONSIBILITY   = '"+loggeduser+"'AND ABNM_FLID=FLID AND to_char(DOCDATE,'dd-Mon-yyyy') BETWEEN SYSDATE-15 AND SYSDATE ");
//		}
//		else {
//			 remSql = new StringBuilder( "SELECT DOCUMENT AS DOCUMENT,DOCUMENTNO AS DOCUMENTNO,ITEM AS ITEM,to_char(DOCDATE,'dd-Mon-yyyy') AS docdate,RESPONSIBILITY,TRDM_NAME AS TRDM_NAME,ALLparents AS ALLparents,CREATEDBY AS CREATEDBY FROM GEN_VW_REMAINDERS,GEN_MV_FLIDHIERARCHY");
//			 remSql.append(" WHERE RESPONSIBILITY   = '"+loggeduser+"' AND ABNM_FLID=FLID ");
//				}
//		    
//		String RemStr=remSql.toString();
//		if(gridParams.getGridFilters()!=null)
//			RemStr+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
//		String count=dbActionTemplate.getSingleValue(" SELECT COUNT(*) FROM ("+RemStr+")");
//		long counts=Long.parseLong(count);
//		gridParams.setTotalRecordCnt(counts);
//		String sqls = "SELECT  * from ( select ROWNUM as slno, a.* from ( select  * from ( " +RemStr+" )  where 1 = 1  ) a ) where slno >= ? and slno <= ?";
//	 	CommonMessage.debugMsg("The Sqls::"+sqls); 
//		Object []  args = { gridParams.getFromRow(), gridParams.getToRow() };
//		CommonMessage.debugMsg("The Args"+args);
//		return dbActionTemplate.getDataList(sqls,args);
//	}
	
	//------------------ Reminder fix 04Feb2026 Vignesh ----------------------------------------------------------------//
	/*@Override
	public Workbook getReminderExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		   try{		
			rs =   getReminderGrid(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();			
			XLConditionalFormats condFormat = new XLConditionalFormats();			
			condFormat.setFontColor(new RGB(255,0,0)); //red font
			 
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			 
			condFormats.add(condFormat);	
			
			XLConditionalFormats condFormatEmpty = new XLConditionalFormats();
			condFormatEmpty.setFontColor(new RGB(254,0,0)); //red font
			 
			condFormatEmpty.setFontHeightPoint((short)14);
			condFormatEmpty.setFontBoldWeight((short)20);
			condFormatEmpty.setFromCol(2);
			condFormatEmpty.setToCol(-1);
			 			
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format,0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  


	}
*/

//	private ResultSet getReminderGrid(CommonFilter commonFilter) throws Exception {
//		// TODO Auto-generated method stub
//		StringBuilder remSql =  new StringBuilder("SELECT DOCUMENT,DOCUMENTNO,ITEM,to_char(DOCDATE,'dd-Mon-yyyy'),RESPONSIBILITY,TRDM_NAME FROM GEN_VW_ACTIONPLAN ");
//		 remSql.append(" WHERE RESPONSIBILITY   = '").append(commonFilter.getEmpch()).append( "'");
//		return dbActionTemplate.getData(remSql.toString()); 
//	}

	public Workbook getReminderExcel(JSONObject colmodel,GridParams gridParams, String format,
			CommonFilter commonFilter,String loggedUser) throws Exception {
		// TODO Auto-generated method stub
		
		ResultSet rs = null;
		   try{	
			   String sql = getRemainderGrid(loggedUser,commonFilter);
				String consql="select * from("+sql.toString()+")where 1=1 ";
				if(gridParams.getGridFilters()!=null)
					consql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				String count="select count(*) from("+consql+")";
				String rowCount=dbActionTemplate.getSingleValue(count);
				long counts=Long.parseLong(rowCount);
				gridParams.setTotalRecordCnt(counts);
				List<String> params= new ArrayList<String>();
				gridParams.setFromRow(null);
				gridParams.setToRow(null);
			   String finalSql="SELECT  * from ( select a.* from ("+consql+") a ) ";
				CommonMessage.debugMsg("Final Sql:" +finalSql);
				rs = dbActionTemplate.getData(finalSql);

				ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0,-1,0 );
			
		   }
		      finally{
		       if( rs != null)
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			 
		   } 
	}
public String getRemainderGrid(String loggeduser,CommonFilter  commonFilter) {
		
	String type=commonFilter.getStatus();
	CommonMessage.debugMsg("type in dao Impl OF excEL IS is"+type);
	
	StringBuilder remSql=null;
	if(type.equals("VL")){
		 remSql = new StringBuilder( "SELECT DOCUMENT AS DOCUMENT,DOCUMENTNO AS DOCUMENTNO,ITEM AS ITEM,to_char(DOCDATE,'dd-Mon-yyyy') AS docdate,RESPONSIBILITY,TRDM_NAME AS TRDM_NAME,ALLparents AS ALLparents,CREATEDBY AS CREATEDBY FROM GEN_VW_REMAINDERS,GEN_MV_FLIDHIERARCHY");
		 remSql.append(" WHERE RESPONSIBILITY   = '"+loggeduser+"'AND ABNM_FLID=FLID AND to_char(DOCDATE,'dd-Mon-yyyy') BETWEEN SYSDATE-15 AND SYSDATE ");
		}
		else {
			 remSql = new StringBuilder( "SELECT DOCUMENT AS DOCUMENT,DOCUMENTNO AS DOCUMENTNO,ITEM AS ITEM,to_char(DOCDATE,'dd-Mon-yyyy') AS docdate,RESPONSIBILITY,TRDM_NAME AS TRDM_NAME,ALLparents AS ALLparents,CREATEDBY AS CREATEDBY FROM GEN_VW_REMAINDERS,GEN_MV_FLIDHIERARCHY");
			 remSql.append(" WHERE RESPONSIBILITY   = '"+loggeduser+"' AND ABNM_FLID=FLID ");
				}
		CommonMessage.debugMsg("SQL:::"+remSql);
		return remSql.toString();
	}

  // --- Vignesh Postgres Fix 30Jan2026 ---------------------------------------------//

//public List<String[]> getApprovalData(String loggeduser, CommonFilter commonFilter) throws Exception {
//
//    StringBuilder apprSql = new StringBuilder(
//        "SELECT URI, DOCUMENTTYPE, DOCUMENTNO, DESCRIPTION, PREPAREDDATE, PREPAREDBY, FLID, ROLENAME " +
//        "FROM ADM_APPROVALS_LIST " +
//        "WHERE APPROVALEMPID = '" + loggeduser + "' " +
//        "ORDER BY CASE upper(DOCUMENTTYPE) " +
//        "   WHEN 'KAIZEN SUGGESTION' THEN 1 " +
//        "   WHEN 'KAIZEN' THEN 2 " +
//        "   WHEN 'ONE POINT LESSON' THEN 3 " +
//        "   WHEN 'SERVICE LEVEL AGREEMENT' THEN 4 " +
//        "   ELSE 999 " +
//        "END"
//    );
//
//    String count = dbActionTemplate.getSingleValue(
//        "SELECT COUNT(*) FROM (" + apprSql.toString() + ") x"
//    );
//    long counts = Long.parseLong(count);
//    commonFilter.setTotalRecordCnt(counts);
//
//    String sqls =
//        "SELECT * FROM ( " +
//        "  SELECT " +
//        "    row_number() OVER ( " +
//        "      ORDER BY DOCUMENTTYPE, to_date(PREPAREDDATE,'DD-Mon-YYYY') DESC " +
//        "    ) AS slno, " +
//        "    a.* " +
//        "  FROM ( " + apprSql.toString() + " ) a " +
//        ") t " +
//        "WHERE t.slno >= CAST(? AS bigint) " +
//        "  AND t.slno <= CAST(? AS bigint) " +
//        "ORDER BY to_date(PREPAREDDATE,'DD-Mon-YYYY') DESC";
//
//    // IMPORTANT: pass args as String because DBActionTemplate.setString() is hardcoded
//    Object[] args = {
//        String.valueOf(commonFilter.getFromRow()),
//        String.valueOf(commonFilter.getToRow())
//    };
//
//    CommonMessage.debugMsg(
//        loggeduser + " Inbox 12 :: New :: " + sqls + " " +
//        commonFilter.getFromRow() + " " + commonFilter.getToRow()
//    );
//
//    return dbActionTemplate.getDataList(sqls, args);
//}

public List<String[]> getApprovalData(String loggeduser, CommonFilter commonFilter) throws Exception {

	CommonMessage.debugMsg("Inside daoimpl alerts");
	
	List<String> paramValues = new ArrayList<String>();
	String condParams = "";
	String commonParams = "";
	
	condParams +="LOGGEDUSER="+loggeduser+";";
	 
	commonParams +="FROMTOROW="+commonFilter.getFromRow()+" AND "+commonFilter.getToRow()+";";
	
	paramValues.add(loggeduser);
	paramValues.add(commonParams);
	
	List<String[]> dataList =  fnCallApi.callFunction("APP_FN_APPROVAL_ALERTS_SB",paramValues,3,true);
	
	String totalCnt = paramValues.get(0); 
	CommonMessage.debugMsg("totalCnt..."+totalCnt);
	boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	
	if(  isInteger )
	{
		long counts=Long.parseLong(totalCnt);
		commonFilter.setTotalRecordCnt(counts);
	}

	return dataList;
	
}


//
//	public List<String[]> getApprovalData(String loggeduser, CommonFilter commonFilter) throws Exception{
//		StringBuilder apprSql = new StringBuilder( "SELECT URI,DOCUMENTTYPE,DOCUMENTNO,DESCRIPTION,PREPAREDDATE,PREPAREDBY,FLID,ROLENAME FROM ADM_APPROVALS_LIST ");
//		apprSql.append(" WHERE APPROVALEMPID = '"+loggeduser+"' order by decode(upper(DOCUMENTTYPE),'KAIZEN SUGGESTION',1,'KAIZEN',2, 'ONE POINT LESSON',3, 'SERVICE LEVEL AGREEMENT',4 )");
//		//apprSql.append(" ,'SERVICE LEVEL AGREEMENT',4,'FIPDEF',5,'FIPMEA',6,'FIPANA',7,'FIPIMP',8,'FIPCON',9,'FIPCLO',10 ) ");
//		
//        String count=dbActionTemplate.getSingleValue(" SELECT COUNT(*) FROM ("+apprSql+")");
//		long counts=Long.parseLong(count);
//		commonFilter.setTotalRecordCnt(counts);
//			
//		String sqls = "SELECT  * from ( select ROWNUM as slno, a.* from ( select  * from ( " +apprSql.toString()+" )  where 1 = 1  ) a ORDER BY DOCUMENTTYPE,TO_DATE(PREPAREDDATE) DESC)   ORDER BY TO_DATE(PREPAREDDATE) DESC"; //where slno >= ? and slno <= ?
//		
//		CommonMessage.debugMsg("sqls>>>>>>"+sqls);
//		
//		Object []  args = { commonFilter.getFromRow(), commonFilter.getToRow() };
//		CommonMessage.debugMsg(loggeduser + " Inbox 12 :: New :: " + sqls+" "+commonFilter.getFromRow()+commonFilter.getToRow());
//		return dbActionTemplate.getDataList(sqls,args);//,args
//		
//	}
//--- Vignesh Postgres Fix 30Jan2026 ---------------------------------------------//
}
