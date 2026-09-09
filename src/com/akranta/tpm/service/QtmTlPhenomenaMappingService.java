package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlPhenomenaMapping;

public interface QtmTlPhenomenaMappingService {

    public QtmTlPhenomenaMapping create(
            QtmTlPhenomenaMapping newPhenomenaMapping,
            QtmTlPhenomenaMapping existPhenomenaMapping)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception;

    public QtmTlPhenomenaMapping update(
            QtmTlPhenomenaMapping newPhenomenaMapping,
            QtmTlPhenomenaMapping existPhenomenaMapping)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception;

    // Find existing record by phenomena + section keyid
    public QtmTlPhenomenaMapping findByQphmAndSect(String qphmKeyid, String sectKeyid)
            throws Exception;

    // Update only phnm_active + phnm_modifiedon
    public void updateActiveStatus(QtmTlPhenomenaMapping mapping)
            throws Exception;

    public List<String[]> getPhenomenaDmtList(CommonFilter commonFilter)
            throws Exception;

    public void QtmTlPhenomenaMappingServiceImplJwt(String jwtToken);
}