package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.Knowwhybean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.ImprovementProjSheetKaiDao;
import com.akranta.tpm.dao.QtmTlKnowwhymstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.ImprovementProjSheetKaiDaoImpl;
import com.akranta.tpm.dao.impl.QtmTlKnowwhymstDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.PlmTlPmtasklistdtl;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;
import com.akranta.tpm.model.VocTlChecklistdtl;
import com.akranta.tpm.service.KnowWhyService;
import com.akranta.tpm.service.api.BestKaizenServiceApi;
import com.akranta.tpm.service.api.KnowWhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.dao.KnowWhyDao;
import com.akranta.tpm.dao.impl.KnowWhyDaoImpl;
import com.akranta.tpm.exportreport.KnowWhyExcelTemplate;
import com.akranta.tpm.exportreport.OPLITCExcelTemplate;

public class KnowWhyServiceImpl implements KnowWhyService {
	
	
	
	//private KnowWhyDao knowWhyDao;
	private QtmTlKnowwhymstDao qtmtlknowwhymstdao;
	private Validations validations ;
	private DBActionTemplate  dbActionTemplate ; 
	private ImprovementProjSheetKaiDao improvementProjSheetKaiDao;
	KnowWhyServiceApi serviceApi;
	
	public KnowWhyServiceImpl(DBActionTemplate dbActionTemplate){
		//knowWhyDao = new KnowWhyDaoImpl(dbActionTemplate);
		qtmtlknowwhymstdao = new QtmTlKnowwhymstDaoImpl(dbActionTemplate);
		this.dbActionTemplate = dbActionTemplate;
		validations = new Validations();
		improvementProjSheetKaiDao =new ImprovementProjSheetKaiDaoImpl(dbActionTemplate);
		// TODO Auto-generated constructor stub
		
	}
	
