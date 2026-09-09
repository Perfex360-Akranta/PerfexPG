package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.DocTlTemplateDefvalDtlDao;
import com.akranta.tpm.dao.sql.DocTlTemplateDefvalDtlSql;
import com.akranta.tpm.model.DocTlTemplateDefvalDtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class DocTlTemplateDefvalDtlDaoImpl implements DocTlTemplateDefvalDtlDao {


	private DBActionTemplate dbActionTemplate; 

	public DocTlTemplateDefvalDtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public DocTlTemplateDefvalDtl create(DocTlTemplateDefvalDtl docTlTemplateDefvalDtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		DocTlTemplateDefvalDtlSql docTlTemplateDefvalDtlSql = new DocTlTemplateDefvalDtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			if(docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl()!= null && docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size()>0) 
			{
				for(int i =0;i<docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().size();i++)
				{
					DocTlTemplateDefvalDtl docTlTemplateValues = docTlTemplateDefvalDtl.getDocTlTemplateDefvalDtl().get(i);
					docTlTemplateValues = fillValues(docTlTemplateValues);
					if(UIUtils.isValidKeyId(docTlTemplateDefvalDtl.getDtpvDtpmKeyid()))
						docTlTemplateValues.setDtpvDtpmKeyid(docTlTemplateDefvalDtl.getDtpvDtpmKeyid());
					if(UIUtils.isValidKeyId(docTlTemplateDefvalDtl.getDtpvDmdmKeyid()))
						docTlTemplateValues.setDtpvDmdmKeyid(docTlTemplateDefvalDtl.getDtpvDmdmKeyid());
					if(UIUtils.isValidKeyId(docTlTemplateDefvalDtl.getDtpvCreatedby()))
						docTlTemplateValues.setDtpvCreatedby(docTlTemplateDefvalDtl.getDtpvCreatedby());
					if(UIUtils.isValidKeyId(docTlTemplateValues.getDtpvKeyid()))
					{
						String modOn = dbActionTemplate.getSingleValue(DocTlTemplateDefvalDtlSql.getModifiedDateSql(docTlTemplateValues.getDtpvKeyid()));
						if(UIUtils.isValidKeyId(modOn))
							docTlTemplateValues.setDtpvModifiedon(modOn);
						sqls.add(DocTlTemplateDefvalDtlSql.getUpdateSql(docTlTemplateDefvalDtlSql.getDtpvDbFields(), docTlTemplateValues.getSaveArray()));
					}
					else
					{
						docTlTemplateValues.setDtpvKeyid(dbActionTemplate.getSequenceNumber(DocTlTemplateDefvalDtlSql.TBL_DOC_TL_TEMPLATE_DEFVAL_DTL, 12, "DTV", "YYMM", "Y")); // set the sequnce number 
						sqls.add(DocTlTemplateDefvalDtlSql.getInsertSql(docTlTemplateDefvalDtlSql.getDtpvDbFields(), docTlTemplateValues.getSaveArray())); // add insert sql for master table
					}
				}
			}

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return docTlTemplateDefvalDtl;
	}
	
	public DocTlTemplateDefvalDtl update(DocTlTemplateDefvalDtl docTlTemplateDefvalDtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		DocTlTemplateDefvalDtlSql docTlTemplateDefvalDtlSql = new DocTlTemplateDefvalDtlSql();
		try {

			sqls.add(DocTlTemplateDefvalDtlSql.getUpdateSql(docTlTemplateDefvalDtlSql.getDtpvDbFields(), docTlTemplateDefvalDtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return docTlTemplateDefvalDtl;
	}
	
	public DocTlTemplateDefvalDtl delete(DocTlTemplateDefvalDtl docTlTemplateDefvalDtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		DocTlTemplateDefvalDtlSql docTlTemplateDefvalDtlSql = new DocTlTemplateDefvalDtlSql();
		try {
			
			sqls.add(DocTlTemplateDefvalDtlSql.getDeleteSql(docTlTemplateDefvalDtlSql.getDtpvDbFields(), docTlTemplateDefvalDtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return docTlTemplateDefvalDtl;
	}
	
	private DocTlTemplateDefvalDtl fillValues(DocTlTemplateDefvalDtl docTlTemplateDefvalDtl)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		docTlTemplateDefvalDtl.setDtpvCreatedon(dateTime);
		docTlTemplateDefvalDtl.setDtpvModifiedon(dateTime);
		docTlTemplateDefvalDtl.setDtpvActive("Y");
	//	docTlTemplateDefvalDtl.setDtpvTempfield("-");
		docTlTemplateDefvalDtl.setDtpvTempfield2("-");
		docTlTemplateDefvalDtl.setDtpvTempfield3("-");
		docTlTemplateDefvalDtl.setDtpvTempfield4("-");
		docTlTemplateDefvalDtl.setDtpvTempfield5("-");
		
		return docTlTemplateDefvalDtl;
	}
	
}

