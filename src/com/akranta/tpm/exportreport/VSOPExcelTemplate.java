package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.bean.ExcelInsertImage;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.service.impl.VisualSOPServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
//import com.sun.rowset.internal.Row;

public class VSOPExcelTemplate {


	VisualSOPServiceImpl vsopService;

	public VSOPExcelTemplate(DBActionTemplate dbActionTemplate) {
		vsopService = new VisualSOPServiceImpl(dbActionTemplate);
	}

	public Workbook fillValues(Map<Integer, List<String[]>> vsopData,
			List<String[]> getVisualid, String format, String path, String vsopId, String imagePath)
			throws Exception {
		
		String excelFormat = null;
		String excelPath = null;
		excelPath = "/Visual_SOP.xlsx";
		
		System.out.print(path + excelPath +" Path Path");
		
		InputStream inp = new FileInputStream(path + excelPath);
		CommonMessage.debugMsg( inp+ "   0........" + vsopData );
		Workbook wb = new XSSFWorkbook(inp);
		//CommonMessage.debugMsg("1........" );
		inp.close();
		//CommonMessage.debugMsg("2........" );
		Sheet sheet = wb.getSheetAt(0);
		//CommonMessage.debugMsg("3........" );
		int k=0;
		if(getVisualid!=null && getVisualid.size()>0 )
		{ 
			CommonMessage.debugMsg("getVisualid.get(0)[0]" + getVisualid.get(0)[0] );
			String Pillar=getVisualid.get(0)[0];
			CommonMessage.debugMsg(" Checking Data :: Pillar"+Pillar);
			sheet.getRow(1).getCell(2).setCellValue(Pillar);
		}
		List<String[]> vsopExcelData = vsopData.get(0);
		
		if (vsopExcelData != null && vsopExcelData.size() >= 1) {
			int rowNo=0;
			
			NumberFormat df = DecimalFormat.getInstance();
    		df.setMinimumFractionDigits(0);
    		df.setMaximumFractionDigits(0);
    		df.setRoundingMode(RoundingMode.UP);
    		
    		float sizz = (float) (vsopExcelData.size()-1) / (float) 6;
    		sizz = Float.parseFloat( df.format(sizz) );
    		k = (int) sizz;
    		int pageNo =0;
            int rowNo1=0;
            int rowNo2=19;
            

			for (int i =1;i<vsopExcelData.size();i++)
			{
				String[] impshtexl = vsopExcelData.get(i);				
	    	    /*double j=(vsopExcelData.size()-1)/6.0;
	    		String df = new DecimalFormat("#.0000").format(j); 
                float round=Math.round(Double.parseDouble(df));*/
                //CommonMessage.debugMsg(" size divide by 6 % double "+Math.round(Double.parseDouble(df)));
                //CommonMessage.debugMsg(" size divide by 6 % round "+round);
				
				if (i == 7|| i == 13|| i == 19|| i == 25) {
					if(i == 7)
					   rowNo1=rowNo1+25;
					else
						rowNo1=rowNo1+24;
				
					rowNo2=rowNo2+24;
					
					List<ExcelInsertImage> imageType1 = new ArrayList();
					ExcelInsertImage  xlImg = new ExcelInsertImage();							
					xlImg.col1= 1;
					xlImg.row1= rowNo1;
					xlImg.imageFileName=imagePath.replace("tmp/images/","images/comp_logo.jpg");
					imageType1.add(xlImg);
					ExcelUtils.addImages(wb,sheet,imageType1);
					
					List<ExcelInsertImage> imageType2 = new ArrayList();
					ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
					xlImg1.col1= 1;
					xlImg1.row1= rowNo2;
					CommonMessage.debugMsg(" imagePath===:: "+imagePath);
					CommonMessage.debugMsg(" rowNo2===:: "+rowNo2+" i == "+i);
					xlImg1.imageFileName=imagePath.replace("tmp/images/","images/visualplus.jpg");
					imageType2.add(xlImg1);
					
					ExcelUtils.addImages(wb,sheet,imageType2);
					
					CommonMessage.debugMsg(" File Path Checking :: "+xlImg.imageFileName);
					
					rowNo = rowNo + 5;
				}	
				
				if (i==1 || i == 7|| i == 13|| i == 19|| i == 25) { 
					if(i == 13)
				       rowNo = rowNo+1;
					else
						rowNo = rowNo+2;
					
					sheet.getRow(rowNo).getCell(12).setCellValue(impshtexl[7]);//product/eqm
					sheet.getRow(rowNo).getCell(15).setCellValue(impshtexl[8]);//opration
					sheet.getRow(rowNo).getCell(6).setCellValue(impshtexl[11]);//jh
					sheet.getRow(rowNo).getCell(9).setCellValue(impshtexl[12]);//dmt
					sheet.getRow(rowNo).getCell(13).setCellValue(impshtexl[13]);//MACHINE
					sheet.getRow(rowNo).getCell(21).setCellValue(impshtexl[14]);//Modified on date
					CommonMessage.debugMsg(" Checking Value of rowNo "+i+"=="+rowNo);
					if ( i == 1||i == 7||i == 13|| i == 19|| i == 25) {
						pageNo = pageNo + 1;
						sheet.getRow(rowNo+1).getCell(18).setCellValue(" Pages: 0"+pageNo+" of 0"+k);//Pages no
					}
					    rowNo = rowNo + 3;
					
				}
				
				if(i>6 && i<31)
				    rowNo = rowNo+(i-(i-1));
				else
				    rowNo = rowNo+i;
				
				
				sheet.getRow(rowNo).getCell(3).setCellValue(impshtexl[0]);//instruction 
				sheet.getRow(rowNo).getCell(12).setCellValue(impshtexl[1]); //keypoint
				sheet.getRow(rowNo).getCell(17).setCellValue(impshtexl[2]);//importance of keypoints
				sheet.getRow(rowNo).getCell(20).setCellValue(impshtexl[3]); //toolsused
				
				//ExcelUtils.setRowHeight(wb, sheet, rowNo ,3, impshtexl[0], 40);
				
				//sheet.getRow(20).getCell(14).setCellValue(impshtexl[6]);//issued by
				//sheet.getRow(20).getCell(13).setCellValue(impshtexl[5]);//appreoved by 
				//sheet.getRow(20).getCell(12).setCellValue(impshtexl[4]);//prepared by
				
				if ( i==1 || i == 7|| i == 13 || i == 19|| i == 25)  {
					if(i == 13)
					  rowNo = rowNo+14;
					else
				       rowNo = rowNo+14;
    
				    sheet.getRow(rowNo).getCell(2).setCellValue(impshtexl[9]);//safty instruction
				    sheet.getRow(rowNo).getCell(8).setCellValue(impshtexl[10]);//effect on compliance
				    
				    if(i == 13)
						rowNo = rowNo-14;
					else
				        rowNo = rowNo-14;
				}
				
				//sheet.getRow(7).getCell(5).setCellValue(impshtexl[4]);
				excelFormat = impshtexl[0];
				
				if(i>=1  && i<6)
					 rowNo=5;
				else if(i==6){
					rowNo=19;
				}else if(i==12){
					rowNo=44;
				}else if(i==18){
					rowNo=67;
				}else if(i==24){
					rowNo=91;
				}
			}
			for (int n = k*24; n <= 118; n++) {
			    
			    sheet.removeRow(sheet.getRow(n) );
				if (n==23||n==46||n==70 ||n==94) {
					n=n+1;
				}					
			}			
		}		
		CommonMessage.debugMsg(" Number of Sheets " +k);

		/*for (int n = 0; n <k; n++) {
			CommonMessage.debugMsg(" Current Sheet " +n);
			CommonMessage.debugMsg(" Current Row " +(n+1)*23);
			sheet.setRowBreak((n+1)*23);
		}		
		*/
		int m =k*24-2;
	    wb.setPrintArea(
	            0, //sheet index
	            0, //start column
	            23, //end column
	            0, //start row
	            m  //end row
	    );
		
		List<ExcelInsertImage> imageType = new ArrayList();
		List<ExcelInsertImage> ToolimageType = new ArrayList();
		
		List<GenTlAllmoduleimgfile> vsopImgList  = vsopService.getVsopImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,vsopId);
		List<String[]> vsopApprovellist  = vsopService.getVsopApprovalList(vsopId);
		
