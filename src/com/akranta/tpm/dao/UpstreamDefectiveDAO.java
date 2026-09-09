package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.GenTlUpstreamdefectMst;

public interface UpstreamDefectiveDAO {

	public abstract List<String[]> getUpstreamGrid(CommonFilter commonFilter, String keyid)throws Exception;

	public abstract GenTlUpstreamdefect createUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,
	  GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect)throws Exception;

	public abstract GenTlUpstreamdefect updateUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,
	  GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect)throws Exception;

	public abstract GenTlUpstreamdefect getFillControlData(String keyid)throws Exception;

	public abstract List<String[]> getUpstreamFormGrid(CommonFilter commonFilter, String keyId)throws Exception;

	public abstract List<String[]> FillControlData(String keyid)throws Exception;

	public abstract GenTlUpstreamdefect deleteUpstreamDefect(GenTlUpstreamdefect newGenTlUpstreamdefect)throws Exception;

	public abstract Workbook getUpstreamDefectExcel(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;

	public abstract GenTlUpstreamdefectMst createNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,
			GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,UpstreamDefect upstreamDefect)throws Exception;

	public abstract GenTlUpstreamdefectMst updateNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,
			GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,GenTlUpstreamdefectDet newGenTlUpstreamdefectDet, UpstreamDefect upstreamDefect)throws Exception;

	public abstract GenTlUpstreamdefectMst getFillControlDatas(String FnlnId, String date, String keyid)throws Exception;

	public abstract GenTlUpstreamdefectMst deleteNewUpstreamDefect(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst)throws Exception;

	public abstract GenTlUpstreamdefectDet deleteNewUpstreamDefectDetails(GenTlUpstreamdefectDet newGenTlUpstreamdefectDet)throws Exception;
	
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception;
	
	public abstract void UpstreamDefectiveDaoImplJwt(String jwtToken);


}
