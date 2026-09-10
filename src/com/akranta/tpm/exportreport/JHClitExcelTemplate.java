package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
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
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.service.BAL_JhClitCalendarService;
import com.akranta.tpm.service.impl.BAL_JhClitCalendarServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class JHClitExcelTemplate {

	BAL_JhClitCalendarService jhncalendarService;

	public JHClitExcelTemplate(DBActionTemplate dbActionTemplate) {
		jhncalendarService = new BAL_JhClitCalendarServiceImpl(dbActionTemplate);
	}

	public Workbook fillValues(List<String[]> kaizenData, String format,
			String path, CommonFilter commonFilter, String imagePath)
			throws Exception {

		String excelPath = null;

		excelPath = "/CLIT.xlsx";
		InputStream inp = new FileInputStream(path + excelPath);
		Workbook wb = new XSSFWorkbook(inp);
		CommonFunctions.debugMsg( "  excel path " + excelPath + "  path " + path);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);
		final short color = IndexedColors.RED.getIndex();
		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 15);
		//header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		header1Font.setColor(IndexedColors.BLACK.getIndex());
		header1Font.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		
		
		CellStyle style = wb.createCellStyle();
//		style.setAlignment(CellStyle.ALIGN_JUSTIFY);
//		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		// style.setFillPattern(color);
//		style.setBorderRight(CellStyle.BORDER_THIN);
//		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header1Font);

		List<String[]> curExcel = kaizenData;
		int g;

		if (curExcel != null && curExcel.size() > 1) {
			for (int k = 2; k < curExcel.size(); k++) {
				int i = 1;
				String[] impshtexl = curExcel.get(k);
				List<ExcelInsertImage> imageType = new ArrayList<ExcelInsertImage>();
				

				sheet.getRow(2).getCell(3)
						.setCellValue(impshtexl[impshtexl.length - 1]);
				sheet.getRow(2).getCell(16)
						.setCellValue(commonFilter.getFromMonth());
				for (g = 2; g < 42; g++) {

					if (g == 4  ) {
						if (UIUtils
								.isValidKeyId(impshtexl[impshtexl.length - 2])) { // Image Tool key id(s)
							String keyid1 = impshtexl[impshtexl.length - 2];
							String[] keyid2 = keyid1.split(",");
							int imgCnt = 0;
							String values = impshtexl[g];
							String[] val =values.split(",");
							for (String tolId : keyid2) {

								if (imgCnt > 2)
									break;
								
								ExcelInsertImage xlImg = new ExcelInsertImage();
								
								List<GenTlToolsimg> kaizenImage = jhncalendarService
										.getCLTIImage(UIUtils.TPM_TEMPIMG_DIR,
												imagePath, tolId);

								if (kaizenImage.size() > 0) {
									GenTlToolsimg img = kaizenImage.get(0);
									//sheet.getRow(4).getCell(k + 2)
										//	.setCellStyle(style);

									String fileName = imagePath
											+ (img.getToimFilename())
													.replace(
															UIUtils.TPM_TEMPIMG_DIR,
															"");
									xlImg.col1 = 4 + imgCnt;
									xlImg.row1 = k + 2;
									xlImg.imageFileName = fileName;
									imageType.add(xlImg);
								}
								else{
									sheet.getRow(k + 2).getCell(4 + imgCnt).setCellValue(val[imgCnt]);
								}
								imgCnt++;
							}
							if (imageType.size() > 0)
								ExcelUtils.addImages(wb, sheet, imageType);
						}
						i++;
					} else if(g<7 || g>10)
					 {
						String val = impshtexl[g];
						int c =1;
						if(g> 4){
							c=3;
						}
						if ("0".equals(val))
							val = "";
						else if ("-1".equals(val))
							val = "m";
						
						sheet.getRow(k + 2).getCell(i + c).setCellValue(val);// Kaizen
																				// Serial
																				// Number...
						i++;
					}
				}
			}
		}
		return wb;
	}
}
