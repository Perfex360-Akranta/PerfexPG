package com.akranta.tpm.dao.sql;

public class GenTlFilemanagerSql {

	public static final String TBL_GEN_TL_FILEMANAGER = "GEN_TL_FILEMANAGER";  

	TableFieldType [] flmnDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refdocno, refdoctype, doctype, slno, filename, description
		, bloblength, fileblob, temp1, temp2, temp3, temp4, temp5, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFlmnDbFields() {
		return flmnDbFields;
	}

	public GenTlFilemanagerSql()
	{
		flmnDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			flmnDbFields[ i ] = new TableFieldType();
		}
		flmnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FLMN_KEYID";
		flmnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "FLMN_REFDOCNO";
		flmnDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "FLMN_REFDOCTYPE";
		flmnDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "FLMN_DOCTYPE";
		flmnDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "FLMN_SLNO";
		flmnDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'N';

		flmnDbFields[ tableFldConstants.filename.ordinal() ].fieldName = "FLMN_FILENAME";
		flmnDbFields[ tableFldConstants.filename.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.description.ordinal() ].fieldName = "FLMN_DESCRIPTION";
		flmnDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.bloblength.ordinal() ].fieldName = "FLMN_BLOBLENGTH";
		flmnDbFields[ tableFldConstants.bloblength.ordinal() ].fieldType = 'N';

		flmnDbFields[ tableFldConstants.fileblob.ordinal() ].fieldName = "FLMN_FILEBLOB";
		flmnDbFields[ tableFldConstants.fileblob.ordinal() ].fieldType = 'B';

		flmnDbFields[ tableFldConstants.temp1.ordinal() ].fieldName = "FLMN_TEMP1";
		flmnDbFields[ tableFldConstants.temp1.ordinal() ].fieldType = 'C';

		flmnDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "FLMN_TEMP2";
		flmnDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'C';

		flmnDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "FLMN_TEMP3";
		flmnDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'C';

		flmnDbFields[ tableFldConstants.temp4.ordinal() ].fieldName = "FLMN_TEMP4";
		flmnDbFields[ tableFldConstants.temp4.ordinal() ].fieldType = 'C';

		flmnDbFields[ tableFldConstants.temp5.ordinal() ].fieldName = "FLMN_TEMP5";
		flmnDbFields[ tableFldConstants.temp5.ordinal() ].fieldType = 'C';

		flmnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FLMN_ACTIVE";
		flmnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		flmnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FLMN_CREATEDBY";
		flmnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		flmnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FLMN_CREATEDON";
		flmnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		flmnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FLMN_MODIFIEDON";
		flmnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_FILEMANAGER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FILEMANAGER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FILEMANAGER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getreloadgridSql(String documentNo, String documentType)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select *  from ( SELECT FLMN_KEYID  , FLMN_FILENAME ,  DECODE(FLMN_DOCTYPE,'OTH','OTHER','BLK','BLOCK DIAGRAM','DPC','DOCUMENT TYPE','IMG','IMAGE','MAP','MAP','VID','VIDEO','MNU','MANUAL','SPC','SPECIFICATION', FLMN_DOCTYPE) AS FLMN_DOCTYPE ,FLMN_DESCRIPTION , ");
		sb.append(" FLMN_CREATEDON  , FLMN_MODIFIEDON  ");
		sb.append( " from " + TBL_GEN_TL_FILEMANAGER );
		sb.append(" where " + " FLMN_REFDOCNO  = '" + documentNo + "'");
		sb.append(" AND FLMN_REFDOCTYPE   = '" + documentType + "'  order by FLMN_MODIFIEDON desc) ");
	 
		return sb.toString();
	}

	public static String getgridcountSql(String documentNo, String getCount) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		/*sb.append("select count(*)  from ( SELECT FLMN_KEYID  , FLMN_FILENAME ,  DECODE(FLMN_DOCTYPE,'OTH','OTHER','BLK','BLOCK DIAGRAM','DPC','DOCUMENT TYPE','IMG','IMAGE','MAP','MAP','VID','VIDEO','MNU','MANUAL','SPC','SPECIFICATION', FLMN_DOCTYPE) AS FLMN_DOCTYPE ,FLMN_DESCRIPTION , ");
		sb.append(" FLMN_CREATEDON  , FLMN_MODIFIEDON  ");
		sb.append( " from " + TBL_GEN_TL_FILEMANAGER );
		sb.append(" where " + " FLMN_REFDOCNO  = '" + documentNo + "'");
		sb.append(" AND FLMN_REFDOCTYPE   = '" + getCount + "'  order by FLMN_MODIFIEDON desc) ");
	 */
		
		sb.append("select count(*)  from ( SELECT * ");
		sb.append( " from DCM_TL_DOCUMENTMANAGER ");
		sb.append(" where " + " DMDM_REFDOCNO  = '" + documentNo + "'");
		sb.append(" AND DMDM_REFDOCTYPE   = '" + getCount + "' ) ");
	 
		return sb.toString();
	}
}

