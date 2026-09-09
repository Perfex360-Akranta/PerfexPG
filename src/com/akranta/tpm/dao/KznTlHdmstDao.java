package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.KznTlHdmst;

public interface KznTlHdmstDao {

	public abstract KznTlHdmst delete(KznTlHdmst kznTlHdmst) throws Exception;
	public abstract List<String[]> getAllKznHdDtls(String kaizenId,String sectId,String mchId,String cellId, String dmtid, String dmtlevel)throws Exception;
	public abstract List<KznTlHdmst> save(List<KznTlHdmst> newKznTlHdmstList, String kaizenId)throws Exception;
	public abstract List<String[]> select(String khdmKaizenid)throws Exception;
	public abstract List<String[]> FillControlData(String keyid)throws Exception;
	public abstract List<KznTlHdmst> deletekzn(List<KznTlHdmst> kznHdmstList)throws Exception;
	public String getcellid(String cellid)throws Exception;		
}

