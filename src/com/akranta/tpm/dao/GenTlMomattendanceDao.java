package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomattendance;

public interface GenTlMomattendanceDao {

	public abstract GenTlMomattendance createatt(GenTlMomattendance genTlMomattendance) throws Exception;
	public abstract GenTlMomattendance updateatt(GenTlMomattendance genTlMomattendance) throws Exception;
	public abstract GenTlMomattendance delete(GenTlMomattendance genTlMomattendance) throws Exception;
	
	public abstract List<String[]> getMomeetingAtt(CommonFilter commonFilter) throws Exception ;
	
}

