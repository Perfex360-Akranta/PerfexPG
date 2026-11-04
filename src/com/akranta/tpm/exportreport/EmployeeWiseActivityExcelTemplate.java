package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class EmployeeWiseActivityExcelTemplate {
	
	public EmployeeWiseActivityExcelTemplate(DBActionTemplate dbActionTemplate) {
		//plnJobService = new plnJobServiceImpl(dbActionTemplate);
	}

	public Workbook fillValues(Map<Integer, List<String[]>> empWActivityData,
			String format, String path, String keyId)
			throws Exception {

		String excelFormat = null;
		String excelPath = null;
		int startColIndex=0;
		int endColIndex=20;
		int startRowIndex=0;
		int endRowIndex=5;
		int sheetNo=0;
		int sNo=1;
		String exsistingFlag=null;
		excelPath = "/EAWrptTmpl.xlsx";
		CommonFunctions.debugMsg("Xl path........" + path + excelPath);
		InputStream inp = new FileInputStream(path + excelPath);

		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(sheetNo);

		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 15);
		//header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		header1Font.setColor(IndexedColors.BLACK.getIndex());
		//header1Font.setFontName(XLConditionalFormats.FONT_WEBDINGS);
		//CellStyle style = wb.createCellStyle();
		java.awt.Color bgColor = new java.awt.Color(
			    (int)245,
			    (int) 123,
			    (int) 61);
		
		 CellStyle styleGroup = wb.createCellStyle();
        // styleGroup.setFillBackgroundColor((short) 233);
		((XSSFCellStyle) styleGroup).setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		
		styleGroup.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(header1Font);
		String[] impshtexl=null;
		List<String[]> empAWExcelData = empWActivityData.get(0);
		endRowIndex+= empAWExcelData.size();
		if (empAWExcelData != null && empAWExcelData.size() > 1) {
            impshtexl = empAWExcelData.get(1);
            
            int row=2;
            sheet.getRow(row).getCell(1).setCellValue(impshtexl[4]);//Factory name
            sheet.getRow(row).getCell(4).setCellValue(impshtexl[5]);//shop name
            sheet.getRow(row).getCell(8).setCellValue(impshtexl[6]);//Cell name
            sheet.getRow(row).getCell(12).setCellValue(impshtexl[7]);//Work order number
            row=4;
        	sheet.getRow(row).getCell(1).setCellValue(impshtexl[9]);//Equipment Name
        	sheet.getRow(row).getCell(4).setCellValue(impshtexl[10]);//work start date 
        	sheet.getRow(row).getCell(8).setCellValue(impshtexl[11]);//work end date
        	sheet.getRow(row).getCell(12).setCellValue(impshtexl[8]);//Work order date
			
        	row=6;
           sheet.getRow(row).getCell(1).setCellValue(impshtexl[3]);//Employee number
           sheet.getRow(row).getCell(4).setCellValue(impshtexl[2]);//employee name
			
			/*
			row+=2;
        	sheet.getRow(row).getCell(1).setCellValue(impshtexl[6]);
			sheet.getRow(row).getCell(9).setCellValue(impshtexl[13]);
			sheet.getRow(row).getCell(11).setCellValue(impshtexl[14]);
			
			row+=2;
        	sheet.getRow(row).getCell(1).setCellValue(impshtexl[7]);
			sheet.getRow(row).getCell(4).setCellValue(impshtexl[9]);
			sheet.getRow(row).getCell(6).setCellValue(impshtexl[10]);
			sheet.getRow(row).getCell(9).setCellValue(impshtexl[15]);
			sheet.getRow(row).getCell(11).setCellValue(impshtexl[16]);*/
          
			CellStyle style1 = wb.createCellStyle();
			//style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        //style1.setAlignment(CellStyle.ALIGN_CENTER);
	        //style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        //style1.setFillPattern(CellStyle.SOLID_FOREGROUND); 
	        //Font header1Font1 = wb.createFont();
	        // header1Font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        //header1Font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        //style1.setFont(header1Font1);
			style1.setBorderTop(BorderStyle.THIN);
	        style1.setBorderLeft(BorderStyle.THIN);
	        style1.setBorderBottom(BorderStyle.THIN);
		    style1.setBorderRight(BorderStyle.THIN);
		    //row=6;
			for (int i = 1; i < empAWExcelData.size(); i++) {
				impshtexl = empAWExcelData.get(i);
				row=i+9;		
				
				//sheet.getRow(row).getCell(1).setCellValue(sNo);//Serial Number
    			//sheet.getRow(row).getCell(2).setCellValue(impshtexl[4]);//WOno
				//sheet.getRow(row).getCell(4).setCellValue(impshtexl[5]);//WO Date
				//sheet.getRow(row).getCell(6).setCellValue(impshtexl[6]);//Equipment number
				//sheet.getRow(row).getCell(10).setCellValue(impshtexl[7]);//Equipment Name
				sheet.getRow(row).getCell(0).setCellValue(impshtexl[12]);//Assembly Name
				sheet.getRow(row).getCell(2).setCellValue(impshtexl[13]);//Activity Type
				sheet.getRow(row).getCell(3).setCellValue(impshtexl[14]);//Activity
				sheet.getRow(row).getCell(6).setCellValue(impshtexl[15]);//Frequency
				sheet.getRow(row).getCell(7).setCellValue(impshtexl[16]);//Inspection parameter and Measuring Method
				sheet.getRow(row).getCell(11).setCellValue(impshtexl[17]);//Actual Reading
				sheet.getRow(row).getCell(12).setCellValue(impshtexl[18]);//Duration
				sheet.getRow(row).getCell(13).setCellValue(impshtexl[19]);//Spares Required
				sheet.getRow(row).getCell(14).setCellValue(impshtexl[20]);//Tools Required
				//sheet.getRow(row).getCell(15).setCellValue(impshtexl[21]);//Observation
				//sheet.getRow(row).getCell(16).setCellValue(impshtexl[22]);//Remarks
				//sNo++;
				
				//for (int ss= 0;ss<=endColIndex;ss++)
		         //	sheet.getRow(row).getCell(ss).setCellStyle(style1);
				setRowHeight(wb, sheet, row ,0, empAWExcelData.get(i)[16],30);
				// ExcelUtils.setRowHeight(wb, sheet, row ,0, empAWExcelData.get(i)[14],40);
				/*float disDetLen=impshtexl[16].length();
				if (disDetLen < 300)
			    	disDetLen=(float) 1.5;
			    else
			    	disDetLen = disDetLen/30;
				CommonFunctions.debugMsg("Size "+disDetLen);
				 sheet.getRow(row).setHeightInPoints(disDetLen/2);*/
			}
			excelFormat = impshtexl[0];	        
		}
		endColIndex+=1;
		sheet.setPrintGridlines(false);
        sheet.setHorizontallyCenter(true);
        //sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
        sheet.setAutobreaks(true);
        sheet.setFitToPage(true);
        //sheet.setDefaultRowHeight((short)0);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        
        wb.setPrintArea(sheetNo,startColIndex,endColIndex,startRowIndex,endRowIndex);
        
		sheet.protectSheet("admin");
		
		return wb;
	}
	public void setRowHeight(Workbook wb, Sheet sheet, int row, int colNo, String strVal, int rowWidth)
	{
		String disDet  = strVal.trim();
	    String entStr = disDet.replaceAll("\n+", "\n");
	    float disDetLen = disDet.length();
	    int enterCnt = StringUtils.countMatches(entStr,"\n");
	    if (disDetLen < rowWidth)
	    	disDetLen=(float) 1.1;
	    else
	    	disDetLen = disDetLen/40;
	    disDetLen = 25*(disDetLen+enterCnt);
	    //sheet.getRow(strRow+g).setHeight((short) disDetLen);
	    sheet.getRow(row).setHeightInPoints(disDetLen);
	}
}
