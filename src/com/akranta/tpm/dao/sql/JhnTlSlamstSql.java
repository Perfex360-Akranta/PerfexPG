package com.akranta.tpm.dao.sql;

public class JhnTlSlamstSql {

	public static final String TBL_JHN_TL_SLAMST = "JHN_TL_SLAMST";  

	TableFieldType [] slamDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, source, date, fromdptid, todptid, status 
		, approvedby, approveddate, remarks,  preparedby,prepareddate
		, frmdmtapproval, todmtapproval, todmtqmapproval, active, createdby
		, createdon, modifiedon
		
	}

	public TableFieldType[] getSlamDbFields() {
		return slamDbFields;
	}

	public JhnTlSlamstSql()
	{
		slamDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			slamDbFields[ i ] = new TableFieldType();
		}
		slamDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SLAM_KEYID";
		slamDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SLAM_FLID";
		slamDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "SLAM_ELEMENTID";
		slamDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.source.ordinal() ].fieldName = "SLAM_SOURCE";
		slamDbFields[ tableFldConstants.source.ordinal() ].fieldType = 'C';

		slamDbFields[ tableFldConstants.date.ordinal() ].fieldName = "SLAM_DATE";
		slamDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		slamDbFields[ tableFldConstants.fromdptid.ordinal() ].fieldName = "SLAM_FROMDPTID";
		slamDbFields[ tableFldConstants.fromdptid.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.todptid.ordinal() ].fieldName = "SLAM_TODPTID";
		slamDbFields[ tableFldConstants.todptid.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SLAM_STATUS";
		slamDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		slamDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "SLAM_APPROVEDBY";
		slamDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "SLAM_APPROVEDDATE";
		slamDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		slamDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SLAM_REMARKS";
		slamDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		slamDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "SLAM_PREPAREDBY";
		slamDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';
		
		slamDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "SLAM_PREPAREDDATE";
		slamDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'D';
		
		slamDbFields[ tableFldConstants.frmdmtapproval.ordinal() ].fieldName = "SLAM_FRMDMTAPPROVAL";
		slamDbFields[ tableFldConstants.frmdmtapproval.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.todmtapproval.ordinal() ].fieldName = "SLAM_TODMTAPPROVAL";
		slamDbFields[ tableFldConstants.todmtapproval.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.todmtqmapproval.ordinal() ].fieldName = "SLAM_TODMTQMAPPROVAL";
		slamDbFields[ tableFldConstants.todmtqmapproval.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SLAM_ACTIVE";
		slamDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		slamDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SLAM_CREATEDBY";
		slamDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		slamDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SLAM_CREATEDON";
		slamDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		slamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SLAM_MODIFIEDON";
		slamDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_JHN_TL_SLAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_JHN_TL_SLAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_JHN_TL_SLAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectmst(String keyid) {
		return "SELECT * from "+TBL_JHN_TL_SLAMST +" where SLAM_KEYID = ?";
	}

	public static String deletemst(String keyid) {
		return "DELETE from "+TBL_JHN_TL_SLAMST +" where SLAM_KEYID = ?";
	}

}

