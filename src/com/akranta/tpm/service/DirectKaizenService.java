package com.akranta.tpm.service;

import java.sql.SQLException;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.KznTlKaizenbankmst;

public interface DirectKaizenService {
	//*************DirectKaizen**************//
	public KznTlKaizenbankmst selectMasterKeyid(String keyid) throws NoDataFoundException, SQLException, Exception;
	public KznTlKaizenbankmst createDKaizen(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions;
	public KznTlKaizenbankmst updateDKaizen(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions;

	public void DirectKaizenServiceImplJwt(String JwtToken);
}
