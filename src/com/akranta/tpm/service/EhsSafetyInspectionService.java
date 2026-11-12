package com.akranta.tpm.service;

import java.util.List;


import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EhsTlInspectiondtl;
import com.akranta.tpm.model.EhsTlInspectionmst;

public interface EhsSafetyInspectionService {
	
	public List<String[]> getAllInspectionMst(CommonFilter commonFilter) throws Exception;
	public EhsTlInspectionmst select(String keyid)throws Exception;
	
	public abstract EhsTlInspectionmst create(EhsTlInspectionmst ehsTlInspectionmst) throws Exception;
	public abstract EhsTlInspectionmst update(EhsTlInspectionmst ehsTlInspectionmst) throws Exception;
	public abstract EhsTlInspectionmst delete(EhsTlInspectionmst ehsTlInspectionmst) throws Exception;
	
	public EhsTlInspectionmst create(EhsTlInspectionmst newEhsTlInspectionmst,EhsTlInspectionmst existEhsTlInspectionmst) throws ValidationExceptions,Exception; 
	public EhsTlInspectionmst update(EhsTlInspectionmst newEhsTlInspectionmst,EhsTlInspectionmst existEhsTlInspectionmst) throws ValidationExceptions,Exception; 

	
	public List<String[]> getAllInspectionDtl(CommonFilter commonFilter) throws Exception;

	public abstract EhsTlInspectiondtl create(EhsTlInspectiondtl ehsTlInspectiondtl) throws Exception;
	public abstract EhsTlInspectiondtl update(EhsTlInspectiondtl ehsTlInspectiondtl) throws Exception;
	public abstract EhsTlInspectiondtl delete(EhsTlInspectiondtl ehsTlInspectiondtl) throws Exception;
	
}
