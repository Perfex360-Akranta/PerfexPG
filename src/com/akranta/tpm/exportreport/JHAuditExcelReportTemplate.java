package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.Replace;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;


import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.service.JhAuditRptService;
import com.akranta.tpm.service.impl.JhAuditRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.taglibs.standard.lang.jpath.adapter.Convert;

public class JHAuditExcelReportTemplate {
	
	JhAuditRptService jhAuditRptService;
    public JHAuditExcelReportTemplate(DBActionTemplate dbActionTemplate){
   	   jhAuditRptService = new JhAuditRptServiceImpl(dbActionTemplate);
		  
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
	//private static final short COLOR = 39, 51, 89;
	
	private static final String PASSDATETIME = "01-Jan-1801 00:00:00";
												
	private String getCharForExcelCol(int colIndex)
	    {
	    	// char c = 'A';
	    	 
	    	 String colChar ="";
	    	 do{
	    		 if( (colIndex / 26) > 0){
	    			 colChar +=  (char)(( 64  + (int) (colIndex / 26))) ;
	    			 colIndex  = (colIndex % 26);
	    		 }	 
	    		 else{
	    			 
	    			 if( colIndex > 0)
	    				 colChar +=  (char)(( 64  +   (int)colIndex % 26 )) ;
	    			 else
	    				 colChar += "A";
	    			 
	    			 colIndex = -1;
	    		 }	 
	    	 }while( colIndex >= 0 );

	    	 return colChar;
	    }
	
	public Workbook fillValues(Map<Integer,List<String[]>> whyData,String format,String path,String kaizId,String imagePath) throws Exception 
	{
		int j;
		String excelFormat = null;
		String excelPath = null;
		Row row = null;
		Cell cell = null;
		List<String[]> graphType = whyData.get(0) ;	
		CommonMessage.debugMsg("graphType.size()graphType.size() "+graphType +"excelFormatgraphType.size().. "+graphType.size());
		excelPath = "/JH Audit Sheet.xlsx";
	 
		   CommonMessage.debugMsg("excelPath.."+excelPath);
			InputStream inp = new FileInputStream(path+excelPath); 
			CommonMessage.debugMsg("Excel Template to be generate...."+whyData.size()+"...");		
		 			    
		   
			Workbook wb = new XSSFWorkbook(inp);
			
		    inp.close();
		    Sheet sheet = wb.getSheetAt(0);  
		
		    
		    Font header1Font = wb.createFont();
	         header1Font.setFontHeightInPoints((short)10);
	         //header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
	         header1Font.setColor(IndexedColors.BLACK.getIndex());
	         header1Font.setFontName(XLConditionalFormats.FONT_DEFAULT);
	         
	         
		    CellStyle style = wb.createCellStyle();
	         style.setAlignment(HorizontalAlignment.CENTER);
	         style.setVerticalAlignment(VerticalAlignment.CENTER);      
	         style.setFont(header1Font);
	         style.setWrapText(true);
	         
	         Font header2Font = wb.createFont();
	         header2Font.setFontHeightInPoints((short)10);
	         header2Font.setBold(true);
	         header2Font.setColor(IndexedColors.BLACK.getIndex());
	         header2Font.setFontName(XLConditionalFormats.FONT_DEFAULT);
	         
	         
		    CellStyle style1 = wb.createCellStyle();
	         style1.setAlignment( HorizontalAlignment.CENTER);
	         style1.setVerticalAlignment(VerticalAlignment.CENTER);  
	         style1.setBorderTop(BorderStyle.THIN);
	         style1.setBorderRight(BorderStyle.THIN);
	         style1.setBorderBottom(BorderStyle.THIN);
	         style1.setFont(header2Font);
	         style1.setWrapText(true);
	         
	        CellStyle style2 = wb.createCellStyle();
	        // style2.setAlignment(CellStyle.ALIGN_CENTER);
	         //style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);  
	         style2.setBorderTop(BorderStyle.THIN);       
	         
	         style2.setBorderBottom(BorderStyle.THIN);
	         style2.setFont(header2Font);
	         style2.setWrapText(true);
	      
	         
		    CommonMessage.debugMsg("Excel Template to be generate123...."+whyData.size()+"...");	
		    
		    List<String[]> curExcel = whyData.get(0);	
		    CommonMessage.debugMsg("curExcel.size() "+curExcel.size());
		   
		  
  		  
		    if(curExcel!=null && curExcel.size() > 0 )
			{ 
		    	String [] impshtexl = curExcel.get(1);
				CommonMessage.debugMsg("Inside the fill values function3 ................");
					
						for(int i=0;i<impshtexl.length;i++)
						{
							CommonMessage.debugMsg(i+ "---"+ impshtexl[i]);
							
						}
	  					
	 		
			    sheet.getRow(1).getCell(1).setCellValue(impshtexl[5]);//AUDIT LEVEL			    
			    sheet.getRow(2).getCell(1).setCellValue(impshtexl[1]);//MODULE			    
			    sheet.getRow(3).getCell(1).setCellValue(impshtexl[2]);//CELL			    
			    sheet.getRow(4).getCell(1).setCellValue(impshtexl[3]);//MCHM			    
			    sheet.getRow(2).getCell(4).setCellValue(impshtexl[4]);//AUDIT DATE			    
			    sheet.getRow(3).getCell(4).setCellValue(impshtexl[6]);//AUDITOR NAME	
			    sheet.getRow(4).getCell(4).setCellValue(impshtexl[9]);//TOTAL POINTS			    
			    sheet.getRow(5).getCell(4).setCellValue(impshtexl[10]);//RESULT		    
			    
			  
			  
			    int rowadd=19;
	  			   int whywhyStartRow =8; 
	  			  CommonMessage.debugMsg("StartRow : "+whywhyStartRow);
	  			String [] impshtexl1 = curExcel.get(1);
	  			for( j=1 ;j < curExcel.size()-1;j++)
	  				
	  			   {
	  					impshtexl1 = curExcel.get(j);
	  				 CommonMessage.debugMsg("j : "+j);
	  				 CommonMessage.debugMsg("impshtexl1[13] : "+impshtexl1[13]);
	  				 CommonMessage.debugMsg("impshtexl1[14] : "+impshtexl1[14]);
	  				 CommonMessage.debugMsg("impshtexl1[15] : "+impshtexl1[15]);
	  				 CommonMessage.debugMsg("impshtexl1[16] : "+impshtexl1[16]);
	  				 CommonMessage.debugMsg("impshtexl1[17] : "+impshtexl1[17]);
	  				 CommonMessage.debugMsg("impshtexl1[18] : "+impshtexl1[18]);
	  				 CommonMessage.debugMsg("impshtexl1[19] : "+impshtexl1[19]);
	  				 CommonMessage.debugMsg("impshtexl1[20] : "+impshtexl1[20]);
	  				 
	  				 
	  				 
	  				  if(j>10)
	  				  {
	  					 CommonMessage.debugMsg("rowadd : "+rowadd);
	  					row = sheet.createRow( rowadd+1);
	  					CommonMessage.debugMsg("row : "+row);
	  					for(int m=0;m<10;m++)
	  				    {  
	  						cell = row.createCell(m);
	  						row.setHeight((short)800);
		  				   
		  				 
	  						//ExcelUtils.formatSheet(wb, sheet,1,rowadd);
	  						CellRangeAddress region = CellRangeAddress.valueOf("A1:"+ getCharForExcelCol(m)  +rowadd);
	  				        final BorderStyle borderMediumDashed = BorderStyle.THIN;
	  				        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet );
	  				        RegionUtil.setBorderTop( borderMediumDashed,region, sheet );
	  				        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet );
	  				        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
	  				        
	  				        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
	  				        RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
	  				        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
	  				        RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
	  				      row.setRowStyle(style);
	  				      cell.setCellStyle(style);
	  				      
	  				    }
	  					rowadd = rowadd+1;
	  					CommonMessage.debugMsg("AFTER INCREMENT rowadd : "+rowadd);
	  					  
	  				  }
	  				CommonMessage.debugMsg("J: "+j);
	  				CommonMessage.debugMsg("END ROW: "+(whywhyStartRow + (j*1)-1));
	  				CommonMessage.debugMsg("impshtexl1[13]"+impshtexl1[13]);
	  					
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(0).setCellValue(j);	
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(1).setCellValue(impshtexl1[13]);//PART OF THE MACHINE
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(2).setCellValue(impshtexl1[14]);//PARAMETERS
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellValue(impshtexl1[15]);//MAX POINTS
		  				
		  				if(!impshtexl1[16].isEmpty())
		  				{
		  					CommonMessage.debugMsg("inside if impshtexl1[16]...."+impshtexl1[16]);
		  				//sheet.getRow(whywhyStartRow + (j*1)-1).getCell(4).setCellStyle(style1);
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(4).setCellValue(impshtexl1[16]);//EXCELLENT
		  				}
		  				if(!impshtexl1[17].isEmpty()) 
		  				{
		  				//sheet.getRow(whywhyStartRow + (j*1)-1).getCell(5).setCellStyle(style1);	
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(5).setCellValue(impshtexl1[17]);// VERY GOOD
		  				}
		  				if(!impshtexl1[18].isEmpty() )
		  				{
		  				//sheet.getRow(whywhyStartRow + (j*1)-1).getCell(6).setCellStyle(style1);
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(6).setCellValue(impshtexl1[18]);//GOOD
		  				}
		  				if(!impshtexl1[19].isEmpty())
		  				{
		  				//sheet.getRow(whywhyStartRow + (j*1)-1).getCell(7).setCellStyle(style1);
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(7).setCellValue(impshtexl1[19]);//AVERAGE
		  				}
		  				if(!impshtexl1[20].isEmpty())
		  				{
		  				//sheet.getRow(whywhyStartRow + (j*1)-1).getCell(8).setCellStyle(style1);
		  				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(8).setCellValue(impshtexl1[20]);//POOR
		  				}
		  			  
		  				
		  				
	  			   }
	  			
	  		  List<String[]> curExcel1 = whyData.get(1);	
	  		  
	  		  CommonMessage.debugMsg("curExcel1.size() "+curExcel1.size()); 
	  		  
	  		    String [] impshtexl2 = curExcel1.get(1);
	  		    
	  			 if(curExcel1!=null && curExcel1.size() > 1 )
		  		    {
		  		    	impshtexl2 = curExcel1.get(1);
		  		    	CommonMessage.debugMsg("impstex2"+impshtexl2[0]);
		  		    	CommonMessage.debugMsg("impshtexl2.length"+impshtexl2.length);
		  		    	for(int i=0;i<impshtexl2.length;i++)
						{
							CommonMessage.debugMsg(i+ "---"+ impshtexl2[i].isEmpty());
							if(impshtexl2[i].isEmpty())
								impshtexl2[i] = "0";
							
						}
		  		    				
		  		  	for(int a=1 ;a < 5;a++)
		  				
		  			   {
		  		  	CommonMessage.debugMsg("rowadd....... : "+rowadd);
  					row = sheet.createRow( rowadd+1);
  					CommonMessage.debugMsg("row........ : "+row);
  					for(int m=0;m<10;m++)
  				    {  
  						cell = row.createCell(m);
  						row.setHeight((short)800);
	  				   
	  				 
  						//ExcelUtils.formatSheet(wb, sheet,1,rowadd);
  						CellRangeAddress region = CellRangeAddress.valueOf("A1:"+ getCharForExcelCol(m)  +rowadd);
  				        final BorderStyle borderMediumDashed = BorderStyle.THIN;
  				        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet );
  				        RegionUtil.setBorderTop( borderMediumDashed,region, sheet );
  				        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet );
  				        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
  				        
  				        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
  				        RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
  				        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
  				        RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), region, sheet );
  				     // row.setRowStyle(style1);
  				     // cell.setCellStyle(style1);
  				      
  				    }
  					
  					CommonMessage.debugMsg("AFTER INCREMENT rowadd....... : "+rowadd);
  					
  					CommonMessage.debugMsg("END ROW.........: "+(whywhyStartRow + (j*1)-1));
  	  			
  					
  					if(a == 1)
  					{
  						CommonMessage.debugMsg("A VALUE IN A1.........: "+a);
  					sheet.getRow(whywhyStartRow + (j*1)-1).getCell(2).setCellStyle(style1);
				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(2).setCellValue("Total Score");	
				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellStyle(style1);
				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellValue(impshtexl2[0]);
  					}
  					if(a==2)
  					{
  						CommonMessage.debugMsg("A VALUE IN A2.........: "+a);
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(2).setCellStyle(style1);
				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(2).setCellValue("Alloted Score");	
				int tot =  Integer.parseInt(impshtexl2[1])+
							Integer.parseInt(impshtexl2[2])+
							Integer.parseInt(impshtexl2[3])+
							Integer.parseInt(impshtexl2[4])+
							Integer.parseInt(impshtexl2[5]);
				CommonMessage.debugMsg("tot.........: "+tot);
				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellStyle(style1);
				sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellValue(Convert.toString(tot));
  					}
  					if(a == 3)
  					{
  						CommonMessage.debugMsg("A VALUE IN A3.........: "+a);
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(0).setCellStyle(style1);
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(0).setCellValue("Note:-");
  						for(int k = 2 ; k<8 ; k++)
  						{
  							CellRangeAddress region = CellRangeAddress.valueOf("A1:"+ getCharForExcelCol(k)  +rowadd);
  	  				        final BorderStyle borderMediumDashed = BorderStyle.THICK;
  	  				        //RegionUtil.setBorderBottom( borderMediumDashed,region, sheet, wb );
  	  				        //RegionUtil.setBorderTop( borderMediumDashed,region, sheet, wb );
  	  				       // RegionUtil.setBorderLeft( borderMediumDashed,region, sheet, wb );
  	  				        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
  	  				        
  	  				       // RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), region, sheet, wb );
  	  				        //RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), region, sheet, wb );
  	  				        //RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), region, sheet, wb );
  	  				        //RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), region, sheet, wb );
  						//sheet.getRow(whywhyStartRow + (j*1)-1).getCell(k).set(CellStyle.BORDER_NONE);
  						}
  						
  					}
  					if(a == 4)
  					{
  						CommonMessage.debugMsg("A VALUE IN A4.........: "+a);
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(0).setCellStyle(style1);
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(0).setCellValue("Revision Date:");
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellStyle(style1);
  						sheet.getRow(whywhyStartRow + (j*1)-1).getCell(3).setCellValue("Revision No:");
  					}
  					  j++;
  					rowadd = rowadd+1;
  				  }		
		  			  
		  		}
	  			    	
	  			  		   
			}
		    
		  
		    
		    return wb;	  
		 
	}

}
