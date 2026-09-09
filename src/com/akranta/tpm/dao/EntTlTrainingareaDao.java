package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.EntTlSkillmstSql;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.FunctionalLocn;

public interface EntTlTrainingareaDao {
	public List<EntTlTrainingarea> getEntTlTrainingareaValues(EntTlTrainingarea entTlTrainingarea) throws Exception;
	public List<EntTlTrainingarea> getAllTrArea(EntTlTrainingarea EntTlTrainingarea) throws Exception;
	public EntTlTrainingarea create(EntTlTrainingarea newEntTlTrainingarea)throws BusinessApplicationExceptions, Exception;	
	public List<EntTlTrainingarea> createList(List<EntTlTrainingarea> newEntTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public EntTlTrainingarea update(EntTlTrainingarea newEntTlTrainingarea)throws BusinessApplicationExceptions, Exception;	
	public EntTlTrainingarea delete(EntTlTrainingarea EntTlTrainingarea)throws BusinessApplicationExceptions, Exception;
	public EntTlTrainingarea select(EntTlTrainingarea EntTlTrainingarea)throws Exception;
	public List<EntTlTrainingarea> selectList(EntTlTrainingarea EntTlTrainingarea)throws Exception;
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception;
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getChildElem(EntTlTrainingarea EntTlTrainingarea,String start,String end) throws Exception;
	public String getTotalCount(EntTlTrainingarea EntTlTrainingarea) throws Exception;
	public int getTrAreaLevel(EntTlTrainingarea EntTlTrainingarea)throws Exception;
	
	public int getConfigTrAreaLevel()throws Exception;
	public String deleteTopic(String topicid)throws Exception;
	
}

