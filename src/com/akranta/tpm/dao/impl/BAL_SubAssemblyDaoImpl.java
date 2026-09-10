package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_GenTlSubAssemblymstDao;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.BAL_GenTlSubAssemblymstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;

public class BAL_SubAssemblyDaoImpl implements BAL_GenTlSubAssemblymstDao {
	
	private DBActionTemplate dbActionTemplate;
	private static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";
	private FunctionalLocValidations functionalLocValidations = null;

    private static final String TBL = "bal_gen_tl_subassemblymst";
    private static final String TBL_SUBASSEMBLY = "bal_gen_tl_subassemblymst";

    // Column list must match GenTlSubAssemblymst.tableFldConstants order
    private static final String[] COLS = {
        "sbam_keyid", "sbam_assemblyid", "sbam_code", "sbam_name",
        "sbam_description", "sbam_remarks", "sbam_active",
        "sbam_createdby", "sbam_createdon", "sbam_modifiedon"
    };
    
    private FunctionCallApi fnCallApi;

    public BAL_SubAssemblyDaoImpl(DBActionTemplate dbActionTemplate) {
        this.dbActionTemplate = dbActionTemplate;
        functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
    }

    public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
        this.dbActionTemplate = dbActionTemplate;
    }

	/*
	 * @Override public BAL_GenTlSubAssemblymst create(BAL_GenTlSubAssemblymst obj)
	 * throws Exception { obj.setSbamKeyid(db.getSequenceNumber(TBL));
	 * obj.setSbamCode(obj.getSbamKeyid()); List<String> sqls = new ArrayList<>();
	 * sqls.add(buildInsert(obj)); db.executeStatements(sqls); return obj; }
	 */
    // commented and added by priyanka on 09/07/2026
	/*
	 * @Override public BAL_GenTlSubAssemblymst create(BAL_GenTlSubAssemblymst obj)
	 * throws Exception {
	 * 
	 * BAL_GenTlSubAssemblymstSql sbamSql = new BAL_GenTlSubAssemblymstSql();
	 * List<String> sqls = new ArrayList<>();
	 * 
	 * // get sequence number — same pattern as assembly
	 * obj.setSbamKeyid(dbActionTemplate.getSequenceNumber(
	 * BAL_GenTlSubAssemblymstSql.TBL_BAL_GEN_TL_SUBASSEMBLYMST));
	 * obj.setSbamCode(obj.getSbamKeyid());
	 * 
	 * System.out.println("SubAssembly Id : " + obj.getSbamKeyid());
	 * 
	 * sqls.add(BAL_GenTlSubAssemblymstSql.getInsertSql( sbamSql.getSbamDbFields(),
	 * obj.getSaveArray())); dbActionTemplate.executeStatements(sqls); return obj; }
	 */
    @Override
    public BAL_GenTlSubAssemblymst create(BAL_GenTlSubAssemblymst obj, GenTlFunctionallocn genTlFunctionallocn) throws Exception {

        BAL_GenTlSubAssemblymstSql sbamSql = new BAL_GenTlSubAssemblymstSql();
        List<String> sqls = new ArrayList<>();

        obj.setSbamKeyid(dbActionTemplate.getSequenceNumber(
                BAL_GenTlSubAssemblymstSql.TBL_BAL_GEN_TL_SUBASSEMBLYMST));
        obj.setSbamCode(obj.getSbamKeyid());

        if (genTlFunctionallocn != null) {
            String elementId = dbActionTemplate.getSingleValue(
                    TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN,
                    "FNLN_ELEMENTID", "FNLN_ORIGINALID",
                    genTlFunctionallocn.getFnlnOriginalid()); // assembly's keyid comes in here
            System.out.println("Element Id : " + elementId);
            if (UIUtils.isValidKeyId(elementId)) {
                BAL_GenTlFunctionallocnSql genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql();

                genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN));
                genTlFunctionallocn.setFnlnOriginalid(obj.getSbamKeyid());
                genTlFunctionallocn.setFnlnElementid(elementId + "-" + obj.getSbamKeyid());
                genTlFunctionallocn.setFnlnParentid(elementId);
                //genTlFunctionallocn.setFnlnDisplaycode(obj.getSbamDescription());
                genTlFunctionallocn.setFnlnDisplaycode(obj.getSbamName());
                //genTlFunctionallocn.setFnlnDescription(obj.getSbamDescription()); // adjust getter name to your actual SubAssembly model field
                genTlFunctionallocn.setFnlnDescription(obj.getSbamName());
                genTlFunctionallocn.setFnlnElementtype("S"); // whatever single-char code represents "SubAssembly" in your convention
                genTlFunctionallocn.setFnlnActive("Y");
                sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(
                        genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
            }
        }

        System.out.println("SubAssembly Id : " + obj.getSbamKeyid());
        sqls.add(BAL_GenTlSubAssemblymstSql.getInsertSql(sbamSql.getSbamDbFields(), obj.getSaveArray()));
        dbActionTemplate.executeStatements(sqls);
        return obj;
    }
    // end 

    
