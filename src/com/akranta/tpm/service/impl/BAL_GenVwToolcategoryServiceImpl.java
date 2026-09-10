package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

//import com.akranta.tpm.dao.CliTlStandardsDao;
import com.akranta.tpm.dao.BAL_GenVwToolcategoryDao;
import com.akranta.tpm.dao.impl.BAL_GenVwToolcategoryDaoImpl;
import com.akranta.tpm.model.BAL_GenVwToolcategory;
import com.akranta.tpm.service.BAL_GenVwToolcategoryService;

public class BAL_GenVwToolcategoryServiceImpl implements BAL_GenVwToolcategoryService {
    
	private BAL_GenVwToolcategoryDao genVwToolcategoryDao;
	
	public BAL_GenVwToolcategoryServiceImpl(DBActionTemplate dbActionTemplate){
		
		genVwToolcategoryDao = new BAL_GenVwToolcategoryDaoImpl(dbActionTemplate);
		
	}
	public void setGenVwToolcategoryDao(BAL_GenVwToolcategoryDao genVwToolcategoryDao)
	{
		this.genVwToolcategoryDao = genVwToolcategoryDao;
	}
	public List<BAL_GenVwToolcategory> getAllTool(BAL_GenVwToolcategory genVwToolcategory)throws Exception {
		// TODO Auto-generated method stub
		return this.genVwToolcategoryDao.getAllTool(genVwToolcategory);
	}
 
}
