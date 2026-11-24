package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlCycletimemst;

public interface BAL_PcsTlCycletimemstDao {

	public abstract BAL_PcsTlCycletimemst create(BAL_PcsTlCycletimemst pcsTlCycletimemst) throws Exception;
	public abstract BAL_PcsTlCycletimemst update(BAL_PcsTlCycletimemst pcsTlCycletimemst) throws Exception;
	public abstract BAL_PcsTlCycletimemst delete(BAL_PcsTlCycletimemst pcsTlCycletimemst) throws Exception;
	public abstract List<BAL_PcsTlCycletimemst> save(List<BAL_PcsTlCycletimemst> newPcsTlCycletimemst)throws Exception;
	public List<String[]> getPcsData(String cellId)throws Exception;
	public List<String[]> getCellCycletimeData(String cellId, String machineId,
	        String productId, GridParams gridParams) throws Exception;
	//public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)throws Exception;
	//public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams);
	public List<String[]> getPcsEntryData(String cellId,String productId,GridParams gridParams)throws Exception;
	public int getTotalCountSql(String cellId,String productId,GridParams gridParams);
	public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public abstract void getcycleTimeUpdate(String product, String cycleTime)throws Exception;
//	public abstract List<String[]> getPcsPrdData(String cellId,String machineId, String productId, String prodGroupId,GridParams gridParams)throws Exception;
	public abstract List<String[]> getPcsPrdData(String cellId,String productId,GridParams gridParams)throws Exception;
	
	public abstract void BAL_PcsTlCycletimemstDaoImplJwt(String jwtToken); 
}

