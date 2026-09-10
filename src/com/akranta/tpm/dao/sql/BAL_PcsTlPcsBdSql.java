package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_BdmTlDtlSql.tableFldConstants;
import com.akranta.tpm.model.BAL_PcsTlPcsBd;

public class BAL_PcsTlPcsBdSql {
	
	public static final String TBL_PCS_TL_PCSBD = "PCS_TL_PCSBD";  

	TableFieldType [] pcsbdDbFields = null;
	
	public enum   tableFldConstants
	{
		 keyid,bdmKeyid,active,createdOn,createdBy;
	}

	public TableFieldType[] getPcsBdDbFields() {
		return pcsbdDbFields;
	}

	public BAL_PcsTlPcsBdSql()
	{
		pcsbdDbFields = new TableFieldType[ 5];
		for(int i = 0;i < 5; i++)
		{	
			pcsbdDbFields[ i ] = new TableFieldType();
		}
		pcsbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PBD_KEYID";
		pcsbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		pcsbdDbFields[ tableFldConstants.bdmKeyid.ordinal() ].fieldName = "PBD_BDM_KEYID";
		pcsbdDbFields[ tableFldConstants.bdmKeyid.ordinal() ].fieldType = 'V';
		
		pcsbdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PBD_ACTIVE";
		pcsbdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'V';
		
		pcsbdDbFields[ tableFldConstants.createdOn.ordinal() ].fieldName = "PBD_CREATEDON";
		pcsbdDbFields[ tableFldConstants.createdOn.ordinal() ].fieldType = 'D';
		
		pcsbdDbFields[ tableFldConstants.createdBy.ordinal() ].fieldName = "PBD_CREATEDBY";
		pcsbdDbFields[ tableFldConstants.createdBy.ordinal() ].fieldType = 'V';
		
		

	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_PCSBD, fieldTypeArr, dataArray);
	}
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		//PcsTlPcsBd newpcsbd= new PcsTlPcsBd();
		String sql=null;
		
		 sql = "DELETE from " + TBL_PCS_TL_PCSBD ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		System.out.println(sql+"in spcsbd");
		
			  return sql;
	}

}
