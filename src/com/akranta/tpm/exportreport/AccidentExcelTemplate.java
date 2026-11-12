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
import com.akranta.tpm.bean.SheAccIncBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlIncidentmst;
import com.akranta.tpm.service.AccidentIncidentRptingService;
import com.akranta.tpm.service.OplService;
import com.akranta.tpm.service.impl.AccidentIncidentRptingServiceImpl;
import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class AccidentExcelTemplate {
	AccidentIncidentRptingService acctIncRptService ;
	
    public AccidentExcelTemplate(DBActionTemplate dbActionTemplate){
         acctIncRptService = new AccidentIncidentRptingServiceImpl(dbActionTemplate);
    	
    }
    
    public Workbook fillValues(Map<Integer, List<String[]>> accInvestData ,String format,String path,String imagePath,String insKeyid) throws Exception
    {	
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/Accident Report.xlsx";	
		
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
         
		List<String[]> cur1 = accInvestData.get(0);	
		List<String[]> cur2 = accInvestData.get(1);	
		
		
		String [] impshtexl = cur1.get(2);		
		CommonFunctions.debugMsg("whywhyStartRow : 1");
		
		if(cur1!=null && cur1.size() > 1 )
		{		
			sheet.getRow(0).getCell(11).setCellValue(impshtexl[0]);//Accident No
			sheet.getRow(8).getCell(2).setCellValue(impshtexl[1]);//name
			sheet.getRow(8).getCell(5).setCellValue(impshtexl[3]);//age
			sheet.getRow(8).getCell(7).setCellValue(impshtexl[2]);//Designation
			sheet.getRow(8).getCell(11).setCellValue(impshtexl[4]);//Emp No
			
			sheet.getRow(9).getCell(2).setCellValue(impshtexl[5].substring(0, 11));//Date of accident
			sheet.getRow(9).getCell(7).setCellValue(impshtexl[5].substring(12,19));//time of accident
			
			sheet.getRow(10).getCell(3).setCellValue(impshtexl[14]);//eXPERIENCE
			sheet.getRow(9).getCell(11).setCellValue(impshtexl[6]);//report date
			sheet.getRow(11).getCell(2).setCellValue(impshtexl[7]);//Eqp No
			sheet.getRow(11).getCell(7).setCellValue(impshtexl[8]);//Loc
			sheet.getRow(12).getCell(2).setCellValue(impshtexl[9]);//Prop Loss
			sheet.getRow(12).getCell(7).setCellValue(impshtexl[10]);//accident direct cost
			sheet.getRow(14).getCell(1).setCellValue(impshtexl[11]);//Breaf desc
			sheet.getRow(18).getCell(1).setCellValue(impshtexl[12]);//facts revalved desc
			//sheet.getRow(21).getCell(1).setCellValue(impshtexl[13]);//action prevent
			//sheet.getRow(21).getCell(9).setCellValue(impshtexl[15]);//target date
			//sheet.getRow(21).getCell(11).setCellValue(impshtexl[16]);//status
			//sheet.getRow(21).getCell(6).setCellValue(impshtexl[14]);//Responsbility
			
			
			
			//sheet.getRow(23).getCell(4).setCellValue(impshtexl[13]);//analysis
			CommonFunctions.debugMsg("whywhyStartRow : 2");
			excelFormat=impshtexl[0];				
		}
		
		int startRow = 21;
		
		CellStyle hiddenstyle = wb.createCellStyle();		
		hiddenstyle.setHidden(true);
		CommonFunctions.debugMsg("whywhyStartRow : 3");
		if(cur2!=null && cur2.size() > 1 )
		{	
			CommonFunctions.debugMsg("whywhyStartRow : 4");
			 for(int j=1 ;j< cur2.size()-1;j++)
	  				
			   {
				 String [] actionPlanData = cur2.get(j+1);
				 CommonFunctions.debugMsg("j : "+j);
				 CommonFunctions.debugMsg("whywhyStartRow : "+startRow);
				  if(j>=3)
					  sheet.createRow( startRow+j);
				  
				 CommonFunctions.debugMsg("llllll : "+startRow);
	  				sheet.getRow(startRow + (j*1)-1).getCell(1).setCellValue(actionPlanData[0]);	
	  				sheet.getRow(startRow + (j*1)-1).getCell(6).setCellValue(actionPlanData[1]);
	  				sheet.getRow(startRow + (j*1)-1).getCell(9).setCellValue(actionPlanData[2]);	
	  				sheet.getRow(startRow + (j*1)-1).getCell(11).setCellValue(actionPlanData[3]);
	  				sheet.getRow(startRow + (j*1)-1).setRowStyle(hiddenstyle);
	  				sheet.getRow(startRow + (j*1)-1).setRowStyle(hiddenstyle);
	  				sheet.getRow(startRow + (j*1)-1).setRowStyle(hiddenstyle);
	  			
			   }
			
			
		}
		
		
		CommonFunctions.debugMsg("Image path, id");
		List<ExcelInsertImage> imageType = new ArrayList();
		
		
		//List<GenTlAllmoduleimgfile> oplImgList  = oplService.getoplImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,oplId);
		List<GenTlAllmoduleimgfile> accincImgList  = acctIncRptService.getImageExcel(UIUtils.TPM_TEMPIMG_DIR,imagePath,insKeyid);
		CommonFunctions.debugMsg("Image Length..."+accincImgList.size());
		CommonFunctions.debugMsg("Image Length..."+imagePath);
		
		if(accincImgList.size()>0)
		{
		CommonFunctions.debugMsg("Image name..."+accincImgList.get(0).getImflFilename());
		CommonFunctions.debugMsg("Image name..."+accincImgList.get(1).getImflFilename());
		
		for(int i=0;i<accincImgList.size();i++)
		{
			if(i==accincImgList.size()-1)
			{
				ExcelInsertImage  xlImg = new ExcelInsertImage();			
				xlImg.col1=7;
				xlImg.row1 = 4;
				xlImg.imageFileName=imagePath+accincImgList.get(i).getImflFilename();			
				imageType.add(xlImg);	
			}
			else
			{
				ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
				xlImg1.col1=1;
				xlImg1.row1 = 4;
				xlImg1.imageFileName=imagePath+accincImgList.get(i).getImflFilename();
				imageType.add(xlImg1);
			}
			
		
	}
		ExcelUtils.addImages(wb,sheet,imageType);
		}
		
		 sheet.protectSheet("admin");
			return wb;		

}
}