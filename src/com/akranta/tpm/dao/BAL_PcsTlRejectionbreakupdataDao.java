package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.BAL_PcsTlRejectionbreakupdata;

public interface BAL_PcsTlRejectionbreakupdataDao {

	public abstract BAL_PcsTlRejectionbreakupdata create(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata,List<BAL_PcsTlRejectionbreakupdata> RejectionGridList) throws Exception;
	public abstract BAL_PcsTlRejectionbreakupdata update(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata,List<BAL_PcsTlRejectionbreakupdata> RejectionGridList) throws Exception;
	public abstract BAL_PcsTlRejectionbreakupdata delete(BAL_PcsTlRejectionbreakupdata pcsTlRejectionbreakupdata) throws Exception;
	public abstract BAL_PcsTlRejectionbreakupdata deleteRejectionbreakupdata(String keyid)throws BusinessApplicationExceptions,Exception;

}

