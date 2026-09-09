package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
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

public class ImprovementProjectSheetTemplate {

	KaizenServices kaizenServices ;
    public ImprovementProjectSheetTemplate(DBActionTemplate dbActionTemplate){
		kaizenServices = new KaizenServiceImpl(dbActionTemplate);
    }
	
	private static final short ROW_INDEX_CIRCLE = 2;
	private static final short ROW_INDEX_COST = 29;
	private static int ROW_INDEX_DEPTIMPNO = 4;
	private static final short ROW_INDEX_EQP = 5;
	private static final short ROW_INDEX_THEMEIDEA = 6;
	private static final short ROW_INDEX_TGT = 9;
	private static final short ROW_INDEX_PRGSRT = 11;
	private static final short ROW_INDEX_YY = 21;
	private static final short ROW_INDEX_RSTIMG = 31;
	private static final short ROW_INDEX_IMG = 16;
	private static final short INCERMENTBY_YY = 2;
	private static final short WHYWHY_START_POINT = 21;
	private static final short GRAPH_START_COL = 31;
	
	

	
	
	public Workbook fillValues(Map<Integer,List<String[]>> kaizenData,String format,String path,String kaizId,String imagePath) throws Exception
	{
	
		
		String excelFormat = "L";
		String excelPath = null;
		List<String[]> graphType = kaizenData.get(5);	
		if( graphType!=null && graphType.size() > 0 )
		{
			String [] impshtexl = graphType.get(1);		   		    		
		    excelFormat=impshtexl[0];	
		}
		//CommonMessage.debugMsg("format.."+excelFormat);
		
		if(excelFormat.equals("L"))
			excelPath = "/Kaizen.xlsx";		
		else if(excelFormat.equals("B"))
			excelPath = "/KaizenBar.xlsx";
		
		
		
		//CommonMessage.debugMsg("excelPath.."+excelPath);
		InputStream inp = new FileInputStream(path+excelPath); 
				
		//InputStream inp = new FileInputStream(path+"/Kaizen.xls");    			    
	    //HSSFWorkbook wb = new HSSFWorkbook(inp);
		Workbook wb = new XSSFWorkbook(inp);
	    inp.close();

		Sheet sheet = wb.getSheetAt(0);  
	
		List<GenTlAllmoduleimgfile> kaizenImage =  kaizenServices.getKaizenImage(UIUtils.TPM_TEMPIMG_DIR,imagePath,kaizId);
	CommonMessage.debugMsg("Size...."+kaizenImage.size());
		if( kaizenImage != null && kaizenImage.size()>1 ){
		
			List<ExcelInsertImage> imageType = new ArrayList<ExcelInsertImage>();
			for(int i=0;i<kaizenImage.size();i++)
			{
				GenTlAllmoduleimgfile img = kaizenImage.get(i);
				String fileName=imagePath+ (img.getImflFilename()).replace(UIUtils.TPM_TEMPIMG_DIR, "");
			
				
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
		CommonMessage.debugMsg("After img");
		List<String[]> curExcel = kaizenData.get(0);	
//		CommonMessage.debugMsg("curExcel...."+curExcel.size());
		if(curExcel!=null && curExcel.size() > 1 )
		{
		    String [] impshtexl = curExcel.get(1);
		    sheet.getRow(ROW_INDEX_CIRCLE).getCell(3).setCellValue("T");
		    CommonMessage.debugMsg("curExcel....1");
		    sheet.getRow(ROW_INDEX_CIRCLE).getCell(8).setCellValue("L");
		    sheet.getRow(ROW_INDEX_DEPTIMPNO).getCell(3).setCellValue(impshtexl[55]);//DEPARTMENT
		    sheet.getRow(ROW_INDEX_DEPTIMPNO).getCell(18).setCellValue(impshtexl[0]);//IMPLEMENT NUMBER
		    sheet.getRow(ROW_INDEX_EQP).getCell(11).setCellValue(impshtexl[6]);//EQUIPMENT--
		    sheet.getRow(ROW_INDEX_THEMEIDEA).getCell(6).setCellValue(impshtexl[19]);//IDEA
		    
		    
		    
		    sheet.getRow(8).getCell(16).setCellValue(impshtexl[12]);//BENCHMARK--
		    sheet.getRow(ROW_INDEX_TGT).getCell(16).setCellValue(impshtexl[13]);//TARGET--
		    sheet.getRow(10).getCell(16).setCellValue(impshtexl[14]);//PROJECT START--
		    sheet.getRow(ROW_INDEX_PRGSRT).getCell(16).setCellValue(impshtexl[15]);//PROJECT FINISH--
		    sheet.getRow(12).getCell(16).setCellValue(impshtexl[9]);//TEAM MEMBER
		    sheet.getRow(ROW_INDEX_IMG).getCell(16).setCellValue(impshtexl[22]);//BENIFITS
		    sheet.getRow(ROW_INDEX_DEPTIMPNO).getCell(17).setCellValue(impshtexl[0]);//improvement No
		    sheet.getRow(ROW_INDEX_EQP).getCell(2).setCellValue(impshtexl[4]);//Cell
		    sheet.getRow(ROW_INDEX_EQP).getCell(5).setCellValue(impshtexl[4]);//Cell
		    sheet.getRow(ROW_INDEX_DEPTIMPNO).getCell(3).setCellValue(impshtexl[5]);//Section
		    sheet.getRow(ROW_INDEX_THEMEIDEA).getCell(2).setCellValue(impshtexl[10]);//Theame
		    sheet.getRow(ROW_INDEX_TGT).getCell(5).setCellValue(impshtexl[20]);//CounterMeasure
		    sheet.getRow(ROW_INDEX_YY).getCell(5).setCellValue(impshtexl[21]);//Result Desc
		    sheet.getRow(20).getCell(5).setCellValue(impshtexl[21]);//Result Desc
		    
		    CommonMessage.debugMsg("curExcel....2");
		    
		    sheet.getRow(ROW_INDEX_TGT).getCell(1).setCellValue(impshtexl[21]);//Present Problem
		    sheet.getRow(ROW_INDEX_COST).getCell(14).setCellValue(impshtexl[25]);//Meterial Cost
		    sheet.getRow(ROW_INDEX_COST).getCell(16).setCellValue(impshtexl[26]);//Labour Cost
		    sheet.getRow(ROW_INDEX_COST).getCell(17).setCellValue(impshtexl[50]);//Total Cost
		    sheet.getRow(23).getCell(16).setCellValue(impshtexl[27]);//How to Do
		    sheet.getRow(ROW_INDEX_YY).getCell(16).setCellValue(impshtexl[47]);//What to Do
		    sheet.getRow(25).getCell(16).setCellValue(impshtexl[48]);//Freq
		    sheet.getRow(1).getCell(3).setCellValue(impshtexl[51]);//Circle Code
		    
		    CommonMessage.debugMsg("curExcel....3");
		    
  			sheet.getRow(ROW_INDEX_EQP).getCell(17).setCellValue(impshtexl[49]);//operation
  			sheet.getRow(ROW_INDEX_RSTIMG).getCell(5).setCellValue(impshtexl[42]);//Result Image
  			sheet.getRow(ROW_INDEX_RSTIMG).getCell(5).setCellValue(impshtexl[42]);//Result Image
  			sheet.getRow(38).getCell(1).setCellValue(impshtexl[18]);//root Cause  			
  			CommonMessage.debugMsg("curExcel....4");
		}
		
		List<String[]> curLoss = kaizenData.get(1);	
		CommonMessage.debugMsg("curLoss....");
		if( curLoss!=null && curLoss.size() > 0 )
		{
		   
		    sheet.getRow(2).getCell(3).setCellValue("T");
		    sheet.getRow(2).getCell(8).setCellValue("L");	  			
		}
		
		
		
		List<String[]> curHd = kaizenData.get(3);	
		Row row = null;
		
	
		CommonMessage.debugMsg("curHd...."+curHd.size());
		if(curHd!=null && curHd.size() > 1 )
		{
			   int i=33;
			  
			   int rowIndex  = i;
					for(int a=1;a<=curHd.size()-1;a++)
					{
						//row = sheet.createRow(rowIndex++);	
						String [] impshtexl = curHd.get(a);				 
						for(int k=0;k<impshtexl.length;k++)
						{
							int j=14;
							
							while(k<impshtexl.length)
							{	
								if(a<10){
									CommonMessage.debugMsg("123");
									sheet.getRow(i).getCell(j).setCellValue(impshtexl[k]);
									j=j+1;
									k++;
								}
								else{		
									CommonMessage.debugMsg("rowIndex...."+impshtexl[k]);
									CommonMessage.debugMsg("j...."+j);
									 row = sheet.createRow(rowIndex);
									row.createCell(j).setCellValue(impshtexl[k]); 	
									j=j+1;
									k++;
								}								
							}
							i=i+1;	
							
							rowIndex++;
						}		
						
					}
			  
				/*if(curHd.size()>9){
					for(int a=10;a>curHd.size()-1;a++){
						
						
						String [] impshtexl = curHd.get(a);				 
						for(int k=0;k<impshtexl.length;k++)
						{
							int j=14;
							while(k<impshtexl.length)
							{	
								row.createCell(j).setCellValue(impshtexl[k]); 
								//sheet.getRow(i).getCell(j).setCellValue(impshtexl[k]);
								CommonMessage.debugMsg("i-"+i+"j-"+j+"k-"+k+"impshtexl[k]-"+impshtexl[k]);
								j=j+1;
								k++;
							}
							i=i+1;				
						}	
						
						CommonMessage.debugMsg("create row");
					}
				}*/
				
			
		}
		CommonMessage.debugMsg("After curHd....");
		List<String[]> curYY = kaizenData.get(4);	
		CommonMessage.debugMsg("after7  "+curYY.size());
		if(curYY!=null && curYY.size() > 1 )
		{	
			
				
			  for(int a=0; a< (curYY.size() < 4?curYY.size():4);a++)
			  {				  
				String [] impshtexl = curYY.get(a);		
				sheet.getRow(WHYWHY_START_POINT+2*(INCERMENTBY_YY*(a-1))).getCell(2).setCellValue(impshtexl[0]);
				sheet.getRow(WHYWHY_START_POINT+2*(INCERMENTBY_YY*(a-1)+INCERMENTBY_YY-1)).getCell(2).setCellValue(impshtexl[1]);
			  }		
		  
		}	
		if(graphType!=null && graphType.size() > 0 )
		{	
		    Row  graphRow = null;
		    String [] impshtexl = null;
			for(int i=0;i<graphType.size();i++)
			{	
				 impshtexl  = graphType.get(i);	
			     graphRow =  sheet.getRow(ROW_INDEX_DEPTIMPNO+i);
				 for(int k=2;k<impshtexl.length;k++)
				 {
					 if( i ==0)
						 graphRow.getCell(GRAPH_START_COL+k).setCellValue(impshtexl[k]);
					 else
						 graphRow.getCell(GRAPH_START_COL+k).setCellValue(Double.parseDouble(impshtexl[k]));
				 }
			}					
		}	
		
		return wb;			 
	}	
}
