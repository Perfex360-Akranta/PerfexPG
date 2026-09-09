package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EmpmailreportModel;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlLayoutfieldimg;

public interface GenTlEmployeemstDao {

	public abstract GenTlEmployeemst create(GenTlEmployeemst genTlEmployeemst) throws Exception;
	public abstract GenTlEmployeemst update(GenTlEmployeemst genTlEmployeemst) throws Exception;
	public abstract GenTlEmployeemst delete(GenTlEmployeemst genTlEmployeemst) throws Exception;
	public abstract GenTlEmployeemst select(String empmKeyid  ) throws Exception;
	public List<String[]> getAllEmployee()throws Exception;
	public List<String[]> getAllEMPMdepartment()throws Exception;
	public abstract GenTlEmployeedtl getselect(String empdKeyid) throws NoDataFoundException, SQLException, Exception;
	public GenTlEmployeeimg selectImg(String nodeId) throws Exception;
	public GenTlEmployeeimg getLayoutImg(GenTlEmployeeimg genTlEmployeeimg) throws Exception;
	public abstract List<String[]> getAllFactoryname(String Employeeid, String Funloclink) throws Exception;
	public abstract List<String[]> getAllSectionName(String Employeeid,String Funloclink) throws Exception;
	public abstract List<String[]> getAllLineName(String Employeeid,String Funloclink) throws Exception;
	public abstract List<String[]> getAllEquipmentName(String Employeeid,String Funloclink)throws Exception;
	public abstract List<String[]> getRoleEmpGrid(CommonFilter commonFilter)throws Exception;
	public List<String[]> empreport(CommonFilter commonFilter,GridParams gridparams)throws Exception;
	public Workbook getExcelreport(CommonFilter commonFilter,JSONObject json,String format)throws Exception;
	public List<EmpmailreportModel> updateEmail(List<EmpmailreportModel> empMailEnableList)throws Exception;
	public String EmployeeData(String empKeyid,String empName,String empPhoneNo,String empEmail) throws Exception;
	public abstract GenTlEmployeemst imagecreate(GenTlEmployeemst genTlEmployeemst,String userkeyid) throws Exception;
	public List<String[]> getEmpData(String userKeyid)throws Exception;
	public abstract void GenTlEmployeemstDaoImplJwt(String jwtToken);
	public GenTlEmployeemst insertEmployeeImage(GenTlEmployeemst genTlEmployeemst) throws Exception;

}
