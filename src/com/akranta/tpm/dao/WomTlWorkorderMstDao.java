package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.WomTlWorkorderMst;
import com.akranta.tpm.model.WomsTlTaskmst;

public interface WomTlWorkorderMstDao {

	public abstract WomTlWorkorderMst create(WomTlWorkorderMst womTlWorkorderMst,WomsTlTaskmst womsTlTaskmst ) throws Exception;
	public abstract WomTlWorkorderMst update(WomTlWorkorderMst womTlWorkorderMst,WomsTlTaskmst womsTlTaskmst ) throws Exception;
	public abstract WomTlWorkorderMst delete(WomTlWorkorderMst womTlWorkorderMst) throws Exception;

	public WomsTlTaskmst createTaskList(List<WomsTlTaskmst> womsTlTaskmstList) throws Exception ;
	
	public WomTlWorkorderMst select(String keyid) throws Exception ;
	public String allowUpdate(String womsKeyid, String type) throws Exception;
	public String getLocationBasedMandfield(String loginFlid) throws Exception;
	public String submitMorderToSap(String womsKey,String type) throws Exception;
	public  String deleteWO(WomTlWorkorderMst newWomTlWorkorderMst) throws Exception;
	public  String getLineNo(String tableName, String condId, String condIdField) throws Exception;
	public abstract List<String[]> getPrintHeaderData(String womsKey) throws Exception;
	public  String getSubContractCheck(String womsKey,String taskId) throws Exception;
	public  int getMatProvIndCnt(String womsKey,	String taskId) throws Exception;
	public  List<String[]> getSapFnlnDetail(String flid, String fnlnTxt) throws Exception;
	public abstract List<String[]> getDefSapFnln(String flid, String fnlnTxt) throws Exception;
}

