package com.akranta.tpm.service;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.TargetGrp;
import com.akranta.tpm.bean.Uniquepositionbean;
//import com.akranta.tpm.bean.Vocchecklistbean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTargetgroupdtl;
import com.akranta.tpm.model.EntTlTargetgroupmst;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlRolemst;

public interface EntTlTopicmstService {
	/*public List<EntTlSkillmst> getEntTlSkillmstValues(String factId,String DeptId) throws Exception;
	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception;
	public List<EntTlSkillmst> getAllSkill(EntTlSkillmst entTlSkillmst) throws Exception;
		
			
	public List<String []> getParentElem(String elemId) throws Exception;
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end) throws Exception;
	public String getTotalCount(List<String> childElem,String formfield) throws Exception;	
	
	public List<EntTlSkillmst> selectList(EntTlSkillmst entTlSkillmst)throws Exception;	
	public List<String[]> getSearchNode(String searchNode,String originalId) throws Exception;
	public List<String[]> getSearchSkillLevel(String searchNode) throws Exception;
	
	
	
	public String validateSkillLevel(EntTlTopicmst entTlTopicmst)throws Exception;
	public String validateDelSkillLevel(EntTlTopicmst entTlTopicmst)throws Exception;	*/
	
	public EntTlTopicmst delete(EntTlTopicmst entTlTopicmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception ;
	public EntTlTopicmst select(EntTlTopicmst entTlTopicmst) throws Exception ;	
	
	public EntTlTopicmst update(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst oldEntTlTopicmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public EntTlTopicmst create(EntTlTopicmst newEntTlTopicmst,EntTlTopicmst oldEntTlTopicmst,String trainingAreaParentId)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	
	public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception;
	
	public List<ComboBox> getEvaluationTypeComboList(CommonFilter commonFilter)throws Exception;	
	public List<ComboBox> getTopiTypeComboList(CommonFilter commonFilter)throws Exception;
	public List<ComboBox> getTopiParentComboList(CommonFilter commonFilter)throws Exception;
	
	public List<ComboBox> getLocationCombo() throws Exception ;
	public List<String[]> getselect(CommonFilter commonFilter) throws Exception;
	public GenTlRolemst createunique(GenTlRolemst newgentlrolemst,
			GenTlRolemst existGenTlRolemst, Uniquepositionbean uniquebean) throws Exception;
	public GenTlRolemst updateunique(GenTlRolemst newgentlrolemst,
			GenTlRolemst existGenTlRolemst, Uniquepositionbean uniquebean) throws ValidationExceptions, Exception;
	public List<String[]> getselectmain(CommonFilter commonFilter) throws Exception;
	//
	Workbook getUniquePositionExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	
	public GenTlRolemst getrolemain(String keyId);
	public GenTlRolemst deleteunique(GenTlRolemst newgentlrolemst) throws Exception;
	public List<String[]> getselectdEmpRole(CommonFilter commonFilter)throws Exception;
	public EntTlTargetgroupmst gettargetmain(String keyId);
	public List<String[]> getselecttarget(CommonFilter commonFilter) throws Exception;
	public List<String[]> getselectmaintarget(CommonFilter commonFilter) throws Exception;
	public EntTlTargetgroupmst createtarget(EntTlTargetgroupmst newEntTlTargetGroupmst,EntTlTargetgroupmst existEntTlTargetgroupmst,TargetGrp targetGrpbean) throws Exception;
	public EntTlTargetgroupmst updatetarget(EntTlTargetgroupmst newEntTlTargetGroupmst,EntTlTargetgroupmst existEntTlTargetgroupmst,TargetGrp targetGrpbean) throws Exception;
	public EntTlTargetgroupmst deletetarget(
			EntTlTargetgroupmst entTlTargetgroupmst,
			EntTlTargetgroupmst existEntTlTargetgroupmst,
			TargetGrp TargetGrpbean) throws Exception;
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception;
	 
	public EntTlTargetgroupdtl deletetargetdtl( List<EntTlTargetgroupdtl> newentTlTargetgroupdtl)throws Exception;
	public GenTlRolemst deleteUPEmployee(GenTlRolemst newgentlrolemst)throws Exception;
	public List<ComboBox> getCategoryComboList(CommonFilter commonFilter)throws Exception;
    public List<ComboBox> getDeliveryModeCombo(ComboFilter comboFilter)throws Exception;

}
