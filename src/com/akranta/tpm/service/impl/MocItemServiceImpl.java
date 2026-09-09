package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MocItemDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.MocItemDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MocItem;
import com.akranta.tpm.service.MocItemService;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.Validations;

public class MocItemServiceImpl implements MocItemService {

    private MocItemDao mocItemDao;
    private Validations validation;

    // ← same pattern as KaizenBankServiceImpl constructor
    public MocItemServiceImpl(DBActionTemplate dbActionTemplate) {
        mocItemDao   = new MocItemDaoImpl(dbActionTemplate);
        validation   = new Validations();
    }

    @Override
    public MocItem create(MocItem mocItem)
            throws Exception, ValidationExceptions, BusinessApplicationExceptions {
        try {
            String validateFor = "create";
            CommonMessage.debugMsg("MocItem Validation validateFor: " + validateFor);
            //validation.validate(mocItem, "MocItemValidation", validateFor);
            fillValues(mocItem);
        } catch (BusinessApplicationExceptions e) {
            CommonMessage.debugMsg("e.getMessage() buss  " + e.getMessage());
            throw new BusinessApplicationExceptions(e.getMessage());
        } catch (ValidationExceptions e) {
            CommonMessage.debugMsg("e.getMessage() vali " + e.getMessage());
            throw new ValidationExceptions(e.getMessage());
        } catch (Exception e) {
            CommonMessage.debugMsg("e.getMessage() excep " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return mocItemDao.create(mocItem);
    }

    @Override
    public MocItem update(MocItem mocItem)
            throws Exception, ValidationExceptions, BusinessApplicationExceptions {
        try {
            String validateFor = "update";
            CommonMessage.debugMsg("MocItem Validation validateFor: " + validateFor);
            //validation.validate(mocItem, "MocItemValidation", validateFor);
            fillValues(mocItem);
        } catch (BusinessApplicationExceptions e) {
            CommonMessage.debugMsg("e.getMessage() buss  " + e.getMessage());
            throw new BusinessApplicationExceptions(e.getMessage());
        } catch (ValidationExceptions e) {
            CommonMessage.debugMsg("e.getMessage() vali " + e.getMessage());
            throw new ValidationExceptions(e.getMessage());
        } catch (Exception e) {
            CommonMessage.debugMsg("e.getMessage() excep " + e.getMessage());
            throw new Exception(e.getMessage());
        }
        return mocItemDao.update(mocItem);
    }
    
    @Override
    public List<String[]> getMocItemList(CommonFilter commonFilter) throws Exception {
        return mocItemDao.getMocItemList(commonFilter);
    }
    
    @Override
    public void MocItemServiceImplJwt(String jwtToken) {
        mocItemDao.MocItemDaoImplJwt(jwtToken);
    }

    
    private MocItem fillValues(MocItem mocItem) throws Exception {
        mocItem.setMocItmActive("Y");

        if (!UIUtils.isValidKeyId(mocItem.getMocItmItem()))
            mocItem.setMocItmItem("{}");

        if (!UIUtils.isValidKeyId(mocItem.getMocItmFlid()))
            mocItem.setMocItmFlid("{}");

        if (!UIUtils.isValidKeyId(mocItem.getMocItmSection()))
            mocItem.setMocItmSection("{}");

        if (!UIUtils.isValidKeyId(mocItem.getMocItmSorton()))
            mocItem.setMocItmSorton("0");

        if (!UIUtils.isValidKeyId(mocItem.getMocItmCreatedby()))
            mocItem.setMocItmCreatedby("{}");

        return mocItem;
    }
    
    @Override
    public MocItem getById(String keyid) throws Exception {
        return mocItemDao.getById(keyid);
    }

    @Override
    public void delete(String keyid) throws Exception {
        mocItemDao.delete(keyid);
    }
    
}