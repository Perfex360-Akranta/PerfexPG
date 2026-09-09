package com.akranta.tpm.dao.impl;




import java.io.File;
import java.io.FileInputStream;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlLosscaptureDao;
import com.akranta.tpm.dao.sql.GenTlImrcontrolchartSql;
import com.akranta.tpm.dao.sql.PcsTlLosscaptureSql;
import com.akranta.tpm.dao.sql.PcsTlOtherlossentrySql;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class PcsTlLosscaptureDaoImpl implements PcsTlLosscaptureDao {


	private DBActionTemplate dbActionTemplate; 
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";
	
	public PcsTlLosscaptureDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlLosscapture create(PcsTlLosscapture pcsTlLosscapture) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PcsTlLosscaptureSql pcsTlLosscaptureSql = new PcsTlLosscaptureSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			pcsTlLosscapture.setPlosKeyid(dbActionTemplate.getSequenceNumber(PcsTlLosscaptureSql.TBL_PCS_TL_LOSSCAPTURE, 15, "PLOS", "MMYY", "Y")); // set the sequnce number 
			sqls.add(PcsTlLosscaptureSql.getInsertSql(pcsTlLosscaptureSql.getPlosDbFields(), pcsTlLosscapture.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscapture;
	}
	
	public PcsTlLosscapture update(PcsTlLosscapture pcsTlLosscapture)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlLosscaptureSql pcsTlLosscaptureSql = new PcsTlLosscaptureSql();
		try {

			sqls.add(PcsTlLosscaptureSql.getUpdateSql(pcsTlLosscaptureSql.getPlosDbFields(), pcsTlLosscapture.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlLosscapture;
	}
	
	public PcsTlLosscapture delete(PcsTlLosscapture pcsTlLosscapture)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlLosscaptureSql pcsTlLosscaptureSql = new PcsTlLosscaptureSql();
		try {
			
			sqls.add(pcsTlLosscaptureSql.getDeleteSql(pcsTlLosscaptureSql.getPlosDbFields(), pcsTlLosscapture.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscapture;
	}

	@Override
	public String populateTempTable(String excelFileName,
			PcsTlOtherlossentry pcsTlOtherlossentry) throws UploadException {
		CommonMessage.debugMsg("dao impl"+ excelFileName);
		int [] colDataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR ,Types.VARCHAR};
		FileInputStream file = null;
		Sheet sheet = null;
		
		Workbook workbook = null;
		StringBuilder sql =new StringBuilder();
		sql.append( " INSERT INTO PCS_TL_OTHERLOSSENTRY(OLSE_KEYID,OLSE_FLID,OLSE_ELEMENTID,OLSE_DATE, OLSE_LOSSID,OLSE_LOSSDATE,");
		sql.append( "OLSE_LOSSVALUE,OLSE_TEMPFIELD1,OLSE_TEMPFIELD2,OLSE_TEMPFIELD3,OLSE_TEMPFIELD4,OLSE_TEMPFIELD5,OLSE_TEMPFIELD6, ");
		sql.append( "OLSE_TEMPFIELD7,OLSE_TEMPFIELD8,OLSE_ACTIVE,OLSE_CREATEDBY,OLSE_CREATEDON,OLSE_MODIFIEDON) VALUES( ");
		sql.append( " ?,'"+pcsTlOtherlossentry.getOlseFlid()+"', '"+pcsTlOtherlossentry.getOlseElementid()+"',");
		
		sql.append( " to_date( '"+pcsTlOtherlossentry.getOlseDate()+"','dd-Mon-yyyy hh24:mi:ss'),'"+pcsTlOtherlossentry.getOlseLossid()+"',");
		
	//	sql.append( " to_date(?,'dd-Mon-yyyy hh24:mi:ss'),?,'"+pcsTlOtherlossentry.getOlseTempfield1()+"',");
		sql.append(" to_date(?,'dd-Mon-yyyy hh24:mi:ss'), ?::numeric ,'"+pcsTlOtherlossentry.getOlseTempfield1()+"',");
		
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield2()+"','"+pcsTlOtherlossentry.getOlseTempfield3()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield4()+"','"+pcsTlOtherlossentry.getOlseTempfield5()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield6()+"','"+pcsTlOtherlossentry.getOlseTempfield7()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield8()+"','"+pcsTlOtherlossentry.getOlseActive()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseCreatedby()+"', to_date( '"+pcsTlOtherlossentry.getOlseCreatedon()+"','dd-Mon-yyyy hh24:mi:ss'),");
		sql.append( "to_date('"+pcsTlOtherlossentry.getOlseModifiedon()+"','dd-Mon-yyyy hh24:mi:ss') ) ");
		try{
			file = new FileInputStream(new File(excelFileName));     
			CommonMessage.debugMsg(file +  "  file");
			workbook =  excelFileName.endsWith(REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook(file):new HSSFWorkbook(file);
			//String Sheet1 = "Sheet1";
			sheet = workbook.getSheetAt(0);
			CommonMessage.debugMsg(sheet.getLastRowNum() +  "  file");
			CommonMessage.debugMsg(workbook +  " workbook");
			int fromRow = 0;
			int batch = 1000; 
			int batchCount = 1; 
			while(true){ 
					
				List<Object[]> dataList = new ArrayList<Object[]>();
				CommonMessage.debugMsg(" fromRow " + fromRow + " to row " + (fromRow + batch)+" sheet" + sheet.getLastRowNum());
				if( ! convertExcelRowstoList(sheet,fromRow+1,(fromRow + batch),2,dataList) ){
					CommonMessage.debugMsg("No data");
					return "No";
				}else
				{
					CommonMessage.debugMsg("dataList.size()  "+dataList.size());
					if(dataList.size()>0){
						dbActionTemplate.executeBatch(sql.toString(), dataList, colDataTypes);
						fromRow = (fromRow + batch);
						dataList = null;
						batchCount++;
						return excelFileName;
					}else
						return "No";
				}
			}
		}catch(Exception e){
			throw new UploadException(e.getMessage());
		}
	}
	private boolean convertExcelRowstoList(Sheet sheet, int fromRow, int toRow,
			int noCols, List<Object[]> dataList) {
		try {
			CommonMessage.debugMsg(noCols+"  noCols  "+fromRow+"  fromRow  "+ toRow+" row num " + sheet.getLastRowNum());
		    if( fromRow > sheet.getLastRowNum()  )
		    	return false;
			
		    toRow =  sheet.getLastRowNum() > toRow ?toRow:  sheet.getLastRowNum();
		    CommonMessage.debugMsg(toRow + " torow");
		    GenSequenceNumber key=new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), "PCS_TL_OTHERLOSSENTRY", 10, "OLS", "", "");
            for(int rowNo = (fromRow);rowNo<=toRow;rowNo++){      
            	CommonMessage.debugMsg(" aftr for loop" + rowNo);
            	String Keyid =key.getSequnceNumber();//dbActionTemplate.getSequenceNumber(PcsTlOtherlossentrySql.TBL_PCS_TL_OTHERLOSSENTRY,10,"OLS","",""); // set the sequnce number
                CommonMessage.debugMsg(Keyid + " keyid");
             	String[] row = new String[ noCols + 1];
                int colNo = 0 ;
                row[colNo++] = Keyid;//Add primary keyid
                
                for(; colNo< (noCols+1);colNo++){ 
                	Cell cell = null;	               
		        	cell = sheet.getRow(rowNo).getCell(colNo-1);
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
	                        		SimpleDateFormat dFormat = new SimpleDateFormat(format);
	                        	CommonMessage.debugMsg("dFormat.format(date) " + dFormat.format(date));
	                        		String datVal=dFormat.format(date).replace("[$-409]", "");
	                        		row[ colNo ] = datVal.replace("@", "");
                        		}	
                        		CommonMessage.debugMsg("row[ colNo ] " + row[ colNo ]);
                        		//row[ colNo ] =  +"";
                        	}
                        	else{
                        		cell.setCellType(CellType.STRING);
                        		//row[ colNo ] = Double.toString(cell.getNumericCellValue());
	                            row[ colNo ] = cell.getStringCellValue();
                        	}
                        	row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case STRING:
                            row[ colNo ] = cell.getStringCellValue();
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
                    CommonMessage.debugMsg(cell.getCellType()+"row[ " + colNo + " ]: " + row[colNo ]);
                }	
               
                dataList.add(row);
            }
            key.closeConnection();
            
          //  file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 return true;
	}

	@Override
	public String updateLossVal(List<PcsTlOtherlossentry> otherLossList) throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); 
		for(PcsTlOtherlossentry pcsTlOtherlossentry:otherLossList){
			sqls.add(PcsTlOtherlossentrySql.updateLossVal(pcsTlOtherlossentry));
		}
		dbActionTemplate.executeStatements(sqls);
		return "Updated";
	}
	
}

