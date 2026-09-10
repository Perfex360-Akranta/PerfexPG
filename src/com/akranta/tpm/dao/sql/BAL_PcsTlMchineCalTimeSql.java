package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_PcsTlPcsBdSql.tableFldConstants;

public class BAL_PcsTlMchineCalTimeSql {
	public static final String TBL_PCS_TL_MACHINECALTIME="PCS_TL_MACHINECALTIME";
	TableFieldType [] pcsMchCalDbFields = null;
	
	public enum fieldConstants
	{
		factoryid , sectionid ,cellid, machineid ,shiftid ,shiftdate,caltime                                                                                                                                                                                                                 

	}
	public TableFieldType [] getMchCalDbfields(){
		return pcsMchCalDbFields;
	}
	public BAL_PcsTlMchineCalTimeSql(){
		pcsMchCalDbFields = new TableFieldType[7];
		for(int i = 0;i < 7; i++)
		{	
			pcsMchCalDbFields[ i ] = new TableFieldType();
		}
		
	           
		pcsMchCalDbFields[fieldConstants.factoryid.ordinal()].fieldName="MCTM_FACTORYID";
		pcsMchCalDbFields[fieldConstants.factoryid.ordinal()].fieldType='V';
		
		pcsMchCalDbFields[fieldConstants.sectionid.ordinal()].fieldName="MCTM_SECTIONID";
		pcsMchCalDbFields[fieldConstants.sectionid.ordinal()].fieldType='V';
		
		pcsMchCalDbFields[fieldConstants.cellid.ordinal()].fieldName="MCTM_CELLID";
		pcsMchCalDbFields[fieldConstants.cellid.ordinal()].fieldType='V';
		
		pcsMchCalDbFields[fieldConstants.machineid.ordinal()].fieldName="MCTM_MACHINEID";
		pcsMchCalDbFields[fieldConstants.machineid.ordinal()].fieldType='V';
		
		pcsMchCalDbFields[fieldConstants.shiftid.ordinal()].fieldName="MCTM_SHIFTID";
		pcsMchCalDbFields[fieldConstants.shiftid.ordinal()].fieldType='V';
		
		pcsMchCalDbFields[fieldConstants.shiftdate.ordinal()].fieldName="MCTM_SHIFTDATE";
		pcsMchCalDbFields[fieldConstants.shiftdate.ordinal()].fieldType='V';
		
		pcsMchCalDbFields[fieldConstants.caltime.ordinal()].fieldName="MCTM_CALTIME";
		pcsMchCalDbFields[fieldConstants.caltime.ordinal()].fieldType='N';
		
	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_MACHINECALTIME, fieldTypeArr, dataArray);
	}
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		//PcsTlPcsBd newpcsbd= new PcsTlPcsBd();
		String sql=null;
		
		 sql = "DELETE from " + TBL_PCS_TL_MACHINECALTIME ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		System.out.println(sql+"in spcsbd");
		
			  return sql;
	}


}
