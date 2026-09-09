package com.akranta.tpm.dao;


import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.AdmTlLoginframework;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;


public interface AdmTlUsermstDao {

	public abstract AdmTlUsermst create(AdmTlUsermst admTlUsermst) throws Exception;
	public abstract AdmTlUsermst update(AdmTlUsermst admTlUsermst) throws Exception;
	public abstract AdmTlUsermst delete(AdmTlUsermst admTlUsermst) throws Exception;
	public AdmTlUsermst validateUser(AdmTlUsermst admTlUsermst) throws ValidationExceptions, Exception;
	public UserLoginDetailsBean getLoginUserDetails(String userKeyid) throws Exception;
	public abstract AdmTlUsermst select(String keyid) throws Exception ;
	public abstract List<String[]> getUser(GridParams gridParams)throws Exception;
	public abstract AdmTlUsermst selectRecall(String userkeyid)throws Exception ;
	public List<String[]> getUserInfo(String userName) throws Exception;
	public abstract Workbook getUserExcel(JSONObject colmodel, String format, GridParams gridParams)throws Exception;
	List<String[]> selectCcnoRecall(String ccNo) throws Exception;
	public  String encriptPassword(String  password, int userPin) throws BusinessApplicationExceptions;
	public  String decryptPassword(String  password, int userPin) throws BusinessApplicationExceptions;
	public DBActionTemplate getDbActionTemplate();
	public abstract List<String[]> selectCCName(String ccNo)throws Exception;
	public abstract void getCheckPasswordExpires(String user)throws BusinessApplicationExceptions,Exception;
	public abstract AdmTlUsermst getUserByLogin(String userLoginId) throws NoDataFoundException, SQLException, Exception;
	public abstract void lockUserAcc(AdmTlUsermst admTlUsermst)throws Exception;
	public abstract List<ComboBox> fillComboValuesRole(ComboFilter userRole) throws Exception;
	
	
	
	public abstract String selectdefpaswrd() throws Exception;
	public abstract String decryptEDPassword(String password, int pin) throws BusinessApplicationExceptions;
	public abstract List<String []> getElementId(String userId,String roleId,String flid) throws Exception;
	public String checkFlidActive(String flid)throws Exception; // added flid InActive Check
	public abstract void resetpwd(String userid,
			AdmTlLoginframework admTlLoginframework) throws Exception;
	public abstract void updateloginattempt(String username)throws Exception;
	public abstract String getloginattemptcount(String username)throws Exception;
	public abstract void lockuser(String username)throws Exception;
	public abstract void loginattemptzero(String usrm_loginid)throws Exception;
	public abstract AdmTlUsermst updateforchangepwd(AdmTlUsermst admTlUsermst)throws Exception;
	public abstract List<String[]> getUserLoginDetailsFillGrid(
			CommonFilter commonFilter)throws Exception;
	public abstract Workbook getuserDetailsReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;
	
	public abstract String checkallowtochangepwd(String user)throws Exception;
	public abstract List<String[]> getLocUser(GridParams gridParams,String location)throws Exception;
	public abstract List<String[]> getLocUserMannul(GridParams gridParams,String flid)throws Exception;
	public abstract Workbook getLocUserExcel(JSONObject colmodel, String format, GridParams gridParams,String location)throws Exception;
	public abstract void resetpwd(List<String> loginIds) throws Exception;
	public String getForgetValidation(String loginId)throws Exception ;
	public List<String[]> getForgetEmpMailIds(String userName) throws Exception;
	public abstract String updateUserLoginTime(AdmTlUsermst admtluersmt) throws Exception;
	public abstract List<String[]> getEmpCount(CommonFilter commonFilter) throws Exception;
	public abstract Workbook getEmpCountExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception;
	//public abstract String getuserDetails(String email) throws Exception;
	public  String getLoginIDAds(String email) throws Exception;
	public abstract String getDecryptPassword(String userId) throws Exception;
	
	public abstract void AdmTlUsermstDaoImplJwt(String jwtToken);

}

