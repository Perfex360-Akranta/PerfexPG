package com.akranta.tpm.dao.impl;

import java.util.List;
import com.akranta.tpm.utils.CommonMessage;

import com.akranta.tpm.dao.ConditionalAppraisalDao;

public class ConditionalAppraisalDaoImpl implements ConditionalAppraisalDao {
	
	private DBActionTemplate dbActionTemplate;
	
	public ConditionalAppraisalDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		// TODO Auto-generated constructor stub
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public List<String[]> getAllConditional() throws Exception {
		StringBuffer sql = new StringBuffer();
		String sql1="";
		sql.append( "SELECT 'Component','Dimension','Checking Tool','Type Of Check','Ideal Condition','Actual Condition'");
		sql.append(	",'Action Required','Responsible','Target Date','Remarks','Status','Completed By','Completed Date'");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component1','Dimension1','Checking Tool1','Type Of Check1','Ideal Condition1','Actual Condition1'");
			sql.append(	",'Action Required1','ATPL-AT','01-Aug-2013','Remarks1','Pending','',''");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component2','Dimension2','Checking Tool2','Type Of Check2','Ideal Condition2','Actual Condition2'");
			sql.append(	",'Action Required2','ATPL-AT','01-Aug-2013','Remarks2','Completed','ATPL-AT ','01-Aug-2013'");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component3','Dimension3','Checking Tool3','Type Of Check3','Ideal Condition3','Actual Condition3'");
			sql.append(	",'Action Required3','ATPL-AT','01-Aug-2013','Remarks7','Pending','',''");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component4','Dimension4','Checking Tool4','Type Of Check4','Ideal Condition4','Actual Condition4'");
			sql.append(	",'Action Required4','ATPL-AT','01-Aug-2013','Remarks4','Completed','ATPL-AT ','01-Aug-2013'");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component5','Dimension5','Checking Tool5','Type Of Check5','Ideal Condition5','Actual Condition5'");
			sql.append(	",'Action Required5','ATPL-AT','01-Aug-2013','Remarks5','Pending','',''");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component6','Dimension6','Checking Tool6','Type Of Check6','Ideal Condition6','Actual Condition6'");
			sql.append(	",'Action Required6','ATPL-AT','01-Aug-2013','Remarks6','Pending','',''");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component7','Dimension7','Checking Tool7','Type Of Check7','Ideal Condition7','Actual Condition7'");
			sql.append(	",'Action Required7','ATPL-AT','01-Aug-2013','Remarks7','Completed','ATPL-AT ','01-Aug-2013'");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component8','Dimension8','Checking Tool8','Type Of Check8','Ideal Condition8','Actual Condition8'");
			sql.append(	",'Action Required8','ATPL-AT','01-Aug-2013','Remarks8','Pending','',''");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component9','Dimension9','Checking Tool9','Type Of Check9','Ideal Condition9','Actual Condition9'");
			sql.append(	",'Action Required9','ATPL-AT','01-Aug-2013','Remarks9','Completed','ATPL-AT ','01-Aug-2013'");
			sql.append(" FROM DUAL UNION ALL");
			sql.append( " SELECT 'Component10','Dimension10','Checking Tool10','Type Of Check10','Ideal Condition10','Actual Condition10'");
			sql.append(	",'Action Required10','ATPL-AT','01-Aug-2013','Remarks10','Pending','',''");
			sql.append(" FROM DUAL");
			/*sql.append(" Select '','2','3','4','5','6','','','','','','',''FROM DUAL UNION ALL");
			sql.append(" Select '','','','','','','','','','','','',''FROM DUAL UNION ALL");
			sql.append(" Select '','','','','','','','','','','','',''FROM DUAL UNION ALL");
			sql.append(" Select '','','','','','','','','','','','',''FROM DUAL UNION ALL");
			sql.append(" Select '','','','','','','','','','','','',''FROM DUAL ");*/
			sql1 = sql.toString();
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql1);
		CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
	}

}
