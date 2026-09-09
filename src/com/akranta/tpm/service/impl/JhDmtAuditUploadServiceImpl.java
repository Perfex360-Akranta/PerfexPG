package com.akranta.tpm.service.impl;

import com.akranta.tpm.service.JhDmtAuditUploadService;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.JhDmtAuditUploadServiceApi;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JhAuditCreationBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.JHAuditSheetCreationItcDao;
import com.akranta.tpm.dao.JhaTlAuditdtlItcDao;
import com.akranta.tpm.dao.JhaTlAuditmstItcDao;
import com.akranta.tpm.dao.JhaTlAuditparameterItcDao;
import com.akranta.tpm.dao.JhaTlTemplatemchlinkDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.JHAuditSheetCreationItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlAuditdtlItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlAuditmstItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlAuditparameterItcDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlTemplatemchlinkDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
public class JhDmtAuditUploadServiceImpl implements JhDmtAuditUploadService{
	
	private  JhDmtAuditUploadServiceApi jhdmtAuditUploadServiceApi;
	
	private  JHAuditSheetCreationItcServiApi jhauditSheetCreationItcServiApi;
	
	private JHAuditSheetCreationItcDao jHAuditSheetItcCreationDao;
	private JhaTlAuditmstItcDao jhaTlAuditmstItcDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
		
