package com.akranta.tpm.service;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BatchBean;
import com.akranta.tpm.bean.EntBatchEmployeeLinkBean;
import com.akranta.tpm.bean.EntTlBatchMstBean;

import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;

public interface EntBatchCreationService {
	public EntBatchMst create(EntBatchMst newEntBatchMst,EntBatchMst oldEntBatchMst,  BatchBean batchBean ) throws ValidationExceptions, Exception;
	public EntBatchMst update(EntBatchMst newEntBatchMst,EntBatchMst oldEntBatchMst,  BatchBean batchBean )  throws Exception;
	public EntBatchMst delete(EntBatchMst EntBatchMst) throws Exception;
	public EntBatchMst select(String Bachkeyid) throws Exception;
	public List<ComboBox> getBatchComboList(String batchProgKey, String frmMonth, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter, String batchId, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getVenueComboList(ComboFilter comboFilter, String flid) throws Exception;

	public EntTlBatchFacultyLink createBatchFaculty(EntTlBatchFacultyLink newEntTlBatchFacultyLink,EntTlBatchFacultyLink oldEntTlBatchFacultyLink,  EntTlBatchMstBean entTlBatchMstBean ) throws ValidationExceptions, Exception;
	public EntTlBatchFacultyLink updateBatchFaculty(EntTlBatchFacultyLink newEntTlBatchFacultyLink,EntTlBatchFacultyLink oldEntTlBatchFacultyLink,  EntTlBatchMstBean entTlBatchMstBean ) throws ValidationExceptions, Exception;
	public EntTlBatchFacultyLink deleteBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink) throws Exception;
	public List<String[]> getBatchFacultyView(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception;
	public List<String[]> getBatchFacultyCount(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception;

	

	public Workbook BatchExportExcel(JSONObject colmodel, String format)throws Exception;
	

	public List<String[]> getBatchEmployeeView(EntTlBatchEmployeeLink entTlBatchEmployeeLink ,String batchId,String deptId, String empFilter)throws Exception;
	//public EntTlBatchEmployeeLink updateBatchEmployee(EntTlBatchEmployeeLink newEntTlBatchEmployeeLink,EntTlBatchEmployeeLink existEntTlBatchEmployeeLink,EntBatchEmployeeLinkBean entBatchEmployeeLinkBean ) throws ValidationExceptions, Exception;
	public List<String[]> getBatch(String progId, String frmMonth)throws Exception;
	public List<ComboBox> getProgkeyidCombo(String consql, ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getspokekeyidCombo(String consql)throws Exception;
	public List<String[]> getFacultylist(String programKeyId)throws Exception;
	public List<EntTlBatchEmployeeLink> createBatchEmployee(String usrm_ccno,String batchId, List<EntTlBatchEmployeeLink> employeelinkList,
			EntBatchEmployeeLinkBean entBatchEmployeeLinkBean) throws Exception;
	public void deleteByBstdKeyId(String bstdKeyid) throws Exception;
	
	

}
