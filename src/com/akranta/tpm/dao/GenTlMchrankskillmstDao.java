package com.akranta.tpm.dao;

import java.util.List;
import com.akranta.tpm.model.GenTlMchrankskillmst;

public interface GenTlMchrankskillmstDao {

	public abstract GenTlMchrankskillmst create(GenTlMchrankskillmst genTlMchrankskillmst) throws Exception;
	public abstract GenTlMchrankskillmst update(GenTlMchrankskillmst genTlMchrankskillmst) throws Exception;
	public abstract GenTlMchrankskillmst delete(GenTlMchrankskillmst genTlMchrankskillmst) throws Exception;
	public List<String[]> getAllMchrankskill() throws Exception;
}

