package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

//import com.akranta.tpm.bean.GenTlAssemblymstBean;
import com.akranta.tpm.bean.GenTlHolidaymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlHolidaymst;

public interface GenTlHolidaymstService {

	public GenTlHolidaymst select(String keyid) throws Exception;

	public GenTlHolidaymst create(GenTlHolidaymst newGenTlHolidaymst,GenTlHolidaymst existGenTlAssemblymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception;

	public GenTlHolidaymst update(GenTlHolidaymst newGenTlHolidaymst,GenTlHolidaymst existGenTlAssemblymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception;

	public GenTlHolidaymst delete(GenTlHolidaymst newgenTlHolidaymst,GenTlHolidaymstBean genTlHolidaymstBean) throws Exception;

	public List<ComboBox> getYear(String string) throws Exception;

	public List<String[]> getAllHoliday() throws Exception;

	public List<String[]> selectAll(String keyId,String flag,String year) throws Exception;

	public GenTlHolidaymst flagSelect(String flag) throws Exception;

	public List<String[]> getHoliday() throws Exception;

	public Workbook HolidayExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format) throws Exception;

	public List<String[]> getAllHoliday(String flag, String year,GridParams gridParams)throws Exception;

	//public List<String[]> getAllHolidayCount(String flag, String year)throws Exception;
	
	public List<String[]> getAllHolidayCount(String flag, String year, String keyId) throws Exception;

	public List<String[]> selectHolidayFactory(String keyId, String flag,String year, GridParams gridParams)throws Exception;

	public Workbook HolidayFactoryExportExcel(String keyId, String flag,String year, GridParams gridParams, JSONObject tableModel, String format)throws Exception;
	


	 public void GenTlHolidaymstServiceImplJwt(String jwtToken);



}
