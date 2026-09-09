package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.MocRfcBasismstSql.tableFldConstants;

public class MocTeamConfigSql {

	public static final String TBL_MOC_TL_ROLECONFIGMST="MOC_TL_ROLECONFIGMST";  

	TableFieldType [] mctc_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,masterid,roleid,groupnum,initial,hazop,finalapp,empid,initialYN,inapprovedby,inapprovedate,inapprovestatus,
		inapproverem,inapproverwkflg,inapproverwkdte,inapproverwkrem,tempfield1,tempfield2,tempfield3,hazopYN,hzapprovedby,hzapprovedate,hzapprovestatus,
		hzapproverem,hzapproverwkflg,hzapproverwkdte,hzapproverwkrem,tempfield4,tempfield5,tempfield6,finalYN,faapprovedby,faapprovedate,faapprovestatus
		,faapproverem,faapproverwkflg,faapproverwkdte,faapproverwkrem,tempfield7,tempfield8,tempfield9,tempfield10,active,createdby,createdon,modifiedon
	
	}

	public TableFieldType[] getmctc_DbFields() {
		return mctc_DbFields;
	}

	public MocTeamConfigSql()
	{
		mctc_DbFields = new TableFieldType[ 46 ];
		for(int i = 0;i < 46; i++)
		{	
			mctc_DbFields[ i ] = new TableFieldType();
		}
		
		
		mctc_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOC_RCM_KEYID";
		mctc_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "MOC_RCM_RFCKEYID";
		mctc_DbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';
		
	
		
		mctc_DbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "MOC_RCM_ROLEID";
		mctc_DbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.groupnum.ordinal() ].fieldName = "MOC_RCM_GROUPNUM";
		mctc_DbFields[ tableFldConstants.groupnum.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.initial.ordinal() ].fieldName = "MOC_RCM_INITIALAPPROVAL";
		mctc_DbFields[ tableFldConstants.initial.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.hazop.ordinal() ].fieldName = "MOC_RCM_HAZOPAPPROVAL";
		mctc_DbFields[ tableFldConstants.hazop.ordinal() ].fieldType = 'V';     
		
		mctc_DbFields[ tableFldConstants.finalapp.ordinal() ].fieldName = "MOC_RCM_FINALAPPROVAL";
		mctc_DbFields[ tableFldConstants.finalapp.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.empid.ordinal() ].fieldName = "MOC_RCM_EMPID";
		mctc_DbFields[ tableFldConstants.empid.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.initialYN.ordinal() ].fieldName = "MOC_RCM_IAYN";
		mctc_DbFields[ tableFldConstants.initialYN.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.inapprovedby.ordinal() ].fieldName = "MOC_RCM_IABY";
		mctc_DbFields[ tableFldConstants.inapprovedby.ordinal() ].fieldType = 'V';
		
	
		
		mctc_DbFields[ tableFldConstants.inapprovedate.ordinal() ].fieldName = "MOC_RCM_IADATE";
		mctc_DbFields[ tableFldConstants.inapprovedate.ordinal() ].fieldType = 'D';
		
		mctc_DbFields[ tableFldConstants.inapprovestatus.ordinal() ].fieldName = "MOC_RCM_IASTATUS";
		mctc_DbFields[ tableFldConstants.inapprovestatus.ordinal() ].fieldType = 'C';
		
		mctc_DbFields[ tableFldConstants.inapproverem.ordinal() ].fieldName = "MOC_RCM_IAREMARKS";
		mctc_DbFields[ tableFldConstants.inapproverem.ordinal() ].fieldType = 'C';
		
		mctc_DbFields[ tableFldConstants.inapproverwkflg.ordinal() ].fieldName = "MOC_RCM_IARWKFLAG";
		mctc_DbFields[ tableFldConstants.inapproverwkflg.ordinal() ].fieldType = 'C';     
		
		mctc_DbFields[ tableFldConstants.inapproverwkdte.ordinal() ].fieldName = "MOC_RCM_IARWKDATE";
		mctc_DbFields[ tableFldConstants.inapproverwkdte.ordinal() ].fieldType = 'D';
		
		mctc_DbFields[ tableFldConstants.inapproverwkrem.ordinal() ].fieldName = "MOC_RCM_IARWKREMARKS";
		mctc_DbFields[ tableFldConstants.inapproverwkrem.ordinal() ].fieldType = 'V';	
		
		
	   mctc_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD1";
		mctc_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		mctc_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD2";
		mctc_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD3";
		mctc_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		
		
		
		mctc_DbFields[ tableFldConstants.hazopYN.ordinal() ].fieldName = "MOC_RCM_HZYN";
		mctc_DbFields[ tableFldConstants.hazopYN.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.hzapprovedby.ordinal() ].fieldName = "MOC_RCM_HZBY";
		mctc_DbFields[ tableFldConstants.hzapprovedby.ordinal() ].fieldType = 'V';
		
	
		
		mctc_DbFields[ tableFldConstants.hzapprovedate.ordinal() ].fieldName = "MOC_RCM_HZDATE";
		mctc_DbFields[ tableFldConstants.hzapprovedate.ordinal() ].fieldType = 'D';
		
		mctc_DbFields[ tableFldConstants.hzapprovestatus.ordinal() ].fieldName = "MOC_RCM_HZSTATUS";
		mctc_DbFields[ tableFldConstants.hzapprovestatus.ordinal() ].fieldType = 'C';
		
		mctc_DbFields[ tableFldConstants.hzapproverem.ordinal() ].fieldName = "MOC_RCM_HZREMARKS";
		mctc_DbFields[ tableFldConstants.hzapproverem.ordinal() ].fieldType = 'C';
		
		mctc_DbFields[ tableFldConstants.hzapproverwkflg.ordinal() ].fieldName = "MOC_RCM_HZRWKFLAG";
		mctc_DbFields[ tableFldConstants.hzapproverwkflg.ordinal() ].fieldType = 'C';     
		
		mctc_DbFields[ tableFldConstants.hzapproverwkdte.ordinal() ].fieldName = "MOC_RCM_HZRWKDATE";
		mctc_DbFields[ tableFldConstants.hzapproverwkdte.ordinal() ].fieldType = 'D';
		
		mctc_DbFields[ tableFldConstants.hzapproverwkrem.ordinal() ].fieldName = "MOC_RCM_HZRWKREMARKS";
		mctc_DbFields[ tableFldConstants.hzapproverwkrem.ordinal() ].fieldType = 'V';	
		
		mctc_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD4";
		mctc_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mctc_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD5";
		mctc_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD6";
		mctc_DbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';
		
		
		mctc_DbFields[ tableFldConstants.finalYN.ordinal() ].fieldName = "MOC_RCM_FAYN";
		mctc_DbFields[ tableFldConstants.finalYN.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.faapprovedby.ordinal() ].fieldName = "MOC_RCM_FABY";
		mctc_DbFields[ tableFldConstants.faapprovedby.ordinal() ].fieldType = 'V';
		
	
		
		mctc_DbFields[ tableFldConstants.faapprovedate.ordinal() ].fieldName = "MOC_RCM_FADATE";
		mctc_DbFields[ tableFldConstants.faapprovedate.ordinal() ].fieldType = 'D';
		
		mctc_DbFields[ tableFldConstants.faapprovestatus.ordinal() ].fieldName = "MOC_RCM_FASTATUS";
		mctc_DbFields[ tableFldConstants.faapprovestatus.ordinal() ].fieldType = 'C';
		
		mctc_DbFields[ tableFldConstants.faapproverem.ordinal() ].fieldName = "MOC_RCM_FAREMARKS";
		mctc_DbFields[ tableFldConstants.faapproverem.ordinal() ].fieldType = 'C';
		
		mctc_DbFields[ tableFldConstants.faapproverwkflg.ordinal() ].fieldName = "MOC_RCM_FARWKFLAG";
		mctc_DbFields[ tableFldConstants.faapproverwkflg.ordinal() ].fieldType = 'C';     
		
		mctc_DbFields[ tableFldConstants.faapproverwkdte.ordinal() ].fieldName = "MOC_RCM_FARWKDATE";
		mctc_DbFields[ tableFldConstants.faapproverwkdte.ordinal() ].fieldType = 'D';
		
		mctc_DbFields[ tableFldConstants.faapproverwkrem.ordinal() ].fieldName = "MOC_RCM_FARWKREMARKS";
		mctc_DbFields[ tableFldConstants.faapproverwkrem.ordinal() ].fieldType = 'V';	
		
		mctc_DbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD7";
		mctc_DbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		mctc_DbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD8";
		mctc_DbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';
		
		mctc_DbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD9";
		mctc_DbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';
		
		
		
		mctc_DbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "MOC_RCM_TEMPFIELD10";
		mctc_DbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';
		
		

		mctc_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOC_RCM_ACTIVE";
		mctc_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mctc_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOC_RCM_CREATEDBY";
		mctc_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mctc_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOC_RCM_CREATEDON";
		mctc_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mctc_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOC_RCM_MODIFIEDON";
		mctc_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_ROLECONFIGMST, fieldTypeArr, dataArray));
		System.out.println("to be cont...");
		return SqlUtils.getInsertSql(TBL_MOC_TL_ROLECONFIGMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_ROLECONFIGMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_ROLECONFIGMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String DeleteRoleConfigByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_ROLECONFIGMST;
	    sql += " WHERE MOC_RCM_RFCKEYID = '" + keyid + "'";
	    return sql;
	}


}

