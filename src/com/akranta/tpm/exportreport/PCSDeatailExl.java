 
package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.service.PcsRptService;
import com.akranta.tpm.service.impl.PcsRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class PCSDeatailExl {

	PcsRptService pcsRptService;
    public PCSDeatailExl(DBActionTemplate dbActionTemplate) {
		pcsRptService = new PcsRptServiceImpl(dbActionTemplate);		
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
	
	

	
	
	public Workbook fillValues(Map<Integer,List<String[]>> pcsData,String rowId, String date,String sectId, String prlmid,String shiftId, String format, String path,String imagePath,String shift) throws Exception
	{
		CommonMessage.debugMsg("excelPath.. ::: "+shift );
		CommonMessage.debugMsg("11111111111111");
		String excelFormat = "L";
		String excelPath = null;
		excelPath = "/ProductionLossReport_Hour1.xlsx";		
		//else if(excelFormat.equals("B"))
			//excelPath = "/KaizenBar.xlsx";
		
		
		
		//CommonMessage.debugMsg("excelPath.."+excelPath);
		InputStream inp = new FileInputStream(path+excelPath); 
				
		//InputStream inp = new FileInputStream(path+"/Kaizen.xls");    			    
	    //HSSFWorkbook wb = new HSSFWorkbook(inp);
		Workbook wb = new XSSFWorkbook(inp);
	    inp.close();

		Sheet sheet = wb.getSheetAt(0);  
		List<String[]> cur1 = pcsData.get(0);
		 
		String [] exl1 = cur1.get(1);	
		
		List<String[]> cur2 = pcsData.get(1);
		 
		String [] exl2 = cur2.get(1);	
		
		
		List<String[]> cur3 = pcsData.get(2);
		CommonMessage.debugMsg("1111111111111 133  " + cur3.size());
		String [] exl3 = cur3.get(1);	
	/*	for(int i=0; i<exl3.length;i++)
		{
			 
			CommonMessage.debugMsg("impshtexl3333:"+exl3[i]);
			 
		}*/
		
		List<String[]> cur4 = pcsData.get(3);
		CommonMessage.debugMsg("1111111111111 144  " + cur4.size());
		//String [] exl4 = cur4.get(1);	
	/*	if(cur4!=null && cur4.size() > 0 )
		{
		   int i=7;
			for(int a=1;a<cur4.size();a++)
			{
				String [] exl4 = cur4.get(a);				 
					for(int p=0;p<=9;p++)
					{
						int j=5;
						while(p<exl4.length)
						{	
							sheet.getRow(i).getCell(j).setCellValue(exl4[p]);
							j=j+1;
							p++;
						}
						i=i+1;
					}	
			}
		}*/
	/*	for(int i=0; i<exl4.length;i++)
		{
			 
			CommonMessage.debugMsg("impshtexl3444:"+exl4[i]);
			 
		}*/
		List<String[]> cur5 = pcsData.get(4);
		CommonMessage.debugMsg("1111111111111 155  " + cur5.size());
	 
	//	String [] exl5 = cur5.get(1);	
		if(cur5!=null && cur5.size() > 0 )
		{
		   int i=20;
			for(int a=1;a<cur5.size();a++)
			{
				String [] exl5 = cur5.get(a);				 
					for(int k=0;k<=7;k++)
					{
						int j=0;
						while(k<exl5.length)
						{	
							sheet.getRow(i).getCell(j).setCellValue(exl5[k]);
							j=j+1;
							k++;
						}
						i=i+1;
					}	
			}
		}
	/*	for(int i=0; i<exl5.length;i++)
		{
			 
			CommonMessage.debugMsg("impshtexl3555:"+exl5[i]);
			 
		}*/
		List<String[]> cur6 = pcsData.get(5);
		CommonMessage.debugMsg("1111111111111 166  " + cur6.size());
		String [] exl6 = cur6.get(1);	
	/*	for(int i=0; i<exl6.length;i++)
		{
			 
			CommonMessage.debugMsg("impshtexl266:"+exl6[i]);
			 
		}*/
		List<String[]> cur7 = pcsData.get(6);
		CommonMessage.debugMsg("1111111111111 155 777 " + cur7.size());
	 
		//String [] exl7 = cur7.get(1);	
		if(cur7!=null && cur7.size() > 0 )
		{
		  // int i=17;
			//for(int a=1;a<cur7.size();a++)
			//{
			    int j=10;
				String [] exl7 = cur7.get(1);	
				
				 CommonMessage.debugMsg( " ::: "+exl7.length);
				 int i=17;
				 
				for(int k=0;k<exl7.length;k++)
				{	
					 CommonMessage.debugMsg("dfdfgsxgxfg");
					sheet.getRow(i+k).getCell(j).setCellValue(exl7[k]);
					//j=j+1;
					//i++;
				//}
			}
		}
	 	//int k = 10;
		
	 /*	for(String[] exclRow : cur7)
		{
			//int j=3; 
			CommonMessage.debugMsg( " : "+exclRow.length);
			 for(int i=17;i<exclRow.length;i++)
			 { 
				  CommonMessage.debugMsg(i + " : "+exclRow[i]);
				  sheet.getRow(i).getCell(k).setCellValue(exclRow[i]);
    			 
    			// j++;
			 }
		}*/
			// k++; 
	/*	for(int i=0; i<exl7.length;i++)
		{
			 
			CommonMessage.debugMsg("impshtexl355577:"+exl7[i]);
			 
		}*/
		CommonMessage.debugMsg("1111111111111 166 888 " );
		List<String[]> cur8 = pcsData.get(7);
		
		//String [] exl8 = cur8.get(0);	
	/*	if(cur8!=null && cur8.size() > 0 )
		{
		   int i=7;
			for(int a=1;a<cur8.size();a++)
			{
				String [] exl8 = cur8.get(a);				 
					for(int l=0;l<=9;l++)
					{
						int j=5;
						while(l<exl8.length)
						{	
							sheet.getRow(i).getCell(j).setCellValue(exl8[l]);
							j=j+1;
							l++;
						}
						i=i+1;
					}	
			}
		}*/
	/*	for(int i=0; i<exl8.length;i++)
		{
			 
			CommonMessage.debugMsg("impshtexl26688:"+exl8[i]);
			 
		}*/
		
	
		
	/*	if( graphType!=null && graphType.size() > 0 )
		{
			String [] impshtexl = graphType.get(0);		   		    		
		    excelFormat=impshtexl[0];	
		    
		}*/
		//CommonMessage.debugMsg("format.."+excelFormat);
		
	//	if(excelFormat.equals("L"))
			
	
	
	
	 
		 //   String [] impshtexl = curExcel.get(1);
		    
		    sheet.getRow(1).getCell(1).setCellValue(exl1[1]);
		    sheet.getRow(2).getCell(1).setCellValue(exl1[2]);
		    sheet.getRow(2).getCell(6).setCellValue(exl1[3]);
		    sheet.getRow(1).getCell(6).setCellValue(exl1[4]);
		    sheet.getRow(3).getCell(6).setCellValue(exl1[5]);
		    sheet.getRow(4).getCell(1).setCellValue(date);
		    sheet.getRow(4).getCell(6).setCellValue(shift);
		    sheet.getRow(29).getCell(5).setCellValue(exl1[6]);
		    sheet.getRow(29).getCell(6).setCellValue(exl1[6]);
		    sheet.getRow(29).getCell(7).setCellValue(exl1[6]);
		    
		  /*  sheet.getRow(17).getCell(7).setCellValue(exl1[6]);
		    sheet.getRow(18).getCell(7).setCellValue(exl1[6]);
		    sheet.getRow(19).getCell(7).setCellValue(exl1[6]);
		    sheet.getRow(20).getCell(7).setCellValue(exl1[6]);
		    /*
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
		    sheet.getRow(ROW_INDEX_TGT).getCell(1).setCellValue(impshtexl[21]);//Present Problem
		    sheet.getRow(ROW_INDEX_COST).getCell(14).setCellValue(impshtexl[25]);//Meterial Cost
		    sheet.getRow(ROW_INDEX_COST).getCell(16).setCellValue(impshtexl[26]);//Labour Cost
		    sheet.getRow(ROW_INDEX_COST).getCell(17).setCellValue(impshtexl[50]);//Total Cost
		    sheet.getRow(23).getCell(16).setCellValue(impshtexl[27]);//How to Do
		    sheet.getRow(ROW_INDEX_YY).getCell(16).setCellValue(impshtexl[47]);//What to Do
		    sheet.getRow(25).getCell(16).setCellValue(impshtexl[48]);//Freq
		    sheet.getRow(1).getCell(3).setCellValue(impshtexl[51]);//Circle Code
  			sheet.getRow(ROW_INDEX_EQP).getCell(17).setCellValue(impshtexl[49]);//operation
  			sheet.getRow(ROW_INDEX_RSTIMG).getCell(5).setCellValue(impshtexl[42]);//Result Image
  			sheet.getRow(ROW_INDEX_RSTIMG).getCell(5).setCellValue(impshtexl[42]);//Result Image
  			sheet.getRow(38).getCell(1).setCellValue(impshtexl[18]);//root Cause  			
  			*/
		
		//CommonMessage.debugMsg("test13");
	 	
		
		return wb;			 
	}	
}
