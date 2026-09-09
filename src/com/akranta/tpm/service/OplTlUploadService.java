/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.OplTlLesson;
import com.akranta.tpm.model.OplTlMst;

public interface OplTlUploadService {	
	public OplTlMst createOPlUpload(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception;
	public OplTlMst updateOPlUpload(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception;

}
