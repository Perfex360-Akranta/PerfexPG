package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//import com.akranta.tpm.bean.SheAccIncBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlIncidentmst;
import com.akranta.tpm.service.EHSPlantSafetyService;
import com.akranta.tpm.service.OplService;
import com.akranta.tpm.service.impl.EHSPlantSafetyServiceImpl;
import com.akranta.tpm.service.impl.OplServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class PlanSafetyExcelTemplate{
	EHSPlantSafetyService EhsplansafetyService ;
    public PlanSafetyExcelTemplate(DBActionTemplate dbActionTemplate){
    	EhsplansafetyService = new EHSPlantSafetyServiceImpl(dbActionTemplate);
    	
    }
    public Workbook fillValues(List<String[]> PSIData,List<String[]> Plansafafetydetaildata,List<String[]> PlanSafetyVerify,String insKeyid,String path) throws Exception
    {	
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/PSI.xlsx";	
		
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
        if(PSIData !=null && PSIData.size()>0){
          for(g=0;g<PSIData.size();g++){
        	//System.out.println("The PSIData:::"+PSIData.size());
	    	
    	    sheet.getRow(2).getCell(2).setCellValue(PSIData.get(g)[0]);//PSI NO
   	      //  System.out.println(" For PSI No:::::"+PSIData.get(g)[0]);
	    	//sheet.getRow(3).getCell(2).setCellValue(PSIData.get(g)[1]);//DMT
  	        //System.out.println(" For DMT:::::"+PSIData.get(g)[1]);
	    	sheet.getRow(3).getCell(2).setCellValue(PSIData.get(g)[1]);//JH
 	     //   System.out.println(" For JH :::::"+PSIData.get(g)[1]);
	    	sheet.getRow(3).getCell(7).setCellValue(PSIData.get(g)[2]);//JH
 	     //   System.out.println(" For JH No:::::"+PSIData.get(g)[2]);
	    	sheet.getRow(4).getCell(2).setCellValue(PSIData.get(g)[3]);//PSI TYPE
 	      //  System.out.println(" For PSI TYPE:::::"+PSIData.get(g)[3]);
	    	sheet.getRow(4).getCell(7).setCellValue(PSIData.get(g)[4]);//DATE
 	      //  System.out.println(" For DATE:::::"+PSIData.get(g)[4]);
	    	sheet.getRow(5).getCell(2).setCellValue(PSIData.get(g)[5]);//PREPARED BY
	    	sheet.getRow(6).getCell(2).setCellValue(PSIData.get(g)[6]);//MEMBER PRESENT
	    	sheet.getRow(7).getCell(2).setCellValue(PSIData.get(g)[7]);//REMARKS  	
}
}
        
     int g1;
     int strRow=11;
     
     
       if(Plansafafetydetaildata !=null && Plansafafetydetaildata.size()>0){
		  // System.out.println("Plansafafetydetaildata:::::"+Plansafafetydetaildata.size());
		  for(g1=0;g1<Plansafafetydetaildata.size();g1++){
	     //System.out.println(" For Plansafafetydetaildata:::::"+Plansafafetydetaildata.size());
	     sheet.getRow(strRow+(g1)).getCell(1).setCellValue(Plansafafetydetaildata.get(g1)[0]);//OBSERVATIONS
	     sheet.getRow(strRow+(g1-0)).getCell(1).setCellValue(Plansafafetydetaildata.get(g1)[0]);//OBSERVATIONS
	  //   System.out.println(" For OBSERVATIONS :::::"+Plansafafetydetaildata.get(g1)[0]);
	     
	     sheet.getRow(strRow+(g1)).getCell(2).setCellValue(Plansafafetydetaildata.get(g1)[1]);//TYPE
	     sheet.getRow(strRow+(g1-0)).getCell(2).setCellValue(Plansafafetydetaildata.get(g1)[1]);//TYPE
	  //   System.out.println(" For TYPE::::"+Plansafafetydetaildata.get(g1)[1]);
	     
	     sheet.getRow(strRow+(g1)).getCell(3).setCellValue(Plansafafetydetaildata.get(g1)[2]);//RELATED TO
	     sheet.getRow(strRow+(g1-0)).getCell(3).setCellValue(Plansafafetydetaildata.get(g1)[2]);//RELATED TO
	  //   System.out.println(" For RELATED TO:::::"+Plansafafetydetaildata.get(g1)[2]);
	     
	     sheet.getRow(strRow+(g1)).getCell(4).setCellValue(Plansafafetydetaildata.get(g1)[3]);//RECOMENTED ACTIONS
	     sheet.getRow(strRow+(g1-0)).getCell(4).setCellValue(Plansafafetydetaildata.get(g1)[3]);//RECOMENTED ACTIONS
	  //   System.out.println(" For RECOMENTED ACTIONS :::::"+Plansafafetydetaildata.get(g1)[3]);
     
	     sheet.getRow(strRow+(g1)).getCell(5).setCellValue(Plansafafetydetaildata.get(g1)[4]);//CORRACTIONS 
	     sheet.getRow(strRow+(g1-0)).getCell(5).setCellValue(Plansafafetydetaildata.get(g1)[4]);//CORRACTIONS 
	  //   System.out.println(" For CORRACTIONS :::::"+Plansafafetydetaildata.get(g1)[4]);
          
	     sheet.getRow(strRow+(g1)).getCell(6).setCellValue(Plansafafetydetaildata.get(g1)[5]);//RESPONSIBILIRY 
	     sheet.getRow(strRow+(g1-0)).getCell(6).setCellValue(Plansafafetydetaildata.get(g1)[5]);//RESPONSIBILIRY 
	   //  System.out.println(" For RESPONSIBILIRY :::::"+Plansafafetydetaildata.get(g1)[5]);
	     
	     sheet.getRow(strRow+(g1)).getCell(7).setCellValue(Plansafafetydetaildata.get(g1)[6]);//TARGET DATE
	     sheet.getRow(strRow+(g1-0)).getCell(7).setCellValue(Plansafafetydetaildata.get(g1)[6]);//TARGET DATE
	  //   System.out.println(" For TARGET DATE :::::"+Plansafafetydetaildata.get(g1)[6]);
	     
	     sheet.getRow(strRow+(g1)).getCell(8).setCellValue(Plansafafetydetaildata.get(g1)[7]);//STATUS
	     sheet.getRow(strRow+(g1-0)).getCell(8).setCellValue(Plansafafetydetaildata.get(g1)[7]);//STATUS
	   //  System.out.println(" For STATUS :::::"+Plansafafetydetaildata.get(g1)[7]);
	     
		  }
		  } 
       
       int g2;
       if(PlanSafetyVerify!=null && PlanSafetyVerify.size()>0)
       {
    	   for(g2=0;g2<PlanSafetyVerify.size();g2++){
    		   
    		//System.out.println("The PSIVERIFY:::"+PlanSafetyVerify.size());
       	    sheet.getRow(8).getCell(2).setCellValue(PlanSafetyVerify.get(g2)[0]);//PSI COMPLETED BY
      	   // System.out.println(" For COMPLETED BY:::::"+PlanSafetyVerify.get(g2)[0]);
   	    	sheet.getRow(8).getCell(4).setCellValue(PlanSafetyVerify.get(g2)[1]);//PSI COMPLETED DATE
    	  //  System.out.println(" For COMPLETED DATE :::::"+PlanSafetyVerify.get(g2)[1]);  
    		sheet.getRow(8).getCell(6).setCellValue(PlanSafetyVerify.get(g2)[2]);//PSI VERIFIED BY
    	  //  System.out.println(" For VERIFIED BY :::::"+PlanSafetyVerify.get(g2)[2]);  
    	    sheet.getRow(8).getCell(8).setCellValue(PlanSafetyVerify.get(g2)[3]);//PSI VERIFIED DATE
    	  //  System.out.println(" For VERIFIED DATE :::::"+PlanSafetyVerify.get(g2)[3]);
    	   }
    	   
       }     
        
    	
		 sheet.protectSheet("admin");
		return wb;		
    }
}