//    @Override
//    public BAL_GenTlSubAssemblymst update(BAL_GenTlSubAssemblymst obj) throws Exception {
//        obj.setSbamCode(obj.getSbamKeyid());
//        List<String> sqls = new ArrayList<>();
//        sqls.add(buildUpdate(obj));
//        db.executeStatements(sqls);
//        return obj;
//    }
    
    //commented and added by priyanka on 09/07/2026
	/*
	 * @Override public BAL_GenTlSubAssemblymst update(BAL_GenTlSubAssemblymst obj)
	 * throws Exception {
	 * 
	 * BAL_GenTlSubAssemblymstSql sbamSql = new BAL_GenTlSubAssemblymstSql();
	 * List<String> sqls = new ArrayList<>();
	 * 
	 * obj.setSbamCode(obj.getSbamKeyid());
	 * 
	 * sqls.add(BAL_GenTlSubAssemblymstSql.getUpdateSql( sbamSql.getSbamDbFields(),
	 * obj.getSaveArray())); dbActionTemplate.executeStatements(sqls); return obj; }
	 */
    
    @Override
    public BAL_GenTlSubAssemblymst update(BAL_GenTlSubAssemblymst obj, GenTlFunctionallocn genTlFunctionallocn) throws Exception {

        BAL_GenTlSubAssemblymstSql sbamSql = new BAL_GenTlSubAssemblymstSql();
        List<String> sqls = new ArrayList<>();

        obj.setSbamCode(obj.getSbamKeyid());

        if (genTlFunctionallocn != null) {
            String elementId = dbActionTemplate.getSingleValue(
                    TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN,
                    "FNLN_ELEMENTID", "FNLN_ORIGINALID",
                    genTlFunctionallocn.getFnlnOriginalid());
            System.out.println("Element Id : " + elementId);
            if (UIUtils.isValidKeyId(elementId)) {
                GenTlFunctionallocnSql genTlFunctionallocnSql = new GenTlFunctionallocnSql();

                genTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_BAL_GEN_TL_FUNCTIONALLOCN));
                genTlFunctionallocn.setFnlnOriginalid(obj.getSbamKeyid());
                genTlFunctionallocn.setFnlnElementid(elementId + "-" + obj.getSbamKeyid());
                genTlFunctionallocn.setFnlnParentid(elementId);
                //genTlFunctionallocn.setFnlnDisplaycode(obj.getSbamCode());
                genTlFunctionallocn.setFnlnDisplaycode(obj.getSbamName());
                //genTlFunctionallocn.setFnlnDescription(obj.getSbamDescription()); // adjust to actual getter
                genTlFunctionallocn.setFnlnDescription(obj.getSbamName());
                genTlFunctionallocn.setFnlnElementtype("S"); // your SubAssembly type code
                genTlFunctionallocn.setFnlnActive("Y");
                //sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
                sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), genTlFunctionallocn.getSaveArray()));
            }
        }

        sqls.add(BAL_GenTlSubAssemblymstSql.getUpdateSql(sbamSql.getSbamDbFields(), obj.getSaveArray()));
        dbActionTemplate.executeStatements(sqls);
        return obj;
    }
    // end 

	/*
	 * @Override public BAL_GenTlSubAssemblymst delete(String delemode,
	 * BAL_GenTlSubAssemblymst obj) throws Exception {
	 * 
	 * BAL_GenTlSubAssemblymstSql sbamSql = new BAL_GenTlSubAssemblymstSql();
	 * List<String> sqls = new ArrayList<>();
	 * 
	 * sqls.add(BAL_GenTlSubAssemblymstSql.getDeleteSql( delemode,
	 * sbamSql.getSbamDbFields(), obj.getSaveArray()));
	 * 
	 * dbActionTemplate.executeStatements(sqls); return obj; }
	 */
    
    @Override
    public BAL_GenTlSubAssemblymst delete(String delemode, BAL_GenTlSubAssemblymst obj) throws Exception {

        List<String> sqls = new ArrayList<String>();
        BAL_GenTlSubAssemblymstSql sbamSql = new BAL_GenTlSubAssemblymstSql();
        String originalId = obj.getSbamKeyid();

        if (!delemode.equals("I"))
            functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);

        try {
            sqls.add(BAL_GenTlSubAssemblymstSql.getDeleteSql(
                    delemode, sbamSql.getSbamDbFields(), obj.getSaveArray()));

            String inactFun = dbActionTemplate.getSingleValue(
                    " SELECT count (*) from " + TBL_GEN_TL_FUNCTIONALLOCN +
                    " where FNLN_ORIGINALID = '" + originalId + "' ");

            if (inactFun.length() > 0) {
                sqls.add(" " + " UPDATE " + TBL_GEN_TL_FUNCTIONALLOCN + " SET " + " FNLN_ACTIVE " +
                        " = 'N'" + " where FNLN_ORIGINALID = '" + originalId + "' ");
            }

            dbActionTemplate.executeStatements(sqls);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return obj;
    }
    // end 

    @Override
    public BAL_GenTlSubAssemblymst select(String keyId) throws Exception {
        BAL_GenTlSubAssemblymst genTlSubAssemblymst = new BAL_GenTlSubAssemblymst();
        String sql = BAL_GenTlSubAssemblymstSql.getSubAssemblymstSql();

        Object args[] = new Object[]{ keyId };
        genTlSubAssemblymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
        return genTlSubAssemblymst;
    }
    
    @Override
    public String getMachineIdByAssembly(String keyId) throws Exception {
        String sql = BAL_GenTlSubAssemblymstSql.getMachineIdByAssemblySql();

        Object args[] = new Object[] { keyId };

        Object[] data = dbActionTemplate.getDataArr(sql, args);
        
        System.out.println("data class = " + data.getClass());
        for (Object obj : data) {
            System.out.println(obj.getClass());
            System.out.println(obj);
        }

        if (data != null && data.length > 0) {
            return data[0].toString();
        }

        return "";
    }

    @Override
    public List<String[]> getGenTlSubAssemblymst(CommonFilter commonFilter) {
        return null; // implement if grid listing is needed
    }

