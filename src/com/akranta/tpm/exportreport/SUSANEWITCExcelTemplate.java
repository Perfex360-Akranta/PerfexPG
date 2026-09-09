package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
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
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.NewSusaService;
import com.akranta.tpm.service.impl.NewSusaServiceImpl;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;

public class SUSANEWITCExcelTemplate{
	NewSusaService newSusaService;
	public SUSANEWITCExcelTemplate(DBActionTemplate dbActionTemplate) {
		newSusaService = new NewSusaServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> SusaDetailsData,List<String[]>susaBehaviourData,String path,String keyid,String imagePath)throws Exception {
		// TODO Auto-generated method stub
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/NewSuSA.xlsx";
	    CommonMessage.debugMsg(" For excelPath :::::"+excelPath);
	    CommonMessage.debugMsg("The Path is::"+path);
		InputStream inp = new FileInputStream(path + excelPath);
		CommonMessage.debugMsg(" For Path & Excel :::::"+inp);
		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);
		
		Font header1Font = wb.createFont();
        header1Font.setFontHeightInPoints((short)15);
        header1Font.setBold(true);
        header1Font.setColor(IndexedColors.BLACK.getIndex());
        header1Font.setFontName(XLConditionalFormats.FONT_WINGDINGS);
        
		CellStyle style = wb.createCellStyle();
	    CellStyle style1 = wb.createCellStyle();
	        
        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //style1.setFillPattern(CellStyle.DIAMONDS);
        
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);        
        
        style.setBorderTop(BorderStyle.NONE);
		style.setBorderLeft(BorderStyle.NONE);
		style.setBorderRight(BorderStyle.NONE);
		style.setBorderBottom(BorderStyle.NONE);
		
        style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		
    	style.setFont(header1Font);
    	
    	int g;
        if(SusaDetailsData !=null && SusaDetailsData.size()>0){
          for(g=0;g<SusaDetailsData.size();g++){
    	    sheet.getRow(2).getCell(2).setCellValue(SusaDetailsData.get(g)[0]);//DOCUMENT NO
   	        //CommonMessage.debugMsg(" For Susa No:::::"+SusaDetailsData.get(g)[0]);
   	        sheet.getRow(2).getCell(5).setCellValue(SusaDetailsData.get(g)[1]);//Date & Time
	       // CommonMessage.debugMsg(" For Date & Time:::::"+SusaDetailsData.get(g)[1]);
	        sheet.getRow(2).getCell(7).setCellValue(SusaDetailsData.get(g)[2]);//Discussion Type
	       // CommonMessage.debugMsg(" For Discussion Type:::::"+SusaDetailsData.get(g)[2]);
   	        
	        sheet.getRow(3).getCell(2).setCellValue(SusaDetailsData.get(g)[3]);//No of Person
 	       // CommonMessage.debugMsg(" For No of Person:::::"+SusaDetailsData.get(g)[3]);
 	        sheet.getRow(3).getCell(6).setCellValue(SusaDetailsData.get(g)[4]);//Role of Person
 	        //CommonMessage.debugMsg(" For Role Of Person:::::"+SusaDetailsData.get(g)[4]);
 	       	sheet.getRow(4).getCell(2).setCellValue(SusaDetailsData.get(g)[5]);//DMT,JH
            //CommonMessage.debugMsg(" For JH:::::"+SusaDetailsData.get(g)[5]);
            sheet.getRow(4).getCell(7).setCellValue(SusaDetailsData.get(g)[6]);//PREPARED BY 
           // CommonMessage.debugMsg(" For PREPARED BY :::::"+SusaDetailsData.get(g)[6]);
            sheet.getRow(5).getCell(2).setCellValue(SusaDetailsData.get(g)[7]);//DISCUSSION SUMM
            //CommonMessage.debugMsg(" For DISCUSSION SUMM:::::"+SusaDetailsData.get(g)[7]);
            sheet.getRow(6).getCell(2).setCellValue(SusaDetailsData.get(g)[8]);//SUSA DONE JH
            //CommonMessage.debugMsg(" For PARTICIPANTS OTHERS:::::"+SusaDetailsData.get(g)[8]);
            sheet.getRow(7).getCell(2).setCellValue(SusaDetailsData.get(g)[9]);//PARTICIPANTS OTHERS
          //  CommonMessage.debugMsg(" For PARTICIPANTS OTHERS:::::"+SusaDetailsData.get(g)[9]);
          }
        }
        
          int g1;
 	   int strRow=10;
 	   if(susaBehaviourData !=null && susaBehaviourData.size()>0){
 	   for(g1=0;g1<susaBehaviourData.size();g1++){
 	     sheet.getRow(strRow+(g1)).getCell(1).setCellValue(susaBehaviourData.get(g1)[0]);//TYPE
 	     sheet.getRow(strRow+(g1-0)).getCell(1).setCellValue(susaBehaviourData.get(g1)[0]);//TYPE
 	     //CommonMessage.debugMsg(" TYPE:::::"+susaBehaviourData.get(g1)[0]);
 	     sheet.getRow(strRow+(g1)).getCell(2).setCellValue(susaBehaviourData.get(g1)[1]);//BEHAVIOUR CATEGORY
 	     sheet.getRow(strRow+(g1-0)).getCell(2).setCellValue(susaBehaviourData.get(g1)[1]);//BEHAVIOUR CATEGORY
 	    // CommonMessage.debugMsg(" For Behaviour Category:::::"+susaBehaviourData.get(g1)[1]);
 	     sheet.getRow(strRow+(g1)).getCell(3).setCellValue(susaBehaviourData.get(g1)[2]);//BEHAVIOUR
 	     sheet.getRow(strRow+(g1-0)).getCell(3).setCellValue(susaBehaviourData.get(g1)[2]);//BEHAVIOUR
 	     //CommonMessage.debugMsg(" For Behaviour:::::"+susaBehaviourData.get(g1)[2]);
 	     sheet.getRow(strRow+(g1)).getCell(4).setCellValue(susaBehaviourData.get(g1)[3]);//CAUSE
 	     sheet.getRow(strRow+(g1-0)).getCell(4).setCellValue(susaBehaviourData.get(g1)[3]);//CAUSE
 	     //CommonMessage.debugMsg(" For Cause :::::"+susaBehaviourData.get(g1)[3]);
 	     sheet.getRow(strRow+(g1)).getCell(5).setCellValue(susaBehaviourData.get(g1)[4]);//ACTION
 	     sheet.getRow(strRow+(g1-0)).getCell(5).setCellValue(susaBehaviourData.get(g1)[4]);//ACTION
 	     //CommonMessage.debugMsg(" For Action :::::"+susaBehaviourData.get(g1)[4]);
 	     sheet.getRow(strRow+(g1)).getCell(7).setCellValue(susaBehaviourData.get(g1)[5]);//PROBABLITY
	     sheet.getRow(strRow+(g1-0)).getCell(7).setCellValue(susaBehaviourData.get(g1)[5]);//PROBABLITY
	     //CommonMessage.debugMsg(" For PROBABLITY :::::"+susaBehaviourData.get(g1)[5]);
	     sheet.getRow(strRow+(g1)).getCell(8).setCellValue(susaBehaviourData.get(g1)[6]);//CONSEQUENCE
	     sheet.getRow(strRow+(g1-0)).getCell(8).setCellValue(susaBehaviourData.get(g1)[6]);//CONSEQUENCE
	     //CommonMessage.debugMsg(" For CONSEQUENCE :::::"+susaBehaviourData.get(g1)[6]);
 		  }    
 	   }
 	   List<ExcelInsertImage> imageType = new ArrayList();
       List<GenTlAllmoduleimgfile> SusaImage =newSusaService.getSusaAllImages(UIUtils.TPM_TEMPIMG_DIR,imagePath,keyid);		
		for(int i=0;i<SusaImage.size();i++)
		{
			  String imagetype=SusaImage.get(i).getImflImagetype();
			   //CommonMessage.debugMsg("The imageType"+imagetype);
			   if("SU1".equals(imagetype)){
				ExcelInsertImage  xlImg = new ExcelInsertImage();			
				xlImg.row1 =17;
				xlImg.col1=1;
				xlImg.imageFileName=SusaImage.get(i).getImflFilename();			
				imageType.add(xlImg);
			 }
				  else if("SU2".equals(imagetype)){
			     //   CommonMessage.debugMsg("The imageType"+imagetype);
					ExcelInsertImage  xlImg1 = new ExcelInsertImage();			
					xlImg1.row1 =17;
					xlImg1.col1=5;
					xlImg1.imageFileName=SusaImage.get(i).getImflFilename();			
					imageType.add(xlImg1);
				  }
				  else if("SU3".equals(imagetype)){
				     //   CommonMessage.debugMsg("The imageType"+imagetype);
						ExcelInsertImage  xlImg2 = new ExcelInsertImage();			
						xlImg2.row1 =21;
						xlImg2.col1=1;
						xlImg2.imageFileName=SusaImage.get(i).getImflFilename();			
						imageType.add(xlImg2);
					  }
				  else if("SU4".equals(imagetype)){
				     //   CommonMessage.debugMsg("The imageType"+imagetype);
						ExcelInsertImage  xlImg3 = new ExcelInsertImage();			
						xlImg3.row1 =21;
						xlImg3.col1=4;
						xlImg3.imageFileName=SusaImage.get(i).getImflFilename();			
						imageType.add(xlImg3);
					  }
		}
		
		 ExcelUtils.addImages(wb,sheet,imageType);	   
         sheet.protectSheet("admin");
         return wb;
   }
}