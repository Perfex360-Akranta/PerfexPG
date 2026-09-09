package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.SkillCheckListBean;
import com.akranta.tpm.model.EntTlChecklistdtl;
import com.akranta.tpm.model.EntTlChecklistmst;
import com.akranta.tpm.model.EntTlSkillChecklist;

public interface EntTlSkillChecklistDao {

	public abstract EntTlSkillChecklist create(EntTlSkillChecklist entTlSkillChecklist) throws Exception;
	public abstract EntTlSkillChecklist update(EntTlSkillChecklist entTlSkillChecklist) throws Exception;
	public abstract EntTlSkillChecklist delete(EntTlSkillChecklist entTlSkillChecklist) throws Exception;
	public abstract EntTlChecklistmst selectAll(String keyId, String dtlId) throws Exception;
	public abstract EntTlChecklistmst create(EntTlChecklistmst newEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception;
	public abstract List<String[]> selectCheckList(String keyId)throws Exception;
	public abstract List<String[]> getAllCheckList()throws Exception;
	public abstract EntTlChecklistmst update(EntTlChecklistmst newEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception;
	public abstract EntTlChecklistmst delete(EntTlChecklistmst newEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception;
	public abstract List<String[]> selectCheckListMainGrid()throws Exception;
	public abstract Workbook SkillExportExcel(JSONObject colModel,String rptFormat)throws Exception;
	public abstract EntTlChecklistmst selectRank(String topicId, String rattingId)throws Exception;
	public abstract EntTlChecklistdtl selectDtl(String keyId)throws Exception;
	public abstract EntTlChecklistdtl deleteDetail(EntTlChecklistdtl newEntTlChecklistdtl)throws Exception;
	public abstract List<String[]> selectCheckListRating(String topicId,String rattingId)throws Exception;

}

