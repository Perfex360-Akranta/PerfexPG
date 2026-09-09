package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.OplService;
import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class OplExcelTemplate {

	OplService oplService ;
	
    public OplExcelTemplate(DBActionTemplate dbActionTemplate){
         oplService = new OplServiceImpl(dbActionTemplate);
    }
    
    public Workbook fillValues(Map<Integer,List<String[]>> oplData,String format,String path,String oplId,String imagePath) throws Exception
    {
	
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/OPL.xlsx";	
		
		InputStream inp = new FileInputStream(path+excelPath); 
		
		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0); 		
		
		 Font header1Font = wb.createFont();
         header1Font.setFontHeightInPoints((short)15);
         header1Font.setBold(true);
         header1Font.setColor(IndexedColors.BLACK.getIndex());
         header1Font.setFontName(XLConditionalFormats.FONT_WEBDINGS);
         CellStyle style = wb.createCellStyle();
         style.setAlignment(HorizontalAlignment.CENTER);
         style.setVerticalAlignment(VerticalAlignment.CENTER);        
         
         style.setBorderRight(BorderStyle.THIN);
         style.setBorderBottom(BorderStyle.THIN);
         style.setFont(header1Font);
         
		List<String[]> oplExcelData = oplData.get(0);	
		CommonMessage.debugMsg("oplData.get(0)"+oplData.get(0));
		CommonMessage.debugMsg("............."+oplExcelData.size());
		if(oplExcelData!=null && oplExcelData.size() > 1 )
		{
			
				String [] impshtexl = oplExcelData.get(1);
				
				CommonMessage.debugMsg("impshtexl:"+impshtexl);
				sheet.getRow(4).getCell(13).setCellValue(impshtexl[1]);//preparation date
				sheet.getRow(6).getCell(13).setCellValue(impshtexl[2]);//prepared by
				sheet.getRow(6).getCell(10).setCellValue(impshtexl[16]);//department
				sheet.getRow(6).getCell(11).setCellValue(impshtexl[18]);//manager
				sheet.getRow(6).getCell(12).setCellValue(impshtexl[18]);//leader
				sheet.getRow(4).getCell(1).setCellValue(impshtexl[6]);//theme 6
				sheet.getRow(9).getCell(1).setCellValue(impshtexl[13]);//lesson 13
				sheet.getRow(8).getCell(0).setCellValue(impshtexl[9]);//Present Condition
				sheet.getRow(8).getCell(7).setCellValue(impshtexl[10]);//Present Condition
				if(impshtexl[8] != null)
				{
					if(impshtexl[8].contains("B"))
					{
						sheet.getRow(6).getCell(1).setCellStyle(style);
						sheet.getRow(6).getCell(1).setCellValue("a");//tick
					}
					if(impshtexl[8].contains("T"))
					{	
						sheet.getRow(6).getCell(7).setCellStyle(style);
						sheet.getRow(6).getCell(7).setCellValue("a");//tick
					}
					if(impshtexl[8].contains("I"))
					{	
						sheet.getRow(6).getCell(3).setCellStyle(style);
						sheet.getRow(6).getCell(3).setCellValue("a");//tick
					}
				}
				
				excelFormat=impshtexl[0];				
		}
		
		List<String[]> oplExcelStud = oplData.get(1);	
		CommonMessage.debugMsg(oplExcelStud.size());
		if(oplExcelStud!=null && oplExcelStud.size() > 1 )
		{
			
				String [] impshtexl = oplExcelStud.get(1);					
				sheet.getRow(10).getCell(2).setCellValue(impshtexl[1]);//date excecuted
				sheet.getRow(12).getCell(2).setCellValue(impshtexl[2]);//Student				
				excelFormat=impshtexl[0];				
		}
	
		CommonMessage.debugMsg("Image path, id");
		List<ExcelInsertImage> imageType = new ArrayList();
		
		List<GenTlAllmoduleimgfile> oplImgList  = oplService.getoplImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,oplId);
		CommonMessage.debugMsg("Image Length..."+oplImgList.size());
		
		for(int i=0;i<oplImgList.size();i++)
		{
			if(i==oplImgList.size()-1)
			{
				ExcelInsertImage  xlImg = new ExcelInsertImage();			
				xlImg.col1=7;
				xlImg.row1 = 7;
				xlImg.imageFileName=oplImgList.get(i).getImflFilename();			
				imageType.add(xlImg);	
			}
			else
			{
				ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
				xlImg1.col1=1;
				xlImg1.row1 = 7;
				xlImg1.imageFileName=oplImgList.get(i).getImflFilename();
				imageType.add(xlImg1);
			}
			/*ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
			xlImg1.col1=7;
			xlImg1.row1 = 7;
			xlImg1.imageFileName=oplImgList.get(i).getImflFilename();
			imageType.add(xlImg1);*/
			
		}
		/*if(oplImgList!=null && oplImgList.size() > 0 )
		{
			GenTlAllmoduleimgfile img = null;
			GenTlAllmoduleimgfile img2 = null;
			if(oplImgList.get(0) != null)
				img = oplImgList.get(0);
			if(oplImgList.get(1) != null)
				img2 = oplImgList.get(1);
				//if("PRE".equals(img.getImflImagetype()))
			if(img != null)
				{
					ExcelInsertImage  xlImg = new ExcelInsertImage();							
					xlImg.col1=1;
					xlImg.row1 = 7;
					xlImg.imageFileName=img.getImflFilename();
					CommonMessage.debugMsg("Add Image Datas11......"+img.getImflFilename());
					imageType.add(xlImg);	
				}
				else if(img2 != null)
				{					
					CommonMessage.debugMsg("test....");
					CommonMessage.debugMsg("test123....."+img2.getImflFilename());
								
					ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
					xlImg1.col1=7;
					xlImg1.row1 = 7;
					xlImg1.imageFileName=img2.getImflFilename();
					CommonMessage.debugMsg("test....");
					CommonMessage.debugMsg("test...."+xlImg1.imageFileName);
					
					imageType.add(xlImg1);
				}
	
				
				CommonMessage.debugMsg("img2....");
				
				CommonMessage.debugMsg("test123...."+imageType);
				
			
			
		}*/
		ExcelUtils.addImages(wb,sheet,imageType);
		/*if( oplData!=null && oplData.size() > 0 )
		{
			for(int i=0; i<oplData.size();i++)
			{
				String [] impshtexl = oplData.get(i);
				for(int j=0;j<impshtexl.length;j++)
				{CommonMessage.debugMsg("oplData "+j+":"+impshtexl[j]);}
				excelFormat=impshtexl[0];	
			}
			List<String []> oplStudentList  = oplService.getAllStudent(OPLId);
			
			CommonMessage.debugMsg("Length.."+oplStudentList.size());
			
			for(int i=0; i<oplStudentList.size();i++)
			{
				String [] impshtexl = oplStudentList.get(i);
				for(int j=0;j<impshtexl.length;j++)
				{
				CommonMessage.debugMsg("impshtexl:"+impshtexl[j]);
				}
				 sheet.getRow(10).getCell(2).setCellValue(impshtexl[1]);
				 sheet.getRow(11).getCell(2).setCellValue(impshtexl[2]);
				 sheet.getRow(12).getCell(2).setCellValue(impshtexl[3]);
				
				excelFormat=impshtexl[0];	
			}
			
			CommonMessage.debugMsg("excelPath.."+excelPath);
		
			CommonMessage.debugMsg("Excel Template to be generate...."+oplData.size()+"...");		
			//InputStream inp = new FileInputStream(path+"/Kaizen.xls");    			    
		    //HSSFWorkbook wb = new HSSFWorkbook(inp);
			List<ExcelInsertImage> imageType = new ArrayList();
			
			String fileName=UIUtils.TPM_TEMPIMG_DIR;
			
			List<GenTlAllmoduleimgfile> oplTlMstList  = oplService.getoplImage(fileName,imagePath,OPLId);
			
			CommonMessage.debugMsg("oplTlMstList...."+oplTlMstList.size());
			
			if(oplTlMstList.size() != 0)
			{
			for(int i=0;i<oplTlMstList.size();i++)
			{
				GenTlAllmoduleimgfile img = oplTlMstList.get(i);
				String imageFileName=imagePath+ (img.getImflFilename()).replace(UIUtils.TPM_TEMPIMG_DIR, "");
				CommonMessage.debugMsg("kaizenImage..."+img.getImflImagetype()+fileName);
			
				ExcelInsertImage  xlImg = new ExcelInsertImage();
				
				if(img.getImflImagetype().equals("AFT")) 
				{ 				
					xlImg.col1=2;
					xlImg.row1 = 16;
				}
				else if(img.getImflImagetype().equals("RES"))
				{			
					xlImg.col1=6;
					xlImg.row1 = 32;
				}
				else if(img.getImflImagetype().equals("PRE"))
				{			
					xlImg.col1=5;
					xlImg.row1 = 16;
				}
				xlImg.imageFileName=fileName;
				imageType.add(xlImg);
			}
			ExcelUtils.addImages(wb,sheet,imageType);
			
		}
		}
    */
		 sheet.protectSheet("admin");
		return wb;		
		
	}



}
