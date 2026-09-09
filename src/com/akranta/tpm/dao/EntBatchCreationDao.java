package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;


public interface EntBatchCreationDao {
	public abstract EntBatchMst create(EntBatchMst entBatchMst) throws Exception;
	public abstract EntBatchMst update(EntBatchMst entBatchMst) throws Exception;
	public abstract EntBatchMst delete(EntBatchMst entBatchMst) throws Exception;
	public abstract EntBatchMst select(String BachKeyid  ) throws Exception;
	public abstract EntTlBatchFacultyLink createBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink) throws Exception;
	public abstract EntTlBatchFacultyLink updateBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink) throws Exception;
	public abstract EntTlBatchFacultyLink deleteBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink) throws Exception;
	public List<String[]> getBatchFacultyView(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception;
	public List<String[]> getBatchFacultyCount(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception;
	public abstract Workbook getBatch(JSONObject colmodel, String format)throws Exception;
	public List<String[]> getBatchEmployeeView(EntTlBatchEmployeeLink entTlBatchEmployeeLink,String batchId,String deptId,String empFilter)throws Exception;
	public abstract List<EntTlBatchEmployeeLink> createBatchEmployee(List<EntTlBatchEmployeeLink> list, String batchId) throws Exception;
	public abstract EntTlBatchEmployeeLink updateBatchEmployee(EntTlBatchEmployeeLink entTlBatchEmployeeLink) throws Exception;
	public abstract List<String[]> getBatchDao(String progId, String frmMonth) throws Exception;
	public abstract List<String[]> getFacultylist(String programKeyId)throws Exception;
	public void deleteByBstdKeyId(String bstdKeyid) throws Exception;
	

}
