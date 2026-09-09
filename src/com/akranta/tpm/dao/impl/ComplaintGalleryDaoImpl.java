package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.ComplaintGalleryDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.QtmTlComplaintgallerySql;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.QtmTlComplaintgallery;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.service.api.ComplaintGalleryServiceApi;

public class ComplaintGalleryDaoImpl implements ComplaintGalleryDao {
private DBActionTemplate dbActionTemplate;
private QtmTlComplaintgallerySql qtmTlComplaintgallerySql; 
private ComplaintGalleryServiceApi complaintgalleryserviceapi;
FunctionCallApi fnCallApi;


	public ComplaintGalleryDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
		 qtmTlComplaintgallerySql =new QtmTlComplaintgallerySql();
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void ComplaintGalleryDaoImplJwt(String JwtToken) 
	{
		try{
			complaintgalleryserviceapi = new ComplaintGalleryServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String[]> getAllCompliant(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_COMPLAINTGALLARY", paramValues);
			
			List<String[]> dataList =   fnCallApi.callFunction("QTM_FN_COMPLAINTGALLARY_SB", paramValues,3,true);
			
			
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}
	public QtmTlComplaintgallery create(QtmTlComplaintgallery qtmTlComplaintgallery) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		QtmTlComplaintgallerySql qtmTlComplaintgallerySql = new QtmTlComplaintgallerySql(); // contains dbtable,field names, Field types and related sqls  of master table
		try{
			String elementId = qtmTlComplaintgallery.getElementid();
		 	String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,QtmTlComplaintgallerySql.TBL_QTM_TL_COMPLAINTGALLERY);

			//qtmTlComplaintgallery.setCmgaKeyid(dbActionTemplate.getSequenceNumber("QTM_TL_COMPLAINTGALLERY", 10, "CMGA", "", "")); // set the sequnce number
			qtmTlComplaintgallery.setCmgaKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "CM", "", "")); // set the sequnce number 
			sqls.add(QtmTlComplaintgallerySql.getInsertSql(qtmTlComplaintgallerySql.getCmgaDbFields(), qtmTlComplaintgallery.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return qtmTlComplaintgallery;
	}
	
	public QtmTlComplaintgallery update(QtmTlComplaintgallery qtmTlComplaintgallery)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		QtmTlComplaintgallerySql qtmTlComplaintgallerySql = new QtmTlComplaintgallerySql();
		try {
			sqls.add(QtmTlComplaintgallerySql.getUpdateSql(qtmTlComplaintgallerySql.getCmgaDbFields(), qtmTlComplaintgallery.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		return qtmTlComplaintgallery;
	}
	
	public QtmTlComplaintgallery delete(QtmTlComplaintgallery qtmTlComplaintgallery)
			throws Exception {
		List<String> sqls = new ArrayList<String>();
		QtmTlComplaintgallerySql qtmTlComplaintgallerySql = new QtmTlComplaintgallerySql();
		try {
			sqls.add(QtmTlComplaintgallerySql.getDeleteSql(qtmTlComplaintgallerySql.getCmgaDbFields(), qtmTlComplaintgallery.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}catch( Exception e){
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return qtmTlComplaintgallery;
	}
	@Override
	public QtmTlComplaintgallery getValues(String keyid)  {
		QtmTlComplaintgallery qtmTlComplaintgallery = new QtmTlComplaintgallery();
		try
		{
			QtmTlComplaintgallerySql qtmTlComplaintgallerySql = new QtmTlComplaintgallerySql();
			String sql = qtmTlComplaintgallerySql.getselectsql();
			Object [] args =  new Object [] { keyid };
			qtmTlComplaintgallery.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			CommonMessage.debugMsg("Sql : "+sql);
			CommonMessage.debugMsg("Keyid : "+keyid);
			
			return  qtmTlComplaintgallery;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return qtmTlComplaintgallery;
	}
	@Override
	public String[] getImgName(String keyid,String fileDir,String imagepath) {
		String[] imagenameArr=new String[2];
		try
		{
			QtmTlComplaintgallerySql qtmTlComplaintgallerySql = new QtmTlComplaintgallerySql();
			String sql = qtmTlComplaintgallerySql.getImgName(keyid);
			String imagename=dbActionTemplate.getSingleValue(sql);
			if(CommonFunctions.isValidKeyId(imagename)) {
			imagenameArr[0]=imagename;
			String refdoctype="CUS";
			String imagetype="CMG";
			CommonMessage.debugMsg(imagename +  " imagename restore");
			CommonMessage.debugMsg(fileDir + "filedir restore ");
			String condSql = " IMFL_REFDOCTYPE = '" + refdoctype+ "' AND IMFL_IMAGETYPE = '" + imagetype+"'";		
			String fileName=imagepath + imagename;
			CommonMessage.debugMsg("filename" + fileName);
			String condSql1 = " AND IMFL_REFKEYID = '" + keyid + "' AND " + condSql ;
			imagename =(fileDir+imagename);	
			
			imagenameArr[1]=imagename;
			dbActionTemplate.restoreFile1("GEN_TL_ALLMODULEIMGFILE", "IMFL_BLOBIMAGE", condSql1, fileName);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return  imagenameArr;
	}
	@Override
	public void deleteImage(String cmgaKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception {
		QtmTlComplaintgallerySql qtmTlComplaintgallerySql = new QtmTlComplaintgallerySql();
		String sql = " DELETE FROM GEN_TL_ALLMODULEIMGFILE WHERE IMFL_REFKEYID='"+cmgaKeyid+"' AND IMFL_REFDOCTYPE='"+refDoc+"' AND IMFL_IMAGETYPE='"+imgType+"'";
		dbActionTemplate.executeStatement(sql);
	}
	@Override
	public Workbook getCompGalExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{				
			rs =   getCompGalResultSet(commonFilter);			 
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );				
		}finally{
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	private ResultSet getCompGalResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("QTM_FN_COMPLAINTGALLARY", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	public String getElementId(String flid) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID ='"+flid+"'";
		CommonMessage.debugMsg(sql);
		String elementId = dbActionTemplate.getSingleValue(sql);
	
		return elementId;
	}
	
	public List<String[]> getCustCompliantCount(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =   fnCallApi.callFunction("QTM_FN_CUSTOMERCOMPLAINT_SB", paramValues,4,false	);
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("QTM_FN_CUSTOMERCOMPLAINT", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}
	public Workbook getCustComplintsCountExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception, SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getCustComplaintsCountResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 3,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	private ResultSet getCustComplaintsCountResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.NewdbFunctionCall2("QTM_FN_CUSTOMERCOMPLAINT", paramValues);
	}	
	
	 public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
	      StringBuffer sql = new StringBuffer();
	      sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
	      sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
	      if (UIUtils.isValidKeyId(loginflid)) {
	         sql.append(" AND FRT_FNLN_KEYID  = '" + loginflid + "' ");
	      }

	      sql.append(" AND FRT_EMPM_KEYID = '" + empId + "'  AND ROLE_LEVEL= '" + loginlevel + "'");
	      Object[] args = new Object[0];
	      CommonMessage.debugMsg(sql);
	      List<String[]> userDatas = this.dbActionTemplate.getDataList(sql.toString(), args);
	      return userDatas;
	   }

}
