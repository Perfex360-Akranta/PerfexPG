package com.akranta.tpm.dao.impl;





import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.QtmTlQpointmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlUpstreamdefectSql;
import com.akranta.tpm.dao.sql.QtmTlKnowwhydtlSql;
import com.akranta.tpm.dao.sql.QtmTlKnowwhymstSql;
import com.akranta.tpm.dao.sql.QtmTlQpointSql;
import com.akranta.tpm.dao.sql.QtmTlQpointdtlSql;
import com.akranta.tpm.dao.sql.QtmTlQpointdtlsSql;
import com.akranta.tpm.dao.sql.QtmTlQpointmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.model.QtmTlQpoint;
import com.akranta.tpm.model.QtmTlQpointdtl;
import com.akranta.tpm.model.QtmTlQpointdtls;
import com.akranta.tpm.model.QtmTlQpointmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class QtmTlQpointmstDaoImpl implements QtmTlQpointmstDao {


	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;


	public QtmTlQpointmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void QtmTlQpointmstDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public QtmTlQpointmst create(QtmTlQpointmst newqtmTlQpointmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlQpointmstSql qtmTlQpointmstsql= new QtmTlQpointmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			newqtmTlQpointmst.setQpmKeyid(dbActionTemplate.getSequenceNumber(QtmTlQpointmstSql.TBL_QTM_TL_QPOINTMST,8, "QTM", "MMYY", "Y")); // set the sequnce number 
			sqls.add(QtmTlQpointmstSql.getInsertSql(qtmTlQpointmstsql.getQpmDbFields(), newqtmTlQpointmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newqtmTlQpointmst;
	}
	
	public QtmTlQpointmst update(QtmTlQpointmst newqtmTlQpointmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		QtmTlQpointmstSql qtmTlQpointmstSql = new QtmTlQpointmstSql();
		try {

			sqls.add(QtmTlQpointmstSql.getUpdateSql(qtmTlQpointmstSql.getQpmDbFields(), newqtmTlQpointmst.getSaveArray()));
			
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return  newqtmTlQpointmst;
	}
	
	@Override
	public QtmTlQpoint createQpoint(QtmTlQpoint newQtmTlQpoint)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlQpointSql qtmTlQpointSql = new QtmTlQpointSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			newQtmTlQpoint.setQptmKeyid(dbActionTemplate.getSequenceNumber(QtmTlQpointSql.TBL_QTM_TL_QPOINT,10,"QPTS","","Y")); // set the sequnce number 
			sqls.add(QtmTlQpointSql.getInsertSql(qtmTlQpointSql.getQptmDbFields(), newQtmTlQpoint.getSaveArray())); // add insert sql for master table
			
			if(newQtmTlQpoint.getQtmTlQpointdtls()!= null  ){
				popSqlsFornewQtmTlQpointdtls(sqls, newQtmTlQpoint.getQtmTlQpointdtls(), newQtmTlQpoint.getQptmKeyid());
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newQtmTlQpoint;
	}

	private void popSqlsFornewQtmTlQpointdtls(List<String> sqls,
			QtmTlQpointdtls qtmTlQpointdtls, String qptmKeyid) throws Exception {
		// TODO Auto-generated method stub
		
		CommonMessage.debugMsg("inside dtl  "+qtmTlQpointdtls.getQptdQpoint());
		if( qtmTlQpointdtls != null )
		{
			QtmTlQpointdtlsSql qtmTlQpointdtlsSql = new QtmTlQpointdtlsSql(); 
			
			CommonMessage.debugMsg("inside for pop  " +  qtmTlQpointdtls.getQptdQptmKeyid());
			CommonMessage.debugMsg("inside for pop  dtl  " +  qtmTlQpointdtls.getQptdKeyid());
			qtmTlQpointdtls.setQptdQptmKeyid(qptmKeyid);
				if( qtmTlQpointdtls.getQptdKeyid() == null )
				{	
					qtmTlQpointdtls.setQptdKeyid(dbActionTemplate.getSequenceNumber(QtmTlQpointdtlsSql.TBL_QTM_TL_QPOINTDTLS,10,"QPTD","",""));
					sqls.add(QtmTlQpointdtlsSql.getInsertSql(qtmTlQpointdtlsSql.getQptdDbFields(), qtmTlQpointdtls.getSaveArray()));
				}
				else{
					sqls.add(QtmTlQpointdtlsSql.getUpdateSql(qtmTlQpointdtlsSql.getQptdDbFields(), qtmTlQpointdtls.getSaveArray()));
				}
			}
		
		
	}

	@Override
	public QtmTlQpoint updateQpoint(QtmTlQpoint newQtmTlQpoint)
			throws Exception {
		// TODO Auto-generated method stub
        List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
        QtmTlQpointSql qtmTlQpointSql = new QtmTlQpointSql();
				
        try{
			CommonMessage.debugMsg(" Inside Dao Impl:: try block ");
			 
			sqls.add(QtmTlQpointSql.getUpdateSql(qtmTlQpointSql.getQptmDbFields(), newQtmTlQpoint.getSaveArray())); // add insert sql for master table

			if(newQtmTlQpoint.getQtmTlQpointdtls()!= null  ){
				popSqlsFornewQtmTlQpointdtls(sqls, newQtmTlQpoint.getQtmTlQpointdtls(), newQtmTlQpoint.getQptmKeyid());
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return newQtmTlQpoint;
	}
			public QtmTlQpoint deleteQPoint(QtmTlQpoint newQtmTlQpoint)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		QtmTlQpointdtlsSql qtmTlQpointdtlsSql = new QtmTlQpointdtlsSql();
		QtmTlQpointSql 	qmTlQpointSql = new QtmTlQpointSql();
		try {
			if(CommonFunctions.isValidKeyId(newQtmTlQpoint.getQptmKeyid())){
			sqls.add(QtmTlQpointdtlsSql.getDeleteForMasterSql(qtmTlQpointdtlsSql.getQptdDbFields(),  newQtmTlQpoint.getQptmKeyid() ));
			sqls.add(QtmTlQpointSql.getDeleteSql(qmTlQpointSql.getQptmDbFields(), newQtmTlQpoint.getSaveArray()));
			}
			else{
				sqls.add(QtmTlQpointdtlsSql.getDeleteSql(qtmTlQpointdtlsSql.getQptdDbFields(),  newQtmTlQpoint.getQtmTlQpointdtls().getSaveArray() ));
			}
		
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newQtmTlQpoint;
		}	
	
	
	/*@Override
	public QtmTlQpointdtls deleteQPoint(QtmTlQpointdtls newQtmTlQpointdtls)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		QtmTlQpointdtlsSql qtmTlQpointdtlSql = new QtmTlQpointdtlsSql();
	
		CommonMessage.debugMsg("sql.......");
		sqls.add(QtmTlQpointdtlsSql.getDeleteSql(qtmTlQpointdtlSql.getQptdDbFields(), newQtmTlQpointdtls.getSaveArray()));
		CommonMessage.debugMsg("sql......."+sqls); 
		dbActionTemplate.executeStatement(QtmTlQpointdtlsSql.getDeleteSql(qtmTlQpointdtlSql.getQptdDbFields(), newQtmTlQpointdtls.getSaveArray()));
	
	    return newQtmTlQpointdtls;
	}*/
	

	@Override
	public QtmTlQpointmst select(String keyid) throws  Exception {
		// TODO Auto-generated method stub
		QtmTlQpointmst newQtmTlQpointmst=new QtmTlQpointmst();//model
		String sql = QtmTlQpointmstSql.getQtmTlQpointmstSql();
		CommonMessage.debugMsg(keyid+" sql "+sql);
		Object args [] = new Object [] {keyid};
		newQtmTlQpointmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return  newQtmTlQpointmst;
	}

	@Override
	public List<String[]> getmaster(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sqls = "";
		List<String[]> getmaster = new ArrayList<String[]>(); 
		QtmTlQpointmstSql Sql = new QtmTlQpointmstSql();
		
		
			
		
		//	String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			String sql =Sql.getmaster(masterKeyid);
			CommonMessage.debugMsg(" sql "+sql);
			getmaster=dbActionTemplate.getDataList(sql);
			CommonMessage.debugMsg(" getSop "+getmaster.size());
	
			return  getmaster;
	
	}

	@Override
	public List<String[]> getAllSop() throws Exception {
		
		String sqls = "";
		List<String[]> getStudent = null; 
		QtmTlQpointmstSql  Sql = new QtmTlQpointmstSql ();
		
		
			
		
		//	String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			String sql =Sql.getAllSop();
			CommonMessage.debugMsg(" sql "+sql);
		

	
			return dbActionTemplate.getDataList(sql);
	}

	@Override
	public QtmTlQpointmst delete(QtmTlQpointmst newqtmtiqpointmst)
			throws Exception {
		// TODO Auto-generated method stub
         
		List<String> sqls = new ArrayList<String>();
		QtmTlQpointmstSql Sql = new QtmTlQpointmstSql();
		
		try {
			sqls.add( "delete from QTM_TL_QPOINTDTL where  QPD_QPMKEYID ='" + newqtmtiqpointmst.getQpmKeyid()+"'");
			sqls.add(QtmTlQpointmstSql .getDeleteSql(Sql.getQpmDbFields(), newqtmtiqpointmst.getSaveArray()));
            
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){

		}
		return newqtmtiqpointmst;
	}

	@Override
	public QtmTlQpointdtl getalldetail(String detailKeyid) throws Exception {
		String sqls = "";
		
		QtmTlQpointdtl qtmTlpointdtl = new QtmTlQpointdtl();
			
			
			
				
			
			//	String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
				String sql =QtmTlQpointdtlSql.getalldetail();
				CommonMessage.debugMsg(detailKeyid+" sql "+sql);
				Object args [] = new Object [] {detailKeyid};
				qtmTlpointdtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));

		
				return qtmTlpointdtl;
	}

	@Override
	public List<String[]> getPoint(String type) throws Exception {
			StringBuffer sql = new StringBuffer();
			sql.append("Select 'Q-Point','No. of locations/gauges' from dual ");
			if(type.equals("PH")){
				sql.append(" union all");
				sql.append(" select 'SWAS panel sample temperature','22' from dual ");
				sql.append(" union all");
				sql.append(" select 'SWAS panel sample flow','22' from dual ");
				sql.append(" union all");
				sql.append(" select 'LP dosing pump discharge pressure','10' from dual ");
				sql.append(" union all");
				sql.append(" select 'LP dosing tank level','5' from dual ");
			}
			else if(type.equals("CN")){
				sql.append(" union all");
				sql.append(" select 'SWAS panel sample temperature','' from dual ");
				sql.append(" union all");
				sql.append(" select 'SWAS panel sample flow','' from dual ");
				sql.append(" union all");
				sql.append(" select 'HP dosing pump discharge pressure','10' from dual ");
			}
			else if(type.equals("PW")){
				sql.append(" union all");
				sql.append(" select 'Alum Rotameter flow','3' from dual ");
				sql.append(" union all");
				sql.append(" select 'Alum dosing pump Pressure','4' from dual ");
				sql.append(" union all");
				sql.append(" select 'Alum Preparation & Dosing  tank level','2' from dual ");
			}
			else if(type.equals("LH")){
				sql.append(" union all");
				sql.append(" select 'Spray water pump discharge pressure','8' from dual ");
			}
			else if(type.equals("FRC")){
				sql.append(" union all");
				sql.append(" select 'Chlorine flow Rotameter','4' from dual ");
				sql.append(" union all");
				sql.append(" select 'Ejector inlet water pressure','2' from dual ");
				sql.append(" union all");
				sql.append(" select 'Chlorine PRV oulet gas pressure','4' from dual ");
				
			}
			CommonMessage.debugMsg(" sql "+sql);
			List<String[]> getmaster=dbActionTemplate.getDataList(sql.toString());
			return  getmaster;
	}

	@Override
	public List<String[]> getQPointNewGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			
			
			String FNLNID=commonFilter.getFlid();
			String DATE=commonFilter.getDteend();
			
			
			String fromDate=commonFilter.getBefToDt();
			String filter=commonFilter.getBdActivity();
			String toDate=commonFilter.getAftToDt();
			
			if(UIUtils.isValidKeyId(commonFilter.getBefToDt()))
				   condParms+="FROMDATE="+fromDate+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getBdActivity()))
				   condParms+="FILTER="+filter+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getAftToDt()))
				   condParms+="TODATE="+toDate+";";
			
			
			if(UIUtils.isValidKeyId(commonFilter.getDteend()))
			   condParms+="DATE="+DATE+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			   condParms+="FLID="+FNLNID+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg(" FNLNID :: "+FNLNID+" DATE :: "+DATE);
			
