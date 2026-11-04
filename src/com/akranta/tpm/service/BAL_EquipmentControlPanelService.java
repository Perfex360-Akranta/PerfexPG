package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface BAL_EquipmentControlPanelService {

	public  List<String[]> getAllEquipmentControlPanelService(CommonFilter commonFilter) throws Exception;
}
