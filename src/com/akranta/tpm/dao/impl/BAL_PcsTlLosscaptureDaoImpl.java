package com.akranta.tpm.dao.impl;




import java.io.File;
import java.io.FileInputStream;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsTlLosscaptureDao;
import com.akranta.tpm.dao.PcsTlLosscaptureDao;
import com.akranta.tpm.dao.sql.GenTlImrcontrolchartSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlLosscaptureSql;
import com.akranta.tpm.dao.sql.PcsTlOtherlossentrySql;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_PcsTlLosscaptureDaoImpl implements BAL_PcsTlLosscaptureDao {


	private DBActionTemplate dbActionTemplate; 
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";
	
	public BAL_PcsTlLosscaptureDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlLosscapture create(PcsTlLosscapture pcsTlLosscapture) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_PcsTlLosscaptureSql pcsTlLosscaptureSql = new BAL_PcsTlLosscaptureSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			pcsTlLosscapture.setPlosKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlLosscaptureSql.BAL_TBL_PCS_TL_LOSSCAPTURE, 15, "PLOS", "MMYY", "Y")); // set the sequnce number 
			sqls.add(BAL_PcsTlLosscaptureSql.getInsertSql(pcsTlLosscaptureSql.getPlosDbFields(), pcsTlLosscapture.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlLosscapture;
	}
	
	public PcsTlLosscapture update(PcsTlLosscapture pcsTlLosscapture)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlLosscaptureSql pcsTlLosscaptureSql = new BAL_PcsTlLosscaptureSql();
		try {

			sqls.add(BAL_PcsTlLosscaptureSql.getUpdateSql(pcsTlLosscaptureSql.getPlosDbFields(), pcsTlLosscapture.getSaveArray()));
			
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
		BAL_PcsTlLosscaptureSql pcsTlLosscaptureSql = new BAL_PcsTlLosscaptureSql();
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
		CommonFunctions.debugMsg("dao impl"+ excelFileName);
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
		sql.append( " to_date(?,'dd-Mon-yyyy hh24:mi:ss'),?,'"+pcsTlOtherlossentry.getOlseTempfield1()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield2()+"','"+pcsTlOtherlossentry.getOlseTempfield3()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield4()+"','"+pcsTlOtherlossentry.getOlseTempfield5()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield6()+"','"+pcsTlOtherlossentry.getOlseTempfield7()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseTempfield8()+"','"+pcsTlOtherlossentry.getOlseActive()+"',");
		sql.append( "'"+pcsTlOtherlossentry.getOlseCreatedby()+"', to_date( '"+pcsTlOtherlossentry.getOlseCreatedon()+"','dd-Mon-yyyy hh24:mi:ss'),");
		sql.append( "to_date('"+pcsTlOtherlossentry.getOlseModifiedon()+"','dd-Mon-yyyy hh24:mi:ss') ) ");
		try{
			file = new FileInputStream(new File(excelFileName));     
			CommonFunctions.debugMsg(file +  "  file");
			workbook =  excelFileName.endsWith(REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook(file):new HSSFWorkbook(file);
			//String Sheet1 = "Sheet1";
			sheet = workbook.getSheetAt(0);
			CommonFunctions.debugMsg(sheet.getLastRowNum() +  "  file");
			CommonFunctions.debugMsg(workbook +  " workbook");
			int fromRow = 0;
			int batch = 1000; 
			int batchCount = 1; 
			while(true){ 
					
				List<Object[]> dataList = new ArrayList<Object[]>();
				CommonFunctions.debugMsg(" fromRow " + fromRow + " to row " + (fromRow + batch)+" sheet" + sheet.getLastRowNum());
				if( ! convertExcelRowstoList(sheet,fromRow+1,(fromRow + batch),2,dataList) ){
					CommonFunctions.debugMsg("No data");
					return "No";
				}else
				{
					CommonFunctions.debugMsg("dataList.size()  "+dataList.size());
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
			CommonFunctions.debugMsg(noCols+"  noCols  "+fromRow+"  fromRow  "+ toRow+" row num " + sheet.getLastRowNum());
		    if( fromRow > sheet.getLastRowNum()  )
		    	return false;
			
		    toRow =  sheet.getLastRowNum() > toRow ?toRow:  sheet.getLastRowNum();
		    CommonFunctions.debugMsg(toRow + " torow");
		    GenSequenceNumber key=new GenSequenceNumber(dbActionTemplate.getNewConnection(), "PCS_TL_OTHERLOSSENTRY", 10, "OLS", "", "");
            for(int rowNo = (fromRow);rowNo<=toRow;rowNo++){      
            	CommonFunctions.debugMsg(" aftr for loop" + rowNo);
            	String Keyid =key.getSequnceNumber();//dbActionTemplate.getSequenceNumber(PcsTlOtherlossentrySql.TBL_PCS_TL_OTHERLOSSENTRY,10,"OLS","",""); // set the sequnce number
                CommonFunctions.debugMsg(Keyid + " keyid");
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
	                        	CommonFunctions.debugMsg("dFormat.format(date) " + dFormat.format(date));
	                        		String datVal=dFormat.format(date).replace("[$-409]", "");
	                        		row[ colNo ] = datVal.replace("@", "");
                        		}	
                        		CommonFunctions.debugMsg("row[ colNo ] " + row[ colNo ]);
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
                    CommonFunctions.debugMsg(cell.getCellType()+"row[ " + colNo + " ]: " + row[colNo ]);
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