//			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_QPOINTSNEWMAINGRID", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("QTM_FN_QPOINTSNEWMAINGRID_SB", paramValues,3,false);
			
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
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}

	@Override
	public List<String[]> getQPointFormNewGrid(CommonFilter commonFilter,String keyId,String area, String kpov, String preparedby)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............"+keyId);
			
			
			
			String FNLNID=commonFilter.getFlid();
			String DATE=commonFilter.getDteend();
			
			if(UIUtils.isValidKeyId(commonFilter.getDteend()))
			   condParms+="DATE="+DATE+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			   condParms+="FLID="+FNLNID+";";
			
			if(UIUtils.isValidKeyId(keyId))
				   condParms+="KEYID="+keyId+";";
			
			if(UIUtils.isValidKeyId(area))
				   condParms+="AREA="+area+";";
			
			if(UIUtils.isValidKeyId(kpov))
				   condParms+="KPOV="+kpov+";";
			
			if(UIUtils.isValidKeyId(preparedby))
				   condParms+="PREPARADBY="+preparedby+";";
			
		
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg(" FNLNID :: "+FNLNID+" DATE :: "+DATE);
			
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_QPOINTFORMGIRD", paramValues);
			
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
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = QtmTlQpointSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	@Override
	public Workbook getQPointExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getQPointResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getQPointResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.NewdbFunctionCall2("QTM_FN_QPOINTSNEWMAINGRID", paramValues);
	
	}

	@Override
	public QtmTlQpoint getFilControlData(String keyid) {
		// TODO Auto-generated method stub
		QtmTlQpoint  qtmTlQpoint = new QtmTlQpoint();
		String getknwwhy;
		CommonMessage.debugMsg("dao impl doubleclick");
		try
		{
			QtmTlQpointSql qtmTlQpointsql = new QtmTlQpointSql();
			
			String sql =  qtmTlQpointsql.getselectsql();
			Object [] args =  new Object [] {keyid };
			qtmTlQpoint.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	}
		catch( Exception e){
		}
		return qtmTlQpoint;
	}
	public List<String[]> getQptscnt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside wertgwer DAO Impl");
				
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
			CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl ::"+commonFilter.getType());
			
			if(UIUtils.isValidKeyId(commonFilter.getType()))
			      condParms +="EMPILLAR="+commonFilter.getType()+";";
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside  opl DAO Impl Cummulative " + paramValues);
			
			//List<String[]>  oplCummulativeList = dbActionTemplate.processFunctionCallsWithColHeaders("OPL_PC_ONEPOINTLESSION.OPL_FN_CUMULATIVERPT", paramValues);
			List<String[]> rootRptList;
			rootRptList = fnCallApi.callFunction ("QTM_FN_CUMULATIVERPT_SB", paramValues,2,true);	
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			 }
		
			return rootRptList	;
			//return oplCummulativeList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook qPpointCumulativeExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   qPpointCumulativeExportExcel(commonFilter);
		    // ADD DEBUGGING
	        if (rs == null) {
	            CommonMessage.debugMsg("ERROR - ResultSet is NULL!");
	            throw new Exception("No data returned from database");
	        }
	        
	        
			ExcelUtils excelUtils = new ExcelUtils(colModel);
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
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet qPpointCumulativeExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		
		 return dbActionTemplate.NewdbFunctionCall2("QTM_FN_CUMULATIVERPT", paramValues);
		 
		    
	}
}

