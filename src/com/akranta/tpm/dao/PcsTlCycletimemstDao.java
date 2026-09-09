package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlCycletimemst;

public interface PcsTlCycletimemstDao {

	public abstract PcsTlCycletimemst create(PcsTlCycletimemst pcsTlCycletimemst) throws Exception;
	public abstract PcsTlCycletimemst update(PcsTlCycletimemst pcsTlCycletimemst) throws Exception;
	public abstract PcsTlCycletimemst delete(PcsTlCycletimemst pcsTlCycletimemst) throws Exception;
	public abstract List<PcsTlCycletimemst> save(List<PcsTlCycletimemst> newPcsTlCycletimemst)throws Exception;
	public List<String[]> getPcsData()throws Exception;
	public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)throws Exception;
	public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams);
	public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public abstract void getcycleTimeUpdate(String product, String cycleTime)throws Exception;
	public abstract List<String[]> getPcsPrdData(String cellId,String machineId, String productId, String prodGroupId,GridParams gridParams)throws Exception;

}

