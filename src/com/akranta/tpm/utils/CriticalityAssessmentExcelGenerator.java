package com.akranta.tpm.utils;

import java.awt.Color;

/*Created By Prasanth
 * 
 * 
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
//import org.apache.poi.ss.usermodel.charts.AxisCrosses;
//import org.apache.poi.ss.usermodel.charts.AxisPosition;
//import org.apache.poi.ss.usermodel.charts.ChartDataSource;
//import org.apache.poi.ss.usermodel.charts.ChartLegend;
//import org.apache.poi.ss.usermodel.charts.DataSources;
//import org.apache.poi.ss.usermodel.charts.LegendPosition;
//import org.apache.poi.ss.usermodel.charts.ScatterChartData;
//import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.scheduler.SchedulerConfigMst;

public class CriticalityAssessmentExcelGenerator {

	private JSONObject tableModel;
	private List<Integer> notWriteColumns = null;
	private List<String[]> colHeaders = null;
	private short[] headerColors = null;
	private List<XLConditionalFormats> groupByFormula = null;
	private List<XLConditionalFormats> xlCondFormat = null;
	private Workbook wb = null;
	private Map<String, CellStyle> styles = null;
	// private NotesMailClient notesMailClient = null;

	private static final Logger SchedulerLog = Logger.getLogger("SchedulerLog");

	private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);

	public static final String REPORT_FORMAT_EXL_2003 = "xls";
	public static final String REPORT_FORMAT_EXL_2007 = "xlsx";

	private static final String STYLE_IDENTIFIER_HEADER_1 = "header1";
	private static final String STYLE_IDENTIFIER_HEADER_2 = "header2";
	private static final String STYLE_IDENTIFIER_HEADER_3 = "header3";
	private static final String STYLE_IDENTIFIER_HEADER_4 = "header4";
	private static final String STYLE_IDENTIFIER_HEADER_5 = "header5";
	private static final String STYLE_IDENTIFIER_HEADER_6 = "header6";
	private static final String STYLE_IDENTIFIER_HEADER_7 = "header7";
	private static final String STYLE_IDENTIFIER_HEADER_8 = "header8";
	private static final String STYLE_IDENTIFIER_ROTATION = "rotation";

	private static final String[] STYLE_IDENTIFIER_HEADERS = { STYLE_IDENTIFIER_HEADER_1, STYLE_IDENTIFIER_HEADER_2,
			STYLE_IDENTIFIER_HEADER_3, STYLE_IDENTIFIER_HEADER_4, STYLE_IDENTIFIER_HEADER_5, STYLE_IDENTIFIER_HEADER_6,
			STYLE_IDENTIFIER_HEADER_7, STYLE_IDENTIFIER_HEADER_8 };

	private static short STYLE_HEADER_1_COLOR = IndexedColors.GREY_25_PERCENT.getIndex();
	private static short STYLE_HEADER_2_COLOR = IndexedColors.AQUA.getIndex();
	private static short STYLE_HEADER_3_COLOR = IndexedColors.LEMON_CHIFFON.getIndex();
	private static short STYLE_HEADER_4_COLOR = IndexedColors.CORAL.getIndex();
	private static short STYLE_HEADER_5_COLOR = IndexedColors.LIGHT_TURQUOISE.getIndex();
	private static short STYLE_HEADER_6_COLOR = IndexedColors.LIGHT_TURQUOISE.getIndex();
	private static short STYLE_HEADER_7_COLOR = IndexedColors.LIGHT_TURQUOISE.getIndex();
	private static short STYLE_HEADER_8_COLOR = IndexedColors.LIGHT_TURQUOISE.getIndex();
	private static short STYLE_GROUPBY_ROW_COLOR = IndexedColors.LIGHT_YELLOW.getIndex();

	private static String STYLE_IDENTIFIER_ALIGN_LEFT = "align_left";
	private static String STYLE_IDENTIFIER_ALIGN_RIGHT = "align_right";
	private static String STYLE_IDENTIFIER_ALIGN_CENTER = "align_center";
	private static String STYLE_IDENTIFIER_GROUPBY_ROW = "groupByRow";

	private List<XLConditionalFormats> condFormats = null;

	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	private float rowHeight = -1;

	// private String [] colModelProperties = {"WIDTH","ALIGN"} ;

	public CriticalityAssessmentExcelGenerator() {
		setDefaultHeaderColors();
	}

	/*
	 * public ExcelUtils(NotesMailClient notesMailClient) { this.notesMailClient =
	 * notesMailClient; setDefaultHeaderColors(); }
	 */

	public CriticalityAssessmentExcelGenerator(JSONObject tableModel) {
		this.tableModel = tableModel;
		setDefaultHeaderColors();

		if (tableModel.has("rowHeight") && tableModel.get("rowHeight") != null) {
			String rowH = tableModel.get("rowHeight").toString();
			// CommonMessage.debugMsg(" rowH " + rowH);
			if (Pattern.matches("^\\d*\\.?\\d+$", rowH))
				this.rowHeight = Float.parseFloat(rowH);
		}
	}

	public CriticalityAssessmentExcelGenerator(JSONObject tableModel, List<XLConditionalFormats> groupByFormula) {
		this.tableModel = tableModel;
		this.groupByFormula = groupByFormula;
		setDefaultHeaderColors();
		if (tableModel.has("rowHeight"))
			this.rowHeight = (short) tableModel.getInt("rowHeight");
	}

	private void setDefaultHeaderColors() {
		headerColors = new short[5];
		headerColors[0] = IndexedColors.AQUA.getIndex();
		headerColors[1] = IndexedColors.BROWN.getIndex();
		headerColors[2] = IndexedColors.BLUE_GREY.getIndex();
		headerColors[3] = IndexedColors.CORAL.getIndex();
		headerColors[4] = IndexedColors.LIGHT_TURQUOISE.getIndex();
	}

	private HorizontalAlignment getHAlign(JSONObject colModel) {
		String hAlignStr;
		HorizontalAlignment halign = HorizontalAlignment.LEFT;

		if (colModel.has("align")) {
			hAlignStr = colModel.getString("align");
			if (hAlignStr.equals("right"))
				halign = HorizontalAlignment.RIGHT;
			else if (hAlignStr.equals("center"))
				halign = HorizontalAlignment.CENTER;
			else
				halign = HorizontalAlignment.LEFT;
		}
		return halign;
	}

	private void setColumnWidths(Sheet sheet, JSONArray jsonColModelArr) {
		JSONObject colModel;
		int width;
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 50 * 30);
		for (int i = 0; i < jsonColModelArr.length(); i++) {

			colModel = jsonColModelArr.getJSONObject(i);
			if (colModel.has("hidden") && colModel.getBoolean("hidden"))
				continue;
			if (colModel.has("width") && colModel.getInt("width") > 0)
				width = colModel.getInt("width");
			else
				width = 200;

			sheet.setColumnWidth(colIndex++, width * 30);
		}
	}

	private int setColHeaders(Workbook workBook, Sheet sheet, JSONObject tableModel) {

		JSONArray jsonArrColHeadrs = null;
		JSONArray jsonColModelArr = null;
		if (tableModel.has("colNames"))
			jsonArrColHeadrs = tableModel.getJSONArray("colNames");
		else if (tableModel.has("rowHeaders"))
			jsonArrColHeadrs = tableModel.getJSONArray("rowHeaders");
		else
			return 0;

		JSONArray rotationRows = null;
		if (tableModel.has("rotationRows"))
			rotationRows = tableModel.getJSONArray("rotationRows");

		CellStyle rowStyle = this.styles.get(STYLE_IDENTIFIER_HEADERS[0]);

		jsonColModelArr = tableModel.getJSONArray("colModel");
		setMaintitle(sheet, rowStyle, jsonColModelArr);

		int noCols = jsonColModelArr.length();
		// CellRangeAddress region =null;
		Row myRow = null;
		JSONObject colModel = null;
		// String tcChar = null;
		short colIndx = 0;

		int i = 1;
		int mergeFrom = 0;// , mergeTo = 0;
		boolean mergeFlag = false;
		String prevColHeader = null, colHeader = null;
		// String title = "";
		// String fcChar =null;
		boolean isRotation = false;
		CellStyle rotationStyle = null;
		String rotation = null;
		Cell cell = null;

		for (; i < jsonArrColHeadrs.length() + 1; i++) {
			JSONArray colHeaders = jsonArrColHeadrs.getJSONArray(i - 1);

			myRow = sheet.createRow(i);
			prevColHeader = null;
			isRotation = isRotationRow(rotationRows, i + 1);
			rowStyle = styles.get(STYLE_IDENTIFIER_HEADERS[i]);

			colIndx = 0;
			cell = myRow.createCell(colIndx);
			cell.setCellValue(i == jsonArrColHeadrs.length() ? "Sl.No" : "");
			cell.setCellStyle(rowStyle);
			colIndx++;

			if (isRotation) {
				rotationStyle = styles.get(STYLE_IDENTIFIER_ROTATION);
				rotationStyle.cloneStyleFrom(rowStyle);
			}
			mergeFlag = false;
			for (int j = 0; j < noCols; j++) {

				colModel = jsonColModelArr.getJSONObject(j);
				if (colModel.has("hidden") && colModel.getBoolean("hidden"))
					continue;

				colHeader = colHeaders.getString(j);

				cell = myRow.createCell(colIndx);
				if (isRotation && colModel.has("headerRotation")
						&& ((rotation = colModel.getString("headerRotation")) != null
								&& rotation.trim().length() > 0)) {
					rotationStyle.setRotation(Short.parseShort(rotation));
					cell.setCellStyle(rotationStyle);
				} else
					cell.setCellStyle(rowStyle);

				cell.setCellValue(colHeader);

				if (colHeader.equals(prevColHeader) && !mergeFlag) {
					mergeFlag = true;
					mergeFrom = colIndx;
				} else if (!colHeader.equals(prevColHeader) && mergeFlag) {
					mergeColumns(sheet, mergeFrom, colIndx, i + 1);
					mergeFlag = false;
				}
				colIndx++;

				prevColHeader = colHeader;
			}
			if (mergeFlag) {
				mergeColumns(sheet, mergeFrom, colIndx, i + 1);
			}
			// sheet.setR
			if (isRotation)
				myRow.setHeight((short) 1500);
			// else
			// myRow.setHeight((short)500);

		}
		sheet.createFreezePane(2, i);

		return i;

	}

	private void mergeColumns(Sheet sheet, int mergeFromCol, int mergeToCol, int row) {
		CommonMessage.debugMsg(" Merge columns from : " + mergeFromCol + "; To :" + mergeToCol);
		String fcChar = getCharForExcelCol(mergeFromCol);// from col char
		String tcChar = getCharForExcelCol(mergeToCol); // to col char
		CommonMessage.debugMsg(" Merge columns from : " + fcChar + row + "; To :" + tcChar + row);
		CellRangeAddress region = CellRangeAddress.valueOf(fcChar + row + ":" + tcChar + row);
		sheet.addMergedRegion(region);
	}
	// madhan changed for column merge issue
