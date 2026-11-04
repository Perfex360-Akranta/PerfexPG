package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GenTlHolidaymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlHolidaymst;

public interface GenTlHolidaymstDao {

	public abstract GenTlHolidaymst create(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception;
	public abstract GenTlHolidaymst update(GenTlHolidaymst genTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception;
	public abstract GenTlHolidaymst delete(GenTlHolidaymst genTlHolidaymst) throws Exception;
	public abstract GenTlHolidaymst select(String keyid) throws Exception;
	public abstract List<String[]> getAllHoliday() throws Exception;
	public abstract List<String[]> selectAll(String keyId,String flag,String year) throws Exception;
	public abstract GenTlHolidaymst delete(GenTlHolidaymst newgenTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception;
	public abstract GenTlHolidaymst flagSelect(String flag) throws Exception;
	public abstract List<String[]> getHoliday() throws Exception;
	public abstract Workbook HolidayExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public abstract List<String[]> selectAll(String flag, String year,GridParams gridParams)throws Exception;
	//public abstract List<String[]> getAllHolidayCount(String flag, String year)throws Exception;
	public List<String[]> getAllHolidayCount(String flag, String year, String keyId) throws Exception;
	public abstract List<String[]> selectHolidayFactory(String keyId,String flag, String year, GridParams gridParams)throws Exception;
	public abstract Workbook HolidayFactoryExportExcel(String keyId,String flag, String year, GridParams gridParams,JSONObject tableModel, String format)throws Exception;
	
	public void GenTlHolidaymstDaoImplJwt(String JwtToken);


}