//    // --- SQL builders (follow same pattern as BAL_GenTlAssemblymstSql) ---
//    private String buildInsert(BAL_GenTlSubAssemblymst o) {
//        return "INSERT INTO " + TBL +
//               " (sbam_keyid, sbam_assemblyid, sbam_code, sbam_name," +
//               "  sbam_description, sbam_remarks, sbam_active," +
//               "  sbam_createdby, sbam_createdon, sbam_modifiedon)" +
//               " VALUES (" +
//               q(o.getSbamKeyid()) + "," + q(o.getSbamAssemblyid()) + "," +
//               q(o.getSbamCode())  + "," + q(o.getSbamName())       + "," +
//               q(o.getSbamDescription()) + "," + q(o.getSbamRemarks()) + "," +
//               q(o.getSbamActive()) + "," + q(o.getSbamCreatedby()) + "," +
//               q(o.getSbamCreatedon()) + "," + q(o.getSbamModifiedon()) + ")";
//    }
    
    private String buildInsert(BAL_GenTlSubAssemblymst o) {
        return "insert into bal_gen_tl_subassemblymst" +
               "(sbam_keyid, sbam_assemblyid, sbam_code, sbam_name," +
               " sbam_description, sbam_remarks, sbam_active," +
               " sbam_createdby, sbam_createdon, sbam_modifiedon)" +
               " values(" +
               q(o.getSbamKeyid())      + "," +
               q(o.getSbamAssemblyid()) + "," +
               q(o.getSbamCode())       + "," +
               q(o.getSbamName())       + "," +
               q(o.getSbamDescription())+ "," +
               q(o.getSbamRemarks())    + "," +
               q(o.getSbamActive())     + "," +
               q(o.getSbamCreatedby())  + "," +
               // ← date format matching Assembly pattern
               " to_date(" + q(o.getSbamCreatedon())  + ",'dd-Mon-yyyy hh24:mi:ss')," +
               " to_date(" + q(o.getSbamModifiedon()) + ",'dd-Mon-yyyy hh24:mi:ss'))";
    }

