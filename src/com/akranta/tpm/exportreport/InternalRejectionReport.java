package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.InternalRejectionRptServlet;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.InternalRejectionRptService;
import com.akranta.tpm.service.impl.InternalRejectionRptServiceImpl;
import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class InternalRejectionReport {
	
	InternalRejectionRptService internalRejectionRptService;
	private JSONObject tableModel;
	ExcelUtils excelUtils = new ExcelUtils(tableModel);
	private short [] headerColors   = null;
	public InternalRejectionReport(DBActionTemplate dbActionTemplate ){
		internalRejectionRptService = new InternalRejectionRptServiceImpl(dbActionTemplate );		
	}
	
	private Map<String,CellStyle> styles = null;
	private static final String STYLE_IDENTIFIER_HEADER_1 = "header1";
	private static final String STYLE_IDENTIFIER_HEADER_2 = "header2";
	private static final String STYLE_IDENTIFIER_HEADER_3 = "header3";
	private static final String STYLE_IDENTIFIER_HEADER_4 = "header4";
	private static final String STYLE_IDENTIFIER_HEADER_5 = "header5";
	private static final String STYLE_IDENTIFIER_ROTATION = "rotation";
	
	private static final String [] STYLE_IDENTIFIER_HEADERS = {STYLE_IDENTIFIER_HEADER_1,STYLE_IDENTIFIER_HEADER_2,
														 STYLE_IDENTIFIER_HEADER_3,STYLE_IDENTIFIER_HEADER_4,
														 STYLE_IDENTIFIER_HEADER_5
														 };
	
	public InternalRejectionReport(JSONObject tableModel){
		this.tableModel = tableModel;
		setDefaultHeaderColors();
	}
	public InternalRejectionReport(JSONObject tableModel,List<XLConditionalFormats> groupByFormula){
		this.tableModel = tableModel;
		setDefaultHeaderColors();
		
		
	}
	
	private void setDefaultHeaderColors(){
		headerColors   = new short[ 5 ];
		headerColors[0] = IndexedColors.AQUA.getIndex();
		headerColors[1] = IndexedColors.BROWN.getIndex();
		headerColors[2] = IndexedColors.BLUE_GREY.getIndex();
		headerColors[3] = IndexedColors.CORAL.getIndex();
		headerColors[4] = IndexedColors.LIGHT_TURQUOISE.getIndex();
	}
	
	private static String STYLE_IDENTIFIER_GROUPBY_ROW = "groupByRow";
	private static String STYLE_IDENTIFIER_ALIGN_LEFT = "align_left";
	private static String STYLE_IDENTIFIER_ALIGN_RIGHT = "align_right";
	private static String STYLE_IDENTIFIER_ALIGN_CENTER = "align_center";
	
	private static short STYLE_HEADER_1_COLOR = IndexedColors.GREY_25_PERCENT.getIndex();
	private static short STYLE_HEADER_2_COLOR = IndexedColors.AQUA.getIndex();
	private static short STYLE_HEADER_3_COLOR = IndexedColors.LEMON_CHIFFON.getIndex();
	private static short STYLE_HEADER_4_COLOR = IndexedColors.CORAL.getIndex();
	private static short STYLE_HEADER_5_COLOR = IndexedColors.LIGHT_TURQUOISE.getIndex();
	private static short STYLE_GROUPBY_ROW_COLOR = IndexedColors.LIGHT_YELLOW.getIndex() ;
	private Workbook wb = null; 
	
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";
	
	  private  Map<String, CellStyle> createStyles(Workbook wb){
	        
	        return defaultStyles(wb);
	    }
	  
	 
	  
	
	public Workbook fillInternalValues(Map<Integer, List<String[]>> internalRejData,String format,JSONObject tableModel) throws IOException {
				
		
		this.wb =  format.equals(REPORT_FORMAT_EXL_2007) ?  new SXSSFWorkbook():new HSSFWorkbook();		
	    Sheet sheet = wb.createSheet();		     
	    sheet.setDisplayGridlines(false);
		Row row = null;
		int rowIndex  = 1;
		
		
		
		  Font header1Font = wb.createFont();
	         header1Font.setFontHeightInPoints((short)12);
	         header1Font.setBold(true);
	         header1Font.setColor(IndexedColors.BLACK.getIndex());	         
	         header1Font.setFontName(XLConditionalFormats.FONT_DEFAULT);
	         
	         
	         Font header2Font = wb.createFont();
	         header2Font.setFontHeightInPoints((short)10);
	         header2Font.setBold(true);
	         header2Font.setColor(IndexedColors.BLACK.getIndex());	         
	         header2Font.setFontName(XLConditionalFormats.FONT_DEFAULT);
	         
		CellStyle style = wb.createCellStyle();		
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setFillForegroundColor(STYLE_HEADER_1_COLOR);
		style.setFillForegroundColor(STYLE_HEADER_1_COLOR);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header1Font);
		
		CellStyle styleRow1 = wb.createCellStyle();
		styleRow1.setFillBackgroundColor(null);
		styleRow1.setAlignment( HorizontalAlignment.CENTER);
		styleRow1.setFillForegroundColor(STYLE_HEADER_2_COLOR);		
		styleRow1.setFillPattern(FillPatternType.SOLID_FOREGROUND);		
		styleRow1.setBorderRight(BorderStyle.THIN);
		styleRow1.setBorderTop(BorderStyle.THIN);
		styleRow1.setBorderBottom(BorderStyle.THIN);        
		style.setFont(header2Font);
		
		
		CellStyle styleData = wb.createCellStyle();
		styleData.setFillBackgroundColor(null);
		styleData.setBorderRight( BorderStyle.THIN);
		styleData.setBorderTop( BorderStyle.THIN);
		styleData.setBorderBottom( BorderStyle.THIN);        
		
		CellStyle styleDataLeft = wb.createCellStyle();
		styleDataLeft.setFillBackgroundColor(null);
		styleDataLeft.setBorderRight( BorderStyle.THIN);
		styleDataLeft.setBorderTop( BorderStyle.THIN);
		styleDataLeft.setBorderBottom( BorderStyle.THIN);  
		styleDataLeft.setAlignment(HorizontalAlignment.RIGHT);
		
		row = sheet.createRow(rowIndex);
		
		styles = createStyles(wb);
		
		JSONArray  jsonArrColHeadrs = null;
		JSONArray  jsonColModelArr = null;
		if( tableModel.has("colNames") )
			jsonArrColHeadrs =  tableModel.getJSONArray("colNames");
		else if( tableModel.has("rowHeaders") )
			jsonArrColHeadrs =  tableModel.getJSONArray("rowHeaders");
		
		CommonMessage.debugMsg("jsonArrColHeadrs...................."+jsonArrColHeadrs);		
		//CellStyle rowStyle =  styles.get(STYLE_IDENTIFIER_HEADER_1);
		CommonMessage.debugMsg(STYLE_IDENTIFIER_HEADER_1);		
		jsonColModelArr = tableModel.getJSONArray("colModel");
		setMaintitle(sheet,style,jsonColModelArr);
		setColumnWidths(sheet,jsonColModelArr);
		
		
		List<String[]> curExcel = internalRejData.get(0);	
		List<String[]> curData = internalRejData.get(1);	
		curExcel = transposeListArr(curExcel);
		if(curExcel!=null && curExcel.size() > 0 )
		{
			for(int i=0;i<curExcel.size();i++)
			{
				row = sheet.createRow(rowIndex++);				
			    String [] impshtexl = curExcel.get(i);			
			    for(int j=2;j<impshtexl.length;j++)
			    {  
			    	row.createCell(j-2).setCellValue(impshtexl[j]); 
			    	sheet.getRow(0).getCell(j-2).setCellStyle(style);
			    	sheet.getRow(1).setHeight((short)400);
			    	sheet.getRow(1).getCell(j-2).setCellStyle(styleRow1);	
			    	sheet.getRow(i).getCell(j-2).setCellStyle(styleData);
			    	sheet.getRow(i+1).getCell(j-2).setCellStyle(styleData);
			    	
			    }
				    
			}		
		}	
		
		if(curData != null && curData.size()>0)
	    {
	    	for(int k=2;k<curData.size();k++)
			{			
	    		row = sheet.createRow(rowIndex++);
			    String [] dataExcel = curData.get(k);			
			    for(int m=2;m<dataExcel.length;m++)
			    {  
			    	row.createCell(m-2).setCellValue(dataExcel[m]); 
			    	sheet.getRow(k+4).getCell(m-2).setCellStyle(styleData);		
			    	
			    }
			}
	    }		
		int excelColIndx = jsonColModelArr.length();				
		formatSheet(wb, sheet,excelColIndx,rowIndex);
		
			
		
		return wb;	
		}
		
		
	
	private List<String[]> transposeListArr(List<String[]> dataList)
	{		
		if( dataList.size() <=0 ) return null;
		List<String[]> transposeList = new ArrayList<String[]>();		
		for( int i =0; i<dataList.get(0).length; i++)
		{	
			String [] tRow = new String [ dataList.size()];
			for (int j=0; j<dataList.size();j++)
			{
				tRow [ j ]= dataList.get(j)[i].equals("0")?dataList.get(j)[i].replace("0", "-"):dataList.get(j)[i];
				
			}
			transposeList.add(tRow);
		}
		return transposeList;
	}
	
