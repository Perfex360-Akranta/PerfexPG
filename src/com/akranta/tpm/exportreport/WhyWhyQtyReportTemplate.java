package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.service.WhywhyReportService;
import com.akranta.tpm.service.impl.WhywhyReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class WhyWhyQtyReportTemplate {
	
	WhywhyReportService whywhyService;
    public WhyWhyQtyReportTemplate(DBActionTemplate dbActionTemplate){
		whywhyService = new WhywhyReportServiceImpl(dbActionTemplate);
    }
  
	
	private static final short ROW_INDEX_CIRCLE = 2;
	private static final short ROW_INDEX_COST = 29;
	private static int ROW_INDEX_DEPTIMPNO = 4;
	private static final short ROW_INDEX_EQP = 5;
	private static final short ROW_INDEX_THEMEIDEA = 6;
	private static final short ROW_INDEX_TGT = 9;
	private static final short ROW_INDEX_PRGSRT = 11;
	private static final short ROW_INDEX_YY = 21;
	private static final short ROW_INDEX_RSTIMG = 31;
	private static final short ROW_INDEX_IMG = 16;
	private static final short INCERMENTBY_YY = 2;
	private static final short WHYWHY_START_POINT = 21;
	private static final short GRAPH_START_COL = 31;
	private static final short GRAPH_INCREMENT = 1;
	private static final String PASSDATETIME = "01-Jan-1801 00:00:00";
												
	
	
	public Workbook fillValues(Map<Integer,List<String[]>> whyData,String format,String path,String kaizId,String imagePath) throws Exception 
	{
	
		String excelFormat = null;
		String excelPath = null;
		List<String[]> graphType = whyData.get(0) ;	
		CommonMessage.debugMsg("graphType.size()graphType.size() "+graphType +"excelFormatgraphType.size().. "+graphType.size());
		excelPath = "/why-whyAnalysis.xlsx";
	 
		   CommonMessage.debugMsg("excelPath.."+excelPath);
			InputStream inp = new FileInputStream(path+excelPath); 
			CommonMessage.debugMsg("Excel Template to be generate...."+whyData.size()+"...");		
		 			    
		   
			Workbook wb = new XSSFWorkbook(inp);
			
		    inp.close();
		    Sheet sheet = wb.getSheetAt(0);  
		
		    
		    Font header1Font = wb.createFont();
	         header1Font.setFontHeightInPoints((short)12);
	         header1Font.setBold(true);
	         header1Font.setColor(IndexedColors.BLACK.getIndex());
	         header1Font.setFontName(XLConditionalFormats.FONT_WEBDINGS);
	         
		    CellStyle style = wb.createCellStyle();
	         style.setAlignment(HorizontalAlignment.CENTER);
	         style.setVerticalAlignment(VerticalAlignment.CENTER);
	        
	         //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	         style.setBorderRight(BorderStyle.THIN);
	         style.setBorderBottom(BorderStyle.THIN);
	         style.setFont(header1Font);
	         
	    	 String [] whyexl = graphType.get(0);
	    
			
	  
		    CommonMessage.debugMsg("Excel Template to be generate123...."+whyData.size()+"...");	
		    
		    List<String[]> curExcel = whyData.get(0);	
		    CommonMessage.debugMsg("curExcel.size() "+curExcel.size());
		    if(curExcel!=null && curExcel.size() > 1 )
			{
				CommonMessage.debugMsg("Inside the fill values function3 ................");
				 String [] impshtexl = curExcel.get(1);
				for(int i=0;i<impshtexl.length;i++)
				{
					CommonMessage.debugMsg(i+ "---"+ impshtexl[i]);
					
				}
		    
			
		
	 			sheet.getRow(5).getCell(8).setCellValue(impshtexl[4]);//equipment no
			    sheet.getRow(5).getCell(2).setCellValue(impshtexl[3]);//equ.name
			 
			    sheet.getRow(2).getCell(10).setCellValue(impshtexl[10]);//areacode
			    sheet.getRow(3).getCell(10).setCellValue(impshtexl[9]);//areaname
			    sheet.getRow(6).getCell(4).setCellValue(impshtexl[10]);//phenomena--
			    sheet.getRow(7).getCell(4).setCellValue(impshtexl[11]);//finalaction
			    sheet.getRow(4).getCell(7).setCellValue(impshtexl[2]);//bddatetime
			    sheet.getRow(4).getCell(9).setCellValue(impshtexl[2]);//whywhydatetime
			    sheet.getRow(6).getCell(10).setCellValue(impshtexl[12]);//spares replaced*/
			    sheet.getRow(4).getCell(2).setCellValue(impshtexl[8]);//spares replaced*/
			    sheet.getRow(15).getCell(8).setCellValue(impshtexl[14]);//Counter Measure*/
			    sheet.getRow(9).getCell(9).setCellValue(impshtexl[26]);//Form Type*/
			    sheet.getRow(11).getCell(9).setCellValue(impshtexl[30]);//Efectiveness*/			    
			   // sheet.getRow(14).getCell(3).setCellValue(impshtexl[33]);//RootCause*/
			    sheet.getRow(12).getCell(9).setCellValue(impshtexl[29]);//Previous date*/
			 
				
			   	if(impshtexl[23].equals("POOR BASIC CONDITION"))
			    {
			   		
			   		sheet.getRow(16).getCell(4).setCellStyle(style);
			   		sheet.getRow(16).getCell(4).setCellValue("a");//tick
			    }
			   	else if(impshtexl[23].equals("POOR OPERATING CONDITION")){
  			    
		   		 sheet.getRow(17).getCell(4).setCellValue("a");//tick
			   	}
			   	else if(impshtexl[23].equals("DETERIORATION")){
  				
			   		sheet.getRow(18).getCell(4).setCellStyle(style);
			   		sheet.getRow(18).getCell(4).setCellValue("a");//tick
			   	}
			   	else if(impshtexl[23].equals("WEAK DESIGN")){
	  			   
			   		sheet.getRow(19).getCell(4).setCellStyle(style);
			   		sheet.getRow(19).getCell(4).setCellValue("a");//tick
			   	}
			   	else if(impshtexl[23].equals("POOR SKILL")){
	  			
			   		sheet.getRow(20).getCell(4).setCellStyle(style);
			   		sheet.getRow(20).getCell(4).setCellValue("a");//tick
			   	}
			   
			  
			   	//why why loop 
			   	
			    int rowadd=9;
	  			   int whywhyStartRow = 9; 
	  			 
	  			 for(int j=1 ;j< graphType.size()-1;j++)
	  				
	  			   {
	  				impshtexl = graphType.get(j);
	  				 CommonMessage.debugMsg("j : "+j);
	  				 CommonMessage.debugMsg("whywhyStartRow : "+whywhyStartRow);
	  				  if(j>=5)
	  					  sheet.createRow( whywhyStartRow+j);
	  				 
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(2).setCellValue(impshtexl[20]);	
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(5).setCellValue(impshtexl[21]);
	  			   }
	  			    	
	  			  		   
				}
		    
		    List<String[]> curExcel1 = whyData.get(1);	
		    if(curExcel1!=null && curExcel1.size() > 1 )
		    {
		    	
		    	String [] impshtexl0 = curExcel1.get(0);
		    	
		    	String [] impshtexl = curExcel1.get(1);
				
		    
				  if(impshtexl[0].contains("KZN")){
	  				sheet.getRow(16).getCell(6).setCellValue(impshtexl[1]);
				   	}
				  else if(impshtexl[0].contains("OPL")){
		  				sheet.getRow(17).getCell(6).setCellValue(impshtexl[1]);
					   	}
				  else if(impshtexl[0].contains("PMC")){
					  sheet.getRow(18).getCell(6).setCellValue(impshtexl[1]);
				  }
				  else if(impshtexl[0].contains("JHN")){
					  sheet.getRow(19).getCell(6).setCellValue(impshtexl[1]);
				  }
				 
				 // else if(impshtexl[0].contains("ISHDPOSSIBLE")){
					 // sheet.getRow(20).getCell(6).setCellValue(impshtexl[1]);
				 // }
		    }
		    
		    return wb;	  
		 
	}

}
