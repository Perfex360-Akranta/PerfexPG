package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.GenTlUpstreamdefectMst;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;



public interface UpstreamDefectiveServices 
{
	public List<String[]> getUpstreamGrid(CommonFilter commonFilter, String keyid)throws Exception;

	public List<ComboBox> getFillcombobox(CommonFilter commonFilter,ComboFilter comboFilter)throws Exception;

	public GenTlUpstreamdefect createUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,
		   GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect)throws Exception;

	public GenTlUpstreamdefect updateUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,
		   GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect)throws Exception;
	
	public GenTlUpstreamdefect deleteUpstreamDefect(GenTlUpstreamdefect newGenTlUpstreamdefect)throws Exception;

	public GenTlUpstreamdefect getFillControlData(String keyid)throws Exception;

	public List<String[]> getUpstreamFormGrid(CommonFilter commonFilter, String keyId)throws Exception;

	public List<String[]> FillControlData(String keyid)throws Exception;

	public Workbook getUpstreamDefectExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;

	public GenTlUpstreamdefectMst createNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,GenTlUpstreamdefectDet newGenTlUpstreamdefectDet, UpstreamDefect upstreamDefect)throws Exception;

	public GenTlUpstreamdefectMst updateNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,GenTlUpstreamdefectDet newGenTlUpstreamdefectDet, UpstreamDefect upstreamDefect)throws Exception;

	public GenTlUpstreamdefectMst getFillControlDatas(String FnlnId, String date, String keyid)throws Exception;

	public GenTlUpstreamdefectMst deleteNewUpstreamDefect(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst)throws Exception;

	public GenTlUpstreamdefectDet deleteNewUpstreamDefectDetails(GenTlUpstreamdefectDet newGenTlUpstreamdefectDet)throws Exception;
	
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception;

	public void UpstreamDefectiveServicesImplJwt(String JwtToken);

	



	

	


	

	
}
