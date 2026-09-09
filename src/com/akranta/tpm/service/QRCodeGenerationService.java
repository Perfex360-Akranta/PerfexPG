package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface QRCodeGenerationService {

	List<String[]> getQrMasterGrid(CommonFilter commonFilter) throws Exception;

}
