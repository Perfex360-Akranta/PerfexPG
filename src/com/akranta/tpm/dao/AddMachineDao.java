package com.akranta.tpm.dao;

import java.util.List;

public interface AddMachineDao {
	public abstract List<String[]> getAddMachine(String machineID, String getEquipmentId);
	public abstract String geteqpGroup(String machineID);
	public abstract void CreateMachineArea(String addMachGridStr, String addMachId);
}
