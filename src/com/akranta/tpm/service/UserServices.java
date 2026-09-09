package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AdmTlUserRollBean;
import com.akranta.tpm.bean.ChangePwdBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.UserBean;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdmTlUsermstDao;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsercustompages;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AdmTlUsersessions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;


public interface UserServices {
	
	
	public AdmTlUsermstDao getAdmTlUsermstDao();
	public AdmTlUsermst validateLogin(AdmTlUsermst admTlUsermst) throws BusinessApplicationExceptions, ValidationExceptions;
	public String getJwtToken(String KeyId);
	public AdmTlUsersessions insertUserSession(AdmTlUsersessions admTlUsersessions) throws Exception;
	public UserLoginDetailsBean getLoginUserDeatils(String userKeyid) throws Exception;
	public AdmTlUsermst saveNewPassword(AdmTlUsermst admTlUsermst,ChangePwdBean changePwdBean)throws BusinessApplicationExceptions, ValidationExceptions, Exception;
	public List<ComboBox> getCcnoComboList(String ccno,ComboFilter ccNo)throws Exception;
	public List<ComboBox> getProfidComboList(ComboFilter Profid)throws Exception;
	public AdmTlUsermst select(String keyid)throws Exception;
	public AdmTlUsermst create(AdmTlUsermst newAdmTlUsermst,
			AdmTlUsermst existAdmTlUsermst, UserBean userBean, String confPwd) throws Exception;
	public AdmTlUsermst update(AdmTlUsermst newAdmTlUsermst,
			AdmTlUsermst existAdmTlUsermst, UserBean userBean, String confPwd)throws Exception;
	public List<ComboBox> getUseridComboList(ComboFilter Userid)throws Exception;
	public List<String[]> getUser(GridParams gridParams)throws Exception;
	public AdmTlUsermst selectRecall(String userkeyid)throws Exception;
	public AdmTlUsermst delete(AdmTlUsermst newAdmTlUsermst)throws Exception;
	public Workbook UserExportExcel(JSONObject colmodel, String format,GridParams gridParams )throws Exception;
	 public List<String[]> selectCcNoRecall(String ccNo) throws Exception;
	 public List<ComboBox> getUserRollComboList(ComboFilter currentFilter, String empId)throws Exception ;
	List<String[]> getUserRoll(String userId) throws Exception;
	public AdmTlUserRoleLink CreateUserRoll(
			AdmTlUserRoleLink newAdmTlUserRoleLink,
			AdmTlUserRoleLink existAdmTlUserRoleLink,
			AdmTlUserRollBean admTlUserRollBean)throws Exception;
	public AdmTlUserRoleLink UpdateUserRoll(
			AdmTlUserRoleLink newAdmTlUserRoleLink,
			AdmTlUserRoleLink existAdmTlUserRoleLink,
			AdmTlUserRollBean admTlUserRollBean)throws Exception;
	public List<String[]> getUserRollS(String userId) throws Exception;
	public String DeleteUserRoll(String rollId,String userId)throws Exception;
	public List<String[]> selectCCName(String ccNo)throws Exception;
	public String sendPWDToMail(String userName)throws Exception;
	public void getCheckPasswordExpires(String user)throws BusinessApplicationExceptions,Exception;
	public AdmTlUsermst getUserByLogin(String userLoginId)throws Exception;
	public AdmTlUsercustompages saveSetHomePage(AdmTlUsercustompages newAdmTlUsercustompages)throws Exception;
	public AdmTlUsercustompages recallHomePage(String userId)throws Exception;
	public List<String[]> getMenuMasterData(String uscpPageuri)throws Exception;
	public String encryptCount(String loginAttmpts,int pin)throws BusinessApplicationExceptions;
	public String decriptCount(String loginAttmpts, int pin)throws BusinessApplicationExceptions;
	public void lockUserAcc(AdmTlUsermst admTlUsermst)throws Exception;
	public List<String []> getElementId(String userId,String roleId, String flid)throws Exception;
	public String checkFlidActive(String flid)throws Exception; // added flid InActive Check
	AdmTlLoginframework updatelgfrm(AdmTlLoginframework newadmTlLoginframework)
			throws Exception;
	AdmTlLoginframework Creatlgfrm(AdmTlLoginframework newadmTlLoginframework)
			throws ValidationExceptions, BusinessApplicationExceptions,
			Exception;
	List<String[]> getloginframeworkgrid() throws Exception;
	public com.akranta.tpm.model.AdmTlLoginframework getloginframeworkdata() throws Exception;
	public void updateloginattempt(String username)throws Exception;
	public String getloginattemptcount(String username)throws Exception;
	public void lockuser(String username)throws Exception;
	public void setloginattemptzero(String usrm_loginid)throws Exception;
	public void resetpwd(String userid)throws Exception;
	public List<String[]> getUserLoginDetailsFillGrid(CommonFilter commonFilter)throws Exception;
	public Workbook getuserDetailsReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;

	public String checkallowtochangepwd(String user)throws Exception;
	public List<String[]> getLocUser(GridParams gridParams,String location)throws Exception;
	public List<String[]> getLocUserMannul(GridParams gridParams,String flid)throws Exception;
	public Workbook LocUserExportExcel(JSONObject colmodel, String format,GridParams gridParams,String location )throws Exception;
	public abstract void resetpwd(List<String> loginIds) throws Exception;
	
	public String getForgetValidation(String userName) throws Exception;
	public List<String[]> getForgetEmpMailIds(String userName) throws Exception;
	public String updateUserLoginTime(AdmTlUsermst admtluersmt) throws Exception;
	public List<String[]> getEmployeeTotal(CommonFilter commonFilter) throws Exception;
	public Workbook EmpCountExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception;
	public String getLoginIDAds(String email) throws Exception;
	public String getDecryptPassword(String userId)throws Exception;
	public void UserServiceImplJwt(String JwtToken);

}
