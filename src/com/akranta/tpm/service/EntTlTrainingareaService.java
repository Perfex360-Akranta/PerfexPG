package com.akranta.tpm.service;

import java.io.FileInputStream;
import java.util.List;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.EntTlTrainingarea;

public interface EntTlTrainingareaService {
	public List<EntTlTrainingarea> getEntTlTrainingareaValues(EntTlTrainingarea entTlTrainingarea) throws Exception;	
	public List<EntTlTrainingarea> getAllTrArea(EntTlTrainingarea entTlTrainingarea) throws Exception;
	public EntTlTrainingarea create(EntTlTrainingarea newEntTlTrainingarea,EntTlTrainingarea oldEntTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<EntTlTrainingarea> createList(List<EntTlTrainingarea> newEntTlTrainingarea,List<EntTlTrainingarea> oldEntTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public EntTlTrainingarea update(EntTlTrainingarea newEntTlTrainingarea,EntTlTrainingarea oldEntTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception;		
	public EntTlTrainingarea delete(EntTlTrainingarea entTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public EntTlTrainingarea select(EntTlTrainingarea entTlTrainingarea)throws Exception;	
	public List<EntTlTrainingarea> selectList(EntTlTrainingarea entTlTrainingarea)throws Exception;	
	public List<String[]> getSearchNode(String searchNode,String originalId) throws Exception;
	public String validateTrAreaLevel(EntTlTrainingarea entTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public String validateDelTrAreaLevel(EntTlTrainingarea entTlTrainingarea)throws Exception;	
	public List<ComboBox> getParentComboList(CommonFilter commonFilter)throws Exception;
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getChildElem(EntTlTrainingarea entTlTrainingarea,String start,String end) throws Exception;
	public String getTotalCount(EntTlTrainingarea entTlTrainingarea) throws Exception;
	public List<ComboBox> getTrainingAreaCombo(String type) throws Exception ;
	public String deleteTopic(String topicid)throws Exception ;
}
