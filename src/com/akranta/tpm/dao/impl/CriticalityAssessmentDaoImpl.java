package com.akranta.tpm.dao.impl;

import java.util.List;
import com.akranta.tpm.utils.CommonMessage;

import com.akranta.tpm.dao.CriticalityAssessmentDao;

public class CriticalityAssessmentDaoImpl implements CriticalityAssessmentDao {
	
private DBActionTemplate dbActionTemplate;
	
	public CriticalityAssessmentDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public List<String[]> getCriticality() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String sql1 ="";
	 sql.append("select 'Equipment Code','Equipment','Criteria','Productivity','Quality','Cost','Delivery','Safety','Operatability','Maintainability','Reliability','Total Ratings'" +
	 		" from dual union all" +
	 		" select 'Equipment Code','Equipment','Weightage','20','20','15','10','20','5','5','5','Total Ratings'" +
	 		" from dual union all"+
	 		" select '20009048','BSW-1 APFC','Bolt Loose','1','1','1','1','1','1','2','1','105'" +
	 		" from dual union all"+
	 		
	 		" select '20009049','BSW-2 APFC','Dust Inside Tube','2','2','2','1','2','2','2','2','205'" +
	 		" from dual union all"+
	 		" select '20009070','NUMERIC UPS-1 (NFL-1).','Air Entrapment','3','3','3','4','3','3','3','3','305'" +
	 		" from dual union all"+
	 		" select '20009072','SERVO STABLIZER (NFL-1).','Bolt loose','4','4','4','2','4','4','4','4','390'" +
	 		" from dual union all"+
	 		" select '20012323','BATTERY BANK NFL-1','Trimming Mark','1','2','2','2','1','1','2','2','155'" +
	 		" from dual union all"+
	 		" select '20012326','NEW OPEN SILO CONVEYORNO-1','Black Mark','1','2','1','5','4','4','4','1','250'" +
	 		" from dual union all"+
	 		" select '20012330','NFL-1 TOP OFFICE ROOM AHU','Discontinious Sprial','3','3','2','2','5','4','1','1','300'" +
	 		" from dual union all"+
	 		" select '30011230','PRIMARY CONECTIVITY SERVER','Short Weight','4','4','4','2','2','4','2','2','320'" +
	 		" from dual ");
	 sql1 = sql.toString();
	CommonMessage.debugMsg("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql1);
	CommonMessage.debugMsg("Grid value" + gridData.get(1));
	return gridData;
	}

}
