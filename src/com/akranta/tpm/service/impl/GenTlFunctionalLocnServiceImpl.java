/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GenTlFunctionalLocnBean;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlFunctionallocnDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.GenTlFunctionallocnDaoImpl;
import com.akranta.tpm.service.GenTlFunctionalLocnService;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlFunctionalLocnServiceImpl implements GenTlFunctionalLocnService 
{
	
	private GenTlFunctionallocnDao genTlFunctionallocnDao ;
	
	public GenTlFunctionalLocnServiceImpl(DBActionTemplate dbActionTemplate)
	{
		genTlFunctionallocnDao = new GenTlFunctionallocnDaoImpl(dbActionTemplate);
	}
	
	public void setPlmTlSparedtlDao(GenTlFunctionallocnDao genTlFunctionallocnDao)
	{
		this.genTlFunctionallocnDao = genTlFunctionallocnDao;
	}

	public List<GenTlFunctionallocn> save(List<GenTlFunctionallocn> existGenTlFunctionallocn) throws Exception 
			
	{
		CommonMessage.debugMsg("Inside Service Impl Save Functoion");
		//for( GenTlFunctionallocn plmTlSparedtl:newGenTlFunctionallocn)
		//{
		//	CommonMessage.debugMsg("newPlmTlSparedtl in save func"+newGenTlFunctionallocn.size());
			
		//}
		
		//fillValues(newGenTlFunctionallocn,existGenTlFunctionallocn,genTlFunctionalLocnBean);
	
		return  genTlFunctionallocnDao.save(existGenTlFunctionallocn);
	}

/*	private List<GenTlFunctionallocn> fillValues(List<GenTlFunctionallocn> newGenTlFunctionallocn,List<GenTlFunctionallocn> oldGenTlFunctionallocn,
			GenTlFunctionalLocnBean genTlFunctionallocnBean) 
	{
		
		for( GenTlFunctionallocn GenTlFunctionallocn : newGenTlFunctionallocn)
		{	
	
			CommonMessage.debugMsg("Inside Service impl");
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