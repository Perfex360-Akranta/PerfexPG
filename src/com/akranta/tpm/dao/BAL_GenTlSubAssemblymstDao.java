package com.akranta.tpm.dao;

import java.util.List;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;

public interface BAL_GenTlSubAssemblymstDao {
    BAL_GenTlSubAssemblymst create(BAL_GenTlSubAssemblymst obj,GenTlFunctionallocn genTlFunctionallocn) throws Exception;
    BAL_GenTlSubAssemblymst update(BAL_GenTlSubAssemblymst obj,GenTlFunctionallocn genTlFunctionallocn) throws Exception;
    BAL_GenTlSubAssemblymst delete(String delemode, BAL_GenTlSubAssemblymst obj) throws Exception;
    BAL_GenTlSubAssemblymst select(String keyid) throws Exception;
    
    String getMachineIdByAssembly(String assemblyId) throws Exception;
    List<String[]> getGenTlSubAssemblymst(CommonFilter commonFilter);
    
    void BAL_GenTlSubAssemblymstDaoImplJwt(String jwtToken);
    
    List<String[]> getSubAssemblyList(CommonFilter commonFilter) throws Exception;
}