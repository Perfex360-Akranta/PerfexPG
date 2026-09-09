	package com.akranta.tpm.service;

	import java.util.List;
	import com.akranta.tpm.Exceptions.ValidationExceptions;
	import com.akranta.tpm.bean.HorizontalDeploymentBean;
import com.akranta.tpm.model.KznTlHdmst;

	public interface KznTlHdmstService {
		
		public KznTlHdmst delete(KznTlHdmst kznTlHdmst) throws Exception;
		public List<String[]> getAllKznHdDtls(String kaizenId,String sectId,String mchId, String cellId, String dmtid, String dmtlevel) throws Exception;
		public List<KznTlHdmst> save(List<KznTlHdmst> kznTlHdmstList,List<KznTlHdmst> existKznTlHdmst,HorizontalDeploymentBean horizontalDeploymentBean)throws Exception,ValidationExceptions;
		public List<String[]> select(String khdmKaizenid)throws Exception;
		public List<String[]> FillControlData(String keyid)throws Exception;
		public List<KznTlHdmst> delete(List<KznTlHdmst> kznHdmstList)throws Exception;
		public String getcellid(String cellid)throws Exception;
				
	}
