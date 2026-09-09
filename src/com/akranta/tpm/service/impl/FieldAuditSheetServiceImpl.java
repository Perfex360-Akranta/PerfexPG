package com.akranta.tpm.service.impl;

import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.FieldAuditSheetDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.FieldAuditSheetDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FieldAuditSheetdtl;
import com.akranta.tpm.model.FieldAuditSheetmst;
import com.akranta.tpm.service.FieldAuditSheetService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
//import com.akranta.tpm.service.api.WhywhyServiceApi;

import net.sf.json.JSONObject;

public class FieldAuditSheetServiceImpl implements FieldAuditSheetService{
	private Validations validations ;
	DBActionTemplate  dbActionTemplate ;
	FieldAuditSheetDao fieldAuditSheetDao;
	CommonFilterDao commonFilterDao;
	private FieldAuditSheetServiceApi fieldauditsheetApi;
public FieldAuditSheetServiceImpl(DBActionTemplate dbActionTemplate){
		 
		validations = new Validations();
		this.dbActionTemplate  =dbActionTemplate;
		fieldAuditSheetDao=new FieldAuditSheetDaoImpl(dbActionTemplate);
		commonFilterDao=new CommonFilterDaoImpl(dbActionTemplate);
}
public void FieldAuditSheetServiceImplJwt(String JwtToken){
	try{
		fieldAuditSheetDao.FieldAuditSheetDaoImplJwt(JwtToken);
		fieldauditsheetApi = new FieldAuditSheetServiceApi(JwtToken);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
    // TODO Auto-generated constructor stub
}
public List<String[]> FieldAuditSheetDetail(CommonFilter commonFilter) throws Exception{
	return fieldAuditSheetDao.FieldAuditSheetDetail(commonFilter);
}
public FieldAuditSheetmst select(String keyid) throws Exception{
	//return fieldAuditSheetDao.select(keyid);
	return fieldauditsheetApi.getCompleteFieldAuditSheetData(keyid);
}		    

public List<ComboBox> PPEType(ComboFilter comboFilter) throws Exception {
	comboFilter.setNameField("PPEM_NAME");
	comboFilter.setIdField("PPEM_KEYID");
	comboFilter.setTableName("JHA_TL_PPEMST");
	return commonFilterDao.fillComboValues(comboFilter);
}
public FieldAuditSheetmst create(FieldAuditSheetmst newFieldAuditSheetmst,FieldAuditSheetmst existFieldAuditSheetmst) 
		throws ValidationExceptions,BusinessApplicationExceptions, Exception{
	//validations.validate(newFieldAuditSheetmst,"FieldAuditSheet","create");
	newFieldAuditSheetmst=fillValues( newFieldAuditSheetmst,existFieldAuditSheetmst);
	//return fieldAuditSheetDao.create(newFieldAuditSheetmst);
	return fieldauditsheetApi.saveFieldAuditSheet(newFieldAuditSheetmst);
	
}
public FieldAuditSheetmst update(FieldAuditSheetmst newFieldAuditSheetmst,
		FieldAuditSheetmst existFieldAuditSheetmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception{
	//validations.validate(newFieldAuditSheetmst,"FieldAuditSheet","create");
	newFieldAuditSheetmst=fillValues( newFieldAuditSheetmst,existFieldAuditSheetmst);
	//return fieldAuditSheetDao.update(newFieldAuditSheetmst);
	return fieldauditsheetApi.saveFieldAuditSheet(newFieldAuditSheetmst);
}

public List<String[]> getFieldAuditSheetList(CommonFilter commonFilter)throws Exception{
	return fieldAuditSheetDao.getFieldAuditSheetList(commonFilter);
}
public Workbook getFieldAuditModificationGridDataExportExcel(CommonFilter commonFilter,
		  JSONObject colmodel, String format) throws  Exception{
	 return fieldAuditSheetDao.getFieldAuditModificationGridDataExportExcel(commonFilter, colmodel, format);
}

public List<String[]> getflid(String originalid) throws Exception{
	  return fieldAuditSheetDao.getflid(originalid);
}

public List<ComboBox> getServiceProvider(ComboFilter comboFilter) throws Exception {
	comboFilter.setNameField("ASPM_NAME");
	comboFilter.setIdField("ASPM_KEYID");
	comboFilter.setTableName("ADM_TL_SERVICEPROVIDERMST");
	return commonFilterDao.fillComboValues(comboFilter);
}

private FieldAuditSheetmst fillValues(FieldAuditSheetmst newFieldAuditSheetmst,FieldAuditSheetmst existFieldAuditSheetmst) {
	
	CommonMessage.debugMsg("fillValues");
	String dateTime = CommonFunctions.pg_dateTimeNow();
	
	String date = newFieldAuditSheetmst.getFasmDate();
	newFieldAuditSheetmst.setFasmDate(CommonFunctions.pg_getDateTimeFromDate(date));
	newFieldAuditSheetmst.setFasmActive("Y");
	newFieldAuditSheetmst.setFasmCreatedon(dateTime);
	newFieldAuditSheetmst.setFasmModifiedon(dateTime);
	
	if(newFieldAuditSheetmst.getFasmKeyid() == null )
		newFieldAuditSheetmst.setFasmKeyid(null);
	if(newFieldAuditSheetmst.getFasmFlid() == null )
		newFieldAuditSheetmst.setFasmFlid("{}");
	if(newFieldAuditSheetmst.getFasmDate() == null )
		newFieldAuditSheetmst.setFasmDate(dateTime);
	if(newFieldAuditSheetmst.getFasmEvaluatedby()==null)
		newFieldAuditSheetmst.setFasmEvaluatedby("{}");
	if(newFieldAuditSheetmst.getFasmJobdesc()==null)
		newFieldAuditSheetmst.setFasmJobdesc("{}");
	if(newFieldAuditSheetmst.getFasmNoofesp()==null)
		newFieldAuditSheetmst.setFasmNoofesp("0");
	if(newFieldAuditSheetmst.getFasmSerprovider()==null)	
		newFieldAuditSheetmst.setFasmSerprovider("{}");
	if(newFieldAuditSheetmst.getFasmShift()==null)
		newFieldAuditSheetmst.setFasmShift("{}");
	if(newFieldAuditSheetmst.getFasmViolations()==null)
		newFieldAuditSheetmst.setFasmViolations("{}");
	if(newFieldAuditSheetmst.getFasmDonedmt()==null)
		newFieldAuditSheetmst.setFasmDonedmt("{}");
	if(newFieldAuditSheetmst.getFasmDonejh()==null)
		newFieldAuditSheetmst.setFasmDonejh("{}");
	if(newFieldAuditSheetmst.getFasmTradeid()==null)
		newFieldAuditSheetmst.setFasmTradeid("{}");
	 if(newFieldAuditSheetmst.getFasmTempfield1()==null)
		 newFieldAuditSheetmst.setFasmTempfield1("{}");
	 if(newFieldAuditSheetmst.getFasmTempfield2()==null)
		 newFieldAuditSheetmst.setFasmTempfield2("{}");
	 if(newFieldAuditSheetmst.getFasmTempfield3()==null)
		 newFieldAuditSheetmst.setFasmTempfield3("{}");
	 if(newFieldAuditSheetmst.getFasmTempfield4()==null)
		 newFieldAuditSheetmst.setFasmTempfield4("{}");
	 if(newFieldAuditSheetmst.getFasmTempfield5()==null)
		 newFieldAuditSheetmst.setFasmTempfield5("{}");
	
	 newFieldAuditSheetmst.setAuditDtl(fillValuesAuditDetails(newFieldAuditSheetmst));
	return newFieldAuditSheetmst;	
}

/*
 * private List<FieldAuditSheetdtl> fillValuesAuditDetails(FieldAuditSheetmst
 * newFieldAuditSheetmst) { // TODO Auto-generated method stub String dateTime =
 * CommonFunctions.dateTimeNow(); List<FieldAuditSheetdtl>
 * fieldAuditSheetdtlList=newFieldAuditSheetmst.getAuditDtl();
 * 
 * if (newFieldAuditSheetmst.getAuditDtl()!=null){ for(int i=0
 * ;i<=fieldAuditSheetdtlList.size()-1;i++){
 * fieldAuditSheetdtlList.get(i).setFasdCreatedon(dateTime);
 * fieldAuditSheetdtlList.get(i).setFasdModifiedon(dateTime);
 * fieldAuditSheetdtlList.get(i).setFasdCreatedby(newFieldAuditSheetmst.
 * getFasmCreatedby()); fieldAuditSheetdtlList.get(i).setFasdActive("Y");
 * 
 * if(fieldAuditSheetdtlList.get(i).getFasdmasterid()==null)
 * fieldAuditSheetdtlList.get(i).setFasdmasterid("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdEspid()==null)
 * fieldAuditSheetdtlList.get(i).setFasdEspid("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdPpeid()==null)
 * fieldAuditSheetdtlList.get(i).setFasdPpeid("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdPpecondition()==null)
 * fieldAuditSheetdtlList.get(i).setFasdPpecondition("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdKnowledge()==null)
 * fieldAuditSheetdtlList.get(i).setFasdKnowledge("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdRemarks()==null)
 * fieldAuditSheetdtlList.get(i).setFasdRemarks("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdTools()==null)
 * fieldAuditSheetdtlList.get(i).setFasdTools("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdWorkpermitsafety()==null)
 * fieldAuditSheetdtlList.get(i).setFasdWorkpermitsafety("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdOtherEspName()==null)
 * fieldAuditSheetdtlList.get(i).setFasdOtherEspName("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdTempfield2()==null)
 * fieldAuditSheetdtlList.get(i).setFasdTempfield2("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdTempfield3()==null)
 * fieldAuditSheetdtlList.get(i).setFasdTempfield3("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdTempfield4()==null)
 * fieldAuditSheetdtlList.get(i).setFasdTempfield4("{}");
 * if(fieldAuditSheetdtlList.get(i).getFasdTempfield5()==null)
 * fieldAuditSheetdtlList.get(i).setFasdTempfield5("{}"); } }
 * CommonMessage.debugMsg("End Of  fillValues fieldAuditSheetdtlList"); return
 * fieldAuditSheetdtlList; }
 * 
 */
// mano

private List<FieldAuditSheetdtl> fillValuesAuditDetails(FieldAuditSheetmst newFieldAuditSheetmst) {
    String dateTime = CommonFunctions.pg_dateTimeNow();
    List<FieldAuditSheetdtl> fieldAuditSheetdtlList = newFieldAuditSheetmst.getAuditDtl();

    if (newFieldAuditSheetmst.getAuditDtl() != null) {
        for (int i = 0; i <= fieldAuditSheetdtlList.size() - 1; i++) {
            fieldAuditSheetdtlList.get(i).setFasdCreatedon(dateTime);
            fieldAuditSheetdtlList.get(i).setFasdModifiedon(dateTime);
            fieldAuditSheetdtlList.get(i).setFasdCreatedby(newFieldAuditSheetmst.getFasmCreatedby());
            fieldAuditSheetdtlList.get(i).setFasdActive("Y");

            // ✅ ALWAYS set the master ID - remove the if condition
           fieldAuditSheetdtlList.get(i).setFasdmasterid(newFieldAuditSheetmst.getFasmKeyid());
            
            if (fieldAuditSheetdtlList.get(i).getFasdEspid() == null)
                fieldAuditSheetdtlList.get(i).setFasdEspid("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdPpeid() == null)
                fieldAuditSheetdtlList.get(i).setFasdPpeid("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdPpecondition() == null)
                fieldAuditSheetdtlList.get(i).setFasdPpecondition("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdKnowledge() == null)
                fieldAuditSheetdtlList.get(i).setFasdKnowledge("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdRemarks() == null)
                fieldAuditSheetdtlList.get(i).setFasdRemarks("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdTools() == null)
                fieldAuditSheetdtlList.get(i).setFasdTools("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdWorkpermitsafety() == null)
                fieldAuditSheetdtlList.get(i).setFasdWorkpermitsafety("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdOtherEspName() == null)
                fieldAuditSheetdtlList.get(i).setFasdOtherEspName("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdTempfield2() == null)
                fieldAuditSheetdtlList.get(i).setFasdTempfield2("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdTempfield3() == null)
                fieldAuditSheetdtlList.get(i).setFasdTempfield3("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdTempfield4() == null)
                fieldAuditSheetdtlList.get(i).setFasdTempfield4("{}");
            if (fieldAuditSheetdtlList.get(i).getFasdTempfield5() == null)
                fieldAuditSheetdtlList.get(i).setFasdTempfield5("{}");
        }
    }
    CommonMessage.debugMsg("End Of  fillValues fieldAuditSheetdtlList");
    return fieldAuditSheetdtlList;
}
public List<String[]> getFieldAuditSheetReport(CommonFilter commonFilter)throws Exception{
	return fieldAuditSheetDao.getFieldAuditSheetReport(commonFilter);
}

public Workbook getFieldAuditSheetReportExcel(CommonFilter commonFilter,
		  JSONObject colmodel, String format) throws  Exception{
	 return fieldAuditSheetDao.getFieldAuditSheetReportExcel(commonFilter, colmodel, format);
}


}