		//CommonMessage.debugMsg(" Checking here for size :: "+vsopApprovellist.size());
		//CommonMessage.debugMsg(" Inside 1234 :: Before :: 11 "+vsopApprovellist.get(1)[0]);
		//CommonMessage.debugMsg(" Inside 5678 :: Before :: 22 "+vsopApprovellist.get(0)[0]);
		int rno = 20;
		for(int s=1;s<=k;s++) {
			for(int a=0;a< vsopApprovellist.size(); a++)
			{
				CommonMessage.debugMsg(" 1rno == "+rno);
				if( a == 1)
				{
				  sheet.getRow(rno).getCell(17).setCellValue(vsopApprovellist.get(1)[0]);//appreoved by
				  sheet.getRow(rno+2).getCell(17).setCellValue(vsopApprovellist.get(1)[1]);//appreoved date
				}
				if(a == 0)
				{
					if(vsopApprovellist.size()>0 && vsopApprovellist.size()< 2){
					  sheet.getRow(rno).getCell(15).setCellValue(vsopApprovellist.get(0)[0]);//appreoved by 
				      sheet.getRow(rno+2).getCell(15).setCellValue(vsopApprovellist.get(0)[1]);//appreoved date
					}else if(vsopApprovellist.size()>1){
					  sheet.getRow(rno).getCell(15).setCellValue(vsopApprovellist.get(0)[0]);//appreoved by 
				      sheet.getRow(rno+2).getCell(15).setCellValue(vsopApprovellist.get(0)[1]);//appreoved date
					}
				}
			}
			CommonMessage.debugMsg(" rno == "+rno);
			rno = rno + 24;
		}
		
