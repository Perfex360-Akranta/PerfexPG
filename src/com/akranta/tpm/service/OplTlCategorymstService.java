/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.OplTlCategorymst;


public interface OplTlCategorymstService {
	
	public OplTlCategorymst create(OplTlCategorymst newOplTlCategorymst,OplTlCategorymst oldOplTlCategorymst,  OplFormBean oplFormBean ) throws ValidationExceptions, Exception;
	public OplTlCategorymst update(OplTlCategorymst newOplTlCategorymst,OplTlCategorymst oldOplTlCategorymst,  OplFormBean oplFormBean )  throws Exception;
/*	public OplTlCategorymst select(String oplKeyid) throws Exception;
	public OplTlCategorymst recall(String sectionId)throws Exception;
*/	public List<String[]> getAllPillarNames(CommonFilter commonfilter) throws Exception;
//	public List<String[]> getAllCategoryNames(CommonFilter commonfilter,List<String> categoryKeyid) throws Exception;
	public OplTlCategorymst deleteAllCategoryNames(OplTlCategorymst oplTlCategorymst) throws Exception;
	public List<Object> getOplCategories(String categoryKeyid) throws Exception;


}
