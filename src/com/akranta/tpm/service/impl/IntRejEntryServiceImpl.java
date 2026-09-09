package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.IntRejEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.IntRejEntryDao;
import com.akranta.tpm.dao.QtmTlInternalrejectionhourlyDao;
import com.akranta.tpm.dao.QtmTlIntrejectionmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.IntRejEntryDaoImpl;
import com.akranta.tpm.dao.impl.PcsEntryDaoImpl;
import com.akranta.tpm.dao.impl.QtmTlInternalrejectionhourlyDaoImpl;
import com.akranta.tpm.dao.impl.QtmTlIntrejectionmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlInternalrejectionhourly;
import com.akranta.tpm.model.QtmTlIntrejectiondtl;
import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.model.QtmTlTestscrapbrkup;
import com.akranta.tpm.service.IntRejEntryService;
import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
//import com.sun.corba.se.impl.orbutil.closure.Constant;

public class IntRejEntryServiceImpl implements IntRejEntryService {
	
	private IntRejEntryDao intRejEntryDao ;
	private CommonFilterDao commonFilterDao;
	private QtmTlIntrejectionmstDao qtmTlIntrejectionmstDao;
	private QtmTlInternalrejectionhourlyDao qtmTlInternalrejectionhourlyDao;	
	private Validations validations ;
	InternalRejectionSerivceApi irServiceApi;

	public IntRejEntryServiceImpl(DBActionTemplate dbActionTemplate)
	{
		intRejEntryDao = new IntRejEntryDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		qtmTlIntrejectionmstDao = new QtmTlIntrejectionmstDaoImpl(dbActionTemplate);
		qtmTlInternalrejectionhourlyDao = new QtmTlInternalrejectionhourlyDaoImpl(dbActionTemplate);
		validations = new Validations();		
 	}
	
	public void IntRejEntryImplJwt(String JwtToken){
    	try{
    		intRejEntryDao.IntRejEntryDaoImplJwt(JwtToken);
    		irServiceApi = new InternalRejectionSerivceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	public List<ComboBox> getProcessCombo(ComboFilter comboFilter ) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();		
		comboFilter.setIdField("QPOM_KEYID");
		comboFilter.setNameField("QPOM_NAME");
		comboFilter.setCodeField("QPOM_CODE");
		comboFilter.setOrderByField("QPOM_CODE");
		
		comboFilter.setTableName(TableNames.TBL_QTM_TL_PROCESSMST);		
		return commonFilterDao.fillComboValues(comboFilter);		
			
	}
	
	public List<ComboBox> getPhenomena(String processId, ComboFilter comboFilter ) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("PHENID");
		comboFilter.setNameField("PHENOMENA");
		comboFilter.setOrderByField("PHENOMENA");
		
		if (UIUtils.isValidKeyId(processId))
			comboFilter.setCondSql(" AND PROCESSID ='" + processId + "' ");
		
		comboFilter.setTableName(TableNames.TBL_QTM_VW_PHENPROCESS);		
		return commonFilterDao.fillComboValues(comboFilter);			
	}
	
	public List<String[]> getInternalRejectionViewGrid(String condParam,String detailTableName) throws Exception {
		return this.intRejEntryDao.getInternalRejectionViewGrid(condParam,detailTableName);
	}

	public List<String[]> getIntRejHourBreakGrid(String qirmKeyid) throws Exception {
		return this.intRejEntryDao.getIntRejHourBreakGrid(qirmKeyid);
	}
	public List<String[]> getInternalRejectionEntryGrid(String condParam) throws Exception {
		return this.intRejEntryDao.getInternalRejectionEntryGrid(condParam);
	}

