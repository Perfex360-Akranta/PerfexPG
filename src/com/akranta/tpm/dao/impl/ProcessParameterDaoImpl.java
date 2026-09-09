package com.akranta.tpm.dao.impl;

import java.util.List;

import com.akranta.tpm.dao.ProcessParameterDao;
import com.akranta.tpm.utils.CommonMessage;

public class ProcessParameterDaoImpl implements ProcessParameterDao{
	
private DBActionTemplate dbActionTemplate;
	
	public ProcessParameterDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public List<String[]> getAllProcess() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
	sql.append("SELECT QTMP_KEYID,QTMP_PARAMETER ,QTMS_KEYID ,QTMS_QTMP_KEYID,QTMS_SUBPARAMETER,'','','','','','','' " 
			+" FROM QTM_TL_PARAMETER , QTM_TL_SUBPARAMETER,DUAL" 
			+" where"
			+ " QTMP_KEYID = QTMS_QTMP_KEYID" 
			+" ORDER BY QTMP_KEYID,QTMS_KEYID");
		String sql1="";
		 sql1 = sql.toString();
			CommonMessage.debugMsg("sql..." + sql);
			List<String[]> gridData = dbActionTemplate.getDataList(sql1);
			CommonMessage.debugMsg("Grid value" + gridData);
			return gridData;
	}

}
