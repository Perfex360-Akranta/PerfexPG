package com.akranta.tpm.service;

import java.util.List;
import com.akranta.tpm.bean.GenTlFunctionalLocnBean;
import com.akranta.tpm.model.GenTlFunctionallocn;



	public interface GenTlFunctionalLocnService 
	{
		
		public List<GenTlFunctionallocn> save(List<GenTlFunctionallocn> existGenTlFunctionallocn )throws Exception;
				

		public List<String[]> getSprs();
		
		

	}


	

