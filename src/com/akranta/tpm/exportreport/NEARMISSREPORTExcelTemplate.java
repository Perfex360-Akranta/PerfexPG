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

public class NEARMISSREPORTExcelTemplate {
	
	NearMissService nearMissService;
	public NEARMISSREPORTExcelTemplate(DBActionTemplate dbActionTemplate) {
		nearMissService = new NearMissServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> nearmissData, List<String[]> unsafeactData, List<String[]> unsafeconditionData, String path)throws IOException {
		// TODO Auto-generated method stub
		
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/NearMissReport.xlsx";

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
        if(nearmissData !=null && nearmissData.size()>0){
          for(g=0;g<nearmissData.size();g++){
        	    
    	    String sevtypotental=nearmissData.get(g)[6]; 
	    	String Probable=nearmissData.get(g)[7]; 
	    	
    	    sheet.getRow(2).getCell(2).setCellValue(nearmissData.get(g)[0]);//NAME
	    	sheet.getRow(2).getCell(8).setCellValue(nearmissData.get(g)[1]);//PREPARED DATE
	    	sheet.getRow(3).getCell(2).setCellValue(nearmissData.get(g)[2]);//DMT
	    	sheet.getRow(3).getCell(8).setCellValue(nearmissData.get(g)[3]);//JH
	    	sheet.getRow(4).getCell(2).setCellValue(nearmissData.get(g)[4]);//DATE&TIMEOFOCCURENCE
	    	sheet.getRow(5).getCell(2).setCellValue(nearmissData.get(g)[5]);//BRIEFDESCRIPTIONOFNEARMISS
	    	
	    	//if("MAJOR".equals(sevtypotental.replaceAll(" ","").toUpperCase())){
	    	if("Major".equals(sevtypotental)){
	    		sheet.getRow(9).getCell(2).setCellStyle(style1);
	    	}else if("Serious".equals(sevtypotental)){
	    		sheet.getRow(9).getCell(6).setCellStyle(style1);
	    	}else if("Minor".equals(sevtypotental)){
	    		sheet.getRow(9).getCell(9).setCellStyle(style1);
	    	}
	    	
	    	if("Frequent".equals(Probable)){
	    		sheet.getRow(11).getCell(2).setCellStyle(style1);
	    	}else if("Occasional".equals(Probable)){
	    		sheet.getRow(11).getCell(6).setCellStyle(style1);
	    	}else if("Rare".equals(Probable)){
	    		sheet.getRow(11).getCell(9).setCellStyle(style1);
	    	}
	    	
	    	sheet.getRow(12).getCell(2).setCellValue(nearmissData.get(g)[8]);//ACTIONRECOMMENDED
	    	sheet.getRow(15).getCell(2).setCellValue(nearmissData.get(g)[9]);//RESPONSIBILITY
	    	sheet.getRow(15).getCell(6).setCellValue(nearmissData.get(g)[10]);//TARGETDATE
	    	sheet.getRow(15).getCell(8).setCellValue(nearmissData.get(g)[11]);//STATUS
	    	
	    	CommonMessage.debugMsg("sevtypotental["+g+"]"+sevtypotental);
	      }
	  }
        
        
         CommonMessage.debugMsg(" unsafeactData size :: "+unsafeactData.size());
         
         int strRow =18;
		
		 if(unsafeactData !=null && unsafeactData.size()>0){
       	 
       	   for(g=1;g<unsafeactData.size();g++){
			  sheet.getRow(strRow+(g-1)).getCell(1).setCellValue(unsafeactData.get(g)[1]);//TITLE
			  String unsafeactid=unsafeactData.get(g)[0];
			  CommonMessage.debugMsg(" unsafeactid ["+g+"]"+unsafeactid.length());
			  
              if(unsafeactid.length()>0){
            	  sheet.getRow(strRow+(g-1)).getCell(4).setCellValue("�");//TITLE
			  }
			  
		    }
		 }
		 
		 int strRow1 =18;
			
		 if(unsafeconditionData !=null && unsafeconditionData.size()>0){
       	 
       	   for(g=2;g<unsafeconditionData.size();g++){
			  sheet.getRow(strRow1+(g-2)).getCell(6).setCellValue(unsafeconditionData.get(g)[1]);//TITLE
			  
			  String unsafeconditionid=unsafeconditionData.get(g)[0]; 
			  
			  CommonMessage.debugMsg(" unsafeconditionid ["+g+"]"+unsafeconditionid.length());
			  
			  if(unsafeconditionid.length()>0){
				  sheet.getRow(strRow1+(g-2)).getCell(10).setCellValue("�");//TITLE
			  }
			  
		    }
		 }
       	 
         sheet.protectSheet("admin");
         return wb;
   }
}