package com.akranta.tpm.dao;

import com.akranta.tpm.model.PlmTlEquipmentlink;

public interface EntTlEquipmentlinkDao {

	public abstract PlmTlEquipmentlink create(PlmTlEquipmentlink plmTlEquipmentlink) throws Exception;
	public abstract PlmTlEquipmentlink update(PlmTlEquipmentlink plmTlEquipmentlink) throws Exception;
	public abstract PlmTlEquipmentlink delete(PlmTlEquipmentlink plmTlEquipmentlink) throws Exception;

}

