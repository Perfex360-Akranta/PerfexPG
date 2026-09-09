package com.akranta.tpm.service.impl;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.LOPCDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.LOPCDaoImpl;
import com.akranta.tpm.dao.sql.LOPCEntrymstsql;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.LopcEntryMst;
import com.akranta.tpm.service.LOPCService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.service.api.LopcEntryServiceApi;


public class LOPCServiceImpl implements LOPCService {
   private Validations validations = new Validations();
   DBActionTemplate dbActionTemplate;
   private LopcEntryServiceApi lopcentryserviceapi;
   LOPCDao lOPCDao;
   CommonFilterDao commonFilterDao;

   public LOPCServiceImpl(DBActionTemplate dbActionTemplate) {
      this.dbActionTemplate = dbActionTemplate;
      this.lOPCDao = new LOPCDaoImpl(dbActionTemplate);
      this.commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
   }
   
   public void LOPCServiceImplJwt(String JwtToken){
		try{
			lOPCDao.LOPCDaoImplJwt(JwtToken);
			lopcentryserviceapi = new LopcEntryServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}

   public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
      this.dbActionTemplate = dbActionTemplate;
   }

   public List<ComboBox> getLOPCCategory(ComboFilter comboFilter) throws Exception {
      comboFilter.setNameField("LOCY_NAME");
      comboFilter.setIdField("LOCY_KEYID");
      comboFilter.setTableName("GEN_TL_LOPCCATEGORY");
      return this.commonFilterDao.fillComboValues(comboFilter);
   }

   public LopcEntryMst create(LopcEntryMst LopcEntryMst, LopcEntryMst existLopcEntryMst) throws Exception {
      String xmlName = "LOPC";
      String validationsFor = "create";
      CommonMessage.debugMsg("Validation XML Name:::::::::" + xmlName);
      this.validations.validate(LopcEntryMst, xmlName, validationsFor);
      this.fillvalues(LopcEntryMst, existLopcEntryMst);
      //return this.lOPCDao.create(LopcEntryMst);
      
      return this.lopcentryserviceapi.saveLopcEntry(LopcEntryMst);
   }

   private void fillvalues(LopcEntryMst LopcEntryMst, LopcEntryMst existLopcEntryMst) throws Exception {
      String dateTime = CommonFunctions.pg_dateTimeNow();
      CommonMessage.debugMsg("DateTime" + dateTime);
      CommonMessage.debugMsg("Inside Fill Values");
      LopcEntryMst.setLoemCreatedon(dateTime);
      LopcEntryMst.setLoemModifiedon(dateTime);
      LopcEntryMst.setLoemActive("Y");
      
      String date = LopcEntryMst.getLoemOccurencedatetime();
      LopcEntryMst.setLoemOccurencedatetime(CommonFunctions.pg_getDateTimeFromDate(date));
      
      
      String date2 = LopcEntryMst.getLoemPrepareddatetime();
      LopcEntryMst.setLoemPrepareddatetime(CommonFunctions.pg_getDateTimeFromDate(date2));
      
      
      
      
      
      
      if (LopcEntryMst.getLoemFnlid() == null) {
         LopcEntryMst.setLoemFnlid("{}");
      }

      if (LopcEntryMst.getLoemEmployeeid() == null) {
         LopcEntryMst.setLoemEmployeeid("{}");
      }

      if (LopcEntryMst.getLoemIdentifiedby() == null) {
         LopcEntryMst.setLoemIdentifiedby("{}");
      }

      if (LopcEntryMst.getLoemLocacategoryid() == null) {
         LopcEntryMst.setLoemLocacategoryid("{}");
      }

      if (LopcEntryMst.getLoemLopcdesc() == null) {
         LopcEntryMst.setLoemLopcdesc("{}");
      }

      if (LopcEntryMst.getLoemTempfield1() == null) {
         LopcEntryMst.setLoemTempfield1("-");
      }

      if (LopcEntryMst.getLoemTempfield2() == null) {
         LopcEntryMst.setLoemTempfield2("-");
      }

      if (LopcEntryMst.getLoemTempfield3() == null) {
         LopcEntryMst.setLoemTempfield3("-");
      }

      if (LopcEntryMst.getLoemTempfield4() == null) {
         LopcEntryMst.setLoemTempfield4("-");
      }

      if (LopcEntryMst.getLoemTempfield5() == null) {
         LopcEntryMst.setLoemTempfield5("-");
      }

   }

