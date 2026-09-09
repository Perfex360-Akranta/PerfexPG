package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;

public interface OplDrilldownDao {

	List<String[]> getAllopldrill(CommonFilter commonFilter)throws Exception;

    public	Workbook getAllopldrillExl(CommonFilter commonFilter, JSONObject colmodel,String rptFormat) throws Exception;

}
