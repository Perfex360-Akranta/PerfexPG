/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlFunctionalLocnBean;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.BAL_GenTlFunctionallocnDao;

import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.BAL_GenTlFunctionallocnDaoImpl;
import com.akranta.tpm.service.BAL_GenTlFunctionalLocnService;
import com.akranta.tpm.utils.Validations;

public class BAL_GenTlFunctionalLocnServiceImpl implements BAL_GenTlFunctionalLocnService 
{
	
	private BAL_GenTlFunctionallocnDao genTlFunctionallocnDao ;
	
	public BAL_GenTlFunctionalLocnServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlFunctionallocnDao = new BAL_GenTlFunctionallocnDaoImpl(dbActionTemplate);
	}
	
	public void setPlmTlSparedtlDao(BAL_GenTlFunctionallocnDao genTlFunctionallocnDao)
	{
		this.genTlFunctionallocnDao = genTlFunctionallocnDao;
	}

	public List<BAL_GenTlFunctionallocn> save(List<BAL_GenTlFunctionallocn> existGenTlFunctionallocn) throws Exception 
			
	{
		System.out.println("Inside Service Impl Save Functoion");
		//for( GenTlFunctionallocn plmTlSparedtl:newGenTlFunctionallocn)
		//{
		//	System.out.println("newPlmTlSparedtl in save func"+newGenTlFunctionallocn.size());
			
		//}
		
		//fillValues(newGenTlFunctionallocn,existGenTlFunctionallocn,genTlFunctionalLocnBean);
	
		return  genTlFunctionallocnDao.save(existGenTlFunctionallocn);
	}

/*	private List<GenTlFunctionallocn> fillValues(List<GenTlFunctionallocn> newGenTlFunctionallocn,List<GenTlFunctionallocn> oldGenTlFunctionallocn,
			GenTlFunctionalLocnBean genTlFunctionallocnBean) 
	{
		
		for( GenTlFunctionallocn GenTlFunctionallocn : newGenTlFunctionallocn)
		{	
	
			System.out.println("Inside Service impl");
			GenTlFunctionallocn.setFnlnActive("Y");
			GenTlFunctionallocn.setFnlnElementtype("SPR");
			oldGenTlFunctionallocn.add(GenTlFunctionallocn);
		}
		return oldGenTlFunctionallocn;
	}
*/
	

	@Override
	public List<String[]> getSprs() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}