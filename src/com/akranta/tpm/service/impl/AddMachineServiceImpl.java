package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.dao.AddMachineDao;
import com.akranta.tpm.dao.impl.AddMachineDaoImpl;
import com.akranta.tpm.service.AddMachineService;

public class AddMachineServiceImpl implements AddMachineService {
	private AddMachineDao addMachineDao;
	public AddMachineServiceImpl(DBActionTemplate dbActionTemplate)
	{
		addMachineDao = new AddMachineDaoImpl(dbActionTemplate);
	}
	@Override
	public List<String[]> getAddMachine(String machineID, String getEquipmentId ) {
		// TODO Auto-generated method stub
		 return addMachineDao.getAddMachine(machineID,getEquipmentId);
	}

	@Override
	public String geteqpGroup(String machineID) {
		// TODO Auto-generated method stub
		return addMachineDao.geteqpGroup(machineID);
	}
	@Override
	public void CreateMachineArea(String addMachGridStr, String addMachId) {
		// TODO Auto-generated method stub
		addMachineDao.CreateMachineArea(addMachGridStr,addMachId);
	}

}
