package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.VisualConBean;
import com.akranta.tpm.bean.VisualControlCheckListBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.VisualControlChartDAO;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.VisualControlChartDAOImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlVisualcntchecklistdtl;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;
import com.akranta.tpm.model.SopTlVisualchecklistdtl;
import com.akranta.tpm.model.SopTlVisualchecklistmst;
import com.akranta.tpm.service.VisualControlCheckServices;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.VisualControlChecklistServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class VisualControlCheckServicesImpl implements VisualControlCheckServices

{  
	//private CommonFilterDao commonFilterDao;
private VisualControlChartDAO visualControlChartDAO;
private CommonFilterDao commonFilterdao;
private Validations validations ;

private  VisualControlChecklistServiceApi visualControlChecklistServiceApi;

public VisualControlCheckServicesImpl (DBActionTemplate dbActionTemplate)
{
	commonFilterdao = new CommonFilterDaoImpl(dbActionTemplate);
	visualControlChartDAO=  new VisualControlChartDAOImpl(dbActionTemplate);
	validations = new Validations();
}
	public List<String[]> getVisualControlGrid(CommonFilter commonFilter,String keyid)	throws Exception {
		CommonMessage.debugMsg(keyid);
		return this.visualControlChartDAO.getVisualControlGrid(commonFilter,keyid);

	}
	public List<String[]> getVisualControl(CommonFilter commonFilter)	throws Exception {
		return this.visualControlChartDAO.getVisualControl(commonFilter);

	}
	
	
	//03-01-2026
		public void VisualControlCheckServicesImplJwt(String JwtToken){
	    	try{
	    		visualControlChartDAO.VisualControlChecklistDaoImplJwt(JwtToken);
	    		visualControlChecklistServiceApi = new VisualControlChecklistServiceApi(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        // TODO Auto-generated constructor stub
	    }
	@Override
	public GenTlVisualcontrolchecklist create(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,GenTlVisualcontrolchecklist oldGenTlVisualcontrolchecklist,VisualControlCheckListBean visualControlCheckListBean)throws Exception {
		// TODO Auto-generated method stub
				try {
					validations.validate(newGenTlVisualcontrolchecklist, "VisualControlCheck","create");
					fillValues(newGenTlVisualcontrolchecklist,oldGenTlVisualcontrolchecklist);
					return visualControlChecklistServiceApi.insertRecord(newGenTlVisualcontrolchecklist,visualControlCheckListBean);
					//return visualControlChartDAO.create(newGenTlVisualcontrolchecklist,visualControlCheckListBean);
				}catch (ValidationExceptions e){
					CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
					throw new ValidationExceptions(e.getMessage());
				}	
		}

	@Override
	public GenTlVisualcontrolchecklist update(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,GenTlVisualcontrolchecklist existGenTlVisualcontrolchecklist,VisualControlCheckListBean visualControlCheckListBean)throws Exception {

			try {
					validations.validate(newGenTlVisualcontrolchecklist, "VisualControlCheck","update");
					fillValues(newGenTlVisualcontrolchecklist,existGenTlVisualcontrolchecklist);
					//return visualControlChartDAO.create(newGenTlVisualcontrolchecklist,visualControlCheckListBean);
					return visualControlChecklistServiceApi.insertRecord(newGenTlVisualcontrolchecklist,visualControlCheckListBean);
				}catch (ValidationExceptions e){
					CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
					throw new ValidationExceptions(e.getMessage());
				}	
	}

	private GenTlVisualcontrolchecklist fillValues(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,GenTlVisualcontrolchecklist oldGenTlVisualcontrolchecklist) {
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		String date = newGenTlVisualcontrolchecklist.getVcclDate();
		newGenTlVisualcontrolchecklist.setVcclDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		if( newGenTlVisualcontrolchecklist.getVcclDate() == null )
			newGenTlVisualcontrolchecklist.setVcclDate(dateTime);
		
		if( newGenTlVisualcontrolchecklist.getVcclEmployeeid() == null )
			newGenTlVisualcontrolchecklist.setVcclEmployeeid("{}");
		
		if( newGenTlVisualcontrolchecklist.getVcclFlid() == null )
			newGenTlVisualcontrolchecklist.setVcclFlid("{}");
		
		if( newGenTlVisualcontrolchecklist.getVcclTitle() == null )
			newGenTlVisualcontrolchecklist.setVcclTitle("{}");		
		
		if( newGenTlVisualcontrolchecklist.getVcclApprovedby() == null )
			newGenTlVisualcontrolchecklist.setVcclApprovedby("{}");
		
		if( newGenTlVisualcontrolchecklist.getVcclActive() == null )
			newGenTlVisualcontrolchecklist.setVcclActive("Y");		
		
		if( newGenTlVisualcontrolchecklist.getVcclTempfield3() == null )
			newGenTlVisualcontrolchecklist.setVcclTempfield3("-");
		
		if( newGenTlVisualcontrolchecklist.getVcclTempfield4() == null )
			newGenTlVisualcontrolchecklist.setVcclTempfield4("-");
		
		if( newGenTlVisualcontrolchecklist.getVcclTempfield5() == null )
			newGenTlVisualcontrolchecklist.setVcclTempfield5("-");
		
		if( newGenTlVisualcontrolchecklist.getVcclTempfield6() == null )
			newGenTlVisualcontrolchecklist.setVcclTempfield6("-");
		
		if( newGenTlVisualcontrolchecklist.getVcclCreatedon() == null )
			newGenTlVisualcontrolchecklist.setVcclCreatedon(dateTime);
			newGenTlVisualcontrolchecklist.setVcclModifiedon(dateTime);
		
			if( newGenTlVisualcontrolchecklist.getVcclCreatedby() == null )
			newGenTlVisualcontrolchecklist.setVcclCreatedby("{}");	
			
			
			//start ✅ Fill values for detail records
			if(newGenTlVisualcontrolchecklist.getVisualControlDetail() != null) {
				List<GenTlVisualcntchecklistdtl> detailList = fillValuesForDetails(
					newGenTlVisualcontrolchecklist, 
					oldGenTlVisualcontrolchecklist
				);
				newGenTlVisualcontrolchecklist.setVisualControlDetail(detailList);
			}
			//end
			
		return newGenTlVisualcontrolchecklist; 
		}
	
//start
	private List<GenTlVisualcntchecklistdtl> fillValuesForDetails(
			GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,
			GenTlVisualcontrolchecklist oldGenTlVisualcontrolchecklist) {
		
		List<GenTlVisualcntchecklistdtl> detailList = newGenTlVisualcontrolchecklist.getVisualControlDetail();
		List<GenTlVisualcntchecklistdtl> newDetailList = new ArrayList<GenTlVisualcntchecklistdtl>();
		
		for(GenTlVisualcntchecklistdtl detail : detailList) {
			fillValuesForDetail(detail, newGenTlVisualcontrolchecklist);
			newDetailList.add(detail);
		}
		
		return newDetailList;
	}

	/**
	 * Fill values for a single detail record
	 */
	private void fillValuesForDetail(
			GenTlVisualcntchecklistdtl genTlVisualcntchecklistdtl,
			GenTlVisualcontrolchecklist genTlVisualcontrolchecklist) {
		
		CommonMessage.debugMsg("Inside fillValuesForDetail..");
		
		// Set foreign key to master
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtVcclKeyid()))
			genTlVisualcntchecklistdtl.setVcdtVcclKeyid(genTlVisualcontrolchecklist.getVcclKeyid());

		// Set active status
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtActive()))
			genTlVisualcntchecklistdtl.setVcdtActive("Y");

		// Set timestamps
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtCreatedon()))
			genTlVisualcntchecklistdtl.setVcdtCreatedon(CommonFunctions.pg_dateTimeNow());

		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtModifiedon()))
			genTlVisualcntchecklistdtl.setVcdtModifiedon(CommonFunctions.pg_dateTimeNow());

		// Set temp fields with default values
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield1()))
			genTlVisualcntchecklistdtl.setVcdtTempfield1("-");

		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield2()))
			genTlVisualcntchecklistdtl.setVcdtTempfield2("-");

		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield3()))
			genTlVisualcntchecklistdtl.setVcdtTempfield3("-");

		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield4()))
			genTlVisualcntchecklistdtl.setVcdtTempfield4("-");

		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield5()))
			genTlVisualcntchecklistdtl.setVcdtTempfield5("-");

		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield6()))
			genTlVisualcntchecklistdtl.setVcdtTempfield6("-");
		
		// Set createdby from master if not present
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtCreatedby()))
			genTlVisualcntchecklistdtl.setVcdtCreatedby(genTlVisualcontrolchecklist.getVcclCreatedby());
	}
	
	//end
	
	@Override
	public GenTlVisualcontrolchecklist delete(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist) throws Exception {
		// TODO Auto-generated method stub
		//return this.visualControlChartDAO.delete(newGenTlVisualcontrolchecklist);
		return this.visualControlChecklistServiceApi.delete(newGenTlVisualcontrolchecklist);
	}
	@Override
	public Workbook getVisualExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return visualControlChartDAO.getVisualExcel(colmodel,format, commonFilter);
	}

	@Override
	public SopTlVisualchecklistmst create(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst,VisualConBean visualBean) throws Exception 
	{
		fillValues( newSopTlVisualchecklistmst,existSopTlVisualchecklistmst,visualBean);//
		// TODO Auto-generated method stub
		return visualControlChartDAO.create(newSopTlVisualchecklistmst) ;
	}
	
	
	private SopTlVisualchecklistmst fillValues(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst,VisualConBean visualBean) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		// newQtmTlSopdtl.setStudemail("{}");
		newSopTlVisualchecklistmst.setVccmActive("Y");
		if( newSopTlVisualchecklistmst.getVccmKeyid() == null )			
		{	
			newSopTlVisualchecklistmst.setVccmCreatedon(dateTime);			
		}					
		else
		{	
			//CommonMessage.debugMsg("Else");
			 //newQtmTlSopmst.setSopmCreatedon(existQtmTlSopmst.getSopmCreatedon());	
			newSopTlVisualchecklistmst.setVccmCreatedon(dateTime);
		}
		newSopTlVisualchecklistmst.setVccmModifiedon(dateTime);
		
		
		 if( newSopTlVisualchecklistmst.getVccmKeyid() == null )
			 newSopTlVisualchecklistmst.setVccmKeyid("{}");
		
		 
		
		if( newSopTlVisualchecklistmst.getVccmTitle() == null )
			newSopTlVisualchecklistmst.setVccmTitle("{}");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield1()== null )
			newSopTlVisualchecklistmst.setVccmTempfield1("{}");
		
		if( newSopTlVisualchecklistmst.getVccmFlid() == null )
			newSopTlVisualchecklistmst.setVccmFlid("-");
		 
		if( newSopTlVisualchecklistmst.getVccmTempfield2() == null )
			newSopTlVisualchecklistmst.setVccmTempfield2("-");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield3() == null )
			newSopTlVisualchecklistmst.setVccmTempfield3("-");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield4() == null )
			newSopTlVisualchecklistmst.setVccmTempfield4("-");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield5() == null )
			newSopTlVisualchecklistmst.setVccmTempfield5("-");
		
		 if( newSopTlVisualchecklistmst.getVccmCreatedby() == null )
			newSopTlVisualchecklistmst.setVccmCreatedby("-");
		
		
		
		
		
	
		return newSopTlVisualchecklistmst;
		// TODO Auto-generated method stub
		
	}
	
		
		
		
	
	@Override
	public SopTlVisualchecklistmst update(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst,VisualConBean visualBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SopTlVisualchecklistmst create(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst)
			throws Exception , ValidationExceptions {
		  if(newSopTlVisualchecklistmst.getVisualControlmstdet()==null ||newSopTlVisualchecklistmst.getVisualControlmstdet().size()<=0)
		  {
			CommonMessage.debugMsg("Service IMPl");
			throw new ValidationExceptions("VisualControlmstdet-required,");
		  }
		  
			validations.validate(newSopTlVisualchecklistmst,"VisualChecklist","create");
			validations.validate(newSopTlVisualchecklistmst.getVisualControlmstdet().get(0),"VisualChecklist","create");
			  CommonMessage.debugMsg(newSopTlVisualchecklistmst.getVisualControlmstdet());

			  
			fillValues(newSopTlVisualchecklistmst,existSopTlVisualchecklistmst);
			return visualControlChartDAO.create(newSopTlVisualchecklistmst);
	}

	private void fillValues(SopTlVisualchecklistmst newSopTlVisualchecklistmst,
			SopTlVisualchecklistmst existSopTlVisualchecklistmst) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		newSopTlVisualchecklistmst.setVccmActive("Y");
		if( newSopTlVisualchecklistmst.getVccmKeyid() == null )			
		{	
			newSopTlVisualchecklistmst.setVccmCreatedon(dateTime);			
		}					
		else
		{	
		    newSopTlVisualchecklistmst.setVccmCreatedon(dateTime);
		}
		    newSopTlVisualchecklistmst.setVccmModifiedon(dateTime);
		
		
		 if( newSopTlVisualchecklistmst.getVccmKeyid() == null )
			 newSopTlVisualchecklistmst.setVccmKeyid("{}");
		
		if( newSopTlVisualchecklistmst.getVccmTitle() == null )
			newSopTlVisualchecklistmst.setVccmTitle("{}");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield1()== null )
			newSopTlVisualchecklistmst.setVccmTempfield1("{}");
		
		if( newSopTlVisualchecklistmst.getVccmFlid() == null )
			newSopTlVisualchecklistmst.setVccmFlid("{}");
		 
		if( newSopTlVisualchecklistmst.getVccmTempfield2() == null )
			newSopTlVisualchecklistmst.setVccmTempfield2("-");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield3() == null )
			newSopTlVisualchecklistmst.setVccmTempfield3("-");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield4() == null )
			newSopTlVisualchecklistmst.setVccmTempfield4("-");
		
		if( newSopTlVisualchecklistmst.getVccmTempfield5() == null )
			newSopTlVisualchecklistmst.setVccmTempfield5("-");
		
		 if( newSopTlVisualchecklistmst.getVccmCreatedby() == null )
			newSopTlVisualchecklistmst.setVccmCreatedby("-");
		
		 newSopTlVisualchecklistmst.setVisualControlmstdet( fillValuesPlmTlPmtasklistdtl( newSopTlVisualchecklistmst, existSopTlVisualchecklistmst))	;
		
		// TODO Auto-generated method stub
		
	}
	

	
	private List<SopTlVisualchecklistdtl> fillValuesPlmTlPmtasklistdtl(
			SopTlVisualchecklistmst newSopTlVisualchecklistmst,
			SopTlVisualchecklistmst existSopTlVisualchecklistmst) {
		// TODO Auto-generated method stu
		
		List<SopTlVisualchecklistdtl> sopTlvisualchecklistdtl = newSopTlVisualchecklistmst.getVisualControlmstdet();
		List<SopTlVisualchecklistdtl> existsopTlvisualchecklistdtl = newSopTlVisualchecklistmst.getVisualControlmstdet();
		SopTlVisualchecklistdtl exitsopTlvisualchecklistdtl = null;
		
		if(existSopTlVisualchecklistmst != null){
			existsopTlvisualchecklistdtl =existSopTlVisualchecklistmst.getVisualControlmstdet();
			if(existsopTlvisualchecklistdtl != null && existsopTlvisualchecklistdtl.size()>0)
				exitsopTlvisualchecklistdtl=existsopTlvisualchecklistdtl.get(0);
		}
			
		
		List<SopTlVisualchecklistdtl> newsopTlvisualchecklistdtl = new ArrayList<SopTlVisualchecklistdtl>();
		//String dateTime = CommonFunctions.dateTimeNow();
		
		for( SopTlVisualchecklistdtl sopvisualchecklistdtl : sopTlvisualchecklistdtl)
			
		{
		if(!UIUtils.isValidKeyId(sopvisualchecklistdtl.getVccdActive()))
			sopvisualchecklistdtl.setVccdActive("Y");
	
		
		sopvisualchecklistdtl.setVccdCreatedby(newSopTlVisualchecklistmst.getVccmCreatedby());
		
		if(!UIUtils.isValidKeyId( sopvisualchecklistdtl.getVccdCheckpoints() ))
			sopvisualchecklistdtl.setVccdCheckpoints("{}");
		
		if(!UIUtils.isValidKeyId( sopvisualchecklistdtl.getVccdSortorder()) )
			sopvisualchecklistdtl.setVccdSortorder("1");
		
		if(!UIUtils.isValidKeyId(sopvisualchecklistdtl.getVccdCriteria() ) )
			sopvisualchecklistdtl.setVccdCriteria("-");
		 
		if(!UIUtils.isValidKeyId( sopvisualchecklistdtl.getVccdOrderno() ))
			sopvisualchecklistdtl.setVccdOrderno("-");
		
		if(!UIUtils.isValidKeyId( sopvisualchecklistdtl.getVccdTempfield3() ) )
			sopvisualchecklistdtl.setVccdTempfield3("-");
		
		if(!UIUtils.isValidKeyId( sopvisualchecklistdtl.getVccdTempfield4() ) )
			sopvisualchecklistdtl.setVccdTempfield4("-");
		
		if(!UIUtils.isValidKeyId( sopvisualchecklistdtl.getVccdTempfield5() ) )
			sopvisualchecklistdtl.setVccdTempfield5("-");

		 if(!UIUtils.isValidKeyId(sopvisualchecklistdtl.getVccdCreatedon()))
			 sopvisualchecklistdtl.setVccdCreatedon(CommonFunctions.dateTimeNow());
		 
		 if(!UIUtils.isValidKeyId(sopvisualchecklistdtl.getVccdModifiedon()))
			 sopvisualchecklistdtl.setVccdModifiedon(CommonFunctions.dateTimeNow());
		 
		 newsopTlvisualchecklistdtl.add(sopvisualchecklistdtl);
		}
		
		// TODO Auto-generated method stub
		return newsopTlvisualchecklistdtl;
	}
	@Override
	public SopTlVisualchecklistmst update(SopTlVisualchecklistmst newSopTlVisualchecklistmst,SopTlVisualchecklistmst existSopTlVisualchecklistmst)
			throws Exception, ValidationExceptions {
		
		validations.validate(newSopTlVisualchecklistmst,"VisualChecklist","create");
		validations.validate(newSopTlVisualchecklistmst.getVisualControlmstdet().get(0),"VisualChecklist","create");

			//validations.validate(newGenTlVisualcontrolchecklist, "VisualControlCheck","update");
			fillValues(newSopTlVisualchecklistmst,existSopTlVisualchecklistmst);
			CommonMessage.debugMsg("newSopTlVisualchecklistmst.getVccmKeyid();service impl;"+newSopTlVisualchecklistmst.getVccmKeyid());
			return visualControlChartDAO.create(newSopTlVisualchecklistmst);
	}

	@Override
	public Workbook getVisualExcelReport(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return visualControlChartDAO.getVisuaReportExcel(colmodel,format, commonFilter);
	}
	@Override
	public List<String[]> getVisualControlReport(CommonFilter commonFilter)throws Exception {
		return visualControlChartDAO.getVisualControlReport(commonFilter);
	}


	@Override
	public SopTlVisualchecklistmst getSelectvis(String keyId) throws Exception {
		return this.visualControlChartDAO. getSelectvis(keyId);
	}
	
	@Override
	public List<String[]> getSelectvisual(CommonFilter commonFilter) throws Exception {
		return this.visualControlChartDAO. getSelectvisual(commonFilter);
	}
	@Override
	public SopTlVisualchecklistmst delete(SopTlVisualchecklistmst newSopTlVisualchecklistmst)
			throws Exception {
		return this.visualControlChartDAO.delete(newSopTlVisualchecklistmst);
	}
	@Override
	public void Deletevis(String keyid) throws Exception {
		CommonMessage.debugMsg("Service Impl:");
		this.visualControlChartDAO.Deletevis(keyid);
		
	}
	@Override
	public Workbook getvisualWorkExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return visualControlChartDAO.getvisualWorkExcel(colmodel,format, commonFilter);
	
	}
	
	
	@Override
	public List<ComboBox> getRecordedbyComboList(ComboFilter comboFilter,String flid) throws Exception {
		//ComboFilter Recordedby = commonFilter.getRecordedby();
		comboFilter.setIdField("VCCM_KEYID");
		comboFilter.setNameField("VCCM_TITLE");	
		if(UIUtils.isValidKeyId(flid)) {
			//comboFilter.setCondSql(" AND VCCM_FLID='"+flid+"'");
			StringBuffer sb = new StringBuffer();
			sb.append(" AND VCCM_FLID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  " );
			sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
			sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
			comboFilter.setCondSql(sb.toString());

		}
		comboFilter.setTableName(TableNames.TBL_SOP_TL_VISUALCHECKLISTMST);
		
		//comboFilter.setCondSql(condSql)
		//CommonMessage.debugMsg(commonFilter.getFlid() + " service impl");
		return commonFilterdao.fillComboValues(comboFilter);
	}
	@Override
	public GenTlVisualcontrolchecklist getSelect(String keyId) throws Exception {
		// TODO Auto-generated method stub
		//return this.visualControlChartDAO.getSelect(keyId);
		return visualControlChecklistServiceApi.getRecordByKeyid(keyId);
	}
	@Override
	public List<String[]> getVisualControlcheckpoints(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return visualControlChartDAO.getVisualControlcheckpoints(commonFilter);
	}
	@Override
	public Workbook getVisualpointsExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("service impl excel");
		return visualControlChartDAO.getVisualpointsExcel(colmodel,format, commonFilter);
	}
	
	@Override
	public Workbook getVisualRptExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("service impl excel");
		return visualControlChartDAO.getVisualRptExcel(colmodel,format, commonFilter);
	}
	@Override
	public List<String[]> selecttitle(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return visualControlChartDAO.selecttitle(commonFilter);
	}
	
	//graph
	
	@Override
	public List<String[]> getVisualWPScoreGraph(CommonFilter commonFilter)throws Exception {
		return this.visualControlChartDAO.getVisualWPScoreGraph(commonFilter);
	}

	@Override
	public Workbook visualWPGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.visualControlChartDAO.visualWPGraphExportExcel(commonFilter,colModel,rptFormat);
	}

	
}
		
	