//	private void mergeColumns(Sheet sheet, int mergeFromCol, int mergeToCol, int row) {
//
//	    // Safety: ensure correct order
//	    if (mergeFromCol >= mergeToCol) {
////	        int tmp = mergeFromCol;
////	        mergeFromCol = mergeToCol;
////	        mergeToCol = tmp;
//	    	return;
//	    }
//
//	    CellRangeAddress newRegion =
//	            new CellRangeAddress(row , row , mergeFromCol, mergeToCol);
//
//	    // Check for overlap
//	    for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
//	        CellRangeAddress existing = sheet.getMergedRegion(i);
//	        if (existing.getFirstRow() == row &&
//	                existing.getLastRow() == row &&
//	                existing.intersects(newRegion)) {
//
//	                sheet.removeMergedRegion(i);
//	            }
////	        if (existing.intersects(newRegion)) {
////	            return; // already merged / overlapping → skip safely
////	        }
//	    }
//
//	    sheet.addMergedRegion(newRegion);
//	}

	private boolean isRotationRow(JSONArray arr, int rowNum) {
		if (arr != null) {
			for (int i = 0; i < arr.length(); i++) {
				if (arr.get(i).equals(rowNum))
					return true;
			}
		}
		return false;
	}

	// private CellStyle getRotationStyle(CellStyle style,CellStyle rotationStyle){

	// }

	private void setMaintitle(Sheet sheet, CellStyle rowStyle, JSONArray jsonColModelArr) {

		Row myRow = sheet.createRow(0);
		String title = "";
		if (tableModel.has("title")) {
			title = tableModel.getString("title");

		}
		int colIndx = 0;

		JSONObject colModel = null;
		// CellStyle rowStyle = style.get(STYLE_IDENTIFIER_HEADERS[0]);
		int noCols = jsonColModelArr.length();
		Cell tCell = null;
		for (int j = 0; j < noCols; j++) {
			colModel = jsonColModelArr.getJSONObject(j);
			if (colModel.has("hidden") && colModel.getBoolean("hidden"))
				continue;

			tCell = myRow.createCell(colIndx);
			if (colIndx == 0)
				tCell.setCellValue(title);

			tCell.setCellStyle(rowStyle);
			colIndx++;
		}
		tCell = myRow.createCell(colIndx++);// for row numbers
		tCell.setCellStyle(rowStyle);

		myRow.setHeight((short) 800);

		String tcChar = getCharForExcelCol(colIndx);
		CellRangeAddress region = CellRangeAddress.valueOf("A1:" + tcChar + "1");
		sheet.addMergedRegion(region);

	}

