package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.CommonParams;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomGroupmst;

public interface GenTlMomGroupmstDao {

	public abstract GenTlMomGroupmst create(GenTlMomGroupmst genTlMomGroupmst) throws BusinessApplicationExceptions,Exception;
	public abstract GenTlMomGroupmst update(GenTlMomGroupmst genTlMomGroupmst) throws BusinessApplicationExceptions,Exception;
	public abstract GenTlMomGroupmst delete(GenTlMomGroupmst genTlMomGroupmst) throws BusinessApplicationExceptions,Exception;
	public abstract List<String[]> getEmpgroupCreationmstGridData(CommonFilter commonFilter,CommonParams commonparams)throws Exception;
	public abstract List<String[]> getGroupGridData(CommonFilter commonFilter,CommonParams commonparams)throws Exception;
	public abstract List<String[]> getEmpgroupViewGridData(GridParams gridparams)throws Exception;
	public abstract GenTlMomGroupmst getGridValues(String keyid)throws Exception;
	public abstract GenTlMomGroupmst RemoveGroupMember(String keyid) throws Exception;
	public abstract Workbook getempGroupDetailExcel(GridParams gridparams,JSONObject colModel, String format,String keyid)throws Exception;
	public abstract Workbook getempGroupViewExcel(GridParams gridparams,JSONObject colModel, String format)throws Exception;
	public abstract void GenTlMomGroupmstDaoImplJwt(String jwtToken);

}

