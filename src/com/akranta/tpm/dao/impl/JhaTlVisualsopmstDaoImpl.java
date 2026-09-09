package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.JhaTlVisualsopmstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.JhaTlVisualsopmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.JhaTlVisualsopmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.VisualsopServiceApi;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;

/* dao implementation */
public class JhaTlVisualsopmstDaoImpl implements JhaTlVisualsopmstDao {

	private VisualsopServiceApi visualsopServiceApi;
	FunctionCallApi fnCallApi;

	
	private DBActionTemplate dbActionTemplate; 
	private JhaTlVisualsopmstSql jhaTlVisualsopmstSql ;
	
	public JhaTlVisualsopmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
    public void JhaTlVisualsopmstDaoImplJwt(String jwtToken) {
		
		try{
			visualsopServiceApi = new VisualsopServiceApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public JhaTlVisualsopmst create(JhaTlVisualsopmst jhaTlVisualsopmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlVisualsopmstSql jhaTlVisualsopmstSql = new JhaTlVisualsopmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			String elementId = jhaTlVisualsopmst.getElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,JhaTlVisualsopmstSql.TBL_JHA_TL_VISUALSOPMST);
			
		 	jhaTlVisualsopmst.setVsomKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,10,"VS","","Y")); // set the sequnce number 
			//jhaTlVisualsopmst.setVsomKeyid(dbActionTemplate.getSequenceNumber(JhaTlVisualsopmstSql.TBL_JHA_TL_VISUALSOPMST,10,"VSOM","","Y"));  
			sqls.add(JhaTlVisualsopmstSql.getInsertSql(jhaTlVisualsopmstSql.getVsomDbFields(), jhaTlVisualsopmst.getSaveArray()));

			
			dbActionTemplate.executeStatements(sqls); 
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlVisualsopmst;
	}
	
	public JhaTlVisualsopmst update(JhaTlVisualsopmst jhaTlVisualsopmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlVisualsopmstSql jhaTlVisualsopmstSql = new JhaTlVisualsopmstSql();
		try {

			sqls.add(JhaTlVisualsopmstSql.getUpdateSql(jhaTlVisualsopmstSql.getVsomDbFields(), jhaTlVisualsopmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return jhaTlVisualsopmst;
	}
	
	public JhaTlVisualsopmst delete(JhaTlVisualsopmst jhaTlVisualsopmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlVisualsopmstSql jhaTlVisualsopmstSql = new JhaTlVisualsopmstSql();
		try {
			sqls.add(jhaTlVisualsopmstSql.getDeleteAllRecord(jhaTlVisualsopmst.getVsomKeyid()));
			sqls.add(jhaTlVisualsopmstSql.getDeleteSql(jhaTlVisualsopmstSql.getVsomDbFields(), jhaTlVisualsopmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlVisualsopmst;
	}
	@Override
	public List<String[]> getAllVisualSopDetailReport(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		  // ✅ Add these lines
	    String modifymode = commonFilter.getMainGroup();
	   // String formtype = commonFilter.getType();

	    if (UIUtils.isValidKeyId(modifymode))
	        condParms += "MODIFYMODE=" + modifymode + ";";

//	 // ✅ Use getRoleLevel() directly
//	    String roleLevelNo = commonFilter.getRoleLevel();
//	    if (UIUtils.isValidKeyId(roleLevelNo))
//	        condParms += "ROLELEVELNO=" + roleLevelNo + ";";
	    
//	    if (UIUtils.isValidKeyId(formtype))
//	        condParms += "FORMTYPE=" + formtype + ";";

		
		CommonMessage.debugMsg("commonParams" + commonParams);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("paramValues"+paramValues);
	//List<String[]> gridData = dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_VISUALSOP", paramValues);
		List<String[]> gridData = fnCallApi.callFunctionWithHeaders("JHN_FN_VISUALSOP_SB",paramValues,2,true);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return gridData;
		
		}
	@Override
	public List<String[]> getPPEDetail(String filePath)throws Exception 
	{   
		
		String sql = JhaTlVisualsopmstSql.getPPERecord();		
		CommonMessage.debugMsg("PPEDetail detail..." + sql);
		CommonMessage.debugMsg("getPPEDetail FILE NAME:::..." + filePath);
		List<String[]> gridData = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("gridData length::" +gridData.size());
		int rIndex=0;			
		for (String[] row : gridData) {
			if( rIndex  > 0){
				CommonMessage.debugMsg("gridData: [1]::" +row[1]);
				CommonMessage.debugMsg("gridData: [2]::" +row[4]);
				String fileName =  filePath+row[4];
				String ImageUrl =  UIUtils.TPM_TEMPIMG_DIR+row[4];
				CommonMessage.debugMsg("PPEDetail: image URL" + ImageUrl );
				if(row[2] != null && ! row[2].isEmpty()){
					String condSql1 = " AND TOIM_KEYID  = '" + row[2] + "'";
					
					dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_TOOLSIMG, "TOIM_BLOBIMAGE", condSql1, fileName);
					gridData.get(rIndex)[4] = ImageUrl; 
				}
			}
			rIndex++;
		}
		return gridData;
	}
	public List<String[]> getAllVisualSopDetail(String keyId,String filePath,CommonFilter commonFilter)
			throws Exception {
		
		CommonMessage.debugMsg("Inside Into getAllVisualSopDetail");
		StringBuffer sql = new StringBuffer();
		//List<String> params = null;
		//sql.append(JhaTlVisualsopmstSql.getAllDetailRecord(keyId));
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
		
		CommonMessage.debugMsg("detail final "+sql);
		List<String[]> gridData = visualsopServiceApi.getdetailbyKeyid(keyId);
		//List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		String refdoctype="VSP";
		String imagetype="TOL";
		String condSql = " IMFL_IMAGETYPE  = '" + imagetype+ "' AND IMFL_REFDOCTYPE = '" + refdoctype+"'";
			int rIndex=0;
			CommonMessage.debugMsg("condSql : " + condSql);
		for (String[] row : gridData) {
			CommonMessage.debugMsg("RIndex["+rIndex+"] : " + row[5]);
			if( rIndex  > 0){
				CommonMessage.debugMsg("RIndex["+rIndex+"] : " + row[5]);
				String fileName =  filePath+row[5];
		
				CommonMessage.debugMsg("RIndex["+rIndex+"] : " +fileName);
				if(CommonFunctions.isValidKeyId(row[5])){//row[5] != null && ! row[5].isEmpty()
					String condSql1 = " AND IMFL_REFKEYID = '" + row[0] + "' AND " + condSql ;
                 try{
                	 String CountSql = "select count(*) from GEN_TL_ALLMODULEIMGFILE where 1=1 " + condSql1 ;
 					String Filecount = dbActionTemplate.getSingleValue(CountSql);
 					CommonMessage.debugMsg("CountSql" + CountSql);
 					if (Integer.parseInt(Filecount) > 0 )
 					{
 						gridData.get(rIndex)[5] = fileName;
 					}
 					else
 					{
 						gridData.get(rIndex)[5] = "no-image";
 					}
                	
					dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileName);
					
                 }
                 catch(Exception e){
                	 if("NO-DATA".equals( e.getMessage())){
                		  
                		 gridData.get(rIndex)[5] =  "no-image";
                	 } 
                 }
				}
			}
			rIndex++;
		}
		return gridData;
	}
	@Override
	public List<GenTlAllmoduleimgfile>  getvsopImage(List<GenTlAllmoduleimgfile> oplImgList,String Vsopid)throws NoDataFoundException, Exception
	{
			CommonMessage.debugMsg("Inside Dao impl EXL Image ");
			//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
			List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
			GenTlAllmoduleimgfile genTlAllmoduleimgfile = oplImgList.get(0);
		try{		
			   //List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
			  
			   
				String sql = "select vsod_keyid from jha_tl_visualsopdtl,jha_tl_visualsopmst where vsom_keyid = vsod_vsom_keyid and vsod_vsom_keyid='"+Vsopid+"' order by vsod_keyid "; 
			
				 
				List<String[]> vsodid = dbActionTemplate.getDataList(sql);
				
				for(int i=0;i < vsodid.size();i++)
				{   
					String Keyid = vsodid.get(i)[0];
					
					GenTlAllmoduleimgfile newgenTlAllmoduleimg = new GenTlAllmoduleimgfile();
					String condSql = " IMFL_REFDOCTYPE = 'VSP' AND IMFL_IMAGETYPE = 'TOL' ";
					String imagecondSql = " and IMFL_REFDOCTYPE = 'VSP' AND IMFL_IMAGETYPE = 'TOL' and imfl_refkeyid ='" + Keyid + "'";
					String Filepath = genTlAllmoduleimgfile.getImflBlobimage();
					Filepath = Filepath + "\"";
					CommonMessage.debugMsg("FilePath" + Filepath);
					
					String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID",Keyid ,condSql);
					
					//dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_TOOLSIMG, "IMFL_BLOBIMAGE", condSql,fileName);
					
					if( fileName != null )
					{
						if( fileName.lastIndexOf("/") > -1 )
						fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
						
						String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+fileName; 
						CommonMessage.debugMsg("fileNamePath" + fileNamePath);
						dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", imagecondSql,fileNamePath);
						//String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
						newgenTlAllmoduleimg.setImflFilename(fileNamePath);
						newgenTlAllmoduleimg.setImflTempfield1(Keyid);
						CommonMessage.debugMsg(" fileName " + fileNamePath);					
				
						
					}
					genTlAllmoduleimgList.add(newgenTlAllmoduleimg);
				}
				
		}	
		catch(Exception e){
			e.printStackTrace();
			
		}
		return genTlAllmoduleimgList;	
			
	}
	
	public List<GenTlToolsimg>  getToolImage(List<GenTlToolsimg> oplImgList,String VsopDid)throws NoDataFoundException, Exception
	{
			CommonMessage.debugMsg("Inside Dao impl EXL Tool Image ");
			//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
			List<GenTlToolsimg> genTlToolsimgList = new ArrayList<GenTlToolsimg>();
			GenTlToolsimg genTlToolsimgfile = oplImgList.get(0);
		try{			
			   //List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
			  
			    CommonMessage.debugMsg("Count For Each");
				CommonMessage.debugMsg("VsopDid" + VsopDid);
				String sql = "select vsod_imgppe from jha_tl_visualsopdtl where vsod_keyid ='"+VsopDid+"'"; 
				CommonMessage.debugMsg("oplImgList 1" + sql);
				 
				String vsodid = dbActionTemplate.getSingleValue(sql);
				CommonMessage.debugMsg("oplImgList" + sql);
				String[] toolid = vsodid.split(",");
				CommonMessage.debugMsg("oplImgList 2" + toolid.length);
				
				for(int i=1;i < toolid.length;i++)
				{
					CommonMessage.debugMsg("toolid ["+ i + "]" + toolid[i]);
				}
				for(int i=1;i < toolid.length;i++)
				{
					GenTlToolsimg newgenTlToolsimg = new GenTlToolsimg();
					String condSql = "";
					String Keyid = toolid[i];
					if (Keyid != null)						
					{   String Condlsql ="and TOIM_keyid ='"+ Keyid +"'";
						String FilePath = genTlToolsimgfile.getToimBlobimage() ;
						FilePath = FilePath + "\"";
						
						String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_TOOLSIMG , "TOIM_FILENAME", "TOIM_KEYID",Keyid);
						
						if( fileName != null )
						{
							if( fileName.lastIndexOf("/") > -1 )
							fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
							
							String fileNamePath = genTlToolsimgfile.getToimBlobimage()+ fileName; 
							CommonMessage.debugMsg("fileNamePath" + fileNamePath);
							dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_TOOLSIMG, "toim_blobimage", Condlsql,fileNamePath );
							//String imgFileName = genTlToolsimgfile.getToimFilename()+fileName;
							newgenTlToolsimg.setToimFilename(fileNamePath);
							 
							CommonMessage.debugMsg(" fileName " + fileNamePath);					
					
							
						}
						genTlToolsimgList.add(newgenTlToolsimg);
					}
				}
				
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		return genTlToolsimgList;	
			
	}
	
	@Override
	public JhaTlVisualsopmst getAllFillControl(String keyId)throws Exception {
		JhaTlVisualsopmst jhaTlVisualsopmst  = new JhaTlVisualsopmst();
		String sql = JhaTlVisualsopmstSql.getSingledata();
		CommonMessage.debugMsg("sql in impl"+sql);
		Object args[] = new Object[] {keyId};
		jhaTlVisualsopmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return jhaTlVisualsopmst;
}
	private ResultSet getVSopReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("JHN_FN_VISUALSOP", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
}
	@Override
	public Workbook visualSOPExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getVSopReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(11);
			
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat,1,1,0 ); //elumalai
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}

	
	private ResultSet getVSopDetailReportResultSet(CommonFilter commonFilter) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		String keyId=commonFilter.getVisualKeyId();
		sql.append(JhaTlVisualsopmstSql.getAllDetailRecordExcelResultset(keyId));
		return dbActionTemplate.getData(sql.toString());
	}
	@Override
	public Workbook visualSOPDetailExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getVSopDetailReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat,0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   } 
	}

	
	
}

