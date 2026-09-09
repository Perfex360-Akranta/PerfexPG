package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlSparesmstSql {

	public static final String TBL_GEN_TL_SPARESMST = "GEN_TL_SPARESMST";  

	TableFieldType [] sprmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, partno, partname, factoryid, source, type, ischangepart
		, ismachinespecific, storeslocationref, criticalityid, classificationid
		, categoryid, subcategoryid, abcclass, supplierpartno, uomid
		, make, model, specification, shelflifeitem, shelflifemonths
		, leadtimeinternal, leadtimeexternal, maxinventorylevel, reorderlevel
		, reorderqty, prefsupplier1, prefsupplier2, prefsupplier3, equipmentgroup
		, standardrate, isdirectentry, drawingno, erpname, erpcode, shelflifeunit
		, tempfield2, tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSprmDbFields() {
		return sprmDbFields;
	}

	public GenTlSparesmstSql()
	{
		sprmDbFields = new TableFieldType[ 42 ];
		for(int i = 0;i < 42; i++)
		{	
			sprmDbFields[ i ] = new TableFieldType();
		}
		sprmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SPRM_KEYID";
		sprmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.partno.ordinal() ].fieldName = "SPRM_PARTNO";
		sprmDbFields[ tableFldConstants.partno.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.partname.ordinal() ].fieldName = "SPRM_PARTNAME";
		sprmDbFields[ tableFldConstants.partname.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "SPRM_FACTORYID";
		sprmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.source.ordinal() ].fieldName = "SPRM_SOURCE";
		sprmDbFields[ tableFldConstants.source.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SPRM_TYPE";
		sprmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.ischangepart.ordinal() ].fieldName = "SPRM_ISCHANGEPART";
		sprmDbFields[ tableFldConstants.ischangepart.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.ismachinespecific.ordinal() ].fieldName = "SPRM_ISMACHINESPECIFIC";
		sprmDbFields[ tableFldConstants.ismachinespecific.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.storeslocationref.ordinal() ].fieldName = "SPRM_STORESLOCATIONREF";
		sprmDbFields[ tableFldConstants.storeslocationref.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.criticalityid.ordinal() ].fieldName = "SPRM_CRITICALITYID";
		sprmDbFields[ tableFldConstants.criticalityid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.classificationid.ordinal() ].fieldName = "SPRM_CLASSIFICATIONID";
		sprmDbFields[ tableFldConstants.classificationid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.categoryid.ordinal() ].fieldName = "SPRM_CATEGORYID";
		sprmDbFields[ tableFldConstants.categoryid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.subcategoryid.ordinal() ].fieldName = "SPRM_SUBCATEGORYID";
		sprmDbFields[ tableFldConstants.subcategoryid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.abcclass.ordinal() ].fieldName = "SPRM_ABCCLASS";
		sprmDbFields[ tableFldConstants.abcclass.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.supplierpartno.ordinal() ].fieldName = "SPRM_SUPPLIERPARTNO";
		sprmDbFields[ tableFldConstants.supplierpartno.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.uomid.ordinal() ].fieldName = "SPRM_UOMID";
		sprmDbFields[ tableFldConstants.uomid.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.make.ordinal() ].fieldName = "SPRM_MAKE";
		sprmDbFields[ tableFldConstants.make.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.model.ordinal() ].fieldName = "SPRM_MODEL";
		sprmDbFields[ tableFldConstants.model.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.specification.ordinal() ].fieldName = "SPRM_SPECIFICATION";
		sprmDbFields[ tableFldConstants.specification.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.shelflifeitem.ordinal() ].fieldName = "SPRM_SHELFLIFEITEM";
		sprmDbFields[ tableFldConstants.shelflifeitem.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.shelflifemonths.ordinal() ].fieldName = "SPRM_SHELFLIFEMONTHS";
		sprmDbFields[ tableFldConstants.shelflifemonths.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.leadtimeinternal.ordinal() ].fieldName = "SPRM_LEADTIMEINTERNAL";
		sprmDbFields[ tableFldConstants.leadtimeinternal.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.leadtimeexternal.ordinal() ].fieldName = "SPRM_LEADTIMEEXTERNAL";
		sprmDbFields[ tableFldConstants.leadtimeexternal.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.maxinventorylevel.ordinal() ].fieldName = "SPRM_MAXINVENTORYLEVEL";
		sprmDbFields[ tableFldConstants.maxinventorylevel.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.reorderlevel.ordinal() ].fieldName = "SPRM_REORDERLEVEL";
		sprmDbFields[ tableFldConstants.reorderlevel.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.reorderqty.ordinal() ].fieldName = "SPRM_REORDERQTY";
		sprmDbFields[ tableFldConstants.reorderqty.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.prefsupplier1.ordinal() ].fieldName = "SPRM_PREFSUPPLIER1";
		sprmDbFields[ tableFldConstants.prefsupplier1.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.prefsupplier2.ordinal() ].fieldName = "SPRM_PREFSUPPLIER2";
		sprmDbFields[ tableFldConstants.prefsupplier2.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.prefsupplier3.ordinal() ].fieldName = "SPRM_PREFSUPPLIER3";
		sprmDbFields[ tableFldConstants.prefsupplier3.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.equipmentgroup.ordinal() ].fieldName = "SPRM_EQUIPMENTGROUP";
		sprmDbFields[ tableFldConstants.equipmentgroup.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.standardrate.ordinal() ].fieldName = "SPRM_STANDARDRATE";
		sprmDbFields[ tableFldConstants.standardrate.ordinal() ].fieldType = 'N';

		sprmDbFields[ tableFldConstants.isdirectentry.ordinal() ].fieldName = "SPRM_ISDIRECTENTRY";
		sprmDbFields[ tableFldConstants.isdirectentry.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.drawingno.ordinal() ].fieldName = "SPRM_DRAWINGNO";
		sprmDbFields[ tableFldConstants.drawingno.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.erpname.ordinal() ].fieldName = "SPRM_ERPNAME";
		sprmDbFields[ tableFldConstants.erpname.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.erpcode.ordinal() ].fieldName = "SPRM_ERPCODE";
		sprmDbFields[ tableFldConstants.erpcode.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.shelflifeunit.ordinal() ].fieldName = "SPRM_SHELFLIFEUNIT";
		sprmDbFields[ tableFldConstants.shelflifeunit.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SPRM_TEMPFIELD2";
		sprmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SPRM_TEMPFIELD3";
		sprmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SPRM_ACTIVE";
		sprmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sprmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SPRM_CREATEDBY";
		sprmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sprmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SPRM_CREATEDON";
		sprmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sprmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SPRM_MODIFIEDON";
		sprmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg("insert sql");
		return SqlUtils.getInsertSql(TBL_GEN_TL_SPARESMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SPARESMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		CommonMessage.debugMsg("Update SQL : "+sql);
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SPARESMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		CommonMessage.debugMsg("Delete SQL : "+sql);
		return sql;
	}
	public static String selectSql(CommonFilter commonFilter)
	{
		//CommonFilter commonFilter = new CommonFilter();
		//return "SELECT * from " + TBL_GEN_TL_SPARESMST;
		CommonMessage.debugMsg("commonFilter.getFromRow()"+commonFilter.getFromRow());
		CommonMessage.debugMsg("commonFilter.getToRow()"+commonFilter.getToRow());
		StringBuffer sql = new StringBuffer();
		sql.append(" Select * from (Select ROWNUM RN, A.* from " );
		sql.append(	"( select sprm_keyid ,SPRM_PARTNO as PARTNO , SPRM_PARTNAME as PARTNAME, DECODE(TRIM(SPRM_SOURCE),'L','LOCAL','I','IMPORT',SPRM_SOURCE) as source,CLSM_NAME AS CLASSIFCN,CRTM_NAME AS CRITICALITY,CATM_NAME AS CATEGORY,SBCM_NAME AS SUBCATEGORY, "); 
		sql.append(" SPRM_MAKE AS MAKE,SPRM_MODEL AS MODEL,SPRM_SPECIFICATION AS SPEC,SPRM_ABCCLASS AS ABCCLASS,");
		sql.append(" SPRM_SHELFLIFEITEM as SLI,SPRM_SHELFLIFEMONTHS as SLM,SPRM_REORDERLEVEL as REORDERLEVEL,SPRM_REORDERQTY as REORDERQTY,UOMM_CODE as UOM ");
		sql.append(" from gen_tl_sparesmst,GEN_TL_CATEGORYMST,GEN_TL_CRITICALITYMST, ");
		sql.append(" GEN_TL_CLASSIFICATIONMST,GEN_TL_SUBCATEGORYMST,ADM_TL_UOMMST "); 
		sql.append(" where SPRM_CRITICALITYID = CRTM_KEYID(+) ");
		sql.append(" AND SPRM_CATEGORYID=CATM_KEYID(+) ");
		sql.append(" AND SPRM_SUBCATEGORYID=SBCM_KEYID(+) ");
		sql.append(" AND SPRM_CLASSIFICATIONID=CLSM_KEYID(+) ");
		sql.append(" AND SPRM_UOMID = UOMM_KEYID(+)) A ) where 1=1 " );
		
		if(CommonFunctions.isValidKeyId(commonFilter.getFromRow()) && CommonFunctions.isValidKeyId(commonFilter.getToRow())){
			sql.append( " AND  RN >= "+commonFilter.getFromRow() + "  and RN <= "+commonFilter.getToRow());
		}
		sql.append( FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
		
		return sql.toString(); 
	}
	public static String TotalSql(){
		StringBuffer sql = new StringBuffer();
		sql.append("Select count (*) from( select sprm_keyid ,SPRM_PARTNO, SPRM_PARTNAME, DECODE(TRIM(SPRM_SOURCE),'L','LOCAL','I','IMPORT',SPRM_SOURCE) as source,CLSM_NAME,CRTM_NAME,CATM_NAME,SBCM_NAME, "); 
				sql.append(" SPRM_MAKE,SPRM_MODEL,SPRM_SPECIFICATION,SPRM_ABCCLASS,");
				sql.append(" SPRM_SHELFLIFEITEM,SPRM_SHELFLIFEMONTHS,SPRM_REORDERLEVEL,SPRM_REORDERQTY,UOMM_CODE ");
				sql.append(" from gen_tl_sparesmst,GEN_TL_CATEGORYMST,GEN_TL_CRITICALITYMST, ");
				sql.append(" GEN_TL_CLASSIFICATIONMST,GEN_TL_SUBCATEGORYMST,ADM_TL_UOMMST "); 
				sql.append(" where SPRM_CRITICALITYID = CRTM_KEYID(+) ");
				sql.append(" AND SPRM_CATEGORYID=CATM_KEYID(+) ");
				sql.append(" AND SPRM_SUBCATEGORYID=SBCM_KEYID(+) ");
				sql.append(" AND SPRM_CLASSIFICATIONID=CLSM_KEYID(+) ");
				sql.append(" AND SPRM_UOMID = UOMM_KEYID(+))");
				CommonMessage.debugMsg("sql"+sql);
				return sql.toString(); 
	}
	public static String selectSql2()
	{
		return "SELECT * from " + TBL_GEN_TL_SPARESMST + " where SPRM_KEYID= ?";
	}
	
	public static String selectSql3()
	{
		return "SELECT * from " + TBL_GEN_TL_SPARESMST + " where SPRM_PARTNO= ?";
	}
	
	public static String getSpareMstSql(String partId)
	{
		return "SELECT * from " + TBL_GEN_TL_SPARESMST + " where SPRM_KEYID= '"+partId+"'";
	}
	
	public static String getSparesMstSql(String partNo)
	{
		return " SELECT * from " + TBL_GEN_TL_SPARESMST + " where SPRM_PARTNO= "+partNo;
	}

	

}

