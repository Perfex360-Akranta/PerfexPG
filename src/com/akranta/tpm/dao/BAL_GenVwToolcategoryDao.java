package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.BAL_GenVwToolcategory;

public interface BAL_GenVwToolcategoryDao {

	public abstract BAL_GenVwToolcategory create(BAL_GenVwToolcategory genVwToolcategory) throws Exception;
	public abstract BAL_GenVwToolcategory update(BAL_GenVwToolcategory genVwToolcategory) throws Exception;
	public abstract BAL_GenVwToolcategory delete(BAL_GenVwToolcategory genVwToolcategory) throws Exception;
	public List<BAL_GenVwToolcategory> getAllTool(BAL_GenVwToolcategory genVwToolcategory) throws Exception;
}

