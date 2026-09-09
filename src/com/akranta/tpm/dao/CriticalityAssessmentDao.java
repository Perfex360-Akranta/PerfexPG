package com.akranta.tpm.dao;

import java.util.List;

public interface CriticalityAssessmentDao {
	
	public List<String[]> getCriticality() throws Exception; 

}
