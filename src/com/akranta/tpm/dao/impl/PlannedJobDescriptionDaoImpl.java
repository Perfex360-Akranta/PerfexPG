package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PlannedJobDescriptionDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.SheTlPlannedjobobservationSql;
import com.akranta.tpm.dao.sql.SheTlPlnjobemplinkSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlPlannedjobobservation;
import com.akranta.tpm.model.SheTlPlnjobemplink;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.PlannedJobObservationServiceApi;




public class PlannedJobDescriptionDaoImpl implements PlannedJobDescriptionDao{
	private DBActionTemplate dbActionTemplate; 
	
	private PlannedJobObservationServiceApi plannedjobobservationserviceapi;
	 FunctionCallApi fnCallApi;


	 public void PlannedJobDescriptionDaoImplJwt(String JwtToken) 
		{
			try{
	    		plannedjobobservationserviceapi = new PlannedJobObservationServiceApi(JwtToken);
			fnCallApi = new FunctionCallApi(JwtToken);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}



	public PlannedJobDescriptionDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlPlannedjobobservation create(SheTlPlannedjobobservation sheTlPlannedjobobservation) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlPlannedjobobservationSql sheTlPlannedjobobservationSql = new SheTlPlannedjobobservationSql(); // contains dbtable,field names, Field types and related sqls  of master table
		List<SheTlPlnjobemplink> sheTlPlnjobemplinkList =sheTlPlannedjobobservation.getSheTlPlnjobemplink();
		try{
			String elementId = sheTlPlannedjobobservation.getPjobElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,SheTlPlannedjobobservationSql.TBL_SHE_TL_PLANNEDJOBOBSERVATION);

		 	sheTlPlannedjobobservation.setPjobKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "PJ", "", "")); // set the sequnce number 
			//sheTlPlannedjobobservation.setPjobKeyid(dbActionTemplate.getSequenceNumber(SheTlPlannedjobobservationSql.TBL_SHE_TL_PLANNEDJOBOBSERVATION, 10, "PJOB", "", "")); // set the sequnce number 
			sqls.add(SheTlPlannedjobobservationSql.getInsertSql(sheTlPlannedjobobservationSql.getPjobDbFields(), sheTlPlannedjobobservation.getSaveArray())); // add insert sql for master table
			if(sheTlPlnjobemplinkList!=null && sheTlPlnjobemplinkList.size()>0)
			{
				for(SheTlPlnjobemplink sheTlPlnjobemplink:sheTlPlnjobemplinkList){
					SheTlPlnjobemplinkSql sheTlPlnjobemplinkSql=new SheTlPlnjobemplinkSql();
					sheTlPlnjobemplink.setPjelPjobKeyid(sheTlPlannedjobobservation.getPjobKeyid());
					sheTlPlnjobemplink.setPjelKeyid(dbActionTemplate.getSequenceNumber(SheTlPlnjobemplinkSql.TBL_SHE_TL_PLNJOBEMPLINK, 10, "PJEL", "", "")); // set the sequnce number
					sqls.add(SheTlPlnjobemplinkSql.getInsertSql(sheTlPlnjobemplinkSql.getPjelDbFields(), sheTlPlnjobemplink.getSaveArray()));
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlPlannedjobobservation;
	}
	
	public SheTlPlannedjobobservation update(SheTlPlannedjobobservation sheTlPlannedjobobservation)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		SheTlPlannedjobobservationSql sheTlPlannedjobobservationSql = new SheTlPlannedjobobservationSql();
		List<SheTlPlnjobemplink> sheTlPlnjobemplinkList =sheTlPlannedjobobservation.getSheTlPlnjobemplink();
			sqls.add(SheTlPlannedjobobservationSql.getUpdateSql(sheTlPlannedjobobservationSql.getPjobDbFields(), sheTlPlannedjobobservation.getSaveArray()));
			CommonFunctions.debugMsg("empIds "+sheTlPlnjobemplinkList.size());
			if(sheTlPlnjobemplinkList!=null && sheTlPlnjobemplinkList.size()>0)
			{
				SheTlPlnjobemplinkSql sheTlPlnjobemplinkSql=new SheTlPlnjobemplinkSql();
				sqls.add(sheTlPlnjobemplinkSql.DeleteEmp(sheTlPlannedjobobservation.getPjobKeyid()));
				for(SheTlPlnjobemplink sheTlPlnjobemplink:sheTlPlnjobemplinkList){
					sheTlPlnjobemplink.setPjelPjobKeyid(sheTlPlannedjobobservation.getPjobKeyid());
					sheTlPlnjobemplink.setPjelKeyid(dbActionTemplate.getSequenceNumber(SheTlPlnjobemplinkSql.TBL_SHE_TL_PLNJOBEMPLINK, 10, "PJEL", "", "")); // set the sequnce number
					sqls.add(SheTlPlnjobemplinkSql.getInsertSql(sheTlPlnjobemplinkSql.getPjelDbFields(), sheTlPlnjobemplink.getSaveArray()));
				}
			}
			dbActionTemplate.executeStatements(sqls);

		return sheTlPlannedjobobservation;
	}
	
	public SheTlPlannedjobobservation delete(SheTlPlannedjobobservation sheTlPlannedjobobservation)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlPlannedjobobservationSql sheTlPlannedjobobservationSql = new SheTlPlannedjobobservationSql();
		try {
			sqls.add(sheTlPlannedjobobservationSql.getDeleteSql(sheTlPlannedjobobservationSql.getPjobDbFields(), sheTlPlannedjobobservation.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlPlannedjobobservation;
	}
	@Override
	public List<String[]> getmaingrid(CommonFilter commonFilter) throws Exception {
		CommonFunctions.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("SHE_FN_PJOBMANIGRID", paramValues);
		List<String[]> dataList =   fnCallApi.callFunction("SHE_FN_PJOBMANIGRID_sb", paramValues,3,false);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonFunctions.debugMsg(dataList);
		return dataList;
	}
	@Override
	public Workbook getPlJobMainExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{	
			rs =   getPlnJbResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	private ResultSet getPlnJbResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("SHE_FN_PJOBMANIGRID", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}
	@Override
	public String getElementID(String originalId) throws Exception {
		String sql = "select fnln_elementid from gen_tl_functionallocn where FNLN_KEYID = '"+originalId+"'";
		CommonFunctions.debugMsg("Element Id sql : "+sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
		CommonFunctions.debugMsg("Element "+getElementID);
		return getElementID;
	}
	@Override
	public SheTlPlannedjobobservation getSelectedData(String keyid)
			throws Exception {
		SheTlPlannedjobobservation sheTlPlannedjobobservation = new SheTlPlannedjobobservation();
		String sql = SheTlPlannedjobobservationSql.select(keyid);
		System.out.println(sql);
		Object args [] = new Object [] { keyid };
		sheTlPlannedjobobservation.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return sheTlPlannedjobobservation;
	}
	@Override
	public List<String []> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception {
		String sql = SheTlPlannedjobobservationSql.selectDep(commonFilter);
		List<String []> depData = dbActionTemplate.getDataList(sql);
		return depData;
	}
	@Override
	public Map<Integer, List<String[]>> PlanExcel(String keyid, String format,
			CommonFilter commonFilter) throws Exception {
		try
		{
			CommonFunctions.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
			paramValues.add(keyid);
			Map<Integer, List<String[]>> plnJobExport= dbActionTemplate.processDbFunCallMultCursor("HSE_FN_PLNJOBOBEXCEL", paramValues,1);
			
			for(int i = 0; i <  plnJobExport.size();i++ )
			{
				CommonFunctions.debugMsg("Inside v: " +plnJobExport.get(i).size() );
			}
			return plnJobExport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public List<String[]> getPojEmployee(String pjobKeyId) throws Exception {
		StringBuilder sql = new StringBuilder( " SELECT '', PJEL_PJOB_EMPLOYEEID,PJEL_KEYID, EMPM_NAME || ' - ' || EMPM_CODE AS \"EMPLOYEE\", ''        AS \"Delete\" ");
			sql.append(" FROM SHE_TL_PLANNEDJOBOBSERVATION, SHE_TL_PLNJOBEMPLINK,GEN_TL_EMPLOYEEMST " );	
			sql.append(" WHERE PJOB_KEYID=PJEL_PJOB_KEYID AND PJEL_PJOB_EMPLOYEEID=EMPM_KEYID " );
			sql.append(" AND PJOB_KEYID='").append(pjobKeyId).append("'" );
			CommonFunctions.debugMsg("Employee SQL ....."+sql);
			List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
			return dataList ;
	}
	
	
	@Override
	public String deleteDetail(String pjelPjobEmployeeid, String gridId) throws Exception,BusinessApplicationExceptions{
		// TODO Auto-generated method stub
		try{
			String sqls ="";
			CommonFunctions.debugMsg("dgridId "+gridId);
	
			
		sqls="delete from SHE_TL_PLNJOBEMPLINK where pjel_pjob_employeeid='" + pjelPjobEmployeeid+ "'"	;
		CommonFunctions.debugMsg(gridId+"  SQls Delete  "+sqls );
		dbActionTemplate.executeStatement(sqls);
		}
		catch(BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e){
			e.printStackTrace();
			return "Data Not Deleted";	
		}
		//return "Data Not Deleted";	
		return "Data Deleted Successfully";
		
	}
    public SheTlPlannedjobobservation UpdateActionPlanId(String pojkeyid,String aplmkeyid)throws Exception{
    	SheTlPlannedjobobservation newsheTlPlannedjobobservation=new SheTlPlannedjobobservation();
    	String sql=SheTlPlannedjobobservationSql.UpdateActionPlan(pojkeyid, aplmkeyid);
    	dbActionTemplate.executeStatement(sql);
    	return newsheTlPlannedjobobservation;
    }
    
	/*public List<String[]> getPJOEmployeeData(GridParams gridParams, CommonFilter commonFilter) throws Exception{
		String sql = getPJOPopup(commonFilter);
		String outerSql="select * from("+sql.toString()+")where 1=1 ";	
		if(gridParams.getGridFilters()!=null)
			outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		String count="select count(*) from("+outerSql+")";
		System.out.println("count in daoimpl"+count);
		String rowCount=dbActionTemplate.getSingleValue(count);
		System.out.println("rowCount in daoimpl"+rowCount);
		long counts=Long.parseLong(rowCount);
		gridParams.setTotalRecordCnt(counts);
		List<String> params= new ArrayList<String>();
		params.add(gridParams.getFromRow());
		params.add(gridParams.getToRow());
		String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+outerSql+") a ) where slno >= ? and slno <= ?";
		System.out.println("finalSql in Dao"+finalSql);
		List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
		return result;	
	}*/

	/*
	 * public List<String[]> getPJOEmployeeData(GridParams gridParams, CommonFilter
	 * commonFilter) throws Exception { String sql = getPJOPopup(commonFilter);
	 * String outerSql = "SELECT * FROM (" + sql.toString() +
	 * ") AS subquery WHERE 1=1 ";
	 * 
	 * if (gridParams.getGridFilters() != null) outerSql +=
	 * FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	 * 
	 * String count = "SELECT COUNT(*) FROM (" + outerSql + ") AS count_query";
	 * System.out.println("count in daoimpl" + count);
	 * 
	 * String rowCount = dbActionTemplate.getSingleValue(count);
	 * System.out.println("rowCount in daoimpl" + rowCount);
	 * 
	 * long counts = Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
	 * 
	 * List<String> params = new ArrayList<String>();
	 * params.add(gridParams.getFromRow()); params.add(gridParams.getToRow());
	 * 
	 * // PostgreSQL pagination with explicit type casting String finalSql =
	 * "SELECT * FROM (" + "SELECT ROW_NUMBER() OVER() AS slno, a.* " + "FROM (" +
	 * outerSql + ") a" + ") AS numbered_rows " +
	 * "WHERE slno >= CAST(? AS BIGINT) AND slno <= CAST(? AS BIGINT)";
	 * 
	 * System.out.println("finalSql in Dao===" + finalSql);
	 * 
	 * List<String[]> result = dbActionTemplate.getDataList(finalSql, params);
	 * return result; }
	 */
    
	/*
	 * public String getPJOPopup(CommonFilter commonFilter){ StringBuffer sql =new
	 * StringBuffer(); String employeeId=commonFilter.getActionKeyId();
	 * if(UIUtils.isValidKeyId(employeeId)){ System.out.println("If");
	 * sql.append("SELECT PJOB_KEYID,EMPM_NAME||'-'||EMPM_CODE,PJOB_TASK_OBSERVED "
	 * ); sql.
	 * append("FROM SHE_TL_PLANNEDJOBOBSERVATION,GEN_TL_EMPLOYEEMST WHERE PJOB_EMPLOYEEID=EMPM_KEYID AND PJOB_EMPLOYEEID='"
	 * +employeeId+"' "); sql.append(" ORDER BY PJOB_KEYID DESC ");
	 * System.out.println("If"+sql.toString()); } return sql.toString(); }
	 */

    @Override
    public List<String[]> getPJOEmployeeData(GridParams gridParams, CommonFilter commonFilter) throws Exception {
        
        CommonFunctions.debugMsg("Inside getPJOEmployeeData daoimpl");
        
        List<String> paramValues = new ArrayList<String>();
        
        String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
        
        String employeeId = commonFilter.getActionKeyId();
        
        if (employeeId != null && !employeeId.isEmpty()) {
            if (!condParms.contains("EMPLOYEEID=")) {
                condParms = condParms + "EMPLOYEEID=" + employeeId + ";";
            }
        }
        
        CommonFunctions.debugMsg("condParms: " + condParms);
        
        String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
        
        paramValues.add(condParms);
        paramValues.add(commonParams);
        
        List<String[]> dataList = fnCallApi.callFunction("GEN_FN_PJOPOPUP_SB", paramValues, 3, true);
        
        if (commonFilter.getViewClick() == 'Y') {
            String totalCnt = paramValues.get(0);
            CommonFunctions.debugMsg("totalCnt...." + totalCnt);
            try {
                commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
            } catch (NumberFormatException e) {
                CommonFunctions.debugMsg("totalCnt is not a valid number: " + totalCnt);
            }
        }
        
        CommonFunctions.debugMsg(dataList);
        return dataList;
    }
}
