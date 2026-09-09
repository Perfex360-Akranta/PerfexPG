package com.akranta.tpm.dao;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MocItem;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;

public interface MocItemDao {
    MocItem create(MocItem mocItem) throws Exception, BusinessApplicationExceptions;
    MocItem update(MocItem mocItem) throws Exception, BusinessApplicationExceptions;
    
    void MocItemDaoImplJwt(String jwtToken);
    List<String[]> getMocItemList(CommonFilter commonFilter) throws Exception;
    
    MocItem getById(String keyid) throws Exception;
    void delete(String keyid) throws Exception;
}