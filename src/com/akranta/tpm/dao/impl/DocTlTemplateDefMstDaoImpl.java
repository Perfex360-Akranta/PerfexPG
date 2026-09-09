package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.DocTlTemplateDefMstDao;
import com.akranta.tpm.dao.sql.DocTlTemplateDefDtlSql;
import com.akranta.tpm.dao.sql.DocTlTemplateDefMstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DocTlTemplateDefDtl;
import com.akranta.tpm.model.DocTlTemplateDefMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class DocTlTemplateDefMstDaoImpl implements DocTlTemplateDefMstDao {


	private DBActionTemplate dbActionTemplate; 

	public DocTlTemplateDefMstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public DocTlTemplateDefMst create(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) 	throws Exception {
		DocTlTemplateDefMstSql docTlTemplateDefMstsql = new DocTlTemplateDefMstSql();
		
		List<String> sqls = new ArrayList<String>(); 
		try{
			if(UIUtils.isValidKeyId(newDocTlTemplateDefMst.getDtpmKeyid()))
				
				sqls.add(DocTlTemplateDefMstSql.getUpdateSql(docTlTemplateDefMstsql.getDtpmDbFields(), newDocTlTemplateDefMst.getSaveArray()));
				
			else {
				newDocTlTemplateDefMst.setDtpmKeyid(dbActionTemplate.getSequenceNumber(DocTlTemplateDefMstSql.TBL_DOC_TL_TEMPLATE_DEF_MST, 10, "DTM", "YYMM", "Y")); // set the sequnce number 
				sqls.add(DocTlTemplateDefMstSql.getInsertSql(docTlTemplateDefMstsql.getDtpmDbFields(), newDocTlTemplateDefMst.getSaveArray())); // add insert sql for master table
				}
				
				if(newDocTlTemplateDefMst.getDocTempTlDtl()!=null ){
					DocTlTemplateDefDtlSql docTlTemplateDefDtlSql = new DocTlTemplateDefDtlSql();
					for(int i=0;i<newDocTlTemplateDefMst.getDocTempTlDtl().size();i++){
						DocTlTemplateDefDtl docTlTemplateDefDtl=newDocTlTemplateDefMst.getDocTempTlDtl().get(i);
						filldocTempDtlvalues(docTlTemplateDefDtl,newDocTlTemplateDefMst);
					
						if(UIUtils.isValidKeyId(docTlTemplateDefDtl.getDtpdKeyid())){
							CommonMessage.debugMsg("for loop"+newDocTlTemplateDefMst.getDocTempTlDtl().size());
							sqls.add(DocTlTemplateDefDtlSql.getUpdateSql(docTlTemplateDefDtlSql.getDtpdDbFields(), docTlTemplateDefDtl.getSaveArray()));
						}
						else{
							CommonMessage.debugMsg("for loop else"+newDocTlTemplateDefMst.getDocTempTlDtl().size());
							docTlTemplateDefDtl.setDtpdKeyid(dbActionTemplate.getSequenceNumber(DocTlTemplateDefDtlSql.TBL_DOC_TL_TEMPLATE_DEF_DTL, 10, "DTD", "YYMM", "Y"));
							sqls.add(DocTlTemplateDefDtlSql.getInsertSql(docTlTemplateDefDtlSql.getDtpdDbFields(), docTlTemplateDefDtl.getSaveArray())); // add insert sql for master table
						}
						}
					
				}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newDocTlTemplateDefMst;
	}
	
	private DocTlTemplateDefDtl filldocTempDtlvalues(DocTlTemplateDefDtl docTlTemplateDefDtl,DocTlTemplateDefMst newDocTlTemplateDefMst) {
		
		if(docTlTemplateDefDtl.getDtpdActive()==null)
			docTlTemplateDefDtl.setDtpdActive("Y");
		if(docTlTemplateDefDtl.getDtpdCreatedon()==null)
			docTlTemplateDefDtl.setDtpdCreatedon(CommonFunctions.dateTimeNow());
		if(docTlTemplateDefDtl.getDtpdKeyword()==null)
			docTlTemplateDefDtl.setDtpdKeyword("{}");
		if(docTlTemplateDefDtl.getDtpdModifiedon()==null)
			docTlTemplateDefDtl.setDtpdModifiedon(CommonFunctions.dateTimeNow());
		if(docTlTemplateDefDtl.getDtpdTempfield()==null)
			docTlTemplateDefDtl.setDtpdTempfield("-");
		if(docTlTemplateDefDtl.getDtpdTempfield2()==null)
			docTlTemplateDefDtl.setDtpdTempfield2("-");
		if(docTlTemplateDefDtl.getDtpdTempfield3()==null)
			docTlTemplateDefDtl.setDtpdTempfield3("-");
		if(docTlTemplateDefDtl.getDtpdTempfield4()==null)
			docTlTemplateDefDtl.setDtpdTempfield4("-");
		if(docTlTemplateDefDtl.getDtpdTempfield5()==null)
			docTlTemplateDefDtl.setDtpdTempfield5("-");
		if(docTlTemplateDefDtl.getDtpdType()==null)
			docTlTemplateDefDtl.setDtpdType("{}");
		if(!UIUtils.isValidKeyId(docTlTemplateDefDtl.getDtpdDtpmKeyid()))
			docTlTemplateDefDtl.setDtpdDtpmKeyid(newDocTlTemplateDefMst.getDtpmKeyid());
		return docTlTemplateDefDtl;
		
		
	}

	public DocTlTemplateDefMst update(DocTlTemplateDefMst docTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		DocTlTemplateDefMstSql docTlTemplateDefMstSql = new DocTlTemplateDefMstSql();
		try {

			sqls.add(DocTlTemplateDefMstSql.getUpdateSql(docTlTemplateDefMstSql.getDtpmDbFields(), docTlTemplateDefMst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return docTlTemplateDefMst;
	}
	
	public DocTlTemplateDefMst delete(DocTlTemplateDefMst docTlTemplateDefMst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		DocTlTemplateDefMstSql docTlTemplateDefMstsql = new DocTlTemplateDefMstSql();
		try {
			
			sqls.add(docTlTemplateDefMstsql.getDeleteSql(docTlTemplateDefMstsql.getDtpmDbFields(), docTlTemplateDefMst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return docTlTemplateDefMst;
	}

	@Override
	public List<String[]> getdocTempList(CommonFilter commonFilter, String type) {
		try{
	
		String sql=null;
		sql="select DTPD_KEYID,DTPD_KEYWORD ,DTPD_TYPE from DOC_TL_TEMPLATE_DEF_DTL where DTPD_DTPM_KEYID= '"+ type +"'";
		CommonMessage.debugMsg("sqls of doc"+sql);	
	
		return dbActionTemplate.getDataList(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;	 
	}

	@Override
	public DocTlTemplateDefMst deleteDocTempData(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefDtl newDocTlTemplateDefdtl) throws Exception {
		List<String> sqls = new ArrayList<String>();
		
		DocTlTemplateDefDtlSql docTlTemplateDefDtlSql = new DocTlTemplateDefDtlSql();
		
		
		try {
			
			CommonMessage.debugMsg("Sql.,:--"+newDocTlTemplateDefdtl.getDtpdKeyid());
			String id=newDocTlTemplateDefMst.getDtpmKeyid();
			String count = dbActionTemplate.getSingleValue(docTlTemplateDefDtlSql.getKeiIdCountSql(newDocTlTemplateDefMst.getDtpmKeyid()));
			CommonMessage.debugMsg("count "+count+"  length..:"+count.length());
			if(UIUtils.isValidKeyId(count)){
				if(count.length()>0){
				sqls.add("delete from DOC_TL_TEMPLATE_DEF_DTL where DTPD_DTPM_KEYID    = '"+ id +"'");
				}
			}
			
			sqls.add("delete from DOC_TL_TEMPLATE_DEF_MST where DTPM_KEYID = '"+ id +"'");
			
		
			CommonMessage.debugMsg("Inside dao impl else"+sqls);
			
				//sqls.add(docTlTemplateDefDtlSql.getDeleteSql(docTlTemplateDefDtlSql.getDtpdDbFields(), newDocTlTemplateDefdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newDocTlTemplateDefMst;
		
	}

	@Override
	public DocTlTemplateDefDtl deleteDocTemp(DocTlTemplateDefDtl doctempdtl) throws BusinessApplicationExceptions, Exception {
		
		List<String> sqls = new ArrayList<String>();
		try{
		String id=doctempdtl.getDtpdKeyid();
		sqls.add("delete from DOC_TL_TEMPLATE_DEF_DTL where DTPD_KEYID    = '"+ id +"'");
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		dbActionTemplate.executeStatements(sqls);
		return doctempdtl;
	}

	@Override
	public List<String[]> getdocumentTempList(CommonFilter commonFilter,String keyId) {
		try{
			
			String sql=null;
			sql="select DTPM_KEYID  ,DTPM_DOCUMENTTYPE  from DOC_TL_TEMPLATE_DEF_MST where DTPM_ACTIVE= 'Y' ";
			  sql+= FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ;
			CommonMessage.debugMsg("sqls of doc"+sql);	
		
			return dbActionTemplate.getDataList(sql);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;	
	
}

	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		DocTlTemplateDefMstSql docTlTemplateDefMstsql = new DocTlTemplateDefMstSql();
		String sql = docTlTemplateDefMstsql.getCountAll(commonFilter);
		
		String returnData = dbActionTemplate.getSingleValue(sql);
		int retData = Integer.parseInt(returnData);
		CommonMessage.debugMsg(retData);
		return  retData;
	}

	@Override
	public DocTlTemplateDefMst getdocTempGridList(String keyId) throws NoDataFoundException, SQLException, Exception {
	
		DocTlTemplateDefMstSql docTlTemplateDefMstsql = new DocTlTemplateDefMstSql();
		DocTlTemplateDefMst newDocTlTemplateDefMst = new DocTlTemplateDefMst();
			String sql=docTlTemplateDefMstsql.getDocTempGrid(keyId);
			CommonMessage.debugMsg("sql..:"+sql);
			Object args[] = new Object[] {keyId};
			newDocTlTemplateDefMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			
			return newDocTlTemplateDefMst;	
	}

	/*
	 * @Override public List<String[]> getDocData(String documentNo, String
	 * documentType) throws Exception { String sql=null; try{
	 * CommonMessage.debugMsg("getDocData DaoImpl.....");
	 * 
	 * sql="SELECT DMDM_KEYID,'','','',DMDM_REFDOCNO,DMDM_REFDOCTYPE,DMDM_ISODOCTYPE,DMDM_SLNO ,"
	 * ; sql+
	 * =" DMDM_KEYWORDS,DMDM_CATEGORY,DMDM_OWNER,DMDM_APPROVEDBY,DMDM_SUBJECTAREA";
	 * sql+=",DMDM_TITLE,DMDM_TYPE,DMDM_FILENAME,DMCM_NAME,DMDM_DESCRIPTION  "; sql+
	 * =",TO_CHAR(DMDM_CREATEDON,'DD-MON-YYYY'),TO_CHAR(DMDM_MODIFIEDON,'DD-MON-YYYY') "
	 * ; sql+
	 * =" FROM DCM_TL_DOCUMENTMANAGER ,DCM_TL_CATEGORYMST WHERE DMCM_KEYID(+)= DMDM_CATEGORY"
	 * ; sql+= " AND DMDM_REFDOCNO = '" + documentNo +
	 * "'  AND  upper(DMDM_REFDOCTYPE) = upper('" + documentType + "')";
	 * CommonMessage.debugMsg("sqls of doc"+sql);
	 * 
	 * //sb.append(" where " + " FLMN_REFDOCNO  = '" + documentNo + "'");
	 * //sb.append(" AND FLMN_REFDOCTYPE   = '" + documentType + "'
	 * 
	 * //return dbActionTemplate.getDataList(sql); }catch(Exception e){
	 * e.printStackTrace(); } return dbActionTemplate.getDataList(sql); }
	 */
	
	
	@Override
	public List<String[]> getDocData(String documentNo, String documentType) throws Exception {
	    String sql = null;
	    try {
	        CommonMessage.debugMsg("getDocData DaoImpl.....");

	        // Keep behavior but make the SQL safe to embed
	        String docNoSafe   = documentNo   == null ? "" : documentNo.replace("'", "''");
	        String docTypeSafe = documentType == null ? "" : documentType.replace("'", "''");

	        StringBuilder sb = new StringBuilder();
	        sb.append("SELECT ");
	        sb.append(" DMDM_KEYID,'','','',"); // padding columns as in your original
	        sb.append(" DMDM_REFDOCNO,DMDM_REFDOCTYPE,DMDM_ISODOCTYPE,DMDM_SLNO,");
	        sb.append(" DMDM_KEYWORDS,DMDM_CATEGORY,DMDM_OWNER,DMDM_APPROVEDBY,DMDM_SUBJECTAREA,");
	        sb.append(" DMDM_TITLE,DMDM_TYPE,DMDM_FILENAME,");
	        sb.append(" DCM_TL_CATEGORYMST.DMCM_NAME,"); // qualified to avoid ambiguity
	        sb.append(" DMDM_DESCRIPTION,");
	        sb.append(" TO_CHAR(DMDM_CREATEDON,'DD-MON-YYYY'),");
	        sb.append(" TO_CHAR(DMDM_MODIFIEDON,'DD-MON-YYYY') ");
	        sb.append("FROM DCM_TL_DOCUMENTMANAGER ");
	        sb.append("LEFT JOIN DCM_TL_CATEGORYMST ");
	        sb.append("  ON DCM_TL_CATEGORYMST.DMCM_KEYID = DCM_TL_DOCUMENTMANAGER.DMDM_CATEGORY ");
	        sb.append("WHERE DMDM_REFDOCNO = '").append(docNoSafe).append("' ");
	        sb.append("  AND UPPER(DMDM_REFDOCTYPE) = UPPER('").append(docTypeSafe).append("')");

	        sql = sb.toString();
	        CommonMessage.debugMsg("sqls of doc" + sql);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dbActionTemplate.getDataList(sql);
	}
}
