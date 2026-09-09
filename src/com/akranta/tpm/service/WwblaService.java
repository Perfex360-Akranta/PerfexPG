package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;


public interface WwblaService {

	public abstract BdmTlWwblamst create(BdmTlWwblamst newBdmTlWwblamst, BdmTlWwblamst existBdmTlWwblamst)  throws Exception;
	public abstract BdmTlWwblamst update(BdmTlWwblamst newBdmTlWwblamst, BdmTlWwblamst existBdmTlWwblamst)  throws Exception;
	public abstract List<BdmTlWwbladtl> getWwblaValues(BdmTlWwbladtl bdmTlWwbladtl,BdmTlWwblamst bdmTlWwblamst ,String id, String masterId)throws Exception;
	public abstract GenTlFishbonemst getFillControl(String wwblaKeyid);
	public abstract List<String[]> getSearchNode(String searchNode,String originalId) throws Exception;
	public abstract BdmTlWwbladtl createChildEntry(BdmTlWwbladtl newBdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl,String editval)throws Exception;
	public abstract BdmTlWwbladtl updateChildEntry(BdmTlWwbladtl newBdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl)throws Exception;
	public abstract BdmTlWwbladtl deleteWWBLAChildEntry(BdmTlWwbladtl newBdmTlWwbladtl)throws Exception;
	public abstract List<String[]> WwblachildData(String dtlId)throws Exception;
	public abstract List<String[]> getAllWwblaGrid(CommonFilter commonFilter)throws Exception;
	public abstract Workbook getWwblaExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
	public abstract BdmTlWwblamst getWwbla(String keyid) throws Exception;
	public abstract BdmTlWwblamst delete(BdmTlWwblamst newBdmTlWwblamst) throws Exception;
	public void WwblaServiceImplJwt(String JwtToken);
	
	
}