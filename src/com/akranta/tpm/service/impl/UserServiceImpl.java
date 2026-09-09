package com.akranta.tpm.service.impl;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AdmTlUserRollBean;
import com.akranta.tpm.bean.ChangePwdBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.UserBean;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdmTlLoginframeworkDao;
import com.akranta.tpm.dao.AdmTlUserRoleLinkDao;
import com.akranta.tpm.dao.AdmTlUsercustompagesDao;
import com.akranta.tpm.dao.AdmTlUsermstDao;
import com.akranta.tpm.dao.AdmTlUsersessionsDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.AdmTlLoginframeworkDaoImpl;
import com.akranta.tpm.dao.impl.AdmTlUserRoleLinkDaoImpl;
import com.akranta.tpm.dao.impl.AdmTlUsercustompagesDaoImpl;
import com.akranta.tpm.dao.impl.AdmTlUsermstDaoImpl;
import com.akranta.tpm.dao.impl.AdmTlUsersessionsDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsercustompages;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AdmTlUsersessions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.UserServices;
import com.akranta.tpm.service.api.UserCreationServiceApi;
import com.akranta.tpm.service.api.UserServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Mail;
import com.akranta.tpm.utils.Validations;

public class UserServiceImpl  implements UserServices {
	
	private AdmTlLoginframeworkDao admTlLoginframeworkDao ;
	private AdmTlUsermstDao admTlUsermstDao;
	private AdmTlUserRoleLinkDao admTlUserRoleLinkDao;
	private AdmTlUsersessionsDao admTlUsersessionsDao; 
	private UserServiceApi userServiceApi;
	private UserCreationServiceApi userCreationServiceApi;
	private CommonFilterDao commonFilterDao;
	private AdmTlUsercustompagesDao admTlUsercustompagesDao;
	
