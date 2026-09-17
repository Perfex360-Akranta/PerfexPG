package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.bean.BAL_SprPckupFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.BAL_PlmTlSparedtl;


	public interface BAL_PlmTlSparedtlService {
		
		public BAL_PlmTlSparedtl delete(BAL_PlmTlSparedtl plmTlSparedtl) throws Exception;
		public List<String[]> getAllMultiSelectSpr() throws Exception;
		public List<ComboBox> getPartNo(String condSql, ComboFilter comboFilter) throws Exception;
		public List<String[]> getAllSprPickup(String standardId) throws Exception;
		public List<String[]> getAlldeleteSpr(String sprId)throws Exception;
		public List<String[]> getAllSprCount(List<BAL_PlmTlSparedtl> spareCountList, String fnlnParentid)throws Exception;
		public List<BAL_PlmTlSparedtl> save(List<BAL_PlmTlSparedtl> sparesPkupList,	List<BAL_PlmTlSparedtl> existPlmTlSparedtl,
				BAL_SprPckupFormBean sprPckupFormBean)throws Exception;
		public List<String[]> getDeleteAll(String standardId) throws Exception;
		public List<String[]> getSprNameSelectSpr(String sprmKeyid) throws Exception;
		public void BAL_PlmTlSparedtlServiceImplJwt(String JwtToken);
		
		

}


	