	public void KnowWhyServiceImplJwt(String JwtToken){
    	try{
    		qtmtlknowwhymstdao.KnowWhyDaoImplJwt(JwtToken);
    		serviceApi = new KnowWhyServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<String[]> getITCKnow(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.qtmtlknowwhymstdao.getITCKnow(commonFilter);
	}

	@Override
	public List<String[]> getKnow(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.qtmtlknowwhymstdao.getKnow(commonFilter);
	}

	@Override
	public QtmTlKnowwhymst create(QtmTlKnowwhymst qtmTlKnowwhymst,QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception,ValidationExceptions {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("serviceimpl 1");
			String validationsFor;
			validationsFor = "create";	
			
			
			CommonMessage.debugMsg("check value :  "+qtmTlKnowwhymst.getCheckPhenomena());
			if(!UIUtils.isValidKeyId(qtmTlKnowwhymst.getCheckPhenomena()))
				qtmTlKnowwhymst.setCheckPhenomena("N");
			CommonMessage.debugMsg("check value :  "+qtmTlKnowwhymst.getCheckPhenomena()+" phenomena  : "+qtmTlKnowwhymst.getKnwmPhenomena()+"  quality : "+qtmTlKnowwhymst.getKnwmQuality());
			validations.validate(qtmTlKnowwhymst, "Knowwhy",validationsFor);
			//validations.validate(qtmTlKnowwhydtl, "Knowwhy","create");
			//QtmTlKnowwhydtl qtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();			
			CommonMessage.debugMsg("serviceimpl 2");
			fillValuess(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
			CommonMessage.debugMsg(qtmTlKnowwhymst.getCheckPhenomena() + " checkvalue");
			//return this.qtmtlknowwhymstdao.create(qtmTlKnowwhymst);
			QtmTlKnowwhymst qtmKnowwhymstResult = serviceApi.saveKnowWhyMst(qtmTlKnowwhymst);
			String fileDir = qtmKnowwhymstResult.getFileDir();
			//String imagepath = qtmTlKnowwhymst.getImagePath();
			String imagename= qtmKnowwhymstResult.getKnwmImage();
			
			String ImageName = (fileDir+imagename);
			qtmKnowwhymstResult.setKnwmImage(ImageName);
			CommonMessage.debugMsg(qtmKnowwhymstResult.getKnwmImage() + " last create");
			return qtmKnowwhymstResult;
			//return serviceApi.saveKnowWhy(qtmTlKnowwhymst);
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}
	
	
	@Override
	public QtmTlKnowwhymst update(QtmTlKnowwhymst qtmTlKnowwhymst,QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception,ValidationExceptions {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("serviceimpl 1");
			String validationsFor;
			validationsFor = "update";	
		validations.validate(qtmTlKnowwhymst, "Knowwhy",validationsFor);
		QtmTlKnowwhydtl qtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
		CommonMessage.debugMsg(" service impl" );
		CommonMessage.debugMsg(" service impl 2" );
		fillValuess(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
		QtmTlKnowwhymst qtmKnowwhymstResult = serviceApi.saveKnowWhyMst(qtmTlKnowwhymst);
		String fileDir = qtmKnowwhymstResult.getFileDir();
		String imagename= qtmKnowwhymstResult.getKnwmImage();
		CommonMessage.debugMsg(qtmKnowwhymstResult.getKnwmImage() + "  update ");
		String ImageName = (fileDir+""+imagename);
		CommonMessage.debugMsg(ImageName +  " update last");
		qtmKnowwhymstResult.setKnwmImage(ImageName);
		//return this.qtmtlknowwhymstdao.update(qtmTlKnowwhymst);
		//return serviceApi.saveKnowWhy(qtmTlKnowwhymst);
		return qtmKnowwhymstResult;
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}

	private void fillValuess(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.pg_dateTimeNow();
		qtmTlKnowwhymst.setKnwmActive("Y");
		qtmTlKnowwhymst.setKnwmModifiedon(dateTime);
		qtmTlKnowwhymst.setKnwmCreatedon(dateTime);
		
		if( qtmTlKnowwhymst.getKnwmFlid() == null )
			qtmTlKnowwhymst.setKnwmFlid("{}");
		
		if( qtmTlKnowwhymst.getKnwmPillarid() == null )
			qtmTlKnowwhymst.setKnwmPillarid("{}");
		
		if( qtmTlKnowwhymst.getKnwmType() == null )
			qtmTlKnowwhymst.setKnwmType("{}");
		if( qtmTlKnowwhymst.getKnwmImage() == null )
			qtmTlKnowwhymst.setKnwmImage("{}");
		if( qtmTlKnowwhymst.getKnwmQuality() == null )
			qtmTlKnowwhymst.setKnwmQuality("{}");
		
		if( qtmTlKnowwhymst.getKnwmElementid() == null )
			qtmTlKnowwhymst.setKnwmElementid("{}");
		
		if( qtmTlKnowwhymst.getKnwmApprovedby() == null )
			qtmTlKnowwhymst.setKnwmApprovedby("{}");
		
		if( qtmTlKnowwhymst.getKnwmDescription() == null )
			qtmTlKnowwhymst.setKnwmDescription("{}");
		
		if( qtmTlKnowwhymst.getKnwmDevelopedby() == null )
			qtmTlKnowwhymst.setKnwmDevelopedby("{}");
		
		
		if( qtmTlKnowwhymst.getKnwmPhenomena() == null )
			qtmTlKnowwhymst.setKnwmPhenomena("-");
		
		String preparedDate = qtmTlKnowwhymst.getKnwmPrepareddate();
		qtmTlKnowwhymst.setKnwmPrepareddate(CommonFunctions.pg_getDateTimeFromDate(preparedDate));
		if( qtmTlKnowwhymst.getKnwmPrepareddate() == null )
			qtmTlKnowwhymst.setKnwmPrepareddate("-");
		
	
		if( qtmTlKnowwhymst.getKnwmVersionno() == null )
			qtmTlKnowwhymst.setKnwmVersionno("0");
		if( qtmTlKnowwhymst.getKnwmVersiondate() == null )
			qtmTlKnowwhymst.setKnwmVersiondate(dateTime);

		if( qtmTlKnowwhymst.getKnwmTempfield5() == null )
			qtmTlKnowwhymst.setKnwmTempfield5("0");
		
	}

	private QtmTlKnowwhymst fillValues(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) {
		// TODO Auto-generated method stub
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		String date = qtmTlKnowwhymst.getKnwmPrepareddate();
		String versionDate = qtmTlKnowwhymst.getKnwmVersiondate();
		if((isValidDateFormat(versionDate))) 
		{
			qtmTlKnowwhymst.setKnwmVersiondate(CommonFunctions.pg_getDateTimeFromTimeStamp(versionDate));
		}
		qtmTlKnowwhymst.setKnwmPrepareddate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		qtmTlKnowwhymst.setKnwmActive("Y");
		qtmTlKnowwhymst.setKnwmModifiedon(dateTime);
		qtmTlKnowwhymst.setKnwmCreatedon(dateTime);
		
		if( qtmTlKnowwhymst.getKnwmFlid() == null )
			qtmTlKnowwhymst.setKnwmFlid("{}");
		
		if( qtmTlKnowwhymst.getKnwmPillarid() == null )
			qtmTlKnowwhymst.setKnwmPillarid("{}");
		
		if( qtmTlKnowwhymst.getKnwmType() == null )
			qtmTlKnowwhymst.setKnwmType("{}");
		if( qtmTlKnowwhymst.getKnwmImage() == null )
			qtmTlKnowwhymst.setKnwmImage("{}");
		if( qtmTlKnowwhymst.getKnwmQuality() == null )
			qtmTlKnowwhymst.setKnwmQuality("{}");
		
		if( qtmTlKnowwhymst.getKnwmElementid() == null )
			qtmTlKnowwhymst.setKnwmElementid("{}");
		
		if( qtmTlKnowwhymst.getKnwmApprovedby() == null )
			qtmTlKnowwhymst.setKnwmApprovedby("{}");
		
		if( qtmTlKnowwhymst.getKnwmDescription() == null )
			qtmTlKnowwhymst.setKnwmDescription("{}");
		
		if( qtmTlKnowwhymst.getKnwmDevelopedby() == null )
			qtmTlKnowwhymst.setKnwmDevelopedby("{}");
		
		
		if( qtmTlKnowwhymst.getKnwmPhenomena() == null )
			qtmTlKnowwhymst.setKnwmPhenomena("-");
		
		
		if( qtmTlKnowwhymst.getKnwmPrepareddate() == null )
			qtmTlKnowwhymst.setKnwmPrepareddate("-");
		
	
		if( qtmTlKnowwhymst.getKnwmVersionno() == null )
			qtmTlKnowwhymst.setKnwmVersionno("1");
		if( qtmTlKnowwhymst.getKnwmVersiondate() == null )
			qtmTlKnowwhymst.setKnwmVersiondate(dateTime);

		if( qtmTlKnowwhymst.getKnwmTempfield5() == null )
			qtmTlKnowwhymst.setKnwmTempfield5("0");

		
		if(qtmTlKnowwhymst.getqtmTlKnowwhydtl()!=null)
			 qtmTlKnowwhymst.setQtmTlKnowwhydtl(fillValuesqtmTlKnowwhydtl( qtmTlKnowwhymst, existQtmTlKnowwhymst,knowwhybean));
		
			 return qtmTlKnowwhymst;
		}

	private QtmTlKnowwhydtl fillValuesqtmTlKnowwhydtl(
			QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) {
		// TODO Auto-generated method stub
		QtmTlKnowwhydtl  newQtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
		CommonMessage.debugMsg("serviceimpl 4");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<QtmTlKnowwhydtl> qtmTlKnowwhydtl = new ArrayList<QtmTlKnowwhydtl>();
		
			
		
		newQtmTlKnowwhydtl.setKnwdActive("Y");
		 
		 CommonMessage.debugMsg("serviceimpl 41");
		 newQtmTlKnowwhydtl.setKnwdModifiedon(dateTime);
		 newQtmTlKnowwhydtl.setKnwdCreatedon(dateTime);


		
		if( newQtmTlKnowwhydtl.getKnwdKnowwhy() == null )
			newQtmTlKnowwhydtl.setKnwdKnowwhy("{}");


		if( newQtmTlKnowwhydtl.getKnwdNormalcondition() == null )
			newQtmTlKnowwhydtl.setKnwdNormalcondition("{}");
		
		if( newQtmTlKnowwhydtl.getKnwdPossiblecauses() == null )
			newQtmTlKnowwhydtl.setKnwdPossiblecauses("{}");
		
		if( newQtmTlKnowwhydtl.getKnwdSolution() == null )
			newQtmTlKnowwhydtl.setKnwdSolution("{}");
		
		CommonMessage.debugMsg("serviceimpl 43");
		if( newQtmTlKnowwhydtl.getKnwdSustenanceaction() == null )
			newQtmTlKnowwhydtl.setKnwdSustenanceaction("-");
		
		
		if( newQtmTlKnowwhydtl.getKnwdTempfield2() == null )
			newQtmTlKnowwhydtl.setKnwdTempfield2("-");
		
	
		if( newQtmTlKnowwhydtl.getKnwdTempfield3() == null )
			newQtmTlKnowwhydtl.setKnwdTempfield3("-");
		
		if( newQtmTlKnowwhydtl.getKnwdTempfield4() == null )
			newQtmTlKnowwhydtl.setKnwdTempfield4("-");
		
		if( newQtmTlKnowwhydtl.getKnwdTempfield5() == null )
			newQtmTlKnowwhydtl.setKnwdTempfield5("-");
			CommonMessage.debugMsg("serviceimpl 5");
			
		
		return newQtmTlKnowwhydtl;
	
	}

	@Override
	public QtmTlKnowwhymst getknwwhy(String keyId, String fileDir, String imagepath) throws Exception {
		// TODO Auto-generated method stub
		//return this.qtmtlknowwhymstdao.getknwwhy(keyId,fileDir,imagepath);
		
		try {
			QtmTlKnowwhymst mst =  serviceApi.getById(keyId);
			qtmtlknowwhymstdao.restorefile( keyId,fileDir,imagepath,mst);
			return mst;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

/*	@Override
	public QtmTlKnowwhydtl createknowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,
			QtmTlKnowwhydtl existQtmTlKnowwhydtl, Knowwhybean knowwhybean)  throws Exception,ValidationExceptions {
		// TODO Auto-generated method stub
		
		try {
			CommonMessage.debugMsg("serviceimpl 1");
			String validationsFor;
			validationsFor = "create";	
			//validations.validate(qtmTlKnowwhydtl, "Knowwhy",validationsFor);
			//validations.validate(qtmTlKnowwhydtl, "Knowwhy","create");
			//QtmTlKnowwhydtl qtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
			
				validations.validate(qtmTlKnowwhydtl,"Knowwhy",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			
			CommonMessage.debugMsg("serviceimpl 2");
			fillValuesqtmTlKnowwhydtl( qtmTlKnowwhydtl, existQtmTlKnowwhydtl,knowwhybean);
			CommonMessage.debugMsg("serviceimpl 6");
			return this.qtmtlknowwhymstdao.createknowwhydtl(qtmTlKnowwhydtl);
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}

	@Override
	public QtmTlKnowwhydtl updateknowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,
			QtmTlKnowwhydtl existQtmTlKnowwhydtl, Knowwhybean knowwhybean)  throws Exception,ValidationExceptions {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("serviceimpl 1");
			String validationsFor;
			validationsFor = "create";	
			//validations.validate(qtmTlKnowwhydtl, "Knowwhy",validationsFor);
			//validations.validate(qtmTlKnowwhydtl, "Knowwhy","create");
			//QtmTlKnowwhydtl qtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
			
				validations.validate(qtmTlKnowwhydtl,"Knowwhy",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			
			CommonMessage.debugMsg("serviceimpl 2");
			fillValuesqtmTlKnowwhydtl( qtmTlKnowwhydtl, existQtmTlKnowwhydtl,knowwhybean);
			CommonMessage.debugMsg("serviceimpl 6");
			return this.qtmtlknowwhymstdao.updateknowwhydtl(qtmTlKnowwhydtl);
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}

	private QtmTlKnowwhydtl fillValuesqtmTlKnowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,
			QtmTlKnowwhydtl existQtmTlKnowwhydtl, Knowwhybean knowwhybean) {
		// TODO Auto-generated method stub
		qtmTlKnowwhydtl.setKnwdActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		 CommonMessage.debugMsg("serviceimpl 41");
		 qtmTlKnowwhydtl.setKnwdModifiedon(dateTime);
		 qtmTlKnowwhydtl.setKnwdCreatedon(dateTime);


		
		if( qtmTlKnowwhydtl.getKnwdKnowwhy() == null )
			qtmTlKnowwhydtl.setKnwdKnowwhy("{}");


		if( qtmTlKnowwhydtl.getKnwdNormalcondition() == null )
			qtmTlKnowwhydtl.setKnwdNormalcondition("{}");
		
		if( qtmTlKnowwhydtl.getKnwdPossiblecauses() == null )
			qtmTlKnowwhydtl.setKnwdPossiblecauses("{}");
		
		if( qtmTlKnowwhydtl.getKnwdSolution() == null )
			qtmTlKnowwhydtl.setKnwdSolution("{}");
		
		CommonMessage.debugMsg("serviceimpl 43");
		if( qtmTlKnowwhydtl.getKnwdTempfield1() == null )
			qtmTlKnowwhydtl.setKnwdTempfield1("-");
		
		
		if( qtmTlKnowwhydtl.getKnwdTempfield2() == null )
			qtmTlKnowwhydtl.setKnwdTempfield2("-");
		
	
		if( qtmTlKnowwhydtl.getKnwdTempfield3() == null )
			qtmTlKnowwhydtl.setKnwdTempfield3("-");
		
		if( qtmTlKnowwhydtl.getKnwdTempfield4() == null )
			qtmTlKnowwhydtl.setKnwdTempfield4("-");
		
		if( qtmTlKnowwhydtl.getKnwdTempfield5() == null )
			qtmTlKnowwhydtl.setKnwdTempfield5("-");
			CommonMessage.debugMsg("serviceimpl 5");
			
		
		return qtmTlKnowwhydtl;
		
	}*/

	@Override
	public QtmTlKnowwhydtl deleteknwwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,String dtlkeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.qtmtlknowwhymstdao.deleteknwwhydtl(qtmTlKnowwhydtl,dtlkeyid);
	}

	@Override
	public QtmTlKnowwhymst delete(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existqtmTlKnowwhymst, Knowwhybean newKnowwhybean) throws Exception {
		// TODO Auto-generated method stub
		//return this.qtmtlknowwhymstdao.delete(qtmTlKnowwhymst);
		String preparedDate = qtmTlKnowwhymst.getKnwmPrepareddate();
		String versionDate = qtmTlKnowwhymst.getKnwmVersiondate();
		qtmTlKnowwhymst.setKnwmVersiondate(CommonFunctions.pg_getDateTimeFromDate(versionDate));
		qtmTlKnowwhymst.setKnwmPrepareddate(CommonFunctions.pg_getDateTimeFromDate(preparedDate));
		qtmTlKnowwhymst.setKnwmCreatedon(CommonFunctions.pg_getDateTimeFromDate(preparedDate));
		qtmTlKnowwhymst.setKnwmModifiedon(CommonFunctions.pg_getDateTimeFromDate(preparedDate));
		return serviceApi.DeleteKnowWhy(qtmTlKnowwhymst);
	}

	@Override
	public Workbook getknowExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return this.qtmtlknowwhymstdao.getknowExcel(colmodel,format,commonFilter);
	}

	@Override
	public List<String[]> getKnowrpt(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return this.qtmtlknowwhymstdao.getKnowrpt(commonFilter);
	}

	@Override
	public Workbook knowWhyExportExcel(String knowId, String format,
			String path, String imagePath, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		 String flid=commonFilter.getFlid();
		 CommonMessage.debugMsg(" flid :: "+flid);
		 List<String[]> KnowwhyData = qtmtlknowwhymstdao.KnowwhyExcelReport(knowId,format,commonFilter);
		 
		 List<String[]> ApprovalData = improvementProjSheetKaiDao.getImprovementProjSheetkaiexlReportApproval("",flid,"KNWWHYAPPR",knowId);
		 
		 CommonMessage.debugMsg("knwwhy");
			//CommonMessage.debugMsg(" Inside Service Impl :: "+KnowwhyData+" Inside Service Impl :: "+KnowwhyData.get(1));
			Workbook wb=(new KnowWhyExcelTemplate(dbActionTemplate)).fillValues(KnowwhyData,ApprovalData,format, path, knowId, imagePath);
			//CommonMessage.debugMsg("Workbook::::::: service impl:"+wb.getSheetAt(0));
			
			CommonMessage.debugMsg("Service implto be gae........."+wb);
			return wb;
			
		}

	@Override
	public List<GenTlAllmoduleimgfile> getKnwwhyImage(String fileName,
			String path, String knwwhyId) throws Exception {
		// TODO Auto-generated method stub
		List<GenTlAllmoduleimgfile> knwwhyImgList = new ArrayList<GenTlAllmoduleimgfile>();
		GenTlAllmoduleimgfile knwwhyImage = new GenTlAllmoduleimgfile();
		
		knwwhyImage.setImflBlobimage(path);
		knwwhyImage.setImflFilename(fileName);
		knwwhyImage.setImflRefkeyid(knwwhyId);
		knwwhyImage.setImflRefdoctype("KNW");
		knwwhyImage.setImflImagetype("KNW");
		knwwhyImgList.add(knwwhyImage);
		
		knwwhyImgList = qtmtlknowwhymstdao.getKnwImage(knwwhyImgList );
		
		return knwwhyImgList;
	}

	@Override
	public QtmTlKnowwhymst createdtl(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("serviceimpl 1");
			String validationsFor;
			validationsFor = "create";	
			
			
			CommonMessage.debugMsg("check value :  "+qtmTlKnowwhymst.getCheckPhenomena());
			if(!UIUtils.isValidKeyId(qtmTlKnowwhymst.getCheckPhenomena()))
				qtmTlKnowwhymst.setCheckPhenomena("N");
			CommonMessage.debugMsg("check value :  "+qtmTlKnowwhymst.getCheckPhenomena()+" phenomena  : "+qtmTlKnowwhymst.getKnwmPhenomena()+"  quality : "+qtmTlKnowwhymst.getKnwmQuality());
			validations.validate(qtmTlKnowwhymst, "Knowwhy",validationsFor);
			//validations.validate(qtmTlKnowwhydtl, "Knowwhy","create");
			QtmTlKnowwhydtl qtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();			
			CommonMessage.debugMsg("serviceimpl 2");
			fillValues(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
			CommonMessage.debugMsg(qtmTlKnowwhymst.getCheckPhenomena() + " checkvalue");
			//return this.qtmtlknowwhymstdao.createdtl(qtmTlKnowwhymst);
			return serviceApi.saveKnowWhy(qtmTlKnowwhymst);
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}

	@Override
	public QtmTlKnowwhymst updatedtl(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("serviceimpl 1");
			String validationsFor;
			validationsFor = "update";	
		validations.validate(qtmTlKnowwhymst, "Knowwhy",validationsFor);
		QtmTlKnowwhydtl qtmTlKnowwhydtl = qtmTlKnowwhymst.getqtmTlKnowwhydtl();
		CommonMessage.debugMsg(" service impl" );
		CommonMessage.debugMsg(" service impl 2" );
		fillValues(qtmTlKnowwhymst,existQtmTlKnowwhymst,knowwhybean);
		
		//return this.qtmtlknowwhymstdao.updatedtl(qtmTlKnowwhymst);
		return serviceApi.saveKnowWhy(qtmTlKnowwhymst);
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
}

	@Override
	public int getStatusCount(String keyid) throws Exception {
		// TODO Auto-generated method stub
		
		return qtmtlknowwhymstdao.getStatusCount(keyid);
	}
	
public String  getkeyiddetail(String keyidd)throws Exception{
	String count=null;
	try{
	
		CommonMessage.debugMsg("appKeyid::::::"+keyidd);
		String Sql=" SELECT COUNT(*) FROM QTM_TL_KNOWWHY_APPROVALHIST WHERE  WRIN_REF_ID='"+keyidd+"'";      
		count=dbActionTemplate.getSingleValue(Sql);   
	}
	catch(SQLException ex){
		ex.printStackTrace();
	}
	return count;
	}

public String getapprovalkeyid(String appKeyid)throws Exception{
	     //return  qtmtlknowwhymstdao.getapprovalkeyid(appKeyid);
		return serviceApi.saveKnowWhyApproval(appKeyid);
	     
}
public List<String[]> getKnwwhycnt(CommonFilter commonFilter)	throws Exception {
	return this.qtmtlknowwhymstdao.getKnwwhycnt(commonFilter);
}
public Workbook KnowWhyCountExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception{
	return this.qtmtlknowwhymstdao.KnowWhyCountExportExcel(commonFilter, colmodel, rptFormat);
}

public static boolean isValidDateFormat(String dateStr) {
    DateTimeFormatter formatter =
    		 DateTimeFormatter.ofPattern("d-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
    if (dateStr == null || dateStr.trim().isEmpty()) {
        return false;   // OR true if date is optional
    }
    try {
        LocalDate.parse(dateStr, formatter);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}

}

