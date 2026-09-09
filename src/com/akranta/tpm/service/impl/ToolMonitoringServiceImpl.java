package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.TlmTlAlertBean;
import com.akranta.tpm.bean.ToolChangeDetailsBean;
//import com.akranta.tpm.bean.ToolMstFormBean;
import com.akranta.tpm.bean.ToolTlMachineLinkBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.ToolMonitoringDao;
import com.akranta.tpm.dao.impl.BdmTlDtlDaoImpl;
import com.akranta.tpm.dao.impl.BdmTlMstDaoImpl;
import com.akranta.tpm.dao.impl.BdmTlWhywhymstDaoImpl;
import com.akranta.tpm.dao.impl.BreakdownDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.ToolMonitoringDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.TlmTLAlertDetails;
import com.akranta.tpm.model.ToolChangeTlDetails;
import com.akranta.tpm.model.ToolTlMachineLink;
//import com.akranta.tpm.model.ToolTlMst;
import com.akranta.tpm.service.ToolMonitoringService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class ToolMonitoringServiceImpl implements ToolMonitoringService{
	private CommonFilterDao commonFilterDao;
	
	ToolMonitoringDao toolmonitoringDao;
	Validations validations;
	String validationsFor="";
	
	public ToolMonitoringServiceImpl(DBActionTemplate dbActionTemplate)
	{
		toolmonitoringDao= new ToolMonitoringDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		
	}
	public List<String []> getStdTime(String toolid,ComboFilter comboFilter) throws Exception{
				
		return toolmonitoringDao.getStdTime(toolid);
	}
	
	public List<String []> getEstSharp(String toolid,ComboFilter comboFilter) throws Exception{			
			
			return toolmonitoringDao.getEstSharp(toolid);
	}
	
	public List<String []> getLastChangeDate(String toolid,String cellId,String machineId) throws Exception{			
		
		return toolmonitoringDao.getLastChangeDate(toolid,cellId,machineId);
}
	
	public List<String []> getProdLastChange(String toolid,String cellId,String machineId) throws Exception{			
		
		return toolmonitoringDao.getProdLastChange(toolid,cellId,machineId);
}
	
public List<String []> getStdLife(String toolid, String mchId) throws Exception{			
		
		return toolmonitoringDao.getStdLife(toolid,mchId);
}
public List<String []> getSharpening(String toolid, String mchId) throws Exception{			
	
	return toolmonitoringDao.getSharpening(toolid,mchId);
}
			//ComboFilter comboFilter = new ComboFilter(); 
		//TLTM_KEYID,TLTM_CODE,TLTM_NAME,
		  /*  comboFilter.setIdField("TLTM_STDCOTIME");
		    comboFilter.setNameField("TLTM_STDCOTIME");	
		    if(UIUtils.isValidKeyId(toolid)){
		    	
		    StringBuilder CondSql=new StringBuilder();
			CondSql.append(" AND TLTM_KEYID='"+toolid+"'") ;
			comboFilter.setCondSql(CondSql.toString())
		    }
			comboFilter.setTableName(TableNames.TBL_TLM_TL_TOOLMST);
			return commonFilterDao.fillComboValues(comboFilter); */ 
		
			public List<ComboBox> getReasons(ComboFilter comboFilter) throws Exception{
				
				//ComboFilter comboFilter = new ComboFilter();
			//TLTM_KEYID,TLTM_CODE,TLTM_NAME,
			    comboFilter.setIdField("TRS_KEYID");
			    comboFilter.setNameField("TRS_DETAIL");
				comboFilter.setCodeField("TRS_CODE");
				comboFilter.setTableName("TLM_TL_REASONSFORCHANGE");
				return commonFilterDao.fillComboValues(comboFilter);			
			}
			
	
	public List<ComboBox> getToolList(String machId,ComboFilter comboFilter) throws Exception{
		CommonFunctions.debugMsg("machId  IN SIDE SERVICE"+machId);
		//ComboFilter comboFilter = new ComboFilter();
	//TLTM_KEYID,TLTM_CODE,TLTM_NAME,
	    comboFilter.setIdField("TLTM_KEYID");
	    comboFilter.setNameField("TLTM_NAME");
		comboFilter.setCodeField("TLTM_CODE");
		comboFilter.setTableName("TLM_TL_TOOLMST,TLM_TL_TOOLEQUIPMENTLINQ");

		 if(UIUtils.isValidKeyId(machId)){			 
		 StringBuilder CondSql=new StringBuilder();
		 CondSql.append(" AND TLTM_KEYID=TLEL_TOOL_KEYID AND TLTM_KEYID IN(SELECT TLEL_TOOL_KEYID FROM TLM_TL_TOOLEQUIPMENTLINQ WHERE TLEL_MACHINEID='"+machId+"')") ;
		 comboFilter.setCondSql(CondSql.toString());
		 }
		return commonFilterDao.fillComboValues(comboFilter);
	
	
}
	@Override
	public List<String[]> getAllDetailsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return this.toolmonitoringDao.getAllDetailsList(commonFilter);

		//return null;
	}
	public List<String[]> getAllSumaryRpt(CommonFilter commonFilter)
	throws Exception {
// TODO Auto-generated method stub
return this.toolmonitoringDao.getAllSumaryRpt(commonFilter);
	}
