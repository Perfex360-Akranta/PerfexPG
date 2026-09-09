package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.MailDao;
import com.akranta.tpm.dao.sql.SqlUtils;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Mail;
import com.akranta.tpm.utils.PrjConstants;


public class MailDaoImpl implements MailDao{
	
	private DBActionTemplate dbActionTemplate; 
	
	public MailDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate =  dbActionTemplate;
	}
	public void getMailInfo(Mail mail)throws Exception
	{
		List<String[]> mailDetails = new ArrayList<String[]>();
		String sql = getEntMailInfoSql();		
		mailDetails = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(mailDetails.get(0)[0]);
		CommonMessage.debugMsg(mailDetails.get(0)[1]);
		
		mail.setUsername(mailDetails.get(0)[0]);
		mail.setPassword(mailDetails.get(0)[1]);
		mail.setHost(mailDetails.get(0)[2]);		
		mail.setPort(mailDetails.get(0)[3]);
		mail.setFromaddress(mailDetails.get(0)[4]);
		
		//return mailDetails;
	}
	private String getEntMailInfoSql() {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT ");
		sql.append(" (SELECT CNFM_SETTINGVALUE FROM  "+TableNames.TBL_ADM_TL_CONFIGURATIONMST+" WHERE  CNFM_CODE ='"+PrjConstants.ENT_MAIL_USERNAME_CODE+"')  EMAILUSR, ");
		sql.append(" (SELECT CNFM_SETTINGVALUE FROM  "+TableNames.TBL_ADM_TL_CONFIGURATIONMST+" WHERE  CNFM_CODE ='"+PrjConstants.ENT_MAIL_PWD_CODE+"')  EMAILPWD, ");
		sql.append(" (SELECT CNFM_SETTINGVALUE FROM  "+TableNames.TBL_ADM_TL_CONFIGURATIONMST+" WHERE  CNFM_CODE ='"+PrjConstants.ENT_MAIL_HOST_CODE+"') EMAILHOST, ");
		sql.append(" (SELECT CNFM_SETTINGVALUE FROM  "+TableNames.TBL_ADM_TL_CONFIGURATIONMST+" WHERE  CNFM_CODE ='"+PrjConstants.ENT_MAIL_PORT_CODE+"')  EMAILPORT, ");
		sql.append(" (SELECT CNFM_SETTINGVALUE FROM  "+TableNames.TBL_ADM_TL_CONFIGURATIONMST+" WHERE  CNFM_CODE ='"+PrjConstants.ENT_MAIL_FROM_ADDRESS+"')  FROMADDRESS ");
		sql.append(" FROM DUAL ");
		
		return sql.toString();
	}

}
