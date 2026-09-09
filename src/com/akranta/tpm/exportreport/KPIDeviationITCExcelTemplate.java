package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.service.KpiTlActualKkService;
import com.akranta.tpm.service.impl.KpiTlActualKkServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;

public class KPIDeviationITCExcelTemplate{
	KpiTlActualKkService newKpiTlActualKkService;
	public KPIDeviationITCExcelTemplate(DBActionTemplate dbActionTemplate) {
		newKpiTlActualKkService = new KpiTlActualKkServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> KPIDeviationData,String path,String flid)throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside deviationTemplate");
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/KPIDeviation.xlsx";
	    CommonMessage.debugMsg("The Path is::"+path);
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
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
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
    	
    	   int g1;
     	   int strRow=3;
     	   if(KPIDeviationData !=null && KPIDeviationData.size()>0){
     	   for(g1=0;g1<KPIDeviationData.size();g1++){
     	     sheet.getRow(strRow+(g1)).getCell(1).setCellValue(KPIDeviationData.get(g1)[0]);//INDICATOR ID
     	     sheet.getRow(strRow+(g1-0)).getCell(1).setCellValue(KPIDeviationData.get(g1)[0]);//INDICATOR ID
     	     sheet.getRow(strRow+(g1)).getCell(2).setCellValue(KPIDeviationData.get(g1)[1]);//INDICATOR NAME
     	     sheet.getRow(strRow+(g1-0)).getCell(2).setCellValue(KPIDeviationData.get(g1)[1]);//INDICATOR NAME
     	     sheet.getRow(strRow+(g1)).getCell(6).setCellValue(KPIDeviationData.get(g1)[2]);//BEHAVIOUR
     	     sheet.getRow(strRow+(g1-0)).getCell(6).setCellValue(KPIDeviationData.get(g1)[2]);//BEHAVIOUR
     	     sheet.getRow(strRow+(g1)).getCell(10).setCellValue(KPIDeviationData.get(g1)[3]);//UOM
     	     sheet.getRow(strRow+(g1-0)).getCell(10).setCellValue(KPIDeviationData.get(g1)[3]);//UOM
     	     sheet.getRow(strRow+(g1)).getCell(13).setCellValue(KPIDeviationData.get(g1)[4]);//DATE
     	     sheet.getRow(strRow+(g1-0)).getCell(13).setCellValue(KPIDeviationData.get(g1)[4]);//DATE
     	     sheet.getRow(strRow+(g1)).getCell(14).setCellValue(KPIDeviationData.get(g1)[5]);//FREQUENCY
    	     sheet.getRow(strRow+(g1-0)).getCell(14).setCellValue(KPIDeviationData.get(g1)[5]);//FREQUENCY
    	     sheet.getRow(strRow+(g1)).getCell(15).setCellValue(KPIDeviationData.get(g1)[6]);//TARGET
    	     sheet.getRow(strRow+(g1-0)).getCell(15).setCellValue(KPIDeviationData.get(g1)[6]);//TARGET
    	     sheet.getRow(strRow+(g1)).getCell(16).setCellValue(KPIDeviationData.get(g1)[7]);//ACTUAL
    	     sheet.getRow(strRow+(g1-0)).getCell(16).setCellValue(KPIDeviationData.get(g1)[7]);//ACTUAL
     	   }
     	   }
    	
    	
    	
    	
    	
 	   
         sheet.protectSheet("admin");
         return wb;
   }
}