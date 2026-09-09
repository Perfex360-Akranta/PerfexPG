package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class QtmTlComplaintgallerySql {

	public static final String TBL_QTM_TL_COMPLAINTGALLERY = "QTM_TL_COMPLAINTGALLERY";  

	TableFieldType [] cmgaDbFields = null;

	public enum   tableFldConstants
	{
		keyid,customerid, gradeproduct, correctiveaction, preventiveaction
		, complaintdescription, complaintdate, manufacturedate, gradespecification
		, flid, elementid, source , defectid , defectqty ,tempfield1, tempfield2,tempfield3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getCmgaDbFields() {
		return cmgaDbFields;
	}

	public QtmTlComplaintgallerySql()
	{
		cmgaDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			cmgaDbFields[ i ] = new TableFieldType();
		}
		
		cmgaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CMGA_KEYID";
		cmgaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		cmgaDbFields[ tableFldConstants.customerid.ordinal() ].fieldName = "CMGA_CUSTOMERID";
		cmgaDbFields[ tableFldConstants.customerid.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.gradeproduct.ordinal() ].fieldName = "CMGA_GRADEPRODUCT";
		cmgaDbFields[ tableFldConstants.gradeproduct.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "CMGA_CORRECTIVEACTION";
		cmgaDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldName = "CMGA_PREVENTIVEACTION";
		cmgaDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.complaintdescription.ordinal() ].fieldName = "CMGA_COMPLAINTDESCRIPTION";
		cmgaDbFields[ tableFldConstants.complaintdescription.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.complaintdate.ordinal() ].fieldName = "CMGA_COMPLAINTDATE";
		cmgaDbFields[ tableFldConstants.complaintdate.ordinal() ].fieldType = 'D';

		cmgaDbFields[ tableFldConstants.manufacturedate.ordinal() ].fieldName = "CMGA_MANUFACTUREDATE";
		cmgaDbFields[ tableFldConstants.manufacturedate.ordinal() ].fieldType = 'D';

		cmgaDbFields[ tableFldConstants.gradespecification.ordinal() ].fieldName = "CMGA_GRADESPECIFICATION";
		cmgaDbFields[ tableFldConstants.gradespecification.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CMGA_FLID";
		cmgaDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CMGA_ELEMENTID";
		cmgaDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		
		cmgaDbFields[ tableFldConstants.source.ordinal() ].fieldName = "CMGA_SOURCE";
		cmgaDbFields[ tableFldConstants.source.ordinal() ].fieldType = 'C';
        
		cmgaDbFields[ tableFldConstants.defectid.ordinal() ].fieldName = "CMGA_DEFECTID";
		cmgaDbFields[ tableFldConstants.defectid.ordinal() ].fieldType = 'C';

		cmgaDbFields[ tableFldConstants.defectqty.ordinal() ].fieldName = "CMGA_DEFECTQTY";
		cmgaDbFields[ tableFldConstants.defectqty.ordinal() ].fieldType = 'N';

		
		cmgaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CMGA_TEMPFIELD1";
		cmgaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';


		cmgaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CMGA_TEMPFIELD2";
		cmgaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		cmgaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CMGA_TEMPFIELD3";
		cmgaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		cmgaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CMGA_ACTIVE";
		cmgaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cmgaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CMGA_CREATEDBY";
		cmgaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cmgaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CMGA_CREATEDON";
		cmgaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cmgaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CMGA_MODIFIEDON";
		cmgaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_COMPLAINTGALLERY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_COMPLAINTGALLERY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_COMPLAINTGALLERY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		return "SELECT * from " + TBL_QTM_TL_COMPLAINTGALLERY + " where CMGA_KEYID= ?";
	}

	public String getImgName(String keyid) {
		String sql = "select IMFL_FILENAME from GEN_TL_ALLMODULEIMGFILE where IMFL_REFKEYID='"+keyid+"'";
		CommonMessage.debugMsg("Sql "+sql);
		return sql;
	}

}

