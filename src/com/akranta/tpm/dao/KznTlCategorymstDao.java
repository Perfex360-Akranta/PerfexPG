package com.akranta.tpm.dao;

import java.util.List;
import com.akranta.tpm.model.KznTlCategorymst;
import com.akranta.tpm.model.KznTlSubcategorymst;

public interface KznTlCategorymstDao {

	public abstract KznTlCategorymst create(KznTlCategorymst kznTlCategorymst) throws Exception;
	public abstract KznTlCategorymst update(KznTlCategorymst kznTlCategorymst) throws Exception;
	public abstract KznTlSubcategorymst create(KznTlSubcategorymst newKznTlSubcategorymst)throws Exception;
	public abstract KznTlSubcategorymst update(KznTlSubcategorymst newKznTlSubcategorymst)throws Exception;
	public List<String[]> getPillarNames()	throws Exception;
	public List<Object> getKznCategories(String categoryKeyid) throws Exception;
	public KznTlCategorymst deleteCategoryNames(KznTlCategorymst kznTlCategorymst)	throws Exception;
	public List<Object> getSubCategory(String ksmKeyId)throws Exception;
	public KznTlSubcategorymst deleteSubcategoryNames(KznTlSubcategorymst kznTlSubcategorymst)throws Exception;

}
