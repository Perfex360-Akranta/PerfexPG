package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.QtmTlPhenomenaMappingDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.QtmTlPhenomenaMappingDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlPhenomenaMapping;
import com.akranta.tpm.service.QtmTlPhenomenaMappingService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;

public class QtmTlPhenomenaMappingServiceImpl implements QtmTlPhenomenaMappingService {

    private DBActionTemplate dbActionTemplate;
    private QtmTlPhenomenaMappingDao phenomenaMappingDao;

    public QtmTlPhenomenaMappingServiceImpl(DBActionTemplate dbActionTemplate) {
        this.dbActionTemplate = dbActionTemplate;
        phenomenaMappingDao   = new QtmTlPhenomenaMappingDaoImpl(dbActionTemplate);
    }

    @Override
    public void QtmTlPhenomenaMappingServiceImplJwt(String jwtToken) {
        try {
            phenomenaMappingDao.QtmTlPhenomenaMappingDaoImplJwt(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------------ create
    @Override
    public QtmTlPhenomenaMapping create(
            QtmTlPhenomenaMapping newPhenomenaMapping,
            QtmTlPhenomenaMapping existPhenomenaMapping)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception {

        newPhenomenaMapping = fillValues(newPhenomenaMapping, existPhenomenaMapping);
        return phenomenaMappingDao.create(newPhenomenaMapping);
    }

    // ------------------------------------------------------------------ update
    @Override
    public QtmTlPhenomenaMapping update(
            QtmTlPhenomenaMapping newPhenomenaMapping,
            QtmTlPhenomenaMapping existPhenomenaMapping)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception {

        newPhenomenaMapping = fillValues(newPhenomenaMapping, existPhenomenaMapping);
        return phenomenaMappingDao.update(newPhenomenaMapping);
    }

    // ----------------------------------------------- findByQphmAndSect (NEW)
    @Override
    public QtmTlPhenomenaMapping findByQphmAndSect(String qphmKeyid, String sectKeyid)
            throws Exception {
        return phenomenaMappingDao.findByQphmAndSect(qphmKeyid, sectKeyid);
    }

    // ----------------------------------------------- updateActiveStatus (NEW)
    @Override
    public void updateActiveStatus(QtmTlPhenomenaMapping mapping) throws Exception {
        // Set modifiedon timestamp before delegating to DAO
        mapping.setPhnmModifiedon(CommonFunctions.dateTimeNow());
        phenomenaMappingDao.updateActiveStatus(mapping);
    }

    // --------------------------------------------------------- fill defaults
    private QtmTlPhenomenaMapping fillValues(
            QtmTlPhenomenaMapping newObj,
            QtmTlPhenomenaMapping existObj) {

        CommonMessage.debugMsg("QtmTlPhenomenaMappingServiceImpl fillValues");
        String dateTime = CommonFunctions.dateTimeNow();
        CommonMessage.debugMsg("QtmTlPhenomenaMappingServiceImpl fillValues" + dateTime);

        newObj.setPhnmActive("Y");
        newObj.setPhnmCreatedon(dateTime);
        newObj.setPhnmModifiedon(dateTime);

        if (newObj.getPhnmKeyid() == null) {
            newObj.setPhnmKeyid(null);
        }

        if (isBlankOrDefault(newObj.getPhnmQphmKeyid())) {
            if (existObj != null && !isBlankOrDefault(existObj.getPhnmQphmKeyid())) {
                newObj.setPhnmQphmKeyid(existObj.getPhnmQphmKeyid());
            } else {
                newObj.setPhnmQphmKeyid("{}");
            }
        }

        if (isBlankOrDefault(newObj.getPhnmSectFlid())) {
            if (existObj != null && !isBlankOrDefault(existObj.getPhnmSectFlid())) {
                newObj.setPhnmSectFlid(existObj.getPhnmSectFlid());
            } else {
                newObj.setPhnmSectFlid("{}");
            }
        }

        if (isBlankOrDefault(newObj.getPhnmPhenomena())) {
            newObj.setPhnmPhenomena("{}");
        }

        if (newObj.getPhnmTempfield1() == null || newObj.getPhnmTempfield1().trim().isEmpty()) {
            newObj.setPhnmTempfield1("-");
        }
        if (newObj.getPhnmTempfield2() == null || newObj.getPhnmTempfield2().trim().isEmpty()) {
            newObj.setPhnmTempfield2("-");
        }
        if (newObj.getPhnmTempfield3() == null || newObj.getPhnmTempfield3().trim().isEmpty()) {
            newObj.setPhnmTempfield3("-");
        }

        CommonMessage.debugMsg("QtmTlPhenomenaMappingServiceImpl fillValues end");
        CommonMessage.debugMsg("  phnmSectFlid  = [" + newObj.getPhnmSectFlid()  + "]");
        CommonMessage.debugMsg("  phnmQphmKeyid = [" + newObj.getPhnmQphmKeyid() + "]");

        return newObj;
    }

    private boolean isBlankOrDefault(String value) {
        return value == null || value.trim().isEmpty() || "{}".equals(value.trim());
    }

    // ------------------------------------------------------------------ list
    @Override
    public List<String[]> getPhenomenaDmtList(CommonFilter commonFilter) throws Exception {
        return phenomenaMappingDao.getPhenomenaDmtList(commonFilter);
    }
}