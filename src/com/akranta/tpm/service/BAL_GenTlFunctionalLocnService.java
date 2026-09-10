package com.akranta.tpm.service;

import java.util.List;
import com.akranta.tpm.bean.GenTlFunctionalLocnBean;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;



	public interface BAL_GenTlFunctionalLocnService 
	{
		
		public List<BAL_GenTlFunctionallocn> save(List<BAL_GenTlFunctionallocn> existGenTlFunctionallocn )throws Exception;
				

		public List<String[]> getSprs();
		
		

	}


	

