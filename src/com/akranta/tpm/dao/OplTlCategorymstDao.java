package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.OplTlCategorymst;

public interface OplTlCategorymstDao {

	public abstract OplTlCategorymst create(OplTlCategorymst oplTlCategorymst) throws Exception;
	public abstract OplTlCategorymst update(OplTlCategorymst oplTlCategorymst) throws Exception;
	
	public List<String[]> getPillarNames(CommonFilter commonfilter)	throws Exception;
	//public List<String[]> getCategoryNames(CommonFilter commonfilter,List <String> categoryKeyid) throws Exception;
	public List<Object> getOplCategories(String categoryKeyid) throws Exception;
	public OplTlCategorymst deleteCategoryNames(OplTlCategorymst oplTlCategorymst)	throws Exception;
	

}

