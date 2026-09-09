package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import com.akranta.tpm.service.NearMissService;
import com.akranta.tpm.service.impl.NearMissServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class NEARMISSITCExcelTemplate {
	
	NearMissService nearMissService;
	public NEARMISSITCExcelTemplate(DBActionTemplate dbActionTemplate) {
		nearMissService = new NearMissServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> nearmissData, List<String[]> unsafeactData, List<String[]> unsafeconditionData, String path)throws IOException {
		// TODO Auto-generated method stub
		
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/NearMissNew.xlsx";

		InputStream inp = new FileInputStream(path + excelPath);

		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);

		Font header1Font = wb.createFont();
        header1Font.setFontHeightInPoints((short)15);
        header1Font.setBold(true);
        header1Font.setColor(IndexedColors.BLACK.getIndex());
        header1Font.setFontName(XLConditionalFormats.FONT_WINGDINGS);
        
		CellStyle style = wb.createCellStyle();
	    CellStyle style1 = wb.createCellStyle();
	        
        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //style1.setFillPattern(CellStyle.DIAMONDS);
        
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);        
        
        style.setBorderTop(BorderStyle.NONE);
		style.setBorderLeft(BorderStyle.NONE);
		style.setBorderRight(BorderStyle.NONE);
		style.setBorderBottom(BorderStyle.NONE);
		
        style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		
    	style.setFont(header1Font);
    	
    	int g;
    	int i;
    	int j;
        if(nearmissData !=null && nearmissData.size()>0){
          for(g=0;g<nearmissData.size();g++){
        	    
    	   // String sevtypotental=nearmissData.get(g)[7]; 
	    String Probable=nearmissData.get(g)[9]; 
        	  CommonMessage.debugMsg("nearmissData.get(g)[0]"+nearmissData.get(g)[0]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[1]"+nearmissData.get(g)[1]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[2]"+nearmissData.get(g)[2]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[3]"+nearmissData.get(g)[3]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[4]"+nearmissData.get(g)[4]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[5]"+nearmissData.get(g)[5]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[6]"+nearmissData.get(g)[6]);
        	  CommonMessage.debugMsg("nearmissData.get(g)[7]"+nearmissData.get(g)[7]);
           	  CommonMessage.debugMsg("nearmissData.get(g)[8]"+nearmissData.get(g)[8]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[9]"+nearmissData.get(g)[9]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[10]"+nearmissData.get(g)[10]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[11]"+nearmissData.get(g)[11]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[12]"+nearmissData.get(g)[12]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[13]"+nearmissData.get(g)[13]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[14]"+nearmissData.get(g)[14]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[15]"+nearmissData.get(g)[15]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[16]"+nearmissData.get(g)[16]);
           	 CommonMessage.debugMsg("nearmissData.get(g)[17]"+nearmissData.get(g)[17]);
        	 CommonMessage.debugMsg("nearmissData.get(g)[18]"+nearmissData.get(g)[18]);
        	 CommonMessage.debugMsg("nearmissData.get(g)[19]"+nearmissData.get(g)[19]);
        	 CommonMessage.debugMsg("nearmissData.get(g)[20]"+nearmissData.get(g)[20]);
        	 CommonMessage.debugMsg("nearmissData.get(g)[21]"+nearmissData.get(g)[21]);
        	 CommonMessage.debugMsg("nearmissData.get(g)[22]"+nearmissData.get(g)[22]);
        	 CommonMessage.debugMsg("nearmissData.get(g)[23]"+nearmissData.get(g)[23]);
           	 
          
	    	
           	
    	    //sheet.getRow(2).getCell(3).setCellValue(nearmissData.get(g)[7]);//NAME
    	    sheet.getRow(3).getCell(2).setCellValue(nearmissData.get(g)[7]);//NAME
    	    sheet.getRow(2).getCell(2).setCellValue(nearmissData.get(g)[0]);//NAME
            sheet.getRow(2).getCell(6).setCellValue(nearmissData.get(g)[2]);//NAME
             sheet.getRow(4).getCell(2).setCellValue(nearmissData.get(g)[3]);//NAME
	    	sheet.getRow(4).getCell(5).setCellValue(nearmissData.get(g)[4]);//PREPARED DATE
	    	sheet.getRow(5).getCell(2).setCellValue(nearmissData.get(g)[5]);//PREPARED DATE
	    	sheet.getRow(5).getCell(5).setCellValue(nearmissData.get(g)[1]);//PREPARED DATE
	    	sheet.getRow(6).getCell(2).setCellValue(nearmissData.get(g)[6]);
	    	sheet.getRow(7).getCell(2).setCellValue(nearmissData.get(g)[8]);
	    	sheet.getRow(7).getCell(5).setCellValue(nearmissData.get(g)[2]);
	    	//if("MAJOR".equals(sevtypotental.replaceAll(" ","").toUpperCase())){
	    	/*if("Major".equals(sevtypotental)){
	    		sheet.getRow(9).getCell(2).setCellStyle(style1);
	    	}else if("Serious".equals(sevtypotental)){
	    		sheet.getRow(9).getCell(6).setCellStyle(style1);
	    	}else if("Minor".equals(sevtypotental)){
	    		sheet.getRow(9).getCell(9).setCellStyle(style1);
	    	}*/
	    	
	    	if("Frequent".equals(Probable)){
	    		sheet.getRow(9).getCell(2).setCellStyle(style1);
	    	}else if("Occasional".equals(Probable)){
	    		sheet.getRow(9).getCell(4).setCellStyle(style1);
	    	}else if("Rare".equals(Probable)){
	    		sheet.getRow(9).getCell(6).setCellStyle(style1);
	    	}
	    	sheet.getRow(10).getCell(2).setCellValue(nearmissData.get(g)[10]);//investigation
	    	sheet.getRow(11).getCell(2).setCellValue(nearmissData.get(g)[13]);
	    	sheet.getRow(12).getCell(4).setCellValue(nearmissData.get(g)[14]);

	    
	    	 sheet.getRow(12).getCell(6).setCellValue(nearmissData.get(g)[15]);
	    
	    	 
	    	CommonMessage.debugMsg("nearmissData.get(g)[21]::"+nearmissData.get(g)[21]);
	    	         sheet.getRow(16).getCell(2).setCellValue(nearmissData.get(g)[11]);
	    			 sheet.getRow(17).getCell(2).setCellValue(nearmissData.get(g)[20]);
			         sheet.getRow(17).getCell(5).setCellValue(nearmissData.get(g)[21]);
	    	
	    	
	    	
	    	
	   
	    		  sheet.getRow(18).getCell(2).setCellValue(nearmissData.get(g)[22]);
	  	         sheet.getRow(18).getCell(5).setCellValue(nearmissData.get(g)[23]);
	    
	    	
	         
	      
	    	//sheet.getRow(12).getCell(6).setCellValue(nearmissData.get(g)[13]);

	    	/*sheet.getRow(12).getCell(2).setCellValue(nearmissData.get(g)[8]);//ACTIONRECOMMENDED
	    	sheet.getRow(15).getCell(2).setCellValue(nearmissData.get(g)[9]);//RESPONSIBILITY
	    	sheet.getRow(15).getCell(6).setCellValue(nearmissData.get(g)[10]);//TARGETDATE
	    	sheet.getRow(15).getCell(8).setCellValue(nearmissData.get(g)[11]);//STATUS*/
	    	
	    	//CommonMessage.debugMsg("sevtypotental["+g+"]"+sevtypotental);
	      }
	  }
        
        
         CommonMessage.debugMsg(" unsafeactData size :: "+unsafeactData.size());
         
         int strRow =13;
         
         CommonMessage.debugMsg("unsafeactData.size()"+unsafeactData.size());
         CommonMessage.debugMsg("unsafeconditionData.size()"+unsafeconditionData.size());
		
		if(unsafeactData !=null || unsafeactData.size()>0){
       	 
       	   for(i=0;i<unsafeactData.size();i++){
			//  sheet.getRow(strRow+(g-1)).getCell(1).setCellValue(unsafeactData.get(g)[1]);//TITLE
       		 CommonMessage.debugMsg("unsafeactData.get(g)[1]"+unsafeactData.get(i)[1]);
       		 sheet.getRow(14).getCell(3).setCellValue(unsafeactData.get(i)[1]);
       		  // String unsafeactid=unsafeactData.get(g)[0];
			//  CommonMessage.debugMsg(" unsafeactid ["+g+"]"+unsafeactid.length());
			  
             // if(unsafeactid.length()>0){
            	//  sheet.getRow(strRow+(g-1)).getCell(4).setCellValue("�");//TITLE
			 // }
			  
		   }
		 }
		 
		 int strRow1 =13;
			
	
		 if(unsafeconditionData !=null || unsafeconditionData.size()>0){
       	 
       	   for(j=0;j<unsafeconditionData.size();j++){
       		 CommonMessage.debugMsg("unsafeconditionData.get(g)[1]"+unsafeconditionData.get(j)[1]);
			 // sheet.getRow(strRow1+(g-2)).getCell(6).setCellValue(unsafeconditionData.get(g)[1]);//TITLE
			  
			 //String unsafeconditionid=unsafeconditionData.get(g)[0]; 
       		 sheet.getRow(14).getCell(1).setCellValue(unsafeconditionData.get(j)[1]);
			  
			//  CommonMessage.debugMsg(" unsafeconditionid ["+g+"]"+unsafeconditionid.length());
			  
			 // if(unsafeconditionid.length()>0){
			//	  sheet.getRow(strRow1+(g-2)).getCell(10).setCellValue("�");//TITLE
			//  }
			  
		    }
		 }
       	 
         sheet.protectSheet("admin");
         return wb;
   }
}