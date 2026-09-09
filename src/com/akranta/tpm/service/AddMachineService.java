package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONArray;

public interface AddMachineService {
	public String geteqpGroup(String machineID);
	public List<String[]> getAddMachine(String machineID, String getEquipmentId);
	public void CreateMachineArea(String addMachGridStr, String addMachId);
	
}
