package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.dao.KKProjecPriorityDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KznTlDmcprojectprioritydtlSql;
import com.akranta.tpm.dao.sql.KznTlDmcprojectprioritymstSql;
import com.akranta.tpm.dao.sql.KznTlKkprojectprioritydtlSql;
import com.akranta.tpm.dao.sql.KznTlKkprojectprioritymstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlDmcprojectprioritydtl;
import com.akranta.tpm.model.KznTlDmcprojectprioritymst;
import com.akranta.tpm.model.KznTlKkprojectprioritydtl;
import com.akranta.tpm.model.KznTlKkprojectprioritymst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KKProjecPriorityDaoImpl implements KKProjecPriorityDao {

	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;

	public KKProjecPriorityDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void KKProjecPriorityDaoImplJwt(String JwtToken) 
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
	public List<String[]> getAllProjectPrioritisat(CommonFilter commonFilter)
			throws Exception {
						
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		//	String condParms = commonFilter == null ? null : FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			//String commonParams =commonFilter == null ? null : FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		    
			String WAVES = commonFilter.getColVal();
			condParms +="WAVES="+WAVES+";";
			condParms+="PROJECTNME="+commonFilter.getParamtype()+";";
		
			paramValues.add(condParms);			
			paramValues.add(commonParams);	        
			//List<String[]> dataList =   dbActionTemplate.processFunctionCalls("JHN_FN_PROJECTPRIORITYFUN", paramValues);	
			List<String[]> dataList =  fnCallApi.callFunction("JHN_FN_PROJECTPRIORITYFUN_SB", paramValues, 5, true);  

			if( commonFilter != null && commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 

				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 			
		
	}
	
	@Override
	public List<String[]> getAlldmcProjectPrioritisat(CommonFilter commonFilter)
			throws Exception {
			
		//String flid = commonFilter.getFlid();
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = commonFilter == null ? null : FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams =commonFilter == null ? null : FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);						
	
			List<String[]> dataList =   dbActionTemplate.processFunctionCalls("JHN_FN_DMCFIPPRIORITYFUN", paramValues);			

			if( commonFilter != null && commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 

				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 			
		
	}
	@Override
	public KznTlKkprojectprioritymst create(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst) throws Exception {
		CommonMessage.debugMsg("var k  value      "+newKznTlKkprojectprioritymst.getDetail().size());

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		String count = "select count(*) from KZN_TL_PRIORITY_PARAMAETER_MST WHERE KKPM_ACTIVE = 'Y'";
		String value = dbActionTemplate.getSingleValue(count);
		int counts = Integer.parseInt(value);
		CommonMessage.debugMsg("counts  /........"+counts);
		int k=0;
		try{			
			if(newKznTlKkprojectprioritymst.getMaster()!=null){
				KznTlKkprojectprioritydtl newKznTlKkprojectprioritydtl = new KznTlKkprojectprioritydtl();
				for(int i=0;i<newKznTlKkprojectprioritymst.getMaster().size();i++){
					KznTlKkprojectprioritymst newKznTlKkprojectprioritymst1=newKznTlKkprojectprioritymst.getMaster().get(i);
					KznTlKkprojectprioritymstSql newKkprojectprioritymstSql = new KznTlKkprojectprioritymstSql();
					KznTlKkprojectprioritydtlSql newKkprojectprioritydtlSql = new KznTlKkprojectprioritydtlSql();
					newKznTlKkprojectprioritymst1.setKppmCreatedby(newKznTlKkprojectprioritymst.getKppmCreatedby());
					fillvalues(newKznTlKkprojectprioritymst1);
					if(newKznTlKkprojectprioritymst1.getKppmKeyid() !=null){
						String projectscore = newKznTlKkprojectprioritymst1.getKppmProjectscore();
						int prjScore = Integer.parseInt(projectscore);
						String masterkeyid = newKznTlKkprojectprioritymst1.getKppmKeyid();
						String rank = newKznTlKkprojectprioritymst1.getKppmRank();
						String Approvedby = newKznTlKkprojectprioritymst1.getKppmApprovedby();
						sqls.add(KznTlKkprojectprioritymstSql.getUpdateMaster(prjScore,masterkeyid,Approvedby,rank));
						
					 }else{
						 newKznTlKkprojectprioritymst1.setKppmKeyid(dbActionTemplate.getSequenceNumber(KznTlKkprojectprioritymstSql.TBL_KZN_TL_KKPROJECTPRIORITYMST,15,"KKM","Y","DDMMYY")); // set the sequnce number							
						 sqls.add(KznTlKkprojectprioritymstSql.getInsertSql(newKkprojectprioritymstSql.getKppmDbFields(), newKznTlKkprojectprioritymst1.getSaveArray()));
					 }
				
					
					for(int j=0;j<counts;j++){
						CommonMessage.debugMsg("var k  value      "+newKznTlKkprojectprioritymst.getDetail().size());
						newKznTlKkprojectprioritydtl=newKznTlKkprojectprioritymst.getDetail().get(k);
						newKznTlKkprojectprioritydtl.setKppdKppmKeyid(newKznTlKkprojectprioritymst1.getKppmKeyid());
						newKznTlKkprojectprioritydtl.setKppdCreatedby(newKznTlKkprojectprioritymst1.getKppmCreatedby());
						fillvalues(newKznTlKkprojectprioritydtl);
						String existscore = "select kppd_score from KZN_TL_KKPROJECTPRIORITYDTL where kppd_kppm_keyid ='"+newKznTlKkprojectprioritydtl.getKppdKppmKeyid()+"' and kppd_kkpm_keyid='"+newKznTlKkprojectprioritydtl.getKppdKkpmKeyid()+"'";
						String detailscore = dbActionTemplate.getSingleValue(existscore);
						if(newKznTlKkprojectprioritydtl.getKppdKppmKeyid() !=null && newKznTlKkprojectprioritydtl.getKppdKkpmKeyid()!=null && detailscore!=null){
							String score = newKznTlKkprojectprioritydtl.getKppdScore();
							int Score = Integer.parseInt(score);
							String masterkeyid = newKznTlKkprojectprioritydtl.getKppdKppmKeyid();
							String parameterkeyid = newKznTlKkprojectprioritydtl.getKppdKkpmKeyid();
							sqls.add(KznTlKkprojectprioritydtlSql.getUpdateDetails(Score,masterkeyid,parameterkeyid));
						 }else{
							 newKznTlKkprojectprioritydtl.setKppdKeyid(dbActionTemplate.getSequenceNumber(KznTlKkprojectprioritydtlSql.TBL_KZN_TL_KKPROJECTPRIORITYDTL,15,"KKD","Y","DDMMYY")); // set the sequnce number
							 sqls.add(KznTlKkprojectprioritydtlSql.getInsertSql(newKkprojectprioritydtlSql.getKppdDbFields(), newKznTlKkprojectprioritydtl.getSaveArray()));
						 }
						k++;
					}
				}
			}
			CommonMessage.debugMsg("before Execute");
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg(" error  "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public KznTlDmcprojectprioritymst createdmcpp(KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst) throws Exception {
		CommonMessage.debugMsg("var k  value      "+newKznTlDmcprojectprioritymst.getDetail().size());

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		String count = "select count(*) from KZN_TL_PRIORITY_PARAMAETER_MST WHERE KKPM_ACTIVE = 'Y'";
		String value = dbActionTemplate.getSingleValue(count);
		int counts = Integer.parseInt(value);
		CommonMessage.debugMsg("counts  /........"+counts);
		int k=0;
		try{			
			if(newKznTlDmcprojectprioritymst.getMaster()!=null){
				KznTlDmcprojectprioritydtl newKznTlDmcprojectprioritydtl = new KznTlDmcprojectprioritydtl();
				for(int i=0;i<newKznTlDmcprojectprioritymst.getMaster().size();i++){
					KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst1=newKznTlDmcprojectprioritymst.getMaster().get(i);
					KznTlDmcprojectprioritymstSql newKznTlDmcprojectprioritymstSql = new KznTlDmcprojectprioritymstSql();
					KznTlDmcprojectprioritydtlSql newKznTlDmcprojectprioritydtlSql = new KznTlDmcprojectprioritydtlSql();
					newKznTlDmcprojectprioritymst1.setDmpmCreatedby(newKznTlDmcprojectprioritymst.getDmpmCreatedby());
					fillvalues(newKznTlDmcprojectprioritymst1);
					if(newKznTlDmcprojectprioritymst1.getDmpmKeyid() !=null){
						CommonMessage.debugMsg("dmpmkeyid="+newKznTlDmcprojectprioritymst1.getDmpmKeyid());
						String projectscore = newKznTlDmcprojectprioritymst1.getDmpmProjectscore();
						int prjScore = Integer.parseInt(projectscore);
						String masterkeyid = newKznTlDmcprojectprioritymst1.getDmpmKeyid();
						String rank = newKznTlDmcprojectprioritymst1.getDmpmRank();
						String Approvedby = newKznTlDmcprojectprioritymst1.getDmpmApprovedby();
						sqls.add(KznTlDmcprojectprioritymstSql.getUpdateMaster(prjScore,masterkeyid,Approvedby,rank));
						
					 }else{
						 newKznTlDmcprojectprioritymst1.setDmpmKeyid(dbActionTemplate.getSequenceNumber(KznTlDmcprojectprioritymstSql.TBL_KZN_TL_DMCPROJECTPRIORITYMST,15,"DCD","DDMMYY","Y")); // set the sequnce number 
						 //newKznTlDmcprojectprioritymst1.setDmpmKeyid(dbActionTemplate.getSequenceNumber(KznTlDmcprojectprioritymstSql.TBL_KZN_TL_DMCPROJECTPRIORITYMST,15,"DCD","Y","DDMMYY")); // set the sequnce number							
						 sqls.add(KznTlDmcprojectprioritymstSql.getInsertSql(newKznTlDmcprojectprioritymstSql.getDmpmDbFields(), newKznTlDmcprojectprioritymst1.getSaveArray()));
					 }
				
					
					for(int j=0;j<counts;j++){
						CommonMessage.debugMsg("var k  value      "+newKznTlDmcprojectprioritymst.getDetail().size());
						newKznTlDmcprojectprioritydtl=newKznTlDmcprojectprioritymst.getDetail().get(k);
						newKznTlDmcprojectprioritydtl.setDmdlDmpmKeyid(newKznTlDmcprojectprioritymst1.getDmpmKeyid());
						newKznTlDmcprojectprioritydtl.setDmdlCreatedby(newKznTlDmcprojectprioritymst1.getDmpmCreatedby());
						fillvalues(newKznTlDmcprojectprioritydtl);
						String existscore = "select dmdl_score from KZN_TL_DMCPROJECTPRIORITYDTL where dmdl_dmpm_keyid ='"+newKznTlDmcprojectprioritydtl.getDmdlKeyid()+"' and dmdl_kkpm_keyid='"+newKznTlDmcprojectprioritydtl.getDmdlKkpmKeyid()+"'";
						String detailscore = dbActionTemplate.getSingleValue(existscore);
						if(newKznTlDmcprojectprioritydtl.getDmdlKeyid() !=null && newKznTlDmcprojectprioritydtl.getDmdlKkpmKeyid()!=null && detailscore!=null){
							String score = newKznTlDmcprojectprioritydtl.getDmdlScore();
							int Score = Integer.parseInt(score);
							String masterkeyid = newKznTlDmcprojectprioritydtl.getDmdlKeyid();
							String parameterkeyid = newKznTlDmcprojectprioritydtl.getDmdlKkpmKeyid();
							sqls.add(KznTlDmcprojectprioritydtlSql.getUpdateDetails(Score,masterkeyid,parameterkeyid));
						 }else{
							 newKznTlDmcprojectprioritydtl.setDmdlKeyid(dbActionTemplate.getSequenceNumber(KznTlDmcprojectprioritydtlSql.TBL_KZN_TL_DMCPROJECTPRIORITYDTL,15,"DMC","DDMMYY","Y")); // set the sequnce number
							 sqls.add(KznTlDmcprojectprioritydtlSql.getInsertSql(newKznTlDmcprojectprioritydtlSql.getDmdlDbFields(), newKznTlDmcprojectprioritydtl.getSaveArray()));
						 }
						k++;
					}
				}
			}
			CommonMessage.debugMsg("before Execute");
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("after Execute");
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg(" error  "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	private void fillvalues(KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst1) {
		String dateTime = CommonFunctions.dateTimeNow();

		if(newKznTlDmcprojectprioritymst1.getDmpmActive() == null){
			newKznTlDmcprojectprioritymst1.setDmpmActive("Y");
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmCreatedon() == null){
			newKznTlDmcprojectprioritymst1.setDmpmCreatedon(dateTime);
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmModifiedon() == null){
			newKznTlDmcprojectprioritymst1.setDmpmModifiedon(dateTime);
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmTempfield1() == null){
			newKznTlDmcprojectprioritymst1.setDmpmTempfield1("-");
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmTempfield2() == null){
			newKznTlDmcprojectprioritymst1.setDmpmTempfield2("-");
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmTempfield3() == null){
			newKznTlDmcprojectprioritymst1.setDmpmTempfield3("-");
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmTempfield4() == null){
			newKznTlDmcprojectprioritymst1.setDmpmTempfield4("-");
		}
		if(newKznTlDmcprojectprioritymst1.getDmpmRank() == null){
			newKznTlDmcprojectprioritymst1.setDmpmRank("-");
		}
	}
	
	private void fillvalues(KznTlDmcprojectprioritydtl newKznTlDmcprojectprioritydtl) {
		String dateTime = CommonFunctions.dateTimeNow();

		if(newKznTlDmcprojectprioritydtl.getDmdlActive() == null){
			newKznTlDmcprojectprioritydtl.setDmdlActive("Y");
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlCreatedon() == null){
			newKznTlDmcprojectprioritydtl.setDmdlCreatedon(dateTime);
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlModifiedon() == null){
			newKznTlDmcprojectprioritydtl.setDmdlModifiedon(dateTime);
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlTempfield1() == null){
			newKznTlDmcprojectprioritydtl.setDmdlTempfield1("-");
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlTempfield2() == null){
			newKznTlDmcprojectprioritydtl.setDmdlTempfield2("-");
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlTempfield3() == null){
			newKznTlDmcprojectprioritydtl.setDmdlTempfield3("-");
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlTempfield4() == null){
			newKznTlDmcprojectprioritydtl.setDmdlTempfield4("-");
		}
		if(newKznTlDmcprojectprioritydtl.getDmdlTempfield5() == null){
			newKznTlDmcprojectprioritydtl.setDmdlTempfield5("-");
		}		
	}
	private void fillvalues(KznTlKkprojectprioritydtl newKznTlKkprojectprioritydtl) {
		String dateTime = CommonFunctions.dateTimeNow();

		if(newKznTlKkprojectprioritydtl.getKppdActive() == null){
			newKznTlKkprojectprioritydtl.setKppdActive("Y");
		}
		if(newKznTlKkprojectprioritydtl.getKppdCreatedon() == null){
			newKznTlKkprojectprioritydtl.setKppdCreatedon(dateTime);
		}
		if(newKznTlKkprojectprioritydtl.getKppdModifiedon() == null){
			newKznTlKkprojectprioritydtl.setKppdModifiedon(dateTime);
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield1() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield1("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield2() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield2("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield3() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield3("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield4() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield4("-");
		}
		if(newKznTlKkprojectprioritydtl.getKppdTempfield5() == null){
			newKznTlKkprojectprioritydtl.setKppdTempfield5("-");
		}		
	}
	
	private void fillvalues(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst) {
		String dateTime = CommonFunctions.dateTimeNow();

		if(newKznTlKkprojectprioritymst.getKppmActive() == null){
			newKznTlKkprojectprioritymst.setKppmActive("Y");
		}
		if(newKznTlKkprojectprioritymst.getKppmCreatedon() == null){
			newKznTlKkprojectprioritymst.setKppmCreatedon(dateTime);
		}
		if(newKznTlKkprojectprioritymst.getKppmModifiedon() == null){
			newKznTlKkprojectprioritymst.setKppmModifiedon(dateTime);
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield1() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield1("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield2() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield2("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield3() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield3("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmTempfield4() == null){
			newKznTlKkprojectprioritymst.setKppmTempfield4("-");
		}
		if(newKznTlKkprojectprioritymst.getKppmRank() == null){
			newKznTlKkprojectprioritymst.setKppmRank("-");
		}
	}
	
	@Override
	public KznTlKkprojectprioritymst delete(KznTlKkprojectprioritymst newKznTlKkprojectprioritymst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		try{
			
			if(newKznTlKkprojectprioritymst.getMaster()!=null){
				for(int i=0;i<newKznTlKkprojectprioritymst.getMaster().size();i++){
					KznTlKkprojectprioritymst newKznTlKkprojectprioritymst1=newKznTlKkprojectprioritymst.getMaster().get(i);
					KznTlKkprojectprioritymstSql newKkprojectprioritymstSql = new KznTlKkprojectprioritymstSql();
					sqls.add(KznTlKkprojectprioritymstSql.getDeleteDetail(newKznTlKkprojectprioritymst1.getKppmKeyid()));
					sqls.add(KznTlKkprojectprioritymstSql.getDeleteSql(newKkprojectprioritymstSql.getKppmDbFields(), newKznTlKkprojectprioritymst1.getSaveArray()));
				}
			}
			dbActionTemplate.executeStatements(sqls);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
