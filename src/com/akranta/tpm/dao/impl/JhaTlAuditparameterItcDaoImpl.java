package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.JhaTlAuditparameterItcDao;

import com.akranta.tpm.dao.sql.JhaTlAuditparameterSql;
import com.akranta.tpm.dao.sql.JhaTlAudittemplateSql;
import com.akranta.tpm.dao.sql.JhaTlTemplategradelinkSql;
import com.akranta.tpm.dao.sql.JhaTlTemplatelevellinkSql;
import com.akranta.tpm.dao.sql.JhaTlTemplatemchlinkSql;
import com.akranta.tpm.dao.sql.JhaTlTemplatesteplinkSql;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlAudittemplate;
import com.akranta.tpm.model.JhaTlTemplategradelink;
import com.akranta.tpm.model.JhaTlTemplatelevellink;
import com.akranta.tpm.model.JhaTlTemplatemchlink;
import com.akranta.tpm.model.JhaTlTemplatesteplink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class JhaTlAuditparameterItcDaoImpl implements JhaTlAuditparameterItcDao {


	private DBActionTemplate dbActionTemplate; 
	private JhaTlTemplatemchlinkSql jhaTlTemplatemchlinkSql = null;
	private JhaTlTemplatesteplinkSql jhaTlTemplatesteplinkSql = null;
	private JhaTlTemplatelevellinkSql jhaTlTemplatelevellinkSql = null;
	private JhaTlAudittemplateSql jhaTlAudittemplateSql = null;
	private JhaTlTemplategradelinkSql jhaTlTemplategradelinkSql = null;
	private JhaTlAuditparameterSql auditparameterSql = null;
	public JhaTlAuditparameterItcDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		jhaTlTemplatemchlinkSql =new JhaTlTemplatemchlinkSql();
		jhaTlTemplatesteplinkSql = new JhaTlTemplatesteplinkSql();
		jhaTlTemplatelevellinkSql = new JhaTlTemplatelevellinkSql();
		jhaTlAudittemplateSql = new JhaTlAudittemplateSql();
		jhaTlTemplategradelinkSql = new JhaTlTemplategradelinkSql();
		auditparameterSql = new JhaTlAuditparameterSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public JhaTlAuditparameter create(JhaTlAuditparameter jhaTlAuditparameter) 	throws ValidationExceptions,BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlAuditparameterSql jhaTlAuditparameterSql = new JhaTlAuditparameterSql(); // contains dbtable,field names, Field types and related sqls  of master table
		CommonMessage.debugMsg("after the ben properties in dao impl");	
		try{
			if(UIUtils.isValidKeyId(jhaTlAuditparameter.getJhapKeyid()))
				sqls.add(JhaTlAuditparameterSql.getUpdateSql(jhaTlAuditparameterSql.getJhapDbFields(), jhaTlAuditparameter.getSaveArray())); // add insert sql for master table
			else
			{
				jhaTlAuditparameter.setJhapKeyid(dbActionTemplate.getSequenceNumber(JhaTlAuditparameterSql.TBL_JHA_TL_AUDITPARAMETER, 10, "JHP", "", "Y"));
				sqls.add(JhaTlAuditparameterSql.getInsertSql(jhaTlAuditparameterSql.getJhapDbFields(), jhaTlAuditparameter.getSaveArray()));
			}
			if(jhaTlAuditparameter.getEquipmentGrid() != null && jhaTlAuditparameter.getEquipmentGrid().size() > 0)
			{
				insertEquipment(jhaTlAuditparameter,sqls);
			}
			if(jhaTlAuditparameter.getJHStepGrid() != null && jhaTlAuditparameter.getJHStepGrid().size() > 0)
			{
				insertJHStep(jhaTlAuditparameter,sqls);
			}
			
			if(jhaTlAuditparameter.getJhLevelGrid() != null && jhaTlAuditparameter.getJhLevelGrid().size() > 0)
			{
				insertJHLevel(jhaTlAuditparameter,sqls);
			}
			if(jhaTlAuditparameter.getJhAuditTemplate() != null && jhaTlAuditparameter.getJhAuditTemplate().size() > 0)
			{
				insertJHAuditTemplate(jhaTlAuditparameter,sqls);
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			return jhaTlAuditparameter;
		}
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		
		
	}
	private void fillValues(JhaTlTemplategradelink jhaTlTemplategradelink,JhaTlAuditparameter jhaTlAuditparameter) {
		
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglAuditmasterid()))
			jhaTlTemplategradelink.setJtglAuditmasterid(jhaTlAuditparameter.getJhapKeyid());
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglActive()))
			jhaTlTemplategradelink.setJtglActive("Y");
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglCreatedon()))
			jhaTlTemplategradelink.setJtglCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglModifiedon()))
			jhaTlTemplategradelink.setJtglModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglCreatedby()))
			jhaTlTemplategradelink.setJtglCreatedby(jhaTlAuditparameter.getJhapCreatedby());
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglGradeid()))
			jhaTlTemplategradelink.setJtglGradeid("{}");
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglMaximummarks()))
			jhaTlTemplategradelink.setJtglMaximummarks("1");
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglMinimummarks()))
			jhaTlTemplategradelink.setJtglMinimummarks("1");
		if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglTemplateid()))
			jhaTlTemplategradelink.setJtglTemplateid("{}");
		
	}
	private List<String> insertJHGrade(JhaTlAuditparameter jhaTlAuditparameter,	List<String> sqls ) throws Exception 
	{		
		if(jhaTlAuditparameter.getJhGradeGrid()!= null && jhaTlAuditparameter.getJhGradeGrid().size()>0)
		{			
			
			//for(int j=0;j<jhaTlAuditparameter.getJhAuditTemplate().size();j++)
			{
				//JhaTlAudittemplate jhaTlAudittemplate = (JhaTlAudittemplate)jhaTlAuditparameter.getJhAuditTemplate().get(j);
			
	    	//for(int i =0;i<jhaTlAuditparameter.getJhGradeGrid().size();i++)
			//{
				JhaTlTemplategradelink jhaTlTemplategradelink = new JhaTlTemplategradelink();
				String dateTime = CommonFunctions.dateTimeNow();
				CommonMessage.debugMsg("dateTime::"+dateTime);
				jhaTlTemplategradelink.setJtglCreatedon(dateTime);
				jhaTlTemplategradelink.setJtglModifiedon(dateTime);
				jhaTlTemplategradelink.setJtglActive("Y");
	    		//JhaTlTemplategradelink jhaTlTemplategradelink = (JhaTlTemplategradelink)jhaTlAuditparameter.getJhGradeGrid().get(i); 
	    		jhaTlTemplategradelink.setJtglAuditmasterid(jhaTlAuditparameter.getJhapKeyid());				
	    		jhaTlTemplategradelink.setJtglCreatedby(jhaTlAuditparameter.getJhapCreatedby());
	    	//	CommonMessage.debugMsg("Insert keyid....."+jhaTlTemplategradelink.getJtglTemplateid()+" "+i);
	    		//if(!UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglTemplateid())){
	    			//jhaTlTemplategradelink.setJtglTemplateid(keyId);
	    			CommonMessage.debugMsg("gradeLinkInsert....");
	    			sqls.add(JhaTlTemplategradelinkSql.getInsertSql(jhaTlTemplategradelinkSql.getJtglDbFields(), jhaTlTemplategradelink.getSaveArray()));
	    	//}//else
	    	//	sqls.add(JhaTlTemplategradelinkSql.getUpdateSql(jhaTlTemplategradelinkSql.getJtglDbFields(), jhaTlTemplategradelink.getSaveArray()));
			
	    	//}
			}
		}		
		return sqls;
	}

	private List<String> updateJHGrade(JhaTlAuditparameter jhaTlAuditparameter,	List<String> sqls,JhaTlAudittemplate jhaTlAudittemplate) throws Exception 
	{		
		CommonMessage.debugMsg("update keyid1");
		if(jhaTlAuditparameter.getJhGradeGrid()!= null && jhaTlAuditparameter.getJhGradeGrid().size()>0)
		{			
			CommonMessage.debugMsg("update keyid2");
			//for(int j=0;j<jhaTlAuditparameter.getJhAuditTemplate().size();j++)
			//{
			//	JhaTlAudittemplate jhaTlAudittemplate = (JhaTlAudittemplate)jhaTlAuditparameter.getJhAuditTemplate().get(j);
			CommonMessage.debugMsg("update keyid3");
	    	for(int i =0;i<jhaTlAuditparameter.getJhGradeGrid().size();i++)
			{	
	    		CommonMessage.debugMsg("update keyid4");
	    		JhaTlTemplategradelink jhaTlTemplategradelink = (JhaTlTemplategradelink)jhaTlAuditparameter.getJhGradeGrid().get(i); 
	    		jhaTlTemplategradelink.setJtglAuditmasterid(jhaTlAuditparameter.getJhapKeyid());				
	    		jhaTlTemplategradelink.setJtglCreatedby(jhaTlAuditparameter.getJhapCreatedby());
	    		
	    		CommonMessage.debugMsg("update keyid....."+jhaTlTemplategradelink.getJtglTemplateid());
	    		if(UIUtils.isValidKeyId(jhaTlTemplategradelink.getJtglTemplateid())){
	    			//jhaTlTemplategradelink.setJtglTemplateid(keyId);
	    			sqls.add(JhaTlTemplategradelinkSql.getUpdateSql(jhaTlTemplategradelinkSql.getJtglDbFields(), jhaTlTemplategradelink.getSaveArray()));
	    		}	
			
	    	}
			}
		//}		
		return sqls;
	}
	private List<String> insertJHAuditTemplate(JhaTlAuditparameter jhaTlAuditparameter,	List<String> sqls) throws Exception {
		
		if(jhaTlAuditparameter.getJhAuditTemplate()!= null && jhaTlAuditparameter.getJhAuditTemplate().size()>0)
		{			
			for(int i =0;i<jhaTlAuditparameter.getJhAuditTemplate().size();i++)
			{	
	    		JhaTlAudittemplate jhaTlAudittemplate = (JhaTlAudittemplate)jhaTlAuditparameter.getJhAuditTemplate().get(i);
	    		
	    		jhaTlAudittemplate.setJautMasterid(jhaTlAuditparameter.getJhapKeyid());				
	    		jhaTlAudittemplate.setJautCreatedby(jhaTlAuditparameter.getJhapCreatedby());
	    		if(!UIUtils.isValidKeyId(jhaTlAudittemplate.getJautKeyid())){
	    			jhaTlAudittemplate.setJautKeyid(dbActionTemplate.getSequenceNumber(JhaTlAudittemplateSql.TBL_JHA_TL_AUDITTEMPLATE, 10, "JHT", "YYMM", "Y"));
	    			sqls.add(JhaTlAudittemplateSql.getInsertSql(jhaTlAudittemplateSql.getJautDbFields(), jhaTlAudittemplate.getSaveArray()));	
	    		}else{
	    			sqls.add(JhaTlAudittemplateSql.getUpdateSql(jhaTlAudittemplateSql.getJautDbFields(), jhaTlAudittemplate.getSaveArray()));
	    			CommonMessage.debugMsg("Inside Update..."+jhaTlAudittemplate.getJautKeyid());
	    			//updateJHGrade(jhaTlAuditparameter,sqls,jhaTlAudittemplate);
	    		}
			}
		}
		return sqls;
	}

	private List<String> insertJHLevel(JhaTlAuditparameter jhaTlAuditparameter,	List<String> sqls) {
		if(jhaTlAuditparameter.getJhLevelGrid()!= null && jhaTlAuditparameter.getJhLevelGrid().size()>0)
		{
			sqls.add(JhaTlTemplatelevellinkSql.getDeleteSql(jhaTlAuditparameter.getJhapKeyid()));
	    	for(int i =0;i<jhaTlAuditparameter.getJhLevelGrid().size();i++)
			{	
	    		JhaTlTemplatelevellink jhaTlTemplatelevellink = (JhaTlTemplatelevellink)jhaTlAuditparameter.getJhLevelGrid().get(i); 
	    		jhaTlTemplatelevellink.setJtllTemplateid(jhaTlAuditparameter.getJhapKeyid());				
	    		jhaTlTemplatelevellink.setJtllCreatedby(jhaTlAuditparameter.getJhapCreatedby());
				sqls.add(JhaTlTemplatelevellinkSql.getInsertSql(jhaTlTemplatelevellinkSql.getJtllDbFields(), jhaTlTemplatelevellink.getSaveArray()));
			}
		}
		return sqls;
	}

	private List<String> insertJHStep(JhaTlAuditparameter jhaTlAuditparameter,List<String> sqls) 
	{

		if(jhaTlAuditparameter.getJHStepGrid()!= null && jhaTlAuditparameter.getJHStepGrid().size()>0)
		{
			sqls.add(JhaTlTemplatesteplinkSql.getDeleteSql(jhaTlAuditparameter.getJhapKeyid()));
			
	    	for(int i =0;i<jhaTlAuditparameter.getJHStepGrid().size();i++)
			{	
	    		JhaTlTemplatesteplink jhaTlTemplatesteplink = (JhaTlTemplatesteplink)jhaTlAuditparameter.getJHStepGrid().get(i); 
	    		jhaTlTemplatesteplink.setJtslTemplateid(jhaTlAuditparameter.getJhapKeyid());				
	    		jhaTlTemplatesteplink.setJtslCreatedby(jhaTlAuditparameter.getJhapCreatedby());
				sqls.add(JhaTlTemplatesteplinkSql.getInsertSql(jhaTlTemplatesteplinkSql.getJtslDbFields(), jhaTlTemplatesteplink.getSaveArray()));
			}
		}
		
		return sqls;
	}

	private List<String> insertEquipment(JhaTlAuditparameter jhaTlAuditparameter,	List<String> sqls) {
		
		if(jhaTlAuditparameter.getEquipmentGrid()!= null && jhaTlAuditparameter.getEquipmentGrid().size()>0) // check for detail table data
		{
			sqls.add(JhaTlTemplatemchlinkSql.getDeleteSql(jhaTlAuditparameter.getJhapKeyid()));
			
	    	for(int i =0;i<jhaTlAuditparameter.getEquipmentGrid().size();i++)
			{	
	    		JhaTlTemplatemchlink jhaTlTemplatemchlink = (JhaTlTemplatemchlink)jhaTlAuditparameter.getEquipmentGrid().get(i); // get detail info from list in empployee object
	    		jhaTlTemplatemchlink.setJtmlTemplateid(jhaTlAuditparameter.getJhapKeyid());				
	    		jhaTlTemplatemchlink.setJtmlCreatedby(jhaTlAuditparameter.getJhapCreatedby());
				sqls.add(JhaTlTemplatemchlinkSql.getInsertSql(jhaTlTemplatemchlinkSql.getJtmlDbFields(), jhaTlTemplatemchlink.getSaveArray()));// add insert sql for detail table
			}
		}
		
		return sqls;
	}

	public JhaTlAuditparameter update(JhaTlAuditparameter jhaTlAuditparameter)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlAuditparameterSql jhaTlAuditparameterSql = new JhaTlAuditparameterSql();
		try {
			sqls.add(JhaTlAuditparameterSql.getUpdateSql(jhaTlAuditparameterSql.getJhapDbFields(), jhaTlAuditparameter.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditparameter;
	}
	
	public JhaTlAuditparameter delete(JhaTlAuditparameter jhaTlAuditparameter)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		JhaTlAuditparameterSql jhaTlAuditparameterSql = new JhaTlAuditparameterSql();
		try {
			
			sqls.add(jhaTlAuditparameterSql.getDeleteSql(jhaTlAuditparameterSql.getJhapDbFields(), jhaTlAuditparameter.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlAuditparameter;
	}

	@Override
	public void deleteParameter(String parameterId) throws BusinessApplicationExceptions,Exception {
		
		List<String> sqls = new ArrayList<String>();
		try{
		sqls.add(auditparameterSql.getAuditTemplateDeleteSql(parameterId));
		//sqls.add(auditparameterSql.getTemplateGradeDeleteSql(parameterId));
		dbActionTemplate.executeStatements(sqls);
		}catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage()+"FK_JHAD_PARAMETERID,");
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
}

