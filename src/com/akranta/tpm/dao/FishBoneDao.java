package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.Fishbone;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
public interface FishBoneDao {
	public abstract Fishbone create(Fishbone fishbone) throws Exception;
	public abstract Fishbone update(Fishbone fishbone) throws Exception;
	public abstract Fishbone delete(Fishbone fishbone) throws Exception;

	public List<Fishbone> getAllFishBone(Fishbone fishbone) throws Exception;
	public List<Fishbone> getFishBoneValues(Fishbone fishbone) throws Exception;
	public  List<String[]>  getSearchNode(String searchNode,String originalId) throws Exception;
	public abstract List<String[]> getAllFishGrid(CommonFilter commonFilter) throws Exception;
	public abstract GenTlFishbonemst create(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst, FishBoneBean fishBoneBean)throws Exception;
	public abstract GenTlFishbonemst update(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst, FishBoneBean fishBoneBean)throws Exception;
	public abstract GenTlFishbonemst getFillControl(String fishboneKeyId)throws Exception;
	//public abstract List<Fishbone> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl)throws Exception;
	public abstract List<GenTlFishbonedtl> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl, GenTlFishbonemst genTlFishbonemst, String id, String masterId)throws Exception;
	//public abstract List<GenTlFishbonemst> getFishBoneValues(GenTlFishbonemst genTlFishbonemst)throws Exception;
	public abstract GenTlFishbonedtl createChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl, FishBoneBean fishBoneBean, String editval)throws Exception;
	public abstract GenTlFishbonedtl updateChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl, FishBoneBean fishBoneBean)throws Exception;
	public abstract GenTlFishbonemst deleteFishBoneMst(GenTlFishbonemst newGenTlFishbonemst)throws Exception;
	public abstract Workbook getFishBoneExcel(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;
	public abstract GenTlFishbonedtl deleteFishBoneChildEntry(GenTlFishbonedtl newGenTlFishbonedtl)throws Exception;
	public abstract List<String[]> getFBDetail(String keyid)throws Exception;
	public abstract Workbook getFBDetailForExcel(String keyid,JSONObject tblJSONObj,String format)throws Exception;
	public List<String[]> getKznSgnCount(CommonFilter commonFilter)throws Exception;
	
	// -- Vignesh
	
	public  Workbook getExcelreport(CommonFilter commonFilter,JSONObject colModel, String format) throws Exception;
	
	public abstract void FishBoneDaoImplJwt(String jwtToken);
}
