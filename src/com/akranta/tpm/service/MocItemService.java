package com.akranta.tpm.service;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MocItem;
import com.akranta.tpm.Exceptions.ValidationExceptions;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;

public interface MocItemService {
    MocItem create(MocItem mocItem) throws Exception, ValidationExceptions, BusinessApplicationExceptions;
    MocItem update(MocItem mocItem) throws Exception, ValidationExceptions, BusinessApplicationExceptions;
    
    void MocItemServiceImplJwt(String jwtToken);
    
    List<String[]> getMocItemList(CommonFilter commonFilter) throws Exception;
    
    MocItem getById(String keyid) throws Exception;
    void delete(String keyid) throws Exception;
}