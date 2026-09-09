package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface QRCodeGenerationDao {

	List<String[]> getQrMasterGrid(CommonFilter commonFilter) throws Exception;

}