   public List<String[]> getLOPCModificationList(CommonFilter commonFilter) throws Exception {
      return this.lOPCDao.getLOPCModificationList(commonFilter);
   }

   public LopcEntryMst getLOPCData(String keyid) throws Exception {
      //LopcEntryMst LopcEntryMst = new LopcEntryMst();
		/*
		 * new LOPCEntrymstsql(); String sql =
		 * "SELECT * FROM GEN_TL_LOPCENTRYMST WHERE LOEM_KEYID=?";
		 * CommonMessage.debugMsg("sql:::" + sql); Object[] args = new Object[]{keyid};
		 * LopcEntryMst.setSaveArray(this.dbActionTemplate.getDataArr(sql, args));
		 */
     // return LopcEntryMst;
      
      return lopcentryserviceapi.getCompleteLopcEntryData(keyid);
   }

	/*
	 * public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter) throws
	 * Exception { return this.lOPCDao.getLOPCActionPlanList(commonFilter); }
	 */
   @Override
   public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter, String employeeId) throws Exception {
       return this.lOPCDao.getLOPCActionPlanList(commonFilter, employeeId);
   }
   public BdmTlWwbladtl UpdateLOPCAClosure(BdmTlWwbladtl bdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl, String keyid, String completedBy, String status, String completedDate, String correctiveaction, String remarks) throws Exception {
     // return this.lOPCDao.UpdateLOPCAClosure(bdmTlWwbladtl, existBdmTlWwbladtl, keyid, completedBy, status, completedDate, correctiveaction, remarks);
      
      return this.lopcentryserviceapi.updateLopcActionClosure(bdmTlWwbladtl, existBdmTlWwbladtl, keyid, completedBy, status, completedDate, correctiveaction, remarks);
   }

   public List<String[]> getLOPCView(CommonFilter commonFilter) throws Exception {
      return this.lOPCDao.getLOPCView(commonFilter);
   }

   public LopcEntryMst update(LopcEntryMst LopcEntryMst, LopcEntryMst existLopcEntryMst, String LopcId) throws Exception {
      String xmlName = "LOPC";
      String validationsFor = "create";
      CommonMessage.debugMsg("Validation XML Name:::::::::" + xmlName);
      this.validations.validate(LopcEntryMst, xmlName, validationsFor);
      this.fillvalues(LopcEntryMst, existLopcEntryMst);
      //return this.lOPCDao.Update(LopcEntryMst, LopcId);
      
      if (LopcEntryMst.getLoemKeyid() == null || LopcEntryMst.getLoemKeyid().trim().isEmpty()) {
    	  LopcEntryMst.setLoemKeyid(LopcId);
      }
      
      return this.lopcentryserviceapi.saveLopcEntry(LopcEntryMst);
   }

   public Workbook getLopcExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
      return this.lOPCDao.getLopcExportExcel(commonFilter, tblJSONObj, format);
   }

   public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
      return this.lOPCDao.getElementId(loginflid, loginlevel, loginElementid, empId);
   }
   public Workbook getLopcModificationExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
	    return this.lOPCDao.getLopcModificationExportExcel(commonFilter, tblJSONObj, format);
	}
	/*
	 * public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter,
	 * JSONObject tblJSONObj, String format) throws Exception { return
	 * this.lOPCDao.getLopcActionClosureExportExcel(commonFilter, tblJSONObj,
	 * format); }
	 */
   public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format, String employeeId) throws Exception {
	    return this.lOPCDao.getLopcActionClosureExportExcel(commonFilter, tblJSONObj, format, employeeId);
	}
   
}
