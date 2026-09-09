package com.akranta.tpm.service.impl;

import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.RiskBean;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.SheTlRiskassessmentmstDao;
import com.akranta.tpm.dao.SheTlRiskassessmentdtlDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.SheTlRiskassessmentmstDaoImpl;
import com.akranta.tpm.dao.impl.SheTlRiskassessmentdtlDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFnlnroleteam;
import com.akranta.tpm.model.SheTlDeriskdtl;
import com.akranta.tpm.model.SheTlDeriskmst;
import com.akranta.tpm.model.SheTlRiskassessmentmst;
import com.akranta.tpm.model.SheTlRiskassessmentdtl;
import com.akranta.tpm.service.RiskAssesmtService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class RiskAssesmtServiceImpl implements RiskAssesmtService{
	private SheTlRiskassessmentmstDao sheTlRiskassessmentmstDao;
	private SheTlRiskassessmentdtlDao sheTlRiskassessmentdtlDao;
	private Validations validations;
	private CommonFilterDao commonFilterDao;
	public RiskAssesmtServiceImpl(DBActionTemplate dbActionTemplate)
	{
		sheTlRiskassessmentmstDao =  new SheTlRiskassessmentmstDaoImpl(dbActionTemplate);
		sheTlRiskassessmentdtlDao =  new SheTlRiskassessmentdtlDaoImpl(dbActionTemplate);
		commonFilterDao =  new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}

	public SheTlRiskassessmentmst create(SheTlRiskassessmentmst newSheTlRiskassessmentmst,SheTlRiskassessmentmst existSheTlRiskassessmentmst)
	throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		CommonFunctions.debugMsg("create");
			validations.validate(  newSheTlRiskassessmentmst,"RiskAssessment","create");		
			newSheTlRiskassessmentmst=fillValues( newSheTlRiskassessmentmst,existSheTlRiskassessmentmst);
			return  sheTlRiskassessmentmstDao.create( newSheTlRiskassessmentmst) ;
	}
	
	@Override
	public SheTlRiskassessmentmst update(
			SheTlRiskassessmentmst newSheTlRiskassessmentmst,
			SheTlRiskassessmentmst existSheTlRiskassessmentmst)
		throws ValidationExceptions,BusinessApplicationExceptions,ValidationExceptions,BusinessApplicationExceptions, Exception {
		CommonFunctions.debugMsg("update");
			validations.validate(  newSheTlRiskassessmentmst,"RiskAssessment","update");		
			newSheTlRiskassessmentmst=fillValues( newSheTlRiskassessmentmst,existSheTlRiskassessmentmst);
			return  sheTlRiskassessmentmstDao.update( newSheTlRiskassessmentmst) ;
	}	
	
	@Override
	public SheTlRiskassessmentmst delete(SheTlRiskassessmentmst newSheTlRiskassessmentmst)
		throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		try {
			return   sheTlRiskassessmentmstDao.delete(newSheTlRiskassessmentmst);	
		}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
		}	
	}
	
	@Override
	public SheTlDeriskmst create(SheTlDeriskmst newSheTlDeriskmst,
			SheTlDeriskmst existSheTlDeriskmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		CommonFunctions.debugMsg("create");
		try {
			validations.validate(  newSheTlDeriskmst,"DeRiskAssessment","create");		
			newSheTlDeriskmst=fillValues( newSheTlDeriskmst,existSheTlDeriskmst);
			return  sheTlRiskassessmentmstDao.create( newSheTlDeriskmst) ;
			
			
		}catch (ValidationExceptions e){
			CommonFunctions.debugMsg("e.getMessage()"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}

	@Override
	public SheTlDeriskmst update(SheTlDeriskmst newSheTlDeriskmst,
			SheTlDeriskmst existSheTlDeriskmst) throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		CommonFunctions.debugMsg("update");
		try {
			validations.validate(  newSheTlDeriskmst,"DeRiskAssessment","update");		
			newSheTlDeriskmst=fillValues( newSheTlDeriskmst,existSheTlDeriskmst);
			return  sheTlRiskassessmentmstDao.update( newSheTlDeriskmst) ;
			
			
		}catch (ValidationExceptions e){
			CommonFunctions.debugMsg("e.getMessage()"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
	}

	@Override
	public SheTlDeriskmst delete(SheTlDeriskmst newSheTlDeriskmst)
		throws ValidationExceptions,BusinessApplicationExceptions,Exception {
		// TODO Auto-generated method stub
		return   sheTlRiskassessmentmstDao.delete(newSheTlDeriskmst);		
	}
	@Override
	public SheTlRiskassessmentmst select(String keyid) throws Exception {	
		return  sheTlRiskassessmentmstDao.select(keyid);
	}
	
	@Override
	public Workbook RiskExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		return sheTlRiskassessmentmstDao.getRiskExcel( commonFilter, tblJSONObj, format);
	}

	@Override
	public List<String[]> getRiskList(CommonFilter commonFilter)
			throws Exception {
		return sheTlRiskassessmentmstDao.getRiskList(commonFilter);		
	}

	@Override
	public List<String[]> getRiskDtlsList(CommonFilter commonFilter) throws Exception {
		return sheTlRiskassessmentmstDao.getRiskDtlsList(commonFilter);
	}
		
	private SheTlRiskassessmentmst fillValues(SheTlRiskassessmentmst newSheTlRiskassessmentmst,SheTlRiskassessmentmst existSheTlRiskassessmentmst) {
		
		CommonFunctions.debugMsg("fillValues");
		String dateTime = CommonFunctions.dateTimeNow();		
		newSheTlRiskassessmentmst.setRasmActive("Y");
		newSheTlRiskassessmentmst.setRasmCreatedon(dateTime);
		newSheTlRiskassessmentmst.setRasmModifiedon(dateTime);
		
		if(newSheTlRiskassessmentmst.getRasmKeyid() == null )
		  newSheTlRiskassessmentmst.setRasmKeyid("{}");
		
		if(newSheTlRiskassessmentmst.getRasmFlid() == null )
			newSheTlRiskassessmentmst.setRasmFlid("{}");
		
		if(newSheTlRiskassessmentmst.getRasmDate() == null )
			newSheTlRiskassessmentmst.setRasmDate(dateTime);
		
		if(newSheTlRiskassessmentmst.getRasmPreparedby() == null )
			newSheTlRiskassessmentmst.setRasmPreparedby("{}");
		
		if(newSheTlRiskassessmentmst.getRasmArea() == null )
			newSheTlRiskassessmentmst.setRasmArea("{}");
		
		if(newSheTlRiskassessmentmst.getRasmTitle() == null )
			newSheTlRiskassessmentmst.setRasmTitle("{}");
		
		if(newSheTlRiskassessmentmst.getRasmTempfield1() == null )
			newSheTlRiskassessmentmst.setRasmTempfield1("-");
		
		if(newSheTlRiskassessmentmst.getRasmTempfield2() == null )
			newSheTlRiskassessmentmst.setRasmTempfield2("-");
		
		if(newSheTlRiskassessmentmst.getRasmTempfield3() == null )
			newSheTlRiskassessmentmst.setRasmTempfield3("-");		
		
		if(newSheTlRiskassessmentmst.getRasmTempfield4() == null )
			newSheTlRiskassessmentmst.setRasmTempfield4("-");
		
		if(newSheTlRiskassessmentmst.getRasmTempfield5() == null )
			newSheTlRiskassessmentmst.setRasmTempfield5("-");
		
		newSheTlRiskassessmentmst.setRiskDetails(fillValuesRiskDetails(newSheTlRiskassessmentmst));
		return newSheTlRiskassessmentmst;	
		
	}
	
	private List<SheTlRiskassessmentdtl> fillValuesRiskDetails(SheTlRiskassessmentmst newSheTlRiskassessmentmst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		List<SheTlRiskassessmentdtl> sheTlRiskassessmentdtlList=newSheTlRiskassessmentmst.getRiskDetails();
		//CommonFunctions.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ sheTlRiskassessmentdtlList.size());
		if (newSheTlRiskassessmentmst.getRiskDetails()!=null){
			for(int i=0 ;i<=sheTlRiskassessmentdtlList.size()-1;i++){		
				sheTlRiskassessmentdtlList.get(i).setRasdCreatedon(dateTime);
				sheTlRiskassessmentdtlList.get(i).setRasdCreatedby(newSheTlRiskassessmentmst.getRasmCreatedby());
				sheTlRiskassessmentdtlList.get(i).setRasdModifiedon(dateTime);
				sheTlRiskassessmentdtlList.get(i).setRasdActive("Y");
	
				if (sheTlRiskassessmentdtlList.get(i).getRasdActivity() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdActivity("{}");
	
				if (sheTlRiskassessmentdtlList.get(i).getRasdConsequence() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdConsequence("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdHazard() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdHazard("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdCause() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdCause("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdProbablityid() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdProbablityid("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdSeviorityid() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdSeviorityid("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdRiskval() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdRiskval("0");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdRisklevelid() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdRisklevelid("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdControltypeid() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdControltypeid("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdControls() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdControls("{}");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdActplan() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdActplan("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdTempfield1() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdTempfield1("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdTempfield2() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdTempfield2("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdTempfield2() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdTempfield2("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdTempfield3() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdTempfield3("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdTempfield4() == null)
					sheTlRiskassessmentdtlList.get(i).setRasdTempfield4("-");
				
				if (sheTlRiskassessmentdtlList.get(i).getRasdTempfield5() == null)				
					sheTlRiskassessmentdtlList.get(i).setRasdTempfield5("-");
			}
		}
		CommonFunctions.debugMsg("End Of  fillValues sheTlRiskassessmentdtlList");
		return sheTlRiskassessmentdtlList;
	}
	
	private SheTlDeriskmst fillValues(SheTlDeriskmst newSheTlDeriskmst,
			SheTlDeriskmst existSheTlDeriskmst) {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("fillValues");
		String dateTime = CommonFunctions.dateTimeNow();		
		newSheTlDeriskmst.setDramActive("Y");
		newSheTlDeriskmst.setDramCreatedon(dateTime);
		newSheTlDeriskmst.setDramModifiedon(dateTime);
		
		if(newSheTlDeriskmst.getDramKeyid() == null )
			newSheTlDeriskmst.setDramKeyid("{}");
		
		if(newSheTlDeriskmst.getDramRasmKeyid() == null )
			newSheTlDeriskmst.setDramRasmKeyid("{}");
		
		if(newSheTlDeriskmst.getDramDate() == null )
			newSheTlDeriskmst.setDramDate(dateTime);
		
		if(newSheTlDeriskmst.getDramPreparedby() == null )
			newSheTlDeriskmst.setDramPreparedby("{}");		
		
		if(newSheTlDeriskmst.getDramTempfield1() == null )
			newSheTlDeriskmst.setDramTempfield1("-");
		
		if(newSheTlDeriskmst.getDramTempfield2() == null )
			newSheTlDeriskmst.setDramTempfield2("-");
		
		if(newSheTlDeriskmst.getDramTempfield3() == null )
			newSheTlDeriskmst.setDramTempfield3("-");		
		
		if(newSheTlDeriskmst.getDramTempfield4() == null )
			newSheTlDeriskmst.setDramTempfield4("-");
		
		if(newSheTlDeriskmst.getDramTempfield5() == null )
			newSheTlDeriskmst.setDramTempfield5("-");
		
		newSheTlDeriskmst.setDeRiskDetails(fillValuesDeRiskDetails(newSheTlDeriskmst));
		return newSheTlDeriskmst;	
	}
	
	private List<SheTlDeriskdtl> fillValuesDeRiskDetails(SheTlDeriskmst newSheTlDeriskmst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		List<SheTlDeriskdtl> sheTlDeriskdtlList=newSheTlDeriskmst.getDeRiskDetails();
		//CommonFunctions.debugMsg("start Of  fillValues sheTlDeriskdtlList:"+ sheTlDeriskdtlList.size());
		if (newSheTlDeriskmst.getDeRiskDetails()!=null){
			for(int i=0 ;i<=sheTlDeriskdtlList.size()-1;i++){		
				sheTlDeriskdtlList.get(i).setDradCreatedon(dateTime);
				sheTlDeriskdtlList.get(i).setDradCreatedby(newSheTlDeriskmst.getDramCreatedby());
				sheTlDeriskdtlList.get(i).setDradModifiedon(dateTime);
				sheTlDeriskdtlList.get(i).setDradActive("Y");
	
				if (sheTlDeriskdtlList.get(i).getDradRasdKeyid() == null)
					sheTlDeriskdtlList.get(i).setDradRasdKeyid("{}");
				
				if (sheTlDeriskdtlList.get(i).getDradProbablityid() == null)
					sheTlDeriskdtlList.get(i).setDradProbablityid("{}");
				
				if (sheTlDeriskdtlList.get(i).getDradSeviorityid() == null)
					sheTlDeriskdtlList.get(i).setDradSeviorityid("{}");
				
				if (sheTlDeriskdtlList.get(i).getDradRiskval() == null)
					sheTlDeriskdtlList.get(i).setDradRiskval("0");
				
				if (sheTlDeriskdtlList.get(i).getDradRisklevelid() == null)
					sheTlDeriskdtlList.get(i).setDradRisklevelid("{}");
				
				if (sheTlDeriskdtlList.get(i).getDradPreparedby() == null)
					sheTlDeriskdtlList.get(i).setDradPreparedby("{}");
				
				if (sheTlDeriskdtlList.get(i).getDradDate() == null)
					sheTlDeriskdtlList.get(i).setDradDate(dateTime);
				
				if (sheTlDeriskdtlList.get(i).getDradTempfield1() == null)
					sheTlDeriskdtlList.get(i).setDradTempfield1("-");
				
				if (sheTlDeriskdtlList.get(i).getDradTempfield2() == null)
					sheTlDeriskdtlList.get(i).setDradTempfield2("-");
				
				if (sheTlDeriskdtlList.get(i).getDradTempfield3() == null)
					sheTlDeriskdtlList.get(i).setDradTempfield3("-");
				
				if (sheTlDeriskdtlList.get(i).getDradTempfield4() == null)
					sheTlDeriskdtlList.get(i).setDradTempfield4("-");
				
				if (sheTlDeriskdtlList.get(i).getDradTempfield5() == null)				
					sheTlDeriskdtlList.get(i).setDradTempfield5("-");
			}
		}
		CommonFunctions.debugMsg("End Of  fillValues sheTlDeriskdtlList");
		return sheTlDeriskdtlList;
	}
	@Override
	public List<ComboBox> getProbablityComboList(
			ComboFilter probablityComboFilter) throws Exception {
		// TODO Auto-generated method stub
		probablityComboFilter.setNameField("PRBM_CODE");
		probablityComboFilter.setIdField("PRBM_KEYID");
		probablityComboFilter.setOrderByField("PRBM_KEYID"); // adding Vignesh june 04 for Ordering
		probablityComboFilter.setTableName(TableNames.TBL_SHE_TL_PROBABLITYMST);
		return commonFilterDao.fillComboValues(probablityComboFilter);
	}

	@Override
	public List<ComboBox> getSeviorityComboList(ComboFilter sivFilterComboFilter)throws Exception {
		// TODO Auto-generated method stub
		sivFilterComboFilter.setNameField("SIVM_CODE");
		sivFilterComboFilter.setIdField("SIVM_KEYID");
		sivFilterComboFilter.setOrderByField("SIVM_KEYID"); // adding Vignesh june 04 for Ordering
		sivFilterComboFilter.setTableName(TableNames.TBL_SHE_TL_SEVIORITYMST);
		return commonFilterDao.fillComboValues(sivFilterComboFilter);
	}

	@Override
	public List<ComboBox> getRiskLevelComboList(ComboFilter riskLevelFilterComboFilter) throws Exception {
		// TODO Auto-generated method stub
		riskLevelFilterComboFilter.setNameField("RILM_RISKLEVEL");
		riskLevelFilterComboFilter.setIdField("RILM_KEYID");
		riskLevelFilterComboFilter.setOrderByField("RILM_KEYID"); // adding Vignesh june 04 for Ordering
		riskLevelFilterComboFilter.setTableName(TableNames.TBL_SHE_TL_RISKLEVELMST);
		return commonFilterDao.fillComboValues(riskLevelFilterComboFilter);
	}

	@Override
	public List<ComboBox> getControlTypeComboList(ComboFilter ControlFilterComboFilter) throws Exception {
		// TODO Auto-generated method stub
		ControlFilterComboFilter.setNameField("CLTM_NAME");
		ControlFilterComboFilter.setIdField("CLTM_KEYID");
		ControlFilterComboFilter.setTableName(TableNames.TBL_SHE_TL_CONTROLTYPEMST);
		return commonFilterDao.fillComboValues(ControlFilterComboFilter);
	}

	@Override
	public String getRiskLevel(String riskVal)
			throws Exception {
		// TODO Auto-generated method stub
		return sheTlRiskassessmentmstDao.getRiskLevel(riskVal);
	}

	@Override
	public List<String[]> getDeRiskList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return sheTlRiskassessmentmstDao.getDeRiskList(commonFilter);
	}

	@Override
	public List<String[]> getDeRiskDtlsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return sheTlRiskassessmentmstDao.getDeRiskDtlsList(commonFilter);
	}

	@Override
	public SheTlDeriskmst selectDeRisk(String dramkeyid) throws Exception {
		// TODO Auto-generated method stub
		return  sheTlRiskassessmentmstDao.selectDeRisk(dramkeyid);
	}

	@Override
	public List<String[]> getDeRiskDtlsGridList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return  sheTlRiskassessmentmstDao.getDeRiskDtlsGridList(commonFilter);
	}

	@Override
	public Workbook DeRiskExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return  sheTlRiskassessmentmstDao.DeRiskExportExcel(commonFilter,tblJSONObj,format);
	}

	@Override
	public String getProbablityVal(String prob) throws Exception {
		// TODO Auto-generated method stub
		return  sheTlRiskassessmentmstDao.getProbablityVal(prob);
	}

	@Override
	public String getSeviorityVal(String sev) throws Exception {
		// TODO Auto-generated method stub
		return  sheTlRiskassessmentmstDao.getSeviorityVal(sev);
	}

	

}
