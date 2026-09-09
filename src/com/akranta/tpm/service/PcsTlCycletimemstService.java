package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsCycletimemstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlCycletimemst;

	public interface PcsTlCycletimemstService {
	
		public List<PcsTlCycletimemst> save(List<PcsTlCycletimemst> pcsTlCycletimemstList,	List<PcsTlCycletimemst> existPcsTlCycletimemst,
				PcsCycletimemstBean pcsCycletimemstBean)throws Exception;
		public List<String[]> getPcsData()throws Exception;
		public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)throws Exception;
		public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams);
		public List<ComboBox> getSubGroupCombo(String condSql)throws Exception;
		public List<ComboBox> getProductCombo(String condSql)throws Exception;
		public PcsTlCycletimemst delete(PcsTlCycletimemst newPcsTlCycletimemst)throws Exception;
		public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
		public void getcycleTimeUpdate(String product, String cycleTime)throws Exception;
		public List<String[]> getPcsPrdData(String cellId, String machineId,String productId, String prodGroupId, GridParams gridParams)throws Exception;
	
}


	

