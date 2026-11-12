package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.utils.CommonFunctions;

public class PlannedJobObserExcelTemplate {

	//plnJobService plnJobService;

	public PlannedJobObserExcelTemplate(DBActionTemplate dbActionTemplate) {
		//plnJobService = new plnJobServiceImpl(dbActionTemplate);
	}

	public Workbook fillValues(Map<Integer, List<String[]>> plnJobData,
			String format, String path, String plnJobId, String imagePath)
			throws Exception {

		String excelFormat = null;
		String excelPath = null;
		excelPath = "/PlannedJobObservation.xlsx";
		System.out.println("Xl path........" + path + excelPath);
		InputStream inp = new FileInputStream(path + excelPath);

		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);

		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 15);
		header1Font.setBold(true);
		header1Font.setColor(IndexedColors.BLACK.getIndex());
		header1Font.setFontName(XLConditionalFormats.FONT_WEBDINGS);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(header1Font);

		List<String[]> plnJobExcelData = plnJobData.get(0);
		if (plnJobExcelData != null && plnJobExcelData.size() > 1) {
            String[] impshtexl = plnJobExcelData.get(1);
            CommonFunctions.debugMsg("impshtexl"+impshtexl.length);
			
			for (int i = 0; i < impshtexl.length; i++) {
				CommonFunctions.debugMsg(i+"  impshtexl["+i+"]"+impshtexl[i]);
			}
			sheet.getRow(4).getCell(2).setCellValue(impshtexl[0]);
			sheet.getRow(4).getCell(10).setCellValue(impshtexl[1]);
			sheet.getRow(4).getCell(15).setCellValue(impshtexl[2]);
			sheet.getRow(8).getCell(1).setCellValue(impshtexl[3]);
			sheet.getRow(7).getCell(10).setCellValue("a");//tick
			//sheet.getRow(7).getCell(9).setCellValue(impshtexl[4]);
			if(impshtexl[4].equals("I")){
				sheet.getRow(7).getCell(10).setCellStyle(style);
				sheet.getRow(7).getCell(10).setCellValue("a");
			}
			else if(impshtexl[4].equals("F")){
				sheet.getRow(7).getCell(12).setCellStyle(style);
				sheet.getRow(7).getCell(12).setCellValue("a");
			}
			//sheet.getRow(9).getCell(9).setCellValue(impshtexl[5]);
			if(impshtexl[5].equals("I")){
				sheet.getRow(9).getCell(10).setCellStyle(style);
				sheet.getRow(9).getCell(10).setCellValue("a");
			}
			else if(impshtexl[5].equals("N")){
				sheet.getRow(9).getCell(12).setCellStyle(style);
				sheet.getRow(9).getCell(12).setCellValue("a");
			}

			//sheet.getRow(11).getCell(1).setCellValue(impshtexl[6]);
			
			if(impshtexl[6].equals("PJRN0001")){
				sheet.getRow(11).getCell(1).setCellStyle(style);
				sheet.getRow(11).getCell(1).setCellValue("a");
			}else if(impshtexl[6].equals("PJRN0002")){
				sheet.getRow(11).getCell(6).setCellStyle(style);
				sheet.getRow(11).getCell(6).setCellValue("a");
			}else if(impshtexl[6].equals("PJRN0003")){
				sheet.getRow(10).getCell(10).setCellStyle(style);
				sheet.getRow(10).getCell(10).setCellValue("a");
			}else if(impshtexl[6].equals("PJRN0004")){
				sheet.getRow(10).getCell(12).setCellStyle(style);
				sheet.getRow(10).getCell(12).setCellValue("a");
			}else if(impshtexl[6].equals("PJRN0005")){
				sheet.getRow(11).getCell(10).setCellStyle(style);
				sheet.getRow(11).getCell(10).setCellValue("a");
			}
			//sheet.getRow(16).getCell(4).setCellValue(impshtexl[7]);
			if(impshtexl[7].equals("Y")){
				sheet.getRow(16).getCell(5).setCellStyle(style);
				sheet.getRow(16).getCell(5).setCellValue("a");
			}
			else if(impshtexl[7].equals("N")){
				sheet.getRow(16).getCell(7).setCellStyle(style);
				sheet.getRow(16).getCell(7).setCellValue("a");
			}
			
			//sheet.getRow(16).getCell(12).setCellValue(impshtexl[8]);
			if(impshtexl[8].equals("Y")){
				sheet.getRow(16).getCell(13).setCellStyle(style);
				sheet.getRow(16).getCell(13).setCellValue("a");
			}
			else if(impshtexl[8].equals("N")){
				sheet.getRow(16).getCell(15).setCellStyle(style);
				sheet.getRow(16).getCell(15).setCellValue("a");
			}
			
			//sheet.getRow(19).getCell(4).setCellValue(impshtexl[9]);
			if(impshtexl[9].equals("Y")){
				sheet.getRow(19).getCell(5).setCellStyle(style);
				sheet.getRow(19).getCell(5).setCellValue("a");
			}
			else if(impshtexl[9].equals("N")){
				sheet.getRow(19).getCell(7).setCellStyle(style);
				sheet.getRow(19).getCell(7).setCellValue("a");
			}
			
			//sheet.getRow(19).getCell(12).setCellValue(impshtexl[10]);
			if(impshtexl[10].equals("Y")){
				sheet.getRow(19).getCell(13).setCellStyle(style);
				sheet.getRow(19).getCell(13).setCellValue("a");
			}
			else if(impshtexl[10].equals("N")){
				sheet.getRow(19).getCell(15).setCellStyle(style);
				sheet.getRow(19).getCell(15).setCellValue("a");
			}
			
			sheet.getRow(22).getCell(2).setCellValue(impshtexl[11]);
			String [] practice=(impshtexl[12]).split(",");
			if(practice[0].contains("POW")||practice[1].contains("POW")||practice[2].contains("POW")||practice[3].contains("POW")){
				sheet.getRow(26).getCell(1).setCellStyle(style);
				sheet.getRow(26).getCell(1).setCellValue("a");
			} if(practice[0].contains("CKF")||practice[1].contains("CKF")||practice[2].contains("CKF")||practice[3].contains("CKF")){ 
				sheet.getRow(27).getCell(1).setCellStyle(style);
				sheet.getRow(27).getCell(1).setCellValue("a");
			} if(practice[0].contains("PPE")||practice[1].contains("PPE")||practice[2].contains("PPE")||practice[3].contains("PPE")){
				sheet.getRow(28).getCell(1).setCellStyle(style);
				sheet.getRow(28).getCell(1).setCellValue("a");
			} if(practice[0].contains("CKW")||practice[1].contains("CKW")||practice[2].contains("CKW")||practice[3].contains("CKW")){
				sheet.getRow(29).getCell(1).setCellStyle(style);
				sheet.getRow(29).getCell(1).setCellValue("a");
			}
			sheet.getRow(34).getCell(1).setCellValue(impshtexl[13]);
			
			//sheet.getRow(35).getCell(4).setCellValue(impshtexl[13]);
			if(impshtexl[14].equals("Y")){
				sheet.getRow(41).getCell(5).setCellStyle(style);
				sheet.getRow(41).getCell(5).setCellValue("a");
			}
			else if(impshtexl[14].equals("N")){
				sheet.getRow(41).getCell(7).setCellStyle(style);
				sheet.getRow(41).getCell(7).setCellValue("a");
			}
			
			//sheet.getRow(35).getCell(12).setCellValue(impshtexl[14]);
			if(impshtexl[15].equals("Y")){
				sheet.getRow(41).getCell(13).setCellStyle(style);
				sheet.getRow(41).getCell(13).setCellValue("a");
			}
			else if(impshtexl[15].equals("N")){
				sheet.getRow(41).getCell(15).setCellStyle(style);
				sheet.getRow(41).getCell(15).setCellValue("a");
			}
			
			sheet.getRow(44).getCell(1).setCellValue(impshtexl[16]);
			sheet.getRow(49).getCell(4).setCellValue(impshtexl[17]);
			sheet.getRow(49).getCell(10).setCellValue(impshtexl[18]);
			sheet.getRow(52).getCell(1).setCellValue(impshtexl[19]);
			sheet.getRow(57).getCell(8).setCellValue(impshtexl[20]);
			sheet.getRow(57).getCell(13).setCellValue(impshtexl[21]);
			excelFormat = impshtexl[0];
		}
		sheet.protectSheet("admin");
		return wb;
	}
}
