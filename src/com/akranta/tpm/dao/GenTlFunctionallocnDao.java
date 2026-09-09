package com.akranta.tpm.dao;

import java.util.List;


import com.akranta.tpm.model.GenTlFunctionallocn;

public interface GenTlFunctionallocnDao {

	public abstract GenTlFunctionallocn create(GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public abstract GenTlFunctionallocn update(GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public abstract GenTlFunctionallocn delete(GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public List<GenTlFunctionallocn> save(List<GenTlFunctionallocn> existGenTlFunctionallocn) throws Exception;

}

