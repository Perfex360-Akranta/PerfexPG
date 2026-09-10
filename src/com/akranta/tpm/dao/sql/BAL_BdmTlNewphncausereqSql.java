package com.akranta.tpm.dao.sql;

public class BAL_BdmTlNewphncausereqSql {

	public static final String TBL_BDM_TL_NEWPHNCAUSEREQ = "BDM_TL_NEWPHNCAUSEREQ";  

	TableFieldType [] bnprDbFields = null;

	public enum   tableFldConstants
	{
		keyid, requesteddate, requestedby, docno, docdate, phenomenatype
		, proposedphn, iscausereq, proposedcause, approvedphn, iscausereqapp
		, approvedcause, isalreadyexist, oldphenomenaid, oldcauseid, newphenomenaid
		, newcauseid, approvedby, approveddate, approvedflag, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBnprDbFields() {
		return bnprDbFields;
	}

	public BAL_BdmTlNewphncausereqSql()
	{
		bnprDbFields = new TableFieldType[ 24 ];
		for(int i = 0;i < 24; i++)
		{	
			bnprDbFields[ i ] = new TableFieldType();
		}
		bnprDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BNPR_KEYID";
		bnprDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.requesteddate.ordinal() ].fieldName = "BNPR_REQUESTEDDATE";
		bnprDbFields[ tableFldConstants.requesteddate.ordinal() ].fieldType = 'D';

		bnprDbFields[ tableFldConstants.requestedby.ordinal() ].fieldName = "BNPR_REQUESTEDBY";
		bnprDbFields[ tableFldConstants.requestedby.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.docno.ordinal() ].fieldName = "BNPR_DOCNO";
		bnprDbFields[ tableFldConstants.docno.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.docdate.ordinal() ].fieldName = "BNPR_DOCDATE";
		bnprDbFields[ tableFldConstants.docdate.ordinal() ].fieldType = 'D';

		bnprDbFields[ tableFldConstants.phenomenatype.ordinal() ].fieldName = "BNPR_PHENOMENATYPE";
		bnprDbFields[ tableFldConstants.phenomenatype.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.proposedphn.ordinal() ].fieldName = "BNPR_PROPOSEDPHN";
		bnprDbFields[ tableFldConstants.proposedphn.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.iscausereq.ordinal() ].fieldName = "BNPR_ISCAUSEREQ";
		bnprDbFields[ tableFldConstants.iscausereq.ordinal() ].fieldType = 'C';

		bnprDbFields[ tableFldConstants.proposedcause.ordinal() ].fieldName = "BNPR_PROPOSEDCAUSE";
		bnprDbFields[ tableFldConstants.proposedcause.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.approvedphn.ordinal() ].fieldName = "BNPR_APPROVEDPHN";
		bnprDbFields[ tableFldConstants.approvedphn.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.iscausereqapp.ordinal() ].fieldName = "BNPR_ISCAUSEREQAPP";
		bnprDbFields[ tableFldConstants.iscausereqapp.ordinal() ].fieldType = 'C';

		bnprDbFields[ tableFldConstants.approvedcause.ordinal() ].fieldName = "BNPR_APPROVEDCAUSE";
		bnprDbFields[ tableFldConstants.approvedcause.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.isalreadyexist.ordinal() ].fieldName = "BNPR_ISALREADYEXIST";
		bnprDbFields[ tableFldConstants.isalreadyexist.ordinal() ].fieldType = 'C';

		bnprDbFields[ tableFldConstants.oldphenomenaid.ordinal() ].fieldName = "BNPR_OLDPHENOMENAID";
		bnprDbFields[ tableFldConstants.oldphenomenaid.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.oldcauseid.ordinal() ].fieldName = "BNPR_OLDCAUSEID";
		bnprDbFields[ tableFldConstants.oldcauseid.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.newphenomenaid.ordinal() ].fieldName = "BNPR_NEWPHENOMENAID";
		bnprDbFields[ tableFldConstants.newphenomenaid.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.newcauseid.ordinal() ].fieldName = "BNPR_NEWCAUSEID";
		bnprDbFields[ tableFldConstants.newcauseid.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "BNPR_APPROVEDBY";
		bnprDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "BNPR_APPROVEDDATE";
		bnprDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		bnprDbFields[ tableFldConstants.approvedflag.ordinal() ].fieldName = "BNPR_APPROVEDFLAG";
		bnprDbFields[ tableFldConstants.approvedflag.ordinal() ].fieldType = 'C';

		bnprDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BNPR_ACTIVE";
		bnprDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bnprDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BNPR_CREATEDBY";
		bnprDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bnprDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BNPR_CREATEDON";
		bnprDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bnprDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BNPR_MODIFIEDON";
		bnprDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_NEWPHNCAUSEREQ, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_NEWPHNCAUSEREQ, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_NEWPHNCAUSEREQ ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

