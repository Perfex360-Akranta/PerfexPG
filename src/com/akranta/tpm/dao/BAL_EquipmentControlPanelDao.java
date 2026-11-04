package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_EquipmentControlPanelDao {
	
	public List<String[]> getEquipmentControlPanelDao (CommonFilter commonFilter) throws Exception;
 
}
