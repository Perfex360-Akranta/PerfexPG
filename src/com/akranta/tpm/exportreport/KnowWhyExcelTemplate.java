package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.service.KnowWhyService;
import com.akranta.tpm.service.impl.KnowWhyServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;


public class KnowWhyExcelTemplate {

	KnowWhyService  knowWhyService;
	
	public KnowWhyExcelTemplate(DBActionTemplate dbActionTemplate) {
		knowWhyService = new KnowWhyServiceImpl(dbActionTemplate);
		
	}

	

	public Workbook fillValues(List<String[]> knowwhyData, List<String[]> approvalData, String format,
			String path, String knowId, String imagePath) throws Exception {
		// TODO Auto-generated method stub

		String excelFormat = null;
		String excelPath = null;
		excelPath = "/Know-WhyAnalysis.xlsx";
		InputStream inp = new FileInputStream(path + excelPath);
//private String restorefile(String keyId, String fileDir, String imagepath, QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception, Exception {
		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);
		
		List<GenTlAllmoduleimgfile> knwImage =  knowWhyService.getKnwwhyImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,knowId);
			if( knwImage != null && knwImage.size()>0 ){
			
				List<ExcelInsertImage> imageType = new ArrayList<ExcelInsertImage>();
				for(int i=0;i<knwImage.size();i++)
				{
					GenTlAllmoduleimgfile img = knwImage.get(i);
					String fileName=imagePath+ (img.getImflFilename()).replace(UIUtils.TPM_TEMPIMG_DIR, "");
					ExcelInsertImage  xlImg = new ExcelInsertImage();
					
			 				
						xlImg.col1=1;
						xlImg.row1 = 11;
						xlImg.imageFileName=fileName;
						
						imageType.add(xlImg);
					}
					ExcelUtils.addImages(wb,sheet,imageType);
					
				}
		Font header1Font = wb.createFont();
		header1Font.setFontHeightInPoints((short) 15);
		header1Font.setBold(true);
		header1Font.setColor(IndexedColors.BLACK.getIndex());
		header1Font.setFontName(XLConditionalFormats.FONT_WEBDINGS);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);

		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setFont(header1Font);
		
		 
		CommonMessage.debugMsg(" approvalData.size()"+approvalData.size());
		
		if(approvalData !=null && approvalData.size()>=1){
			
			for(int g=0;g<approvalData.size();g++){
                    CommonMessage.debugMsg("impshtexls Checking  ["+g+"]  g"+approvalData.get(g)[0]);
			    	CommonMessage.debugMsg("impshtexls Checking ["+g+"]  g"+approvalData.get(g)[1]);
			    	String Approvaldata=approvalData.get(g)[0]; 
			    	CommonMessage.debugMsg("impshtexls Checking ::::"+Approvaldata);
			    	//sheet.getRow(4).getCell(3).setCellValue(approvalData.get(g)[1]);
			    }
			}
		
		int row=7;
		for(int j=0 ; j < knowwhyData.size(); j++ ){
		
		String[] KnowwhyExcelData = knowwhyData.get(j);
		
		for (int i = 0; i < KnowwhyExcelData.length; i++) {
			 CommonMessage.debugMsg("KnowwhyExcelData["+i+"]"+KnowwhyExcelData[i]);
		}
		
         if (KnowwhyExcelData != null && KnowwhyExcelData.length > 0) {
			
			sheet.getRow(1).getCell(6).setCellValue(KnowwhyExcelData[7]);
			CommonMessage.debugMsg("The KnowwhyExcelData 7::::"+KnowwhyExcelData[7]);

			sheet.getRow(2).getCell(3).setCellValue(KnowwhyExcelData[6]);
			CommonMessage.debugMsg("The KnowwhyExcelData 6::::"+KnowwhyExcelData[6]);

			sheet.getRow(4).getCell(1).setCellValue(KnowwhyExcelData[3]);//preparation date
			CommonMessage.debugMsg("The KnowwhyExcelData 3::::"+KnowwhyExcelData[3]);

			sheet.getRow(4).getCell(2).setCellValue(KnowwhyExcelData[0]);//preparation date
			CommonMessage.debugMsg("The KnowwhyExcelData 0::::"+KnowwhyExcelData[0]);
			//sheet.getRow(4).getCell(3).setCellValue(KnowwhyExcelData[1]);//prepared by
			sheet.getRow(3).getCell(6).setCellValue(KnowwhyExcelData[4]);
			CommonMessage.debugMsg("The KnowwhyExcelData 4::::"+KnowwhyExcelData[4]);
			
			sheet.getRow(3).getCell(9).setCellValue(KnowwhyExcelData[15]);
			sheet.getRow(row+j).getCell(9).setCellValue(KnowwhyExcelData[15]);
			//sheet.getRow(3).getCell(9).setCellValue(knowwhyData.get(j)[15]);
			CommonMessage.debugMsg("The Version No::"+KnowwhyExcelData[15]);
			//CommonMessage.debugMsg("The Version No::"+KnowwhyExcelData[15]);
			//CommonMessage.debugMsg("The Version No::"+KnowwhyExcelData[6]);
			
			sheet.getRow(7).getCell(1).setCellValue(KnowwhyExcelData[2]);
			CommonMessage.debugMsg("The KnowwhyExcelData 2::::"+KnowwhyExcelData[2]);

			
			sheet.getRow(row+j).getCell(6).setCellValue(KnowwhyExcelData[8]);
			sheet.getRow(row+j).getCell(5).setCellValue(j+1);
			sheet.getRow(row+j).getCell(7).setCellValue(KnowwhyExcelData[9]);
			sheet.getRow(row+j).getCell(8).setCellValue(KnowwhyExcelData[10]);
			sheet.getRow(row+j).getCell(9).setCellValue(KnowwhyExcelData[11]);
			sheet.getRow(8).getCell(1).setCellValue(KnowwhyExcelData[5]);
			sheet.getRow(row+j).getCell(10).setCellValue(KnowwhyExcelData[12]);
           
			
			//sheet.getRow(1).getCell(9).setCellValue(KnowwhyExcelData[12]);
			//sheet.getRow(1).getCell(9).setCellValue(KnowwhyExcelData[13]);
			//CommonMessage.debugMsg("KnowwhyExcelData[12]"+KnowwhyExcelData[12]);
			sheet.getRow(2).getCell(9).setCellValue("1 of 1");
			sheet.getRow(2).getCell(6).setCellValue(KnowwhyExcelData[13]);
			CommonMessage.debugMsg("KnowwhyExcelData[13]"+KnowwhyExcelData[13]);
			excelFormat = KnowwhyExcelData[0];
		}
          //row=row+1;
         
         CommonMessage.debugMsg(" Inside Excel Template :: "+approvalData.size());
         
         if(approvalData!=null && approvalData.size()>0 )
 		{
 			CommonMessage.debugMsg(" Inside Excel Template "+approvalData);
 			for(int g=0;g<approvalData.size();g++){
 				
		    	CommonMessage.debugMsg("impshtexls Checking :::: get(g)[0] "+approvalData.get(g)[0]);
		    	CommonMessage.debugMsg("impshtexls Checking :::: get(g)[1] "+approvalData.get(g)[2]);
		    	
		    	if(UIUtils.isValidKeyId( approvalData.get(g)[2]) ){
			    	sheet.getRow(4).getCell(3).setCellValue(approvalData.get(g)[1]);
			    	sheet.getRow(4).getCell(5).setCellValue(approvalData.get(g)[2]);
		    	}
 			}
 		}
       
         
         
	}
		
		sheet.protectSheet("admin");
		return wb;
	
	}
}
	
	

