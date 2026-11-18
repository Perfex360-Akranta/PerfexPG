package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;
import com.akranta.tpm.model.BAL_PcsTlLossphenomenamst;

public interface BAL_PcsTlLossphenomenamstDao {

	public abstract BAL_PcsTlLossphenomenamst create(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception;
	public abstract BAL_PcsTlLossphenomenamst update(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception;
	public abstract BAL_PcsTlLossphenomenamst delete(BAL_PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception;
	public abstract List<String[]> getComboTextContent(String phenId, String type)throws Exception;

}

