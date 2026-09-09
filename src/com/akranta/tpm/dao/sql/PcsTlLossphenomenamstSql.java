package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class PcsTlLossphenomenamstSql {

	public static final String TBL_PCS_TL_LOSSPHENOMENAMST = "PCS_TL_LOSSPHENOMENAMST";  

	TableFieldType [] plpmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, mainloss, tempfield1, tempfield2, tempfield3, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPlpmDbFields() {
		return plpmDbFields;
	}

	public PcsTlLossphenomenamstSql()
	{
		plpmDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			plpmDbFields[ i ] = new TableFieldType();
		}
		plpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PLPM_KEYID";
		plpmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "PLPM_NAME";
		plpmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.mainloss.ordinal() ].fieldName = "PLPM_MAINLOSS";
		plpmDbFields[ tableFldConstants.mainloss.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PLPM_TEMPFIELD1";
		plpmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PLPM_TEMPFIELD2";
		plpmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PLPM_TEMPFIELD3";
		plpmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PLPM_ACTIVE";
		plpmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		plpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PLPM_CREATEDBY";
		plpmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		plpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PLPM_CREATEDON";
		plpmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		plpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PLPM_MODIFIEDON";
		plpmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LOSSPHENOMENAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LOSSPHENOMENAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LOSSPHENOMENAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		
		return sql;
	}

	public List<PcsTlLossphenfactorylink> getPcsTlLossphenfactorylink() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	    public String getLinkCountSql(String plpmKeyid) {
	        return "SELECT COUNT(1) FROM pcs_tl_lossphenfactorylink WHERE PPFL_PLPM_KEYID = '" + plpmKeyid + "'";
	    }
	
	    
		public static String getLinkCountSqlByPhenomena(String plpmKeyId) {
		    return "SELECT COUNT(1) " +
		           "FROM pcs_tl_lossphenfactorylink " +
		           "WHERE ppfl_plpm_keyid = '" + plpmKeyId + "'";
		}



	public String getComboTextContent(String keyId, String type) {
		// TODO Auto-generated method stub
		String sql=" select PLPM_KEYID,KEYID,PLPM_NAME  from  pcs_tl_lossphenomenamst,pcs_vw_lossnames where PLPM_MAINLOSS=KEYID ";
		if(type.equals("PHENOMENA"))
			sql+=" and plpm_keyid='"+keyId+"'";
		else if(type.equals("LOSS"))
			sql+=" and PLPM_MAINLOSS='"+keyId+"'";
		CommonMessage.debugMsg("sql:"+sql);
		return sql;
	}
	
	
	





}

