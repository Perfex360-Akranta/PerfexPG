package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;

public interface ControlResponseDao {
	public List<String[]> controlResponseReport(CommonFilter commonFilter) throws Exception;
	
	public void controlResponseDaoImplJwt(String JwtToken);


}
