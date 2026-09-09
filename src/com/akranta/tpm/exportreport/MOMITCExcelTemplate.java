package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.MoMeetingService;
import com.akranta.tpm.service.impl.MoMeetingServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
//import com.sun.rowset.internal.Row;

public class MOMITCExcelTemplate {
	
	MoMeetingService moMeetingService;
	
	public MOMITCExcelTemplate(DBActionTemplate dbActionTemplate) {
		moMeetingService = new MoMeetingServiceImpl(dbActionTemplate);
	}

	public Workbook fillValues(List<String[]> mommstData,
			List<String[]> momdtlData, List<String[]> atnplnData, String path,List<String[]> attdanceData, List<String[]> externalData) throws IOException {
		// TODO Auto-generated method stub

		String excelFormat = null;
		String excelPath = null;
		excelPath = "/MOM.xlsx";

		InputStream inp = new FileInputStream(path + excelPath);
		
		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);
		
		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 15);
		//header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
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
        if(mommstData !=null && mommstData.size()>0){
           for(g=0;g<mommstData.size();g++){
        	    
        	    sheet.getRow(2).getCell(3).setCellValue(mommstData.get(g)[0]);//TITLE
		    	sheet.getRow(2).getCell(15).setCellValue(mommstData.get(g)[1]);//TYPE
		    	sheet.getRow(3).getCell(12).setCellValue(mommstData.get(g)[2]);//PILLAR
		    	sheet.getRow(3).getCell(15).setCellValue(mommstData.get(g)[3]);//DATE
		    	sheet.getRow(5).getCell(1).setCellValue(mommstData.get(g)[4]);//AGENDA
		    	sheet.getRow(5).getCell(9).setCellValue(mommstData.get(g)[5]);//SAFETY TALK
		    	sheet.getRow(5).getCell(14).setCellValue(mommstData.get(g)[6]);//REMARKS
		    	sheet.getRow(3).getCell(3).setCellValue(mommstData.get(g)[7]);//FUNCLOCTION
		    	sheet.getRow(1).getCell(15).setCellValue(mommstData.get(g)[8]);//FUNCLOCTION
	       }
        }
        
         int strRow =11;
		
         if(momdtlData !=null && momdtlData.size()>0){
        	
        	 for(g=0;g<momdtlData.size();g++){
				//if (g<10) {
        		    sheet.getRow(strRow+g).getCell(1).setCellValue(g+1);//sno
				    sheet.getRow(strRow+g).getCell(2).setCellValue(momdtlData.get(g)[0]);//TITLE
		    	    sheet.getRow(strRow+g).getCell(6).setCellValue(momdtlData.get(g)[1]);//TYPE
		    	    sheet.getRow(strRow+g).getCell(10).setCellValue(momdtlData.get(g)[2]);//TITLE
		    	    sheet.getRow(strRow+g).getCell(14).setCellValue(momdtlData.get(g)[3]);//TYPE
		    	    sheet.getRow(strRow+g).getCell(15).setCellValue(momdtlData.get(g)[4]);//TYPE
		    	    sheet.getRow(strRow+g).getCell(16).setCellValue(momdtlData.get(g)[5]);//TYPE
				//}
		    	    /*
		    	    CommonMessage.debugMsg("momdtlData.get(g)[0]"+momdtlData.get(g)[0]);
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
		    	    
		    	    ExcelUtils.setRowHeight(wb, sheet, strRow+g ,2, momdtlData.get(g)[0], 40);
		    	    
		    }
			
			strRow = strRow + g; 
			//strRow = strRow + 1;
            
		    strRow = strRow+0;
			
            CellStyle style1 = wb.createCellStyle();
            
	        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style1.setAlignment( HorizontalAlignment.CENTER);
	        style1.setVerticalAlignment( VerticalAlignment.CENTER);
	        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        
	        CommonMessage.debugMsg(" In Excel Template :: 5 ");
	        
	        Font header1Font1 = wb.createFont();
	        header1Font1.setBold(true);
	        
	        style1.setFont(header1Font1);
	        
			sheet.getRow(strRow).getCell(1).setCellValue("Attendance");//TITLE	
			sheet.getRow(strRow).getCell(1).setCellStyle(style1);
			
			strRow =strRow +1;
	        style1.setBorderLeft(BorderStyle.THIN);
	        style1.setBorderBottom(BorderStyle.THIN);
			
			sheet.getRow(strRow).getCell(1).setCellValue("S.No");//TITLE
            sheet.getRow(strRow).getCell(2).setCellValue("Employee");//TITLE
            sheet.getRow(strRow).getCell(6).setCellValue("Employee Code");//TITLE
            //sheet.getRow(strRow).getCell(10).setCellValue("Role");//TITLE
            sheet.getRow(strRow).getCell(10).setCellValue("Status ?");//TITLE
            sheet.getRow(strRow).getCell(14).setCellValue("External Member");//TITLE
            sheet.getRow(strRow).getCell(15).setCellValue("Purpose");//TITLE
            
            header1Font1.setBold(true);
            style1.setFont(header1Font1);
            
	        style1.setBorderRight(BorderStyle.THIN);
            style1.setBorderLeft(BorderStyle.THIN);
			
            for (int ss=1;ss<=15;ss++)
            	sheet.getRow(strRow).getCell(ss).setCellStyle(style1);

            //sheet.getRow(strRow).setRowStyle(style1);
        	
        }else{
        	
            CellStyle style1 = wb.createCellStyle();
	        
	        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
	        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style1.setAlignment(HorizontalAlignment.CENTER);
	        style1.setVerticalAlignment( VerticalAlignment.CENTER);
	        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        
	        Font header1Font1 = wb.createFont();
	        header1Font1.setBold(true);
	        style1.setBorderLeft( BorderStyle.THIN);
	        style1.setBorderBottom( BorderStyle.THIN);
	        style1.setFont(header1Font1);
	        
	        style1.setAlignment(HorizontalAlignment.LEFT);
	        strRow =strRow +1;
	        sheet.getRow(strRow).getCell(1).setCellValue("Attendance");//TITLE
			sheet.getRow(strRow).getCell(1).setCellStyle(style1);
			strRow =strRow +1;
			sheet.getRow(strRow).getCell(1).setCellValue("S.No");//TITLE
            sheet.getRow(strRow).getCell(2).setCellValue("Employee");//TITLE
            sheet.getRow(strRow).getCell(6).setCellValue("Employee Code");//TITLE
            //sheet.getRow(strRow).getCell(5).setCellValue("Role");//TITLE
            sheet.getRow(strRow).getCell(10).setCellValue("Status ?");//TITLE
            sheet.getRow(strRow).getCell(14).setCellValue("External Member");//TITLE
            sheet.getRow(strRow).getCell(15).setCellValue("Purpose");//TITLE
            
         
            header1Font1.setBold(true);
            style1.setFont(header1Font1);
            
	        style1.setBorderRight(BorderStyle.THIN);
            style1.setBorderLeft(BorderStyle.THIN);
			
            sheet.getRow(strRow).getCell(1).setCellStyle(style1);
            sheet.getRow(strRow).getCell(2).setCellStyle(style1);
            sheet.getRow(strRow).getCell(6).setCellStyle(style1);
            sheet.getRow(strRow).getCell(10).setCellStyle(style1);
            //sheet.getRow(strRow).getCell(6).setCellStyle(style1);
            
            for (int ss=1;ss<=15;ss++)
            	sheet.getRow(strRow).getCell(ss).setCellStyle(style1);
            
        }
         
        strRow =strRow +1;
       
		//if ((attdanceData == null || attdanceData.size() > 0)||(externalData != null || externalData.size() > 0)) {
        int lastRow = 0;
        if (attdanceData != null && attdanceData.size() > 0) {
        	CommonMessage.debugMsg(" 3 For :: ");
			for (g = 0; g < attdanceData.size(); g++) {
				//CommonMessage.debugMsg(" 4 For :: ");
				sheet.getRow(strRow+g).getCell(1).setCellValue(g+1);//sno	
				sheet.getRow(strRow +g).getCell(2).setCellValue(attdanceData.get(g)[1]);//TITLE
	    	    sheet.getRow(strRow +g).getCell(6).setCellValue(attdanceData.get(g)[2]);//TYPE
	    	    //sheet.getRow(strRow +g).getCell(10).setCellValue(attdanceData.get(g)[3]);//TYPE
	    	    sheet.getRow(strRow +g).getCell(10).setCellValue(attdanceData.get(g)[4]);//TYPE
             
	    	    /*if (externalData != null && externalData.size() > 0) {
		    	    sheet.getRow(strRow +g).getCell(14).setCellValue(externalData.get(g)[0]);//TITLE
		    	    sheet.getRow(strRow +g).getCell(15).setCellValue(externalData.get(g)[1]);//TYPE
	    	    }*/
	    	    
	    	    CommonMessage.debugMsg(" 4 For :: ");
	    	}

			lastRow = strRow +g;
			/*for (int n = strRow +g; n <= 200; n++) {
				//if (externalData == null) {
				    sheet.removeRow(sheet.getRow(n) );
				//}
			}*/
			
         }
        
        CommonMessage.debugMsg(" Inside :: "+strRow);
        if (externalData != null && externalData.size() > 0) {
			for (g = 0; g < externalData.size(); g++) {
				//CommonMessage.debugMsg(" 2 :: "+externalData.get(g)[1]);
				//CommonMessage.debugMsg(" 1 For :: "+externalData.get(g)[0]);
				sheet.getRow(strRow +g).getCell(14).setCellValue(externalData.get(g)[0]);//TITLE
	    	    sheet.getRow(strRow +g).getCell(15).setCellValue(externalData.get(g)[1]);//TYPE
			}
			if (lastRow<(strRow+g)) 
				lastRow = strRow+g;
	    }
        
        CommonMessage.debugMsg(" lastRow:: "+lastRow);
        for (int n = lastRow; n <= 200; n++) {
			//if (externalData == null) {
			    sheet.removeRow(sheet.getRow(n) );
			//}
		}
				
        sheet.protectSheet("admin");
	    return wb;
    }
}