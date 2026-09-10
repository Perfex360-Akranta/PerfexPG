package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_GenTlSectionmstSql.tableFldConstants;

public class BAL_GenTlCellmstSql {

	public static final String TBL_GEN_TL_CELLMST = "GEN_TL_CELLMST";  

	TableFieldType [] cellDbFields = null;

	public enum   tableFldConstants
	{
		keyid, companyid, factoryid, sectionid, sectiongroup, code, name
		, levelno, pcname, cellorder, effectivedate, inactivateddate
		, costcentreid, flid,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCellDbFields() {
		return cellDbFields;
	}

	public BAL_GenTlCellmstSql()
	{
		cellDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			cellDbFields[ i ] = new TableFieldType();
		}
		cellDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CELL_KEYID";
		cellDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "CELL_COMPANYID";
		cellDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "CELL_FACTORYID";
		cellDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "CELL_SECTIONID";
		cellDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.sectiongroup.ordinal() ].fieldName = "CELL_SECTIONGROUP";
		cellDbFields[ tableFldConstants.sectiongroup.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.code.ordinal() ].fieldName = "CELL_CODE";
		cellDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.name.ordinal() ].fieldName = "CELL_NAME";
		cellDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "CELL_LEVELNO";
		cellDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		cellDbFields[ tableFldConstants.pcname.ordinal() ].fieldName = "CELL_PCNAME";
		cellDbFields[ tableFldConstants.pcname.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.cellorder.ordinal() ].fieldName = "CELL_CELLORDER";
		cellDbFields[ tableFldConstants.cellorder.ordinal() ].fieldType = 'N';

		cellDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "CELL_EFFECTIVEDATE";
		cellDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		cellDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldName = "CELL_INACTIVATEDDATE";
		cellDbFields[ tableFldConstants.inactivateddate.ordinal() ].fieldType = 'D';

		cellDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldName = "CELL_COSTCENTREID";
		cellDbFields[ tableFldConstants.costcentreid.ordinal() ].fieldType = 'V';
		
		cellDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CELL_FLID";
		cellDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CELL_ACTIVE";
		cellDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cellDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CELL_CREATEDBY";
		cellDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cellDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CELL_CREATEDON";
		cellDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cellDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CELL_MODIFIEDON";
		cellDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_CELLMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_CELLMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String delemode,TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql ="";
		
		if (delemode.equals("I")) {  
			sql = "UPDATE " + TBL_GEN_TL_CELLMST ;		
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +" = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		else  {		
			sql = "DELETE from " + TBL_GEN_TL_CELLMST ;
		
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				   " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		return sql;
	}
	public static String getGenTlCellmstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_CELLMST + " where CELL_KEYID = ?  ";
	}
	
	public static String getCompanySql() {
		// TODO Auto-generated method stub
		return " SELECT fnln_parentid from " + TableNames.TBL_GEN_TL_FUNCTIONALLOCN+ " where fnln_elementid = ?  ";
	}

}

