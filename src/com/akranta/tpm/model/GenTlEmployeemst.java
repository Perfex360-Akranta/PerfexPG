package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlMommst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class GenTlEmployeemst {

	private  Object [] saveArray = null;  
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	private List<GenTlEmployeedtl> employeeDetail ;
	private List<GenTlEmployeeimg> employeeImg ;
	private GenTlFunctionallocn genTlFunctionallocn;
	public enum   tableFldConstants
	{
		keyid, name, code, employeetype, employeenumber, joineddate, departmentid
		, designationid, factoryid, isshiftincharge, sectionid, iscellmanager
		, cellid, tradeid, extensionphone, gender, mobile, email, personalinfo
		, remarks, issectionmanager, skillcategory, gradeid, isoperator
		, company, roleid, sbuid, embmenablemail, location
		, active, createdby, createdon, modifiedon
	}

	public GenTlEmployeemst()
	{
		setEmployeeDetail(new ArrayList<GenTlEmployeedtl> ());
		setEmployeeImg(new ArrayList<GenTlEmployeeimg> ());
		saveArray = new  Object [ 33 ];
	}
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}

	public void setEmployeeDetail(List<GenTlEmployeedtl> employeeDetail) {
		this.employeeDetail = employeeDetail;
	}
	
	public List<GenTlEmployeedtl> getEmployeeDetail() {
		
		return employeeDetail;
	}

	public List<GenTlEmployeeimg> getEmployeeImg() {
		return employeeImg;
	}

	public void setEmployeeImg(List<GenTlEmployeeimg> employeeImg) {
		this.employeeImg = employeeImg;
	}
	
	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEmpmKeyid(String empmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = empmKeyid;
	}

	public String getEmpmName() {
		return (String) saveArray[ tableFldConstants.name.ordinal() ];
	}

	public void setEmpmName(String empmName) {
		saveArray[ tableFldConstants.name.ordinal() ] = empmName;
	}

	public String getEmpmCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setEmpmCode(String empmCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = empmCode;
	}

	public String getEmpmEmployeetype() {
		return (String) saveArray[ tableFldConstants.employeetype.ordinal() ];
	}

	public void setEmpmEmployeetype(String empmEmployeetype) {
		saveArray[ tableFldConstants.employeetype.ordinal() ] = empmEmployeetype;
	}

	public String getEmpmEmployeenumber() {
		return (String) saveArray[ tableFldConstants.employeenumber.ordinal() ];
	}

	public void setEmpmEmployeenumber(String empmEmployeenumber) {
		saveArray[ tableFldConstants.employeenumber.ordinal() ] = empmEmployeenumber;
	}

	public String getEmpmJoineddate() {
		return (String) saveArray[ tableFldConstants.joineddate.ordinal() ];
	}

	public void setEmpmJoineddate(String empmJoineddate) {
		saveArray[ tableFldConstants.joineddate.ordinal() ] = empmJoineddate;
	}

	public String getEmpmDepartmentid() {
		return (String) saveArray[ tableFldConstants.departmentid.ordinal() ];
	}

	public void setEmpmDepartmentid(String empmDepartmentid) {
		saveArray[ tableFldConstants.departmentid.ordinal() ] = empmDepartmentid;
	}

	public String getEmpmDesignationid() {
		return (String) saveArray[ tableFldConstants.designationid.ordinal() ];
	}

	public void setEmpmDesignationid(String empmDesignationid) {
		saveArray[ tableFldConstants.designationid.ordinal() ] = empmDesignationid;
	}

	public String getEmpmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setEmpmFactoryid(String empmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = empmFactoryid;
	}

	public String getEmpmIsshiftincharge() {
		return (String) saveArray[ tableFldConstants.isshiftincharge.ordinal() ];
	}

	public void setEmpmIsshiftincharge(String empmIsshiftincharge) {
		saveArray[ tableFldConstants.isshiftincharge.ordinal() ] = empmIsshiftincharge;
	}

	public String getEmpmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setEmpmSectionid(String empmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = empmSectionid;
	}

	public String getEmpmIscellmanager() {
		return (String) saveArray[ tableFldConstants.iscellmanager.ordinal() ];
	}

	public void setEmpmIscellmanager(String empmIscellmanager) {
		saveArray[ tableFldConstants.iscellmanager.ordinal() ] = empmIscellmanager;
	}

	public String getEmpmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setEmpmCellid(String empmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = empmCellid;
	}

	public String getEmpmTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setEmpmTradeid(String empmTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = empmTradeid;
	}

	public String getEmpmExtensionphone() {
		return (String) saveArray[ tableFldConstants.extensionphone.ordinal() ];
	}

	public void setEmpmExtensionphone(String empmExtensionphone) {
		saveArray[ tableFldConstants.extensionphone.ordinal() ] = empmExtensionphone;
	}

	public String getEmpmGender() {
		return (String) saveArray[ tableFldConstants.gender.ordinal() ];
	}

	public void setEmpmGender(String empmGender) {
		saveArray[ tableFldConstants.gender.ordinal() ] = empmGender;
	}

	public String getEmpmMobile() {
		return (String) saveArray[ tableFldConstants.mobile.ordinal() ];
	}

	public void setEmpmMobile(String empmMobile) {
		saveArray[ tableFldConstants.mobile.ordinal() ] = empmMobile;
	}

	public String getEmpmEmail() {
		return (String) saveArray[ tableFldConstants.email.ordinal() ];
	}

	public void setEmpmEmail(String empmEmail) {
		saveArray[ tableFldConstants.email.ordinal() ] = empmEmail;
	}

	public String getEmpmPersonalinfo() {
		return (String) saveArray[ tableFldConstants.personalinfo.ordinal() ];
	}

	public void setEmpmPersonalinfo(String empmPersonalinfo) {
		saveArray[ tableFldConstants.personalinfo.ordinal() ] = empmPersonalinfo;
	}

	public String getEmpmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setEmpmRemarks(String empmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = empmRemarks;
	}

	public String getEmpmIssectionmanager() {
		return (String) saveArray[ tableFldConstants.issectionmanager.ordinal() ];
	}

	public void setEmpmIssectionmanager(String empmIssectionmanager) {
		saveArray[ tableFldConstants.issectionmanager.ordinal() ] = empmIssectionmanager;
	}

	public String getEmpmSkillcategory() {
		return (String) saveArray[ tableFldConstants.skillcategory.ordinal() ];
	}

	public void setEmpmSkillcategory(String empmSkillcategory) {
		saveArray[ tableFldConstants.skillcategory.ordinal() ] = empmSkillcategory;
	}

	public String getEmpmGradeid() {
		return (String) saveArray[ tableFldConstants.gradeid.ordinal() ];
	}

	public void setEmpmGradeid(String empmGradeid) {
		saveArray[ tableFldConstants.gradeid.ordinal() ] = empmGradeid;
	}

	public String getEmpmIsoperator() {
		return (String) saveArray[ tableFldConstants.isoperator.ordinal() ];
	}

	public void setEmpmIsoperator(String empmIsoperator) {
		saveArray[ tableFldConstants.isoperator.ordinal() ] = empmIsoperator;
	}

	public String getEmpmCompany() {
		return (String) saveArray[ tableFldConstants.company.ordinal() ];
	}

	public void setEmpmCompany(String empmCompany) {
		saveArray[ tableFldConstants.company.ordinal() ] = empmCompany;
	}

	public String getEmpmRoleid() {
		return (String) saveArray[ tableFldConstants.roleid.ordinal() ];
	}

	public void setEmpmRoleid(String empmEmpmRoleid) {
		saveArray[ tableFldConstants.roleid.ordinal() ] = empmEmpmRoleid;
	}

	public String getEmpmSbuId() {
		return (String) saveArray[ tableFldConstants.sbuid.ordinal() ];
	}

	public void setEmpmSbuId(String empmSbuId) {
		saveArray[ tableFldConstants.sbuid.ordinal() ] = empmSbuId;
	}

	public String getEmpmenablemail() {
		return (String) saveArray[ tableFldConstants.embmenablemail.ordinal() ];
	}

	public void setEmpmenablemail(String embmenablemail) {
		saveArray[ tableFldConstants.embmenablemail.ordinal() ] = embmenablemail;
	}

	public String getEmpmLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setEmpmLocation(String empmLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = empmLocation;
	}

	public String getEmpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEmpmActive(String empmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = empmActive;
	}

	public String getEmpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEmpmCreatedby(String empmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = empmCreatedby;
	}

	public String getEmpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEmpmCreatedon(String empmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = empmCreatedon;
	}

	public String getEmpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}
	public void setEmpmModifiedon(String empmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = empmModifiedon;
	}

	public GenTlFunctionallocn getGenTlFunctionallocn() {
		return genTlFunctionallocn;
	}
	
	
	 public String toJsonManual() {
		    StringBuilder sb = new StringBuilder();
		    sb.append("{");
		


		    boolean first = true;
		    for (tableFldConstants field : tableFldConstants.values()) {
		        int index = field.ordinal();
		        if (index < saveArray.length) {
		            if (!first) sb.append(",");
		            sb.append("\"").append(field.name()).append("\":");
		            Object val = saveArray[index];
		            if(field.name() == "keyid" && val == null) {
		            	sb.append("null");
		            }else if (val == null) {
		                sb.append("\"{}\"");
		            } else {
		                sb.append("\"").append(val.toString()).append("\"");
		            }
		            first = false;
		        }
		    }

		   
		    sb.append("}");
		    return sb.toString();
		}
	  
	  public static GenTlEmployeemst fromJson(String json) {
		  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

		  JSONObject obj = JSONObject.fromObject(json);

		  GenTlEmployeemst mst = new GenTlEmployeemst();
		  CommonMessage.debugMsg("RAW JSON Response: :"+json);

		    for (tableFldConstants field : tableFldConstants.values()) {
		    	String key = field.name();
		    	
		    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
		        String val = obj.optString(field.name(), null);
		        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
		        //mst.setValue(field, val != null && val.equals("null") ? null : val);
		        mst.setValue(field, val);
		    	
		    }
		    return mst;
		}
}

