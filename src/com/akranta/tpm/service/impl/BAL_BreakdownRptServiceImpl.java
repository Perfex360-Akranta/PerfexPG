//<AuthoR -- Manikandan>
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import com.akranta.tpm.dao.BAL_BreakDownParetoDao;
//import com.akranta.tpm.dao.BAL_LossSummaryRptDao;
import com.akranta.tpm.dao.impl.BAL_BreakDownParetoDaoImpl;
//import com.akranta.tpm.dao.impl.LossSummaryRptDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_BreakdownRptService;

public class BAL_BreakdownRptServiceImpl implements BAL_BreakdownRptService {
private BAL_BreakDownParetoDao breakdownRptDao;
	
	
	public BAL_BreakdownRptServiceImpl(DBActionTemplate dbActionTemplate)
	{
		breakdownRptDao = new BAL_BreakDownParetoDaoImpl(dbActionTemplate);
	
	}
	@Override
	public List<String[]> getAllbkdpareto(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.breakdownRptDao.getAllbkdpareto( commonFilter);
	}

}
