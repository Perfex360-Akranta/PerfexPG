package com.akranta.tpm.dao.impl;




import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.JhaTlAuditreportmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.JhaTlAuditreportdtlSql;
import com.akranta.tpm.dao.sql.JhaTlAuditreportmstSql;
import com.akranta.tpm.dao.sql.PcsTlOtherlossentrySql;
//import com.akranta.tpm.dao.sql.SheTlPlannedjobobservationSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditreportdtl;
import com.akranta.tpm.model.JhaTlAuditreportmst;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class JhaTlAuditreportmstDaoImpl implements JhaTlAuditreportmstDao {


	private DBActionTemplate dbActionTemplate; 
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";

	public JhaTlAuditreportmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public JhaTlAuditreportmst create(JhaTlAuditreportmst jhaTlAuditreportmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlAuditreportmstSql jhaTlAuditreportmstSql = new JhaTlAuditreportmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			jhaTlAuditreportmst.setAurmKeyid(dbActionTemplate.getSequenceNumber(JhaTlAuditreportmstSql.TBL_JHA_TL_AUDITREPORTMST)); // set the sequnce number 
			sqls.add(JhaTlAuditreportmstSql.getInsertSql(jhaTlAuditreportmstSql.getAurmDbFields(), jhaTlAuditreportmst.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditreportmst;
	}
	
	public JhaTlAuditreportmst update(JhaTlAuditreportmst jhaTlAuditreportmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		List<JhaTlAuditreportdtl> jhaTlAuditreportdtlList=jhaTlAuditreportmst.getJhaTlAuditreportdtl();
		JhaTlAuditreportmstSql jhaTlAuditreportmstSql = new JhaTlAuditreportmstSql();
		JhaTlAuditreportdtlSql jhaTlAuditreportdtlSql = new JhaTlAuditreportdtlSql();
		try {
			sqls.add(JhaTlAuditreportmstSql.getUpdateSql(jhaTlAuditreportmstSql.getAurmDbFields(), jhaTlAuditreportmst.getSaveArray()));
			if(jhaTlAuditreportdtlList!=null ){
				if(jhaTlAuditreportdtlList.size()>0){
					for(JhaTlAuditreportdtl jhaTlAuditreportdtl:jhaTlAuditreportdtlList){
						sqls.add(JhaTlAuditreportdtlSql.getUpdateSql(jhaTlAuditreportdtlSql.getAurdDbFields(), jhaTlAuditreportdtl.getSaveArray()));
					}
				}
			}
			CommonMessage.debugMsg("sqls:  "+sqls.toString());
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditreportmst;
	}
	
	public JhaTlAuditreportmst delete(JhaTlAuditreportmst jhaTlAuditreportmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlAuditreportmstSql jhaTlAuditreportmstSql=new JhaTlAuditreportmstSql();
		try {
			
			sqls.add(JhaTlAuditreportdtlSql.getDeletedtlSql(jhaTlAuditreportmst));
			sqls.add(JhaTlAuditreportmstSql.getDeleteSql(jhaTlAuditreportmstSql.getAurmDbFields(), jhaTlAuditreportmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditreportmst;
	}

	@Override
	public String populateTempTable(String excelFileName,
			JhaTlAuditreportmst jhaTlAuditreportmst) throws Exception {
		CommonMessage.debugMsg("dao impl"+ excelFileName);
		Map<Integer,List<Object[]>> auditDataList =new HashMap<Integer,List<Object[]>>();
		
		JhaTlAuditreportmstSql jhaTlAuditreportmstSql = new JhaTlAuditreportmstSql();
		String Keyid=jhaTlAuditreportmst.getAurmKeyid();
		if(!UIUtils.isValidKeyId(jhaTlAuditreportmst.getAurmKeyid()))
			jhaTlAuditreportmst.setAurmKeyid(dbActionTemplate.getSequenceNumber(JhaTlAuditreportmstSql.TBL_JHA_TL_AUDITREPORTMST,15,"ARM","","")); // set the sequnce number

		int [] insertDataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		int [] updateDataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		int [] colDataTypes = {Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		FileInputStream file = null;
		Sheet sheet = null;
		Workbook workbook = null;
		StringBuilder sql =new StringBuilder();
		sql.append( " INSERT INTO JHA_TL_AUDITREPORTDTL (AURD_KEYID,AURD_AURM_KEYID, AURD_ORDERNO,AURD_SHEETNUMBER,AURD_SHEETNAME,");
		sql.append( " AURD_SLNO,AURD_AUDITELEMENT,AURD_MM,AURD_MA,AURD_OBSERVATIONS,AURD_REMARKS,");
		sql.append( " AURD_TEMPFIELD1,AURD_TEMPFIELD2,AURD_TEMPFIELD3,AURD_ACTIVE,AURD_CREATEDBY,AURD_CREATEDON,AURD_MODIFIEDON) ");
		sql.append( " VALUES( ?,'"+jhaTlAuditreportmst.getAurmKeyid()+"', ?,?,?,?,?,?,?,?,?,");
		sql.append( " '"+jhaTlAuditreportmst.getAurmTempfield1()+"','"+jhaTlAuditreportmst.getAurmTempfield2()+"',");
		sql.append( "'"+jhaTlAuditreportmst.getAurmTempfield3()+"','"+jhaTlAuditreportmst.getAurmActive()+"',");
		sql.append( "'"+jhaTlAuditreportmst.getAurmCreatedby()+"', to_date( '"+jhaTlAuditreportmst.getAurmCreatedon()+"','dd-Mon-yyyy hh24:mi:ss'),");
		sql.append( "to_date('"+jhaTlAuditreportmst.getAurmModifiedon()+"','dd-Mon-yyyy hh24:mi:ss') ) ");
		
		Object[] masterDataInsert =   {   jhaTlAuditreportmst.getAurmKeyid(),
			jhaTlAuditreportmst.getAurmFlid(),
			jhaTlAuditreportmst.getAurmElementid(),
			jhaTlAuditreportmst.getAurmDate(),
			jhaTlAuditreportmst.getAurmPreparedby(),
			jhaTlAuditreportmst.getAurmUploadedby(),
			jhaTlAuditreportmst.getAurmUploadedfile(),
			jhaTlAuditreportmst.getAurmTempfield1(),
			jhaTlAuditreportmst.getAurmTempfield2(),
			jhaTlAuditreportmst.getAurmTempfield3(),
			jhaTlAuditreportmst.getAurmActive(),
			jhaTlAuditreportmst.getAurmCreatedby()
		};
		Object[] masterDataUpdate =   {   jhaTlAuditreportmst.getAurmKeyid(),
			jhaTlAuditreportmst.getAurmFlid(),
			jhaTlAuditreportmst.getAurmElementid(),
			jhaTlAuditreportmst.getAurmDate(),
			jhaTlAuditreportmst.getAurmPreparedby(),
			jhaTlAuditreportmst.getAurmUploadedby(),
			jhaTlAuditreportmst.getAurmUploadedfile(),
			jhaTlAuditreportmst.getAurmTempfield1(),
			jhaTlAuditreportmst.getAurmTempfield2(),
			jhaTlAuditreportmst.getAurmTempfield3(),
			jhaTlAuditreportmst.getAurmActive(),
			jhaTlAuditreportmst.getAurmCreatedby(),
			jhaTlAuditreportmst.getAurmKeyid()
		};
		
		file = new FileInputStream(new File(excelFileName));     
		CommonMessage.debugMsg(file +  "  file");
		workbook =  excelFileName.endsWith(REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook(file):new HSSFWorkbook(file);
		int sheetNum=workbook.getNumberOfSheets()-1;
		int i=0;
		try{
			int fromRow = 1;
			int batch = 1000; 
			int batchCount = 1; 
			int totalrow=fromRow + batch;
			while(true){
				if(i<sheetNum){
					sheet=workbook.getSheetAt(i);
					List<Object[]> dataList = new ArrayList<Object[]>();
					CommonMessage.debugMsg(batchCount+"  totalrow  : "+totalrow);
					try{
					if(! convertExcelRowstoList(workbook,i,fromRow+1,batchCount,totalrow,6,dataList) ){
						CommonMessage.debugMsg("No data");
						return "No";
					}
					}catch(Exception e ){
						throw new Exception(e.getMessage());
					}
					List<String> sqls = new ArrayList<String>();
					List<int[]> dataType=new ArrayList<int[]>();
					List<Object[]> mstdataList = new ArrayList<Object[]>();
					CommonMessage.debugMsg(batchCount+"  = batchCount i="+i);
					if(batchCount>1 || i!=0 ){
						sqls.add(JhaTlAuditreportmstSql.getUpdateSql(jhaTlAuditreportmst));
						mstdataList.add(0, masterDataUpdate);
						dataType.add(updateDataTypes );
					}else{
						if(UIUtils.isValidKeyId(Keyid)){
							sqls.add(JhaTlAuditreportmstSql.getUpdateSql(jhaTlAuditreportmst));
							mstdataList.add(0, masterDataUpdate);
							dataType.add(updateDataTypes );
						}else{
							sqls.add(JhaTlAuditreportmstSql.getInsertSql(jhaTlAuditreportmst));
							mstdataList.add(0, masterDataInsert);
							dataType.add(insertDataTypes );
						}
					}
					sqls.add(sql.toString());
					auditDataList.put(0, mstdataList);
					auditDataList.put(1, dataList);
					dataType.add(colDataTypes);
					try{
						sheet=workbook.getSheetAt(i);
						CommonMessage.debugMsg(i+" totalrow "+totalrow +" totalrow "+sheetNum+"  sheet.getLastRowNum() "+sheet.getLastRowNum());
						dbActionTemplate.executeBatch(sqls, auditDataList, dataType);
					}catch(Exception e ){
						CommonMessage.debugMsg("dao Impl Exception "+e.getMessage());
						throw new Exception(e.getMessage());
					}
					if(sheet.getLastRowNum()<=totalrow)
					{
						i=i+1;	
						batchCount=1;
						totalrow=fromRow + batch;
					}else{
						batchCount++;
						totalrow=totalrow + batch;
					}
				}else
				{
					CommonMessage.debugMsg(" if else end sheetNum ");
					return excelFileName;
				}
			}
		}catch(Exception e){
			CommonMessage.debugMsg("dao Impl UploadException "+e.getMessage());
			throw new UploadException(e.getMessage());
		}
	}
	private boolean convertExcelRowstoList(Workbook workbook,int sheetNum ,int fromRow,int batchCount, int rowStart,
			int noCols, List<Object[]> dataList) {
		Sheet sheet = null;
		
		//int sheetNum=workbook.getNumberOfSheets()-1;
		try {
			sheet=workbook.getSheetAt(sheetNum);
			int toRow=rowStart;
			int lastRowNum=sheet.getLastRowNum();
			CommonMessage.debugMsg(" sheet.getSheetName() "+sheet.getSheetName());
			if(batchCount>1)
				fromRow=fromRow+(batchCount-1)*1000;
			CommonMessage.debugMsg(batchCount+"  batchCount "+noCols+"  noCols  "+fromRow+"  fromRow  "+ toRow+"  sheet.getLastRowNum() "+sheet.getLastRowNum()+" lastRowNum " + lastRowNum);
		    if( fromRow > sheet.getLastRowNum()  )
		    	return false;
			GenSequenceNumber key=new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), "JHA_TL_AUDITREPORTDTL", 15, "ARD", "", "");
		    toRow = sheet.getLastRowNum() > toRow ?toRow:  sheet.getLastRowNum();
		    int orderNo=fromRow-1;
            for(int rowNo = (fromRow);rowNo<=toRow;rowNo++){      
            	//CommonMessage.debugMsg(" aftr for loop" + rowNo);
            	String Keyid =key.getSequnceNumber();//dbActionTemplate.getSequenceNumber(JhaTlAuditreportdtlSql.TBL_JHA_TL_AUDITREPORTDTL,15,"ARD","",""); // set the sequnce number
             	String[] row = new String[ noCols + 4];
                int colNo = 0 ;
                row[colNo++] = Keyid;//Add primary keyid
                row[colNo++] = String.valueOf(orderNo);//Add ordernumber
                row[colNo++] = String.valueOf(sheetNum+1);
                row[colNo++] = String.valueOf(sheet.getSheetName());
                for(; colNo< (noCols+4);colNo++){ 
                	Cell cell = null;	               
		        	cell = sheet.getRow(rowNo).getCell(colNo-4);
                    switch(cell.getCellType()) {		                    
                        case BOOLEAN:
                            if (cell.getBooleanCellValue()) 
                            	row[ colNo ]="1";                            
                            else
                            	row[ colNo ]="0";
                            
                            row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case FORMULA:
                            row[ colNo ] = Double.toString(cell.getNumericCellValue());
                            row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case NUMERIC:
                        	if( DateUtil.isCellDateFormatted(cell)){
                        		Date date = cell.getDateCellValue();
                        		if( date != null ){
	                        		String format = cell.getCellStyle().getDataFormatString();
	                        		format = format.replaceAll("\\\\-", "-").replace("mmm", "MMM");
	                        		SimpleDateFormat dFormat = new SimpleDateFormat(format,Locale.ENGLISH);
	                        		String datVal=dFormat.format(date).replace("[$-409]", "");
	                        		row[ colNo ] = datVal.replace("@", "");
                        		}	
                        	}
                        	else{
                        		cell.setCellType(CellType.STRING);
                        		//row[ colNo ] = Double.toString(cell.getNumericCellValue());
	                            row[ colNo ] = cell.getStringCellValue();
                        	}
                        	row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case STRING:
                        	String cellVal=cell.getStringCellValue();
                        	 for (int i = 0; i < cellVal.length(); i++) {
                        	        if (cellVal.charAt(i) > 128 || (cellVal.charAt(i) >= 91 && cellVal.charAt(i) <= 96) ||(cellVal.charAt(i) >= 34 && cellVal.charAt(i) <= 39)) {
                        	        	CommonMessage.debugMsg(cellVal.length()+"=cellVal.length() inside if  i="+i+"  cellVal.charAt(i) "+cellVal.charAt(i));
                        	        	cellVal = cellVal.substring(0,  i) 
                        	                    + cellVal.substring(i + 1);
                        	            i++;
                        	        }
                        	    }
                        	 CommonMessage.debugMsg("cellVal   : "+cellVal);
                            row[ colNo ] =cellVal; 
    						row[ colNo ] = (row[ colNo ] != null ? row[ colNo ].replace("'", "''").replace("{}", "").replace("<**>","").replace("<*", "").replace("*>", ""):"");
    						row[ colNo ] = (row[ colNo ] == null ? "{}":row[ colNo ]);
                            break;
                        case ERROR:
                        	row[ colNo ] ="";
                        	break;
                        case BLANK:
                        	row[ colNo ] ="{}";
                        	break;
                	}
                }	                
                dataList.add(row);
                orderNo++;
            }
            key.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 return true;
	}

	@Override
	public List<String[]> getGriddata(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();	
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		condParms+="MSTKEYID=";
		if(UIUtils.isValidKeyId(commonFilter.getAuditRpt()))
			condParms+=commonFilter.getAuditRpt();
		condParms+=";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_AUDITELEMENTREPORT", paramValues);
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
	@Override
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();	
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_AUDITELEMENTREPORTGRID", paramValues);
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
	@Override
	public JhaTlAuditreportmst selectData(String keyid) throws Exception {
		JhaTlAuditreportmst jhaTlAuditreportmst =new JhaTlAuditreportmst (); 
		String sql = JhaTlAuditreportmstSql.select();
		CommonMessage.debugMsg( keyid+"  keyid SQL : "+sql);
		Object args [] = new Object [] { keyid };
		jhaTlAuditreportmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return jhaTlAuditreportmst ;
	}
	@Override
	public List<JhaTlAuditreportdtl> delete(List<JhaTlAuditreportdtl> listJhaTlAuditreportdtl) throws Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlAuditreportdtlSql jhaTlAuditreportdtlSql=new JhaTlAuditreportdtlSql();
		try {
			for(JhaTlAuditreportdtl jhaTlAuditreportdtl:listJhaTlAuditreportdtl){
				sqls.add(JhaTlAuditreportdtlSql.getDeleteSql(jhaTlAuditreportdtlSql.getAurdDbFields(), jhaTlAuditreportdtl.getSaveArray()));
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return listJhaTlAuditreportdtl;
	}

	@Override
	public Workbook getExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{				
			rs =   getResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );				
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	private ResultSet getResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("JHN_FN_AUDITELEMENTREPORTGRID", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}
}

