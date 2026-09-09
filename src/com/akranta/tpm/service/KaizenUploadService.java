package com.akranta.tpm.service;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.KznTlMst;

public interface KaizenUploadService {
	public KznTlMst updateKaizenUpload(KznTlMst newKznTlMst,KznTlMst oldKznTlMst,  KaizenFormBean kaizenFormBean, String apprvallevel,String status )  throws Exception;
	public KznTlMst createKaizenUpload(KznTlMst newKznTlMst,KznTlMst oldKznTlMst,  KaizenFormBean kaizenFormBean, String apprvallevel,String status ) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public KznTlMst select(String kznKeyid) throws Exception;

	public KznTlMst getkznImage(String fileName, String filePath, KznTlMst kznTlMst) throws NoDataFoundException, Exception;

	public String getkaizenTheme(String kznbKeyid) throws Exception;

	public String getkaizenBenefit(String kznbKeyid) throws Exception;

	public String getThemename(String benefit) throws Exception;

	public String getkaizenPcdqsme(String kznbKeyid) throws Exception;

	public String selectKznb(String kznbKeyid) throws Exception;

	

}
