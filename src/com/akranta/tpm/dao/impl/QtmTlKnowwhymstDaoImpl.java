package com.akranta.tpm.dao.impl;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.QtmTlKnowwhymstDao;
import com.akranta.tpm.dao.sql.EntTlTargetgroupmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.QtmTlKnowwhymstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.service.api.BestKaizenServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.KnowWhyServiceApi;
import com.akranta.tpm.dao.sql.QtmTlKnowwhydtlSql;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class QtmTlKnowwhymstDaoImpl implements QtmTlKnowwhymstDao {


	private DBActionTemplate dbActionTemplate; 
	private QtmTlKnowwhymstSql qtmTlKnowwhymstSql =  null;
	FunctionCallApi fnCallApi;
	KnowWhyServiceApi serviceApi;

	public QtmTlKnowwhymstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		qtmTlKnowwhymstSql = new QtmTlKnowwhymstSql();
	}
	
	public void KnowWhyDaoImplJwt(String JwtToken) 
	{
		try{
			serviceApi = new KnowWhyServiceApi (JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public QtmTlKnowwhymst create(QtmTlKnowwhymst qtmTlKnowwhymst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
		
		QtmTlKnowwhymstSql qtmTlKnowwhymstsql = new QtmTlKnowwhymstSql(); // contains dbtable,field names, Field types and related sqls  of master table

		String elementId = qtmTlKnowwhymst.getKnwmElementid();
	 	String location = null;
	 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,QtmTlKnowwhymstSql.TBL_QTM_TL_KNOWWHYMST);

		try{
			qtmTlKnowwhymst.setKnwmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf,10,"KNW","","")); // set the sequnce number 
			//qtmTlKnowwhymst.setKnwmKeyid(dbActionTemplate.getSequenceNumber(QtmTlKnowwhymstSql.TBL_QTM_TL_KNOWWHYMST,10,"KNWM","","")); // set the sequnce number 
			sqls.add(QtmTlKnowwhymstSql.getInsertSql(qtmTlKnowwhymstsql.getKnwmDbFields(), qtmTlKnowwhymst.getSaveArray())); // add insert sql for master table
	
			CommonMessage.debugMsg("after dtl "+sqls.toString());
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmImage() + " daoimpl image name");
			String fileDir = qtmTlKnowwhymst.getFileDir();
			//String imagepath = qtmTlKnowwhymst.getImagePath();
			String imagename= qtmTlKnowwhymst.getKnwmImage();
			
			String ImageName = (fileDir+imagename);
			qtmTlKnowwhymst.setKnwmImage(ImageName);
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmImage() + " last create");
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return qtmTlKnowwhymst;
	}
	
	public QtmTlKnowwhymst update(QtmTlKnowwhymst qtmTlKnowwhymst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		
		QtmTlKnowwhymstSql qtmTlKnowwhymstSql = new QtmTlKnowwhymstSql();
		try {

			sqls.add(QtmTlKnowwhymstSql.getUpdateSql(qtmTlKnowwhymstSql.getKnwmDbFields(), qtmTlKnowwhymst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			String fileDir = qtmTlKnowwhymst.getFileDir();
			String imagename= qtmTlKnowwhymst.getKnwmImage();
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmImage() + "  update ");
			String ImageName = (fileDir+""+imagename);
			CommonMessage.debugMsg(ImageName +  " update last");
			qtmTlKnowwhymst.setKnwmImage(ImageName);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlKnowwhymst;
	}
	
	public QtmTlKnowwhymst delete(QtmTlKnowwhymst qtmTlKnowwhymst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		QtmTlKnowwhymstSql qtmTlKnowwhymstsql = new QtmTlKnowwhymstSql();
		QtmTlKnowwhydtlSql qtmTlKnowwhydtlSql = new QtmTlKnowwhydtlSql();
		try {
			sqls.add(QtmTlKnowwhydtlSql.getDeleteForMasterSql(qtmTlKnowwhydtlSql.getKnwdDbFields(),  qtmTlKnowwhymst.getKnwmKeyid() ));
			sqls.add(QtmTlKnowwhymstSql.getDeleteSql(qtmTlKnowwhymstsql.getKnwmDbFields(), qtmTlKnowwhymst.getSaveArray()));
			

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return qtmTlKnowwhymst;
	}
	
	private void popSqlsForQtmTlKnowwhydtl(List<String>  sqls,QtmTlKnowwhydtl qtmTlKnowwhydtlList,String masterId ) throws Exception
	{
	CommonMessage.debugMsg("inside dtl  "+qtmTlKnowwhydtlList.getKnwdKnowwhy());
	List<String> WorkFlow=new ArrayList<String>();
	StringBuilder sb=new StringBuilder();
	
		if( qtmTlKnowwhydtlList != null )
		{
			QtmTlKnowwhydtlSql qtmTlKnowwhydtlSql = new QtmTlKnowwhydtlSql(); 
			CommonMessage.debugMsg("inside for  ");
			qtmTlKnowwhydtlList.setKnwdKnwmKeyid(masterId);
				if( qtmTlKnowwhydtlList.getKnwdKeyid() == null )
				{	
					qtmTlKnowwhydtlList.setKnwdKeyid(dbActionTemplate.getSequenceNumber(QtmTlKnowwhydtlSql.TBL_QTM_TL_KNOWWHYDTL,10,"KNWD","",""));
					sqls.add(QtmTlKnowwhydtlSql.getInsertSql(qtmTlKnowwhydtlSql.getKnwdDbFields(), qtmTlKnowwhydtlList.getSaveArray()));
					sb.append(" DELETE FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='" +masterId+"'");			
					WorkFlow.add(sb.toString());
					CommonMessage.debugMsg("The WorkFlow Delete"+WorkFlow);
					dbActionTemplate.executeStatements(WorkFlow); 
					CommonMessage.debugMsg("After Execute Block  WorkFlow Delete"+WorkFlow);
				}
				else{
					sqls.add(QtmTlKnowwhydtlSql.getUpdateSql(qtmTlKnowwhydtlSql.getKnwdDbFields(), qtmTlKnowwhydtlList.getSaveArray()));
				}
			}
		
	}


	
	@Override
	public List<String[]> getKnow(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		List<String[]> getKnwmain = null;
		
		try {
			String KEYID = commonFilter.getKey() ;
			String mode=commonFilter.getMainGroup();
			CommonMessage.debugMsg(" Inside CommonFunctions :: mode "+mode);
			String condParams = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg(" KEYID "+KEYID);
			
			if(UIUtils.isValidKeyId(mode)){CommonMessage.debugMsg(" Inside Uiutils :: 1 ");
		    
			condParams +="MODIFYMODE="+mode+";";
			CommonMessage.debugMsg(" Inside Uiutils :: 2 ");
		    
			}
		
			condParams=condParams+";KEYID="+KEYID+";";
			paramValues.add(condParams);
			paramValues.add(commonParams);
			//getKnwmain =dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_KNOWWHYMST",paramValues);
			getKnwmain =   fnCallApi.callFunction("QTM_FN_KNOWWHYMST_SB",paramValues,3,false);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(getKnwmain);
			return getKnwmain; 
		}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
			}

	@Override
	public QtmTlKnowwhymst getknwwhy(String keyId,String fileDir, String imagepath) {
		// TODO Auto-generated method stub
		QtmTlKnowwhymst  qtmTlKnowwhymst = new QtmTlKnowwhymst();
		String getknwwhy;
		CommonMessage.debugMsg("dao impl doubleclick");
		try
		{
			QtmTlKnowwhymstSql qtmTlKnowwhymstsql = new QtmTlKnowwhymstSql();
			
			String sql =  qtmTlKnowwhymstsql.getselectsql();
			Object [] args =  new Object [] {keyId };
			qtmTlKnowwhymst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
			CommonMessage.debugMsg(sql+""+keyId );
			CommonMessage.debugMsg(" image name dao impl"+qtmTlKnowwhymst.getKnwmImage() );
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmKeyid()  +  "  keyid "+ fileDir + " file Dir "+ imagepath);
			restorefile( keyId,fileDir,imagepath,qtmTlKnowwhymst);			
				
			}
		catch( Exception e){
		}
          return qtmTlKnowwhymst;
	}


	@Override
	public String restorefile(String keyId, String fileDir, String imagepath, QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("restore");
		 String ImageName = "";
		if(CommonFunctions.isValidKeyId(qtmTlKnowwhymst.getKnwmImage())){
			String refdoctype="KNW";
			String imagetype="KNW";
			String imagename=qtmTlKnowwhymst.getKnwmImage();
			CommonMessage.debugMsg(imagename +  " imagename restore");
			CommonMessage.debugMsg(fileDir + "filedir restore ");
			String condSql = " IMFL_REFDOCTYPE = '" + refdoctype+ "' AND IMFL_IMAGETYPE = '" + imagetype+"'";
			CommonMessage.debugMsg(imagepath + qtmTlKnowwhymst.getKnwmImage() + " filename 4");
			CommonMessage.debugMsg(imagepath + " filename 2");
			CommonMessage.debugMsg( qtmTlKnowwhymst.getKnwmImage() + " filename3");
			String fileName=imagepath + qtmTlKnowwhymst.getKnwmImage();
			CommonMessage.debugMsg("filename" + fileName);
			String condSql1 = " AND IMFL_REFKEYID = '" + keyId + "' AND " + condSql ;
			  ImageName =(fileDir+imagename);		
				CommonMessage.debugMsg("ImageName232" + ImageName);
				qtmTlKnowwhymst.setKnwmImage(ImageName); 
				CommonMessage.debugMsg("image name last"+qtmTlKnowwhymst.getKnwmImage() + "condSql1 " + condSql1 + " fileName" + fileName);
			dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileName);						
			CommonMessage.debugMsg("ImageName2" + fileDir+""+imagename);
			
		}
		
	
		return ImageName;
		
	}

	@Override
	public List<String[]> getITCKnow(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		List<String[]> getknwdtl = null;
		
		try {
			String KEYID = commonFilter.getKey() ;
			String condParams = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg(" KEYID "+KEYID);
			condParams=condParams+";KEYID="+KEYID+";";
			paramValues.add(condParams);
			paramValues.add(commonParams);
			//getknwdtl =dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_KNOWWHYDTL",paramValues);
			getknwdtl =fnCallApi.callFunction("QTM_FN_KNOWWHYDTL_SB",paramValues,3,false);
		}catch( Exception e){
		}
		return getknwdtl;
	}

	
	/*@Override
	public QtmTlKnowwhydtl createknowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();  sqls for execution  
		QtmTlKnowwhydtlSql qtmTlKnowwhydtlsql = new QtmTlKnowwhydtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		QtmTlKnowwhymst qtmTlKnowwhymst = new QtmTlKnowwhymst();
		
		try{
			CommonMessage.debugMsg("dao impl");
			qtmTlKnowwhydtl.setKnwdKnwmKeyid(qtmTlKnowwhymst.getKnwmKeyid());
			CommonMessage.debugMsg( " mstkeyid   dao impl  "+qtmTlKnowwhydtl.getKnwdKnwmKeyid());
			qtmTlKnowwhydtl.setKnwdKeyid(dbActionTemplate.getSequenceNumber(QtmTlKnowwhydtlSql.TBL_QTM_TL_KNOWWHYDTL,10,"KNWD","","")); // set the sequnce number 
			sqls.add(QtmTlKnowwhydtlSql.getInsertSql(qtmTlKnowwhydtlsql.getKnwdDbFields(),qtmTlKnowwhydtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return qtmTlKnowwhydtl;
	}
	@Override
	public QtmTlKnowwhydtl updateknowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		QtmTlKnowwhydtlSql qtmTlKnowwhydtlSql = new QtmTlKnowwhydtlSql();
		try {

			sqls.add(QtmTlKnowwhydtlSql.getUpdateSql(qtmTlKnowwhydtlSql.getKnwdDbFields(), qtmTlKnowwhydtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlKnowwhydtl;
	}
*/
	
	@Override
	public QtmTlKnowwhydtl deleteknwwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,String dtlkeyid) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		String keyid = qtmTlKnowwhydtl.getKnwdKeyid();
		CommonMessage.debugMsg(keyid + " keyid dao impl delete");
		QtmTlKnowwhydtlSql qtmTlKnowwhydtlsql = new QtmTlKnowwhydtlSql();
		try {
			
			sqls.add(QtmTlKnowwhydtlSql.getDelete(dtlkeyid));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return qtmTlKnowwhydtl;
	}

	@Override
	public Workbook getknowExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception, SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getKnowResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 3,0,0 );//Change for Excel Download 2->3
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	private ResultSet getKnowResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
	//	List<String> paramValues = getFilterParamValues(commonFilter);
	//	CommonMessage.debugMsg("result");
	//	return dbActionTemplate.dbFunctionCall("SOP_PC_SOP.QTM_FN_KNOWWHYMST", paramValues);
		
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.NewdbFunctionCall2("QTM_FN_KNOWWHYMST_SB", paramValues);
	}

	@Override
	public List<String[]> getKnowrpt(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		List<String[]> getKnwmain = null;
		
		try {
			String KEYID = commonFilter.getKey() ;
			String condParams = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg(" KEYID "+KEYID);
			condParams=condParams+";KEYID="+KEYID+";";
			paramValues.add(condParams);
			paramValues.add(commonParams);
			getKnwmain =dbActionTemplate.processFunctionCallsWithColHeaders("SOP_PC_SOP.QTM_FN_KNOWWHYRPT",paramValues);
		}catch( Exception e){
		}
		return getKnwmain;
	}

	@Override
	public List<String[]> KnowwhyExcelReport(String knowId,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> KnowwhyExportReport= null;
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(knowId);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			KnowwhyExportReport = (List<String[]>) dbActionTemplate.processFunctionCalls("QTM_FN_KNOWWHYPAGERPT", paramValues);
			CommonMessage.debugMsg(KnowwhyExportReport.size()+"   KnowwhyExportReport size : " +KnowwhyExportReport.size() );
			return KnowwhyExportReport;
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public List<GenTlAllmoduleimgfile> getKnwImage(
			List<GenTlAllmoduleimgfile> knwwhyImgList) throws Exception 
		// TODO Auto-generated method stub
		
			{
		try{
			CommonMessage.debugMsg("Inside Dao impl Imgh");
			
			List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
			CommonMessage.debugMsg("before loop"  +knwwhyImgList.size() );
			String sql="";
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:knwwhyImgList)
			{
				String fileName="";
				CommonMessage.debugMsg("after loop" + genTlAllmoduleimgfile.getImflFilename());
				//String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
				String condSql1 = " and  IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";;
				sql = " select IMFL_FILENAME from Gen_Tl_Allmoduleimgfile WHERE 1=1 "+condSql1;
				CommonMessage.debugMsg(dbActionTemplate  +  " dbabction");
				CommonMessage.debugMsg(sql);
				fileName = dbActionTemplate.getSingleValue(sql);
				CommonMessage.debugMsg(fileName +  " fileName");
				if( fileName != null )
				{
					
					if( fileName.lastIndexOf("/") > -1 )
					fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
					CommonMessage.debugMsg(fileName +   "  fileName 1");
					String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
				    CommonMessage.debugMsg(fileNamePath +  " fileNamePath"); 
					String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
					CommonMessage.debugMsg(imgFileName +  "  imgfile"); 
					genTlAllmoduleimgfile.setImflFilename(imgFileName);
					CommonMessage.debugMsg(genTlAllmoduleimgfile.getImflFilename() + "filename");
					CommonMessage.debugMsg(" fileName " + fileNamePath);
					
					dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
					genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
				}
				
			}
			
			return genTlAllmoduleimgList;
		}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new Exception(e.getMessage()); 
				
			}
		}

	@Override
	public QtmTlKnowwhymst createdtl(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception {
		// TODO Auto-generated method stub
                    List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
            		//List<String> workflow=new ArrayList<String>();
                   // StringBuilder sb=new StringBuilder();        
		 
		QtmTlKnowwhymstSql qtmTlKnowwhymstsql = new QtmTlKnowwhymstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		 
		try{
		
			qtmTlKnowwhymst.setKnwmKeyid(dbActionTemplate.getSequenceNumber(QtmTlKnowwhymstSql.TBL_QTM_TL_KNOWWHYMST,10,"KNWM","","")); // set the sequnce number 
			sqls.add(QtmTlKnowwhymstSql.getInsertSql(qtmTlKnowwhymstsql.getKnwmDbFields(), qtmTlKnowwhymst.getSaveArray())); // add insert sql for master table
			if(qtmTlKnowwhymst.getqtmTlKnowwhydtl()!= null  ){
				popSqlsForQtmTlKnowwhydtl(sqls, qtmTlKnowwhymst.getqtmTlKnowwhydtl(), qtmTlKnowwhymst.getKnwmKeyid());
			}
			//   
			CommonMessage.debugMsg("after dtl "+sqls.toString());
           // sb.append(" DELETE FROM GEN_TL_WORKFLOW_INFO WHERE WRIN_REF_ID='" +qtmTlKnowwhymst.getKnwmKeyid()+"'");			
           // workflow.add(sb.toString());
			//CommonMessage.debugMsg("The WorkFlow Delete"+workflow);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			//dbActionTemplate.executeStatements(workflow);
		//	CommonMessage.debugMsg("The WorkFlow Final Data"+workflow);
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmImage() + " daoimpl image name");
			String fileDir = qtmTlKnowwhymst.getFileDir();
			String imagename= qtmTlKnowwhymst.getKnwmImage();
			String ImageName = (fileDir+imagename);
			qtmTlKnowwhymst.setKnwmImage(ImageName);
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmImage() + " last create");
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return qtmTlKnowwhymst;
	}
	

	@Override
	public QtmTlKnowwhymst updatedtl(QtmTlKnowwhymst qtmTlKnowwhymst) throws Exception {
		// TODO Auto-generated method stub
	List<String> sqls = new ArrayList<String>();
		
		QtmTlKnowwhymstSql qtmTlKnowwhymstSql = new QtmTlKnowwhymstSql();
		try {

			sqls.add(QtmTlKnowwhymstSql.getUpdateSql(qtmTlKnowwhymstSql.getKnwmDbFields(), qtmTlKnowwhymst.getSaveArray()));
			if(qtmTlKnowwhymst.getqtmTlKnowwhydtl()!= null){
				popSqlsForQtmTlKnowwhydtl(sqls, qtmTlKnowwhymst.getqtmTlKnowwhydtl(), qtmTlKnowwhymst.getKnwmKeyid());
			}
			dbActionTemplate.executeStatements(sqls);
			String fileDir = qtmTlKnowwhymst.getFileDir();
			String imagename= qtmTlKnowwhymst.getKnwmImage();
			CommonMessage.debugMsg(qtmTlKnowwhymst.getKnwmImage() + "  update ");
			String ImageName = (fileDir+""+imagename);
			CommonMessage.debugMsg(ImageName +  " update last");
			qtmTlKnowwhymst.setKnwmImage(ImageName);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return qtmTlKnowwhymst;
	}

	@Override
	public int getStatusCount(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String cntSql="select count(*) from QTM_TL_KNOWWHY_APPROVALHIST where WRIN_REF_ID='"+keyid+"'";
		String cunt=dbActionTemplate.getSingleValue(cntSql);
		int cnt=Integer.parseInt(cunt);
		return cnt;
	}
	public String getapprovalkeyid(String appKeyid)throws Exception{
		
		List<String> sqls = new ArrayList<String>();
		//List<String> sqls2 = new ArrayList<String>();/* sqls for execution */ 
		try{
			if(UIUtils.isValidKeyId(appKeyid)){	
				StringBuffer sb1 = new StringBuffer();
			//	StringBuffer sb2 = new StringBuffer();
				CommonMessage.debugMsg("appKeyid::::::"+appKeyid);
				sb1.append(" INSERT INTO QTM_TL_KNOWWHY_APPROVALHIST ");
				sb1.append(" SELECT * FROM GEN_TL_WORKFLOW_INFO  WHERE  WRIN_REF_ID='"+appKeyid+"'");
				sqls.add(sb1.toString());
			//	sb2.append("DELETE FROM GEN_TL_WORKFLOW_INFO  WHERE  WRIN_REF_ID='"+appKeyid+"'");
			//	sqls2.add(sb2.toString());
				CommonMessage.debugMsg("sqls"+sqls);
				dbActionTemplate.executeStatements(sqls);
			  //  dbActionTemplate.executeStatements(sqls2);
				CommonMessage.debugMsg(sqls.toString()+" result");
			}	
	     }
		catch(Exception ex){
		  ex.printStackTrace();	
		}
		 return sqls.toString();
		}
	public List<String[]> getKnwwhycnt(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside wertgwer DAO Impl");
				
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	
			CommonMessage.debugMsg(" :: dashboardtype :: DaoImpl ::"+commonFilter.getType());
			
			if(UIUtils.isValidKeyId(commonFilter.getType()))
			      condParms +="EMPILLAR="+commonFilter.getType()+";";
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside  opl DAO Impl Cummulative " + paramValues);
			
			//List<String[]>  oplCummulativeList = dbActionTemplate.processFunctionCallsWithColHeaders("OPL_PC_ONEPOINTLESSION.OPL_FN_CUMULATIVERPT", paramValues);
			List<String[]> rootRptList;
			//rootRptList = dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_KNOWWHYCOUNT", paramValues);
			
			rootRptList = fnCallApi.callFunction("QTM_FN_KNOWWHYCOUNT_SB", paramValues,3,true);
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			 }
		
			return rootRptList	;
			//return oplCummulativeList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}


	public Workbook KnowWhyCountExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception, SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getKnowCountResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 2,0,0 );//Change for Excel Download
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	private ResultSet getKnowCountResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		commonFilter.setFromRow("1");
		commonFilter.setToRow("100");
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
        //return dbActionTemplate.NewdbFunctionCall2("QTM_FN_KNOWWHYMST", paramValues);
		return dbActionTemplate.NewdbFunctionCall2("QTM_FN_KNOWWHYCOUNT", paramValues);
	}	
}

