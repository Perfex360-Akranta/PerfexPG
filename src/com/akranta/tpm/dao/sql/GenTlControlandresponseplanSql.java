package com.akranta.tpm.dao.sql;

public class GenTlControlandresponseplanSql {

	public static final String TBL_GEN_TL_CONTROLANDRESPONSEPLAN = "GEN_TL_CONTROLANDRESPONSEPLAN";  

	TableFieldType [] carpDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, processstep, kpov, frequency, whererecorded
		, controllimits, measurementmethod, whomeasures, decisionrule
		, uom, speclimits, samplesize, informto, correctiveaction, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getCarpDbFields() {
		return carpDbFields;
	}

	public GenTlControlandresponseplanSql()
	{
		carpDbFields = new TableFieldType[ 25 ];
		for(int i = 0;i < 25; i++)
		{	
			carpDbFields[ i ] = new TableFieldType();
		}
		carpDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CARP_KEYID";
		carpDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CARP_FLID";
		carpDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CARP_ELEMENTID";
		carpDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.processstep.ordinal() ].fieldName = "CARP_PROCESSSTEP";
		carpDbFields[ tableFldConstants.processstep.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.kpov.ordinal() ].fieldName = "CARP_KPOV";
		carpDbFields[ tableFldConstants.kpov.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "CARP_FREQUENCY";
		carpDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.whererecorded.ordinal() ].fieldName = "CARP_WHERERECORDED";
		carpDbFields[ tableFldConstants.whererecorded.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.controllimits.ordinal() ].fieldName = "CARP_CONTROLLIMITS";
		carpDbFields[ tableFldConstants.controllimits.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.measurementmethod.ordinal() ].fieldName = "CARP_MEASUREMENTMETHOD";
		carpDbFields[ tableFldConstants.measurementmethod.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.whomeasures.ordinal() ].fieldName = "CARP_WHOMEASURES";
		carpDbFields[ tableFldConstants.whomeasures.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.decisionrule.ordinal() ].fieldName = "CARP_DECISIONRULE";
		carpDbFields[ tableFldConstants.decisionrule.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "CARP_UOM";
		carpDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.speclimits.ordinal() ].fieldName = "CARP_SPECLIMITS";
		carpDbFields[ tableFldConstants.speclimits.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.samplesize.ordinal() ].fieldName = "CARP_SAMPLESIZE";
		carpDbFields[ tableFldConstants.samplesize.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.informto.ordinal() ].fieldName = "CARP_INFORMTO";
		carpDbFields[ tableFldConstants.informto.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "CARP_CORRECTIVEACTION";
		carpDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CARP_TEMPFIELD1";
		carpDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		carpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CARP_TEMPFIELD2";
		carpDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		carpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CARP_TEMPFIELD3";
		carpDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		carpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CARP_TEMPFIELD4";
		carpDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		carpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CARP_TEMPFIELD5";
		carpDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		carpDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CARP_ACTIVE";
		carpDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		carpDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CARP_CREATEDBY";
		carpDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		carpDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CARP_CREATEDON";
		carpDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		carpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CARP_MODIFIEDON";
		carpDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_CONTROLANDRESPONSEPLAN, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_CONTROLANDRESPONSEPLAN, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_CONTROLANDRESPONSEPLAN ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getunsafedata() {
		// TODO Auto-generated method stub
		String  sql= "select * from GEN_TL_CONTROLANDRESPONSEPLAN WHERE CARP_KEYID = ?";
		return sql;
	}

}

