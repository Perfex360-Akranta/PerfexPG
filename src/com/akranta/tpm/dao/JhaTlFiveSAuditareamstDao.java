package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.AuditAreaBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;

public interface JhaTlFiveSAuditareamstDao {

	
	public List<String[]> getAllFiveSAuditarea(CommonFilter commonFilter) throws Exception;
	/*public JhaTlFiveSAuditareamst create(
			JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst,
			JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst,
			AuditAreaBean auditareabean);
	
	public JhaTlFiveSAuditareamst update(
			JhaTlFiveSAuditareamst newJhaTlFiveSAuditareamst,
			JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst,
			AuditAreaBean auditareabean);*/
	public abstract JhaTlFiveSAuditareamst create(JhaTlFiveSAuditareamst jhaTlFiveSAuditareamst) throws Exception;
	public abstract JhaTlFiveSAuditareamst update(JhaTlFiveSAuditareamst jhaTlFiveSAuditareamst) throws Exception;
    public abstract JhaTlFiveSAuditareamst delete(JhaTlFiveSAuditareamst jhaTlFiveSAuditareamst) throws Exception;
	public JhaTlFiveSAuditareamst getAllFiveSAuditarea(String auditKeyId) throws Exception;
	public Workbook getFivesauditExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws IOException, SQLException, Exception;
	public List<String[]> getAllFiveSAuditareamaster(CommonFilter commonFilter)
	throws Exception;

}

