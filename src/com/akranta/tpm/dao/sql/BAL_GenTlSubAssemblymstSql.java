package com.akranta.tpm.dao.sql;

public class BAL_GenTlSubAssemblymstSql {

    public static final String TBL_BAL_GEN_TL_SUBASSEMBLYMST = "BAL_GEN_TL_SUBASSEMBLYMST";

    TableFieldType[] sbamDbFields = null;

    // Must match BAL_GenTlSubAssemblymst.tableFldConstants enum order exactly:
    // keyid, assemblyid, code, name, description, remarks, active, createdby, createdon, modifiedon
    public enum tableFldConstants {
        keyid, assemblyid, code, name, description, remarks, active, createdby, createdon, modifiedon
    }

    public TableFieldType[] getSbamDbFields() {
        return sbamDbFields;
    }

    public BAL_GenTlSubAssemblymstSql() {
        sbamDbFields = new TableFieldType[10];
        for (int i = 0; i < 10; i++) {
            sbamDbFields[i] = new TableFieldType();
        }

        sbamDbFields[tableFldConstants.keyid.ordinal()].fieldName      = "SBAM_KEYID";
        sbamDbFields[tableFldConstants.keyid.ordinal()].fieldType       = 'V';

        sbamDbFields[tableFldConstants.assemblyid.ordinal()].fieldName  = "SBAM_ASSEMBLYID";
        sbamDbFields[tableFldConstants.assemblyid.ordinal()].fieldType  = 'V';

        sbamDbFields[tableFldConstants.code.ordinal()].fieldName        = "SBAM_CODE";
        sbamDbFields[tableFldConstants.code.ordinal()].fieldType        = 'V';

        sbamDbFields[tableFldConstants.name.ordinal()].fieldName        = "SBAM_NAME";
        sbamDbFields[tableFldConstants.name.ordinal()].fieldType        = 'V';

        sbamDbFields[tableFldConstants.description.ordinal()].fieldName = "SBAM_DESCRIPTION";
        sbamDbFields[tableFldConstants.description.ordinal()].fieldType = 'V';

        sbamDbFields[tableFldConstants.remarks.ordinal()].fieldName     = "SBAM_REMARKS";
        sbamDbFields[tableFldConstants.remarks.ordinal()].fieldType     = 'V';

        sbamDbFields[tableFldConstants.active.ordinal()].fieldName      = "SBAM_ACTIVE";
        sbamDbFields[tableFldConstants.active.ordinal()].fieldType      = 'C';

        sbamDbFields[tableFldConstants.createdby.ordinal()].fieldName   = "SBAM_CREATEDBY";
        sbamDbFields[tableFldConstants.createdby.ordinal()].fieldType   = 'V';

        sbamDbFields[tableFldConstants.createdon.ordinal()].fieldName   = "SBAM_CREATEDON";
        sbamDbFields[tableFldConstants.createdon.ordinal()].fieldType   = 'D';

        sbamDbFields[tableFldConstants.modifiedon.ordinal()].fieldName  = "SBAM_MODIFIEDON";
        sbamDbFields[tableFldConstants.modifiedon.ordinal()].fieldType  = 'D';
    }

    public static String getInsertSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
        return SqlUtils.getInsertSql(TBL_BAL_GEN_TL_SUBASSEMBLYMST, fieldTypeArr, dataArray);
    }

    public static String getUpdateSql(TableFieldType[] fieldTypeArr, Object[] dataArray) {
        String sql = SqlUtils.getUpdateSql(TBL_BAL_GEN_TL_SUBASSEMBLYMST, fieldTypeArr, dataArray);
        sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
               " = '" + (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
        return sql;
    }

    public static String getDeleteSql(String delemode, TableFieldType[] fieldTypeArr, Object[] dataArray) {
        String sql = "";
        if (delemode.equals("I")) {
            sql  = "UPDATE " + TBL_BAL_GEN_TL_SUBASSEMBLYMST;
            sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName + " = 'N'";
            sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
                   " = '" + (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
        } else {
            sql  = "DELETE from " + TBL_BAL_GEN_TL_SUBASSEMBLYMST;
            sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
                   " = '" + (String) dataArray[tableFldConstants.keyid.ordinal()] + "'";
        }
        return sql;
    }

    public static String getSubAssemblymstSql() {
        return " SELECT * from " + TBL_BAL_GEN_TL_SUBASSEMBLYMST + " where SBAM_KEYID = ? ";
		/*
		 * return "SELECT sa.*, vw.machineid " + "FROM " + TBL_BAL_GEN_TL_SUBASSEMBLYMST
		 * + " sa " +
		 * "JOIN BAL_GEN_VW_MCHASMLINK vw ON sa.sbam_assemblyid = vw.assemblyid " +
		 * "WHERE sa.SBAM_KEYID = ?";
		 */
    }
    
    public static String getMachineIdByAssemblySql() {
        return "SELECT vw.machineid " +
               "FROM " + TBL_BAL_GEN_TL_SUBASSEMBLYMST + " sa " +
               "JOIN BAL_GEN_VW_MCHASMLINK vw " +
               "ON sa.sbam_assemblyid = vw.assemblyid " +
               "WHERE sa.SBAM_KEYID = ?";
    }
}