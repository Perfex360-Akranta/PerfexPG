package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.bean.BAL_SprPckupFormBean;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;

public interface BAL_PlmTlSparedtlDao {

	public abstract BAL_PlmTlSparedtl delete(BAL_PlmTlSparedtl plmTlSparedtl) throws Exception;
	public List<String[]> getMultiSelectSpr() throws Exception;
	public List<String[]> getSprPickup(String standardId) throws Exception ;
	public List<String[]> getDeleteSpr(String pspdKeyId)throws Exception ;
	public List<String[]> getAllSprCount(List<BAL_PlmTlSparedtl> spareCountList, String fnlnParentid)throws Exception;
	public List<BAL_PlmTlSparedtl> save(List<BAL_PlmTlSparedtl> sparesPkupList)throws Exception;
	public  List<String[]> getDeleteAll(String standardId) throws Exception;
	public List<String[]> getSprNameSelectSpr(String sprmKeyid);
	 public abstract void BAL_PlmTlSparedtlDaoImplJwt(String jwtToken);
	}
	