		public JhDmtAuditUploadServiceImpl(DBActionTemplate dbActionTemplate)
		{
			jHAuditSheetItcCreationDao =  new JHAuditSheetCreationItcDaoImpl(dbActionTemplate);
			jhaTlAuditmstItcDao =  new JhaTlAuditmstItcDaoImpl(dbActionTemplate);
			commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
			validations = new Validations();
		}
		
		
		//03-01-2026
		public void JhDmtAuditUploadServiceImplJwt(String JwtToken){
	    	try{
	    		jhaTlAuditmstItcDao.JhaTlAuditmstItcDaoImplJwt(JwtToken);
	    		jhdmtAuditUploadServiceApi = new JhDmtAuditUploadServiceApi(JwtToken);
	    		jhauditSheetCreationItcServiApi = new JHAuditSheetCreationItcServiApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
//		
//	 //14-jan
//		public void JHAuditSheetCreationItcServiImplJwt(String JwtToken){
//	    	try{
//	    		jhaTlAuditmstItcDao.JhaTlAuditmstItcDaoImplJwt(JwtToken);
//	    		jhauditSheetCreationItcServiApi = new JHAuditSheetCreationItcServiApi(JwtToken);
//	    	}
//	    	catch(Exception e)
//	    	{
//	    		e.printStackTrace();
//	    	}
//	        // TODO Auto-generated constructor stub
//	    }

		public JhaTlAuditmst create(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions,Exception {

			try {
				String validationsFor = "create";
				validations.validate(newjhaTlAuditmst,"JhDmtAuditUpload",validationsFor);
				fillValues(newjhaTlAuditmst,oldjhaTlAuditmst,jhAuditCreationBean);
				return jhdmtAuditUploadServiceApi.insertRecord(newjhaTlAuditmst);	
				//return jhaTlAuditmstItcDao.createJhDmt(newjhaTlAuditmst);	
			}catch (ValidationExceptions e){
				CommonMessage.debugMsg("e.getMessage()"+e.getMessage());
				throw new ValidationExceptions(e.getMessage());
			}	
		}
		
		public JhaTlAuditmst update(JhaTlAuditmst newJhaTlAuditmst,JhaTlAuditmst oldJhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) throws ValidationExceptions,Exception {
			//return null;
		
			String validationsFor;
			validationsFor = "update";
			validations.validate(newJhaTlAuditmst,"JhDmtAuditUpload",validationsFor);
			fillValues(newJhaTlAuditmst, oldJhaTlAuditmst, jhAuditCreationBean);
			return jhdmtAuditUploadServiceApi.insertRecord(newJhaTlAuditmst);	
			//return jhaTlAuditmstItcDao.updateJhDmt(newJhaTlAuditmst);
		}

public JhaTlAuditmst select(JhaTlAuditmst jhaTlAuditmst) throws Exception {
		// TODO Auto-generated method stub
		//return this.jhaTlAuditmstItcDao.select(jhaTlAuditmst);
	//return this.jhdmtAuditUploadServiceApi.getAuditByKeyid(jhaTlAuditmst.getJhamKeyid());
	return this.jhauditSheetCreationItcServiApi.getAuditByKeyid(jhaTlAuditmst);
	}
     @Override
	public JhaTlAuditparameter recallValues(JhaTlAuditparameter jhaTlAuditparameter) throws Exception {
		//return this.jhaTlAuditmstItcDao.recallValues(jhaTlAuditparameter);
    	 return jhauditSheetCreationItcServiApi.getParameterByKeyid(jhaTlAuditparameter.getJhapKeyid());
	}
		
		private JhaTlAuditmst fillValues(JhaTlAuditmst newjhaTlAuditmst,JhaTlAuditmst oldjhaTlAuditmst,  JhAuditCreationBean jhAuditCreationBean) {
			newjhaTlAuditmst.setJhamActive("Y");
			String dateTime = CommonFunctions.pg_dateTimeNow();
			newjhaTlAuditmst.setJhamAuditupload("Y");
			
			if( ! UIUtils.isValidKeyId(newjhaTlAuditmst.getJhamKeyid()))
			{
				newjhaTlAuditmst.setJhamCreatedon(dateTime);
				newjhaTlAuditmst.setJhamModifiedon(dateTime);
			}
			else{
				newjhaTlAuditmst.setJhamCreatedon(dateTime);
				newjhaTlAuditmst.setJhamModifiedon(dateTime);
			}  
			
			if( newjhaTlAuditmst.getJhamFlid() == null )
				newjhaTlAuditmst.setJhamFlid("{}");
			
			if( newjhaTlAuditmst.getJhamAudittype() == null )
				newjhaTlAuditmst.setJhamAudittype("{}");
			
			if( newjhaTlAuditmst.getJhamAuditpillar() == null )
				newjhaTlAuditmst.setJhamAuditpillar("{}");

			//07-jan
			String JhamAuditdate = newjhaTlAuditmst.getJhamAuditdate();
			newjhaTlAuditmst.setJhamAuditdate(CommonFunctions.pg_getDateTimeFromDate(newjhaTlAuditmst.getJhamAuditdate()));
			
			if( newjhaTlAuditmst.getJhamAuditdate() == null )
				newjhaTlAuditmst.setJhamAuditdate(Constants.pgPassNullDateTime);
			
			if( newjhaTlAuditmst.getJhamNextauditdate() == null )
				newjhaTlAuditmst.setJhamNextauditdate(Constants.pgFutureNullDateTime);
			
			if( newjhaTlAuditmst.getJhamTotalpoints() == null )
				newjhaTlAuditmst.setJhamTotalpoints("0");
			
			if( newjhaTlAuditmst.getJhamAuditorname() == null )
				newjhaTlAuditmst.setJhamAuditorname("{}");
			
			if( newjhaTlAuditmst.getJhamAuditortype() == null )
				newjhaTlAuditmst.setJhamAuditortype("{}");
			
			if( newjhaTlAuditmst.getJhamJhstepid() == null )
				newjhaTlAuditmst.setJhamJhstepid("{}");
			
			if( newjhaTlAuditmst.getJhamStatus() == null )
				newjhaTlAuditmst.setJhamStatus("P");
			
			if( newjhaTlAuditmst.getJhamAuditteamid() == null )
				newjhaTlAuditmst.setJhamAuditteamid("{}");
			
			if( newjhaTlAuditmst.getJhamLeadername() == null )
				newjhaTlAuditmst.setJhamLeadername("{}");
			
			if( newjhaTlAuditmst.getJhamNextauditteam() == null )
				newjhaTlAuditmst.setJhamNextauditteam("{}");
			
			if( newjhaTlAuditmst.getJhamTempfield2() == null )
				newjhaTlAuditmst.setJhamTempfield2("{}");
			
			if( newjhaTlAuditmst.getJhamTempfield3() == null )
				newjhaTlAuditmst.setJhamTempfield3("{}");
			
			if( newjhaTlAuditmst.getJhamTempfield4() == null )
				newjhaTlAuditmst.setJhamTempfield4("{}");
			
			if( newjhaTlAuditmst.getJhamTempfield5() == null )
				newjhaTlAuditmst.setJhamTempfield5("{}");

			return newjhaTlAuditmst;		
		}
		public List<String[]> getjhAuditUploadfillGrid(CommonFilter commonFilter) throws Exception
		{
			CommonMessage.debugMsg("getTrainingHoursPgm");
			return this.jhaTlAuditmstItcDao.getjhAuditUploadfillGrid(commonFilter);
		}
		public String getMinMarks(String auditLevel,String auditTemplate) throws Exception {
			// TODO Auto-generated method stub
			//return jhaTlAuditmstItcDao.getMinMarks(auditLevel,auditTemplate);
			Integer minMarks = jhauditSheetCreationItcServiApi.getMinimumMarks(auditLevel, auditTemplate);
		    return minMarks.toString();
		}
		public List<String[]> getMinPoints(String parameter,String auditTeam)throws Exception
		{
			//return this.jhaTlAuditmstItcDao.getMinPoints(parameter,auditTeam);
			Integer minPoints = jhauditSheetCreationItcServiApi.getMinimumPoints(parameter, auditTeam);
			
		    List<String[]> result = new ArrayList<>();
		    String[] row = new String[] { minPoints.toString() };
		    result.add(row);
		    
		    return result;
		}
		@Override
		public String getJhLeader(String flid) throws Exception {
			// TODO Auto-generated method stub
			return jhaTlAuditmstItcDao.getJhLeader(flid);
		}
		public Workbook jhAuditUploadExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
			return this.jhaTlAuditmstItcDao.jhAuditUploadExportExcel(commonFilter,colmodel,rptFormat);
		}


}
