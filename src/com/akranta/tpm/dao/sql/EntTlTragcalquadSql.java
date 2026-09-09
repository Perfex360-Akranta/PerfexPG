package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
public class EntTlTragcalquadSql{

	public static final String TBL_ENT_TL_TRGCALQUAD = "ENT_TL_TRGCALQUAD";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid,empm_keyid,empm_roleid,empm_topicid,flid, location,dmt, jh,currentlevel,
		currleveldate ,l1pass,l1date,l1trgcalid,l1remarks,l2pass,l2date,l2trgcalid,l2remarks,
		l3pass,l3date,l3updby,l3remarks,l4pass,l4date,l4updby,l4remarks,tempfield1, tempfield2,
		tempfield3, tempfield4,tempfield5, createdby,active,createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTragcalquadSql()
	{
		ftymDbFields = new TableFieldType[ 35 ];
		for(int i = 0;i < 35; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCQ_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "ETCQ_EMPM_KEYID";
		ftymDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.empm_roleid.ordinal() ].fieldName = "ETCQ_EMPM_ROLEID";
		ftymDbFields[ tableFldConstants.empm_roleid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.empm_topicid.ordinal() ].fieldName = "ETCQ_TOPICID";
		ftymDbFields[ tableFldConstants.empm_topicid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "ETCQ_FLID";
		ftymDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.location.ordinal() ].fieldName = "ETCQ_LOCATION";
		ftymDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.dmt.ordinal() ].fieldName = "ETCQ_DMT";
		ftymDbFields[ tableFldConstants.dmt.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.jh.ordinal() ].fieldName = "ETCQ_JH";
		ftymDbFields[ tableFldConstants.jh.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.currentlevel.ordinal() ].fieldName = "ETCQ_CURRENTLEVEL";
		ftymDbFields[ tableFldConstants.currentlevel.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.currleveldate.ordinal() ].fieldName = "ETCQ_CURRENTLEVELDATE";
		ftymDbFields[ tableFldConstants.currleveldate.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.l1pass.ordinal() ].fieldName = "ETCQ_L1PASS";
		ftymDbFields[ tableFldConstants.l1pass.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.l1date.ordinal() ].fieldName = "ETCQ_L1DATE";
		ftymDbFields[ tableFldConstants.l1date.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.l1trgcalid.ordinal() ].fieldName = "ETCQ_L1_TRGCALID";
		ftymDbFields[ tableFldConstants.l1trgcalid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.l1remarks.ordinal() ].fieldName = "ETCQ_L1REMARKS";
		ftymDbFields[ tableFldConstants.l1remarks.ordinal() ].fieldType = 'V';
        
		ftymDbFields[ tableFldConstants.l2pass.ordinal() ].fieldName = "ETCQ_L2PASS";
		ftymDbFields[ tableFldConstants.l2pass.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.l2date.ordinal() ].fieldName = "ETCQ_L2DATE";
		ftymDbFields[ tableFldConstants.l2date.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.l2trgcalid.ordinal() ].fieldName = "ETCQ_L2_TRGCALID";
		ftymDbFields[ tableFldConstants.l2trgcalid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.l2remarks.ordinal() ].fieldName = "ETCQ_L2REMARKS";
		ftymDbFields[ tableFldConstants.l2remarks.ordinal() ].fieldType = 'V';
		 
		ftymDbFields[ tableFldConstants.l3pass.ordinal() ].fieldName = "ETCQ_L3PASS";
		ftymDbFields[ tableFldConstants.l3pass.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.l3date.ordinal() ].fieldName = "ETCQ_L3DATE";
		ftymDbFields[ tableFldConstants.l3date.ordinal() ].fieldType = 'D';
       
		ftymDbFields[ tableFldConstants.l3updby.ordinal() ].fieldName = "ETCQ_L3_UPDBY";
		ftymDbFields[ tableFldConstants.l3updby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.l3remarks.ordinal() ].fieldName = "ETCQ_L3REMARKS";
		ftymDbFields[ tableFldConstants.l3remarks.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.l4pass.ordinal() ].fieldName = "ETCQ_L4PASS";
		ftymDbFields[ tableFldConstants.l4pass.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.l4date.ordinal() ].fieldName = "ETCQ_L4DATE";
		ftymDbFields[ tableFldConstants.l4date.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.l4updby.ordinal() ].fieldName = "ETCQ_L4_UPDBY";
		ftymDbFields[ tableFldConstants.l4updby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.l4remarks.ordinal() ].fieldName = "ETCQ_L4REMARKS";
		ftymDbFields[ tableFldConstants.l4remarks.ordinal() ].fieldType = 'V';
		
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETCQ_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETCQ_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETCQ_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';
		
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETCQ_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETCQ_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ETCQ_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
      
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCQ_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';
		 
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCQ_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCQ_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALQUAD, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGCALQUAD, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGCALQUAD ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

public static String selectData(String modiefiedid) {
		// TODO Auto-generated method stub
		String sql=null;
		//if(UIUtils.isValidKeyId(keyId)){
			sql= " select ETCQ_CURRENTLEVEL from ENT_TL_TRGCALQUAD where ETCQ_KEYID IN (" + modiefiedid + ")";
	
		//}
	    return sql;
	}
}

