package com.akranta.tpm.dao;

import java.util.List;


import com.akranta.tpm.model.BAL_GenTlFunctionallocn;

public interface BAL_GenTlFunctionallocnDao {

	public abstract BAL_GenTlFunctionallocn create(BAL_GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public abstract BAL_GenTlFunctionallocn update(BAL_GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public abstract BAL_GenTlFunctionallocn delete(BAL_GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public List<BAL_GenTlFunctionallocn> save(List<BAL_GenTlFunctionallocn> existGenTlFunctionallocn) throws Exception;

}

