package com.akranta.tpm.bean;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class UserLoginDetailsBean {
	private String loginId;
	private String userName;
	private String employeeName;
	private String deptName;
	private String designation;
	private String lastLoggedOn;
	private String loginTime;
	private String password;
	private String defaultpwd;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLastLoggedOn() {
		return lastLoggedOn;
	}
	public void setLastLoggedOn(String lastLoggedOn) {
		this.lastLoggedOn = lastLoggedOn;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setDefaultpwd(String defaultpwd) {
		this.defaultpwd = defaultpwd;
	}
	public String getDefaultpwd() {
		return defaultpwd;
	}
	
	
	public static UserLoginDetailsBean fromJson(String json) {
		CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		JSONObject obj = JSONObject.fromObject(json);

		UserLoginDetailsBean bean = new UserLoginDetailsBean();

		bean.setLoginId(getJsonValue(obj, "loginId"));
		bean.setUserName(getJsonValue(obj, "userName"));
		bean.setEmployeeName(getJsonValue(obj, "employeeName"));
		bean.setDeptName(getJsonValue(obj, "deptName"));
		bean.setDesignation(getJsonValue(obj, "designation"));
		bean.setPassword(getJsonValue(obj, "password"));
		bean.setDefaultpwd(getJsonValue(obj, "defaultpwd"));
		bean.setLastLoggedOn(getJsonValue(obj, "lastLoggedOn"));
		bean.setLoginTime(getJsonValue(obj, "loginTime"));

		return bean;
	}

	// --- Helper method to handle null and "null" values properly ---
	private static String getJsonValue(JSONObject obj, String key) {
		if (!obj.has(key)) return null;
		Object val = obj.get(key);
		if (val == null || val.equals("null")) return null;
		String strVal = val.toString().trim();
		return "null".equalsIgnoreCase(strVal) ? null : strVal;
	}
}