		int Column=2;
		int row=14;
		
		for(int i=0;i<vsopImgList.size();i++)
		{  
			if (i == 6|| i == 12|| i == 17|| i == 24)
				row=row + 18;
			
			if(Column == 17)
			{
				Column = 2;				
				//row=17;
				row=row + 3;
				
			}
			if(Column == 3)
			{
				Column = 9;			
			}
			if(Column == 10)
			{
				Column = 16;				
			}
			
			if (vsopImgList.get(i).getImflFilename() != null)
			{
				CommonMessage.debugMsg(" Inside Imgsize :: "+(vsopImgList.size()-1));
				if(i==vsopImgList.size()-1)
				{  
					CommonMessage.debugMsg(" Inside Imgsize :: if "+Column+" "+row);
					ExcelInsertImage  xlImg = new ExcelInsertImage();			
					xlImg.col1=Column;
					xlImg.row1 = row;
					xlImg.imageFileName=vsopImgList.get(i).getImflFilename();			
					imageType.add(xlImg);	
				}
				else
				{   
					CommonMessage.debugMsg(" Inside Imgsize :: else "+Column+" "+row);
					ExcelInsertImage  xlImg1 = new ExcelInsertImage();							
					xlImg1.col1=Column;
					xlImg1.row1 = row;
					xlImg1.imageFileName=vsopImgList.get(i).getImflFilename();				
					imageType.add(xlImg1);
				}
//				String DetailId = vsopImgList.get(i).getImflTempfield1();
//				
//				List<GenTlToolsimg> vsopToolList  = vsopService.getVsopToolImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,DetailId);
//				
//				
//				int ppecolumn = 3 ;
//				CommonMessage.debugMsg("Column" + Column );
//				
//				if(Column == 2)
//				{
//					ppecolumn = 3; 
//				}
//				else if(Column == 9)
//				{
//					ppecolumn = 10; 
//				}else if(Column == 16)
//				{
//					ppecolumn = 17; 
//				}
//				
//				for(int t=0 ; t < vsopToolList.size();t++)
//				{   
//					CommonMessage.debugMsg("ppecolumn" + ppecolumn  + "row" + row);
//					
//					//if (t == 6|| t == 12|| t == 17|| t == 24)
//						//row=row + 18;
//					
//					if (vsopToolList.get(t).getToimFilename() != null)
//					{
//						/*if(t==vsopToolList.size()-1)
//						{ 
//							ExcelInsertImage  xlTlImg = new ExcelInsertImage();			
//							xlTlImg.col1=ppecolumn;
//							xlTlImg.row1 = row-1;
//							xlTlImg.imageFileName=vsopToolList.get(t).getToimFilename();			
//							ToolimageType.add(xlTlImg);
//						}
//						else
//						{   
//							ExcelInsertImage  xlTlImg1 = new ExcelInsertImage();							
//							xlTlImg1.col1=ppecolumn;
//							xlTlImg1.row1 = row-1;
//							xlTlImg1.imageFileName=vsopToolList.get(t).getToimFilename();				
//							ToolimageType.add(xlTlImg1);
//						} */
//						   
//						ExcelInsertImage  xlTlImg1 = new ExcelInsertImage();							
//						xlTlImg1.col1=ppecolumn;
//						xlTlImg1.row1 = row-1;
//						xlTlImg1.imageFileName=vsopToolList.get(t).getToimFilename();				
//						ToolimageType.add(xlTlImg1);
//
//					}
//					ppecolumn = ppecolumn + 1 ;
//						
//				}	
				
				
			}
			Column = Column + 1;
	      }
		
		
		ExcelUtils.addImages(wb,sheet,imageType);
		ExcelUtils.addImages(wb,sheet,ToolimageType);
		sheet.protectSheet("admin");
		return wb;

	}
}
