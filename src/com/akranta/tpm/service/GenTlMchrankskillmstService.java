package com.akranta.tpm.service;

import java.util.List;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlMchrankskillmstBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMchrankskillmst;
import com.akranta.tpm.model.PlmTlCriteriamst;

public interface GenTlMchrankskillmstService {

	GenTlMchrankskillmst getMchrankskill(String mchrankskillKeyId) throws Exception;
	public List<ComboBox> getIncidentTypeComboList(CommonFilter commonFilter) throws Exception;
	public List<String[]> getAllMchrankskill() throws Exception;
	public GenTlMchrankskillmst create(GenTlMchrankskillmst newGenTlMchrankskillmst,GenTlMchrankskillmst existGenTlMchrankskillmst,GenTlMchrankskillmstBean genTlMchrankskillmst) throws ValidationExceptions,Exception;
	public GenTlMchrankskillmst update(GenTlMchrankskillmst newGenTlMchrankskillmst,GenTlMchrankskillmst existGenTlMchrankskillmst,GenTlMchrankskillmstBean genTlMchrankskillmst)throws Exception;
	public GenTlMchrankskillmst delete(GenTlMchrankskillmst newGenTlMchrankskillmst)throws BusinessApplicationExceptions, Exception;
	public PlmTlCriteriamst create(PlmTlCriteriamst newePlmTlCriteriamst, PlmTlCriteriamst existPlmTlCriteriamst)throws Exception;
	public PlmTlCriteriamst update(PlmTlCriteriamst newePlmTlCriteriamst, PlmTlCriteriamst existPlmTlCriteriamst)throws Exception;
	public PlmTlCriteriamst delete(PlmTlCriteriamst newePlmTlCriteriamst)throws Exception;
	List<String[]> getCriteriaMstList(CommonFilter commonFilter)throws Exception;
}
