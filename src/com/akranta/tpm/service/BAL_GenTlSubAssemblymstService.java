package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlSubAssemblymstBean;
import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;

public interface BAL_GenTlSubAssemblymstService {
    BAL_GenTlSubAssemblymst create(BAL_GenTlSubAssemblymst newObj, BAL_GenTlSubAssemblymst oldObj,
                               BAL_GenTlSubAssemblymstBean bean,GenTlFunctionallocn genTlFunctionallocn)
            throws ValidationExceptions, Exception;

    BAL_GenTlSubAssemblymst update(BAL_GenTlSubAssemblymst newObj, BAL_GenTlSubAssemblymst oldObj,
                               BAL_GenTlSubAssemblymstBean bean,GenTlFunctionallocn genTlFunctionallocn)
            throws Exception;

    BAL_GenTlSubAssemblymst delete(String delemode, BAL_GenTlSubAssemblymst obj)
            throws ValidationExceptions, Exception;

    BAL_GenTlSubAssemblymst select(String keyid) throws Exception;
    
    String getMachineIdByAssembly(String assemblyId) throws Exception;
    
    public void BAL_GenTlSubAssemblymstServiceImpl(String JwtToken);
    
    List<String[]> getSubAssemblyGridData(CommonFilter commonFilter) throws Exception;
    
    //List<String[]> getSubAssemblyGridData(CommonFilter commonFilter) throws Exception;
}