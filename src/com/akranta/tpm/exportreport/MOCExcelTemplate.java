package com.akranta.tpm.exportreport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import com.akranta.tpm.service.MocService;

import com.akranta.tpm.service.impl.MocServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;

public class MOCExcelTemplate{
	MocService mocService;
    public MOCExcelTemplate(DBActionTemplate dbActionTemplate) throws Exception{
    	mocService=new MocServiceImpl(dbActionTemplate);
    	
    }


	public Workbook fillvaluesMOC(List<String[]> MOCWorkFLow,List<String[]> RFC,List<String[]> Question,List<String[]> Whatif,List<String[]> Hazop, List<String[]> WHReccommend,List<String[]>  PssrCheck,List<String[]> PssrReccommend,List<String[]> MOCClosure,List<String[]> Approvals,List<String[]> HazopApprovals,List<String[]> FinalApprovals,String format, String path, String dMT, String jH,String MOCDate,String mocKeyid,String suggestion,String initiator, String title, String type, String nature,String pChange,String SuggestionId,String detail,String WhatifFacility,String WhatifTeam,String WhatifDate,String hazopFacility, String hazopTeam, String hazopNode, String hazopDesign, String hazopDate,String pidNo,String PssrFacility,String MOCDetails) throws IOException {
		// TODO Auto-generated method stub
		String excelPath = null;
		excelPath = "/MOCExceView.xlsx";	
		CommonMessage.debugMsg(path+" Paths of excel in moc excel view IN PERFEX");
		InputStream inp = new FileInputStream(path + excelPath);
	       
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
    //    header1Font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        header1Font.setBold(true);
        header1Font.setColor(IndexedColors.BLACK.getIndex());
        header1Font.setFontName(XLConditionalFormats.FONT_WINGDINGS);
        
		CellStyle style = wb.createCellStyle();
	    CellStyle style1 = wb.createCellStyle();
	        
        style1.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        
      //  style1.setAlignment(CellStyle.ALIGN_CENTER);
        style1.setAlignment(HorizontalAlignment.CENTER);
        
    //    style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
      	style1.setVerticalAlignment(VerticalAlignment.CENTER);
        
    //    style1.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        //style1.setFillPattern(CellStyle.DIAMONDS);
       
        
        
        
      //  style.setAlignment(CellStyle.ALIGN_CENTER);
     	style.setAlignment(HorizontalAlignment.CENTER);
     	
       // style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);   
    	style.setVerticalAlignment(VerticalAlignment.CENTER);
        
   //     style.setBorderTop(CellStyle.BORDER_NONE);
	//	style.setBorderLeft(CellStyle.BORDER_NONE);
	//	style.setBorderRight(CellStyle.BORDER_NONE);
	//	style.setBorderBottom(CellStyle.BORDER_NONE);
		
		style.setBorderTop(BorderStyle.NONE);
		style.setBorderLeft(BorderStyle.NONE);
		style.setBorderRight(BorderStyle.NONE);
		style.setBorderBottom(BorderStyle.NONE);
		
     //   style.setBorderTop(CellStyle.BORDER_THIN);
	//	style.setBorderLeft(CellStyle.BORDER_THIN);
	//	style.setBorderRight(CellStyle.BORDER_THIN);
	//	style.setBorderBottom(CellStyle.BORDER_THIN);
		
		
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		
		
		
    	style.setFont(header1Font);
    	//Moc WorkFLow
   CommonMessage.debugMsg("iNSIE");
        int WorkFlowRow =7;
		    int g;
        if(MOCWorkFLow !=null && MOCWorkFLow.size()>0){ 	
    	 for(g=0;g<MOCWorkFLow.size();g++){
    		 
    		    sheet.getRow(WorkFlowRow+g).getCell(2).setCellValue(MOCWorkFLow.get(g)[0]);//DMT				   		    
    		    sheet.getRow(WorkFlowRow+g).getCell(6).setCellValue(MOCWorkFLow.get(g)[1]);//DMT 				    
		    	    sheet.getRow(WorkFlowRow+g).getCell(10).setCellValue(MOCWorkFLow.get(g)[2]);//APR
		    	    sheet.getRow(WorkFlowRow+g).getCell(13).setCellValue(MOCWorkFLow.get(g)[3]);//MAY
		    	    sheet.getRow(WorkFlowRow+g).getCell(15).setCellValue(MOCWorkFLow.get(g)[4]);//JUN
		    	   
    	          }		    	    
 	     }
        
        //MOC Request for Change
        int RequestForChange =10;
	    int g1;
    if(RFC !=null && RFC.size()>0){ 	
	 for(g1=0;g1<RFC.size();g1++){
		 
		 sheet1.getRow(RequestForChange+g1).getCell(5).setCellValue(RFC.get(g1)[0]);//Basis Of Change				   		    
		 
	    	   
	          }		    	    
	     }
    // Questionaire
    int Questionaire=6;
    int g2;
    if(Question !=null && Question.size()>0){ 	
   	 for(g2=0;g2<Question.size();g2++){
   		 
   		 sheet2.getRow(Questionaire+g2).getCell(2).setCellValue(Question.get(g2)[0]);//Question			   		    
   		 sheet2.getRow(Questionaire+g2).getCell(6).setCellValue(Question.get(g2)[1]);
   	    	   
   	          }		    	    
   	     }
    int What=7;
    int g3;
    if(Whatif !=null && Whatif.size()>0){ 	
      	 for(g3=0;g3<Whatif.size();g3++){
      		 
      		 sheet3.getRow(What+g3).getCell(2).setCellValue(Whatif.get(g3)[0]);//Whatif				   		    
      		 sheet3.getRow(What+g3).getCell(5).setCellValue(Whatif.get(g3)[1]);
      		sheet3.getRow(What+g3).getCell(9).setCellValue(Whatif.get(g3)[2]);
      		sheet3.getRow(What+g3).getCell(12).setCellValue(Whatif.get(g3)[3]);
      		sheet3.getRow(What+g3).getCell(14).setCellValue(Whatif.get(g3)[4]);
      		sheet3.getRow(What+g3).getCell(15).setCellValue(Whatif.get(g3)[5]);
     		 sheet3.getRow(What+g3).getCell(16).setCellValue(Whatif.get(g3)[6]);
     		 sheet3.getRow(What+g3).getCell(17).setCellValue(Whatif.get(g3)[7]);
     		 sheet3.getRow(What+g3).getCell(18).setCellValue(Whatif.get(g3)[8]);
     		 sheet3.getRow(What+g3).getCell(19).setCellValue(Whatif.get(g3)[9]);
     		 sheet3.getRow(What+g3).getCell(20).setCellValue(Whatif.get(g3)[10]);
     		sheet3.getRow(What+g3).getCell(21).setCellValue(Whatif.get(g3)[11]);
     
      	          }		    	    
      	     }
    
    int HzOperation=8;
    int g4;
    if(Hazop !=null && Hazop.size()>0){ 	
     	 for(g4=0;g4<Hazop.size();g4++){
     		 
     		 sheet4.getRow(HzOperation+g4).getCell(2).setCellValue(Hazop.get(g4)[0]);//Hazop				   		    
     		 sheet4.getRow(HzOperation+g4).getCell(5).setCellValue(Hazop.get(g4)[1]);
     		sheet4.getRow(HzOperation+g4).getCell(9).setCellValue(Hazop.get(g4)[2]);
     		sheet4.getRow(HzOperation+g4).getCell(12).setCellValue(Hazop.get(g4)[3]);
     		sheet4.getRow(HzOperation+g4).getCell(14).setCellValue(Hazop.get(g4)[4]);
     		sheet4.getRow(HzOperation+g4).getCell(15).setCellValue(Hazop.get(g4)[5]);
    		 sheet4.getRow(HzOperation+g4).getCell(16).setCellValue(Hazop.get(g4)[6]);
    		 sheet4.getRow(HzOperation+g4).getCell(17).setCellValue(Hazop.get(g4)[7]);
    		 sheet4.getRow(HzOperation+g4).getCell(18).setCellValue(Hazop.get(g4)[8]);
    		 sheet4.getRow(HzOperation+g4).getCell(19).setCellValue(Hazop.get(g4)[9]);
    		 sheet4.getRow(HzOperation+g4).getCell(20).setCellValue(Hazop.get(g4)[10]);
    		sheet4.getRow(HzOperation+g4).getCell(21).setCellValue(Hazop.get(g4)[11]);
    		sheet4.getRow(HzOperation+g4).getCell(22).setCellValue(Hazop.get(g4)[12]);
    		sheet4.getRow(HzOperation+g4).getCell(23).setCellValue(Hazop.get(g4)[13]);
    		
    
     	          }		    	    
     	     }
   //HzReccommendation
    int HzReccommend=6;
    int g5;
    if(WHReccommend !=null && WHReccommend.size()>0){ 	
    	CommonMessage.debugMsg("Inside Haop ");
      	 for(g5=0;g5<WHReccommend.size();g5++){
      		CommonMessage.debugMsg("IF Haop ");
      		 sheet5.getRow(HzReccommend+g5).getCell(2).setCellValue(WHReccommend.get(g5)[0]);//Question			   		    
      		 sheet5.getRow(HzReccommend+g5).getCell(3).setCellValue(WHReccommend.get(g5)[1]);
      		 sheet5.getRow(HzReccommend+g5).getCell(7).setCellValue(WHReccommend.get(g5)[2]);	
      		sheet5.getRow(HzReccommend+g5).getCell(8).setCellValue(WHReccommend.get(g5)[3]);	
      		sheet5.getRow(HzReccommend+g5).getCell(10).setCellValue(WHReccommend.get(g5)[4]);	
      		sheet5.getRow(HzReccommend+g5).getCell(12).setCellValue(WHReccommend.get(g5)[5]);
      		sheet5.getRow(HzReccommend+g5).getCell(14).setCellValue(WHReccommend.get(g5)[6]);
      	          }		    	    
      	     }
    
  // PssrCheckList
    int CHeckList=7;
    int g6;
    if(PssrCheck !=null && PssrCheck.size()>0){ 	
   	 for(g6=0;g6<PssrCheck.size();g6++){
   		 
   		 sheet6.getRow(CHeckList+g6).getCell(2).setCellValue(PssrCheck.get(g6)[0]);//Question			   		    
   		 sheet6.getRow(CHeckList+g6).getCell(5).setCellValue(PssrCheck.get(g6)[1]);
   		 sheet6.getRow(CHeckList+g6).getCell(7).setCellValue(PssrCheck.get(g6)[2]);	   
   	          }		    	    
   	     }

   int Pssrr =6;
    int g7;
    if(PssrReccommend !=null && PssrReccommend.size()>0){ 	
     	 for(g7=0;g7<PssrReccommend.size();g7++){
     		CommonMessage.debugMsg("Inside Reccedsf ");
     		 sheet7.getRow(Pssrr+g7).getCell(2).setCellValue(PssrReccommend.get(g7)[0]);			   		    
       		 sheet7.getRow(Pssrr+g7).getCell(6).setCellValue(PssrReccommend.get(g7)[1]);
       		 sheet7.getRow(Pssrr+g7).getCell(7).setCellValue(PssrReccommend.get(g7)[2]);
       		sheet7.getRow(Pssrr+g7).getCell(8).setCellValue(PssrReccommend.get(g7)[3]);
       		sheet7.getRow(Pssrr+g7).getCell(10).setCellValue(PssrReccommend.get(g7)[4]);
       		sheet7.getRow(Pssrr+g7).getCell(12).setCellValue(PssrReccommend.get(g7)[5]);
       		sheet7.getRow(Pssrr+g7).getCell(14).setCellValue(PssrReccommend.get(g7)[6]);
     	 }		    	    
     	     }
    
    int Closure=5;
    int g8;
    if(MOCClosure !=null && MOCClosure.size()>0){ 	
    	CommonMessage.debugMsg("Inside Closure");
   	 for(g8=0;g8<MOCClosure.size();g8++){
   		 
   		 sheet8.getRow(Closure+g8).getCell(2).setCellValue(MOCClosure.get(g8)[0]);//Question			   		    
   		 sheet8.getRow(Closure+g8).getCell(6).setCellValue(MOCClosure.get(g8)[1]);
   	    	   
   	          }		    	    
   	     }
  int  Approve=6;
  int g9;
  if(Approvals !=null && Approvals.size()>0){ 	
  	CommonMessage.debugMsg("Inside Closure");
 	 for(g9=0;g9<Approvals.size();g9++){
 		 
 		 sheet9.getRow(Approve+g9).getCell(2).setCellValue(Approvals.get(g9)[0]);//Question			   		    
 		 sheet9.getRow(Approve+g9).getCell(7).setCellValue(Approvals.get(g9)[1]);
 		sheet9.getRow(Approve+g9).getCell(8).setCellValue(Approvals.get(g9)[2]);
 		sheet9.getRow(Approve+g9).getCell(10).setCellValue(Approvals.get(g9)[3]);
 		sheet9.getRow(Approve+g9).getCell(12).setCellValue(Approvals.get(g9)[4]);
 	
 	    	   
 	          }		    	    
 	     }
  
  int  HZApprove=24;
  int g10;
  if(HazopApprovals !=null && HazopApprovals.size()>0){ 	
  	CommonMessage.debugMsg("Inside Closure");
 	 for(g10=0;g10<HazopApprovals.size();g10++){
 		 
 		 sheet9.getRow(HZApprove+g10).getCell(2).setCellValue(HazopApprovals.get(g10)[0]);//Question			   		    
 		 sheet9.getRow(HZApprove+g10).getCell(7).setCellValue(HazopApprovals.get(g10)[1]);
 		sheet9.getRow(HZApprove+g10).getCell(8).setCellValue(HazopApprovals.get(g10)[2]);
 		sheet9.getRow(HZApprove+g10).getCell(10).setCellValue(HazopApprovals.get(g10)[3]);
 		sheet9.getRow(HZApprove+g10).getCell(12).setCellValue(HazopApprovals.get(g10)[4]);
 	
 	    	   
 	          }		    	    
 	     }
  
  int  FAApprove=42;
  int g11;
  if(FinalApprovals !=null && FinalApprovals.size()>0){ 	
  	CommonMessage.debugMsg("Inside Closure");
 	 for(g11=0;g11<FinalApprovals.size();g11++){
 		 
 		 sheet9.getRow(FAApprove+g11).getCell(2).setCellValue(FinalApprovals.get(g11)[0]);//Question			   		    
 		 sheet9.getRow(FAApprove+g11).getCell(7).setCellValue(FinalApprovals.get(g11)[1]);
 		sheet9.getRow(FAApprove+g11).getCell(8).setCellValue(FinalApprovals.get(g11)[2]);
 		sheet9.getRow(FAApprove+g11).getCell(10).setCellValue(FinalApprovals.get(g11)[3]);
 		sheet9.getRow(FAApprove+g11).getCell(12).setCellValue(FinalApprovals.get(g11)[4]);
 	
 	    	   
 	          }		    	    
 	     }
        //************************Excel Heading Related************************// 
     //   String dMT, String jH,String MOCDate,String mocKeyid,String suggestion
    
        if(mocKeyid !=null ){  	
        	     sheet.getRow(3).getCell(6).setCellValue(suggestion);//Suggestion 17
        	     sheet.getRow(4).getCell(6).setCellValue(mocKeyid);//mocid
          		 sheet.getRow(4).getCell(13).setCellValue(MOCDate);//DATE  
          		 sheet.getRow(4).getCell(17).setCellValue(SuggestionId);//SUGGESTIONID  
          		 sheet.getRow(5).getCell(6).setCellValue(dMT);//Date   
          		 sheet.getRow(5).getCell(15).setCellValue(jH);//Keyid
          		
          		 sheet1.getRow(2).getCell(5).setCellValue(suggestion);
          		 sheet1.getRow(3).getCell(5).setCellValue(mocKeyid);
          		 sheet1.getRow(3).getCell(10).setCellValue(MOCDate);
          		 sheet1.getRow(3).getCell(13).setCellValue(SuggestionId);
          		 sheet1.getRow(4).getCell(5).setCellValue(dMT);
          	     sheet1.getRow(4).getCell(12).setCellValue(jH);
          	     sheet1.getRow(5).getCell(5).setCellValue(initiator);
          	     sheet1.getRow(5).getCell(10).setCellValue(title);
          	     sheet1.getRow(6).getCell(5).setCellValue(type);
        	     sheet1.getRow(6).getCell(12).setCellValue(nature);
          	     sheet1.getRow(7).getCell(5).setCellValue(detail);
           	     sheet1.getRow(8).getCell(5).setCellValue(pChange); 
           	   
           	     sheet2.getRow(2).getCell(5).setCellValue(suggestion);
           	     sheet2.getRow(3).getCell(5).setCellValue(mocKeyid);
           	     sheet2.getRow(3).getCell(11).setCellValue(MOCDate);
           	     sheet2.getRow(3).getCell(14).setCellValue(SuggestionId);
              	
           	     sheet2.getRow(4).getCell(5).setCellValue(dMT);
           	     sheet2.getRow(4).getCell(13).setCellValue(jH);
           	    
           
           	sheet3.getRow(2).getCell(5).setCellValue(suggestion);
          
           	sheet3.getRow(3).getCell(5).setCellValue(mocKeyid);
           	sheet3.getRow(3).getCell(15).setCellValue(MOCDate);
           	sheet3.getRow(3).getCell(20).setCellValue(SuggestionId);
        	
        	sheet3.getRow(4).getCell(5).setCellValue(dMT);
        	sheet3.getRow(4).getCell(19).setCellValue(jH);
        	
           	sheet3.getRow(5).getCell(20).setCellValue(mocKeyid);
        	sheet3.getRow(5).getCell(5).setCellValue(WhatifFacility);
        	sheet3.getRow(5).getCell(14).setCellValue(WhatifTeam);
        	sheet3.getRow(5).getCell(20).setCellValue(WhatifDate);
        
        	sheet4.getRow(2).getCell(5).setCellValue(suggestion);
        	sheet4.getRow(3).getCell(5).setCellValue(mocKeyid);
        	sheet4.getRow(3).getCell(14).setCellValue(MOCDate);
        	sheet4.getRow(3).getCell(18).setCellValue(SuggestionId);
        	sheet4.getRow(3).getCell(22).setCellValue(pidNo);
        	sheet4.getRow(4).getCell(5).setCellValue(dMT);
        	sheet4.getRow(4).getCell(14).setCellValue(jH);
        	sheet4.getRow(4).getCell(18).setCellValue(hazopDate);
        	sheet4.getRow(4).getCell(22).setCellValue(hazopNode);
        	
        	sheet4.getRow(5).getCell(5).setCellValue(hazopFacility);
        	sheet4.getRow(5).getCell(18).setCellValue(hazopTeam);
        	sheet4.getRow(6).getCell(5).setCellValue(hazopDesign);
        
        	
        	  sheet5.getRow(2).getCell(5).setCellValue(suggestion);
              sheet5.getRow(3).getCell(5).setCellValue(mocKeyid);
              sheet5.getRow(3).getCell(10).setCellValue(MOCDate);
              sheet5.getRow(3).getCell(14).setCellValue(SuggestionId);
              
              sheet5.getRow(4).getCell(5).setCellValue(dMT);
              sheet5.getRow(4).getCell(10).setCellValue(jH);
              
              
             	
          sheet6.getRow(2).getCell(4).setCellValue(suggestion);
          sheet6.getRow(3).getCell(4).setCellValue(mocKeyid);
          sheet6.getRow(3).getCell(7).setCellValue(MOCDate);
          sheet6.getRow(3).getCell(9).setCellValue(SuggestionId);
          
      	  sheet6.getRow(4).getCell(4).setCellValue(dMT);
       	  sheet6.getRow(4).getCell(7).setCellValue(jH);
    	  sheet6.getRow(5).getCell(4).setCellValue(PssrFacility);
    	  sheet6.getRow(5).getCell(7).setCellValue(detail);
    	  
    	  
    	  sheet7.getRow(2).getCell(5).setCellValue(suggestion);
          sheet7.getRow(3).getCell(5).setCellValue(mocKeyid);
          sheet7.getRow(3).getCell(10).setCellValue(MOCDate);
          sheet7.getRow(3).getCell(14).setCellValue(SuggestionId);
          sheet7.getRow(4).getCell(5).setCellValue(dMT);
      	  sheet7.getRow(4).getCell(10).setCellValue(jH);
      	  

      	  
      	  sheet8.getRow(2).getCell(5).setCellValue(suggestion);
          sheet8.getRow(2).getCell(14).setCellValue(SuggestionId);
          sheet8.getRow(3).getCell(5).setCellValue(mocKeyid);
          sheet8.getRow(3).getCell(8).setCellValue(MOCDate);
          sheet8.getRow(3).getCell(11).setCellValue(dMT);
      	  sheet8.getRow(3).getCell(14).setCellValue(jH);
      	  
      	sheet9.getRow(2).getCell(5).setCellValue(suggestion);
        sheet9.getRow(2).getCell(14).setCellValue(SuggestionId);
        sheet9.getRow(3).getCell(5).setCellValue(mocKeyid);
        sheet9.getRow(3).getCell(7).setCellValue(MOCDate);
        sheet9.getRow(3).getCell(11).setCellValue(dMT);
        sheet9.getRow(3).getCell(14).setCellValue(jH);
        	
        }
        
        
		return wb;
     
	}
	
		         
		        
}