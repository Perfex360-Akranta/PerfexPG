package com.akranta.tpm.dao.impl;
/*
 * Author Prasanth
 */

import java.sql.Connection;
import java.sql.ResultSet;

import oracle.net.ns.DataDescriptorPacket;

import org.apache.commons.lang.RandomStringUtils;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdmTlUsermstDao;
import com.akranta.tpm.dao.sql.AdmTlPwdhistorySql;
import com.akranta.tpm.dao.sql.AdmTlUsercustompagesSql;
import com.akranta.tpm.dao.sql.AdmTlUsermstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlPwdhistory;
import com.akranta.tpm.model.AdmTlUsercustompages;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.api.UserCreationServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class AdmTlUsermstDaoImpl implements AdmTlUsermstDao {


	private DBActionTemplate dbActionTemplate; 
	private AdmTlPwdhistorySql admTlPwdhistorySql;
	FunctionCallApi fnCallApi;
	UserCreationServiceApi userCreationServiceApi;
	String comboFetchCount="100";
	public AdmTlUsermstDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		this.dbActionTemplate = dbActionTemplate;
		admTlPwdhistorySql = new AdmTlPwdhistorySql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void AdmTlUsermstDaoImplJwt(String JwtToken) 
	{
		try{
			 
			 userCreationServiceApi = new UserCreationServiceApi(JwtToken);	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public DBActionTemplate getDbActionTemplate() {
		return this.dbActionTemplate;
	}
	
	
	
	public AdmTlUsermst create(AdmTlUsermst admTlUsermst) 	throws Exception {

		
		CommonMessage.debugMsg("User password "+admTlUsermst.getUsrm_password());
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AdmTlPwdhistory  admTlPwdhistory = new AdmTlPwdhistory();
		AdmTlUsermstSql admTlUsermstSql = new AdmTlUsermstSql(); // contains dbtable,field names, Field types and related sqls  of master table  
		try{
		    String keyid=dbActionTemplate.getSequenceNumber(AdmTlUsermstSql.TBL_ADM_TL_USERMST);
			admTlUsermst.setUsrm_keyid(keyid); // set the sequnce number
			String pin=keyid.substring(3);
			
			int pinNumber=Integer.parseInt(pin);
			
			admTlUsermst.setUsrm_userpin(Integer.toString(pinNumber));
			String pin1=admTlUsermst.getUsrm_userpin();
			;
			CommonMessage.debugMsg(admTlUsermst.getUsrm_password()+"USERM PASSWORD IN DAO IMPL");
			//String Password=admTlUsermst.getUsrm_loginid();
			String Password=admTlUsermst.getUsrm_password();
			Password=encriptPassword(Password,pinNumber);
			//Password=encriptPassword("pspd@123",1234);
			CommonMessage.debugMsg("Encrypted Passwod is"+Password);
			admTlUsermst.setUsrm_password(Password);
			
		/*	String defPassword=admTlUsermst.getUsrm_defaultpassword();
			CommonMessage.debugMsg("defPassword:"+defPassword);
			defPassword=encriptPassword(defPassword,pinNumber);*/
			admTlUsermst.setUsrm_defaultpassword(Password);
			
			/*String loginpassword = encriptPassword(admTlUsermst.getUsrm_loginid(),pinNumber);
			
			admTlUsermst.setUsrm_password(loginpassword) ;
			admTlUsermst.setUsrm_defaultpassword(loginpassword) ;*/
			
			//SWETHA CHANGE FOR USER CUSTOM PAGE - LOADING DASHBOARD NOV 3
			String custompageKeyId = dbActionTemplate.getSequenceNumber(AdmTlUsercustompagesSql.TBL_ADM_TL_USERCUSTOMPAGES,12,"USC",null,null);
			CommonMessage.debugMsg("CUSTOME RKYEID  "+custompageKeyId);
			AdmTlUsercustompages userCustomPages = new AdmTlUsercustompages();
			userCustomPages.setUscpKeyid(custompageKeyId);
			userCustomPages.setUscpUsrmKeyid(keyid);
			userCustomPages.setUscpPageuri("empEqp_input.base");
			userCustomPages.setUscpParams("{}");
			userCustomPages.setUscpFormheader("Home");
			userCustomPages.setUscpDisplayorder("1");
			userCustomPages.setUscpTempfield1("-");
			userCustomPages.setUscpTempfield2("-");
			userCustomPages.setUscpTempfield3("-");
			userCustomPages.setUscpTempfield4("-");
			userCustomPages.setUscpTempfield5("-");
			userCustomPages.setUscpActive("Y");
			userCustomPages.setUscpCreatedby("EMP0001");
			userCustomPages.setUscpCreatedon(admTlUsermst.getUsrm_createdon());
			userCustomPages.setUscpModifiedon(admTlUsermst.getUsrm_modifiedon());
			AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql();
			
			
			
			
			
			admTlPwdhistory = filladmTlPwdhistory(admTlUsermst);
			String cythKeyid = dbActionTemplate.getSequenceNumber(AdmTlPwdhistorySql.TBL_ADM_TL_PWDHISTORY,8,"PWH",null,null);
			
			admTlPwdhistory.setPwdhKeyid(cythKeyid);
			sqls.add(AdmTlUsermstSql.getInsertSql(admTlUsermstSql.getUsrmDbFields(), admTlUsermst.getSaveArray())); // add insert sql for master table
			sqls.add(AdmTlPwdhistorySql.getInsertSql(admTlPwdhistorySql.getPwdhDbFields(),admTlPwdhistory.getSaveArray()));
			
			sqls.add(AdmTlUsercustompagesSql.getInsertSql(admTlUsercustompagesSql.getUscpDbFields(),userCustomPages.getSaveArray()));
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls;

			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return admTlUsermst;
	}
	
	
	/*
	 * public AdmTlUsermst updateforchangepwd (AdmTlUsermst admTlUsermst) throws
	 * Exception {//.
	 * 
	 * Map<Integer,List<Object[]>> saveData = new HashMap<Integer,List<Object[]>>();
	 * List<int[]> dataTypesList = new ArrayList<int[]>(); List<String> sqls = new
	 * ArrayList<String>(); //AdmTlUsermstSql admTlUsermstSql = new
	 * AdmTlUsermstSql(); //AdmTlPwdhistory admTlPwdhistory = new AdmTlPwdhistory();
	 * List<Object[]> dataList1 = new ArrayList<Object[]>(); //List<Object[]>
	 * dataList2 = new ArrayList<Object[]>(); List<Object[]> dataList3 = new
	 * ArrayList<Object[]>(); List<Object[]> dataList4 = new ArrayList<Object[]>();
	 * List<Object[]> dataList5 = new ArrayList<Object[]>(); int index = 0;
	 * 
	 * try {
	 * 
	 * String keyid=admTlUsermst.getUsrm_keyid();
	 * 
	 * 
	 * //admTlUsermst.setUsrm_keyid(keyid); // set the sequnce number String
	 * pin=keyid.substring(3);
	 * 
	 * int pinNumber=Integer.parseInt(pin);
	 * admTlUsermst.setUsrm_userpin(Integer.toString(pinNumber)); String
	 * Password=admTlUsermst.getUsrm_password();
	 * Password=encriptPassword(Password,pinNumber);
	 * admTlUsermst.setUsrm_password(Password);
	 * 
	 * 
	 * String dateTime = CommonFunctions.dateTimeNow();
	 * admTlUsermst.setUsrm_lastpwdchanged(dateTime);
	 * admTlUsermst.setUsrm_isactive("Y"); admTlUsermst.setUsrm_loginatempt("0");
	 * AdmTlPwdhistory admTlPwdhistory = filladmTlPwdhistory(admTlUsermst); String
	 * cythKeyid =
	 * dbActionTemplate.getSequenceNumber(AdmTlPwdhistorySql.TBL_ADM_TL_PWDHISTORY,8
	 * ,"PWH",null,null); admTlPwdhistory.setPwdhKeyid(cythKeyid); String pwdNoSql =
	 * AdmTlPwdhistorySql.getPwdNo(admTlUsermst.getUsrm_keyid()); String
	 * pwdHistRemSql = AdmTlPwdhistorySql.getPwdHistoryRemember(); String pwdNo =
	 * dbActionTemplate.getSingleValue(pwdNoSql); String remVal =
	 * dbActionTemplate.getSingleValue(pwdHistRemSql);
	 * 
	 * 
	 * 
	 * Object[] dtlDatatmp = { admTlUsermst.getUsrm_keyid()};
	 * dataList1.add(dtlDatatmp);
	 * 
	 * 
	 * Object[] dtlDatatmp3 = { admTlUsermst.getUsrm_password(),
	 * admTlUsermst.getUsrm_defaultpassword() ,
	 * com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlDate(admTlUsermst.
	 * getUsrm_lastpwdchanged()) , admTlUsermst.getUsrm_keyid() };
	 * dataList3.add(dtlDatatmp3);
	 * 
	 * Object[] dtlDatatmp4 = { admTlPwdhistory.getPwdhKeyid(),
	 * admTlPwdhistory.getPwdhUserid(), admTlPwdhistory.getPwdhPasswordno(),
	 * admTlPwdhistory.getPwdhPassword(), admTlPwdhistory.getPwdhCreatedby() };
	 * dataList4.add(dtlDatatmp4);
	 * 
	 * 
	 * 
	 * //admTlPwdhistory.setPwdhPasswordno(pwdNo);
	 * sqls.add(AdmTlPwdhistorySql.getUpdateHistorySql());
	 * saveData.put(index++,dataList1); int [] insDataType = {Types.VARCHAR};
	 * dataTypesList.add(insDataType);
	 * 
	 * 
	 * admTlPwdhistory.setPwdhPasswordno("0");
	 * sqls.add(AdmTlUsermstSql.getUpdateSqlforpwdchange());
	 * saveData.put(index++,dataList3); int [] insDataType3 =
	 * {Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR};
	 * dataTypesList.add(insDataType3);
	 * 
	 * sqls.add(AdmTlPwdhistorySql.getInsertSql());
	 * CommonMessage.debugMsg(AdmTlPwdhistorySql.getInsertSql()+" insert sql");
	 * saveData.put(index++,dataList4); int [] insDataType4 =
	 * {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
	 * dataTypesList.add(insDataType4);
	 * 
	 * if(UIUtils.isValidKeyId(pwdNo)){
	 * 
	 * Object[] dtlDatatmp5 = { remVal, admTlUsermst.getUsrm_keyid() };
	 * dataList5.add(dtlDatatmp5);
	 * 
	 * 
	 * sqls.add(AdmTlPwdhistorySql.getDeleteHistorySql());
	 * saveData.put(index++,dataList5); int [] insDataType5 =
	 * {Types.VARCHAR,Types.VARCHAR}; dataTypesList.add(insDataType5);
	 * 
	 * } CommonMessage.debugMsg(sqls.toString() +" SQL in change pass");
	 * dbActionTemplate.executeBatch(sqls , saveData , dataTypesList); return
	 * admTlUsermst;
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); throw new Exception(e.getMessage()); }
	 * 
	 * 
	 * }
	 */
	
public AdmTlUsermst updateforchangepwd (AdmTlUsermst admTlUsermst)	throws Exception {//. 
		
	    Map<Integer,List<Object[]>> saveData = new HashMap<Integer,List<Object[]>>();
	    List<int[]> dataTypesList = new ArrayList<int[]>();
		List<String> sqls = new ArrayList<String>();
		//AdmTlUsermstSql admTlUsermstSql = new AdmTlUsermstSql();
		//AdmTlPwdhistory  admTlPwdhistory = new AdmTlPwdhistory();
		List<Object[]> dataList1 = new ArrayList<Object[]>();
		//List<Object[]> dataList2 = new ArrayList<Object[]>();
		List<Object[]> dataList3 = new ArrayList<Object[]>();
		List<Object[]> dataList4 = new ArrayList<Object[]>();
		List<Object[]> dataList5 = new ArrayList<Object[]>();
		int index = 0; 
		
		try {
			
			String keyid=admTlUsermst.getUsrm_keyid();
			
			CommonMessage.debugMsg("Date change in DAO IMPLE"+admTlUsermst.getUsrm_lastpwdchanged());
			//admTlUsermst.setUsrm_keyid(keyid); // set the sequnce number
			String pin=keyid.substring(3);
			
			int pinNumber=Integer.parseInt(pin);
			admTlUsermst.setUsrm_userpin(Integer.toString(pinNumber));			
			String Password=admTlUsermst.getUsrm_password();
			Password=encriptPassword(Password,pinNumber);
			admTlUsermst.setUsrm_password(Password);
				
			
			String dateTime = CommonFunctions.pg_dateTimeNow();
			admTlUsermst.setUsrm_lastpwdchanged(dateTime);
			admTlUsermst.setUsrm_isactive("Y");
			admTlUsermst.setUsrm_loginatempt("0");
			AdmTlPwdhistory admTlPwdhistory = filladmTlPwdhistory(admTlUsermst);
			String cythKeyid = dbActionTemplate.getSequenceNumber(AdmTlPwdhistorySql.TBL_ADM_TL_PWDHISTORY,8,"PWH",null,null);
			admTlPwdhistory.setPwdhKeyid(cythKeyid);
			String pwdNoSql = AdmTlPwdhistorySql.getPwdNo(admTlUsermst.getUsrm_keyid());
			String pwdHistRemSql = AdmTlPwdhistorySql.getPwdHistoryRemember();
			String pwdNo = dbActionTemplate.getSingleValue(pwdNoSql);
			String remVal = dbActionTemplate.getSingleValue(pwdHistRemSql);
			
			
			
			Object[] dtlDatatmp =   {  
					admTlUsermst.getUsrm_keyid()};			
					dataList1.add(dtlDatatmp);
					
					//Added By Swetha
					String lastPwdChanged = admTlUsermst.getUsrm_lastpwdchanged();
					String onlyDate = lastPwdChanged.substring(0,10);

					java.sql.Date sqlDate = java.sql.Date.valueOf(onlyDate);
		
			Object[] dtlDatatmp3 =   {   admTlUsermst.getUsrm_password(),
					admTlUsermst.getUsrm_defaultpassword() ,
					sqlDate,
					admTlUsermst.getUsrm_keyid()
					};
			dataList3.add(dtlDatatmp3);
			
			Object[] dtlDatatmp4 =   {  
					admTlPwdhistory.getPwdhKeyid(),
					admTlPwdhistory.getPwdhUserid(),
					admTlPwdhistory.getPwdhPasswordno(),
					admTlPwdhistory.getPwdhPassword(),									
					admTlPwdhistory.getPwdhCreatedby()									    
				};
			dataList4.add(dtlDatatmp4);
			
			
			
				//admTlPwdhistory.setPwdhPasswordno(pwdNo);
			sqls.add(AdmTlPwdhistorySql.getUpdateHistorySql());
			saveData.put(index++,dataList1);
			int [] insDataType = {Types.VARCHAR};
			dataTypesList.add(insDataType);
			
			
			admTlPwdhistory.setPwdhPasswordno("0");
			sqls.add(AdmTlUsermstSql.getUpdateSqlforpwdchange());
			saveData.put(index++,dataList3);
			int [] insDataType3 = {Types.VARCHAR,Types.VARCHAR,Types.DATE,Types.VARCHAR};
			dataTypesList.add(insDataType3);
			
			sqls.add(AdmTlPwdhistorySql.getInsertSql());
			CommonMessage.debugMsg(AdmTlPwdhistorySql.getInsertSql()+" insert sql");
			saveData.put(index++,dataList4);
			int [] insDataType4 = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR};
			dataTypesList.add(insDataType4);
			
			if(UIUtils.isValidKeyId(pwdNo)){
				
				Object[] dtlDatatmp5 =   {   
						remVal,
						admTlUsermst.getUsrm_keyid()					
						};
				dataList5.add(dtlDatatmp5);
				
				
				sqls.add(AdmTlPwdhistorySql.getDeleteHistorySql());
				saveData.put(index++,dataList5);
				int [] insDataType5 = {Types.VARCHAR,Types.VARCHAR};
				dataTypesList.add(insDataType5);
				
			}
			CommonMessage.debugMsg(sqls.toString() +" SQL in change pass");
			dbActionTemplate.executeBatch(sqls , saveData , dataTypesList);
			return admTlUsermst;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
		
	}
	
	public AdmTlUsermst update(AdmTlUsermst admTlUsermst)	throws Exception {//. 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlUsermstSql admTlUsermstSql = new AdmTlUsermstSql();
		AdmTlPwdhistory  admTlPwdhistory = new AdmTlPwdhistory();
		
			String keyid=admTlUsermst.getUsrm_keyid();
			
		   
			//admTlUsermst.setUsrm_keyid(keyid); // set the sequnce number
			String pin=keyid.substring(3);
			
			int pinNumber=Integer.parseInt(pin);
			admTlUsermst.setUsrm_userpin(Integer.toString(pinNumber));
			/*int oldpinnumber = Integer.parseInt(admTlUsermst.getUsrm_userpin());
			CommonMessage.debugMsg( "oldpinnumber" + pinNumber  +  oldpinnumber);
			if(pinNumber != oldpinnumber )
			{
				admTlUsermst.setUsrm_userpin(Integer.toString(pinNumber));
				
				String Password=admTlUsermst.getUsrm_loginid();
				Password=encriptPassword(Password,pinNumber);
				admTlUsermst.setUsrm_password(Password);
				
			}*/
			
			
			
			
			
			//String pin1=admTlUsermst.getUsrm_userpin();
			
			//String Password=admTlUsermst.getUsrm_password();
			
			//Password=encriptPassword(Password,pinNumber);
			//admTlUsermst.setUsrm_password(Password);
			//String defPassword=admTlUsermst.getUsrm_defaultpassword();
			//CommonMessage.debugMsg("defPassword:"+defPassword);
			
			//defPassword=encriptPassword(defPassword,pinNumber);
			//admTlUsermst.setUsrm_defaultpassword(defPassword);
			String dateTime = CommonFunctions.dateTimeNow();
			admTlUsermst.setUsrm_lastpwdchanged(dateTime);
			admTlUsermst.setUsrm_isactive("Y");
			admTlUsermst.setUsrm_loginatempt("0");
			admTlPwdhistory = filladmTlPwdhistory(admTlUsermst);
			String cythKeyid = dbActionTemplate.getSequenceNumber(AdmTlPwdhistorySql.TBL_ADM_TL_PWDHISTORY,8,"PWH",null,null);
			admTlPwdhistory.setPwdhKeyid(cythKeyid);
			String pwdNoSql = AdmTlPwdhistorySql.getPwdNo(admTlUsermst.getUsrm_keyid());
			String pwdHistRemSql = AdmTlPwdhistorySql.getPwdHistoryRemember();
			String pwdNo = dbActionTemplate.getSingleValue(pwdNoSql);
			String remVal = dbActionTemplate.getSingleValue(pwdHistRemSql);
			
				//admTlPwdhistory.setPwdhPasswordno(pwdNo);
				sqls.add(AdmTlPwdhistorySql.getUpdateHistorySql(admTlUsermst.getUsrm_keyid()));
		
			admTlPwdhistory.setPwdhPasswordno("0");
			sqls.add(AdmTlUsermstSql.getUpdateSql(admTlUsermstSql.getUsrmDbFields(), admTlUsermst.getSaveArray()));
			sqls.add(AdmTlPwdhistorySql.getInsertSql(admTlPwdhistorySql.getPwdhDbFields(),admTlPwdhistory.getSaveArray()));
			if(UIUtils.isValidKeyId(pwdNo)){
				sqls.add(AdmTlPwdhistorySql.getDeleteHistorySql(admTlUsermst.getUsrm_keyid(),remVal));
			}
			dbActionTemplate.executeStatements(sqls);
			return admTlUsermst;
			
		
		
		
	}
	
	public AdmTlUsermst delete(AdmTlUsermst admTlUsermst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		AdmTlUsermstSql admTlUsermstSql = new AdmTlUsermstSql();
		try {
			sqls.add(AdmTlUsermstSql.getDeleteSql(admTlUsermstSql.getUsrmDbFields(), admTlUsermst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlUsermst;
	}
	
	public AdmTlUsermst getUserByLogin(String loginId) throws NoDataFoundException, SQLException, Exception
	{
		
		String sql = AdmTlUsermstSql.getUserSqlByLoginId();
		Object args[] = new Object [] { loginId.toUpperCase() };
		
		AdmTlUsermst admTlUsermst = new AdmTlUsermst() ;
		
		admTlUsermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return admTlUsermst;

	}
	
	public AdmTlUsermst getUserBykeyid(String loginId) throws NoDataFoundException, SQLException, Exception
	{
		
		String sql = AdmTlUsermstSql.getUserSqlBykeyid();
		Object args[] = new Object [] { loginId.toUpperCase() };
		
		AdmTlUsermst admTlUsermst = new AdmTlUsermst() ;
		
		admTlUsermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return admTlUsermst;

	}

	public UserLoginDetailsBean getLoginUserDetails(String userKeyid) throws Exception{
		String sql = AdmTlUsermstSql.getUserLoginDetails();
		Object args[] = { userKeyid };
		UserLoginDetailsBean userDetails = new UserLoginDetailsBean();
		CommonMessage.debugMsg(userDetails+" IN sidet the DAO Impl" + sql );
		List<UserLoginDetailsBean> userDetailsList =(List<UserLoginDetailsBean>) dbActionTemplate.getDataList (sql, args, userDetails);
		if( userDetailsList.size() > 0)
			return userDetailsList.get(0);
		return null;
	}
	
	public  AdmTlUsermst validateUser(AdmTlUsermst admTlUsermst) throws ValidationExceptions,BusinessApplicationExceptions,SQLException, Exception
	{
		
		try{
			CommonMessage.debugMsg("validateUser Dao Imp...");
			//lockUser(admTlUsermst.getUsrm_loginid());
			AdmTlUsermst existingUser = getUserByLogin(admTlUsermst.getUsrm_loginid());
			String fromdate=existingUser.getUsrm_validfrom();
			String tilldate=existingUser.getUsrm_validtill();
			String valid=existingUser.getUsrm_isvalidityreq();
			String Current=CommonFunctions.getDate();
			String Currentdate=Current.concat(" 00:00:00");
			CommonMessage.debugMsg("CurrentDate is: "+Currentdate + "Fromdate is:" + fromdate + "Todate is:" + tilldate + "Validiting :"+ valid);
			int userPin = Integer.parseInt(existingUser.getUsrm_userpin());
			int datafiff = CommonFunctions.getDateDiff(Currentdate, tilldate);
			int dateto=CommonFunctions.getDateDiff(fromdate, Currentdate);
			CommonMessage.debugMsg("datafiff: "+datafiff);
			CommonMessage.debugMsg("dateto : "+dateto);
			/*if("Y".equals(valid)&& (datafiff < 0 || dateto < 0))
			{
				CommonMessage.debugMsg("Date Diff:"+datafiff);
				throw new ValidationExceptions("Usrm_login_validity,"); // invalid password
			}*/
			String LocationId=dbActionTemplate.getSingleValue("GEN_TL_EMPLOYEEMST","EMPM_LOCATION","EMPM_CODE",admTlUsermst.getUsrm_loginid().toUpperCase());
            CommonMessage.debugMsg("The Validate user  is:::"+LocationId);
     
            if(LocationId.equals("LCN0000005")){
             
             String DhqSql=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM ADM_TL_USERMST,GEN_TL_DHQADSLIST WHERE DHQEMPM_LOCATIONID='"+LocationId+"' AND  USRM_ISACTIVE= 'Y' AND DHQEMPM_CODE=USRM_LOGINID AND DHQEMPM_CODE='"+admTlUsermst.getUsrm_loginid().toUpperCase()+"' "); 	
             CommonMessage.debugMsg("The DhqSql"+DhqSql);	
             CommonMessage.debugMsg("The DhqSql length"+DhqSql.length());
             if(DhqSql.equals("0")){
            	 throw new ValidationExceptions("ADSID,");
             }
            }
            
			CommonMessage.debugMsg(existingUser.getUsrm_isuserlocked()+" Get the User encrypted password ");

			if( "Y".equals(existingUser.getUsrm_isuserlocked()) )
			{   String sql= "select COUNT(*) from ADM_TL_USERMST where USRM_LASTLOGINDATE <= current_date-91 AND USRM_LOGINID='"+admTlUsermst.getUsrm_loginid().trim()+"'";
				String val=dbActionTemplate.getSingleValue(sql);
			    // int count=Integer.valueOf(val);
				CommonMessage.debugMsg("sql:::"+sql);
				CommonMessage.debugMsg("val:::"+val);
				
				
				  if(val.equals("0")) {
					  throw new ValidationExceptions("Usrm_userlocked,"); //  invalid password
				
				   } else{ 
					   throw new ValidationExceptions("Usrm_userremarks,");
				  }
				 
				 
			}
			
			else if( "N".equals(existingUser.getUsrm_isactive()) )
			{
				throw new ValidationExceptions("Usrm_inactive,"); // invalid password
			}
			else if(! existingUser.getUsrm_password().equals( encriptPassword(admTlUsermst.getUsrm_password(),userPin) )  )
			{
				CommonMessage.debugMsg(existingUser.getUsrm_password()+"USRM");
				CommonMessage.debugMsg(admTlUsermst.getUsrm_password()+"ADMTL");
				throw new ValidationExceptions("Usrm_password-invalid,",existingUser); // invalid password
			}
			
			StringBuffer sql = new StringBuffer();
			/*
			 * sql.append( " SELECT     DECODE(USRM_ISVALIDITYREQ,'Y',DECODE( ");
			 * sql.append(
			 * " greatest(0,date_TRUNC(USRM_VALIDFROM)-date_TRUNC(current_date)),0,");
			 * sql.append(
			 * " DECODE(greatest(0,date_TRUNC(current_date)-date_TRUNC(USRM_VALIDTILL)),0,'N','E'),'S'),'N') AS IS_EXPIRED "
			 * ); sql.append( " FROM ADM_TL_USERMST "); sql.append(
			 * " WHERE trim(USRM_LOGINID)='"+admTlUsermst.getUsrm_loginid()+"' ");
			 */
			

sql.append( "SELECT ");
 sql.append( " CASE ");
sql.append( "    WHEN USRM_ISVALIDITYREQ = 'Y' THEN ");
 sql.append( "     CASE ");
  sql.append( "      WHEN GREATEST(0, DATE_TRUNC('day', USRM_VALIDFROM)::date - CURRENT_DATE) = 0 THEN ");
   sql.append( "       CASE ");
  sql.append( "          WHEN GREATEST(0, CURRENT_DATE - DATE_TRUNC('day', USRM_VALIDTILL)::date) = 0 THEN 'N' ");
   sql.append( "         ELSE 'E' ");
    sql.append( "      END ");
    sql.append( "    ELSE 'S' END ");
   sql.append( " ELSE 'N' ");
 sql.append( " END AS IS_EXPIRED ");
sql.append( "FROM ADM_TL_USERMST ");
sql.append( " WHERE TRIM(USRM_LOGINID) = '"+admTlUsermst.getUsrm_loginid().trim()+"'");
			
			CommonMessage.debugMsg(" Query In side the DAOIMPL"+ sql.toString());

			String isActive = dbActionTemplate.getSingleValue(sql.toString());
			

			if(UIUtils.isValidKeyId(isActive) && "E".equals(isActive) )
			{
				throw new ValidationExceptions("Usrm_login_expired,"); // invalid password
			}
			if(UIUtils.isValidKeyId(isActive) && "S".equals(isActive) )
			{
				throw new ValidationExceptions("Usrm_login_validity,"); // invalid password
			}
			return existingUser;
			
		}catch(NoDataFoundException e){
			
			throw new ValidationExceptions("Usrm_loginid-invalid,"); // invalid login id
		}	
		
	}
	
	public  String encriptPassword(String  password, int userPin) throws BusinessApplicationExceptions
	{
		int tempUserPin =  getFormatedUserPin(userPin);
		CommonMessage.debugMsg("USER PASSWORD"+password);
		String encriptPass ="";
		for(int i=0; i< password.length(); i++){
			int sum = password.charAt(i) + tempUserPin;
			encriptPass  +=  (sum) < 128 ? (char)(sum): (char) (32+(sum-128)+ tempUserPin);		
		}
		CommonMessage.debugMsg("ENCRIPT PASS "+encriptPass);
		return encriptPass;

	}
	
	private int getFormatedUserPin(int userPin) throws BusinessApplicationExceptions 
	{
		NumberFormat formatter = new DecimalFormat("0000");
		String pin = formatter.format(userPin); 
		
		
		if( Integer.parseInt(pin) == 0)
			throw new BusinessApplicationExceptions("tpmusr-000005"); // invalid user pin
		
		int digit = 0, sum = 0;

		for( int i = 0 ; i < pin.length(); i++ )
		{	
			digit = (int)pin.charAt(i) ;
			sum +=  digit;
		}
		String sumStr = Integer.toString(sum);

		do{
			sum = 0;
			for( int i = 0; i < sumStr.length(); i++ )
			{
				digit = (int)(sumStr.charAt(i)-'0');
				sum += digit;
			}
			sumStr = Integer.toString(sum);
		}while( sum > 9 );	
		return sum; 
	}

	@Override
	public AdmTlUsermst select(String keyid) throws Exception {
		
		
		AdmTlUsermst admTlUsermst=new AdmTlUsermst();
		String sql = AdmTlUsermstSql.getUserSqlByLoginId();
		CommonMessage.debugMsg(sql+"Query");
		Object args [] = new Object [] {keyid};
		admTlUsermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		String fdate=admTlUsermst.getUsrm_validfrom();
		String tdate=admTlUsermst.getUsrm_validtill();
		String isval=admTlUsermst.getUsrm_isvalidityreq();
		CommonMessage.debugMsg("isval: "+isval);
		if(isval.equals("N")){
		String fromdate=UIUtils.removeDefaultDate(fdate,"18");
		String todate=UIUtils.removeDefaultDate(tdate, "11");
		CommonMessage.debugMsg("Date Converted1: "+todate);

		 String ValidFromdte=CommonFunctions.getDate();
		 String ValidTilldte=CommonFunctions.addMonth(ValidFromdte, 6);
		 CommonMessage.debugMsg("Date Converted: "+ValidFromdte);
		 CommonMessage.debugMsg("Date Converted: "+ValidTilldte);
		 admTlUsermst.setUsrm_validfrom(ValidFromdte);
		 admTlUsermst.setUsrm_validtill(ValidTilldte);
		
		}
		else{
			CommonMessage.debugMsg("date conversion:");
			String vfdate=UIUtils.getActualDateForm(fdate);
			String vtdate=UIUtils.getActualDateForm(tdate);
			admTlUsermst.setUsrm_validfrom(vfdate);
			admTlUsermst.setUsrm_validtill(vtdate);
		}
		String password=admTlUsermst.getUsrm_password();
		
		String userId=admTlUsermst.getUsrm_keyid();
		String pin=userId.substring(3);
		int pinVal=Integer.parseInt(pin);
		String passOriginal=decryptPassword(password,pinVal);
		
		admTlUsermst.setUsrm_password(passOriginal);
		return admTlUsermst;
	
	}

	@Override
	public List<String[]> getUser(GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		

		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		
		if(gridParams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
		
		
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_usermaingrid_sb", paramValues);
		List<String[]> result =  fnCallApi.callFunction("gen_fn_usermaingrid_sb", paramValues,2,true);
		
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			
			if(  isInteger )
			{
				long counts=Long.parseLong(totalCnt);
				gridParams.setTotalRecordCnt(counts);
			}
		
			
			
			return result;
		 
	                   
		/*
		 * String sql = getUserMasterGrid();
		 * 
		 * String outerSql="select * from("+sql.toString()+")where 1=1 ";
		 * CommonMessage.debugMsg("Outer Sql Query");
		 * CommonMessage.debugMsg("Entered "+gridParams.getGridFilters());
		 * if(gridParams.getGridFilters()!=null)
		 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
		 * String count="select count(*) from("+outerSql+")"; String
		 * rowCount=dbActionTemplate.getSingleValue(count); long
		 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
		 * List<String> params= new ArrayList<String>();
		 * params.add(gridParams.getFromRow());
		 * 
		 * params.add(gridParams.getToRow()); //String
		 * finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
		 * +outerSql+") a ) where slno >= ? and slno <= ?"; //Query Change By Swetha -
		 * User Master String finalSql = "SELECT * FROM (" +
		 * "SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) as slno, a.* " + "FROM ("
		 * + outerSql + ") a" + ") WHERE slno >= ?::BIGINT AND slno <= ?::BIGINT";
		 * CommonMessage.debugMsg("finalSql in sql"+finalSql); List<String[]>
		 * result=dbActionTemplate.getDataList(finalSql, params);
		 * CommonMessage.debugMsg("Final User Result");
		 */
		
		
	}
	
	@Override
	public AdmTlUsermst selectRecall(String userkeyid) throws Exception {
		// TODO Auto-generated method stub
		AdmTlUsermst admTlUsermst=new AdmTlUsermst();
		String sql = AdmTlUsermstSql.getUserByKeyid();
	    
	    
	    
		Object args [] = new Object [] {userkeyid};
		admTlUsermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return admTlUsermst;
	}
	public List<String[]> getUserInfo(String userName) throws Exception {
		
		
		String sql = "SELECT Trim(USRM_KEYID),USRM_USERPIN,Trim(USRM_PASSWORD),Trim(EMPM_EMAIL) FROM gen_tl_employeemst,Adm_Tl_Usermst WHERE  empm_keyid = USRM_CCNO AND  USRM_LOGINID = '"+userName.toUpperCase()+"'";
		   
		
		List<String []> userDatas = dbActionTemplate.getDataList(sql);
		List<String []> dataList = new ArrayList<String[]>();
		 if(userDatas.size()>0)
		{
			String pin=userDatas.get(0)[1];
			int pinVal=Integer.parseInt(pin);
			String passOriginal=decryptPassword(userDatas.get(0)[2],pinVal);
			String emailId = userDatas.get(0)[3];
			if(emailId == null|| emailId.trim().equals(""))
				throw new Exception("EmailId-required");
			String[] row = new String[ 2 ];
			row[0] = passOriginal;
			row[1] = emailId;
			dataList.add(row);
			
		}
		else
			throw new BusinessApplicationExceptions("Usrm_loginid-notFound");
	
		return dataList;
		
		
	}
	@Override
	public List<String[]> selectCcnoRecall(String ccNo) throws Exception {
		// TODO Auto-generated method stub
		
		String sql = AdmTlUsermstSql.getUserByCcno(ccNo);
	    
	    
	    
	    List<String[]> result=dbActionTemplate.getDataList(sql);
		return result;
	}

	@Override
	public Workbook getUserExcel(JSONObject colmodel, String format, GridParams gridParams)
			throws Exception {
		 ResultSet rs = null;
		   try{
			
			//rs =   getdownTimeReportResultSet();
			  
			    String sql = getUserMasterGrid();
			    
				String outerSql="select * from("+sql.toString()+")where 1=1 ";	
				if(gridParams.getGridFilters()!=null)
					outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				String count="select count(*) from("+outerSql+")";
				String rowCount=dbActionTemplate.getSingleValue(count);
				long counts=Long.parseLong(rowCount);
				gridParams.setTotalRecordCnt(counts);
				List<String> params= new ArrayList<String>();
				gridParams.setFromRow(null);
				gridParams.setToRow(null);
				//params.add(gridParams.getFromRow());
				//params.add(gridParams.getToRow());
				String finalSql="SELECT  * from ( select a.* from ("+outerSql+") a ) ";
				
				rs = dbActionTemplate.getData(finalSql);
				
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0, 0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	
	}
	
public String getUserMasterGrid() {
		
		//Query Change By Swetha - User Master
		StringBuffer sql =new StringBuffer();
		sql.append("SELECT USRM_KEYID, USRM_USERNAME AS USERNAME, LOCN_NAME AS EMPLOCATION, ");
		sql.append("EMPM_NAME AS EMPLOYEENUMBER, EMPM_EMPLOYEETYPE AS EMPTYPE, ");
		sql.append("USRM_LOGINID AS LOGINID, USRM_PASSWORD, USRM_DEFAULTPASSWORD, ");
		sql.append("DEPT_NAME AS DEPARTMENT, DESG_NAME AS DESIGNATION, ");
		sql.append("REPLACE(USRM_EXTENSIONPHONE::TEXT, '-99', '') AS EXTENSIONPHONE, ");
		sql.append("USRM_ISACTIVE AS ACTIVE, USRM_ISUSERLOCKED AS LOCKED, USRM_REMARKS AS REMARKS ");
		sql.append("FROM ADM_TL_USERMST ");
		sql.append("INNER JOIN GEN_TL_EMPLOYEEMST ON USRM_CCNO = EMPM_KEYID ");
		sql.append("LEFT JOIN GEN_TL_DEPARTMENTMST ON EMPM_DEPARTMENTID = DEPT_KEYID ");
		sql.append("LEFT JOIN GEN_TL_DESIGNATIONMST ON USRM_DESIGNATIONID = DESG_KEYID ");
		sql.append("LEFT JOIN GEN_TL_LOCATIONMST ON LOCN_KEYID = EMPM_LOCATION ");
		sql.append("ORDER BY USRM_USERNAME");
		return sql.toString();
	}
	
	
	private ResultSet getdownTimeReportResultSet() throws Exception
	{
		String sql=AdmTlUsermstSql.getUser();
		
		return dbActionTemplate.getData(sql);
		
	}

	@Override
	public List<String[]> selectCCName(String ccNo) throws Exception {
		// TODO Auto-generated method stub
	String sql = AdmTlUsermstSql.getUserNameByCCNO(ccNo);
	    
	    
	   
	    List<String[]> result=dbActionTemplate.getDataList(sql);
	   
		return result;
	}

	
	
	private AdmTlPwdhistory filladmTlPwdhistory(AdmTlUsermst newAdmTlUsermst) 
	{
		AdmTlPwdhistory admTlPwdhistory =new AdmTlPwdhistory();
		admTlPwdhistory.setPwdhLastpwdchangedon(newAdmTlUsermst.getUsrm_lastpwdchanged());
		admTlPwdhistory.setPwdhPassword(newAdmTlUsermst.getUsrm_password());
		admTlPwdhistory.setPwdhPasswordno("0");
		admTlPwdhistory.setPwdhUserid(newAdmTlUsermst.getUsrm_keyid());
		admTlPwdhistory.setPwdhActive(newAdmTlUsermst.getUsrm_isactive());
		admTlPwdhistory.setPwdhCreatedby(newAdmTlUsermst.getUsrm_createdby());
		admTlPwdhistory.setPwdhCreatedon(newAdmTlUsermst.getUsrm_createdon());
		admTlPwdhistory.setPwdhModifiedon(newAdmTlUsermst.getUsrm_modifiedon());
		return admTlPwdhistory;
	}

public void getCheckPasswordExpires(String user) throws  BusinessApplicationExceptions,Exception{
		
		String expiresDataSql = AdmTlPwdhistorySql.getPwdExpires(user);
		List<String[]> passwordData;
		
		AdmTlUsermst admTlUsermst = getUserByLogin(user);
		CommonMessage.debugMsg("getcheckpasswordexpires");

		passwordData = dbActionTemplate.getDataList(expiresDataSql);
		CommonMessage.debugMsg("getcheckpasswordexpires2");
		String expire ="";
		String gracPendDays="";
		String intiPendDays  = "";
		if(passwordData.size()>0)
		{
			String grace = passwordData.get(0)[0];				
			String intimation = passwordData.get(0)[1];
			 gracPendDays = passwordData.get(0)[2];
			intiPendDays = passwordData.get(0)[3];
			int intiDays = Integer.parseInt(intiPendDays);
			/*
			 * if(admTlUsermst.getUsrm_defaultpassword().equals(admTlUsermst.
			 * getUsrm_password().trim())) { throw new
			 * BusinessApplicationExceptions("Password_same:"+ -1); }
			 */
			
			if(intimation.equals("Y") && intiDays >= 0)
			{
				if(intiDays == 0){
					
					throw new BusinessApplicationExceptions("Password_Expires-Today:"+0);
				}
				else
					throw new BusinessApplicationExceptions("Password_Expires:"+intiPendDays);
			}
			else if(grace.equals("Y"))
			{				
				throw new BusinessApplicationExceptions("Password_GracePriodExpires:"+gracPendDays);
			}
			else if(intiDays < 0)
			{
				throw new BusinessApplicationExceptions("Password_Expired:"+intiPendDays);
			}
			else 
			{
				if(admTlUsermst.getUsrm_defaultpassword().equals(admTlUsermst.getUsrm_password().trim()))
				{
					throw new BusinessApplicationExceptions("Password_same:"+ -1);
				}
			}
			
			
			
		}
		
	
	}
	
	public void lockUser(String userId) throws Exception
	{
		try{
		String expiresDataSql = AdmTlPwdhistorySql.getPwdExpires(userId);
		List<String []> pwdExpiresDatas = dbActionTemplate.getDataList(expiresDataSql);
		if(pwdExpiresDatas.size()>0)
		{
			String grace=pwdExpiresDatas.get(0)[0];	
			//int graceTime = Integer.parseInt(grace);
			if(grace.equals("Y"))
			{
				String lockSql = AdmTlPwdhistorySql.lockUserAcc(userId);
				dbActionTemplate.executeStatement(lockSql);
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String decryptPassword(String password, int userPin)	throws BusinessApplicationExceptions {
		int tempUserPin =  getFormatedUserPin(userPin);

		String decriptPass ="";
		for(int i=0; i< password.length(); i++ ){
			int diff = password.charAt(i) - tempUserPin;
			decriptPass  +=  (diff) > 32 ? (char)(diff) : (char)(127-((32-diff) + tempUserPin));
		}	
		return decriptPass;
	}


	@Override
	public void lockUserAcc(AdmTlUsermst admTlUsermst)throws Exception {
	
		String lockSql = AdmTlPwdhistorySql.lockUserAcc(admTlUsermst.getUsrm_loginid());
		dbActionTemplate.executeStatement(lockSql);
	}


	@Override
	public String selectdefpaswrd() throws Exception {
		String DefPasswrd=dbActionTemplate.getSingleValue("select LGFR_DEFSYSPASSWORD from adm_tl_loginframework");
		return DefPasswrd;
		
	}

	@Override
	public String decryptEDPassword(String password, int pin) throws BusinessApplicationExceptions {
		int tempUserPin =  getFormatedUserPin(pin);

		String decriptPass ="";
		for(int i=0; i< password.length(); i++ ){
			int diff = password.charAt(i) - tempUserPin;
			decriptPass  +=  (diff) > 32 ? (char)(diff) : (char)(127-((32-diff) + tempUserPin));
		}	
		return decriptPass;
	}

	@Override
	public List<String []> getElementId(String userId,String roleId,String flid) throws Exception {
		
		StringBuffer sql =new StringBuffer();
		
		CommonMessage.debugMsg(" user id in user servlet  "+userId+"     "+roleId+"    "+flid);
		
		sql.append("SELECT TRIM(FNLN_ELEMENTID),FNLN_KEYID,ROLE_LEVEL FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
		sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
		
		if(UIUtils.isValidKeyId(flid))
			sql.append(" AND FRT_FNLN_KEYID  = '"+ flid +"' ");
		
		sql.append(" AND FRT_EMPM_KEYID = '"+ userId.trim() +"' AND FRT_ROLE_KEYID= '"+roleId+"' ");
		
		Object [] args = {userId,roleId} ;
		
		CommonMessage.debugMsg( " Elemnet id Sql "+  sql);
		//List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
		List<String []> userDatas = dbActionTemplate.getDataList(sql.toString());

		CommonMessage.debugMsg( " Elemnet id Sql 2 "+  userDatas.size());
		
		return userDatas;
	}
	
	
	@Override
	public String checkFlidActive(String flid) throws Exception {
		
		String elementType = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_elementtype", "fnln_keyid", flid);
		String value = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_originalid", "fnln_keyid", flid);
		String field = GenTlFunctionallocnSql.getReturnFieldByElementType(elementType);
		String checkField = GenTlFunctionallocnSql.getCheckFieldByElementType(elementType);
		String TableName = GenTlFunctionallocnSql.getTableNamesByElementTypeSql(elementType);
		String active = dbActionTemplate.getSingleValue(TableName, field, checkField, value);
		
		if("N".equals(active)) {
			return "N";
		}else {
			return "Y";
		}
		
	}

	@Override
	public void resetpwd(String userid,AdmTlLoginframework admTlLoginframework) throws Exception {
		
		
//		String password =admTlLoginframework.getLgfrDefsyspassword();
			
		/*if (admTlLoginframework.getLgfrIspassautogen().equals("Y"))
		{
		int maxpasslength = Integer.parseInt(admTlLoginframework.getLgfrMaxpasslength());
		
		String Randampassword = RandomStringUtils.randomAlphanumeric(maxpasslength).toUpperCase();
		
		CommonMessage.debugMsg("Randampassword" + Randampassword);
		
		 password = encriptPassword(Randampassword,1);
		} */
	//	AdmTlUsermst admTlUsermst =  getUserBykeyid(userid);
//		password = encriptPassword(admTlUsermst.getUsrm_loginid(),Integer.parseInt(admTlUsermst.getUsrm_userpin()));
		//password = encriptPassword(Randampassword,1);
		
			StringBuffer sql =new StringBuffer();
			//sql.append("UPDATE ADM_TL_USERMST SET USRM_PASSWORD = '" + password + "' , USRM_DEFAULTPASSWORD = '" + password + "' , USRM_LOGINATTEMPT = 0 , USRM_ISUSERLOCKED = 'N' , USRM_ISACTIVE='Y' WHERE USRM_KEYID='"+userid+"'");
			sql.append("UPDATE ADM_TL_USERMST SET  USRM_LOGINATTEMPT = 0 , USRM_ISUSERLOCKED = 'N' ,USRM_REMARKS='-' , USRM_ISACTIVE='Y' WHERE TRIM(USRM_KEYID)='"+userid.trim()+"' OR TRIM(USRM_LOGINID)='"+ userid.trim()+"'");
			CommonMessage.debugMsg("sql: " + sql.toString());
			dbActionTemplate.executeStatement(sql.toString());	
		
		
	}

	@Override
	public void updateloginattempt(String username) throws Exception {
		
		String sql = "UPDATE ADM_TL_USERMST SET USRM_LOGINATTEMPT = USRM_LOGINATTEMPT + 1 WHERE USRM_LOGINID='"+username+"'" ;
		dbActionTemplate.executeStatement(sql.toString());	
		
	}

	@Override
	public String getloginattemptcount(String username) throws Exception {
		String sql = "Select USRM_LOGINATTEMPT from ADM_TL_USERMST where USRM_LOGINID ='"+username+"'";
		
		return dbActionTemplate.getSingleValue(sql);
	}

	@Override
	public void lockuser(String username) throws Exception {
		String sql = "UPDATE ADM_TL_USERMST SET USRM_ISUSERLOCKED  = 'Y' WHERE USRM_LOGINID='"+username+"'" ;
		dbActionTemplate.executeStatement(sql.toString());	
	}

	@Override
	public void loginattemptzero(String usrm_loginid) throws Exception {
		String sql = "UPDATE ADM_TL_USERMST SET USRM_LOGINATTEMPT  = '0' WHERE USRM_LOGINID='"+usrm_loginid+"'" ;
		dbActionTemplate.executeStatement(sql.toString());	
	}
	

	@Override
	public List<String[]> getUserLoginDetailsFillGrid(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		condParms=condParms+"FLID="+commonFilter.getFlid();
		//CommonMessage.debugMsg("The Flid Is:::"+condParms);
		//String flid=commonFilter.getFlid();
		//CommonMessage.debugMsg("The Flid Is:::"+condParms);
		paramValues.add(condParms);
		paramValues.add(commonParams);
	//	paramValues.add(flid);
		
		
		//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.GEN_FN_USERLOGININFO", paramValues);
		//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.GEN_FN_FNLNEMPCOUNT", paramValues);
		List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_USERLOGININFO", paramValues, 3 ,true);
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			//CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
		
   }

	@Override
	public Workbook getuserDetailsReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getuserDetailsReport(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
	        
			return excelUtils.writeToExcel(rs,format,3,1,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getuserDetailsReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_USERLOGININFO", paramValues);
	}


	/*
	 * @Override public String checkallowtochangepwd(String user) throws Exception {
	 * 
	 * StringBuilder sql = new StringBuilder(); // sql.
	 * append(" select round(sysdate - USRM_LASTPWDCHANGED) from ADM_TL_USERMST where USRM_LOGINID = '"
	 * ).append( user ).append('\''); sql.
	 * append(" select ROUND(CURRENT_DATE - USRM_LASTPWDCHANGED) from ADM_TL_USERMST where USRM_LOGINID = '"
	 * ).append( user ).append('\'');
	 * 
	 * return dbActionTemplate.getSingleValue(sql.toString()); }
	 */
	@Override
	public String checkallowtochangepwd(String user) throws Exception {
		
		StringBuilder sql = new  StringBuilder();
//		sql.append(" select round(sysdate - USRM_LASTPWDCHANGED) from ADM_TL_USERMST where USRM_LOGINID = '" ).append( user ).append('\'');
		//sql.append(" select ROUND(CURRENT_DATE - USRM_LASTPWDCHANGED) from ADM_TL_USERMST where USRM_LOGINID = '" ).append( user ).append('\'');
		sql.append(
			    "SELECT COALESCE(EXTRACT(DAY FROM (CURRENT_DATE - USRM_LASTPWDCHANGED)), 0)::int " +
			    "FROM ADM_TL_USERMST WHERE USRM_LOGINID = '"
			).append(user).append('\'');
		return dbActionTemplate.getSingleValue(sql.toString());
	}
	
	/*
	 * @Override public List<String[]> getLocUser(GridParams gridParams, String
	 * location) throws Exception { // TODO Auto-generated method stub
	 * 
	 * 
	 * String sql = getLocUserMasterGrid(location);
	 * 
	 * String outerSql="select * from("+sql.toString()+")where 1=1 ";
	 * if(gridParams.getGridFilters()!=null)
	 * outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	 * String count="select count(*) from("+outerSql+")";
	 * CommonMessage.debugMsg("count in daoimpl"+count); String
	 * rowCount=dbActionTemplate.getSingleValue(count);
	 * CommonMessage.debugMsg("rowCount in daoimpl"+rowCount); long
	 * counts=Long.parseLong(rowCount); gridParams.setTotalRecordCnt(counts);
	 * List<String> params= new ArrayList<String>();
	 * params.add(gridParams.getFromRow());
	 * 
	 * params.add(gridParams.getToRow()); String
	 * finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("
	 * +outerSql+") a ) where slno >= ? and slno <= ?";
	 * CommonMessage.debugMsg("finalSql in Dao"+finalSql); List<String[]>
	 * result=dbActionTemplate.getDataList(finalSql, params);
	 * 
	 * return result;
	 * 
	 * }
	 */
	
	@Override
	public List<String[]> getLocUser(GridParams gridParams, String location) throws Exception {
		// TODO Auto-generated method stub
	                   
		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		if(UIUtils.isValidKeyId(location)){
			condParams +="EMPM_LOCATION="+location+";";
		}
		
		if(gridParams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
				List<String[]> result =  fnCallApi.callFunction("gen_fn_locusermastergrid_sb", paramValues,2,true);
				
					String totalCnt = paramValues.get(0); 
					CommonMessage.debugMsg("totalCnt..."+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					
					if(  isInteger )
					{
						long counts=Long.parseLong(totalCnt);
						gridParams.setTotalRecordCnt(counts);
					}
							
					
					return result;	
		
	}
	
	@Override
	public List<String[]> getLocUserMannul(GridParams gridParams, String flid) throws Exception {
		// TODO Auto-generated method stub
	                   
		List<String> paramValues = new ArrayList<String>();		
		String condParams = "";
		String commonParams = "";
		
		if(UIUtils.isValidKeyId(flid)){
			condParams +="FLID="+flid+";";
		}
		
		if(gridParams.getGridFilters()!=null){
			//str.append("GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter())+";");
			commonParams +="GRIDFILTER="+FilterCondSql.makeGridFilterCond(gridParams.getGridFilters())+";";
		}
		
		commonParams +="FROMTOROW="+gridParams.getFromRow()+" AND "+gridParams.getToRow()+";";
		
		paramValues.add(condParams);
		paramValues.add(commonParams);
		
		//List<String[]> result =  dbActionTemplate.processFunctionCalls("gen_fn_inactiveemployeegrid_sb", paramValues);
				List<String[]> result =  fnCallApi.callFunction("gen_fn_locusermastergridJHDMT_sb", paramValues,2,true);
				
					String totalCnt = paramValues.get(0); 
					CommonMessage.debugMsg("totalCnt..."+totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					
					if(  isInteger )
					{
						long counts=Long.parseLong(totalCnt);
						gridParams.setTotalRecordCnt(counts);
					}
							
					
					return result;	
		
	}
	
	@Override
	public Workbook getLocUserExcel(JSONObject colmodel, String format, GridParams gridParams,String location)
			throws Exception {
		 ResultSet rs = null;
		   try{
			
			//rs =   getdownTimeReportResultSet();
			  
			    String sql = getLocUserMasterGrid(location);
			    
				String outerSql="select * from("+sql.toString()+")where 1=1 ";	
				if(gridParams.getGridFilters()!=null)
					outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
				String count="select count(*) from("+outerSql+")";
				String rowCount=dbActionTemplate.getSingleValue(count);
				long counts=Long.parseLong(rowCount);
				gridParams.setTotalRecordCnt(counts);
				List<String> params= new ArrayList<String>();
				gridParams.setFromRow(null);
				gridParams.setToRow(null);
				//params.add(gridParams.getFromRow());
				//params.add(gridParams.getToRow());
				String finalSql="SELECT  * from ( select a.* from ("+outerSql+") a ) ";
				CommonMessage.debugMsg("Final Sql:" +finalSql);
				rs = dbActionTemplate.getData(finalSql);
				
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0, 0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	
	}
	
	
public String getLocUserMasterGrid(String location) {
		
		StringBuffer sql =new StringBuffer();
		
		sql.append(" Select '-' as CHKBOX , USRM_KEYID,USRM_USERNAME as USERNAME,LOCN_NAME AS EMPLOCATION, EMPM_NAME AS EMPLOYEENAME,EMPM_CODE AS EMPLOYEENUMBER, EMPM_EMPLOYEETYPE AS EMPTYPE, ");
		sql.append(" USRM_LOGINID AS LOGINID,USRM_PASSWORD,USRM_DEFAULTPASSWORD,DEPT_NAME AS DEPARTMENT,DESG_NAME AS DESIGNATION,");
		sql.append("REPLACE(USRM_EXTENSIONPHONE,'-99',''),USRM_REMARKS AS REMARKS from ADM_TL_USERMST,GEN_TL_EMPLOYEEMST,GEN_TL_DEPARTMENTMST,GEN_TL_DESIGNATIONMST, GEN_TL_LOCATIONMST ");
		sql.append(" WHERE USRM_CCNO= EMPM_KEYID AND USRM_DEPARTMENTID=DEPT_KEYID(+) AND USRM_DESIGNATIONID=DESG_KEYID(+) AND LOCN_KEYID (+) = EMPM_LOCATION ");
		//sql.append(" AND usrm_loginattempt= '3' ");
		if(UIUtils.isValidKeyId(location))
		    sql.append(" AND EMPM_LOCATION='"+location+"'");
		
		sql.append(" order by USRM_USERNAME");
		CommonMessage.debugMsg("sql in loc user"+sql.toString());
		return sql.toString();
	}
@Override
public void resetpwd(List<String> loginIds) throws Exception {

	    ArrayList<String> sqls=new ArrayList<String>();
		for(String loginId:loginIds){
			StringBuffer sql =new StringBuffer();			
			AdmTlUsermst admTlUsermst =  getUserByLogin(loginId);
			String password = encriptPassword(admTlUsermst.getUsrm_loginid(),Integer.parseInt(admTlUsermst.getUsrm_userpin()));			
			sql.append("UPDATE ADM_TL_USERMST SET USRM_PASSWORD = '" + password + "' , USRM_DEFAULTPASSWORD = '" + password + "' , USRM_LOGINATTEMPT = 0 , USRM_ISUSERLOCKED = 'N' , USRM_ISACTIVE='Y' WHERE USRM_LOGINID='"+loginId+"'");
			CommonMessage.debugMsg("sql :" +sql);
			sqls.add(sql.toString());
			
		}
		dbActionTemplate.executeStatements(sqls);	
		
	}

	public String resetpwd(String loginId) throws Exception {
		CommonMessage.debugMsg("Reset Password");
		String password ="";	
		AdmTlUsermst admTlUsermst =  getUserByLogin(loginId);
		int maxpasslength = admTlUsermst.getUsrm_password().length();
		//CommonMessage.debugMsg("admTlUsermst.getUsrm_password() :" + admTlUsermst.getUsrm_password());
		String Randampassword = RandomStringUtils.randomAlphanumeric(maxpasslength).toUpperCase();
		CommonMessage.debugMsg("Randampassword :" + Randampassword);
		//CommonMessage.debugMsg("admTlUsermst.getUsrm_userpin() :" + admTlUsermst.getUsrm_userpin());		
		password = encriptPassword(Randampassword,Integer.parseInt(admTlUsermst.getUsrm_userpin()));
	   // CommonMessage.debugMsg("encriptPassword :" + password);
		StringBuffer sql =new StringBuffer();
		sql.append("UPDATE ADM_TL_USERMST SET USRM_PASSWORD = '" + password + "' , USRM_DEFAULTPASSWORD = '" + password + "' , USRM_LOGINATTEMPT = 0 , USRM_ISUSERLOCKED = 'N' , USRM_ISACTIVE='Y' WHERE UPPER(USRM_LOGINID)='"+loginId.toUpperCase()+"'");
		CommonMessage.debugMsg("Reset sql :" +sql);
		dbActionTemplate.executeStatement(sql.toString());	
		return Randampassword;
		
	}	

//New Method for Reset Password

	public String getForgetValidation(String loginId)throws Exception {

		AdmTlUsermst existingUser=null;
		try{
			existingUser = getUserByLogin(loginId);
		}catch(Exception e){
			throw new BusinessApplicationExceptions("Usrm_loginid-notFound");
		}
		
		String fromdate=existingUser.getUsrm_validfrom();
		String tilldate=existingUser.getUsrm_validtill();
		String Current=CommonFunctions.getDate();
		String Currentdate=Current.concat(" 00:00:00");
		String isvalidityRequired=existingUser.getUsrm_isvalidityreq();
		int datediff = CommonFunctions.getDateDiff(Currentdate, tilldate);
		int dateto=CommonFunctions.getDateDiff(Currentdate,fromdate );
		
		if( isvalidityRequired.equals("Y") && datediff<0){
					throw new ValidationExceptions("Usrm_inactive,"); // invalid password
		}else{
		
			List<String []> dataList = new ArrayList<String[]>();
			String sql = " SELECT Trim(USRM_KEYID),USRM_USERPIN,Trim(USRM_PASSWORD),Trim(EMPM_EMAIL),Trim(USRM_LOGINID) FROM gen_tl_employeemst,Adm_Tl_Usermst WHERE  empm_keyid = USRM_CCNO AND  UPPER(USRM_LOGINID) = '"+ loginId.toUpperCase() +"'";
			CommonMessage.debugMsg("sql :" +sql);
			List<String []> userDatas = dbActionTemplate.getDataList(sql);	
			if(userDatas.size()<=0){
				throw new BusinessApplicationExceptions("Usrm_loginid-notFound");
			}
			else{
				CommonMessage.debugMsg(" userDatas : "+userDatas );
				String emailId = userDatas.get(0)[3];
				CommonMessage.debugMsg(" emailId : "+emailId );
				
//				if(!UIUtils.isValidEmail(emailId))
//					throw new Exception("EmailId-required");
				
				if(!UIUtils.isValidEmail(emailId)) {
					String jhMemberSql = "SELECT Trim(USRM_KEYID),Trim(USRM_LOGINID),FRT_ROLE_KEYID,empm_keyid,frt_fnln_keyid FROM  "
							+ "Adm_Tl_Usermst JOIN gen_tl_employeemst  ON empm_keyid = USRM_CCNO "
							+ "JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID  "
							+ "WHERE    UPPER(USRM_LOGINID) = '"+ loginId.toUpperCase() +"' AND FRT_ROLE_KEYID = 'AROL0010' ";
					CommonMessage.debugMsg("jhMemberSql :" +jhMemberSql);
					List<String []> jhUserDatas = dbActionTemplate.getDataList(jhMemberSql);
					if(jhUserDatas.size()<=0){
						throw new Exception("EmailId-required");
					}else
					{
						CommonMessage.debugMsg(" jhUserDatas : "+jhUserDatas );
						String empId = jhUserDatas.get(0)[3];
						String flid = jhUserDatas.get(0)[4];
						CommonMessage.debugMsg(" empId : "+empId +" flid : "+flid );
						
						String jhLeaderSql = "SELECT "
								+ "	TRIM(EMPM_EMAIL) "
								+ "FROM "
								+ "	GEN_TL_EMPLOYEEMST  "
								+ "	JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID   "
								+ "WHERE  frt_role_keyid = 'AROL0006' and  frt_fnln_keyid = '"+ flid +"' ";
						CommonMessage.debugMsg("jhLeaderSql :" +jhLeaderSql);
						List<String []> jhLeaderMails = dbActionTemplate.getDataList(jhLeaderSql);
						if(jhLeaderMails.size()<=0){
							throw new Exception("EmailId-required");
						}else
						{
							emailId = jhLeaderMails.get(0)[0];
						}
						if(UIUtils.isValidEmail(emailId))
							return "suceess-jh";
						else 
							throw new Exception("EmailId-required");
					}
					
				}
			}
			
			CommonMessage.debugMsg("------------------AmdTlUsermstDaoImpl------------------");
			return "suceess";
		}	
	}
	
	public List<String[]> getForgetEmpMailIds(String loginId)throws Exception{
		
			List<String []> dataList = new ArrayList<String[]>();
			String sql = " SELECT Trim(USRM_KEYID),USRM_USERPIN,Trim(USRM_PASSWORD),Trim(EMPM_EMAIL),Trim(USRM_LOGINID) FROM gen_tl_employeemst,Adm_Tl_Usermst WHERE  empm_keyid = USRM_CCNO AND  UPPER(USRM_LOGINID) = '"+ loginId.toUpperCase() +"'";
			CommonMessage.debugMsg("sql :" +sql);
			List<String []> userDatas = dbActionTemplate.getDataList(sql);	
			
			if(userDatas.size()<=0){
				throw new BusinessApplicationExceptions("Usrm_loginid-notFound");
			}else
			{
				CommonMessage.debugMsg(" userDatas : "+userDatas );
				String emailId = userDatas.get(0)[3];
				CommonMessage.debugMsg(" emailId : "+emailId );
				
				if(!UIUtils.isValidEmail(emailId)) {
					String jhMemberSql = "SELECT Trim(USRM_KEYID),Trim(USRM_LOGINID),FRT_ROLE_KEYID,empm_keyid,frt_fnln_keyid FROM  "
							+ "Adm_Tl_Usermst JOIN gen_tl_employeemst  ON empm_keyid = USRM_CCNO "
							+ "JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID  "
							+ "WHERE    UPPER(USRM_LOGINID) = '"+ loginId.toUpperCase() +"' AND FRT_ROLE_KEYID = 'AROL0010' ";
					CommonMessage.debugMsg("jhMemberSql :" +jhMemberSql);
					List<String []> jhUserDatas = dbActionTemplate.getDataList(jhMemberSql);
					if(jhUserDatas.size()<=0){
						throw new Exception("EmailId-required");
					}else
					{
						CommonMessage.debugMsg(" jhUserDatas : "+jhUserDatas );
						String empId = jhUserDatas.get(0)[3];
						String flid = jhUserDatas.get(0)[4];
						CommonMessage.debugMsg(" empId : "+empId +" flid : "+flid );
						
						String jhLeaderSql = "SELECT "
								+ "	TRIM(EMPM_EMAIL) "
								+ "FROM "
								+ "	GEN_TL_EMPLOYEEMST  "
								+ "	JOIN GEN_TL_FNLNROLETEAM ON EMPM_KEYID = FRT_EMPM_KEYID   "
								+ "WHERE  frt_role_keyid = 'AROL0006' and  frt_fnln_keyid = '"+ flid +"' ";
						CommonMessage.debugMsg("jhLeaderSql :" +jhLeaderSql);
						List<String []> jhLeaderMails = dbActionTemplate.getDataList(jhLeaderSql);
						if(jhLeaderMails.size()<=0){
							throw new Exception("EmailId-required");
						}else
						{
							emailId = jhLeaderMails.get(0)[0];
						}
					}
					
				}
					
				String Randampassword=resetpwd(loginId);
				CommonMessage.debugMsg("Original Password2: "+Randampassword );
				
				String[] row = new String[ 2 ];
				row[0] = Randampassword;
				row[1] = emailId;
				dataList.add(row);
				
			}			
			CommonMessage.debugMsg("------------------AmdTlUsermstDaoImpl------------------");
			return dataList;
	}

	@Override
	public String updateUserLoginTime(AdmTlUsermst admtluersmt)
			throws Exception {
		// TODO Auto-generated method stub
		   String sql="UPDATE ADM_TL_USERMST set USRM_LASTLOGINDATE=TO_DATE('"+admtluersmt.getUsrm_lastlogindate()+"','DD-MON_YYYY HH24:MI:SS') where USRM_KEYID='"+admtluersmt.getUsrm_keyid().trim()+"'";
		    CommonMessage.debugMsg("sql"+sql);   
		   dbActionTemplate.executeStatement(sql);
		return sql;
		   
	}

	@Override
	public List<String[]> getEmpCount(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg("The condParms::::"+condParms);

		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		//List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST2.GEN_FN_FNLNEMPCOUNT", paramValues);
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_FNLNEMPCOUNT", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			//CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
		
	}

	@Override
	public Workbook getEmpCountExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		
			
				List<String> paramValues = new ArrayList<String>();
				
				String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
				paramValues.add(condParms);
				paramValues.add(commonParams);
				 
				// ResultSet rs = null;
				 try{
				 	//rs=dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.GEN_FN_FNLNEMPCOUNT", paramValues);
				 	rs=dbActionTemplate.NewdbFunctionCall2("GEN_FN_FNLNEMPCOUNT", paramValues);
				 	ExcelUtils excelUtils = new ExcelUtils(colmodel);
					return excelUtils.writeToExcel(rs,format,2,0,0 );
				 		  
				 }finally{
					   if( rs != null)
				 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
					     
				 }
	}


	@Override
	public List<ComboBox> fillComboValuesRole(ComboFilter comboFilter) throws Exception
	{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer tbl = new StringBuffer(" , ROLE_LEVEL from "+ comboFilter.getTableName() + " where 1 = 1  ");
		//sql.append(" SELECT DISTINCT ");
		
		CommonMessage.debugMsg("::::comboFilter.getTableName()>>"+comboFilter.getTableName());
		StringBuilder selectSql = new StringBuilder(" SELECT DISTINCT ");
		String likeSql = null ;
		if( comboFilter.getIdField()!= null )//comboFilter.getIdField()
			selectSql.append(comboFilter.getIdField() + " id ");
		
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField() + "||'-'||" +comboFilter.getCodeField() );
			if( comboFilter.getCode() != null && comboFilter.getName() != null)
				likeSql = " ( upper(" + comboFilter.getCodeField() + ") like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%') or "  
						 + " upper( "+ comboFilter.getNameField() + ") like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')  ) ";
		}
		else if( comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField());
			if(  comboFilter.getName() != null )
				likeSql = " Upper(" + comboFilter.getNameField() + ") like Upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
		else if( comboFilter.getCodeField() != null )
		{
			selectSql.append("," + comboFilter.getCodeField()); 
			if( comboFilter.getCode() != null )
				likeSql = " Upper( "+ comboFilter.getCodeField() + ") like Upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')"; 
		}
		/*else if( comboFilter.getCode() != null )
		{
			//selectSql = "," + comboFilter.getCodeField();  
		 	likeSql = comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%'"; 
		}*/
		
		//
		
		/*if(UIUtils.isValidKeyId( comboFilter.getCode())){ //PRDM_NAME,PRDM_CODE
			likeSql += "and PRDM_NAME  ||'-' || PRDM_CODE  like upper('%"+comboFilter.getCode()+"%')";
	        CommonMessage.debugMsg(" Product Id : "+comboFilter.getCode());
		}*/
		CommonMessage.debugMsg("getID   :"+comboFilter.getId());
	 /* if(UIUtils.isValidKeyId(comboFilter.getName()) && !UIUtils.isValidKeyId(comboFilter.getId()))
	  {	  
			//selectSql = "," + comboFilter.getNameField();
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getCodeField() != null )
			likeSql  =" and "+  comboFilter.getCodeField() + " like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')";
			CommonMessage.debugMsg(UIUtils.isValidKeyId(likeSql)+" condsqlll  "+likeSql);
		}
	   else{
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
	*/
		
		//sql.append( selectSql + " text ");
		selectSql.append(" text " );
		
		CommonMessage.debugMsg("selectSql>>>>>"+selectSql);
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() >  0)
			selectSql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", "") );//sql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", ""));
		String cSql ="";
		//tbl.append(" from ");
		//tbl.append(comboFilter.getTableName());
		//sql.append(" where 1 = 1  " ) ;// + likeSql);and
		//sql.append(" and  " ) ;
		boolean isCombo = false;
		if( (! "grid".equals(comboFilter.getMode())) && UIUtils.isValidKeyId(comboFilter.getId())  && ! UIUtils.isValidKeyId(comboFilter.getName()) )
		{	
			isCombo =true;
			StringBuilder s = new StringBuilder(  selectSql +", 1 r ");
			
			selectSql.append(",2  r " );
			s.append(tbl);
			
			
			//s.append(" UNION " );
			//CommonMessage.debugMsg("  s ------ " + s);
			
			if(comboFilter.getId().indexOf(",")>0){
				s.append(" and  "+comboFilter.getIdField() + "  in('"+comboFilter.getId().replaceAll(",", "','")+"')");
				cSql = " and  "+comboFilter.getIdField() + " not in ('"+comboFilter.getId().replaceAll(",", "','")+"')";
			}	
			else{
				s.append(" and  "+comboFilter.getIdField() + " = '" +comboFilter.getId()+"'");//'"+employee.getId()+"'"
				cSql = " and  "+comboFilter.getIdField() + " <> '" +comboFilter.getId() + "'";//'"+employee.getId()+"'"
			}	
			
			if(comboFilter.getCondSql() != null )
				s.append(comboFilter.getCondSql());
			
			//sql.insert(0, s + " UNION ");
			//sql.append(" UNION " + s + " ) where 1 = 1 ");
			//sql.insert(0, " SELECT * from ( ");
			//CommonMessage.debugMsg("  sql ------ " + sql );
			s.append(" UNION ");
			sql.append(s);
			
			
		}
		selectSql.append( tbl);
		sql.append( selectSql );
		
		if(UIUtils.isValidKeyId(likeSql))
			sql.append( " and " + likeSql);
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		
		if( isCombo )
			sql.append(cSql);		
		sql.append(" order by ");
		
		if( isCombo )
			sql.append(" r, ");
		
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() > 0 )
			sql.append( comboFilter.getOrderByField() );
		else
			sql.append(" ROLE_LEVEL asc,text asc ");
		
		//ResultSet rs = null; 
		//Connection connection = null;
		
		CommonMessage.debugMsg(" sql111 " + sql);
		try{
			/*rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				comboList.add(compComb);
			}*/
			//return comboList;
			
			CommonMessage.debugMsg("sql.toString()>>>>"+sql.toString());
			
			return exceuteComboQuery(sql.toString(), null, comboFilter);
		}finally{
			
			
			//DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}
		private List<ComboBox> exceuteComboQuery(String sql, Object[]condArgs,ComboFilter comboFilter  ) throws Exception
		{
			String mode = comboFilter.getMode();
			StringBuilder eSql = new StringBuilder();
			if("grid".equals(mode)){
				StringBuilder exectueSql = new StringBuilder();
				exectueSql.append("SELECT * FROM (");
				exectueSql.append(sql);
				exectueSql.append(") WHERE 1=1 ").append(FilterCondSql.makeGridFilterCond(comboFilter.getGridparam().getGridFilters()));
				StringBuilder sqlCount = new StringBuilder("select count(*) from (");
				sqlCount.append( exectueSql );
				sqlCount.append(" )");
				String totalRecords = "";
				totalRecords = dbActionTemplate.getSingleValue(sqlCount.toString());
				comboFilter.getGridparam().setTotalRecordCnt(Integer.parseInt(totalRecords));

				eSql.append("SELECT * FROM (SELECT ROWNUM AS SLNO,A.* FROM (");
				eSql.append( exectueSql);
				eSql.append(" )A )WHERE SLNO BETWEEN  ");
				eSql.append(comboFilter.getGridparam().getFromRow());
				eSql.append(" AND ");
				eSql.append(comboFilter.getGridparam().getToRow());
				//CommonMessage.debugMsg("ifExectueCombo");
		     }
			 else{
				 eSql.append( " select * from ( ");
				 eSql.append( sql );
				 eSql.append(") ");//where rownum <= ").append(comboFetchCount) ;
				
				
			 }
				CommonMessage.debugMsg("exectueSql    :: "+eSql );
				//if( UIUtils.isValidKeyId(comboFilter.getNewSelectQuery()))
				
				 return getComboGriddata(eSql.toString(),condArgs,mode);
			//	 commnented now		return null;
				/*else
					return getComboValues(exectueSql,condArgs);*/
			 
		}
		
		private List<ComboBox> getComboGriddata(String exectueSql, Object[] condArgs, String mode) throws Exception {
			// TODO Auto-generated method stub
			//CommonMessage.debugMsg("getComboGriddata");
			ResultSet rs = null;
			Connection connection = null;
			Statement statement = null; 
			try{
				//if( condArgs != null )
				//	rs = dbActionTemplate.getData(sql, condArgs) ;
				//else
				
				
				List<ComboBox> comboList = new ArrayList<ComboBox>();

				if("grid".equals(mode)){
					List<String []>   combolistArray = dbActionTemplate.getDataListWithColHeader(exectueSql,null) ;
					for(int i=0;i<combolistArray.size();i++){
						 
						 ComboBox combobox = new ComboBox();	 
						// combobox.setId(combolistArray.get(i)[0]);
						 //combobox.setText(combolistArray.get(i)[1]);
						 combobox.setColumns(combolistArray.get(i));
						 
						 comboList.add(combobox );	 
					 }
				}	
				else{
					rs = dbActionTemplate.getData(exectueSql) ;
					statement = rs.getStatement();
					connection = rs.getStatement().getConnection();
					while(rs.next())
					{	
						ComboBox compComb = new ComboBox();
						compComb.setId(rs.getString("id"));
						compComb.setText(rs.getString("text"));
						comboList.add(compComb);
					}
				}	
				 
				 
				return comboList;
			}finally{
				if( rs != null)
					rs.close();
				rs = null;
				DBActionTemplate.closeConnection(rs,statement, null, null, connection);
			}
		}
		/*public String getdhqloginId(String loginId)throws BusinessApplicationExceptions,Exception{
			//StringBuilder sql=new StringBuilder();
	        String sql="";		
			String LocationId=dbActionTemplate.getSingleValue("GEN_TL_EMPLOYEEMST", "EMPM_LOCATION", "EMPM_CODE",loginId);
            CommonMessage.debugMsg("The LocationId is:::"+LocationId);
         
            if(LocationId.equals("LCN0000005")){
               	CommonMessage.debugMsg("Inside the Location DHQ");
               	sql=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM ADM_TL_USERMST,GEN_TL_EMPLOYEEMST,GEN_TL_DHQEMPLOYEEMST WHERE EMPM_KEYID=USRM_CCNO AND EMPM_LOCATION='"+LocationId+"' AND USRM_ISACTIVE='Y' AND DHQEMPM_CODE=USRM_LOGINID AND USRM_LOGINID='"+loginId+"' ");	
               	
               	sql.append("SELECT COUNT(*) USRM_LOGINID FROM ADM_TL_USERMST,GEN_TL_EMPLOYEEMST,GEN_TL_DHQEMPLOYEEMST WHERE ");
               	sql.append("EMPM_KEYID=USRM_CCNO AND EMPM_LOCATION='"+LocationId+"' ");
               	sql.append(" AND USRM_ISACTIVE='Y' AND DHQEMPM_CODE=USRM_LOGINID AND USRM_LOGINID='"+loginId+"' ");
               	
              //  CommonMessage.debugMsg("The Sql is:Length::"+sql.length());
                CommonMessage.debugMsg("The Sql is:::"+sql);
                if(sql.equals("0")){
                	CommonMessage.debugMsg("Inside the Sql length");
                	throw new BusinessApplicationExceptions("ADS,");
                }
                return dbActionTemplate.getSingleValue(sql);	
                
                }
       
            return LocationId;
		}*/

		@Override
		public String getLoginIDAds(String email) throws Exception {
			// TODO Auto-generated method stub
			
			CommonMessage.debugMsg(" Email in side the Dao Impl "+email);
			String sql = "SELECT UADA_USERNAME FROM ADM_TL_URLADSAUTH WHERE UADA_ADSUSERNAME='"+email+"'";
			CommonMessage.debugMsg(" Email in side the service Impl "+sql);
			return dbActionTemplate.getSingleValue(sql);
		}
		
		public String getDecryptPassword(String userId) throws Exception {
			// TODO Auto-generated method stub
			
			CommonMessage.debugMsg(" Email in side the Dao Impl "+userId);
			String sql = "SELECT USRM_PASSWORD FROM ADM_TL_USERMST WHERE TRIM(USRM_LOGINID)='"+userId+"'";
			String password=dbActionTemplate.getSingleValue(sql);
			String password1="kolkata@11";
			String encPassword=encriptPassword(password1,1234);
			String decriypPassword=decryptPassword(encPassword, 1234);
			CommonMessage.debugMsg(" Email in side the service Impl password1 encPassword "+encPassword);
			CommonMessage.debugMsg(" Email in side the service Impl password1 decriypPassword "+decriypPassword);

			return decriypPassword;
		}

		
}

