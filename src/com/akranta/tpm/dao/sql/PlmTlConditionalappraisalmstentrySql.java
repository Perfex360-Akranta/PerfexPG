package com.akranta.tpm.dao.sql;

public class PlmTlConditionalappraisalmstentrySql {

	public static final String TBL_PLM_TL_CONDITIONALAPPRAISALMSTENTRY = "PLM_TL_CONAPPRAISALMSTENTRY";  

	TableFieldType [] cdamDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCdamDbFields() {
		return cdamDbFields;
	}

	public PlmTlConditionalappraisalmstentrySql()
	{
		cdamDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			cdamDbFields[ i ] = new TableFieldType();
		}
		cdamDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CDAM_KEYID";
		cdamDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CDAM_FLID";
		cdamDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CDAM_ELEMENTID";
		cdamDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.date.ordinal() ].fieldName = "CDAM_DATE";
		cdamDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		cdamDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CDAM_TEMPFIELD1";
		cdamDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CDAM_TEMPFIELD2";
		cdamDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CDAM_TEMPFIELD3";
		cdamDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CDAM_TEMPFIELD4";
		cdamDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CDAM_TEMPFIELD5";
		cdamDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CDAM_ACTIVE";
		cdamDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cdamDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CDAM_CREATEDBY";
		cdamDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cdamDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CDAM_CREATEDON";
		cdamDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cdamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CDAM_MODIFIEDON";
		cdamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_CONDITIONALAPPRAISALMSTENTRY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_PLM_TL_CONDITIONALAPPRAISALMSTENTRY, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_PLM_TL_CONDITIONALAPPRAISALMSTENTRY );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

	public String getDeleteDetailSql(String cdamKeyid) {
		String sql=" DELETE FROM PLM_TL_CONAPPRAISALMSTENTRY WHERE CDAP_CDAM_KEYID='"+cdamKeyid+"'";
		return sql;
	}

}