//    public static String getCharForExcelCol(int colIndex)
//    {
//    	// char c = 'A';
//    	 
//    	 String colChar ="";
//    	 do{
//    		 if( (colIndex / 26) > 0){
//    			 colChar +=  (char)(( 64  + (int) (colIndex / 26))) ;
//    			 colIndex  = (colIndex % 26);
//    		 }	 
//    		 else{
//    			 
//    			 if( colIndex > 0)
//    				 colChar +=  (char)(( 64  +   (int)colIndex % 26 )) ;
//    			 else
//    				 colChar += "A";
//    			 
//    			 colIndex = -1;
//    		 }	 
//    	 }while( colIndex >= 0 );
//
//    	 return colChar;
//    }
//    madhan 
	public static String getCharForExcelCol(int colIndex) {
		StringBuilder col = new StringBuilder();
		// colIndex++; // convert 0-based to 1-based

		while (colIndex > 0) {
			int rem = (colIndex - 1) % 26;
			col.insert(0, (char) ('A' + rem));
			colIndex = (colIndex - 1) / 26;
		}
		return col.toString();
	}

	public void setNotWriteColumns(List<Integer> notWriteColumns) {
		this.notWriteColumns = notWriteColumns;
	}

	public List<Integer> getNotWriteColumns() {
		return notWriteColumns;
	}

	public void setColHeaders(List<String[]> colHeaders) {
		this.colHeaders = colHeaders;
	}

	public List<String[]> getColHeaders() {
		return colHeaders;
	}

	public static void addImage(HSSFWorkbook wb, Sheet sheet, String imageFileNamePath, int rowNumber, int colNumber)
			throws IOException {

		try {
			//// CommonMessage.debugMsg(" rowNumber " +rowNumber + " colNumber "
			//// +colNumber );
			InputStream is = new FileInputStream(imageFileNamePath);
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIdx = 0;
			imageFileNamePath = imageFileNamePath.toUpperCase();
			if (imageFileNamePath != null && (imageFileNamePath.endsWith(".JPG") || imageFileNamePath.endsWith("JPEG")))
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			else if (imageFileNamePath != null && (imageFileNamePath.endsWith(".PNG")))
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			else if (imageFileNamePath != null && (imageFileNamePath.endsWith(".DIB")))
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_DIB);
			else
				pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PICT);

			is.close();

			CreationHelper helper = wb.getCreationHelper();

			// create sheet

			// Create the drawing patriarch. This is the top level container for all shapes.
			Drawing drawing = sheet.createDrawingPatriarch();

			// add a picture shape
			ClientAnchor anchor = helper.createClientAnchor();
			// set top-left corner of the picture,
			// subsequent call of Picture#resize() will operate relative to it
			anchor.setCol1(colNumber);
			anchor.setRow1(rowNumber);

			Picture pict = drawing.createPicture(anchor, pictureIdx);

			// pict.resize(-5);
			// auto-size picture relative to its top-left corner
			pict.resize();
		} catch (IOException e) {
			// CommonMessage.debugMsg( " addImage " + imageFileNamePath + e.getMessage());
		}

	}

	public static void addImage(Workbook wb, Sheet sheet, String imageFileNamePath, int rowNumber, int colNumber)
			throws IOException {
		// Create the drawing patriarch. This is the top level container for all shapes.
		Drawing drawing = sheet.createDrawingPatriarch();

		try {
			if (imageFileNamePath != null) {
				InputStream is = new FileInputStream(imageFileNamePath);
				byte[] bytes = IOUtils.toByteArray(is);
				int pictureIdx = 0;
				imageFileNamePath = imageFileNamePath.toUpperCase();

				if ((imageFileNamePath.endsWith(".JPG") || imageFileNamePath.endsWith("JPEG")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
				else if ((imageFileNamePath.endsWith(".PNG")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				else if (imageFileNamePath.endsWith(".DIB"))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_DIB);
				else
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PICT);

				is.close();

				CreationHelper helper = wb.getCreationHelper();

				// add a picture shape
				ClientAnchor anchor = helper.createClientAnchor();
				// set top-left corner of the picture,
				// subsequent call of Picture#resize() will operate relative to it
				anchor.setCol1(colNumber);
				anchor.setRow1(rowNumber);

				Picture pict = drawing.createPicture(anchor, pictureIdx);
				pict.resize();
			}
		} catch (IOException e) {
			// CommonMessage.debugMsg( " addImage " + imageFileNamePath + e.getMessage());
		}

	}

	public static void addImages(HSSFWorkbook wb, Sheet sheet, List<ExcelInsertImage> images) throws IOException {

		// Create the drawing patriarch. This is the top level container for all shapes.
		Drawing drawing = sheet.createDrawingPatriarch();

		for (ExcelInsertImage excelImage : images) {

			try {

				InputStream is = new FileInputStream(excelImage.imageFileName);
				byte[] bytes = IOUtils.toByteArray(is);
				int pictureIdx = 0;

				excelImage.imageFileName = excelImage.imageFileName.toUpperCase();

				if (excelImage.imageFileName != null
						&& (excelImage.imageFileName.endsWith(".JPG") || excelImage.imageFileName.endsWith("JPEG")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
				else if (excelImage.imageFileName != null && (excelImage.imageFileName.endsWith(".PNG")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				else if (excelImage.imageFileName != null && (excelImage.imageFileName.endsWith(".DIB")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_DIB);
				else
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PICT);

				is.close();

				CreationHelper helper = wb.getCreationHelper();

				// add a picture shape
				ClientAnchor anchor = helper.createClientAnchor();
				// set top-left corner of the picture,
				// subsequent call of Picture#resize() will operate relative to it
				anchor.setCol1(excelImage.col1);
				anchor.setRow1(excelImage.row1);

				Picture pict = drawing.createPicture(anchor, pictureIdx);
				pict.resize();

			} catch (IOException e) {
				// CommonMessage.debugMsg( " addImage " + excelImage.imageFileName +
				// e.getMessage());
			}
		}
	}

	public static void addImages(Workbook wb, Sheet sheet, List<ExcelInsertImage> images) throws IOException {
		// Create the drawing patriarch. This is the top level container for all shapes.
		Drawing drawing = sheet.createDrawingPatriarch();

		for (ExcelInsertImage excelImage : images) {

			try {
				InputStream is = new FileInputStream(excelImage.imageFileName);
				byte[] bytes = IOUtils.toByteArray(is);
				int pictureIdx = 0;
				excelImage.imageFileName = excelImage.imageFileName.toUpperCase();

				// CommonMessage.debugMsg("ADD IMA : "+excelImage.imageFileName);
				if (excelImage.imageFileName != null
						&& (excelImage.imageFileName.endsWith(".JPG") || excelImage.imageFileName.endsWith("JPEG")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
				else if (excelImage.imageFileName != null && (excelImage.imageFileName.endsWith(".PNG")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				else if (excelImage.imageFileName != null && (excelImage.imageFileName.endsWith(".DIB")))
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_DIB);
				else
					pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PICT);

				is.close();

				CreationHelper helper = wb.getCreationHelper();

				// add a picture shape
				ClientAnchor anchor = helper.createClientAnchor();
				// set top-left corner of the picture,
				// subsequent call of Picture#resize() will operate relative to it
				anchor.setCol1(excelImage.col1);
				anchor.setRow1(excelImage.row1);

				Picture pict = drawing.createPicture(anchor, pictureIdx);
				pict.resize();

			} catch (IOException e) {
				// CommonMessage.debugMsg( " addImage " + excelImage.imageFileName +
				// e.getMessage());
			}
		}

	}

	/*
	 * public Workbook writeToExcel(ResultSet rs , String reportType,int fromRow,int
	 * fromCol,int toCol ) throws IOException, SQLException {
	 * 
	 * this.rs = rs; JSONArray jsonColModelArr =
	 * tableModel.getJSONArray("colModel"); int colModelLength =
	 * jsonColModelArr.length(); boolean transpose = false; if(
	 * tableModel.has("transpose") ){ transpose =
	 * tableModel.getBoolean("transpose"); }
	 * 
	 * //this.wb = reportType.equals(REPORT_FORMAT_EXL_2007) ? new SXSSFWorkbook(
	 * (transpose ? -1:100)):new HSSFWorkbook(); // keep 100 rows in memory,
	 * exceeding rows will be flushed to disk //this.wb =
	 * reportType.equals(REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook():new
	 * HSSFWorkbook(); // keep 100 rows in memory, exceeding rows will be flushed to
	 * disk Sheet sheet = null; String extension ="xls"; if(
	 * REPORT_FORMAT_EXL_2007.equals(reportType) ){ extension ="xlsx"; } //MADHAN
	 * added URLDecoder.decode for space in path final File f = new
	 * File(URLDecoder.decode(CriticalityAssessmentExcelGenerator.class.
	 * getProtectionDomain().getCodeSource().getLocation().getPath(),
	 * StandardCharsets.UTF_8.name() ));
	 * 
	 * CommonMessage.debugMsg(" f " + f.getPath());
	 * 
	 * String filePath =
	 * f.getPath().replace("classes\\com\\akranta\\tpm\\utils\\ExcelUtils.class",
	 * "exceltemplates"); String Path=filePath.replace("classes", "exceltemplates");
	 * StringBuilder templateFile = new StringBuilder( Path)
	 * .append("\\" ).append( "excelrptgen.").append(extension);
	 * CommonMessage.debugMsg("filepath"+filePath);
	 * CommonMessage.debugMsg("templateFile"+templateFile); // templateFile.append("x");
	 * 
	 * InputStream inp = new FileInputStream(templateFile.toString());
	 * 
	 * //this.wb = reportType.equals(REPORT_FORMAT_EXL_2007) ? new SXSSFWorkbook(
	 * (transpose ? -1:100)):new HSSFWorkbook(inp); // keep 100 rows in memory,
	 * exceeding rows will be flushed to disk //this.wb =
	 * reportType.equals(REPORT_FORMAT_EXL_2007) ? new SXSSFWorkbook( new
	 * XSSFWorkbook(inp), (transpose ? -1:100)):new HSSFWorkbook(inp); // keep 100
	 * rows in memory, exceeding rows will be flushed to disk if(
	 * REPORT_FORMAT_EXL_2007.equals(reportType) ) { XSSFWorkbook xssf = new
	 * XSSFWorkbook(inp); this.wb = new SXSSFWorkbook(xssf, 200); //this.wb = new
	 * XSSFWorkbook(inp); } else this.wb = new HSSFWorkbook(inp);
	 * 
	 * inp.close();
	 * 
	 * //Sheet sheet = wb.createSheet(); sheet = wb.getSheetAt(0);
	 * 
	 * 
	 * 
	 * //Sheet sheet = wb.createSheet(); String cellValue = null;
	 * 
	 * 
	 * sheet.setDisplayGridlines(false);
	 * 
	 * this.rsmd = rs.getMetaData(); int colCount = rsmd.getColumnCount(); int
	 * dataIndex =-1 ,grpByRowIndx = 0;
	 * 
	 * 
	 * JSONObject colModel = null; Boolean groupBy = false; String groupByField =
	 * null, prevGrpVal = null , curGrpVal = null ; int groupByColIndex = -1; if(
	 * tableModel.has("groupBy")){ groupBy = tableModel.getBoolean("groupBy"); if(
	 * groupBy && tableModel.has("groupByField")){ groupBy = true; groupByField =
	 * tableModel.getString("groupByField"); } }
	 * 
	 * styles = createStyles(reportType);
	 * 
	 * int totHeaders= setColHeaders(wb,sheet,tableModel); int rowIndex =
	 * totHeaders; int slNo = 1; int excelColIndx = 0,tblColIndx=0; String cellAlign
	 * = null; Row grpByRow = null; Cell cell = null; //CellRangeAddress region =
	 * null; boolean firstFlag = true; String symbol = null; String imgPath = null;
	 * Row row = null; XLConditionalFormats condFormat = null; while( rs.next() ){
	 * 
	 * dataIndex++; if( dataIndex < fromRow ) continue;
	 * 
	 * if( groupBy ) { if( groupByColIndex != -1 ){ if( curGrpVal != null && !
	 * curGrpVal.equals(prevGrpVal) && firstFlag ) {
	 * setGroupByRowCell(excelColIndx,grpByRow,
	 * curGrpVal,styles.get(STYLE_IDENTIFIER_GROUPBY_ROW), sheet, (grpByRowIndx+1));
	 * firstFlag = false; } prevGrpVal = curGrpVal; curGrpVal =
	 * getValueFromResultSet(rsmd, rs, groupByColIndex); } if( prevGrpVal == null ||
	 * ! curGrpVal.equals(prevGrpVal) ) { if( ! firstFlag && groupByFormula != null)
	 * { addGroupByFormula(sheet,rowIndex++,excelColIndx,grpByRowIndx+2); }
	 * grpByRowIndx = rowIndex; slNo = 1; grpByRow = sheet.createRow(rowIndex++);
	 * if( ! firstFlag ) setGroupByRowCell(excelColIndx,grpByRow,
	 * curGrpVal,styles.get(STYLE_IDENTIFIER_GROUPBY_ROW), sheet,(grpByRowIndx+1));
	 * } } if( ! transpose) { row = sheet.createRow(rowIndex++);
	 * //CommonMessage.debugMsg(" this.rowHeight " + this.rowHeight);
	 * if(this.rowHeight > -1) row.setHeightInPoints(this.rowHeight);
	 * 
	 * excelColIndx = 0; tblColIndx = 0; cell = row.createCell(excelColIndx);
	 * cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
	 * cell.setCellValue(slNo++); excelColIndx++; } else{ rowIndex = totHeaders;
	 * colModel = jsonColModelArr.getJSONObject(tblColIndx); if(
	 * colModel.has("hidden") && colModel.getBoolean("hidden") ) { tblColIndx++;
	 * continue; } }
	 * 
	 * for(int cellnum = fromCol; cellnum < colCount-toCol; cellnum++){ if(
	 * colModelLength <= tblColIndx ) break;
	 * 
	 * if( ! transpose ) colModel = jsonColModelArr.getJSONObject(tblColIndx++);
	 * 
	 * if( groupBy && groupByColIndex == -1 && (groupByField != null &&
	 * groupByField.equals(colModel.getString("index"))) ){ groupByColIndex =
	 * cellnum; curGrpVal = getValueFromResultSet(rsmd, rs, cellnum); }
	 * 
	 * if( colModel.has("hidden") && colModel.getBoolean("hidden") ) continue;
	 * 
	 * if( transpose ) { if( excelColIndx > 1) row = sheet.getRow(rowIndex); else{
	 * if( removeRow ) { long tmpColIndex = dataIndex; boolean rmExist = false;
	 * 
	 * while(rs.next()){ if( tmpColIndex >= rmFromCol && ( tmpColIndex <= rmToCol ||
	 * rmToCol == -1)){
	 * 
	 * cellValue = DBActionTemplate.getValueFromResultSet(rsmd, rs, cellnum);
	 * rmExist = false; for(String compVal : rmValueArr){ if(
	 * compVal.equalsIgnoreCase(cellValue)){ rmExist =true; } } if( ! rmExist )
	 * break; } tmpColIndex++;
	 * 
	 * } rs.absolute(dataIndex); if( rmExist) { // cellnum++; continue ; } }
	 * 
	 * row = sheet.createRow((rowIndex)); excelColIndx = 0; cell =
	 * row.createCell(excelColIndx);
	 * cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
	 * cell.setCellValue(slNo++); excelColIndx++; } } cell =
	 * row.createCell(excelColIndx); if( ! transpose ) excelColIndx++;
	 * 
	 * cellValue = getValueFromResultSet(rsmd, rs, cellnum); cellAlign = null;
	 * 
	 * 
	 * if( this.condFormats != null && (cellAlign =
	 * getFontStyleIdentfier(excelColIndx , cellValue))!= null ); else
	 * if(colModel.has("align") && (cellAlign = colModel.getString("align") ) !=
	 * null && cellAlign.equals("right") ) cellAlign = STYLE_IDENTIFIER_ALIGN_RIGHT;
	 * else if( cellAlign != null && cellAlign.equals("center")) cellAlign =
	 * STYLE_IDENTIFIER_ALIGN_CENTER; else cellAlign = STYLE_IDENTIFIER_ALIGN_LEFT;
	 * 
	 * cell.setCellStyle(styles.get(cellAlign)); condFormat = null; imgPath = null;
	 * symbol = null;
	 * 
	 * if( this.condFormats != null ) { //CommonMessage.debugMsg( " cellValue " +
	 * cellValue + " excelColIndx " + excelColIndx); condFormat =
	 * getSymbolStr(excelColIndx , cellValue); //CommonMessage.debugMsg(
	 * " condFormat " + condFormat ); if( condFormat != null){ symbol =
	 * condFormat.getSymbolStr(); imgPath = condFormat.getImgPathName(); } }
	 * 
	 * boolean isNumeric = Pattern.matches("^\\d*$", cellValue);
	 * //CommonMessage.debugMsg(" imgPath " +imgPath);
	 * 
	 * if( (imgPath == null)&& (! isNumeric || symbol != null ||cellValue == null
	 * ||cellValue.isEmpty())) cell.setCellValue( symbol == null ? cellValue :symbol
	 * ); else if(imgPath != null){ addImage(wb, sheet, imgPath, rowIndex-1,
	 * excelColIndx-1); symbol = null;
	 * 
	 * } else cell.setCellValue(Double.parseDouble(cellValue));
	 * 
	 * if( transpose ){ rowIndex++; }
	 * 
	 * } if( transpose ){ tblColIndx++; excelColIndx++; } }
	 * 
	 * if( tableModel.has("removeRow") && transpose ) { //int totalRowRemoved
	 * =removeRow(sheet, totHeaders, rowIndex,excelColIndx); removeRow(sheet,
	 * totHeaders, rowIndex,excelColIndx); //rowIndex -= totalRowRemoved;
	 * 
	 * }
	 * 
	 * if( groupBy && ! firstFlag && groupByFormula != null) {
	 * addGroupByFormula(sheet,rowIndex++,excelColIndx,grpByRowIndx+2); }
	 * setColumnWidths(sheet,jsonColModelArr); //madhan //formatSheet(wb,
	 * sheet,excelColIndx,rowIndex);
	 * 
	 * 
	 * // if( this.xlCondFormat != null) //
	 * setConditionalFormats(sheet,wb,reportType,totHeaders+1,rowIndex,tblColIndx);
	 * 
	 * 
	 * // wb.getSheetAt(0).getSheetConditionalFormatting()
	 * 
	 * // FileOutputStream out = new FileOutputStream("testSxssf.xlsx"); //
	 * wb.write(out); // out.close();
	 * 
	 * styles =null; return wb; }
	 */
	
	public Workbook writeToExcel(ResultSet rs, String reportType, int fromRow, int fromCol, int toCol) throws IOException, SQLException {
	    
	    this.rs = rs;
	    JSONArray jsonColModelArr = tableModel.getJSONArray("colModel");
	    int colModelLength = jsonColModelArr.length();
	    boolean transpose = false;
	    if (tableModel.has("transpose")) {
	        transpose = tableModel.getBoolean("transpose");
	    }
	    
	    Sheet sheet = null;
	    String extension = "xls";
	    if (REPORT_FORMAT_EXL_2007.equals(reportType)) {
	        extension = "xlsx";
	    }
	    
	    final File f = new File(ExcelUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	    
	    CommonMessage.debugMsg(" f " + f.getPath());
	    
	    String filePath = f.getPath().replace("classes\\com\\akranta\\tpm\\utils\\ExcelUtils.class", "exceltemplates");
	    String Path = filePath.replace("classes", "exceltemplates");
	    StringBuilder templateFile = new StringBuilder(Path).append("\\").append("excelrptgen.").append(extension);
	    CommonMessage.debugMsg("filepath" + filePath);
	    CommonMessage.debugMsg("templateFile" + templateFile);
	    
	    InputStream inp = new FileInputStream(templateFile.toString());
	    
	    if (REPORT_FORMAT_EXL_2007.equals(reportType))
	        this.wb = new XSSFWorkbook(inp);
	    else
	        this.wb = new HSSFWorkbook(inp);
	    
	    inp.close();
	    
	    sheet = wb.getSheetAt(0);
	    
	    String cellValue = null;
	    
	    sheet.setDisplayGridlines(false);
	    
	    this.rsmd = rs.getMetaData();
	    int colCount = rsmd.getColumnCount();
	    int dataIndex = -1, grpByRowIndx = 0;
	    
	    JSONObject colModel = null;
	    Boolean groupBy = false;
	    String groupByField = null, prevGrpVal = null, curGrpVal = null;
	    int groupByColIndex = -1;
	    if (tableModel.has("groupBy")) {
	        groupBy = tableModel.getBoolean("groupBy");
	        if (groupBy && tableModel.has("groupByField")) {
	            groupBy = true;
	            groupByField = tableModel.getString("groupByField");
	        }
	    }
	    
	    styles = createStyles(reportType);
	    
	    int totHeaders = setColHeaders(wb, sheet, tableModel);
	    int rowIndex = totHeaders;
	    int slNo = 1;
	    int excelColIndx = 0, tblColIndx = 0;
	    String cellAlign = null;
	    Row grpByRow = null;
	    Cell cell = null;
	    boolean firstFlag = true;
	    String symbol = null;
	    String imgPath = null;
	    Row row = null;
	    XLConditionalFormats condFormat = null;
	    
	    // Find the criteria column index and equipment column index (1-based index, accounting for fromCol offset)
	    int criteriaColIndex = -1;
	    int equipmentColIndex = -1;
	    for (int i = 1; i <= colCount; i++) {
	        try {
	            String columnName = rsmd.getColumnName(i);
	            if ("CRITERIA".equalsIgnoreCase(columnName)) {
	                criteriaColIndex = i;
	            }
	            if ("EQUIPMENT".equalsIgnoreCase(columnName)) {
	                equipmentColIndex = i;
	            }
	        } catch (Exception e) {
	            // Continue if column not accessible
	        }
	    }
	    
	    while (rs.next()) {
	        
	        dataIndex++;
	        if (dataIndex < fromRow) continue;
	        
	        // Get the criteria value for this row to determine if we need green background
	        String criteriaValue = null;
	        if (criteriaColIndex != -1 && criteriaColIndex <= colCount) {
	            try {
	                criteriaValue = rs.getString(criteriaColIndex);
	            } catch (Exception e) {
	                criteriaValue = null;
	            }
	        }
	        boolean applyGreenBackground = (criteriaValue != null && 
	                                       !criteriaValue.trim().isEmpty() &&
	                                       (criteriaValue.trim().equalsIgnoreCase("Non-Critical") || 
	                                        criteriaValue.trim().equalsIgnoreCase("Critical")));
	        
	        if (groupBy) {
	            if (groupByColIndex != -1) {
	                if (curGrpVal != null && !curGrpVal.equals(prevGrpVal) && firstFlag) {
	                    setGroupByRowCell(excelColIndx, grpByRow, curGrpVal, styles.get(STYLE_IDENTIFIER_GROUPBY_ROW), sheet, (grpByRowIndx + 1));
	                    firstFlag = false;
	                }
	                prevGrpVal = curGrpVal;
	                curGrpVal = getValueFromResultSet(rsmd, rs, groupByColIndex);
	            }
	            if (prevGrpVal == null || !curGrpVal.equals(prevGrpVal)) {
	                if (!firstFlag && groupByFormula != null) {
	                    addGroupByFormula(sheet, rowIndex++, excelColIndx, grpByRowIndx + 2);
	                }
	                grpByRowIndx = rowIndex;
	                slNo = 1;
	                grpByRow = sheet.createRow(rowIndex++);
	                if (!firstFlag)
	                    setGroupByRowCell(excelColIndx, grpByRow, curGrpVal, styles.get(STYLE_IDENTIFIER_GROUPBY_ROW), sheet, (grpByRowIndx + 1));
	            }
	        }
	        if (!transpose) {
	            row = sheet.createRow(rowIndex++);
	            if (this.rowHeight > -1)
	                row.setHeightInPoints(this.rowHeight);
	            
	            excelColIndx = 0;
	            tblColIndx = 0;
	            cell = row.createCell(excelColIndx);
	            
	            // Serial number column - no green background
	            cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
	            cell.setCellValue(slNo++);
	            excelColIndx++;
	        } else {
	            rowIndex = totHeaders;
	            colModel = jsonColModelArr.getJSONObject(tblColIndx);
	            if (colModel.has("hidden") && colModel.getBoolean("hidden")) {
	                tblColIndx++;
	                continue;
	            }
	        }
	        
	        for (int cellnum = fromCol; cellnum < colCount - toCol; cellnum++) {
	            if (colModelLength <= tblColIndx) break;
	            
	            if (!transpose) colModel = jsonColModelArr.getJSONObject(tblColIndx++);
	            
	            if (groupBy && groupByColIndex == -1 && (groupByField != null && groupByField.equals(colModel.getString("index")))) {
	                groupByColIndex = cellnum;
	                curGrpVal = getValueFromResultSet(rsmd, rs, cellnum);
	            }
	            
	            if (colModel.has("hidden") && colModel.getBoolean("hidden"))
	                continue;
	            
	            if (transpose) {
	                if (excelColIndx > 1)
	                    row = sheet.getRow(rowIndex);
	                else {
	                    row = sheet.createRow((rowIndex));
	                    excelColIndx = 0;
	                    cell = row.createCell(excelColIndx);
	                    cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
	                    cell.setCellValue(slNo++);
	                    excelColIndx++;
	                }
	            }
	            cell = row.createCell(excelColIndx);
	            if (!transpose) excelColIndx++;
	            
	            cellValue = getValueFromResultSet(rsmd, rs, cellnum);
	            cellAlign = null;
	            
	            if (this.condFormats != null &&
	                    (cellAlign = getFontStyleIdentfier(excelColIndx, cellValue)) != null);
	            else if (colModel.has("align") &&
	                    (cellAlign = colModel.getString("align")) != null &&
	                    cellAlign.equals("right")) cellAlign = STYLE_IDENTIFIER_ALIGN_RIGHT;
	            else if (cellAlign != null &&
	                    cellAlign.equals("center")) cellAlign = STYLE_IDENTIFIER_ALIGN_CENTER;
	            else cellAlign = STYLE_IDENTIFIER_ALIGN_LEFT;
	            
	            // Check if this is the EQUIPMENT column using colModel index
	            String colIndex = colModel.getString("index");
	            boolean isEquipmentColumn = "EQUIPMENT".equalsIgnoreCase(colIndex);
	            
	            // Apply green background style ONLY to Equipment column if criteria has value
	            CellStyle styleToApply;
	            if (applyGreenBackground && isEquipmentColumn) {
	                // Use green background style based on alignment
	                if (cellAlign.equals(STYLE_IDENTIFIER_ALIGN_RIGHT)) {
	                    styleToApply = styles.get("EQUIPMENT_GREEN_RIGHT");
	                } else if (cellAlign.equals(STYLE_IDENTIFIER_ALIGN_CENTER)) {
	                    styleToApply = styles.get("EQUIPMENT_GREEN_CENTER");
	                } else {
	                    styleToApply = styles.get("EQUIPMENT_GREEN_LEFT");
	                }
	            } else {
	                styleToApply = styles.get(cellAlign);
	            }
	            
	            cell.setCellStyle(styleToApply);
	            
	            condFormat = null;
	            imgPath = null;
	            symbol = null;
	            
	            if (this.condFormats != null) {
	                condFormat = getSymbolStr(excelColIndx, cellValue);
	                if (condFormat != null) {
	                    symbol = condFormat.getSymbolStr();
	                    imgPath = condFormat.getImgPathName();
	                }
	            }
	            
	            boolean isNumeric = Pattern.matches("^\\d*$", cellValue);
	            
	            // Fix for Equipment Code column - prevent scientific notation
	            if ("EQUIPMENTCODE".equalsIgnoreCase(colIndex)) {
	                // Always treat equipment code as text to prevent scientific notation
	                cell.setCellValue(cellValue);
	            } else if ((imgPath == null) && (!isNumeric || symbol != null || cellValue == null || cellValue.isEmpty())) {
	                cell.setCellValue(symbol == null ? cellValue : symbol);
	            } else if (imgPath != null) {
	                addImage(wb, sheet, imgPath, rowIndex - 1, excelColIndx - 1);
	                symbol = null;
	            } else {
	                cell.setCellValue(Double.parseDouble(cellValue));
	            }
	            
	            if (transpose) {
	                rowIndex++;
	            }
	        }
	        if (transpose) {
	            tblColIndx++;
	            excelColIndx++;
	        }
	    }
	    
	    if (tableModel.has("removeRow") && transpose) {
	        removeRow(sheet, totHeaders, rowIndex, excelColIndx);
	    }
	    
	    if (groupBy && !firstFlag && groupByFormula != null) {
	        addGroupByFormula(sheet, rowIndex++, excelColIndx, grpByRowIndx + 2);
	    }
	    setColumnWidths(sheet, jsonColModelArr);
	    
	    formatSheet(wb, sheet, excelColIndx, rowIndex);
	    
	    styles = null;
	    return wb;
	}
	private int removeRow(Sheet sheet, int dataStartRow, int totalRow, int totalCol) {
		JSONObject removeValObj = null;
		int rowsRemoved = 0;
		Integer rmFromCol = null, rmToCol = null;
		String rmValue = null;

		String[] rmValueArr = null;

		removeValObj = tableModel.getJSONObject("removeRow");
		rmFromCol = removeValObj.getInt("fromCol");
		rmToCol = removeValObj.getInt("toCol");
		rmValue = removeValObj.getString("value") + ",";
		rmValueArr = rmValue.split(",");
		int rowIndex = dataStartRow;
		for (int i = dataStartRow; i < totalRow; i++) {
			// Row row = sheet.getRow(rowIndex);
			Row row = sheet.getRow(i);
			boolean rmExist = true;
			for (int j = rmFromCol - 1; j < (rmToCol == -1 ? totalCol : rmToCol); j++) {
				// CommonMessage.debugMsg("j..."+row.getCell(j).toString());
				// String cellValue = row.getCell(j).getStringCellValue();
				String cellValue = row.getCell(j).toString();
				// CommonMessage.debugMsg("cellValue..."+cellValue);
				if (!checkValueExist(rmValueArr, cellValue)) {
					rmExist = false;
					break;
				}
			}
			if (rmExist && rmFromCol <= (rmToCol == -1 ? totalCol : rmToCol) && rmToCol != 0) {

				row.setZeroHeight(true);
				/*
				 * sheet.removeRow(row); int lastRowNum = sheet.getLastRowNum();
				 * sheet.shiftRows( rowIndex + 1, lastRowNum, -1);
				 */
				rowsRemoved++;
			} else {
				row.getCell(0).setCellValue((rowIndex - dataStartRow) + 1);
				rowIndex++;
			}
		}
		return rowsRemoved;

	}

	private boolean checkValueExist(String[] valueList, String value) {
		for (String s : valueList)
			if (s.equals(value) || (value != null && value.isEmpty()))
				return true;

		return false;
	}

	private void setConditionalFormats(Sheet sheet, Workbook wb, String rptType, int totHeaders, int totRows,
			int totCols) {

		Byte operator = null;
		if (this.xlCondFormat != null) {
			ConditionalFormattingRule rule = null;
			FontFormatting font = null;
			short fontColorIndex = 0;
			for (XLConditionalFormats condFormat : this.xlCondFormat) {

				SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();
				if (condFormat.getCondFormulaStr() != null)
					rule = sheetCF.createConditionalFormattingRule(condFormat.getCondFormulaStr());
				else {
					operator = getCondOperator(condFormat.getOperator());
					if (operator != null)
						rule = sheetCF.createConditionalFormattingRule(operator, condFormat.getCondValue());
				}

				if (condFormat.getFontColor() != null) {
					font = rule.createFontFormatting();
					// font.setFontStyle(true, false);
					if (REPORT_FORMAT_EXL_2003.equals(rptType)) {
						HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();
						palette.setColorAtIndex((short) (IndexedColors.PLUM.index + fontColorIndex),
								(byte) condFormat.getFontColor().red, (byte) condFormat.getFontColor().green, // RGB
																												// green
								(byte) condFormat.getFontColor().blue // RGB blue
						);
						font.setFontColorIndex((short) (IndexedColors.PLUM.index + fontColorIndex));
						fontColorIndex++;
					} else {
						java.awt.Color awtColor = new java.awt.Color((int) condFormat.getFontColor().red,
								(int) condFormat.getFontColor().green, (int) condFormat.getFontColor().blue);
						((XSSFFont) font).setColor(new XSSFColor(awtColor, new DefaultIndexedColorMap()));
//						((XSSFFont)font).setColor(new XSSFColor(new java.awt.Color(
//								(int)condFormat.getFontColor().red ,
//								(int)condFormat.getFontColor().green, 
//								(int)condFormat.getFontColor().blue)) );

					}

					// font.setFontColorIndex(new XSSFColor(new java.awt.Color(0, 255, 0)));
				}
				if (condFormat.getBgColor() != null) {
					PatternFormatting patternFmt = rule.createPatternFormatting();
					if (REPORT_FORMAT_EXL_2003.equals(rptType)) {
						HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();
						palette.setColorAtIndex((short) (IndexedColors.CORNFLOWER_BLUE.index + fontColorIndex),
								(byte) condFormat.getBgColor().red, (byte) condFormat.getBgColor().green, // RGB green
								(byte) condFormat.getBgColor().blue // RGB blue

						);

						patternFmt
								.setFillForegroundColor((short) (IndexedColors.CORNFLOWER_BLUE.index + fontColorIndex));
						patternFmt.setFillPattern(FillPatternType.SOLID_FOREGROUND.getCode());
						fontColorIndex++;
					} else {
						java.awt.Color bgColor = new java.awt.Color((int) condFormat.getBgColor().red,
								(int) condFormat.getBgColor().green, (int) condFormat.getBgColor().blue);

						// ✅ Use modern constructor with color map
						XSSFColor poiBgColor = new XSSFColor(bgColor, new DefaultIndexedColorMap());

						// ✅ Apply color and pattern safely
						((XSSFCellStyle) patternFmt).setFillForegroundColor(poiBgColor);
						patternFmt.setFillPattern(FillPatternType.SOLID_FOREGROUND.getCode());
//		        		((XSSFCellStyle) patternFmt).setFillForegroundColor(new XSSFColor(new java.awt.Color(
//								(int)condFormat.getBgColor().red ,
//								(int)condFormat.getBgColor().green, 
//								(int)condFormat.getBgColor().blue)) );
//		        		
//		        		patternFmt.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					}
				}

				String colStartChar = getCharForExcelCol(condFormat.getFromCol());
				String colEndChar = getCharForExcelCol(condFormat.getToCol() == -1 ? totCols : condFormat.getToCol());

				CellRangeAddress[] regions = {
						CellRangeAddress.valueOf(colStartChar + totHeaders + ":" + colEndChar + totRows) };

				sheetCF.addConditionalFormatting(regions, rule);
			}
		}
	}

	private Byte getCondOperator(ComparisonOperator op) {

		if (op == ComparisonOperator.EQUAL) {
			return org.apache.poi.ss.usermodel.ComparisonOperator.EQUAL;
		} else if (op == ComparisonOperator.GE) {
			return org.apache.poi.ss.usermodel.ComparisonOperator.GE;
		} else if (op == ComparisonOperator.LE) {
			return org.apache.poi.ss.usermodel.ComparisonOperator.LE;
		} else if (op == ComparisonOperator.LT) {
			return org.apache.poi.ss.usermodel.ComparisonOperator.LT;
		} else if (op == ComparisonOperator.GT) {
			return org.apache.poi.ss.usermodel.ComparisonOperator.GT;
		} else if (op == ComparisonOperator.NOT_EQUAL) {
			return org.apache.poi.ss.usermodel.ComparisonOperator.NOT_EQUAL;
		} else
			return null;
	}

	public static void formatSheet(Workbook wb, Sheet sheet, int excelColIndx, int rowIndex) {

		CellRangeAddress region = CellRangeAddress.valueOf("A1:" + getCharForExcelCol(excelColIndx) + rowIndex);
//        final short borderMediumDashed = CellStyle.BORDER_THICK;
		BorderStyle borderStyle = BorderStyle.THICK;
		RegionUtil.setBorderBottom(borderStyle, region, sheet);
		RegionUtil.setBorderTop(borderStyle, region, sheet);
		RegionUtil.setBorderLeft(borderStyle, region, sheet);
		RegionUtil.setBorderRight(borderStyle, region, sheet);
//        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet, wb );
//        RegionUtil.setBorderTop( borderMediumDashed,region, sheet, wb );
//        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet, wb );
//        RegionUtil.setBorderRight( borderMediumDashed, region, sheet, wb );

		RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
		RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
		RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
		RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);

		// sheet.wprotectWorkbook("admin", "admin");
		// sheet.protectSheet("admin");

		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);

		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);
		return;

	}

	private void addGroupByFormula(Sheet sheet, int rowIndx, int totCols, int fromRowIndx) {
		Row row = sheet.createRow(rowIndx);
		Cell cell = null;
		// Integer startColindx = 256;
		for (int i = 0; i < totCols; i++) {
			cell = row.createCell(i);

			setGroupFormula(cell, i + 1, fromRowIndx);
		}

	}

	private void setGroupFormula(Cell cell, int colNum, int fromRowIndx) {

		String formula = null;
		String range = null, colChar = null;

		for (XLConditionalFormats formulaObj : this.groupByFormula) {

			if (formulaObj.getFontName() != null && formulaObj.getFromCol() <= colNum
					&& (formulaObj.getToCol() >= colNum || formulaObj.getToCol() == -1)) {
				colChar = getCharForExcelCol(colNum);
				range = colChar + fromRowIndx + ":" + colChar + cell.getRowIndex();
				formula = formulaObj.getCondFormulaStr();
				formula = formula.replace("?", range);
//				cell.setCellType(Cell.CELL_TYPE_FORMULA);	
				cell.setCellType(CellType.FORMULA);

				cell.setCellStyle(this.styles
						.get(formulaObj.getFromCol() + "" + formulaObj.getToCol() + formulaObj.getIdentfier()));
				cell.setCellFormula(formula);
				break;
			}

		}
	}

	private void setGroupByRowCell(int cols, Row grpByRow, String curGrpVal, CellStyle style, Sheet sheet,
			int grpByRowIndx) {
		Cell cell = null;
		for (int i = 0; i < cols; i++) {
			cell = grpByRow.createCell(i);
			if (i == 0) {
				cell.setCellValue(curGrpVal);
			}
			cell.setCellStyle(style);
		}

		CellRangeAddress region = CellRangeAddress
				.valueOf("A" + grpByRowIndx + ":" + getCharForExcelCol(cols) + grpByRowIndx);
		sheet.addMergedRegion(region);
	}

	/**
	 * cell styles used for formatting sheets
	 */
	private Map<String, CellStyle> createStyles(Workbook wb) {

		return defaultStyles(wb);
	}

	/*
	 * private Map<String, CellStyle> createStyles(String format) { Map<String,
	 * CellStyle> styles = defaultStyles(wb);
	 * 
	 * short colorIndex = 0; if (this.condFormats != null) { colorIndex =
	 * populateCustomStyles(styles, this.condFormats, format, colorIndex); } if
	 * (this.groupByFormula != null) { colorIndex = populateCustomStyles(styles,
	 * this.groupByFormula, format, colorIndex); }
	 * 
	 * return styles; }
	 */
	
	
	private Map<String, CellStyle> createStyles(String format) {
	    Map<String, CellStyle> styles = defaultStyles(wb);
	    
	    short colorIndex = 0;
	    if (this.condFormats != null) {
	        colorIndex = populateCustomStyles(styles, this.condFormats, format, colorIndex);
	    }
	    if (this.groupByFormula != null) {
	        colorIndex = populateCustomStyles(styles, this.groupByFormula, format, colorIndex);
	    }
	    
	    CellStyle styleGreen = wb.createCellStyle();
	    styleGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    styleGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    setBorders(styleGreen);
	    
	    CellStyle styleRed = wb.createCellStyle();
	    styleRed.setFillForegroundColor(IndexedColors.RED.getIndex());
	    styleRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    setBorders(styleRed);
	    
	    CellStyle styleYellow = wb.createCellStyle();
	    styleYellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
	    styleYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    setBorders(styleYellow);
	    
	    CellStyle styleOrange = wb.createCellStyle();
	    styleOrange.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
	    styleOrange.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    setBorders(styleOrange);
	    
	    styles.put("STAGE_GREEN", styleGreen);
	    styles.put("STAGE_RED", styleRed);
	    styles.put("STAGE_YELLOW", styleYellow);
	    styles.put("STAGE_ORANGE", styleOrange);
	    
	    // Add green background styles for equipment rows with different alignments
	    CellStyle equipmentGreenLeft = wb.createCellStyle();
	    equipmentGreenLeft.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    equipmentGreenLeft.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    equipmentGreenLeft.setAlignment(HorizontalAlignment.LEFT);
	    equipmentGreenLeft.setVerticalAlignment(VerticalAlignment.CENTER);
	    setBorders(equipmentGreenLeft);
	    
	    CellStyle equipmentGreenCenter = wb.createCellStyle();
	    equipmentGreenCenter.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    equipmentGreenCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    equipmentGreenCenter.setAlignment(HorizontalAlignment.CENTER);
	    equipmentGreenCenter.setVerticalAlignment(VerticalAlignment.CENTER);
	    setBorders(equipmentGreenCenter);
	    
	    CellStyle equipmentGreenRight = wb.createCellStyle();
	    equipmentGreenRight.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    equipmentGreenRight.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    equipmentGreenRight.setAlignment(HorizontalAlignment.RIGHT);
	    equipmentGreenRight.setVerticalAlignment(VerticalAlignment.CENTER);
	    setBorders(equipmentGreenRight);
	    
	    styles.put("EQUIPMENT_GREEN_LEFT", equipmentGreenLeft);
	    styles.put("EQUIPMENT_GREEN_CENTER", equipmentGreenCenter);
	    styles.put("EQUIPMENT_GREEN_RIGHT", equipmentGreenRight);
	    
	    return styles;
	}

	// Helper method to apply borders to cell styles
	private void setBorders(CellStyle style) {
	    style.setBorderTop(BorderStyle.THIN);
	    style.setBorderBottom(BorderStyle.THIN);
	    style.setBorderLeft(BorderStyle.THIN);
	    style.setBorderRight(BorderStyle.THIN);
	    style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	}

	private short populateCustomStyles(Map<String, CellStyle> styles, List<XLConditionalFormats> condFormats,
			String format, short colorIndex) {
		CellStyle style = null;

		for (XLConditionalFormats condFormat : condFormats) {
			//// CommonMessage.debugMsg(" condFormat.getFontName() "
			//// +condFormat.getFontName());
			if (condFormat.getFontName() != null) {
				Font dataFont = this.wb.createFont();
				dataFont.setFontName(condFormat.getFontName());
				if (condFormat.getFontHeightPoint() != null)
					dataFont.setFontHeightInPoints(condFormat.getFontHeightPoint());

				style = this.wb.createCellStyle();
				if (condFormat.getFontBoldWeight() != null)
					dataFont.setBold(true);
//		        	dataFont.setBoldweight(condFormat.getFontBoldWeight()); 

				if (condFormat.getFontColor() != null) {
					if (REPORT_FORMAT_EXL_2003.equals(format)) {
						HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();
						palette.setColorAtIndex((short) (IndexedColors.PLUM.index + colorIndex),
								(byte) condFormat.getFontColor().red, (byte) condFormat.getFontColor().green, // RGB
																												// green
								(byte) condFormat.getFontColor().blue // RGB blue
						);
						dataFont.setColor((short) (IndexedColors.PLUM.index + colorIndex));
						colorIndex++;
					} else {
						java.awt.Color awtColor = new java.awt.Color((int) condFormat.getFontColor().red,
								(int) condFormat.getFontColor().green, (int) condFormat.getFontColor().blue);
						((XSSFFont) dataFont).setColor(new XSSFColor(awtColor, new DefaultIndexedColorMap()));
//			        	((XSSFFont)dataFont).setColor(new XSSFColor(new java.awt.Color(
//													(int)condFormat.getFontColor().red ,
//													(int)condFormat.getFontColor().green, 
//													(int)condFormat.getFontColor().blue)) );
//			        	
//			        }
					}
					if (condFormat.getBgColor() != null) {

						if (REPORT_FORMAT_EXL_2003.equals(format)) {
							HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();
							palette.setColorAtIndex((short) (IndexedColors.CORNFLOWER_BLUE.index + colorIndex),
									(byte) condFormat.getBgColor().red, (byte) condFormat.getBgColor().green, // RGB
																												// green
									(byte) condFormat.getBgColor().blue // RGB blue

							);

							style.setFillForegroundColor((short) (IndexedColors.CORNFLOWER_BLUE.index + colorIndex));
							style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
							colorIndex++;
						} else {

							Color bg = new Color((int) condFormat.getBgColor().red, (int) condFormat.getBgColor().green,
									(int) condFormat.getBgColor().blue);

							XSSFCellStyle xssfStyle = (XSSFCellStyle) style;
							xssfStyle.setFillForegroundColor(new XSSFColor(bg, null));

//		        		((XSSFCellStyle) style).setFillForegroundColor(new XSSFColor(new java.awt.Color(
//								(int)condFormat.getBgColor().red ,
//								(int)condFormat.getBgColor().green, 
//								(int)condFormat.getBgColor().blue)) );

							style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
						}

					}
					style.setAlignment(HorizontalAlignment.CENTER);
					style.setVerticalAlignment(VerticalAlignment.CENTER);
					style.setFont(dataFont);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setWrapText(true);

					String ident = condFormat.getFromCol() + "" + condFormat.getToCol() + condFormat.getIdentfier();
					styles.put(ident, style);
				}
			}
		}
		return colorIndex;
	}

	private String getFontStyleIdentfier(int colNum) {
		if (this.condFormats != null) {
			for (XLConditionalFormats condFormat : condFormats) {
				if (condFormat.getFontName() != null && condFormat.getFromCol() <= colNum
						&& condFormat.getToCol() >= colNum) {
					return condFormat.getFromCol() + "" + condFormat.getToCol();
				}
			}
		}
		return null;
	}

	private String getFontStyleIdentfier(int colNum, String value) throws SQLException {
		if (this.condFormats != null) {
			for (XLConditionalFormats condFormat : condFormats) {
				if (condFormat.getDbChkColIndx() != null && condFormat.getDbChkColIndx() >= 0)
					value = getValueFromResultSet(rsmd, rs, condFormat.getDbChkColIndx());

				if (condFormat.getFontName() != null && condFormat.getFromCol() <= colNum
						&& (condFormat.getToCol() >= colNum || condFormat.getToCol() == -1)
						&& checkCondValue(condFormat.getOperator(), value, condFormat.getCondValue())) {
					// CommonMessage.debugMsg(" colNum " + colNum + " : " +
					// condFormat.getFromCol()+ "" +
					// condFormat.getToCol()+condFormat.getIdentfier());
					return condFormat.getFromCol() + "" + condFormat.getToCol() + condFormat.getIdentfier();
				}
			}
		}
		return null;
	}

	/*
	 * private String getSymbolStr(int colNum, String value){ if( this.condFormats
	 * != null ) { for( XLConditionalFormats condFormat : condFormats){
	 * 
	 * if( condFormat.getSymbolStr() != null && condFormat.getFontName() != null &&
	 * condFormat.getFromCol() <= colNum && ( condFormat.getToCol() >= colNum ||
	 * condFormat.getToCol() == -1 ) &&
	 * checkCondValue(condFormat.getOperator(),value,condFormat.getCondValue() ) ) {
	 * return condFormat.getSymbolStr(); } } } return null; }
	 */

	private XLConditionalFormats getSymbolStr(int colNum, String value) {
		if (this.condFormats != null) {
			for (XLConditionalFormats condFormat : condFormats) {

				if (condFormat.getSymbolStr() != null && condFormat.getFontName() != null
						&& condFormat.getFromCol() <= colNum
						&& (condFormat.getToCol() >= colNum || condFormat.getToCol() == -1)
						&& checkCondValue(condFormat.getOperator(), value, condFormat.getCondValue())) {
					return condFormat;// condFormat.getSymbolStr();
				}
			}
		}
		return null;
	}

	private Boolean checkCondValue(ComparisonOperator op, String checkValue, String condValue) {

		// CommonMessage.debugMsg(" condValue " + condValue + " checkValue " +
		// checkValue);
		if (op == ComparisonOperator.EQUAL) {
			return (condValue != null ? condValue.equals(checkValue) ? true : false : false);
		}

		else if (op == ComparisonOperator.CONTAINS) {
			return (condValue != null ? condValue.contains(checkValue) ? true : false : false);
		} else if (op == ComparisonOperator.GE) {
			if (Pattern.matches("^\\d*\\.?\\d+$", checkValue) && Pattern.matches("^\\d*\\.?\\d+$", condValue)) {
				return Double.parseDouble(checkValue) >= Double.parseDouble(condValue);
			}
		} else if (op == ComparisonOperator.LE) {
			if (Pattern.matches("^\\d*\\.?\\d+$", checkValue) && Pattern.matches("^\\d*\\.?\\d+$", condValue)) {
				return Double.parseDouble(checkValue) <= Double.parseDouble(condValue);
			}
		} else if (op == ComparisonOperator.LT) {
			if (Pattern.matches("^\\d*\\.?\\d+$", checkValue) && Pattern.matches("^\\d*\\.?\\d+$", condValue)) {
				return Double.parseDouble(checkValue) < Double.parseDouble(condValue);
			}
		} else if (op == ComparisonOperator.GT) {
			if (Pattern.matches("^\\d*\\.?\\d+$", checkValue) && Pattern.matches("^\\d*\\.?\\d+$", condValue)) {
				return Double.parseDouble(checkValue) > Double.parseDouble(condValue);
			}
		} else if (op == ComparisonOperator.NOT_EQUAL) {
			return (condValue != null ? condValue.equals(checkValue) ? null : true : null);
		}

		return false;

	}

	private Map<String, CellStyle> defaultStyles(Workbook wb) {
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		// short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

		CellStyle style;
		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 12);
//          header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		header1Font.setBold(true);
		header1Font.setColor(IndexedColors.BLACK.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_1_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header1Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_1, style);

		Font header2Font = wb.createFont();
		header2Font.setFontHeightInPoints((short) 8);
//          header2Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		header2Font.setBold(true);
		header2Font.setColor(IndexedColors.BLACK.getIndex());
		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_2_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header2Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_2, style);

		Font header3Font = wb.createFont();
//          header3Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		header3Font.setBold(true);
		header3Font.setFontHeightInPoints((short) 8);
		header3Font.setColor(IndexedColors.BLACK.getIndex());

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_3_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_3, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_4_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_4, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_5_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_5, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_6_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_6, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_7_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_7, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_8_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_HEADER_8, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_3_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header3Font);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_ROTATION, style);

		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short) 8);
		dataFont.setColor(IndexedColors.BLACK.getIndex());

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(dataFont);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_ALIGN_LEFT, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(dataFont);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_ALIGN_CENTER, style);

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(dataFont);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_ALIGN_RIGHT, style);

		/** Group by Row Style */
		Font grpupByRwFont = wb.createFont();
