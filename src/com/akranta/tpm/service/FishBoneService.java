package com.akranta.tpm.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.Fishbone;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;

public interface FishBoneService {
	public List<Fishbone> getAllFishBone(Fishbone fishbone) throws Exception;
	public List<Fishbone> getFishBoneValues(Fishbone fishbone) throws Exception;
	public List<String[]> getSearchNode(String searchNode,String originalId) throws Exception;
	public List<String[]> getAllFishGrid(CommonFilter commonFilter) throws Exception;
	public GenTlFishbonemst create(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst, FishBoneBean fishBoneBean)throws Exception;
	public GenTlFishbonemst update(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst, FishBoneBean fishBoneBean)throws Exception;
	public GenTlFishbonemst getFillControl(String fishboneKeyId)throws Exception;
	//public List<GenTlFishbonemst> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl)throws Exception;
	public List<GenTlFishbonedtl> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl, GenTlFishbonemst genTlFishbonemst, String id, String masterId)throws Exception;
	//public List<GenTlFishbonemst> getFishBoneValues(GenTlFishbonemst genTlFishbonemst)throws Exception;
	public GenTlFishbonedtl createChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl, FishBoneBean fishBoneBean, String editval)throws Exception;
	public GenTlFishbonedtl updateChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl, FishBoneBean fishBoneBean)throws Exception;
	public GenTlFishbonemst deleteFishBoneMst(GenTlFishbonemst newGenTlFishbonemst)throws Exception;
	public Workbook getFishBoneExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
	public GenTlFishbonedtl deleteFishBoneChildEntry(GenTlFishbonedtl newGenTlFishbonedtl)throws Exception;
	public List<String[]> getFBDetail(String keyid)throws Exception;
	public Workbook getFBDetailForExcel(String keyid, JSONObject colModel, String format)throws Exception;
	public List<String[]> getKznSgnCount(CommonFilter commonFilter)throws Exception;
	
	// -- Vignesh
	
	public  Workbook getExcelreport(CommonFilter commonFilter,JSONObject colModel, String format) throws Exception;
	
	public void FishBoneServiceImplJwt(String string);
	
}
