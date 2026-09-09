package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlJhactivitychartdtl;

public interface GenTlJhactivitychartdtlDao {

	public abstract GenTlJhactivitychartdtl create(GenTlJhactivitychartdtl genTlJhactivitychartdtl) throws Exception;
	public abstract GenTlJhactivitychartdtl update(GenTlJhactivitychartdtl genTlJhactivitychartdtl) throws Exception;
	public abstract GenTlJhactivitychartdtl delete(GenTlJhactivitychartdtl genTlJhactivitychartdtl) throws Exception;
	public abstract GenTlJhactivitychartdtl delete(
			List<GenTlJhactivitychartdtl> jhActDtlList) throws Exception;

}