//          grpupByRwFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		grpupByRwFont.setBold(true);
		grpupByRwFont.setFontHeightInPoints((short) 10);
		grpupByRwFont.setColor(IndexedColors.BLACK.getIndex());

		style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(grpupByRwFont);
		style.setFillForegroundColor(STYLE_GROUPBY_ROW_COLOR);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setWrapText(true);
		styles.put(STYLE_IDENTIFIER_GROUPBY_ROW, style);

		return styles;
	}

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",Locale.ENGLISH);
		return sdf.format(cal.getTime());

	}

	public static void writeToResponse(HttpServletResponse response, Workbook wb, String fileName, String fileType)
			throws IOException {
		String timeNow = now();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "_" + timeNow + "." + fileType);
		response.setHeader("Pragma", "no-cache");
		// response.setHeader("Cache-Control", "no-cache");
		/**
		 * the Above line commented and the next line added so local excel template can
		 * be opened in IE
		 **/
		response.setHeader("Cache-Control", "private");
		// Set the Header Info to tell the browser the format of the file.
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		// response.addHeader("Content-Disposition", "inline; filename="+fileName);
		ServletOutputStream out = response.getOutputStream();

		wb.write(out);
		out.flush();
		out.close();
		wb = null;
	}

	public static void writeToExcelViewResponse(HttpServletResponse response, Workbook wb, String fileName,
			String fileType) throws IOException {
		// String timeNow = now();
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "" + fileType);
		response.setHeader("Pragma", "no-cache");
		// response.setHeader("Cache-Control", "no-cache");
		/**
		 * the Above line commented and the next line added so local excel template can
		 * be opened in IE
		 **/
		response.setHeader("Cache-Control", "private");
		// Set the Header Info to tell the browser the format of the file.
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		// response.addHeader("Content-Disposition", "inline; filename="+fileName);
		CommonMessage.debugMsg("Excel Extension" + fileName + "_" + fileType);
		ServletOutputStream out = response.getOutputStream();

		wb.write(out);
		out.flush();
		out.close();
		wb = null;
	}

	public static String getFormat(HttpServletRequest request) {
		String format = request.getParameter(ReqtParamNameConst.EXPORT_EXL_FORMAT);
		// CommonMessage.debugMsg(" format " + format);
		if (format != null && format.equals("7"))
			return REPORT_FORMAT_EXL_2007;

		return REPORT_FORMAT_EXL_2003;
	}

	public void setCondFormats(List<XLConditionalFormats> condFormats) {
		this.condFormats = condFormats;
	}

	public List<XLConditionalFormats> getCondFormats() {
		return condFormats;
	}

	public void setXlCondFormat(List<XLConditionalFormats> xlCondFormat) {
		this.xlCondFormat = xlCondFormat;
	}

	public List<XLConditionalFormats> getXlCondFormat() {
		return xlCondFormat;
	}

