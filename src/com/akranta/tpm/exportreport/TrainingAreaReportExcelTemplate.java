package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class TrainingAreaReportExcelTemplate {
	

	public Workbook fillValues(Map<Integer, List<String[]>> excelData,
			String format, String path, String roleName, String trcl) throws Exception {
		// TODO Auto-generated method stub
		Row row = null;
		CommonMessage.debugMsg("roleName:"+roleName);
		String trclsub=trcl.substring(4);
		CommonMessage.debugMsg("trclsub:"+trclsub);
		String reptrcl=trclsub.replaceAll(",", "/");
		CommonMessage.debugMsg("reptrcl:"+reptrcl);
		String excelPath =null;
		if(format.equals("xlsx"))
		excelPath = "/trainingareaexceltemplate.xlsx";
		else if(format.equals("xls"))
		excelPath = "/trainingareaexceltemplate.xls";	
		
		InputStream inp = new FileInputStream(path+excelPath); 
		CommonMessage.debugMsg("excel file path:"+path+excelPath);
		Workbook wb = new XSSFWorkbook(inp);
	    inp.close();
	    CellStyle styleright=wb.createCellStyle();
//		styleright.setAlignment(CellStyle.ALIGN_RIGHT);
		styleright.setAlignment(HorizontalAlignment.CENTER);
		
	//	styleright.setBorderRight(CellStyle.BORDER_THIN);
		styleright.setBorderRight(BorderStyle.THIN);
		
	//	styleright.setBorderTop(CellStyle.BORDER_THIN);
		styleright.setBorderTop(BorderStyle.THIN);
		
	//	styleright.setBorderBottom(CellStyle.BORDER_THIN);
		styleright.setBorderBottom(BorderStyle.THIN);
		
	//	styleright.setBorderLeft(CellStyle.BORDER_THIN);
		styleright.setBorderLeft(BorderStyle.THIN);
		
		CellStyle style = wb.createCellStyle();
		
//		 style.setAlignment(CellStyle.ALIGN_LEFT);
		 style.setAlignment(HorizontalAlignment.LEFT);
		 
	//	 style.setBorderRight(CellStyle.BORDER_THIN);
		 style.setBorderRight(BorderStyle.THIN);
		 
	  //      style.setBorderTop(CellStyle.BORDER_THIN);
	        style.setBorderTop(BorderStyle.THIN);
	       
	       
	        
	    	style.setBorderBottom(BorderStyle.THIN);
	    //	 style.setBorderBottom(CellStyle.BORDER_THIN);
	    	 
	    //    style.setBorderLeft(CellStyle.BORDER_THIN);
	        style.setBorderLeft(BorderStyle.THIN);
	        
	        CellStyle styleCenter = wb.createCellStyle();
	        
	    //    styleCenter.setAlignment(CellStyle.ALIGN_CENTER);
	    //    styleCenter.setBorderLeft(CellStyle.BORDER_THIN);
	     //   styleCenter.setBorderTop(CellStyle.BORDER_THIN);
	     //   styleCenter.setBorderBottom(CellStyle.BORDER_THIN);
	        
	        style.setAlignment(HorizontalAlignment.CENTER);
	    	style.setBorderLeft(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
	        
	        
	        
		Sheet sheet = wb.getSheetAt(0); 
		sheet.getRow(2).getCell(4).setCellValue(reptrcl);
		sheet.getRow(3).getCell(4).setCellValue(roleName);
		List<String[]> curRoleTopicList = excelData.get(0);	
		int sNo=1;
		int rowNo=7;
		
		int rowIndex2=9;
		String state=null;
		for(int i=2;i<curRoleTopicList.size();i++)
		{
			String[] curTopicList = curRoleTopicList.get(i);
			CommonMessage.debugMsg("curTopicList:"+curTopicList);
			if(rowNo<9)
			{
				state="if";	
			CommonMessage.debugMsg("rowNo:"+rowNo);
			sheet.getRow(rowNo).getCell(1).setCellValue(sNo);
			sheet.getRow(rowNo).getCell(2).setCellValue(curTopicList[6]);
			sheet.getRow(rowNo).getCell(5).setCellValue(curTopicList[8]);
			sheet.getRow(rowNo).getCell(8).setCellValue(curTopicList[9]);
			sheet.getRow(rowNo).getCell(10).setCellValue(curTopicList[10]);
			sheet.getRow(rowNo).getCell(11).setCellValue(curTopicList[11]);
			sheet.getRow(rowNo).getCell(12).setCellValue(curTopicList[12]);
			sheet.getRow(rowNo).getCell(13).setCellValue(curTopicList[13]);
			rowNo++;
			sNo++;
			}
			else
			{
				state="else";
				row = sheet.createRow(rowIndex2);
				CellRangeAddress region = CellRangeAddress.valueOf("C"+rowIndex2+":E"+rowIndex2);
				sheet.addMergedRegion( region );
				
			//	final short borderMediumDashed = CellStyle.BORDER_THIN;
				BorderStyle borderMediumDashed = BorderStyle.THIN ;
			//	final short borderMediumDashed = 	BorderStyle.THIN;
			
		        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet );
		        RegionUtil.setBorderTop( borderMediumDashed,region, sheet );
		        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet);
		        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
		        
		        CellRangeAddress region1 = CellRangeAddress.valueOf("F"+rowIndex2+":H"+rowIndex2);
				sheet.addMergedRegion( region1 );
				
				RegionUtil.setBorderBottom( borderMediumDashed,region1, sheet );
		        RegionUtil.setBorderTop( borderMediumDashed,region1, sheet );
		        RegionUtil.setBorderLeft( borderMediumDashed,region1, sheet );
		        RegionUtil.setBorderRight( borderMediumDashed, region1, sheet );
		        CellRangeAddress region2 = CellRangeAddress.valueOf("I"+rowIndex2+":J"+rowIndex2);
				sheet.addMergedRegion( region2 );
				RegionUtil.setBorderBottom( borderMediumDashed,region2, sheet );
		        RegionUtil.setBorderTop( borderMediumDashed,region2, sheet);
		        RegionUtil.setBorderLeft( borderMediumDashed,region2, sheet );
		        RegionUtil.setBorderRight( borderMediumDashed, region2, sheet );
		        CellRangeAddress region3 = CellRangeAddress.valueOf("Q"+rowIndex2+":T"+rowIndex2);
				CommonMessage.debugMsg("rowIndex1:"+rowIndex2);
				sheet.addMergedRegion( region3 );
				
			        RegionUtil.setBorderBottom( borderMediumDashed,region3, sheet );
			        RegionUtil.setBorderTop( borderMediumDashed,region3, sheet);
			        RegionUtil.setBorderLeft( borderMediumDashed,region3, sheet);
			        RegionUtil.setBorderRight( borderMediumDashed, region3, sheet);
		       
		        row.createCell(1).setCellValue(sNo);
		      row.getCell(1).setCellStyle(styleCenter); 
				row.createCell(2).setCellValue(curTopicList[6]);
			 row.getCell(2).setCellStyle(style); 
				row.createCell(5).setCellValue(curTopicList[8]);
				row.getCell(5).setCellStyle(style); 
				row.createCell(8).setCellValue(curTopicList[9]);
				row.getCell(8).setCellStyle(styleright); 
				row.createCell(10).setCellValue(curTopicList[10]);
			row.getCell(10).setCellStyle(styleright); 
				row.createCell(11).setCellValue(curTopicList[11]);
			 row.getCell(11).setCellStyle(styleright); 
				row.createCell(12).setCellValue(curTopicList[12]);
			 row.getCell(12).setCellStyle(styleright); 
				row.createCell(13).setCellValue(curTopicList[13]);
			 row.getCell(13).setCellStyle(styleright); 
			 row.createCell(15).setCellStyle(styleCenter);
				rowIndex2++;
				sNo++;
			}
			
		}
		
	if(state.equals("else"))
		{
			CellRangeAddress region = CellRangeAddress.valueOf("C"+rowIndex2+":E"+rowIndex2);
			sheet.addMergedRegion( region );
			
	//	    borderMediumDashed = CellStyle.BORDER_THIN;
			BorderStyle borderMediumDashed = BorderStyle.THIN ;
			
	        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet);
	        RegionUtil.setBorderTop( borderMediumDashed,region, sheet );
	        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet );
	        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
	        CellRangeAddress region1 = CellRangeAddress.valueOf("F"+rowIndex2+":H"+rowIndex2);
			sheet.addMergedRegion( region1 );
			RegionUtil.setBorderBottom( borderMediumDashed,region1, sheet );
	        RegionUtil.setBorderTop( borderMediumDashed,region1, sheet );
	        RegionUtil.setBorderLeft( borderMediumDashed,region1, sheet );
	        RegionUtil.setBorderRight( borderMediumDashed, region1, sheet );
	        CellRangeAddress region2 = CellRangeAddress.valueOf("I"+rowIndex2+":J"+rowIndex2);
			sheet.addMergedRegion( region2 );
			RegionUtil.setBorderBottom( borderMediumDashed,region2, sheet );
	        RegionUtil.setBorderTop( borderMediumDashed,region2, sheet );
	        RegionUtil.setBorderLeft( borderMediumDashed,region2, sheet );
	        RegionUtil.setBorderRight( borderMediumDashed, region2, sheet );
	        CellRangeAddress region3 = CellRangeAddress.valueOf("Q"+rowIndex2+":T"+rowIndex2);
			CommonMessage.debugMsg("rowIndex1:"+rowIndex2);
			sheet.addMergedRegion( region3 );
			
		        RegionUtil.setBorderBottom( borderMediumDashed,region3, sheet);
		        RegionUtil.setBorderTop( borderMediumDashed,region3, sheet );
		        RegionUtil.setBorderLeft( borderMediumDashed,region3, sheet );
		        RegionUtil.setBorderRight( borderMediumDashed, region3, sheet );
		       
	       // row.createCell(1).setCellValue(sNo);
	      row.getCell(1).setCellStyle(styleCenter); 
			//row.createCell(2).setCellValue(curTopicList[6]);
		 row.getCell(2).setCellStyle(style); 
			//row.createCell(5).setCellValue(curTopicList[8]);
			row.getCell(5).setCellStyle(style); 
			//row.createCell(8).setCellValue(curTopicList[9]);
 row.getCell(8).setCellStyle(styleright); 
		//	row.createCell(10).setCellValue(curTopicList[10]);
		row.getCell(10).setCellStyle(styleright); 
		//	row.createCell(11).setCellValue(curTopicList[11]);
		 row.getCell(11).setCellStyle(styleright); 
			//row.createCell(12).setCellValue(curTopicList[12]);
		 row.getCell(12).setCellStyle(styleright); 
		//	row.createCell(13).setCellValue(curTopicList[13]);
		 row.getCell(13).setCellStyle(styleright); 
			
		}
		List<String[]> curRoleEmployeeList = excelData.get(1);
		int rowNumber=7;
		  int a=9;
		  int rowIndex1 =a;
		  int list1Size=curRoleTopicList.size();
		  CommonMessage.debugMsg("list1Size:"+list1Size);
		  
		  if(list1Size>2)
		  {
			  list1Size-=4;
			  rowIndex1+=list1Size;
			  CommonMessage.debugMsg("rowIndex1assign:"+rowIndex1);
			 
		  }
		  else if(list1Size==2)
		  {
			  rowIndex1 =a;
		  }
		  
		sNo=1;
		String status=null;
		for(int i=1;i<curRoleEmployeeList.size();i++)
		{
			CommonMessage.debugMsg("curRoleEmployeeList:"+curRoleEmployeeList.size());
			
			String[] curEmployeeList = curRoleEmployeeList.get(i);
			
			
			if(rowNumber<rowIndex1 && status!="else")
				{
				status="if";
				sheet.getRow(rowNumber).getCell(15).setCellValue(sNo);
				sheet.getRow(rowNumber).getCell(16).setCellValue(curEmployeeList[2]);
				rowNumber++;
				sNo++;
				CommonMessage.debugMsg("sNo:"+sNo);}
			else 
			{	CellRangeAddress region = CellRangeAddress.valueOf("Q"+rowIndex1+":T"+rowIndex1);
			sheet.addMergedRegion( region );
				CommonMessage.debugMsg("rowIndex...."+rowIndex1);
				//CommonMessage.debugMsg("j...."+j);
				 row = sheet.createRow(rowIndex1);
				 
			       
				row.createCell(15).setCellValue(sNo); 
				row.getCell(15).setCellStyle(styleCenter);
				row.createCell(16).setCellValue(curEmployeeList[2]);
				
				BorderStyle borderMediumDashed = BorderStyle.THIN;
				 
			        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet );
			        RegionUtil.setBorderTop( borderMediumDashed,region, sheet );
			        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet );
			        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
				rowIndex1++;
				sNo++;
				CommonMessage.debugMsg("sNo:"+sNo);
				status="else";
			}
		}
		if(status.equals("else"))
		{	CommonMessage.debugMsg("inside else");
			CellRangeAddress region = CellRangeAddress.valueOf("Q"+rowIndex1+":T"+rowIndex1);
			CommonMessage.debugMsg("rowIndex1:"+rowIndex1);
			sheet.addMergedRegion( region );
		//	 final short borderMediumDashed = CellStyle.BORDER_THIN;
			 BorderStyle borderMediumDashed = BorderStyle.THIN;
			 
		        RegionUtil.setBorderBottom( borderMediumDashed,region, sheet );
		        RegionUtil.setBorderTop( borderMediumDashed,region, sheet );
		        RegionUtil.setBorderLeft( borderMediumDashed,region, sheet );
		        RegionUtil.setBorderRight( borderMediumDashed, region, sheet );
		}
		return wb;
	}

}
