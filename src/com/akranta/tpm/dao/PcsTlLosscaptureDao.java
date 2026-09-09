package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.upload.UploadException;

public interface PcsTlLosscaptureDao {

	public abstract PcsTlLosscapture create(PcsTlLosscapture pcsTlLosscapture) throws Exception;
	public abstract PcsTlLosscapture update(PcsTlLosscapture pcsTlLosscapture) throws Exception;
	public abstract PcsTlLosscapture delete(PcsTlLosscapture pcsTlLosscapture) throws Exception;
	public abstract String populateTempTable(String excelFileName,
			PcsTlOtherlossentry pcsTlOtherlossentry) throws UploadException;
	public abstract String updateLossVal(List<PcsTlOtherlossentry> otherLossList) throws BusinessApplicationExceptions, Exception;

}

