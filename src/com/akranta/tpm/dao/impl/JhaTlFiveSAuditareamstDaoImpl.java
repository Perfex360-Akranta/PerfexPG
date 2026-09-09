package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.AuditAreaBean;
import com.akranta.tpm.dao.JhaTlFiveSAuditareamstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.JhaTlFiveSAuditareamstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;
 
 public class JhaTlFiveSAuditareamstDaoImpl implements JhaTlFiveSAuditareamstDao {


	private DBActionTemplate dbActionTemplate; 

	public JhaTlFiveSAuditareamstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	@Override
	public List<String[]> getAllFiveSAuditareamaster(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		//String sql="select *  from JHA_TL_FIVE_S_AUDITAREAMST";
		String sql="Select FVAS_KEYID,fvas_areaname,Empm_Name,decode(fvas_areatype,'S','SHOP','O','OFFICE'),crcm_name";
		sql+=" FROM gen_tl_employeemst,JHA_TL_FIVE_S_AUDITAREAMST,gen_tl_circlemst";
        sql+=" Where Fvas_Responsibiltyid = Empm_Keyid(+) and fvas_circleid=crcm_keyid(+) ";
        sql+= FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ;
	    CommonMessage.debugMsg("sql..."+sql);
        List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData ;
		
		
	}
     
	@Override
	public JhaTlFiveSAuditareamst getAllFiveSAuditarea(String auditKeyId) throws Exception{
		// TODO Auto-generated method stub
		JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst = new JhaTlFiveSAuditareamst();
		String sql = JhaTlFiveSAuditareamstSql.getauditdata();
		Object args[] = new Object[] {auditKeyId};
		newJhaTlFiveSAuditareamst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return newJhaTlFiveSAuditareamst;
		
	}

	public JhaTlFiveSAuditareamst create(JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlFiveSAuditareamstSql  jhaTlFiveSAuditareamstSql= new JhaTlFiveSAuditareamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		
			CommonMessage.debugMsg("JhaTlFiveSAuditareamstSql");
			//jhaTlFiveSAuditareamst.setFvasKeyid(dbActionTemplate.getSequenceNumber(tableName, keyLength, preFix, dateFormat, formatReset)
			newJhaTlFiveSAuditareamst.setFvasKeyid(dbActionTemplate.getSequenceNumber("JHA_TL_FIVE_S_AUDITAREAMST", 8, "FVA", "", "Y" ));
			newJhaTlFiveSAuditareamst.setFvasParentid(newJhaTlFiveSAuditareamst.getFvasKeyid());
			//jhaTlFiveSAuditareamst.setFvasKeyid(dbActionTemplate.getSequenceNumber(JhaTlFiveSAuditareamstSql.TBL_JHA_TL_FIVE_S_AUDITAREAMST)); // set the sequnce number
			CommonMessage.debugMsg("key"+newJhaTlFiveSAuditareamst.getFvasKeyid());
			sqls.add(JhaTlFiveSAuditareamstSql.getInsertSql(jhaTlFiveSAuditareamstSql.getFvasDbFields(), newJhaTlFiveSAuditareamst.getSaveArray())); // add insert sql for master table
			//newJhaTlFiveSAuditareamst.setFvasKeyid(dbActionTemplate.getSequenceNumber(tableName, keyLength, preFix, dateFormat, formatReset)

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		
		return newJhaTlFiveSAuditareamst;
	}
	
	public JhaTlFiveSAuditareamst update(JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlFiveSAuditareamstSql jhaTlFiveSAuditareamstSql = new JhaTlFiveSAuditareamstSql();
		try {
			//newJhaTlFiveSAuditareamst.setFvasKeyid(dbActionTemplate (JhaTlFiveSAuditareamstSql.TBL_JHA_TL_FIVE_S_AUDITAREAMST));
			
			newJhaTlFiveSAuditareamst.setFvasParentid(newJhaTlFiveSAuditareamst.getFvasKeyid());
			sqls.add(JhaTlFiveSAuditareamstSql.getUpdateSql(jhaTlFiveSAuditareamstSql.getFvasDbFields(), newJhaTlFiveSAuditareamst.getSaveArray()));
			
			 
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newJhaTlFiveSAuditareamst;
	}
	

	@SuppressWarnings("static-access")
	public JhaTlFiveSAuditareamst delete(JhaTlFiveSAuditareamst jhaTlFiveSAuditareamst)
	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlFiveSAuditareamstSql jhaTlFiveSAuditareamstSql = new JhaTlFiveSAuditareamstSql();
		
			CommonMessage.debugMsg("sql.......");
			sqls.add(JhaTlFiveSAuditareamstSql.getDeleteSql(jhaTlFiveSAuditareamstSql.getFvasDbFields(), jhaTlFiveSAuditareamst.getSaveArray()));
			CommonMessage.debugMsg("sql......."+sqls);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			dbActionTemplate.executeStatement(JhaTlFiveSAuditareamstSql.getDeleteSql(jhaTlFiveSAuditareamstSql.getFvasDbFields(), jhaTlFiveSAuditareamst.getSaveArray()));
			
		
		return jhaTlFiveSAuditareamst;
	}

	@Override
	public Workbook getFivesauditExcel(JSONObject colmodel, String rptFormat,CommonFilter commonFilter) throws Exception {
		       ResultSet rs = null;
		   try{
			
			rs =   getFivesauditResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getFivesauditResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		//List<String> paramValues = getFilterParamValues();
		String sql="Select FVAS_KEYID,fvas_areaname,Empm_Name,decode(fvas_areatype,'S','SHOP','O','OFFICE'),crcm_name";
		sql+=" FROM gen_tl_employeemst,JHA_TL_FIVE_S_AUDITAREAMST,gen_tl_circlemst";
        sql+=" Where Fvas_Responsibiltyid = Empm_Keyid(+) and fvas_circleid=crcm_keyid(+) ";
        sql+= FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ;
	    CommonMessage.debugMsg("sql..."+sql);
       
		return dbActionTemplate.getData(sql);
	//	return null;
		//return null;
	}

	public List<String[]> getAllFiveSAuditarea(CommonFilter commonFilter) throws Exception
	{
		return null;
	}

	
	
 /*	@Override
	public JhaTlFiveSAuditareamst create(
			JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst,
			JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst,
			AuditAreaBean auditareabean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JhaTlFiveSAuditareamst update(
			JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst,
			JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst,
			AuditAreaBean auditareabean) {
		// TODO Auto-generated method stub
		return null;
	}
 */
	
	
	
}

