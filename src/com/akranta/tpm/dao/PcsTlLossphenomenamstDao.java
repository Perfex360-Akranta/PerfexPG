package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.PcsTlLossphenomenamst;

public interface PcsTlLossphenomenamstDao {

	public abstract PcsTlLossphenomenamst create(PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception;
	public abstract PcsTlLossphenomenamst update(PcsTlLossphenomenamst pcsTlLossphenomenamst) throws Exception;
	public abstract PcsTlLossphenomenamst delete(PcsTlLossphenomenamst pcsTlLossphenomenamst, String mstKeyid) throws Exception;
	public abstract List<String[]> getComboTextContent(String phenId, String type)throws Exception;
	

	    // NEW:
	public abstract    int countLinksForPhenomena(String mstKeyid) throws Exception;



}

