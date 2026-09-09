/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.KznTlCategorymst;
import com.akranta.tpm.model.KznTlSubcategorymst;


public interface KznTlCategorymstService {
	
	public KznTlCategorymst create(KznTlCategorymst newKznTlCategorymst,KznTlCategorymst existKznTlCategorymst,  KaizenFormBean kaizenFormBean ) throws ValidationExceptions, Exception;
	public KznTlCategorymst update(KznTlCategorymst newKznTlCategorymst,KznTlCategorymst oldKznTlCategorymst,  KaizenFormBean kaizenFormBean  )  throws Exception;
	public KznTlCategorymst deleteAllCategoryNames(KznTlCategorymst kznTlCategorymst) throws Exception;
	public List<Object> getKznCategories(String categoryKeyid) throws Exception;
	public List<String[]> getAllPillarNames( ) throws Exception;
	public List<Object> getAllSubCategory(String ksmKeyId)throws Exception;
	public KznTlSubcategorymst create(KznTlSubcategorymst newKznTlSubcategorymst,KznTlSubcategorymst existKznTlSubcategorymst,KaizenFormBean kaizenFormBean)throws ValidationExceptions, Exception;
	public KznTlSubcategorymst update(KznTlSubcategorymst newKznTlSubcategorymst,KznTlSubcategorymst existKznTlSubcategorymst,KaizenFormBean kaizenFormBean)throws ValidationExceptions, Exception;
	public KznTlSubcategorymst deleteAllSubcategoryNames(KznTlSubcategorymst kznTlSubcategorymst)throws Exception;
	
}