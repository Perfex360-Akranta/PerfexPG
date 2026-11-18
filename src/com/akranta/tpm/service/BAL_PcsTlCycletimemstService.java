package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsCycletimemstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlCycletimemst;

	public interface BAL_PcsTlCycletimemstService {
	
		public List<BAL_PcsTlCycletimemst> save(List<BAL_PcsTlCycletimemst> pcsTlCycletimemstList,	List<BAL_PcsTlCycletimemst> existPcsTlCycletimemst,
				PcsCycletimemstBean pcsCycletimemstBean)throws Exception;
		public List<String[]> getPcsData()throws Exception;
		//public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)throws Exception;
		public List<String[]> getPcsEntryData(String cellId, String productId,GridParams gridParams)throws Exception;		
		//public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams);
		public int getTotalCountSql(String cellId,String productId,GridParams gridParams);		
		public List<ComboBox> getSubGroupCombo(String condSql)throws Exception;
		/*public List<ComboBox> getProductCombo(String condSql)throws Exception;*/
		public List<ComboBox> getCellCombo(ComboFilter comboFilter)throws Exception;
		public BAL_PcsTlCycletimemst delete(BAL_PcsTlCycletimemst newPcsTlCycletimemst)throws Exception;
		public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
		public void getcycleTimeUpdate(String product, String cycleTime)throws Exception;
	//	public List<String[]> getPcsPrdData(String cellId, String machineId,String productId, String prodGroupId, GridParams gridParams)throws Exception;
		public List<String[]> getPcsPrdData(String cellId,String productId, GridParams gridParams)throws Exception;
		public List<ComboBox> getProductCombo(ComboFilter currentFilter,
				String factId, String cellId) throws Exception;
		
		public void BAL_PcsTlCycletimemstServiceImplJwt(String jwtToken);

	
}


	

