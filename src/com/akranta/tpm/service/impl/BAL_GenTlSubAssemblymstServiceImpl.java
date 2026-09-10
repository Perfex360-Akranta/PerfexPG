package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlSubAssemblymstBean;
import com.akranta.tpm.dao.BAL_GenTlSubAssemblymstDao;
import com.akranta.tpm.dao.impl.BAL_SubAssemblyDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.BAL_GenTlSubAssemblymstService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

import com.akranta.tpm.service.api.BALBreakDownDropdownServiceApi;

public class BAL_GenTlSubAssemblymstServiceImpl implements BAL_GenTlSubAssemblymstService {

    private final BAL_GenTlSubAssemblymstDao genTlSubAssemblymstDao;
    private final Validations validations;
    
    private BALBreakDownDropdownServiceApi serviceApi;
    
    private String lastMachineId;

    public BAL_GenTlSubAssemblymstServiceImpl(DBActionTemplate dbActionTemplate) {
        this.genTlSubAssemblymstDao = new BAL_SubAssemblyDaoImpl(dbActionTemplate);
        this.validations = new Validations();
    }

    @Override
    public BAL_GenTlSubAssemblymst create(BAL_GenTlSubAssemblymst newObj, BAL_GenTlSubAssemblymst oldObj,
                                      BAL_GenTlSubAssemblymstBean bean,GenTlFunctionallocn genTlFunctionallocn)
            throws ValidationExceptions, Exception {
        //validations.validate(newObj, "subassemblycreation", "create");
        fillValues(newObj, oldObj);
        //return genTlSubAssemblymstDao.create(newObj,genTlFunctionallocn);
        return serviceApi.saveSubAssemblyApi(newObj, genTlFunctionallocn);
    }

    @Override
    public BAL_GenTlSubAssemblymst update(BAL_GenTlSubAssemblymst newObj, BAL_GenTlSubAssemblymst oldObj,
                                      BAL_GenTlSubAssemblymstBean bean,GenTlFunctionallocn genTlFunctionallocn)
            throws Exception {
        //validations.validate(newObj, "subassemblycreation", "update");
        fillValues(newObj, oldObj);
        //return genTlSubAssemblymstDao.update(newObj,genTlFunctionallocn);
        return serviceApi.updateSubAssemblyApi(newObj, genTlFunctionallocn);
    }

    @Override
    public BAL_GenTlSubAssemblymst delete(String delemode, BAL_GenTlSubAssemblymst obj)
            throws ValidationExceptions, Exception {
        //return genTlSubAssemblymstDao.delete(delemode, obj);
        return serviceApi.deleteSubAssemblyApi(delemode, obj);
    }

    @Override
    public BAL_GenTlSubAssemblymst select(String keyid) throws Exception {
        //return genTlSubAssemblymstDao.select(keyid);
        String[] machineIdHolder = new String[1];
        BAL_GenTlSubAssemblymst result = serviceApi.selectSubAssemblyApi(keyid, machineIdHolder);
        this.lastMachineId = machineIdHolder[0];
        return result;
    }
    
    @Override
    public String getMachineIdByAssembly(String assemblyId) throws Exception {
        //return genTlSubAssemblymstDao.getMachineIdByAssembly(assemblyId);
        return lastMachineId != null ? lastMachineId : "";
    }
    
    public void BAL_GenTlSubAssemblymstServiceImpl(String JwtToken){
    	try{
    		genTlSubAssemblymstDao.BAL_GenTlSubAssemblymstDaoImplJwt(JwtToken);
    		
    		serviceApi = new BALBreakDownDropdownServiceApi(JwtToken);
    		//genTlAssemblymstServiceApi = new BAL_GenTlAssemblymstServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<String[]> getSubAssemblyGridData(CommonFilter commonFilter) throws Exception {
        return genTlSubAssemblymstDao.getSubAssemblyList(commonFilter);
    }

    private void fillValues(BAL_GenTlSubAssemblymst newObj, BAL_GenTlSubAssemblymst oldObj) {
        newObj.setSbamActive("Y");
        //String now = CommonFunctions.dateTimeNow();
        String now = CommonFunctions.pg_dateTimeNow();// added here by priyanka

        if (newObj.getSbamKeyid() == null)
            newObj.setSbamCreatedon(now);
        else
            newObj.setSbamCreatedon(oldObj != null ? oldObj.getSbamCreatedon() : now);

        newObj.setSbamModifiedon(now);
        System.out.println(newObj.getSbamCode()+"****code");

        if (newObj.getSbamCode()        == null) newObj.setSbamCode("{}");
        if (newObj.getSbamName()        == null) newObj.setSbamName("{}");
        if (newObj.getSbamDescription() == null) newObj.setSbamDescription("{}");
        System.out.println(newObj.getSbamCode()+"****code");
		/*
		 * if (newObj.setSbamRemarks(newObj.getSbamRemarks()) == null)
		 * newObj.setSbamRemarks("{}"); // keep null-safe
		 */      
        if (newObj.getSbamRemarks() == null) newObj.setSbamRemarks("{}");
        if (newObj.getSbamAssemblyid()  == null) newObj.setSbamAssemblyid("{}");
    }
}