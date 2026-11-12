package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.service.EHSAccidentIncidentRptingService;
import com.akranta.tpm.service.impl.EHSAccidentIncidentRptingServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class ACCIDENTITCExcelTemplate {
	
	EHSAccidentIncidentRptingService nearMissService;
	public ACCIDENTITCExcelTemplate(DBActionTemplate dbActionTemplate) {
		nearMissService = new EHSAccidentIncidentRptingServiceImpl(dbActionTemplate);
	}
	public Workbook fillValues(List<String[]> getfillaccidentdata ,List<String[]> accidentEmpData,List<String[]>accidentresData,String path,String keyid,String imagePath,String Documentdesc)throws Exception {
		// TODO Auto-generated method stub
	
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/Accident.xlsx";
	    System.out.println(" For excelPath :::::"+excelPath);

		InputStream inp = new FileInputStream(path + excelPath);
	    System.out.println(" For inp :::::"+inp);

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
        if(getfillaccidentdata !=null && getfillaccidentdata.size()>0){
          for(g=0;g<getfillaccidentdata.size();g++){
        	System.out.println("The getfillaccidentdata:::"+getfillaccidentdata.size());
	    	
    	    sheet.getRow(2).getCell(2).setCellValue(getfillaccidentdata.get(g)[0]);//INCIDENT NO
   	        System.out.println(" For Incident No:::::"+getfillaccidentdata.get(g)[0]);
	    	sheet.getRow(3).getCell(2).setCellValue(getfillaccidentdata.get(g)[1]);//INCIDENT/ACCIDENT CATEGORY
  	        System.out.println(" For Incident Category No:::::"+getfillaccidentdata.get(g)[1]);
	    	sheet.getRow(4).getCell(2).setCellValue(getfillaccidentdata.get(g)[2]);//DMT
 	        System.out.println(" For DMT No:::::"+getfillaccidentdata.get(g)[2]);
	    	sheet.getRow(4).getCell(7).setCellValue(getfillaccidentdata.get(g)[3]);//JH
 	        System.out.println(" For JH No:::::"+getfillaccidentdata.get(g)[3]);
	    	sheet.getRow(5).getCell(2).setCellValue(getfillaccidentdata.get(g)[4]);//OCCURED DATE TIME
 	        System.out.println(" For OCCURED DATE TIME:::::"+getfillaccidentdata.get(g)[4]);
	    	sheet.getRow(5).getCell(7).setCellValue(getfillaccidentdata.get(g)[5]);//AREA
 	        System.out.println(" For AREA:::::"+getfillaccidentdata.get(g)[5]);
	    	sheet.getRow(6).getCell(2).setCellValue(getfillaccidentdata.get(g)[6]);//REPORTED DATE TIME
 	        System.out.println(" For REPORTED DATE TIME:::::"+getfillaccidentdata.get(g)[6]);
	    	sheet.getRow(6).getCell(7).setCellValue(getfillaccidentdata.get(g)[7]);//REPORTED BY
 	        System.out.println(" For REPORTED BY:::::"+getfillaccidentdata.get(g)[7]);
 	        sheet.getRow(8).getCell(2).setCellValue(getfillaccidentdata.get(g)[8]);//DESCRIPTION OF ACCIDENT/INCIDENT
 	        System.out.println(" For DESCRIPTION OF ACCIDENT/INCIDENT :::::"+getfillaccidentdata.get(g)[8]);
 	        sheet.getRow(9).getCell(2).setCellValue(getfillaccidentdata.get(g)[9]);//RECOMMENDATIONS FROM REPORTER
 	        System.out.println(" For RECOMMENDATIONS FROM REPORTER :::::"+getfillaccidentdata.get(g)[9]);    
 	        sheet.getRow(16).getCell(2).setCellValue(getfillaccidentdata.get(g)[10]);//INVESTIGATED BY  //15-10
 	        System.out.println(" For INVESTIGATED BY :::::"+getfillaccidentdata.get(g)[10]);
 	        sheet.getRow(16).getCell(6).setCellValue(getfillaccidentdata.get(g)[11]);//INVEATIGATION DATE TIME
 	        System.out.println(" For INVEATIGATION DATE TIME :::::"+getfillaccidentdata.get(g)[11]); 	        
	    	sheet.getRow(17).getCell(2).setCellValue(getfillaccidentdata.get(g)[12]);//INVEATIGATION //11-16
 	        System.out.println(" For INVEATIGATION :::::"+getfillaccidentdata.get(g)[12]); 	        
	    	
 	        sheet.getRow(19).getCell(2).setCellValue(getfillaccidentdata.get(g)[13]);//WITNESS1
	        System.out.println(" For WITNESS1 :::::"+getfillaccidentdata.get(g)[13]); 	        
	    	
	        sheet.getRow(19).getCell(7).setCellValue(getfillaccidentdata.get(g)[14]);//WITNESS2
	        System.out.println(" For WITNESS2 :::::"+getfillaccidentdata.get(g)[14]); 	        
	    	

            }
    	  }
        
        int g3;
        
        if(accidentresData !=null && accidentresData.size()>0){
            for(g3=0;g3<accidentresData.size();g3++){
          	System.out.println("The accidentresData:::"+accidentresData.size());
  	    	
          	 
 	        sheet.getRow(23).getCell(2).setCellValue(accidentresData.get(g3)[0]);//RECOMMENDED CORRECTIVE ACTION
	    	System.out.println(" For RECOMMENDED CORRECTIVE ACTION :::::"+accidentresData.get(g3)[0]);
	    	sheet.getRow(24).getCell(2).setCellValue(accidentresData.get(g3)[1]);//RESPONSIBILITY
	    	System.out.println(" For RESPONSIBILITY :::::"+accidentresData.get(g3)[1]);
	    	sheet.getRow(24).getCell(7).setCellValue(accidentresData.get(g3)[2]);//TARGET DATE
	    	System.out.println(" For TARGET DATE :::::"+accidentresData.get(g3)[2]);
	    	sheet.getRow(25).getCell(2).setCellValue(accidentresData.get(g3)[3]);//ACTION TAKEN
	    	System.out.println(" For ACTION TAKEN :::::"+accidentresData.get(g3)[3]);
	    	
	    	sheet.getRow(26).getCell(2).setCellValue(accidentresData.get(g3)[4]);//COMPLETED BY
	    	System.out.println(" For COMPLETED BY:::::"+accidentresData.get(g3)[4]);
	    	sheet.getRow(26).getCell(7).setCellValue(accidentresData.get(g3)[5]);//COMPLETED DATE
	    	System.out.println(" For COMPLETED DATE :::::"+accidentresData.get(g3)[5]);
	    	sheet.getRow(27).getCell(2).setCellValue(accidentresData.get(g3)[6]);//VERIFIED BY
	    	System.out.println(" For VERIFIED BY:::::"+accidentresData.get(g3)[6]);
	    	sheet.getRow(27).getCell(7).setCellValue(accidentresData.get(g3)[7]);//VERIFIED DATE
	    	System.out.println(" For VERIFIED DATE :::::"+accidentresData.get(g3)[7]); 	
            }
        }
          	
          	
	   int g1;
	   int strRow=12;
	   if(accidentEmpData !=null && accidentEmpData.size()>0){
		   System.out.println("accidentEmpData:::::"+accidentEmpData.size());
		  for(g1=0;g1<accidentEmpData.size();g1++){
	     System.out.println(" For accidentEmpData:::::"+accidentEmpData.size());
	     sheet.getRow(strRow+(g1)).getCell(1).setCellValue(accidentEmpData.get(g1)[2]);//EMPLOYEE NO
	     sheet.getRow(strRow+(g1-0)).getCell(1).setCellValue(accidentEmpData.get(g1)[2]);//EMPLOYEE NO
	     System.out.println(" For Employee No:::::"+accidentEmpData.get(g1)[2]);
	     
	     sheet.getRow(strRow+(g1)).getCell(2).setCellValue(accidentEmpData.get(g1)[3]);//EMPLOYEE NAME
	     sheet.getRow(strRow+(g1-0)).getCell(2).setCellValue(accidentEmpData.get(g1)[3]);//EMPLOYEE NAME
	     System.out.println(" For Employee Name:::::"+accidentEmpData.get(g1)[3]);
	     
	     sheet.getRow(strRow+(g1)).getCell(6).setCellValue(accidentEmpData.get(g1)[4]);//INJURY TYPE
	     sheet.getRow(strRow+(g1-0)).getCell(6).setCellValue(accidentEmpData.get(g1)[4]);//INJURY TYPE
	     System.out.println(" For Employee Injury Type:::::"+accidentEmpData.get(g1)[4]);
	     sheet.getRow(strRow+(g1)).getCell(8).setCellValue(accidentEmpData.get(g1)[5]);//INJURY LOCATION
	     sheet.getRow(strRow+(g1-0)).getCell(8).setCellValue(accidentEmpData.get(g1)[5]);//INJURY LOCATION
	     System.out.println(" For Employee Location :::::"+accidentEmpData.get(g1)[5]);
		  }    
	   }
        List<ExcelInsertImage> imageType = new ArrayList();
        List<GenTlAllmoduleimgfile> IncidentImage =nearMissService.getIncidentAllImages(UIUtils.TPM_TEMPIMG_DIR,imagePath,keyid);		
		System.out.println("The Incident Image"+IncidentImage.size()+"imagePath"+imagePath);
		for(int i=0;i<IncidentImage.size();i++)
		{
			  String imagetype=IncidentImage.get(i).getImflImagetype();
			   System.out.println("The imageType"+imagetype);
			   if("IN1".equals(imagetype)){
				 System.out.println("The imageType"+imagetype);
				ExcelInsertImage  xlImg = new ExcelInsertImage();			
				xlImg.row1 =20;
				xlImg.col1=1;
				xlImg.imageFileName=IncidentImage.get(i).getImflFilename();			
				imageType.add(xlImg);
			 }
				  else if("IN2".equals(imagetype)){
			        System.out.println("The imageType"+imagetype);
					ExcelInsertImage  xlImg1 = new ExcelInsertImage();			
					xlImg1.row1 =20;
					xlImg1.col1=4;
					xlImg1.imageFileName=IncidentImage.get(i).getImflFilename();			
					imageType.add(xlImg1);
				  }
				  else if("IN3".equals(imagetype)){
				        System.out.println("The imageType"+imagetype);
						ExcelInsertImage  xlImg2 = new ExcelInsertImage();			
						xlImg2.row1 =21;
						xlImg2.col1=1;
						xlImg2.imageFileName=IncidentImage.get(i).getImflFilename();			
						imageType.add(xlImg2);
					  }
				  else if("IN4".equals(imagetype)){
				        System.out.println("The imageType"+imagetype);
						ExcelInsertImage  xlImg3 = new ExcelInsertImage();			
						xlImg3.row1 =21;
						xlImg3.col1=4;
						xlImg3.imageFileName=IncidentImage.get(i).getImflFilename();			
						imageType.add(xlImg3);
					  }
				  else if("IN5".equals(imagetype)){
				        System.out.println("The imageType"+imagetype);
						ExcelInsertImage  xlImg4 = new ExcelInsertImage();			
						xlImg4.row1 =22;
						xlImg4.col1=1;
						xlImg4.imageFileName=IncidentImage.get(i).getImflFilename();			
						imageType.add(xlImg4);
					  }
				  else if("IN6".equals(imagetype)){
				        System.out.println("The imageType"+imagetype);
						ExcelInsertImage  xlImg4 = new ExcelInsertImage();			
						xlImg4.row1 =22;
						xlImg4.col1=4;
						xlImg4.imageFileName=IncidentImage.get(i).getImflFilename();			
						imageType.add(xlImg4);
					  }
		}
		 ExcelUtils.addImages(wb,sheet,imageType);	
         sheet.protectSheet("admin");
         return wb;
   }
}