//	private void testChart() throws IOException{
//		Workbook wb = new XSSFWorkbook();
//        Sheet sheet = wb.createSheet("Sheet 1");
//        final int NUM_OF_ROWS = 3;
//        final int NUM_OF_COLUMNS = 10;
//
//        // Create a row and put some cells in it. Rows are 0 based.
//        Row row;
//        Cell cell;
//        for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
//            row = sheet.createRow((short) rowIndex);
//            for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
//                cell = row.createCell((short) colIndex);
//                cell.setCellValue(colIndex * (rowIndex + 1));
//            }
//        }
//
//        Drawing drawing = sheet.createDrawingPatriarch();
//        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);
//
//        Chart chart = drawing.createChart(anchor);
//        ChartLegend legend = chart.getOrCreateLegend();
//        legend.setPosition(LegendPosition.TOP_RIGHT);
//
//        ScatterChartData data = chart.getChartDataFactory().createScatterChartData();
//
//        ValueAxis bottomAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM);
//        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
//        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
//
//        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
//        ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
//        ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));
//
//
//        data.addSerie(xs, ys1);
//        data.addSerie(xs, ys2);
//
//        chart.plot( data, bottomAxis, leftAxis);
//
//        // Write the output to a file
//        FileOutputStream fileOut = new FileOutputStream("ooxml-scatter-chart.xlsx");
//        wb.write(fileOut);
//        fileOut.close();
//	}
//	
	/*
	 * private void testChart(Sheet sheet){ Drawing drawing =
	 * sheet.createDrawingPatriarch(); ClientAnchor anchor = drawing.createAnchor(0,
	 * 0, 0, 0, 0, 5, 10, 15);
	 * 
	 * Chart chart = drawing.createChart(anchor);
	 * 
	 * 
	 * HSSFChart.getSheetCharts(arg0) HSSFChartType. ChartLegend legend =
	 * chart.getOrCreateLegend(); legend.setPosition(LegendPosition.TOP_RIGHT);
	 * 
	 * ScatterChartData data = chart.getChartDataFactory().createScatterChartData();
	 * 
	 * ValueAxis bottomAxis =
	 * chart.getChartAxisFactory().createValueAxis(AxisPosition.BOTTOM); ValueAxis
	 * leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
	 * leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
	 * 
	 * ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new
	 * CellRangeAddress(0, 0, 0, 5)); ChartDataSource<Number> ys1 =
	 * DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, 5));
	 * ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new
	 * CellRangeAddress(2, 2, 0, 5));
	 * 
	 * data.addSerie(xs, ys1); data.addSerie(xs, ys2);
	 * 
	 * chart.plot(data, bottomAxis, leftAxis); HSSFChart s = new HSSFChart();
	 * s.createSeries().s }
	 */

	public Workbook writeToExcelNoColModel(ResultSet rs, String reportType, int fromRow)
			throws IOException, SQLException {

		Workbook wb = reportType.equals(REPORT_FORMAT_EXL_2007) ? new SXSSFWorkbook(100) : new HSSFWorkbook(); // keep
																												// 100
																												// rows
																												// in
																												// memory,
																												// exceeding
																												// rows
																												// will
																												// be
																												// flushed
																												// to
																												// disk

		Sheet sheet = wb.createSheet();
		String cellValue = null;

		sheet.setDisplayGridlines(false);

		this.rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		int dataIndex = -1;

		styles = createStyles(wb);
		Row headerRow = sheet.createRow(0);
		Cell hCell = headerRow.createCell(0);
		hCell.setCellStyle(styles.get(STYLE_IDENTIFIER_HEADER_1));
		hCell.setCellValue("Sl.No");
		sheet.setColumnWidth(0, 100 * 30);

		for (int i = 1; i <= colCount; i++) {

			hCell = headerRow.createCell(i);
			hCell.setCellStyle(styles.get(STYLE_IDENTIFIER_HEADER_1));
			hCell.setCellValue(rsmd.getColumnName(i));

			sheet.setColumnWidth(i, 100 * 30);

		}

		int rowIndex = 1;
		int slNo = 1;
		int excelColIndx = 0;
		String cellAlign = null;

		Cell cell = null;
		// CellRangeAddress region = null;
		String symbol = null;
		Row row = null;

		while (rs.next()) {

			dataIndex++;
			row = sheet.createRow(rowIndex++);
			excelColIndx = 0;
			cell = row.createCell(excelColIndx);
			cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
			cell.setCellValue(slNo++);
			excelColIndx++;

			for (int cellnum = 0; cellnum < colCount; cellnum++) {
				cell = row.createCell(excelColIndx);
				excelColIndx++;

				cellValue = getValueFromResultSet(rsmd, rs, cellnum);
				cellAlign = STYLE_IDENTIFIER_ALIGN_LEFT;

				/*
				 * if( this.condFormats != null && (cellAlign =
				 * getFontStyleIdentfier(excelColIndx , cellValue))!= null ); else
				 * if(colModel.has("align") && (cellAlign = colModel.getString("align") ) !=
				 * null && cellAlign.equals("right") ) cellAlign = STYLE_IDENTIFIER_ALIGN_RIGHT;
				 * else if( cellAlign != null && cellAlign.equals("center")) cellAlign =
				 * STYLE_IDENTIFIER_ALIGN_CENTER; else cellAlign = STYLE_IDENTIFIER_ALIGN_LEFT;
				 */
				cell.setCellStyle(styles.get(cellAlign));
				// CommonMessage.debugMsg(" cellValue " +cellValue );
				boolean isNumeric = Pattern.matches("^\\d*$", cellValue);

				if (!isNumeric || symbol != null || cellValue == null || cellValue.isEmpty())
					cell.setCellValue(symbol == null ? cellValue : symbol);
				else
					cell.setCellValue(Double.parseDouble(cellValue));

			}
		}

		// setColumnWidths(sheet,jsonColModelArr);

		formatSheet(wb, sheet, excelColIndx, rowIndex);
		// if( this.xlCondFormat != null)
		// setConditionalFormats(sheet,wb,reportType,totHeaders+1,rowIndex,tblColIndx);

		// wb.getSheetAt(0).getSheetConditionalFormatting()

		// FileOutputStream out = new FileOutputStream("testSxssf.xlsx");
		// wb.write(out);
		// out.close();

		styles = null;
		return wb;
	}

	public static void setRowHeight(Workbook wb, Sheet sheet, int row, int colNo, String strVal, int rowWidth) {
		String disDet = strVal.trim();
		String entStr = disDet.replaceAll("\n+", "\n");
		float disDetLen = disDet.length();
		int enterCnt = StringUtils.countMatches(entStr, "\n");
		if (disDetLen < rowWidth)
			disDetLen = (float) 1.5;
		else
			disDetLen = disDetLen / 40;
		disDetLen = 20 * (disDetLen + enterCnt);
		// sheet.getRow(strRow+g).setHeight((short) disDetLen);
		sheet.getRow(row).setHeightInPoints(disDetLen);
		/*
		 * if (colNo>0) { Cell cell = sheet.getRow(row).getCell(colNo); CellStyle cs =
		 * wb.createCellStyle(); cs.setWrapText(true); cell.setCellStyle(cs); }
		 */
	}

	public void genExlMailSend(ResultSet rs, SchedulerConfigMst schedulerConfigMst, String filePath) throws Exception {

		CommonMessage.debugMsg("createTableObjectn B4");

		tableModel = createTableObject(rs, schedulerConfigMst);

		CommonMessage.debugMsg("createTableObjectn after");
		this.rs = rs;
		JSONArray jsonColModelArr = tableModel.getJSONArray("colModel");

		int colModelLength = jsonColModelArr.length();
		boolean transpose = false;

		if (tableModel.has("transpose")) {
			transpose = tableModel.getBoolean("transpose");
		}
		String reportType = REPORT_FORMAT_EXL_2007;
		// this.wb = reportType.equals(REPORT_FORMAT_EXL_2007) ? new SXSSFWorkbook(
		// (transpose ? -1:100)):new HSSFWorkbook(); // keep 100 rows in memory,
		// exceeding rows will be flushed to disk
		this.wb = reportType.equals(REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook() : new HSSFWorkbook(); // keep 100 rows
																										// in memory,
																										// exceeding
																										// rows will be
																										// flushed to
																										// disk

		Sheet oSheet = wb.createSheet();
		String cellValue = null;
		this.wb.setSheetName(0, schedulerConfigMst.getReportName());

		oSheet.setDisplayGridlines(false);

		this.rsmd = rs.getMetaData();
		int colCount = rsmd.getColumnCount();
		int dataIndex = schedulerConfigMst.getXlRptStartRowIndx() - 1, grpByRowIndx = 0;

		JSONObject colModel = null;
		Boolean groupBy = false;
		String groupByField = null, prevGrpVal = null, curGrpVal = null;
		int groupByColIndex = -1;
		if (tableModel.has("groupBy")) {
			groupBy = tableModel.getBoolean("groupBy");
			if (groupBy && tableModel.has("groupByField")) {
				groupBy = true;
				groupByField = tableModel.getString("groupByField");
			}
		}

		CommonMessage.debugMsg("groupByField" + groupByField);

		styles = createStyles(reportType);

		int totHeaders = setColHeaders(wb, oSheet, tableModel);
		int rowIndex = totHeaders;
		int slNo = 1;
		int excelColIndx = 0, tblColIndx = 0;
		String cellAlign = null;
		Row grpByRow = null;
		Cell cell = null;
		// CellRangeAddress region = null;
		boolean firstFlag = true;
		String symbol = null;
		String imgPath = null;
		Row row = null;
		XLConditionalFormats condFormat = null;
		int fromCol = schedulerConfigMst.getXlRptStartColIndx();
		int fromRow = schedulerConfigMst.getXlRptStartRowIndx();

		int compCIndx = schedulerConfigMst.getGroupBy1ColIndx();
		int compCG2Indx = schedulerConfigMst.getGroupBy2ColIndx();
		int toMCIndx = schedulerConfigMst.getToMailColIndx();
		int ccMCIndx = schedulerConfigMst.getCcMailColIndx();
		int empNIndx = schedulerConfigMst.getEmpNameColIndx();
		String prevCVal = null;
		String prevCG2Val = null;

		String currentCVal = null;
		String currentCG2Val = null;
		String toMailAddress = null;
		String ccMailAddress = null;
		String empName = null;
		Workbook rptWb = null;
		Sheet sheet = null;
		ByteArrayOutputStream f = new ByteArrayOutputStream();
		wb.write(f);

		StringBuilder fileName = new StringBuilder();
		FileOutputStream out = null;

		while (rs.next()) {

			dataIndex++;
			if (dataIndex < fromRow)
				continue;

			currentCVal = rs.getString(compCIndx);

			// CommonMessage.debugMsg("currentCVal "+currentCVal );

			if (!currentCVal.equals(prevCVal)) {

				if (rptWb != null) {

					if (tableModel.has("removeRow") && transpose) {
						removeRow(sheet, totHeaders, rowIndex, excelColIndx);
					}

					if (groupBy && !firstFlag && groupByFormula != null) {
						addGroupByFormula(sheet, rowIndex++, excelColIndx, grpByRowIndx + 2);
					}
					setColumnWidths(sheet, jsonColModelArr);

					formatSheet(rptWb, sheet, excelColIndx, rowIndex);
					fileName.setLength(0);
					fileName.append(filePath).append(schedulerConfigMst.getReportName())
							.append(empName.replaceAll(" ", "")).append("_").append(now()).append(".xls");
					CommonMessage.debugMsg(" fileName" + fileName);

					out = new FileOutputStream(fileName.toString());
					rptWb.write(out);
					out.close();
					out = null;
					try {
						SchedulerLog.info("Sending mail to " + toMailAddress);
						SchedulerLog.info("Sending mail CC " + ccMailAddress);

						List<String> attachments = new ArrayList<String>();
						attachments.add(fileName.toString());
						StringBuffer mailContent = new StringBuffer();
						mailContent.append("This is system generated mail.Please don't reply to this mail.");
						mailContent.append(" For any further assistance please contact Perfex Support Team.");
						CommonMessage.debugMsg(" mailContent mailContent  " + mailContent);
						SchedulerLog.info(" mailContent mailContent  " + mailContent);
						// notesMailClient.send(toMailAddress, ccMailAddress,
						// schedulerConfigMst.getSubject()+" as on date : " +
						// CommonFunctions.getDate(),mailContent.toString(), attachments);
						mailContent.append("Regards.");
						mailContent.append("Perfex application support team.");

					} /*
						 * catch (Exception e) { // TODO Auto-generated catch block
						 * SchedulerLog.info("Mail Not Send to  " + toMailAddress,e ); }
						 */ catch (Exception e) {
						// e.printStackTrace();
						SchedulerLog.info("Mail Not Send to  " + toMailAddress, e);
					}
					excelColIndx = 0;
					rowIndex = totHeaders;
					grpByRowIndx = 0;
					tblColIndx = 0;
					slNo = 1;
				}
				CommonMessage.debugMsg(ccMCIndx + "  toMCIndxtoMCIndx " + toMCIndx);

				toMailAddress = rs.getString(toMCIndx);
				ccMailAddress = rs.getString(ccMCIndx);
				empName = rs.getString(empNIndx);

				// CommonMessage.debugMsg(" toMailAddress " + toMailAddress );
				// CommonMessage.debugMsg(" ccMailAddress " + ccMailAddress );
				// CommonMessage.debugMsg(" empName " + empName );
				ByteArrayInputStream in = new ByteArrayInputStream(f.toByteArray());
				rptWb = null;
				rptWb = WorkbookFactory.create(in);
				in.close();
				in = null;
				// CommonMessage.debugMsg(" empName --- " );
				sheet = null;
				sheet = rptWb.getSheetAt(0);

				styles = createStyles(rptWb);
			}

			// added by babu 27-04-2015
			currentCG2Val = rs.getString(compCG2Indx);
			// CommonMessage.debugMsg("currentCG2Val=="+currentCG2Val);
			if (!currentCG2Val.equals(prevCG2Val)) {
				// CommonMessage.debugMsg("prevCG2Val=="+prevCG2Val);
				// CommonMessage.debugMsg("excelColIndx=="+excelColIndx+"currentCG2Val=="+currentCG2Val+"grpByRowIndx=="+grpByRowIndx);
				grpByRowIndx = rowIndex;
				CommonMessage.debugMsg("rowIndex==" + rowIndex);
				grpByRow = sheet.createRow(rowIndex++);
				CommonMessage.debugMsg("grpByRow==" + rowIndex);
				if (excelColIndx == 0)
					excelColIndx = schedulerConfigMst.getXlRptStartColIndx() - 1;
				CommonMessage.debugMsg("excelColIndx==" + excelColIndx + "currentCG2Val==" + currentCG2Val);
				// CommonMessage.debugMsg("excelColIndx=="+excelColIndx);
				setGroupByRowCell(excelColIndx, grpByRow, currentCG2Val, styles.get(STYLE_IDENTIFIER_GROUPBY_ROW),
						sheet, (grpByRowIndx + 1));
			}

			prevCG2Val = currentCG2Val;

			// CommonMessage.debugMsg("groupBy=="+groupBy);
			if (groupBy) {
				if (groupByColIndex != -1) {
					if (curGrpVal != null && !curGrpVal.equals(prevGrpVal) && firstFlag) {
						setGroupByRowCell(excelColIndx, grpByRow, curGrpVal, styles.get(STYLE_IDENTIFIER_GROUPBY_ROW),
								sheet, (grpByRowIndx + 1));
						firstFlag = false;
					}
					prevGrpVal = curGrpVal;
					curGrpVal = getValueFromResultSet(rsmd, rs, groupByColIndex);
				}
				if (prevGrpVal == null || !curGrpVal.equals(prevGrpVal)) {
					if (!firstFlag && groupByFormula != null) {
						addGroupByFormula(sheet, rowIndex++, excelColIndx, grpByRowIndx + 2);
					}
					grpByRowIndx = rowIndex;
					slNo = 1;
					grpByRow = sheet.createRow(rowIndex++);
					if (!firstFlag)
						setGroupByRowCell(excelColIndx, grpByRow, curGrpVal, styles.get(STYLE_IDENTIFIER_GROUPBY_ROW),
								sheet, (grpByRowIndx + 1));
				}
			}
			if (!transpose) {
				row = sheet.createRow(rowIndex++);
				// CommonMessage.debugMsg(" this.rowHeight " + this.rowHeight);
				if (this.rowHeight > -1)
					row.setHeightInPoints(this.rowHeight);

				excelColIndx = 0;
				tblColIndx = 0;
				cell = row.createCell(excelColIndx);
				cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
				cell.setCellValue(slNo++);
				excelColIndx++;
			} else {
				rowIndex = totHeaders;
				colModel = jsonColModelArr.getJSONObject(tblColIndx);
				if (colModel.has("hidden") && colModel.getBoolean("hidden")) {
					tblColIndx++;
					continue;
				}
			}

			for (int cellnum = fromCol; cellnum < colCount; cellnum++) {
				if (colModelLength <= tblColIndx)
					break;

				if (!transpose)
					colModel = jsonColModelArr.getJSONObject(tblColIndx++);

				if (groupBy && groupByColIndex == -1
						&& (groupByField != null && groupByField.equals(colModel.getString("index")))) {
					groupByColIndex = cellnum;
					curGrpVal = getValueFromResultSet(rsmd, rs, cellnum);
				}

				if (colModel.has("hidden") && colModel.getBoolean("hidden"))
					continue;

				if (transpose) {
					if (excelColIndx > 1)
						row = sheet.getRow(rowIndex);
					else {
						row = sheet.createRow((rowIndex));
						excelColIndx = 0;
						cell = row.createCell(excelColIndx);
						cell.setCellStyle(styles.get(STYLE_IDENTIFIER_ALIGN_RIGHT));
						cell.setCellValue(slNo++);
						excelColIndx++;
					}
				}
				cell = row.createCell(excelColIndx);
				if (!transpose)
					excelColIndx++;

				cellValue = getValueFromResultSet(rsmd, rs, cellnum);
				cellAlign = null;

				if (this.condFormats != null && (cellAlign = getFontStyleIdentfier(excelColIndx, cellValue)) != null)
					;
				else if (colModel.has("align") && (cellAlign = colModel.getString("align")) != null
						&& cellAlign.equals("right"))
					cellAlign = STYLE_IDENTIFIER_ALIGN_RIGHT;
				else if (cellAlign != null && cellAlign.equals("center"))
					cellAlign = STYLE_IDENTIFIER_ALIGN_CENTER;
				else
					cellAlign = STYLE_IDENTIFIER_ALIGN_LEFT;

				cell.setCellStyle(styles.get(cellAlign));
				condFormat = null;
				imgPath = null;
				symbol = null;

				if (this.condFormats != null) {
					condFormat = getSymbolStr(excelColIndx, cellValue);
					if (condFormat != null) {
						symbol = condFormat.getSymbolStr();
						imgPath = condFormat.getImgPathName();
					}
				}

				boolean isNumeric = Pattern.matches("^\\d*$", cellValue);

				if ((imgPath == null) && (!isNumeric || symbol != null || cellValue == null || cellValue.isEmpty()))
					cell.setCellValue(symbol == null ? cellValue : symbol);
				else if (imgPath != null) {
					addImage(wb, sheet, imgPath, rowIndex - 1, excelColIndx - 1);
					symbol = null;

				} else
					cell.setCellValue(Double.parseDouble(cellValue));

				if (transpose) {
					rowIndex++;
				}

			}
			if (transpose) {
				tblColIndx++;
				excelColIndx++;
			}
			prevCVal = currentCVal;
		}
		if (rptWb != null) {
			if (tableModel.has("removeRow") && transpose) {
				removeRow(sheet, totHeaders, rowIndex, excelColIndx);
			}

			if (groupBy && !firstFlag && groupByFormula != null) {
				addGroupByFormula(sheet, rowIndex++, excelColIndx, grpByRowIndx + 2);
			}
			setColumnWidths(sheet, jsonColModelArr);

			formatSheet(rptWb, sheet, excelColIndx, rowIndex);

			fileName.setLength(0);
			fileName.append(filePath).append(schedulerConfigMst.getReportName()).append(empName.replaceAll(" ", ""))
					.append("_").append(now()).append(".xls");
			// CommonMessage.debugMsg(" fileName" + fileName);

			out = new FileOutputStream(fileName.toString());
			rptWb.write(out);
			out.close();
			out = null;
			rptWb = null;
			try {
				SchedulerLog.info("Mail Sending to  " + toMailAddress);

				List<String> attachments = new ArrayList<String>();
				attachments.add(fileName.toString());
				// notesMailClient.send(toMailAddress, ccMailAddress,
				// schedulerConfigMst.getSubject(), "Hi,", fileName.toString());

				StringBuffer mailContent = new StringBuffer();
				/*
				 * mailContent.append("\r\n"); mailContent.append("Greetings,");
				 * mailContent.append("\r\n");
				 * mailContent.append("Reminder  :  ").append(schedulerConfigMst.getSubject());
				 */

				// mailContent.append(" <label style='color:red;'> ");
				mailContent.append("This is system generated mail.Please don't reply to this mail.");
				mailContent.append(" For any further assistance please contact Perfex Support Team");
				// mailContent.append("</label> ");

				/*
				 * mailContent.append("( BCM : 4119 & 5081 mail : BCM TPM Software Support ");
				 * mailContent.append(" TBN : 7200 & 9903540684 mail : TBN Perfex Support ");
				 * mailContent.append(" KOV : 1601 & 1610 mail : KOV TPM Software Support ");
				 * mailContent.append(" BOL : 201 & 235 mail : PSPD BOL PerfexSupport ");
				 * mailContent.append(" DHQ : 519 & 398 mail : PSPD DHQ Perfex Support ) ");
				 */

				// mailContent.append("Regards,");
				// mailContent.append("\r\n");
				// mailContent.append("Perfex 360 Support Team");
				CommonMessage.debugMsg(mailContent + " mailContent mailContent mailContent");
				schedulerConfigMst
						.setSubject(schedulerConfigMst.getSubject() + " as on date : " + CommonFunctions.getDate());

				// notesMailClient.send(toMailAddress, ccMailAddress,
				// schedulerConfigMst.getSubject(), mailContent.toString(), attachments);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				SchedulerLog.info("Mail Not Send to  " + toMailAddress, e);
			}
		}
		f.close();
		this.wb = null;
		styles = null;

	}

	private JSONObject createTableObject(ResultSet rs, SchedulerConfigMst schedulerConfigMst)
			throws NoDataFoundException {

		try {
			List<String[]> headRows = getRowFromResultSet(rs, 0, schedulerConfigMst.getXlRptStartRowIndx(),
					schedulerConfigMst.getXlRptStartColIndx());

			JSONObject tableModel = new JSONObject();
			JSONArray colModel = createColModel(headRows.get(0));
			tableModel.put("colModel", colModel);
			tableModel.put("title", schedulerConfigMst.getTitle());
			JSONArray colNames = new JSONArray();

			for (int i = 1; i <= schedulerConfigMst.getNumberOfHeader(); i++)
				colNames.put(headRows.get(i));

			tableModel.put("colNames", colNames);

			return tableModel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new NoDataFoundException("No Header Row Found");
		}

	}

	private JSONArray createColModel(String[] colModelMeta) {

		JSONArray colModels = new JSONArray();
		for (String cMeta : colModelMeta) {
			JSONObject cM = new JSONObject();
			CommonMessage.debugMsg(" cMeta " + cMeta);
			String[] properties = cMeta.split("#");
			for (int i = 0; i < properties.length; i++) {
				String[] pVal = properties[i].split("=");
				cM.put(pVal[0], pVal[1]);
			}
			colModels.put(cM);
		}
		return colModels;
	}

	private List<String[]> getRowFromResultSet(ResultSet rs, final int fromRow, final int toRow, final int fromCol)
			throws SQLException {
		ResultSetMetaData rsmd;
		// int rowCount = 0;
		CommonMessage.debugMsg(" fromRow " + fromRow + " toRow " + toRow + " fromCol " + fromCol);
		int colCount = 0;
		try {
			rsmd = rs.getMetaData();
			colCount = rsmd.getColumnCount();

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		List<String[]> dataList = new ArrayList<String[]>();

		int rowIndx = 0;
		while (rs.next()) {
			if (rowIndx++ < fromRow)
				continue;

			String[] row = new String[colCount - fromCol];
			int cInd = 0;
			for (int i = fromCol; i < colCount; i++) {

				row[cInd++] = getValueFromResultSet(rsmd, rs, i);
			}
			dataList.add(row);
			if (rowIndx >= toRow)
				break;
			// rowCount++;;
		}
		return dataList;
	}
	// -------------- Altered By Vignesh 06Nov2025
	// ------------------------------------------//

	public String getValueFromResultSet(ResultSetMetaData rsmd, ResultSet rs, int colIndex) throws SQLException {
		String tmpValue = null;
		final int idx = colIndex + 1;
		final int type = rsmd.getColumnType(idx);

		if (type == java.sql.Types.VARCHAR || type == java.sql.Types.CHAR || type == java.sql.Types.LONGVARCHAR
				|| type == java.sql.Types.NVARCHAR || type == java.sql.Types.NCHAR
				|| type == java.sql.Types.LONGNVARCHAR) {

			tmpValue = rs.getString(idx);
			tmpValue = (tmpValue != null ? tmpValue.replace("{}", "").replace("<", "").replace(">", "") : "");

		} else if (type == java.sql.Types.DATE) {
			tmpValue = convertSqlTimeStampToString(rs.getTimestamp(idx));

		} else if (type == java.sql.Types.TIMESTAMP || type == java.sql.Types.TIMESTAMP_WITH_TIMEZONE) { // JDBC 4.2+
			tmpValue = convertSqlTimeStampToString(rs.getTimestamp(idx));

		} else if (type == java.sql.Types.NUMERIC || type == java.sql.Types.DECIMAL || type == java.sql.Types.FLOAT
				|| type == java.sql.Types.DOUBLE || type == java.sql.Types.REAL) {
			// Preserve your original number formatting logic
			String numVal = rs.getString(idx);
			if (numVal == null || numVal.trim().isEmpty()) {
				tmpValue = "";
			} else if (numVal.indexOf('.') >= 0) {
				tmpValue = Double.toString(rs.getDouble(idx));
			} else {
				// could be larger than int
				long asLong = rs.getLong(idx);
				if (rs.wasNull()) {
					tmpValue = "";
				} else if (asLong <= Integer.MAX_VALUE && asLong >= Integer.MIN_VALUE) {
					tmpValue = Integer.toString(rs.getInt(idx));
				} else {
					tmpValue = Long.toString(asLong);
				}
			}

		} else if (type == java.sql.Types.INTEGER || type == java.sql.Types.SMALLINT || type == java.sql.Types.TINYINT
				|| type == java.sql.Types.BIGINT) {
			// Convert integer-like types to String (no casting to String!)
			if (type == java.sql.Types.BIGINT) {
				long v = rs.getLong(idx);
				tmpValue = rs.wasNull() ? "" : Long.toString(v);
			} else {
				int v = rs.getInt(idx);
				tmpValue = rs.wasNull() ? "" : Integer.toString(v);
			}

		} else if (type == java.sql.Types.BOOLEAN || type == java.sql.Types.BIT) {
			Object obj = rs.getObject(idx);
			tmpValue = (obj == null) ? "" : obj.toString();

		} else {
			// Safe, generic fallback for any other types (avoids ClassCastException)
			Object obj = rs.getObject(idx);
			tmpValue = (obj == null) ? "" : obj.toString();
		}

		return tmpValue;
	}
	// -------------- Altered By Vignesh 06Nov2025
	// ------------------------------------------//

	/*
	 * public String getValueFromResultSet(ResultSetMetaData rsmd, ResultSet rs, int
	 * colIndex ) throws SQLException{ String tmpValue = null;
	 * 
	 * if( rsmd.getColumnType(colIndex+1) == java.sql.Types.VARCHAR ){ tmpValue =
	 * rs.getString(colIndex+1); tmpValue = (tmpValue != null ?
	 * tmpValue.replace("{}", "").replace("<*", "").replace("*>", "") :""); } else
	 * if( rsmd.getColumnType(colIndex+1) == java.sql.Types.DATE ) tmpValue =
	 * convertSqlTimeStampToString(rs.getTimestamp(colIndex+1)); else if(
	 * rsmd.getColumnType(colIndex+1) == java.sql.Types.NUMERIC ) { String numVal =
	 * rs.getString(colIndex+1); if( numVal != null && numVal.indexOf(".") >=0 ) {
	 * tmpValue = Double.toString(rs.getDouble(colIndex+1)); } else if( numVal !=
	 * null && Double.parseDouble(numVal) < Integer.MAX_VALUE ) tmpValue =
	 * Integer.toString(rs.getInt(colIndex+1)); else if( numVal != null ) tmpValue =
	 * Long.toString(rs.getLong(colIndex+1)); else tmpValue =""; } else if(
	 * rsmd.getColumnType(colIndex+1) == java.sql.Types.TIMESTAMP) tmpValue =
	 * convertSqlTimeStampToString(rs.getTimestamp(colIndex+1)); else tmpValue =
	 * (String)rs.getObject(colIndex+1);
	 * 
	 * return tmpValue;
	 * 
	 * }
	 */

	public String convertSqlTimeStampToString(java.sql.Timestamp timestamp) {
		if (timestamp != null)
			return timestampFormat.format((java.util.Date) timestamp);
		return null;
	}

}