	public List<String[]> getInternalRejectionEntryTestGrid(String condParam,CommonFilter commonFilter) throws Exception {
		return this.intRejEntryDao.getInternalRejectionEntryTestGrid(condParam,commonFilter);
	}
	public Workbook intRejExportExcel( String condParam, JSONObject colmodel, String format) throws Exception {
		return this.intRejEntryDao.intRejExportExcel(condParam, colmodel, format);
	}
	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId, String mchId) throws Exception {
		return this.intRejEntryDao.getEntryExistsDates(detailTable, entryDate,cellId, mchId);
	}
	public List<String[]> getScrapBreakup(String rejectionId,String referenceId) throws Exception
	{
		return this.intRejEntryDao.getScrapBreakup(rejectionId,referenceId);
	}
	public List<String[]> getPendingColor(String entryDate,String cellId,String mchId,String detailTable) throws Exception
	{
		return this.intRejEntryDao.getPendingColor(entryDate,cellId,mchId,detailTable);
	}
	public String getIsHourlyEntry() throws Exception {
		return this.intRejEntryDao.getIsHourlyEntry();
	}

	
	public String checkQirm(String QirmPldetailsid,String QirmProductid) throws Exception {
		return this.intRejEntryDao.checkQirm(QirmPldetailsid,QirmProductid);		
	}
	
	
			
	public QtmTlIntrejectionmst create(QtmTlIntrejectionmst newQtmTlIntrejectionmst,
			List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl)throws Exception {
			CommonMessage.debugMsg("creaate service impl");
			String validationsFor = "create";	

			validations.validate(newQtmTlIntrejectionmst,"intRejEntryCreation",validationsFor);
			validations.validate(newQtmTlIntrejectiondtl,"intRejEntryCreation",validationsFor);
			fillValues(newQtmTlIntrejectionmst);			
			detailFillValues(newQtmTlIntrejectiondtl);
			
			
			return intRejEntryDao.create(newQtmTlIntrejectionmst,newQtmTlIntrejectiondtl);
			//return qtmTlIntrejectionmstDao.create(qtmTlIntrejectionmst);
			
			
	} 
	
	public QtmTlIntrejectiondtl create(QtmTlIntrejectionmst qtmTlIntrejectionmst, 
			QtmTlIntrejectiondtl qtmTlIntrejectiondtl, IntRejEntryBean intRejEntryBean) throws Exception, ValidationExceptions {
			CommonMessage.debugMsg("creaate service impl");
			String validationsFor;
			
/*			if (intRejEntryBean.getQirmKeyid().equals(""))	{
				validationsFor = "create";		
			}
			else {
				validationsFor = "create";	
				
			}
*/
			if(UIUtils.isValidKeyId(intRejEntryBean.getQirdKeyid()))			
				validationsFor = "update";
			else
				validationsFor = "create";

			validations.validate(qtmTlIntrejectionmst,"intRejEntryCreation",validationsFor);
			validations.validate(qtmTlIntrejectiondtl,"intRejEntryCreation",validationsFor);
			
			CommonMessage.debugMsg("inside for lpp");
			
			fillValues(qtmTlIntrejectionmst,intRejEntryBean);
			
			//qtmTlIntrejectionmst.setQirmElementid("-");
			String elementId = commonFilterDao.getElementID(qtmTlIntrejectionmst.getQirmMachineid());
			CommonMessage.debugMsg("elementId==="+elementId);
			qtmTlIntrejectionmst.setQirmElementid(elementId);
			
			if ( UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmElementid())) 
				qtmTlIntrejectionmst.setQirmElementid("-");
			qtmTlIntrejectionmst.setQirmFlid("-");
			qtmTlIntrejectionmst.setQirmModifiedon(qtmTlIntrejectionmst.getQirmCreatedon());
			
			validations.validate(qtmTlIntrejectionmst,"intRejEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			CommonMessage.debugMsg("Report Type : "+intRejEntryBean.getReportType());
			String repType = intRejEntryBean.getReportType();
			
			
			//detailFillValues(qtmTlIntrejectiondtl,intRejEntryBean);
			
			int totQty = 0;
			if(qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl()!= null && qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().size()>0) // check for detail table data
			{
				for(int i =0;i<qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().size();i++)
				{	
					boolean validate = true;
					QtmTlIntrejectiondtl intRejection = qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().get(i);
					if(UIUtils.isValidKeyId(intRejection.getQirdQuantity()))
						totQty = totQty + Integer.parseInt(intRejection.getQirdQuantity().trim());
					if(UIUtils.isValidKeyId(repType))
					{
						if(repType.equals("QAH"))
						{
							CommonMessage.debugMsg("QTY : "+intRejection.getQirdQuantity());
							if(UIUtils.isValidKeyId(intRejection.getQirdQuantity()))
							{
								if(Integer.parseInt(intRejection.getQirdQuantity().trim()) < 10)
								{
									validate = false;
								}
							}
							else
								validate = false;
							
						}
					}
					CommonMessage.debugMsg("validate : "+validate);
					if(validate)
						validations.validate(intRejection,"intRejEntryCreation","4MTYPE");//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
						validations.validate(intRejection,"intRejEntryCreation",validationsFor);
					
				}
				
				if(UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmInspectionqty()) && totQty != 0)
				{
					if(totQty > Integer.parseInt(qtmTlIntrejectionmst.getQirmInspectionqty().trim()))
						throw new BusinessApplicationExceptions("ExceedInspectedQty"); 
				}
						
				
			}
			
			CommonMessage.debugMsg("After Filling Values");
			return intRejEntryDao.create(qtmTlIntrejectionmst,qtmTlIntrejectiondtl,intRejEntryBean);
			//return qtmTlIntrejectionmstDao.create(qtmTlIntrejectionmst);
			
			
	}
	
	public QtmTlIntrejectiondtl createYY(QtmTlIntrejectionmst qtmTlIntrejectionmst, 
			QtmTlIntrejectiondtl qtmTlIntrejectiondtl, IntRejEntryBean intRejEntryBean) throws Exception, ValidationExceptions {

		CommonMessage.debugMsg("creaate service impl");
			String validationsFor;
			
			/*if (intRejEntryBean.getQirmKeyid().equals(""))	{
				validationsFor = "create";		
			}
			else {
				validationsFor = "create";	
				
			}*/
			
			if(UIUtils.isValidKeyId(intRejEntryBean.getQirdKeyid()))			
				validationsFor = "update";
			else
				validationsFor = "create";
			CommonMessage.debugMsg("inside for lpp");
			
			validations.validate(qtmTlIntrejectionmst,"intRejEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			//detailFillValues(qtmTlIntrejectiondtl,intRejEntryBean);
			validations.validate(qtmTlIntrejectiondtl,"intRejEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.employee.xml - defined rules for server side validations
			validations.validate(qtmTlIntrejectiondtl,"intRejEntryCreation","4MTYPE");
			
			
			fillValues(qtmTlIntrejectionmst);

			//qtmTlIntrejectionmst.setQirmElementid("-");
			String elementId = commonFilterDao.getElementID(qtmTlIntrejectionmst.getQirmMachineid());
			CommonMessage.debugMsg("elementId==="+elementId);
			qtmTlIntrejectionmst.setQirmElementid(elementId);
			
			if (!UIUtils.isValidKeyId( qtmTlIntrejectionmst.getQirmElementid() )) 
				qtmTlIntrejectionmst.setQirmElementid("{}");

			CommonMessage.debugMsg("qtmTlIntrejectionmst.elementId==="+qtmTlIntrejectionmst.getQirmElementid());
			//qtmTlIntrejectionmst.setQirmFlid("-");
			
			detailFillForSingle(qtmTlIntrejectiondtl);
			
			qtmTlIntrejectionmst.setQirmModifiedon(qtmTlIntrejectionmst.getQirmCreatedon());

			
			CommonMessage.debugMsg("After Filling Values");
			//return intRejEntryDao.createYY(qtmTlIntrejectionmst,qtmTlIntrejectiondtl,intRejEntryBean);
			return irServiceApi.saveInternalRejection(qtmTlIntrejectionmst, qtmTlIntrejectiondtl, intRejEntryBean);
			//return qtmTlIntrejectionmstDao.create(qtmTlIntrejectionmst);
			
			
	} 
	public QtmTlInternalrejectionhourly createHourly(QtmTlInternalrejectionhourly qtmTlInternalrejectionhourly, 
			QtmTlIntrejectionmst qtmTlIntrejectionmst, IntRejEntryBean intRejEntryBean) throws Exception {
		try {
			
			CommonMessage.debugMsg("creaate service impl");
			String qirmKeyid;
			if(!UIUtils.isValidKeyId(intRejEntryBean.getQirmKeyid())) {
				CommonMessage.debugMsg("fill vlaues in hourly for inte mast id:"+qtmTlIntrejectionmst.getQirmPlmasterid());
				fillValues(qtmTlIntrejectionmst,intRejEntryBean);			
			}
			//qtmTlIntrejectionmst.setQirmElementid("-");
			String elementId = commonFilterDao.getElementID(qtmTlIntrejectionmst.getQirmMachineid());
			
			//CommonMessage.debugMsg("elementId==="+elementId);
			qtmTlIntrejectionmst.setQirmElementid(elementId);
			CommonMessage.debugMsg(elementId+"  elementId123...."+qtmTlIntrejectionmst.getQirmElementid());
			
			if (! UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmElementid())) 
				qtmTlIntrejectionmst.setQirmElementid("-");
			
			qtmTlIntrejectionmst.setQirmFlid("-");
			qtmTlIntrejectionmst.setQirmModifiedon(qtmTlIntrejectionmst.getQirmCreatedon());
			
			
			String validationsFor;			
			if(UIUtils.isValidKeyId(intRejEntryBean.getQirdKeyid()))			
				validationsFor = "update";
			else
				validationsFor = "create";
			CommonMessage.debugMsg("inside for lpp");

			validations.validate(qtmTlInternalrejectionhourly,"intRejEntryCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	
			qtmTlInternalrejectionhourly.setQihbQtmKeyid(intRejEntryBean.getQirmKeyid());
			CommonMessage.debugMsg("intRejEntryBean.getQirmKeyid()"+intRejEntryBean.getQirmKeyid());
			fillValuesHourly(qtmTlInternalrejectionhourly,intRejEntryBean);
			String balQty = intRejEntryDao.getBalanceQty(qtmTlIntrejectionmst.getQirmMachineid(), qtmTlIntrejectionmst.getQirmProductiondate(), qtmTlIntrejectionmst.getQirmInspectedshiftid(),qtmTlIntrejectionmst.getQirmSectionid());
			if(!UIUtils.isValidKeyId(balQty))
			{
				balQty = "0";
			}
			CommonMessage.debugMsg("Bal Qty : "+balQty);
			double balance = Double.parseDouble(balQty.trim());
			CommonMessage.debugMsg("Balance: "+balance);
			double accQty = 0;	
			double rejQty = 0;	
			if(UIUtils.isValidKeyId(qtmTlInternalrejectionhourly.getQihbAcceptedqty()))
				accQty =Integer.parseInt(qtmTlInternalrejectionhourly.getQihbAcceptedqty().trim());
			if(UIUtils.isValidKeyId(qtmTlInternalrejectionhourly.getQihbRejectedqty()))
				rejQty =Integer.parseInt(qtmTlInternalrejectionhourly.getQihbRejectedqty().trim());
			double totQty = UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmInspectionqty())?Double.parseDouble(qtmTlIntrejectionmst.getQirmInspectionqty().trim()):0;
			double insQty = UIUtils.isValidKeyId(qtmTlInternalrejectionhourly.getQihbInspectedqty())?Double.parseDouble(qtmTlInternalrejectionhourly.getQihbInspectedqty().trim()):0;
			CommonMessage.debugMsg("accQty: "+accQty);
			CommonMessage.debugMsg("totQty: "+totQty);
			CommonMessage.debugMsg("insQty: "+insQty);		
			
			balance = balance + totQty;
			accQty =  accQty + rejQty;
			accQty = accQty != 0?totQty - accQty:totQty;			
			insQty = !balQty.equals("0")?insQty + accQty:insQty;
			CommonMessage.debugMsg("balance : "+balance);
			CommonMessage.debugMsg(insQty+" : "+balance);
			if(insQty > balance)
			{			
				throw new BusinessApplicationExceptions("ExceedTotalQty"); 
			}
		
			CommonMessage.debugMsg("After Filling Values");
			if(!UIUtils.isValidKeyId(intRejEntryBean.getQirmKeyid())) {
				QtmTlIntrejectionmst newqtmTlIntrejectionmst = qtmTlIntrejectionmstDao.create(qtmTlIntrejectionmst);
				qirmKeyid = newqtmTlIntrejectionmst.getQirmKeyid();
				CommonMessage.debugMsg("qirmKeyid" + qirmKeyid);
				intRejEntryBean.setQirmKeyid(qirmKeyid);
				if(!UIUtils.isValidKeyId(qtmTlInternalrejectionhourly.getQihbQtmKeyid()))
					qtmTlInternalrejectionhourly.setQihbQtmKeyid(qirmKeyid);
			}
			
			if(UIUtils.isValidKeyId(intRejEntryBean.getQihbKeyid())) {
				qtmTlInternalrejectionhourly.setQihbKeyid(intRejEntryBean.getQihbKeyid());
				return qtmTlInternalrejectionhourlyDao.update(qtmTlInternalrejectionhourly);
			}
			else
				return qtmTlInternalrejectionhourlyDao.create(qtmTlInternalrejectionhourly);
			
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}		
	}
	public QtmTlTestscrapbrkup createBreakup(QtmTlTestscrapbrkup newQtmTlTestscrapbrkup, 
			QtmTlTestscrapbrkup oldQtmTlTestscrapbrkup,String insQty,String qaHold) throws Exception {
	
			int breakupQty = 0;
			CommonMessage.debugMsg("createBreakup Service Impl");			
			fillBreakup(newQtmTlTestscrapbrkup);		
			if(newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup()!= null && newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup().size()>0) // check for detail table data
			{	
				for(int i =0;i<newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup().size();i++)
				{
					QtmTlTestscrapbrkup breakup = (QtmTlTestscrapbrkup)newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup().get(i);
					if(UIUtils.isValidKeyId(breakup.getQsbrQuantity()) || UIUtils.isValidKeyId(breakup.getQsbrRemarks()))
					{
						validations.validate(breakup,"intRejEntryCreation","Breakup");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
						breakupQty += Integer.parseInt(breakup.getQsbrQuantity().trim());
					}
				}
			}		
			if(UIUtils.isValidKeyId(insQty))
			{
				int insQnty = Integer.parseInt(insQty.trim());
				int qaHld = 0;
				if(UIUtils.isValidKeyId(qaHold))
					qaHld = Integer.parseInt(qaHold.trim());
				qaHld = breakupQty + qaHld;
				
				if(qaHld > insQnty)
					throw new BusinessApplicationExceptions("ExceedInspectedQty"); 
			}
			CommonMessage.debugMsg("After Filling Values");
			return intRejEntryDao.createBreakup(newQtmTlTestscrapbrkup,oldQtmTlTestscrapbrkup);
			
	} 
	
	private QtmTlTestscrapbrkup fillBreakup(QtmTlTestscrapbrkup newQtmTlTestscrapbrkup) {
		String dateTime = CommonFunctions.dateTimeNow();		
		newQtmTlTestscrapbrkup.setQsbrCreatedon(dateTime);
		newQtmTlTestscrapbrkup.setQsbrActive("Y");
		if(!UIUtils.isValidKeyId(newQtmTlTestscrapbrkup.getQsbrReferencekeyid()))
		newQtmTlTestscrapbrkup.setQsbrReferencekeyid("{}");
		newQtmTlTestscrapbrkup.setQsbrBacklogflag("{}");
		newQtmTlTestscrapbrkup.setQsbrRemarks("{}");
		newQtmTlTestscrapbrkup.setQsbrQuantity("0");
		if(!UIUtils.isValidKeyId(newQtmTlTestscrapbrkup.getQsbrRejectionid()))
			newQtmTlTestscrapbrkup.setQsbrRejectionid("{}");
		return newQtmTlTestscrapbrkup;
	}
	private QtmTlInternalrejectionhourly fillValuesHourly(QtmTlInternalrejectionhourly qtmTlInternalrejectionhourly , IntRejEntryBean intRejEntryBean) {
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		qtmTlInternalrejectionhourly.setQihbInspectedqty(intRejEntryBean.getInspectedQty());
		qtmTlInternalrejectionhourly.setQihbShifthour(intRejEntryBean.getQihbShifthour());
		qtmTlInternalrejectionhourly.setQihbMrbqty(intRejEntryBean.getQihbMrbqty());
		qtmTlInternalrejectionhourly.setQihbTestingqty(intRejEntryBean.getQihbTestingqty());
		qtmTlInternalrejectionhourly.setQihbAcceptedqty(intRejEntryBean.getQihbAcceptedqty());
		qtmTlInternalrejectionhourly.setQihbBalanceqty(intRejEntryBean.getQihbBalanceqty());
		qtmTlInternalrejectionhourly.setQihbRejectedqty(intRejEntryBean.getQihbRejectedqty());		
		qtmTlInternalrejectionhourly.setQihbQahold(intRejEntryBean.getQihbQahold());
		
		
		if (qtmTlInternalrejectionhourly.getQihbShifttiming()==null) 
			qtmTlInternalrejectionhourly.setQihbShifttiming("{}");
		
		if (qtmTlInternalrejectionhourly.getQihbInspectedqty()==null || qtmTlInternalrejectionhourly.getQihbInspectedqty()=="" ) 
			qtmTlInternalrejectionhourly.setQihbInspectedqty("0");
		if (qtmTlInternalrejectionhourly.getQihbAcceptedqty()==null || qtmTlInternalrejectionhourly.getQihbAcceptedqty()=="") 
			qtmTlInternalrejectionhourly.setQihbAcceptedqty("0");
		if (qtmTlInternalrejectionhourly.getQihbBalanceqty()==null || qtmTlInternalrejectionhourly.getQihbBalanceqty()=="") 
			qtmTlInternalrejectionhourly.setQihbBalanceqty("0");
		if (qtmTlInternalrejectionhourly.getQihbTestingqty()==null || qtmTlInternalrejectionhourly.getQihbTestingqty()=="") 
			qtmTlInternalrejectionhourly.setQihbTestingqty("0");
		if (qtmTlInternalrejectionhourly.getQihbRejectedqty()==null || qtmTlInternalrejectionhourly.getQihbRejectedqty()=="") 
			qtmTlInternalrejectionhourly.setQihbRejectedqty("0");
				
		if (qtmTlInternalrejectionhourly.getQihbMrbqty()==null || qtmTlInternalrejectionhourly.getQihbMrbqty()=="") 
			qtmTlInternalrejectionhourly.setQihbMrbqty("0");
		if (qtmTlInternalrejectionhourly.getQihbQahold()==null || qtmTlInternalrejectionhourly.getQihbQahold()=="") 
			qtmTlInternalrejectionhourly.setQihbQahold("0");
		
		qtmTlInternalrejectionhourly.setQihbDisplayorder("1");
		
		qtmTlInternalrejectionhourly.setQihbReserve1("{}");
		qtmTlInternalrejectionhourly.setQihbReserve2("{}");
		qtmTlInternalrejectionhourly.setQihbReserve3("{}");
		qtmTlInternalrejectionhourly.setQihbReserve4("{}");
		qtmTlInternalrejectionhourly.setQihbReserve5("{}");

		
		qtmTlInternalrejectionhourly.setQihbActive("Y");
		
		qtmTlInternalrejectionhourly.setQihbTimestamp(dateTime);
		qtmTlInternalrejectionhourly.setQihbModtimestamp(dateTime);

		return qtmTlInternalrejectionhourly;
	}
	
	private QtmTlIntrejectionmst fillValues(QtmTlIntrejectionmst qtmTlIntrejectionmst,IntRejEntryBean intRejEntryBean) throws Exception {
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		qtmTlIntrejectionmst.setQirmProdgroupid("{}");
		if (qtmTlIntrejectionmst.getQirmCavity()==null) 
			qtmTlIntrejectionmst.setQirmCavity("0");		
		if (qtmTlIntrejectionmst.getQirmTestingscrap()==null) 
			qtmTlIntrejectionmst.setQirmTestingscrap("0");		
		if (qtmTlIntrejectionmst.getQirmBatchno()==null) 
			qtmTlIntrejectionmst.setQirmBatchno("{}");		
		if (qtmTlIntrejectionmst.getQirmBacklogqty()==null) 
			qtmTlIntrejectionmst.setQirmBacklogqty("0");

		if (qtmTlIntrejectionmst.getQirmQaholdqty()==null) 
			qtmTlIntrejectionmst.setQirmQaholdqty("0");
		if (qtmTlIntrejectionmst.getQirmQaholdpercentage()==null) 
			qtmTlIntrejectionmst.setQirmQaholdpercentage("0");
		
		if (qtmTlIntrejectionmst.getQirmShiftdate()==null) 
			qtmTlIntrejectionmst.setQirmShiftdate(dateTime);
		if (qtmTlIntrejectionmst.getQirmShiftid()==null) 
			qtmTlIntrejectionmst.setQirmShiftid("{}");
		
		if (qtmTlIntrejectionmst.getQirmQualityentryby()==null) 
			qtmTlIntrejectionmst.setQirmQualityentryby(qtmTlIntrejectionmst.getQirmCreatedby());
		if (qtmTlIntrejectionmst.getQirmQualityentrydate()==null) 
			qtmTlIntrejectionmst.setQirmQualityentrydate(dateTime);


		if (qtmTlIntrejectionmst.getQirmApprovalby()==null) 
			qtmTlIntrejectionmst.setQirmApprovalby("0");
		if (qtmTlIntrejectionmst.getQirmApprovaldate()==null) 
			qtmTlIntrejectionmst.setQirmApprovaldate(Constants.futureNullDate);
		if (qtmTlIntrejectionmst.getQirmStatus()==null) 
			qtmTlIntrejectionmst.setQirmStatus("C");

		if (qtmTlIntrejectionmst.getQirmRemarks()==null) 
			qtmTlIntrejectionmst.setQirmRemarks("{}");

		if (qtmTlIntrejectionmst.getQirmBacklogflag()==null) 
			qtmTlIntrejectionmst.setQirmBacklogflag("N");
		if (qtmTlIntrejectionmst.getQirmRemarks()==null) 
			qtmTlIntrejectionmst.setQirmRemarks("C");
		if (qtmTlIntrejectionmst.getQirmMrbqty()==null) 
			qtmTlIntrejectionmst.setQirmMrbqty("0");
		if (qtmTlIntrejectionmst.getQirmBalanceqty()==null) 
			qtmTlIntrejectionmst.setQirmBalanceqty("0");
		
		if (qtmTlIntrejectionmst.getQirmStatus()==null) 
			qtmTlIntrejectionmst.setQirmStatus("C");
		
		if (qtmTlIntrejectionmst.getQirmVirtualproduced()==null) 
			qtmTlIntrejectionmst.setQirmVirtualproduced("0");
		
		if (qtmTlIntrejectionmst.getQirmActualproduced()==null) 
			qtmTlIntrejectionmst.setQirmActualproduced(qtmTlIntrejectionmst.getQirmTotalproduction());

		
		/*if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmParentmasterid())) 
			qtmTlIntrejectionmst.setQirmParentmasterid("{}");
		
		if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmLinkmasterid())) 
			qtmTlIntrejectionmst.setQirmLinkmasterid("{}");*/
		
		if (qtmTlIntrejectionmst.getQirmReferencekeyid()==null) 
			qtmTlIntrejectionmst.setQirmReferencekeyid("{}");
		
		if (qtmTlIntrejectionmst.getQirmTempfield1()==null) 
			qtmTlIntrejectionmst.setQirmTempfield1("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield2()==null) 
			qtmTlIntrejectionmst.setQirmTempfield2("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield3()==null) 
			qtmTlIntrejectionmst.setQirmTempfield3("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield4()==null) 
			qtmTlIntrejectionmst.setQirmTempfield4("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield5()==null) 
			qtmTlIntrejectionmst.setQirmTempfield5("{}");
		
		String elementId = commonFilterDao.getElementID(qtmTlIntrejectionmst.getQirmMachineid());
		CommonMessage.debugMsg("elementId==="+elementId);
		qtmTlIntrejectionmst.setQirmElementid(elementId);
		
		if (qtmTlIntrejectionmst.getQirmElementid()==null || qtmTlIntrejectionmst.getQirmElementid()=="") 
			qtmTlIntrejectionmst.setQirmElementid("{}");
		if (qtmTlIntrejectionmst.getQirmFlid()==null || qtmTlIntrejectionmst.getQirmFlid()=="") 
			qtmTlIntrejectionmst.setQirmFlid("{}");
		qtmTlIntrejectionmst.setQirmActive("Y");

		qtmTlIntrejectionmst.setQirmCreatedon(dateTime);
		qtmTlIntrejectionmst.setQirmModifiedon(dateTime);
		
		return qtmTlIntrejectionmst;
	}
	
private QtmTlIntrejectionmst fillValues(QtmTlIntrejectionmst qtmTlIntrejectionmst ) throws Exception {
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		qtmTlIntrejectionmst.setQirmProdgroupid("{}");
		String insDate = qtmTlIntrejectionmst.getQirmInspectiondate();
		String shiftDate = qtmTlIntrejectionmst.getQirmShiftdate();
		
		qtmTlIntrejectionmst.setQirmInspectiondate(CommonFunctions.pg_getDateTimeFromDate(insDate));
		qtmTlIntrejectionmst.setQirmShiftdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(shiftDate));
		
		if (qtmTlIntrejectionmst.getQirmCavity()==null) 
			qtmTlIntrejectionmst.setQirmCavity("0");	
		
		if (qtmTlIntrejectionmst.getQirmFactoryid()==null) 
			qtmTlIntrejectionmst.setQirmFactoryid("{}");
		
		if (qtmTlIntrejectionmst.getQirmCellid()==null) 
			qtmTlIntrejectionmst.setQirmCellid("{}");
		
		if (qtmTlIntrejectionmst.getQirmMachineid()==null) 
			qtmTlIntrejectionmst.setQirmMachineid("{}");
		
		if (qtmTlIntrejectionmst.getQirmPlmasterid()==null) 
			qtmTlIntrejectionmst.setQirmPlmasterid("{}");
		
		if (qtmTlIntrejectionmst.getQirmPldetailid()==null) 
			qtmTlIntrejectionmst.setQirmPldetailid("{}");
		
		if (qtmTlIntrejectionmst.getQirmParentmasterid()==null) 
			qtmTlIntrejectionmst.setQirmParentmasterid("{}");
		
		if (qtmTlIntrejectionmst.getQirmActualproduced()==null) 
			qtmTlIntrejectionmst.setQirmActualproduced("0");
		
		
		if (qtmTlIntrejectionmst.getQirmLinkmasterid()==null) 
			qtmTlIntrejectionmst.setQirmLinkmasterid("{}");
		
		if (qtmTlIntrejectionmst.getQirmRemarks()==null) 
			qtmTlIntrejectionmst.setQirmRemarks("{}");
		
		if (qtmTlIntrejectionmst.getQirmMrbqty()==null) 
			qtmTlIntrejectionmst.setQirmMrbqty("0");
		
	    
		if (qtmTlIntrejectionmst.getQirmTestingscrap()==null) 
			qtmTlIntrejectionmst.setQirmTestingscrap("0");	
		
		if (qtmTlIntrejectionmst.getQirmProductid()==null) 
			qtmTlIntrejectionmst.setQirmProductid("{}");	
		
		if (qtmTlIntrejectionmst.getQirmInspectionid()==null) 
			qtmTlIntrejectionmst.setQirmInspectionid(qtmTlIntrejectionmst.getQirmCreatedby());	
		
		if (qtmTlIntrejectionmst.getQirmTotalproduction()==null) 
			qtmTlIntrejectionmst.setQirmTotalproduction("0");	
		
		if (qtmTlIntrejectionmst.getQirmInspectionqty()==null) 
			qtmTlIntrejectionmst.setQirmInspectionqty("0");	
		
	    
		if (qtmTlIntrejectionmst.getQirmAcceptedqty()==null) 
			qtmTlIntrejectionmst.setQirmAcceptedqty("0");	
		
		if (qtmTlIntrejectionmst.getQirmEntryby()==null) 
			qtmTlIntrejectionmst.setQirmEntryby("{}");	
		
		
		if (qtmTlIntrejectionmst.getQirmInspectedshiftid()==null) 
			qtmTlIntrejectionmst.setQirmInspectedshiftid("{}");	
		
			
		
		if (qtmTlIntrejectionmst.getQirmBatchno()==null) 
			qtmTlIntrejectionmst.setQirmBatchno("{}");		
		if (qtmTlIntrejectionmst.getQirmBacklogqty()==null) 
			qtmTlIntrejectionmst.setQirmBacklogqty("0");

		if (qtmTlIntrejectionmst.getQirmQaholdqty()==null) 
			qtmTlIntrejectionmst.setQirmQaholdqty("0");
		if (qtmTlIntrejectionmst.getQirmQaholdpercentage()==null) 
			qtmTlIntrejectionmst.setQirmQaholdpercentage("0");
		
		if (qtmTlIntrejectionmst.getQirmShiftdate()==null) 
			qtmTlIntrejectionmst.setQirmShiftdate(dateTime);
		if (qtmTlIntrejectionmst.getQirmShiftid()==null) 
			qtmTlIntrejectionmst.setQirmShiftid("{}");
		
		if (qtmTlIntrejectionmst.getQirmQualityentryby()==null) 
			qtmTlIntrejectionmst.setQirmQualityentryby(qtmTlIntrejectionmst.getQirmCreatedby());
		if (qtmTlIntrejectionmst.getQirmQualityentrydate()==null) 
			qtmTlIntrejectionmst.setQirmQualityentrydate(dateTime);


		if (qtmTlIntrejectionmst.getQirmApprovalby()==null) 
			qtmTlIntrejectionmst.setQirmApprovalby("0");
		if (qtmTlIntrejectionmst.getQirmApprovaldate()==null) 
			qtmTlIntrejectionmst.setQirmApprovaldate(Constants.pgFutureNullDateTime);
		if (qtmTlIntrejectionmst.getQirmStatus()==null) 
			qtmTlIntrejectionmst.setQirmStatus("C");

		if (qtmTlIntrejectionmst.getQirmRemarks()==null) 
			qtmTlIntrejectionmst.setQirmRemarks("{}");

		if (qtmTlIntrejectionmst.getQirmBacklogflag()==null) 
			qtmTlIntrejectionmst.setQirmBacklogflag("N");
		if (qtmTlIntrejectionmst.getQirmRemarks()==null) 
			qtmTlIntrejectionmst.setQirmRemarks("C");
		if (qtmTlIntrejectionmst.getQirmMrbqty()==null) 
			qtmTlIntrejectionmst.setQirmMrbqty("0");
		if (qtmTlIntrejectionmst.getQirmBalanceqty()==null) 
			qtmTlIntrejectionmst.setQirmBalanceqty("0");
		
		if (qtmTlIntrejectionmst.getQirmStatus()==null) 
			qtmTlIntrejectionmst.setQirmStatus("C");
		
		if (qtmTlIntrejectionmst.getQirmVirtualproduced()==null) 
			qtmTlIntrejectionmst.setQirmVirtualproduced("0");
		
		if (qtmTlIntrejectionmst.getQirmActualproduced()==null) 
			qtmTlIntrejectionmst.setQirmActualproduced(qtmTlIntrejectionmst.getQirmTotalproduction());

		
		/*if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmParentmasterid())) 
			qtmTlIntrejectionmst.setQirmParentmasterid("{}");
		
		if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmLinkmasterid())) 
			qtmTlIntrejectionmst.setQirmLinkmasterid("{}");*/
		
		if (qtmTlIntrejectionmst.getQirmReferencekeyid()==null) 
			qtmTlIntrejectionmst.setQirmReferencekeyid("{}");
		
		if (qtmTlIntrejectionmst.getQirmTempfield1()==null) 
			qtmTlIntrejectionmst.setQirmTempfield1("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield2()==null) 
			qtmTlIntrejectionmst.setQirmTempfield2("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield3()==null) 
			qtmTlIntrejectionmst.setQirmTempfield3("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield4()==null) 
			qtmTlIntrejectionmst.setQirmTempfield4("{}");
		if (qtmTlIntrejectionmst.getQirmTempfield5()==null) 
			qtmTlIntrejectionmst.setQirmTempfield5("{}");
		
		if (qtmTlIntrejectionmst.getQirmProductiondate()==null) 
			qtmTlIntrejectionmst.setQirmProductiondate(Constants.pgFutureNullDateTime);
		
		
		
		String elementId = commonFilterDao.getElementID(qtmTlIntrejectionmst.getQirmMachineid());
		CommonMessage.debugMsg("elementId==="+elementId);
		qtmTlIntrejectionmst.setQirmElementid(elementId);
		
		if (qtmTlIntrejectionmst.getQirmElementid()==null || qtmTlIntrejectionmst.getQirmElementid()=="") 
			qtmTlIntrejectionmst.setQirmElementid("{}");
		if (qtmTlIntrejectionmst.getQirmFlid()==null || qtmTlIntrejectionmst.getQirmFlid()=="") 
			qtmTlIntrejectionmst.setQirmFlid("{}");
		qtmTlIntrejectionmst.setQirmActive("Y");

		qtmTlIntrejectionmst.setQirmCreatedon(dateTime);
		qtmTlIntrejectionmst.setQirmModifiedon(dateTime);
		
		return qtmTlIntrejectionmst;
	}

	private QtmTlIntrejectiondtl detailFillForSingle(QtmTlIntrejectiondtl newQtmTlIntrejectiondtl) { 

		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		if (newQtmTlIntrejectiondtl.getQirdRemarks()==null || newQtmTlIntrejectiondtl.getQirdRemarks()=="")
			newQtmTlIntrejectiondtl.setQirdRemarks("{}");
		else			
			newQtmTlIntrejectiondtl.setQirdRemarks(newQtmTlIntrejectiondtl.getQirdRemarks());
		
		
		if (newQtmTlIntrejectiondtl.getQirdCauseid()==null)
			newQtmTlIntrejectiondtl.setQirdCauseid("-");

		if (newQtmTlIntrejectiondtl.getQirdRemarks()==null)
			newQtmTlIntrejectiondtl.setQirdRemarks("-");

		
		if (newQtmTlIntrejectiondtl.getQirdProdfamilyid()==null)
			newQtmTlIntrejectiondtl.setQirdProdfamilyid("{}");
		if (newQtmTlIntrejectiondtl.getQirdBacklogflag()==null)
			newQtmTlIntrejectiondtl.setQirdBacklogflag("N");
		if (newQtmTlIntrejectiondtl.getQirdRemarks()==null)
			newQtmTlIntrejectiondtl.setQirdRemarks("{}");

		if (newQtmTlIntrejectiondtl.getQirdWwmasterid()==null)
			newQtmTlIntrejectiondtl.setQirdWwmasterid("{}");
		
		//newQtmTlIntrejectiondtl.setQirdEntrytype(intRejEntryBean.getReportType());
		
		if (newQtmTlIntrejectiondtl.getQirdEntrytype()==null)
			newQtmTlIntrejectiondtl.setQirdEntrytype("DIR");
		
		if (newQtmTlIntrejectiondtl.getQirdReferenceid()==null)
			newQtmTlIntrejectiondtl.setQirdReferenceid("DIR");
		
		if (newQtmTlIntrejectiondtl.getPlrkKeyid()==null)
			newQtmTlIntrejectiondtl.setPlrkKeyid("{}");
		
		//qtmTlIntrejectiondtl.setQirdQhbKeyid(intRejEntryBean.getQihbKeyid());
		
		if (newQtmTlIntrejectiondtl.getQirdQhbKeyid()==null)
			newQtmTlIntrejectiondtl.setQirdQhbKeyid("{}");

		//if (newQtmTlIntrejectiondtl.getPlrkKeyid()==null) 
		//	newQtmTlIntrejectiondtl.setPlrkKeyid(PlrkKeyid());
		if (newQtmTlIntrejectiondtl.getQirdSubprocessid()==null) 
			newQtmTlIntrejectiondtl.setQirdSubprocessid("{}");
		if (newQtmTlIntrejectiondtl.getQirdQty()==null) 
			newQtmTlIntrejectiondtl.setQirdQty("0");
		if (newQtmTlIntrejectiondtl.getQirdTempfield4()==null) 
			newQtmTlIntrejectiondtl.setQirdTempfield4("{}");
		if (newQtmTlIntrejectiondtl.getQirdTempfield5()==null) 
			newQtmTlIntrejectiondtl.setQirdTempfield5("{}");	
		
		newQtmTlIntrejectiondtl.setQirdActive("Y");

		newQtmTlIntrejectiondtl.setQirdCreatedon(dateTime);
		newQtmTlIntrejectiondtl.setQirdModifiedon(dateTime);
		
		return newQtmTlIntrejectiondtl;
	}


	private List<QtmTlIntrejectiondtl> detailFillValues(List<QtmTlIntrejectiondtl> qtmTlIntrejectiondtl) {
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		
		//CommonMessage.debugMsg("intRejEntryBean.getPlrkKeyid()"+intRejEntryBean.getPlrkKeyid());
		
		//qtmTlIntrejectiondtl.setQird4mtype(intRejEntryBean.getQird4mType());
		//qtmTlIntrejectiondtl.setQirdType(intRejEntryBean.getQirdType());
		for(QtmTlIntrejectiondtl arrQtmTlIntrejectiondtl : qtmTlIntrejectiondtl )
		{
		if (arrQtmTlIntrejectiondtl.getQirdRemarks()==null || arrQtmTlIntrejectiondtl.getQirdRemarks()=="")
			arrQtmTlIntrejectiondtl.setQirdRemarks("{}");
		else			
			arrQtmTlIntrejectiondtl.setQirdRemarks(arrQtmTlIntrejectiondtl.getQirdRemarks());
		
		
		if (arrQtmTlIntrejectiondtl.getQirdCauseid()==null)
			arrQtmTlIntrejectiondtl.setQirdCauseid("-");

		if (arrQtmTlIntrejectiondtl.getQirdRemarks()==null)
			arrQtmTlIntrejectiondtl.setQirdRemarks("-");

		
		if (arrQtmTlIntrejectiondtl.getQirdProdfamilyid()==null)
			arrQtmTlIntrejectiondtl.setQirdProdfamilyid("{}");
		if (arrQtmTlIntrejectiondtl.getQirdBacklogflag()==null)
			arrQtmTlIntrejectiondtl.setQirdBacklogflag("N");
		if (arrQtmTlIntrejectiondtl.getQirdRemarks()==null)
			arrQtmTlIntrejectiondtl.setQirdRemarks("{}");

		if (arrQtmTlIntrejectiondtl.getQirdWwmasterid()==null)
			arrQtmTlIntrejectiondtl.setQirdWwmasterid("{}");
		
		//arrQtmTlIntrejectiondtl.setQirdEntrytype(intRejEntryBean.getReportType());
		
		if (arrQtmTlIntrejectiondtl.getQirdEntrytype()==null)
			arrQtmTlIntrejectiondtl.setQirdEntrytype("D");
		
		if (arrQtmTlIntrejectiondtl.getQirdReferenceid()==null)
			arrQtmTlIntrejectiondtl.setQirdReferenceid("DIR");
		
		if (arrQtmTlIntrejectiondtl.getPlrkKeyid()==null)
			arrQtmTlIntrejectiondtl.setPlrkKeyid("{}");
		
		
		
		
		//qtmTlIntrejectiondtl.setQirdQhbKeyid(intRejEntryBean.getQihbKeyid());
		
		if (arrQtmTlIntrejectiondtl.getQirdQhbKeyid()==null)
			arrQtmTlIntrejectiondtl.setQirdQhbKeyid("{}");

		//if (arrQtmTlIntrejectiondtl.getPlrkKeyid()==null) 
		//	arrQtmTlIntrejectiondtl.setPlrkKeyid(PlrkKeyid());
		if (arrQtmTlIntrejectiondtl.getQirdSubprocessid()==null) 
			arrQtmTlIntrejectiondtl.setQirdSubprocessid("{}");
		if (arrQtmTlIntrejectiondtl.getQirdQty()==null) 
			arrQtmTlIntrejectiondtl.setQirdQty("0");
		if (arrQtmTlIntrejectiondtl.getQirdTempfield4()==null) 
			arrQtmTlIntrejectiondtl.setQirdTempfield4("{}");
		if (arrQtmTlIntrejectiondtl.getQirdTempfield5()==null) 
			arrQtmTlIntrejectiondtl.setQirdTempfield5("{}");	
		
		arrQtmTlIntrejectiondtl.setQirdActive("Y");

		arrQtmTlIntrejectiondtl.setQirdCreatedon(dateTime);
		arrQtmTlIntrejectiondtl.setQirdModifiedon(dateTime);
		}
		
		return qtmTlIntrejectiondtl;
	}

	@Override
	public QtmTlIntrejectionmst update(
			QtmTlIntrejectionmst newQtmTlIntrejectionmst,
			List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getInternalRejectionMstGrid(CommonFilter commonFilter)  throws Exception {
		
		String flid = commonFilter.getFlid();
		return irServiceApi.getInternalRejectionMstGrid(flid);
		//return this.intRejEntryDao.getInternalRejectionMstGrid(commonFilter);
	}

	@Override
	public QtmTlIntrejectionmst getInternalRejectionMstdata(String keyid) throws Exception {
		//return this.intRejEntryDao.getInternalRejectionMstdata( keyid);
		return irServiceApi.getInternalRejectionMasterData(keyid);
	}
	public List<ComboBox> getproductCombo(ComboFilter comboFilter , String factId, String mchId,  String prdModelId, String entryDate,String rawMaterial) throws Exception
	{
		//ComboFilter comboFilter = new ComboFilter();
		
		//String isHourly = pcsEntryDao.getIsHourlyEntry();	
					
	    comboFilter.setCodeField("PRDM_CODE");		
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setNameField("PRDM_NAME");
		
		String condSql ="";		
		if (UIUtils.isValidKeyId(prdModelId))  
			condSql += " AND PRDM_MODEL ='"+prdModelId+"'";
		
		if (UIUtils.isValidKeyId(mchId)) { 
			condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM PCS_TL_CYCLETIMEMST " +
					" WHERE CYTM_MACHINEID='" + mchId + "'  " ;
			//condSql += " AND CYTM_ACTIVE='Y' "; 
		}
		//if (UIUtils.isValidKeyId(factId) )  
		//		condSql += " AND CYTM_FACTORYID ='"+factId+"'  ";

		if (UIUtils.isValidKeyId(entryDate) )  
			condSql += " AND '"+ entryDate + "' BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE  ";

		if( UIUtils.isValidKeyId(factId) || UIUtils.isValidKeyId(mchId) )
				condSql += " ) ";

		if (UIUtils.isValidKeyId(rawMaterial) )  
			condSql += " AND PRDM_RAWMATTYPE='"+ rawMaterial + "'";
		//condSql += " AND PRDM_ACTIVE = 'Y' ";
		
		comboFilter.setCondSql(condSql);
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);	
		return commonFilterDao.fillComboValues(comboFilter);		
	}

	@Override
	public Workbook getMstGridExcelData(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		
		return intRejEntryDao.getMstGridExcelData(commonFilter, colModel, rptFormat);
	}

	
}
