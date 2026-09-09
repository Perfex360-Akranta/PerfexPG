package com.akranta.tpm.dao.impl;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.LOPCDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.LOPCEntrymstsql;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.LopcEntryMst;
import com.akranta.tpm.utils.ExcelUtils;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.LopcEntryServiceApi;
import com.akranta.tpm.utils.CommonMessage;

public class LOPCDaoImpl implements LOPCDao {
   private DBActionTemplate dbActionTemplate;
   private LopcEntryServiceApi lopcentryserviceapi;
	FunctionCallApi fnCallApi;
	
   LOPCEntrymstsql lOPCEntrymstsql = null;
   

   public LOPCDaoImpl(DBActionTemplate dbActionTemplate) {
      this.lOPCEntrymstsql = new LOPCEntrymstsql();
      this.dbActionTemplate = dbActionTemplate;
   }
   
   public void LOPCDaoImplJwt(String JwtToken) 
  	{
  		try{
  			lopcentryserviceapi = new LopcEntryServiceApi(JwtToken);
  		fnCallApi = new FunctionCallApi(JwtToken);
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}
  	}

   public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
      this.dbActionTemplate = dbActionTemplate;
   }

   public LopcEntryMst create(LopcEntryMst lopcEntryMst) throws Exception {
      CommonMessage.debugMsg("Inside DaoImpl");
      List<String> sqls = new ArrayList();
      LOPCEntrymstsql lOPCEntrymstsql = new LOPCEntrymstsql();

      try {
         CommonMessage.debugMsg("Inside try loop");
         lopcEntryMst.setLoemKeyid(this.dbActionTemplate.getSequenceNumber("GEN_TL_LOPCENTRYMST", 14, "LOPC", (String)null, "N"));
         sqls.add(LOPCEntrymstsql.getInsertSql(lOPCEntrymstsql.getLopcDbFields(), lopcEntryMst.getSaveArray()));
         this.dbActionTemplate.executeStatements(sqls);
         return lopcEntryMst;
      } catch (Exception var5) {
         throw new Exception(var5.getMessage());
      }
   }

   public List<String[]> getLOPCModificationList(CommonFilter commonFilter) throws Exception {
      try {
         List<String> paramValues = new ArrayList();
         String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
         String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
         paramValues.add(condParms);
         paramValues.add(commonParams);
         List<String[]> dataList = null;
         //dataList = this.dbActionTemplate.processFunctionCalls("GEN_FN_LOPCVIEW", paramValues);
         
         dataList = this.fnCallApi.callFunction("GEN_FN_LOPCVIEW_SB", paramValues,3,true);
         
         
         
         
        
         if (commonFilter.getViewClick() == 'Y') {
            String totalCnt = (String)paramValues.get(0);
            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
            if (isInteger) {
               commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
            }
         }

         return dataList;
      } catch (Exception var8) {
         throw new Exception(var8.getMessage());
      }
   }
