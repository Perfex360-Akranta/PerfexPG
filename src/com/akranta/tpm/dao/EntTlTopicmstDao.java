package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupdtl;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.EntTlTopicLinkRoledtl;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.GenTlRolemst;

public interface EntTlTopicmstDao {
	
	
	public abstract EntTlTopicmst create(EntTlTopicmst entTlTopicmst,EntTlTrainingarea newEntTlTrainingarea) throws BusinessApplicationExceptions, Exception;
	public abstract EntTlTopicmst update(EntTlTopicmst entTlTopicmst) throws Exception;
	public abstract EntTlTopicmst delete(EntTlTopicmst entTlTopicmst) throws Exception;
	
	public EntTlTopicmst select(EntTlTopicmst entTlTopicmst)throws Exception;
	public List<EntTlTopicmst> selectList(EntTlTopicmst entTlTopicmst)throws Exception;
	public abstract List<String[]> getTopicDet(CommonFilter commonFilter,String TopicKeyid);
	public Workbook TopicExportExcel(CommonFilter commonFilter, JSONObject colmodel,String format) throws SQLException, Exception ;
	public abstract EntTlTopicmst createTopic(EntTlTopicmst newEntTlTopicmst,EntTlTopicLinkRoledtl newEntTlLinkRoledtl) throws  Exception;
	public abstract void DeleteTopiclist(String keyid) throws BusinessApplicationExceptions, Exception;
	public abstract EntTlTopicmst updateTopic(EntTlTopicmst newEntTlTopicmst,EntTlTopicLinkRoledtl newEntTlLinkRoledtl,String trRoleid) throws Exception;
	public abstract EntTlTopicmst deleteTopicmst(EntTlTopicmst entTlTopicmst) throws Exception;
	public abstract List<String[]> getSql(String keyid) throws Exception;
	public abstract List<String[]> getselect(CommonFilter commonfilter) throws Exception;
	public abstract GenTlRolemst createunique(GenTlRolemst newgentlrolemst) throws Exception;
	public abstract GenTlRolemst updateunique(GenTlRolemst newgentlrolemst) throws Exception;
	public abstract List<String[]> getselectmain(CommonFilter commonFilter) throws Exception;
	public abstract GenTlRolemst getrolemain(String keyId);
	public abstract GenTlRolemst deleteunique(GenTlRolemst newgentlrolemst) throws Exception;
	public abstract int selectCount(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getselectdEmpRole(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getselectmaintarget(CommonFilter commonFilter) throws Exception;
	public abstract EntTlTargetgroupmst gettargetmain(String keyId);
	public abstract List<String[]> getselecttarget(CommonFilter commonFilter) throws Exception;
	public abstract EntTlTargetgroupmst createtarget(EntTlTargetgroupmst newEntTlTargetGroupmst) throws Exception;
	public abstract EntTlTargetgroupmst updatetarget(EntTlTargetgroupmst newEntTlTargetGroupmst) throws Exception;
	public abstract EntTlTargetgroupmst deletetarget(EntTlTargetgroupmst entTlTargetgroupmst) throws Exception;
	public abstract Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception, SQLException;
	public abstract EntTlTargetgroupdtl deletetargetdtl(List<EntTlTargetgroupdtl> newentTlTargetgroupdtl) throws Exception;
	public abstract GenTlRolemst deleteUPEmployee(GenTlRolemst newgentlrolemst) throws Exception;
	//
	Workbook getUniquePositionExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public String getLocationid(String locationid) throws Exception;
}