//    private String buildUpdate(BAL_GenTlSubAssemblymst o) {
//        return "UPDATE " + TBL + " SET" +
//               "  sbam_assemblyid = " + q(o.getSbamAssemblyid()) + "," +
//               "  sbam_code = "       + q(o.getSbamCode())       + "," +
//               "  sbam_name = "       + q(o.getSbamName())       + "," +
//               "  sbam_description = "+ q(o.getSbamDescription())+ "," +
//               "  sbam_remarks = "    + q(o.getSbamRemarks())    + "," +
//               "  sbam_active = "     + q(o.getSbamActive())     + "," +
//               "  sbam_modifiedon = " + q(o.getSbamModifiedon()) +
//               " WHERE sbam_keyid = " + q(o.getSbamKeyid());
//    }
    
    private String buildUpdate(BAL_GenTlSubAssemblymst o) {
        return "update bal_gen_tl_subassemblymst set" +
               " sbam_assemblyid = " + q(o.getSbamAssemblyid()) + "," +
               " sbam_code = "       + q(o.getSbamCode())       + "," +
               " sbam_name = "       + q(o.getSbamName())       + "," +
               " sbam_description = "+ q(o.getSbamDescription())+ "," +
               " sbam_remarks = "    + q(o.getSbamRemarks())    + "," +
               " sbam_active = "     + q(o.getSbamActive())     + "," +
               " sbam_modifiedon = to_date(" + q(o.getSbamModifiedon()) + ",'dd-Mon-yyyy hh24:mi:ss')" +
               " where sbam_keyid = "+ q(o.getSbamKeyid());
    }

    /** Wrap value in single quotes; null → '{}' (matches assembly pattern) */
    private String q(String v) {
        return "'" + (v != null ? v.replace("'", "''") : "{}") + "'";
    }
    
    public void BAL_GenTlSubAssemblymstDaoImplJwt(String jwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(jwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
    
    @Override
    public List<String[]> getSubAssemblyList(CommonFilter commonFilter) throws Exception {
        try {
            

            List<String> paramValues = new ArrayList<>();

            // Pass assemblyId as condParms filter (mirrors MACHINEID in Assembly)
            String condParms = "";
            if (CommonFunctions.isValidKeyId(commonFilter.getChkAssm())) {
                condParms = "ASSEMBLYID=" + commonFilter.getChkAssm() + ";";
            }
            String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

            CommonMessage.debugMsg("getSubAssemblyList condParms=[" + condParms + "]");

            paramValues.add(condParms);
            paramValues.add(commonParams);

            List<String[]> dataList = fnCallApi.callFunction(
                    "BAL_GEN_FN_SUBASSEMBLYMST", paramValues, 3, true);

            System.out.println("paramValues after function call = " + paramValues);

            if (commonFilter.getViewClick() == 'Y') {
                String totalCnt = paramValues.get(0);
                boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
                if (isInteger) {
                    commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
                }
            }

            return dataList;

        } catch (Exception e) {
            CommonMessage.debugMsg("getSubAssemblyList error: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    
}