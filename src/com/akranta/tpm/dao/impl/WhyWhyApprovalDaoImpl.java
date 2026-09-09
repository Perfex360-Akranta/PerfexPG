package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.CommonFilter;
import  com.akranta.tpm.dao.WhyWhyApprovalDao;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.WhywhyServiceApi;

import com.akranta.tpm.utils.CommonMessage;

public class WhyWhyApprovalDaoImpl implements WhyWhyApprovalDao{
	
	

	
	private DBActionTemplate dbActionTemplate; 
	private WhywhyServiceApi whywhyServiceApi;
	FunctionCallApi fnCallApi;

	public WhyWhyApprovalDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void WhywhyApprovalDaoImplJwt(String JwtToken) 
	{
		try{
			whywhyServiceApi = new WhywhyServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String[]> getAllWhywhyApproval(CommonFilter commonFilter,String roleId,String empId) throws Exception {
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			condParms += "EMPID="+empId+";";
			condParms += "ROLEID="+roleId+";";
			if(UIUtils.isValidKeyId(commonFilter.getRefdocid()))
				condParms += "REFDOCID="+commonFilter.getRefdocid()+";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("paramValues"+paramValues);
			List<String[]> dataList =  null;
			//String roleId=commonFilter.getRoleLevel();
			CommonMessage.debugMsg("roleIdroleIdroleId in DAO Impl "+roleId);
			
			String role = dbActionTemplate.getSingleValue("gen_tl_trade_role_link","gtrl_roleid","gtrl_roleid", roleId);
			//if(roleId.equals("AROL0131") || roleId.equals("AROL0132") || roleId.equals("AROL0133") || roleId.equals("AROL0134") || roleId.equals("AROL0135") || roleId.equals("AROL0003") )
			
			if(role != null )
			{	
				CommonMessage.debugMsg(" In side If roleIdroleIdroleId in DAO Impl "+roleId);

				commonFilter.setStatus("AI");
			//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("BDM_FN_WHYWHYAPPROVAL", paramValues);
			
			dataList = 	fnCallApi.callFunction("BDM_FN_WHYWHYAPPROVAL_SB", paramValues,3,true);
		}
		else{
			CommonMessage.debugMsg(" In side else roleIdroleIdroleId in DAO Impl "+roleId);
			commonFilter.setStatus("PC");
			//dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("BDM_FN_WHYWHYAPPROVALPC", paramValues);
			dataList = 	fnCallApi.callFunction("BDM_FN_WHYWHYAPPROVALPC_SB", paramValues,3,true);

		}
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}

	@Override
	public BdmTlWhywhymst update(BdmTlWhywhymst newBdmTlWhywhymst) throws Exception
			 {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
   StringBuffer sql= new StringBuffer();
		  sql.append("  UPDATE BDM_TL_WHYWHYMST SET WWMS_APPROLEID='"+newBdmTlWhywhymst.getWwmsApprRoleid()+ "', WWMS_APPROVEDBY='"+newBdmTlWhywhymst.getWwmsApprovedBy()+"',  ");
		  sql.append( " WWMS_APPROVEDON= "+"to_date('"+com.akranta.tpm.utils.CommonFunctions.getDate()+"','DD-Mon-YYYY'),");//'"+newBdmTlWhywhymst.getWwmsApprvedOn()+"', " );
		  sql.append( " WWMS_APPSTATUS='"+newBdmTlWhywhymst.getWwmsAppStatus()+"',WWMS_APPREMARKS='"+newBdmTlWhywhymst.getWwmsAppRemarks()+"'  ");
		 sql.append( "  WHERE WWMS_KEYID='"+newBdmTlWhywhymst.getWwmsKeyid()+"' ");
		 
		 System.out.print(" In sid the DAo Impl "+sql);
		 sqls.add(sql.toString());
		 dbActionTemplate.executeStatements(sqls);
		 return newBdmTlWhywhymst;
	}

	@Override
	public List<String[]> getSpentTime(String keyId) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dtlsList=new ArrayList();
		String sql="SELECT WWMS_PROBLEM,WWMS_AREA,WWMS_TIMESPENT FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID ='"+keyId+"'";
		
		dtlsList=dbActionTemplate.getDataList(sql);
		return dtlsList;
	}
}
