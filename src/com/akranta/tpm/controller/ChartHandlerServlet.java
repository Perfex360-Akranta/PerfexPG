package com.akranta.tpm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTitle;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class ChartHandlerServlet extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		process(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		process(request,response);
		
	}
	
	private void process( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		String action = UIUtils.getActionPart(request);
		
		CommonFunctions.debugMsg(" action " + action);
		if( action.equals("test.charts")){
		
			request.setAttribute("chartUrl", request.getParameter("url"));
			UIUtils.forwardRequest(request, response, "/pages/Gen/testchart.jsp");
		}

		else if( action.equals("export.charts")){
			//System.out.println("export.charts");
			exportCharttoExcel(request,response);
			
		}
		else if( action.equals("exportChart.charts")){
			//System.out.println("exportChart.charts");
			String chartDataStr = request.getParameter("chartData");
			String multiple = request.getParameter("multiple");
		//	System.out.println("chartData:--->"+chartDataStr);
			//System.out.println("multiple:--->"+multiple);
			String format = ExcelUtils.getFormat(request);
			//System.out.println("multiple " + multiple +" fff " + format +  " Chart Data : "+chartDataStr);
			String path = UIUtils.getExcelTemplatePath(request);
			//System.out.println("Path 1:"+path);
			Workbook wb = null;
			String title ="";
			try {
				if( ! "Y".equals(multiple)){
					JSONObject jsonObject =JSONObject.fromString(chartDataStr);
				//	System.out.println("jsonObject1" +jsonObject);
					JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
					//System.out.println("chartObj1" +chartObj);
					JSONObject headersObj = (JSONObject) chartObj.get("headers");
					//System.out.println("headersObj1" +headersObj);
					title = headersObj.getString("title");
					if(UIUtils.isValidKeyId(title))
						title = title.replace(" ", "");
					wb = fillExcValues(jsonObject,path,format);
				}else{
					String fileName = request.getParameter("fileName");
					title =fileName;
					JSONArray jsonArray =JSONArray.fromString(chartDataStr);
					//System.out.println("else-jsonArray" +jsonArray);
					wb = createMultipleGraph(jsonArray,path,format);
				}
			  	
				ExcelUtils.writeToResponse(response, wb, title,format );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public Workbook fillExcValues(JSONObject jsonObject,String path,String format)throws Exception
	{
		JSONObject datasObj = (JSONObject) jsonObject.get("values");
		JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
		JSONObject headersObj = (JSONObject) chartObj.get("headers");		
		JSONArray dataArray = datasObj.getJSONArray("datas");
		JSONArray headersArray = headersObj.getJSONArray("colHeaders");
		JSONArray rowTitle = headersObj.getJSONArray("rowHeaders");		
		JSONArray datas =  dataArray.getJSONArray(0);
		String type = datasObj.getString("type");
		String titleY = headersObj.getString("yAxis");
		String titleX = headersObj.getString("xAxis");
		String title = headersObj.getString("title");
	
		String excelPath =getExcelFile(type); //getChartTemplateName(type);
		//CommonFunctions.debugMsg(type + " excelPath : "+path+excelPath);
		InputStream inp = new FileInputStream(path+excelPath+"."+format); 
		//CommonFunctions.debugMsg(format + " : "+ExcelUtils.REPORT_FORMAT_EXL_2007);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ?  new XSSFWorkbook(inp):new HSSFWorkbook(inp); 
		inp.close();
		Sheet sheet = wb.getSheetAt(1);
		Sheet firstsheet = wb.getSheetAt(0);
		
		
		int colCount =datas.length();
		int rowFlag = getRowNo(type);
		int dataRowsCount = rowFlag + dataArray.length();
		int flag = 0;
		int titleNo =0;
		int arrIndex=0;
	
		while(flag<200)
		{
			Row row =  sheet.createRow(flag);
			
			if(flag >= dataRowsCount ) // || flag < rowFlag)
			{
				if(flag>1){
					row.setZeroHeight(true);
					//sheet.removeRow(row);
				}
				flag++;
				continue;
			}
			for(int j=0;j<200;j++)
			{	
				if(flag < dataRowsCount)
				{
				   Cell cell = row.getCell(j);  
				   if (cell == null)  
					   cell = row.createCell(j);	
				   if( j <= colCount)
				   {
					  if(flag == 0)
					   {
						   if(j == 0)
						   {
							   cell.setCellValue(title);
							   if(type.equals("pie"))
								{
										int f=flag;
										while(f<60)
										{
											firstsheet.createRow(f);
											f++;
										}
								}
						   }
						   else if(j==1)
							   cell.setCellValue(UIUtils.isValidKeyId(titleY)?titleY:"");
						   else if(j==2)
							   cell.setCellValue(UIUtils.isValidKeyId(titleX)?titleX:"");
					   }
					   else if(flag==1)
					   {
						   if(j != 0)
							   cell.setCellValue(headersArray.length()>0?headersArray.get(j-1).toString():"");	
					   }
					   else
					  {
						   //CommonFunctions.debugMsg(flag + " : "+rowFlag);
						   if(flag >= rowFlag)
						   {
							  // int valNo=(flag-2)%5;		
							   
							   JSONArray val = dataArray.getJSONArray(arrIndex);
							   if(j==0)
							   {
								   if(type.equals("pie"))
									{
									   Cell fccell = firstsheet.getRow(flag-1).getCell(12);  
									   if (fccell == null)  
										   fccell = firstsheet.getRow(flag-1).createCell(12);
									   fccell.setCellValue(rowTitle.get(titleNo).toString());
									}
								   cell.setCellValue(rowTitle.get(titleNo).toString());
								   titleNo++;								   
							   }
							   else
							   {
								   if(j==colCount)
									   arrIndex++;
								   if(UIUtils.isValidKeyId(val.getString(j-1)))
									   cell.setCellValue(new Double(val.getDouble(j-1)));
								   else
									   cell.setCellValue(new Double(0));
								   
								   if(type.equals("pie"))
									{
									   Cell fccell = firstsheet.getRow(flag-1).getCell(j+12);  
									   if (fccell == null)  
										   fccell = firstsheet.getRow(flag-1).createCell(j+12);
									   if(UIUtils.isValidKeyId(val.getString(j-1)))
										   fccell.setCellValue(new Double(val.getDouble(j-1)));
									   else
										   fccell.setCellValue(new Double(0));
									 
									}
								   //CommonFunctions.debugMsg("Value : "+val.getDouble(j-1));
							   }
							   
						   }
						   
					   }
					   	CellStyle cellstyle =wb.createCellStyle();
						cellstyle.setAlignment(HorizontalAlignment.RIGHT);	
						cell.setCellStyle(cellstyle);
				   }
				   else if( flag == 0)
				   {
					   sheet.setColumnHidden(j,true);
				   }
				}
			}
			flag++;
			
		}
		
		return wb;
	}
	
	public Workbook createMultipleGraph(JSONArray jsonArray,String path,String format)throws Exception
	{
		//System.out.println("createMultipleGraph");
		String excelPath = "/multiplesheetgraph_line"  ;//getExcelFile(type); //getChartTemplateName(type);
		CommonFunctions.debugMsg( " excelPath : "+path+excelPath);
		InputStream inp = new FileInputStream(path+excelPath+"."+format); 
		//CommonFunctions.debugMsg(format + " : "+ExcelUtils.REPORT_FORMAT_EXL_2007);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ?  new XSSFWorkbook(inp):new HSSFWorkbook(inp); 
		inp.close();
		int totalSheet = wb.getNumberOfSheets()-1;
		//System.out.println("totalSheet:" +totalSheet);
		int remvIndx = jsonArray.length();
		//System.out.println("jsonArray.length():" +remvIndx);
		String sheetName ="";
		for(int g=0;g < totalSheet  ; g++ ){

			if( g >= jsonArray.length()    ){
				wb.removeSheetAt(remvIndx);
				continue;
			}
			
			JSONObject jsonObject = jsonArray.getJSONObject(g);
			//System.out.println("jsonObject-2:" +jsonObject);
			JSONObject datasObj = (JSONObject) jsonObject.get("values");
			//System.out.println("datasObj-2:" +datasObj);
			JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
			//System.out.println("chartObj-2:" +chartObj);
			JSONObject headersObj = (JSONObject) chartObj.get("headers");	
			//System.out.println("headersObj-2:" +headersObj);
			JSONArray dataArray = datasObj.getJSONArray("datas");
			//System.out.println("dataArray-2:" +dataArray);
			JSONArray headersArray = headersObj.getJSONArray("colHeaders");
			//System.out.println("headersArray-2:" +headersArray);
			JSONArray rowTitle = headersObj.getJSONArray("rowHeaders");	
			//System.out.println("rowTitle-2:" +rowTitle);
			JSONArray datas =  dataArray.getJSONArray(0);
			//System.out.println("datas-2:" +datas);
			String titleY = headersObj.getString("yAxis");
			//System.out.println("titleY-2:" +titleY);
			String titleX = headersObj.getString("xAxis");
			//System.out.println("titleX-2:" +titleX);
			String title = headersObj.getString("title");
			//System.out.println("title-2:" +title);
		
			
			//Sheet sheet = wb.getSheetAt(g);
			Sheet firstsheet = wb.getSheetAt(g);
			
			
			int colCount =datas.length();
			//System.out.println("datas.length()-2:" +colCount);
			int totCols = 200;
			int rowStart = 200;
			int flag = rowStart ;
			int noRows = 15; 
			int rowEnds = rowStart+noRows; 
			int dataRowsCount =rowStart + dataArray.length();
			int titleNo =0;
			int arrIndex=0;
			int titleRow = 199;
			int titleCol = 0;
			int xAxisTitleCol = 2 ;
			int yAxisTitleCol = 1 ;
			
			//title = org.apache.commons.lang.StringEscapeUtils.unescapeJava(title);
			
			Row row = firstsheet.createRow(titleRow);
			if( title != null && title.length() > 31 )
				sheetName = title.substring(0,title.length()-1);
			else
				sheetName = title;
			
			//sheetName = sheetName.replaceAll("/","").replaceAll("\\","");
			sheetName = sheetName.replaceAll("[^\\w\\s\\-_]", "");
			
			wb.setSheetName(g, sheetName);
			
			Cell cell = row.getCell(titleCol);  
			if (cell == null)  
			   cell = row.createCell(titleCol);
			
			cell.setCellValue(title);
			
			
			cell = row.getCell(xAxisTitleCol);  
			if (cell == null)  
			   cell = row.createCell(xAxisTitleCol);
			
			cell.setCellValue(UIUtils.isValidKeyId(titleX)?titleX:"");
			
			cell = row.getCell(yAxisTitleCol);  
			if (cell == null)  
			   cell = row.createCell(yAxisTitleCol);
			cell.setCellValue(UIUtils.isValidKeyId(titleY)?titleY:"");
			
			
			
			while(flag<rowEnds)
			{
				row = firstsheet.createRow(flag);
				if(flag >= dataRowsCount ) // || flag < rowFlag)
				{
					row.setZeroHeight(true);
					flag++;
					continue;
				}
				JSONArray val = dataArray.getJSONArray(arrIndex++);
				//System.out.println("dataArray["+arrIndex+ "]==" +val);
				for(int j=0;j<totCols;j++)
				{	
					if( j <= colCount)
					{
					   cell = row.getCell(j);  
					   if (cell == null)  
						   cell = row.createCell(j);	
					   if(flag==rowStart)
					   {
						   if(j != 0)
							   cell.setCellValue(headersArray.length()>0?headersArray.get(j-1).toString():"");	
					   }
					   else
					   {
						   
						   if(j==0)
						   {
							   cell.setCellValue(rowTitle.get(titleNo).toString());
							   titleNo++;								   
						   }
						   else
						   {
							   if(UIUtils.isValidKeyId(val.getString(j-1)))
								   cell.setCellValue(new Double(val.getDouble(j-1)));
							   else
								   cell.setCellValue(new Double(0));
							   
						   }
					   }
					   	CellStyle cellstyle =wb.createCellStyle();
						cellstyle.setAlignment(HorizontalAlignment.RIGHT);	
						cell.setCellStyle(cellstyle);
					}
					else if( flag == rowStart)
				    {
					   firstsheet.setColumnHidden(j,true);
				    }
				}
				flag++;
				
			}
		}
		return wb;
	}
	
	public Workbook fillExcelValues(JSONObject jsonObject,String path,String format)throws Exception
	{
		
		JSONObject datasObj = (JSONObject) jsonObject.get("values");
		JSONObject chartObj = (JSONObject) jsonObject.get("chartDatas");
		JSONObject headersObj = (JSONObject) chartObj.get("headers");		
		JSONArray dataArray = datasObj.getJSONArray("datas");
		JSONArray headersArray = headersObj.getJSONArray("colHeaders");
		JSONArray rowTitle = headersObj.getJSONArray("rowHeaders");
		
		JSONArray datas =  dataArray.getJSONArray(0);
		String type = datasObj.getString("type");
		String titleY = headersObj.getString("yAxis");
		String titleX = headersObj.getString("xAxis");
		String title = headersObj.getString("title");
	
		String excelPath = getExcelFile(type);
		InputStream inp = new FileInputStream(path+excelPath+"."+format); 
		CommonFunctions.debugMsg(format + " : "+ExcelUtils.REPORT_FORMAT_EXL_2007);
		Workbook wb = format.equals(ExcelUtils.REPORT_FORMAT_EXL_2007) ?  new XSSFWorkbook(inp):new HSSFWorkbook(inp); // keep 100 rows in memory, exceeding rows will be flushed to disknew XSSFWorkbook(inp);
		inp.close();	   
		Sheet sheet = wb.getSheetAt(1);
		
		int dataRowsCount = 2 + dataArray.length();
		int colCount =datas.length();
		
		for(int i=0;i< 300;i++)
		{
			sheet.createRow(i);
			if(i >= dataRowsCount)
				sheet.getRow(i).setZeroHeight(true);
			for(int j=0;j<200;j++)
			{	
				if(i < dataRowsCount)
				{
				   Cell cell = sheet.getRow(i).getCell(j);  
				   if (cell == null)  
					   cell = sheet.getRow(i).createCell(j);	
				   if( j <= colCount)
				   {
					   if(i == 0)
					   {
						   if(j == 0)
							   cell.setCellValue(title);
						   else if(j==1)
							   cell.setCellValue(UIUtils.isValidKeyId(titleY)?titleY:"");
						   else if(j==2)
							   cell.setCellValue(UIUtils.isValidKeyId(titleX)?titleX:"");
					   }
					   else if(i==1)
					   {
						   if(j != 0)
							   cell.setCellValue(headersArray.length()>0?headersArray.get(j-1).toString():"");	
					   }
					   else
					   {
						   JSONArray val = dataArray.getJSONArray(i-2);
						   if(j==0)
							   cell.setCellValue(rowTitle.get(i-2).toString());
						   else
						   {
							   cell.setCellValue(new Double(val.getDouble(j-1)));							  
						   }
						   
					   }
					   	CellStyle cellstyle =wb.createCellStyle();
						cellstyle.setAlignment(HorizontalAlignment.RIGHT);	
						cell.setCellStyle(cellstyle);
				   }
				   else
				   {
					   //CommonFunctions.debugMsg("hid : "+j);
					   sheet.setColumnHidden(j,true);
				   }
				}
			}
		}
		return wb;
	}
	public Workbook fillValues(JSONObject jsonObject,String chartData,String format,String path,String imgPath)throws Exception
	{
		
		String excelPath = "/LineChart.xlsx";			
	    InputStream inp = new FileInputStream(path+excelPath); 
	   
	    
	    Workbook wb = new XSSFWorkbook(inp);
	    inp.close();	   
	    Sheet sheet = wb.getSheetAt(1);
	
	    String[] chartDatasArr = chartData.split(";");
	    String[] headers = chartDatasArr[0].substring(1).split(",");
	    String[] rowHeaders = chartDatasArr[1].substring(1).split(",");
	    String[] datasArr = chartDatasArr[2].substring(2).split("}");
	    
	    for(int i=0;i< 300;i++)
		{
	    	
	    	if(i==1)
	    		sheet.createRow(1);
	    	else if(i >= 2+datasArr.length-1)
	    	{
	    		CellStyle hiddenstyle =  wb.createCellStyle();
		    	hiddenstyle.setHidden(true);
	    		sheet.createRow(i).setRowStyle(hiddenstyle);	    	
	    		sheet.getRow(i).setZeroHeight(true);
	    	}
			int arrFlag = 0;
			if(i>1 && i<2+datasArr.length-1)
				arrFlag = i-2;
			
			String[] datas =  datasArr[arrFlag].split(",");	
			
			for(int j=0;j<250;j++)
			{	
				   String data = null;						
				   if(i!=0 && i< 2+datasArr.length-1)
				   {
						 Cell cell = sheet.getRow(i).getCell(j);  
						 if (cell == null)  
							 cell = sheet.getRow(i).createCell(j);				
						
						 if(j<=datas.length)
						 {
							   if(i==1)
							   {
								  if(j>0)
									data = headers[j-1].replace(")", "");		
								  else
									data="";
								}
								else if(i>1)
								{
									if(j==0)
										data = rowHeaders[j].replace(")", "");	
									else
										data = datas[j-1];					
									if(data.indexOf("{")>=0)
										data = data.substring(1);
								}
							
								CellStyle cellstyle =wb.createCellStyle();
								cellstyle.setAlignment(HorizontalAlignment.RIGHT);	
								
								if(i>1)
								{
									if(j>0)
										cell.setCellValue(new Double(data));
									else
										cell.setCellValue(data);
								}
								else
								  cell.setCellValue(data);
								cell.setCellStyle(cellstyle);	
						 }
						 else
						 {
							sheet.setColumnHidden(j,true);
						 }
					 }
			}
			
		}	 
	    return  wb;
		
	}
	private void exportCharttoExcel(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		String chartDataStr = request.getParameter("chartData");
		UIUtils.displayRequestParamsValue(request);
		if( chartDataStr != null ){
			StringBuffer excelChartData =new StringBuffer();
			JSONObject chartData = JSONObject.fromString(chartDataStr);
			String chartType = null ;
			String seriesStr = "";
			List<ChartSeries> chartSeriesList;
			
			if( chartData.has("chartType") )
				chartType = chartData.getString("chartType");

			ChartXAxis xAxis = null;
			String xAxisStr ="";
			if( chartData.has("xAxis") ){
				xAxis = (ChartXAxis)JSONObject.toBean(chartData.getJSONObject("xAxis"),ChartXAxis.class);
				if( xAxis.getCategories()  != null )
					seriesStr += ",,,"+ xAxis.getCategories().toString().replace("[", "").replace("]", "") + "((" ;
				xAxisStr = (xAxis.getTitle() !=null ?xAxis.getTitle().getText() != null ? xAxis.getTitle().getText().replace(",", "") :"":"") +"{{";  
			}	

			
			if( chartData.has("series") )	
			{
				chartSeriesList = (List<ChartSeries>)JSONArray.toList(chartData.getJSONArray("series"),ChartSeries.class);
				for(ChartSeries chartSeries : chartSeriesList)
				{
					if( ! "pie".equals(chartSeries.getType())  )
						seriesStr += chartSeries.getType() + ","  + chartSeries.getyAxis() +","+ chartSeries.getName() +"," + chartSeries.getData().toString().replace("[", "").replace("]", "") + "((" ;
					else{
						List<List<?>> pieDataList = (List<List<?>>)chartSeries.getData();
						//Object[] pieDataList = chartSeries.getData().toArray();
						
						for(List<?> dataL :pieDataList )
						{	
							//seriesStr +=  ;
							int dataIndx = 1; 
							for(Object data :dataL)
							{
								if( dataIndx++ == 2 &&  Double.parseDouble(data.toString()) < 0)
									data = "0";
								seriesStr += data.toString().replace(",", "") +"," ;
								
							}
							seriesStr = seriesStr.substring(0,seriesStr.lastIndexOf(","))+"((" ;
						}
					}
					
					
					if( chartType == null)
						chartType = chartSeries.getType();
				}
				
				seriesStr = seriesStr.substring(0,seriesStr.lastIndexOf("(("));
				
				CommonFunctions.debugMsg(" seriesStr " + seriesStr);
			}
			
			ChartTitle title = null, subTitle = null; 
			if( chartData.has("title") ){
				title = (ChartTitle ) JSONObject.toBean(chartData.getJSONObject("title"),ChartTitle.class);
			}	
			if( chartData.has("subTitle") )
				subTitle = (ChartTitle ) JSONObject.toBean(chartData.getJSONObject("subTitle"),ChartTitle.class);
			
			
			List<ChartYAxis> yAxisList = null;
			String yAxisStr ="";
			if( chartData.has("yAxis") ){
				yAxisList = (List<ChartYAxis>)JSONArray.toList(chartData.getJSONArray("yAxis"),ChartYAxis.class);
				for(ChartYAxis yAxis:yAxisList){
					yAxisStr += (yAxis.getTitle()!=null? yAxis.getTitle().getText() != null ?yAxis.getTitle().getText().replace(",", ""):"" :"") + "," ; 
					yAxisStr += (yAxis.getOpposite() != null ? yAxis.getOpposite() :"") + "{{";
				}
				yAxisStr = yAxisStr.substring(0,yAxisStr.lastIndexOf("{{")-1);
			}
			
			
			
			excelChartData.append(CommonFunctions.dateTimeNow().replaceAll(" ", "") +  ( title != null ? title.getText().replaceAll(" ", "") : "") + ".xls" + "@@");
			excelChartData.append(chartType+"@@");
			excelChartData.append(  (title != null ? title.getText() : "") + "@@");
			excelChartData.append(  (subTitle != null ? subTitle.getText() : "") + "@@");
			excelChartData.append(  seriesStr + "@@");
			excelChartData.append(  xAxisStr + "@@");
			excelChartData.append(  yAxisStr + "@@");
			
			CommonFunctions.debugMsg(" excelChartData   " + excelChartData);
			
			response.sendRedirect("http://localhost:4270/Default.aspx?chartData=" + excelChartData);
		}	
	}
	
	private static String getExcelFile(String type)
	{
		if(UIUtils.isValidKeyId(type))
		{
			if(type.equals("line") || type.equals("spline"))
				return  "/LineChart";
			else if(type.equals("LineSecondary"))
				return  "/LineSecondary";
			else if(type.equals("pie"))
				return  "/PieChart";
			else if(type.equals("PieColumn"))
				return  "/PieColChart";
			else if(type.equals("column"))
				return  "/ColChart";
			else if(type.equals("Pareto"))
				return  "/Pareto";
		}
		else
			return  "/LineChart";
		return null;
	}
	private static int getRowNo(String type)
	{
		if(UIUtils.isValidKeyId(type))
		{
			if(type.equals("line") || type.equals("spline"))
				return  2;
			else if(type.equals("LineSecondary"))
				return  2;
			else if(type.equals("pie"))
				return  2;
			else if(type.equals("PieColumn"))
				return  7;
			else if(type.equals("column"))
				return  2;
			else if(type.equals("Pareto"))
				return  2;
		}
		else
			return  2;
		return 2;
	}
	private static String getChartTemplateName(String type)
	{
		if(UIUtils.isValidKeyId(type))
		{
			if(type.equals("pie"))
				return "/PieChart";
			else if(type.equals("Pareto"))
				return  "/Pareto";
			else if(type.equals("LineSecondary") || type.equals("spline") )
				return  "/LineSecondary";
			else
				return  "/ChartTemplate1";
		}
		else
			return  "/ChartTemplate1";	
	}
}