//mano
   public Workbook getLopcModificationExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
	    List<String> paramValues = new ArrayList();
	    String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(condParms);
	    paramValues.add(commonParams);
	    ResultSet rs = null;

	    Workbook var10;
	    try {
	        rs = this.dbActionTemplate.NewdbFunctionCall2("GEN_FN_LOPCVIEW", paramValues);
	        ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
	        var10 = excelUtils.writeToExcel(rs, format, 2, 0, 0);
	    } finally {
	        if (rs != null) {
	            DBActionTemplate.closeConnection(rs, (Statement)null, (CallableStatement)null, (PreparedStatement)null, rs.getStatement().getConnection());
	        }
	    }

	    return var10;
	}

	/*
	 * public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter) throws
	 * Exception { try { List<String> paramValues = new ArrayList(); String
	 * condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter); String
	 * commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	 * paramValues.add(condParms); paramValues.add(commonParams); List<String[]>
	 * dataList = null; CommonMessage.debugMsg("inside Dao"); dataList =
	 * this.dbActionTemplate.processFunctionCalls("GEN_FN_LOPCACTIONPLAN",
	 * paramValues); if (commonFilter.getViewClick() == 'Y') { String totalCnt =
	 * (String)paramValues.get(0); boolean isInteger = Pattern.matches("^\\d*$",
	 * totalCnt); if (isInteger) {
	 * commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)); } }
	 * 
	 * return dataList; } catch (Exception var8) { throw new
	 * Exception(var8.getMessage()); } }
	 */
   @Override
   public List<String[]> getLOPCActionPlanList(CommonFilter commonFilter, String employeeId) throws Exception {
       try {
           List<String> paramValues = new ArrayList<>();
           String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
           
           // CRITICAL: Add USERID to condParms in the correct format
           if (employeeId != null && !employeeId.trim().isEmpty()) {
               if (!condParms.endsWith(";")) {
                   condParms += ";";
               }
               condParms += "USERID=" + employeeId;
           }
           
           CommonMessage.debugMsg("inside Dao with employeeId: " + employeeId);
           CommonMessage.debugMsg("condParms: " + condParms);
           
           String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
           paramValues.add(condParms);
           paramValues.add(commonParams);
           
           List<String[]> dataList = null;
          // dataList = this.dbActionTemplate.processFunctionCalls("GEN_FN_LOPCACTIONPLAN", paramValues);
           
           dataList = this.fnCallApi.callFunction("GEN_FN_LOPCACTIONPLAN_SB", paramValues,3,true);
           
           
           
           
           if (commonFilter.getViewClick() == 'Y') {
               String totalCnt = (String)paramValues.get(0);
               boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
               if (isInteger) {
                   commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
               }
           }
           
           return dataList;
       } catch (Exception var8) {
           throw new Exception(var8.getMessage());
       }
   }

   //mano
	/*
	 * public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter,
	 * JSONObject tblJSONObj, String format) throws Exception { List<String>
	 * paramValues = new ArrayList(); String condParms =
	 * FilterCondSql.getAbnRelatedConditionStr(commonFilter); String commonParams =
	 * FilterCondSql.getGridCommonParams(commonFilter); paramValues.add(condParms);
	 * paramValues.add(commonParams); ResultSet rs = null;
	 * 
	 * Workbook var10; try { rs =
	 * this.dbActionTemplate.NewdbFunctionCall2("GEN_FN_LOPCACTIONPLAN",
	 * paramValues); ExcelUtils excelUtils = new ExcelUtils(tblJSONObj); var10 =
	 * excelUtils.writeToExcel(rs, format, 2, 0, 0); } finally { if (rs != null) {
	 * DBActionTemplate.closeConnection(rs, (Statement)null,
	 * (CallableStatement)null, (PreparedStatement)null,
	 * rs.getStatement().getConnection()); } }
	 * 
	 * return var10; }
	 */
   
   public Workbook getLopcActionClosureExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format, String employeeId) throws Exception {
	    List<String> paramValues = new ArrayList<>();
	    String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	    
	    // **MANUAL FIX: Append employeeId to condParms**
	    if (employeeId != null && !employeeId.isEmpty()) {
	        condParms = condParms + ";USERID=" + employeeId;
	    }
	    CommonMessage.debugMsg("condParms with USERID: " + condParms);
	    
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(condParms);
	    paramValues.add(commonParams);
	    ResultSet rs = null;

	    Workbook var10;
	    try {
	        rs = this.dbActionTemplate.NewdbFunctionCall2("GEN_FN_LOPCACTIONPLAN", paramValues);
	        ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
	        var10 = excelUtils.writeToExcel(rs, format, 2, 0, 0);
	    } finally {
	        if (rs != null) {
	            DBActionTemplate.closeConnection(rs, (Statement)null, (CallableStatement)null, (PreparedStatement)null, rs.getStatement().getConnection());
	        }
	    }

	    return var10;
	}
	/*
	 * public BdmTlWwbladtl UpdateLOPCAClosure(BdmTlWwbladtl bdmTlWwbladtl,
	 * BdmTlWwbladtl existBdmTlWwbladtl, String keyid, String completedBy, String
	 * status, String completedDate, String correctiveaction, String remarks) throws
	 * Exception { StringBuilder sql = new StringBuilder();
	 * sql.append("UPDATE BDM_TL_WWBLADTL SET WWBD_ACTIONTAKEN='" + correctiveaction
	 * + "',WWBD_COMPLETEDBY='" + completedBy + "',WWBD_COMPLETEDDATE='" +
	 * completedDate + "',WWBD_STATUS='" + status + "',WWBD_REMARKS='" + remarks +
	 * "' WHERE WWBD_WWBL_KEYID='" + keyid + "' "); CommonMessage.debugMsg("sql" + sql);
	 * this.dbActionTemplate.executeStatement(sql.toString()); return bdmTlWwbladtl;
	 * }
	 */
   //mano
	/*
	 * public BdmTlWwbladtl UpdateLOPCAClosure(BdmTlWwbladtl bdmTlWwbladtl,
	 * BdmTlWwbladtl existBdmTlWwbladtl, String keyid, String completedBy, String
	 * status, String completedDate, String correctiveaction, String remarks) throws
	 * Exception {
	 * 
	 * // First, get the WWBD_WWBL_KEYID (foreign key) from the record StringBuilder
	 * getForeignKeySql = new StringBuilder(); getForeignKeySql.
	 * append("SELECT WWBD_WWBL_KEYID FROM BDM_TL_WWBLADTL WHERE WWBD_KEYID='").
	 * append(keyid).append("'");
	 * 
	 * CommonMessage.debugMsg("Select SQL: " + getForeignKeySql); Object[] resultArray =
	 * this.dbActionTemplate.getDataArr(getForeignKeySql.toString(), new Object[0]);
	 * 
	 * String wwblKeyid = null; if (resultArray != null && resultArray.length > 0) {
	 * wwblKeyid = (String) resultArray[0]; }
	 * 
	 * CommonMessage.debugMsg("Retrieved wwblKeyid: " + wwblKeyid);
	 * 
	 * if (wwblKeyid == null || wwblKeyid.isEmpty()) { throw new
	 * Exception("Foreign key WWBD_WWBL_KEYID not found for WWBD_KEYID: " + keyid);
	 * }
	 * 
	 * // Now update all records with that foreign key StringBuilder updateSql = new
	 * StringBuilder(); updateSql.append("UPDATE BDM_TL_WWBLADTL SET ");
	 * updateSql.append("WWBD_ACTIONTAKEN='").append(correctiveaction).append("', "
	 * ); updateSql.append("WWBD_COMPLETEDBY='").append(completedBy).append("', ");
	 * updateSql.append("WWBD_COMPLETEDDATE='").append(completedDate).append("', ");
	 * updateSql.append("WWBD_STATUS='").append(status).append("', ");
	 * updateSql.append("WWBD_REMARKS='").append(remarks).append("' ");
	 * updateSql.append("WHERE WWBD_WWBL_KEYID='").append(wwblKeyid).append("'");
	 * 
	 * CommonMessage.debugMsg("Update SQL: " + updateSql);
	 * this.dbActionTemplate.executeStatement(updateSql.toString());
	 * 
	 * return bdmTlWwbladtl; }
	 */
   public BdmTlWwbladtl UpdateLOPCAClosure(BdmTlWwbladtl bdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl, String keyid, String completedBy, String status, String completedDate, String correctiveaction, String remarks) throws Exception {
	    
	    
	    StringBuilder getForeignKeySql = new StringBuilder();
	    getForeignKeySql.append("SELECT WWBD_WWBL_KEYID FROM BDM_TL_WWBLADTL WHERE WWBD_KEYID='").append(keyid).append("'");
	    
	    CommonMessage.debugMsg("Select SQL: " + getForeignKeySql);
	    Object[] resultArray = this.dbActionTemplate.getDataArr(getForeignKeySql.toString(), new Object[0]);
	    
	    String wwblKeyid = null;
	    if (resultArray != null && resultArray.length > 0) {
	        wwblKeyid = (String) resultArray[0];
	    }
	    
	    CommonMessage.debugMsg("Retrieved wwblKeyid: " + wwblKeyid);
	    
	    if (wwblKeyid == null || wwblKeyid.isEmpty()) {
	        throw new Exception("Foreign key WWBD_WWBL_KEYID not found for WWBD_KEYID: " + keyid);
	    }
	    
	    // Now update all records with that foreign key
	    StringBuilder updateSql = new StringBuilder();
	    updateSql.append("UPDATE BDM_TL_WWBLADTL SET ");
	    updateSql.append("WWBD_ACTIONTAKEN='").append(correctiveaction).append("', ");
	    updateSql.append("WWBD_COMPLETEDBY='").append(completedBy).append("', ");
	    updateSql.append("WWBD_COMPLETEDDATE='").append(completedDate).append("', ");
	    updateSql.append("WWBD_STATUS='").append(status).append("', ");
	    updateSql.append("WWBD_REMARKS='").append(remarks).append("' ");
	    updateSql.append("WHERE WWBD_WWBL_KEYID='").append(wwblKeyid).append("'");
	    
	    CommonMessage.debugMsg("Update SQL: " + updateSql);
	    this.dbActionTemplate.executeStatement(updateSql.toString());
	    
	    // Check if all detail records are completed, then update master table
	    if ("C".equals(status)) {
	        checkAndUpdateMasterInvestigation(wwblKeyid);
	    }
	    
	    return bdmTlWwbladtl;
	}

	
	private void checkAndUpdateMasterInvestigation(String wwblKeyid) throws Exception {
	    // Check if there are any pending records
	    StringBuilder checkSql = new StringBuilder();
	    checkSql.append("SELECT COUNT(*) FROM BDM_TL_WWBLADTL ");
	    checkSql.append("WHERE WWBD_WWBL_KEYID='").append(wwblKeyid).append("' ");
	    checkSql.append("AND WWBD_STATUS != 'C' ");
	    checkSql.append("AND WWBD_ACTIVE='Y'");
	    
	    CommonMessage.debugMsg("Check SQL: " + checkSql);
	    Object[] countArray = this.dbActionTemplate.getDataArr(checkSql.toString(), new Object[0]);
	    
	    int pendingCount = 0;
	    if (countArray != null && countArray.length > 0) {
	        pendingCount = Integer.parseInt(String.valueOf(countArray[0]));
	    }
	    
	    CommonMessage.debugMsg("Pending count: " + pendingCount);
	    
	    // If no pending records, update master table to 'C'
	    if (pendingCount == 0) {
	        StringBuilder updateMasterSql = new StringBuilder();
	        updateMasterSql.append("UPDATE BDM_TL_WWBLAMST SET ");
	        updateMasterSql.append("WWBL_INVESTIGATION='C' ");
	        updateMasterSql.append("WHERE WWBL_KEYID='").append(wwblKeyid).append("'");
	        
	        CommonMessage.debugMsg("Update Master SQL: " + updateMasterSql);
	        this.dbActionTemplate.executeStatement(updateMasterSql.toString());
	        CommonMessage.debugMsg("Master investigation status updated to Completed");
	    } else {
	        CommonMessage.debugMsg("Still " + pendingCount + " pending records. Master not updated.");
	    }
	}
   public List<String[]> getLOPCView(CommonFilter commonFilter) throws Exception {
      try {
         List<String> paramValues = new ArrayList();
         String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
         String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
         paramValues.add(condParms);
         paramValues.add(commonParams);
         List<String[]> dataList = null;
         CommonMessage.debugMsg("inside Dao View");
         //dataList = this.dbActionTemplate.processFunctionCalls("GEN_FN_LOPCFINALVIEW", paramValues);
         
         dataList = this.fnCallApi.callFunction("GEN_FN_LOPCFINALVIEW_SB", paramValues,3,true);
         
         
         if (commonFilter.getViewClick() == 'Y') {
            String totalCnt = (String)paramValues.get(0);
            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
            if (isInteger) {
               commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
            }
         }

         return dataList;
      } catch (Exception var8) {
         throw new Exception(var8.getMessage());
      }
   }

   public LopcEntryMst Update(LopcEntryMst lopcEntryMst, String LopcId) throws Exception {
      CommonMessage.debugMsg("Inside DaoImpl");
      List<String> sqls = new ArrayList();
      LOPCEntrymstsql lOPCEntrymstsql = new LOPCEntrymstsql();

      try {
         CommonMessage.debugMsg("Inside try loop");
         lopcEntryMst.setLoemKeyid(LopcId);
         sqls.add(LOPCEntrymstsql.getUpdateSql(lOPCEntrymstsql.getLopcDbFields(), lopcEntryMst.getSaveArray()));
         this.dbActionTemplate.executeStatements(sqls);
         return lopcEntryMst;
      } catch (Exception var6) {
         throw new Exception(var6.getMessage());
      }
   }

   public Workbook getLopcExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
      List<String> paramValues = new ArrayList();
      String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
      String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
      paramValues.add(condParms);
      paramValues.add(commonParams);
      ResultSet rs = null;

      Workbook var10;
      try {
         rs = this.dbActionTemplate.NewdbFunctionCall2("GEN_FN_LOPCFINALVIEW", paramValues);
         ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
         var10 = excelUtils.writeToExcel(rs, format, 2, 0, 0);
      } finally {
         if (rs != null) {
            DBActionTemplate.closeConnection(rs, (Statement)null, (CallableStatement)null, (PreparedStatement)null, rs.getStatement().getConnection());
         }

      }

      return var10;
   }

   public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
      sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
      if (UIUtils.isValidKeyId(loginflid)) {
         sql.append(" AND FRT_FNLN_KEYID  = '" + loginflid + "' ");
      }

      sql.append(" AND FRT_EMPM_KEYID = '" + empId + "'  AND ROLE_LEVEL= '" + loginlevel + "'");
      Object[] args = new Object[0];
      CommonMessage.debugMsg(sql);
      List<String[]> userDatas = this.dbActionTemplate.getDataList(sql.toString(), args);
      return userDatas;
   }
   
  
}
