package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import com.akranta.tpm.utils.CommonMessage;
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
import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.NewSusaService;
import com.akranta.tpm.service.NewTrainingcalendarService;
import com.akranta.tpm.service.impl.NewSusaServiceImpl;
import com.akranta.tpm.service.impl.NewTrainingcalendarServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;

public class TrainingCalendarNEWITCExcelTemplate{
	NewTrainingcalendarService newTrainingcalendarService;
	public TrainingCalendarNEWITCExcelTemplate(DBActionTemplate dbActionTemplate) {
		newTrainingcalendarService = new NewTrainingcalendarServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> TrgCalDetailsData,List<String[]> TrgCalEmployeeData,String path,String keyid)throws Exception {
		// TODO Auto-generated method stub
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/NewTrainingCalendar.xlsx";
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
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);        
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
    
        if(TrgCalDetailsData !=null && TrgCalDetailsData.size()>0){
          for(g=0;g<TrgCalDetailsData.size();g++){
    	    sheet.getRow(2).getCell(2).setCellValue(TrgCalDetailsData.get(g)[0]);//DOCUMENT NO
   	        CommonMessage.debugMsg(" For Training No:::::"+TrgCalDetailsData.get(g)[0]);
   	        sheet.getRow(2).getCell(5).setCellValue(TrgCalDetailsData.get(g)[1]);//Date
	 	    CommonMessage.debugMsg(" For Date:::::"+TrgCalDetailsData.get(g)[1]);
   	        sheet.getRow(3).getCell(2).setCellValue(TrgCalDetailsData.get(g)[2]);//Functional Location
	        CommonMessage.debugMsg(" For Functional Location:::::"+TrgCalDetailsData.get(g)[2]);  
	        sheet.getRow(4).getCell(3).setCellValue(TrgCalDetailsData.get(g)[3]);//Type of Training-General
	        CommonMessage.debugMsg(" For General Training:::::"+TrgCalDetailsData.get(g)[3]); 
	        sheet.getRow(4).getCell(5).setCellValue(TrgCalDetailsData.get(g)[4]);//Type of Training-General
	        CommonMessage.debugMsg(" For Unique Position Training:::::"+TrgCalDetailsData.get(g)[4]);  
	   
	 	    sheet.getRow(5).getCell(2).setCellValue(TrgCalDetailsData.get(g)[5]);//Title/Topic
	        CommonMessage.debugMsg(" For Title:::::"+TrgCalDetailsData.get(g)[5]);
	        sheet.getRow(6).getCell(2).setCellValue(TrgCalDetailsData.get(g)[6]);//Duration
            CommonMessage.debugMsg(" For Duration:::::"+TrgCalDetailsData.get(g)[6]);
            sheet.getRow(6).getCell(5).setCellValue(TrgCalDetailsData.get(g)[7]);//Asessment Required
            CommonMessage.debugMsg(" For Asessment Required :::::"+TrgCalDetailsData.get(g)[7]);
            sheet.getRow(6).getCell(7).setCellValue(TrgCalDetailsData.get(g)[8]);//Mark Required
            CommonMessage.debugMsg(" For Mark Required:::::"+TrgCalDetailsData.get(g)[8]);
            sheet.getRow(7).getCell(2).setCellValue(TrgCalDetailsData.get(g)[9]);//Faculty
            CommonMessage.debugMsg(" For Faculty:::::"+TrgCalDetailsData.get(g)[9]);
            sheet.getRow(8).getCell(2).setCellValue(TrgCalDetailsData.get(g)[10]);//Completed By
            CommonMessage.debugMsg(" For Completed By:::::"+TrgCalDetailsData.get(g)[10]);
            sheet.getRow(8).getCell(5).setCellValue(TrgCalDetailsData.get(g)[11]);//Completed Date
            CommonMessage.debugMsg(" For Completed Date:::::"+TrgCalDetailsData.get(g)[11]);              
          }
        }
        
       int g1;
 	   int strRow=10;
 	   if(TrgCalEmployeeData !=null && TrgCalEmployeeData.size()>0){
 	   for(g1=0;g1<TrgCalEmployeeData.size();g1++){
 	     sheet.getRow(strRow+(g1)).getCell(1).setCellValue(TrgCalEmployeeData.get(g1)[0]);//EMPLOYEE
 	     sheet.getRow(strRow+(g1-0)).getCell(1).setCellValue(TrgCalEmployeeData.get(g1)[0]);//EMPLOYEE
 	     sheet.getRow(strRow+(g1)).getCell(2).setCellValue(TrgCalEmployeeData.get(g1)[1]);//DEPT
 	     sheet.getRow(strRow+(g1-0)).getCell(2).setCellValue(TrgCalEmployeeData.get(g1)[1]);//DEPT
 	     sheet.getRow(strRow+(g1)).getCell(3).setCellValue(TrgCalEmployeeData.get(g1)[2]);//PRESENT/ABSENT
 	     sheet.getRow(strRow+(g1-0)).getCell(3).setCellValue(TrgCalEmployeeData.get(g1)[2]);//PRESENT/ABSENT
 	     sheet.getRow(strRow+(g1)).getCell(4).setCellValue(TrgCalEmployeeData.get(g1)[3]);//DATE
 	     sheet.getRow(strRow+(g1-0)).getCell(4).setCellValue(TrgCalEmployeeData.get(g1)[3]);//DATE
 	     sheet.getRow(strRow+(g1)).getCell(5).setCellValue(TrgCalEmployeeData.get(g1)[4]);//SCORE
 	     sheet.getRow(strRow+(g1-0)).getCell(5).setCellValue(TrgCalEmployeeData.get(g1)[4]);//SCORE
 	     sheet.getRow(strRow+(g1)).getCell(7).setCellValue(TrgCalEmployeeData.get(g1)[5]);//RESULT
	     sheet.getRow(strRow+(g1-0)).getCell(7).setCellValue(TrgCalEmployeeData.get(g1)[5]);//RESULT
	    /* sheet.getRow(strRow+(g1)).getCell(8).setCellValue(TrgCalEmployeeData.get(g1)[6]);//REMARKS
	     sheet.getRow(strRow+(g1-0)).getCell(8).setCellValue(TrgCalEmployeeData.get(g1)[6]);//REMARKS
*/ 		  }    
 	   }
 	   
         sheet.protectSheet("admin");
         return wb;
   }
}