
	package com.akranta.tpm.exportreport;

	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Map;

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
	import com.akranta.tpm.model.GenTlAllmoduleimgfile;
	import com.akranta.tpm.service.OplService;
	import com.akranta.tpm.service.impl.OplServiceImpl;
	import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
	import com.akranta.tpm.utils.ExcelUtils;

	public class OPLGeneralTemplate {

		OplService oplService;

		public OPLGeneralTemplate(DBActionTemplate dbActionTemplate) {
			oplService = new OplServiceImpl(dbActionTemplate);
		}

		public Workbook fillValues(Map<Integer, List<String[]>> oplData,
				List<String[]> approvalData, List<String[]> funclcnData, String format, String path, String oplId, String imagePath)
				throws Exception {
			CommonMessage.debugMsg("gvfdgdf");
			String excelFormat = null;
			String excelPath = null;
			excelPath = "/OPL GENERAL.xlsx";
			CommonMessage.debugMsg("Xl path........" + path + excelPath);
			
			InputStream inp = new FileInputStream(path + excelPath);
			CommonMessage.debugMsg("gvfdgdf1112");
			Workbook wb = new XSSFWorkbook(inp);
			inp.close();
			
			Sheet sheet = wb.getSheetAt(0);
			CommonMessage.debugMsg("gvfdgdf12");
			Font header1Font = wb.createFont();
			header1Font.setFontHeightInPoints((short) 15);
	//		header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			header1Font.setBold(true);
			header1Font.setColor(IndexedColors.BLACK.getIndex());
			header1Font.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			CellStyle style = wb.createCellStyle();
	//		style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
		//	style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			CommonMessage.debugMsg("gvfdgdf23");
			
	//		style.setBorderTop(CellStyle.BORDER_NONE);
	//		style.setBorderLeft(CellStyle.BORDER_NONE);
	//		style.setBorderRight(CellStyle.BORDER_NONE);
	//		style.setBorderBottom(CellStyle.BORDER_NONE);
			
			style.setBorderTop(BorderStyle.NONE);
			style.setBorderLeft(BorderStyle.NONE);
			style.setBorderRight(BorderStyle.NONE);
			style.setBorderBottom(BorderStyle.NONE);
			CommonMessage.debugMsg("gvfdgdf34");
			
	//		style.setBorderTop(CellStyle.BORDER_THIN);
	//		style.setBorderLeft(CellStyle.BORDER_THIN);
	//		style.setBorderRight(CellStyle.BORDER_THIN);
	//		style.setBorderBottom(CellStyle.BORDER_THIN);
			
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setBorderBottom(BorderStyle.THIN);
			
			style.setFont(header1Font);
			CommonMessage.debugMsg("gvfdgdft67");
			CommonFilter commonFilter = new CommonFilter();		
			String Empillar=commonFilter.getIC();
			CommonMessage.debugMsg(" Inside Excel Template Empillar :: "+Empillar);
			CommonMessage.debugMsg("gvfdgdf6789");
			List<String[]> oplExcelData = oplData.get(0);
			
			CommonMessage.debugMsg("oplData.get(0)" + oplId+" List size "+oplExcelData.size());
			CommonMessage.debugMsg(" List Data  01 " + oplExcelData.get(0)[0]+" List Data  02 "+oplExcelData.get(0)[1]+" List Data  03 "+oplExcelData.get(0)[2]);

	        //CommonMessage.debugMsg(" "+oplExcelData.);
	        
			    int g;  
			
			    		    
			    
				if(approvalData !=null && approvalData.size()>0){
					
					for(g=0;g<approvalData.size();g++){
						
					    	String Approvaldata=approvalData.get(g)[0]; 
					    	String Approvaldate=approvalData.get(g)[1]; 
					    	
					    	CommonMessage.debugMsg("impshtexls Checking ::::"+Approvaldata);
					    	
					    	CommonMessage.debugMsg(" Checking For Approval Date :: "+approvalData.get(g)[2]);
					    	
					    	if("JHLEADER".equals(Approvaldata.replaceAll(" ","").toUpperCase())){
					    	    sheet.getRow(8).getCell(14).setCellValue(approvalData.get(g)[1]);
						    	if (UIUtils.isValidKeyId(approvalData.get(g)[2]))
					    	        sheet.getRow(10).getCell(14).setCellValue(approvalData.get(g)[2]);
					    	} 
					    	
					    	if("DMTLEADER".equals(Approvaldata.replaceAll(" ","").toUpperCase())){
					    	    sheet.getRow(8).getCell(15).setCellValue(approvalData.get(g)[1]);
					    	    if (UIUtils.isValidKeyId(approvalData.get(g)[2]))
					    	        sheet.getRow(10).getCell(15).setCellValue(approvalData.get(g)[2]);
					    	}
				    	    
						
					    }
					}
				
				  CommonMessage.debugMsg(" funclcnData :: "+funclcnData.size());
		 	    
	             if(funclcnData !=null && funclcnData.size()>=1){
					
					for(g=0;g<funclcnData.size();g++){
						
							CommonMessage.debugMsg("funclcnData Checking  ["+g+"]  g"+funclcnData.get(g)[0]);
					    	CommonMessage.debugMsg("funclcnData Checking ["+g+"]  g"+funclcnData.get(g)[1]);
					    	CommonMessage.debugMsg("funclcnData Checking ["+g+"]  g"+funclcnData.get(g)[2]);
					    	CommonMessage.debugMsg("funclcnData Checking ["+g+"]  g"+funclcnData.get(g)[3]);
					    	CommonMessage.debugMsg("funclcnData Checking ["+g+"]  g"+funclcnData.get(g)[4]);
					    	//CommonMessage.debugMsg("impshtexls Checking ["+g+"]  g"+funclcnData.get(g)[5]);
					    
					    	String FunclcnDatadata=funclcnData.get(g)[0]; 
					    	
					    	CommonMessage.debugMsg("impshtexls Checking ::::"+FunclcnDatadata);
					    	
					    	
					    	sheet.getRow(2).getCell(8).setCellValue(funclcnData.get(g)[0]);
					    	sheet.getRow(2).getCell(10).setCellValue(funclcnData.get(g)[1]);
					    	sheet.getRow(2).getCell(12).setCellValue(funclcnData.get(g)[2]);
					    	sheet.getRow(2).getCell(14).setCellValue(funclcnData.get(g)[3]);
					    	sheet.getRow(2).getCell(15).setCellValue(funclcnData.get(g)[4]);
					    	sheet.getRow(2).getCell(13).setCellValue(approvalData.get(g)[1]);
					    	
					    	/*if("JH LEADER".equals(Approvaldata)){
					    	    sheet.getRow(8).getCell(14).setCellValue(approvalData.get(g)[1]);
					    	}
					    	if("DMT Leader".equals(Approvaldata)){
					    	sheet.getRow(8).getCell(15).setCellValue(approvalData.get(g)[1]);
					    	}*/
							
						}
					}


					
				
			if (oplExcelData != null && oplExcelData.size() > 0) {
				
				CommonMessage.debugMsg(" Inside If :::oplExcelData::: ");
	            String[] impshtexl = oplExcelData.get(1);
				CommonMessage.debugMsg("impshtexl234456"+impshtexl.length);
				
				for (int i = 0; i < impshtexl.length; i++) {
					 CommonMessage.debugMsg("impshtexl["+i+"]"+impshtexl[i]);
				}

				CommonMessage.debugMsg("impshtexl4546:" + impshtexl.toString());
				sheet.getRow(4).getCell(3).setCellValue(impshtexl[0]);//tHEME
				sheet.getRow(4).getCell(14).setCellValue(impshtexl[1]);//OPL NO
				sheet.getRow(5).getCell(14).setCellValue(impshtexl[2]);//Prepared Date
				 CommonMessage.debugMsg("45678");
				if(impshtexl[3] != null)
				{
					if(impshtexl[3].contains("B"))
					{
						sheet.getRow(8).getCell(4).setCellStyle(style);
						sheet.getRow(8).getCell(4).setCellValue("�");//Classification Basic
					}
					 CommonMessage.debugMsg("4451");
					if(impshtexl[3].contains("T"))
					{	
						sheet.getRow(8).getCell(8).setCellStyle(style);
						sheet.getRow(8).getCell(8).setCellValue("�");//tick
					}
					CommonMessage.debugMsg("4451241");
					if(impshtexl[3].contains("I"))
					{	
						sheet.getRow(8).getCell(6).setCellStyle(style);
						sheet.getRow(8).getCell(6).setCellValue("�");//tick
					}
				}
				CommonMessage.debugMsg("4451gjuygh");
				
			/*	if( impshtexl[8].equals("Y"))  
				{
					sheet.getRow(13).getCell(1).setCellValue("General");
					//sheet.getRow(13).getCell(1).setCellValue("How");
  				 // sheet.getRow(23).getCell(1).setCellValue("General");
				}else if( impshtexl[8].equals("Y"))  
				{
					sheet.getRow(11).getCell(1).setCellValue("Not Ok");
					//sheet.getRow(13).getCell(1).setCellValue("Ok");
					sheet.getRow(23).getCell(1).setCellValue("Ok");
				}
		
				if( impshtexl[9].equals("Y"))  
				{
					sheet.getRow(11).getCell(1).setCellValue("Present");
					//sheet.getRow(13).getCell(1).setCellValue("After");
					//sheet.getRow(23).getCell(1).setCellValue("After");
				}
				*/
				sheet.getRow(8).getCell(10).setCellValue(impshtexl[4]);
				sheet.getRow(13).getCell(10).setCellValue(impshtexl[5]);
				sheet.getRow(24).getCell(10).setCellValue(impshtexl[6]);
				sheet.getRow(13).getCell(2).setCellValue(impshtexl[10]);
				sheet.getRow(8).getCell(12).setCellValue(impshtexl[7]);
				excelFormat = impshtexl[0];
				
				CommonMessage.debugMsg("4451udnj7t");
				
				List<ExcelInsertImage> imageType = new ArrayList();
				CommonMessage.debugMsg("gfdsggkdgkdhjggkdfskghkfsgk");
				List<GenTlAllmoduleimgfile> oplImgList  = oplService.getoplImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,oplId);
				CommonMessage.debugMsg("Image Length..."+oplImgList.size());
				
				
				for(int i=0;i<oplImgList.size();i++)
				{
					CommonMessage.debugMsg(" Inside Excel Template Before "+oplImgList.get(i).getImflFilename());
					
					String imagetype=oplImgList.get(i).getImflImagetype();
					CommonMessage.debugMsg(" imagetype :: "+imagetype);
					
					//if(i==oplImgList.size()-1)
					if("PRE".equals(imagetype))
					{
						ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
						xlImg1.col1=2;
						xlImg1.row1 = 13;
						xlImg1.imageFileName=oplImgList.get(i).getImflFilename();
						imageType.add(xlImg1);
					}
					else
					{
						
						ExcelInsertImage  xlImg = new ExcelInsertImage();			
						xlImg.col1=2;
						xlImg.row1 =13 ;
						xlImg.imageFileName=oplImgList.get(i).getImflFilename();			
						imageType.add(xlImg);
						
					}
				}
				
				ExcelUtils.addImages(wb,sheet,imageType);
				
				
				
				
			}
			sheet.protectSheet("admin");
			return wb;
		
		}
	}
	
