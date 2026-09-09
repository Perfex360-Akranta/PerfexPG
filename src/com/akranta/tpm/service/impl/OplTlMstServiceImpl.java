	/*Created By : Siddharth.A*/
	package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.OplTlMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.OplTlMstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.OplTlLesson;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.model.OplTlPillarlink;
//import com.akranta.tpm.model.PlmTlSparedtl;
import com.akranta.tpm.service.OplTlMstService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

	public class OplTlMstServiceImpl implements OplTlMstService {
		
		private OplTlMstDao oplTlMstDao ; 
		private CommonFilterDao commonFilterDao;
		private Validations validations ;
		//-- added by vignesh -- //
		private OplTlMstServiceApi oplServiceApi;	
		
		public OplTlMstServiceImpl(DBActionTemplate dbActionTemplate)
		{
			oplTlMstDao = new OplTlMstDaoImpl(dbActionTemplate);
			commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
			validations = new Validations();
		}
		
		
		public void OplTlMstServiceImplJwt(String JwtToken){
		    try{
		    	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
		        // (Optional) if you want service-level direct access
		         oplServiceApi = new OplTlMstServiceApi(JwtToken);
		    } catch(Exception e){
		        e.printStackTrace();
		    }
		}

		
		public void setOplTlMstDao(OplTlMstDao oplTlMstDao)
		{
			this.oplTlMstDao = oplTlMstDao;
		}
		
		 
	
		@Override
		public OplTlMst create(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception {

			try 
			{
				CommonMessage.debugMsg(" Create=== "+oplFormBean.getFormMode());
				String validationsFor;
				
				if(oplFormBean.getFormActionMode() != null && oplFormBean.getFormActionMode().equals("category") ){
					
					validationsFor = "category";
					validations.validate(newOplTlMst,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					validations.validate(oplFormBean,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					
				}
				//else if(UIUtils.isValidKeyId(oplFormBean.getFormActionMode()))
				//	validationsFor = "approval";
				else if(oplFormBean.getFormMode()!=null){
					validationsFor = "create";
					validations.validate(newOplTlMst,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					validations.validate(oplFormBean,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
				}else {
					validationsFor = "create";
					validations.validate(newOplTlMst,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					validations.validate(oplFormBean,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
				
				
				/*if(newOplTlMst!= null && newOplTlMst.getOplmRelated().equalsIgnoreCase("MLD"))
				validations.validate(newOplTlMst,"oplcreation","mould");
				*/
				CommonMessage.debugMsg(" Checking For Approval Mode :: "+oplFormBean.getFormActionMode());
				
				//if(oplFormBean.getFormActionMode().equals("approval")||oplFormBean.getFormMode().equals("approval")){
				if(UIUtils.isValidKeyId(oplFormBean.getFormActionMode())&& "approval".equals(oplFormBean.getFormMode())){
					validationsFor = "approval";
					validations.validate(oplFormBean,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
				} 
				
				
				fillValues(newOplTlMst,oldOplTlMst,oplFormBean);
			
				
				BdmTlYycountermeasurelink bdmTlYycountermeasurelink = fillYYLinkValues(newOplTlMst,oplFormBean);
				GenTlDocupdates genTlDocupdates = fillDocUpdates(newOplTlMst,oplFormBean);
				
				
				//return oplTlMstDao.create(newOplTlMst,bdmTlYycountermeasurelink,genTlDocupdates);
				return oplServiceApi.insertRecord(newOplTlMst, bdmTlYycountermeasurelink, genTlDocupdates);

			//    return oplTlMstDao.createViaApi(newOpl);
				
			}
			catch (ValidationExceptions e)
			{
				System.out.print(" validate n create exception"+e.getLocalizedMessage());
				throw new ValidationExceptions(e.getMessage());
			}	
		}

		@Override
		public OplTlMst update(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean)  throws ValidationExceptions, Exception 
		{
			
			//CommonMessage.debugMsg(" Inside service Impl :: Update 22 "+oplFormBean.getFormActionMode());
			String validationsFor;
			if(newOplTlMst != null && newOplTlMst.getOplmRelated().equalsIgnoreCase("MLD"))
				validations.validate(newOplTlMst,"oplcreation","mould");
			
			validations.validate(newOplTlMst,"oplcreation","update");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
				
			//if(oplFormBean.getFormActionMode()!=null)
			validations.validate(oplFormBean,"oplcreation","update");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			CommonMessage.debugMsg(" Inside SErvice :: "+oplFormBean.getFormMode());

			//commented by nkk - to avoid "data not saving" in approval mode - 09Aug2014
            /*if(UIUtils.isValidKeyId(oplFormBean.getFormActionMode()) && oplFormBean.getFormActionMode().equals("approval")){//CommonMessage.debugMsg(" Inside SErvice :: 1121121414 "+oplFormBean.getFormMode());
				
				CommonMessage.debugMsg(" Inside SErvice :: 1121121414 "+oplFormBean.getFormActionMode());
                validationsFor = "approval";
				validations.validate(oplFormBean,"oplcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
			*/
			
			fillValues(newOplTlMst,oldOplTlMst,oplFormBean);
			
			BdmTlYycountermeasurelink bdmTlYycountermeasurelink = fillYYLinkValues(newOplTlMst,oplFormBean);
			
			GenTlDocupdates genTlDocupdates = fillDocUpdates(newOplTlMst,oplFormBean);
			
		//	return oplTlMstDao.update(newOplTlMst,bdmTlYycountermeasurelink,genTlDocupdates);
			
			return oplServiceApi.insertRecord(newOplTlMst, bdmTlYycountermeasurelink, genTlDocupdates);
			
		}
		
        @Override
		public OplTlLesson insertIntoLesson(OplTlLesson oplTlLesson)  throws ValidationExceptions, Exception {
			fillLessonValues(oplTlLesson);
			return oplTlMstDao.insertIntoLesson(oplTlLesson);
		}
		
	
		@Override
		public OplTlMst delete(OplTlMst oplTlMst) throws Exception {
	
			return oplTlMstDao.delete(oplTlMst);
		}
		
		
		@Override
		public OplTlMst select(String oplKeyid) throws Exception {
			CommonMessage.debugMsg("in serbssd");
			
			  return oplServiceApi.getByKeyid(oplKeyid);
		//	return oplTlMstDao.select(oplKeyid);
			  // -- changed by Vignesh	
		}
		

		public List<ComboBox> getDocumentNoCombo(ComboFilter doccomboFilter ) throws Exception
		{
			
			doccomboFilter.setCodeField("OPLM_KEYID");
			doccomboFilter.setIdField("OPLM_KEYID");
			doccomboFilter.setTableName(TableNames.TBL_OPL_TL_MST);
			
			return commonFilterDao.fillComboValues(doccomboFilter);
			
		}
		
		public List<String[]> getAllOplReport(CommonFilter commonFilter,String emppillar) throws Exception
		{
			return this.oplTlMstDao.getOplReportDao(commonFilter,emppillar);
		}
		
		@Override
		public List<String[]> getFourQuadrantmatrix(CommonFilter commonFilter,String flid, String cellid,String Empid)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getFourQuadrantmatrix(commonFilter,flid,cellid,Empid);
		}
		@Override
		public List<String[]> getFourQuadrantmatrixDataWise(
				CommonFilter commonFilter, String flid) throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getFourQuadrantmatrixDataWise(commonFilter,flid);
		}		

		@Override
		public Workbook getFourQuarExcelDatewise(JSONObject colmodel,
				String format, CommonFilter commonFilter, String imagepath)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getFourQuarExcelDatewise(colmodel,format,commonFilter,imagepath);
		}

		
		@Override
		public List<String[]> getAllFourQudrantReport(CommonFilter commonFilter)throws Exception {
			
			return this.oplTlMstDao.getAllFourQudrantReport(commonFilter);
		}

		
		public List<String[]> getAllStudent(String opllOplid,String cellId, String oplKeyid) throws Exception 
		{
		
		//	return this.oplTlMstDao.getStudents(opllOplid,cellId,oplKeyid);
			// -- VIGNESH
			return this.oplServiceApi.getStudents(opllOplid,cellId,oplKeyid);
		}
		
	
		public List<String[]>  getAllPillarNameCodes(String oplId) throws Exception {
			
			return this.oplTlMstDao.getPillarNameCodes(oplId);
		}
		
		public List<String[]> getAllImprvCategory(List<String> pillarId)throws Exception
		{
			
			return this.oplTlMstDao.getImprvCategory(pillarId);
		}
		
		public List<String[]> selectLesson(String oplloplId, String studId)throws Exception
		{
			return this.oplTlMstDao.selectLesson(oplloplId,studId);
		}
		
		public Workbook oplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType) throws Exception
		{
			
			
			return this.oplTlMstDao.oplRptExportExcel(commonFilter,tblJSONObj,reportType);
		}

		@Override
		public Workbook getEmPillarOPLReportExcel(JSONObject colmodel,
				String format, CommonFilter commonFilter,String emppillar) throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getEmPillarOPLReportExcel(colmodel,format,commonFilter,emppillar);
		}

		@Override
		public Workbook getoplfourquadrantmatrix(JSONObject colmodel,
				String format, CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getoplfourquadrantmatrix(colmodel,format,commonFilter);
		}
		
		@Override
		public List<String[]> getAllOplEmpPillarMainGrid(
				CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getAllOplEmpPillarMainGrid(commonFilter);
		}
		
	
		
		@Override
		public List<String[]> getdatevalidate(String keyId, String rowId) throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getdatevalidate(keyId,rowId);
		}
		@Override
		public List<String[]> getdatevalidation(String keyId)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getdatevalidation(keyId);
		}

		@Override
		public List<String[]> getdatevalidating(String keyId, String rowId)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.getdatevalidating(keyId,rowId);
		}

		public OplTlMst updateDocUpdates(OplTlMst oplTlMst, String docId,String yyId)throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.updateDocUpdates(oplTlMst,docId,yyId);
		}
		public OplTlMst updateApprovedStatusLevel(String status,String keyid, String nextLevel, String type, String value,String mpValue) throws Exception {
			return this. oplTlMstDao.updateApprovedStatusLevel(status,keyid, nextLevel,type,value,mpValue) ;
		}
		

		private List<OplTlPillarlink> fillPillarLinkValues(OplTlMst newOplTlMst,OplTlMst oldOplTlMst) 
		{
			CommonMessage.debugMsg("Inside pillar fillval");
		//	String dateTime = CommonFunctions.pg_getDate();
			String dateTime = CommonFunctions.pg_dateTimeNow();
			List<OplTlPillarlink> newOplTlPillarlink = newOplTlMst.getPillarLink();
			List<OplTlPillarlink> oldOplTlPillarLinks = null;
			OplTlPillarlink oldOplTlPillarlink  = null;
		
			
			if( oldOplTlMst != null)
			{
				oldOplTlPillarLinks = oldOplTlMst.getPillarLink();
			
				if( oldOplTlPillarLinks != null && oldOplTlPillarLinks.size() > 0 )
					oldOplTlPillarlink = oldOplTlPillarLinks.get(0);
			}	
			CommonMessage.debugMsg("before insert");
			
			List<OplTlPillarlink> newOplTlPillarlinkList = new ArrayList<OplTlPillarlink>();
			for( OplTlPillarlink oplTlPillarlink : newOplTlPillarlink)
			{	
				if(oplTlPillarlink.getDbMode().equals("INSERT"))			
				{	
					oplTlPillarlink.setOpplCreatedon(dateTime);
				}	
				else
				{
					oplTlPillarlink.setOpplCreatedon(oldOplTlPillarlink.getOpplCreatedon());
				}
				CommonMessage.debugMsg("newOplTlMst.getOplmKeyid()="+newOplTlMst.getOplmKeyid());
			
				if( oplTlPillarlink.getOpplTpmpillarid() == null )
					oplTlPillarlink.setOpplTpmpillarid("{}");
				
				if(oplTlPillarlink.getOpplOplcategoryid() == null)
					oplTlPillarlink.setOpplOplcategoryid("{}");
					
				oplTlPillarlink.setOpplCreatedby(newOplTlMst.getOplmCreatedby());
				
				if(oplTlPillarlink.getSelectionFlag().equals("DELETE"))
					oplTlPillarlink.setDbMode("DELETE");
							   
				if(oplTlPillarlink.getSelectionFlag().equals("UPDATE"))
					oplTlPillarlink.setDbMode("UPDATE");
				newOplTlPillarlinkList.add(oplTlPillarlink);
			}
			return newOplTlPillarlinkList;
	    }
			
		@Override
		public List<GenTlAllmoduleimgfile> saveOplImg(OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft)throws Exception
		{
			// TODO Auto-generated method stub
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
			CommonMessage.debugMsg("newGenTlAllmoduleimgfile"+newGenTlAllmoduleimgfile.size()+""+newGenTlAllmoduleimgfile);
			if( newGenTlAllmoduleimgfile != null )//&& CommonFunctions.isValidKeyId(newGenTlAllmoduleimgfile.getToimFilename()) )
			{
				
				newGenTlAllmoduleimgfile = fillOplImgValues(oplTlMst);
				return this.oplTlMstDao.saveOplImg(oplTlMst,keyid,imagetypepre,imagetypeaft);
			}
			return null;
		}
		

		@Override
		public  OplTlMst getoplImage( String fileName, String filePath, OplTlMst oplTlMst)throws NoDataFoundException, Exception
		{
			// TODO Auto-generated method stub
			CommonMessage.debugMsg("Inside Service Impl img");
			List<GenTlAllmoduleimgfile> oplImgList = new ArrayList<GenTlAllmoduleimgfile>();
			if(oplTlMst!=null)
			{
		
				GenTlAllmoduleimgfile oplBeforeImage = new GenTlAllmoduleimgfile();
					
				oplBeforeImage.setImflBlobimage(filePath);
				oplBeforeImage.setImflFilename(fileName);
				oplBeforeImage.setImflRefkeyid(oplTlMst.getOplmKeyid());
				oplBeforeImage.setImflRefdoctype("OPL");
				oplBeforeImage.setImflImagetype("PRE");
				oplImgList.add(oplBeforeImage);
				
				GenTlAllmoduleimgfile oplAfterImage = new GenTlAllmoduleimgfile();
				
				oplAfterImage.setImflBlobimage(filePath);
				oplAfterImage.setImflFilename(fileName);
				oplAfterImage.setImflRefkeyid(oplTlMst.getOplmKeyid());
				oplAfterImage.setImflRefdoctype("OPL");
				oplAfterImage.setImflImagetype("AFT");
				oplImgList.add(oplAfterImage);

			}
			oplImgList = oplTlMstDao.getOplImage(oplImgList);
			for( GenTlAllmoduleimgfile genTlAllmoduleimgfile : oplImgList){
				if( "AFT".equals(genTlAllmoduleimgfile.getImflImagetype() ))
						oplTlMst.setOplmAfterimage(genTlAllmoduleimgfile.getImflFilename());
				else if( "PRE".equals(genTlAllmoduleimgfile.getImflImagetype()) )
						oplTlMst.setOplmPresentimage(genTlAllmoduleimgfile.getImflFilename());
			}
			oplTlMst.setAllmoduleimgfile(oplImgList);
			return oplTlMst;
		}

		private List<GenTlAllmoduleimgfile> fillOplImgValues(OplTlMst oplTlMst) 
		{
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
			List<GenTlAllmoduleimgfile> genTlAllmoduleimgfileList =new ArrayList<GenTlAllmoduleimgfile>();
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
			{
				CommonMessage.debugMsg("lllllll");
				String dateTime = CommonFunctions.pg_dateTimeNow();
				CommonMessage.debugMsg(dateTime);
				
				genTlAllmoduleimgfile.setImflRefkeyid(oplTlMst.getOplmKeyid());
				genTlAllmoduleimgfile.setImflModifiedon(oplTlMst.getOplmModifiedon());
				long length =0;
				
				String fileName = genTlAllmoduleimgfile.getImflFilename();
				if( CommonFunctions.isValidKeyId(fileName))
				{
					fileName =  fileName.substring(fileName.lastIndexOf("/")+1);
					genTlAllmoduleimgfile.setImflFilename(fileName );
					fileName =genTlAllmoduleimgfile.getImflBlobimage() + fileName;
					CommonMessage.debugMsg(" fileName " + fileName);
					genTlAllmoduleimgfile.setImflBlobimage(fileName);
					
					if( CommonFunctions.isFileExists(fileName ) )
						length = new File(fileName).length();
					
					genTlAllmoduleimgfile.setImflBloblength(Long.toString(length) );
					
					if(genTlAllmoduleimgfile.getImflRefdoctype()==null)
						genTlAllmoduleimgfile.setImflRefdoctype("OPL");
					if( genTlAllmoduleimgfile.getImflTempfield1() == null )
						genTlAllmoduleimgfile.setImflTempfield1("{}");
					if( genTlAllmoduleimgfile.getImflTempfield2() == null )
						genTlAllmoduleimgfile.setImflTempfield2("{}");
		
					CommonMessage.debugMsg("IMS : " +genTlAllmoduleimgfile.getImflRefkeyid());
					
					genTlAllmoduleimgfileList.add(genTlAllmoduleimgfile);
				}
			}
		
			return genTlAllmoduleimgfileList; 
		}

		private OplTlMst fillValues(OplTlMst newOplTlMst,OplTlMst oldOplTlMst, OplFormBean oplFormBean)
		{
			
			newOplTlMst.setOplmActive("Y");
			newOplTlMst.setOplmOplupload("N");
		//	String dateTime = CommonFunctions.pg_getDate();
			String dateTime = CommonFunctions.pg_dateTimeNow();
			// -- adding new --- vignesh
			
//			 if (dateTime != null && dateTime.length() >= 10 && dateTime.charAt(4) == '-' && dateTime.charAt(7) == '-') {
//			        dateTime = CommonFunctions.pg_getDateTimeFromDate(dateTime); // "06-Jan-2026"
//			    }
			 
			 newOplTlMst.setOplmCreatedon(dateTime);
			    newOplTlMst.setOplmModifiedon(dateTime);

			    // ✅ If OPLM_DATE exists but is ISO, convert it also (because DAO uses dd-Mon-yyyy mask)
			    if (newOplTlMst.getOplmDate() != null) {
			        String d = newOplTlMst.getOplmDate();
			        if (d.length() >= 10 && d.charAt(4) == '-' && d.charAt(7) == '-') {
			            newOplTlMst.setOplmDate(CommonFunctions.pg_getDateTimeFromDate(d));
			        }
			    } else {
			        newOplTlMst.setOplmDate(dateTime);
			    }
			 //   -----------------------------------------
			if(newOplTlMst.getOplmKeyid() == null )
			{	
				newOplTlMst.setOplmCreatedon(dateTime);
			}
			else
			{				
				newOplTlMst.setOplmCreatedon(dateTime);
			}
			
			newOplTlMst.setOplmModifiedon(dateTime);
			
			if(newOplTlMst.getOplmDate()==null)
				newOplTlMst.setOplmDate(dateTime);
			
			if(newOplTlMst.getOplmElementid() == null)
				newOplTlMst.setOplmElementid("{}");
			
			if( newOplTlMst.getOplmTpmpillarid() == null )
				newOplTlMst.setOplmTpmpillarid("{}");
		
			if( newOplTlMst.getOplmFactoryid() == null )
				newOplTlMst.setOplmFactoryid("{}");
			
			if( newOplTlMst.getOplmTheme() == null )
				newOplTlMst.setOplmTheme("{}");
			
			if( newOplTlMst.getOplmMachineid() == null )
				newOplTlMst.setOplmMachineid("{}");
			
			if( newOplTlMst.getOplmThemecategoryid() == null )
				newOplTlMst.setOplmThemecategoryid("{}");
			
			if( newOplTlMst.getOplmLesson() == null )
				newOplTlMst.setOplmLesson("{}");
			
			if( newOplTlMst.getOplmApprovedid() == null )
				newOplTlMst.setOplmApprovedid("{}");
			
			if( newOplTlMst.getOplmUtiliseforfuture() == null )
				newOplTlMst.setOplmUtiliseforfuture("N");
			else
				newOplTlMst.setOplmUtiliseforfuture("Y");
			
			if( newOplTlMst.getOplmMpworthy() == null )
				newOplTlMst.setOplmMpworthy("N");
		    else
				newOplTlMst.setOplmMpworthy("Y");
			
			if( newOplTlMst.getOplmIsUpload() == null )
				newOplTlMst.setOplmIsUpload("-");
			
			
			if( newOplTlMst.getOplmIsgeneral() == null )
				newOplTlMst.setOplmIsgeneral("-");
			
			if( newOplTlMst.getOplmTempfield4() == null )
				newOplTlMst.setOplmTempfield4("-");
			
			if( newOplTlMst.getOplmTempfield5() == null )
				newOplTlMst.setOplmTempfield5("-");
			
			String resultArea ="";
			
			if( oplFormBean.getClassificationB() != null )
				resultArea = oplFormBean.getClassificationB();
			if( oplFormBean.getClassificationI() != null )
				resultArea += oplFormBean.getClassificationI();
			if( oplFormBean.getClassificationT() != null )
				resultArea += oplFormBean.getClassificationT();
			if( oplFormBean.getClassificationS() != null )
				resultArea += oplFormBean.getClassificationS();
			if( oplFormBean.getClassificationC() != null )
				resultArea += oplFormBean.getClassificationC();
			if( oplFormBean.getClassificationP() != null )
				resultArea += oplFormBean.getClassificationP();

			CommonMessage.debugMsg("The resultArea:::"+resultArea);
			CommonMessage.debugMsg("OPLMClassification"+newOplTlMst.getOplmClassification());
			newOplTlMst.setOplmClassification(resultArea);
			
			if( newOplTlMst.getOplmClassdescription() == null )
				newOplTlMst.setOplmClassdescription("<**>");
			
			if( newOplTlMst.getOplmBenefit() == null )
				newOplTlMst.setOplmBenefit("{}");
				
			newOplTlMst.setOplmType("R");
			
			if( newOplTlMst.getOplmTradeid() == null )
				newOplTlMst.setOplmTradeid("{}");
		
			if( newOplTlMst.getOplmPresentcondition() == null )
				newOplTlMst.setOplmPresentcondition("<**>");
		
			if( newOplTlMst.getOplmAftercondition() == null )
				newOplTlMst.setOplmAftercondition("<**>");
		
			if( newOplTlMst.getOplmPresentimage() == null )
				newOplTlMst.setOplmPresentimage("{}");
		
			if( newOplTlMst.getOplmAfterimage() == null )
				newOplTlMst.setOplmAfterimage("<**>");
			
			if( newOplTlMst.getOplmPrepareddate() == null )
				newOplTlMst.setOplmPrepareddate(Constants.passNullDate);
			
			if( newOplTlMst.getOplmApproveddate() == null )
				newOplTlMst.setOplmApproveddate(Constants.passNullDate);
			
			
			if( newOplTlMst.getOplmRefdoctype() == null)
				newOplTlMst.setOplmRefdoctype("{}");
			
			if( newOplTlMst.getOplmFlid() == null)
				newOplTlMst.setOplmFlid("{}");
			
			if( newOplTlMst.getOplmProcess() == null)
				newOplTlMst.setOplmProcess("{}");
			
			
			if( newOplTlMst.getOplmElementid() == null)
				newOplTlMst.setOplmElementid("{}");
			
			if( newOplTlMst.getOplmRefdocno() == null)
				newOplTlMst.setOplmRefdocno("{}");
			
			if( newOplTlMst.getOplmRemarks() == null)
				newOplTlMst.setOplmRemarks("{}");
			
				newOplTlMst.setOplmRelatedto("C");
				
			if( newOplTlMst.getOplmDepartmentmanager() == null)
				newOplTlMst.setOplmDepartmentmanager("<**>");
			
			if( newOplTlMst.getOplmSectionmanager() == null)
				newOplTlMst.setOplmSectionmanager("<**>");
			
			if( newOplTlMst.getOplmGroupleader() == null)
				newOplTlMst.setOplmGroupleader("<**>");
			
				newOplTlMst.setOplmRequestflag("N");
			
				CommonMessage.debugMsg(" Inside Fill Values :: "+newOplTlMst.getOplmStatus());
				
			//if(newOplTlMst.getOplmStatus()==null)
				 //newOplTlMst.setOplmStatus("C");
			
			if( newOplTlMst.getOplmMouldid() == null)
				newOplTlMst.setOplmMouldid("{}");

			CommonMessage.debugMsg(" Inside Service Impl ::Before "+newOplTlMst.getOplmIsok());
			
			if( newOplTlMst.getOplmIsok()!=null && ( newOplTlMst.getOplmIsok().equalsIgnoreCase("ON") ||
					newOplTlMst.getOplmIsok().equalsIgnoreCase("Y")))
				newOplTlMst.setOplmIsok("Y");
			
			//else if( newOplTlMst.getOplmIsok().equals("N"))
				//newOplTlMst.setOplmIsok("N");
			
			else
				newOplTlMst.setOplmIsok("N");
			
			CommonMessage.debugMsg(" Inside Service After :: "+newOplTlMst.getOplmIsok());
			
			
			CommonMessage.debugMsg(" newOplTlMst :: Before  "+newOplTlMst.getOplmIspresent());
			
			
			if( newOplTlMst.getOplmIspresent()!=null && ( newOplTlMst.getOplmIspresent().equalsIgnoreCase("ON") ||
					newOplTlMst.getOplmIspresent().equalsIgnoreCase("Y")))
				newOplTlMst.setOplmIspresent("Y");
			
			if(UIUtils.isValidKeyId(newOplTlMst.getOplmIspresent())){
				if(newOplTlMst.getOplmIspresent().equals("Y")){
					newOplTlMst.setOplmIspresent("Y");	
				}
			}
				
			//else if( && newOplTlMst.getOplmIspresent()!=null)
				
			else
				newOplTlMst.setOplmIspresent("N");
			
			
			CommonMessage.debugMsg(" newOplTlMst :: After "+newOplTlMst.getOplmIspresent());
			
			
			CommonMessage.debugMsg(" Inside Service Impl :: "+oplFormBean.getOplmwhyhow()); 
			
			

			/*if(UIUtils.isValidKeyId(oplFormBean.getOplmwhyhow()) && UIUtils.isValidKeyId(newOplTlMst.getOplmIsok()))
		    	newOplTlMst.setOplmIsok("N");
		
			else*/ if(UIUtils.isValidKeyId(oplFormBean.getOplmwhyhow()))
				newOplTlMst.setOplmIsok("W");
			
			//else if(UIUtils.isValidKeyId(newOplTlMst.getOplmIsok()))
				//newOplTlMst.setOplmIsok("Y");
				
				
			//if((oplFormBean.getFormMode()==null)||( oplFormBean.getFormMode().equals("CREATE")  ))
				//newOplTlMst.setOplmStatus("P");
			//else
				//newOplTlMst.setOplmStatus("C");
			
			    //newOplTlMst.setOplmStatus("C");
			 
			newOplTlMst.setPillarLink( fillPillarLinkValues(newOplTlMst,oldOplTlMst));
			return newOplTlMst;
		}

		@Override
		public OplTlMst recall(String sectionId) throws Exception 
		{
			// TODO Auto-generated method stub
			
			OplTlMst oplmst= new OplTlMst();
			return oplmst;
		}

		
		public OplTlLesson fillLessonValues(OplTlLesson oplTlLesson) 
		{
			// TODO Auto-generated method stub
	
			CommonMessage.debugMsg("Inside fill LEsson Values");
			oplTlLesson.setOpllActive("Y");
		
			String dateTime = CommonFunctions.dateTimeNow();
			CommonMessage.debugMsg("lllllll"+dateTime);	
			CommonMessage.debugMsg("lllllll"+oplTlLesson.getOpllDate());	
			if(oplTlLesson.getOpllDate()==null)
				oplTlLesson.setOpllDate(dateTime);
		
			if( oplTlLesson.getOpllEmployeeid() == null )
				oplTlLesson.setOpllEmployeeid("{}");
			
		
			return oplTlLesson;
		}
		
		private BdmTlYycountermeasurelink fillYYLinkValues(OplTlMst newOplTlMst, OplFormBean oplFormBean) 
		{
			// TODO Auto-generated method stub
		
			BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
			
			bdmTlYycountermeasurelink.setYycmActive("Y");
			bdmTlYycountermeasurelink.setYycmTempfield1("-");
			bdmTlYycountermeasurelink.setYycmTempfield2("-");
			bdmTlYycountermeasurelink.setYycmTempfield3("-");
			bdmTlYycountermeasurelink.setYycmTempfield4("-");
			bdmTlYycountermeasurelink.setYycmTempfield5("-");
			bdmTlYycountermeasurelink.setYycmWoid("{}");
			bdmTlYycountermeasurelink.setYycmRefdoctype("OPL");
			
			if(UIUtils.isValidKeyId(oplFormBean.getYyId()))
				bdmTlYycountermeasurelink.setYycmYyid(oplFormBean.getYyId());
			
			if(UIUtils.isValidKeyId(newOplTlMst.getOplmKeyid()))
				bdmTlYycountermeasurelink.setYycmCountermsrid(newOplTlMst.getOplmKeyid());
			
			bdmTlYycountermeasurelink.setYycmCreatedby(newOplTlMst.getOplmCreatedby());
			bdmTlYycountermeasurelink.setYycmCreatedon(newOplTlMst.getOplmCreatedon());
			
			bdmTlYycountermeasurelink.setYycmModifieyon(newOplTlMst.getOplmModifiedon());
			
			
			if(UIUtils.isValidKeyId(oplFormBean.getYyId()))
				return bdmTlYycountermeasurelink;
			else
				return null;
			
		}

	
		private GenTlDocupdates fillDocUpdates(OplTlMst newOplTlMst,OplFormBean oplFormBean)
		{
			GenTlDocupdates genTlDocupdates = new GenTlDocupdates();
		
			genTlDocupdates.setDcupDetailid(newOplTlMst.getOplmKeyid());
			
			genTlDocupdates.setDcupUpdatedoctype("OPL");
			if(UIUtils.isValidKeyId(oplFormBean.getDocId()))
				genTlDocupdates.setDcupKeyid(oplFormBean.getDocId());
			
			if(UIUtils.isValidKeyId(oplFormBean.getDocId()))
				return genTlDocupdates;
			else
				return null;
		
		}

		
		@Override
		public Workbook getFourQuarExcel(JSONObject colmodel, String format,
				CommonFilter commonFilter,String imagepath) throws Exception {
			return this.oplTlMstDao.getFourQuarExcel(colmodel,format,commonFilter,imagepath);
		}

		@Override
		public void deleteimage(String keyid, String imagetype)
				throws Exception {
			// TODO Auto-generated method stub
			this.oplTlMstDao.deleteimage(keyid,imagetype);
		}

		@Override
		public List<String[]> FillEmployeeDatainGrid(String keyid)
				throws Exception {
			// TODO Auto-generated method stub
			return this.oplTlMstDao.FillEmployeeDatainGrid(keyid);
		
		}
		public List<String[]> getIndividualOplReport(CommonFilter commonFilter, String emppillar) throws Exception{
			return this.oplTlMstDao.getIndividualOplReport(commonFilter, emppillar);
		}
		
		public Workbook IndividualoplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType) throws Exception
		{
			return this.oplTlMstDao.IndividualoplRptExportExcel(commonFilter,tblJSONObj,reportType);
		}
		
		
		@Override
        public List<String[]> getOplUpdatedRow(String keyId) throws Exception {
            return this.oplTlMstDao.getOplUpdatedRow(keyId);
     }

		
 }