	private Validations validations ;
	private String ldapAdServer = null; 
	private String ldapDomain ="";
	public UserServiceImpl(DBActionTemplate dbActionTemplate) 	
	{
		try {
			admTlLoginframeworkDao = new AdmTlLoginframeworkDaoImpl(dbActionTemplate);
			admTlUsermstDao = new AdmTlUsermstDaoImpl(dbActionTemplate);
			admTlUsersessionsDao = new AdmTlUsersessionsDaoImpl(dbActionTemplate);
			admTlUserRoleLinkDao=new AdmTlUserRoleLinkDaoImpl(dbActionTemplate);
			commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
			admTlUsercustompagesDao=new AdmTlUsercustompagesDaoImpl(dbActionTemplate);
//			userServiceApi = new UserServiceApi();
			validations = new Validations();
			ldapAdServer = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "LDAP_SERVER_URL");
			ldapDomain = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "LDAP_SERVER_DOMAIN");
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessage.debugMsg("  UserServiceImpl  ex " + e.getMessage() );
		}
	} 
	
	public void UserServiceImplJwt(String JwtToken) 	
	{
		admTlUsermstDao.AdmTlUsermstDaoImplJwt(JwtToken);
		userCreationServiceApi = new UserCreationServiceApi(JwtToken);	
		userServiceApi = new UserServiceApi(JwtToken);
			
			
			
	} 
	
	public AdmTlUsermstDao getAdmTlUsermstDao(){
		return admTlUsermstDao;
	}
	public AdmTlUsermst create(AdmTlUsermst newAdmTlUsermst,AdmTlUsermst oldAdmTlUsermst,UserBean userBean,String confPwd) throws ValidationExceptions,Exception {

		try {
			
			 
			String validationsFor = "create";
			CommonMessage.debugMsg("valid");
			validations.validate(newAdmTlUsermst,"UserCreation",validationsFor);
			CommonMessage.debugMsg("valid1");
			String Password=newAdmTlUsermst.getUsrm_password();
			 String ConfPassword=confPwd;
			 
			 if(!Password.equals(ConfPassword))
			 {
				 throw new ValidationExceptions("Usrm_Confpassword-invalid"); 
			 }
			 CommonMessage.debugMsg("fillvalue");
			fillValues(newAdmTlUsermst,oldAdmTlUsermst,userBean);
			CommonMessage.debugMsg("user pin after fill values"+newAdmTlUsermst.getUsrm_userpin());
			
//			AdmTlLoginframework admTlLoginframework = new AdmTlLoginframework();
//			admTlLoginframework = admTlLoginframeworkDao.getloginframwworkdata();
//			
//			
//			CommonMessage.debugMsg("loginframe");
//			String Randampassword  = admTlLoginframework.getLgfrDefsyspassword();
//			if (admTlLoginframework.getLgfrIspassautogen().equals("Y"))
//			{    int maxpasswordlen =Integer.parseInt(admTlLoginframework.getLgfrMaxpasslength());
//				 Randampassword = RandomStringUtils.randomAlphanumeric(maxpasswordlen).toUpperCase();
//			}
//			
			
			//String password = admTlUsermstDao.encriptPassword(Randampassword,1);
			
//			newAdmTlUsermst.setUsrm_password(Randampassword);
//			newAdmTlUsermst.setUsrm_defaultpassword(Randampassword);
			
			// GBachlFunctionallocn  newGBachlFunctionallocn = fillFunctionLoc(newBachBatchMst);
			 CommonMessage.debugMsg("after fill values");
			//return admTlUsermstDao.create(newAdmTlUsermst);
			 
			 return userCreationServiceApi.saveUser(newAdmTlUsermst);
			
			
		}catch (ValidationExceptions e){

			throw e;
		}	
	}

    
	public void setBachBatchCreationDao(AdmTlUsermstDao admTlUsermstDao)
	{
		this.admTlUsermstDao = admTlUsermstDao;
	}

	

	private AdmTlUsermst fillValues(AdmTlUsermst newadmTlUsermst,AdmTlUsermst oldadmTlUsermst,UserBean userBean) {
		newadmTlUsermst.setUsrm_isactive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if( ! UIUtils.isValidKeyId(newadmTlUsermst.getUsrm_keyid())  )
		{
			newadmTlUsermst.setUsrm_createdon(dateTime);
			newadmTlUsermst.setUsrm_modifiedon(dateTime);
		}
		else{
			newadmTlUsermst.setUsrm_keyid(oldadmTlUsermst.getUsrm_keyid());
			newadmTlUsermst.setUsrm_createdon(oldadmTlUsermst.getUsrm_createdon());
			newadmTlUsermst.setUsrm_modifiedon(oldadmTlUsermst.getUsrm_modifiedon());
		}  
		

		
		if( newadmTlUsermst.getUsrm_ccno() == null )
			newadmTlUsermst.setUsrm_ccno("-");
		
		
		
		if( newadmTlUsermst.getUsrm_departmentid() == null )
			newadmTlUsermst.setUsrm_departmentid("-");
		
		if( newadmTlUsermst.getUsrm_designationid() == null )
			newadmTlUsermst.setUsrm_designationid("-");
		
		if( newadmTlUsermst.getUsrm_extensionphone() == null )
			newadmTlUsermst.setUsrm_extensionphone("-99");
		
		if( newadmTlUsermst.getUsrm_isadministartor()== null )
			newadmTlUsermst.setUsrm_isadministartor("N");
		
		
		
		if( newadmTlUsermst.getUsrm_ispwdlockenabled() == null )
			newadmTlUsermst.setUsrm_ispwdlockenabled("N");
		
		if( newadmTlUsermst.getUsrm_istemplateuser() == null )
			newadmTlUsermst.setUsrm_istemplateuser("N");
		
		if( newadmTlUsermst.getUsrm_isuserlocked() == null )
			newadmTlUsermst.setUsrm_isuserlocked("N");
		
		if( newadmTlUsermst.getUsrm_lastlogindate() == null )
			newadmTlUsermst.setUsrm_lastlogindate(dateTime);
		
		if( newadmTlUsermst.getUsrm_lastpwdchanged() == null )
			newadmTlUsermst.setUsrm_lastpwdchanged(dateTime);
		
		if( newadmTlUsermst.getUsrm_loginid() == null )
			newadmTlUsermst.setUsrm_loginid("-");
		
		if( newadmTlUsermst.getUsrm_password()==null)
			newadmTlUsermst.setUsrm_password("-");
		
		if( newadmTlUsermst.getUsrm_remarks()==null)
			newadmTlUsermst.setUsrm_remarks("-");
		
		if( newadmTlUsermst.getUsrm_loginatempt()==null)
			newadmTlUsermst.setUsrm_loginatempt("0");
		
		if( newadmTlUsermst.getUsrm_isvalidityreq()==null)
			newadmTlUsermst.setUsrm_isvalidityreq("N");
		String validFrom = newadmTlUsermst.getUsrm_validfrom();
		String validTill = newadmTlUsermst.getUsrm_validtill();
		
		newadmTlUsermst.setUsrm_validfrom(CommonFunctions.pg_getDateTimeFromDate(validFrom));
		newadmTlUsermst.setUsrm_validtill(CommonFunctions.pg_getDateTimeFromDate(validTill));
		if( newadmTlUsermst.getUsrm_validfrom()==null)
			newadmTlUsermst.setUsrm_validfrom(dateTime);
		
		if( newadmTlUsermst.getUsrm_validtill()==null)
			newadmTlUsermst.setUsrm_validtill(dateTime);
		
		if( newadmTlUsermst.getUsrm_securitypolicyid()==null)
			newadmTlUsermst.setUsrm_securitypolicyid("-");
		
		if( newadmTlUsermst.getUsrm_username()==null)
			newadmTlUsermst.setUsrm_username("-");
		
		CommonMessage.debugMsg("BEFORE SET - user pin: " + newadmTlUsermst.getUsrm_userpin());

		if( newadmTlUsermst.getUsrm_userpin() == null || 
			    "null".equals(newadmTlUsermst.getUsrm_userpin()) )
			    newadmTlUsermst.setUsrm_userpin("0");

		CommonMessage.debugMsg("AFTER SET - user pin: " + newadmTlUsermst.getUsrm_userpin());
		
		
		
		
		return newadmTlUsermst;
		
	}
	

	public AdmTlUsermst update(AdmTlUsermst newAdmTlUsermst,AdmTlUsermst oldAdmTlUsermst,UserBean userBean,String confPwd) throws Exception {	// TODO Auto-generated method stub
			//return null;
		try 
		{
		
			
		String validationsFor;
		validationsFor = "update";
		validations.validate(newAdmTlUsermst,"UserCreation",validationsFor);
		
		String Password=newAdmTlUsermst.getUsrm_password();
		
		 String ConfPassword=confPwd;
		 CommonMessage.debugMsg("after validations");
		 if(!Password.equals(ConfPassword))
		 {
			 throw new ValidationExceptions("Usrm_Confpassword-invalid"); 
		 }
		fillValues(newAdmTlUsermst,oldAdmTlUsermst,userBean);
		CommonMessage.debugMsg("after fill avlues");
		// GBachlFunctionallocn  newGBachlFunctionallocn = fillFunctionLoc(newBachBatchMst);
		
		String chkDefPwd = userBean.getChkIsdefPwd();
		if("Y".equals(chkDefPwd))
		{   
			CommonMessage.debugMsg("Inside  Defaultvalues");
			
			String keyid=newAdmTlUsermst.getUsrm_keyid();
			String pin=keyid.substring(3);
			int pinNumber=Integer.parseInt(pin);		
			String defPassword = newAdmTlUsermst.getUsrm_loginid();
			String defaultpwd = encriptPasswordForUpdate(defPassword,pinNumber);
			CommonMessage.debugMsg("InsideDefaultvalues" + defaultpwd);
			newAdmTlUsermst.setUsrm_password(defaultpwd);
			newAdmTlUsermst.setUsrm_defaultpassword(defaultpwd);			
		}else
		{   
			String keyid=newAdmTlUsermst.getUsrm_keyid();
			String pin=keyid.substring(3);
			int pinNumber=Integer.parseInt(pin);
			String password = newAdmTlUsermst.getUsrm_password();
			String defPassword = newAdmTlUsermst.getUsrm_loginid();
			
			String encpassword = encriptPasswordForUpdate(password,pinNumber);
			String defPwd = encriptPasswordForUpdate(defPassword,pinNumber);
			
			newAdmTlUsermst.setUsrm_password(encpassword);
			newAdmTlUsermst.setUsrm_defaultpassword(defPwd);		
		}
		
		//return admTlUsermstDao.update(newAdmTlUsermst);
		return userCreationServiceApi.saveUser(newAdmTlUsermst);
	}
		catch (ValidationExceptions e){
			throw e;
		}	
	}
	
	public  String encriptPasswordForUpdate(String  password, int userPin) throws BusinessApplicationExceptions
	{
		int tempUserPin =  getFormatedUserPinForUpdate(userPin);
		CommonMessage.debugMsg("USER PASSWORD"+password);
		String encriptPass ="";
		for(int i=0; i< password.length(); i++){
			int sum = password.charAt(i) + tempUserPin;
			encriptPass  +=  (sum) < 128 ? (char)(sum): (char) (32+(sum-128)+ tempUserPin);		
		}
		CommonMessage.debugMsg("ENCRIPT PASS "+encriptPass);
		return encriptPass;

	}
	
	private int getFormatedUserPinForUpdate(int userPin) throws BusinessApplicationExceptions 
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


		
	public void setAdmTlUsermstDao(AdmTlUsermstDao admTlUsermstDao)
	{
		this.admTlUsermstDao = admTlUsermstDao;
	}
