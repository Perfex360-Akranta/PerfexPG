package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.impl.KaizenServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class KaizenExcelTemplate {
	KaizenServices kaizenServices ;
	
	
    public KaizenExcelTemplate(DBActionTemplate dbActionTemplate){
		kaizenServices = new KaizenServiceImpl(dbActionTemplate);
    }

	public Workbook fillValues(Map<Integer,List<String[]>> kaizenData,List<String[]> ApprovalData,List<String[]> getkznHDScanTbl,String format,String path,String kaizId,String imagePath,String workFlow, List<String[]> teamdata, List<String[]> activitydata) throws Exception
	{
	
		String excelFormat = "L";
		String excelPath = null;
		
		excelPath = "/KaizenItc.xlsx";		
		
		
		CommonMessage.debugMsg("excelpath:::"+excelPath);
		
		CommonMessage.debugMsg(" Inside Excel Template :: teamdata "+teamdata.size());
		
		InputStream inp = new FileInputStream(path+excelPath); 
				
		
		CommonMessage.debugMsg(" activitydata :: Inside Excel Template "+activitydata.size());
		
		CommonMessage.debugMsg(" activitydata :: Inside Excel Template "+activitydata.get(0)[0]);
		
		Workbook wb = new XSSFWorkbook(inp);
		//CellStyle style = wb.createCellStyle();
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
        
		/*
		 * style.setBorderLeft(BorderStyle.THIN); style.setBorderTop(BorderStyle.THIN);
		 */
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);   
        
        
      
        style.setFont(header1Font);
                
		List<GenTlAllmoduleimgfile> kaizenImage =  kaizenServices.getKaizenImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,kaizId);
		CommonMessage.debugMsg("Size...."+kaizenImage.size());
		CommonMessage.debugMsg(" Inside Excel Templete kaizenData :: "+kaizenData);
		CommonMessage.debugMsg(" Inside Excel Templete ApprovalData :: "+ApprovalData.size());
		CommonMessage.debugMsg(" Inside Excel Templete getkznHDScanTbl :: "+getkznHDScanTbl.size());
		
		
		
			if( kaizenImage != null && kaizenImage.size()>0 ){
			
				List<ExcelInsertImage> imageType = new ArrayList<ExcelInsertImage>();
				for(int i=0;i<kaizenImage.size();i++)
				{
					GenTlAllmoduleimgfile img = kaizenImage.get(i);
					String fileName=imagePath+ (img.getImflFilename()).replace(UIUtils.TPM_TEMPIMG_DIR, "");
				
					
					ExcelInsertImage  xlImg = new ExcelInsertImage();
					
					CommonMessage.debugMsg(" Inside Excel Template :: "+img.getImflImagetype());
					
					if(img.getImflImagetype().equals("AFT")) 
					{ 				
						xlImg.col1=5;
						xlImg.row1 = 13;
					}
					else if(img.getImflImagetype().equals("RES"))
					{			
						xlImg.col1=5;
						xlImg.row1 = 21;
						
					}
					else if(img.getImflImagetype().equals("BEN"))
					{			
						xlImg.col1=11;
						xlImg.row1 = 21;
						
					}
					else if(img.getImflImagetype().equals("PRE"))
					{			
						xlImg.col1=1;
						xlImg.row1 = 13;
						
					}
					xlImg.imageFileName=fileName;
					
					imageType.add(xlImg);
				}
				ExcelUtils.addImages(wb,sheet,imageType);
				
			}
	
		CommonMessage.debugMsg("After img");
		List<String[]> curExcel = kaizenData.get(0);	
		int g;
		CommonMessage.debugMsg("curExcel...."+curExcel.size());
		
		//&& !workFlow.equals("modify") && !workFlow.equals("view") && !workFlow.equals("create")
		
		
		if(activitydata!=null && activitydata.size()>0 )
		{
			CommonMessage.debugMsg(" Inside Excel Template "+workFlow);
			String Pillar=activitydata.get(0)[0];
			if(("KK").equals(Pillar)){
			   sheet.getRow(5).getCell(11).setCellStyle(style1);
			}else if(("JH").equals(Pillar)){
			   //sheet.getRow(5).getCell(12).setCellValue("Color.gray");
			   sheet.getRow(5).getCell(12).setCellStyle(style1);
			}else if(("QM").equals(Pillar)){
			   sheet.getRow(5).getCell(13).setCellStyle(style1);
			}else if(("ET").equals(Pillar)){
			   sheet.getRow(5).getCell(14).setCellStyle(style1);
			}else if(("PM").equals(Pillar)){
			   sheet.getRow(5).getCell(15).setCellStyle(style1);
			}else if(("EHS").equals(Pillar)){
			   sheet.getRow(5).getCell(16).setCellStyle(style1);
			}else if(("OTPM").equals(Pillar)){
			   sheet.getRow(5).getCell(17).setCellStyle(style1);
			}else if(("EM").equals(Pillar)){
			   sheet.getRow(5).getCell(18).setCellStyle(style1);
			}
			
		}
		
		if(ApprovalData!=null && ApprovalData.size()>0 )
		{
			CommonMessage.debugMsg(" Inside Excel Template "+workFlow);
			CommonMessage.debugMsg("Inside Excel Template"+workFlow);
			CommonMessage.debugMsg("G Size "+ApprovalData.size());
			for(g=0;g<ApprovalData.size();g++){
				int strRow =26;
				if (g<7) {
			    	CommonMessage.debugMsg("impshtexls Checking ::::"+ApprovalData.get(g)[0]);
			    	CommonMessage.debugMsg("impshtexls Checking ::::"+ApprovalData.get(g)[1]);
			    	CommonMessage.debugMsg("impshtexls Checking34 ::::"+ApprovalData.get(g)[3]);
			    	strRow = strRow +g;
			    	sheet.getRow(strRow).getCell(11).setCellValue(ApprovalData.get(g)[0]);
					sheet.getRow(strRow).getCell(13).setCellValue(ApprovalData.get(g)[1]);
					sheet.getRow(strRow).getCell(15).setCellValue(ApprovalData.get(g)[2]);
					sheet.getRow(strRow).getCell(16).setCellValue(ApprovalData.get(g)[4]);
				}
			}
		}
		
		if(teamdata!=null && teamdata.size()>=0)
		{
		
			for(g=0;g<teamdata.size();g++){
				
				CommonMessage.debugMsg(" Inside Team data "+teamdata.get(g)[0]);
				CommonMessage.debugMsg(" Inside Equipment data "+teamdata.get(g)[1]);
				
				sheet.getRow(6).getCell(1).setCellValue(teamdata.get(g)[0]);//Team Member.....
				sheet.getRow(6).getCell(5).setCellValue(teamdata.get(g)[1]);
				
					
		    }
		
			
		}
		
		if(getkznHDScanTbl!=null && getkznHDScanTbl.size()>=0)
		{
			
			//List<String[]> impshtexlHD = getkznHDScanTbl;   && !workFlow.equals("modify") && !workFlow.equals("view")
			
			CommonMessage.debugMsg(" Inside Excel Template "+workFlow);
			
			for(g=0;g<getkznHDScanTbl.size();g++){
				int strRow =32;
				strRow = strRow +g;
				if (g<4) {
					//sheet.getRow(strRow).getCell(6).setCellValue(getkznHDScanTbl.get(g)[3]);
					sheet.getRow(strRow).getCell(6).setCellValue(getkznHDScanTbl.get(g)[2]);
					sheet.getRow(strRow).getCell(7).setCellValue(getkznHDScanTbl.get(g)[4]);
					sheet.getRow(strRow).getCell(8).setCellValue(getkznHDScanTbl.get(g)[5]);
					sheet.getRow(strRow).getCell(10).setCellValue(getkznHDScanTbl.get(g)[6]);
				}
		    	
				CommonMessage.debugMsg("g HD Checking For HD Data value for the function");
		      
		    	
		    }
			
			
			
		}
		
		if(curExcel!=null && curExcel.size() > 0 )
		{
			
		    String [] impshtexl = curExcel.get(1);
		    for(g=0;g<impshtexl.length;g++){
		    	CommonMessage.debugMsg("g value for the function");
		    	CommonMessage.debugMsg("impshtexl["+g+"]::::"+impshtexl[g]);
		    }
				    //sheet.getRow(8).getCell(11).setCellValue(impshtexl[15]);//Idea...
				    
				    sheet.getRow(9).getCell(11).setCellValue(impshtexl[0]);//Kaizen Serial Number...
				    sheet.getRow(9).getCell(15).setCellValue(impshtexl[1]);//Equipment.......
				    //sheet.getRow(6).getCell(1).setCellValue(impshtexl[9]);//Team Name ...
				    sheet.getRow(9).getCell(1).setCellValue(impshtexl[6]);//Theme ........
				    sheet.getRow(10).getCell(16).setCellValue(impshtexl[9]);//Target......
				    sheet.getRow(11).getCell(13).setCellValue(impshtexl[10]);//Kaizen Start...
				    sheet.getRow(12).getCell(13).setCellValue(impshtexl[11]);//Kaizen Finish...
				    sheet.getRow(9).getCell(5).setCellValue(impshtexl[15]);// Kaizen Idea...
				    sheet.getRow(11).getCell(1).setCellValue(impshtexl[12]);//Problem status ...
				    sheet.getRow(11).getCell(5).setCellValue(impshtexl[16]);//counter Measure ...
				    
				    
				    if (UIUtils.isValidKeyId(impshtexl[5]))
				        sheet.getRow(14).getCell(11).setCellValue(impshtexl[5]);//Team.....

				    if (UIUtils.isValidKeyId(impshtexl[47]))
				        sheet.getRow(20).getCell(5).setCellValue(impshtexl[47]);//Results.....

				    if (UIUtils.isValidKeyId(impshtexl[18]))
				        sheet.getRow(20).getCell(11).setCellValue(impshtexl[18]);//Benefit ...

//				    if (UIUtils.isValidKeyId(impshtexl[49]))
//				        sheet.getRow(20).getCell(1).setCellValue(impshtexl[49]);//Results.....
				    if (UIUtils.isValidKeyId(impshtexl[14]))
				        sheet.getRow(22).getCell(1).setCellValue(impshtexl[14]);
				   // impshtexl[14]::::OOOO 
				    if (UIUtils.isValidKeyId(impshtexl[52]))
				        sheet.getRow(22).getCell(1).setCellValue(impshtexl[52]);//Results.....

				    if (UIUtils.isValidKeyId(impshtexl[51]))
				        sheet.getRow(10).getCell(13).setCellValue(impshtexl[51]);//Base Line.....
				    
				    //ExcelUtils.setRowHeight(wb, sheet, 9, 1, impshtexl[6], 40);
				    //ExcelUtils.setRowHeight(wb, sheet, 9, 5, impshtexl[15], 40);
				    //ExcelUtils.setRowHeight(wb, sheet, 11, 1, impshtexl[12], 40);
				    //ExcelUtils.setRowHeight(wb, sheet, 11, 5, impshtexl[16], 40);
				    //ExcelUtils.setRowHeight(wb, sheet, 14, 11, impshtexl[16], 40);
				    //ExcelUtils.setRowHeight(wb, sheet, 20, 5, impshtexl[49], 40);
				    				    
				    if(impshtexl[50] != null)
					{
						if(impshtexl[50].contains("P"))
						{
							sheet.getRow(7).getCell(11).setCellStyle(style);
							sheet.getRow(7).getCell(11).setCellValue("�");//tick
						}
						if(impshtexl[50].contains("Q"))
						{	
							sheet.getRow(7).getCell(12).setCellStyle(style);
							sheet.getRow(7).getCell(12).setCellValue("�");//tick
						}
						if(impshtexl[50].contains("C"))
						{	
							sheet.getRow(7).getCell(13).setCellStyle(style);
							sheet.getRow(7).getCell(13).setCellValue("�");//tick
						}
						if(impshtexl[50].contains("D"))
						{	
							sheet.getRow(7).getCell(14).setCellStyle(style);
							sheet.getRow(7).getCell(14).setCellValue("�");//tick
						}
						if(impshtexl[50].contains("S"))
						{	
							sheet.getRow(7).getCell(15).setCellStyle(style);
							sheet.getRow(7).getCell(15).setCellValue("�");//tick
						}
						if(impshtexl[50].contains("M"))
						{	
							sheet.getRow(7).getCell(16).setCellStyle(style);
							sheet.getRow(7).getCell(16).setCellValue("�");//tick
						}
						if(impshtexl[50].contains("E"))
						{	
							sheet.getRow(7).getCell(17).setCellStyle(style);
							sheet.getRow(7).getCell(17).setCellValue("�");//tick
						}
						
						
					}
				  
		  			excelFormat = impshtexl[0];


		  			
			
		}
		sheet.protectSheet("admin");
		return wb;			 
		}	
}
