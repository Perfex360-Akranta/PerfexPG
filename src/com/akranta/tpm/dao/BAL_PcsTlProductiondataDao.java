package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlHtDetails;
import com.akranta.tpm.model.BAL_PcsTlHtProductionHistory;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.BAL_PcsTlProductiondata;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;
import com.akranta.tpm.model.PcsTlWorkorderlink;

public interface BAL_PcsTlProductiondataDao {

	public abstract BAL_PcsTlProductiondata create(BAL_PcsTlProductiondata pcsTlProductiondata) throws Exception;
	public abstract BAL_PcsTlProductiondata createNew(BAL_PcsTlProductiondata pcsTlProductiondata,BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl,BAL_PcsEntryBean pcsEntryBean) throws Exception;
	//public abstract BAL_PcsTlProductiondata createNew(BAL_PcsTlProductiondata newPscTlProductiondata,BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory,BAL_PcsTlHtDetails newPcsTlHtDetails) throws Exception;
	public abstract BAL_PcsTlHtDetails createNew(BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory,BAL_PcsTlHtDetails newPcsTlHtDetails,BAL_PcsTlHtDetails existPcsTlHtDetails) throws Exception;

	public abstract BAL_PcsTlProductiondata update(BAL_PcsTlProductiondata pcsTlProductiondata) throws Exception;
	public abstract BAL_PcsTlProductiondata delete(BAL_PcsTlProductiondata pcsTlProductiondata) throws Exception;
	public List<String[]> getPcsProductionCaptureGrid(String flid, String date, String Todate , String shiftid) throws Exception ;
	public abstract BAL_PcsTlProductiondata deleteProductiondata(String keyid)throws BusinessApplicationExceptions,Exception;
	public abstract BAL_PcsTlProductiondata removeShiftNoplan(String mstkeyId, String dtlkeyId,String cellId, String entrydate)throws BusinessApplicationExceptions,Exception;
	
}

