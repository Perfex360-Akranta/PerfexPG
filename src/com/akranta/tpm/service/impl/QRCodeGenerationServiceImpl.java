package com.akranta.tpm.service.impl;

import java.util.List;

import com.akranta.tpm.dao.QRCodeGenerationDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.QRCodeGenerationDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.QRCodeGenerationService;

public class QRCodeGenerationServiceImpl implements QRCodeGenerationService {

	
	private QRCodeGenerationDao qrCodeGenerationDao;
	public QRCodeGenerationServiceImpl(DBActionTemplate dbActionTemplate)
	{
		qrCodeGenerationDao =  new QRCodeGenerationDaoImpl(dbActionTemplate);
	}	
	public List<String[]> getQrMasterGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return qrCodeGenerationDao.getQrMasterGrid(commonFilter);
	}

}
