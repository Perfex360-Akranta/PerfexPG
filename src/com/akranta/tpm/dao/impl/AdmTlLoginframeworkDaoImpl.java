package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AdmTlLoginframeworkDao;
import com.akranta.tpm.dao.sql.AdmTlLoginframeworkSql;
import com.akranta.tpm.dao.sql.AdmTlUsermstSql;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class AdmTlLoginframeworkDaoImpl implements AdmTlLoginframeworkDao {


	private DBActionTemplate dbActionTemplate; 

	public AdmTlLoginframeworkDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public AdmTlLoginframework create(AdmTlLoginframework admTlLoginframework) 	throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AdmTlLoginframeworkSql admTlLoginframeworksql = new AdmTlLoginframeworkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			
			
			sqls.add("UPDATE ADM_TL_LOGINFRAMEWORK SET LGFR_ACTIVE ='N' where LGFR_ACTIVE = 'Y' ");
			
		//	sqls.add("UPDATE ADM_TL_USERMST SET USRM_PASSWORD ='"+ admTlLoginframework.getLgfrDefsyspassword() +"' where USRM_DEFAULTPASSWORD = USRM_PASSWORD ");
			
		//	sqls.add("UPDATE ADM_TL_USERMST SET USRM_DEFAULTPASSWORD ='"+ admTlLoginframework.getLgfrDefsyspassword() +"' where USRM_DEFAULTPASSWORD <> '"+ admTlLoginframework.getLgfrDefsyspassword() +"' ");
			
			admTlLoginframework.setLgfrKeyid(dbActionTemplate.getSequenceNumber(AdmTlLoginframeworkSql.TBL_ADM_TL_LOGINFRAMEWORK, 8, "SPF", "DDMM", "Y")); 
			sqls.add(AdmTlLoginframeworkSql.getInsertSql(admTlLoginframeworksql.getLgfrDbFields(), admTlLoginframework.getSaveArray())); 
						
			
		
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return admTlLoginframework;
	}
	
	public AdmTlLoginframework update(AdmTlLoginframework admTlLoginframework)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlLoginframeworkSql admTlLoginframeworkSql = new AdmTlLoginframeworkSql();
		try {

			sqls.add(AdmTlLoginframeworkSql.getUpdateSql(admTlLoginframeworkSql.getLgfrDbFields(), admTlLoginframework.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return admTlLoginframework;
	}
	
	public AdmTlLoginframework delete(AdmTlLoginframework admTlLoginframework)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		AdmTlLoginframeworkSql admTlLoginframeworksql = new AdmTlLoginframeworkSql();
		try {
			
			sqls.add(AdmTlLoginframeworkSql.getDeleteSql(admTlLoginframeworksql.getLgfrDbFields(), admTlLoginframework.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlLoginframework;
	}
	public static String getSql(String statusType)
	{
		String sql="";
		List<String > paramValues = new ArrayList<String>();
		if(statusType.equals("U")){
			sql="select ' ',USRM_KEYID ,EMPM_NAME,EMPM_CODE ,USRM_LOGINID ,decode(USRM_ISACTIVE,'Y','Active','N','InActive') from ADM_TL_USERMST,gen_tl_employeemst where USRM_CCNO=EMPM_KEYID";
		}
		else if(statusType.equals("A")){
			 sql="select ' ',USRM_KEYID ,EMPM_NAME,EMPM_CODE ,USRM_LOGINID ,decode(USRM_ISACTIVE,'Y','Active','N','InActive') from ADM_TL_USERMST,gen_tl_employeemst where USRM_ISACTIVE='Y' AND USRM_CCNO=EMPM_KEYID";	
		}
		else{
			 sql="select ' ',USRM_KEYID,EMPM_NAME,EMPM_CODE ,USRM_LOGINID ,decode(USRM_ISACTIVE,'Y','Active','N','InActive') from ADM_TL_USERMST,gen_tl_employeemst where USRM_ISACTIVE='N' AND USRM_CCNO=EMPM_KEYID";
		}
		return sql;
	}
	@Override
	public List<String[]> getReleaseGrid(CommonFilter commonFilter,String statusType) throws Exception {
		
		
		List<String[]> Releasedata = dbActionTemplate.getDataList(getSql(statusType));	
		return Releasedata;
	}

	@Override
	public List<AdmTlUsermst> Update(AdmTlUsermst newAdmTlUsermst,List<AdmTlUsermst> newadmTlUsermst2,String statusType) {
		List<String> sqls = new ArrayList<String>();
		String sql="";
		AdmTlUsermstSql admTlUsermstSql = new AdmTlUsermstSql();
		try {
			String DefPasswrd=dbActionTemplate.getSingleValue("select LGFR_DEFSYSPASSWORD from adm_tl_loginframework");
			for(int i=0;i<newadmTlUsermst2.size();i++){
				
				String keyid=	newadmTlUsermst2.get(i).getUsrm_keyid();
				if(statusType.equals("U")){
					sql="update adm_tl_usermst set USRM_PASSWORD='"+DefPasswrd+"' where USRM_KEYID='"+keyid+"' ";
				}
				else if(statusType.equals("A")){
					 sql="update adm_tl_usermst set USRM_PASSWORD='"+DefPasswrd+"',USRM_ISACTIVE='N' where USRM_KEYID='"+keyid+"' ";
				}
				else if(statusType.equals("I")){
					 sql="update adm_tl_usermst set USRM_PASSWORD='"+DefPasswrd+"',USRM_ISACTIVE='Y' where USRM_KEYID='"+keyid+"' ";
				}
				CommonMessage.debugMsg("SQL:"+sql);
			sqls.add(sql);
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return newadmTlUsermst2;
		
	}

	@Override
	public Workbook getReleasLockedAccountExcel(JSONObject colmodel,String rptFormat, CommonFilter commonFilter,String statusType) throws Exception {
		ResultSet rs = null;
		  try{
			rs =   getReleaselockResultSet(commonFilter,statusType);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getReleaselockResultSet(CommonFilter commonFilter,String statusType) throws Exception {
		return dbActionTemplate.getData(getSql(statusType));
	}
	@Override
	public AdmTlLoginframework ChangeRleasePassrd(AdmTlLoginframework loginframework,String defpasswrd) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		try {
			String keyid=dbActionTemplate.getSingleValue("select LGFR_KEYID from adm_tl_loginframework");
			
			sqls.add("UPDATE ADM_TL_LOGINFRAMEWORK  SET LGFR_DEFSYSPASSWORD = '"+loginframework.getLgfrDefsyspassword()+"' WHERE LGFR_KEYID  = '"+keyid+"'");
			dbActionTemplate.executeStatements(sqls);
			return loginframework;
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public List<String[]> getloginframeworkgrid() throws Exception {
		 CommonMessage.debugMsg("Inside Dao IMPL");
		StringBuilder sql = new StringBuilder();
	/*	 sql.append( " select 'Keyid','User Name Length','Default password','Min Password Length','Max Password Length','No of Alphabets', ");
		 sql.append( " 'No of Numbers','Password ChangeGap','Password ChangeFreq','Password GracePeriod','Password Intimation','Password NeverExp', " ) ;
		 sql.append(  " 'Password HistoryRemember','Min Password Change','No of Faild Login Attempts','Is Login Audit','Is Pass Audit','Is Private Audit', " );
		 sql.append(  " 'Created Date' , 'IsPassword AutoGen' from dual " );
		 sql.append(  " union " );
		 sql.append(  " select LGFR_KEYID,to_char(LGFR_USERNAMELENGTH),to_char(LGFR_DEFSYSPASSWORD),to_char(LGFR_MINPASSLENGTH),to_char(LGFR_MAXPASSLENGTH),to_char(LGFR_ALPHABETS),");
		 sql.append(  " to_char(LGFR_NUMERALS),to_char(LGFR_PASSCHANGEGAP),to_char(LGFR_PASSCHANGEFREQ),to_char(LGFR_PASSGRACEPERIOD),to_char(LGFR_PASSINTIMATION),to_char(LGFR_PASSNEVEREXPIRES), " ) ;
		 sql.append(  " to_char(LGFR_PASSHISTORYREMEMBER),to_char(LGFR_MINCHARPASSCHANGE),to_char(LGFR_FAILEDLOGINATTEMPTS),to_char(LGFR_ISLOGINAUDIT),to_char(LGFR_ISPASSAUDIT), " );
		 sql.append(  " to_char(LGFR_ISPRIVAUDIT),to_char(LGFR_CREATEDDATE) ,LGFR_ISPASSAUTOGEN ");
		 sql.append(  " from ADM_TL_LOGINFRAMEWORK WHERE LGFR_ACTIVE='Y' " );						 
		*/
		 sql.append( "	  SELECT ");
		 sql.append( "'Keyid',");
		 sql.append( "'User Name Length',");
		 sql.append( "'Default password',");
		 sql.append( "'Min Password Length',");
		 sql.append( " 'Max Password Length', ");
		 sql.append( " 'No of Alphabets', ");
		 sql.append( "  'No of Numbers', ");
		 sql.append( "  'Password ChangeGap', ");
		 sql.append( " 'Password ChangeFreq', ");
		 sql.append( " 'Password GracePeriod', ");
		 sql.append( " 'Password Intimation', ");
		 sql.append( " 'Password NeverExp', ");
		 sql.append( " 'Password HistoryRemember', ");
		 sql.append( " 'Min Password Change', ");
		 sql.append( " 'No of Faild Login Attempts', ");
		 sql.append( " 'Is Login Audit', ");
		 sql.append( " 'Is Pass Audit', ");
		 sql.append( " 'Is Private Audit', ");
		 sql.append( " 'Created Date', ");
		 sql.append( " 'IsPassword AutoGen' ");

		 sql.append( " UNION ALL ");

		 sql.append( " SELECT ");
		 sql.append( "  LGFR_KEYID::text,");
		 sql.append( " LGFR_USERNAMELENGTH::text,");
		 sql.append( " LGFR_DEFSYSPASSWORD::text,");
		 sql.append( " LGFR_MINPASSLENGTH::text,");
		 sql.append( " LGFR_MAXPASSLENGTH::text,");
		 sql.append( " LGFR_ALPHABETS::text,");
		 sql.append( " LGFR_NUMERALS::text,");
		 sql.append( " LGFR_PASSCHANGEGAP::text,");
		 sql.append( " LGFR_PASSCHANGEFREQ::text,");
		 sql.append( " LGFR_PASSGRACEPERIOD::text,");
		 sql.append( " LGFR_PASSINTIMATION::text,");
		 sql.append( " LGFR_PASSNEVEREXPIRES::text,");
		 sql.append( " LGFR_PASSHISTORYREMEMBER::text,");
		 sql.append( " LGFR_MINCHARPASSCHANGE::text,");
		 sql.append( " LGFR_FAILEDLOGINATTEMPTS ::text,");
		 sql.append( " LGFR_ISLOGINAUDIT::text,");
		 sql.append( " LGFR_ISPASSAUDIT::text,");
		 sql.append( " LGFR_ISPRIVAUDIT::text,");
		 sql.append( " LGFR_CREATEDDATE::text,");
		 sql.append( " LGFR_ISPASSAUTOGEN::text");
		 sql.append( " FROM ");
		 sql.append( " ADM_TL_LOGINFRAMEWORK WHERE  LGFR_ACTIVE = 'Y' ");
		 CommonMessage.debugMsg("Gridsql" +  sql.toString());
		
		List<String[]> datalist = dbActionTemplate.getDataList(sql.toString());		
		
		return datalist;
	}

	@Override
	public AdmTlLoginframework getloginframwworkdata() throws Exception {
					
		AdmTlLoginframework admTlLoginframework = new AdmTlLoginframework();
		String sql = AdmTlLoginframeworkSql.getsingledata();
		Object args[] = new Object[] {"Y"};
		admTlLoginframework.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return admTlLoginframework;
	}

	@Override
	public String passwordhistory(String userkeyid,String Password) throws Exception {
		
		
		String sql = AdmTlUsermstSql.checkpasswordhistory();
		String historty = "";
		Object args[] = new Object[] {userkeyid , Password };
		List<String[]> datalist = dbActionTemplate.getDataList(sql, args);
		if (datalist.size()>0)
		{
			historty = datalist.get(0)[0];
		}
		
		return historty;
	}
	
	
	
	
}