//	@Override
//	public AdmTlUsermst validateLogin(AdmTlUsermst admTlUsermst) throws BusinessApplicationExceptions, ValidationExceptions{
//
//			CommonMessage.debugMsg("IN side the service impl" +admTlUsermst.getUsrm_loginid().indexOf('\\'));
//			try {
//				Validations validations = new Validations();
//				validations.validate(admTlUsermst,"Login","UserLogin");
//				
//				/*****
//				 * Added for windows User Validation 
//				 * 
//				 */
//				//CommonMessage.debugMsg(" before -- login " + admTlUsermst.getUsrm_loginid());
//				if( admTlUsermst.getUsrm_loginid().indexOf('\\') > 0){
//					int ind = admTlUsermst.getUsrm_loginid().lastIndexOf('\\');
//					admTlUsermst.setWindowsGroup(admTlUsermst.getUsrm_loginid().substring(0,ind+1));
//					admTlUsermst.setUsrm_loginid(admTlUsermst.getUsrm_loginid().substring(ind+1));
//					//CommonMessage.debugMsg(" -- login " + admTlUsermst.getUsrm_loginid());
//				}
//				CommonMessage.debugMsg("Before valid window ");
//				//boolean isValidWindUser = isValidWindowsUser(admTlUsermst.getUsrm_loginid(),admTlUsermst.getUsrm_password());
//				//CommonMessage.debugMsg(" isWindowsUser "+ isWindowsUser);
//				//if( isValidWindUser )
//				//	return admTlUsermstDao.getUserByLogin(admTlUsermst.getUsrm_loginid());
//				return admTlUsermstDao.validateUser(admTlUsermst);
//				
//			}catch (ValidationExceptions e){
//				//e.printStackTrace();
//				if(e.getMessage().indexOf("Usrm_password-invalid") >= 0)
//				{	
////					if(! UIUtils.isValidKeyId(admTlUsermst.getWindowsGroup()) && UIUtils.isValidKeyId(ldapDomain)){
////						admTlUsermst.setWindowsGroup(ldapDomain+"\\");
////					}
////					boolean isValidWindUser = isValidWindowsUser(admTlUsermst.getWindowsGroup()+admTlUsermst.getUsrm_loginid(),admTlUsermst.getUsrm_password());
////					CommonMessage.debugMsg("isValidWindUser"+isValidWindUser);
////					if( isValidWindUser ){
////						//CommonMessage.debugMsg(" e " + e );
////						//CommonMessage.debugMsg(" e " + e.getObject() );
////						CommonMessage.debugMsg(" e " + e.getObject() );
////						CommonMessage.debugMsg(" e " + e );
////						return (AdmTlUsermst) e.getObject();
////					
////					}
//					/*else{
//						throw new ValidationExceptions("Usrm_password-AEDS");
//					}*/
//					CommonMessage.debugMsg("Commented the LDAP SERVER CONNECTION INTENTIONALLY");
//				}
//				throw e;
//			}catch (BusinessApplicationExceptions e) {
//				//e.printStackTrace();
//				throw e;
//			}catch(NoDataFoundException e){
//				e.printStackTrace();
//				throw new ValidationExceptions("WIND_LOGINID_NOTINAPP,");
//			}catch (SQLException e){
//				e.printStackTrace();
//				throw new BusinessApplicationExceptions("tpmsql-000006");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new BusinessApplicationExceptions("tpmoth-000007");
//			}
//		
//	}
	@Override
	public AdmTlUsermst validateLogin(AdmTlUsermst admTlUsermst) throws BusinessApplicationExceptions, ValidationExceptions{

			CommonMessage.debugMsg("IN side the service impl" +admTlUsermst.getUsrm_loginid().indexOf('\\'));
			try {
				Validations validations = new Validations();
				validations.validate(admTlUsermst,"Login","UserLogin");
				
				/*****
				 * Added for windows User Validation 
				 * 
				 */
				//CommonMessage.debugMsg(" before -- login " + admTlUsermst.getUsrm_loginid());
				if( admTlUsermst.getUsrm_loginid().indexOf('\\') > 0){
					int ind = admTlUsermst.getUsrm_loginid().lastIndexOf('\\');
					admTlUsermst.setWindowsGroup(admTlUsermst.getUsrm_loginid().substring(0,ind+1));
					admTlUsermst.setUsrm_loginid(admTlUsermst.getUsrm_loginid().substring(ind+1));
					//CommonMessage.debugMsg(" -- login " + admTlUsermst.getUsrm_loginid());
				}
				CommonMessage.debugMsg("Before valid window ");
				//boolean isValidWindUser = isValidWindowsUser(admTlUsermst.getUsrm_loginid(),admTlUsermst.getUsrm_password());
				//CommonMessage.debugMsg(" isWindowsUser "+ isWindowsUser);
				//if( isValidWindUser )
				//	return admTlUsermstDao.getUserByLogin(admTlUsermst.getUsrm_loginid());
				try{
					CommonMessage.debugMsg("validateUser Dao Imp...");
					//lockUser(admTlUsermst.getUsrm_loginid());
					AdmTlUsermst existingUser = getUserByLogin(admTlUsermst.getUsrm_loginid());
					String fromdate=CommonFunctions.pg_getDateTimeFromPGTimeStamp(existingUser.getUsrm_validfrom());
					String tilldate=CommonFunctions.pg_getDateTimeFromPGTimeStamp(existingUser.getUsrm_validtill());
//					String fromdate=CommonFunctions.pg_getDateTimeFromPGTimeStamp(existingUser.getUsrm_validfrom().concat(" 00:00:00"));
//					String tilldate=CommonFunctions.pg_getDateTimeFromPGTimeStamp(existingUser.getUsrm_validtill().concat(" 00:00:00"));
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
					String LocationId= userServiceApi.getEmployeeLocation(admTlUsermst.getUsrm_loginid().toUpperCase());
		            CommonMessage.debugMsg("The Validate user  is:::"+LocationId);
		     
		            if(LocationId.equals("LCN0000005")){
		             
//		             String DhqSql=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM ADM_TL_USERMST,GEN_TL_DHQADSLIST WHERE DHQEMPM_LOCATIONID='"+LocationId+"' AND  USRM_ISACTIVE= 'Y' AND DHQEMPM_CODE=USRM_LOGINID AND DHQEMPM_CODE='"+admTlUsermst.getUsrm_loginid().toUpperCase()+"' "); 	
//		             CommonMessage.debugMsg("The DhqSql"+DhqSql);	
//		             CommonMessage.debugMsg("The DhqSql length"+DhqSql.length());
//		             if(DhqSql.equals("0")){
//		            	 throw new ValidationExceptions("ADSID,");
//		             }
		            }
		            
					CommonMessage.debugMsg(existingUser.getUsrm_isuserlocked()+" Get the User encrypted password ");

					if( "Y".equals(existingUser.getUsrm_isuserlocked()) )
					{   
						String sql= "select COUNT(*) from ADM_TL_USERMST where USRM_LASTLOGINDATE <= current_date-91 AND USRM_LOGINID='"+admTlUsermst.getUsrm_loginid().trim()+"'";
					//	String val=dbActionTemplate.getSingleValue(sql);
					  //   int count=Integer.valueOf(val);
						CommonMessage.debugMsg("sql:::"+sql);
						throw new ValidationExceptions("Usrm_userlocked,");
//						CommonMessage.debugMsg("val:::"+val);
						
						
//						  if(val.equals("0")) {
//							  throw new ValidationExceptions("Usrm_userlocked,"); //  invalid password
//						
//						   } else{ 
//							   throw new ValidationExceptions("Usrm_userremarks,");
//						  }
						 
						 
					}
					
					else if( "N".equals(existingUser.getUsrm_isactive()) )
					{
						throw new ValidationExceptions("Usrm_inactive,"); // invalid password
					}
					else if(! existingUser.getUsrm_password().equals( encriptPassword(admTlUsermst.getUsrm_password(),userPin) )  )
					{
						throw new ValidationExceptions("Usrm_password-invalid,",existingUser); // invalid password
					}
					
			
				
					String isActive = userServiceApi.getEmployeeIsActive(admTlUsermst.getUsrm_loginid().trim());
					

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
				
//				return admTlUsermstDao.validateUser(admTlUsermst);
				
			}catch (ValidationExceptions e){
				//e.printStackTrace();
				if(e.getMessage().indexOf("Usrm_password-invalid") >= 0)
				{	
					if(! UIUtils.isValidKeyId(admTlUsermst.getWindowsGroup()) && UIUtils.isValidKeyId(ldapDomain)){
						admTlUsermst.setWindowsGroup(ldapDomain+"\\");
					}
					boolean isValidWindUser = isValidWindowsUser(admTlUsermst.getWindowsGroup()+admTlUsermst.getUsrm_loginid(),admTlUsermst.getUsrm_password());
					CommonMessage.debugMsg("isValidWindUser"+isValidWindUser);
					if( isValidWindUser ){
						//CommonMessage.debugMsg(" e " + e );
						//CommonMessage.debugMsg(" e " + e.getObject() );
						CommonMessage.debugMsg(" e " + e.getObject() );
						CommonMessage.debugMsg(" e " + e );
						return (AdmTlUsermst) e.getObject();
					}
					/*else{
						throw new ValidationExceptions("Usrm_password-AEDS");
					}*/
				}
				throw e;
			}catch (BusinessApplicationExceptions e) {
				//e.printStackTrace();
				throw e;
			}catch(NoDataFoundException e){
				e.printStackTrace();
				throw new ValidationExceptions("WIND_LOGINID_NOTINAPP,");
			}catch (SQLException e){
				e.printStackTrace();
				throw new BusinessApplicationExceptions("tpmsql-000006");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new BusinessApplicationExceptions("tpmoth-000007");
			}
		
	}
	
	public  String encriptPassword(String  password, int userPin) throws BusinessApplicationExceptions
	{
		int tempUserPin =  getFormatedUserPin(userPin);

		String encriptPass ="";
		for(int i=0; i< password.length(); i++){
			int sum = password.charAt(i) + tempUserPin;
			encriptPass  +=  (sum) < 128 ? (char)(sum): (char) (32+(sum-128)+ tempUserPin);		
		}
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
	public String getJwtToken(String KeyId) {

	  CommonMessage.debugMsg("IN side the service impl for Getting token " +KeyId);
	  try {
		return userServiceApi.getJwtToken(KeyId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
	}
	
	public AdmTlUsersessions insertUserSession(AdmTlUsersessions admTlUsersessions) throws Exception{
		fillUserSessionValues(admTlUsersessions);
		return userServiceApi.insertSession(admTlUsersessions);
		//return admTlUsersessionsDao.create(admTlUsersessions);
	}
	
	public AdmTlUsermst saveNewPassword(AdmTlUsermst admTlUsermst,ChangePwdBean changePwdBean)throws BusinessApplicationExceptions, ValidationExceptions,Exception
	{
		
			Validations validations = new Validations();
			validations.validate(changePwdBean,"Login","changePWD");
			CommonMessage.debugMsg("validat complete");
			
			String oldPwd = admTlUsermstDao.encriptPassword(changePwdBean.getCpwdOldpwd(), Integer.parseInt(admTlUsermst.getUsrm_userpin()));
			String newPwd = admTlUsermstDao.encriptPassword(changePwdBean.getCpwdNewpwd(), Integer.parseInt(admTlUsermst.getUsrm_userpin()));
			
			CommonMessage.debugMsg("oldPwd" + oldPwd + "admTlUsermst.getUsrm_password()" + admTlUsermst.getUsrm_password());
			
			if(! admTlUsermst.getUsrm_password().equals(oldPwd)  )
			{
				throw new ValidationExceptions("Usrm_Oldpassword-invalid,"); // invalid password
			}
			if(changePwdBean.getCpwdNewpwd().equals(changePwdBean.getCpwdOldpwd()))
			{
				throw new ValidationExceptions("Usrm_password-match,"); // invalid password
			}
			if(! changePwdBean.getCpwdNewpwd().equals( changePwdBean.getCpwdConfpwd() )  )
			{
				throw new ValidationExceptions("Usrm_Confpassword-invalid,"); // invalid password
			}
			if(admTlUsermst.getUsrm_loginid().equals(newPwd))
			{
				throw new ValidationExceptions("sameasloginid,"); // invalid password
			}
			
			CommonMessage.debugMsg( " default password " + newPwd + changePwdBean.getCpwdNewpwd() + oldPwd);
			
			String HistoryPwd = admTlLoginframeworkDao.passwordhistory(admTlUsermst.getUsrm_keyid(),newPwd);
			
			
			CommonMessage.debugMsg("HistoryPwd" + HistoryPwd);
			
			if (HistoryPwd.equals("1"))
			{				
				throw new ValidationExceptions("historypwd,"); 			
			}
			
			int charCount = 0;
    		char temp;
    		int numbercount = 0;
    		int specialchar = 0;
    		String  newpwd2  = changePwdBean.getCpwdNewpwd();
    		CommonMessage.debugMsg("temp newpwd2" + newpwd2);
    		
			for( int i = 0; i < newpwd2.length( ); i++ )
    		{
    		    temp = newpwd2.charAt( i );
    		    CommonMessage.debugMsg("temp newpwd2" + temp);
    		    if (Character.isLetter(temp))
    		    {   
    		    	 charCount = charCount + 1;
    		    }
    		    else if(temp >= 48 && temp <= 57)
      
    		    {
    		    	numbercount = numbercount + 1;
    		    }
    		    
    		   
    		   
    		    
    		   /*if(Character.isAlphabetic(temp))
    		   {
    				charCount = charCount + 1;  
    		   }else 
    		   {
    			   numbercount = numbercount + 1;
    		   }*/
    		}
			
			String special = "!@#$%^&*()_";
			
			for (int r=0; r< special.length(); r++) {
 		       if (newpwd2.indexOf(special.charAt(r)) >= 0) {
 		    	  specialchar = specialchar +1 ;
 		          break;
 		       }
 		    }
			
			CommonMessage.debugMsg("cn" + charCount + numbercount + " special " + specialchar);
			
			AdmTlLoginframework admTlLoginframework = new AdmTlLoginframework();
			admTlLoginframework = admTlLoginframeworkDao.getloginframwworkdata();
			
			String noofaph = admTlLoginframework.getLgfrAlphabets();
			String Maxpwdlng = admTlLoginframework.getLgfrMaxpasslength();
			String Minpwdlng = admTlLoginframework.getLgfrMinpasslength();
			String noofnum = admTlLoginframework.getLgfrNumerals();
			int noofpasshischeck = Integer.parseInt(admTlLoginframework.getLgfrPasshistoryremember());
			
			
			
			if(newPwd.length() > Integer.parseInt(Maxpwdlng))
			{
				throw new ValidationExceptions("maxpwdlenght,",admTlLoginframework); // invalid password
			}
			
			if(newPwd.length() < Integer.parseInt(Minpwdlng))
			{
				throw new ValidationExceptions("minpwdlenght,",admTlLoginframework); // invalid password
			}
			
			if(charCount < Integer.parseInt(noofaph))
			{
				throw new ValidationExceptions("minAlphabets,",admTlLoginframework); // invalid password
			}
			
			if(numbercount < Integer.parseInt(noofnum))
			{
				throw new ValidationExceptions("minnumeric,",admTlLoginframework); // invalid password
			}
			
			if (specialchar < 1)
			{
				throw new ValidationExceptions("sepicalchar,",admTlLoginframework); // invalid password
			}
			if(UIUtils.isValidKeyId(newPwd))
				CommonMessage.debugMsg("changePwdBean.getCpwdNewpwd()" + changePwdBean.getCpwdNewpwd());
				admTlUsermst.setUsrm_password(changePwdBean.getCpwdNewpwd());
			//admTlUsermst.setUsrm_defaultpassword(admTlUsermst.getUsrm_defaultpassword());
			/*if(UIUtils.isValidKeyId(newPwd))
				admTlUsermst.setUsrm_defaultpassword(changePwdBean.getCpwdNewpwd());*/
			if(!UIUtils.isValidKeyId(admTlUsermst.getUsrm_remarks()))
				admTlUsermst.setUsrm_remarks("{}");
			
			admTlUsermst.setUsrm_loginatempt("0");
			return admTlUsermstDao.updateforchangepwd(admTlUsermst);
		

	}
	public void pwdvalidations(String password) throws ValidationExceptions,BusinessApplicationExceptions,Exception
	{
		int charCount = 0;
		char temp;
		int numbercount = 0;
		 	
			for( int i = 0; i < password.length( ); i++ )
			{
			    temp = password.charAt( i );
			   
			    if (Character.isLetter(temp))
    		    {   
    		    	 charCount = charCount + 1;
    		    }
    		    else
    		    {
    		    	numbercount = numbercount + 1;
    		    }
    		    
			   /* CommonMessage.debugMsg("charvalue" + temp);
			   if(Character.isAlphabetic(temp))
			   {
				   charCount = charCount + 1;
			   }else 
			   {
				   numbercount = numbercount + 1;
			   }*/
			}
			CommonMessage.debugMsg(" charCount " + charCount + " numbercount " + numbercount);
			AdmTlLoginframework admTlLoginframework = new AdmTlLoginframework();
			admTlLoginframework = admTlLoginframeworkDao.getloginframwworkdata();
			
			String noofaph = admTlLoginframework.getLgfrAlphabets();
			String Maxpwdlng = admTlLoginframework.getLgfrMaxpasslength();
			String Minpwdlng = admTlLoginframework.getLgfrMinpasslength();
			String noofnum = admTlLoginframework.getLgfrNumerals();
			
			if(password.length() > Integer.parseInt(Maxpwdlng))
			{
				throw new ValidationExceptions("maxpwdlenght,",admTlLoginframework); // invalid password
			}
			
			if(password.length() < Integer.parseInt(Minpwdlng))
			{
				throw new ValidationExceptions("minpwdlenght,",admTlLoginframework); // invalid password
			}
			
			if(charCount < Integer.parseInt(noofaph))
			{
				throw new ValidationExceptions("minAlphabets,",admTlLoginframework); // invalid password
			}
			
			if(numbercount < Integer.parseInt(noofnum))
			{
				throw new ValidationExceptions("minnumeric,",admTlLoginframework); // invalid password
			}
		
		
	}

	public  void fillUserSessionValues( AdmTlUsersessions admTlUsersessions ){
		
//		String dateTime = CommonFunctions.dateTimeNow();
		
		
//		admTlUsersessions.setUsseLogintime(dateTime);
//		admTlUsersessions.setUsseLogouttime(Constants.futureNullDate);
//		admTlUsersessions.setUsseSessiondate(CommonFunctions.getDate());
//		admTlUsersessions.setUsseSessionstatus("Y");
//		admTlUsersessions.setUsseTempfield1("{}");
//		admTlUsersessions.setUsseTempfield2("{}");
//		admTlUsersessions.setUsseTempfield3("{}");
		
        String dateTime = CommonFunctions.pg_dateTimeNow();
        String currentDate = CommonFunctions.pg_getDate();
		
		
		admTlUsersessions.setUsseLogintime(dateTime);
		admTlUsersessions.setUsseLogouttime(Constants.pgFutureNullDateTime);
		admTlUsersessions.setUsseSessiondate(dateTime);
		admTlUsersessions.setUsseSessionstatus("Y");
		admTlUsersessions.setUsseTempfield1("{}");
		admTlUsersessions.setUsseTempfield2("{}");
		admTlUsersessions.setUsseTempfield3("{}");
	}
	@Override
	public UserLoginDetailsBean getLoginUserDeatils(String userKeyid)
			throws Exception {
		return userServiceApi.getLoginUserDetails(userKeyid);
//		return admTlUsermstDao.getLoginUserDetails(userKeyid);
	}
	@Override
	public List<ComboBox> getCcnoComboList(String ccno,ComboFilter ccNo)
			throws Exception {
		CommonMessage.debugMsg("ccno in service impl=>"+ccno);
//		ComboFilter ccNo = new ComboFilter();
		ccNo.setIdField("EMPM_KEYID");
		ccNo.setNameField("EMPM_CODE");
		ccNo.setCodeField("EMPM_NAME");
		StringBuffer sb = new StringBuffer();
		sb.append("AND  EMPM_KEYID NOT IN (SELECT USRM_CCNO FROM ADM_TL_USERMST");
		if(UIUtils.isValidKeyId(ccno))
		{
			sb.append(" where usrm_ccno<>'"+ccno+"'");
		}
		sb.append(")");
		ccNo.setCondSql(sb.toString());
		ccNo.setTableName("GEN_TL_EMPLOYEEMST");
		
		return commonFilterDao.fillComboValues(ccNo);
	}
	@Override
	public List<ComboBox> getProfidComboList(ComboFilter Profid )
			throws Exception {
		//ComboFilter Profid=new ComboFilter();
		Profid.setIdField("LGFR_KEYID");
		Profid.setNameField("LGFR_KEYID");
		Profid.setCodeField("LGFR_CREATEDDATE");
		Profid.setTableName("ADM_TL_LOGINFRAMEWORK");
		Profid.setCondSql("AND LGFR_ACTIVE ='Y'");
		
		return  commonFilterDao.fillComboValues(Profid);
	}
	@Override
	public AdmTlUsermst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		//return admTlUsermstDao.select(keyid);
		return userCreationServiceApi.userRecall(keyid);
	}
	
	
	@Override
	public List<ComboBox> getUseridComboList(ComboFilter Userid)
			throws Exception {
		//ComboFilter Userid=new ComboFilter();
		Userid.setIdField("USRM_KEYID");
		Userid.setNameField("USRM_KEYID");
		Userid.setTableName("ADM_TL_USERMST");
		return commonFilterDao.fillComboValues(Userid);
	}


	@Override
	public List<ComboBox> getUserRollComboList(ComboFilter userRole,String empId)
			throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter UserRoll=new ComboFilter();
		
		
		if( empId != null ){
			//userRole.setCondSql(" AND ROLE_KEYID in  ( SELECT FRT_ROLE_KEYID FROM GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID ='" + empId +"')");
		    
			StringBuffer sql=new StringBuffer();
			userRole.setIdField(" ROLE_KEYID||'-'||FLID ");
			//userRole.
			
			userRole.setCodeField("Role_Name || ' - ' ||Fnln_Displaycode ");
			//userRole.setCodeField("ROLE_LEVEL");
		
			userRole.setTableName(" Gen_Tl_Fnlnroleteam, Adm_Tl_Roleorder, GEN_MV_FLIDHIERARCHY ");// madhan  Adm_Tl_Roleorder to Adm_Tl_RoleMST
			 
			StringBuffer cndSql = new StringBuffer();
			
			cndSql.append(" AND Role_Keyid = Frt_Role_Keyid And Flid = Frt_Fnln_Keyid  " ); 
			
			if( empId != null ) {
				cndSql.append(" AND FRT_EMPM_KEYID ='"+empId.trim() +"' ");
			}
			userRole.setCondSql(cndSql.toString());
			
			//userRole.set
			//return commonFilterDao.fillComboValues(userRole);
		//	return exceuteComboQuery(sql.toString(), null, userRole);
		}
		else {
			userRole.setIdField("ROLE_KEYID");
			//userRole.setNameField("ROLE_CODE");
			userRole.setCodeField("ROLE_NAME");
			userRole.setTableName("Adm_Tl_Rolemst"); // madhan  Adm_Tl_Roleorder to Adm_Tl_RoleMST
			
		}
		
       // return CommonFilterDao.fillComboValues(userRole);	
		return admTlUsermstDao.fillComboValuesRole(userRole);
	}
	
	 private AdmTlLoginframework Fillvalues(AdmTlLoginframework newsadmTlLoginframework) throws BusinessApplicationExceptions
		{ 
			
			
			String dateTime = CommonFunctions.dateTimeNow();
			
			newsadmTlLoginframework.setLgfrCreateddate(dateTime);
			
			newsadmTlLoginframework.setLgfrCreatedon(dateTime);
			
			newsadmTlLoginframework.setLgfrModifiedon(dateTime);
			
			newsadmTlLoginframework.setLgfrIsloginaudit("N");
			
			newsadmTlLoginframework.setLgfrIspassaudit("N");
			
			newsadmTlLoginframework.setLgfrIsprivaudit("N");
			
			newsadmTlLoginframework.setLgfrPassneverexpires("N");
			
			if(newsadmTlLoginframework.getLgfrIspassautogen() == null)
			{
				newsadmTlLoginframework.setLgfrIspassautogen("N");
			}
			if(newsadmTlLoginframework.getLgfrIspolicyactive() == null)
			{
				newsadmTlLoginframework.setLgfrIspolicyactive("N");
			}
			
			newsadmTlLoginframework.setLgfrminpwdchangedays("7");
			newsadmTlLoginframework.setLgfrActive("Y");
			
			
			int pinNumber=1;		
			String defPassword = newsadmTlLoginframework.getLgfrDefsyspassword();	
			//String defPassword = Randampassword ;
			CommonMessage.debugMsg("defPassword" +defPassword);
			newsadmTlLoginframework.setLgfrDefsyspassword(admTlUsermstDao.encriptPassword(defPassword,pinNumber));
			
			CommonMessage.debugMsg("Enc defPassword" + newsadmTlLoginframework.getLgfrDefsyspassword());
			return newsadmTlLoginframework;
			
			
		}
	 @Override
	public List<String[]> getUser(GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.getUser(gridParams);
	}
	@Override	public List<String[]> getUserRoll(String userId) throws Exception {
		// TODO Auto-generated method stub
		//return admTlUserRoleLinkDao.getUserRoll(userId);
		return userCreationServiceApi.findSingleRole(userId);
	}
	@Override
	public List<String[]> getUserRollS(String userId) throws Exception {
		// TODO Auto-generated method stub
		//return admTlUserRoleLinkDao.getUserRollS(userId);
		return userCreationServiceApi.findMultipleRole(userId);
	}
	@Override
	public AdmTlUsermst selectRecall(String userkeyid) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.selectRecall(userkeyid);
	}
	@Override
	public List<String[]> selectCcNoRecall(String ccNo) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.selectCcnoRecall(ccNo);
	}
	@Override
	public AdmTlUsermst delete(AdmTlUsermst newAdmTlUsermst) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.delete(newAdmTlUsermst);
	}
	@Override
	public Workbook UserExportExcel(JSONObject colmodel, String format, GridParams gridParams)
			throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.getUserExcel(colmodel,format,gridParams);
	}

	@Override
	public AdmTlUserRoleLink CreateUserRoll(
			AdmTlUserRoleLink newAdmTlUserRoleLink,
			AdmTlUserRoleLink existAdmTlUserRoleLink,
			AdmTlUserRollBean admTlUserRollBean) throws Exception 
			{
		String from="create";
		try {
			
			 
			String validationsFor = "create";
			CommonMessage.debugMsg("b4 validation");
			validations.validate(newAdmTlUserRoleLink,"UserRollCreation",validationsFor);
			CommonMessage.debugMsg("after validation");
			
			fillRollValues(newAdmTlUserRoleLink,existAdmTlUserRoleLink,admTlUserRollBean,from);
			
			// GBachlFunctionallocn  newGBachlFunctionallocn = fillFunctionLoc(newBachBatchMst);
			 
			//return admTlUserRoleLinkDao.create(newAdmTlUserRoleLink);
			return userCreationServiceApi.saveRole(newAdmTlUserRoleLink);
			
			
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("exception msg:"+e.toString());
			throw new ValidationExceptions(e.getMessage());
		}	
		
		// TODO Auto-generated method stub
	
	}
	public AdmTlUserRoleLink fillRollValues(AdmTlUserRoleLink newAdmTlUserRoleLink,AdmTlUserRoleLink existnewAdmTlUserRoleLink,AdmTlUserRollBean admTlUserRollBean,String from){
		

		newAdmTlUserRoleLink.setArulActive("Y");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		
		if( from.equals("create")  )
		{
			
			newAdmTlUserRoleLink.setArulCreatedon(dateTime);
			newAdmTlUserRoleLink.setArulModifiedon(dateTime);
			
		}

		if( from.equals("update")  )
		{
			
			//newAdmTlUserRoleLink.setArulCreatedon(dateTime);
			newAdmTlUserRoleLink.setArulModifiedon(dateTime);
			newAdmTlUserRoleLink.setArulCreatedon(dateTime);
			//newAdmTlUserRoleLink.setArulCreatedon(existnewAdmTlUserRoleLink.getArulCreatedon());
			
			
			
		}
		
		CommonMessage.debugMsg("Printijg form mode "+from);
		
		/*else {
			CommonMessage.debugMsg("Inside else");
			newAdmTlUserRoleLink.setArulCreatedon(existnewAdmTlUserRoleLink.getArulCreatedon());
			
		}  */
		return newAdmTlUserRoleLink;
		
		
		
	}
	@Override
	public AdmTlUserRoleLink UpdateUserRoll(
			AdmTlUserRoleLink newAdmTlUserRoleLink,
			AdmTlUserRoleLink existAdmTlUserRoleLink,
			AdmTlUserRollBean admTlUserRollBean) throws Exception {
		// TODO Auto-generated method stub
		String from="update";
		try 
		{
		
		
		
		
		fillRollValues(newAdmTlUserRoleLink,existAdmTlUserRoleLink,admTlUserRollBean,from);
		
		 
		// GBachlFunctionallocn  newGBachlFunctionallocn = fillFunctionLoc(newBachBatchMst);
		 	
		//return admTlUserRoleLinkDao.update(newAdmTlUserRoleLink);
		return userCreationServiceApi.updateRole(newAdmTlUserRoleLink);
	}
		catch (ValidationExceptions e){
			
			throw new ValidationExceptions(e.getMessage());
		}	
		
	}
	@Override
	public String DeleteUserRoll(String rollId,String userId )
			throws Exception {
		// TODO Auto-generated method stub
		//return admTlUserRoleLinkDao.delete(rollId,userId);
		String status = "";
		userCreationServiceApi.deleteRole(rollId, userId);
		status = "success";
		return status;
	}
	public String sendPWDToMail(String userName)throws Exception
	{
		if(!UIUtils.isValidKeyId(userName)){
			throw new ValidationExceptions("Usrm_loginid-required,");
		}
		List<String []> userDatas = admTlUsermstDao.getUserInfo(userName);
		Mail mail = new Mail(commonFilterDao.getDBActionTemplate());
		if(userDatas.size()>0)
		{
			try {
				
				mail.sendMail("",userDatas.get(0)[1],"Password Request","Your Password Is "+userDatas.get(0)[0]);
				return userDatas.get(0)[1] ;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return "";
	}
	@Override
	public List<String[]> selectCCName(String ccNo) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.selectCCName(ccNo);
	}

	@Override
	public void getCheckPasswordExpires(String user) throws BusinessApplicationExceptions,Exception{
		 
		
		 //CommonMessage.debugMsg("getCheckPasswordExpires service impl");
		 admTlUsermstDao.getCheckPasswordExpires(user);
		
		
	}

	@Override
	public AdmTlUsermst getUserByLogin(String userLoginId) throws Exception {
	//	return admTlUsermstDao.getUserByLogin(userLoginId);
		return userServiceApi.getUserByLogin(userLoginId);
	}

	@Override
	public AdmTlUsercustompages saveSetHomePage(AdmTlUsercustompages newAdmTlUsercustompages) throws Exception {
		
		
		/*if(newAdmTlUsercustompages.getUscpPageuri().trim() == "" )
			throw new Exception("Select Url");*/
		fillValues(newAdmTlUsercustompages);	
		return admTlUsercustompagesDao.create(newAdmTlUsercustompages);		
	}

	private AdmTlUsercustompages fillValues(AdmTlUsercustompages newAdmTlUsercustompages) {

		
		String dateTime = CommonFunctions.dateTimeNow();		
		
		newAdmTlUsercustompages.setUscpModifiedon(dateTime);
		newAdmTlUsercustompages.setUscpActive("Y");
		newAdmTlUsercustompages.setUscpCreatedon(dateTime);
		
		/*if(newAdmTlUsercustompages.getUscpUsrmKeyid() == null)
		newAdmTlUsercustompages.setUscpUsrmKeyid("{}");*/
		
		if(newAdmTlUsercustompages.getUscpDisplayorder() == null)
			newAdmTlUsercustompages.setUscpDisplayorder("1");
		
		if(newAdmTlUsercustompages.getUscpFormheader() == null)
			newAdmTlUsercustompages.setUscpFormheader("{}");
		
		if(newAdmTlUsercustompages.getUscpPageuri() == null)
			newAdmTlUsercustompages.setUscpPageuri("{}");

		if(newAdmTlUsercustompages.getUscpParams() == null)
			newAdmTlUsercustompages.setUscpParams("{}");
		
		if(newAdmTlUsercustompages.getUscpCreatedby() == null)
			newAdmTlUsercustompages.setUscpCreatedby("{}");
		
		//if(newAdmTlUsercustompages.getUscpTempfield1() == null)
			newAdmTlUsercustompages.setUscpTempfield1("-");
		
		//if(newAdmTlUsercustompages.getUscpTempfield2() == null)
			newAdmTlUsercustompages.setUscpTempfield2("-");
		
		//if(newAdmTlUsercustompages.getUscpTempfield3() == null)
			newAdmTlUsercustompages.setUscpTempfield3("-");
		
		//if(newAdmTlUsercustompages.getUscpTempfield4() == null)
			newAdmTlUsercustompages.setUscpTempfield4("-");
		
		//if(newAdmTlUsercustompages.getUscpTempfield5() == null)
			newAdmTlUsercustompages.setUscpTempfield5("-");
		
			return newAdmTlUsercustompages;
		

	}

	@Override
	public AdmTlUsercustompages recallHomePage(String userId) throws Exception {
		return admTlUsercustompagesDao.recallHomePage(userId);		
	}

	@Override
	public List<String[]> getMenuMasterData(String userId)	throws Exception {
		return admTlUsercustompagesDao.getMenuMasterData(userId);
	}/*
	public List<String[]> getMenuMasterData(String userId)	throws Exception {
		CommonMessage.debugMsg(" uscpPageuriuscpPageuri in service impl "+userId);
		List<String[]> getMenuMasterData =null;
				admTlUsercustompagesDao.getMenuMasterData(userId);
		return getMenuMasterData;//admTlUsercustompagesDao.getMenuMasterData(uscpPageuri);
	}*/

	@Override
	public String encryptCount(String loginAttmpts,int pin) throws BusinessApplicationExceptions {
		
		return admTlUsermstDao.encriptPassword(loginAttmpts, pin);
	}

	@Override
	public String decriptCount(String loginAttmpts, int pin) throws BusinessApplicationExceptions {
		return admTlUsermstDao.decryptPassword(loginAttmpts, pin);
	}

	@Override
	public void lockUserAcc(AdmTlUsermst admTlUsermst)throws Exception {

		 admTlUsermstDao.lockUserAcc(admTlUsermst);
	}
	
	@Override
	public List<String[]> getloginframeworkgrid() throws Exception {
		
		
		List<String[]> Griddata = admTlLoginframeworkDao.getloginframeworkgrid();
		
		for(int i=1;i < Griddata.size();i++ )
		{
			String[] rowdata = Griddata.get(i);
			String password = rowdata[2];
			password = admTlUsermstDao.decryptPassword(password, 1);
			Griddata.get(i)[2] = password;
		}
		
		
		
		return Griddata;
	}
	@Override
	public AdmTlLoginframework Creatlgfrm(AdmTlLoginframework newadmTlLoginframework) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		
			String validationsFor = "create";
			
			validations.validate(newadmTlLoginframework,"lgfrmwrk",validationsFor);
			
			
			if (newadmTlLoginframework.getLgfrIspassautogen().equals("N"))
			{
				pwdvalidations(newadmTlLoginframework.getLgfrDefsyspassword());
			}
			CommonMessage.debugMsg("After Password check");
			Fillvalues(newadmTlLoginframework);
			CommonMessage.debugMsg("After Fillvalues");
			return admTlLoginframeworkDao.create(newadmTlLoginframework);
				
		
	}

	@Override
	public AdmTlLoginframework updatelgfrm(
			AdmTlLoginframework newadmTlLoginframework) throws Exception {
		try
		{   String validationsFor = "update";		
			validations.validate(newadmTlLoginframework,"lgfrmwrk",validationsFor);
			Fillvalues(newadmTlLoginframework);
			return admTlLoginframeworkDao.update(newadmTlLoginframework);
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("exception msg:"+e.toString());
			throw new ValidationExceptions(e.getMessage());
		}	
	}


	public void resetpwd(String userid) throws Exception {
		AdmTlLoginframework admTlLoginframework = new AdmTlLoginframework();
		admTlLoginframework = admTlLoginframeworkDao.getloginframwworkdata();
		
		 admTlUsermstDao.resetpwd(userid,admTlLoginframework);
	}


	private boolean isValidWindowsUser(final String ldapUsername ,final String ldapPassword) throws ValidationExceptions{
		
        final String ldapSearchBase = "";
        CommonMessage.debugMsg(" In side the is valid Method");
        
//        final String ldapAccountToLookup = "prasanth";
        
         	 
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        if(ldapUsername != null) {
            //env.put(Context.SECURITY_PRINCIPAL, "cn="+ldapUsername+",ou=Akranta Users,dc=akranta,dc=com");
        	env.put(Context.SECURITY_PRINCIPAL, ldapUsername);
        }
        if(ldapPassword != null) {
            env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
        }
        CommonMessage.debugMsg("  Ldap server ldapPassword " + ldapUsername + " - "  + ldapPassword); 
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.REFERRAL, "follow");
        env.put(Context.PROVIDER_URL, ldapAdServer);
         
        //ensures that objectSID attribute values
        //will be returned as a byte[] instead of a String
        CommonMessage.debugMsg("  Setting After objectSID BEFORE objectSID " + ldapUsername + " - "  + ldapPassword); 

       // env.put("java.naming.ldap.attributes.binary", "objectSID");
        
        CommonMessage.debugMsg("  Setting After objectSID " + ldapUsername + " - "  + ldapPassword); 

        //ADS_SECURE_AUTHENTICATION
        // the following is helpful in debugging errors
        //env.put("com.sun.jndi.ldap.trace.ber", System.err);
        
		CommonMessage.debugMsg("ldapAdServer 2");
		
        LdapContext ctx =null;
		try{
			CommonMessage.debugMsg(" connecting to Ldap server " + ldapAdServer + " with  " + ldapUsername );
			ctx = new InitialLdapContext(env,null);
			CommonMessage.debugMsg(" after connection " + ctx);
			ctx.close();
			ctx = null;
			return true;
		} catch (NamingException e) {
			
			CommonMessage.debugMsg(" Error  " + e.getMessage() );
			e.printStackTrace();
			if( e.getMessage().indexOf("AcceptSecurityContext") > 0 ){
				String exception = "LDAP_ERR_UNKNOWN,";
				if( e.getMessage().indexOf("data 525")>0){
					exception = "LDAP_ERR_49_525,";
				}
				else if( e.getMessage().indexOf("data 52e")>0){
					exception = "LDAP_ERR_49_52e,";
				}
				else if( e.getMessage().indexOf("data 530")>0){
					exception = "LDAP_ERR_49_530,";
				}
				else if( e.getMessage().indexOf("data 531")>0){
					exception = "LDAP_ERR_49_531,";
				}
				else if( e.getMessage().indexOf("data 532")>0){
					exception = "LDAP_ERR_49_532,";
				}
				else if( e.getMessage().indexOf("data 533")>0){
					exception = "LDAP_ERR_49_533,";
				}
				else if( e.getMessage().indexOf("data 701")>0){
					exception = "LDAP_ERR_49_701,";
				}
				else if( e.getMessage().indexOf("data 773")>0){
					exception = "LDAP_ERR_49_773,";
				}
				else if( e.getMessage().indexOf("data 775")>0){
					exception = "LDAP_ERR_49_775,";
				}
				throw new ValidationExceptions("Usrm_password-invalid,");
			}	
			return false;
			//e.printStackTrace();
			//throw new ValidationExceptions("INVALID_WIND_USRORPAWD,"); // invalid windows username or pwd 
		}
		//return false;
		
	}
	
	

	@Override
	public List<String []> getElementId(String userId,String roleId, String flid) throws Exception {
		return admTlUsermstDao.getElementId(userId,roleId, flid);
	}
	
	@Override
	public String  checkFlidActive(String flid) throws Exception {
		return admTlUsermstDao.checkFlidActive(flid);
	}

	@Override
	public AdmTlLoginframework getloginframeworkdata() throws Exception {
		// TODO Auto-generated method stub
		return admTlLoginframeworkDao.getloginframwworkdata();
	}

	@Override
	public void updateloginattempt(String username) throws Exception {
		admTlUsermstDao.updateloginattempt(username);
	}

	@Override
	public String getloginattemptcount(String username) throws Exception {
		
		//return admTlUsermstDao.getloginattemptcount(username);
		return userServiceApi.getUserLoginAttempt(username);
	}

	@Override
	public void lockuser(String username) throws Exception {
		admTlUsermstDao.lockuser(username);
		
	}

	@Override
	public void setloginattemptzero(String usrm_loginid) throws Exception {
		admTlUsermstDao.loginattemptzero(usrm_loginid);
	}
	@Override
	public List<String[]> getUserLoginDetailsFillGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		
		return admTlUsermstDao.getUserLoginDetailsFillGrid(commonFilter);
		
	}

	@Override
	public Workbook getuserDetailsReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.getuserDetailsReportExcel(colmodel,format,commonFilter);
	}

	@Override
	public String checkallowtochangepwd(String user) throws Exception {
		// TODO Auto-generated method stub
		return admTlUsermstDao.checkallowtochangepwd(user);
	}

	
	 @Override
		public List<String[]> getLocUser(GridParams gridParams,String location) throws Exception {
			// TODO Auto-generated method stub
			return admTlUsermstDao.getLocUser(gridParams,location);
		}
		
		
		 @Override
			public List<String[]> getLocUserMannul(GridParams gridParams,String flid) throws Exception {
				// TODO Auto-generated method stub
				return admTlUsermstDao.getLocUserMannul(gridParams,flid);
			}	
		
		@Override
		public Workbook LocUserExportExcel(JSONObject colmodel, String format, GridParams gridParams,String location)
				throws Exception {
			// TODO Auto-generated method stub
			return admTlUsermstDao.getLocUserExcel(colmodel,format,gridParams,location);
		}

		@Override
		public void resetpwd(List<String> loginIds) throws Exception {
			
			admTlUsermstDao.resetpwd(loginIds);
		}
		//New Method to send the mail for resetpassword
		
		
		@Override
		public String getForgetValidation(String loginId)throws Exception{
			String userDatas = admTlUsermstDao.getForgetValidation(loginId);
			return userDatas;
		}
		@Override
		public List<String[]> getForgetEmpMailIds(String loginId) throws Exception {
			List<String []> userDatas = admTlUsermstDao.getForgetEmpMailIds(loginId);
			return userDatas;
		}

		@Override
		public String updateUserLoginTime(AdmTlUsermst admtluersmt)
				throws Exception {
			// TODO Auto-generated method stub
//			return admTlUsermstDao.updateUserLoginTime(admtluersmt);
			return userServiceApi.updateUserLoginTime(admtluersmt);
		}

		@Override
		public List<String[]> getEmployeeTotal(CommonFilter commonFilter)
				throws Exception {
			// TODO Auto-generated method stub
			return admTlUsermstDao.getEmpCount(commonFilter);
		}

		@Override
		public Workbook EmpCountExportExcel(CommonFilter commonFilter,
				JSONObject colmodel, String format) throws Exception {
			// TODO Auto-generated method stub
			return admTlUsermstDao.getEmpCountExportExcel(commonFilter,colmodel,format);
		}

		@Override
		public String getLoginIDAds(String email) throws Exception {
			// TODO Auto-generated method stub
			
			CommonMessage.debugMsg(" Email in side the service Impl"+email);
			return this.admTlUsermstDao.getLoginIDAds(email);
		}
		public String getDecryptPassword(String userId) throws Exception {
			// TODO Auto-generated method stub
			
			CommonMessage.debugMsg(" Email in side the service Impl"+userId);
			return this.admTlUsermstDao.getDecryptPassword(userId);
		}
}