public List<String[]> getSumary(CommonFilter commonFilter,String type)
throws Exception {
//TODO Auto-generated method stub  
return this.toolmonitoringDao.getSumary(commonFilter,type);

}


public List<String[]> getSumaryDetails(CommonFilter commonFilter)
throws Exception {
//TODO Auto-generated method stub  getSumaryDetails
return this.toolmonitoringDao.getSumaryDetails(commonFilter);

}


public List<String[]> getSumaryGrid(CommonFilter commonFilter,String type,String alertType)
throws Exception {
//TODO Auto-generated method stub
return this.toolmonitoringDao.getSumaryGrid(commonFilter,type,alertType);

}
	@Override
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl,
			ToolChangeTlDetails existToolTlDtl
			) throws Exception {
		CommonFunctions.debugMsg("In side create service");
		String validationsFor;
		validationsFor = "create";
		//validations.validate(newToolTlDtl,"ToolChangeDetails",validationsFor);
		fillValues(newToolTlDtl);
		return toolmonitoringDao.create(newToolTlDtl);
	}
	
	@Override
	public ToolChangeTlDetails update(ToolChangeTlDetails newToolTlDtl,
			ToolChangeTlDetails existToolTlDtl,
			ToolChangeDetailsBean toolDtlBean) throws Exception {
		String validationsFor;
		  
		validationsFor = "update";
//	validations.validate(newToolTlDtl,"ToolChangeDetails",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
	
	fillValues(newToolTlDtl,existToolTlDtl,toolDtlBean);
	
		return toolmonitoringDao.update(newToolTlDtl);
	}
	@Override
	public ToolChangeTlDetails delete(ToolChangeTlDetails newToolTlDtl) throws Exception {
		// TODO Auto-generated method stub
		return toolmonitoringDao.delete(newToolTlDtl);
	}
	private void fillValues(ToolChangeTlDetails newToolTlDtl) {
	    String dateTime = CommonFunctions.dateTimeNow();
			
			CommonFunctions.debugMsg(" in sdie fill values");
			
			if((newToolTlDtl.getActive()==null))
				newToolTlDtl.setActive("Y");
			if((newToolTlDtl.getCreatedOn()==null))
				newToolTlDtl.setCreatedOn(dateTime);
			if((newToolTlDtl.getModifiedOn()==null))
				newToolTlDtl.setModifiedOn(dateTime);
			
			if((newToolTlDtl.getRemark()==null))
				newToolTlDtl.setRemark("-");
			if((newToolTlDtl.getStdLife()==null))
				newToolTlDtl.setStdLife("0");
			if((newToolTlDtl.getExtendedLife()==null))
				newToolTlDtl.setExtendedLife("0");
			if((newToolTlDtl.getLifeEarly()==null))
				newToolTlDtl.setLifeEarly("N");
			if((newToolTlDtl.getLifeExtended()==null))
				newToolTlDtl.setLifeExtended("N");
			if((newToolTlDtl.getTrialTool()==null))
				newToolTlDtl.setTrialTool("N");
			if((newToolTlDtl.getUsedRemark()==null))
				newToolTlDtl.setUsedRemark("-");
			if((newToolTlDtl.getChangeReason()==null))
				newToolTlDtl.setChangeReason("-");
			if((newToolTlDtl.getWhyWhy()==null))
				newToolTlDtl.setWhyWhy("-");
			
			if((newToolTlDtl.getTempField6()==null))
				newToolTlDtl.setTempField6("-");
			if((newToolTlDtl.getTempField8()==null))
				newToolTlDtl.setTempField8("-");
			if((newToolTlDtl.getTempField9()==null))
				newToolTlDtl.setTempField9("-");
			if((newToolTlDtl.getTempField10()==null))
				newToolTlDtl.setTempField10("-");
			if((newToolTlDtl.getStdCoTime()==null))
				newToolTlDtl.setStdCoTime("0");
			if((newToolTlDtl.getActCoTime()==null))
				newToolTlDtl.setActCoTime("0");
			
			
			
		}
	@Override
	public ToolChangeTlDetails getFillValue(String keyId) throws Exception {
		// TODO Auto-generated method stub
		return this.toolmonitoringDao.getFillValue(keyId);
	}
	
	private void fillValues(ToolChangeTlDetails newToolTlDtl,
			ToolChangeTlDetails existToolTlDtl,
			ToolChangeDetailsBean toolDtlBean) {
		newToolTlDtl.setActive("Y");
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(newToolTlDtl.getToolKeyid() == null ){
			newToolTlDtl.setCreatedOn(dateTime);
		}
		else{
			//System.out.println("Testing in ...Service impl"+newToolTlDtl.getGmntCreatedon());
			newToolTlDtl.setCreatedOn(dateTime);
		}
		 if(newToolTlDtl.getToolKeyid() == null ){
			newToolTlDtl.setModifiedOn(dateTime);
		 }else
			newToolTlDtl.setModifiedOn(newToolTlDtl.getCreatedOn());
		
		 	newToolTlDtl.setModifiedOn(dateTime);
		if( ! UIUtils.isValidKeyId(newToolTlDtl.getToolSrNo()) )
			newToolTlDtl.setToolSrNo("{}");
		if( ! UIUtils.isValidKeyId(newToolTlDtl.getFactory()) )
			newToolTlDtl.setFactory("-");
		if( ! UIUtils.isValidKeyId(newToolTlDtl.getRemark()) )
			newToolTlDtl.setRemark("-");
		
		if(! UIUtils.isValidKeyId(newToolTlDtl.getStdLife()))
			newToolTlDtl.setStdLife("0");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getExtendedLife()))
			newToolTlDtl.setExtendedLife("0");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getLifeEarly()))
			newToolTlDtl.setLifeEarly("N");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getLifeExtended()))
			newToolTlDtl.setLifeExtended("N");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getTrialTool()))
			newToolTlDtl.setTrialTool("N");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getUsedRemark()))
			newToolTlDtl.setUsedRemark("-");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getChangeReason()))
			newToolTlDtl.setChangeReason("-");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getWhyWhy()))
			newToolTlDtl.setWhyWhy("-");
		
		if(! UIUtils.isValidKeyId(newToolTlDtl.getTempField6()))
			newToolTlDtl.setTempField6("-");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getFlid()))
			newToolTlDtl.setFlid("-");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getTempField8()))
			newToolTlDtl.setTempField8("-");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getTempField9()))
			newToolTlDtl.setTempField9("-");
		if(! UIUtils.isValidKeyId(newToolTlDtl.getTempField10()))
			newToolTlDtl.setTempField10("-");
		
		if( ! UIUtils.isValidKeyId(newToolTlDtl.getTempField6()) )
			newToolTlDtl.setTempField6("-");
		
		
			
		
		
	}
	@Override
	public ToolChangeTlDetails create(ToolChangeTlDetails newToolTlDtl,
			ToolChangeTlDetails existToolTlDtl, String alertkeyid)
			throws Exception {		
			CommonFunctions.debugMsg("In side create service summary");
			String validationsFor;
			validationsFor = "create";
			//validations.validate(newToolTlDtl,"ToolChangeDetails",validationsFor);
			fillValues(newToolTlDtl);
			return toolmonitoringDao.create(newToolTlDtl,alertkeyid);
		
	}
	@Override
	public String createSerialNo(List<TlmTLAlertDetails> tlmTLAlertDetailsList)
			throws Exception {
		CommonFunctions.debugMsg("Inside the Service method");
		return toolmonitoringDao.updateSrNo(tlmTLAlertDetailsList);
	}
	@Override
	public TlmTLAlertDetails createSerialNo(TlmTLAlertDetails newTlmTlAlertdetails,	TlmTLAlertDetails extlmTlAlertdetails,
			TlmTlAlertBean toolTlmTlAlertBean) throws Exception {
		
		newTlmTlAlertdetails.setToolDetails( fillToolDetailList(newTlmTlAlertdetails.getToolDetails(),toolTlmTlAlertBean.getToolAlert()));

		// TODO Auto-generated method stub
		return toolmonitoringDao.updateSrNo(newTlmTlAlertdetails);
	}
	private List<TlmTLAlertDetails> fillToolDetailList(	List<TlmTLAlertDetails> toolDetails, List<TlmTlAlertBean> toolAlert) {
		//private List<ToolTlMachineLink> fillMachineList(List<ToolTlMachineLink> machineList,List<ToolTlMachineLinkBean> machineLink, String toolKeyid) {
			
			List<TlmTLAlertDetails>  toolTlmTLAlertDetailsList = new ArrayList<TlmTLAlertDetails>();
			String dateTime = CommonFunctions.dateTimeNow();
			//for( SapTlSparesreplaced newSapTlSparesreplaced : sparesSAPData)
			for( int i=0;i<toolAlert.size();i++)
			{	
				TlmTLAlertDetails toolTlAlert = new TlmTLAlertDetails();				
				TlmTlAlertBean toolBean= new TlmTlAlertBean();
				toolBean=toolAlert.get(i);					
				
				
				if(!UIUtils.isValidKeyId(toolTlAlert.getKeyid()))
					toolTlAlert.setKeyid(toolBean.getKeyId());
				
				if(!UIUtils.isValidKeyId(toolTlAlert.getToolSrNo()))
					toolTlAlert.setToolSrNo(toolBean.getToolSrlNo());
				
				if(!UIUtils.isValidKeyId(toolTlAlert.getToolShrpNo())) 
					toolTlAlert.setToolShrpNo(toolBean.getToolSrhpNo().replaceAll("\\s",""));
				CommonFunctions.debugMsg("toolBean.getToolSrlNo()"+toolBean.getToolSrlNo());
								
				toolTlmTLAlertDetailsList.add(toolTlAlert);
			// TODO Auto-generated method stub
				//CommonFunctions.debugMsg("kznTeambean.getDepKeyid()"+kznTlTeammembers.getKztmEmpCode() +"  "+kznTeambean.getEmpCode());
			
		}
			 
			return toolTlmTLAlertDetailsList;
		}
	@Override
	public Workbook getAllSumaryRptExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return  toolmonitoringDao.getAllSumaryRptExportExcel(commonFilter, tableModel,  format);
	}

	
}