private void setMaintitle(Sheet sheet, CellStyle style, JSONArray jsonColModelArr ){
		
		Row myRow = sheet.createRow(0);
		String title ="";
		//if ( tableModel.has("title") ){
			title = "Internal Rejection Report";
			
		//}
		int colIndx = 0;
		
		JSONObject colModel = null;
		//CellStyle rowStyle = style.get(STYLE_IDENTIFIER_HEADERS[0]);
		int noCols = jsonColModelArr.length();
		Cell tCell =null;
		for( int j = 0 ; j < noCols;j++){
			colModel =  jsonColModelArr.getJSONObject(j);
			if( colModel.has("hidden") && colModel.getBoolean("hidden")  )
				continue;
			
			tCell =  myRow.createCell(colIndx);
			if( colIndx == 0 )
				tCell.setCellValue( title );
			
			tCell.setCellStyle(style);
			colIndx++;
		}
		myRow.setHeight((short)700);

		String tcChar = getCharForExcelCol(colIndx);
		CellRangeAddress region = CellRangeAddress.valueOf("A1:"+tcChar+"1");
		sheet.addMergedRegion( region );

	}

    private String getCharForExcelCol(int colIndex)
    {
    	// char c = 'A';
    	 
    	 String colChar ="";
    	 do{
    		 if( (colIndex / 26) > 0){
    			 colChar +=  (char)(( 64  + (int) (colIndex / 26))) ;
    			 colIndex  = (colIndex % 26);
    		 }	 
    		 else{
    			 
    			 if( colIndex > 0)
    				 colChar +=  (char)(( 64  +   (int)colIndex % 26 )) ;
    			 else
    				 colChar += "A";
    			 
    			 colIndex = -1;
    		 }	 
    	 }while( colIndex >= 0 );

    	 return colChar;
    }

    
    private Map<String, CellStyle> defaultStyles(Workbook wb){
    	Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

        //  short borderColor = IndexedColors.GREY_50_PERCENT.getIndex();

          CellStyle style;
          Font header1Font = wb.createFont();
          header1Font.setFontHeightInPoints((short)12);
          header1Font.setBold(true);
          header1Font.setColor(IndexedColors.BLACK.getIndex());
          style = wb.createCellStyle();
          style.setAlignment(HorizontalAlignment.CENTER);
          style.setVerticalAlignment(VerticalAlignment.CENTER);
          style.setFillForegroundColor(STYLE_HEADER_1_COLOR);
          style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          style.setBorderRight(BorderStyle.THICK);
          style.setBorderBottom(BorderStyle.THIN);
          style.setFont(header1Font);
          style.setWrapText(true);
          styles.put(STYLE_IDENTIFIER_HEADER_1, style);
          
          
          Font header2Font = wb.createFont();
          header2Font.setFontHeightInPoints((short)10);
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
          header3Font.setBold(true);
          header3Font.setFontHeightInPoints((short)10	);
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
          style.setFillForegroundColor(STYLE_HEADER_3_COLOR);
          style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
          style.setBorderRight(BorderStyle.THIN);
          style.setBorderBottom(BorderStyle.THIN);
          style.setFont(header3Font);
          style.setWrapText(true);
          styles.put(STYLE_IDENTIFIER_ROTATION, style);
          
          Font dataFont = wb.createFont();
          dataFont.setFontHeightInPoints((short)8);
          dataFont.setColor(IndexedColors.BLACK.getIndex());
          
          style = wb.createCellStyle();
          style.setAlignment(HorizontalAlignment.LEFT);
          style.setVerticalAlignment(VerticalAlignment.CENTER);
          style.setFont(dataFont);
          style.setBorderRight( BorderStyle.THIN);
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
          style.setAlignment(HorizontalAlignment.CENTER);
          style.setVerticalAlignment(VerticalAlignment.CENTER);
          style.setFont(dataFont);
          style.setBorderRight(BorderStyle.THIN);
          style.setBorderBottom(BorderStyle.THIN);
          style.setWrapText(true);
          styles.put(STYLE_IDENTIFIER_ALIGN_RIGHT, style);
          
          /** Group by Row Style */
          Font grpupByRwFont = wb.createFont();
          grpupByRwFont.setBold(true);
          grpupByRwFont.setFontHeightInPoints((short)10	);
          grpupByRwFont.setColor(IndexedColors.BLACK.getIndex());
          
          style = wb.createCellStyle();
          style.setAlignment(HorizontalAlignment.CENTER);
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
    
    private void setColumnWidths(Sheet sheet, JSONArray jsonColModelArr){
		 JSONObject colModel;
		 int width;
		 int colIndex = 0;
		 for( int i = 0 ;i < jsonColModelArr.length();i++){
       	colModel =  jsonColModelArr.getJSONObject(i);
       	if( colModel.has("hidden") && colModel.getBoolean("hidden") )
       		continue;
       	if(  colModel.has("width") && colModel.getInt("width") > 0)
       		width = colModel.getInt("width");
       	else
       		width = 200;
       	

			sheet.setColumnWidth(colIndex++, width*30);
       }
	}
    
private void formatSheet(Workbook wb, Sheet sheet,int excelColIndx, int rowIndex){
		
		CellRangeAddress region = CellRangeAddress.valueOf("A1:"+ getCharForExcelCol(excelColIndx)  +rowIndex);
		
		BorderStyle borderMediumDashed = BorderStyle.THICK;
        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet);
        RegionUtil.setBorderTop( borderMediumDashed,region, sheet);
        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet);
        RegionUtil.setBorderRight( borderMediumDashed, region, sheet);
        
        RegionUtil.setBottomBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
        RegionUtil.setTopBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
        RegionUtil.setLeftBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
        RegionUtil.setRightBorderColor(IndexedColors.BLACK.getIndex(), region, sheet);
        
        //sheet.wprotectWorkbook("admin", "admin");
        sheet.protectSheet("admin");
        
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
	}
}