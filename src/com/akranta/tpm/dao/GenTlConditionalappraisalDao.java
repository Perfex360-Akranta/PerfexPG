package com.akranta.tpm.dao;


import java.util.List;

import com.akranta.tpm.model.GenTlConditionalappraisal;

public interface GenTlConditionalappraisalDao {

	public abstract GenTlConditionalappraisal create(GenTlConditionalappraisal genTlConditionalappraisal) throws Exception;
	public abstract GenTlConditionalappraisal update(GenTlConditionalappraisal genTlConditionalappraisal) throws Exception;
	public abstract GenTlConditionalappraisal delete(GenTlConditionalappraisal genTlConditionalappraisal) throws Exception;
	public List<String[]>getConditionalAppraisalGrid() throws Exception;
	public abstract GenTlConditionalappraisal getAllFillControl(String keyId)throws Exception;
	public abstract List<String[]> getfillgriddata()throws Exception;
}

