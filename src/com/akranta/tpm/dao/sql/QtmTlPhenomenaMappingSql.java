package com.akranta.tpm.dao.sql;

/**
 * SQL constants and helpers for QTM_TL_PHENOMENA_MAPPING table.
 *
 * Column order MUST match QtmTlPhenomenaMapping.tableFldConstants ordinal:
 *   0  phnm_keyid
 *   1  phnm_qphm_keyid
 *   2  phnm_sect_flid
 *   3  phnm_phenomena
 *   4  phnm_tempfield1
 *   5  phnm_tempfield2
 *   6  phnm_tempfield3
 *   7  phnm_active
 *   8  phnm_createdby
 *   9  phnm_createdon
 *  10  phnm_modifiedon
 */
public class QtmTlPhenomenaMappingSql {

    public static final String TBL_QTM_TL_PHENOMENA_MAPPING = "QTM_TL_PHENOMENA_MAPPING";

    // ---------------------------------------------------------------- fields
    /** Returns DB column names in saveArray ordinal order. */
    public String[] getPhnmDbFields() {
        return new String[]{
            "phnm_keyid",       // 0
            "phnm_qphm_keyid",  // 1
            "phnm_sect_flid",   // 2
            "phnm_phenomena",   // 3
            "phnm_tempfield1",  // 4
            "phnm_tempfield2",  // 5
            "phnm_tempfield3",  // 6
            "phnm_active",      // 7
            "phnm_createdby",   // 8
            "phnm_createdon",   // 9
            "phnm_modifiedon"   // 10
        };
    }

    // --------------------------------------------------------------- select
    public static String getSelectSql() {
        return "SELECT phnm_keyid, phnm_qphm_keyid, phnm_sect_flid, phnm_phenomena, "
             + "phnm_tempfield1, phnm_tempfield2, phnm_tempfield3, phnm_active, "
             + "phnm_createdby, phnm_createdon, phnm_modifiedon "
             + "FROM QTM_TL_PHENOMENA_MAPPING "
             + "WHERE phnm_keyid = ?";
    }

    // --------------------------------------------------------------- insert
    public static String getInsertSql(String[] fields, Object[] values) {
        StringBuilder cols = new StringBuilder(
                "INSERT INTO QTM_TL_PHENOMENA_MAPPING (");
        StringBuilder vals = new StringBuilder(") VALUES (");

        for (int i = 0; i < fields.length; i++) {
            if (i > 0) {
                cols.append(", ");
                vals.append(", ");
            }
            cols.append(fields[i]);
            Object v = values[i];
            if (v == null) {
                vals.append("NULL");
            } else {
                String s = v.toString();
                vals.append("'").append(s.replace("'", "''")).append("'");
            }
        }
        cols.append(vals).append(")");
        return cols.toString();
    }
    // --------------------------------------------------------------- update
    public static String getUpdateSql(String[] fields, Object[] values) {
        StringBuilder sql = new StringBuilder(
                "UPDATE QTM_TL_PHENOMENA_MAPPING SET ");

        boolean first = true;

        for (int i = 1; i < fields.length; i++) { // skip index 0 (PK)

            if (!first) {
                sql.append(", ");
            }

            sql.append(fields[i]).append(" = ");

            Object v = values[i];

            if (v == null) {
                sql.append("NULL");
            } else {
                String s = v.toString().replace("'", "''");
                sql.append("'").append(s).append("'");
            }

            first = false;
        }

        sql.append(" WHERE phnm_keyid = '")
           .append(values[0].toString().replace("'", "''"))
           .append("'");

        return sql.toString();
    }
}