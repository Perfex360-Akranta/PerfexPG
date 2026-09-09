package com.akranta.tpm.service;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.SkillCheckListBean;
import com.akranta.tpm.model.EntTlChecklistdtl;
import com.akranta.tpm.model.EntTlChecklistmst;
import com.akranta.tpm.model.EntTlSkillChecklist;

public interface SkillCheckListService {

	
	public EntTlSkillChecklist create(EntTlSkillChecklist newEntTlSkillChecklist,EntTlSkillChecklist existEntTlSkillChecklist) throws Exception;

	public EntTlChecklistmst select(String keyid,String dtlId) throws Exception;

	public EntTlChecklistmst create(EntTlChecklistmst newEntTlChecklistmst,EntTlChecklistmst existEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception;

	public List<String[]> selectCheckList(String keyId)throws Exception;

	public List<String[]> getAllCheckList()throws Exception;

	public EntTlChecklistmst update(EntTlChecklistmst newEntTlChecklistmst,EntTlChecklistmst existEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception;

	public EntTlChecklistmst delete(EntTlChecklistmst newEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception;

	public List<String[]> selectCheckListMainGrid()throws Exception;

	public Workbook SkillExportExcel(JSONObject tblJSONObj, String format)throws Exception;

	public EntTlChecklistmst selectRank(String topicId, String rattingId)throws Exception;

	public EntTlChecklistdtl selectDtl(String keyId)throws Exception;

	public EntTlChecklistdtl deleteDetail(EntTlChecklistdtl newEntTlChecklistdtl)throws Exception;

	public List<String[]> selectCheckListRating(String topicId, String rattingId)throws Exception;

	
}
