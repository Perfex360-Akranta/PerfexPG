package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.dao.BAL_EquipmentControlPanelDao;
import com.akranta.tpm.dao.impl.BAL_EquipmentContolPanelDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_EquipmentControlPanelService;



public class BAL_EquipmentControlPanelServiceImpl implements BAL_EquipmentControlPanelService
{
private BAL_EquipmentControlPanelDao equipmentControlPanelDao;

public BAL_EquipmentControlPanelServiceImpl(DBActionTemplate dbActionTemplate)
{
	equipmentControlPanelDao =  new BAL_EquipmentContolPanelDaoImpl(dbActionTemplate);
}
public List<String[]> getAllEquipmentControlPanelService(CommonFilter commonFilter) throws Exception
{
	return this.equipmentControlPanelDao.getEquipmentControlPanelDao( commonFilter);
}
	
	
}