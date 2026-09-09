package com.akranta.tpm.dao;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.GenTlToolsmst;

public interface GenTlToolsmstDao {

	public abstract GenTlToolsmst create(GenTlToolsmst genTlToolsmst) throws Exception;
	public abstract GenTlToolsmst update(GenTlToolsmst genTlToolsmst) throws Exception;
	public abstract GenTlToolsmst delete(GenTlToolsmst genTlToolsmst) throws Exception;
	
	public GenTlToolsmst select(String Util) throws Exception;
	public GenTlToolsimg insertToolImg(GenTlToolsimg genTlToolsimg) 	throws Exception;
	public GenTlToolsimg getToolsImage(GenTlToolsimg genTlToolsimg) throws NoDataFoundException, Exception;
	public GenTlToolsimg updateToolImg(GenTlToolsimg genTlToolsimg) 	throws Exception ;
}

