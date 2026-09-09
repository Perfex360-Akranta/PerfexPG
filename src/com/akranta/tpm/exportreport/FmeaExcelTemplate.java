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
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class FmeaExcelTemplate {

	public FmeaExcelTemplate(DBActionTemplate dbActionTemplate) {
		//plnJobService = new plnJobServiceImpl(dbActionTemplate);
	}

	public Workbook fillValues(Map<Integer, List<String[]>> fmeaData,
			String format, String path, String keyId, String type, List<String[]> actionpln)
			throws Exception {

		String excelFormat = null;
		String excelPath = null;
		if(type.equals("design")){
			excelPath = "/DesignFMEA.xlsx";
		}
		else if(type.equals("equipment")){
			excelPath = "/EquipmentFMEA.xlsx";
		}
		else if(type.equals("process")){
			excelPath = "/ProcessFMEA.xlsx";
		}
		CommonMessage.debugMsg("type......." + type);
		CommonMessage.debugMsg("Xl path........" + path + excelPath);
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
		String[] impshtexl = null;
		String[] actionlist = null;
		List<String[]> fmeaExcelData = fmeaData.get(0);
		if (fmeaExcelData != null && fmeaExcelData.size() > 1) {
			impshtexl = fmeaExcelData.get(1);

			// Fill header information based on type
			if(type.equals("design")){
				sheet.getRow(1).getCell(1).setCellValue(impshtexl[4]);
				sheet.getRow(1).getCell(13).setCellValue(impshtexl[3]);
				sheet.getRow(2).getCell(1).setCellValue(impshtexl[5]);
				
				sheet.getRow(3).getCell(1).setCellValue(impshtexl[6]);
				sheet.getRow(3).getCell(3).setCellValue(impshtexl[8]);
				sheet.getRow(3).getCell(13).setCellValue(impshtexl[7]);
				
				sheet.getRow(4).getCell(13).setCellValue(impshtexl[2]);
			}
			else if(type.equals("equipment")){
				sheet.getRow(1).getCell(1).setCellValue(impshtexl[4]);
				sheet.getRow(1).getCell(15).setCellValue(impshtexl[3]);
				sheet.getRow(2).getCell(1).setCellValue(impshtexl[5]);
				
				sheet.getRow(3).getCell(1).setCellValue(impshtexl[6]);
				sheet.getRow(3).getCell(5).setCellValue(impshtexl[8]);
				sheet.getRow(3).getCell(15).setCellValue(impshtexl[7]);
				
				sheet.getRow(4).getCell(15).setCellValue(impshtexl[2]);
			}
			else if(type.equals("process")){
				sheet.getRow(1).getCell(1).setCellValue(impshtexl[4]);
				sheet.getRow(1).getCell(14).setCellValue(impshtexl[3]);
				sheet.getRow(2).getCell(1).setCellValue(impshtexl[5]);
				
				sheet.getRow(3).getCell(1).setCellValue(impshtexl[6]);
				sheet.getRow(3).getCell(4).setCellValue(impshtexl[8]);
				sheet.getRow(3).getCell(14).setCellValue(impshtexl[7]);
				
				sheet.getRow(4).getCell(14).setCellValue(impshtexl[2]);
			}
			
			// Fill FMEA detail rows
			for (int i = 1; i < fmeaExcelData.size(); i++) {
				impshtexl = fmeaExcelData.get(i);
				int row = i + 7;
				
				if(type.equals("design")){
					sheet.getRow(row).getCell(1).setCellValue(impshtexl[10]);
					sheet.getRow(row).getCell(2).setCellValue(impshtexl[13]);
					sheet.getRow(row).getCell(3).setCellValue(impshtexl[14]);
					sheet.getRow(row).getCell(4).setCellValue(impshtexl[15]);
					sheet.getRow(row).getCell(5).setCellValue(impshtexl[16]);
					sheet.getRow(row).getCell(6).setCellValue(impshtexl[17]);
					sheet.getRow(row).getCell(7).setCellValue(impshtexl[18]);
					sheet.getRow(row).getCell(8).setCellValue(impshtexl[19]);
					sheet.getRow(row).getCell(9).setCellValue(impshtexl[20]);
					sheet.getRow(row).getCell(10).setCellValue(impshtexl[21]);
					sheet.getRow(row).getCell(11).setCellValue(impshtexl[22]);
					sheet.getRow(row).getCell(12).setCellValue(impshtexl[23]);
					sheet.getRow(row).getCell(13).setCellValue(impshtexl[24]);
					sheet.getRow(row).getCell(14).setCellValue(impshtexl[25]);
					sheet.getRow(row).getCell(15).setCellValue(impshtexl[26]);
					sheet.getRow(row).getCell(16).setCellValue(impshtexl[27]);
					sheet.getRow(row).getCell(17).setCellValue(impshtexl[28]);
				}
				else if(type.equals("equipment")){
					CommonMessage.debugMsg("Equipment FMEA Detail Row " + row);
					CommonMessage.debugMsg("impshtexl[10] (Component): " + impshtexl[10]);
					CommonMessage.debugMsg("impshtexl[11] (Function): " + impshtexl[11]);
					CommonMessage.debugMsg("impshtexl[12] (Functional Failure): " + impshtexl[12]);
					CommonMessage.debugMsg("impshtexl[21] (RPN): " + impshtexl[21]);
					CommonMessage.debugMsg("impshtexl[22] (ACTION1): " + impshtexl[22]);
					CommonMessage.debugMsg("impshtexl[23] (ACTION2/Resp): " + impshtexl[23]);
					CommonMessage.debugMsg("impshtexl[24] (ACTION3/TargetDate): " + impshtexl[24]);
					CommonMessage.debugMsg("impshtexl[25] (STATUS): " + impshtexl[25]);
					
					sheet.getRow(row).getCell(1).setCellValue(impshtexl[10]);
					sheet.getRow(row).getCell(2).setCellValue(impshtexl[11]);
					sheet.getRow(row).getCell(3).setCellValue(impshtexl[12]);
					sheet.getRow(row).getCell(4).setCellValue(impshtexl[13]);
					sheet.getRow(row).getCell(5).setCellValue(impshtexl[14]);
					sheet.getRow(row).getCell(6).setCellValue(impshtexl[15]);
					sheet.getRow(row).getCell(7).setCellValue(impshtexl[16]);
					sheet.getRow(row).getCell(8).setCellValue(impshtexl[17]);
					sheet.getRow(row).getCell(9).setCellValue(impshtexl[18]);
					sheet.getRow(row).getCell(10).setCellValue(impshtexl[19]);
					sheet.getRow(row).getCell(11).setCellValue(impshtexl[20]);
					
					// Action plan data from the MAIN query (inline with FMEA details)
					// These columns come from the LEFT JOIN with action plan tables
					sheet.getRow(row).getCell(12).setCellValue(impshtexl[21]); // ACTION1 - Recommended Action
					sheet.getRow(row).getCell(13).setCellValue(impshtexl[22]); // ACTION2 - Resp
					sheet.getRow(row).getCell(14).setCellValue(impshtexl[23]); // ACTION3 - Target Date
					sheet.getRow(row).getCell(15).setCellValue(impshtexl[24]); // STATUS - Actions Taken
					
					// Re-evaluation columns
					sheet.getRow(row).getCell(16).setCellValue(impshtexl[25]); // RESEVERITY
					sheet.getRow(row).getCell(17).setCellValue(impshtexl[26]); // REOCCURRENCE
					sheet.getRow(row).getCell(18).setCellValue(impshtexl[27]); // REDETECTION
					sheet.getRow(row).getCell(19).setCellValue(impshtexl[28]); // RERPN
				}
				else if(type.equals("process")){
					sheet.getRow(row).getCell(1).setCellValue(impshtexl[10]);
					sheet.getRow(row).getCell(2).setCellValue(impshtexl[11]);
					sheet.getRow(row).getCell(3).setCellValue(impshtexl[13]);
					sheet.getRow(row).getCell(4).setCellValue(impshtexl[14]);
					sheet.getRow(row).getCell(5).setCellValue(impshtexl[15]);
					sheet.getRow(row).getCell(6).setCellValue(impshtexl[16]);
					sheet.getRow(row).getCell(7).setCellValue(impshtexl[17]);
					sheet.getRow(row).getCell(8).setCellValue(impshtexl[18]);
					sheet.getRow(row).getCell(9).setCellValue(impshtexl[19]);
					sheet.getRow(row).getCell(10).setCellValue(impshtexl[20]);
					sheet.getRow(row).getCell(11).setCellValue(impshtexl[21]);
					sheet.getRow(row).getCell(12).setCellValue(impshtexl[22]);
					sheet.getRow(row).getCell(13).setCellValue(impshtexl[23]);
					sheet.getRow(row).getCell(14).setCellValue(impshtexl[24]);
					sheet.getRow(row).getCell(15).setCellValue(impshtexl[25]);
					sheet.getRow(row).getCell(16).setCellValue(impshtexl[26]);
					sheet.getRow(row).getCell(17).setCellValue(impshtexl[27]);
					sheet.getRow(row).getCell(18).setCellValue(impshtexl[28]);
				}
			}
			
			// NOTE: The action plan data is already included in the main query result
			// The separate actionpln parameter appears to be redundant or used differently
			// Based on the SQL query, action plan data comes with each FMEA detail row
			
			// This section might be for ADDITIONAL action plans not yet filled
			// OR it might be legacy code that should be removed
			if(actionpln != null && actionpln.size() != 0) {
				CommonMessage.debugMsg("WARNING: actionpln parameter has " + actionpln.size() + " items");
				CommonMessage.debugMsg("This might be redundant since action plans are already in fmeaExcelData");
				
				for (int i = 0; i < actionpln.size(); i++) {
					actionlist = actionpln.get(i);
					CommonMessage.debugMsg("ActionPlan row " + i + " has " + actionlist.length + " columns");
					for(int j = 0; j < actionlist.length && j < 10; j++) {
						CommonMessage.debugMsg("  actionlist[" + j + "]: " + actionlist[j]);
					}
				}
			}
			
			excelFormat = impshtexl[0];
		}
		sheet.protectSheet("admin");
		return wb;
	}
}
