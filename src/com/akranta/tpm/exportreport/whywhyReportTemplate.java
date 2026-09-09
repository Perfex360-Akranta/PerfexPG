
package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.WhywhyReportService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.WhywhyReportServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class whywhyReportTemplate {

	WhywhyReportService whywhyService;
	private String[] impshtexl;

	public whywhyReportTemplate(DBActionTemplate dbActionTemplate) {
		whywhyService = new WhywhyReportServiceImpl(dbActionTemplate);
	}

	private static final short ROW_INDEX_CIRCLE = 2;
	private static final short ROW_INDEX_COST = 29;
	private static int ROW_INDEX_DEPTIMPNO = 4;
	private static final short ROW_INDEX_EQP = 5;
	private static final short ROW_INDEX_THEMEIDEA = 6;
	private static final short ROW_INDEX_TGT = 9;
	private static final short ROW_INDEX_PRGSRT = 11;
	private static final short ROW_INDEX_YY = 21;
	private static final short ROW_INDEX_RSTIMG = 31;
	private static final short ROW_INDEX_IMG = 16;
	private static final short INCERMENTBY_YY = 2;
	private static final short WHYWHY_START_POINT = 21;
	private static final short GRAPH_START_COL = 31;
	private static final short GRAPH_INCREMENT = 1;
	private static final String PASSDATETIME = "01-Jan-1801 00:00:00";

	public Workbook fillValues(Map<Integer, List<String[]>> whyData, String format, String path, String kaizId,
			String imagePath) throws Exception {

		String excelFormat = null;
		String excelPath = null;
		List<String[]> graphType = whyData.get(0);
		CommonMessage.debugMsg(
				"graphType.size()graphType.size() " + graphType + "excelFormatgraphType.size().. " + graphType.size());
		excelPath = "/why-whyAnalysis.xlsx";

		CommonMessage.debugMsg("excelPath.." + excelPath);
		InputStream inp = new FileInputStream(path + excelPath);
		CommonMessage.debugMsg("Excel Template to be generate...." + whyData.size() + "...");

		Workbook wb = new XSSFWorkbook(inp);
		/*
		 * inp.close(); Sheet sheet = wb.getSheetAt(0);
		 * 
		 * 
		 * Font header1Font = wb.createFont();
		 * header1Font.setFontHeightInPoints((short)12);
		 * header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		 * header1Font.setColor(IndexedColors.BLACK.getIndex());
		 * header1Font.setFontName(XLConditionalFormats.FONT_WEBDINGS);
		 * 
		 * CellStyle style = wb.createCellStyle();
		 * style.setAlignment(CellStyle.ALIGN_CENTER);
		 * style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		 * 
		 * //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		 * style.setBorderRight(CellStyle.BORDER_THIN);
		 * style.setBorderBottom(CellStyle.BORDER_THIN); style.setFont(header1Font);
		 * 
		 * String [] whyexl = graphType.get(0);
		 * 
		 * 
		 * 
		 * CommonMessage.debugMsg("Excel Template to be generate123...."+whyData.size(
		 * )+"...");
		 * 
		 * List<String[]> curExcel = whyData.get(0); if(curExcel!=null &&
		 * curExcel.size() > 1 ) {
		 * CommonMessage.debugMsg("Inside the fill values function3 ................"
		 * ); String [] impshtexl = curExcel.get(1); for(int i=0;i<impshtexl.length;i++)
		 * { CommonMessage.debugMsg(i+ "---"+ impshtexl[i]);
		 * 
		 * }
		 */

		/*
		 * 
		 * sheet.getRow(5).getCell(8).setCellValue(impshtexl[5]);//equipment no
		 * sheet.getRow(5).getCell(2).setCellValue(impshtexl[4]);//equ.name
		 * 
		 * sheet.getRow(2).getCell(10).setCellValue(impshtexl[10]);//areacode
		 * sheet.getRow(3).getCell(10).setCellValue(impshtexl[9]);//areaname
		 * sheet.getRow(6).getCell(4).setCellValue(impshtexl[11]);//phenomena--
		 * sheet.getRow(7).getCell(4).setCellValue(impshtexl[12]);//finalaction
		 * sheet.getRow(4).getCell(7).setCellValue(impshtexl[2]);//bddatetime
		 * sheet.getRow(4).getCell(9).setCellValue(impshtexl[3]);//bddatetime
		 * sheet.getRow(6).getCell(10).setCellValue(impshtexl[13]);//spares replaced
		 * sheet.getRow(4).getCell(2).setCellValue(impshtexl[8]);//spares replaced
		 * sheet.getRow(15).getCell(8).setCellValue(impshtexl[15]);//Counter Measure
		 * sheet.getRow(11).getCell(9).setCellValue(impshtexl[31]);//Efectiveness
		 * sheet.getRow(14).getCell(3).setCellValue(impshtexl[33]);//RootCause
		 * sheet.getRow(12).getCell(9).setCellValue(impshtexl[32]);//Previous date
		 * 
		 * 
		 * if(impshtexl[24].equals("POOR BASIC CONDITION")) {
		 * 
		 * sheet.getRow(16).getCell(4).setCellStyle(style);
		 * sheet.getRow(16).getCell(4).setCellValue("a");//tick } else
		 * if(impshtexl[24].equals("POOR OPERATING CONDITION")){
		 * 
		 * sheet.getRow(17).getCell(4).setCellValue("a");//tick } else
		 * if(impshtexl[24].equals("DETERIORATION")){
		 * 
		 * sheet.getRow(18).getCell(4).setCellStyle(style);
		 * sheet.getRow(18).getCell(4).setCellValue("a");//tick } else
		 * if(impshtexl[24].equals("WEAK DESIGN")){
		 * 
		 * sheet.getRow(19).getCell(4).setCellStyle(style);
		 * sheet.getRow(19).getCell(4).setCellValue("a");//tick } else
		 * if(impshtexl[24].equals("POOR SKILL")){
		 * 
		 * sheet.getRow(20).getCell(4).setCellStyle(style);
		 * sheet.getRow(20).getCell(4).setCellValue("a");//tick }
		 * 
		 * 
		 * 
		 * 
		 * int rowadd=9; int whywhyStartRow = 9;
		 * 
		 * for(int j=1 ;j< graphType.size();j++)
		 * 
		 * { impshtexl = graphType.get(j);
		 * 
		 * if(j>=5) sheet.createRow( rowadd+j);
		 * 
		 * sheet.getRow(whywhyStartRow +
		 * (j*1)-1).getCell(2).setCellValue(impshtexl[21]); sheet.getRow(whywhyStartRow
		 * + (j*1)-1).getCell(5).setCellValue(impshtexl[22]); }
		 * 
		 * 
		 * }
		 * 
		 * List<String[]> curExcel1 = whyData.get(1); if(curExcel1!=null &&
		 * curExcel1.size() > 1 ) {
		 * 
		 * String [] impshtexl0 = curExcel1.get(0);
		 * 
		 * String [] impshtexl = curExcel1.get(1);
		 * 
		 * 
		 * if(impshtexl[0].contains("KZN")){
		 * sheet.getRow(16).getCell(6).setCellValue(impshtexl[1]); } else
		 * if(impshtexl[0].contains("OPL")){
		 * sheet.getRow(17).getCell(6).setCellValue(impshtexl[1]); } else
		 * if(impshtexl[0].contains("PMC")){
		 * sheet.getRow(18).getCell(6).setCellValue(impshtexl[1]); } else
		 * if(impshtexl[0].contains("JHN")){
		 * sheet.getRow(19).getCell(6).setCellValue(impshtexl[1]); }
		 * 
		 * else if(impshtexl[0].contains("ISHDPOSSIBLE")){
		 * sheet.getRow(20).getCell(6).setCellValue(impshtexl[1]); } }
		 */

		return wb;

	}

	/*
	 * public Workbook fillValuesExlView(Map<Integer, List<String[]>> whyData,
	 * String format, String path, String rowId, String imagePath) throws Exception
	 * { // TODO Auto-generated method stub String excelFormat = null; String
	 * excelPath = null; int startColIndex=0; int endColIndex=10; int
	 * startRowIndex=0; int endRowIndex=22; int sheetNo=0; List<String[]>
	 * whywhyDetailsData = whyData.get(sheetNo) ; List<String[]>
	 * whyCounterMeasureData = whyData.get(1) ;
	 * CommonMessage.debugMsg("whywhyDetailsData.size() "+whywhyDetailsData.size()
	 * ); CommonMessage.debugMsg("whyCounterMeasureData.size() "
	 * +whyCounterMeasureData.size()); excelPath = "/WhyWhyAnalysis.xlsx";
	 * CommonMessage.debugMsg("excelPath.."+excelPath); InputStream inp = new
	 * FileInputStream(path+excelPath); Workbook wb = new XSSFWorkbook(inp); Sheet
	 * sheet = wb.getSheetAt(0); String[] impshtexl=null; String[]
	 * impshtexlcounter=null; List<ExcelInsertImage> imageType = new
	 * ArrayList<ExcelInsertImage>(); if (whywhyDetailsData != null &&
	 * whywhyDetailsData.size() > 1) { impshtexl = whywhyDetailsData.get(1);
	 * 
	 * for (int i = 0; i < impshtexl.length; i++) {
	 * CommonMessage.debugMsg("impshtexl Inside Excel Template ["+i+"]"+impshtexl[i]); }
	 * 
	 * int row=0; sheet.getRow(row).getCell(7).setCellValue(impshtexl[0]);//keyid
	 * row+=1; sheet.getRow(row).getCell(1).setCellValue(impshtexl[6]);//area
	 * sheet.getRow(row).getCell(4).setCellValue(impshtexl[1]);//equipment
	 * sheet.getRow(row).getCell(7).setCellValue(impshtexl[2]);//date
	 * 
	 * row+=1; sheet.getRow(row).getCell(3).setCellValue(impshtexl[7]);//problem
	 * sheet.getRow(row).getCell(7).setCellValue(impshtexl[11]);//Reported Date
	 * 
	 * row+=1; sheet.getRow(row).getCell(3).setCellValue(impshtexl[5]);//immediate
	 * action sheet.getRow(row).getCell(7).setCellValue(impshtexl[14]);//Spares Used
	 * 
	 * row+=1; sheet.getRow(row).getCell(3).setCellValue(impshtexl[9]);//pbm
	 * attended by sheet.getRow(row).getCell(8).setCellValue(impshtexl[8]);//time
	 * spent row+=1;
	 * 
	 * CellStyle oldstyle = wb.getCellStyleAt((short) row); CellStyle style =
	 * wb.createCellStyle(); style.cloneStyleFrom(oldstyle); for (int i = 1; i <
	 * whywhyDetailsData.size(); i++) { impshtexl = whywhyDetailsData.get(i);
	 * row+=1;
	 * CommonMessage.debugMsg(" row Inside Excel Template :: 1234 :: ["+i+"]"+row);
	 * sheet.getRow(row).getCell(1).setCellValue(impshtexl[20]);//Ans }
	 * 
	 * List<XLConditionalFormats> condFormats=getConditionalFormat(imagePath,10,11);
	 * String symbol = null; String imgPath = null; XLConditionalFormats condFormat
	 * = null;
	 * 
	 * //excelUtils.setCondFormats(condFormats);
	 * 
	 * row=13; sheet.getRow(row).getCell(4).setCellValue(impshtexl[12]);//if there
	 * else to be checked
	 * sheet.getRow(row).getCell(8).setCellValue(impshtexl[27]);//Root Cause
	 * row=row+1; CommonMessage.debugMsg(" Themes :: System :: "); if (whywhyDetailsData
	 * != null && whyCounterMeasureData.size() > 1) {
	 * 
	 * for (int i = 1; i < whyCounterMeasureData.size(); i++) { impshtexlcounter =
	 * whyCounterMeasureData.get(i);
	 * 
	 * row+=1;
	 * 
	 * CommonMessage.debugMsg("impshtexl[1].."+impshtexlcounter[1]);
	 * CommonMessage.debugMsg("impshtexl[3].."+impshtexlcounter[3]);
	 * CommonMessage.debugMsg("impshtexl[4].."+impshtexlcounter[4]);
	 * sheet.getRow(row).getCell(3).setCellValue(impshtexlcounter[1]);//Id
	 * sheet.getRow(row).getCell(4).setCellValue(impshtexlcounter[3]);//
	 * Responsibility
	 * sheet.getRow(row).getCell(5).setCellValue(impshtexlcounter[7]);//Description
	 * sheet.getRow(row).getCell(8).setCellValue(impshtexlcounter[4]);//Date
	 * sheet.getRow(row).getCell(9).setCellValue(impshtexlcounter[6]);//Status
	 * 
	 * if( condFormats != null ) {
	 * //CommonMessage.debugMsg("impshtexl[6].."+impshtexlcounter[6]); condFormat
	 * = getSymbolStr(11 , impshtexlcounter[6],condFormats);
	 * //CommonMessage.debugMsg( " condFormat " + condFormat.getCondValue() ); if(
	 * condFormat != null){ symbol = condFormat.getSymbolStr(); imgPath =
	 * condFormat.getImgPathName(); }
	 * 
	 * if(imgPath != null){ CommonMessage.debugMsg( " imgPath " + imgPath );
	 * ExcelUtils.addImage(wb, sheet, imgPath, row, 11); symbol = null; } }
	 * 
	 * } } row+=2; sheet.getRow(row).getCell(3).setCellValue(impshtexl[10]);//why
	 * why done by excelFormat = impshtexl[0]; } sheet.setPrintGridlines(false);
	 * sheet.setHorizontallyCenter(true);
	 * //sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
	 * sheet.getPrintSetup().setFitHeight((short)1);
	 * sheet.getPrintSetup().setFitWidth((short)1); sheet.setAutobreaks(true);
	 * sheet.setFitToPage(true); PrintSetup printSetup = sheet.getPrintSetup();
	 * printSetup.setLandscape(true);
	 * 
	 * wb.setPrintArea(sheetNo,startColIndex,endColIndex,startRowIndex,endRowIndex);
	 * 
	 * sheet.protectSheet("admin");
	 * 
	 * return wb; }
	 */
	
	
	/*
	 * public Workbook fillValuesExlView(Map<Integer, List<String[]>> whyData,
	 * String format, String path, String rowId, String imagePath) throws Exception
	 * {
	 * 
	 * String excelFormat = null; String excelPath = null; int startColIndex=0; int
	 * endColIndex=10; int startRowIndex=0; int endRowIndex=22; int sheetNo=0;
	 * 
	 * List<String[]> whywhyDetailsData = whyData.get(sheetNo); List<String[]>
	 * whyCounterMeasureData = whyData.get(1);
	 * 
	 * CommonMessage.debugMsg("whywhyDetailsData.size() "+whywhyDetailsData.size());
	 * CommonMessage.debugMsg("whyCounterMeasureData.size() "+whyCounterMeasureData.
	 * size());
	 * 
	 * excelPath = "/WhyWhyAnalysis.xlsx";
	 * CommonMessage.debugMsg("excelPath.."+excelPath);
	 * 
	 * InputStream inp = new FileInputStream(path+excelPath); Workbook wb = new
	 * XSSFWorkbook(inp); Sheet sheet = wb.getSheetAt(0);
	 * 
	 * String[] impshtexl=null; String[] impshtexlcounter=null;
	 * List<ExcelInsertImage> imageType = new ArrayList<ExcelInsertImage>();
	 * 
	 * if (whywhyDetailsData != null && whywhyDetailsData.size() > 1) { impshtexl =
	 * whywhyDetailsData.get(1);
	 * 
	 * // Debug print all values for (int i = 0; i < impshtexl.length; i++) {
	 * CommonMessage.debugMsg("impshtexl Inside Excel Template ["+i+"]"+impshtexl[i]
	 * ); }
	 * 
	 * int row=0; sheet.getRow(row).getCell(7).setCellValue(impshtexl[0]);//keyid
	 * 
	 * row+=1; sheet.getRow(row).getCell(1).setCellValue(impshtexl[6]);//area
	 * sheet.getRow(row).getCell(4).setCellValue(impshtexl[1]);//equipment - THIS IS
	 * THE FIX! sheet.getRow(row).getCell(7).setCellValue(impshtexl[2]);//date
	 * 
	 * row+=1; sheet.getRow(row).getCell(3).setCellValue(impshtexl[7]);//problem
	 * sheet.getRow(row).getCell(7).setCellValue(impshtexl[11]);//Reported Date
	 * 
	 * row+=1; sheet.getRow(row).getCell(3).setCellValue(impshtexl[5]);//immediate
	 * action // FIX: Changed from impshtexl[14] to impshtexl[13] for spare parts
	 * sheet.getRow(row).getCell(7).setCellValue(impshtexl[13]);//Spares Used -
	 * CORRECTED INDEX!
	 * 
	 * row+=1; sheet.getRow(row).getCell(3).setCellValue(impshtexl[9]);//pbm
	 * attended by sheet.getRow(row).getCell(8).setCellValue(impshtexl[8]);//time
	 * spent row+=1;
	 * 
	 * CellStyle oldstyle = wb.getCellStyleAt((short) row); CellStyle style =
	 * wb.createCellStyle(); style.cloneStyleFrom(oldstyle);
	 * 
	 * // Loop through why-why analysis data for (int i = 1; i <
	 * whywhyDetailsData.size(); i++) { impshtexl = whywhyDetailsData.get(i);
	 * row+=1;
	 * CommonMessage.debugMsg(" row Inside Excel Template :: 1234 :: ["+i+"]"+row);
	 * sheet.getRow(row).getCell(1).setCellValue(impshtexl[20]);//Ans }
	 * 
	 * List<XLConditionalFormats> condFormats=getConditionalFormat(imagePath,10,11);
	 * String symbol = null; String imgPath = null; XLConditionalFormats condFormat
	 * = null;
	 * 
	 * row=13; sheet.getRow(row).getCell(4).setCellValue(impshtexl[12]);//if there
	 * else to be checked
	 * sheet.getRow(row).getCell(8).setCellValue(impshtexl[27]);//Root Cause
	 * row=row+1;
	 * 
	 * CommonMessage.debugMsg(" Themes :: System :: ");
	 * 
	 * if (whywhyDetailsData != null && whyCounterMeasureData.size() > 1) { for (int
	 * i = 1; i < whyCounterMeasureData.size(); i++) { impshtexlcounter =
	 * whyCounterMeasureData.get(i); row+=1;
	 * 
	 * sheet.getRow(row).getCell(3).setCellValue(impshtexlcounter[1]);//Id
	 * sheet.getRow(row).getCell(4).setCellValue(impshtexlcounter[3]);//
	 * Responsibility
	 * sheet.getRow(row).getCell(5).setCellValue(impshtexlcounter[7]);//Description
	 * sheet.getRow(row).getCell(8).setCellValue(impshtexlcounter[4]);//Date
	 * sheet.getRow(row).getCell(9).setCellValue(impshtexlcounter[6]);//Status
	 * 
	 * if( condFormats != null ) { condFormat = getSymbolStr(11 ,
	 * impshtexlcounter[6],condFormats); if( condFormat != null){ symbol =
	 * condFormat.getSymbolStr(); imgPath = condFormat.getImgPathName(); }
	 * 
	 * if(imgPath != null){ CommonMessage.debugMsg( " imgPath " + imgPath );
	 * ExcelUtils.addImage(wb, sheet, imgPath, row, 11); symbol = null; } } } }
	 * 
	 * row+=2; sheet.getRow(row).getCell(3).setCellValue(impshtexl[10]);//why why
	 * done by excelFormat = impshtexl[0]; }
	 * 
	 * sheet.setPrintGridlines(false); sheet.setHorizontallyCenter(true);
	 * sheet.getPrintSetup().setFitHeight((short)1);
	 * sheet.getPrintSetup().setFitWidth((short)1); sheet.setAutobreaks(true);
	 * sheet.setFitToPage(true); PrintSetup printSetup = sheet.getPrintSetup();
	 * printSetup.setLandscape(true);
	 * 
	 * wb.setPrintArea(sheetNo,startColIndex,endColIndex,startRowIndex,endRowIndex);
	 * sheet.protectSheet("admin");
	 * 
	 * return wb; }
	 */
	
	public Workbook fillValuesExlView(Map<Integer, List<String[]>> whyData,
            String format, String path, String rowId, String imagePath) throws Exception {

        String excelFormat = null;
        String excelPath = null;
        int startColIndex = 0;
        int endColIndex = 10;
        int startRowIndex = 0;
        int endRowIndex = 25;
        int sheetNo = 0;

        List<String[]> whywhyDetailsData = whyData.get(sheetNo);
        List<String[]> whyCounterMeasureData = whyData.get(1);

        CommonFunctions.debugMsg("whywhyDetailsData.size() " + whywhyDetailsData.size());
        CommonFunctions.debugMsg("whyCounterMeasureData.size() " + whyCounterMeasureData.size());

        excelPath = "/WhyWhyAnalysis.xlsx";
        CommonFunctions.debugMsg("excelPath.." + excelPath);

        InputStream inp = new FileInputStream(path + excelPath);
        Workbook wb = new XSSFWorkbook(inp);
        Sheet sheet = wb.getSheetAt(0);
        sheet.setZoom(100);

        String[] impshtexl = null;
        String[] impshtexlcounter = null;

        if (whywhyDetailsData != null && whywhyDetailsData.size() > 1) {
            impshtexl = whywhyDetailsData.get(1);
            CommonMessage.debugMsg("DEBUG impshtexl total length = " + impshtexl.length);

            for (int i = 0; i < impshtexl.length; i++) {
                CommonMessage.debugMsg("impshtexl Inside Excel Template [" + i + "]" + impshtexl[i]);
            }

            int row = 0;
            sheet.getRow(row).getCell(7).setCellValue(impshtexl[0]);

            row += 1;
            sheet.getRow(row).getCell(1).setCellValue(impshtexl[6]);
            sheet.getRow(row).getCell(4).setCellValue(impshtexl[1]);
            sheet.getRow(row).getCell(7).setCellValue(impshtexl[2]);

            row += 1;
            sheet.getRow(row).getCell(3).setCellValue(impshtexl[7]);
            sheet.getRow(row).getCell(7).setCellValue(impshtexl[11]);

            row += 1;
            sheet.getRow(row).getCell(3).setCellValue(impshtexl[5]);
            sheet.getRow(row).getCell(7).setCellValue(impshtexl[13]);

            row += 1;
            sheet.getRow(row).getCell(3).setCellValue(impshtexl[9]);
            sheet.getRow(row).getCell(8).setCellValue(impshtexl[8]);
            
            row += 1;
            
            sheet.getRow(row).getCell(3).setCellValue(impshtexl[57]);
            sheet.getRow(row).getCell(7).setCellValue(impshtexl[58]);

            row += 1;
            
            int templateCheckedRow = 14;
            int templateWhyCount = 7;
            int dataWhyCount = whywhyDetailsData.size() - 1;
            int extraWhyRows = 0;

            if (dataWhyCount > templateWhyCount) {
                extraWhyRows = dataWhyCount - templateWhyCount;
                CommonMessage.debugMsg("Inserting " + extraWhyRows + " extra row(s) for " + dataWhyCount + " whys");

                sheet.shiftRows(templateCheckedRow, sheet.getLastRowNum(), extraWhyRows);

                XSSFDrawing drawingWhy = ((XSSFSheet) sheet).getDrawingPatriarch();
                if (drawingWhy != null) {
                    for (XSSFShape shape : drawingWhy.getShapes()) {
                        if (shape instanceof XSSFPicture) {
                            XSSFClientAnchor anchor = (XSSFClientAnchor) ((XSSFPicture) shape).getAnchor();
                            if (anchor != null && anchor.getRow1() >= templateCheckedRow) {
                                anchor.setRow1(anchor.getRow1() + extraWhyRows);
                                anchor.setRow2(anchor.getRow2() + extraWhyRows);
                            }
                        }
                    }
                }

                Row styleSourceRow = sheet.getRow(12);

                for (int e = 0; e < extraWhyRows; e++) {
                    int newRowIndex = templateCheckedRow + e;
                    Row newRow = sheet.createRow(newRowIndex);
                    newRow.setHeight(styleSourceRow.getHeight());

                    for (int c = 0; c <= 10; c++) {
                        Cell newCell = newRow.createCell(c);
                        Cell styleCell = styleSourceRow.getCell(c);

                        if (styleCell != null) {
                            newCell.setCellStyle(styleCell.getCellStyle());
                        }
                    }

                    newRow.getCell(0).setCellValue("why-" + (templateWhyCount + e + 1) + ":");
                }

                endRowIndex = endRowIndex + extraWhyRows;
            }

            for (int i = 1; i < whywhyDetailsData.size(); i++) {
                impshtexl = whywhyDetailsData.get(i);
                row += 1;

                CommonMessage.debugMsg("row Inside Excel Template :: 1234 :: [" + i + "]" + row);

                sheet.getRow(row).getCell(1).setCellValue(impshtexl[20]);
            }

            impshtexl = whywhyDetailsData.get(whywhyDetailsData.size() - 1);

            int checkedRootCauseRow = templateCheckedRow + extraWhyRows;
            int cmHeaderRow = checkedRootCauseRow + 1;

            int cmOplRow = cmHeaderRow + 1;
            int cmCltiRow = cmHeaderRow + 2;
            int cmCondMonRow = cmHeaderRow + 3;
            int cmPrevMaintRow = cmHeaderRow + 4;
            int cmModKaizenRow = cmHeaderRow + 5;
            int templateActionPlanRow = cmHeaderRow + 6;

            Map<String, String> statusToCondValue = new HashMap<>();
            statusToCondValue.put("agreed", "1");
            statusToCondValue.put("aggreed", "1");
            statusToCondValue.put("planned", "2");
            statusToCondValue.put("implemented", "3");
            statusToCondValue.put("effective", "4");

            List<XLConditionalFormats> condFormats = getConditionalFormat(imagePath, 10, 11);

            String imgPath = null;
            XLConditionalFormats condFormat = null;

            sheet.getRow(checkedRootCauseRow).getCell(4).setCellValue(impshtexl[12]);
            sheet.getRow(checkedRootCauseRow).getCell(8).setCellValue(impshtexl[27]);

            int actionPlanCount = 0;

            for (int i = 1; i < whyCounterMeasureData.size(); i++) {
                if ("Action plan".equalsIgnoreCase(whyCounterMeasureData.get(i)[0])) {
                    actionPlanCount++;
                }
            }

            int extraActionPlanRows = actionPlanCount > 1 ? actionPlanCount - 1 : 0;

            if (extraActionPlanRows > 0) {
                sheet.shiftRows(templateActionPlanRow + 1, sheet.getLastRowNum(), extraActionPlanRows);

                XSSFDrawing drawingAp = ((XSSFSheet) sheet).getDrawingPatriarch();

                if (drawingAp != null) {
                    for (XSSFShape shape : drawingAp.getShapes()) {
                        if (shape instanceof XSSFPicture) {
                            XSSFClientAnchor anchor = (XSSFClientAnchor) ((XSSFPicture) shape).getAnchor();

                            if (anchor != null && anchor.getRow1() >= templateActionPlanRow + 1) {
                                anchor.setRow1(anchor.getRow1() + extraActionPlanRows);
                                anchor.setRow2(anchor.getRow2() + extraActionPlanRows);
                            }
                        }
                    }
                }

                Row styleSourceRow = sheet.getRow(templateActionPlanRow);

                List<CellRangeAddress> templateMerges = new ArrayList<>();

                for (int m = 0; m < sheet.getNumMergedRegions(); m++) {
                    CellRangeAddress region = sheet.getMergedRegion(m);

                    if (region.getFirstRow() == templateActionPlanRow) {
                        templateMerges.add(region);
                    }
                }

                for (int e = 0; e < extraActionPlanRows; e++) {
                    int newRowIdx = templateActionPlanRow + 1 + e;

                    Row newRow = sheet.createRow(newRowIdx);

                    if (styleSourceRow != null) {
                        newRow.setHeight(styleSourceRow.getHeight());
                    }

                    for (int c = 0; c <= 10; c++) {
                        Cell newCell = newRow.createCell(c);

                        if (styleSourceRow != null) {
                            Cell styleCell = styleSourceRow.getCell(c);

                            if (styleCell != null) {
                                CellStyle newStyle = wb.createCellStyle();
                                newStyle.cloneStyleFrom(styleCell.getCellStyle());
                                newStyle.setWrapText(false);
                                newCell.setCellStyle(newStyle);
                            }
                        }
                    }

                    for (CellRangeAddress templateMerge : templateMerges) {
                        CellRangeAddress newMerge = new CellRangeAddress(
                                newRowIdx,
                                newRowIdx,
                                templateMerge.getFirstColumn(),
                                templateMerge.getLastColumn());

                        sheet.addMergedRegion(newMerge);
                    }
                }

                endRowIndex = endRowIndex + extraActionPlanRows;
            }

            int actionPlanRowOffset = 0;

            if (whyCounterMeasureData != null && whyCounterMeasureData.size() > 1) {
                for (int i = 1; i < whyCounterMeasureData.size(); i++) {
                    impshtexlcounter = whyCounterMeasureData.get(i);

                    String cmType = impshtexlcounter[0] != null ? impshtexlcounter[0].trim() : "";

                    int targetRow;

                    switch (cmType.toLowerCase()) {

                        case "change in workpractice / training / opl":
                            targetRow = cmOplRow;
                            break;

                        case "routine activity (clti)":
                            targetRow = cmCltiRow;
                            break;

                        case "condition monitoring":
                            targetRow = cmCondMonRow;
                            break;

                        case "preventive maintenance":
                            targetRow = cmPrevMaintRow;
                            break;

                        case "modification (kaizen)":
                            targetRow = cmModKaizenRow;
                            break;

                        case "action plan":
                            targetRow = templateActionPlanRow + actionPlanRowOffset;

                            if (actionPlanRowOffset > 0) {
                                Row labelRow = sheet.getRow(targetRow);

                                if (labelRow == null) {
                                    labelRow = sheet.createRow(targetRow);
                                }

                                Cell labelCell = labelRow.getCell(0);

                                if (labelCell == null) {
                                    labelCell = labelRow.createCell(0);
                                }

                                labelCell.setCellValue("Action Plan");
                            }

                            actionPlanRowOffset++;
                            break;

                        default:
                            continue;
                    }

                    Row targetRowObj = sheet.getRow(targetRow);

                    if (targetRowObj == null) {
                        targetRowObj = sheet.createRow(targetRow);
                    }

                    targetRowObj.getCell(3).setCellValue(impshtexlcounter[1]);
                    targetRowObj.getCell(4).setCellValue(impshtexlcounter[3]);
                    targetRowObj.getCell(5).setCellValue(impshtexlcounter[7]);
                    targetRowObj.getCell(8).setCellValue(impshtexlcounter[4]);
                    targetRowObj.getCell(9).setCellValue(impshtexlcounter[6]);

                    String statusStr = impshtexlcounter[6] != null
                            ? impshtexlcounter[6].trim().toLowerCase()
                            : "";

                    String condValueKey = statusToCondValue.getOrDefault(statusStr, "0");

                    condFormat = getSymbolStr(11, condValueKey, condFormats);

                    if (condFormat != null) {
                        imgPath = condFormat.getImgPathName();

                        if (imgPath != null) {
                            ExcelUtils.addImage(wb, sheet, imgPath, targetRow, 11);
                        }
                    }
                }
            }

            int actualLastActionPlanRow = templateActionPlanRow + extraActionPlanRows;
            int statusLegendRow = actualLastActionPlanRow + 1;
            int whyDoneByRow = actualLastActionPlanRow + 2;
            
            
         //   List<XLConditionalFormats> condFormats1 = getConditionalFormat(imagePath, 10, 12);

         // REMOVE this unused line:
         // List<XLConditionalFormats> condFormats1 = getConditionalFormat(imagePath, 10, 12);

         // ADD debug to find what's actually in condFormats:
         CommonMessage.debugMsg("DEBUG condFormats size: " + (condFormats != null ? condFormats.size() : "NULL"));
         for (XLConditionalFormats cf : condFormats) {
             CommonMessage.debugMsg("  condValue=" + cf.getCondValue() + " img=" + cf.getImgPathName());
         }

         condFormat = getSymbolStr(11, "1", condFormats);
         if (condFormat != null) {
             ExcelUtils.addImage(wb, sheet, condFormat.getImgPathName(), statusLegendRow, 1);
         } else { CommonMessage.debugMsg("DEBUG: key 1 not found"); }

         condFormat = getSymbolStr(11, "2", condFormats);
         if (condFormat != null) {
             ExcelUtils.addImage(wb, sheet, condFormat.getImgPathName(), statusLegendRow, 4);
         } else { CommonMessage.debugMsg("DEBUG: key 2 not found"); }

         condFormat = getSymbolStr(11, "3", condFormats);
         if (condFormat != null) {
             ExcelUtils.addImage(wb, sheet, condFormat.getImgPathName(), statusLegendRow, 7);
         } else { CommonMessage.debugMsg("DEBUG: key 3 not found"); }

         condFormat = getSymbolStr(11, "4", condFormats);
         if (condFormat != null) {
             String effectivePath = condFormat.getImgPathName();
             java.io.File f = new java.io.File(effectivePath);
             if (!f.exists()) {
                 // Try alternate casings
                 String[] alternates = {
                     effectivePath.replace("Effective.PNG", "effective.PNG"),
                     effectivePath.replace("Effective.PNG", "Effective.png"),
                     effectivePath.replace("Effective.PNG", "effective.png"),
                     effectivePath.replace("Effective.PNG", "EffectiveWhy.PNG")
                 };
                 for (String alt : alternates) {
                     if (new java.io.File(alt).exists()) {
                         effectivePath = alt;
                         CommonMessage.debugMsg("DEBUG: Found Effective image at: " + effectivePath);
                         break;
                     }
                 }
             }
             if (new java.io.File(effectivePath).exists()) {
                 ExcelUtils.addImage(wb, sheet, effectivePath, statusLegendRow, 9);
             } else {
                 CommonMessage.debugMsg("DEBUG: Effective image not found at any path - skipping");
             }
         }
            sheet.getRow(whyDoneByRow).getCell(3).setCellValue(impshtexl[10]);
            sheet.getRow(whyDoneByRow).getCell(9).setCellValue(impshtexl[54]);
            sheet.getRow(whyDoneByRow+1).getCell(9).setCellValue(impshtexl[55]);
            sheet.getRow(whyDoneByRow+2).getCell(9).setCellValue(impshtexl[56]);
            excelFormat = impshtexl[0];
        }

        sheet.setPrintGridlines(false);
        sheet.setHorizontallyCenter(true);
        sheet.getPrintSetup().setFitHeight((short) 1);
        sheet.getPrintSetup().setFitWidth((short) 1);
        sheet.setAutobreaks(true);
        sheet.setFitToPage(true);

        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);

        wb.setPrintArea(sheetNo, startColIndex, endColIndex, startRowIndex, endRowIndex);

        sheet.protectSheet("admin");

        return wb;
    }
    
	private XLConditionalFormats getSymbolStr(int colNum, String value, List<XLConditionalFormats> condFormats) {
		if (condFormats != null) {
			for (XLConditionalFormats condFormat : condFormats) {
				// CommonMessage.debugMsg( " condFormat.getCondValue() "
				// +condFormat.getCondValue() );
				if (condFormat.getCondValue().equals(value)) {
					// CommonMessage.debugMsg( " condFormat.getCondValue() "
					// +condFormat.getCondValue() );
					return condFormat;// condFormat.getSymbolStr();
				}
			}
		}
		return null;
	}

	public List<XLConditionalFormats> getConditionalFormat(String imagePath, int fromCol, int toCol) {
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		XLConditionalFormats statusBooked = new XLConditionalFormats();
		statusBooked.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked.setSymbolStr("-");
		statusBooked.setFromCol(fromCol);
		statusBooked.setToCol(toCol);
		statusBooked.setOperator(ComparisonOperator.EQUAL);
		statusBooked.setCondValue("0");
		statusBooked.setIdentfier("None");
		statusBooked.setImgPathName(imagePath + "images/whywhy/whynone.PNG");
		// statusBooked.setBgColor(new RGB(67, 197, 221));
		CommonMessage.debugMsg(statusBooked.getImgPathName() + " image nmae");
		condFormats.add(statusBooked);

		XLConditionalFormats statusBooked2 = new XLConditionalFormats();
		statusBooked2.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked2.setFromCol(fromCol);
		statusBooked2.setToCol(toCol);
		statusBooked2.setSymbolStr("-");
		statusBooked2.setOperator(ComparisonOperator.EQUAL);
		statusBooked2.setCondValue("1");
		statusBooked2.setIdentfier("Agreed");
		statusBooked2.setImgPathName(imagePath + "images/whywhy/Aggreed.PNG");
		condFormats.add(statusBooked2);

		XLConditionalFormats statusBooked3 = new XLConditionalFormats();
		statusBooked3.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked3.setFromCol(fromCol);
		statusBooked3.setToCol(toCol);
		statusBooked3.setSymbolStr("-");
		statusBooked3.setOperator(ComparisonOperator.EQUAL);
		statusBooked3.setCondValue("2");
		statusBooked3.setIdentfier("Planned");
		statusBooked3.setImgPathName(imagePath + "images/whywhy/Plannedwhy.PNG");
		condFormats.add(statusBooked3);

		XLConditionalFormats statusBooked4 = new XLConditionalFormats();
		statusBooked4.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked4.setFromCol(fromCol);
		statusBooked4.setToCol(toCol);
		statusBooked4.setSymbolStr("-");
		statusBooked4.setOperator(ComparisonOperator.EQUAL);
		statusBooked4.setCondValue("3");
		statusBooked4.setIdentfier("Implemented");
		statusBooked4.setImgPathName(imagePath + "images/whywhy/Implemented.PNG");
		condFormats.add(statusBooked4);

		XLConditionalFormats statusBooked5 = new XLConditionalFormats();
		statusBooked5.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked5.setFromCol(fromCol);
		statusBooked5.setToCol(toCol);
		statusBooked5.setOperator(ComparisonOperator.EQUAL);
		statusBooked5.setCondValue("4");
		statusBooked5.setSymbolStr("-");
		statusBooked5.setIdentfier("Effective");
		statusBooked5.setImgPathName(imagePath + "images/whywhy/Effective.PNG");
		// statusBooked5.setBgColor(new RGB(239, 182, 239));
		condFormats.add(statusBooked5);
		return condFormats;
	}

}
