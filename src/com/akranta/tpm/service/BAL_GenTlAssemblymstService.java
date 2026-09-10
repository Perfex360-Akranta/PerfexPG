/**
 * Author:N Arun
 * Created on:25.11.2011
 */
package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_GenTlAssemblymstBean;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAssemblymst;

import net.sf.json.JSONObject;


public interface BAL_GenTlAssemblymstService {
	public GenTlAssemblymst create(GenTlAssemblymst newGenTlAssemblymst,GenTlAssemblymst oldGenTlAssemblymst,  BAL_GenTlAssemblymstBean genTlAssemblymstBean ) throws ValidationExceptions, Exception;
	public GenTlAssemblymst update(GenTlAssemblymst newGenTlAssemblymst,GenTlAssemblymst oldGenTlAssemblymst,  BAL_GenTlAssemblymstBean genTlAssemblymstBean )  throws Exception;
	public GenTlAssemblymst delete(String delemode, GenTlAssemblymst genTlAssemblymst) throws ValidationExceptions,Exception;
	public GenTlAssemblymst select(String keyid) throws Exception;
	//public List<ComboBox> getAssemblycombo(String condSql)  throws Exception;
	//public List<String[]> getAllAssemblyRecall(String assemblyId)  throws Exception;
	//public List<String[]> gen_tl_assemblymst(String assemblyId) throws Exception;
	//public void gen_tl_assemblymst (String assemblyId) throws Exception;
	//List<String[]> getAllAssemblyRecall(String assemblyId) throws Exception;
	//public GenTlAssemblymst assmformfill(String keyId);
	
	//added by priyanka on 16/06/2026
	
	// -- ADD THESE THREE METHODS to BAL_GenTlAssemblymstService --
	
	public void BAL_GenTlAssemblymstServiceImplJwt(String JwtToken);
	/*
	 * void initJwt(String jwtToken);
	 * 
	 * List<String[]> getAssemblyGridData(CommonParams commonParams) throws
	 * Exception; JSONObject getAssemblyColModel() throws Exception;
	 * 
	 * int getAssemblyGridCount(CommonParams commonParams) throws Exception;
	 */
	
	List<String[]> getAssemblyGridData(CommonFilter commonFilter) throws Exception;



}
