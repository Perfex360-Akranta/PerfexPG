package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.dao.ControlResponseDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class ControlResponseDaoImpl implements ControlResponseDao{
	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;
	public ControlResponseDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
	}
	
	public void controlResponseDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<String[]> controlResponseReport(CommonFilter commonFilter)
			throws Exception {
		
		/*StringBuffer sql=new StringBuffer();
		List<String> params = null;

        sql.append("SELECT Carp_Keyid AS \"KeyID\",");
		sql.append("Carp_Processstep AS \"Process Step \",");
		sql.append("Carp_Kpov AS \"KPOV\" ,Carp_Uom AS \"UOM\",");
		sql.append("Carp_Speclimits As \"Spec Limits\",");
		sql.append("Carp_Controllimits  AS \"Control Limits\","); 
		sql.append("Carp_Measurementmethod AS \"Measurement\",");
		sql.append("Carp_Samplesize AS \"Sample Size\",");
		sql.append("Carp_Frequency AS \"Frequency\",");
		sql.append("Carp_Whomeasures AS \"Who Measures\",");
		sql.append("Carp_Whererecorded AS \"Where It Is\",");
		
		sql.append("Carp_Decisionrule AS \"Decision Rule To\",");
		sql.append("Carp_Informto AS \"Inform To\",");
		sql.append("Carp_Correctiveaction AS \"Corrective\"");
		sql.append(" FROM "); 
		sql.append("GEN_TL_CONTROLANDRESPONSEPLAN" );
		sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));

		CommonMessage.debugMsg("sql..." + sql);
		//List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		//CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
		
		*/
		
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_CONDITIONANADRESPONSE", paramValues);
			
			List<String[]> dataList =  fnCallApi.callFunction("QTM_FN_CONDITIONANADRESPONSE_SB", paramValues, 3, false);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;		
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
		
		
		
	}

}
