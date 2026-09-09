package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.InputStream;
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

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class NewDashboardExcelTemplate{
	DashboardService dashboardService;
    public NewDashboardExcelTemplate(DBActionTemplate dbActionTemplate) throws Exception{
    	dashboardService=new DashboardServiceImpl(dbActionTemplate);
    	
    }
    public Workbook fillValues(List<String[]> EHSMAtrixData,List<String[]> AETAdhrenceData,List<String[]> PACTAdhrenceData,List<String[]> MOMListData,
    		List<String[]> KaizenData,List<String[]> AbnormalityData,List<String[]> TransactionData,List<String[]> AbnormalityPendingData,List<String[]> AetPercentage,List<String[]> PActPercentage,List<String[]> funclcnData,String path,
    		String fromMonth,String toMonth,String FirstMonth,String FYearStart,String FYearEnd,String QuarterFirst1,
    		String QuarterEnd,String Location,String Dept,String DMTname,String JHkeyid,String FromDate,String ToDate,String Finance) throws Exception{	
		
    	String excelPath = null;
		excelPath = "/NewDashboard.xlsx";	
		InputStream inp = new FileInputStream(path + excelPath);
	    String CurrentYear=CommonFunctions.getCurrentYear();
	    Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
	    String PreYear=String.valueOf(PreviousYear);
	    String CurYear=PreYear.substring(2,4);
	    String next=CurrentYear.substring(0,1);
		String NextYear=(next)+1;        
		Workbook wb = new XSSFWorkbook(inp);
		inp.close();
		Sheet sheet = wb.getSheetAt(0);
		
		Sheet sheet1 = wb.getSheetAt(1);
		Sheet sheet2 = wb.getSheetAt(2);
		Sheet sheet3=wb.getSheetAt(3);
		Sheet sheet4=wb.getSheetAt(4);
		Sheet sheet5=wb.getSheetAt(5);
		Sheet sheet6=wb.getSheetAt(6);
		Sheet sheet7=wb.getSheetAt(7);
		Sheet sheet8=wb.getSheetAt(8);
		Sheet sheet9=wb.getSheetAt(9);
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
		
            //************************EHS Metrics Related************************//    
    	
    	    int EHSRow =5;
 		    int g;
            if(EHSMAtrixData !=null && EHSMAtrixData.size()>0){ 	
        	 for(g=0;g<EHSMAtrixData.size();g++){
        		 
        		    sheet.getRow(EHSRow+g).getCell(1).setCellValue(EHSMAtrixData.get(g)[0]);//DMT				   		    
        		    sheet.getRow(EHSRow+g).getCell(2).setCellValue(EHSMAtrixData.get(g)[1]);//DMT 				    
 		    	    sheet.getRow(EHSRow+g).getCell(5).setCellValue(EHSMAtrixData.get(g)[2]);//APR
 		    	    sheet.getRow(EHSRow+g).getCell(6).setCellValue(EHSMAtrixData.get(g)[3]);//MAY
 		    	    sheet.getRow(EHSRow+g).getCell(7).setCellValue(EHSMAtrixData.get(g)[4]);//JUN
 		    	    sheet.getRow(EHSRow+g).getCell(9).setCellValue(EHSMAtrixData.get(g)[5]);//JUL
 		    	    sheet.getRow(EHSRow+g).getCell(10).setCellValue(EHSMAtrixData.get(g)[6]);//AUG 	         	    
 	         	    sheet.getRow(EHSRow+g).getCell(11).setCellValue(EHSMAtrixData.get(g)[7]);//SEP 	         	    
 	         	    sheet.getRow(EHSRow+g).getCell(12).setCellValue(EHSMAtrixData.get(g)[8]);//OCT 	         	    
 	         	    sheet.getRow(EHSRow+g).getCell(13).setCellValue(EHSMAtrixData.get(g)[9]);//NOV 	         	    
 	         	    sheet.getRow(EHSRow+g).getCell(14).setCellValue(EHSMAtrixData.get(g)[10]);//DEC 	         	    
 	         	    sheet.getRow(EHSRow+g).getCell(15).setCellValue(EHSMAtrixData.get(g)[11]);//JAN
 	         	    sheet.getRow(EHSRow+g).getCell(16).setCellValue(EHSMAtrixData.get(g)[12]);//FEB 	         	    
 	         	    sheet.getRow(EHSRow+g).getCell(17).setCellValue(EHSMAtrixData.get(g)[13]);//MAR
        	          }		    	    
     	     }
         
          //************************AET Adherence Related************************//    
            
           int AetRow =4;
   		   int g1;
           if(AETAdhrenceData !=null && AETAdhrenceData.size()>0){
          	
          	 for(g1=0;g1<AETAdhrenceData.size();g1++){
          		    sheet1.getRow(AetRow+g1).getCell(1).setCellValue(g1+1);//sno
          		    sheet1.getRow(AetRow+g1).getCell(2).setCellValue(AETAdhrenceData.get(g1)[0]);//EMPLOYEE   		    	    
   				    sheet1.getRow(AetRow+g1).getCell(6).setCellValue(AETAdhrenceData.get(g1)[1]);//CODE   		    	    
   	         	    sheet1.getRow(AetRow+g1).getCell(7).setCellValue(AETAdhrenceData.get(g1)[2]);//MEETINGS
   		    	    sheet1.getRow(AetRow+g1).getCell(8).setCellValue(AETAdhrenceData.get(g1)[3]);//PRESENT
   		    	    sheet1.getRow(AetRow+g1).getCell(10).setCellValue(AETAdhrenceData.get(g1)[4]);//ABSENT
   		    	    sheet1.getRow(AetRow+g1).getCell(11).setCellValue(AETAdhrenceData.get(g1)[5]);//LEAVE   	         	    
   	         	    sheet1.getRow(AetRow+g1).getCell(12).setCellValue(AETAdhrenceData.get(g1)[6]);//WEEKOFF	         	        
	         	    sheet1.getRow(AetRow+g1).getCell(13).setCellValue(AETAdhrenceData.get(g1)[7]);//ON DUTY	         	    
	         	    sheet1.getRow(AetRow+g1).getCell(14).setCellValue(AETAdhrenceData.get(g1)[8]);//PERCENTAGE
          	       }		    	    
   	        }
           
         //************************PACT Adherence Related************************//    
           
           int PactRow =4;
   		   int g2;
           if(PACTAdhrenceData !=null && PACTAdhrenceData.size()>0){
          	 for(g2=0;g2<PACTAdhrenceData.size();g2++){
          		    sheet2.getRow(PactRow+g2).getCell(1).setCellValue(g2+1);//sno  
          		    sheet2.getRow(PactRow+g2).getCell(2).setCellValue(PACTAdhrenceData.get(g2)[0]);//EMPLOYEE   		    	    
   				    sheet2.getRow(PactRow+g2).getCell(6).setCellValue(PACTAdhrenceData.get(g2)[1]);//CODE   		    	    
   	         	    sheet2.getRow(PactRow+g2).getCell(7).setCellValue(PACTAdhrenceData.get(g2)[2]);//MEETINGS
   		    	    sheet2.getRow(PactRow+g2).getCell(8).setCellValue(PACTAdhrenceData.get(g2)[3]);//PRESENT
   		    	    sheet2.getRow(PactRow+g2).getCell(10).setCellValue(PACTAdhrenceData.get(g2)[4]);//ABSENT
   		    	    sheet2.getRow(PactRow+g2).getCell(11).setCellValue(PACTAdhrenceData.get(g2)[5]);//LEAVE   	         	    
   	         	    sheet2.getRow(PactRow+g2).getCell(12).setCellValue(PACTAdhrenceData.get(g2)[6]);//WEEKOFF	         	        
	         	    sheet2.getRow(PactRow+g2).getCell(13).setCellValue(PACTAdhrenceData.get(g2)[7]);//ON DUTY	         	    
	         	    sheet2.getRow(PactRow+g2).getCell(14).setCellValue(PACTAdhrenceData.get(g2)[8]);//PERCENTAGE
          	     }		    	    
   	        }
           
           //************************MOM Review Related************************// 
           
           int strRow =4;
		   int g3;
           if(MOMListData !=null && MOMListData.size()>0){  	
       	     for(g3=0;g3<MOMListData.size();g3++){
       		        sheet3.getRow(strRow+g3).getCell(1).setCellValue(g3+1);//sno	    
       		        sheet3.getRow(strRow+g3).getCell(2).setCellValue(MOMListData.get(g3)[0]);//FLOCATION		    	    
				    sheet3.getRow(strRow+g3).getCell(5).setCellValue(MOMListData.get(g3)[1]);//MEETINGNO	         	    
		    	    sheet3.getRow(strRow+g3).getCell(9).setCellValue(MOMListData.get(g3)[2]);//MEETINGDATE
		    	    sheet3.getRow(strRow+g3).getCell(12).setCellValue(MOMListData.get(g3)[3]);//DISCUSSION                    
	         	    sheet3.getRow(strRow+g3).getCell(14).setCellValue(MOMListData.get(g3)[4]);//RESPONSIBILITY	         	    
	         	    sheet3.getRow(strRow+g3).getCell(15).setCellValue(MOMListData.get(g3)[5]);//TARGET	         	    
		    	    sheet3.getRow(strRow+g3).getCell(16).setCellValue(MOMListData.get(g3)[6]);//ACTIONPLAN
		    	    sheet3.getRow(strRow+g3).getCell(17).setCellValue(MOMListData.get(g3)[7]);//STATUS
       	         }		    	    
	      }
           
           //************************Kaizen Satus Related************************// 

              int KaizenRow =4;
      		  int g4;
              if(KaizenData !=null && KaizenData.size()>0){
             	 for(g4=0;g4<KaizenData.size();g4++){
             		    sheet4.getRow(KaizenRow+g4).getCell(1).setCellValue(g4+1);//sno  
      				    sheet4.getRow(KaizenRow+g4).getCell(2).setCellValue(KaizenData.get(g4)[0]);//DMT      				    
      				    sheet4.getRow(KaizenRow+g4).getCell(6).setCellValue(KaizenData.get(g4)[1]);//TYPE      				    
      		    	    sheet4.getRow(KaizenRow+g4).getCell(7).setCellValue(KaizenData.get(g4)[2]);//APR      	         	    
      		    	    sheet4.getRow(KaizenRow+g4).getCell(8).setCellValue(KaizenData.get(g4)[3]);//MAY
      		    	    sheet4.getRow(KaizenRow+g4).getCell(9).setCellValue(KaizenData.get(g4)[4]);//JUN
      		    	    sheet4.getRow(KaizenRow+g4).getCell(11).setCellValue(KaizenData.get(g4)[5]);//JUL
      		    	    sheet4.getRow(KaizenRow+g4).getCell(12).setCellValue(KaizenData.get(g4)[6]);//AUG      	         	    
      	         	    sheet4.getRow(KaizenRow+g4).getCell(13).setCellValue(KaizenData.get(g4)[7]);//SEP      	         	    
      	         	    sheet4.getRow(KaizenRow+g4).getCell(14).setCellValue(KaizenData.get(g4)[8]);//OCT      	         	    
      	         	    sheet4.getRow(KaizenRow+g4).getCell(15).setCellValue(KaizenData.get(g4)[9]);//NOV      	         	    
      	         	    sheet4.getRow(KaizenRow+g4).getCell(16).setCellValue(KaizenData.get(g4)[10]);//DEC      	         	    
      	         	    sheet4.getRow(KaizenRow+g4).getCell(17).setCellValue(KaizenData.get(g4)[11]);//JAN
      	         	    sheet4.getRow(KaizenRow+g4).getCell(18).setCellValue(KaizenData.get(g4)[12]);//FEB      	         	    
      	         	    sheet4.getRow(KaizenRow+g4).getCell(19).setCellValue(KaizenData.get(g4)[13]);//MAR
             	    }		    	    
      	  }
              
              //************************Abnormality Financial Year Related************************// 
              
                int AbnormalityRow =4;
        		int g5;
                if(AbnormalityData !=null && AbnormalityData.size()>0){
               	 for(g5=0;g5<AbnormalityData.size();g5++){
               		        sheet5.getRow(AbnormalityRow+g5).getCell(1).setCellValue(g5+1);//sno
        				    sheet5.getRow(AbnormalityRow+g5).getCell(2).setCellValue(AbnormalityData.get(g5)[0]);//ABNORMALITYTYPE        				    
        		    	    sheet5.getRow(AbnormalityRow+g5).getCell(6).setCellValue(AbnormalityData.get(g5)[1]);//TILLIDENTIFIED        	         	    
        		    	    sheet5.getRow(AbnormalityRow+g5).getCell(7).setCellValue(AbnormalityData.get(g5)[2]);//TILLELIMINATED
        		    	    sheet5.getRow(AbnormalityRow+g5).getCell(8).setCellValue(AbnormalityData.get(g5)[3]);
        		    	    sheet5.getRow(AbnormalityRow+g5).getCell(10).setCellValue(AbnormalityData.get(g5)[4]);
        		    	    sheet5.getRow(AbnormalityRow+g5).getCell(11).setCellValue(AbnormalityData.get(g5)[5]);        	         	    
        	         	    sheet5.getRow(AbnormalityRow+g5).getCell(12).setCellValue(AbnormalityData.get(g5)[6]);       	         	       
       	         	        sheet5.getRow(AbnormalityRow+g5).getCell(13).setCellValue(AbnormalityData.get(g5)[7]);   	         	          
   	         	            sheet5.getRow(AbnormalityRow+g5).getCell(14).setCellValue(AbnormalityData.get(g5)[8]);        	         	 
  	         	            sheet5.getRow(AbnormalityRow+g5).getCell(15).setCellValue(AbnormalityData.get(g5)[9]);  	         	    
  	         	            sheet5.getRow(AbnormalityRow+g5).getCell(16).setCellValue(AbnormalityData.get(g5)[10]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(17).setCellValue(AbnormalityData.get(g5)[11]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(18).setCellValue(AbnormalityData.get(g5)[12]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(19).setCellValue(AbnormalityData.get(g5)[13]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(20).setCellValue(AbnormalityData.get(g5)[14]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(21).setCellValue(AbnormalityData.get(g5)[15]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(22).setCellValue(AbnormalityData.get(g5)[16]);	         	        
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(23).setCellValue(AbnormalityData.get(g5)[17]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(24).setCellValue(AbnormalityData.get(g5)[18]);      
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(25).setCellValue(AbnormalityData.get(g5)[19]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(26).setCellValue(AbnormalityData.get(g5)[20]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(27).setCellValue(AbnormalityData.get(g5)[21]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(28).setCellValue(AbnormalityData.get(g5)[22]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(29).setCellValue(AbnormalityData.get(g5)[23]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(30).setCellValue(AbnormalityData.get(g5)[24]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(31).setCellValue(AbnormalityData.get(g5)[25]);	         	         
	         	            sheet5.getRow(AbnormalityRow+g5).getCell(32).setCellValue(AbnormalityData.get(g5)[26]);
      
               	     }		    	    
        	   } 
                
                //************************Abnormality Pending Related************************// 
                
                int AbnPendingRow =4;
     		   int g6;
                if(AbnormalityPendingData !=null && AbnormalityPendingData.size()>0){
            	        for(g6=0;g6<AbnormalityPendingData.size();g6++){
            		        sheet6.getRow(AbnPendingRow+g6).getCell(1).setCellValue(g6+1);//sno
     				    sheet6.getRow(AbnPendingRow+g6).getCell(2).setCellValue(AbnormalityPendingData.get(g6)[0]);//ABNORMALITY TYPE
     		    	    sheet6.getRow(AbnPendingRow+g6).getCell(5).setCellValue(AbnormalityPendingData.get(g6)[1]);//PENDING                     
     	         	    sheet6.getRow(AbnPendingRow+g6).getCell(6).setCellValue(AbnormalityPendingData.get(g6)[2]);//PENDING
     		    	    sheet6.getRow(AbnPendingRow+g6).getCell(7).setCellValue(AbnormalityPendingData.get(g6)[3]);//PENDING
     		    	    sheet6.getRow(AbnPendingRow+g6).getCell(8).setCellValue(AbnormalityPendingData.get(g6)[4]);//PENDING     
                	 }		    	    
     	    }
                
                
                
       
                //************************LMP Tools Related************************// 
                
             int TrsRow =4;
		     int g7;
             if(TransactionData !=null && TransactionData.size()>0){
       	         for(g7=0;g7<TransactionData.size();g7++){
       		        sheet7.getRow(TrsRow+g7).getCell(1).setCellValue(g7+1);//sno
				    sheet7.getRow(TrsRow+g7).getCell(2).setCellValue(TransactionData.get(g7)[0]);//DMT
		    	    sheet7.getRow(TrsRow+g7).getCell(5).setCellValue(TransactionData.get(g7)[1]);//SUGGESTION
	         	    sheet7.getRow(TrsRow+g7).getCell(8).setCellValue(TransactionData.get(g7)[2]);//APPROVED SUGGESTION
		    	    sheet7.getRow(TrsRow+g7).getCell(9).setCellValue(TransactionData.get(g7)[3]);//KAIZEN
		    	    sheet7.getRow(TrsRow+g7).getCell(10).setCellValue(TransactionData.get(g7)[4]);//OPL
		    	    sheet7.getRow(TrsRow+g7).getCell(11).setCellValue(TransactionData.get(g7)[5]);//ABNORMALITY	         	    
	         	    sheet7.getRow(TrsRow+g7).getCell(12).setCellValue(TransactionData.get(g7)[6]);//MEETINGS	         	    
	         	    sheet7.getRow(TrsRow+g7).getCell(13).setCellValue(TransactionData.get(g7)[7]);//NEARMISS	         	    
	         	    sheet7.getRow(TrsRow+g7).getCell(14).setCellValue(TransactionData.get(g7)[8]);//WHY WHY	         	    
	         	    sheet7.getRow(TrsRow+g7).getCell(15).setCellValue(TransactionData.get(g7)[9]);//ACTION PLAN	         	   
	         	    sheet7.getRow(TrsRow+g7).getCell(16).setCellValue(TransactionData.get(g7)[10]);//FISHBONE	         	   
	         	    sheet7.getRow(TrsRow+g7).getCell(17).setCellValue(TransactionData.get(g7)[11]);//VISUALCONTROL
       	       }		    	    
	      }
        
         
           //************************AET % Related************************// 
 
           int AETPerRow =4;
 		   int g8;
           if(AetPercentage !=null && AetPercentage.size()>0){
        	   for(g8=0;g8<AetPercentage.size();g8++){
        		    sheet8.getRow(AETPerRow+g8).getCell(1).setCellValue(g8+1);//sno     
 				    sheet8.getRow(AETPerRow+g8).getCell(2).setCellValue(AetPercentage.get(g8)[0]);//EMPLOYEE
 		    	    sheet8.getRow(AETPerRow+g8).getCell(6).setCellValue(AetPercentage.get(g8)[1]);//CODE 	         	    
 		    	    sheet8.getRow(AETPerRow+g8).getCell(7).setCellValue(AetPercentage.get(g8)[2]);//APR
 		    	    sheet8.getRow(AETPerRow+g8).getCell(8).setCellValue(AetPercentage.get(g8)[3]);//MAY
 		    	    sheet8.getRow(AETPerRow+g8).getCell(10).setCellValue(AetPercentage.get(g8)[4]);//JUN
 		    	    sheet8.getRow(AETPerRow+g8).getCell(11).setCellValue(AetPercentage.get(g8)[5]);//JUL
 		    	    sheet8.getRow(AETPerRow+g8).getCell(12).setCellValue(AetPercentage.get(g8)[6]);//AUG 	         	    
 	         	    sheet8.getRow(AETPerRow+g8).getCell(13).setCellValue(AetPercentage.get(g8)[7]);//SEP 	         	    
 	         	    sheet8.getRow(AETPerRow+g8).getCell(14).setCellValue(AetPercentage.get(g8)[8]);//OCT 	         	    
 	         	    sheet8.getRow(AETPerRow+g8).getCell(15).setCellValue(AetPercentage.get(g8)[9]);//NOV 	         	    
 	         	    sheet8.getRow(AETPerRow+g8).getCell(16).setCellValue(AetPercentage.get(g8)[10]);//DEC 	         	    
 	         	    sheet8.getRow(AETPerRow+g8).getCell(17).setCellValue(AetPercentage.get(g8)[11]);//JAN
 	         	    sheet8.getRow(AETPerRow+g8).getCell(18).setCellValue(AetPercentage.get(g8)[12]);//FEB 	         	    
 	         	    sheet8.getRow(AETPerRow+g8).getCell(19).setCellValue(AetPercentage.get(g8)[13]);//MAR
 	         	    sheet8.getRow(AETPerRow+g8).getCell(20).setCellValue(AetPercentage.get(g8)[14]);//TOTAL 
 	         	    sheet8.getRow(AETPerRow+g8).getCell(21).setCellValue(AetPercentage.get(g8)[15]);//MEETINGS	         	    
		    	    sheet8.getRow(AETPerRow+g8).getCell(22).setCellValue(AetPercentage.get(g8)[16]);//PRESENT
		    	    sheet8.getRow(AETPerRow+g8).getCell(23).setCellValue(AetPercentage.get(g8)[17]);//ABSENT
		    	    sheet8.getRow(AETPerRow+g8).getCell(24).setCellValue(AetPercentage.get(g8)[18]);//LEAVE	         	    
	         	    sheet8.getRow(AETPerRow+g8).getCell(25).setCellValue(AetPercentage.get(g8)[19]);//WEEKOFF	         	    
	         	    sheet8.getRow(AETPerRow+g8).getCell(26).setCellValue(AetPercentage.get(g8)[20]);//ONDUTY     
        	    }		    	    
 	   }
        
           //************************PACT % Related************************// 

              int PACTPerRow =4;
     		  int g9;
              if(PActPercentage !=null && PActPercentage.size()>0){
            	 for(g9=0;g9<PActPercentage.size();g9++){
            		   sheet9.getRow(PACTPerRow+g9).getCell(1).setCellValue(g9+1);//sno	        
     				    sheet9.getRow(PACTPerRow+g9).getCell(2).setCellValue(PActPercentage.get(g9)[0]);//EMPLOYEE
     		    	    sheet9.getRow(PACTPerRow+g9).getCell(6).setCellValue(PActPercentage.get(g9)[1]);//CODE     	         	    
     		    	    sheet9.getRow(PACTPerRow+g9).getCell(7).setCellValue(PActPercentage.get(g9)[2]);//APR
     		    	    sheet9.getRow(PACTPerRow+g9).getCell(8).setCellValue(PActPercentage.get(g9)[3]);//MAY
     		    	    sheet9.getRow(PACTPerRow+g9).getCell(10).setCellValue(PActPercentage.get(g9)[4]);//JUN
     		    	    sheet9.getRow(PACTPerRow+g9).getCell(11).setCellValue(PActPercentage.get(g9)[5]);//JUL
     		    	    sheet9.getRow(PACTPerRow+g9).getCell(12).setCellValue(PActPercentage.get(g9)[6]);//AUG     	         	    
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(13).setCellValue(PActPercentage.get(g9)[7]);//SEP     	         	    
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(14).setCellValue(PActPercentage.get(g9)[8]);//OCT
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(15).setCellValue(PActPercentage.get(g9)[9]);//NOV     	         	    
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(16).setCellValue(PActPercentage.get(g9)[10]);//DEC     	         	    
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(17).setCellValue(PActPercentage.get(g9)[11]);//JAN
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(18).setCellValue(PActPercentage.get(g9)[12]);//FEB     	         	    
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(19).setCellValue(PActPercentage.get(g9)[13]);//MAR
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(20).setCellValue(PActPercentage.get(g9)[14]);//TOTAL 
     	         	    sheet9.getRow(PACTPerRow+g9).getCell(21).setCellValue(PActPercentage.get(g9)[15]);//MEETINGS   	         	    
   		    	        sheet9.getRow(PACTPerRow+g9).getCell(22).setCellValue(PActPercentage.get(g9)[16]);//PRESENT
   		    	        sheet9.getRow(PACTPerRow+g9).getCell(23).setCellValue(PActPercentage.get(g9)[17]);//ABSENT
   		    	        sheet9.getRow(PACTPerRow+g9).getCell(24).setCellValue(PActPercentage.get(g9)[18]);//LEAVE
   	         	        sheet9.getRow(PACTPerRow+g9).getCell(25).setCellValue(PActPercentage.get(g9)[19]);//WEEKOFF
   	         	        sheet9.getRow(PACTPerRow+g9).getCell(26).setCellValue(PActPercentage.get(g9)[20]);//ONDUTY
            	 }		    	    
     	}
        
              //************************Excel Heading Related************************// 

                if(Location !=null && Location.length()>0){
                	
                	   if(Finance.equals("Y")){
                		   
                         CommonMessage.debugMsg("Inside Fiance Y"+Finance);
                	     sheet.getRow(1).getCell(2).setCellValue("EHS Metrics(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
                  		 sheet.getRow(3).getCell(3).setCellValue(Location);//LOCATION  
                  		 sheet.getRow(3).getCell(9).setCellValue(DMTname);//DMT  
                  		 sheet.getRow(3).getCell(14).setCellValue(Dept);//JH   
                  		 sheet.getRow(4).getCell(5).setCellValue("Apr-"+CurYear);//APR
                  		 sheet.getRow(4).getCell(6).setCellValue("May-"+CurYear);//MAY
                  		 sheet.getRow(4).getCell(7).setCellValue("Jun-"+CurYear);//JUN
                  		 sheet.getRow(4).getCell(9).setCellValue("Jul-"+CurYear);//JUL
                  		 sheet.getRow(4).getCell(10).setCellValue("Aug-"+CurYear);//AUG
                  		 sheet.getRow(4).getCell(11).setCellValue("Sep-"+CurYear);//SEP
                  		 sheet.getRow(4).getCell(12).setCellValue("Oct-"+CurYear);//OCT
                  		 sheet.getRow(4).getCell(13).setCellValue("Nov-"+CurYear);//NOV
                  		 sheet.getRow(4).getCell(14).setCellValue("Dec-"+CurYear);//DEC
                  		 sheet.getRow(4).getCell(15).setCellValue("Jan-"+NextYear);//JAN
                  		 sheet.getRow(4).getCell(16).setCellValue("Feb-"+NextYear);//FEB
                  		 sheet.getRow(4).getCell(17).setCellValue("Mar-"+NextYear);//MAR
                  		 
                  		 sheet1.getRow(1).getCell(2).setCellValue("AET Attendance Adherence(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
                  		 sheet1.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
                  		 sheet1.getRow(2).getCell(8).setCellValue(DMTname);//DMT 
                  		 sheet1.getRow(2).getCell(13).setCellValue(Dept);//JH
                  		 
                  		 sheet2.getRow(1).getCell(2).setCellValue("PACT Attendance Adherence(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST 
                  		 sheet2.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
                  		 sheet2.getRow(2).getCell(8).setCellValue(DMTname);//DMT 
                  		 sheet2.getRow(2).getCell(13).setCellValue(Dept);//JH
                  		 
                    	 sheet3.getRow(1).getCell(2).setCellValue("Minutes of Meeting Review Points & Status(From "+QuarterFirst1+" TO "+QuarterEnd+")");//FYEARFIRST
                  		 sheet3.getRow(2).getCell(3).setCellValue(Location);//LOCATION  
                  		 sheet3.getRow(2).getCell(12).setCellValue(DMTname);//DMT 
                  		 sheet3.getRow(2).getCell(15).setCellValue(Dept);//JH
                  		 
                  		 sheet4.getRow(1).getCell(2).setCellValue("Status of Implemented Kaizens(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
                  		 sheet4.getRow(2).getCell(4).setCellValue(Location);//LOCATION  
                  		 sheet4.getRow(2).getCell(12).setCellValue(DMTname);//DMT 
                  		 sheet4.getRow(2).getCell(16).setCellValue(Dept);//JH 
                  		 sheet4.getRow(3).getCell(7).setCellValue("Apr-"+CurYear);//APR
                  		 sheet4.getRow(3).getCell(8).setCellValue("May-"+CurYear);//MAY
                  		 sheet4.getRow(3).getCell(9).setCellValue("Jun-"+CurYear);//JUN
                  		 sheet4.getRow(3).getCell(11).setCellValue("Jul-"+CurYear);//JUL
                  		 sheet4.getRow(3).getCell(12).setCellValue("Aug-"+CurYear);//AUG
                  		 sheet4.getRow(3).getCell(13).setCellValue("Sep-"+CurYear);//SEP
                  		 sheet4.getRow(3).getCell(14).setCellValue("Oct-"+CurYear);//OCT
                  		 sheet4.getRow(3).getCell(15).setCellValue("Nov-"+CurYear);//NOV
                  		 sheet4.getRow(3).getCell(16).setCellValue("Dec-"+CurYear);//DEC
                  		 sheet4.getRow(3).getCell(17).setCellValue("Jan-"+NextYear);//JAN
                  		 sheet4.getRow(3).getCell(18).setCellValue("Feb-"+NextYear);//FEB
                  		 sheet4.getRow(3).getCell(19).setCellValue("Mar-"+NextYear);//MAR
                  		 
                  		 
                  		 sheet5.getRow(2).getCell(4).setCellValue(Location);//LOCATION  
                  		 sheet5.getRow(2).getCell(7).setCellValue(DMTname);//DMT 
                  		 sheet5.getRow(2).getCell(10).setCellValue(Dept);//JH
               		     sheet5.getRow(1).getCell(2).setCellValue("Status of Abnormality(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
               		     sheet5.getRow(3).getCell(6).setCellValue("I-JAN-MAR-"+CurYear);
               		     sheet5.getRow(3).getCell(7).setCellValue("R-JAN-MAR-"+CurYear);
               		     sheet5.getRow(3).getCell(8).setCellValue("I-APR-"+CurYear);
                 		 sheet5.getRow(3).getCell(10).setCellValue("R-APR-"+CurYear);
               		     sheet5.getRow(3).getCell(11).setCellValue("I-MAY-"+CurYear);
               		     sheet5.getRow(3).getCell(12).setCellValue("R-MAY-"+CurYear);
               		     sheet5.getRow(3).getCell(13).setCellValue("I-JUN-"+CurYear);
               		     sheet5.getRow(3).getCell(14).setCellValue("R-JUN-"+CurYear);
               		     sheet5.getRow(3).getCell(15).setCellValue("I-JUL-"+CurYear);
               		     sheet5.getRow(3).getCell(16).setCellValue("R-JUL-"+CurYear);
               		     sheet5.getRow(3).getCell(17).setCellValue("I-AUG-"+CurYear);
            		     sheet5.getRow(3).getCell(18).setCellValue("R-AUG-"+CurYear);
            		     sheet5.getRow(3).getCell(19).setCellValue("I-SEP-"+CurYear);
            		     sheet5.getRow(3).getCell(20).setCellValue("R-SEP-"+CurYear);
            		     sheet5.getRow(3).getCell(21).setCellValue("I-OCT-"+CurYear);
            		     sheet5.getRow(3).getCell(22).setCellValue("R-OCT-"+CurYear);
            		     sheet5.getRow(3).getCell(23).setCellValue("I-NOV-"+CurYear);
            		     sheet5.getRow(3).getCell(24).setCellValue("R-NOV-"+CurYear);
            		     sheet5.getRow(3).getCell(25).setCellValue("I-DEC-"+CurYear);
            		     sheet5.getRow(3).getCell(26).setCellValue("R-DEC-"+CurYear);
            		     sheet5.getRow(3).getCell(27).setCellValue("I-JAN-"+NextYear);
            		     sheet5.getRow(3).getCell(28).setCellValue("R-JAN-"+NextYear);
            		     sheet5.getRow(3).getCell(29).setCellValue("I-FEB-"+NextYear);
            		     sheet5.getRow(3).getCell(30).setCellValue("R-FEB-"+NextYear);
            		     sheet5.getRow(3).getCell(31).setCellValue("I-MAR-"+NextYear);
            		     sheet5.getRow(3).getCell(32).setCellValue("R-MAR-"+NextYear);
               		  
                 	     sheet6.getRow(1).getCell(2).setCellValue("Abnormality Pending(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
                 	     sheet6.getRow(2).getCell(3).setCellValue(Location);//LOCATION  
              		     sheet6.getRow(2).getCell(5).setCellValue(DMTname);//DMT 
                         sheet6.getRow(2).getCell(7).setCellValue(Dept);//JH
                         
                 	     sheet7.getRow(1).getCell(2).setCellValue("LMP Tools Usage Summary(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
               		     sheet7.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
              		     sheet7.getRow(2).getCell(9).setCellValue(DMTname);//DMT 
              		     sheet7.getRow(2).getCell(14).setCellValue(Dept);//JH
                         
              		     if(UIUtils.isValidKeyId(JHkeyid)){
                  		    sheet6.getRow(3).getCell(2).setCellValue("JH");//JH
              		     }
              		     else{
               		    	sheet6.getRow(3).getCell(2).setCellValue("Abnormality Type");//ABN TYPE
              		     }
              		   
                        sheet8.getRow(1).getCell(2).setCellValue("AET Adherence Percentage(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST   
              	        sheet8.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
           		        sheet8.getRow(2).getCell(8).setCellValue(DMTname);//DMT 
           		        sheet8.getRow(2).getCell(13).setCellValue(Dept);//JH
           		        sheet8.getRow(3).getCell(7).setCellValue("Apr-"+CurYear);//APR
               		    sheet8.getRow(3).getCell(8).setCellValue("May-"+CurYear);//MAY
               		    sheet8.getRow(3).getCell(10).setCellValue("Jun-"+CurYear);//JUN
               		    sheet8.getRow(3).getCell(11).setCellValue("Jul-"+CurYear);//JUL
               		    sheet8.getRow(3).getCell(12).setCellValue("Aug-"+CurYear);//AUG
               		    sheet8.getRow(3).getCell(13).setCellValue("Sep-"+CurYear);//SEP
               		    sheet8.getRow(3).getCell(14).setCellValue("Oct-"+CurYear);//OCT
               		    sheet8.getRow(3).getCell(15).setCellValue("Nov-"+CurYear);//NOV
               		    sheet8.getRow(3).getCell(16).setCellValue("Dec-"+CurYear);//DEC
               		    sheet8.getRow(3).getCell(17).setCellValue("Jan-"+NextYear);//JAN
               		    sheet8.getRow(3).getCell(18).setCellValue("Feb-"+NextYear);//FEB
               		    sheet8.getRow(3).getCell(19).setCellValue("Mar-"+NextYear);//MAR
              		   
              		    sheet9.getRow(1).getCell(2).setCellValue("PACT Adherence Percentage(From "+FYearStart+" TO "+FYearEnd+")");//FYEARFIRST
               	        sheet9.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
            		    sheet9.getRow(2).getCell(8).setCellValue(DMTname);//DMT 
            		    sheet9.getRow(2).getCell(13).setCellValue(Dept);//JH
            		    sheet9.getRow(3).getCell(7).setCellValue("Apr-"+CurYear);//APR
                		sheet9.getRow(3).getCell(8).setCellValue("May-"+CurYear);//MAY
                		sheet9.getRow(3).getCell(10).setCellValue("Jun-"+CurYear);//JUN
                		sheet9.getRow(3).getCell(11).setCellValue("Jul-"+CurYear);//JUL
                		sheet9.getRow(3).getCell(12).setCellValue("Aug-"+CurYear);//AUG
                		sheet9.getRow(3).getCell(13).setCellValue("Sep-"+CurYear);//SEP
                		sheet9.getRow(3).getCell(14).setCellValue("Oct-"+CurYear);//OCT
                		sheet9.getRow(3).getCell(15).setCellValue("Nov-"+CurYear);//NOV
                		sheet9.getRow(3).getCell(16).setCellValue("Dec-"+CurYear);//DEC
                		sheet9.getRow(3).getCell(17).setCellValue("Jan-"+NextYear);//JAN
                		sheet9.getRow(3).getCell(18).setCellValue("Feb-"+NextYear);//FEB
                		sheet9.getRow(3).getCell(19).setCellValue("Mar-"+NextYear);//MAR
                	   }
                else{
                	 CommonMessage.debugMsg("Else"); 
                	 String Years=FromDate.substring(9,11);
                	 CommonMessage.debugMsg("Years::::"+Years);
                	 sheet.getRow(1).getCell(2).setCellValue("EHS Metrics(From "+FromDate+" TO "+ToDate+")");//FYEARFIRST
              		 sheet.getRow(3).getCell(3).setCellValue(Location);//LOCATION  
              		 sheet.getRow(3).getCell(9).setCellValue(DMTname);//DMT  
              		 sheet.getRow(3).getCell(14).setCellValue(Dept);//JH   
              		 sheet.getRow(4).getCell(5).setCellValue("Apr-"+Years);//APR
              		 sheet.getRow(4).getCell(6).setCellValue("May-"+Years);//MAY
              		 sheet.getRow(4).getCell(7).setCellValue("Jun-"+Years);//JUN
              		 sheet.getRow(4).getCell(9).setCellValue("Jul-"+Years);//JUL
              		 sheet.getRow(4).getCell(10).setCellValue("Aug-"+Years);//AUG
              		 sheet.getRow(4).getCell(11).setCellValue("Sep-"+Years);//SEP
              		 sheet.getRow(4).getCell(12).setCellValue("Oct-"+Years);//OCT
              		 sheet.getRow(4).getCell(13).setCellValue("Nov-"+Years);//NOV
              		 sheet.getRow(4).getCell(14).setCellValue("Dec-"+Years);//DEC
              		 sheet.getRow(4).getCell(15).setCellValue("Jan-"+Years);//JAN
              		 sheet.getRow(4).getCell(16).setCellValue("Feb-"+Years);//FEB
              		 sheet.getRow(4).getCell(17).setCellValue("Mar-"+Years);//MAR

                	 sheet1.getRow(1).getCell(2).setCellValue("AET Attendance Adherence(From "+FromDate+" TO "+ToDate+")");//FROMDATE
             		 sheet1.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
             		 sheet1.getRow(2).getCell(8).setCellValue(DMTname);//DMT 
             		 sheet1.getRow(2).getCell(13).setCellValue(Dept);//JH  
             		 
              		 sheet2.getRow(1).getCell(2).setCellValue("PACT Attendance Adherence(From "+FromDate+" TO "+ToDate+")");//FROMDATE 
              		 sheet2.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
              		 sheet2.getRow(2).getCell(8).setCellValue(DMTname);//DMT 
              		 sheet2.getRow(2).getCell(13).setCellValue(Dept);//JH
              		 
              		 sheet3.getRow(1).getCell(2).setCellValue("Minutes of Meeting Review Points & Status(From "+FromDate+" TO "+ToDate+")");//FROMDATE
              		 sheet3.getRow(2).getCell(3).setCellValue(Location);//LOCATION  
              		 sheet3.getRow(2).getCell(12).setCellValue(DMTname);//DMT 
              		 sheet3.getRow(2).getCell(15).setCellValue(Dept);//JH
              		 
              		 sheet4.getRow(1).getCell(2).setCellValue("Status of Implemented Kaizens(From "+FromDate+" TO "+ToDate+")");//FROMDATE
              		 sheet4.getRow(2).getCell(4).setCellValue(Location);//LOCATION  
              		 sheet4.getRow(2).getCell(12).setCellValue(DMTname);//DMT 
              		 sheet4.getRow(2).getCell(16).setCellValue(Dept);//JH 
              		 sheet4.getRow(3).getCell(7).setCellValue("Apr-"+Years);//APR
              		 sheet4.getRow(3).getCell(8).setCellValue("May-"+Years);//MAY
              		 sheet4.getRow(3).getCell(9).setCellValue("Jun-"+Years);//JUN
              		 sheet4.getRow(3).getCell(11).setCellValue("Jul-"+Years);//JUL
              		 sheet4.getRow(3).getCell(12).setCellValue("Aug-"+Years);//AUG
              		 sheet4.getRow(3).getCell(13).setCellValue("Sep-"+Years);//SEP
              		 sheet4.getRow(3).getCell(14).setCellValue("Oct-"+Years);//OCT
              		 sheet4.getRow(3).getCell(15).setCellValue("Nov-"+Years);//NOV
              		 sheet4.getRow(3).getCell(16).setCellValue("Dec-"+Years);//DEC
              		 sheet4.getRow(3).getCell(17).setCellValue("Jan-"+Years);//JAN
              		 sheet4.getRow(3).getCell(18).setCellValue("Feb-"+Years);//FEB
              		 sheet4.getRow(3).getCell(19).setCellValue("Mar-"+Years);//MAR
              		 
              		 
              	   sheet6.getRow(1).getCell(2).setCellValue("Abnormality Pending(From "+FromDate+" TO "+ToDate+")");//FROMDATE
           	       sheet6.getRow(2).getCell(3).setCellValue(Location);//LOCATION  
        		   sheet6.getRow(2).getCell(5).setCellValue(DMTname);//DMT 
                   sheet6.getRow(2).getCell(7).setCellValue(Dept);//JH
                   
                    if(UIUtils.isValidKeyId(JHkeyid)){
                    	sheet6.getRow(3).getCell(2).setCellValue("JH");//JH
         		     }
         		     else{
          		    	sheet6.getRow(3).getCell(2).setCellValue("Abnormality Type");//ABN TYPE
         		     }
                   
           	       sheet7.getRow(1).getCell(2).setCellValue("LMP Tools Usage Summary(From "+FromDate+" TO "+ToDate+")");//FROMDATE
         		   sheet7.getRow(2).getCell(5).setCellValue(Location);//LOCATION  
        		   sheet7.getRow(2).getCell(9).setCellValue(DMTname);//DMT 
        		   sheet7.getRow(2).getCell(14).setCellValue(Dept);//JH 
                	
                }
                }
		 sheet.protectSheet("admin");
		 sheet1.protectSheet("admin");
		 sheet2.protectSheet("admin");
		 sheet3.protectSheet("admin");
		 sheet4.protectSheet("admin");
		 sheet5.protectSheet("admin");
		 sheet6.protectSheet("admin");
		 sheet7.protectSheet("admin");
		 sheet8.protectSheet("admin");
		 sheet9.protectSheet("admin");
		 return wb;		
    }
}