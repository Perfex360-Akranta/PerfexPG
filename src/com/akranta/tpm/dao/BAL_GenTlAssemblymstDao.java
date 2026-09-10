package com.akranta.tpm.dao;



import java.util.List;

import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlFunctionallocn;

import net.sf.json.JSONObject;

public interface BAL_GenTlAssemblymstDao {

	public abstract GenTlAssemblymst create(GenTlAssemblymst genTlAssemblymst,GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public abstract GenTlAssemblymst update(GenTlAssemblymst genTlAssemblymst,GenTlFunctionallocn genTlFunctionallocn) throws Exception;
	public abstract GenTlAssemblymst delete(String delemode, GenTlAssemblymst genTlAssemblymst) throws Exception;
	//public abstract GenTlAssemblymst select(String keyId) throws Exception;
	public abstract List<String[]> getGenTlAssemblymst(CommonFilter commonFilter);
	//public abstract List<String[]> getAssembly(CommonFilter commonFilter);
	//public abstract void gen_tl_assemblymst(String assemblyId);
	//public abstract List<String[]> getAllAssemblyRecall(String assemblyId);
	//public abstract GenTlAssemblymst assmformfill(String keyId);
	
	//added here by priyanka on 16/06/2026
	
	GenTlAssemblymst select(String keyId) throws Exception;
	
	void BAL_GenTlAssemblymstDaoImplJwt(String jwtToken);
	List<String[]> getAssemblyList(CommonFilter commonFilter) throws Exception;
	
	//List<String[]> getSubAssemblyList(CommonFilter commonFilter) throws Exception;

	/*
	 * JSONObject getAssemblyColModel() throws Exception;
	 * 
	 * int getAssemblyCount(CommonFilter commonFilter) throws Exception;
	 * 
	 * List<String[]> getAssemblyGridData(CommonFilter commonFilter) throws
	 * Exception;
	 */



}

