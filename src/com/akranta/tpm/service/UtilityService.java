package com.akranta.tpm.service;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.UtilityFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.GenTlToolsmst;

import java.util.List;

public interface UtilityService {
	public List<ComboBox> getUtilityIdCombo(String condSql,ComboFilter comboFilter ) throws Exception;
	public List<ComboBox> getCategoryCombo(String condSql,ComboFilter comboFilter ) throws Exception;
	
	public GenTlToolsmst create(GenTlToolsmst  newGenTlToolsmst,GenTlToolsmst oldGenTlToolsmst, UtilityFormBean ToolsFormBean ) throws ValidationExceptions, Exception;
	public GenTlToolsmst update(GenTlToolsmst newGenTlToolsmst,GenTlToolsmst oldGenTlToolsmst, UtilityFormBean ToolsFormBean )  throws Exception;
	public GenTlToolsmst delete(GenTlToolsmst genTlToolsmst) throws Exception;
	
	public GenTlToolsmst select(String Util) throws Exception;
	public GenTlToolsimg insertToolImg(GenTlToolsmst newGenTlToolsmst ) 	throws Exception;
	public GenTlToolsimg getToolsImage(GenTlToolsmst genTlToolsmst) throws NoDataFoundException, Exception;
	public GenTlToolsimg updateToolImg(GenTlToolsmst newGenTlToolsmst) 	throws Exception ;
}



