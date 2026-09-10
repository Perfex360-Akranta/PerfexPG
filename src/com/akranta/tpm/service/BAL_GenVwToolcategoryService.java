package com.akranta.tpm.service;

import java.util.List;

import com.akranta.tpm.model.BAL_GenVwToolcategory;

public interface BAL_GenVwToolcategoryService  {
	public List<BAL_GenVwToolcategory> getAllTool(BAL_GenVwToolcategory genVwToolcategory)throws Exception;
}
