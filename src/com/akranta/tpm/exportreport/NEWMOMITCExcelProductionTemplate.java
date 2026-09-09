package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.MoMeetingService;
import com.akranta.tpm.service.NewMoMeetingService;
import com.akranta.tpm.service.impl.MoMeetingServiceImpl;
import com.akranta.tpm.service.impl.NewMoMeetingServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class 	NEWMOMITCExcelProductionTemplate {
	
	NewMoMeetingService newmoMeetingService;
	public NEWMOMITCExcelProductionTemplate(DBActionTemplate dbActionTemplate) {
		newmoMeetingService = new NewMoMeetingServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> mommstData,List<String[]> momdtlData, List<String[]> atnplnData, String path,
			List<String[]> attdanceData, String glbType, List<String[]> externalData) throws IOException {


		// TODO Auto-generated method stub

		String excelFormat = null;
		String excelPath = null;
		excelPath = "/MOM_PDN.xlsx";

		InputStream inp = new FileInputStream(path + excelPath);

		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);
		
		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 15);
		header1Font.setBold(true);
		header1Font.setColor(IndexedColors.BLACK.getIndex());
		header1Font.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		CellStyle style = wb.createCellStyle();
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
		CommonFilter commonFilter = new CommonFilter();		
        int g;  
        CommonMessage.debugMsg(" Inside New Excel Template :: 1"+mommstData.size());
        
        CommonMessage.debugMsg(" Inside New Excel Template :: 1"+mommstData);
        
         if(mommstData !=null && mommstData.size()>0){
           for(g=0;g<mommstData.size();g++){
        	   CommonMessage.debugMsg(" INSIDE FOR LOOP :: ");
        	   CommonMessage.debugMsg("impshtexl["+g+"]");
        	    sheet.getRow(2).getCell(3).setCellValue(mommstData.get(g)[0]);//TITLE
		    	sheet.getRow(2).getCell(6).setCellValue(mommstData.get(g)[1]);//TYPE
		    	sheet.getRow(3).getCell(6).setCellValue(mommstData.get(g)[3]);//DATE
		    	sheet.getRow(5).getCell(1).setCellValue(mommstData.get(g)[4]);//AGENDA
		    	sheet.getRow(3).getCell(3).setCellValue(mommstData.get(g)[7]);//FUNCLOCTION
		    	sheet.getRow(1).getCell(6).setCellValue(mommstData.get(g)[8]);//FUNCLOCTION
		    	CommonMessage.debugMsg("impshtexl["+g+"]");
	       }
    }

         CommonMessage.debugMsg(" Inside New Excel Template :: 2"+momdtlData.size());
         
		int strRow =11;
		
		 if(momdtlData !=null && momdtlData.size()>0){
        	 
        	 for(g=0;g<momdtlData.size();g++){
				    
        		    sheet.getRow(strRow+g).getCell(1).setCellValue(g+1);//sno
				    sheet.getRow(strRow+g).getCell(3).setCellValue(momdtlData.get(g)[0]);//TITLE
		    	    sheet.getRow(strRow+g).getCell(4).setCellValue(momdtlData.get(g)[2]);//TITLE
		    	    sheet.getRow(strRow+g).getCell(5).setCellValue(momdtlData.get(g)[3]);//TYPE
		    	    sheet.getRow(strRow+g).getCell(6).setCellValue(momdtlData.get(g)[4]);//TYPE
		    	    sheet.getRow(strRow+g).getCell(7).setCellValue(momdtlData.get(g)[5]);//TYPE
		    	    
		    	    /*
		    	    String disDet  = momdtlData.get(g)[0];
		    	    float disDetLen = disDet.length();
		    	    CommonMessage.debugMsg("disDetLen=="+disDetLen);
		    	    if (disDetLen<40)
		    	    	disDetLen=(float) 1.5;
		    	    else
		    	    	disDetLen = disDetLen/40;
		    	    CommonMessage.debugMsg("disDetLen=="+disDetLen);
		    	    disDetLen = 20*disDetLen;
		    	    //sheet.getRow(strRow+g).setHeight((short) disDetLen);
		    	    sheet.getRow(strRow+g).setHeightInPoints(disDetLen);
		    	    */
		    	    
		    	    ExcelUtils.setRowHeight(wb, sheet, strRow+g ,3, momdtlData.get(g)[0], 40);

		    }
			
			strRow = strRow + g; 
			//strRow = strRow + 1;
            
            //sheet.getRow(strRow).getCell(1).setCellValue("Members Present");//TITLE
            
			strRow = strRow + 1;
			
			CellStyle style1 = wb.createCellStyle();
	        
	        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style1.setAlignment(HorizontalAlignment.CENTER);
	        style1.setVerticalAlignment(VerticalAlignment.CENTER);
	        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        
	        Font header1Font1 = wb.createFont();
	        header1Font1.setBold(true);
	        style1.setBorderLeft(BorderStyle.THIN);
	        style1.setBorderBottom(BorderStyle.THIN);
	        style1.setFont(header1Font1);
	        
	        style1.setAlignment(HorizontalAlignment.LEFT);
			sheet.getRow(strRow).getCell(1).setCellValue("Attendance");//TITLE
			sheet.getRow(strRow).getCell(1).setCellStyle(style1);
			
			
			strRow =strRow +1;
			
			sheet.getRow(strRow).getCell(1).setCellValue("S.No");//TITLE
            sheet.getRow(strRow).getCell(3).setCellValue("Employee");//TITLE
            sheet.getRow(strRow).getCell(4).setCellValue("Employee Code");//TITLE
            //sheet.getRow(strRow).getCell(5).setCellValue("Role");//TITLE
            sheet.getRow(strRow).getCell(5).setCellValue("Status ?");//TITLE
            sheet.getRow(strRow).getCell(6).setCellValue("External Member");//TITLE
            sheet.getRow(strRow).getCell(7).setCellValue("Purpose");//TITLE
            
            header1Font1.setBold(true);
            style1.setFont(header1Font1);
            
	        style1.setBorderRight(BorderStyle.THIN);
            style1.setBorderLeft(BorderStyle.THIN);
			
            sheet.getRow(strRow).getCell(1).setCellStyle(style1);
            sheet.getRow(strRow).getCell(3).setCellStyle(style1);
            sheet.getRow(strRow).getCell(4).setCellStyle(style1);
            sheet.getRow(strRow).getCell(5).setCellStyle(style1);
            sheet.getRow(strRow).getCell(6).setCellStyle(style1);
            sheet.getRow(strRow).getCell(7).setCellStyle(style1);
            //sheet.getRow(strRow).getCell(6).setCellStyle(style1);
            
            //sheet.getRow(strRow).setRowStyle(style1);
			
        }else{
        	
            CellStyle style1 = wb.createCellStyle();
	        
	        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style1.setAlignment(HorizontalAlignment.CENTER);
	        style1.setVerticalAlignment(VerticalAlignment.CENTER);
	        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        
	        Font header1Font1 = wb.createFont();
	        header1Font1.setBold(true);
	        style1.setBorderLeft(BorderStyle.THIN);
	        style1.setBorderBottom(BorderStyle.THIN);
	        style1.setFont(header1Font1);
	        
	        style1.setAlignment(HorizontalAlignment.LEFT);
	        strRow =strRow +1;
	        sheet.getRow(strRow).getCell(1).setCellValue("Attendance");//TITLE
			sheet.getRow(strRow).getCell(1).setCellStyle(style1);
			strRow =strRow +1;
			sheet.getRow(strRow).getCell(1).setCellValue("S.No");//TITLE
            sheet.getRow(strRow).getCell(3).setCellValue("Employee");//TITLE
            sheet.getRow(strRow).getCell(4).setCellValue("Employee Code");//TITLE
            //sheet.getRow(strRow).getCell(5).setCellValue("Role");//TITLE
            sheet.getRow(strRow).getCell(5).setCellValue("Status ?");//TITLE
            sheet.getRow(strRow).getCell(6).setCellValue("External Member");//TITLE
            sheet.getRow(strRow).getCell(7).setCellValue("Purpose");//TITLE
            
            header1Font1.setBold(true);
            style1.setFont(header1Font1);
            
	        style1.setBorderRight(BorderStyle.THIN);
            style1.setBorderLeft(BorderStyle.THIN);
			
            sheet.getRow(strRow).getCell(1).setCellStyle(style1);
            sheet.getRow(strRow).getCell(3).setCellStyle(style1);
            sheet.getRow(strRow).getCell(4).setCellStyle(style1);
            sheet.getRow(strRow).getCell(5).setCellStyle(style1);
            //sheet.getRow(strRow).getCell(6).setCellStyle(style1);
        	
        }

        strRow =strRow +1;
        int lastRow = 0;
		if (attdanceData != null && attdanceData.size() > 0) {
		
			for (g = 0; g < attdanceData.size(); g++) {
			
				sheet.getRow(strRow+g).getCell(1).setCellValue(g+1);//sno	
				sheet.getRow(strRow +g).getCell(3).setCellValue(attdanceData.get(g)[1]);//TITLE
	    	    sheet.getRow(strRow +g).getCell(4).setCellValue(attdanceData.get(g)[2]);//TYPE
	    	   // sheet.getRow(strRow +g).getCell(5).setCellValue(attdanceData.get(g)[3]);//TYPE
	    	    sheet.getRow(strRow +g).getCell(5).setCellValue(attdanceData.get(g)[4]);//TYPE
		   }
			
			//strRow = strRow + g;
			lastRow = strRow + g;
			//strRow = strRow + 1;
			CommonMessage.debugMsg(" Inside New Excel Template 4 :: "+strRow);
			CommonMessage.debugMsg(" lastRow after att"+lastRow);
			/*for (int n = strRow; n < 200; n++) {
				sheet.removeRow(sheet.getRow(n) );
			}*/
		}
		
		CommonMessage.debugMsg(" Inside :: "+strRow);
        if (externalData != null && externalData.size() > 0) {
			for (g = 0; g < externalData.size(); g++) {
				sheet.getRow(strRow +g).getCell(6).setCellValue(externalData.get(g)[0]);//TITLE
	    	    sheet.getRow(strRow +g).getCell(7).setCellValue(externalData.get(g)[1]);//TYPE
			}
			if (lastRow<(strRow+g))
				lastRow = strRow+g;
			
			CommonMessage.debugMsg(" strRow+g:: "+strRow+g);
	    }
        
        CommonMessage.debugMsg(" lastRow:: "+lastRow);
        for (int n = lastRow; n < 200; n++) {
        	//CommonMessage.debugMsg(" n:: "+n);
        	sheet.removeRow(sheet.getRow(n) );
		}

		sheet.protectSheet("admin");
		
	    return wb;
    
	}

}
